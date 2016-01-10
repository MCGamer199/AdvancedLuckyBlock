package com.LuckyBlock.Events;

import com.LuckyBlock.Commands.LuckyBlockCommand;
import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Engine.Types;
import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.War.Cage;
import com.LuckyBlock.War.Hat;
import com.LuckyBlock.War.Particle;
import com.LuckyBlock.War.War;
import com.LuckyBlock.enums.ShopItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

@SuppressWarnings("unused")
public class Gui implements Listener {


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
    private static HashMap<UUID, Inventory> pi = new HashMap<UUID, Inventory>();


    public static void openOptions(Player player) {
        if (War.containPlayer(player.getUniqueId())) {
            if (!War.getGame(player.getUniqueId()).inGame()) {
                War war = War.getGame(player.getUniqueId());
                Inventory inv = Bukkit.createInventory(player, 9, green + "Options");
                ItemStack[] items = new ItemStack[16];
                ItemStack back = ItemMaker.createItem(Material.COMPASS, 1, (short) 0, red + "" + bold + "Close", Arrays.asList(gray + "Click To Close"));
                int place = 0;
                if (!war.isAllowGates()) {
                    items[place] = ItemMaker.createItem(Material.GRASS, 1, (short) 0, darkgreen + "Spawns",
                            Arrays.asList(gray + "Choose Your Spawn"));
                    place++;
                }
                String on = red + "OFF";
                if (war.isSpawnFallingBlocks()) {
                    on = green + "ON";
                }
                items[place] = ItemMaker.createItem(Material.SAND, 1, 1, yellow + "Falling Blocks", Arrays.asList("", on, gray + "Click to change"));
                place++;
                items[place] = ItemMaker.createItem(Material.REDSTONE, 1, 0, red + "Particles", Arrays.asList("", gray + "Click to open"));
                place++;
                items[place] = ItemMaker.createItem(Material.SKULL_ITEM, 1, 3, green + "List Players", Arrays.asList("", gray + "Click to see players"));
                //place++;
                //items[place] = ItemMaker.createItem(Material.SKULL_ITEM, 1, 2, darkred + "" + bold + "Mob Ability", Arrays.asList("", gray + "Click to open"));
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null && items[i].getType() != Material.AIR) {
                        inv.addItem(items[i]);
                    }
                }
                inv.setItem(inv.getSize() - 1, back);
                player.openInventory(inv);
            }
        }
    }

    public static void openCages(Player player, int page) {
        Inventory inv = Bukkit.createInventory(player, 54, darkpurple + "" + bold + "Cages Shop");
        UUID uuid = player.getUniqueId();
        int money = LuckyBlockAPI.getMoney(player);
        inv.setItem(inv.getSize() - 1, ItemMaker.createItem(Material.COMPASS, 1, (short) 0, red + "Back", Arrays.asList(gray + "Click to back")));
        ItemStack item = LuckyBlockAPI.createItemStack(Material.ARROW, 1, (short) 0, green + "Current Page");
        ItemStack moneyitem = ItemMaker.createItem(Material.EMERALD, 1, (short) 0, yellow + "Data", Arrays.asList(gold + "Money: " +
                green + LuckyBlockAPI.money.get(uuid), gold + "Gold: " + green + LuckyBlockAPI.golds.get(uuid)));
        inv.setItem(inv.getSize() - 5, item);
        item.setAmount(page);
        ItemMeta itemM = item.getItemMeta();
        List<String> list = new ArrayList<String>();
        list.add(gray + "" + page);
        itemM.setLore(list);
        item.setItemMeta(itemM);
        inv.setItem(inv.getSize() - 5, item);
        Cage cage = Cage.getDefaultCage();
        if (cage != null) {
            String g = "";
            if (Cage.selectedcage.get(uuid) == cage) {
                g = blue + "Selected!";
            } else {
                g = green + "Click to select!";
            }
            inv.setItem(inv.getSize() - 9, ItemMaker.createItem(cage.getType(), 1, cage.getData(), cage.getDisplayName(), Arrays.asList("", g)));
        }
        int place = 10;
        boolean more = false;
        if (page == 1) {
            for (int x = (page - 1) * 27 + ((page - 1) * 2); x < (page * 27) + (page * 2); x++) {
                if (Cage.getCages().size() > x) {
                    Cage c = Cage.getCages().get(x);
                    if (c.isDefault() == false) {
                        if (place == 17 || place == 26 || place == 35) {
                            place += 2;
                        }
                        String g = "";
                        String cost = "";
                        if (Cage.selectedcage.get(uuid) == c) {
                            g = blue + "Selected!";
                        } else {
                            if (Cage.playercages.get(uuid).contains(c)) {
                                g = green + "Click to select";
                            } else {
                                if (!c.isBuyable()) {
                                    g = red + "Locked!";
                                } else {
                                    cost = gray + "$" + c.getCost();
                                    if (money >= c.getCost()) {
                                        g = green + "Click to purchase!";
                                    } else {
                                        g = red + "You don't have the needed cost!";
                                    }
                                }
                            }
                        }
                        inv.setItem(place, ItemMaker.createItem(c.getType(), 1, c.getData(), c.getDisplayName(), Arrays.asList("", g, cost)));
                        place++;
                    }
                }
                if (Cage.getCages().size() >= (page * 27) + (page * 2) + 1) {
                    more = true;
                }
            }
        } else {
            for (int x = (page - 1) * 27 + ((page - 1) * 2) - (page - 2); x < (page * 27) + (page * 2) - 1; x++) {
                if (Cage.getCages().size() > x) {
                    Cage c = Cage.getCages().get(x);
                    if (c.isDefault() == false) {
                        if (place == 17 || place == 26 || place == 35) {
                            place += 2;
                        }
                        String g = "";
                        String cost = "";
                        if (Cage.selectedcage.get(uuid) == c) {
                            g = blue + "Selected!";
                        } else {
                            if (Cage.playercages.get(uuid).contains(c)) {
                                g = green + "Click to select";
                            } else {
                                if (!c.isBuyable()) {
                                    g = red + "Locked!";
                                } else {
                                    cost = gray + "$" + c.getCost();
                                    if (money >= c.getCost()) {
                                        g = green + "Click to purchase!";
                                    } else {
                                        g = red + "You don't have the needed cost!";
                                    }
                                }
                            }
                        }
                        inv.setItem(place, ItemMaker.createItem(c.getType(), 1, c.getData(), c.getDisplayName(), Arrays.asList(gray + "", g, cost)));
                        place++;
                    }
                }
                if (Cage.getCages().size() >= (page * 27) + (page * 2)) {
                    more = true;
                }
            }
        }
        if (more) {
            inv.setItem(inv.getSize() - 2, ItemMaker.createItem(Material.ARROW, page + 1, (short) 0, green + "Next Page",
                    Arrays.asList(gray + "" + (page + 1))));
        }
        if (page > 1) {
            inv.setItem(inv.getSize() - 8, ItemMaker.createItem(Material.ARROW, page - 1, (short) 0, green + "Prev Page",
                    Arrays.asList(gray + "" + (page - 1))));
        }
        inv.setItem(inv.getSize() - 6, moneyitem);
        player.openInventory(inv);
    }

    public static int getOpenedPage(Player player) {
        int page = 1;
        if (player.getOpenInventory() != null) {
            Inventory inv = player.getOpenInventory().getTopInventory();
            if (inv.getTitle() != null) {
                if (inv.getTitle().equalsIgnoreCase(darkpurple + "" + bold + "Cages Shop")) {
                    if (inv.getItem(inv.getSize() - 5) != null && inv.getItem(inv.getSize() - 5).getType() != Material.AIR) {
                        ItemStack item = inv.getItem(inv.getSize() - 5);
                        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(green + "Current Page")) {
                                if (item.getItemMeta().hasLore()) {
                                    if (item.getItemMeta().getLore().size() > 0) {
                                        String s = ChatColor.stripColor(item.getItemMeta().getLore().get(0));
                                        try {
                                            page = Integer.parseInt(s);
                                        } catch (NumberFormatException ex) {
                                            //
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (inv.getItem(inv.getSize() - 2) != null && inv.getItem(inv.getSize() - 2).getType() != Material.AIR) {
                        ItemStack item = inv.getItem(inv.getSize() - 2);
                        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(green + "Next Page")) {
                                if (item.getItemMeta().hasLore()) {
                                    List<String> list = item.getItemMeta().getLore();
                                    if (list.size() > 0) {
                                        String s = ChatColor.stripColor(list.get(0));
                                        try {
                                            page = Integer.parseInt(s) - 1;
                                        } catch (NumberFormatException ex) {
                                            //
                                        }
                                    }
                                }
                            }
                        }
                    } else if (inv.getItem(inv.getSize() - 9) != null && inv.getItem(inv.getSize() - 9).getType() != Material.AIR) {
                        ItemStack item = inv.getItem(inv.getSize() - 9);
                        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(green + "Prev Page")) {
                                if (item.getItemMeta().hasLore()) {
                                    List<String> list = item.getItemMeta().getLore();
                                    if (list.size() > 0) {
                                        String s = ChatColor.stripColor(list.get(0));
                                        try {
                                            page = Integer.parseInt(s) + 1;
                                        } catch (NumberFormatException ex) {
                                            //
                                        }
                                    }
                                }
                            }
                        }
                    } else if (inv.getItem(inv.getSize() - 5) != null && inv.getItem(inv.getSize() - 5).getType() != Material.AIR) {
                        ItemStack item = inv.getItem(inv.getSize() - 5);
                        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(green + "Current Page")) {
                                if (item.getItemMeta().hasLore()) {
                                    if (item.getItemMeta().getLore().size() > 0) {
                                        String s = ChatColor.stripColor(item.getItemMeta().getLore().get(0));
                                        try {
                                            page = Integer.parseInt(s);
                                        } catch (NumberFormatException ex) {
                                            //
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return page;
    }

    public static void openSpawns(Player player, int page) {
        UUID uuid = player.getUniqueId();
        if (War.containPlayer(uuid)) {
            War war = War.getGame(uuid);
            int ssize = 0;
            for (int x = ((page - 1) * 45); x < war.getSpawns().length; x++) {
                if (war.getSpawns()[x] != null) {
                    ssize++;
                }
            }
            int size = 0;
            if (ssize < 9) {
                size = 18;
            } else if (ssize > 8 && ssize < 18) {
                size = 27;
            } else if (ssize > 17 && ssize < 27) {
                size = 36;
            } else if (ssize > 26 && ssize < 36) {
                size = 45;
            } else {
                size = 54;
            }
            Inventory inv = Bukkit.createInventory(player, size, darkgreen + "Choose Spawn");
            ItemStack[] items = new ItemStack[128];
            ItemStack back = LuckyBlockAPI.createItemStack(Material.COMPASS, 1, (short) 0, red + "Back");
            ItemStack cp = ItemMaker.createItem(Material.ARROW, 1, (short) 0, green + "Prev Page", Arrays.asList(gray + "" + (page - 1)));
            if (page > 1) {
                inv.setItem(inv.getSize() - 9, cp);
            }
            for (int x = 0; x < ssize; x++) {
                if (x < (size - 1)) {
                    if (x < 45) {
                        if (inv.getItem(x) == null) {
                            items[x] = LuckyBlockAPI.createItemStack(Material.SAND, 1, (short) 0, yellow + "Spawn " + (x + ((page - 1) * 45) + 1));
                            ItemMeta itemM = items[x].getItemMeta();
                            List<String> list = new ArrayList<String>();
                            String n = "Unused";
                            for (String s : war.getPs().keySet()) {
                                String[] g = items[x].getItemMeta().getDisplayName().split(yellow + "Spawn ");
                                if (g.length == 2) {
                                    try {
                                        int num = Integer.parseInt(ChatColor.stripColor(g[1])) - 1;
                                        if (war.getPs().get(s) == num) {
                                            n = s;
                                        }
                                    } catch (NumberFormatException ex) {
                                        //
                                    }
                                }
                            }
                            list.add(green + "User: " + gray + n);
                            if (n.equalsIgnoreCase("Unused")) {
                                list.add(green + "Click to Choose.");
                            } else {
                                list.add(red + "You can't Choose this spawn.");
                            }
                            itemM.setLore(list);
                            items[x].setItemMeta(itemM);
                        }
                    }
                }
            }
            for (int x = 0; x < items.length; x++) {
                if (items[x] != null && items[x].getType() != Material.AIR) {
                    inv.addItem(items[x]);
                }
            }
            int total = 0;
            for (int x = 0; x < (inv.getSize() - 1); x++) {
                if (inv.getItem(x) != null && inv.getItem(x).getType() != Material.AIR) {
                    total++;
                }
            }
            if (total > 44) {
                ItemStack next = ItemMaker.createItem(Material.ARROW, 1, (short) 0, green + "Next Page", Arrays.asList(gray + "" + (page + 1)));
                inv.setItem(inv.getSize() - 2, next);
            }
            inv.setItem(inv.getSize() - 1, back);
            inv.setItem(inv.getSize() - 5, ItemMaker.createItem(Material.ARROW, page, (short) 0, green + "Current Page"
                    , Arrays.asList(gray + "" + page)));
            player.openInventory(inv);
        }
    }

    public static void openSkills(Player player) {
        UUID uuid = player.getUniqueId();
        Inventory inv = Bukkit.createInventory(player, 36, aqua + "" + bold + "Skills Shop");
        if (!LuckyBlockAPI.money.containsKey(uuid)) {
            LuckyBlockAPI.money.put(uuid, 0);
        }
        if (!LuckyBlockAPI.golds.containsKey(uuid)) {
            LuckyBlockAPI.golds.put(uuid, (short) 0);
        }
        int m = LuckyBlockAPI.maxHealth.get(uuid);
        int money = LuckyBlockAPI.money.get(uuid);
        int mu = LuckyBlockAPI.multiply.get(uuid);
        int h = LuckyBlockAPI.speedmine.get(uuid);
        int g = LuckyBlockAPI.golds.get(uuid);
        List<ShopItems> bshops = LuckyBlockAPI.bitems.get(uuid);
        ItemStack back = new ItemStack(Material.COMPASS);
        ItemStack moneyitem = new ItemStack(Material.EMERALD);
        ItemStack item1 = new ItemStack(Material.APPLE);
        ItemStack item3 = new ItemStack(Material.IRON_INGOT);
        ItemStack item4 = new ItemStack(Material.WOOD_PICKAXE);
        ItemMeta backM = back.getItemMeta();
        ItemMeta moneyitemM = moneyitem.getItemMeta();
        ItemMeta item1M = item1.getItemMeta();
        ItemMeta item3M = item3.getItemMeta();
        ItemMeta item4M = item4.getItemMeta();
        if (m > 1 && m < 4) {
            item1.setType(Material.GOLDEN_APPLE);
        } else if (m > 3 && m < 7) {
            item1.setType(Material.GOLDEN_APPLE);
            item1.setDurability((short) 1);
        } else if (m > 6 && m < 10) {
            item1.setType(Material.POTION);
            item1.setDurability((short) 8261);
        } else if (m > 9 && m < 30) {
            item1.setType(Material.ENCHANTED_BOOK);
        } else if (m > 29) {
            item1.setType(Material.PAPER);
        }

        if (mu == 2) {
            item3.setType(Material.GOLD_INGOT);
        } else if (mu == 3) {
            item3.setType(Material.DIAMOND);
        } else if (mu == 4) {
            item3.setType(Material.PAPER);
        }

        if (h == 2) {
            item4.setType(Material.STONE_PICKAXE);
        } else if (h == 3) {
            item4.setType(Material.IRON_PICKAXE);
        } else if (h == 4) {
            item4.setType(Material.GOLD_PICKAXE);
        } else if (h == 5) {
            item4.setType(Material.DIAMOND_PICKAXE);
        } else if (h > 5) {
            item4.setType(Material.PAPER);
        }
        backM.setDisplayName(red + "Back");
        moneyitemM.setDisplayName(yellow + "Data");
        item1M.setDisplayName(red + "Max Health");
        item3M.setDisplayName(gold + "Multiple Coins");
        item4M.setDisplayName(green + "Haste");
        List<String> list = new ArrayList<String>();
        list.add(gray + "Click to Back");
        backM.setLore(list);
        list.clear();
        list.add(green + "Current Level " + gold + m);
        if (m == 1) {
            if (money > 349) {
                list.add(green + "$350");
            } else {
                list.add(red + "$350");
            }
        } else if (m > 1 && m < 4) {
            if (money > (((m * 120) * 3) - 1)) {
                list.add(green + "$" + (int) ((m * 120) * 3));
            } else {
                list.add(red + "$" + (int) ((m * 120) * 3));
            }
        } else if (m > 3 && m < 7) {
            if (money > (((m * 150) * m) - 1)) {
                list.add(green + "$" + ((m * 150) * m));
            } else {
                list.add(red + "$" + ((m * 150) * m));
            }
        } else if (m > 6 && m < 30) {
            if (money > (((m * 150) * m * 2) - 1)) {
                list.add(green + "$" + ((m * 150) * m * 2));
            } else {
                list.add(red + "$" + ((m * 150) * m * 2));
            }
        } else if (m > 29) {
            list.add(blue + "You Reached the maximum level for this item!");
        }
        item1M.setLore(list);
        list.clear();
        list.add(green + "Current Level " + gold + mu);
        if (mu == 1) {
            if (money > 5499) {
                list.add(green + "$9500");
            } else {
                list.add(red + "$9500");
            }
        } else if (mu == 2) {
            if (money > 44999) {
                list.add(green + "$45000");
            } else {
                list.add(red + "$45000");
            }
        } else if (mu == 3) {
            if (money > 149999) {
                list.add(green + "$150000");
            } else {
                list.add(red + "$150000");
            }
        } else if (mu > 3) {
            list.add(blue + "You Reached the maximum level for this item!");
        }
        item3M.setLore(list);
        list.clear();
        list.add(green + "Current Level " + gold + h);
        if (h == 1) {
            if (money >= 550) {
                list.add(green + "$550");
            } else {
                list.add(red + "$550");
            }
        } else if (h == 2) {
            if (money >= 1100) {
                list.add(green + "$1100");
            } else {
                list.add(red + "$1100");
            }
        } else if (h == 3) {
            if (money >= 2400) {
                list.add(green + "$2400");
            } else {
                list.add(red + "$2400");
            }
        } else if (h == 4) {
            if (money >= 6500) {
                list.add(green + "$6500");
            } else {
                list.add(red + "$6500");
            }
        } else if (h == 5) {
            if (money >= 14000) {
                list.add(green + "$14000");
            } else {
                list.add(red + "$14000");
            }
        } else if (h > 5) {
            list.add(blue + "You Reached the maximum level for this item!");
        }
        item4M.setLore(list);
        list.clear();
        list.add(gold + "Money: " + green + LuckyBlockAPI.money.get(uuid));
        list.add(gold + "Gold: " + green + LuckyBlockAPI.golds.get(uuid));
        moneyitemM.setLore(list);
        list.clear();
        back.setItemMeta(backM);
        moneyitem.setItemMeta(moneyitemM);
        item1.setItemMeta(item1M);
        item3.setItemMeta(item3M);
        item4.setItemMeta(item4M);
        inv.setItem(inv.getSize() - 1, back);
        inv.setItem(inv.getSize() - 2, moneyitem);
        inv.addItem(item1);
        inv.addItem(item3);
        inv.addItem(item4);
        for (int x = inv.firstEmpty(); x < inv.getSize(); x++) {
            ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            ItemMeta glassM = glass.getItemMeta();
            glassM.setDisplayName(darkgray + "" + bold + "Locked");
            glass.setItemMeta(glassM);
            if (inv.getItem(x) == null) {
                inv.setItem(x, glass);
            }
        }
        player.openInventory(inv);
        Game.AnimateInv(player, inv, "skills");
    }

    public static void openWeapons(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, aqua + "" + bold + "Weapons Shop");
        UUID uuid = player.getUniqueId();
        int money = LuckyBlockAPI.money.get(uuid);
        List<ShopItems> bitems = LuckyBlockAPI.bitems.get(uuid);
        ItemStack[] stacks = new ItemStack[32];
        ItemStack back = new ItemStack(Material.COMPASS);
        ItemStack moneyitem = new ItemStack(Material.EMERALD);
        stacks[0] = new ItemStack(Material.BOW);
        stacks[1] = new ItemStack(Material.DIAMOND_SWORD);
        Map<Enchantment, Integer> e = new HashMap<Enchantment, Integer>();
        e.put(LuckyBlock.lightning, 10);
        stacks[2] = LuckyBlockAPI.createItemStack(Material.IRON_AXE, 1, (short) 0, aqua + "" + bold + "Thor's Axe", null, e);
        stacks[3] = LuckyBlockAPI.createItemStack(Material.LOG, 16, (short) 0, gold + "" + bold + "Log");
        e.clear();
        e.put(LuckyBlock.glow, 1);
        stacks[4] = LuckyBlockAPI.createItemStack(Material.NAME_TAG, 1, (short) 0, yellow + "Advanced Lucky Block Tool", null, e);
        e.clear();
        e.put(Enchantment.DIG_SPEED, 1);
        e.put(Enchantment.DURABILITY, 2);
        stacks[5] = LuckyBlockAPI.createItemStack(Material.DIAMOND_AXE, 1, (short) 0, yellow + "Lucky Axe", null, e);
        e.clear();
        e.put(Enchantment.LOOT_BONUS_BLOCKS, 1);
        e.put(Enchantment.DURABILITY, 2);
        stacks[6] = LuckyBlockAPI.createItemStack(Material.DIAMOND_PICKAXE, 1, (short) 0, yellow + "Lucky Pickaxe", null, e);
        e.clear();
        e.put(Enchantment.DIG_SPEED, 1);
        e.put(Enchantment.DURABILITY, 2);
        stacks[7] = LuckyBlockAPI.createItemStack(Material.DIAMOND_SPADE, 1, (short) 0, yellow + "Lucky Shovel", null, e);
        e.clear();
        ItemMeta backM = back.getItemMeta();
        ItemMeta moneyitemM = moneyitem.getItemMeta();
        ItemMeta itemM = stacks[0].getItemMeta();
        ItemMeta item1M = stacks[1].getItemMeta();
        backM.setDisplayName(red + "Back");
        moneyitemM.setDisplayName(yellow + "Data");
        itemM.setDisplayName(yellow + "" + bold + "Lucky Bow");
        item1M.setDisplayName(yellow + "" + bold + "Lucky Sword");
        itemM.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        item1M.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        List<String> list = new ArrayList<String>();
        list.add(gray + "Click to back");
        backM.setLore(list);
        list.clear();
        list.add(gray + "Find " + itemM.getDisplayName() + gray + " in chests.");
        if (!bitems.contains(ShopItems.LUCKY_BOW)) {
            int cost = 2000;
            ChatColor c = null;
            if (money >= cost) {
                c = ChatColor.GREEN;
            } else {
                c = ChatColor.RED;
            }
            list.add(c + "$" + cost);
        } else {
            list.add(blue + "Bought!");
        }
        itemM.setLore(list);
        list.clear();
        list.add(gray + "Find " + item1M.getDisplayName() + gray + " in chests.");
        if (!bitems.contains(ShopItems.LUCKY_SWORD)) {
            int cost = 1900;
            ChatColor c = null;
            if (money >= cost) {
                c = ChatColor.GREEN;
            } else {
                c = ChatColor.RED;
            }
            list.add(c + "$" + cost);
        } else {
            list.add(blue + "Bought!");
        }
        item1M.setLore(list);
        list.clear();
        list.add(gray + "Lightning X");
        list.add(gray + "Find " + stacks[2].getItemMeta().getDisplayName() + gray + " in chests.");
        if (!bitems.contains(ShopItems.THOR_AXE)) {
            int cost = 2500;
            ChatColor c = null;
            if (money >= cost) {
                c = ChatColor.GREEN;
            } else {
                c = ChatColor.RED;
            }
            list.add(c + "$" + cost);
        } else {
            list.add(blue + "Bought!");
        }
        ItemMeta item2M = stacks[3].getItemMeta();
        item2M.setLore(list);
        list.clear();
        item2M.setDisplayName(aqua + "" + bold + "Thor's Axe");
        item2M.addEnchant(LuckyBlock.lightning, 10, true);
        list.add(gray + "Start the game with " + gold + "x16 Log");
        if (!bitems.contains(ShopItems.BLOCKS)) {
            int cost = 900;
            ChatColor c = null;
            if (money >= cost) {
                c = green;
            } else {
                c = red;
            }
            list.add(c + "$" + cost);
        } else {
            list.add(blue + "Bought!");
        }
        ItemMeta item3M = stacks[3].getItemMeta();
        item3M.setLore(list);
        list.clear();
        LuckyBlockAPI.addLore(stacks[4], gray + "Find " + stacks[4].getItemMeta().getDisplayName() + gray + " in chests.");
        if (!bitems.contains(ShopItems.ADVANCED_LUCKY_BLOCK_TOOL)) {
            int cost = 3000;
            ChatColor c = null;
            if (money >= cost) {
                c = green;
            } else {
                c = red;
            }
            LuckyBlockAPI.addLore(stacks[4], c + "$" + cost);
        } else {
            LuckyBlockAPI.addLore(stacks[4], blue + "Bought!");
        }
        LuckyBlockAPI.addLore(stacks[5], gray + "Find " + stacks[5].getItemMeta().getDisplayName() + gray + " in chests.");
        if (!bitems.contains(ShopItems.LUCKY_AXE)) {
            int cost = 1800;
            ChatColor c = null;
            if (money >= cost) {
                c = green;
            } else {
                c = red;
            }
            LuckyBlockAPI.addLore(stacks[5], c + "$" + cost);
        } else {
            LuckyBlockAPI.addLore(stacks[5], blue + "Bought!");
        }

        LuckyBlockAPI.addLore(stacks[6], gray + "Find " + stacks[6].getItemMeta().getDisplayName() + gray + " in chests.");
        if (!bitems.contains(ShopItems.LUCKY_PICKAXE)) {
            int cost = 1750;
            ChatColor c = null;
            if (money >= cost) {
                c = green;
            } else {
                c = red;
            }
            LuckyBlockAPI.addLore(stacks[6], c + "$" + cost);
        } else {
            LuckyBlockAPI.addLore(stacks[6], blue + "Bought!");
        }

        LuckyBlockAPI.addLore(stacks[7], gray + "Find " + stacks[7].getItemMeta().getDisplayName() + gray + " in chests.");
        if (!bitems.contains(ShopItems.LUCKY_SHOVEL)) {
            int cost = 1700;
            ChatColor c = null;
            if (money >= cost) {
                c = green;
            } else {
                c = red;
            }
            LuckyBlockAPI.addLore(stacks[7], c + "$" + cost);
        } else {
            LuckyBlockAPI.addLore(stacks[7], blue + "Bought!");
        }
        back.setItemMeta(backM);
        list.add(gold + "Money: " + green + LuckyBlockAPI.money.get(uuid));
        list.add(gold + "Gold: " + green + LuckyBlockAPI.golds.get(uuid));
        moneyitemM.setLore(list);
        list.clear();
        moneyitem.setItemMeta(moneyitemM);
        stacks[0].setItemMeta(itemM);
        stacks[1].setItemMeta(item1M);
        stacks[2].setItemMeta(item2M);
        stacks[3].setItemMeta(item3M);
        inv.setItem(inv.getSize() - 1, back);
        inv.setItem(inv.getSize() - 2, moneyitem);
        for (int x = 0; x < stacks.length; x++) {
            if (stacks[x] != null) {
                inv.addItem(stacks[x]);
            }
        }
        for (int x = inv.firstEmpty(); x < inv.getSize(); x++) {
            ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            ItemMeta glassM = glass.getItemMeta();
            glassM.setDisplayName(darkgray + "" + bold + "Locked");
            glass.setItemMeta(glassM);
            if (inv.getItem(x) == null) {
                inv.setItem(x, glass);
            }
        }
        player.openInventory(inv);
        Game.AnimateInv(player, inv, "weapons");
    }

    public static void openPlus(Player player) {
        Inventory inv = Bukkit.createInventory(player, 36, aqua + "" + bold + "Shop+");
        UUID uuid = player.getUniqueId();
        List<ShopItems> bitems = LuckyBlockAPI.bitems.get(uuid);
        int money = LuckyBlockAPI.money.get(uuid);
        ItemStack back = new ItemStack(Material.COMPASS);
        ItemStack moneyitem = new ItemStack(Material.EMERALD);
        ItemStack item1 = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack item2 = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack item3 = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack item4 = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack sell = new ItemStack(Material.GOLD_NUGGET);
        if (bitems.contains(ShopItems.SATURATION_SKILL)) {
            item1.setType(Material.PAPER);
        }
        if (bitems.contains(ShopItems.WATER_BREATHING_SKILL)) {
            item2.setType(Material.PAPER);
        }
        if (bitems.contains(ShopItems.HIGH_JUMP_SKILL)) {
            item3.setType(Material.PAPER);
        }
        if (bitems.contains(ShopItems.FIRE_RESISTANCE_SKILL)) {
            item4.setType(Material.PAPER);
        }
        ItemMeta backM = back.getItemMeta();
        ItemMeta moneyitemM = moneyitem.getItemMeta();
        ItemMeta item1M = item1.getItemMeta();
        ItemMeta item2M = item2.getItemMeta();
        ItemMeta item3M = item3.getItemMeta();
        ItemMeta item4M = item4.getItemMeta();
        ItemMeta sellM = sell.getItemMeta();
        backM.setDisplayName(red + "Back");
        moneyitemM.setDisplayName(yellow + "Data");
        item1M.setDisplayName(yellow + "" + bold + "Saturation Skill");
        item2M.setDisplayName(darkaqua + "Water Breathing Skill");
        item3M.setDisplayName(darkgreen + "High Jump Skill");
        item4M.setDisplayName(darkgray + "" + bold + "Fire Resistance Skill");
        sellM.setDisplayName(yellow + "" + bold + "Sell");
        List<String> list = new ArrayList<String>();
        list.add(gray + "Click to Back");
        backM.setLore(list);
        list.clear();
        if (!bitems.contains(ShopItems.SATURATION_SKILL)) {
            if (money >= 1850) {
                list.add(green + "$1850");
                list.add(gray + "Click to Unlock");
            } else {
                list.add(red + "$1850");
                list.add(red + "You don't have enough money!");
            }
        } else {
            list.add(blue + "Bought!");
        }
        item1M.setLore(list);
        list.clear();
        if (!bitems.contains(ShopItems.WATER_BREATHING_SKILL)) {
            if (money >= 1800) {
                list.add(green + "$1800");
                list.add(gray + "Click to Unlock");
            } else {
                list.add(red + "$1800");
                list.add(red + "You don't have enough money!");
            }
        } else {
            list.add(blue + "Bought!");
        }
        item2M.setLore(list);
        list.clear();
        if (!bitems.contains(ShopItems.HIGH_JUMP_SKILL)) {
            if (money >= 1900) {
                list.add(green + "$1900");
                list.add(gray + "Click to Unlock");
            } else {
                list.add(red + "$1900");
                list.add(red + "You don't have enough money!");
            }
        } else {
            list.add(blue + "Bought!");
        }
        item3M.setLore(list);
        list.clear();
        if (!bitems.contains(ShopItems.FIRE_RESISTANCE_SKILL)) {
            if (money >= 2100) {
                list.add(green + "$2100");
                list.add(gray + "Click to Unlock");
            } else {
                list.add(red + "$2100");
                list.add(red + "You don't have enough money!");
            }
        } else {
            list.add(blue + "Bought!");
        }
        item4M.setLore(list);
        list.clear();
        int total = 0;
        if (bitems.contains(ShopItems.SATURATION_SKILL)) {
            total += 900;
        }
        if (bitems.contains(ShopItems.WATER_BREATHING_SKILL)) {
            total += 850;
        }
        if (bitems.contains(ShopItems.HIGH_JUMP_SKILL)) {
            total += 950;
        }
        if (bitems.contains(ShopItems.FIRE_RESISTANCE_SKILL)) {
            total += 1150;
        }
        list.add(gray + "Sell all Skills");
        list.add(green + "Total: " + gold + total);
        sellM.setLore(list);
        list.clear();
        back.setItemMeta(backM);
        list.add(gold + "Money: " + green + LuckyBlockAPI.money.get(uuid));
        list.add(gold + "Gold: " + green + LuckyBlockAPI.golds.get(uuid));
        moneyitemM.setLore(list);
        list.clear();
        moneyitem.setItemMeta(moneyitemM);
        item1.setItemMeta(item1M);
        item2.setItemMeta(item2M);
        item3.setItemMeta(item3M);
        item4.setItemMeta(item4M);
        sell.setItemMeta(sellM);
        inv.setItem(inv.getSize() - 1, back);
        inv.setItem(inv.getSize() - 2, moneyitem);
        inv.addItem(item1);
        inv.addItem(item2);
        inv.addItem(item3);
        inv.addItem(item4);
        inv.setItem(inv.getSize() - 3, sell);
        for (int x = inv.firstEmpty(); x < inv.getSize(); x++) {
            ItemStack glass = LuckyBlockAPI.createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, darkgray + "" + bold + "Locked");
            if (inv.getItem(x) == null) {
                inv.setItem(x, glass);
            }
        }
        player.openInventory(inv);
        Game.AnimateInv(player, inv, "plus");
    }

    public static void openHats(Player player) {
        Inventory inv = Bukkit.createInventory(player, 45, aqua + "" + bold + "Hats Shop");
        UUID uuid = player.getUniqueId();
        int money = LuckyBlockAPI.money.get(uuid);
        short golds = LuckyBlockAPI.golds.get(uuid);
        int level = LuckyBlockAPI.playerlevel.get(uuid)[0];
        ItemStack[] items = new ItemStack[32];
        List<String> list = new ArrayList<String>();
        list.add(gray + "This hat is cosmetic!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.GLASS_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.GLASS_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "$" + Hat.GLASS_HAT.getCost());
            if (level >= Hat.GLASS_HAT.getLevel()) {
                if (money >= Hat.GLASS_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough money!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.GLASS_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[0] = ItemMaker.createItem(Material.GLASS, 1, (short) 0, blue + "" + bold + "GLASS HAT", list);
        list.clear();
        list.add(gray + "This hat is cosmetic!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.COLORFUL_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.COLORFUL_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "" + Hat.COLORFUL_HAT.getCost() + gold + "g");
            if (level >= Hat.COLORFUL_HAT.getLevel()) {
                if (golds >= Hat.COLORFUL_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough gold!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.COLORFUL_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[1] = ItemMaker.createItem(Material.WOOL, 1, (short) 0, blue + "" + bold + "COLORFUL HAT", list);
        list.clear();
        list.add(gray + "Drops diamond on kill players!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.DIAMOND_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.DIAMOND_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "$" + Hat.DIAMOND_HAT.getCost());
            if (level >= Hat.DIAMOND_HAT.getLevel()) {
                if (money >= Hat.DIAMOND_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough money!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.DIAMOND_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[2] = ItemMaker.createItem(Material.DIAMOND_BLOCK, 1, (short) 0, blue + "" + bold + "DIAMOND HAT", list);
        list.clear();
        list.add(gray + "15% Chance to reflect arrow when hit!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.LEATHER_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.LEATHER_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "" + Hat.LEATHER_HAT.getCost() + gold + "g");
            if (level >= Hat.LEATHER_HAT.getLevel()) {
                if (golds >= Hat.LEATHER_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough gold!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.LEATHER_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[3] = ItemMaker.createItem(Material.LEATHER_HELMET, 1, (short) 0, blue + "" + bold + "ARCHER HAT", list);
        list.clear();
        list.add(gray + "Gives strength and slowness effects!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.HARD_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.HARD_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "" + Hat.HARD_HAT.getCost() + gold + "g");
            if (level >= Hat.HARD_HAT.getLevel()) {
                if (golds >= Hat.HARD_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough gold!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.HARD_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[4] = ItemMaker.createItem(Material.OBSIDIAN, 1, (short) 0, blue + "" + bold + "HARD HAT", list);
        list.clear();
        list.add(gray + "This hat is cosmetic!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.CAGE_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.CAGE_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "" + Hat.CAGE_HAT.getCost() + gold + "g");
            if (level >= Hat.CAGE_HAT.getLevel()) {
                if (golds >= Hat.CAGE_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough gold!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.CAGE_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[5] = ItemMaker.createItem(Material.MOB_SPAWNER, 1, (short) 0, blue + "" + bold + "CAGE HAT", list);
        list.clear();
        list.add(gray + "10% Chance to spawn tnt on death!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.TNT_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.TNT_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "$" + Hat.TNT_HAT.getCost());
            if (level >= Hat.TNT_HAT.getLevel()) {
                if (money >= Hat.TNT_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough money!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.TNT_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[6] = ItemMaker.createItem(Material.TNT, 1, (short) 0, blue + "" + bold + "TNT HAT", list);
        list.clear();
        list.add(gray + "5% to get 2 extra gold ingot when mining gold ore!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.NOTCH_HAT)) {
            if (Hat.getSelected().get(uuid) != Hat.NOTCH_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(gray + "" + Hat.NOTCH_HAT.getCost() + gold + "g");
            if (level >= Hat.NOTCH_HAT.getLevel()) {
                if (golds >= Hat.NOTCH_HAT.getCost()) {
                    list.add(green + "Click to unlock");
                } else {
                    list.add(red + "You don't have enough gold!");
                }
            } else {
                list.add(gray + "Required level: " + Hat.NOTCH_HAT.getLevel());
                list.add(red + "You don't have the required levels!");
            }
        }
        items[7] = ItemMaker.createItem(Material.GOLD_BLOCK, 1, (short) 0, blue + "" + bold + "NOTCH HAT", list);
        list.clear();
        list.add(gray + "Gives Speed effect!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.PUMPKIN_HAT) || Hat.getSelected().get(uuid) == Hat.PUMPKIN_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.PUMPKIN_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        items[8] = ItemMaker.createItem(Material.PUMPKIN, 1, (short) 0, blue + "" + bold + "PUMPKIN HAT", list);
        list.clear();
        list.add(gray + "Summons snow man when the game starts!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.SNOW_HAT) || Hat.getSelected().get(uuid) == Hat.SNOW_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.SNOW_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        items[9] = ItemMaker.createItem(Material.SNOW_BLOCK, 1, (short) 0, blue + "" + bold + "SNOW HAT", list);
        list.clear();
        list.add(gray + "Opens crafting table on click!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.CRAFTING_HAT) || Hat.getSelected().get(uuid) == Hat.CRAFTING_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.CRAFTING_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        items[10] = ItemMaker.createItem(Hat.CRAFTING_HAT.getType(), 1, (short) 0, blue + "" + bold + "CRAFTING HAT", list);
        list.clear();
        list.add(gray + "Opens enchanting table on click!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.ENCHANTING_HAT) || Hat.getSelected().get(uuid) == Hat.ENCHANTING_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.ENCHANTING_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        items[11] = ItemMaker.createItem(Hat.ENCHANTING_HAT.getType(), 1, (short) 0, blue + "" + bold + "ENCHANTING HAT", list);
        list.clear();
        list.add(gray + "This hat is cosmetic!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.BRICK_HAT) || Hat.getSelected().get(uuid) == Hat.BRICK_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.BRICK_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        items[12] = ItemMaker.createItem(Hat.BRICK_HAT.getType(), 1, (short) 0, blue + "" + bold + "BRICK HAT", list);
        list.clear();
        list.add(gray + "Summons enderman when the game start!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.ENDERMAN_HAT) || Hat.getSelected().get(uuid) == Hat.ENDERMAN_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.ENDERMAN_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        ItemStack it = ItemMaker.createItem(Hat.ENDERMAN_HAT.getType(), 1, (short) 3, blue + "" + bold + "ENDERMAN HAT", list);
        if (LuckyBlock.bukkitVersion[1] > 7) {
            it = ItemMaker.createSkull(it, "9f06dd5c-e04b-4cc1-b4e0-fc577f0562b0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5l" +
                    "Y3JhZnQubmV0L3RleHR1cmUvN2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=");
        } else {
            SkullMeta sk = (SkullMeta) it.getItemMeta();
            sk.setOwner("MHF_Enderman");
            it.setItemMeta(sk);
        }
        items[13] = it;
        list.clear();
        if (Hat.getHats().get(uuid).contains(Hat.ENDER_HAT)) {
            list.add(gray + "25% chance to teleport when damaged by entity!");
        } else {
            list.add(gray + "UNKNOWN!");
        }
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.ENDER_HAT) || Hat.getSelected().get(uuid) == Hat.ENDER_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.ENDER_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        items[14] = ItemMaker.createItem(Hat.ENDER_HAT.getType(), 1, (short) 0, blue + "" + bold + "ENDER HAT", list);
        list.clear();

        list.add(gray + "Secret Hat!");
        list.add("");
        if (Hat.getHats().get(uuid).contains(Hat.HEROBRINE_HAT) || Hat.getSelected().get(uuid) == Hat.HEROBRINE_HAT) {
            if (Hat.getSelected().get(uuid) != Hat.HEROBRINE_HAT) {
                list.add(green + "Click to select");
            } else {
                list.add(blue + "Selected");
            }
        } else {
            list.add(red + "Locked!");
        }
        ItemStack ht = ItemMaker.createItem(Hat.HEROBRINE_HAT.getType(), 1, (short) 3, white + "" + bold + "HEROBRINE HAT", list);
        if (LuckyBlock.bukkitVersion[1] > 7) {
            ht = ItemMaker.createSkull(ht, "64cdab58-dc2a-4023-8552-730c825a6c5a",
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThiN2NhM2M3ZDMxNGE2MWFiZ"
                            + "WQ4ZmMxOGQ3OTdmYzMwYjZlZmM4NDQ1NDI1YzRlMjUwOTk3ZTUyZTZjYiJ9fX0=");
        } else {
            SkullMeta sk = (SkullMeta) it.getItemMeta();
            sk.setOwner("Herobrine");
            ht.setItemMeta(sk);
        }
        ItemMeta itM = ht.getItemMeta();
        itM.setLore(list);
        itM.setDisplayName(white + "" + bold + "HEROBRINE HAT");
        ht.setItemMeta(itM);
        items[15] = ht;
        list.clear();

        int t = 16;
        for (Hat h : Hat.values()) {
            if (h.isRequirelevel()) {
                if (level >= h.getLevel()) {
                    ItemStack i = null;
                    if (h == Hat.CHEST_HAT) {
                        list.add(gray + "Get extra inventory (click the item in-game to open)!");
                        list.add("");
                        if (Hat.getHats().get(uuid).contains(Hat.CHEST_HAT)) {
                            if (Hat.getSelected().get(uuid) != Hat.CHEST_HAT) {
                                list.add(green + "Click to select");
                            } else {
                                list.add(blue + "Selected");
                            }
                        } else {
                            list.add(gray + "" + Hat.CHEST_HAT.getCost() + gold + "g");
                            if (level >= Hat.CHEST_HAT.getLevel()) {
                                if (golds >= Hat.CHEST_HAT.getCost()) {
                                    list.add(green + "Click to unlock");
                                } else {
                                    list.add(red + "You don't have enough gold!");
                                }
                            } else {
                                list.add(gray + "Required level: " + Hat.CHEST_HAT.getLevel());
                                list.add(red + "You don't have the required levels!");
                            }
                        }
                        i = ItemMaker.createItem(Material.CHEST, 1, (short) 0, blue + "" + bold + "CHEST HAT", list);
                        list.clear();
                    }
                    if (h == Hat.END_HAT) {
                        list.add(gray + "This hat is cosmetic!");
                        list.add("");
                        if (Hat.getHats().get(uuid).contains(Hat.END_HAT)) {
                            if (Hat.getSelected().get(uuid) != Hat.END_HAT) {
                                list.add(green + "Click to select");
                            } else {
                                list.add(blue + "Selected");
                            }
                        } else {
                            list.add(gray + "$" + Hat.END_HAT.getCost());
                            if (level >= Hat.END_HAT.getLevel()) {
                                if (money >= Hat.END_HAT.getCost()) {
                                    list.add(green + "Click to unlock");
                                } else {
                                    list.add(red + "You don't have enough money!");
                                }
                            } else {
                                list.add(gray + "Required level: " + Hat.END_HAT.getLevel());
                                list.add(red + "You don't have the required levels!");
                            }
                        }
                        i = ItemMaker.createItem(Material.ENDER_STONE, 1, (short) 0, blue + "" + bold + "ENDSTONE HAT", list);
                        list.clear();
                    }
                    if (h == Hat.ICE_HAT) {
                        list.add(gray + "Gives ice blocks o mine them!");
                        list.add("");
                        if (Hat.getHats().get(uuid).contains(h)) {
                            if (Hat.getSelected().get(uuid) != h) {
                                list.add(green + "Click to select");
                            } else {
                                list.add(blue + "Selected");
                            }
                        } else {
                            list.add(gray + "$" + h.getCost());
                            if (level >= h.getLevel()) {
                                if (money >= h.getCost()) {
                                    list.add(green + "Click to unlock");
                                } else {
                                    list.add(red + "You don't have enough money!");
                                }
                            } else {
                                list.add(gray + "Required level: " + h.getLevel());
                                list.add(red + "You don't have the required levels!");
                            }
                        }
                        i = ItemMaker.createItem(h.getType(), 1, (short) 0, blue + "" + bold + "ICE HAT", list);
                        list.clear();
                    }
                    if (h == Hat.MELON_HAT) {
                        list.add(gray + "This hat is cosmetic!");
                        list.add("");
                        if (Hat.getHats().get(uuid).contains(h)) {
                            if (Hat.getSelected().get(uuid) != h) {
                                list.add(green + "Click to select");
                            } else {
                                list.add(blue + "Selected");
                            }
                        } else {
                            list.add(gray + "$" + h.getCost());
                            if (level >= h.getLevel()) {
                                if (money >= h.getCost()) {
                                    list.add(green + "Click to unlock");
                                } else {
                                    list.add(red + "You don't have enough money!");
                                }
                            } else {
                                list.add(gray + "Required level: " + h.getLevel());
                                list.add(red + "You don't have the required levels!");
                            }
                        }
                        i = ItemMaker.createItem(h.getType(), 1, (short) 0, blue + "" + bold + "MELON HAT", list);
                        list.clear();
                    }
                    if (h == Hat.HAY_HAT) {
                        list.add(gray + "Right click on any crop to grow it!");
                        list.add("");
                        if (Hat.getHats().get(uuid).contains(h)) {
                            if (Hat.getSelected().get(uuid) != h) {
                                list.add(green + "Click to select");
                            } else {
                                list.add(blue + "Selected");
                            }
                        } else {
                            list.add(gray + "" + h.getCost() + gold + "g");
                            if (level >= h.getLevel()) {
                                if (golds >= h.getCost()) {
                                    list.add(green + "Click to unlock");
                                } else {
                                    list.add(red + "You don't have enough gold!");
                                }
                            } else {
                                list.add(gray + "Required level: " + h.getLevel());
                                list.add(red + "You don't have the required levels!");
                            }
                        }
                        i = ItemMaker.createItem(h.getType(), 1, (short) 0, blue + "" + bold + "HAY HAT", list);
                        list.clear();
                    }
                    if (h == Hat.NOFALL_HAT) {
                        list.add(gray + "Reduces fall damage by 50%!");
                        list.add("");
                        if (Hat.getHats().get(uuid).contains(h)) {
                            if (Hat.getSelected().get(uuid) != h) {
                                list.add(green + "Click to select");
                            } else {
                                list.add(blue + "Selected");
                            }
                        } else {
                            list.add(gray + "" + h.getCost() + gold + "g");
                            if (level >= h.getLevel()) {
                                if (golds >= h.getCost()) {
                                    list.add(green + "Click to unlock");
                                } else {
                                    list.add(red + "You don't have enough gold!");
                                }
                            } else {
                                list.add(gray + "Required level: " + h.getLevel());
                                list.add(red + "You don't have the required levels!");
                            }
                        }
                        i = ItemMaker.createItem(h.getType(), 1, (short) 0, blue + "" + bold + "NOFALL HAT", list);
                        list.clear();
                    }

                    if (i != null) {
                        items[t] = i;
                    }
                    t++;
                }
            }
        }

        for (int x = 0; x < items.length; x++) {
            if (items[x] != null) {
                inv.addItem(items[x]);
            }
        }
        ItemStack back = ItemMaker.createItem(Material.COMPASS, 1, (short) 0, red + "Back", Arrays.asList(gray + "Click to back"));
        ItemStack moneyitem = ItemMaker.createItem(Material.EMERALD, 1, (short) 0, yellow + "Data", Arrays.asList(gold + "Money: " +
                green + LuckyBlockAPI.money.get(uuid), gold + "Gold: " + green + LuckyBlockAPI.golds.get(uuid)));
        ItemStack non = ItemMaker.createItem(Material.ENDER_PEARL, 1, (short) 0, blue + "" + bold + "NONE", Arrays.asList("", gray + "Click to reset"));
        inv.setItem(inv.getSize() - 1, back);
        inv.setItem(inv.getSize() - 2, moneyitem);
        inv.setItem(inv.getSize() - 9, non);
        for (int x = 0; x < inv.getSize(); x++) {
            if (inv.getItem(x) == null) {
                ItemStack glass = LuckyBlockAPI.createItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, darkgray + "" + bold + "Locked");
                inv.setItem(x, glass);
            }
        }
        Game.AnimateInv(player, inv, "hats");
        player.openInventory(inv);
    }

    public static void openParticles(Player player) {
        UUID uuid = player.getUniqueId();
        Inventory inv = Bukkit.createInventory(player, 36, red + "Particles Pack");
        ItemStack[] items = new ItemStack[64];
        String[] stats = new String[64];
        int i = 0;
        for (Particle particles : Particle.values()) {
            if (Particle.selected.get(uuid) == particles) {
                stats[i] = blue + "Selected!";
            }
            i++;
        }
        i = 0;
        for (Particle particles : Particle.values()) {
            if (Particle.unlocked.get(uuid).contains(particles)) {
                if (stats[i] == null) {
                    stats[i] = green + "Click to select";
                }
            }
            i++;
        }
        i = 0;
        for (Particle particles : Particle.values()) {
            if (!(Particle.unlocked.get(uuid).contains(particles) || Particle.selected.get(uuid) == particles)) {
                stats[i] = red + "Locked!";
            }
            i++;
        }
        items[0] = ItemMaker.createItem(Material.REDSTONE, 1, 0, red + "Redstone Particle", Arrays.asList("", stats[1]));
        items[1] = ItemMaker.createItem(Material.BLAZE_POWDER, 1, 0, gold + "Flame Particle", Arrays.asList("", stats[2]));
        items[2] = ItemMaker.createItem(Material.LAVA_BUCKET, 1, 0, gold + "Lava Particle", Arrays.asList("", stats[3]));
        items[3] = ItemMaker.createItem(Material.REDSTONE_BLOCK, 1, 0, red + "Heart Particle", Arrays.asList("", stats[4]));
        items[4] = ItemMaker.createItem(Material.OBSIDIAN, 1, 0, darkpurple + "Portal Particle", Arrays.asList("", stats[5]));
        items[5] = ItemMaker.createItem(Material.SLIME_BALL, 1, 0, green + "Slime Particle", Arrays.asList("", stats[6]));
        items[6] = ItemMaker.createItem(Material.FIREWORK_CHARGE, 1, 0, gray + "Smoke Particle", Arrays.asList("", stats[7]));
        items[7] = ItemMaker.createItem(Material.FIREWORK_CHARGE, 1, 0, darkgray + "Large Smoke Particle", Arrays.asList("", stats[8]));
        items[8] = ItemMaker.createItem(Material.DIAMOND_SWORD, 1, 0, darkgreen + "Crit Particle", Arrays.asList("", stats[9]));
        items[9] = ItemMaker.createItem(Material.NOTE_BLOCK, 1, 0, darkred + "Note Particle", Arrays.asList("", stats[10]));
        items[10] = ItemMaker.createItem(Material.SNOW_BALL, 1, 0, white + "" + bold + "Snowball Particle", Arrays.asList("", stats[11]));
        items[11] = ItemMaker.createItem(Material.POTION, 1, 0, aqua + "Spell Particle", Arrays.asList("", stats[12]));
        items[12] = ItemMaker.createItem(Material.MONSTER_EGG, 1, 120, darkgreen + "" + bold + "Angry Villager Particle", Arrays.asList("", stats[13]));
        for (ItemStack item : items) {
            if (item != null) {
                inv.addItem(item);
            }
        }
        inv.setItem(inv.getSize() - 1, ItemMaker.createItem(Material.COMPASS, 1, 0, red + "Back", Arrays.asList(gray + "Click to back")));
        inv.setItem(inv.getSize() - 9, ItemMaker.createItem(Material.ENDER_PEARL, 1, 0, blue + "" + bold + "Reset Particle",
                Arrays.asList(gray + "Click to reset")));
        player.openInventory(inv);
    }

    public static void openMobAbility(Player player) {
        Inventory inv = Bukkit.createInventory(player, 18, yellow + "List Mobs");
        ItemStack[] items = new ItemStack[32];
        items[0] = ItemMaker.createItem(Material.MONSTER_EGG, 1, 54, blue + "Zombie Abilities");
        for (ItemStack item : items) {
            if (item != null) {
                inv.addItem(item);
            }
        }
        inv.setItem(inv.getSize() - 1, ItemMaker.createItem(Material.COMPASS, 1, 0, red + "Back", Arrays.asList(gray + "Click to back")));
        player.openInventory(inv);
    }

    public static void openLBGui(Player player, boolean msg) {
        if (!player.hasPermission("lb.gui")) {
            player.sendMessage(LuckyBlockCommand.getMessage("NoPermission1"));
            return;
        }
        int size = 18;
        int g = 0;
        for (Types type : Types.getTypes()) {
            g++;
            if (type.getMaxLuck() > 0) {
                g++;
            }
            if (type.getMinLuck() < 0) {
                g++;
            }
        }
        if (g > 8 && g < 18) {
            size = 18;
        } else if (g > 17 && g < 27) {
            size = 27;
        } else if (g > 26 && g < 36) {
            size = 36;
        } else if (g > 35 && g < 45) {
            size = 45;
        } else if (g > 44 && g < 54) {
            size = 54;
        }
        Inventory inv = Bukkit.createInventory(player, size, yellow + "[Lucky Blocks]");
        for (Types type : Types.getTypes()) {
            inv.addItem(type.toItemStack(0));
            if (type.getMaxLuck() > 0) {
                inv.addItem(type.toItemStack(type.getMaxLuck()));
            }
            if (type.getMinLuck() < 0) {
                inv.addItem(type.toItemStack(type.getMinLuck()));
            }
        }
        inv.setItem(inv.getSize() - 1, ItemMaker.createItem(Material.COMPASS, 1, 0, red + "Close", Arrays.asList(gray + "Click to close")));
        player.openInventory(inv);
        if (msg) {
            player.sendMessage(LuckyBlockCommand.getMessage("OpenGui.Success"));
        }
    }

    @EventHandler
    private void onClickItemOptions(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            UUID uuid = player.getUniqueId();
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                ItemStack item = event.getCurrentItem();
                if (inv.getTitle() != null) {
                    String title = inv.getTitle();
                    if (title.equalsIgnoreCase(green + "Options") || title.equalsIgnoreCase(darkgreen + "Choose Spawn") || title.equalsIgnoreCase(red + "Particles Pack")
                            || title.equalsIgnoreCase(green + "Players in this game") || title.equalsIgnoreCase(yellow + "List Mobs")) {
                        event.setCancelled(true);
                        if (War.containPlayer(uuid)) {
                            War war = War.getGame(uuid);
                            if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                                String name = item.getItemMeta().getDisplayName();
                                if (item.getType() == Material.GRASS) {
                                    if (name.equalsIgnoreCase(darkgreen + "Spawns")) {
                                        if (War.getGame(uuid) != null) {
                                            if (War.getGame(uuid).isAllowGates() == false) {
                                                openSpawns(player, 1);
                                            }
                                        }
                                    }
                                }
                                if (item.getType() == Material.COMPASS) {
                                    if (name.equalsIgnoreCase(red + "" + bold + "Close")) {
                                        player.closeInventory();
                                    } else if (name.equalsIgnoreCase(red + "Back")) {
                                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                        openOptions(player);
                                    }
                                }
                                if (item.getType() == Material.SAND) {
                                    if (name.startsWith(yellow + "Spawn")) {
                                        String[] d = ChatColor.stripColor(name).split("Spawn ");
                                        if (d.length == 2) {
                                            try {
                                                int number = Integer.parseInt(d[1]);
                                                if (war.getPs().containsValue(number - 1)) {
                                                    player.sendMessage(red + "This Spawn is already taken!");
                                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                                } else {
                                                    war.SetPlayerSpawn(player.getName(), number - 1);
                                                    player.sendMessage(green + "You choosed a spawn!");
                                                    player.playSound(player.getLocation(), Sound.CREEPER_DEATH, 1, 2);
                                                    openSpawns(player, getOpenedPage(player));
                                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                                        UUID u = p.getUniqueId();
                                                        if (u != uuid) {
                                                            if (War.containPlayer(u)) {
                                                                if (War.getGame(uuid).getId() == War.getGame(u).getId()) {
                                                                    if (p.getOpenInventory().getTitle() != null) {
                                                                        if (p.getOpenInventory().getTitle().equalsIgnoreCase(darkgreen + "Choose Spawn")) {
                                                                            openSpawns(p, 1);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (NumberFormatException ex) {
                                                //
                                            }
                                        }
                                    }
                                }
                                if (item.getType() == Material.ARROW) {
                                    if (name.equalsIgnoreCase(green + "Next Page")) {
                                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                        openSpawns(player, getOpenedPage(player) + 1);
                                    } else if (name.equalsIgnoreCase(green + "Prev Page")) {
                                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                        openSpawns(player, getOpenedPage(player) - 1);
                                    }
                                }
                                if (name.equalsIgnoreCase(yellow + "Falling Blocks")) {
                                    if (item.getType() == Material.SAND) {
                                        if (player.hasPermission("lb.vip")) {
                                            war.setSpawnFallingBlocks(!war.isSpawnFallingBlocks());
                                            openOptions(player);
                                            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                        }
                                    }
                                }
                                if (name.equalsIgnoreCase(red + "Particles")) {
                                    if (item.getType() == Material.REDSTONE) {
                                        openParticles(player);
                                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                    }
                                }
                                if (name.equalsIgnoreCase(green + "List Players")) {
                                    if (item.getType() == Material.SKULL_ITEM) {
                                        Inventory in = Bukkit.createInventory(player, 54, green + "Players in this game");
                                        for (int x = 0; x < war.getPlayers().size(); x++) {
                                            for (Player p : Bukkit.getOnlinePlayers()) {
                                                if (p.getUniqueId() == war.getPlayers().get(x)) {
                                                    ItemStack i = ItemMaker.createItem(Material.SKULL_ITEM, 1, 3, yellow + p.getName());
                                                    i = ItemMaker.setSkullOwner(i, p.getName());
                                                    i = ItemMaker.addLore(i, "");
                                                    i = ItemMaker.addLore(i, gray + "Lvl: " + green + LuckyBlockAPI.playerlevel.get(p.getUniqueId())[0]);
                                                    String k = "None";
                                                    if (Kits.ckit.containsKey(p.getUniqueId())) {
                                                        k = Kits.ckit.get(p.getUniqueId());
                                                    }
                                                    i = ItemMaker.addLore(i, gray + "Kit: " + green + k);
                                                    i = ItemMaker.addLore(i, gray + "--------------------");
                                                    in.addItem(i);
                                                }
                                            }
                                        }
                                        in.setItem(in.getSize() - 1, ItemMaker.createItem(Material.COMPASS, 1, 0, red + "Back"));
                                        player.openInventory(in);
                                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                    }
                                }
                                if (name.equalsIgnoreCase(darkred + "" + bold + "Mob Ability")) {
                                    //openMobAbility(player);
                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                    player.sendMessage(darkred + "Work In Progress!");
                                }
                                if (ChatColor.stripColor(name).endsWith("Particle")) {
                                    int rawslot = event.getRawSlot();
                                    if (rawslot < 36) {
                                        Particle particle = Particle.NONE;
                                        if (item.getType() == Material.ENDER_PEARL) {
                                            particle = Particle.NONE;
                                        } else if (item.getType() == Material.REDSTONE) {
                                            particle = Particle.REDSTONE;
                                        } else if (item.getType() == Material.REDSTONE_BLOCK) {
                                            particle = Particle.HEART;
                                        } else if (item.getType() == Material.BLAZE_POWDER) {
                                            particle = Particle.FLAME;
                                        } else if (item.getType() == Material.LAVA_BUCKET) {
                                            particle = Particle.LAVA;
                                        } else if (item.getType() == Material.OBSIDIAN) {
                                            particle = Particle.PORTAL;
                                        } else if (item.getType() == Material.SLIME_BALL) {
                                            particle = Particle.SLIME;
                                        } else if (item.getType() == Material.FIREWORK_CHARGE) {
                                            if (ChatColor.stripColor(name).startsWith("Large Smoke")) {
                                                particle = Particle.LARGE_SMOKE;
                                            } else {
                                                particle = Particle.SMOKE;
                                            }
                                        } else if (item.getType() == Material.DIAMOND_SWORD) {
                                            particle = Particle.CRIT;
                                        } else if (item.getType() == Material.NOTE_BLOCK) {
                                            particle = Particle.NOTE;
                                        } else if (item.getType() == Material.SNOW_BALL) {
                                            particle = Particle.SNOWBALL;
                                        } else if (item.getType() == Material.POTION) {
                                            particle = Particle.SPELL;
                                        } else if (item.getType() == Material.MONSTER_EGG) {
                                            particle = Particle.VILLAGER_ANGRY;
                                        }
                                        if (Particle.selected.get(uuid) != particle) {
                                            if (particle != Particle.NONE) {
                                                if (Particle.unlocked.get(uuid).contains(particle)) {
                                                    player.sendMessage(green + "Selected " + particle.toString());
                                                    Particle.selected.put(uuid, particle);
                                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                                    player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                                                    openParticles(player);
                                                } else {
                                                    player.sendMessage(red + "Locked!");
                                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                                }
                                            } else {
                                                player.sendMessage(green + "Reseting Particle.");
                                                Particle.selected.put(uuid, Particle.NONE);
                                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                                                openParticles(player);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onClickCageShop(InventoryClickEvent event) {
        if (event.getInventory().getTitle() != null) {
            if (event.getInventory().getTitle().equalsIgnoreCase(darkpurple + "" + bold + "Cages Shop")) {
                if (event.getCurrentItem() != null) {
                    if (event.getWhoClicked() instanceof Player) {
                        Player player = (Player) event.getWhoClicked();
                        UUID uuid = player.getUniqueId();
                        int money = LuckyBlockAPI.getMoney(player);
                        ItemStack item = event.getCurrentItem();
                        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                            String name = item.getItemMeta().getDisplayName();
                            if (item.getType() == Material.ARROW) {
                                if (name.equalsIgnoreCase(green + "Next Page")) {
                                    player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                    openCages(player, getOpenedPage(player) + 1);
                                } else if (name.equalsIgnoreCase(green + "Prev Page")) {
                                    player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                    openCages(player, getOpenedPage(player) - 1);
                                }
                            }
                            Cage c = null;
                            for (Cage cage : Cage.getCages()) {
                                if (item.getType() == cage.getType()) {
                                    if (item.getDurability() == cage.getData()) {
                                        if (name.equalsIgnoreCase(cage.getDisplayName())) {
                                            c = cage;
                                        }
                                    }
                                }
                            }
                            if (c != null) {
                                String success = "";
                                if (Cage.selectedcage.get(uuid) != c) {
                                    if (!c.isDefault()) {
                                        if (Cage.playercages.get(uuid).contains(c)) {
                                            Cage.selectedcage.put(uuid, c);
                                            LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                        } else {
                                            if (c.isBuyable()) {
                                                if (money >= c.getCost()) {
                                                    Cage.playercages.get(uuid).add(c);
                                                    LuckyBlockAPI.removeMoney(player, c.getCost());
                                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                                    success = "true";
                                                    player.sendMessage(green + "Successfully bought " + white + c.getName());
                                                } else {
                                                    success = "false";
                                                    player.sendMessage(red + "You don't have enough money!");
                                                }
                                            } else {
                                                success = "false";
                                                player.sendMessage(red + "Locked!");
                                            }
                                        }
                                    } else {
                                        Cage.selectedcage.put(uuid, c);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                                    }
                                } else {
                                    return;
                                }
                                if (success.equalsIgnoreCase("true")) {
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                                    openCages(player, getOpenedPage(player));
                                } else if (success.equalsIgnoreCase("false")) {
                                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1);
                                } else {
                                    openCages(player, getOpenedPage(player));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClickItem(InventoryClickEvent event) {
        if (event.getInventory().getTitle() != null) {
            Inventory inv = event.getInventory();
            if (inv.getTitle().equalsIgnoreCase(aqua + "" + bold + "Skills Shop") || inv.getTitle().equalsIgnoreCase(aqua + "" + bold + "Weapons Shop")
                    || inv.getTitle().equalsIgnoreCase(aqua + "" + bold + "Shop+") || inv.getTitle().equalsIgnoreCase(aqua + "" + bold + "Hats Shop")
                    || inv.getTitle().equalsIgnoreCase(darkpurple + "" + bold + "Cages Shop")) {
                if (!(event.getWhoClicked() instanceof Player)) {
                    return;
                }
                Player player = (Player) event.getWhoClicked();
                UUID uuid = player.getUniqueId();
                String success = "else";
                if (event.getCurrentItem() != null) {
                    if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                        int money = LuckyBlockAPI.money.get(uuid);
                        int golds = LuckyBlockAPI.golds.get(uuid);
                        int level = LuckyBlockAPI.playerlevel.get(uuid)[0];
                        ItemStack item = event.getCurrentItem();
                        String name = item.getItemMeta().getDisplayName();
                        List<ShopItems> bitems = LuckyBlockAPI.bitems.get(uuid);
                        if (item.getType() == Material.COMPASS) {
                            if (name.equalsIgnoreCase(red + "Back")) {
                                Game.openShop(player);
                                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            }
                        }
                        if (name.equalsIgnoreCase(red + "Max Health")) {
                            int m = LuckyBlockAPI.maxHealth.get(uuid);
                            if (m < 30) {
                                int cost = 350;
                                if (m > 1 && m < 4) {
                                    cost = ((m * 120) * 3);
                                } else if (m > 3 && m < 7) {
                                    cost = ((m * 150) * m);
                                } else if (m > 6) {
                                    cost = ((m * 150) * m * 2);
                                }
                                if (money >= cost) {
                                    LuckyBlockAPI.maxHealth.put(uuid, m + 1);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Skill and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openSkills(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "You Reached the maximum level for this skill!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(gold + "Multiple Coins")) {
                            int m = LuckyBlockAPI.multiply.get(uuid);
                            if (m < 4) {
                                int cost = 9500;
                                if (m == 2) {
                                    cost = 45000;
                                } else if (m == 3) {
                                    cost = 150000;
                                }
                                if (money >= cost) {
                                    LuckyBlockAPI.multiply.put(uuid, m + 1);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Skill and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openSkills(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "You Reached the maximum level for this skill!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(green + "Haste")) {
                            int h = LuckyBlockAPI.speedmine.get(uuid);
                            if (h < 6) {
                                int cost = 550;
                                if (h == 2) {
                                    cost = 1100;
                                } else if (h == 3) {
                                    cost = 2400;
                                } else if (h == 4) {
                                    cost = 6500;
                                } else if (h == 5) {
                                    cost = 14000;
                                }
                                if (money >= cost) {
                                    LuckyBlockAPI.speedmine.put(uuid, h + 1);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Skill and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openSkills(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "You Reached the maximum level for this skill!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(yellow + "" + bold + "Saturation Skill")) {
                            if (!bitems.contains(ShopItems.SATURATION_SKILL)) {
                                int cost = 1850;
                                if (money >= cost) {
                                    LuckyBlockAPI.bitems.get(uuid).add(ShopItems.SATURATION_SKILL);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Skill and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openPlus(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "This Item is already bought!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(darkaqua + "Water Breathing Skill")) {
                            if (!bitems.contains(ShopItems.WATER_BREATHING_SKILL)) {
                                int cost = 1800;
                                if (money >= cost) {
                                    LuckyBlockAPI.bitems.get(uuid).add(ShopItems.WATER_BREATHING_SKILL);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Skill and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openPlus(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "This Item is already bought!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(darkgreen + "High Jump Skill")) {
                            if (!bitems.contains(ShopItems.HIGH_JUMP_SKILL)) {
                                int cost = 1900;
                                if (money >= cost) {
                                    LuckyBlockAPI.bitems.get(uuid).add(ShopItems.HIGH_JUMP_SKILL);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Skill and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openPlus(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "This Item is already bought!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(darkgray + "" + bold + "Fire Resistance Skill")) {
                            if (!bitems.contains(ShopItems.FIRE_RESISTANCE_SKILL)) {
                                int cost = 2100;
                                if (money >= cost) {
                                    LuckyBlockAPI.bitems.get(uuid).add(ShopItems.FIRE_RESISTANCE_SKILL);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Skill and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openPlus(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "This Item is already bought!");
                                success = "false";
                            }
                        }
                        if (item.getType() == Material.GOLD_NUGGET) {
                            if (name.equalsIgnoreCase(yellow + "" + bold + "Sell")) {
                                if (item.getItemMeta().hasLore() && item.getItemMeta().getLore().size() > 1 && item.getItemMeta().getLore().get(1) != null) {
                                    String st = ChatColor.stripColor(item.getItemMeta().getLore().get(1));
                                    String[] d = st.split("Total: ");
                                    if (d.length == 2) {
                                        try {
                                            int t = Integer.parseInt(d[1]);
                                            if (t < 1) {
                                                player.sendMessage(red + "You don't have any skill to sell!");
                                                success = "false";
                                            } else {
                                                LuckyBlockAPI.bitems.put(uuid, new ArrayList<ShopItems>());
                                                LuckyBlockAPI.addMoney(player, t);
                                                LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                                player.sendMessage(green + "You have Got " + gold + t + green + " For selling Skills!");
                                                success = "true";
                                                openPlus(player);
                                            }
                                        } catch (NumberFormatException ex) {
                                            //
                                        }
                                    }
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(yellow + "" + bold + "Lucky Bow")) {
                            int cost = 2000;
                            if (!bitems.contains(ShopItems.LUCKY_BOW)) {
                                if (money >= cost) {
                                    LuckyBlockAPI.bitems.get(uuid).add(ShopItems.LUCKY_BOW);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Weapon and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openWeapons(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "This Item is already bought!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(yellow + "" + bold + "Lucky Sword")) {
                            int cost = 1900;
                            if (!bitems.contains(ShopItems.LUCKY_SWORD)) {
                                if (money >= cost) {
                                    LuckyBlockAPI.bitems.get(uuid).add(ShopItems.LUCKY_SWORD);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Weapon and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openWeapons(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "This Item is already bought!");
                                success = "false";
                            }
                        }
                        if (name.equalsIgnoreCase(aqua + "" + bold + "Thor's Axe")) {
                            int cost = 2500;
                            if (!bitems.contains(ShopItems.THOR_AXE)) {
                                if (money >= cost) {
                                    LuckyBlockAPI.bitems.get(uuid).add(ShopItems.THOR_AXE);
                                    LuckyBlockAPI.money.put(uuid, money - cost);
                                    player.sendMessage(green + "You Have Bought a Weapon and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                    success = "true";
                                    openWeapons(player);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                } else {
                                    player.sendMessage(red + "You don't have enough money!");
                                    success = "false";
                                }
                            } else {
                                player.sendMessage(red + "This Item is already bought!");
                                success = "false";
                            }
                        }
                        if (item.getType() == Material.LOG) {
                            if (name.equalsIgnoreCase(gold + "" + bold + "Log")) {
                                int cost = 900;
                                if (!bitems.contains(ShopItems.BLOCKS)) {
                                    if (money >= cost) {
                                        LuckyBlockAPI.bitems.get(uuid).add(ShopItems.BLOCKS);
                                        LuckyBlockAPI.money.put(uuid, money - cost);
                                        player.sendMessage(green + "You Have Bought a Weapon and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                        success = "true";
                                        openWeapons(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "This Item is already bought!");
                                    success = "false";
                                }
                            }
                        }
                        if (item.getType() == Material.NAME_TAG) {
                            if (name.equalsIgnoreCase(yellow + "Advanced Lucky Block Tool")) {
                                int cost = 3000;
                                if (!bitems.contains(ShopItems.ADVANCED_LUCKY_BLOCK_TOOL)) {
                                    if (money >= cost) {
                                        LuckyBlockAPI.bitems.get(uuid).add(ShopItems.ADVANCED_LUCKY_BLOCK_TOOL);
                                        LuckyBlockAPI.money.put(uuid, money - cost);
                                        player.sendMessage(green + "You Have Bought a Weapon and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                        success = "true";
                                        openWeapons(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "This Item is already bought!");
                                    success = "false";
                                }
                            }
                        }
                        if (item.getType() == Material.DIAMOND_AXE) {
                            if (name.equalsIgnoreCase(yellow + "Lucky Axe")) {
                                int cost = 1800;
                                if (!bitems.contains(ShopItems.LUCKY_AXE)) {
                                    if (money >= cost) {
                                        LuckyBlockAPI.bitems.get(uuid).add(ShopItems.LUCKY_AXE);
                                        LuckyBlockAPI.money.put(uuid, money - cost);
                                        player.sendMessage(green + "You Have Bought a Weapon and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                        success = "true";
                                        openWeapons(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "This Item is already bought!");
                                    success = "false";
                                }
                            }
                        }
                        if (item.getType() == Material.DIAMOND_PICKAXE) {
                            if (name.equalsIgnoreCase(yellow + "Lucky Pickaxe")) {
                                int cost = 1750;
                                if (!bitems.contains(ShopItems.LUCKY_PICKAXE)) {
                                    if (money >= cost) {
                                        LuckyBlockAPI.bitems.get(uuid).add(ShopItems.LUCKY_PICKAXE);
                                        LuckyBlockAPI.money.put(uuid, money - cost);
                                        player.sendMessage(green + "You Have Bought a Weapon and you have " + gold + LuckyBlockAPI.money.get(uuid) + green + " Money!");
                                        success = "true";
                                        openWeapons(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "This Item is already bought!");
                                    success = "false";
                                }
                            }
                        }
                        if (item.getType() == Material.DIAMOND_SPADE) {
                            if (name.equalsIgnoreCase(yellow + "Lucky Shovel")) {
                                int cost = 1800;
                                if (!bitems.contains(ShopItems.LUCKY_SHOVEL)) {
                                    if (money >= cost) {
                                        LuckyBlockAPI.bitems.get(uuid).add(ShopItems.LUCKY_SHOVEL);
                                        LuckyBlockAPI.money.put(uuid, money - cost);
                                        player.sendMessage(green + "You Have bought a weapons!");
                                        success = "true";
                                        openWeapons(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "This Item is already bought!");
                                    success = "false";
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "GLASS HAT")) {
                            Hat h = Hat.GLASS_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (money >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeMoney(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "COLORFUL HAT")) {
                            Hat h = Hat.COLORFUL_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "NONE")) {
                            if (Hat.getSelected().get(uuid) != Hat.NONE) {
                                Hat.getSelected().put(uuid, Hat.NONE);
                                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                player.sendMessage(green + "Reseting hat.");
                                LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                openHats(player);
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "DIAMOND HAT")) {
                            Hat h = Hat.DIAMOND_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (money >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeMoney(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "ARCHER HAT")) {
                            Hat h = Hat.LEATHER_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "HARD HAT")) {
                            Hat h = Hat.HARD_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "CAGE HAT")) {
                            Hat h = Hat.CAGE_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "TNT HAT")) {
                            Hat h = Hat.TNT_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (money >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeMoney(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "NOTCH HAT")) {
                            Hat h = Hat.NOTCH_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "PUMPKIN HAT")) {
                            Hat h = Hat.PUMPKIN_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "SNOW HAT")) {
                            Hat h = Hat.SNOW_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "CRAFTING HAT")) {
                            Hat h = Hat.CRAFTING_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "ENCHANTING HAT")) {
                            Hat h = Hat.ENCHANTING_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "BRICK HAT")) {
                            Hat h = Hat.BRICK_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "ENDERMAN HAT")) {
                            Hat h = Hat.ENDERMAN_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(white + "" + bold + "HEROBRINE HAT")) {
                            Hat h = Hat.HEROBRINE_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "ENDER HAT")) {
                            Hat h = Hat.ENDER_HAT;
                            if (!(Hat.getHats().get(uuid).contains(h)) || Hat.getSelected().get(uuid) == h) {
                                player.sendMessage(red + "Locked!");
                                success = "false";
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "CHEST HAT")) {
                            Hat h = Hat.CHEST_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "ENDSTONE HAT")) {
                            Hat h = Hat.END_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (money >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeMoney(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "ICE HAT")) {
                            Hat h = Hat.ICE_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (money >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeMoney(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "MELON HAT")) {
                            Hat h = Hat.MELON_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (money >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeMoney(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough money!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "HAY HAT")) {
                            Hat h = Hat.HAY_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "NOFALL HAT")) {
                            Hat h = Hat.NOFALL_HAT;
                            if (!Hat.getHats().get(uuid).contains(h)) {
                                if (level >= h.getLevel()) {
                                    if (golds >= h.getCost()) {
                                        h.unlock(uuid);
                                        LuckyBlockAPI.removeGold(player, h.getCost());
                                        player.sendMessage(green + "You have unlocked a new hat!");
                                        success = "true";
                                        openHats(player);
                                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    } else {
                                        player.sendMessage(red + "You don't have enough gold!");
                                        success = "false";
                                    }
                                } else {
                                    player.sendMessage(red + "You don't have the required levels!");
                                    success = "false";
                                }
                            } else {
                                if (Hat.getSelected().get(uuid) != h) {
                                    Hat.getSelected().put(uuid, h);
                                    player.sendMessage(green + "Selected Hat " + gold + h);
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                                    LuckyBlockAPI.savePlayerData(uuid, player.getName());
                                    openHats(player);
                                }
                            }
                        }
                        if (success.equalsIgnoreCase("true")) {
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 100f, 1);
                        } else if (success.equalsIgnoreCase("false")) {
                            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.8f, 1);
                        }
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onClickItem1(InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            ItemStack item = event.getCurrentItem();
            if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                if (ChatColor.stripColor(item.getItemMeta().getDisplayName()).endsWith("HAT")) {
                    if (event.getInventory().getTitle() != null) {
                        if (!event.getInventory().getTitle().equalsIgnoreCase(aqua + "" + bold + "Hats Shop")) {
                            event.setCancelled(true);
                        }
                    } else {
                        event.setCancelled(true);
                    }
                    if (War.containPlayer(event.getWhoClicked().getUniqueId())) {
                        String name = item.getItemMeta().getDisplayName();
                        if (name.equalsIgnoreCase(blue + "" + bold + "CRAFTING HAT")) {
                            event.getWhoClicked().openWorkbench(event.getWhoClicked().getLocation(), true);
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "CHEST HAT")) {
                            if (pi.containsKey(event.getWhoClicked().getUniqueId())) {
                                event.getWhoClicked().openInventory(pi.get(event.getWhoClicked().getUniqueId()));
                            } else {
                                pi.put(event.getWhoClicked().getUniqueId(), Bukkit.createInventory(event.getWhoClicked(), 27));
                                event.getWhoClicked().openInventory(pi.get(event.getWhoClicked().getUniqueId()));
                            }
                        }
                        if (name.equalsIgnoreCase(blue + "" + bold + "ENCHANTING HAT")) {
                            event.getWhoClicked().openEnchanting(event.getWhoClicked().getLocation(), true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onClickLBGui(InventoryClickEvent event) {
        if (event.getInventory().getTitle() != null && event.getInventory().getTitle().equalsIgnoreCase(yellow + "[Lucky Blocks]")) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                if (event.getWhoClicked() instanceof Player) {
                    Player player = (Player) event.getWhoClicked();
                    int size = event.getInventory().getSize();
                    int rawslot = event.getRawSlot();
                    if (rawslot < size) {
                        if (event.getCurrentItem().getType() != Material.COMPASS) {
                            player.getInventory().addItem(event.getCurrentItem());
                            player.sendMessage(LuckyBlockCommand.getMessage("Gui.GetLB"));
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 0);
                        } else {
                            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 0);
                            player.closeInventory();
                        }
                    }
                }
            }
        }
    }


}
