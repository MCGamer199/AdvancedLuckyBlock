package com.LuckyBlock.Engine;

import com.LuckyBlock.Commands.*;
import com.LuckyBlock.Enchantments.Glow;
import com.LuckyBlock.Enchantments.Lightning;
import com.LuckyBlock.Entities.CustomEntities;
import com.LuckyBlock.Entities.SuperSlime;
import com.LuckyBlock.Events.*;
import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.Resources.*;
import com.LuckyBlock.Structures.StructureFiles;
import com.LuckyBlock.War.Cage;
import com.LuckyBlock.War.MobAbility;
import com.LuckyBlock.War.MobAbility.ListMobs;
import com.LuckyBlock.War.War;
import com.LuckyBlock.enums.LuckySkin;
import com.LuckyBlock.enums.WarType;
import com.LuckyBlock.logic.Range;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.EulerAngle;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


/**
 * <b>Lucky Block</b>
 * <p>
 * This library was created by @MCGamer199 for Lucky Block
 * <p>
 * You can use it and modify it under the following conditions:
 * <ul>
 * <li>Don't claim this class as your own
 * <li>Don't remove this disclaimer
 * </ul>
 * <p>
 * <i>The main Class</i>
 *
 * @author MCGamer199
 * @version 1.9.2
 */


@SuppressWarnings({"unused", "deprecation"})
public class LuckyBlock extends JavaPlugin implements Listener {

//  Lucky Block Engine  //

    public static final String pluginname = "LuckyBlock";
    public static final String version = "1.9.2";

    public static LuckyBlock instance;
    public static byte[] bukkitVersion = new byte[2];
    public static Random randoms = new Random();
    public static HashMap<String, Boolean> cblocks = new HashMap<String, Boolean>();
    public static HashMap<UUID, Integer> gifts = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Boolean> strong = new HashMap<UUID, Boolean>();
    public static HashMap<UUID, Integer> ex = new HashMap<UUID, Integer>();
    public static List<String> snowmove = new ArrayList<String>();
    public static List<String> colors1 = new ArrayList<String>();
    public static List<String> colors = new ArrayList<String>();
    public static HashMap<UUID, List<Location>> c4 = new HashMap<UUID, List<Location>>();
    public static Glow glow = new Glow(23);
    public static Lightning lightning = new Lightning(25);
    public static File[] Items = new File[256];
    public static List<Types> lbs = new ArrayList<Types>();
    public static boolean title = false;
    public static HashMap<ItemStack, Integer> its = new HashMap<ItemStack, Integer>();
    public static HashMap<ItemStack, Short> its1 = new HashMap<ItemStack, Short>();
    public static String rules = "";
    public File configf = new File(getDataFolder() + File.separator + "config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(configf);
    public File lbfF = new File(getDataFolder() + File.separator + "Values/Default/file1.yml");
    public FileConfiguration lbf = YamlConfiguration.loadConfiguration(lbfF);
    public File lbfile1F = new File(getDataFolder() + File.separator + "Values/Default/file2.yml");
    public FileConfiguration lbfile1 = YamlConfiguration.loadConfiguration(lbfile1F);
    public File file1F = new File(getDataFolder() + File.separator + "Values/Default/file3.yml");
    public FileConfiguration file1 = YamlConfiguration.loadConfiguration(file1F);
    public File file2F = new File(getDataFolder() + File.separator + "Values/Default/file4.yml");
    public FileConfiguration file2 = YamlConfiguration.loadConfiguration(file2F);
    public File file3F = new File(getDataFolder() + File.separator + "Values/Default/file5.yml");
    public FileConfiguration file3 = YamlConfiguration.loadConfiguration(file3F);
    public File file4F = new File(getDataFolder() + File.separator + "Values/Default/file6.yml");
    public FileConfiguration file4 = YamlConfiguration.loadConfiguration(file4F);
    public File file5F = new File(getDataFolder() + File.separator + "Values/Default/file7.yml");
    public FileConfiguration file5 = YamlConfiguration.loadConfiguration(file5F);
    public File file6F = new File(getDataFolder() + File.separator + "Values/Default/file8.yml");
    public FileConfiguration file6 = YamlConfiguration.loadConfiguration(file6F);
    public File gameF = new File(getDataFolder() + File.separator + "Games.yml");
    public FileConfiguration game = YamlConfiguration.loadConfiguration(gameF);
    public File kitsF = new File(getDataFolder() + File.separator + "Kits.yml");
    public FileConfiguration kits = YamlConfiguration.loadConfiguration(kitsF);
    public File shopF = new File(getDataFolder() + File.separator + "Shop.yml");
    public FileConfiguration shop = YamlConfiguration.loadConfiguration(shopF);
    public File MessagesF = new File(getDataFolder() + File.separator + "Messages.yml");
    public FileConfiguration Messages = YamlConfiguration.loadConfiguration(MessagesF);
    public File flbF = new File(getDataFolder() + File.separator + "Types/LuckyBlock.yml");
    public FileConfiguration flb = YamlConfiguration.loadConfiguration(flbF);
    public File folder1 = new File(getDataFolder() + File.separator + "Types/");
    public File cgF = new File(getDataFolder() + File.separator + "Cages/DefaultCage.yml");
    public File folder2 = new File(getDataFolder() + File.separator + "Cages/");
    public FileConfiguration cg = YamlConfiguration.loadConfiguration(cgF);
    public File MainItemF = new File(getDataFolder() + File.separator + "Items/example.yml");
    public FileConfiguration MainItem = YamlConfiguration.loadConfiguration(MainItemF);
    public File Folder = new File(getDataFolder() + File.separator + "Items/");
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
    File dataF = new File(getDataFolder() + File.separator + "Data.yml");
    public FileConfiguration data = YamlConfiguration.loadConfiguration(dataF);
    private Server server;

    public static WorldGuardPlugin getWorldGuard() {
        Plugin plugin = LuckyBlock.instance.getServer().getPluginManager().getPlugin("WorldGuard");
        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }
        return (WorldGuardPlugin) plugin;
    }

    public static void patch() {
    /*
           try {
		      Method a = net.minecraft.server.v1_8_R1.EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
		      a.setAccessible(true);

		   } catch (Exception ignored) {
		      // Do some cleanup and error-handling here.
		   }*/
    }

    @Override
    public void onEnable() {
        getLogger().info(pluginname + " , " + version + " Enabled.");
        instance = this;
        Plugin plugin;
        patch();
        String b = Bukkit.getBukkitVersion();
        String[] d = b.replace(".", "&").split("&");
        bukkitVersion[0] = Byte.parseByte(d[0]);
        bukkitVersion[1] = Byte.parseByte(d[1].charAt(0) + "");
        getLogger().info("Your bukkit version is: " + bukkitVersion[0] + "." + bukkitVersion[1]);
        for (int x = 9; x > -1; x--) {
            colors.add("&" + x);
        }
        for (char c = 'a'; c < 'g'; c++) {
            colors.add("&" + c);
        }
        colors.add("&l");
        colors.add("&m");
        colors.add("&n");
        colors.add("&o");
        colors1.add("");
        colors1.add("");
        colors1.add("");
        colors1.add("&l");
        colors1.add("&m");
        colors1.add("&n");
        colors1.add("&o");
        LuckyBlockAPI.loadPortals();
        loadEnchantments();
        StructureFiles.loadFiles();
        LuckyBlockAPI.loadLuckyBlocks();
        MessagesLoader.loadMessages(Messages, MessagesF);
        this.server = getServer();
        LuckyBlockConfig();
        LuckyBlockCommand cmd = new LuckyBlockCommand();
        GameCommands gc = new GameCommands();
        TeamCommands tc = new TeamCommands();
        getCommand(config.getString("LuckyBlockCommand.Command")).setExecutor(cmd);
        getCommand(config.getString("LuckyBlockWarCommand.Command")).setExecutor(gc);
        getCommand(config.getString("TeamsCommand.Command")).setExecutor(tc);
        getCommand(config.getString("LuckyBlockCommand.Command")).setTabCompleter(new ConstructTabCompleter());
        getCommand(config.getString("LuckyBlockWarCommand.Command")).setTabCompleter(new ConstructTabCompleter1());
        getCommand(config.getString("TeamsCommand.Command")).setTabCompleter(new ConstructTabCompleter2());
        getServer().getPluginManager().registerEvents(new PlaceLuckyBlock(), instance);
        getServer().getPluginManager().registerEvents(new BreakLuckyBlock(), instance);
        getServer().getPluginManager().registerEvents(new SomeEvents(), instance);
        getServer().getPluginManager().registerEvents(new Game(), instance);
        getServer().getPluginManager().registerEvents(new PlayerListener(), instance);
        getServer().getPluginManager().registerEvents(new Kits(), instance);
        getServer().getPluginManager().registerEvents(new Gui(), instance);
        getServer().getPluginManager().registerEvents(new CustomEntities(), instance);
        getServer().getPluginManager().registerEvents(new SuperSlime(0), instance);
        getServer().getPluginManager().registerEvents(new LuckyBlockWorld(), instance);

        LuckyBlockAPI.loadExp();
        LuckyBlockAPI.loadData();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);
        closeInventories();
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                try {
                    String world = game.getString("Location.world");
                    double x = game.getDouble("Location.x");
                    double y = game.getDouble("Location.y");
                    double z = game.getDouble("Location.z");
                    float pitch = (float) game.getDouble("Location.pitch");
                    float yaw = (float) game.getDouble("Location.yaw");
                    Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                    LuckyBlockAPI.mainlobby = loc;
                } catch (Exception ex) {
                    //
                }
                try {
                    for (int x = 0; x < game.getConfigurationSection("Games").getKeys(false).size(); x++) {
                        for (int y = 0; y < LuckyBlock.instance.game.getConfigurationSection("Games").getConfigurationSection(
                                game.getConfigurationSection("Games").getKeys(false).toArray()[x].toString()).getKeys(false).size(); y++) {
                            if (LuckyBlock.instance.game.getConfigurationSection("Games").getConfigurationSection(game.getConfigurationSection("Games")
                                    .getKeys(false).toArray()[x].toString()).getKeys(false).toArray()[y].toString().startsWith("ID")) {
                                ConfigurationSection cs = game.getConfigurationSection("Games").getConfigurationSection(game.getConfigurationSection("Games")
                                        .getKeys(false).toArray()[x].toString());
                                boolean ig = cs.getBoolean("InGame");
                                int mp = cs.getInt("MaxPlayers");
                                int minp = cs.getInt("MinPlayers");
                                boolean isenabled = cs.getBoolean("Enabled");
                                int id = cs.getInt("ID");
                                War war = new War(id);
                                war.setMaxPlayers(mp);
                                war.setEnabled(isenabled);
                                war.setMinPlayers(minp);
                                war.setSpawnFallingBlocks(cs.getBoolean("SpawnFallingBlocks"));
                                if (cs.getString("Lobby") != null) {
                                    String lworld = cs.getString("Lobby.world");
                                    double lx = cs.getDouble("Lobby.x");
                                    double ly = cs.getDouble("Lobby.y");
                                    double lz = cs.getDouble("Lobby.z");
                                    float pitch = (float) cs.getDouble("Lobby.pitch");
                                    float yaw = (float) cs.getDouble("Lobby.yaw");
                                    Location loc = new Location(Bukkit.getWorld(lworld), lx, ly, lz, yaw, pitch);
                                    war.setLobby(loc);
                                }
                                List<String> u = cs.getStringList("Players");
                                List<UUID> uuids = new ArrayList<UUID>();
                                for (int i = 0; i < u.size(); i++) {
                                    uuids.add(UUID.fromString(u.get(i)));
                                }
                                List<String> uu = cs.getStringList("DeadPlayers");
                                List<UUID> uui = new ArrayList<UUID>();
                                for (int i = 0; i < uu.size(); i++) {
                                    uui.add(UUID.fromString(uu.get(i)));
                                }
                                war.setIngame(ig);
                                for (int xx = 0; xx < uuids.size(); xx++) {
                                    war.addPlayer(uuids.get(xx));
                                }
                                for (int xx = 0; xx < uui.size(); xx++) {
                                    war.addSpectator(uui.get(xx));
                                }
                                war.setTimetostart(cs.getInt("TimeToStart"));
                                if (cs.getString("FirstPos.world") != null) {
                                    Location l = new Location(Bukkit.getWorld(cs.getString("FirstPos.world")), cs.getInt("FirstPos.x"), cs.getInt("FirstPos.y"), cs.getInt("FirstPos.z"));
                                    war.setFirstpos(l);
                                }
                                if (cs.getString("SecPos.world") != null) {
                                    Location ll = new Location(Bukkit.getWorld(cs.getString("SecPos.world")), cs.getInt("SecPos.x"), cs.getInt("SecPos.y"), cs.getInt("SecPos.z"));
                                    war.setSecpos(ll);
                                }
                                List<Location> locs = new ArrayList<Location>();
                                List<String> spawns = cs.getStringList("Spawns");
                                for (int c = 0; c < spawns.size(); c++) {
                                    String[] d = spawns.get(c).split(",");
                                    Location loc = new Location(Bukkit.getWorld(d[0]), Double.parseDouble(d[1]), Double.parseDouble(d[2]), Double.parseDouble(d[3]));
                                    loc.setPitch(Float.parseFloat(d[4]));
                                    loc.setYaw(Float.parseFloat(d[5]));
                                    locs.add(loc);
                                }
                                for (int v = 0; v < locs.size(); v++) {
                                    war.setSpawn(v, locs.get(v));
                                }
                                if (war.getSpawns()[0] != null) {
                                    war.setWorld(war.getSpawns()[0].getWorld());
                                }
                                war.setAllowGates(cs.getBoolean("AllowGates"));
                                if (cs.getString("Center.world") != null) {
                                    Location center = new Location(Bukkit.getWorld(cs.getString("Center.world")), cs.getInt("Center.x"), cs.getInt("Center.y"), cs.getInt("Center.z"));
                                    war.setCenter(center);
                                }
                                for (int size = 0; size < cs.getConfigurationSection("Blocks").getKeys(false).size(); size++) {
                                    String cc = cs.getConfigurationSection("Blocks").getKeys(false).toArray()[size].toString();
                                    String world = cs.getString("Blocks." + cc + ".world");
                                    int dx = cs.getInt("Blocks." + cc + ".x");
                                    int dy = cs.getInt("Blocks." + cc + ".y");
                                    int dz = cs.getInt("Blocks." + cc + ".z");
                                    String dim = world + "," + dx + "," + dy + "," + dz;
                                    Block block = Bukkit.getWorld(world).getBlockAt(dx, dy, dz);
                                    war.addBlock(block);
                                }
                                if (cs.getString("Name") != null) {
                                    war.setName(cs.getString("Name"));
                                }
                                if (cs.getString("Type") != null) {
                                    war.setType(WarType.valueOf(cs.getString("Type")));
                                }
                                if (cs.getStringList("DangerBlocks").size() > 0) {
                                    List<String> li = cs.getStringList("DangerBlocks");
                                    for (int g = 0; g < li.size(); g++) {
                                        war.addDangerBlock(Integer.parseInt(li.get(g)));
                                    }
                                }
                                if (cs.getStringList("LuckyBlocks").size() > 0) {
                                    List<String> li = cs.getStringList("LuckyBlocks");
                                    for (int i = 0; i < li.size(); i++) {
                                        String[] d = li.get(i).split(" ");
                                        String[] dd = d[0].split(",");
                                        String w = dd[0];
                                        int xx = Integer.parseInt(dd[1]);
                                        int yy = Integer.parseInt(dd[2]);
                                        int zz = Integer.parseInt(dd[3]);
                                        String dim = w + "," + xx + "," + yy + "," + zz;
                                        int lc = Integer.parseInt(d[1]);
                                        int ch = 0;
                                        if (d[2].equalsIgnoreCase("[random]")) {
                                            ch = randoms.nextInt(Types.getFile(Types.fromId(1), 0).getStringList("Chest.Items." + Types.getFile(Types.fromId(1), 0)
                                                    .getConfigurationSection("Chest.Items").getKeys(false).toArray()[0].toString()).size());
                                        } else {
                                            ch = Integer.parseInt(d[2]);
                                        }
                                        int idd = Integer.parseInt(d[3]);
                                        int[] ii = new int[3];
                                        ii[0] = lc;
                                        ii[1] = ch;
                                        ii[2] = idd;
                                        war.addLB(dim, ii);
                                    }
                                }
                                War.listWars.add(war);
                                if (cs.getString("Registered") != null) {
                                    if (cs.getBoolean("Registered") == false) {
                                        registerChests(war);
                                    } else {
                                        if (cs.getStringList("Chests").size() > 0) {
                                            for (int i = 0; i < cs.getStringList("Chests").size(); i++) {
                                                String[] d = cs.getStringList("Chests").get(i).split(",");
                                                String dim = d[0] + "," + d[1] + "," + d[2] + "," + d[3];
                                                String lucky = d[4];
                                                int g = 0;
                                                if (d.length == 6) {
                                                    g = Integer.parseInt(d[5]);
                                                }
                                                war.addChest(dim, lucky, g);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    //
                }
                try {
                    for (int x = 0; x < data.getConfigurationSection("Teams").getKeys(false).size(); x++) {
                        for (int y = 0; y < data.getConfigurationSection("Teams").getConfigurationSection(
                                data.getConfigurationSection("Teams").getKeys(false).toArray()[x].toString()).getKeys(false).size(); y++) {
                            ConfigurationSection cs = data.getConfigurationSection("Teams").getConfigurationSection(data.getConfigurationSection("Teams")
                                    .getKeys(false).toArray()[x].toString());
                            Team team = new Team("", cs.getInt("ID"));
                            List<String> u = cs.getStringList("Players");
                            List<UUID> uuids = new ArrayList<UUID>();
                            for (int i = 0; i < u.size(); i++) {
                                uuids.add(UUID.fromString(u.get(i)));
                            }
                            team.setPlayers(uuids);
                            team.setName(cs.getString("Name"));
                            if (cs.getString("Owner") != null) {
                                String ownerS = cs.getString("Owner");
                                UUID owner = UUID.fromString(ownerS);
                                if (cs.getStringList("Requests") != null && cs.getStringList("Requests").size() > 0) {
                                    team.setRequests(cs.getStringList("Requests"));
                                }
                                if (cs.getStringList("Kicked") != null && cs.getStringList("Kicked").size() > 0) {
                                    team.setKicked(cs.getStringList("Kicked"));
                                }
                                if (cs.getStringList("Accepted") != null && cs.getStringList("Accepted").size() > 0) {
                                    team.setAccepted(cs.getStringList("Accepted"));
                                }
                            }
                            team.save();
                        }
                    }
                } catch (Exception ex) {
                    //
                }
            }
        }, 5L);
        MainItem.set("Type", "EMERALD");
        MainItem.set("Data", "none");
        MainItem.set("DisplayName", "&cEmerald");
        List<String> list = new ArrayList<String>();
        list.add("say HELLO!");
        MainItem.set("Actions.Action1.RightClick.RunCommands", list);
        List<String> l = new ArrayList<String>();
        l.add("say HELLO!");
        //MainItem.set("Actions.Action2.LeftClick.RunCommands", l);
        MainItem.options().header("This is an example for item creation.");
        MainItem.options().copyDefaults(true);
        try {
            MainItem.save(MainItemF);
        } catch (Exception ex) {
            //
        }
        File[] f = Folder.listFiles();
        for (int i = 0; i < f.length; i++) {
            if (i < 256) {
                Items[i] = f[i];
            }
        }
        for (int i = 0; i < Items.length; i++) {
            if (Items[i] != null) {
                File fileF = Items[i];
                FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
                try {
                    file.save(fileF);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        try {
            ConfigurationSection section = data.getConfigurationSection("Detectors");
            for (int x = 0; x < section.getKeys(false).size(); x++) {
                ConfigurationSection cs = section.getConfigurationSection(section.getKeys(false).toArray()[x].toString());
                Detector detector = null;
                for (int y = 0; y < cs.getKeys(false).size(); y++) {
                    String s = cs.getKeys(false).toArray()[y].toString();
                    if (s.equalsIgnoreCase("ID")) {
                        detector = new Detector(cs.getInt(s));
                    }
                    if (s.equalsIgnoreCase("Range")) {
                        int dx = cs.getInt("Range.x");
                        int dy = cs.getInt("Range.y");
                        int dz = cs.getInt("Range.z");
                        Range range = new Range(dx, dy, dz);
                        detector.setRange(range);
                    }
                    if (s.equalsIgnoreCase("Blocks")) {
                        List<String> bs = cs.getStringList(s);
                        for (int i = 0; i < bs.size(); i++) {
                            detector.addBlock(bs.get(i));
                        }
                    }
                    if (detector != null) {
                        LuckyBlockAPI.detectors.add(detector);
                    }
                }
            }
        } catch (Exception ex) {
            //
        }
        if (Bukkit.getPluginManager().getPlugin("TitleManager") != null) {
            title = true;
        }
        int num = 1;
        for (ListMobs mobs : ListMobs.values()) {
            MobAbility m = new MobAbility("MCGamer199", mobs, num);
            num++;
        }
    }

    @Override
    public void onDisable() {
        for (War war : War.listWars) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (war.getPlayers().contains(p.getUniqueId())) {
                    if (LuckyBlockAPI.mainlobby != null) {
                        p.teleport(LuckyBlockAPI.mainlobby);
                    } else {
                        p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                    }
                }
            }
            if (war.inGame()) {
                war.EndGame(false);
            }
        }
        War.listWars.clear();
        instance = null;
        getLogger().info(pluginname + " , " + version + " Disabled.");
    }

    public void playEffects(final Block block, final Location loc, long l, final int t) {
        Location lo = null;
        if (t == 0) {
            lo = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        } else {
            lo = new Location(loc.getWorld(), loc.getX() + 0.4, loc.getY() + 0.4, loc.getZ() + 0.4);
        }
        final String dim = LuckyBlockAPI.createDim(block);
        final Material m = block.getType();
        final Types type = Types.fromBlock(dim);
        final SchedulerTask task = new SchedulerTask();
        final Location loca = lo;
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int x = 0;

            @Override
            public void run() {
                Types type = null;
                for (Types t : lbs) {
                    if (LuckyBlockAPI.ids.containsKey(dim) && LuckyBlockAPI.ids.get(dim) == t.getId()) {
                        type = t;
                    }
                }
                if (type != null) {
                    if (block.getType() == m) {
                        if (type.getData() > -1) {
                            if (block.getData() != type.getData()) {
                                if (block.getType().isSolid()) {
                                    x = 1;
                                }
                            }
                        }
                    } else {
                        x = 1;
                    }
                    if (!LuckyBlockAPI.IsLuckyBlock(dim)) {
                        x = 1;
                    }
                } else {
                    x = 1;
                }
                if (x == 0) {
                    if (type.isAllowtickparticles()) {
                        try {
                            if (type.getTickparticles() != null) {
                                String particle = type.getTickparticles();
                                String[] part = particle.split(" ");
                                float rx = Float.parseFloat(part[1]);
                                float ry = Float.parseFloat(part[2]);
                                float rz = Float.parseFloat(part[3]);
                                float speed = Float.parseFloat(part[4]);
                                int amount = Integer.parseInt(part[5]);
                                int range = Integer.parseInt(part[6]);
                                ParticleEffect.valueOf(part[0].toUpperCase()).display(rx, ry, rz, speed, amount, loca, range);
                            }
                        } catch (Exception ex) {
                            //
                        }
                    }
                } else {
                    LuckyBlockAPI.removeLB(dim);
                    task.run();
                }
            }
        }, l, l));
    }

    public void Loops(final Block block) {
        final String dim = LuckyBlockAPI.createDim(block);
        final Material mat = block.getType();
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (block.getRelative(BlockFace.UP).getType() == Material.FIRE) {
                    if (Types.fromBlock(dim) != null && Types.fromBlock(dim).getAbilities().contains(BlockAbilities.RESISTANCE_EXPLOSIONS)) {
                        block.getRelative(BlockFace.UP).setType(Material.AIR);
                    } else {
                        task.run();
                    }
                }
            }
        }, 2L, 3L));
    }

    // Stuck //

    private void loadRecipes() {
        Bukkit.resetRecipes();
        for (Types type : lbs) {
            try {
                FileConfiguration f = type.getConfig();
                if (f.getBoolean("Craftable")) {
                    List<String> shape = f.getStringList("Recipe.Shape");
                    List<String> items = f.getStringList("Ingredients");
                    int amount = f.getInt("Recipe.Amount");
                    ItemStack item = ItemMaker.createItem(type.getType(), amount, type.getData(), type.getName(),
                            Arrays.asList(gold + "%0"));
                    ShapedRecipe sh = new ShapedRecipe(item);
                    sh.shape(new String[]{shape.get(0), shape.get(1), shape.get(2)});
                    for (int o = 0; o < items.size(); o++) {
                        String[] s = items.get(o).split(" ");
                        if (s.length > 1) {
                            sh.setIngredient(s[0].charAt(0), Material.getMaterial(s[1].toUpperCase()));
                        }
                    }
                    Bukkit.getServer().addRecipe(sh);
                }
            } catch (Exception ex) {
                //
            }
        }
    }

    // Tower //

    public void LuckyBlockConfig() {
        config.options().header("Lucky Block Config");
        List<String> worlds = new ArrayList<String>();
        config.addDefault("Allow.ExplosionGrief", true);
        config.addDefault("Allow.ExplosionFire", true);
        config.addDefault("Allow.ExplosionDamage", true);
        config.addDefault("Allow.SnowMoving", true);
        config.addDefault("UsePermission.placeluckyblock", false);
        config.addDefault("SpawnBosses", true);
        config.addDefault("UsePermission.breakluckyblock", false);
        config.addDefault("UsePermission.craftluckyblock", false);
        config.addDefault("SpawnRate", 0);
        config.addDefault("LuckyBlockCommand.Command", "lb");
        config.addDefault("LuckyBlockWarCommand.Command", "lbw");
        config.addDefault("TeamsCommand.Command", "teams");
        config.addDefault("Allow3dDrops", false);
        config.addDefault("CustomItems", false);
        config.addDefault("UseVersion-8", true);
        config.addDefault("AutoIgniteTnt", true);
        config.addDefault("LimitedArea", true);
        config.addDefault("AutoFuel", true);
        config.addDefault("AutoFillLapis", true);
        config.addDefault("InvincibleItems", false);
        config.addDefault("CreateExampleCages", true);
        config.addDefault("GravityForFruits", true);
        config.addDefault("SpawnFruits", false);
        config.addDefault("CreateExampleKits", true);
        config.addDefault("SnowMovementTime", 40);
        config.addDefault("SpawnWorlds", Arrays.asList("*All*"));
        config.addDefault("Rules", "Set the rules in the config file.");
        config.addDefault("Messages.NoPermission.breakluckyblock", "&cYou don't have permission to break lucky block!");
        config.addDefault("Messages.NoPermission.placeluckyblock", "&cYou don't have permission to place lucky block!");
        config.addDefault("Messages.NoPermission.craftluckyblock", "&cYou don't have permission to craft lucky block!");
        List<String> items = new ArrayList<String>();
        items.add("type:emerald,luck:11");
        items.add("type:diamond,luck:10");
        items.add("type:gold_ingot,luck:9");
        items.add("type:iron_ingot,luck:5");
        items.add("type:gold_nugget,luck:1");
        items.add("type:raw_chicken,luck:-1");
        items.add("type:rotten_flesh,luck:-2");
        items.add("type:poisonous_potato,luck:-3");
        items.add("type:spider_eye,luck:-4");
        items.add("type:raw_fish,data:3,luck:-5");
        items.add("type:fermented_spider_eye,luck:-3");
        items.add("type:quartz,luck:3");
        items.add("type:golden_apple,data:0,luck:30");
        items.add("type:golden_apple,data:1,luck:100");
        items.add("type:nether_star,luck:100");
        items.add("type:iron_barding,luck:6");
        items.add("type:gold_barding,luck:10");
        items.add("type:diamond_barding,luck:11");
        config.addDefault("Materials", items);
        if (config.getBoolean("CreateExampleKits")) {
            kits.addDefault("Kits.Kit1.Name", "Archer");
            kits.addDefault("Kits.Kit1.KitItem.Type", "BOW");
            kits.addDefault("Kits.Kit1.KitItem.DisplayName", "&6Archer Kit");
            kits.addDefault("Kits.Kit1.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Full Leather Armor", "&e1x Bow", "&e8x Arrow"));
            kits.addDefault("Kits.Kit1.Items.item1.Type", "LEATHER_HELMET");
            kits.addDefault("Kits.Kit1.Items.item2.Type", "LEATHER_CHESTPLATE");
            kits.addDefault("Kits.Kit1.Items.item3.Type", "LEATHER_LEGGINGS");
            kits.addDefault("Kits.Kit1.Items.item4.Type", "LEATHER_BOOTS");
            kits.addDefault("Kits.Kit1.Items.item5.Type", "BOW");
            kits.addDefault("Kits.Kit1.Items.item6.Type", "ARROW");
            kits.addDefault("Kits.Kit1.Items.item6.Amount", 8);
            kits.addDefault("Kits.Kit2.Name", "Knight");
            kits.addDefault("Kits.Kit2.VIP", true);
            kits.addDefault("Kits.Kit2.Show", "VIP");
            kits.addDefault("Kits.Kit2.KitItem.Type", "IRON_HELMET");
            kits.addDefault("Kits.Kit2.KitItem.DisplayName", "&9Knight Kit");
            kits.addDefault("Kits.Kit2.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Full Iron Armor", "&e1x Iron Sword", "&cVIP"));
            kits.addDefault("Kits.Kit2.Items.item1.Type", "IRON_HELMET");
            kits.addDefault("Kits.Kit2.Items.item2.Type", "IRON_CHESTPLATE");
            kits.addDefault("Kits.Kit2.Items.item3.Type", "IRON_LEGGINGS");
            kits.addDefault("Kits.Kit2.Items.item4.Type", "IRON_BOOTS");
            kits.addDefault("Kits.Kit2.Items.item5.Type", "IRON_SWORD");
            kits.addDefault("Kits.Kit3.Name", "Ecologist");
            kits.addDefault("Kits.Kit3.KitItem.Type", "IRON_AXE");
            kits.addDefault("Kits.Kit3.KitItem.DisplayName", "&5Ecologist Kit");
            kits.addDefault("Kits.Kit3.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Iron Axe", "&e16x Log"));
            kits.addDefault("Kits.Kit3.Items.item1.Type", "IRON_AXE");
            kits.addDefault("Kits.Kit3.Items.item2.Type", "LOG");
            kits.addDefault("Kits.Kit3.Items.item2.Amount", 16);
            kits.addDefault("Kits.Kit4.Name", "Tnt man Kit");
            kits.addDefault("Kits.Kit4.VIP", true);
            kits.addDefault("Kits.Kit4.Show", true);
            kits.addDefault("Kits.Kit4.KitItem.Type", "TNT");
            kits.addDefault("Kits.Kit4.KitItem.DisplayName", "&4Tnt man Kit");
            kits.addDefault("Kits.Kit4.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e10x Tnt", "&e1x Redstone Torch", "&e8x Redstone", "&cVIP"));
            kits.addDefault("Kits.Kit4.Items.item1.Type", "TNT");
            kits.addDefault("Kits.Kit4.Items.item1.Amount", 10);
            kits.addDefault("Kits.Kit4.Items.item2.Type", "REDSTONE_TORCH_ON");
            kits.addDefault("Kits.Kit4.Items.item3.Type", "REDSTONE");
            kits.addDefault("Kits.Kit4.Items.item3.Amount", 8);
            kits.addDefault("Kits.Kit5.Name", "Enchanter Kit");
            kits.addDefault("Kits.Kit5.VIP", true);
            kits.addDefault("Kits.Kit5.Show", true);
            kits.addDefault("Kits.Kit5.KitItem.Type", "ENCHANTMENT_TABLE");
            kits.addDefault("Kits.Kit5.KitItem.DisplayName", "&dEnchanter Kit");
            kits.addDefault("Kits.Kit5.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Enchantment Table", "&e16x Exp Bottle",
                    "&e8x Bookshelf", "&cVIP"));
            kits.addDefault("Kits.Kit5.Items.item1.Type", "ENCHANTMENT_TABLE");
            kits.addDefault("Kits.Kit5.Items.item2.Type", "EXP_BOTTLE");
            kits.addDefault("Kits.Kit5.Items.item2.Amount", 16);
            kits.addDefault("Kits.Kit5.Items.item3.Type", "BOOKSHELF");
            kits.addDefault("Kits.Kit5.Items.item3.Amount", 8);
            kits.addDefault("Kits.Kit6.Name", "Miner Kit");
            kits.addDefault("Kits.Kit6.VIP", true);
            kits.addDefault("Kits.Kit6.Show", true);
            kits.addDefault("Kits.Kit6.KitItem.Type", "IRON_PICKAXE");
            kits.addDefault("Kits.Kit6.KitItem.DisplayName", "&7Miner Kit");
            kits.addDefault("Kits.Kit6.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Iron Pickaxe", "&e32x Stone", "&cVIP"));
            kits.addDefault("Kits.Kit6.Items.item1.Type", "IRON_PICKAXE");
            kits.addDefault("Kits.Kit6.Items.item2.Type", "STONE");
            kits.addDefault("Kits.Kit6.Items.item2.Amount", 32);
            kits.addDefault("Kits.Kit7.Name", "Starter Kit");
            kits.addDefault("Kits.Kit7.KitItem.Type", "WOOD_AXE");
            kits.addDefault("Kits.Kit7.KitItem.DisplayName", "&aStarter Kit");
            kits.addDefault("Kits.Kit7.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Wooden Axe", "&e1x Wooden Pickaxe", "&e1x Wooden Shovel"));
            kits.addDefault("Kits.Kit7.Items.item1.Type", "WOOD_AXE");
            kits.addDefault("Kits.Kit7.Items.item2.Type", "WOOD_PICKAXE");
            kits.addDefault("Kits.Kit7.Items.item3.Type", "WOOD_SPADE");
            kits.addDefault("Kits.Kit8.Name", "Shears man Kit");
            kits.addDefault("Kits.Kit8.Show", true);
            kits.addDefault("Kits.Kit8.KitItem.Type", "MONSTER_EGG");
            kits.addDefault("Kits.Kit8.KitItem.Data", 91);
            kits.addDefault("Kits.Kit8.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Shears", "&e4x Sheep spawn egg"));
            kits.addDefault("Kits.Kit8.KitItem.DisplayName", "&f&lShears man Kit");
            kits.addDefault("Kits.Kit8.Items.item1.Type", "SHEARS");
            kits.addDefault("Kits.Kit8.Items.item2.Type", "MONSTER_EGG");
            kits.addDefault("Kits.Kit8.Items.item2.Amount", 4);
            kits.addDefault("Kits.Kit8.Items.item2.Data", 91);
            kits.addDefault("Kits.Kit9.Name", "Brewer Kit");
            kits.addDefault("Kits.Kit9.Show", true);
            kits.addDefault("Kits.Kit9.KitItem.Type", "POTION");
            kits.addDefault("Kits.Kit9.KitItem.Data", 1);
            kits.addDefault("Kits.Kit9.KitItem.Lore", Arrays.asList("&7Click to choose", "", "&e1x Potion of strength I for 30 sec",
                    "&e3x Potions of Healing II"));
            kits.addDefault("Kits.Kit9.KitItem.DisplayName", "&8Brewer Kit");
            kits.addDefault("Kits.Kit9.Items.item1.Type", "POTION");
            kits.addDefault("Kits.Kit9.Items.item1.PotionEffects", Arrays.asList("5 30 0"));
            kits.addDefault("Kits.Kit9.Items.item2.Type", "POTION");
            kits.addDefault("Kits.Kit9.Items.item2.PotionEffects", Arrays.asList("6 1.0 1"));
        }
        config.options().copyDefaults(true);
        data.options().header("This File Contains All Stored Data.");
        data.options().copyDefaults(true);
        kits.options().header("You can create custom kits here.");
        kits.options().copyDefaults(true);
        FileLoader.load(lbf, lbfF);
        FileLoader.load1(lbfile1, lbfile1F);
        FileLoader.load2(file1, file1F);
        FileLoader.load3(file2, file2F);
        FileLoader.load4(file3, file3F);
        FileLoader.load5(file4, file4F);
        FileLoader.load6(file5, file5F);
        FileLoader.load7(file6, file6F);
        flb.addDefault("ID", 1);
        flb.addDefault("Name", "&eLucky Block");
        flb.addDefault("Folder", "Default");
        flb.addDefault("WorksInCreative", true);
        flb.addDefault("UseNormalBlock", false);
        flb.addDefault("AllowPlaceSound", true);
        flb.addDefault("PlaceSound", "CREEPER_DEATH 1 0");
        flb.addDefault("AllowBreakSound", true);
        flb.addDefault("BreakSound", "ANVIL_LAND 1 2");
        flb.addDefault("AllowPlaceParticles", true);
        flb.addDefault("PlaceParticles", "SPELL_WITCH 0.4 0.5 0.4 0 450 0.4 0.3 0.4 20");
        flb.addDefault("AllowBreakParticles", true);
        flb.addDefault("BreakParticles", "REDSTONE 0.6 0.7 0.6 0 500 0.4 0.3 0.4 10");
        flb.addDefault("AllowTickParticles", true);
        flb.addDefault("TickParticles", "SPELL_MOB_AMBIENT 0.3 0.3 0.3 0 100 10");
        flb.addDefault("Type", "SPONGE");
        flb.addDefault("Data", -1);
        flb.addDefault("MinLuck", -200);
        flb.addDefault("MaxLuck", 200);
        flb.addDefault("Craftable", true);
        flb.addDefault("UseSkin", false);
        flb.addDefault("Skin", "LUCKY");
        flb.addDefault("Recipe.Amount", 1);
        flb.addDefault("Recipe.Shape", Arrays.asList("XXX", "XVX", "XXX"));
        flb.addDefault("Ingredients", Arrays.asList("X GOLD_INGOT", "V DROPPER"));
        flb.addDefault("Worlds", Arrays.asList("*All*"));
        flb.options().copyDefaults(true);
        flb.options().header("Lucky Block");

        cg.set("Name", "Default Cage");
        cg.set("Type", "STAINED_GLASS");
        cg.set("Data", 11);
        cg.set("DisplayName", "&9Default Cage");
        cg.set("Size.x", 3);
        cg.set("Size.y", 5);
        cg.set("Size.z", 3);
        cg.set("Buyable", false);
        cg.set("Default", true);
        cg.set("Cost", 0);

        File[] filess = new File[64];
        filess[0] = new File(getDataFolder() + File.separator + "Cages/Dirt.yml");
        filess[1] = new File(getDataFolder() + File.separator + "Cages/Ice.yml");
        filess[2] = new File(getDataFolder() + File.separator + "Cages/Mushroom.yml");
        filess[3] = new File(getDataFolder() + File.separator + "Cages/PackedIce.yml");
        filess[4] = new File(getDataFolder() + File.separator + "Cages/Leaves.yml");
        filess[5] = new File(getDataFolder() + File.separator + "Cages/Lucky.yml");
        filess[6] = new File(getDataFolder() + File.separator + "Cages/Glowstone.yml");
        filess[7] = new File(getDataFolder() + File.separator + "Cages/Netherrack.yml");
        filess[8] = new File(getDataFolder() + File.separator + "Cages/Gold.yml");
        filess[9] = new File(getDataFolder() + File.separator + "Cages/WhiteGlass.yml");
        filess[10] = new File(getDataFolder() + File.separator + "Cages/Redstone.yml");
        filess[11] = new File(getDataFolder() + File.separator + "Cages/Diamond.yml");
        filess[12] = new File(getDataFolder() + File.separator + "Cages/Tnt.yml");
        filess[13] = new File(getDataFolder() + File.separator + "Cages/Log.yml");
        filess[14] = new File(getDataFolder() + File.separator + "Cages/Pumpkin.yml");
        filess[15] = new File(getDataFolder() + File.separator + "Cages/OrangeGlass.yml");
        filess[16] = new File(getDataFolder() + File.separator + "Cages/MagentaGlass.yml");
        filess[17] = new File(getDataFolder() + File.separator + "Cages/LightBlueGlass.yml");
        filess[18] = new File(getDataFolder() + File.separator + "Cages/YellowGlass.yml");
        filess[19] = new File(getDataFolder() + File.separator + "Cages/LimeGlass.yml");
        filess[20] = new File(getDataFolder() + File.separator + "Cages/PinkGlass.yml");
        filess[21] = new File(getDataFolder() + File.separator + "Cages/GrayGlass.yml");
        filess[22] = new File(getDataFolder() + File.separator + "Cages/CyanGlass.yml");
        filess[23] = new File(getDataFolder() + File.separator + "Cages/PurpleGlass.yml");
        filess[24] = new File(getDataFolder() + File.separator + "Cages/BrownGlass.yml");
        filess[25] = new File(getDataFolder() + File.separator + "Cages/GreenGlass.yml");
        filess[26] = new File(getDataFolder() + File.separator + "Cages/RedGlass.yml");
        filess[27] = new File(getDataFolder() + File.separator + "Cages/BlackGlass.yml");
        filess[28] = new File(getDataFolder() + File.separator + "Cages/Emerald.yml");
        filess[29] = new File(getDataFolder() + File.separator + "Cages/LightGrayGlass.yml");
        filess[30] = new File(getDataFolder() + File.separator + "Cages/Sandstone.yml");
        FileConfiguration[] filesC = new FileConfiguration[64];
        for (int x = 0; x < filess.length; x++) {
            if (filess[x] != null) {
                filesC[x] = YamlConfiguration.loadConfiguration(filess[x]);
            }
        }
        filesC[0].set("Name", "Dirt Cage");
        filesC[0].set("Type", "DIRT");
        filesC[0].set("Data", 0);
        filesC[0].set("DisplayName", "&6Dirt Cage");
        filesC[0].set("Buyable", true);
        filesC[0].set("Default", false);
        filesC[0].set("Cost", 1500);

        filesC[1].set("Name", "Ice Cage");
        filesC[1].set("Type", "ICE");
        filesC[1].set("Data", 0);
        filesC[1].set("DisplayName", "&bIce Cage");
        filesC[1].set("Buyable", false);
        filesC[1].set("Default", false);
        filesC[1].set("Cost", 0);

        filesC[2].set("Name", "Mushroom Cage");
        filesC[2].set("Type", "HUGE_MUSHROOM_2");
        filesC[2].set("Data", 0);
        filesC[2].set("DisplayName", "&cMushroom Cage");
        filesC[2].set("Buyable", false);
        filesC[2].set("Default", false);
        filesC[2].set("Cost", 0);

        filesC[3].set("Name", "Packed Ice Cage");
        filesC[3].set("Type", "PACKED_ICE");
        filesC[3].set("Data", 0);
        filesC[3].set("DisplayName", "&3Packed Ice Cage");
        filesC[3].set("Buyable", true);
        filesC[3].set("Default", false);
        filesC[3].set("Cost", 5000);

        filesC[4].set("Name", "Leaves Cage");
        filesC[4].set("Type", "LEAVES");
        filesC[4].set("Data", 0);
        filesC[4].set("DisplayName", "&aLeaves Cage");
        filesC[4].set("Buyable", true);
        filesC[4].set("Default", false);
        filesC[4].set("Cost", 10000);

        filesC[5].set("Name", "Lucky Cage");
        filesC[5].set("Type", "SPONGE");
        filesC[5].set("Data", 0);
        filesC[5].set("DisplayName", "&eLucky Cage");
        filesC[5].set("Buyable", false);
        filesC[5].set("Default", false);
        filesC[5].set("Cost", 0);

        filesC[6].set("Name", "Glowing Cage");
        filesC[6].set("Type", "GLOWSTONE");
        filesC[6].set("Data", 0);
        filesC[6].set("DisplayName", "&6Glowing Cage");
        filesC[6].set("Buyable", true);
        filesC[6].set("Default", false);
        filesC[6].set("Cost", 8000);

        filesC[7].set("Name", "Nether Cage");
        filesC[7].set("Type", "NETHERRACK");
        filesC[7].set("Data", 0);
        filesC[7].set("DisplayName", "&dNether Cage");
        filesC[7].set("Buyable", true);
        filesC[7].set("Default", false);
        filesC[7].set("Cost", 25000);

        filesC[8].set("Name", "Gold Cage");
        filesC[8].set("Type", "GOLD_BLOCK");
        filesC[8].set("Data", 0);
        filesC[8].set("DisplayName", "&eGold Cage");
        filesC[8].set("Buyable", false);
        filesC[8].set("Default", false);
        filesC[8].set("Cost", 0);

        filesC[9].set("Name", "White Glass Cage");
        filesC[9].set("Type", "STAINED_GLASS");
        filesC[9].set("Data", 0);
        filesC[9].set("DisplayName", "&fWhite Glass Cage");
        filesC[9].set("Buyable", true);
        filesC[9].set("Default", false);
        filesC[9].set("Cost", 6000);

        filesC[10].set("Name", "Redstone Cage");
        filesC[10].set("Type", "REDSTONE_BLOCK");
        filesC[10].set("Data", 0);
        filesC[10].set("DisplayName", "&cRedstone Cage");
        filesC[10].set("Buyable", true);
        filesC[10].set("Default", false);
        filesC[10].set("Cost", 16000);

        filesC[11].set("Name", "Diamond Cage");
        filesC[11].set("Type", "DIAMOND_BLOCK");
        filesC[11].set("Data", 0);
        filesC[11].set("DisplayName", "&bDiamond Cage");
        filesC[11].set("Buyable", false);
        filesC[11].set("Default", false);
        filesC[11].set("Cost", 0);

        filesC[12].set("Name", "Tnt Cage");
        filesC[12].set("Type", "TNT");
        filesC[12].set("Data", 0);
        filesC[12].set("DisplayName", "&4&lTnt Cage");
        filesC[12].set("Buyable", true);
        filesC[12].set("Default", false);
        filesC[12].set("Cost", 15000);

        filesC[13].set("Name", "Log Cage");
        filesC[13].set("Type", "LOG");
        filesC[13].set("Data", 0);
        filesC[13].set("DisplayName", "&6Log Cage");
        filesC[13].set("Buyable", true);
        filesC[13].set("Default", false);
        filesC[13].set("Cost", 4000);

        filesC[14].set("Name", "Pumpkin Cage");
        filesC[14].set("Type", "PUMPKIN");
        filesC[14].set("Data", 0);
        filesC[14].set("DisplayName", "&6Pumpkin Cage");
        filesC[14].set("Buyable", true);
        filesC[14].set("Default", false);
        filesC[14].set("Cost", 9000);

        filesC[15].set("Name", "Orange Glass Cage");
        filesC[15].set("Type", "STAINED_GLASS");
        filesC[15].set("Data", 1);
        filesC[15].set("DisplayName", "&6Orange Glass Cage");
        filesC[15].set("Buyable", true);
        filesC[15].set("Default", false);
        filesC[15].set("Cost", 6000);

        filesC[16].set("Name", "Magenta Glass Cage");
        filesC[16].set("Type", "STAINED_GLASS");
        filesC[16].set("Data", 2);
        filesC[16].set("DisplayName", "&5Magenta Glass Cage");
        filesC[16].set("Buyable", true);
        filesC[16].set("Default", false);
        filesC[16].set("Cost", 6000);

        filesC[17].set("Name", "Light Blue Glass Cage");
        filesC[17].set("Type", "STAINED_GLASS");
        filesC[17].set("Data", 3);
        filesC[17].set("DisplayName", "&bLight Blue Glass Cage");
        filesC[17].set("Buyable", true);
        filesC[17].set("Default", false);
        filesC[17].set("Cost", 6000);

        filesC[18].set("Name", "Yellow Glass Cage");
        filesC[18].set("Type", "STAINED_GLASS");
        filesC[18].set("Data", 4);
        filesC[18].set("DisplayName", "&eYellow Glass Cage");
        filesC[18].set("Buyable", true);
        filesC[18].set("Default", false);
        filesC[18].set("Cost", 6000);

        filesC[19].set("Name", "Lime Glass Cage");
        filesC[19].set("Type", "STAINED_GLASS");
        filesC[19].set("Data", 5);
        filesC[19].set("DisplayName", "&aLime Glass Cage");
        filesC[19].set("Buyable", true);
        filesC[19].set("Default", false);
        filesC[19].set("Cost", 6000);

        filesC[20].set("Name", "Pink Glass Cage");
        filesC[20].set("Type", "STAINED_GLASS");
        filesC[20].set("Data", 6);
        filesC[20].set("DisplayName", "&dPink Glass Cage");
        filesC[20].set("Buyable", true);
        filesC[20].set("Default", false);
        filesC[20].set("Cost", 6000);

        filesC[21].set("Name", "Gray Glass Cage");
        filesC[21].set("Type", "STAINED_GLASS");
        filesC[21].set("Data", 7);
        filesC[21].set("DisplayName", "&7Gray Glass Cage");
        filesC[21].set("Buyable", true);
        filesC[21].set("Default", false);
        filesC[21].set("Cost", 6000);

        filesC[29].set("Name", "Light Gray Glass Cage");
        filesC[29].set("Type", "STAINED_GLASS");
        filesC[29].set("Data", 8);
        filesC[29].set("DisplayName", "&7Light Gray Glass Cage");
        filesC[29].set("Buyable", true);
        filesC[29].set("Default", false);
        filesC[29].set("Cost", 6000);

        filesC[22].set("Name", "Cyan Glass Cage");
        filesC[22].set("Type", "STAINED_GLASS");
        filesC[22].set("Data", 9);
        filesC[22].set("DisplayName", "&3Cyan Glass Cage");
        filesC[22].set("Buyable", true);
        filesC[22].set("Default", false);
        filesC[22].set("Cost", 6000);

        filesC[23].set("Name", "Purple Glass Cage");
        filesC[23].set("Type", "STAINED_GLASS");
        filesC[23].set("Data", 10);
        filesC[23].set("DisplayName", "&5Purple Glass Cage");
        filesC[23].set("Buyable", true);
        filesC[23].set("Default", false);
        filesC[23].set("Cost", 6000);

        filesC[24].set("Name", "Brown Glass Cage");
        filesC[24].set("Type", "STAINED_GLASS");
        filesC[24].set("Data", 12);
        filesC[24].set("DisplayName", "&eBrown Glass Cage");
        filesC[24].set("Buyable", true);
        filesC[24].set("Default", false);
        filesC[24].set("Cost", 6000);

        filesC[25].set("Name", "Green Glass Cage");
        filesC[25].set("Type", "STAINED_GLASS");
        filesC[25].set("Data", 13);
        filesC[25].set("DisplayName", "&2Green Glass Cage");
        filesC[25].set("Buyable", true);
        filesC[25].set("Default", false);
        filesC[25].set("Cost", 6000);

        filesC[26].set("Name", "Red Glass Cage");
        filesC[26].set("Type", "STAINED_GLASS");
        filesC[26].set("Data", 14);
        filesC[26].set("DisplayName", "&cRed Glass Cage");
        filesC[26].set("Buyable", true);
        filesC[26].set("Default", false);
        filesC[26].set("Cost", 6000);

        filesC[27].set("Name", "Black Glass Cage");
        filesC[27].set("Type", "STAINED_GLASS");
        filesC[27].set("Data", 15);
        filesC[27].set("DisplayName", "&8Black Glass Cage");
        filesC[27].set("Buyable", true);
        filesC[27].set("Default", false);
        filesC[27].set("Cost", 20000);

        filesC[28].set("Name", "Emerald Cage");
        filesC[28].set("Type", "EMERALD_BLOCK");
        filesC[28].set("Data", 0);
        filesC[28].set("DisplayName", "&cRed Glass Cage");
        filesC[28].set("Buyable", true);
        filesC[28].set("Default", false);
        filesC[28].set("Cost", 50000);

        filesC[30].set("Name", "Sandstone Cage");
        filesC[30].set("Type", "SANDSTONE");
        filesC[30].set("Data", 0);
        filesC[30].set("DisplayName", "&eSandstone Cage");
        filesC[30].set("Buyable", true);
        filesC[30].set("Default", false);
        filesC[30].set("Cost", 100000);

        if (config.getBoolean("CreateExampleCages")) {
            try {
                for (int x = 0; x < filesC.length; x++) {
                    if (filesC[x] != null) {
                        if (filess[x] != null) {
                            filesC[x].save(filess[x]);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            config.save(configf);
            data.save(dataF);
            if (config.getBoolean("CreateExampleKits")) {
                kits.save(kitsF);
            }
            flb.save(flbF);
            lbf.save(lbfF);
            if (config.getBoolean("CreateExampleCages")) {
                cg.save(cgF);
            }
            //slb.save(slbF);
        } catch (IOException e) {
            getLogger().info("Error: Saving files error!");
        }
        Kits.loadKits();
        if (!folder2.exists()) {
            try {
                folder2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //isNew = config.getBoolean("UseVersion-8");
        File[] files = folder1.listFiles();
        for (int x = 0; x < files.length; x++) {
            File f = files[x];
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            char[] key = {'M', 'C', 'G', 'a', 'm', 'e', 'r', '1', '9', '9'};
            Material mat = Material.getMaterial(c.getString("Type").toUpperCase());
            Types type = new Types(key, c.getInt("ID"), c.getString("Name"), c.getBoolean("UseNormalBlock"), c.getBoolean("WorksInCreative"), (short) c.getInt("MaxLuck"),
                    (short) c.getInt("MinLuck"), c.getString("Folder"), mat, (short) c.getInt("Data"));
            type.setWorlds(c.getStringList("Worlds"));
            ItemStack item = new ItemStack(type.getType());
            if (type.getData() > -1) {
                item.setDurability(type.getData());
            }
            if (type.getName() != null) {
                ItemMeta itemM = item.getItemMeta();
                itemM.setDisplayName(ChatColor.translateAlternateColorCodes('&', type.getName()));
                item.setItemMeta(itemM);
            }
            if (c.getStringList("Abilities").size() > 0) {
                for (int i = 0; i < c.getStringList("Abilities").size(); i++) {
                    type.addAbility(BlockAbilities.valueOf(c.getStringList("Abilities").get(i)));
                }
            }
            if (c.getBoolean("AllowPlaceSound")) {
                type.setAllowplacesound(true);
            }
            if (c.getBoolean("AllowBreakSound")) {
                type.setAllowbreaksound(true);
            }
            if (c.getBoolean("AllowPlaceParticles")) {
                type.setAllowplaceparticles(true);
            }
            if (c.getBoolean("AllowBreakParticles")) {
                type.setAllowbreakparticles(true);
            }
            if (c.getBoolean("AllowTickParticles")) {
                type.setAllowtickparticles(true);
            }
            if (c.getString("PlaceSound") != null) {
                type.setPlacesound(c.getString("PlaceSound"));
            }
            if (c.getString("BreakSound") != null) {
                type.setBreaksound(c.getString("BreakSound"));
            }
            if (c.getString("PlaceParticles") != null) {
                type.setPlaceparticles(c.getString("PlaceParticles"));
            }
            if (c.getString("BreakParticles") != null) {
                type.setBreakparticles(c.getString("BreakParticles"));
            }
            if (c.getString("TickParticles") != null) {
                type.setTickparticles(c.getString("TickParticles"));
            }
            type.setUseLuckyBlockSkin(c.getBoolean("UseSkin"));
            if (c.getString("Skin") != null) {
                type.setSkin(LuckySkin.valueOf(c.getString("Skin")));
            }
            type.save();
        }
        loadRecipes();
        List<String> list = config.getStringList("Materials");
        for (int x = 0; x < list.size(); x++) {
            Material mat = null;
            int luck = 0;
            short data = -1;
            String[] d = list.get(x).split(",");
            for (int i = 0; i < d.length; i++) {
                if (d[i].startsWith("type:")) {
                    mat = Material.getMaterial(d[i].split("type:")[1].toUpperCase());
                }
            }
            for (int i = 0; i < d.length; i++) {
                if (d[i].startsWith("data:")) {
                    data = Short.parseShort(d[i].split("data:")[1]);
                }
                if (d[i].startsWith("luck:")) {
                    luck = Integer.parseInt(d[i].split("luck:")[1]);
                }
            }
            ItemStack item = new ItemStack(mat);
            if (data > -1) {
                item.setDurability(data);
            }
            if (data > -1) {
                its1.put(item, data);
            }
            its.put(item, luck);
        }
        File[] cages = folder2.listFiles();
        for (int x = 0; x < cages.length; x++) {
            File f = cages[x];
            FileConfiguration ff = YamlConfiguration.loadConfiguration(f);
            Cage cage = new Cage();
            cage.setBuyable(ff.getBoolean("Buyable"));
            cage.setCost(ff.getInt("Cost"));
            cage.setData((byte) ff.getInt("Data"));
            cage.setName(ff.getString("Name"));
            cage.setDefault(ff.getBoolean("Default"));
            cage.setType(Material.getMaterial(ff.getString("Type").toUpperCase()));
            cage.setDisplayName(ChatColor.translateAlternateColorCodes('&', ff.getString("DisplayName")));
            cage.save();
        }
        rules = ChatColor.translateAlternateColorCodes('&', config.getString("Rules"));
    }

    public void STUCK(final Player player, final Location loc, final Long time) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    int a = 7;

                    @Override
                    public void run() {
                        if (a > 0) {
                            player.teleport(loc);
                            a--;
                        } else {
                            task.run();
                        }
                    }
                }, 0L, time)
        );
    }

    // Meteros //

    public void Tower(final Block block, final int dmg) {
        final Location loc = block.getLocation();
        block.getWorld().playEffect(loc, Effect.POTION_BREAK, dmg);
        FallingBlock fb = block.getWorld().spawnFallingBlock(loc.add(0, 10, 0), Material.STAINED_CLAY, (byte) LuckyBlock.randoms.nextInt(15));
        fb.setDropItem(false);
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int loop = LuckyBlock.randoms.nextInt(4) + 6;

            @Override
            public void run() {
                if (loop > 1) {
                    block.getWorld().getHighestBlockAt(loc).getWorld().spawnFallingBlock(loc, Material.STAINED_CLAY,
                            (byte) LuckyBlock.randoms.nextInt(15)).setDropItem(false);
                    ;
                    loop--;
                } else if (loop == 1) {
                    FallingBlock bb = block.getWorld().getHighestBlockAt(loc).getWorld().spawnFallingBlock(loc, Material.DIAMOND_BLOCK, (byte) 0);
                    Tower1(bb);
                    loop--;
                } else if (loop < 1) {
                    task.run();
                }
            }
        }, 6L, 6L));
    }


    // Flying Lucky Block //

    protected void Tower1(final FallingBlock fb) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (fb.isValid()) {
                    if (fb.isOnGround()) {
                        Location loc = fb.getLocation();
                        loc = fb.getLocation();
                        loc.getWorld().strikeLightning(loc);
                        task.run();
                    }
                } else {
                    Location loc = fb.getLocation();
                    loc = fb.getLocation();
                    loc.getWorld().strikeLightning(loc);
                    task.run();
                }
            }
        }, 3L, 1L));
    }

    // Lightning //

    public void Meteor(final FallingBlock fb) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    int x = 0;

                    @Override
                    public void run() {
                        if (!fb.isDead()) {
                            ParticleEffect.SMOKE_LARGE.display((float) 0.3, (float) 0.2, (float) 0.3, 0, 170, fb.getLocation(), 200);
                            for (Entity e : fb.getNearbyEntities(6, 6, 6)) {
                                if (e instanceof LivingEntity) {
                                    e.setFireTicks(100);
                                    e.setFallDistance(10F);
                                }
                            }
                            fb.getWorld().dropItem(fb.getLocation(), new ItemStack(Material.STONE)).setPickupDelay(600);
                        } else if (fb.isDead() || fb.isOnGround()) {
                            if (x == 0) {
                                x = 1;
                                int xx = fb.getLocation().getBlockX();
                                int y = fb.getLocation().getBlockY();
                                int z = fb.getLocation().getBlockZ();
                                try {
                                    boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                                    boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                                    boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                                    if (damage == true) {
                                        fb.getWorld().createExplosion(xx, y, z, 8F, setFire, breakBlocks);
                                    } else {
                                        fb.getWorld().createExplosion(xx, y, z, 0F, setFire, breakBlocks);
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                fb.remove();
                                task.run();
                            }
                        }
                    }
                }, 3L, 3L)
        );
    }

    public void FlyingBat(final Bat bat) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (bat.getPassenger() != null) {
                    bat.getPassenger().remove();
                    bat.remove();
                }
                ItemStack lucky = ItemMaker.createItem(Types.fromId(1).getType(), 1, (short) 0, Types.fromId(1).getName(),
                        Arrays.asList(yellow + "%" + (randoms.nextInt(15) + 1)));
                if (LuckyBlockAPI.getLB(1).getData() > -1) {
                    lucky.setDurability(LuckyBlockAPI.getLB(1).getData());
                }
                bat.getWorld().dropItem(bat.getLocation(), lucky);
            }
        }, 2L);
    }

    public void LightningR(final Player player, final Block block) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    int x = randoms.nextInt(7) + 9;

                    @Override
                    public void run() {
                        if (x > 0) {
                            player.getWorld().strikeLightning(block.getLocation());
                            x--;
                        } else {
                            task.run();
                        }
                    }
                }, 0L, 5L)
        );
    }

    public void BloodW(final Item i, int time) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                i.remove();
            }
        }, time);
    }

    public void c4(final Item i, final Block block) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                i.teleport(block.getLocation());
            }
        }, 10L);
    }

    public void Bomb(final Item item) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                int x = item.getLocation().getBlockX();
                int y = item.getLocation().getBlockY();
                int z = item.getLocation().getBlockZ();
                try {
                    boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                    boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                    boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                    if (damage == true) {
                        item.getWorld().createExplosion(x, y, z, 4F, setFire, breakBlocks);
                    } else {
                        item.getWorld().createExplosion(x, y, z, 0F, setFire, breakBlocks);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                item.remove();
            }
        }, 70L);
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (!(item.isDead())) {
                    ParticleEffect.SMOKE_NORMAL.display(0.1f, 0.3f, 0.1f, 0, 3, item.getLocation(), 100);
                } else {
                    task.run();
                }
            }
        }, 0L, 0L));
    }

    public void Guns(final Snowball s, final int i) {
        // Gun=1;Gun1=2.5;Gun3=5.5;Gun4=9;Gun5=11;Gun6=18;Gun7=22;Gun8=30;Gun9=39;Gun10=50;Gun11=75;Gun12=125;Gun13=210;
        // Gun14=320;Gun15=500;Gun16=800;Gun17=1500;Gun18=25000;
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    @Override
                    public void run() {
                        if (!(s.isDead())) {
                            if (i == 1) {
                                ParticleEffect.SNOWBALL.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 2) {
                                ParticleEffect.SLIME.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 3) {
                                ParticleEffect.VILLAGER_HAPPY.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 4) {
                                ParticleEffect.FLAME.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 5) {
                                ParticleEffect.FIREWORKS_SPARK.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 6) {
                                ParticleEffect.ENCHANTMENT_TABLE.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 7) {
                                ParticleEffect.SMOKE_NORMAL.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 8) {
                                ParticleEffect.SMOKE_LARGE.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 9) {
                                ParticleEffect.DRIP_LAVA.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 10) {
                                ParticleEffect.REDSTONE.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 11) {
                                ParticleEffect.REDSTONE.display(0.1f, 0.2f, 0.1f, 1, 35, s.getLocation(), 160);
                            } else if (i == 12) {
                                ParticleEffect.SPELL.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 13) {
                                ParticleEffect.LAVA.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 14) {
                                ParticleEffect.SPELL_INSTANT.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 15) {
                                ParticleEffect.CRIT.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 16) {
                                ParticleEffect.PORTAL.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 17) {
                                ParticleEffect.TOWN_AURA.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 18) {
                                ParticleEffect.CRIT_MAGIC.display(0.1f, 0.2f, 0.1f, 0, 10, s.getLocation(), 160);
                            } else if (i == 19) {
                                ParticleEffect.DRIP_WATER.display(0.1f, 0.2f, 0.1f, 1, 20, s.getLocation(), 160);
                            } else if (i == 20) {
                                ParticleEffect.HEART.display(0.05f, 0.1f, 0.05f, 1, 5, s.getLocation(), 160);
                            } else if (i == 21) {
                                ParticleEffect.REDSTONE.display(0.05f, 0.1f, 0.05f, 0, 5, s.getLocation(), 160);
                            }
                        } else {
                            task.run();
                        }
                    }
                }, 0L, 0L)
        );
    }

    public void TBomb(final Snowball snowball) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    @Override
                    public void run() {
                        if (!(snowball.isDead())) {
                            ParticleEffect.FIREWORKS_SPARK.display(0.1f, 0.2f, 0.1f, 0, 5, snowball.getLocation(), 100);
                        } else {
                            task.run();
                        }
                    }
                }, 0L, 0L)
        );
    }

    public void BombBlock(final Block block, int i) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (block.getType() == Material.STAINED_CLAY) {
                    int x = block.getLocation().getBlockX();
                    int y = block.getLocation().getBlockY();
                    int z = block.getLocation().getBlockZ();
                    try {
                        boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                        boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                        boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                        if (damage == true) {
                            block.getWorld().createExplosion(x, y, z, 4F, setFire, breakBlocks);
                        } else {
                            block.getWorld().createExplosion(x, y, z, 0F, setFire, breakBlocks);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }, i);
    }

    public void FireWorks(final Block block) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    int x = randoms.nextInt(5) + 5;

                    @Override
                    public void run() {
                        if (x > 0) {
                            for (int x = LuckyBlock.randoms.nextInt(18) + 8; x > 0; x--) {
                                final Firework fwork = (Firework) block.getWorld().spawnEntity(block.getLocation(), EntityType.FIREWORK);
                                FireworkMeta fwm = fwork.getFireworkMeta();
                                Random r = new Random();
                                int rt = r.nextInt(4) + 1;
                                Type type = Type.BALL;
                                if (rt == 1) type = Type.BALL;
                                if (rt == 2) type = Type.BALL_LARGE;
                                if (rt == 3) type = Type.BURST;
                                if (rt == 4) type = Type.CREEPER;
                                if (rt == 5) type = Type.STAR;
                                FireworkEffect f = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(org.bukkit.Color
                                        .fromBGR(LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255)))
                                        .withColor(Color.fromBGR(LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255)))
                                        .withColor(Color.fromBGR(LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255)))
                                        .withFade(Color.fromBGR(LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255), LuckyBlock.randoms.nextInt(255)))
                                        .with(type).trail(r.nextBoolean()).build();
                                fwm.clearEffects();
                                fwm.addEffect(f);
                                int rp = r.nextInt(3) + 1;
                                fwm.setPower(rp);
                                fwork.setFireworkMeta(fwm);
                            }
                            x--;
                        } else {
                            task.run();
                        }
                    }
                }, 5L, 20L)
        );
    }

    public void Tree(final Block block, final TreeType treetype) {
        Block b = block;
        block.getRelative(BlockFace.DOWN).setType(Material.DIRT);
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                block.getWorld().generateTree(block.getLocation(), treetype);
            }
        }, 1L);
    }

    public void Wait(final Material mat, final Block block, final Location bloc, final Player player, final int luckyBlock) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                Chicken ws = (Chicken) block.getWorld().spawnEntity(bloc, EntityType.CHICKEN);
                for (Entity e : ws.getNearbyEntities(3, 3, 3)) {
                    if (e instanceof Item) {
                        Item item = (Item) e;
                        if (item.getItemStack().getType() == mat) {
                            item.remove();
                        }
                    }
                }
                ws.remove();
            }
        }, 2L);
    }

    @EventHandler
    public void onDMGBlock(EntityExplodeEvent event) {
        List<Block> effected = event.blockList();
        for (int x = 0; x < effected.size(); x++) {
            Block block = effected.get(x);
            String dim = LuckyBlockAPI.createDim(block);
            if (Types.fromBlock(dim) != null && Types.fromBlock(dim).getAbilities().contains(BlockAbilities.RESISTANCE_EXPLOSIONS)) {
                if (LuckyBlockAPI.luck.containsKey(dim) && LuckyBlockAPI.chances.containsKey(dim)) {
                    Wait(block.getType(), block, block.getLocation(), null, LuckyBlockAPI.luck.get(dim));
                    LuckyBlockAPI.removeLB(dim);
                }
            }
        }
    }

    public void FakeDiamond(final Item item) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                item.remove();
            }
        }, 85L);
    }

    @EventHandler
    public void RiderDmg(EntityDamageEvent event) {
        if (event.getEntity() instanceof Skeleton) {
            final Skeleton sk = (Skeleton) event.getEntity();
            if (sk.hasMetadata("rider")) {
                if (sk.getCustomName() != null) {
                    if (sk.getCustomName().startsWith(blue + "Rider")) {
                        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                            @Override
                            public void run() {
                                Damageable d = (Damageable) sk;
                                String name = "";
                                if (d.getHealth() >= 10) {
                                    name = green + "" + (float) d.getHealth();
                                } else if (d.getHealth() > 4 && (float) d.getHealth() < 10) {
                                    name = gold + "" + (float) d.getHealth();
                                } else {
                                    name = red + "" + (float) d.getHealth();
                                }
                                sk.setCustomName(blue + "Rider " + name + white + "/" + green + (float) d.getMaxHealth());
                            }
                        }, 1L);
                    }
                }
            }
        }
    }

    public void SpawnRider(final Player player) {
        final Skeleton sk = (Skeleton) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
        sk.setMetadata("rider", new FixedMetadataValue(this, "1"));
        sk.getEquipment().setItemInHand(new ItemStack(Material.BOW));
        sk.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
        sk.setMaxHealth(200.0);
        sk.setHealth(200.0);
        Damageable d = (Damageable) sk;
        String name = "";
        if (d.getHealth() >= 10) {
            name = green + "" + (float) d.getHealth();
        } else if (d.getHealth() > 4 && (float) d.getHealth() < 10) {
            name = gold + "" + (float) d.getHealth();
        } else {
            name = red + "" + (float) d.getHealth();
        }
        sk.setCustomName(blue + "Rider " + name + white + "/" + green + (float) d.getMaxHealth());
        sk.setCustomNameVisible(true);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (sk.getVehicle() == null) {
                    Ride(sk, player);
                }
            }
        }, 10L, 20L);
    }

    public void Ride(final Skeleton sk, Player player) {
        List<UUID> uuids = new ArrayList<UUID>();
        for (Entity e : sk.getNearbyEntities(50, 50, 50)) {
            if (e instanceof LivingEntity) {
                if (!(e instanceof Player)) {
                    if (e.getUniqueId() != sk.getUniqueId()) {
                        if (e.getPassenger() == null) {
                            uuids.add(e.getUniqueId());
                        }
                    }
                }
            }
        }
        if (uuids.size() > 0) {
            int x = randoms.nextInt(uuids.size());
            for (Entity e : sk.getNearbyEntities(50, 50, 50)) {
                if (e.getUniqueId() == uuids.get(x)) {
                    final LivingEntity en = (LivingEntity) e;
                    final SchedulerTask task = new SchedulerTask();
                    task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                                int y = 0;

                                @Override
                                public void run() {
                                    if (y == 0) {
                                        if (!en.isDead()) {
                                            if (!sk.isDead()) {
                                                en.setPassenger(sk);
                                            } else {
                                                y = 1;
                                            }
                                        } else {
                                            y = 1;
                                        }
                                    } else {
                                        task.run();
                                    }
                                }
                            }, 5L, 5L)
                    );
                }
            }
        }
    }

    public void CBlock(final Block block) {
        final String dim = block.getX() + "," + block.getY() + "," + block.getZ();
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                int x = randoms.nextInt(2) + 1;
                if (x == 1) {
                    block.setType(Material.CHEST);
                } else if (x == 2) {
                    block.setType(Material.FURNACE);
                }
                cblocks.put(dim, true);
                CBlock1(block, randoms.nextInt(25) + 10);
            }
        }, 1L);
    }

    @EventHandler
    public void breakCBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        String dim = block.getX() + "," + block.getY() + "," + block.getZ();
        if (cblocks.containsKey(dim)) {
            cblocks.remove(dim);
        }
    }

    public void CBlock1(final Block block, long l) {
        final String dim = block.getX() + "," + block.getY() + "," + block.getZ();
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    @Override
                    public void run() {
                        if (cblocks.containsKey(dim)) {
                            if (block.getData() < 5) {
                                block.setData((byte) (block.getData() + 1));
                            } else {
                                block.setData((byte) 0);
                            }
                        } else {
                            task.run();
                        }
                    }
                }, l, l)
        );
    }

    public void Slimed(final Slime slime) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    @Override
                    public void run() {
                        if (!slime.isDead()) {
                            for (Entity e : slime.getNearbyEntities(6, 6, 6)) {
                                if (e instanceof LivingEntity) {
                                    LivingEntity l = (LivingEntity) e;
                                    if (!(l instanceof Player)) {
                                        Damageable d = (Damageable) l;
                                        if (d.getHealth() < d.getMaxHealth() && d.getHealth() > 0) {
                                            try {
                                                l.setHealth(d.getHealth() + 3);
                                            } catch (Exception ex) {
                                                l.setHealth(d.getMaxHealth());
                                            }
                                            ParticleEffect.HEART.display(0.4F, 0.5F, 0.4F, 0, 25, l.getLocation(), 64);
                                        }
                                    }
                                }
                            }
                        } else {
                            task.run();
                        }
                    }
                }, 100L, 80L)
        );
    }

    @EventHandler
    public void onGenerate(ChunkPopulateEvent event) {
        int r = randoms.nextInt(200) + 1;
        World world = event.getWorld();
        if (!LuckyBlockWorld.isEquals(world.getGenerator())) {
            List<String> worlds = config.getStringList("SpawnWorlds");
            if (!worlds.contains("*All*")) {
                if (!worlds.contains(world.getName())) {
                    if (config.getInt("SpawnRate") != 0) {
                        if (r < (config.getInt("SpawnRate"))) {
                            int x = randoms.nextInt(10) - 5;
                            int z = randoms.nextInt(10) - 5;
                            int x1 = event.getChunk().getX() + x;
                            int z1 = event.getChunk().getZ() + z;
                            int y = event.getWorld().getHighestBlockAt(x1, z1).getY();
                            if (event.getWorld().getBlockAt(x1, y, z1).getType() != Material.AIR) {
                                y++;
                            }
                            Block block = event.getChunk().getBlock(x1, y, z1);
                            Block b = block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);
                            if (b.isLiquid() == false && b.getType() != Material.DOUBLE_PLANT && b.getType() != Material.DEAD_BUSH && b.getType() != Material.YELLOW_FLOWER
                                    && b.getType() != Material.RED_ROSE && b.getRelative(BlockFace.UP).getType() != Material.AIR && block.getRelative(BlockFace.DOWN).isLiquid()
                                    == false) {
                                block.setType(Types.getTypes().get(0).getType());
                                if (Types.getTypes().get(0).getData() > -1) {
                                    block.setData((byte) Types.getTypes().get(0).getData());
                                }
                                String dim = LuckyBlockAPI.createDim(block);
                                int luck = randoms.nextInt(20);
                                int chance = randoms.nextInt(lbf.getInt("Luck.Chances"));
                                LuckyBlockAPI.saveLuckyBlock(dim, luck, chance, "null", 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void Gift(Player player) {
        Damageable d = (Damageable) player;
        int c = randoms.nextInt(9) + 1;
        if (c == 1) {
            ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack item1 = new ItemStack(Material.DIAMOND_BOOTS);
            if (player.getEquipment().getHelmet() == null || player.getEquipment().getHelmet().getType() == Material.AIR) {
                player.getEquipment().setHelmet(item);
            } else {
                player.getInventory().addItem(item);
            }
            if (player.getEquipment().getBoots() == null || player.getEquipment().getBoots().getType() == Material.AIR) {
                player.getEquipment().setBoots(item1);
            } else {
                player.getInventory().addItem(item1);
            }
            player.sendMessage(green + "Added Armor to your inventory!");
        } else if (c == 2) {
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, randoms.nextInt(10) + 6));
            player.sendMessage(green + "Added Golden Apples to your inventory!");
        } else if (c == 3) {
            try {
                player.setHealth(d.getHealth() + 5);
            } catch (Exception ex) {
                player.setHealth(d.getMaxHealth());
            }
            player.sendMessage(green + "Healed!");
        } else if (c == 4) {
            player.getInventory().addItem(new ItemStack(Material.DIAMOND, randoms.nextInt(10) + 5));
            player.sendMessage(green + "Added Diamonds to your inventory!");
        } else if (c == 5) {
            Strong(player, 160);
            player.sendMessage(green + "You are invincible for 8 sec!");
        } else if (c == 6) {
            player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
            player.getInventory().addItem(new ItemStack(Material.BOW));
            player.sendMessage(green + "Added Weapons to your inventory");
        } else if (c == 7) {
            if (ex.containsKey(player.getUniqueId())) {
                ex.put(player.getUniqueId(), ex.get(player.getUniqueId()) + 1);
            } else {
                ex.put(player.getUniqueId(), 1);
            }
            player.sendMessage(green + "Added 1 Exploding punch point!");
        } else if (c == 8) {
            player.sendMessage(green + "Added Random Chest to your inventory!");
            ItemStack item = new ItemStack(Material.CHEST);
            ItemMeta itemM = item.getItemMeta();
            itemM.setDisplayName(green + "" + bold + "" + italic + "Random Chest");
            item.setItemMeta(itemM);
            player.getInventory().addItem(item);
        } else if (c == 9) {
            player.sendMessage(green + "Added Ender Pearls to your inventory");
            player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, randoms.nextInt(3) + 1));
        }
    }

    private void Strong(final Player player, long l) {
        strong.put(player.getUniqueId(), true);
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                strong.put(player.getUniqueId(), false);
            }
        }, l);
    }

    @EventHandler
    public void onHitWhileStrong(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            UUID uuid = player.getUniqueId();
            if (strong.containsKey(uuid)) {
                if (strong.get(uuid)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void str1009(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity l = (LivingEntity) event.getEntity();
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                UUID uuid = player.getUniqueId();
                if (ex.containsKey(uuid)) {
                    if (ex.get(uuid) > 0) {
                        l.getWorld().createExplosion(l.getLocation().getX(), l.getLocation().getY(), l.getLocation().getZ(), 4F, false, false);
                        player.sendMessage(red + "BOOM!");
                        ex.put(uuid, ex.get(uuid) - 1);
                    } else {
                        ex.remove(uuid);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onplayermove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (snowmove.contains(player.getName())) {
            final SchedulerTask task = new SchedulerTask();
            task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                        @Override
                        public void run() {
                            if (snowmove.contains(player.getName())) {
                                Location loc = player.getLocation().add(0, -1, 0);
                                if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.WATER && loc.getBlock().getType() != Material.LAVA) {
                                    loc.getBlock().setType(Material.SNOW_BLOCK);
                                }
                            } else {
                                task.run();
                            }
                        }
                    }, 10L, 6L)
            );
        }
    }

    public void Bedrock(final Block block) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                block.setType(Material.BEDROCK);
                if (block.getRelative(BlockFace.UP).getType() == Material.AIR) {
                    block.getRelative(BlockFace.UP).setType(Material.SIGN_POST);
                    Sign sign = (Sign) block.getRelative(BlockFace.UP).getState();
                    sign.setLine(1, "Well it's");
                    sign.setLine(2, "your problem");
                    sign.update(true);
                }
            }
        }, 1L);
    }

    public void Trap(Player player) {
        final Block block = player.getLocation().getBlock();
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                block.getLocation().add(2, 0, 0).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(2, 0, 1).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(2, 0, -1).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(2, 0, 2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(2, 0, -2).getBlock().setType(Material.SMOOTH_BRICK);
                int a = -2;
                int b = -2;
                for (int x = 5; x > 0; x--) {
                    for (int y = 5; y > 0; y--) {
                        block.getLocation().add(a, -1, b).getBlock().setType(Material.SMOOTH_BRICK);
                        a++;
                    }
                    a = -2;
                    b++;
                }
                block.getLocation().add(1, 0, -2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(1, 0, 2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(0, 0, 2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(0, 0, -2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(-1, 0, -2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(-1, 0, 2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(-2, 0, 2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(-2, 0, -2).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(-2, 0, 0).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(-2, 0, 1).getBlock().setType(Material.SMOOTH_BRICK);
                block.getLocation().add(-2, 0, -1).getBlock().setType(Material.SMOOTH_BRICK);

                block.getLocation().add(2, 1, 0).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(2, 1, 1).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(2, 1, -1).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(2, 1, 2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(2, 1, -2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(1, 1, -2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(1, 1, 2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(0, 1, 2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(0, 1, -2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(-1, 1, -2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(-1, 1, 2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(-2, 1, 2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(-2, 1, -2).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(-2, 1, 0).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(-2, 1, 1).getBlock().setType(Material.IRON_FENCE);
                block.getLocation().add(-2, 1, -1).getBlock().setType(Material.IRON_FENCE);
                int c = -2;
                int d = -2;
                for (int x = 5; x > 0; x--) {
                    for (int y = 5; y > 0; y--) {
                        block.getLocation().add(c, 2, d).getBlock().setType(Material.SMOOTH_BRICK);
                        c++;
                    }
                    c = -2;
                    d++;
                }
                int e = -1;
                int f = -1;
                for (int x = 3; x > 0; x--) {
                    for (int y = 3; y > 0; y--) {
                        block.getLocation().add(e, 3, f).getBlock().setType(Material.SMOOTH_BRICK);
                        e++;
                    }
                    e = -1;
                    f++;
                }
                block.getLocation().add(0, 3, 0).getBlock().setType(Material.LAVA);
            }
        }, 1L);
        trap2(block);
    }

    public void trap2(final Block block) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                block.getLocation().add(0, 2, 0).getBlock().setType(Material.AIR);
            }
        }, 100L);
    }

    public void craft(final Player player, final CraftingInventory craft, final int x) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (x == 1) {
                    craft1(player, craft);
                } else if (x == 2) {
                    craft2(player, craft);
                }
            }
        }, 2L);
    }

    protected void craft2(Player player, CraftingInventory craft) {
        //TODO
    }

    protected void craft1(Player player, CraftingInventory craft) {
        boolean fail = false;
        int found = 0;
        int found1 = 0;
        int luck = 0;
        int slot = 0;
        int total = 0;
        int luck1 = 0;
        int lores = 0;
        List<String> lore = new ArrayList<String>();
        ItemStack item = null;
        Map<Enchantment, Integer> enchs = null;
        Types types = null;
        for (int x = 1; x < craft.getSize(); x++) {
            for (int i = 0; i < lbs.size(); i++) {
                if (craft.getItem(x) != null && craft.getItem(x).getType() == lbs.get(i).getType() && craft.getItem(x).hasItemMeta() && craft.getItem(x)
                        .getItemMeta().hasDisplayName() && craft.getItem(x).getItemMeta().getDisplayName().equalsIgnoreCase(lbs.get(i).getName())) {
                    types = lbs.get(i);
                    found++;
                    slot = x;
                    item = new ItemStack(types.getType());
                    item.setDurability(types.getData());
                    ItemMeta itemM = item.getItemMeta();
                    if (craft.getItem(x).hasItemMeta() && craft.getItem(x).getItemMeta().hasEnchants()) {
                        enchs = craft.getItem(x).getItemMeta().getEnchants();
                    }
                    itemM.setDisplayName(types.getName());
                    if (craft.getItem(x).getItemMeta().hasLore()) {
                        if (craft.getItem(x).getItemMeta().getLore().size() > 0) {
                            for (int a = 0; a < craft.getItem(x).getItemMeta().getLore().size(); a++) {
                                String l = craft.getItem(x).getItemMeta().getLore().get(a);
                                if (ChatColor.stripColor(l).startsWith("%")) {
                                    String[] d = ChatColor.stripColor(l).split("%");
                                    luck1 = Integer.parseInt(d[1]);
                                    lores = a;
                                    lore = craft.getItem(x).getItemMeta().getLore();
                                    a = craft.getItem(x).getItemMeta().getLore().size();
                                }
                            }
                        }
                    }
                    item.setItemMeta(itemM);
                }
            }
        }
        if (found == 1) {
            for (int x = 1; x < craft.getSize(); x++) {
                int s = 0;
                for (ItemStack i : its.keySet()) {
                    if (s == 0) {
                        if (craft.getItem(x) != null) {
                            if (craft.getItem(x).getType() == i.getType()) {
                                if (its1.containsKey(i)) {
                                    if (craft.getItem(x).getDurability() == its1.get(i)) {
                                        found1 = 1;
                                        luck += its.get(i);
                                        s = 1;
                                    }
                                } else {
                                    found1 = 1;
                                    luck += its.get(i);
                                    s = 1;
                                }
                            }
                        }
                    }
                }
                if (s == 0) {
                    if (x != slot) {
                        if (craft.getItem(x) != null) {
                            fail = true;
                        }
                    }
                }
            }
        }
        total = luck + luck1;
        if (found > 1) {
            fail = true;
        }
        if (found1 == 0) {
            fail = true;
        }
        if (types != null) {
            if (total > types.getMaxLuck() || total < types.getMinLuck()) {
                if (total > types.getMaxLuck()) {
                    if (luck1 < types.getMaxLuck()) {
                        total = types.getMaxLuck();
                    } else {
                        total = luck1;
                    }
                }
                if (total < types.getMinLuck()) {
                    total = types.getMinLuck();
                }
                //fail = true;
            }
        }
        if (fail == false) {
            ItemMeta itemM = item.getItemMeta();
            List<String> l = new ArrayList<String>();
            for (int i = 0; i < lores; i++) {
                l.add(lore.get(i));
            }
            l.add(Types.getLuckString(total));
            for (int i = lores + 1; i < lore.size(); i++) {
                l.add(lore.get(i));
            }
            itemM.setLore(l);
            if (enchs != null) {
                for (Enchantment e : enchs.keySet()) {
                    itemM.addEnchant(e, enchs.get(e), true);
                }
            }
            item.setItemMeta(itemM);
            craft.setResult(item);
            player.updateInventory();
        }
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent event) {
        if (!(bukkitVersion[1] > 7)) {
            return;
        }
        if (event.getInventory().getType() == InventoryType.WORKBENCH) {
            final CraftingInventory c = (CraftingInventory) event.getInventory();
            if (event.getWhoClicked() instanceof Player) {
                final Player player = (Player) event.getWhoClicked();
                if (event.getCurrentItem() == null) {
                    return;
                }
                ItemStack item = event.getCurrentItem();
                Types type = null;
                for (Types t : lbs) {
                    if (t.getType() == item.getType()) {
                        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                            if (t.getName() == item.getItemMeta().getDisplayName()) {
                                type = t;
                            }
                        }
                    }
                }
                if (type != null) {
                    int y = 0;
                    if (event.getRawSlot() != 0) {
                        return;
                    }
                    for (int x = 0; x < type.getConfig().getStringList("Ingredients").size(); x++) {
                        String[] d = type.getConfig().getStringList("Ingredients").get(x).split(" ");
                        if (d.length == 2) {
                            if (c.contains(Material.getMaterial(d[1]))) {
                                y = 1;
                            } else {
                                y = 0;
                            }
                        }
                    }
                    if (y == 0) {
                        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                            @Override
                            public void run() {
                                for (int x = 1; x < c.getSize(); x++) {
                                    if (c.getItem(x) != null) {
                                        if (c.getItem(x).getAmount() > 1) {
                                            c.getItem(x).setAmount(c.getItem(x).getAmount() - 1);
                                        } else {
                                            c.removeItem(c.getItem(x));
                                            player.updateInventory();
                                        }
                                    }
                                }
                            }
                        }, 1L);
                    }
                }
            }
        }
    }

    public void spawnLB(final int luckyBlock, final Types type, final Location bloc) {
        final ItemStack it = type.toItemStack();
        ItemMeta itM = it.getItemMeta();
        List<String> itL = new ArrayList<String>();
        itL.add(Types.getLuckString(luckyBlock));
        itM.setLore(itL);
        it.setItemMeta(itM);
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                bloc.getWorld().dropItem(bloc, it);
                ParticleEffect.EXPLOSION_NORMAL.display(0.3f, 0.3f, 0.3f, 0, 150, bloc, 60);
            }
        }, 3L);
    }

    public void Loop(final int id) {
        final War war = War.getGame(id);
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    @Override
                    public void run() {
                        if (war.inGame() == true && war.getPlayers().size() > 1) {
                            war.addTime(1);
                            war.getNextRefill().minTime(1);
                            if (war.getNextRefill().getTotal() < 1) {
                                war.fillChests();
                                war.getNextRefill().addTime(120);
                                for (Player p : war.getPlayerList()) {
                                    p.sendMessage(green + "Chests have been refilled!");
                                    p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1, 1);
                                }
                            }
                            if (config.getBoolean("SpawnBosses")) {
                                if (war.getTime().getTotal() == 300) {
                                    war.spawnBoss(300, "Golem");
                                } else if (war.getTime().getTotal() == 400) {
                                    war.spawnBoss(500, "Golem");
                                } else if (war.getTime().getTotal() == 500) {
                                    war.spawnBoss(600, "Golem");
                                } else if (war.getTime().getTotal() == 600) {
                                    war.spawnBoss(750, "Golem");
                                } else if (war.getTime().getTotal() == 700) {
                                    war.spawnBoss(1000, "Golem");
                                } else if (war.getTime().getTotal() == 1000) {
                                    war.spawnBoss(500, "Wither");
                                }
                            }
                            if (war.isSpawnFallingBlocks()) {
                                if (randoms.nextInt(100) > 85) {
                                    Location l = new Location(war.getCenter().getWorld(), war.getCenter().getX() + (randoms.nextInt(20) - 20),
                                            randoms.nextInt(10) + 240, war.getCenter().getZ() + (randoms.nextInt(20) - 20));
                                    l.getWorld().spawnFallingBlock(l, Material.COBBLESTONE, (byte) 0);
                                }
                            }
                            List<UUID> uuids = war.getPlayers();
                            List<UUID> ds = war.getSpectators();
                            for (final Player p : Bukkit.getOnlinePlayers()) {
                                UUID uuid = p.getUniqueId();
                                if (uuids.contains(uuid) || war.getSpectators().contains(uuid)) {
                                    LuckyBlockAPI.showScoreboard1(p, war, war.getTime().getSecond(), war.getTime().getMin());
                                }
                                if (uuids.contains(uuid)) {
                                    if (p.getLocation().getY() < 1) {
                                        if (uuids.size() > 1) {
                                            if (uuids.size() < 3) {
                                                war.leaveGame(p.getUniqueId(), true, "false");
                                            } else {
                                                war.leaveGame(p.getUniqueId(), false, "false");
                                            }
                                            Game.testPlayer(p);
                                        }
                                    }
                                    if (war.getType() == WarType.INSANE) {
                                        int t = 0;
                                        for (int x = 0; x < war.getDangerblocks().length; x++) {
                                            if (p.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
                                                if (p.getLocation().add(0, -1, 0).getBlock().getTypeId() == war.getDangerblocks()[x]) {
                                                    if (uuids.size() > 1) {
                                                        t = 1;
                                                    }
                                                }
                                            }
                                        }
                                        if (t == 1) {
                                            ParticleEffect.FLAME.display(2f, 2f, 2f, 1000, 1, p.getLocation(), 128);
                                            if (uuids.size() < 3) {
                                                war.leaveGame(p.getUniqueId(), true, "false");
                                            } else {
                                                war.leaveGame(p.getUniqueId(), false, "false");
                                            }
                                            Game.testPlayer(p);
                                        }
                                    }
                                    if (war.getFirstpos() != null && war.getSecpos() != null) {
                                        Location f = war.getFirstpos();
                                        Location s = war.getSecpos();
                                        int maxX = 0;
                                        int minX = 0;
                                        int maxZ = 0;
                                        int minZ = 0;
                                        if (f.getBlockX() > s.getBlockX()) {
                                            maxX = f.getBlockX();
                                        } else {
                                            maxX = s.getBlockX();
                                            minX = f.getBlockX();
                                        }
                                        if (f.getBlockZ() > s.getBlockZ()) {
                                            maxZ = f.getBlockZ();
                                        } else {
                                            maxZ = s.getBlockZ();
                                            minZ = f.getBlockZ();
                                        }
                                        if (p.getLocation().getBlockX() > maxX || p.getLocation().getBlockX() < minX ||
                                                p.getLocation().getBlockZ() > maxZ || p.getLocation().getBlockZ() < minZ) {
                                            if (!LuckyBlockAPI.warnedPlayers.contains(p.getUniqueId())) {
                                                LuckyBlockAPI.warnedPlayers.add(p.getUniqueId());
                                                sendWarning(p, war);
                                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 100f, 1f);
                                            }
                                        }
                                    }
                                }
                                if (ds.contains(uuid)) {
                                    if (p.getLocation().getY() < 1) {
                                        if (war.getSpawns().length > 0) {
                                            Location loc = war.getSpawns()[0];
                                            p.teleport(loc);
                                        }
                                    }
                                }
                            }
                        } else {
                            task.run();
                        }
                    }
                }, 20L, 20L)
        );
    }

    public void Loop3(final War war) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (war.inGame()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (war.containsSpectator(p.getUniqueId())) {
                            if (p.getAllowFlight() == false || p.isFlying() == false) {
                                p.setAllowFlight(true);
                                p.setFlying(true);
                            }
                        }
                    }
                } else {
                    task.run();
                }
            }
        }, 20L, 8L));
    }

    public void w1002(final Player player) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                player.teleport(LuckyBlockAPI.mainlobby);
                player.sendMessage(green + "Teleporting to main lobby!");
                player.setGameMode(GameMode.ADVENTURE);
            }
        }, 1L);
    }

    public void w1003(final War war, final Player player) {
        w1006(player, war);
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                player.sendMessage(darkred + "" + bold + "Victory!");
                war.EndGame(true);
            }
        }, 160L);
    }

    public void w1005(final War war) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (war.inGame() == true) {
                    war.EndGame(false);
                }
            }
        }, 170L);
    }

    public void w1006(final Player player, final War war) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (war.inGame() && war.getPlayers().size() > 0) {
                    if (player.getAllowFlight() == false || player.isFlying() == false) {
                        player.setAllowFlight(true);
                        player.setFlying(true);
                    }
                } else {
                    task.run();
                }
            }
        }, 10L, 8L));
    }

    public void Loop1(final Player player) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                    int x = 0;

                    @Override
                    public void run() {
                        UUID uuid = player.getUniqueId();
                        if (War.containPlayer(uuid)) {
                            War war = War.getGame(uuid);
                            if (war.inGame() == false) {
                                LuckyBlockAPI.showScoreboard(player, x);
                                x++;
                                if (x > 4) {
                                    x = 0;
                                }
                            } else {
                                ScoreboardManager s = Bukkit.getScoreboardManager();
                                player.setScoreboard(s.getMainScoreboard());
                                task.run();
                            }
                        } else {
                            ScoreboardManager s = Bukkit.getScoreboardManager();
                            player.setScoreboard(s.getNewScoreboard());
                            task.run();
                        }
                    }
                }, 10L, 5L)
        );
    }

    public void registerChests(final War war) {
        if (war.getFirstpos() == null || war.getSecpos() == null) {
            return;
        }
        war.getChests().clear();
        final int dz1;
        if (war.getFirstpos().getBlockZ() > war.getSecpos().getBlockZ()) {
            dz1 = war.getFirstpos().getBlockZ();
        } else {
            dz1 = war.getSecpos().getBlockZ();
        }
        final int dx1;
        final int dy1;
        if (war.getFirstpos().getBlockX() > war.getSecpos().getBlockX()) {
            dx1 = war.getFirstpos().getBlockX();
        } else {
            dx1 = war.getSecpos().getBlockX();
        }
        if (war.getFirstpos().getBlockY() > war.getSecpos().getBlockY()) {
            dy1 = war.getFirstpos().getBlockY();
        } else {
            dy1 = war.getSecpos().getBlockY();
        }
        final int dx;
        final int dy;
        final int dz;
        if (war.getFirstpos().getBlockX() < war.getSecpos().getBlockX()) {
            dx = war.getFirstpos().getBlockX();
        } else {
            dx = war.getSecpos().getBlockX();
        }
        if (war.getFirstpos().getBlockY() <= war.getSecpos().getBlockY()) {
            dy = war.getFirstpos().getBlockY();
        } else {
            dy = war.getSecpos().getBlockY();
        }
        if (war.getFirstpos().getBlockZ() <= war.getSecpos().getBlockZ()) {
            dz = war.getFirstpos().getBlockZ();
        } else {
            dz = war.getSecpos().getBlockZ();
        }
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int tx = dz;
            int ty = dy;
            int tz = dz;
            int g = 1;

            @Override
            public void run() {
                for (int x = dx; x < (dx1 + 1); x++) {
                    if (tz <= dz1) {
                        for (int y = dy; y < (dy1 + 1); y++) {
                            if (war.getSpawns()[0] != null) {
                                Block block = war.getSpawns()[0].getWorld().getBlockAt(x, y, tz);
                                if (block.getType() == Material.CHEST) {
                                    String dim = LuckyBlockAPI.createDim(block);
                                    war.addChest(dim, "Normal", g);
                                    g++;
                                }
                                if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block))) {
                                    int[] ii = new int[3];
                                    ii[0] = LuckyBlockAPI.luck.get(LuckyBlockAPI.createDim(block));
                                    ii[1] = LuckyBlockAPI.chances.get(LuckyBlockAPI.createDim(block));
                                    ii[2] = LuckyBlockAPI.ids.get(LuckyBlockAPI.createDim(block));
                                    war.addLB(LuckyBlockAPI.createDim(block), ii);
                                }
                            }
                        }
                    } else {
                        task.run();
                        war.setRegistered(true);
                        war.save();
                        getLogger().info("Registered Chests!");
                    }
                }
                tz++;
            }
        }, 5L, 3L));
    }

    public void sendWarning(final Player player, final War war) {
        if (!config.getBoolean("LimitedArea")) {
            return;
        }
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int time = 10;

            @Override
            public void run() {
                if (war.inGame()) {
                    Location f = war.getFirstpos();
                    Location s = war.getSecpos();
                    int maxX = 0;
                    int minX = 0;
                    int maxZ = 0;
                    int minZ = 0;
                    if (f.getBlockX() > s.getBlockX()) {
                        maxX = f.getBlockX();
                    } else {
                        maxX = s.getBlockX();
                        minX = f.getBlockX();
                    }
                    if (f.getBlockZ() > s.getBlockZ()) {
                        maxZ = f.getBlockZ();
                    } else {
                        maxZ = s.getBlockZ();
                        minZ = f.getBlockZ();
                    }
                    if (player.getLocation().getBlockX() > maxX || player.getLocation().getBlockX() < minX ||
                            player.getLocation().getBlockZ() > maxZ || player.getLocation().getBlockZ() < minZ) {
                        if (War.containPlayer(player.getUniqueId())) {
                            if (time > 0) {
                                player.sendMessage(red + "Retrun to the playing area or you will die in " + green + time + red + " seconds.");
                                time--;
                                player.playSound(player.getLocation(), Sound.FIREWORK_LARGE_BLAST, 100f, 1f);
                            } else {
                                if (war.getPlayers().size() > 2) {
                                    war.leaveGame(player.getUniqueId(), false, "false");
                                } else {
                                    war.leaveGame(player.getUniqueId(), true, "false");
                                }
                                LuckyBlockAPI.warnedPlayers.remove(player.getUniqueId());
                                task.run();
                            }
                        } else {
                            LuckyBlockAPI.warnedPlayers.remove(player.getUniqueId());
                            task.run();
                        }
                    } else {
                        LuckyBlockAPI.warnedPlayers.remove(player.getUniqueId());
                        task.run();
                    }
                } else {
                    task.run();
                }
            }
        }, 20L, 20L));
    }

    public void LoopTnt(final TNTPrimed tnt) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int i = 35;

            @Override
            public void run() {
                if (tnt.isValid()) {
                    tnt.setCustomName(darkred + "" + bold + "Tnt " + yellow + i);
                    i--;
                } else {
                    task.run();
                }
            }
        }, 1L, 1L));
    }

    public void replay(UUID uuid) {
        Player player = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (uuid == p.getUniqueId()) {
                player = p;
            }
        }
        if (player != null) {
            War war = War.getGame(uuid);
            if (war != null) {
                String line = ("{\"text\":\"Want To Play Again? \",\"color\":\"green\",\"extra\":[{\"text\":\"Click Here\",\"color\":\"yellow\",\"bold\":\"true\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/" +
                        LuckyBlockCommand.lwcmd + " replay\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§d§lClick Here to play " + gold + bold + war.getName() + " §d§lagain.\"}}]}");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tellraw " + player.getName() + " " + line);
            }
        }
    }

    public void showText(Player player) {
        if (War.getGame(player.getUniqueId()) != null) {
            War war = War.getGame(player.getUniqueId());
            String line = ("{\"text\":\"" + yellow + bold + war.getName() + "\"}");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "title " + player.getName() + " title " + line);
        }
    }

    public void Loop2(final War war) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (war.inGame()) {
                    if (war.getWorld() != null) {
                        for (Entity e : Bukkit.getWorld(war.getWorld()).getEntities()) {
                            if (e instanceof Item) {
                                SummonItem((Item) e);
                            }
                        }
                    }
                } else {
                    task.run();
                }
            }
        }, 1L, 1L));
    }

    public void SummonItem(Item item) {
        try {
            Location loc = new Location(item.getWorld(), item.getLocation().getX(), item.getLocation().getY() - 100, item.getLocation().getZ());
            Location l = new Location(item.getWorld(), item.getLocation().getX(), item.getLocation().getY() - 1, item.getLocation().getZ());
            ArmorStand as = (ArmorStand) item.getWorld().spawnEntity(item.getLocation(), EntityType.ARMOR_STAND);
            as.setArms(true);
            as.setBasePlate(false);
            as.setGravity(true);
            as.setVisible(false);
            as.setSmall(true);
            as.setItemInHand(item.getItemStack());
            as.teleport(l);
            if (item.getItemStack() != null) {
                ItemStack i = item.getItemStack();
                if (i.getType() == Material.WOOD_SWORD || i.getType() == Material.STONE_SWORD || i.getType() == Material.GOLD_SWORD
                        || i.getType() == Material.IRON_SWORD || i.getType() == Material.DIAMOND_SWORD) {
                    EulerAngle e = new EulerAngle(85, 0, 85);
                    //as.setRightArmPose(e);
                }
            }
            as.setCustomName("3ditem");
            item.remove();
        } catch (Exception ex) {
            //
        }
    }

    public void loadEnchantments() {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(glow);
            } catch (IllegalArgumentException e) {
                //
            }
            try {
                Enchantment.registerEnchantment(lightning);
            } catch (IllegalArgumentException e) {
                //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Loop4(final IronGolem golem, final War war) {
        final SchedulerTask task = new SchedulerTask();
        task.setId(getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (war.inGame() && golem.isValid()) {
                    Damageable d = (Damageable) golem;
                    int h = (int) golem.getHealth();
                    if (h > 200) {
                        golem.setCustomName(yellow + "Lucky Iron Golem " + green + h);
                    } else if (h < 201 && h > 100) {
                        golem.setCustomName(yellow + "Lucky Iron Golem " + yellow + h);
                    } else if (h > 50 && h < 101) {
                        golem.setCustomName(yellow + "Lucky Iron Golem " + gold + h);
                    } else {
                        golem.setCustomName(yellow + "Lucky Iron Golem " + red + h);
                    }
                    if (golem.getTarget() == null) {
                        for (Entity e : golem.getNearbyEntities(10, 10, 10)) {
                            if (e instanceof LivingEntity) {
                                LivingEntity l = (LivingEntity) e;
                                if (!(l instanceof IronGolem)) {
                                    if (!(l instanceof Player)) {
                                        golem.setTarget(l);
                                    } else {
                                        Player p = (Player) l;
                                        if (p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
                                            golem.setTarget(p);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (golem.getTarget() != null) {
                        if (golem.getWorld().getName().equalsIgnoreCase(golem.getTarget().getWorld().getName())) {
                            if (golem.getLocation().distance(golem.getTarget().getLocation()) > 10 || golem.getTarget().isValid() == false) {
                                golem.setTarget(null);
                            }
                        }
                    }
                } else {
                    task.run();
                }
            }
        }, 10L, 10L));
    }

    public void closeInventories() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getOpenInventory() != null) {
                if (p.getOpenInventory().getTitle() != null) {
                    String t = p.getOpenInventory().getTitle();
                    if (t.equalsIgnoreCase(darkgreen + "Lucky Block Shop") || t.equalsIgnoreCase(aqua + "" + bold + "Weapons Shop")
                            || t.equalsIgnoreCase(aqua + "" + bold + "Skills Shop") || t.equalsIgnoreCase(aqua + "" + bold + "Shop+")
                            || t.equalsIgnoreCase(darkred + "Player's Gui") || t.equalsIgnoreCase(aqua + "" + bold + "Hats Shop") || t.equalsIgnoreCase(darkpurple + ""
                            + bold + "Cages Shop") || t.equalsIgnoreCase(yellow + "" + bold + "Convertor Shop")) {
                        p.closeInventory();
                    }
                }
            }
        }
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new LuckyBlockWorld();
    }


}
