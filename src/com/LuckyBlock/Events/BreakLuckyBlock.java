package com.LuckyBlock.Events;

import com.LuckyBlock.Commands.LuckyBlockCommand;
import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Engine.Types;
import com.LuckyBlock.Entities.Soldier;
import com.LuckyBlock.Entities.Soldier.SpawnWay;
import com.LuckyBlock.Events.ActionPerformer.Actions;
import com.LuckyBlock.Resources.ParticleEffect;
import com.LuckyBlock.Resources.SchedulerTask;
import net.minecraft.server.v1_8_R1.ItemArmor;
import net.minecraft.server.v1_8_R1.ItemSword;
import net.minecraft.server.v1_8_R1.ItemTool;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings({"unused", "deprecation"})
public class BreakLuckyBlock implements Listener {


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
    protected static Random randoms = new Random();


    //  Break Lucky Block  //

    public static void openLB(Block block, Types types, Player player) {
        if (!LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block))) {
            return;
        }
        String dim = LuckyBlockAPI.createDim(block);
        Location bloc = block.getLocation();
        int chancei = LuckyBlockAPI.chances.get(dim);
        int luckyBlock = LuckyBlockAPI.luck.get(dim);
        block.setType(Material.AIR);
        if (player != null) {
            if (player.getItemInHand() != null) {
                if (player.getItemInHand().hasItemMeta()) {
                    if (player.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                        if (player.getGameMode() != GameMode.CREATIVE) {
                            LuckyBlock.instance.spawnLB(luckyBlock, types, bloc);
                        }
                        LuckyBlockAPI.removeLB(dim);
                        return;
                    }
                }
            }
            if (player.getGameMode() == GameMode.CREATIVE) {
                if (types.isWorksInCreative() == false) {
                    return;
                }
            }
        }
        if (types.isAllowbreaksound()) {
            if (types.getBreaksound() != null) {
                Sound sound = null;
                float vol = 100;
                float pit = 1;
                String[] s = types.getBreaksound().split(" ");
                try {
                    sound = Sound.valueOf(s[0].toUpperCase());
                } catch (Exception ex) {
                    LuckyBlock.instance.getLogger().info("Invalid Sound!");
                }
                try {
                    vol = Float.parseFloat(s[1]);
                    pit = Float.parseFloat(s[2]);
                } catch (NumberFormatException ex) {
                    LuckyBlock.instance.getLogger().info("Error with sound!");
                }
                playFixedSound(bloc, sound, vol, pit);
            }
        }
        LuckyBlockAPI.removeLB(dim);
        LuckyBlockAPI.luck.remove(dim);
        LuckyBlockAPI.chances.remove(dim);
        if (types.isAllowbreakparticles()) {
            if (types.getBreakparticles() != null) {
                String particle = types.getBreakparticles();
                String[] part = particle.split(" ");
                float rx = Float.parseFloat(part[1]);
                float ry = Float.parseFloat(part[2]);
                float rz = Float.parseFloat(part[3]);
                float speed = Float.parseFloat(part[4]);
                int amount = Integer.parseInt(part[5]);
                double lx = Double.parseDouble(part[6]);
                double ly = Double.parseDouble(part[7]);
                double lz = Double.parseDouble(part[8]);
                int range = Integer.parseInt(part[9]);
                ParticleEffect.valueOf(part[0].toUpperCase()).display(rx, ry, rz, speed, amount, bloc.add(lx, ly, lz), range);
            }
        }
        FileConfiguration file = null;
        String[] ad = LuckyBlock.instance.configf.getPath().split(LuckyBlock.instance.configf.getName());
        if (types != null) {
            File fileF = new File(ad[0] + "/Values/" + types.getFolder() + "/");
            if (luckyBlock > -1 && luckyBlock < 20) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[0].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            } else if (luckyBlock > 19 && luckyBlock < 51) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[1].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            } else if (luckyBlock > 50 && luckyBlock < 100) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[2].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            } else if (luckyBlock > 99 && luckyBlock < 200) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[3].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            } else if (luckyBlock > 199) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[4].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            } else if (luckyBlock < 0 && luckyBlock > -50) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[5].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            } else if (luckyBlock < -49 && luckyBlock > -101) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[6].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            } else if (luckyBlock < -100) {
                fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[7].getName());
                file = YamlConfiguration.loadConfiguration(fileF);
            }
        } else {
            File fileF = new File(ad[0] + "/Values/");
            File file1F = new File(fileF.getPath() + "/" + fileF.listFiles()[0].getName());
            fileF = new File(file1F.getPath() + "/" + file1F.listFiles()[0].getName());
            file = YamlConfiguration.loadConfiguration(fileF);
        }
        String typ = "null";
        TreeType treetype = TreeType.TREE;
        String[] text = {null, null, null, null};
        short height = 0;
        String cmd = null;
        int xp = 0;
        int cc = chancei;
        List<String> lucks = file.getStringList("Luck.Values");
        int zz = -1;
        for (int x = 0; x < lucks.size(); x++) {
            String[] s = lucks.get(x).split(file.getString("SplitSymbol"));
            for (int y = 0; y < s.length; y++) {
                if (s[y].startsWith("Chance:")) {
                    String[] d = s[y].split("Chance:");
                    String[] dd = d[1].split("-");
                    if (dd.length == 1) {
                        try {
                            if (Integer.parseInt(d[1]) == cc) {
                                zz = x;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else if (dd.length == 2) {
                        List<Integer> l = new ArrayList<Integer>();
                        try {
                            for (int k = Integer.parseInt(dd[0]); k < (Integer.parseInt(dd[1]) + 1); k++) {
                                l.add(k);
                            }
                            if (l.contains(cc)) {
                                zz = x;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        if (zz != -1) {
            String[] s = lucks.get(zz).split(file.getString("SplitSymbol"));
            for (int y = 0; y < s.length; y++) {
                if (s[y].startsWith("Type:")) {
                    String[] a = s[y].split("Type:");
                    try {
                        typ = a[1].toUpperCase();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (s[y].startsWith("TreeType:")) {
                    String[] a = s[y].split("TreeType:");
                    if (a[1].equalsIgnoreCase("bigtree") || a[1].equalsIgnoreCase("treebig")) {
                        treetype = TreeType.BIG_TREE;
                    } else if (a[1].equalsIgnoreCase("brownmushroom") || a[1].equalsIgnoreCase("mushroombrown")) {
                        treetype = TreeType.BROWN_MUSHROOM;
                    } else if (a[1].equalsIgnoreCase("darkoak") || a[1].equalsIgnoreCase("bigoak")) {
                        treetype = TreeType.DARK_OAK;
                    } else if (a[1].equalsIgnoreCase("junglebush") || a[1].equalsIgnoreCase("bush")) {
                        treetype = TreeType.JUNGLE_BUSH;
                    } else if (a[1].equalsIgnoreCase("megaredwood") || a[1].equalsIgnoreCase("redwoodmega")) {
                        treetype = TreeType.MEGA_REDWOOD;
                    } else if (a[1].equalsIgnoreCase("redmushroom") || a[1].equalsIgnoreCase("mushroomred")) {
                        treetype = TreeType.RED_MUSHROOM;
                    } else if (a[1].equalsIgnoreCase("smalljungle") || a[1].equalsIgnoreCase("junglesmall")) {
                        treetype = TreeType.SMALL_JUNGLE;
                    } else if (a[1].equalsIgnoreCase("tallbirch") || a[1].equalsIgnoreCase("birchtall")) {
                        treetype = TreeType.TALL_BIRCH;
                    } else if (a[1].equalsIgnoreCase("tallredwood") || a[1].equalsIgnoreCase("redwoodtall")) {
                        treetype = TreeType.TALL_REDWOOD;
                    } else {
                        try {
                            treetype = TreeType.valueOf(a[1].toUpperCase());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (s[y].startsWith("Text:")) {
                    String[] a = s[y].split("Text:");
                    if (a.length == 2) {
                        String[] b = a[1].split(",");
                        for (int x = 0; x < b.length; x++) {
                            if (x < 4) {
                                text[x] = ChatColor.translateAlternateColorCodes('&', b[x]);
                            }
                        }
                    }
                } else if (s[y].startsWith("Amount:")) {
                    String[] a = s[y].split("Amount:");
                    if (a.length == 2) {
                        String[] b = a[1].split("-");
                        if (b.length == 2) {
                            try {
                                xp = LuckyBlock.randoms.nextInt(((Integer.parseInt(b[1]) - Integer.parseInt(b[0]))) + 1) + Integer.parseInt(b[0]);
                            } catch (NumberFormatException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                xp = Integer.parseInt(a[1]);
                            } catch (NumberFormatException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                } else if (s[y].startsWith("Command:")) {
                    String[] a = s[y].split("Command:");
                    if (a.length == 2) {
                        cmd = a[1];
                        cmd = cmd.replace("%Player%", player.getName());
                        cmd = cmd.replace("{player}", player.getName());
                        cmd = cmd.replace("{playername}", player.getName());
                    }
                } else if (s[y].startsWith("Value:")) {
                    String[] a = s[y].split("Value:");
                    if (a.length == 2) {
                        try {
                            height = Short.parseShort(a[1]);
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (s[y].startsWith("Message:")) {
                    String[] a = s[y].split("Message:");
                    if (a.length == 2) {
                        if (player != null) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', a[1]));
                        }
                    }
                }
            }

            if (typ.equalsIgnoreCase("CHEST")) {
                ConfigurationSection cs = file.getConfigurationSection("Chest.Items");
                int FK = 0;
                for (String sk : cs.getKeys(false)) {
                    FK++;
                }
                int chs = LuckyBlock.randoms.nextInt(FK);
                block.setType(Material.CHEST);
                int chc = 0;
                List<String> chests = cs.getStringList(cs.getKeys(false).toArray()[chs].toString());
                int z = 0;
                int times = 0;
                for (int x = 0; x < chests.size(); x++) {
                    if (chests.get(x).startsWith("Chances:")) {
                        String[] asd = chests.get(x).split("Chances:");
                        chc = Integer.parseInt(asd[1]);
                    } else if (chests.get(x).startsWith("Times:")) {
                        String[] asd = chests.get(x).split("Times:");
                        String[] asdf = asd[1].split("-");
                        if (asdf.length > 1) {
                            times = LuckyBlock.randoms.nextInt(((Integer.parseInt(asdf[1]) - Integer.parseInt(asdf[0]))) + 1) + Integer.parseInt(asdf[0]);
                        } else {
                            times = Integer.parseInt(asd[1]);
                        }
                    }
                }
                Chest chest = (Chest) block.getState();
                for (int gh = 0; gh < times; gh++) {
                    int c = LuckyBlock.randoms.nextInt(chc) + 1;
                    for (int x = 0; x < chests.size(); x++) {
                        String[] ss = chests.get(x).split(file.getString("SplitSymbol"));
                        for (int y = 0; y < ss.length; y++) {
                            if (ss[y].startsWith("Chance:")) {
                                String[] d = ss[y].split("Chance:");
                                String[] dd = d[1].split("-");
                                if (dd.length == 1) {
                                    try {
                                        if (Integer.parseInt(d[1]) == c) {
                                            z = x;
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else if (dd.length == 2) {
                                    List<Integer> l = new ArrayList<Integer>();
                                    try {
                                        for (int k = Integer.parseInt(dd[0]); k < (Integer.parseInt(dd[1]) + 1); k++) {
                                            l.add(k);
                                        }
                                        if (l.contains(c)) {
                                            z = x;
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    ItemStack item = null;
                    item = LuckyBlockAPI.getItem(file, chests, z, player);
                    if (item != null) {
                        try {
                            chest.getInventory().setItem(LuckyBlock.randoms.nextInt(27), item);
                        } catch (Exception ex) {
                            //
                        }
                    }
                }

            } else if (typ.equalsIgnoreCase("POTIONS")) {
                if (player != null) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, (short) 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, (short) 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, (short) 0));
                }

            } else if (typ.equalsIgnoreCase("FALLING BLOCK")) {
                block.getWorld().playEffect(bloc, Effect.POTION_BREAK, LuckyBlock.randoms.nextInt(10), 300);
                block.getWorld().playEffect(bloc, Effect.POTION_BREAK, LuckyBlock.randoms.nextInt(10), 300);
                block.getWorld().playEffect(bloc, Effect.POTION_BREAK, LuckyBlock.randoms.nextInt(10), 300);

                int c = LuckyBlock.randoms.nextInt(file.getInt("FallingBlocks.Chances")) + 1;
                List<String> blocks = file.getStringList("FallingBlocks.Blocks");
                int z = -1;
                for (int x = 0; x < blocks.size(); x++) {
                    String[] sx = blocks.get(x).split(file.getString("SplitSymbol"));
                    for (int y = 0; y < sx.length; y++) {
                        if (sx[y].startsWith("Chance:")) {
                            String[] d = sx[y].split("Chance:");
                            String[] dd = d[1].split("-");
                            if (dd.length == 1) {
                                try {
                                    if (Integer.parseInt(d[1]) == c) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (dd.length == 2) {
                                List<Integer> l = new ArrayList<Integer>();
                                try {
                                    for (int k = Integer.parseInt(dd[0]); k < (Integer.parseInt(dd[1]) + 1); k++) {
                                        l.add(k);
                                    }
                                    if (l.contains(c)) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if (z != -1) {
                    Material type = Material.STONE;
                    int bb = 0;
                    String[] ss = blocks.get(z).split(file.getString("SplitSymbol"));
                    for (int y = 0; y < ss.length; y++) {
                        if (ss[y].startsWith("Type:")) {
                            String[] a = ss[y].split("Type:");
                            try {
                                type = Material.getMaterial(a[1].toUpperCase());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else if (ss[y].startsWith("Data:")) {
                            String[] a = ss[y].split("Data:");
                            String[] d = a[1].split("-");
                            if (d.length > 1) {
                                try {
                                    bb = LuckyBlock.randoms.nextInt(((Integer.parseInt(d[1]) - Integer.parseInt(d[0]))) + 1) + Integer.parseInt(d[0]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                try {
                                    bb = Integer.parseInt(a[1]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    block.getWorld().spawnFallingBlock(bloc.add(0, 10, 0), type, (byte) bb);
                }

            } else if (typ.equalsIgnoreCase("MOB")) {
                int c = LuckyBlock.randoms.nextInt(file.getInt("Entities.Chances")) + 1;
                List<String> mobs = file.getStringList("Entities.Mobs");
                int z = -1;
                for (int x = 0; x < mobs.size(); x++) {
                    String[] ss = mobs.get(x).split(file.getString("SplitSymbol"));
                    for (int y = 0; y < ss.length; y++) {
                        if (ss[y].startsWith("Chance:")) {
                            String[] d = ss[y].split("Chance:");
                            String[] dd = d[1].split("-");
                            if (dd.length == 1) {
                                try {
                                    if (Integer.parseInt(d[1]) == c) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (dd.length == 2) {
                                List<Integer> l = new ArrayList<Integer>();
                                try {
                                    for (int k = Integer.parseInt(dd[0]); k < (Integer.parseInt(dd[1]) + 1); k++) {
                                        l.add(k);
                                    }
                                    if (l.contains(c)) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if (z != -1) {
                    LuckyBlockAPI.spawnEntity(bloc, file, z, player, luckyBlock + 1);
                }

            } else if (typ.equalsIgnoreCase("FALLING BLOCK")) {
                block.getWorld().playEffect(bloc, Effect.POTION_BREAK, LuckyBlock.randoms.nextInt(10), 300);
                block.getWorld().playEffect(bloc, Effect.POTION_BREAK, LuckyBlock.randoms.nextInt(10), 300);
                block.getWorld().playEffect(bloc, Effect.POTION_BREAK, LuckyBlock.randoms.nextInt(10), 300);
                int c = LuckyBlock.randoms.nextInt(file.getInt("FallingBlocks.Chances")) + 1;
                List<String> blocks = file.getStringList("FallingBlocks.Blocks");
                int z = -1;
                for (int x = 0; x < blocks.size(); x++) {
                    String[] sx = blocks.get(x).split(file.getString("SplitSymbol"));
                    for (int y = 0; y < sx.length; y++) {
                        if (sx[y].startsWith("Chance:")) {
                            String[] d = sx[y].split("Chance:");
                            String[] dd = d[1].split("-");
                            if (dd.length == 1) {
                                try {
                                    if (Integer.parseInt(d[1]) == c) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (dd.length == 2) {
                                List<Integer> l = new ArrayList<Integer>();
                                try {
                                    for (int k = Integer.parseInt(dd[0]); k < (Integer.parseInt(dd[1]) + 1); k++) {
                                        l.add(k);
                                    }
                                    if (l.contains(c)) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if (z != -1) {
                    Material type = Material.STONE;
                    int bb = 0;
                    String[] ss = blocks.get(z).split(file.getString("SplitSymbol"));
                    for (int y = 0; y < ss.length; y++) {
                        if (ss[y].startsWith("Type:")) {
                            String[] a = ss[y].split("Type:");
                            try {
                                type = Material.getMaterial(a[1].toUpperCase());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else if (ss[y].startsWith("Data:")) {
                            String[] a = ss[y].split("Data:");
                            String[] d = a[1].split("-");
                            if (d.length > 1) {
                                try {
                                    bb = LuckyBlock.randoms.nextInt(((Integer.parseInt(d[1]) - Integer.parseInt(d[0]))) + 1) + Integer.parseInt(d[0]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                try {
                                    bb = Integer.parseInt(a[1]);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    block.getWorld().spawnFallingBlock(bloc.add(0, 10, 0), type, (byte) bb);
                }

            } else if (typ.equalsIgnoreCase("LAVA")) {
                block.setData((byte) 0);
                block.setType(Material.LAVA);
                block.getRelative(BlockFace.EAST).setType(Material.LAVA);
                block.getRelative(BlockFace.WEST).setType(Material.LAVA);
                block.getRelative(BlockFace.SOUTH).setType(Material.LAVA);
                block.getRelative(BlockFace.NORTH).setType(Material.LAVA);

            } else if (typ.equalsIgnoreCase("VILLAGER")) {
                final Villager villager = (Villager) block.getWorld().spawnEntity(block.getLocation(), EntityType.VILLAGER);
                villager.setCustomName(gold + "" + bold + "Lucky Villager");
                villager.setMaxHealth(50);
                villager.setHealth(50);
                final Location bb = bloc;
                final SchedulerTask task = new SchedulerTask();
                task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncDelayedTask(LuckyBlock.instance, new Runnable() {
                    @Override
                    public void run() {
                        if (!villager.isDead()) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld() == bb.getWorld()) {
                                    double distance = p.getLocation().distance(bb);
                                    int maxdistance = 100;
                                    if (distance < 20) {
                                        p.sendMessage(red + "BOOOM!!!!");
                                    }
                                }
                            }
                            villager.remove();
                            int x = villager.getLocation().getBlockX();
                            int y = villager.getLocation().getBlockY();
                            int z = villager.getLocation().getBlockZ();
                            try {
                                boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                                boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                                boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                                if (damage == true) {
                                    villager.getWorld().createExplosion(x, y, z, 3F, setFire, breakBlocks);
                                } else {
                                    villager.getWorld().createExplosion(x, y, z, 0F, setFire, breakBlocks);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }, (randoms.nextInt(70) + 50)));

            } else if (typ.equalsIgnoreCase("SPLASH POTION")) {
                ItemStack tpotion = new ItemStack(Material.POTION, 1, (short) 16454);
                PotionMeta tpotionM = (PotionMeta) tpotion.getItemMeta();
                int t = LuckyBlock.randoms.nextInt(3) + 1;
                if (t == 1) {
                    tpotionM.addCustomEffect(new PotionEffect(PotionEffectType.getById(randoms.nextInt(2) + 6), 1, 2), true);
                } else if (t == 2) {
                    tpotionM.addCustomEffect(new PotionEffect(PotionEffectType.getById(randoms.nextInt(5) + 1), randoms.nextInt(300) + 300,
                            randoms.nextInt(2)), true);
                } else if (t == 3) {
                    tpotionM.addCustomEffect(new PotionEffect(PotionEffectType.getById(randoms.nextInt(15) + 8), randoms.nextInt(300) + 300
                            , randoms.nextInt(3)), true);
                }
                tpotion.setItemMeta(tpotionM);
                ThrownPotion potion = (ThrownPotion) block.getWorld().spawnEntity(bloc.add(0, 10, 0), EntityType.SPLASH_POTION);
                potion.setItem(tpotion);

            } else if (typ.equalsIgnoreCase("PRIMED TNT")) {
                TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation().add(0, 20, 0), EntityType.PRIMED_TNT);
                tnt.setYield(3F);
                tnt.setFireTicks(2000);
                tnt.setFuseTicks(50);
                try {
                    boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                    if (breakBlocks == false) {
                        tnt.setMetadata("tnt", new FixedMetadataValue(LuckyBlock.instance, "true"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else if (typ.equalsIgnoreCase("LIGHTNING")) {
                if (player != null) {
                    LuckyBlock.instance.LightningR(player, block);
                }

            } else if (typ.equalsIgnoreCase("FAKE DIAMOND")) {
                final Item item = (Item) block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 64));
                item.setPickupDelay(32000);
                LuckyBlock.instance.FakeDiamond(item);

            } else if (typ.equalsIgnoreCase("FIREWORK")) {
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
                FireworkEffect f = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(org.bukkit.Color.GREEN).withColor(org.bukkit.Color.RED).withColor(org.bukkit.Color.YELLOW).withFade(org.bukkit.Color.AQUA).with(type).trail(r.nextBoolean()).build();
                fwm.clearEffects();
                fwm.addEffect(f);
                int rp = r.nextInt(2) + 1;
                fwm.setPower(rp);
                fwork.setFireworkMeta(fwm);
                final SchedulerTask task = new SchedulerTask();
                final int l = luckyBlock;
                task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncDelayedTask(LuckyBlock.instance, new Runnable() {
                    @Override
                    public void run() {
                        int trap = LuckyBlock.randoms.nextInt(2);
                        if (trap > 0) {
                            if (l < 51) {
                                TNTPrimed t = (TNTPrimed) fwork.getWorld().spawnEntity(fwork.getLocation(), EntityType.PRIMED_TNT);
                                try {
                                    boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                                    if (breakBlocks == false) {
                                        t.setMetadata("tnt", new FixedMetadataValue(LuckyBlock.instance, "true"));
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                t.setFuseTicks(70);
                                t.setYield(5F);
                            } else {
                                FallingBlock tntfake = (FallingBlock) fwork.getWorld().spawnFallingBlock(fwork.getLocation(), Material.EMERALD_BLOCK, (byte) 0);
                            }
                        } else if (trap == 0) {
                            if (l < 20) {
                                FallingBlock tntfake = (FallingBlock) fwork.getWorld().spawnFallingBlock(fwork.getLocation(), Material.TNT, (byte) 0);
                                tntfake.setDropItem(false);
                            } else {
                                FallingBlock bl = (FallingBlock) fwork.getWorld().spawnFallingBlock(fwork.getLocation(), Material.DIAMOND_BLOCK, (byte) 0);
                                bl.setDropItem(false);
                                ExperienceOrb xp = (ExperienceOrb) fwork.getWorld().spawnEntity(fwork.getLocation(), EntityType.EXPERIENCE_ORB);
                                xp.setExperience((randoms.nextInt(30) + 15) * 2);
                                bl.setPassenger(xp);
                            }
                        }
                        task.run();
                    }
                }, 60L));

            } else if (typ.equalsIgnoreCase("DROPPED ITEMS")) {
                int x = LuckyBlock.randoms.nextInt(file.getStringList("DroppedItems.Items").size());
                List<String> list = file.getStringList("DroppedItems.Items");
                LuckyBlockAPI.dropItem(list, x, block.getWorld().getName(), bloc);

            } else if (typ.equalsIgnoreCase("STUCK")) {
                if (player != null) {
                    Location loc = player.getLocation();
                    player.sendMessage(LuckyBlockCommand.getMessage("StuckTrap"));
                    LuckyBlock.instance.STUCK(player, loc, (long) ((LuckyBlock.randoms.nextInt(10) + 10)));
                }

            } else if (typ.equalsIgnoreCase("DAMAGE")) {
                if (player != null) {
                    player.damage(2.5);
                    player.sendMessage(LuckyBlockCommand.getMessage("Damage"));
                }

            } else if (typ.equalsIgnoreCase("TOWER")) {
                LuckyBlock.instance.Tower(block, LuckyBlock.randoms.nextInt(10) + 1);

            } else if (typ.equalsIgnoreCase("FPIGS")) {
                for (int x = LuckyBlock.randoms.nextInt(5) + 4; x > 0; x--) {
                    final Bat bat = (Bat) block.getWorld().spawnEntity(block.getLocation().add(0, 0, 0), EntityType.BAT);
                    bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 0));
                    final Pig pig = (Pig) block.getWorld().spawnEntity(block.getLocation(), EntityType.PIG);
                    if (LuckyBlock.randoms.nextInt(2) + 1 == 1) {
                        pig.setCustomName(yellow + "Lucky Pig " + green + "+1 Health");
                    } else {
                        pig.setCustomName(yellow + "Lucky Pig " + green + "+2 Health");
                    }
                    pig.setCustomNameVisible(true);
                    bat.setPassenger(pig);
                    bat.setMetadata("luckybat", new FixedMetadataValue(LuckyBlock.instance, "true"));
                    final SchedulerTask task = new SchedulerTask();
                    task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
                                @Override
                                public void run() {
                                    if (!pig.isDead() && !bat.isDead()) {
                                        ParticleEffect.REDSTONE.display(0.3f, 0.3f, 0.3f, 1, 200, pig.getLocation(), 30);
                                    } else {
                                        task.run();
                                    }
                                }
                            }, 2L, 2L)
                    );
                }

            } else if (typ.equalsIgnoreCase("SLIMES")) {
                int size = LuckyBlock.randoms.nextInt(3) + 1;
                Slime slime = (Slime) block.getWorld().spawnEntity(block.getLocation(), EntityType.SLIME);
                slime.setSize(size);
                Slime slime1 = (Slime) block.getWorld().spawnEntity(block.getLocation(), EntityType.SLIME);
                slime1.setSize(size);
                Slime slime2 = (Slime) block.getWorld().spawnEntity(block.getLocation(), EntityType.SLIME);
                slime2.setSize(size);
                Slime slime3 = (Slime) block.getWorld().spawnEntity(block.getLocation(), EntityType.SLIME);
                slime3.setSize(size);
                slime.setMaxHealth(48.0);
                slime1.setMaxHealth(48.0);
                slime2.setMaxHealth(48.0);
                slime3.setMaxHealth(48.0);
                slime.setHealth(48.0);
                slime1.setHealth(48.0);
                slime2.setHealth(48.0);
                slime3.setHealth(48.0);
                String ls = red + "Lucky Slime";
                slime.setCustomName(ls);
                slime1.setCustomName(ls);
                slime2.setCustomName(ls);
                slime3.setCustomName(ls);
                LuckyBlock.instance.Slimed(slime);
                LuckyBlock.instance.Slimed(slime1);
                LuckyBlock.instance.Slimed(slime2);
                LuckyBlock.instance.Slimed(slime3);
                slime1.setPassenger(slime);
                slime2.setPassenger(slime1);
                slime3.setPassenger(slime2);

            } else if (typ.equalsIgnoreCase("METEORS")) {
                for (int x = 8; x > 0; x--) {
                    FallingBlock fb = (FallingBlock) block.getWorld().spawnFallingBlock(block.getLocation().add(
                            LuckyBlock.randoms.nextInt(10), 35, LuckyBlock.randoms.nextInt(10)), Material.STONE, (byte) 0);
                    fb.setVelocity(fb.getVelocity().multiply(2));
                    LuckyBlock.instance.Meteor(fb);
                }

            } else if (typ.equalsIgnoreCase("FLYINGLB")) {
                Bat bat = (Bat) block.getWorld().spawnEntity(block.getLocation(), EntityType.BAT);
                bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 0));
                FallingBlock fb = (FallingBlock) bat.getWorld().spawnFallingBlock(bat.getLocation().add(0, 5, 0), Material.SPONGE, (byte) 0);
                bat.setPassenger(fb);
                bat.setMetadata("flyinglb", new FixedMetadataValue(LuckyBlock.instance, "true"));
                fb.setDropItem(false);
                final Bat b = bat;
                final SchedulerTask task = new SchedulerTask();
                final Types t = types;
                task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
                            @Override
                            public void run() {
                                if (!b.isDead()) {
                                    if (b.getPassenger() == null) {
                                        b.remove();
                                        ItemStack lucky = t.toItemStack();
                                        b.getWorld().dropItem(b.getLocation(), lucky);
                                    }
                                } else {
                                    task.run();
                                }
                            }
                        }, 20L, 20L)
                );

            } else if (typ.equalsIgnoreCase("SOLDIER")) {
                Soldier soldier = new Soldier(SpawnWay.DEFAULT);
                soldier.setTarget(player);
                soldier.spawn(bloc);

            } else if (typ.equalsIgnoreCase("LBITEM")) {
                ItemStack l = types.toItemStack();
                block.getWorld().dropItem(bloc, l);

            } else if (typ.equalsIgnoreCase("SNOWMOVEMENT")) {
                if (player != null) {
                    if (LuckyBlock.instance.config.getBoolean("Allow.SnowMoving") == true) {
                        String g = LuckyBlockCommand.getMessage("SnowMovingStarted");
                        g = g.replace("%SnowTime%", LuckyBlock.instance.config.getInt("SnowMovementTime") + "");
                        player.sendMessage(g);
                        if (!LuckyBlock.snowmove.contains(player.getName())) {
                            LuckyBlock.snowmove.add(player.getName());
                            final Player p = player;
                            final SchedulerTask task = new SchedulerTask();
                            task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncDelayedTask(LuckyBlock.instance, new Runnable() {
                                @Override
                                public void run() {
                                    LuckyBlock.snowmove.remove(p.getName());
                                    p.sendMessage(green + "The Snowmove has been ended!");
                                }
                            }, LuckyBlock.instance.config.getInt("SnowMovementTime") * 20));
                        }
                    } else {
                        player.sendMessage(LuckyBlockCommand.getMessage("SnowMovingDisabled"));
                    }
                }

            } else if (typ.equalsIgnoreCase("BEDROCK")) {
                LuckyBlock.instance.Bedrock(block);

            } else if (typ.equalsIgnoreCase("JAIL")) {
                LuckyBlock.instance.Trap(player);

            } else if (typ.equalsIgnoreCase("TREE")) {
                LuckyBlock.instance.Tree(block, treetype);
                if (player != null) {
                    player.sendMessage(LuckyBlockCommand.getMessage("TreeSpawned"));
                }

            } else if (typ.equalsIgnoreCase("SHOOT SHEEP")) {
                if (player != null) {
                    player.getInventory().addItem(LuckyBlockAPI.createItemStack(Material.STICK, 4, (short) 0, red + "Sheep Shooter"));
                }

            } else if (typ.equalsIgnoreCase("CHANGEABLE BLOCK")) {
                LuckyBlock.instance.CBlock(block);

            } else if (typ.equalsIgnoreCase("WOLVES OR OCELOTS")) {
                int x = LuckyBlock.randoms.nextInt(2) + 1;
                if (x == 1) {
                    for (int t = LuckyBlock.randoms.nextInt(5) + 7; t > 0; t--) {
                        Wolf wolf = (Wolf) block.getWorld().spawnEntity(bloc, EntityType.WOLF);
                        wolf.setTamed(true);
                        wolf.setOwner(player);
                        wolf.setMaxHealth(30.0);
                        wolf.setHealth(30.0);
                        wolf.setSitting(true);
                        wolf.setCollarColor(DyeColor.getByData((byte) LuckyBlock.randoms.nextInt(16)));
                        ParticleEffect.HEART.display(0.3f, 0.3f, 0.3f, 0, 10, wolf.getLocation(), 64);
                        Damageable d = (Damageable) wolf;
                        wolf.setCustomName(yellow + "" + bold + "Wolf " + green + d.getHealth() + white + "/" + green + d.getMaxHealth());
                        wolf.setCustomNameVisible(true);
                    }
                } else {
                    for (int t = LuckyBlock.randoms.nextInt(5) + 7; t > 0; t--) {
                        Ocelot ocelot = (Ocelot) block.getWorld().spawnEntity(bloc, EntityType.OCELOT);
                        ocelot.setCatType(org.bukkit.entity.Ocelot.Type.getType(LuckyBlock.randoms.nextInt(4)));
                        ocelot.setSitting(true);
                        ocelot.setOwner(player);
                        ocelot.setTamed(true);
                        ocelot.setMaxHealth(20.0);
                        ocelot.setHealth(20.0);
                    }
                }

            } else if (typ.equalsIgnoreCase("FIREWORKS")) {
                LuckyBlock.instance.FireWorks(block);

            } else if (typ.equalsIgnoreCase("FEED")) {
                if (player != null) {
                    try {
                        player.setFoodLevel(20);
                    } catch (Exception ex) {
                        player.setFoodLevel(20);
                    }
                    player.sendMessage(LuckyBlockCommand.getMessage("Feed"));
                }

            } else if (typ.equalsIgnoreCase("HEAL")) {
                if (player != null) {
                    Damageable d = (Damageable) player;
                    player.setHealth(d.getMaxHealth());
                    player.sendMessage(LuckyBlockCommand.getMessage("Heal"));
                }

            } else if (typ.equalsIgnoreCase("SIGN")) {
                for (int x = 0; x < 10; x++) {
                    //
                }
                block.setType(Material.SIGN_POST);
                Sign sign = (Sign) block.getState();
                for (int x = 0; x < text.length; x++) {
                    sign.setLine(x, text[x]);
                }
                sign.update(true);

            } else if (typ.equalsIgnoreCase("REPAIR")) {
                if (player != null) {
                    player.sendMessage(LuckyBlockCommand.getMessage("RepairItems"));
                    for (int x = 0; x < player.getInventory().getSize(); x++) {
                        if (player.getInventory().getItem(x) != null && player.getInventory().getItem(x).getType() != Material.AIR) {
                            net.minecraft.server.v1_8_R1.ItemStack nms = CraftItemStack.asNMSCopy(player.getInventory().getItem(x));
                            if (nms.getItem() instanceof ItemTool || nms.getItem() instanceof ItemSword || nms.getItem() instanceof ItemArmor) {
                                if (player.getInventory().getItem(x).getDurability() > 0) {
                                    player.getInventory().getItem(x).setDurability((short) 0);
                                }
                            }
                        }
                    }
                    for (ItemStack item : player.getInventory().getArmorContents()) {
                        if (item != null && item.getType() != Material.AIR) {
                            net.minecraft.server.v1_8_R1.ItemStack nms = CraftItemStack.asNMSCopy(item);
                            if (nms.getItem() instanceof ItemTool || nms.getItem() instanceof ItemSword || nms.getItem() instanceof ItemArmor) {
                                if (item.getDurability() > 0) {
                                    item.setDurability((short) 0);
                                }
                            }
                        }
                    }
                }

            } else if (typ.equalsIgnoreCase("ENCHANT")) {
                if (player != null) {
                    if (player.getItemInHand() == null) {
                        player.sendMessage(LuckyBlockCommand.getMessage("EnchantItem.Fail"));
                        return;
                    }
                    ItemMeta im = player.getItemInHand().getItemMeta();
                    int t = LuckyBlock.randoms.nextInt(2) + 1;
                    if (player.getItemInHand().getType() == Material.BOW) {
                        if (t == 1) {
                            im.addEnchant(Enchantment.getById(LuckyBlock.randoms.nextInt(4) + 48), 1, true);
                        } else if (t == 2) {
                            im.addEnchant(Enchantment.DURABILITY, LuckyBlock.randoms.nextInt(3) + 1, true);
                        }
                    } else {
                        if (t == 1) {
                            im.addEnchant(Enchantment.getById(LuckyBlock.randoms.nextInt(6) + 16), LuckyBlock.randoms.nextInt(2) + 1, true);
                        } else if (t == 2) {
                            im.addEnchant(Enchantment.DURABILITY, LuckyBlock.randoms.nextInt(3) + 1, true);
                        }
                    }
                    player.getItemInHand().setItemMeta(im);
                    player.sendMessage(LuckyBlockCommand.getMessage("EnchantItem.Success"));
                    player.updateInventory();
                }

            } else if (typ.equalsIgnoreCase("ADDED ITEM")) {
                if (player != null) {
                    int x = LuckyBlock.randoms.nextInt(file.getStringList("AddedItems.Items").size());
                    List<String> list = file.getStringList("AddedItems.Items");
                    try {
                        player.getInventory().addItem(LuckyBlockAPI.getItem(file, list, x, player));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    String message = null;
                    String[] d = file.getStringList("AddedItems.Items").get(x).split("#");
                    if (d.length > 1) {
                        for (int y = 0; y < d.length; y++) {
                            if (d[y].startsWith("Message:")) {
                                String[] c = d[y].split("Message:");
                                if (c.length == 2) {
                                    message = ChatColor.translateAlternateColorCodes('&', c[1]);
                                }
                            }
                        }
                    }
                    player.sendMessage(message);
                }

            } else if (typ.equalsIgnoreCase("XP")) {
                ExperienceOrb exp = (ExperienceOrb) block.getWorld().spawnEntity(bloc, EntityType.EXPERIENCE_ORB);
                exp.setExperience(xp);

            } else if (typ.equalsIgnoreCase("POISON ENEMIES")) {
                if (player != null) {
                    player.sendMessage(LuckyBlockCommand.getMessage("PoisonEntities"));
                    for (Entity e : player.getNearbyEntities(10, 10, 10)) {
                        if (e instanceof LivingEntity) {
                            LivingEntity l = (LivingEntity) e;
                            if (l.getUniqueId() != player.getUniqueId()) {
                                if (!(l instanceof Tameable)) {
                                    l.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 10));
                                    l.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 10));
                                    l.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 10));
                                    l.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 15));
                                } else {
                                    Tameable t = (Tameable) l;
                                    if (!t.isTamed()) {
                                        l.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 10));
                                        l.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 10));
                                        l.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 10));
                                        l.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 15));
                                    }
                                }
                            }
                        }
                    }
                }

            } else if (typ.equalsIgnoreCase("GIFT")) {
                if (player != null) {
                    if (LuckyBlock.gifts.containsKey(player.getUniqueId())) {
                        LuckyBlock.gifts.put(player.getUniqueId(), LuckyBlock.gifts.get(player.getUniqueId()) + 1);
                    } else {
                        LuckyBlock.gifts.put(player.getUniqueId(), 1);
                    }
                    player.sendMessage(LuckyBlockCommand.getMessage("GetGift"));
                }

            } else if (typ.equalsIgnoreCase("CUSTOM STRUCTURE")) {
                int c = randoms.nextInt(file.getInt("CustomStructures.Chances")) + 1;
                List<String> blocks = file.getStringList("CustomStructures.Structures");
                int z = -1;
                String ltype = "Player";
                for (int x = 0; x < blocks.size(); x++) {
                    String[] sx = blocks.get(x).split(file.getString("SplitSymbol"));
                    for (int y = 0; y < sx.length; y++) {
                        if (sx[y].startsWith("Chance:")) {
                            String[] d = sx[y].split("Chance:");
                            String[] dd = d[1].split("-");
                            if (dd.length == 1) {
                                try {
                                    if (Integer.parseInt(d[1]) == c) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (dd.length == 2) {
                                List<Integer> l = new ArrayList<Integer>();
                                try {
                                    for (int k = Integer.parseInt(dd[0]); k < (Integer.parseInt(dd[1]) + 1); k++) {
                                        l.add(k);
                                    }
                                    if (l.contains(c)) {
                                        z = x;
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else if (sx[y].startsWith("LocationType:")) {
                            String[] d = sx[y].split("LocationType:");
                            if (d.length == 2) {
                                if (d[1].equalsIgnoreCase("Player")) {
                                    ltype = "Player";
                                } else if (d[1].equalsIgnoreCase("Block")) {
                                    ltype = "Block";
                                }
                            }
                        }
                    }
                }
                if (z != -1) {
                    String[] to = blocks.get(z).split("//");
                    int[] i = new int[3];
                    for (int y = 0; y < to.length; y++) {
                        Material type = Material.STONE;
                        String[] tt = to[y].split("#");
                        int bb = 0;
                        for (int f = 0; f < tt.length; f++) {
                            if (tt[f].startsWith("Type:")) {
                                String[] a = tt[f].split("Type:");
                                try {
                                    type = Material.getMaterial(Integer.parseInt(a[1]));
                                } catch (Exception ex) {
                                    type = Material.getMaterial(a[1].toUpperCase());
                                }
                            } else if (tt[f].startsWith("Data:")) {
                                String[] a = tt[f].split("Data:");
                                String[] d = a[1].split("-");
                                if (d.length > 1) {
                                    try {
                                        bb = LuckyBlock.randoms.nextInt(((Integer.parseInt(d[1]) - Integer.parseInt(d[0]))) + 1) + Integer.parseInt(d[0]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    try {
                                        bb = Integer.parseInt(a[1]);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            } else if (tt[f].startsWith("Loc:")) {
                                String[] lo = tt[f].split("Loc:");
                                if (lo.length == 2) {
                                    String[] d = lo[1].split(",");
                                    if (d.length == 3) {
                                        try {
                                            i[0] = Integer.parseInt(d[0]);
                                            i[1] = Integer.parseInt(d[1]);
                                            i[2] = Integer.parseInt(d[2]);
                                        } catch (NumberFormatException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                        try {
                            Location loc = player.getLocation();
                            if (ltype.equalsIgnoreCase("Player")) {
                                loc = player.getLocation();
                            } else if (ltype.equalsIgnoreCase("Block")) {
                                loc = bloc;
                            }
                            if (ltype.equalsIgnoreCase("Player")) {
                                loc.add(i[0], i[1], i[2]).getBlock().setType(type);
                                loc.add(i[0], i[1], i[2]).getBlock().setData((byte) bb);
                            } else if (ltype.equalsIgnoreCase("Block")) {
                                Location l = new Location(loc.getWorld(), loc.getBlockX() + i[0], loc.getBlockY() + i[1], loc.getBlockZ() + i[2]);
                                l.getBlock().setType(type);
                                l.getBlock().setData((byte) bb);
                            }
                        } catch (Exception ex) {
                            //
                        }
                    }
                }

            } else if (typ.equalsIgnoreCase("Run Command")) {
                if (cmd != null) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                }

            } else if (typ.equalsIgnoreCase("Remove Harmful Effects")) {
                if (player != null) {
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    player.removePotionEffect(PotionEffectType.CONFUSION);
                    player.removePotionEffect(PotionEffectType.HARM);
                    player.removePotionEffect(PotionEffectType.POISON);
                    player.removePotionEffect(PotionEffectType.SLOW);
                    player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    player.removePotionEffect(PotionEffectType.WITHER);
                    player.sendMessage(LuckyBlockCommand.getMessage("RemoveHarmfulEffects"));
                }

            } else if (typ.equalsIgnoreCase("Teleport")) {
                if (player != null) {
                    player.teleport(player.getLocation().add(0, height, 0));
                }

            } else if (typ.equalsIgnoreCase("PerformAction")) {
                for (int x = 0; x < file.getStringList("Actions").size(); x++) {
                    String g = file.getStringList("Actions").get(x);
                    String type = null;
                    String[] d = g.split(",");
                    for (int y = 0; y < d.length; y++) {
                        if (d[y].startsWith("ActionType:")) {
                            type = d[y].split("ActionType:")[1];
                        }
                    }
                    if (type != null) {
                        ActionPerformer.perform(Actions.getByName(type), g, player, block);
                    }
                }

            } else if (typ.equalsIgnoreCase("RandomItem")) {
                List<String> list = file.getStringList("RandomItems");
                final List<ItemStack> items = new ArrayList<ItemStack>();
                for (int x = 0; x < list.size(); x++) {
                    items.add(LuckyBlockAPI.getItem(list.get(x), file.getString("SplitSymbol")));
                }
                if (items.size() == 1) {
                    block.getWorld().dropItem(bloc, items.get(0));
                } else if (items.size() > 1) {
                    final ArmorStand as = (ArmorStand) block.getWorld().spawnEntity(bloc, EntityType.ARMOR_STAND);
                    as.setVisible(false);
                    as.setSmall(true);
                    EulerAngle angle = new EulerAngle(-14f, 44f, 0f);
                    as.setRightArmPose(angle);
                    as.setGravity(false);
                    final int l = items.size();
                    final SchedulerTask task = new SchedulerTask();
                    final Block b = block;
                    final Location lo = bloc;
                    final Player p = player;
                    task.setId(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
                        int x = randoms.nextInt((int) (l * 1.5));
                        int f = 0;

                        @Override
                        public void run() {
                            if (x > 0) {
                                as.setItemInHand(items.get(f));
                                playFixedSound(lo, Sound.CHICKEN_EGG_POP, 1, 0, 20);
                                x--;
                                f++;
                                if (f >= l) {
                                    f = 0;
                                }
                            } else {
                                if (f > 0) {
                                    b.getWorld().dropItem(lo, items.get(f - 1));
                                } else {
                                    b.getWorld().dropItem(lo, items.get(items.size() - 1));
                                }
                                playFixedSound(lo, Sound.ITEM_PICKUP, 1, 2, 20);
                                ParticleEffect.CLOUD.display(0.3f, 0.3f, 0.3f, 0, 6, lo, 30);
                                as.remove();
                                task.run();
                            }
                        }
                    }, 3, 5));
                }

            }
        }
    }

    public static void playFixedSound(Location loc, Sound sound, float vol, float pit) {
        int maxdistance = 30;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld() == loc.getWorld()) {
                double distance = p.getLocation().distance(loc);
                if (distance < maxdistance) {
                    float volume = (float) (1 - (distance / maxdistance));
                    p.playSound(p.getLocation(), sound, vol * volume, pit);
                }
            }
        }
    }

    public static void playFixedSound(Location loc, Sound sound, float vol, float pit, int maxdistance) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld() == loc.getWorld()) {
                double distance = p.getLocation().distance(loc);
                if (distance < maxdistance) {
                    float volume = (float) (1 - (distance / maxdistance));
                    p.playSound(p.getLocation(), sound, vol * volume, pit);
                }
            }
        }
    }

    @EventHandler
    public void LuckyBlockEvent(BlockBreakEvent event) {
        final Player player = event.getPlayer();
        if (LuckyBlock.instance.config.getBoolean("UsePermission.breakluckyblock")) {
            if (!player.hasPermission("lb.break")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.config.getString("Messages.NoPermission.breakluckyblock")));
                return;
            }
        }
        if (player.getGameMode() == GameMode.CREATIVE) {
            if (player.getItemInHand() != null) {
                ItemStack item = player.getItemInHand();
                if (item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.IRON_SWORD
                        || item.getType() == Material.GOLD_SWORD || item.getType() == Material.DIAMOND_SWORD) {
                    return;
                }
            }
        }
        Block block = event.getBlock();
        Location bloc = block.getLocation();
        String dim = LuckyBlockAPI.createDim(block);
        Types types = null;
        for (Types t : LuckyBlock.lbs) {
            int g = 0;
            if (t.getWorlds().contains("*All*")) {
                g = 1;
            }
            for (int x = 0; x < t.getWorlds().size(); x++) {
                if (t.getWorlds().get(x).equalsIgnoreCase(player.getWorld().getName())) {
                    g = 1;
                }
            }
            if (g == 0) {
                return;
            }
            if (!t.isWorksInCreative()) {
                if (player.getGameMode() == GameMode.CREATIVE) {
                    return;
                }
            }
            if (t.isNormalBlock()) {
                if (block.getType() == t.getType()) {
                    LuckyBlockAPI.luck.put(dim, LuckyBlock.randoms.nextInt(15));
                    LuckyBlockAPI.chances.put(dim, LuckyBlock.randoms.nextInt(LuckyBlock.instance.lbf.getInt("Luck.Chances")) + 1);
                    LuckyBlockAPI.ids.put(dim, t.getId());
                    types = t;
                }
            } else {
                if (LuckyBlockAPI.luck.containsKey(dim) && LuckyBlockAPI.chances.containsKey(dim)) {
                    if (LuckyBlockAPI.ids.containsKey(dim)) {
                        if (LuckyBlockAPI.ids.get(dim) == t.getId()) {
                            types = t;
                        }
                    }
                }
            }
        }
        String test = null;
        if (LuckyBlockAPI.IsLuckyBlock(dim)) {
            test = "";
        }
        if (types != null || test != null) {
            if (LuckyBlockAPI.BlockOwner.containsKey(dim)) {
                UUID uuid = player.getUniqueId();
                if (!LuckyBlockAPI.BlockOwner.get(dim).equalsIgnoreCase(uuid.toString())) {
                    if (!player.hasPermission("lb.breakall")) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            if (block.getType() == Material.PORTAL) {
                return;
            }
            event.setCancelled(true);
            openLB(block, types, player);
        }
    }


}
