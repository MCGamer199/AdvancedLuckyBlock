package com.LuckyBlock.War;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Events.Kits;
import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.Resources.ParticleEffect;
import com.LuckyBlock.Resources.SchedulerTask;
import com.LuckyBlock.Resources.TitleAPI;
import com.LuckyBlock.enums.ShopItems;
import com.LuckyBlock.enums.WarType;
import com.LuckyBlock.logic.Time;
import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.*;


@SuppressWarnings({"deprecation"})
public class War extends Arena {

    public static List<War> listWars = new ArrayList<War>();
    private static HashMap<UUID, UUID> endermans = new HashMap<UUID, UUID>();
    private Location lobby = null;
    private Location[] Spawns = new Location[512];
    private WarType type = WarType.DEFAULT;
    private List<UUID> Spectators = new ArrayList<UUID>();
    private Block[] blocks = new Block[512];
    private boolean removed = false;
    private String world = null;
    private Location firstpos;
    private Location secpos;
    private List<String> chests = new ArrayList<String>();
    private boolean registered = false;
    private String name;
    private Location center = null;
    private Time time = new Time();
    private Map<String, Integer> ps = new HashMap<String, Integer>();
    private Map<UUID, int[]> rewards = new HashMap<UUID, int[]>();
    private int[] dangerblocks = new int[256];
    private HashMap<String, int[]> lbs = new HashMap<String, int[]>();
    private boolean startWait;
    private Time startTime = new Time();
    private boolean allowGates;
    private boolean spawnFallingBlocks;
    private Time nextRefill = new Time();
    private int timetostart = 30;
    public War(int id) {
        this.id = id;
        type = WarType.DEFAULT;
    }


    public War(int id, WarType type) {
        this.id = id;
        this.type = type;
    }

    public static War getGame(int id) {
        War war = null;
        for (int x = 0; x < listWars.size(); x++) {
            if (listWars.get(x).getId() == id) {
                war = listWars.get(x);
            }
        }
        return war;
    }

    public static HashMap<UUID, UUID> getEndermans() {
        return endermans;
    }

    public static String getStaticMessage(War war, String loc) {
        String s = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString(loc));
        if (war != null) {
            s = s.replace("%ID%", "" + war.id);
            s = s.replace("%MaxPlayers%", "" + war.MaxPlayers);
            s = s.replace("%MinPlayers%", "" + war.MinPlayers);
            s = s.replace("%Name%", war.name);
            if (war.world != null) {
                s = s.replace("%World%", war.world);
            }
            s = s.replace("%AllowGates%", "" + war.allowGates);
            s = s.replace("%InGame%", "" + war.ingame);
            s = s.replace("%Enabled%", "" + war.isEnabled());
            s = s.replace("%Registered%", "" + war.registered);
            s = s.replace("%Removed%", "" + war.removed);
            s = s.replace("%StartWait%", "" + war.startWait);
            s = s.replace("%Type%", war.type.toString());
            s = s.replace("%StartTime%", "" + war.startTime.getTotal());
            s = s.replace("%Time%", "" + war.time.getTotal());
            s = s.replace("%TimeSec%", "" + war.time.getSecond());
            s = s.replace("%TimeMin%", "" + war.time.getMin());
            s = s.replace("%TimeHour%", "" + war.time.getHour());
        }
        return s;
    }

    public static boolean containPlayer(UUID uuid) {
        boolean g = false;
        for (War war : listWars) {
            if (war.getPlayers().contains(uuid)) {
                g = true;
            }
        }
        return g;
    }

    public static War getGame(UUID uuid) {
        War war = null;
        for (int x = 0; x < listWars.size(); x++) {
            if (listWars.get(x).containsPlayer(uuid)) {
                war = listWars.get(x);
            }
            if (listWars.get(x).containsSpectator(uuid)) {
                war = listWars.get(x);
            }
        }
        return war;
    }

    public static boolean containSpectator(UUID uuid) {
        War war = null;
        boolean is = false;
        if (getGame(uuid) != null) {
            war = getGame(uuid);
            if (war.getSpectators().contains(uuid)) {
                is = true;
            }
        }
        return is;
    }

    public boolean isSpawnFallingBlocks() {
        return spawnFallingBlocks;
    }

    public void setSpawnFallingBlocks(boolean spawnFallingBlocks) {
        this.spawnFallingBlocks = spawnFallingBlocks;
    }

    public Time getNextRefill() {
        return nextRefill;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public boolean isStartWait() {
        return startWait;
    }

    public void setStartWait(boolean startWait) {
        this.startWait = startWait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<UUID, int[]> getRewards() {
        return rewards;
    }

    public void SetPlayerSpawn(String name, int number) {
        ps.put(name, number);
    }

    public int getPlayerSpawn(UUID uuid) {
        int i = 0;
        if (ps.containsKey(uuid)) {
            i = ps.get(uuid);
        }
        return i;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void addTime(int amount) {
        time.addTime(amount);
    }

    public Location getCenter() {
        return center;
    }

    public void setCenter(Location center) {
        this.center = center;
        if (center != null) {
            LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".Center.world", center.getWorld().getName());
            LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".Center.x", center.getBlockX());
            LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".Center.y", center.getBlockY());
            LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".Center.z", center.getBlockZ());
            LuckyBlockAPI.saveConfigs();
        }
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world.getName();
    }

    public void setSpawn(int number, Location loc) {
        this.Spawns[number] = loc;
    }

    public UUID getPlayer(int number) {
        return this.Players.get(number);
    }

    public UUID getSpectator(int number) {
        return this.Spectators.get(number);
    }

    public Location[] getSpawns() {
        return this.Spawns;
    }

    public void setSpawns(List<Location> spawns) {
        for (int x = 0; x < spawns.size(); x++) {
            Spawns[x] = spawns.get(x);
        }
    }

    public boolean containsPlayer(UUID uuid) {
        boolean b = false;
        if (Players.contains(uuid)) {
            b = true;
        }
        return b;
    }

    public Location getFirstpos() {
        return firstpos;
    }

    public void setFirstpos(Location firstpos) {
        this.firstpos = firstpos;
    }

    public boolean containsSpectator(UUID uuid) {
        return Spectators.contains(uuid);
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
        LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".Registered", registered);
        LuckyBlockAPI.saveConfigs();
    }

    public Location getSecpos() {
        return secpos;
    }

    public void setSecpos(Location secpos) {
        this.secpos = secpos;
    }

    public void removeBlock(int number) {
        if (blocks[number] != null) {
            blocks[number] = null;
        } else {
            throw new NullPointerException("Block can't be found!");
        }
    }

    public void removeBlock(Block block) {
        for (int x = 0; x < blocks.length; x++) {
            if (blocks[x] != null) {
                String dim = LuckyBlockAPI.createDim(block);
                String d = LuckyBlockAPI.createDim(blocks[x]);
                if (dim.equalsIgnoreCase(d)) {
                    blocks[x] = null;
                }
            }
        }
    }

    public WarType getType() {
        return this.type;
    }

    public void setType(WarType type) {
        this.type = type;
    }

    public List<UUID> getSpectators() {
        return this.Spectators;
    }

    public void setSpectators(List<UUID> spectators) {
        Spectators = spectators;
    }

    public Location getLobby() {
        return this.lobby;
    }

    public void setLobby(Location loc) {
        this.lobby = loc;
    }

    public void addChest(String dim, String string, int inum) {
        chests.add(dim + "," + string + "," + inum);
    }

    public List<String> getChests() {
        return this.chests;
    }

    public void setChests(List<String> chests) {
        this.chests = chests;
    }

    public void leaveGame(UUID uuid, boolean end, String state) {
        if (Players.size() == 2) {
            for (UUID u : getHeighstPlayerReward(RewardType.KILLS)) {
                Player p = getPlayer(u);
                p.sendMessage(ChatColor.GREEN + "You got +50 Money and +15 XP for being the first killer");
                addReward(u, RewardType.MONEY, 50);
                addReward(u, RewardType.XP, 15);
            }
        }
        Player player = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId() == uuid) {
                player = p;
            }
        }
        if (state.equalsIgnoreCase("false") || state.equalsIgnoreCase("true")) {
            LuckyBlock.instance.replay(uuid);
        }
        if (state.equalsIgnoreCase("else")) {
            if (allowGates) {
                if (!ingame) {
                    if (ps.containsKey(player.getName())) {
                        Block block = Spawns[ps.get(player.getName())].getBlock();
                        int size = 1;
                        int size1 = 1;
                        int size2 = 2;
                        if (type == WarType.DEFAULT || type == WarType.INSANE) {
                            size = 1;
                        } else if (type == WarType.TEAMS) {
                            size = 2;
                        }
                        for (int x = size * -1; x < (size + 1); x++) {
                            for (int y = size2 * -1; y < (size2 + 1); y++) {
                                for (int z = size1 * -1; z < (size1 + 1); z++)
                                    block.getLocation().add(x, y, z).getBlock().setType(Material.AIR);
                            }
                        }
                    }
                }
            }
        }
        ScoreboardManager sc = Bukkit.getScoreboardManager();
        player.setScoreboard(sc.getNewScoreboard());
        if (ps.containsKey(player.getName())) {
            ps.remove(player.getName());
        }
        if (state.equalsIgnoreCase("false")) {
            player.sendMessage(getMessage("LoseGame"));
            if (end == false) {
                if (LuckyBlock.bukkitVersion[1] > 7) {
                    if (LuckyBlock.title) {
                        TitleAPI.sendTitle(player, 20, 60, 100,
                                ChatColor.RED + "" + ChatColor.BOLD + "You Died", ChatColor.GRAY + "" + ChatColor.BOLD + "You are a spectator now!");
                    }
                }
            }
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.getType() != Material.AIR) {
                    player.getWorld().dropItem(player.getLocation(), item);
                }
            }
            for (ItemStack item : player.getInventory().getArmorContents()) {
                if (item != null && item.getType() != Material.AIR) {
                    if (!(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && ChatColor.stripColor(item.getItemMeta().getDisplayName()).endsWith("HAT"))) {
                        player.getWorld().dropItem(player.getLocation(), item);
                    }
                }
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Players.contains(p.getUniqueId()) || Spectators.contains(p.getUniqueId())) {
                    if (player.getUniqueId() != p.getUniqueId()) {
                        if (Players.size() > 2) {
                            p.sendMessage(ChatColor.YELLOW + "Remaining Players: " + ChatColor.RED + (Players.size() - 1));
                            String msg = ChatColor.YELLOW + "Remaining Players: " + ChatColor.RED + (Players.size() - 1);
                            if (LuckyBlock.title) {
                                sendActionbarMessage(p, msg);
                            }
                        }
                    }
                }
            }
            if (LuckyBlock.bukkitVersion[1] > 7) {
                Location loc = player.getLocation().add(0, 300, 0);
                Location l = player.getLocation();
                ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                as.setVisible(false);
                String t = null;
                String tt = null;
                if (time.getMin() < 10) {
                    t = "0" + time.getMin();
                } else {
                    t = "" + time.getMin();
                }
                if (time.getSecond() < 10) {
                    tt = "0" + time.getSecond();
                } else {
                    tt = "" + time.getSecond();
                }
                as.setCustomName(ChatColor.BLUE + "" + ChatColor.BOLD + "X " + ChatColor.RED + ChatColor.BOLD + player.getName() + " " + ChatColor.YELLOW
                        + t + ":" + tt);
                as.setCustomNameVisible(true);
                as.setGravity(false);
                as.setSmall(true);
                as.setRemoveWhenFarAway(false);
                as.teleport(l);
            }
        }
        player.getInventory().clear();
        player.updateInventory();
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setWalkSpeed(0.2f);
        player.setFoodLevel(20);
        player.setAllowFlight(false);
        setArrowsInBody(player, (byte) 0);
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        if (end == true) {
            if (player.isDead() == false) {
                if (LuckyBlockAPI.inventories.containsKey(player.getUniqueId())) {
                    player.getInventory().setContents(LuckyBlockAPI.inventories.get(player.getUniqueId()));
                    if (LuckyBlockAPI.ArmorContents.containsKey(player.getUniqueId())) {
                        player.getInventory().setArmorContents(LuckyBlockAPI.ArmorContents.get(player.getUniqueId()));
                    }
                    player.updateInventory();
                }
                player.setExp(0);
                player.setLevel(0);
                if (LuckyBlockAPI.xp.containsKey(player.getUniqueId())) {
                    player.setExp(LuckyBlockAPI.xp.get(player.getUniqueId()));
                    LuckyBlockAPI.xp.remove(player.getUniqueId());
                }
                if (LuckyBlockAPI.level.containsKey(player.getUniqueId())) {
                    player.setLevel(LuckyBlockAPI.level.get(player.getUniqueId()));
                    LuckyBlockAPI.level.remove(player.getUniqueId());
                }
                if (end == true) {
                    int mo = getReward(uuid, RewardType.MONEY);
                    int xp = getReward(uuid, RewardType.XP);
                    if (Players.size() == 1) {
                        if (state.equalsIgnoreCase("true")) {
                            int amount = 90 * LuckyBlockAPI.multiply.get(uuid);
                            mo += amount;
                            xp += 20;
                            LuckyBlockAPI.addXP(player, xp);
                            LuckyBlockAPI.addMoney(player, mo);
                        }
                    }
                    if (ingame) {
                        player.sendMessage(ChatColor.GREEN + "You got a total of " + ChatColor.GOLD + mo + ChatColor.GREEN + " money!");
                        player.sendMessage(ChatColor.GREEN + "You got a total of " + ChatColor.GOLD + xp + ChatColor.GREEN + " xp!");
                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                    }
                }
                LuckyBlockAPI.saveExp(player);
                player.setGameMode(GameMode.ADVENTURE);
                if (LuckyBlockAPI.mainlobby != null) {
                    player.setFallDistance(0);
                    player.teleport(LuckyBlockAPI.mainlobby);
                    player.setFallDistance(0);
                } else {
                    player.sendMessage(getMessage("InvalidLobby"));
                }
            }
        }
        if (end == false) {
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 0));
            Spectators.add(uuid);
            List<String> l = new ArrayList<String>();
            for (int x = 0; x < Spectators.size(); x++) {
                l.add(Spectators.get(x).toString());
            }
            LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".DeadPlayers", l);
            LuckyBlockAPI.saveConfigs();
            player.sendMessage(getMessage("PlayerSpectator"));
            ItemStack compass = new ItemStack(Material.NETHER_STAR);
            ItemMeta compassM = compass.getItemMeta();
            compassM.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Players");
            List<String> list = new ArrayList<String>();
            list.add(ChatColor.GRAY + "Click to Open");
            compassM.setLore(list);
            compass.setItemMeta(compassM);
            player.getInventory().addItem(compass);
            player.updateInventory();
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 99999, 10));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 3));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 0));
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (containSpectator(p.getUniqueId())) {
                    p.hidePlayer(player);
                }
            }
            try {
                player.teleport(Spawns[0]);
            } catch (Exception ex) {
                player.sendMessage(getMessage("NoSpawnFound"));
            }
        }
        if (Players.contains(player.getUniqueId())) {
            Players.remove(player.getUniqueId());
            List<String> list = LuckyBlock.instance.game.getStringList(LuckyBlockAPI.getGameFile(id) + ".Players");
            for (int t = 0; t < list.size(); t++) {
                if (list.get(t).equalsIgnoreCase(player.getUniqueId().toString())) {
                    list.remove(t);
                }
            }
            LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".Players", list);
            LuckyBlockAPI.saveConfigs();
            if (!state.equalsIgnoreCase("end")) {
                if (ingame == true) {
                    if (Players.size() < 2) {
                        if (Players.size() == 1) {
                            UUID u = Players.get(0);
                            if (!LuckyBlockAPI.multiply.containsKey(u)) {
                                LuckyBlockAPI.multiply.put(u, 1);
                            }
                            if (LuckyBlockAPI.wins.containsKey(u)) {
                                LuckyBlockAPI.wins.put(u, LuckyBlockAPI.wins.get(u) + 1);
                            } else {
                                LuckyBlockAPI.wins.put(u, 1);
                            }
                            String name = null;
                            LuckyBlock.instance.w1005(this);
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getUniqueId() == u) {
                                    p.sendMessage(getMessage("WinGame.1"));
                                    name = p.getName();
                                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    ParticleEffect.FIREWORKS_SPARK.display(1.5f, 2f, 1.5f, 0, 350, p.getLocation(), 64);
                                    LuckyBlock.instance.w1003(this, p);
                                }
                            }
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getUniqueId() != u) {
                                    if (world != null) {
                                        String g = getMessage("WinGame.2");
                                        g = g.replace("%Player%", name);
                                        p.sendMessage(g);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            save();
            reloadSigns();
        }
    }

    public Map<String, Integer> getPs() {
        return ps;
    }

    public void StartGame() {
        if (world != null) {
            World wo = Bukkit.getWorld(world);
            wo.setAutoSave(false);
        }
        fillChests();
        if (world != null) {
            for (Entity e : Bukkit.getWorld(world).getEntities()) {
                if (!(e instanceof Player)) {
                    e.remove();
                }
            }
        }
        if (Players.size() < 2) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Players.contains(p.getUniqueId())) {
                    p.sendMessage(getMessage("GameEnded"));
                }
            }
            EndGame(false);
            return;
        }
        for (UUID uuid : Players) {
            if (LuckyBlockAPI.plays.containsKey(uuid)) {
                LuckyBlockAPI.plays.put(uuid, LuckyBlockAPI.plays.get(uuid) + 1);
            } else {
                LuckyBlockAPI.plays.put(uuid, 1);
            }
            LuckyBlockAPI.savePlayerData(uuid, null);
        }
        ingame = true;
        LuckyBlock.instance.game.set(getGameFile() + ".InGame", true);
        LuckyBlockAPI.saveConfigs();
        nextRefill.setSecond(120);
        LuckyBlock.instance.Loop(id);
        LuckyBlock.instance.Loop3(this);
        if (LuckyBlock.instance.config.getBoolean("Allow3dDrops")) {
            if (LuckyBlock.bukkitVersion[1] > 7) {
                LuckyBlock.instance.Loop2(this);
            }
        }
        if (this.world != null) {
            World world = Bukkit.getWorld(this.world);
            world.setStorm(false);
            world.setThundering(false);
            world.setDifficulty(Difficulty.HARD);
            for (Entity e : world.getEntities()) {
                if (e instanceof Item) {
                    e.remove();
                }
            }
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Players.contains(p.getUniqueId())) {
                if (!ps.containsKey(p.getName())) {
                    int x = 0;
                    for (int i : ps.values()) {
                        if (x == i) {
                            x++;
                        }
                    }
                    ps.put(p.getName(), x);
                }
            }
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Players.contains(p.getUniqueId())) {
                if (LuckyBlock.bukkitVersion[1] > 7) {
                    try {
                        TitleAPI.sendTitle(p, 20, 40, 80,
                                ChatColor.YELLOW + "" + ChatColor.BOLD + name, ChatColor.GREEN + "" + ChatColor.BOLD + "Try to survive");
                        sendActionbarMessage(p, ChatColor.GREEN + "Players: " + Players.size());
                    } catch (Exception ex) {
                        LuckyBlock.instance.getLogger().info("Title Manager is missed! Floating texts won't show up.");
                    }
                }
                EntityDamageEvent e = new EntityDamageEvent(p, DamageCause.CUSTOM, 0);
                p.setLastDamageCause(e);
                p.sendMessage(getMessage("GameStarted"));
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                for (PotionEffect effect : p.getActivePotionEffects()) {
                    p.removePotionEffect(effect.getType());
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
                p.getInventory().clear();
                if (allowGates == false) {
                    try {
                        p.teleport(Spawns[ps.get(p.getName())]);
                    } catch (Exception ex) {
                        p.sendMessage(getMessage("InvalidSpawn"));
                    }
                } else {
                    for (int i = 0; i < Spawns.length; i++) {
                        if (Spawns[i] != null) {
                            Block block = Spawns[i].getBlock();
                            int size = 1;
                            int size1 = 1;
                            int size2 = 2;
                            if (type == WarType.DEFAULT || type == WarType.INSANE) {
                                size = 1;
                            } else if (type == WarType.TEAMS) {
                                size = 2;
                            }
                            for (int x = size * -1; x < (size + 1); x++) {
                                for (int y = size2 * -1; y < (size2 + 1); y++) {
                                    for (int z = size1 * -1; z < (size1 + 1); z++)
                                        block.getLocation().add(x, y, z).getBlock().setType(Material.AIR);
                                }
                            }
                        }
                    }
                }
                p.setGameMode(GameMode.SURVIVAL);
                Kits.startGame(p);
                UUID uuid = p.getUniqueId();
                if (LuckyBlockAPI.maxHealth.containsKey(uuid)) {
                    if (LuckyBlockAPI.maxHealth.get(uuid) < 30) {
                        p.setMaxHealth(19 + LuckyBlockAPI.maxHealth.get(uuid));
                    } else {
                        p.setMaxHealth(20 + LuckyBlockAPI.maxHealth.get(uuid));
                    }
                    p.setLevel(0);
                    p.setExp(0);
                    p.setFoodLevel(20);
                    Damageable d = (Damageable) p;
                    p.setHealth(d.getMaxHealth());
                    if (LuckyBlockAPI.speedmine.containsKey(uuid)) {
                        int s = LuckyBlockAPI.speedmine.get(uuid);
                        if (s > 1) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 9999999, s - 2));
                        }
                    }
                    p.closeInventory();
                    List<ShopItems> bitems = LuckyBlockAPI.bitems.get(uuid);
                    if (bitems.contains(ShopItems.SATURATION_SKILL)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 9999999, 0));
                    }
                    if (bitems.contains(ShopItems.WATER_BREATHING_SKILL)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9999999, 0));
                    }
                    if (bitems.contains(ShopItems.FIRE_RESISTANCE_SKILL)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 0));
                    }
                    if (bitems.contains(ShopItems.BLOCKS)) {
                        p.getInventory().addItem(new ItemStack(Material.LOG, 16));
                    }
                    if (Hat.getSelected().get(uuid) != Hat.NONE) {
                        Hat hat = Hat.getSelected().get(uuid);
                        ItemStack item = new ItemStack(hat.getType());
                        ItemMeta itemM = item.getItemMeta();
                        String n = "";
                        if (hat == Hat.LEATHER_HAT) {
                            n = "ARCHER HAT";
                        } else {
                            n = hat.toString().replace("_", " ");
                        }
                        itemM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + n);
                        item.setItemMeta(itemM);
                        if (hat == Hat.ENDERMAN_HAT) {
                            if (LuckyBlock.bukkitVersion[1] > 7) {
                                item = ItemMaker.createSkull(item, "9f06dd5c-e04b-4cc1-b4e0-fc577f0562b0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5l" +
                                        "Y3JhZnQubmV0L3RleHR1cmUvN2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=");
                            } else {
                                SkullMeta sk = (SkullMeta) item.getItemMeta();
                                sk.setOwner("MHF_Enderman");
                                item.setItemMeta(sk);
                            }
                            item.setDurability((short) 3);
                        }
                        p.getInventory().setHelmet(item);
                        HatEvent(p, hat);
                        if (Hat.getSelected().get(uuid) == Hat.SNOW_HAT) {
                            SnowManEvent(p);
                        }
                        if (Hat.getSelected().get(uuid) == Hat.ENDERMAN_HAT) {
                            EndermanEvent(p);
                        }
                        if (Hat.getSelected().get(uuid) == Hat.HEROBRINE_HAT) {
                            addItems(p);
                        }
                    }
                }
            }
        }
        reloadSigns();
    }

    private void HatEvent(final Player player, final Hat hat) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            @Override
            public void run() {
                if (ingame && Players.contains(player.getUniqueId())) {
                    if (hat == Hat.COLORFUL_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            ItemStack item = player.getInventory().getHelmet();
                            short damage = item.getDurability();
                            if (damage < 15) {
                                damage++;
                            } else {
                                damage = 0;
                            }
                            item.setDurability(damage);
                            player.getInventory().setHelmet(item);
                        }
                    } else if (hat == Hat.GLASS_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            Item item = player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GLASS));
                            if (LuckyBlock.bukkitVersion[1] > 7) {
                                item.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD + "GLASS");
                                item.setCustomNameVisible(true);
                                item.setVelocity(item.getVelocity().multiply(2));
                                item.setPickupDelay(20000);
                                ItemEvent(item);
                            }
                        }
                    } else if (hat == Hat.HARD_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 0, true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 1, true));
                        }
                    } else if (hat == Hat.CAGE_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            Location l = new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2,
                                    player.getLocation().getZ());
                            ParticleEffect.FLAME.display(0.3f, 0.3f, 0.3f, 0, 8, l, 100);
                        }
                    } else if (hat == Hat.PUMPKIN_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                        }
                    } else if (hat == Hat.BRICK_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            ParticleEffect.VILLAGER_HAPPY.display(0.3f, 0.3f, 0.3f, 0, 8, player.getLocation(), 100);
                        }
                    } else if (hat == Hat.END_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            ParticleEffect.PORTAL.display(0.3f, 0.3f, 0.3f, 0, 8, player.getLocation(), 100);
                        }
                    }
                } else {
                    task.run();
                }
            }
        }, 10L, 10L));
    }

    private void SnowManEvent(final Player player) {
        final Snowman man = (Snowman) player.getWorld().spawnEntity(player.getLocation(), EntityType.SNOWMAN);
        man.setCustomName(ChatColor.WHITE + "" + ChatColor.BOLD + "Snowman " + ChatColor.GRAY + ChatColor.BOLD + ((int) man.getHealth()));
        man.setCustomNameVisible(true);
        man.setMaxHealth(10);
        man.setHealth(10);
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            @Override
            public void run() {
                if (!man.isValid() && Players.contains(player.getUniqueId())) {
                    task.run();
                }
                if (man.getLocation().getY() < 1) {
                    man.teleport(player);
                }
                man.setCustomName(ChatColor.WHITE + "" + ChatColor.BOLD + "Snowman " + ChatColor.GRAY + ChatColor.BOLD + ((int) man.getHealth()));
                if (man.getTarget() == null) {
                    for (Entity e : man.getNearbyEntities(10, 10, 10)) {
                        if (e instanceof LivingEntity) {
                            LivingEntity l = (LivingEntity) e;
                            if (!(l instanceof Snowman)) {
                                if (!(l instanceof Player)) {
                                    man.setTarget(l);
                                } else {
                                    Player p = (Player) l;
                                    if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                                        if (p.getUniqueId() != player.getUniqueId()) {
                                            man.setTarget(p);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                int x = 0;
                if (man.getTarget() != null) {
                    if (man.getWorld().getName().equalsIgnoreCase(man.getTarget().getWorld().getName())) {
                        if (man.getLocation().distance(man.getTarget().getLocation()) > 10 || man.getTarget().isValid() == false) {
                            man.setTarget(null);
                        } else {
                            x = 1;
                        }
                    } else {
                        x = 1;
                    }
                }
                if (x == 1) {
                    //Snowball ball = man.launchProjectile(Snowball.class);
                    //ball.setShooter(man);
                }
            }
        }, 5, 5));
    }

    private void EndermanEvent(final Player player) {
        final Enderman man = (Enderman) player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMAN);
        man.setMaxHealth(25);
        man.setHealth(25);
        endermans.put(man.getUniqueId(), player.getUniqueId());
        man.setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Enderman " + ChatColor.GRAY + ChatColor.BOLD + ((int) man.getHealth()));
        man.setCustomNameVisible(true);
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            @Override
            public void run() {
                if (!(man.isValid() && Players.contains(player.getUniqueId()))) {
                    task.run();
                    endermans.remove(man.getUniqueId());
                }
                if (man.getLocation().getY() < 1) {
                    man.teleport(player);
                }
                man.setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Enderman " + ChatColor.GRAY + ChatColor.BOLD + ((int) man.getHealth()));
                if (man.getTarget() != null) {
                    if (man.getTarget() instanceof Player) {
                        if (man.getTarget().getUniqueId() == player.getUniqueId()) {
                            man.setTarget(null);
                        }
                    }
                }
                if (man.getTarget() == null) {
                    for (Entity e : man.getNearbyEntities(25, 25, 25)) {
                        if (e instanceof LivingEntity) {
                            LivingEntity l = (LivingEntity) e;
                            if (!(l instanceof Player)) {
                                man.setTarget(l);
                            } else {
                                Player p = (Player) l;
                                if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                                    if (p.getUniqueId() != player.getUniqueId()) {
                                        man.setTarget(p);
                                    }
                                }
                            }
                        }
                    }
                }
                if (man.getTarget() != null) {
                    if (man.getWorld().getName().equalsIgnoreCase(man.getTarget().getWorld().getName())) {
                        if (man.getLocation().distance(man.getTarget().getLocation()) > 10 || man.getTarget().isValid() == false) {
                            man.setTarget(null);
                        }
                    }
                }
            }
        }, 5, 2));
    }

    public void EndGame(boolean win) {
        time.reset();
        ps.clear();
        for (String s : chests) {
            String[] d = s.split(",");
            String w = d[0];
            int x = Integer.parseInt(d[1]);
            int y = Integer.parseInt(d[2]);
            int z = Integer.parseInt(d[3]);
            Block b = Bukkit.getWorld(w).getBlockAt(x, y, z);
            if (b != null) {
                if (b.getType() == Material.CHEST) {
                    Chest chest = (Chest) b.getState();
                    chest.getInventory().clear();
                }
            }
        }
        startWait = false;
        List<UUID> list = new ArrayList<UUID>();
        for (int x = 0; x < Spectators.size(); x++) {
            list.add(Spectators.get(x));
        }
        if (world != null) {
            World w = Bukkit.getWorld(world);
            int x = 0;
            for (Entity e : w.getEntities()) {
                if (!(e instanceof Player || e instanceof ItemFrame)) {
                    e.remove();
                    x++;
                }
            }
            LuckyBlock.instance.getLogger().info("Removed " + x + " Entities!");
        }
        for (int x = 0; x < Players.size(); x++) {
            list.add(Players.get(x));
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Spectators.contains(p.getUniqueId())) {
                for (Player pp : Bukkit.getOnlinePlayers()) {
                    p.showPlayer(pp);
                }
                for (Player pp : Bukkit.getOnlinePlayers()) {
                    pp.showPlayer(p);
                }
            }
        }
        if (Players.size() > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Players.contains(p.getUniqueId())) {
                    if (p.getAllowFlight() == true) {
                        p.setAllowFlight(false);
                    }
                    for (Player pp : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(pp);
                    }
                    for (Player pp : Bukkit.getOnlinePlayers()) {
                        pp.showPlayer(p);
                    }
                    if (win) {
                        leaveGame(p.getUniqueId(), true, "true");
                    } else {
                        leaveGame(p.getUniqueId(), true, "else");
                    }
                    if (LuckyBlockAPI.mainlobby != null) {
                        p.teleport(LuckyBlockAPI.mainlobby);
                    } else {
                        p.sendMessage(getMessage("InvalidMainLobby"));
                    }
                }
                if (Spectators.contains(p.getUniqueId())) {
                    if (!p.isOp()) {
                        if (p.getAllowFlight() == true) {
                            p.setAllowFlight(false);
                        }
                    }
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                    p.getInventory().clear();
                    p.setMaxHealth(20);
                    p.setHealth(20);
                    p.setWalkSpeed(0.2f);
                    p.setFoodLevel(20);
                    p.setAllowFlight(false);
                    if (LuckyBlockAPI.inventories.containsKey(p.getUniqueId())) {
                        p.getInventory().setContents(LuckyBlockAPI.inventories.get(p.getUniqueId()));
                        if (LuckyBlockAPI.ArmorContents.containsKey(p.getUniqueId())) {
                            p.getInventory().setArmorContents(LuckyBlockAPI.ArmorContents.get(p.getUniqueId()));
                        }
                        p.updateInventory();
                    }
                    p.setExp(0);
                    p.setLevel(0);
                    if (LuckyBlockAPI.xp.containsKey(p.getUniqueId())) {
                        p.setExp(LuckyBlockAPI.xp.get(p.getUniqueId()));
                        LuckyBlockAPI.xp.remove(p.getUniqueId());
                    }
                    if (LuckyBlockAPI.level.containsKey(p.getUniqueId())) {
                        p.setLevel(LuckyBlockAPI.level.get(p.getUniqueId()));
                        LuckyBlockAPI.level.remove(p.getUniqueId());
                    }
                    LuckyBlockAPI.saveExp(p);
                    if (LuckyBlockAPI.mainlobby != null) {
                        p.teleport(LuckyBlockAPI.mainlobby);
                    } else {
                        p.sendMessage(getMessage("InvalidMainLobby"));
                    }
                }
            }
            Players.clear();
        }
        if (Spectators.size() > 0) {
            Spectators.clear();
        }
        ingame = false;
        rewards.clear();
        if (blocks != null) {
            reloadSigns();
        }
        save();
        for (int x = 0; x < 1000; x++) {
            //
        }
        rollback();
    }

    private void setArrowsInBody(Player player, byte amount) {
        CraftPlayer p = (CraftPlayer) player;
        p.getHandle().getDataWatcher().watch(9, new Byte(amount));
    }

    public String getGameFile() {
        String loc = null;
        for (String s : LuckyBlock.instance.game.getConfigurationSection("Games").getKeys(false)) {
            try {
                for (int x = 0; x < LuckyBlock.instance.game.getConfigurationSection("Games").getConfigurationSection(s).getKeys(false).size(); x++) {
                    if (LuckyBlock.instance.game.getConfigurationSection("Games").getConfigurationSection(s).getKeys(false).toArray()[x].toString().startsWith("ID")) {
                        if (LuckyBlock.instance.game.getConfigurationSection("Games").getConfigurationSection(s).getInt("ID") == id) {
                            x = LuckyBlock.instance.game.getConfigurationSection("Games").getConfigurationSection(s).getKeys(false).size();
                            loc = "Games." + s;
                        }
                    }
                }
            } catch (Exception ex) {
                //
            }
        }
        return loc;
    }

    public void reloadSigns() {
        for (Block block : blocks) {
            if (block != null) {
                if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                    Sign sign = (Sign) block.getState();
                    sign.setLine(0, ChatColor.DARK_GREEN + "LB:" + ChatColor.YELLOW + id);
                    if (name != null) {
                        sign.setLine(1, ChatColor.GOLD + "" + ChatColor.BOLD + name);
                    }
                    if (ingame) {
                        sign.setLine(3, ChatColor.DARK_PURPLE + "Ingame");
                        sign.update(true);
                        return;
                    }
                    sign.setLine(2, ChatColor.LIGHT_PURPLE + "" + Players.size() + ChatColor.WHITE
                            + "/" + ChatColor.LIGHT_PURPLE + MaxPlayers);
                    if (removed == false) {
                        if (Players.size() < MaxPlayers) {
                            if (isEnabled()) {
                                sign.setLine(3, ChatColor.GREEN + "Click To Join");
                            } else {
                                sign.setLine(3, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Disabled");
                            }
                        } else {
                            sign.setLine(3, ChatColor.DARK_RED + "Full!");
                        }
                    } else {
                        sign.setLine(3, ChatColor.RED + "Invalid Game!");
                    }
                    sign.update(true);
                }
            }
        }
    }

    public HashMap<String, int[]> getLbs() {
        return lbs;
    }

    public void addLB(String dim, int[] values) {
        lbs.put(dim, values);
    }

    public void addBlock(Block block) {
        boolean found = false;
        for (int x = 0; x < blocks.length; x++) {
            Block b = blocks[x];
            if (block == b) {
                found = true;
            }
        }
        if (found == false) {
            for (int x = 0; x < blocks.length; x++) {
                if (blocks[x] == null) {
                    blocks[x] = block;
                    x = blocks.length;
                }
            }
        }
    }

    public Block getBlock(int number) {
        Block block = null;
        if (blocks[number] != null) {
            block = blocks[number];
        } else {
            throw new NullPointerException("Block can't be found!");
        }
        return block;
    }

    public Block[] getBlocks() {
        return this.blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public boolean containsBlock(Block block) {
        boolean is = false;
        for (int x = 0; x < blocks.length; x++) {
            if (blocks[x] != null) {
                if (blocks[x] == block) {
                    is = true;
                }
            }
        }
        return is;
    }

    public boolean containsBlock(String dim) {
        boolean is = false;
        for (int x = 0; x < blocks.length; x++) {
            if (blocks[x] != null) {
                String d = LuckyBlockAPI.createDim(blocks[x]);
                if (dim.equalsIgnoreCase(d)) {
                    is = true;
                }
            }
        }
        return is;
    }

    public void remove() {
        removed = true;
        reloadSigns();
        String loc = LuckyBlockAPI.getGameFile(id);
        LuckyBlock.instance.game.set(loc, null);
        listWars.remove(this);
        LuckyBlockAPI.saveConfigs();
    }

    public int[] getDangerblocks() {
        return dangerblocks;
    }

    public void addDangerBlock(int id) {
        for (int x = 0; x < dangerblocks.length; x++) {
            if (dangerblocks[x] == 0) {
                dangerblocks[x] = id;
                x = dangerblocks.length;
            }
        }
    }

    public void addSpectator(UUID uuid) {
        Spectators.add(uuid);
    }

    public void fillChests() {
        for (String s : lbs.keySet()) {
            LuckyBlockAPI.saveLuckyBlock(s, lbs.get(s)[0], lbs.get(s)[1], "null", lbs.get(s)[2]);
        }
        HashMap<Integer, List<Chest>> fixedChests = new HashMap<Integer, List<Chest>>();
        for (int x = 0; x < chests.size(); x++) {
            String[] d = chests.get(x).split(",");
            World w = Bukkit.getWorld(d[0]);
            int xx = Integer.parseInt(d[1]);
            int y = Integer.parseInt(d[2]);
            int z = Integer.parseInt(d[3]);
            int g = Integer.parseInt(d[5]);
            Block block = w.getBlockAt(xx, y, z);
            if (block.getType() == Material.CHEST) {
                Chest chest = (Chest) block.getState();
                if (!fixedChests.containsKey(g)) {
                    fixedChests.put(g, new ArrayList<Chest>());
                }
                fixedChests.get(g).add(chest);
            }
        }
        for (int x = 0; x < chests.size(); x++) {
            String[] d = chests.get(x).split(",");
            World w = Bukkit.getWorld(d[0]);
            int xx = Integer.parseInt(d[1]);
            int y = Integer.parseInt(d[2]);
            int z = Integer.parseInt(d[3]);
            Block block = w.getBlockAt(xx, y, z);
            String lucky = d[4];
            int f = Integer.parseInt(d[5]);
            if (block.getType() == Material.CHEST) {
                Chest chest = (Chest) block.getState();
                Random random = new Random();
                Chest[] c = new Chest[512];
                for (int h = 0; h < fixedChests.get(f).size(); h++) {
                    c[h] = fixedChests.get(f).get(h);
                }
                if (lucky.equalsIgnoreCase("Normal")) {
                    for (int size = 0; size < random.nextInt(6) + 12; size++) {
                        ItemStack item = null;
                        int g = random.nextInt(100) + 1;
                        String s = null;
                        if (g < 10) {
                            item = new ItemStack(Material.WOOD);
                            item.setAmount((random.nextInt(4) + 1) * 16);
                        } else if (g > 9 && g < 20) {
                            item = new ItemStack(Material.STONE);
                            item.setAmount((random.nextInt(4) + 1) * 16);
                        } else if (g > 19 && g < 30) {
                            item = new ItemStack(Material.COOKED_BEEF);
                            item.setAmount((random.nextInt(3) + 1) * 8);
                        } else if (g > 29 && g < 37) {
                            int r = random.nextInt(4) + 298;
                            if (r == 298) {
                                for (Chest ch : c) {
                                    if (ch != null) {
                                        if (!(ch.getBlockInventory().contains(298) || ch.getBlockInventory().contains(302) || ch.getBlockInventory().contains(306) ||
                                                ch.getBlockInventory().contains(310) || ch.getBlockInventory().contains(314))) {
                                            item = new ItemStack(r);
                                        }
                                    }
                                }
                            } else if (r == 299) {
                                for (Chest ch : c) {
                                    if (ch != null) {
                                        if (!(ch.getBlockInventory().contains(299) || ch.getBlockInventory().contains(303) || ch.getBlockInventory().contains(307) ||
                                                ch.getBlockInventory().contains(311) || ch.getBlockInventory().contains(315))) {
                                            item = new ItemStack(r);
                                        }
                                    }
                                }
                            } else if (r == 300) {
                                for (Chest ch : c) {
                                    if (ch != null) {
                                        if (!(ch.getBlockInventory().contains(300) || ch.getBlockInventory().contains(304) || ch.getBlockInventory().contains(308) ||
                                                ch.getBlockInventory().contains(312) || ch.getBlockInventory().contains(316))) {
                                            item = new ItemStack(r);
                                        }
                                    }
                                }
                            } else if (r == 301) {
                                for (Chest ch : c) {
                                    if (ch != null) {
                                        if (!(ch.getBlockInventory().contains(301) || ch.getBlockInventory().contains(305) || ch.getBlockInventory().contains(309) ||
                                                ch.getBlockInventory().contains(313) || ch.getBlockInventory().contains(317))) {
                                            item = new ItemStack(r);
                                        }
                                    }
                                }
                            }
                        } else if (g > 36 && g < 40) {
                            item = new ItemStack(random.nextInt(4) + 314);
                        } else if (g > 39 && g < 48) {
                            item = new ItemStack(random.nextInt(4) + 272);
                        } else if (g > 47 && g < 53) {
                            item = new ItemStack(Material.DIAMOND);
                        } else if (g > 52 && g < 60) {
                            item = new ItemStack(Material.SNOW_BALL);
                            item.setAmount((random.nextInt(2) + 1) * 8);
                        } else if (g > 59 && g < 64) {
                            item = new ItemStack(Material.TNT);
                            item.setAmount(random.nextInt(3) + 1);
                            s = "TNT";
                        } else if (g > 63 && g < 70) {
                            item = new ItemStack(Material.EXP_BOTTLE);
                            item.setAmount(random.nextInt(5) + 4);
                        } else if (g > 69 && g < 75) {
                            item = new ItemStack(Material.BOW);
                            s = "BOW";
                        }
                        int h = random.nextInt(chest.getBlockInventory().getSize());
                        if (item != null) {
                            if (chest.getBlockInventory().getItem(h) == null) {
                                chest.getBlockInventory().setItem(h, item);
                            }
                        }
                        if (s != null) {
                            ItemStack i = new ItemStack(Material.STONE);
                            if (s.equalsIgnoreCase("TNT")) {
                                i.setType(Material.FLINT_AND_STEEL);
                            } else if (s.equalsIgnoreCase("BOW")) {
                                i.setType(Material.ARROW);
                                i.setAmount((random.nextInt(3) + 1) * 8);
                            }
                            chest.getBlockInventory().setItem(random.nextInt(chest.getBlockInventory().getSize()), i);
                        }
                    }
                } else if (lucky.equalsIgnoreCase("SemiLucky")) {
                    for (int size = 0; size < random.nextInt(8) + 10; size++) {
                        ItemStack item = null;
                        int g = random.nextInt(200) + 1;
                        String s = null;
                        if (g < 10) {
                            item = new ItemStack(Material.WOOD);
                            item.setAmount((random.nextInt(4) + 1) * 16);
                        } else if (g > 9 && g < 20) {
                            item = new ItemStack(Material.STONE);
                            item.setAmount((random.nextInt(4) + 1) * 16);
                        } else if (g > 19 && g < 30) {
                            item = new ItemStack(Material.COOKED_BEEF);
                            item.setAmount((random.nextInt(3) + 1) * 16);
                        } else if (g > 29 && g < 45) {
                            item = new ItemStack(random.nextInt(4) + 302);
                        } else if (g > 44 && g < 54) {
                            item = new ItemStack(random.nextInt(4) + 314);
                        } else if (g > 53 && g < 67) {
                            item = new ItemStack(random.nextInt(4) + 267);
                        } else if (g > 66 && g < 72) {
                            item = new ItemStack(Material.STONE_SWORD);
                        } else if (g > 71 && g < 80) {
                            item = new ItemStack(Material.SNOW_BALL);
                            item.setAmount((random.nextInt(2) + 1) * 8);
                        } else if (g > 79 && g < 85) {
                            item = new ItemStack(Material.TNT);
                            item.setAmount(random.nextInt(3) + 1);
                            s = "TNT";
                        } else if (g > 84 && g < 90) {
                            item = new ItemStack(Material.EXP_BOTTLE);
                            item.setAmount(random.nextInt(5) + 4);
                        } else if (g > 89 && g < 100) {
                            item = new ItemStack(Material.BOW);
                            ItemMeta itemM = item.getItemMeta();
                            itemM.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                            item.setItemMeta(itemM);
                            s = "BOW";
                        } else if (g > 99 && g < 110) {
                            item = new ItemStack(Material.GOLDEN_APPLE);
                            item.setAmount(1);
                        }
                        int h = random.nextInt(chest.getBlockInventory().getSize());
                        if (item != null) {
                            if (chest.getBlockInventory().getItem(h) == null) {
                                chest.getBlockInventory().setItem(h, item);
                            }
                        }
                        if (s != null) {
                            ItemStack i = new ItemStack(Material.STONE);
                            if (s.equalsIgnoreCase("TNT")) {
                                i.setType(Material.FLINT_AND_STEEL);
                            } else if (s.equalsIgnoreCase("BOW")) {
                                i.setType(Material.ARROW);
                                i.setAmount((random.nextInt(4) + 1) * 8);
                            }
                            chest.getBlockInventory().setItem(random.nextInt(chest.getBlockInventory().getSize()), i);
                        }
                    }
                } else if (lucky.equalsIgnoreCase("Lucky")) {
                    for (int size = 0; size < random.nextInt(8) + 10; size++) {
                        ItemStack item = null;
                        int g = random.nextInt(200) + 1;
                        String s = null;
                        if (g < 10) {
                            item = new ItemStack(Material.WOOD);
                            item.setAmount((random.nextInt(4) + 1) * 16);
                        } else if (g > 9 && g < 20) {
                            item = new ItemStack(Material.STONE);
                            item.setAmount((random.nextInt(4) + 1) * 16);
                        } else if (g > 19 && g < 30) {
                            item = new ItemStack(Material.COOKED_BEEF);
                            item.setAmount((random.nextInt(3) + 1) * 16);
                        } else if (g > 29 && g < 45) {
                            item = new ItemStack(random.nextInt(4) + 306);
                        } else if (g > 44 && g < 54) {
                            item = new ItemStack(random.nextInt(4) + 310);
                        } else if (g > 53 && g < 67) {
                            item = new ItemStack(random.nextInt(4) + 276);
                        } else if (g > 66 && g < 72) {
                            item = new ItemStack(Material.IRON_SWORD);
                        } else if (g > 71 && g < 80) {
                            item = new ItemStack(Material.SNOW_BALL);
                            item.setAmount((random.nextInt(2) + 1) * 8);
                        } else if (g > 79 && g < 85) {
                            item = new ItemStack(Material.TNT);
                            item.setAmount(random.nextInt(3) + 1);
                            s = "TNT";
                        } else if (g > 84 && g < 90) {
                            item = new ItemStack(Material.EXP_BOTTLE);
                            item.setAmount(random.nextInt(5) + 4);
                        } else if (g > 89 && g < 100) {
                            item = new ItemStack(Material.BOW);
                            ItemMeta itemM = item.getItemMeta();
                            itemM.addEnchant(Enchantment.ARROW_DAMAGE, random.nextInt(3) + 1, true);
                            item.setItemMeta(itemM);
                            s = "BOW";
                        } else if (g > 99 && g < 110) {
                            item = new ItemStack(Material.GOLDEN_APPLE);
                            item.setAmount(random.nextInt(3) + 1);
                        }
                        int h = random.nextInt(chest.getBlockInventory().getSize());
                        if (item != null) {
                            if (chest.getBlockInventory().getItem(h) == null) {
                                chest.getBlockInventory().setItem(h, item);
                            }
                        }
                        if (s != null) {
                            ItemStack i = new ItemStack(Material.STONE);
                            if (s.equalsIgnoreCase("TNT")) {
                                i.setType(Material.FLINT_AND_STEEL);
                            } else if (s.equalsIgnoreCase("BOW")) {
                                i.setType(Material.ARROW);
                                i.setAmount((random.nextInt(4) + 1) * 8);
                            }
                            chest.getBlockInventory().setItem(random.nextInt(chest.getBlockInventory().getSize()), i);
                        }
                    }
                }
            }
        }
    }

    public void spawnBoss(int health, String type) {
        if (type.equalsIgnoreCase("Golem")) {
            if (center != null && world != null) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Players.contains(p.getUniqueId())) {
                        p.sendMessage(getMessage("BossSpawned"));
                    }
                }
                IronGolem golem = (IronGolem) Bukkit.getWorld(world).spawnEntity(center, EntityType.IRON_GOLEM);
                golem.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lucky Iron Golem ");
                golem.setCustomNameVisible(true);
                golem.setPlayerCreated(false);
                golem.setMaxHealth(health);
                golem.setHealth(health);
                LuckyBlock.instance.Loop4(golem, this);
            }
        } else if (type.equalsIgnoreCase("Wither")) {
            if (world != null) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Players.contains(p.getUniqueId())) {
                        p.sendMessage(getMessage("BossSpawned"));
                    }
                }
                for (int x = 0; x < Spawns.length; x++) {
                    if (Spawns[x] != null) {
                        Wither wither = (Wither) Bukkit.getWorld(world).spawnEntity(Spawns[x], EntityType.WITHER);
                        wither.setMaxHealth(health);
                        wither.setHealth(health);
                        wither.setCustomName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Boss");
                    }
                }
            }
        }
    }

    public void save() {
        if (LuckyBlockAPI.getGameFile(id) != null) {
            String loc = LuckyBlockAPI.getGameFile(id);
            FileConfiguration file = LuckyBlock.instance.game;
            file.set(loc + ".ID", id);
            file.set(loc + ".InGame", ingame);
            file.set(loc + ".MaxPlayers", MaxPlayers);
            file.set(loc + ".MinPlayers", MinPlayers);
            file.set(loc + ".Enabled", isEnabled());
            file.set(loc + ".AllowGates", allowGates);
            file.set(loc + ".SpawnFallingBlocks", spawnFallingBlocks);
            file.set(loc + ".TimeToStart", timetostart);
            for (int x = 0; x < blocks.length; x++) {
                if (blocks[x] != null) {
                    Block block = blocks[x];
                    String[] d = LuckyBlockAPI.createDim(block).split(",");
                    file.set(loc + ".Blocks.Block" + (x + 1) + ".world", d[0]);
                    file.set(loc + ".Blocks.Block" + (x + 1) + ".x", Integer.parseInt(d[1]));
                    file.set(loc + ".Blocks.Block" + (x + 1) + ".y", Integer.parseInt(d[2]));
                    file.set(loc + ".Blocks.Block" + (x + 1) + ".z", Integer.parseInt(d[3]));
                }
            }
            List<String> l = new ArrayList<String>();
            for (String s : lbs.keySet()) {
                int[] i = lbs.get(s);
                l.add(s + " " + i[0] + " [random] " + i[2]);
            }
            file.set(loc + ".LuckyBlocks", l);
            if (chests.size() > 0) {
                file.set(loc + ".Chests", chests);
            }
            file.set(loc + ".Players", Players);
            file.set(loc + ".DeadPlayers", Spectators);
            file.set(loc + ".Name", name);
            file.set(loc + ".Type", type.toString());
            if (dangerblocks[0] != 0) {
                List<Integer> db = new ArrayList<Integer>();
                for (int x = 0; x < dangerblocks.length; x++) {
                    if (dangerblocks[x] > 0) {
                        db.add(dangerblocks[x]);
                    }
                }
                if (db.size() > 0) {
                    file.set(loc + ".DangerBlocks", db);
                }
            }
            List<Location> li = new ArrayList<Location>();
            for (int x = 0; x < Spawns.length; x++) {
                if (Spawns[x] != null) {
                    li.add(Spawns[x]);
                }
            }
            List<String> li1 = new ArrayList<String>();
            for (int x = 0; x < li.size(); x++) {
                String wor = li.get(x).getWorld().getName();
                double tx = li.get(x).getX();
                double ty = li.get(x).getY();
                double tz = li.get(x).getZ();
                li1.add(wor + "," + tx + "," + ty + "," + tz + "," + li.get(x).getPitch() + "," + li.get(x).getYaw());
            }
            file.set(loc + ".Spawns", li1);
            LuckyBlock.instance.game = file;
            LuckyBlockAPI.saveConfigs();
            if (world == null) {
                if (Spawns[0] != null) {
                    world = Spawns[0].getWorld().getName();
                }
            }
            reloadSigns();
            int found = 0;
            for (int x = 0; x < listWars.size(); x++) {
                if (listWars.get(x).getId() == id) {
                    found = 1;
                }
            }
            if (found == 0) {
                listWars.add(this);
            }
        } else {
            int s = 1;
            try {
                for (int x = 0; x < LuckyBlock.instance.game.getConfigurationSection("Games").getKeys(false).size(); x++) {
                    if (LuckyBlock.instance.game.getConfigurationSection("Games").getKeys(false).toArray()[x].toString().startsWith("Game")) {
                        String[] g = LuckyBlock.instance.game.getConfigurationSection("Games").getKeys(false).toArray()[x].toString().split("Game");
                        if (g.length > 1) {
                            try {
                                int nu = Integer.parseInt(g[1]);
                                if (nu >= s) {
                                    s = nu + 1;
                                }
                            } catch (NumberFormatException ex) {
                                s = 1;
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                //
            }
            LuckyBlock.instance.game.set("Games.Game" + s + ".ID", id);
            save();
            LuckyBlockAPI.saveConfigs();
        }
    }

    public int getReward(UUID uuid, RewardType rewardtype) {
        int m = 0;
        if (Players.contains(uuid) || Spectators.contains(uuid)) {
            if (rewards.containsKey(uuid)) {
                int[] i = rewards.get(uuid);
                m = i[rewardtype.id];
            } else {
                int[] i = new int[RewardType.size];
                rewards.put(uuid, i);
            }
        } else {
            throw new NullPointerException("Player can't be found!");
        }
        return m;
    }

    public void setReward(UUID uuid, RewardType rewardtype, int value) {
        if (rewards.containsKey(uuid)) {
            int[] i = rewards.get(uuid);
            i[rewardtype.id] = value;
            rewards.put(uuid, i);
        } else {
            int[] i = new int[RewardType.size];
            i[rewardtype.id] = value;
            rewards.put(uuid, i);
        }
    }

    public void addReward(UUID uuid, RewardType rewardtype, int amount) {
        if (rewards.containsKey(uuid)) {
            int[] i = rewards.get(uuid);
            i[rewardtype.id] += amount;
            rewards.put(uuid, i);
        } else {
            int[] i = new int[RewardType.size];
            i[rewardtype.id] += amount;
            rewards.put(uuid, i);
        }
    }

    void sendActionbarMessage(Player player, String message) {
        ActionbarTitleObject t = new ActionbarTitleObject(message);
        t.send(player);
    }

    public void startWait() {
        if (ingame || startWait) {
            return;
        }
        startTime.setSecond(0);
        startTime.addTime(timetostart);
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID uuid = p.getUniqueId();
            if (Players.contains(uuid)) {
                p.sendMessage(getMessage("TimeStarted.1"));
                startWait = true;
                p.setLevel((int) startTime.getTotal());
            }
        }
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            int i = 0;

            @Override
            public void run() {
                if (ingame == false) {
                    if (startTime.getTotal() == 10) {
                        if (i == 0) {
                            fillChests();
                            i = 1;
                        }
                    }
                    if (Players.size() > 0) {
                        if (startTime.getTotal() > 1) {
                            startTime.minTime(1);
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                UUID uuid = p.getUniqueId();
                                if (Players.contains(uuid)) {
                                    p.setLevel((int) startTime.getTotal());
                                }
                            }
                            if (startTime.getTotal() == 10) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    UUID uuid = p.getUniqueId();
                                    if (Players.contains(uuid)) {
                                        p.sendMessage(getMessage("TimeStarted.2"));
                                    }
                                }
                            }
                            if (startTime.getTotal() > 0 && startTime.getTotal() < 10) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    UUID uuid = p.getUniqueId();
                                    if (Players.contains(uuid)) {
                                        p.sendMessage(getMessage("TimeStarted.2"));
                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 2);
                                    }
                                }
                            }
                        } else {
                            StartGame();
                            startWait = false;
                            task.run();
                        }
                    } else {
                        startWait = false;
                        task.run();
                    }
                } else {
                    startWait = false;
                    task.run();
                }
            }
        }, 20L, 20L));
    }

    private void ItemEvent(final Item item) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncDelayedTask(LuckyBlock.instance, new Runnable() {
            @Override
            public void run() {
                if (item.isValid()) {
                    item.remove();
                }
                task.run();
            }
        }, LuckyBlock.randoms.nextInt(100) + 80));
    }

    //Unloading maps, to rollback maps. Will delete all player builds until last server save
    private void unloadMap() {
        if (Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(world), false)) {
            LuckyBlock.instance.getLogger().info("Successfully unloaded " + world);
        } else {
            LuckyBlock.instance.getLogger().severe("Could not unload " + world);
        }
    }

    //Loading maps (MUST BE CALLED AFTER UNLOAD MAPS TO FINISH THE ROLLBACK PROCESS)
    private void loadMap() {
        Bukkit.getServer().createWorld(new WorldCreator(world));
    }

    //Maprollback method, because were too lazy to type 2 lines
    public void rollback() {
        if (world != null) {
            unloadMap();
            loadMap();
            for (int x = 0; x < chests.size(); x++) {
                String[] d = chests.get(x).split(",");
                Block b = Bukkit.getWorld(d[0]).getBlockAt(Integer.parseInt(d[1]), Integer.parseInt(d[2]), Integer.parseInt(d[3]));
                if (b.getType() == Material.CHEST) {
                    Chest chest = (Chest) b.getState();
                    chest.getBlockInventory().clear();
                }
            }
        } else {
            throw new NullPointerException("Can't rollback null world!");
        }
    }

    public boolean isAllowGates() {
        return allowGates;
    }

    public void setAllowGates(boolean allowGates) {
        this.allowGates = allowGates;
    }

    public String getMessage(String loc) {
        String s = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString(loc));
        s = s.replace("%ID%", "" + id);
        s = s.replace("%MaxPlayers%", "" + MaxPlayers);
        s = s.replace("%MinPlayers%", "" + MinPlayers);
        s = s.replace("%Name%", name);
        if (world != null) {
            s = s.replace("%World%", world);
        }
        s = s.replace("%AllowGates%", "" + allowGates);
        s = s.replace("%InGame%", "" + ingame);
        s = s.replace("%Enabled%", "" + isEnabled());
        s = s.replace("%Registered%", "" + registered);
        s = s.replace("%Removed%", "" + removed);
        s = s.replace("%StartWait%", "" + startWait);
        s = s.replace("%Type%", type.toString());
        s = s.replace("%StartTime%", "" + startTime.getTotal());
        s = s.replace("%Time%", "" + time.getTotal());
        s = s.replace("%TimeSec%", "" + time.getSecond());
        s = s.replace("%TimeMin%", "" + time.getMin());
        s = s.replace("%TimeHour%", "" + time.getHour());
        return s;
    }

    public void addSpawn(Location loc) {
        for (int x = 0; x < Spawns.length; x++) {
            if (Spawns[x] == null) {
                Spawns[x] = loc;
                x = Spawns.length;
            }
        }
    }

    public List<UUID> getHeighstPlayerReward(RewardType type) {
        int h = 0;
        List<UUID> list = new ArrayList<UUID>();
        for (UUID uuid : Players) {
            if (getReward(uuid, type) > h) {
                h = getReward(uuid, type);
            }
        }
        for (UUID uuid : Spectators) {
            if (getReward(uuid, type) > h) {
                h = getReward(uuid, type);
            }
        }
        if (h > 0) {
            for (UUID uuid : rewards.keySet()) {
                if (rewards.get(uuid)[type.id] == h) {
                    list.add(uuid);
                }
            }
        }
        return list;
    }

    public int getHeighstReward(RewardType type) {
        int h = 0;
        for (UUID uuid : Players) {
            if (getReward(uuid, type) > h) {
                h = getReward(uuid, type);
            }
        }
        for (UUID uuid : Spectators) {
            if (getReward(uuid, type) > h) {
                h = getReward(uuid, type);
            }
        }
        return h;
    }

    public List<Player> getPlayerList() {
        List<Player> list = new ArrayList<Player>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Players.contains(p.getUniqueId()) || Spectators.contains(p.getUniqueId())) {
                list.add(p);
            }
        }
        return list;
    }

    public Player getPlayer(UUID uuid) {
        Player player = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId() == uuid) {
                player = p;
            }
        }
        return player;
    }

    public void setTimetostart(int timetostart) {
        this.timetostart = timetostart;
    }

    private void addItems(Player player) {
    /*
    ItemStack item = ItemMaker.createItem(Material.GHAST_TEAR, 4, 0, ChatColor.YELLOW + "" + ChatColor.BOLD
    + "Herobrine's Tear", Arrays.asList(ChatColor.GRAY + "Right click to vanish yourself"));
    ItemStack item1 = ItemMaker.createItem(Material.SUGAR, 3, 0, ChatColor.YELLOW + "" + ChatColor.BOLD
    + "Herobrine's Item", Arrays.asList(ChatColor.GRAY + "Right click to spawn hostile mobs"));
    player.getInventory().addItem(item);
    player.getInventory().addItem(item1);
    */
    }

    @Override
    public String toString() {
        return "War:" + id + "," + name + "," + world + "," + MaxPlayers + "," + MinPlayers;
    }

    public static enum RewardType {
        MONEY(0),
        XP(1),
        KILLS(2);
        public static int size = values().length;
        private int id;

        private RewardType(int id) {
            this.id = id;
        }
    }


}
