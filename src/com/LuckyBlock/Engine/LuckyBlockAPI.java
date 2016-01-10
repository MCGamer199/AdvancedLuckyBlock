package com.LuckyBlock.Engine;

import com.LuckyBlock.Commands.LuckyBlockCommand;
import com.LuckyBlock.Events.Kits;
import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.Resources.*;
import com.LuckyBlock.Resources.Team;
import com.LuckyBlock.War.Cage;
import com.LuckyBlock.War.Hat;
import com.LuckyBlock.War.Particle;
import com.LuckyBlock.War.War;
import com.LuckyBlock.War.War.RewardType;
import com.LuckyBlock.enums.ShopItems;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;


/**
 * <b>Lucky Block API</b>
 * <p>
 * This library was created by @MCGamer199 for Lucky Block
 * <p>
 * You can use it and modify it under the following conditions:
 * <ul>
 * <li>Don't claim this class as your own
 * <li>Don't remove this disclaimer
 * </ul>
 * <p>
 * <i>It would be nice if you provide credit to me if you use this class in a published project</i>
 *
 * @author MCGamer199
 * @since 1.4
 */
public class LuckyBlockAPI implements Listener {

    public static final String name = "LuckyBlockAPI";
    public static final String version = "1.0";
    public static LuckyBlockAPI instance;
    public static HashMap<String, Integer> chances = new HashMap<String, Integer>();
    public static HashMap<String, Integer> luck = new HashMap<String, Integer>();
    public static File lbsF = new File(LuckyBlock.instance.getDataFolder() + File.separator + "LuckyBlocks.yml");
    public static FileConfiguration lbs = YamlConfiguration.loadConfiguration(lbsF);
    public static HashMap<UUID, Integer> money = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Short> golds = new HashMap<UUID, Short>();
    public static Location mainlobby = null;
    public static HashMap<String, Boolean> lbwblocks = new HashMap<String, Boolean>();
    public static HashMap<UUID, ItemStack[]> inventories = new HashMap<UUID, ItemStack[]>();
    public static HashMap<UUID, ItemStack[]> ArmorContents = new HashMap<UUID, ItemStack[]>();
    public static HashMap<String, String> BlockOwner = new HashMap<String, String>();
    public static List<Block> problocks = new ArrayList<Block>();
    public static HashMap<UUID, Float> xp = new HashMap<UUID, Float>();
    public static HashMap<UUID, Integer> level = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> maxHealth = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> multiply = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> speedmine = new HashMap<UUID, Integer>();
    public static List<UUID> sat = new ArrayList<UUID>();
    public static HashMap<UUID, Integer> kills = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> wins = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Long> totalDamage = new HashMap<UUID, Long>();
    public static HashMap<UUID, Integer> deaths = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> breakBlocks = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> buildBlocks = new HashMap<UUID, Integer>();
    public static HashMap<UUID, List<ShopItems>> bitems = new HashMap<UUID, List<ShopItems>>();
    public static HashMap<UUID, String> cname = new HashMap<UUID, String>();
    public static HashMap<UUID, int[]> playerlevel = new HashMap<UUID, int[]>();
    public static HashMap<String, String> commands = new HashMap<String, String>();
    public static HashMap<UUID, Integer> plays = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Scoreboard> scoreboard = new HashMap<UUID, Scoreboard>();
    public static List<UUID> warnedPlayers = new ArrayList<UUID>();
    public static List<Team> teams = new ArrayList<Team>();
    public static List<Detector> detectors = new ArrayList<Detector>();
    public static HashMap<String, Integer> ids = new HashMap<String, Integer>();
    protected static ChatColor red = ChatColor.RED;
    protected static ChatColor blue = ChatColor.BLUE;
    protected static ChatColor aqua = ChatColor.AQUA;
    protected static ChatColor black = ChatColor.BLACK;
    protected static ChatColor bold = ChatColor.BOLD;
    protected static ChatColor darkaqua = ChatColor.DARK_AQUA;
    protected static ChatColor darkblue = ChatColor.DARK_BLUE;
    protected static ChatColor darkgray = ChatColor.DARK_GRAY;
    protected static ChatColor darkgreen = ChatColor.DARK_GREEN;
    protected static ChatColor darkpurple = ChatColor.DARK_PURPLE;
    protected static ChatColor darkred = ChatColor.DARK_RED;
    protected static ChatColor gold = ChatColor.GOLD;
    protected static ChatColor gray = ChatColor.GRAY;
    protected static ChatColor green = ChatColor.GREEN;
    protected static ChatColor italic = ChatColor.ITALIC;
    protected static ChatColor lightpurple = ChatColor.LIGHT_PURPLE;
    protected static ChatColor magic = ChatColor.MAGIC;
    protected static ChatColor reset = ChatColor.RESET;
    protected static ChatColor strikethrough = ChatColor.STRIKETHROUGH;
    protected static ChatColor underline = ChatColor.UNDERLINE;
    protected static ChatColor white = ChatColor.WHITE;
    protected static ChatColor yellow = ChatColor.YELLOW;

    /**
     * Saves The Lucky Block
     *
     * @param dim    The Location "World's name + , + X + , + Y + , + Z"
     * @param luck   The Luck of The Lucky Block
     * @param chance The Chance of The Lucky Block
     * @see LuckyBlockAPI
     */
    public static void saveLuckyBlock(String dim, int luck, int chance, int id) {
        if (Bukkit.getPluginManager().getPlugin("LuckyBlock") != null) {
            List<String> list = lbs.getStringList("LuckyBlocks");
            if (!LuckyBlockAPI.luck.containsKey(dim)) {
                LuckyBlockAPI.luck.put(dim, luck);
            }
            if (!chances.containsKey(dim)) {
                LuckyBlockAPI.chances.put(dim, chance);
            }
            int x = 0;
            for (int y = 0; y < list.size(); y++) {
                if (!list.get(y).startsWith(dim)) {
                    x = 1;
                }
            }
            if (x == 1) {
                if (luck == -32768) {
                    luck = 0;
                }
                if (chance < -32768) {
                    chance = 0;
                }
                list.add(dim + " " + luck + " " + chance + " null " + id);
                lbs.set("LuckyBlocks", list);
                try {
                    lbs.save(lbsF);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                for (int y = 0; y < list.size(); y++) {
                    if (list.get(y).startsWith(dim)) {
                        list.remove(y);
                    }
                }
                list.add(dim + " " + luck + " " + chance + " null " + ids);
                lbs.set("LuckyBlocks", list);
                try {
                    lbs.save(lbsF);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Saves The Lucky Block
     *
     * @param dim    The Location "World's name + , + X + , + Y + , + Z".
     * @param luck   The Luck of The Lucky Block.
     * @param chance The Chance of The Lucky Block.
     * @param owner  The Owner's UUID of the Lucky Block.
     * @param id     The id.
     * @see LuckyBlockAPI
     */
    public static void saveLuckyBlock(String dim, int luck, int chance, String owner, int id) {
        if (Bukkit.getPluginManager().getPlugin("LuckyBlock") != null) {
            List<String> list = lbs.getStringList("LuckyBlocks");
            if (!LuckyBlockAPI.luck.containsKey(dim)) {
                LuckyBlockAPI.luck.put(dim, luck);
            }
            if (!chances.containsKey(dim)) {
                LuckyBlockAPI.chances.put(dim, chance);
            }
            int x = 0;
            for (int y = 0; y < list.size(); y++) {
                if (!list.get(y).startsWith(dim)) {
                    x = 1;
                }
            }
            if (x == 1) {
                if (luck == -32768) {
                    luck = 0;
                }
                if (chance < -32768) {
                    chance = 0;
                }
                if (list.contains(dim + " " + luck + " " + chance + " " + owner + " " + id)) {
                    list.remove(dim + " " + luck + " " + chance + " " + owner + " " + id);
                }
                list.add(dim + " " + luck + " " + chance + " " + owner + " " + id);
                lbs.set("LuckyBlocks", list);
                try {
                    lbs.save(lbsF);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                for (int y = 0; y < list.size(); y++) {
                    if (list.get(y).startsWith(dim)) {
                        list.remove(y);
                    }
                }
                list.add(dim + " " + luck + " " + chance + " " + owner + " " + id);
                lbs.set("LuckyBlocks", list);
                try {
                    lbs.save(lbsF);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Saves Player's Exp.
     *
     * @param player
     * @see LuckyBlockAPI
     */
    public static void saveExp(Player player) {
        UUID uuid = player.getUniqueId();
        if (xp.containsKey(uuid) && level.containsKey(uuid)) {
            int l = level.get(uuid);
            float p = xp.get(uuid);
            LuckyBlock.instance.data.set("Players." + uuid + ".Level", l);
            LuckyBlock.instance.data.set("Players." + uuid + ".Exp", p);
            saveConfigs();
        } else {
            LuckyBlock.instance.data.set("Players." + uuid + ".Level", 0);
            LuckyBlock.instance.data.set("Players." + uuid + ".Exp", 0);
        }
    }

    /**
     * Loads Experience.
     *
     * @see LuckyBlockAPI
     */
    public static void loadExp() {
        ConfigurationSection cs = LuckyBlock.instance.data.getConfigurationSection("Players");
        try {
            for (int x = 0; x < cs.getKeys(false).size(); x++) {
                String s = cs.getKeys(false).toArray()[x].toString();
                UUID uuid = UUID.fromString(s);
                float p = (float) cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Exp");
                int l = cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Level");
                xp.put(uuid, p);
                level.put(uuid, l);
            }
        } catch (Exception ex) {
            //
        }
    }

    /**
     * Saves Player's Data.
     *
     * @param uuid
     * @param name
     * @see LuckyBlockAPI
     */
    public static void savePlayerData(UUID uuid, String name) {
        String loc = "Players." + uuid;
        FileConfiguration data = LuckyBlock.instance.data;
        if (!maxHealth.containsKey(uuid)) {
            maxHealth.put(uuid, 1);
        }
        if (!multiply.containsKey(uuid)) {
            multiply.put(uuid, 1);
        }
        if (!golds.containsKey(uuid)) {
            golds.put(uuid, (short) 0);
        }
        if (!money.containsKey(uuid)) {
            money.put(uuid, 0);
        }
        if (!speedmine.containsKey(uuid)) {
            speedmine.put(uuid, 1);
        }
        if (!kills.containsKey(uuid)) {
            kills.put(uuid, 0);
        }
        if (!wins.containsKey(uuid)) {
            wins.put(uuid, 0);
        }
        if (!buildBlocks.containsKey(uuid)) {
            buildBlocks.put(uuid, 0);
        }
        if (!breakBlocks.containsKey(uuid)) {
            breakBlocks.put(uuid, 0);
        }
        if (!deaths.containsKey(uuid)) {
            deaths.put(uuid, 0);
        }
        if (!bitems.containsKey(uuid)) {
            bitems.put(uuid, new ArrayList<ShopItems>());
        }
        if (!cname.containsKey(uuid)) {
            cname.put(uuid, name);
        }
        if (!Hat.getHats().containsKey(uuid)) {
            Hat.getHats().put(uuid, new ArrayList<Hat>());
        }
        if (!Cage.playercages.containsKey(uuid)) {
            Cage.playercages.put(uuid, new ArrayList<Cage>());
        }
        if (!Hat.getSelected().containsKey(uuid)) {
            Hat.getSelected().put(uuid, Hat.NONE);
        }
        if (!Cage.selectedcage.containsKey(uuid)) {
            Cage.selectedcage.put(uuid, Cage.getDefaultCage());
        }
        if (!totalDamage.containsKey(uuid)) {
            totalDamage.put(uuid, 0l);
        }
        if (!LuckyBlock.gifts.containsKey(uuid)) {
            LuckyBlock.gifts.put(uuid, 0);
        }
        if (!playerlevel.containsKey(uuid)) {
            int[] p = new int[2];
            p[0] = 0;
            p[1] = 0;
            playerlevel.put(uuid, p);
        }
        if (!plays.containsKey(uuid)) {
            plays.put(uuid, 0);
        }
        if (!Particle.unlocked.containsKey(uuid)) {
            Particle.unlocked.put(uuid, new ArrayList<Particle>());
        }
        if (!Particle.selected.containsKey(uuid)) {
            Particle.selected.put(uuid, Particle.NONE);
        }
        if (maxHealth.get(uuid) < 1) {
            maxHealth.put(uuid, 1);
        }
        if (multiply.get(uuid) < 1) {
            multiply.put(uuid, 1);
        }
        if (speedmine.get(uuid) < 1) {
            speedmine.put(uuid, 1);
        }
        if (name != null) {
            data.set("Players." + uuid + ".Name", name);
        }
        data.set(loc + ".Money", money.get(uuid));
        data.set(loc + ".Gold", golds.get(uuid));
        data.set(loc + ".CustomName", cname.get(uuid));
        int[] i = playerlevel.get(uuid);
        data.set(loc + ".Stats.Level", i[0]);
        data.set(loc + ".Stats.XP", i[1]);
        data.set(loc + ".Attributes.MaxHealth", maxHealth.get(uuid));
        data.set(loc + ".Attributes.Multiply", multiply.get(uuid));
        data.set(loc + ".Attributes.Haste", speedmine.get(uuid));
        data.set(loc + ".Stats.Kills", kills.get(uuid));
        data.set(loc + ".Stats.Wins", wins.get(uuid));
        data.set(loc + ".Stats.Deaths", deaths.get(uuid));
        data.set(loc + ".Stats.BlocksB", buildBlocks.get(uuid));
        data.set(loc + ".Stats.BlocksD", breakBlocks.get(uuid));
        data.set(loc + ".Stats.GamesPlayed", plays.get(uuid));
        data.set(loc + ".Stats.TotalDamage", totalDamage.get(uuid));
        data.set(loc + ".CustomName", cname.get(uuid));
        data.set(loc + ".Gifts", LuckyBlock.gifts.get(uuid));
        List<String> al = new ArrayList<String>();
        for (int x = 0; x < Particle.unlocked.get(uuid).size(); x++) {
            al.add(Particle.unlocked.get(uuid).get(x).toString());
        }
        data.set(loc + ".UnlockedParticles", al);
        data.set(loc + ".SelectedParticle", Particle.selected.get(uuid).toString());
        if (Kits.ckit.containsKey(uuid)) {
            data.set("Players." + uuid + ".Kit", Kits.ckit.get(uuid));
        }
        if (Hat.getHats().get(uuid).size() > 0) {
            List<String> l = new ArrayList<String>();
            for (int x = 0; x < Hat.getHats().get(uuid).size(); x++) {
                l.add(Hat.getHats().get(uuid).get(x).toString());
            }
            data.set("Players." + uuid + ".Hats", l);
        }
        if (Cage.playercages.get(uuid).size() > 0) {
            List<String> l = new ArrayList<String>();
            for (int x = 0; x < Cage.playercages.get(uuid).size(); x++) {
                l.add(Cage.playercages.get(uuid).get(x).getName());
            }
            data.set("Players." + uuid + ".UnlockedCages", l);
        }
        data.set("Players." + uuid + ".SelectedHat", Hat.getSelected().get(uuid).toString());
        data.set("Players." + uuid + ".SelectedCage", Cage.selectedcage.get(uuid).getName());
        List<String> list = new ArrayList<String>();
        for (int x = 0; x < bitems.get(uuid).size(); x++) {
            list.add(bitems.get(uuid).get(x).toString());
        }
        data.set("Players." + uuid + ".UnlockedItems", list);
        LuckyBlock.instance.data = data;
        saveConfigs();
    }

    /**
     * Loads Data.
     */
    public static void loadData() {
        ConfigurationSection cs = LuckyBlock.instance.data.getConfigurationSection("Players");
        try {
            for (int x = 0; x < cs.getKeys(false).size(); x++) {
                String s = cs.getKeys(false).toArray()[x].toString();
                UUID uuid = UUID.fromString(s);
                int mh = cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Attributes.MaxHealth");
                int m = cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Attributes.Multiply");
                int h = cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Attributes.Haste");
                int mo = cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Money");
                short g = (short) cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Gold");
                maxHealth.put(uuid, mh);
                multiply.put(uuid, m);
                money.put(uuid, mo);
                golds.put(uuid, g);
                speedmine.put(uuid, h);
                kills.put(uuid, cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.Kills"));
                wins.put(uuid, cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.Wins"));
                buildBlocks.put(uuid, cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.BlocksB"));
                breakBlocks.put(uuid, cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.BlocksD"));
                deaths.put(uuid, cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.Deaths"));
                cname.put(uuid, cs.getString(cs.getKeys(false).toArray()[x].toString() + ".CustomName"));
                plays.put(uuid, cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.GamesPlayed"));
                totalDamage.put(uuid, (long) cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.TotalDamage"));
                LuckyBlock.gifts.put(uuid, cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Gifts"));
                List<Particle> lp = new ArrayList<Particle>();
                for (int i = 0; i < cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".UnlockedParticles").size(); i++) {
                    lp.add(Particle.valueOf(cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".UnlockedParticles").get(i)));
                }
                Particle.unlocked.put(uuid, lp);
                Particle.selected.put(uuid, Particle.valueOf(cs.getString(cs.getKeys(false).toArray()[x].toString() + ".SelectedParticle")));
                if (cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".Hats").size() > 0) {
                    List<Hat> l = new ArrayList<Hat>();
                    for (int i = 0; i < cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".Hats").size(); i++) {
                        l.add(Hat.valueOf(cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".Hats").get(i)));
                    }
                    Hat.getHats().put(uuid, l);
                }
                if (cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".UnlockedCages").size() > 0) {
                    List<Cage> l = new ArrayList<Cage>();
                    for (int i = 0; i < cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".UnlockedCages").size(); i++) {
                        l.add(Cage.fromName(cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".UnlockedCages").get(i)));
                    }
                    Cage.playercages.put(uuid, l);
                }
                if (cs.getString(cs.getKeys(false).toArray()[x].toString() + ".SelectedHat") != null) {
                    String value = cs.getString(cs.getKeys(false).toArray()[x].toString() + ".SelectedHat");
                    Hat.getSelected().put(uuid, Hat.valueOf(value));
                }
                if (cs.getString(cs.getKeys(false).toArray()[x].toString() + ".SelectedCage") != null) {
                    Cage.selectedcage.put(uuid, Cage.fromName(cs.getString(cs.getKeys(false).toArray()[x].toString() + ".SelectedCage")));
                }
                int[] i = new int[2];
                i[0] = cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.Level");
                i[1] = cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".Stats.XP");
                playerlevel.put(uuid, i);
                List<String> list = cs.getStringList(cs.getKeys(false).toArray()[x].toString() + ".UnlockedItems");
                List<ShopItems> sh = new ArrayList<ShopItems>();
                for (int y = 0; y < list.size(); y++) {
                    sh.add(ShopItems.valueOf(list.get(y)));
                }
                bitems.put(uuid, sh);
                if (cs.getString(cs.getKeys(false).toArray()[x].toString() + ".Kit") != null) {
                    Kits.ckit.put(uuid, cs.getString(cs.getKeys(false).toArray()[x].toString() + ".Kit"));
                }
            }
        } catch (Exception ex) {
            //
        }
    }

    /**
     * Loads Lucky Blocks.
     *
     * @see LuckyBlockAPI
     */
    public static void loadLuckyBlocks() {
        try {
            for (int x = 0; x < lbs.getStringList("LuckyBlocks").size(); x++) {
                String[] d = lbs.getStringList("LuckyBlocks").get(x).split(" ");
                String[] dd = d[0].split(",");
                luck.put(d[0], Integer.parseInt(d[1]));
                chances.put(d[0], Integer.parseInt(d[2]));
                ids.put(d[0], Integer.parseInt(d[4]));
                int xx = Integer.parseInt(dd[1]);
                int y = Integer.parseInt(dd[2]);
                int z = Integer.parseInt(dd[3]);
                if (Bukkit.getWorld(dd[0]) != null) {
                    final Block block = Bukkit.getWorld(dd[0]).getBlockAt(xx, y, z);
                    final String dim = createDim(block);
                    if (block.getType() != Material.AIR) {
                        LuckyBlock.instance.playEffects(block, block.getLocation(), LuckyBlock.randoms.nextInt(60) + 1, 1);
                    } else {
                        removeLB(dim);
                    }
                    final SchedulerTask task = new SchedulerTask();
                    task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncDelayedTask(LuckyBlock.instance, new Runnable() {
                        @Override
                        public void run() {
                            if (Types.fromBlock(dim) != null && Types.fromBlock(dim).getAbilities().size() > 0 && Types.fromBlock(dim).getAbilities().contains(
                                    BlockAbilities.RESISTANCE_EXPLOSIONS)) {
                                LuckyBlock.instance.Loops(block);
                            }
                            task.run();
                        }
                    }, 10L));
                }
                if (!d[3].equalsIgnoreCase("null")) {
                    BlockOwner.put(d[0], d[3]);
                }
            }
            LuckyBlock.instance.getLogger().info("Loaded Lucky Blocks. " + lbs.getStringList("LuckyBlocks").size() + " Lucky Blocks Found!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Removes Lucky Block.
     *
     * @param dim The Location "World's name + , + X + , + Y + , + Z"
     * @param
     * @see LuckyBlockAPI
     */
    public static void removeLB(String dim) {
        List<String> list = lbs.getStringList("LuckyBlocks");
        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).startsWith(dim)) {
                list.remove(x);
            }
        }
        lbs.set("LuckyBlocks", list);
        saveConfigs();
        LuckyBlockAPI.luck.remove(dim);
        LuckyBlockAPI.chances.remove(dim);
    }

    /**
     * Saves Portals.
     *
     * @see LuckyBlockAPI
     */
    public static void savePortals() {
        List<String> list = new ArrayList<String>();
        for (String s : lbwblocks.keySet()) {
            list.add(s + "," + lbwblocks.get(s));
        }
        LuckyBlock.instance.data.addDefault("Portals", list);
        saveConfigs();
    }

    /**
     *
     */
    public static void loadPortals() {
        List<String> list = LuckyBlock.instance.data.getStringList("Portals");
        if (list.size() > 0) {
            for (int x = 0; x < list.size(); x++) {
                String[] d = list.get(x).split(",");
                String g = d[0] + "," + d[1] + "," + d[2] + "," + d[3];
                lbwblocks.put(g, Boolean.parseBoolean(d[4]));
            }
        }
    }

    /**
     * Gets Lucky Block
     *
     * @param number The number of the Lucky Block from the list
     * @see LuckyBlockAPI
     */
    public static String getLuckyBlock(int number) {
        String s = null;
        List<String> list = lbs.getStringList("LuckyBlocks");
        if (list.size() > number) {
            s = list.get(number);
        }
        return s;
    }

    /**
     * Checks if the specified block is a lucky block.
     *
     * @param file The File
     * @param dim  The Location "World + , + X + , + Y + , + Z"
     * @return True if the block is a lucky block, False if the block isn't a lucky block.
     */
    public static boolean IsLuckyBlock(String dim) {
        boolean lb = false;
        if (luck.containsKey(dim)) {
            lb = true;
        }
        if (chances.containsKey(dim)) {
            lb = true;
        }
        return lb;
    }

    /**
     * Gets all Lucky Blocks from a file
     *
     * @param file The File
     * @return
     */
    public static List<String> getLuckyBlocks(FileConfiguration file) {
        List<String> lbs = file.getStringList("LuckyBlocks");
        return lbs;
    }

    /**
     * Get Money By Unique Identifier.
     *
     * @param player The Player.
     * @see LuckyBlockAPI
     */
    public static int getMoney(Player player) {
        UUID uuid = player.getUniqueId();
        int m = 0;
        if (money.containsKey(uuid)) {
            m = money.get(uuid);
        } else {
            money.put(uuid, 0);
        }
        return m;
    }

    /**
     * Get Money By Player's Name.
     *
     * @param player The Player.
     * @see LuckyBlockAPI
     */
    public static short getGold(Player player) {
        UUID uuid = player.getUniqueId();
        short g = 0;
        if (golds.containsKey(uuid)) {
            g = golds.get(uuid);
        } else {
            golds.put(uuid, (short) 0);
        }
        return g;
    }

    /**
     * Gets Item.
     *
     * @see LuckyBlockAPI
     */
    @SuppressWarnings("deprecation")
    public static ItemStack getItem(FileConfiguration config, List<String> list, int x, Player player) {
        ItemStack item = null;
        String[] o = list.get(x).split("//");
        for (int op = 0; op < o.length; op++) {
            String st = o[op];
            String[] aa = st.split(config.getString("SplitSymbol"));
            if (aa.length > 0) {
                for (int i = 0; i < aa.length; i++) {
                    if (aa[i].startsWith("type:")) {
                        String[] a = aa[i].split("type:");
                        if (a[1].startsWith("%lb%")) {
                            String[] d = a[1].split("%lb%");
                            int id = Integer.parseInt(d[1]);
                            item = getLB(id).toItemStack(new Random().nextInt(20));
                        } else if (a[1].equalsIgnoreCase("%LuckySword%")) {
                            item = new ItemStack(Material.DIAMOND_SWORD);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_SWORD)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.DAMAGE_ALL, 1);
                                    item = createItemStack(Material.DIAMOND_SWORD, 1, (short) 0, yellow + "Lucky Sword", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyBow%")) {
                            item = new ItemStack(Material.BOW);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_BOW)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.ARROW_DAMAGE, 1);
                                    item = createItemStack(Material.BOW, 1, (short) 0, yellow + "Lucky Bow", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%ThorAxe%")) {
                            item = new ItemStack(Material.IRON_AXE);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.THOR_AXE)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(LuckyBlock.lightning, 10);
                                    item = createItemStack(Material.IRON_AXE, 1, (short) 0, aqua + "" + bold + "Thor's Axe", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyTool%")) {
                            item = new ItemStack(Material.NAME_TAG);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.ADVANCED_LUCKY_BLOCK_TOOL)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(LuckyBlock.glow, 1);
                                    item = createItemStack(Material.NAME_TAG, 1, (short) 0, yellow + "Advanced Lucky Block Tool", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyAxe%")) {
                            item = new ItemStack(Material.DIAMOND_AXE);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_AXE)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.DIG_SPEED, 1);
                                    e.put(Enchantment.DURABILITY, 2);
                                    item = createItemStack(Material.DIAMOND_AXE, 1, (short) 0, yellow + "Lucky Axe", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyPickaxe%")) {
                            item = new ItemStack(Material.DIAMOND_PICKAXE);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_PICKAXE)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.LOOT_BONUS_BLOCKS, 10);
                                    e.put(Enchantment.DURABILITY, 2);
                                    item = createItemStack(Material.DIAMOND_PICKAXE, 1, (short) 0, yellow + "Lucky Pickaxe", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyShovel%")) {
                            item = new ItemStack(Material.DIAMOND_SPADE);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_SHOVEL)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.DIG_SPEED, 1);
                                    e.put(Enchantment.DURABILITY, 2);
                                    item = createItemStack(Material.DIAMOND_SPADE, 1, (short) 0, yellow + "Lucky Shovel", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyHelmet%")) {
                            item = new ItemStack(Material.DIAMOND_HELMET);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_HELMET)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    e.put(Enchantment.WATER_WORKER, 1);
                                    item = createItemStack(Material.DIAMOND_HELMET, 1, (short) 0, yellow + "Lucky Helmet", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyChestplate%")) {
                            item = new ItemStack(Material.DIAMOND_CHESTPLATE);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_CHESTPLATE)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    e.put(Enchantment.THORNS, 1);
                                    item = createItemStack(Material.DIAMOND_CHESTPLATE, 1, (short) 0, yellow + "Lucky Chestplate", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyLeggings%")) {
                            item = new ItemStack(Material.DIAMOND_LEGGINGS);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_LEGGINGS)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    e.put(Enchantment.PROTECTION_EXPLOSIONS, 1);
                                    item = createItemStack(Material.DIAMOND_LEGGINGS, 1, (short) 0, yellow + "Lucky Leggings", null, e);
                                    return item;
                                }
                            }
                        } else if (a[1].equalsIgnoreCase("%LuckyBoots%")) {
                            item = new ItemStack(Material.DIAMOND_BOOTS);
                            if (player != null) {
                                UUID uuid = player.getUniqueId();
                                if (bitems.containsKey(uuid) && bitems.get(uuid).contains(ShopItems.LUCKY_BOOTS)) {
                                    Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
                                    e.put(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    e.put(Enchantment.PROTECTION_FALL, 1);
                                    item = createItemStack(Material.DIAMOND_BOOTS, 1, (short) 0, yellow + "Lucky Boots", null, e);
                                    return item;
                                }
                            }
                        }
                        try {
                            if (item == null) {
                                item = new ItemStack(Material.getMaterial(a[1].toUpperCase()));
                            }
                        } catch (Exception ex) {
                            player.sendMessage("Error with: " + a[1]);
                            ex.printStackTrace();
                        }
                    } else if (aa[i].startsWith("typeid:")) {
                        String[] a = aa[i].split("typeid:");
                        String[] am = a[1].split("-");
                        String[] an = a[1].split("/");
                        if (an.length > 1) {
                            int p = LuckyBlock.randoms.nextInt(an.length);
                            try {
                                item = new ItemStack(Material.getMaterial(Integer.parseInt(an[p])));
                            } catch (Exception ex) {
                                item = new ItemStack(Material.STONE);
                            }
                        } else {
                            if (am.length > 1) {
                                try {
                                    item = new ItemStack(Material.getMaterial(LuckyBlock.randoms.nextInt(((Integer.parseInt(am[1]) - Integer.parseInt(am[0]))) + 1) +
                                            Integer.parseInt(am[0])));
                                } catch (Exception ex) {
                                    item = new ItemStack(Material.STONE);
                                }
                            } else {
                                try {
                                    item = new ItemStack(Material.getMaterial(Integer.parseInt(a[1])));
                                } catch (Exception ex) {
                                    item = new ItemStack(Material.STONE);
                                }
                            }
                        }
                    } else if (aa[i].startsWith("amount:")) {
                        String[] b = aa[i].split("amount:");
                        String[] am = b[1].split("-");
                        if (am.length > 1) {
                            try {
                                item.setAmount(LuckyBlock.randoms.nextInt(((Integer.parseInt(am[1]) - Integer.parseInt(am[0]))) + 1) + Integer.parseInt(am[0]));
                            } catch (Exception ex) {
                                item.setAmount(1);
                            }
                        } else {
                            try {
                                item.setAmount(Integer.parseInt(b[1]));
                            } catch (NumberFormatException e) {
                                item.setAmount(1);
                            }
                        }
                    } else if (aa[i].startsWith("data:")) {
                        String[] c = aa[i].split("data:");
                        String[] cd = c[1].split("-");
                        if (cd.length > 1) {
                            item.setDurability((short) ((short) LuckyBlock.randoms.nextInt(((Integer.parseInt(cd[1]) - Integer.parseInt(cd[0]))) + 1) + Integer.parseInt(cd[0])));
                        } else {
                            try {
                                item.setDurability(Short.parseShort(c[1]));
                            } catch (NumberFormatException ex) {
                                item.setDurability((short) 0);
                            }
                        }
                    } else if (aa[i].startsWith("DisplayName:")) {
                        String[] e = aa[i].split("DisplayName:");
                        if (!e[1].equalsIgnoreCase("null")) {
                            ItemMeta itemM = item.getItemMeta();
                            itemM.setDisplayName(ChatColor.translateAlternateColorCodes('&', e[1]));
                            item.setItemMeta(itemM);
                        }
                    } else if (aa[i].startsWith("Lore:")) {
                        String[] g = aa[i].split("Lore:");
                        if (!g[1].equalsIgnoreCase("null")) {
                            ItemMeta itemM = item.getItemMeta();
                            String[] bb = g[1].split("%%");
                            List<String> itemL = new ArrayList<String>();
                            for (int size = 0; size < bb.length; size++) {
                                if (player != null) {
                                    bb[size] = bb[size].replace("{playername}", player.getName());
                                }
                                if (item.getType() == Material.LEATHER_HELMET || item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_LEGGINGS
                                        || item.getType() == Material.LEATHER_BOOTS) {
                                    LeatherArmorMeta l = (LeatherArmorMeta) item.getItemMeta();
                                    bb[size] = bb[size].replace("{RED}", "" + l.getColor().getRed());
                                    bb[size] = bb[size].replace("{BLUE}", "" + l.getColor().getBlue());
                                    bb[size] = bb[size].replace("{GREEN}", "" + l.getColor().getGreen());
                                }
                                bb[size] = bb[size].replace(ChatColor.stripColor("{randomnumber}"), "" + LuckyBlock.randoms.nextInt(1000000));
                                bb[size] = bb[size].replace("{randomcolor}", LuckyBlock.colors.get(LuckyBlock.randoms.nextInt(LuckyBlock.colors.size())));
                                itemL.add(ChatColor.translateAlternateColorCodes('&', bb[size]));
                            }
                            itemM.setLore(itemL);
                            item.setItemMeta(itemM);
                        }
                    } else if (aa[i].startsWith("Enchants:")) {
                        String[] I = aa[i].split("Enchants:");
                        if (!I[1].equalsIgnoreCase("null")) {
                            ItemMeta itemM = item.getItemMeta();
                            String[] cc = I[1].split("%%");
                            for (int size = 0; size < cc.length; size++) {
                                String[] dd = cc[size].split(" ");
                                String[] am = dd[0].split("-");
                                int ench = 0;
                                int level = 0;
                                if (am.length > 1) {
                                    try {
                                        ench = LuckyBlock.randoms.nextInt(((Integer.parseInt(am[1]) - Integer.parseInt(am[0]))) + 1) + Integer.parseInt(am[0]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    try {
                                        ench = Integer.parseInt(dd[0]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                String[] al = dd[1].split("-");
                                if (al.length > 1) {
                                    try {
                                        level = LuckyBlock.randoms.nextInt(((Integer.parseInt(al[1]) - Integer.parseInt(al[0]))) + 1) + Integer.parseInt(al[0]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    try {
                                        level = Integer.parseInt(dd[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                itemM.addEnchant(Enchantment.getById(ench), level, true);
                            }
                            item.setItemMeta(itemM);
                        }
                    } else if (aa[i].startsWith("PotionEffects:")) {
                        String[] k = aa[i].split("PotionEffects:");
                        if (!k[1].equalsIgnoreCase("null")) {
                            PotionMeta itemM = (PotionMeta) item.getItemMeta();
                            String[] ee = k[1].split("%%");
                            for (int size = 0; size < ee.length; size++) {
                                String[] ff = ee[size].split(" ");
                                if (ff.length == 3) {
                                    int id = 1;
                                    int sec = 30;
                                    int amplifier = 0;
                                    String[] BBB = ff[0].split("-");
                                    String[] BBBI = ff[1].split("-");
                                    String[] BBBII = ff[2].split("-");
                                    int zzz = 0;
                                    if (BBB.length > 1) {
                                        id = LuckyBlock.randoms.nextInt(((Integer.parseInt(BBB[1]) - Integer.parseInt(BBB[0]))) + 1) + Integer.parseInt(BBB[0]);
                                        zzz = 1;
                                    } else {
                                        id = Integer.parseInt(ff[0]);
                                    }
                                    if (BBBI.length > 1) {
                                        sec = LuckyBlock.randoms.nextInt(((Integer.parseInt(BBBI[1]) - Integer.parseInt(BBBI[0]))) + 1) + Integer.parseInt(BBBI[0]);
                                        zzz = 1;
                                    } else {
                                        sec = Integer.parseInt(ff[1]);
                                    }
                                    if (BBBII.length > 1) {
                                        amplifier = LuckyBlock.randoms.nextInt(((Integer.parseInt(BBBII[1]) - Integer.parseInt(BBBII[0]))) + 1) + Integer.parseInt(BBBII[0]);
                                        zzz = 1;
                                    } else {
                                        amplifier = Integer.parseInt(ff[2]);
                                    }
                                    if (zzz == 1) {
                                        itemM.addCustomEffect(new PotionEffect(PotionEffectType.getById(id), sec * 20, amplifier), true);
                                    } else {
                                        try {
                                            itemM.addCustomEffect(new PotionEffect(PotionEffectType.getById(Integer.parseInt(ff[0])), Integer.parseInt(ff[1]) * 20,
                                                    Integer.parseInt(ff[2])), true);
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                } else if (ff.length == 2) {
                                    try {
                                        itemM.addCustomEffect(new PotionEffect(PotionEffectType.getById(Integer.parseInt(ff[0])), Integer.parseInt(ff[1]) * 20, 0), true);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (ff.length == 1) {
                                    try {
                                        itemM.addCustomEffect(new PotionEffect(PotionEffectType.getById(Integer.parseInt(ff[0])), 600, 0), true);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    itemM.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0), true);
                                }
                            }
                            item.setItemMeta(itemM);
                        }
                    } else if (aa[i].startsWith("BookMeta:")) {
                        String[] I = aa[i].split("BookMeta:");
                        if (!I[1].equalsIgnoreCase("null")) {
                            BookMeta bm = (BookMeta) item.getItemMeta();
                            String[] cc = I[1].split("%%");
                            for (int v = 0; v < cc.length; v++) {
                                if (cc[v].startsWith("Author:")) {
                                    String[] cc1 = cc[v].split("Author:");
                                    bm.setAuthor(ChatColor.translateAlternateColorCodes('&', cc1[1]));
                                } else if (cc[v].startsWith("Title:")) {
                                    String[] cc1 = cc[v].split("Title:");
                                    bm.setTitle(ChatColor.translateAlternateColorCodes('&', cc1[1]));
                                } else if (cc[v].startsWith("Pages:")) {
                                    String[] cc1 = cc[v].split("Pages:");
                                    String[] cc2 = cc1[1].split("^");
                                    for (int size = 0; size < cc2.length; size++) {
                                        bm.addPage(ChatColor.translateAlternateColorCodes('&', cc2[size]));
                                    }
                                }
                            }
                            item.setItemMeta(bm);
                        }
                    } else if (aa[i].startsWith("LeatherArmor:")) {
                        String[] I = aa[i].split("LeatherArmor:");
                        LeatherArmorMeta lm = (LeatherArmorMeta) item.getItemMeta();
                        String[] II = I[1].split(",");
                        if (II.length == 3) {
                            String[] III = II[0].split("-");
                            String[] IV = II[1].split("-");
                            String[] V = II[2].split("-");
                            int c1 = 0;
                            int c2 = 0;
                            int c3 = 0;
                            int zzz = 0;
                            if (III.length > 1) {
                                c1 = LuckyBlock.randoms.nextInt(((Integer.parseInt(III[1]) - Integer.parseInt(III[0]))) + 1) + Integer.parseInt(III[0]);
                                zzz = 1;
                            } else {
                                c1 = Integer.parseInt(II[0]);
                            }
                            if (IV.length > 1) {
                                c2 = LuckyBlock.randoms.nextInt(((Integer.parseInt(IV[1]) - Integer.parseInt(IV[0]))) + 1) + Integer.parseInt(IV[0]);
                                zzz = 1;
                            } else {
                                c2 = Integer.parseInt(II[1]);
                            }
                            if (V.length > 1) {
                                c3 = LuckyBlock.randoms.nextInt(((Integer.parseInt(V[1]) - Integer.parseInt(V[0]))) + 1) + Integer.parseInt(V[0]);
                                zzz = 1;
                            } else {
                                c3 = Integer.parseInt(II[2]);
                            }
                            if (zzz == 1) {
                                lm.setColor(Color.fromRGB(c1, c2, c3));
                            } else {
                                try {
                                    lm.setColor(Color.fromRGB(Integer.parseInt(II[0]), Integer.parseInt(II[1]), Integer.parseInt(II[2])));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                lm.setColor(Color.fromRGB(Integer.parseInt(II[0])));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        item.setItemMeta(lm);
                    } else if (aa[i].startsWith("SkullOwner:")) {
                        String[] I = aa[i].split("SkullOwner:");
                        if (!I[1].equalsIgnoreCase("null")) {
                            SkullMeta skull = (SkullMeta) item.getItemMeta();
                            skull.setOwner(I[1]);
                            item.setItemMeta(skull);
                        }
                    } else if (aa[i].startsWith("StoredEnchants:")) {
                        String[] I = aa[i].split("StoredEnchants:");
                        String[] cc = I[1].split("%%");
                        EnchantmentStorageMeta itemM = (EnchantmentStorageMeta) item.getItemMeta();
                        for (int size1 = 0; size1 < cc.length; size1++) {
                            String[] dd = cc[size1].split(" ");
                            String[] am = dd[0].split("-");
                            int ench = 0;
                            int level = 0;
                            if (am.length > 1) {
                                try {
                                    ench = LuckyBlock.randoms.nextInt(((Integer.parseInt(am[1]) - Integer.parseInt(am[0]))) + 1) + Integer.parseInt(am[0]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                try {
                                    ench = Integer.parseInt(dd[0]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            String[] al = dd[1].split("-");
                            if (al.length > 1) {
                                try {
                                    level = LuckyBlock.randoms.nextInt(((Integer.parseInt(al[1]) - Integer.parseInt(al[0]))) + 1) + Integer.parseInt(al[0]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                try {
                                    level = Integer.parseInt(dd[1]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            itemM.addStoredEnchant(Enchantment.getById(ench), level, true);
                        }
                        item.setItemMeta(itemM);
                    } else if (aa[i].startsWith("Luck:")) {
                        String[] I = aa[i].split("Luck:");
                        String[] K = I[1].split("-");
                        int luck = 0;
                        if (K.length > 1) {
                            try {
                                luck = LuckyBlock.randoms.nextInt(((Integer.parseInt(K[1]) - Integer.parseInt(K[0]))) + 1) + Integer.parseInt(K[0]);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                luck = Integer.parseInt(I[1]);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
                            ItemMeta iM = item.getItemMeta();
                            List<String> l = iM.getLore();
                            if (luck > 0 && luck < 40) {
                                l.add(yellow + "%" + luck);
                            } else if (luck == 0) {
                                l.add(gold + "%" + luck);
                            } else if (luck >= 40 && luck < 100) {
                                l.add(green + "%" + luck);
                            } else if (luck > 99) {
                                l.add(green + "%99");
                            } else {
                                l.add(red + "%" + luck);
                            }
                            iM.setLore(l);
                            item.setItemMeta(iM);
                        } else {
                            ItemMeta iM = item.getItemMeta();
                            List<String> l = new ArrayList<String>();
                            if (luck > 0 && luck < 40) {
                                l.add(yellow + "%" + luck);
                            } else if (luck == 0) {
                                l.add(gold + "%" + luck);
                            } else if (luck >= 40 && luck < 100) {
                                l.add(green + "%" + luck);
                            } else if (luck > 99) {
                                l.add(green + "%99");
                            } else {
                                l.add(red + "%" + luck);
                            }
                            iM.setLore(l);
                            item.setItemMeta(iM);
                        }
                    }
                }
            }
        }
        return item;
    }

    /**
     * Sets money for player.
     *
     * @param player The Player
     * @param value  The number of money
     * @see LuckyBlockAPI
     */
    public static void setMoney(Player player, int value) {
        money.put(player.getUniqueId(), value);
        LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
    }

    /**
     * Sets Gold for player.
     *
     * @param player The Player
     * @param value  The number of gold
     * @see LuckyBlockAPI
     */
    public static void setGold(Player player, int value) {
        if (value < 32001) {
            golds.put(player.getUniqueId(), (short) value);
            LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
        }
    }

    /**
     * Adds money for player.
     *
     * @param player The Player
     * @param value  The value
     * @see LuckyBlockAPI
     */
    public static void addMoney(Player player, int value) {
        if (money.containsKey(player.getUniqueId())) {
            money.put(player.getUniqueId(), money.get(player.getUniqueId()) + value);
        } else {
            money.put(player.getUniqueId(), value);
        }
        LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
    }

    /**
     * Adds gold for player.
     *
     * @param player The Player
     * @param value  The value
     * @see LuckyBlockAPI
     */
    public static void addGold(Player player, int value) {
        if (!golds.containsKey(player.getUniqueId())) {
            golds.put(player.getUniqueId(), (short) 0);
        }
        if (golds.get(player.getUniqueId()) < 32001) {
            golds.put(player.getUniqueId(), (short) (golds.get(player.getUniqueId()) + value));
            LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
        }
    }

    /**
     * Removes money from player.
     *
     * @param player The Player
     * @param value  The value
     * @see LuckyBlockAPI
     */
    public static void removeMoney(Player player, int value) {
        if (money.containsKey(player.getUniqueId())) {
            if (money.get(player.getUniqueId()) > value) {
                money.put(player.getUniqueId(), money.get(player.getUniqueId()) - value);
            } else {
                money.put(player.getUniqueId(), 0);
            }
        } else {
            money.put(player.getUniqueId(), 0);
        }
        LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
    }

    /**
     * Removes gold from player.
     *
     * @param player The Player
     * @param value  The valie
     * @see LuckyBlockAPI
     */
    public static void removeGold(Player player, int value) {
        if (golds.containsKey(player.getUniqueId())) {
            if (golds.get(player.getUniqueId()) > value) {
                golds.put(player.getUniqueId(), (short) (golds.get(player.getUniqueId()) - value));
            } else {
                golds.put(player.getUniqueId(), (short) 0);
            }
        } else {
            golds.put(player.getUniqueId(), (short) 0);
        }
        LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
    }

    /**
     * Creates item.
     *
     * @param type   The Material type
     * @param amount The Amount
     * @param data   The Data or durability
     * @param name   The Display Name
     * @see LuckyBlockAPI
     */
    public static ItemStack createItemStack(Material type, int amount, short data, String name) {
        ItemStack item = new ItemStack(type);
        if (amount > 0) {
            item.setAmount(amount);
        }
        if (data > 0) {
            item.setDurability(data);
        }
        ItemMeta itemM = item.getItemMeta();
        if (name != null) {
            itemM.setDisplayName(name);
        }
        item.setItemMeta(itemM);
        return item;
    }

    /**
     * Gets Item.
     *
     * @see LuckyBlockAPI
     */
    public static void dropItem(List<String> list, int x, String world, Location loc) {
        ItemStack item = null;
        String[] o = list.get(x).split("//");
        for (int op = 0; op < o.length; op++) {
            String st = o[op];
            String[] aa = st.split(LuckyBlock.instance.lbf.getString("SplitSymbol"));
            if (aa.length > 0) {
                int tries = 1;
                for (int i = 0; i < aa.length; i++) {
                    if (aa[i].startsWith("tries:")) {
                        String[] s = aa[i].split("tries:");
                        String[] ss = s[1].split("-");
                        if (ss.length > 1) {
                            tries = LuckyBlock.randoms.nextInt(((Integer.parseInt(ss[1]) - Integer.parseInt(ss[0]))) + 1) + Integer.parseInt(ss[0]);
                        } else {
                            try {
                                tries = Integer.parseInt(s[1]);
                            } catch (NumberFormatException ex) {
                                tries = 1;
                            }
                        }
                    }
                }
                for (int times = tries; times > 0; times--) {
                    item = getItem(st, LuckyBlock.instance.lbf.getString("SplitSymbol"));
                    Bukkit.getWorld(world).dropItem(loc, item);
                }
            }
        }
    }

    /**
     * Creates item.
     *
     * @param type     The Material type
     * @param amount   The Amount
     * @param data     The Data or durability
     * @param name     The Display Name
     * @param lore     The Lore
     * @param enchants The Enchants
     * @see LuckyBlockAPI
     */
    public static ItemStack createItemStack(Material type, int amount, short data, String name, List<String> lore, Map<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(type);
        if (amount > 0) {
            item.setAmount(amount);
        }
        if (data > 0) {
            item.setDurability(data);
        }
        ItemMeta itemM = item.getItemMeta();
        if (name != null) {
            itemM.setDisplayName(name);
        }
        if (lore != null && lore.size() > 0) {
            itemM.setLore(lore);
        }
        if (enchants != null && enchants.size() > 0) {
            for (Enchantment e : enchants.keySet()) {
                itemM.addEnchant(e, enchants.get(e), true);
            }
        }
        item.setItemMeta(itemM);
        return item;
    }

    /**
     * Creates String.
     *
     * @param block The Block
     * @see LucyBlockAPI
     */
    public static String createDim(Block block) {
        String dim = block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ();
        return dim;
    }

    /**
     * Generates Land.
     *
     * @param loc
     * @see LuckyBlockAPI
     */
    public static void GenerateLand(Location loc) {
        Random randoms = new Random();
        //Block block = loc.getBlock();
        int g = randoms.nextInt(6) + 6;
        for (int h = 0; h < g; h--) {
            for (int x = 0; x < g; x++) {
                for (int z = 0; z < g; z++) {
                    //TODO
                }
            }
        }
    }

    /**
     * Creates Entity.
     *
     * @param config The Config.
     * @param number The Number.
     * @see LuckyBlockAPI
     */
    @SuppressWarnings("deprecation")
    public static void spawnEntity(Location loc, FileConfiguration config, int number, Player player, int luck) {
        String e = config.getStringList("Entities.Mobs").get(number);
        String[] vv = e.split("//");
        int tries = 1;
        for (int pp = 0; pp < vv.length; pp++) {
            String[] split = vv[pp].split("#");
            for (int kk = 0; kk < split.length; kk++) {
                if (split[kk].startsWith("Tries:")) {
                    String[] s = split[kk].split("Tries:");
                    String[] ss = s[1].split("-");
                    if (ss.length > 1) {
                        tries = LuckyBlock.randoms.nextInt(((Integer.parseInt(ss[1]) - Integer.parseInt(ss[0]))) + 1) + Integer.parseInt(ss[0]);
                    } else {
                        try {
                            tries = Integer.parseInt(s[1]);
                        } catch (NumberFormatException ex) {
                            tries = 1;
                        }
                    }
                }
            }
            for (int times = tries; times > 0; times--) {
                String[] splits = vv[pp].split("#");
                EntityType type = EntityType.PIG;
                for (int x = 0; x < splits.length; x++) {
                    if (splits[x].startsWith("Type:")) {
                        String[] a = splits[x].split("Type:");
                        if (a[1].equalsIgnoreCase("cavespider") || a[1].equalsIgnoreCase("cspider")) {
                            type = EntityType.CAVE_SPIDER;
                        } else if (a[1].equalsIgnoreCase("irongolem") || a[1].equalsIgnoreCase("villagergolem")) {
                            type = EntityType.IRON_GOLEM;
                        } else if (a[1].equalsIgnoreCase("droppeditem")) {
                            type = EntityType.DROPPED_ITEM;
                        } else if (a[1].equalsIgnoreCase("magmacube") || a[1].equalsIgnoreCase("lavaslime")) {
                            type = EntityType.MAGMA_CUBE;
                        } else if (a[1].equalsIgnoreCase("enderdragon")) {
                            type = EntityType.ENDER_DRAGON;
                        } else if (a[1].equalsIgnoreCase("enderpearl")) {
                            type = EntityType.ENDER_PEARL;
                        } else if (a[1].equalsIgnoreCase("endersignal")) {
                            type = EntityType.ENDER_SIGNAL;
                        } else if (a[1].equalsIgnoreCase("xp")) {
                            type = EntityType.EXPERIENCE_ORB;
                        } else if (a[1].equalsIgnoreCase("fallingblock") || a[1].equalsIgnoreCase("fallingsand")) {
                            type = EntityType.FALLING_BLOCK;
                        } else if (a[1].equalsIgnoreCase("fishinghook")) {
                            type = EntityType.FISHING_HOOK;
                        } else if (a[1].equalsIgnoreCase("itemframe")) {
                            type = EntityType.ITEM_FRAME;
                        } else if (a[1].equalsIgnoreCase("witherskull")) {
                            type = EntityType.WITHER_SKULL;
                        } else if (a[1].equalsIgnoreCase("leashhitch") || a[1].equalsIgnoreCase("leashknot")) {
                            type = EntityType.LEASH_HITCH;
                        } else if (a[1].equalsIgnoreCase("minecartchest") || a[1].equalsIgnoreCase("chestminecart")) {
                            type = EntityType.MINECART_CHEST;
                        } else if (a[1].equalsIgnoreCase("minecartcommand") || a[1].equalsIgnoreCase("commandblockminecart") || a[1].equalsIgnoreCase("commandminecart")) {
                            type = EntityType.MINECART_COMMAND;
                        } else if (a[1].equalsIgnoreCase("minecartfurnace") || a[1].equalsIgnoreCase("furnaceminecart")) {
                            type = EntityType.MINECART_FURNACE;
                        } else if (a[1].equalsIgnoreCase("minecarthopper") || a[1].equalsIgnoreCase("hopperminecart")) {
                            type = EntityType.MINECART_HOPPER;
                        } else if (a[1].equalsIgnoreCase("minecartmobspawner") || a[1].equalsIgnoreCase("mobspawnerminecart") ||
                                a[1].equalsIgnoreCase("spawnerminecart")) {
                            type = EntityType.MINECART_MOB_SPAWNER;
                        } else if (a[1].equalsIgnoreCase("minecarttnt") || a[1].equalsIgnoreCase("tntminecart")) {
                            type = EntityType.MINECART_TNT;
                        } else if (a[1].equalsIgnoreCase("mushroomcow") || a[1].equalsIgnoreCase("mooshroom") || a[1].equalsIgnoreCase("redcow")) {
                            type = EntityType.MUSHROOM_COW;
                        } else if (a[1].equalsIgnoreCase("pigzombie") || a[1].equalsIgnoreCase("zombiepigman") || a[1].equalsIgnoreCase("pzombie")) {
                            type = EntityType.PIG_ZOMBIE;
                        } else if (a[1].equalsIgnoreCase("primedtnt") || a[1].equalsIgnoreCase("tntprimed") || a[1].equalsIgnoreCase("tnt")) {
                            type = EntityType.PRIMED_TNT;
                        } else if (a[1].equalsIgnoreCase("smallfireball") || a[1].equalsIgnoreCase("sfireball")) {
                            type = EntityType.SMALL_FIREBALL;
                        } else if (a[1].equalsIgnoreCase("splashpotion")) {
                            type = EntityType.SPLASH_POTION;
                        } else if (a[1].equalsIgnoreCase("thrownexpbottle") || a[1].equalsIgnoreCase("thrownxpbottle")) {
                            type = EntityType.THROWN_EXP_BOTTLE;
                        } else if (a[1].equalsIgnoreCase("horse")) {
                            type = EntityType.HORSE;
                        } else if (a[1].equalsIgnoreCase("wither")) {
                            type = EntityType.WITHER;
                        } else if (a[1].equalsIgnoreCase("guardian")) {
                            type = EntityType.GUARDIAN;
                        } else if (a[1].equalsIgnoreCase("armorstand")) {
                            type = EntityType.ARMOR_STAND;
                        } else {
                            type = EntityType.fromName(a[1]);
                        }
                    }
                }
                Entity entity = null;
                if (type != EntityType.DROPPED_ITEM) {
                    if (type != null) {
                        entity = Bukkit.getWorld(loc.getWorld().getName()).spawnEntity(loc, type);
                    } else {
                        throw new NullPointerException("Error with an entity: " + e);
                    }
                } else {
                    entity = Bukkit.getWorld(loc.getWorld().getName()).dropItem(loc, new ItemStack(Material.STONE));
                }
                for (int x = 0; x < splits.length; x++) {
                    if (splits[x].startsWith("maxHealth:")) {
                        if (entity instanceof LivingEntity) {
                            String[] a = splits[x].split("maxHealth:");
                            String[] cc = a[1].split("-");
                            LivingEntity l = (LivingEntity) entity;
                            if (cc.length == 1) {
                                try {
                                    l.setMaxHealth(Double.parseDouble(a[1]));
                                } catch (NumberFormatException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                try {
                                    l.setMaxHealth(LuckyBlock.randoms.nextInt(((Integer.parseInt(cc[1]) - Integer.parseInt(cc[0]))) + 1) + Integer.parseInt(cc[0]));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    if (splits[x].startsWith("Health:")) {
                        if (entity instanceof LivingEntity) {
                            String[] b = splits[x].split("Health:");
                            String[] bb = b[1].split("-");
                            LivingEntity l = (LivingEntity) entity;
                            Damageable d = (Damageable) l;
                            if (bb.length == 1) {
                                if (b[1].equalsIgnoreCase("%maxHealth%")) {
                                    l.setHealth(d.getMaxHealth());
                                } else {
                                    try {
                                        l.setHealth(Double.parseDouble(b[1]));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    l.setHealth(LuckyBlock.randoms.nextInt(((Integer.parseInt(bb[1]) - Integer.parseInt(bb[0]))) + 1) + Integer.parseInt(bb[0]));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    if (splits[x].startsWith("CustomName:")) {
                        if (entity instanceof LivingEntity) {
                            String[] c = splits[x].split("CustomName:");
                            LivingEntity l = (LivingEntity) entity;
                            Damageable d = (Damageable) l;
                            Damageable dd = (Damageable) player;
                            c[1] = c[1].replace("%health%", "" + d.getHealth());
                            c[1] = c[1].replace("%maxhealth%", "" + d.getMaxHealth());
                            if (player != null) {
                                c[1] = c[1].replace("%playername%", player.getName());
                                c[1] = c[1].replace("%playerhealth%", "" + dd.getHealth());
                                c[1] = c[1].replace("%playerlevel%", "" + player.getLevel());
                                c[1] = c[1].replace("%playerxp%", "" + player.getExp());
                                c[1] = c[1].replace("%playertxp%", "" + player.getTotalExperience());
                                c[1] = c[1].replace("%playeruuid%", "" + player.getUniqueId());
                            }
                            l.setCustomName(ChatColor.translateAlternateColorCodes('&', c[1]));
                        }
                    }
                    if (splits[x].startsWith("CustomNameVisible:")) {
                        if (entity instanceof LivingEntity) {
                            String[] d = splits[x].split("CustomNameVisible:");
                            LivingEntity l = (LivingEntity) entity;
                            try {
                                l.setCustomNameVisible(Boolean.parseBoolean(d[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (splits[x].startsWith("CanPickupItems:")) {
                        if (entity instanceof LivingEntity) {
                            String[] d = splits[x].split("CanPickupItems:");
                            LivingEntity l = (LivingEntity) entity;
                            try {
                                boolean can = Boolean.parseBoolean(d[1]);
                                l.setCanPickupItems(can);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (splits[x].startsWith("PersistenceRequired:")) {
                        if (entity instanceof LivingEntity) {
                            String[] d = splits[x].split("PersistenceRequired:");
                            LivingEntity l = (LivingEntity) entity;
                            try {
                                boolean is = Boolean.parseBoolean(d[1]);
                                l.setRemoveWhenFarAway(is);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("FallDistance:")) {
                        String[] d = splits[x].split("FallDistance:");
                        try {
                            float f = Float.parseFloat(d[1]);
                            entity.setFallDistance(f);
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (splits[x].startsWith("FireTicks:")) {
                        String[] d = splits[x].split("FireTicks:");
                        try {
                            int f = Integer.parseInt(d[1]);
                            entity.setFireTicks(f);
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (splits[x].startsWith("Age:")) {
                        if (entity instanceof Ageable) {
                            String[] d = splits[x].split("Age:");
                            Ageable a = (Ageable) entity;
                            try {
                                int age = Integer.parseInt(d[1]);
                                a.setAge(age);
                            } catch (NumberFormatException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (splits[x].startsWith("Baby:")) {
                        if (entity instanceof Ageable) {
                            String[] d = splits[x].split("Baby:");
                            Ageable a = (Ageable) entity;
                            try {
                                boolean is = Boolean.parseBoolean(d[1]);
                                if (is == true) {
                                    a.setBaby();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("Tamed:")) {
                        if (entity instanceof Tameable) {
                            String[] d = splits[x].split("Tamed:");
                            Tameable t = (Tameable) entity;
                            try {
                                boolean is = Boolean.parseBoolean(d[1]);
                                t.setTamed(is);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (splits[x].startsWith("Owner:")) {
                        if (entity instanceof Tameable) {
                            String[] d = splits[x].split("Owner:");
                            Tameable t = (Tameable) entity;
                            d[1] = d[1].replace("{playername}", player.getName());
                            t.setOwner(Bukkit.getPlayer(d[1]));
                        }
                    }
                    if (splits[x].startsWith("Sitting:")) {
                        String[] d = splits[x].split("Sitting:");
                        if (entity instanceof Wolf) {
                            Wolf wolf = (Wolf) entity;
                            try {
                                boolean is = Boolean.parseBoolean(d[1]);
                                wolf.setSitting(is);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else if (entity instanceof Ocelot) {
                            Ocelot ocelot = (Ocelot) entity;
                            try {
                                boolean is = Boolean.parseBoolean(d[1]);
                                ocelot.setSitting(is);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("Powered:")) {
                        if (entity instanceof Creeper) {
                            String[] d = splits[x].split("Powered:");
                            Creeper creeper = (Creeper) entity;
                            try {
                                boolean is = Boolean.parseBoolean(d[1]);
                                creeper.setPowered(is);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("CarryingChest:")) {
                        if (entity instanceof Horse) {
                            String[] d = splits[x].split("CarryingChest:");
                            Horse horse = (Horse) entity;
                            try {
                                boolean is = Boolean.parseBoolean(d[1]);
                                horse.setCarryingChest(is);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("Equipments:")) {
                        if (entity instanceof LivingEntity) {
                            String[] d = splits[x].split("Equipments:");
                            LivingEntity l = (LivingEntity) entity;
                            String[] ddd = d[1].split(">>");
                            for (int ii = 0; ii < ddd.length; ii++) {
                                ItemStack item = getItem(ddd[ii], ",");
                                if (ii == 0) {
                                    l.getEquipment().setItemInHand(item);
                                } else if (ii == 1) {
                                    l.getEquipment().setHelmet(item);
                                } else if (ii == 2) {
                                    l.getEquipment().setChestplate(item);
                                } else if (ii == 3) {
                                    l.getEquipment().setLeggings(item);
                                } else if (ii == 4) {
                                    l.getEquipment().setBoots(item);
                                }
                            }
                        }
                    }

                    if (splits[x].startsWith("Metadatas:")) {
                        String[] f = splits[x].split("Metadatas:");
                        String[] ff = f[1].split("%%");
                        for (int g = 0; g < ff.length; g++) {
                            String[] fff = ff[g].split(",");
                            if (fff.length > 1) {
                                entity.setMetadata(fff[0], new FixedMetadataValue(LuckyBlock.instance, fff[1]));
                            }
                        }
                    }

                    if (splits[x].startsWith("CatType:")) {
                        if (entity instanceof Ocelot) {
                            Ocelot ocelot = (Ocelot) entity;
                            String[] f = splits[x].split("CatType:");
                            try {
                                ocelot.setCatType(org.bukkit.entity.Ocelot.Type.getType(Integer.parseInt(f[1])));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("Saddle:")) {
                        if (entity instanceof Pig) {
                            Pig pig = (Pig) entity;
                            String[] f = splits[x].split("Saddle:");
                            try {
                                pig.setSaddle(Boolean.parseBoolean(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else if (entity instanceof Horse) {
                            String[] f = splits[x].split("Saddle:");
                            Horse horse = (Horse) entity;
                            if (f.length > 1) {
                                try {
                                    if (Boolean.parseBoolean(f[1]) == true) {
                                        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }

                    if (splits[x].startsWith("Sheared:")) {
                        if (entity instanceof Sheep) {
                            Sheep sheep = (Sheep) entity;
                            String[] f = splits[x].split("Sheared:");
                            try {
                                sheep.setSheared(Boolean.parseBoolean(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (splits[x].startsWith("Color:")) {
                        if (entity instanceof Sheep) {
                            Sheep sheep = (Sheep) entity;
                            String[] f = splits[x].split("Color:");
                            String[] g = f[1].split("-");
                            if (g.length > 1) {
                                try {
                                    byte by = (byte) (LuckyBlock.randoms.nextInt(((Integer.parseInt(g[1]) - Integer.parseInt(g[0]))) + 1) +
                                            Integer.parseInt(g[0]));
                                    sheep.setColor(DyeColor.getByData(by));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                sheep.setColor(DyeColor.getByData(Byte.parseByte(f[1])));
                            }
                        }
                    }

                    if (splits[x].startsWith("Size:")) {
                        if (entity instanceof Slime) {
                            Slime slime = (Slime) entity;
                            String[] f = splits[x].split("Size:");
                            try {
                                slime.setSize(Integer.parseInt(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else if (entity instanceof MagmaCube) {
                            MagmaCube lslime = (MagmaCube) entity;
                            String[] f = splits[x].split("Size:");
                            try {
                                lslime.setSize(Integer.parseInt(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("CollarColor:")) {
                        if (entity instanceof Wolf) {
                            Wolf wolf = (Wolf) entity;
                            String[] f = splits[x].split("CollarColor:");
                            String[] g = f[1].split("-");
                            if (g.length > 1) {
                                wolf.setCollarColor(DyeColor.getByData((byte) (LuckyBlock.randoms.nextInt(((Integer.parseInt(g[1]) - Integer.parseInt(g[0]))) + 1)
                                        + Integer.parseInt(g[0]))));
                            } else {
                                try {
                                    wolf.setCollarColor(DyeColor.getByData(Byte.parseByte(f[1])));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }

                    if (splits[x].startsWith("Profession:")) {
                        if (entity instanceof Villager) {
                            Villager villager = (Villager) entity;
                            String[] f = splits[x].split("Profession:");
                            try {
                                villager.setProfession(Profession.getProfession(Integer.parseInt(f[1])));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("PlayerCreated:")) {
                        if (entity instanceof IronGolem) {
                            IronGolem iron = (IronGolem) entity;
                            String[] f = splits[x].split("PlayerCreated:");
                            try {
                                iron.setPlayerCreated(Boolean.parseBoolean(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("Angry:")) {
                        if (entity instanceof PigZombie) {
                            PigZombie pz = (PigZombie) entity;
                            String[] f = splits[x].split("Angry:");
                            try {
                                pz.setAngry(Boolean.parseBoolean(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (splits[x].startsWith("Anger:")) {
                        if (entity instanceof PigZombie) {
                            PigZombie pz = (PigZombie) entity;
                            String[] f = splits[x].split("Anger:");
                            try {
                                pz.setAnger(Integer.parseInt(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("DropChances:")) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity l = (LivingEntity) entity;
                            String[] f = splits[x].split("DropChances:");
                            String[] ff = f[1].split(",");
                            for (int y = 0; y < ff.length; y++) {
                                if (y == 0) {
                                    try {
                                        l.getEquipment().setItemInHandDropChance(Float.parseFloat(ff[0]));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (y == 1) {
                                    try {
                                        l.getEquipment().setHelmetDropChance(Float.parseFloat(ff[1]));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (y == 2) {
                                    try {
                                        l.getEquipment().setChestplateDropChance(Float.parseFloat(ff[2]));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (y == 3) {
                                    try {
                                        l.getEquipment().setLeggingsDropChance(Float.parseFloat(ff[3]));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (y == 4) {
                                    try {
                                        l.getEquipment().setBootsDropChance(Float.parseFloat(ff[4]));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    if (splits[x].startsWith("SkeletonType:")) {
                        if (entity instanceof Skeleton) {
                            Skeleton sk = (Skeleton) entity;
                            String[] f = splits[x].split("SkeletonType:");
                            try {
                                sk.setSkeletonType(SkeletonType.getType(Integer.parseInt(f[1])));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("IsBaby:")) {
                        if (entity instanceof Zombie) {
                            Zombie zombie = (Zombie) entity;
                            String[] f = splits[x].split("IsBaby:");
                            try {
                                zombie.setBaby(Boolean.parseBoolean(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (splits[x].startsWith("IsVillager:")) {
                        if (entity instanceof Zombie) {
                            Zombie zombie = (Zombie) entity;
                            String[] f = splits[x].split("IsVillager:");
                            try {
                                zombie.setVillager(Boolean.parseBoolean(f[1]));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    if (splits[x].startsWith("Target:")) {
                        if (entity instanceof Creature) {
                            Creature c = (Creature) entity;
                            String[] f = splits[x].split("Target:");
                            if (f[1].equalsIgnoreCase("%player%")) {
                                c.setTarget(player);
                            } else {
                                Player p = Bukkit.getPlayer(f[1]);
                                c.setTarget(p);
                            }
                        }

                    }

                    if (splits[x].startsWith("PotionEffects:")) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity l = (LivingEntity) entity;
                            String[] f = splits[x].split("PotionEffects:");
                            String[] g = f[1].split("%%");
                            for (int o = 0; o < g.length; o++) {
                                String[] h = g[o].split(" ");
                                if (h.length == 1) {
                                    try {
                                        l.addPotionEffect(new PotionEffect(PotionEffectType.getById(Integer.parseInt(h[0])), 600, 0));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h.length == 2) {
                                    try {
                                        l.addPotionEffect(new PotionEffect(PotionEffectType.getById(Integer.parseInt(h[0])), Integer.parseInt(h[1]) * 20, 0));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h.length > 2) {
                                    try {
                                        l.addPotionEffect(new PotionEffect(PotionEffectType.getById(Integer.parseInt(h[0])), Integer.parseInt(h[1]) * 20, Integer.parseInt(h[2])));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    } else if (splits[x].startsWith("ParticleEffects:")) {
                        String[] f = splits[x].split("ParticleEffects:");
                        String[] g = f[1].split("%%");
                        for (int y = 0; y < g.length; y++) {
                            String[] h = g[y].split(",");
                            int id = -1;
                            String name = "null";
                            float xx = 0;
                            float yy = 0;
                            float zz = 0;
                            float speed = 0;
                            int amount = 0;
                            int range = 0;
                            for (int z = 0; z < h.length; z++) {
                                if (h[z].startsWith("id:")) {
                                    String[] i = h[z].split("id:");
                                    try {
                                        id = Integer.parseInt(i[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h[z].startsWith("name:")) {
                                    String[] i = h[z].split("name:");
                                    if (i[1].equalsIgnoreCase("critmagic") || i[1].equalsIgnoreCase("magiccrit")) {
                                        name = "CRIT_MAGIC";
                                    } else if (i[1].equalsIgnoreCase("driplava") || i[1].equalsIgnoreCase("lavadrip")) {
                                        name = "DRIP_LAVA";
                                    } else if (i[1].equalsIgnoreCase("smoke") || i[1].equalsIgnoreCase("normalsmoke") || i[1].equalsIgnoreCase("smokenormal")) {
                                        name = "SMOKE_NORMAL";
                                    } else {
                                        name = i[1].toUpperCase();
                                    }
                                } else if (h[z].startsWith("rx:")) {
                                    String[] i = h[z].split("rx:");
                                    try {
                                        xx = Float.parseFloat(i[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h[z].startsWith("ry:")) {
                                    String[] i = h[z].split("ry:");
                                    try {
                                        yy = Float.parseFloat(i[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h[z].startsWith("rz:")) {
                                    String[] i = h[z].split("rz:");
                                    try {
                                        zz = Float.parseFloat(i[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h[z].startsWith("speed:")) {
                                    String[] i = h[z].split("speed:");
                                    try {
                                        speed = Float.parseFloat(i[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h[z].startsWith("amount:")) {
                                    String[] i = h[z].split("amount:");
                                    try {
                                        amount = Integer.parseInt(i[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (h[z].startsWith("range:")) {
                                    String[] i = h[z].split("range:");
                                    try {
                                        range = Integer.parseInt(i[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            if (id != -1) {
                                ParticleEffect.fromId(id).display(xx, yy, zz, speed, amount, entity.getLocation(), range);
                            } else {
                                if (name != "null") {
                                    ParticleEffect.fromName(name).display(xx, yy, zz, speed, amount, entity.getLocation(), range);
                                }
                            }
                        }
                    } else if (splits[x].startsWith("ItemStack:")) {
                        if (entity instanceof Item) {
                            Item itemm = (Item) entity;
                            String[] h = splits[x].split("ItemStack:");
                            itemm.setItemStack(getItem(h[1], ","));
                        }

                    } else if (splits[x].startsWith("Velocity:")) {
                        String[] g = splits[x].split("Velocity:");
                        if (g.length > 1) {
                            if (g[1].startsWith("(vel)")) {
                                String[] h = g[1].split("(vel)");
                                String[] i = h[1].split("<>");
                                if (i.length == 1) {
                                    entity.setVelocity(entity.getVelocity());
                                } else {
                                    //TODO
                                }
                            } else {
                                try {
                                    //entity.setVelocity(entity.getVelocity().s);
                                } catch (NumberFormatException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                    } else if (splits[x].startsWith("maxSpeed:")) {
                        if (entity instanceof Boat) {
                            String[] g = splits[x].split("maxSpeed:");
                            Boat boat = (Boat) entity;
                            if (g.length > 1) {
                                try {
                                    boat.setMaxSpeed(Double.parseDouble(g[1]));
                                } catch (NumberFormatException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } else if (splits[x].startsWith("WorkOnLand:")) {
                        if (entity instanceof Boat) {
                            String[] g = splits[x].split("WorkOnLand:");
                            Boat boat = (Boat) entity;
                            if (g.length > 1) {
                                try {
                                    boat.setWorkOnLand(Boolean.parseBoolean(g[1]));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                    } else if (splits[x].startsWith("HorseColor:")) {
                        if (entity instanceof Horse) {
                            String[] g = splits[x].split("HorseColor:");
                            Horse horse = (Horse) entity;
                            if (g.length > 1) {
                                horse.setColor(org.bukkit.entity.Horse.Color.valueOf(g[1].toUpperCase()));
                            }
                        }
                    } else if (splits[x].startsWith("Style:")) {
                        if (entity instanceof Horse) {
                            String[] g = splits[x].split("Style:");
                            Horse horse = (Horse) entity;
                            if (g.length > 1) {
                                try {
                                    if (g[1].equalsIgnoreCase("whitedots")) {
                                        horse.setStyle(Style.WHITE_DOTS);
                                    } else if (g[1].equalsIgnoreCase("blackdots")) {
                                        horse.setStyle(Style.BLACK_DOTS);
                                    } else {
                                        horse.setStyle(Style.valueOf(g[1].toUpperCase()));
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets Game Sign.
     *
     * @see LuckyBlockAPI
     */
    public static Block getBlock(String dim) {
        String[] s = dim.split(",");
        Block block = Bukkit.getWorld(s[0]).getBlockAt(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
        return block;
    }

    /**
     * Gets Game from file.
     *
     * @see LuckyBlockAPI
     */
    public static String getGameFile(int id) {
        String loc = null;
        if (LuckyBlock.instance.game.getConfigurationSection("Games") != null) {
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
        } else {
            LuckyBlock.instance.game.createSection("Games");
        }
        return loc;
    }

    /**
     * Gets Game ID by Player.
     *
     * @see LuckyBlockAPI
     */
    public static int getId(Player player) {
        UUID uuid = player.getUniqueId();
        int id = 0;
        for (int x = 0; x < War.listWars.size(); x++) {
            if (War.listWars.get(x).containsPlayer(uuid)) {
                id = War.listWars.get(x).getId();
            }
        }
        return id;
    }

    /**
     * Removes all Lucky Block Dropped items.
     *
     * @see LuckyBlockAPI
     */
    public static void removeDrops(World world) {
        for (Entity e : world.getEntities()) {
            if (e instanceof Item) {
                Item item = (Item) e;
                ItemStack i = item.getItemStack();
                Types type = null;
                for (Types t : LuckyBlock.lbs) {
                    if (t.getType() == i.getType()) {
                        if (i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
                            if (i.getItemMeta().getDisplayName().equalsIgnoreCase(t.getName())) {
                                type = t;
                            }
                        }
                    }
                }
                if (type != null) {
                    item.remove();
                }
            }
        }
    }

    /**
     * A method for saving configs.
     *
     * @see LuckyBlockAPI
     */
    public static void saveConfigs() {
        try {
            lbs.save(lbsF);
            LuckyBlock.instance.game.save(LuckyBlock.instance.gameF);
            LuckyBlock.instance.data.save(LuckyBlock.instance.dataF);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ItemStack addLore(ItemStack item, String lore) {
        ItemMeta itemM = item.getItemMeta();
        List<String> list = new ArrayList<String>();
        if (itemM.hasLore()) {
            list = itemM.getLore();
        }
        list.add(lore);
        itemM.setLore(list);
        item.setItemMeta(itemM);
        return item;
    }

    /**
     * Gets Location to detector from data.yml file.
     *
     * @see LuckyBlockAPI
     */
    public static String getDet(int id) {
        String s = null;
        ConfigurationSection cs = LuckyBlock.instance.data.getConfigurationSection("Detectors");
        try {
            for (int x = 0; x < cs.getKeys(false).size(); x++) {
                if (cs.getInt(cs.getKeys(false).toArray()[x].toString() + ".ID") != 0) {
                    s = "Detectors." + cs.getKeys(false).toArray()[x].toString();
                    x = cs.getKeys(false).size();
                }
            }
        } catch (Exception ex) {
            //
        }
        return s;
    }

    /**
     * Shows Scoreboard for certain player.
     *
     * @param player
     * @see LuckyBlockAPI
     */
    public static void showScoreboard(Player player, int time) {
        if (player.getScoreboard() != null) {
            Scoreboard s = player.getScoreboard();
            scoreboard.put(player.getUniqueId(), s);
        }
        UUID uuid = player.getUniqueId();
        savePlayerData(uuid, player.getName());
        player.setFoodLevel(20);
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard score = manager.getNewScoreboard();
        if (War.getGame(uuid) != null) {
            War war = War.getGame(uuid);
            Objective kills = score.registerNewObjective("kills", "dummy");
            kills.setDisplaySlot(DisplaySlot.SIDEBAR);
            ChatColor c = aqua;
            if (time < 1) {
                c = aqua;
            } else if (time == 1) {
                c = darkaqua;
            } else if (time == 2) {
                c = blue;
            } else if (time == 3) {
                c = darkblue;
            } else if (time == 4) {
                c = blue;
            } else if (time > 4) {
                c = darkaqua;
            }
            kills.setDisplayName(c + "" + bold + player.getName());
            if (war.getName() != null) {
                Score gn = kills.getScore("Game Name: " + green + war.getName());
                gn.setScore(0);
            }
            Score mm = kills.getScore("Map: " + green + war.getWorld());
            mm.setScore(1);
            Score mn = kills.getScore("ID: " + green + war.getId());
            mn.setScore(2);
            Score s = kills.getScore("Kills: " + green + LuckyBlockAPI.kills.get(uuid));
            s.setScore(3);
            Score s1 = kills.getScore("Money: " + green + money.get(uuid));
            s1.setScore(4);
            Score s2 = kills.getScore("Gold: " + green + golds.get(uuid));
            s2.setScore(5);
            Score em = kills.getScore("");
            em.setScore(12);
            Score s3 = kills.getScore("Wins: " + green + wins.get(uuid));
            s3.setScore(6);
            Score s4 = kills.getScore("Deaths: " + green + deaths.get(uuid));
            s4.setScore(7);
            int[] i = playerlevel.get(uuid);
            Score s5 = kills.getScore("Level: " + green + i[0]);
            s5.setScore(8);
            Score s6 = kills.getScore("XP: " + green + i[1]);
            s6.setScore(9);
            Score s7 = kills.getScore("Games Played: " + green + plays.get(uuid));
            s7.setScore(10);
            Score s8 = kills.getScore("Type: " + green + war.getType());
            s8.setScore(11);
            player.setScoreboard(score);
        }
    }

    public static void showScoreboard1(Player player, War war, int sec, int min) {
        UUID uuid = player.getUniqueId();
        savePlayerData(uuid, player.getName());
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard score = manager.getNewScoreboard();
        Objective o = score.registerNewObjective("Game", "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        String t = null;
        String tt = null;
        String t1 = null;
        String tt1 = null;
        if (min < 10) {
            t = "0" + min;
        } else {
            t = "" + min;
        }
        if (sec < 10) {
            tt = "0" + sec;
        } else {
            tt = "" + sec;
        }
        if (war.getNextRefill().getMin() < 10) {
            t1 = "0" + war.getNextRefill().getMin();
        } else {
            t1 = "" + war.getNextRefill().getMin();
        }
        if (war.getNextRefill().getSecond() < 10) {
            tt1 = "0" + war.getNextRefill().getSecond();
        } else {
            tt1 = "" + war.getNextRefill().getSecond();
        }
        o.setDisplayName(gold + "Time: " + green + "" + bold + t + ":" + tt);
        Score s = o.getScore(gold + "ID: " + green + war.getId());
        Score s1 = o.getScore(gold + "Players: " + green + war.getPlayers().size());
        Score s2 = o.getScore(gold + "Spectators: " + green + war.getSpectators().size());
        Score s4 = o.getScore(gold + "Location: " + green + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " +
                player.getLocation().getBlockZ());
        if (war.getCenter() != null) {
            Score s5 = o.getScore(gold + "Center: " + green + war.getCenter().getBlockX() + " " + war.getCenter().getBlockY() + " " +
                    war.getCenter().getBlockZ());
            s5.setScore(9);
        }
        Score s6 = o.getScore(gold + "Type: " + green + war.getType());
        Score ss = o.getScore("     ");
        Score s7 = o.getScore(gold + "Map: " + green + war.getWorld());
        Score s8 = o.getScore(gold + "Collected Coins: " + green + war.getReward(uuid, RewardType.MONEY));
        Score s9 = o.getScore(gold + "Collected XP: " + green + war.getReward(uuid, RewardType.XP));
        Score s10 = o.getScore(gold + "Kills: " + green + war.getReward(uuid, RewardType.KILLS));
        Score s11 = o.getScore(gold + "Time until next refill: " + green + "" + bold + t1 + ":" + tt1);
        s8.setScore(14);
        s9.setScore(15);
        s10.setScore(16);
        ss.setScore(13);
        s.setScore(1);
        s1.setScore(2);
        s2.setScore(3);
        s4.setScore(8);
        s6.setScore(11);
        s7.setScore(12);
        s11.setScore(17);
        player.setScoreboard(score);
    }

    public static void addXP(Player player, int xp2) {
        UUID uuid = player.getUniqueId();
        boolean levelup = false;
        int[] i = playerlevel.get(uuid);
        i[1] += xp2;
        int l = i[0];
        int x = i[1];
        int t = 0;
        if (l < 5) {
            t = (3 * l) + 10 + l;
        } else if (l > 4 && l < 31) {
            t = (6 * l) + 10 + l;
        } else if (l > 30) {
            t = (10 * l) + l;
        }
        if (x > t) {
            i[0]++;
            i[1] -= t;
            levelup = true;
            player.sendMessage(LuckyBlockCommand.getMessage("LevelUp"));
            int mon = 15 + (l * 5);
            LuckyBlockAPI.money.put(uuid, LuckyBlockAPI.money.get(uuid) + mon);
            player.sendMessage(green + "You have earned " + gold + mon + green + " Money!");
        }
        playerlevel.put(uuid, i);
        LuckyBlockAPI.savePlayerData(uuid, player.getName());
        if (levelup) {
            List<Hat> hats = new ArrayList<Hat>();
            for (Hat hat : Hat.values()) {
                if (hat.getLevel() == i[0]) {
                    if (hat.getLevel() > 0) {
                        hats.add(hat);
                    }
                }
            }
            if (hats.size() > 0) {
                for (int y = 0; y < hats.size(); y++) {
                    player.sendMessage(yellow + hats.get(y).toString() + green + " has been unlocked!");
                }
            }
            addXP(player, 0);
        }
    }

    public static void openGui(Player player, int loc) {
        ConfigurationSection cs = LuckyBlock.instance.data.getConfigurationSection("Players");
        UUID uuid = UUID.fromString(cs.getKeys(false).toArray()[loc].toString());
        String name = LuckyBlock.instance.data.getString(cs.getKeys(false).toArray()[loc].toString() + ".Name");
        Inventory inv = Bukkit.createInventory(player, 36, darkred + "Player's Gui");
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemStack[] items = new ItemStack[32];
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack item1 = new ItemStack(Material.NETHER_STAR);
        ItemStack item2 = new ItemStack(Material.BOOK);
        ItemStack item3 = new ItemStack(Material.GOLD_INGOT);
        ItemStack item4 = new ItemStack(Material.EXP_BOTTLE);
        ItemStack item5 = new ItemStack(Material.EMERALD);
        ItemStack item6 = new ItemStack(Material.DIAMOND);
        ItemStack back = ItemMaker.createItem(Material.COMPASS, 1, (short) 0, red + "" + bold + "Close", Arrays.asList(gray + "Click to close"));
        SkullMeta headM = (SkullMeta) head.getItemMeta();
        ItemMeta itemM = item.getItemMeta();
        ItemMeta itemM1 = item1.getItemMeta();
        ItemMeta itemM2 = item2.getItemMeta();
        ItemMeta itemM3 = item3.getItemMeta();
        ItemMeta itemM4 = item4.getItemMeta();
        ItemMeta itemM5 = item5.getItemMeta();
        ItemMeta itemM6 = item6.getItemMeta();
        headM.setOwner(name);
        headM.setDisplayName(reset + cname.get(uuid));
        head.setItemMeta(headM);
        List<String> list = new ArrayList<String>();
        itemM.setDisplayName(aqua + "" + bold + "Kills");
        itemM1.setDisplayName(green + "" + bold + "Wins");
        itemM2.setDisplayName(blue + "" + bold + "Games Played");
        itemM3.setDisplayName(gold + "" + bold + "Money and Gold");
        itemM4.setDisplayName(yellow + "" + bold + "Level and XP");
        itemM5.setDisplayName(red + "" + bold + "Skills");
        itemM6.setDisplayName(darkred + "" + bold + "Unlocked Items");
        int k = kills.get(uuid);
        DecimalFormat formatter = new DecimalFormat("#,###");
        list.add(gray + formatter.format(k));
        list.add(green + "Total Damage: " + gray + formatter.format(totalDamage.get(uuid)));
        itemM.setLore(list);
        list.clear();
        int di = 0;
        try {
            double d1 = wins.get(uuid);
            double d2 = plays.get(uuid);
            di = (int) ((d1 / d2) * 100);
        } catch (Exception ex) {
            //
        }
        if (di > 100) {
            di = 100;
        }
        list.add(gray + formatter.format(wins.get(uuid)) + lightpurple + " (" + green + "%" + di + lightpurple + ")");
        itemM1.setLore(list);
        list.clear();
        list.add(gray + formatter.format(plays.get(uuid)));
        itemM2.setLore(list);
        list.clear();
        list.add(green + "Money: " + gold + formatter.format(money.get(uuid)) + green + " ,Gold: " + gold + formatter.format(golds.get(uuid)));
        itemM3.setLore(list);
        list.clear();
        list.add(green + "Level: " + gold + formatter.format(playerlevel.get(uuid)[0]) + green + " ,XP: " + gold + formatter.format(playerlevel.get(uuid)[1]));
        itemM4.setLore(list);
        list.clear();
        list.add(gray + "Max Health: " + green + maxHealth.get(uuid));
        list.add(gray + "Multiply: " + green + multiply.get(uuid));
        list.add(gray + "Haste: " + green + speedmine.get(uuid));
        itemM5.setLore(list);
        list.clear();
        List<ShopItems> si = bitems.get(uuid);
        if (si.size() == 0) {
            list.add(red + "This Player has no Unlocked Items!");
        } else {
            for (int x = 0; x < si.size(); x++) {
                list.add(gray + si.get(x).name());
            }
        }
        itemM6.setLore(list);
        list.clear();
        item.setItemMeta(itemM);
        item1.setItemMeta(itemM1);
        item2.setItemMeta(itemM2);
        item3.setItemMeta(itemM3);
        item4.setItemMeta(itemM4);
        item5.setItemMeta(itemM5);
        item6.setItemMeta(itemM6);
        inv.setItem(31, head);
        items[0] = item;
        items[1] = item1;
        items[2] = item2;
        items[3] = item3;
        items[4] = item4;
        items[5] = item5;
        items[6] = item6;
        for (ItemStack i : items) {
            if (i != null) {
                inv.addItem(i);
            }
        }
        inv.setItem(inv.getSize() - 1, back);
        player.openInventory(inv);
    }

    public static Team getTeam(UUID uuid) {
        Team t = null;
        for (int x = 0; x < teams.size(); x++) {
            Team team = teams.get(x);
            if (team.getPlayers().contains(uuid)) {
                t = team;
            }
        }
        return t;
    }

    /**
     *
     */
    public static Types getLB(String dim) {
        Types type = null;
        for (Types t : LuckyBlock.lbs) {
            if (ids.containsKey(dim)) {
                if (ids.get(dim) == t.getId()) {
                    type = t;
                }
            }
        }
        return type;
    }

    /**
     *
     */
    public static Types getLB(int id) {
        Types type = null;
        for (Types t : LuckyBlock.lbs) {
            if (t.getId() == id) {
                type = t;
            }
        }
        return type;
    }

    public static String getPlayerName(UUID uuid) {
        String name = null;
        try {
            for (int x = 0; x < LuckyBlock.instance.data.getConfigurationSection("Players").getKeys(false).size(); x++) {
                String s = LuckyBlock.instance.data.getConfigurationSection("Players").getKeys(false).toArray()[x].toString();
                if (s.equalsIgnoreCase(uuid.toString())) {
                    name = LuckyBlock.instance.data.getString("Players." + s + ".Name");
                }
            }
        } catch (Exception ex) {
            //
        }
        return name;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getItem(String string, String splitsymbol) {
        ItemStack item = null;
        String[] aa = string.split(splitsymbol);
        for (int i = 0; i < aa.length; i++) {
            if (aa[i].startsWith("type:")) {
                String[] a = aa[i].split("type:");
                try {
                    item = new ItemStack(Material.getMaterial(a[1].toUpperCase()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (aa[i].startsWith("typeid:")) {
                String[] a = aa[i].split("typeid:");
                String[] am = a[1].split("-");
                String[] an = a[1].split("/");
                if (an.length > 1) {
                    int p = LuckyBlock.randoms.nextInt(an.length);
                    try {
                        item = new ItemStack(Material.getMaterial(Integer.parseInt(an[p])));
                    } catch (Exception ex) {
                        item = new ItemStack(Material.STONE);
                    }
                } else {
                    if (am.length > 1) {
                        try {
                            item = new ItemStack(Material.getMaterial(LuckyBlock.randoms.nextInt(((Integer.parseInt(am[1]) - Integer.parseInt(am[0]))) + 1) +
                                    Integer.parseInt(am[0])));
                        } catch (Exception ex) {
                            item = new ItemStack(Material.STONE);
                        }
                    } else {
                        try {
                            item = new ItemStack(Material.getMaterial(Integer.parseInt(a[1])));
                        } catch (Exception ex) {
                            item = new ItemStack(Material.STONE);
                        }
                    }
                }
            } else if (aa[i].startsWith("amount:")) {
                String[] b = aa[i].split("amount:");
                String[] am = b[1].split("-");
                if (am.length > 1) {
                    try {
                        item.setAmount(LuckyBlock.randoms.nextInt(((Integer.parseInt(am[1]) - Integer.parseInt(am[0]))) + 1) + Integer.parseInt(am[0]));
                    } catch (Exception ex) {
                        item.setAmount(1);
                    }
                } else {
                    try {
                        item.setAmount(Integer.parseInt(b[1]));
                    } catch (NumberFormatException e) {
                        item.setAmount(1);
                    }
                }
            } else if (aa[i].startsWith("data:")) {
                String[] c = aa[i].split("data:");
                String[] cd = c[1].split("-");
                if (cd.length > 1) {
                    item.setDurability((short) ((short) LuckyBlock.randoms.nextInt(((Integer.parseInt(cd[1]) - Integer.parseInt(cd[0]))) + 1) + Integer.parseInt(cd[0])));
                } else {
                    try {
                        item.setDurability(Short.parseShort(c[1]));
                    } catch (NumberFormatException ex) {
                        item.setDurability((short) 0);
                    }
                }
            } else if (aa[i].startsWith("DisplayName:")) {
                String[] e = aa[i].split("DisplayName:");
                if (!e[1].equalsIgnoreCase("null")) {
                    ItemMeta itemM = item.getItemMeta();
                    itemM.setDisplayName(ChatColor.translateAlternateColorCodes('&', e[1]));
                    item.setItemMeta(itemM);
                }
            } else if (aa[i].startsWith("Lore:")) {
                String[] g = aa[i].split("Lore:");
                if (!g[1].equalsIgnoreCase("null")) {
                    ItemMeta itemM = item.getItemMeta();
                    String[] bb = g[1].split("%%");
                    List<String> itemL = new ArrayList<String>();
                    for (int size = 0; size < bb.length; size++) {
                        itemL.add(ChatColor.translateAlternateColorCodes('&', bb[size]));
                    }
                    itemM.setLore(itemL);
                    item.setItemMeta(itemM);
                }
            } else if (aa[i].startsWith("Enchants:")) {
                String[] I = aa[i].split("Enchants:");
                if (!I[1].equalsIgnoreCase("null")) {
                    ItemMeta itemM = item.getItemMeta();
                    String[] cc = I[1].split("%%");
                    for (int size = 0; size < cc.length; size++) {
                        String[] dd = cc[size].split(" ");
                        String[] am = dd[0].split("-");
                        int ench = 0;
                        int level = 0;
                        if (am.length > 1) {
                            try {
                                ench = LuckyBlock.randoms.nextInt(((Integer.parseInt(am[1]) - Integer.parseInt(am[0]))) + 1) + Integer.parseInt(am[0]);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                ench = Integer.parseInt(dd[0]);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        String[] al = dd[1].split("-");
                        if (al.length > 1) {
                            try {
                                level = LuckyBlock.randoms.nextInt(((Integer.parseInt(al[1]) - Integer.parseInt(al[0]))) + 1) + Integer.parseInt(al[0]);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                level = Integer.parseInt(dd[1]);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        itemM.addEnchant(Enchantment.getById(ench), level, true);
                    }
                    item.setItemMeta(itemM);
                }
            } else if (aa[i].startsWith("PotionEffects:")) {
                String[] k = aa[i].split("PotionEffects:");
                if (!k[1].equalsIgnoreCase("null")) {
                    PotionMeta itemM = (PotionMeta) item.getItemMeta();
                    String[] ee = k[1].split("%%");
                    for (int size = 0; size < ee.length; size++) {
                        String[] ff = ee[size].split(" ");
                        try {
                            boolean can = false;
                            try {
                                Double.parseDouble(ff[1]);
                                can = true;
                            } catch (Exception ex) {
                                //
                            }
                            int id = Integer.parseInt(ff[0]);
                            int sec = Integer.parseInt(ff[1]);
                            int amplifier = Integer.parseInt(ff[2]);
                            if (!can) {
                                itemM.addCustomEffect(new PotionEffect(PotionEffectType.getById(id), sec * 20, amplifier), true);
                            } else {
                                itemM.addCustomEffect(new PotionEffect(PotionEffectType.getById(id), sec, amplifier), true);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        item.setItemMeta(itemM);
                    }
                }
            } else if (aa[i].startsWith("Author:")) {
                String[] I = aa[i].split("Author:");
                if (I.length == 2) {
                    BookMeta b = (BookMeta) item.getItemMeta();
                    b.setAuthor(ChatColor.translateAlternateColorCodes('&', I[1]));
                    item.setItemMeta(b);
                }
            } else if (aa[i].startsWith("Title:")) {
                String[] I = aa[i].split("Title:");
                if (I.length == 2) {
                    BookMeta b = (BookMeta) item.getItemMeta();
                    b.setTitle(ChatColor.translateAlternateColorCodes('&', I[1]));
                    item.setItemMeta(b);
                }
            } else if (aa[i].startsWith("Pages:")) {
                String[] I = aa[i].split("Pages:");
                if (I.length == 2) {
                    String[] d = I[1].split("%%");
                    BookMeta b = (BookMeta) item.getItemMeta();
                    for (int g = 0; g < d.length; g++) {
                        b.addPage(ChatColor.translateAlternateColorCodes('&', d[g]));
                    }
                    item.setItemMeta(b);
                }
            } else if (aa[i].startsWith("LeatherArmor:")) {
                String[] I = aa[i].split("LeatherArmor:");
                if (!I[1].equalsIgnoreCase("null")) {
                    LeatherArmorMeta lm = (LeatherArmorMeta) item.getItemMeta();
                    String[] II = I[1].split(",");
                    if (II.length > 1) {
                        try {
                            lm.setColor(Color.fromBGR(Integer.parseInt(II[0]), Integer.parseInt(II[1]), Integer.parseInt(II[2])));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            lm.setColor(Color.fromBGR(Integer.parseInt(II[0])));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    item.setItemMeta(lm);
                }
            } else if (aa[i].startsWith("SkullOwner:")) {
                String[] I = aa[i].split("SkullOwner:");
                if (!I[1].equalsIgnoreCase("null")) {
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setOwner(I[1]);
                    item.setItemMeta(skull);
                }
            } else if (aa[i].startsWith("StoredEnchants:")) {
                String[] I = aa[i].split("StoredEnchants:");
                String[] cc = I[1].split("%%");
                EnchantmentStorageMeta itemM = (EnchantmentStorageMeta) item.getItemMeta();
                for (int size1 = 0; size1 < cc.length; size1++) {
                    String[] dd = cc[size1].split(" ");
                    try {
                        int ench = Integer.parseInt(dd[0]);
                        int level = Integer.parseInt(dd[1]);
                        itemM.addStoredEnchant(Enchantment.getById(ench), level, true);
                        item.setItemMeta(itemM);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (aa[i].startsWith("Unbreakable:")) {
                String[] I = aa[i].split("Unbreakable:");
                if (I.length == 2) {
                    if (Boolean.parseBoolean(I[1])) {
                        item = ItemMaker.makeUnbreakable(item);
                    }
                }
            } else if (aa[i].startsWith("Attributes:")) {
                String[] I = aa[i].split("Attributes:");
                String[] cc = I[1].split("%%");
                for (int size1 = 0; size1 < cc.length; size1++) {
                    //String[] dd = cc[size1].split(" ");

                }
            }
        }
        return item;
    }


}
