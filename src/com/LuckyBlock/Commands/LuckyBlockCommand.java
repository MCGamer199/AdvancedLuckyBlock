package com.LuckyBlock.Commands;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Engine.LuckyBlockWorld;
import com.LuckyBlock.Engine.Types;
import com.LuckyBlock.Events.Gui;
import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.Resources.Detector;
import com.LuckyBlock.logic.Range;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * <b>LuckyBlockCommand</b>
 * <p>
 * This library was created by @MCGamer199 for Lucky Block
 * <p>
 * You are can use it and modify it under the following conditions:
 * <ul>
 * <li>Don't claim this class as your own
 * <li>Don't remove this disclaimer
 * </ul>
 * <p>
 * <i>Lucky Block Commands</i>
 *
 * @author MCGamer199
 */


@SuppressWarnings({"unused", "deprecation"})
public class LuckyBlockCommand implements CommandExecutor {


    public static String invalid = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("InvalidSender"));
    public static String error = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("InvalidArgs"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("NoPermission"));
    public static String num = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("InvalidNumber"));
    public static String invalidplayer = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("InvalidPlayer"));
    public static String lcmd = LuckyBlock.instance.config.getString("LuckyBlockCommand.Command");
    public static String lwcmd = LuckyBlock.instance.config.getString("LuckyBlockWarCommand.Command");
    public static String tcmd = LuckyBlock.instance.config.getString("TeamsCommand.Command");
    public static byte pages = 3;
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
    String thelp = getMessage("InvalidCommand");

    /**
     * Returns Message from Messages.yml file.
     *
     * @param loc
     * @return
     */
    public static String getMessage(String loc) {
        String s = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString(loc));
        s = s.replace("<lbwcmd>", lwcmd);
        s = s.replace("<lbcmd>", lcmd);
        s = s.replace("<tcmd>", tcmd);
        return s;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lb.commands")) {
            sender.sendMessage(noperm);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(thelp);
        } else {
            if (args[0].equalsIgnoreCase("help")) {
                if (args.length == 1) {

                    sender.sendMessage(aqua + "------ Showing help page ------");
                    sender.sendMessage(aqua + "Page 1/" + pages);
                    sender.sendMessage(green + "/" + lcmd + " clearlbs; " + gold + "Removes all Lucky Blocks.");
                    sender.sendMessage(green + "/" + lcmd + " detector; " + gold + "Gives player detector.");
                    sender.sendMessage(green + "/" + lcmd + " getfile [FileName] [Loc] [Value]; " + gold + "Changes value in a file.");
                    sender.sendMessage(green + "/" + lcmd + " gift; " + gold + "Opens a gift.");
                    sender.sendMessage(green + "/" + lcmd + " gui; " + gold + "Opens Lucky Block gui.");
                    sender.sendMessage(green + "/" + lcmd + " gold [Player]; " + gold + "Tells you how much gold the player has.");
                    sender.sendMessage(green + "/" + lcmd + " help [Page]; " + gold + "Help command.");
                    sender.sendMessage(green + "/" + lcmd + " lb/give/luckyblock [Player] (Amount) [Luck-Int] [Type]; " + gold + "Lucky Block Command.");
                    sender.sendMessage(green + "/" + lcmd + " money [Player]; " + gold + "Tells you how much money the player has.");
                    sender.sendMessage(aqua + "-------------------------------");

                } else if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("1")) {

                        sender.sendMessage(aqua + "------ Showing help page ------");
                        sender.sendMessage(aqua + "Page 1/" + pages);
                        sender.sendMessage(green + "/" + lcmd + " clearlbs; " + gold + "Removes all Lucky Blocks.");
                        sender.sendMessage(green + "/" + lcmd + " detector; " + gold + "Gives player detector.");
                        sender.sendMessage(green + "/" + lcmd + " gift; " + gold + "Opens a gift.");
                        sender.sendMessage(green + "/" + lcmd + " gui; " + gold + "Opens Lucky Block gui.");
                        sender.sendMessage(green + "/" + lcmd + " gold [Player]; " + gold + "Tells you how much gold the player has.");
                        sender.sendMessage(green + "/" + lcmd + " help [Page]; " + gold + "Help command.");
                        sender.sendMessage(green + "/" + lcmd + " lb/give/luckyblock [Player] (Amount) [Luck-Int] [Type]; " + gold + "Lucky Block Command.");
                        sender.sendMessage(green + "/" + lcmd + " money [Player]; " + gold + "Tells you how much money the player has.");
                        sender.sendMessage(aqua + "-------------------------------");

                    } else if (args[1].equalsIgnoreCase("2")) {

                        sender.sendMessage(aqua + "------ Showing help page ------");
                        sender.sendMessage(aqua + "Page 2/" + pages);
                        sender.sendMessage(green + "/" + lcmd + " reload; " + gold + "Reloads config.yml file.");
                        sender.sendMessage(green + "/" + lcmd + " setchance [Chance-Int]; " + gold + "Sets chance for lucky block.");
                        sender.sendMessage(green + "/" + lcmd + " setgold (Gold) [Player]; " + gold + "Sets gold for player.");
                        sender.sendMessage(green + "/" + lcmd + " setmoney (Money) [Player]; " + gold + "Sets money for player.");
                        sender.sendMessage(green + "/" + lcmd + " setowner (UUID); " + gold + "Sets Lucky Block's Owner.");
                        sender.sendMessage(green + "/" + lcmd + " setluck [Luck-Int]; " + gold + "Sets luck for lucky block.");
                        sender.sendMessage(green + "/" + lcmd + " setrange [ID] (Range); " + gold + "Sets range for detector.");
                        sender.sendMessage(green + "/" + lcmd + " thoraxe [Player]; " + gold + "Gives player thor's axe.");
                        sender.sendMessage(aqua + "-------------------------------");

                    } else if (args[1].equalsIgnoreCase("3")) {

                        sender.sendMessage(aqua + "------ Showing help page ------");
                        sender.sendMessage(aqua + "Page 3/" + pages);
                        sender.sendMessage(green + "/" + lcmd + " types [Page]; " + gold + "Lists lucky block types.");
                        sender.sendMessage(green + "/" + lcmd + " v/ersion; " + gold + "The version of the plugin.");
                        sender.sendMessage(lightpurple + "Nothing more!");
                        sender.sendMessage(aqua + "-------------------------------");

                    } else {
                        try {
                            byte page = Byte.parseByte(args[1]);
                            if (page > 0) {

                                sender.sendMessage(aqua + "------ Showing help page ------");
                                sender.sendMessage(aqua + "Page " + page + "/" + pages);
                                sender.sendMessage(aqua + "-------------------------------");

                            } else {

                                sender.sendMessage(num);

                            }
                        } catch (NumberFormatException ex) {

                            sender.sendMessage(num);

                        }
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("addlore")) {
                if (!sender.hasPermission("lb.commands.addlore")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length > 1) {
                    if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
                        if (player.getItemInHand().getItemMeta().hasLore()) {
                            ItemMeta ll = player.getItemInHand().getItemMeta();
                            List<String> lll = ll.getLore();
                            String d = "";
                            for (int x = 0; x < args.length; x++) {
                                if (x > 0) {
                                    if (x == 1) {
                                        d = args[x];
                                    } else {
                                        d = d + " " + args[x];
                                    }
                                }
                            }
                            lll.add(ChatColor.translateAlternateColorCodes('&', d));
                            ll.setLore(lll);
                            player.getItemInHand().setItemMeta(ll);
                            d = ChatColor.translateAlternateColorCodes('&', d);
                        } else {
                            ItemMeta ll = player.getItemInHand().getItemMeta();
                            String d = "";
                            for (int x = 0; x < args.length; x++) {
                                if (x > 0) {
                                    if (x == 1) {
                                        d = args[x];
                                    } else {
                                        d = d + " " + args[x];
                                    }
                                }
                            }
                            List<String> lll = new ArrayList<String>();
                            lll.add(ChatColor.translateAlternateColorCodes('&', d));
                            ll.setLore(lll);
                            player.getItemInHand().setItemMeta(ll);
                            d = ChatColor.translateAlternateColorCodes('&', d);
                        }
                    }
                }

            } else if (args[0].equalsIgnoreCase("removelore")) {
                if (!sender.hasPermission("lb.commands.removelore")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length == 1) {
                    if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
                        if (player.getItemInHand().hasItemMeta()) {
                            if (player.getItemInHand().getItemMeta().hasLore()) {
                                ItemMeta im = player.getItemInHand().getItemMeta();
                                im.setLore(null);
                                player.getItemInHand().setItemMeta(im);
                            }
                        }
                    }
                } else {
                    player.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("luckyblock") || args[0].equalsIgnoreCase("lb") || args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission("lb.commands.luckyblock")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (args.length < 6) {
                    Player target = null;
                    if (args.length == 1) {
                        if (!(sender instanceof Player)) {
                            sender.sendMessage(invalid);
                            return true;
                        }
                        target = (Player) sender;
                    }
                    if (target == null) {
                        target = Bukkit.getPlayer(args[1]);
                    }
                    if (target == null) {
                        String msg = invalidplayer.replace("%Target%", args[1]);
                        sender.sendMessage(msg);
                        return true;
                    }
                    int amount = 1;
                    int id = 1;
                    int luck = 0;
                    if (args.length > 2) {
                        try {
                            amount = Integer.parseInt(args[2]);
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(num);
                        }
                    }
                    if (amount < 1) {
                        sender.sendMessage(num);
                        return true;
                    }
                    if (args.length > 3) {
                        try {
                            luck = Integer.parseInt(args[3]);
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(num);
                        }
                    }
                    if (args.length > 4) {
                        try {
                            id = Integer.parseInt(args[4]);
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(num);
                        }
                    }
                    Types type = Types.fromId(id);
                    if (type == null) {
                        sender.sendMessage(getMessage("GiveLB.InvalidType"));
                        return true;
                    }
                    short data = 0;
                    if (type.getData() > -1) {
                        data = type.getData();
                    }
                    if (luck > type.getMaxLuck() || luck < type.getMinLuck()) {
                        sender.sendMessage(getMessage("GiveLB.InvalidLuck"));
                        return true;
                    }
                    ItemStack item = type.toItemStack(luck);
                    item.setAmount(amount);
                    target.getInventory().addItem(item);
                    sender.sendMessage(getMessage("GiveLB.Success"));
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("renameitem")) {
                if (!sender.hasPermission("lb.commands.renameitem")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return false;
                }
                Player player = (Player) sender;
                if (args.length == 1) {
                    if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
                        return true;
                    }
                } else if (args.length > 1) {
                    if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
                        return true;
                    }
                    ItemMeta meta = player.getItemInHand().getItemMeta();
                    String d = "";
                    for (int x = 0; x < args.length; x++) {
                        if (x > 0) {
                            if (x == 1) {
                                d = args[x];
                            } else {
                                d = d + " " + args[x];
                            }
                        }
                    }
                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', d));
                    player.getItemInHand().setItemMeta(meta);
                }

            } else if (args[0].equalsIgnoreCase("setluck")) {
                if (!sender.hasPermission("lb.commands.setluck")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("SetLuck.InvalidUsage"));
                } else if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                    }
                    Player player = (Player) sender;
                    Block block = player.getTargetBlock((Set<Material>) null, 200);
                    if (block.getType() != Material.AIR && block.getType() != Material.LAVA && block.getType() != Material.WATER
                            && block.getType() != Material.STATIONARY_LAVA && block.getType() != Material.STATIONARY_WATER) {
                        try {
                            int luck = Integer.parseInt(args[1]);
                            String dim = LuckyBlockAPI.createDim(block);
                            if (!LuckyBlockAPI.chances.containsKey(dim)) {
                                LuckyBlockAPI.chances.put(dim, 1);
                            }
                            LuckyBlockAPI.luck.put(dim, luck);
                            int id = 0;
                            if (Types.fromBlock(dim) != null) {
                                id = Types.fromBlock(dim).getId();
                            } else {
                                id = Types.getTypes().get(0).getId();
                            }
                            LuckyBlockAPI.saveLuckyBlock(dim, luck, LuckyBlockAPI.chances.get(dim), "null", id);
                            String g = getMessage("SetLuck.Success");
                            g = g.replace("%Luck%", "" + luck);
                            player.sendMessage(g);
                        } catch (NumberFormatException ex) {
                            player.sendMessage(num);
                        }
                    } else {
                        player.sendMessage(getMessage("SetLuck.InvalidBlock"));
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setchance")) {
                if (!sender.hasPermission("lb.commands.setchance")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("SetChance.InvalidUsage"));
                } else if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                    }
                    Player player = (Player) sender;
                    if (player.getTargetBlock((Set<Material>) null, 200).getType() != Material.AIR) {
                        Block block = player.getTargetBlock((Set<Material>) null, 200);
                        try {
                            int chance = Integer.parseInt(args[1]);
                            String dim = LuckyBlockAPI.createDim(block);
                            if (!LuckyBlockAPI.luck.containsKey(dim)) {
                                LuckyBlockAPI.luck.put(dim, 0);
                            }
                            LuckyBlockAPI.chances.put(dim, chance);
                            int id = Types.fromBlock(dim).getId();
                            LuckyBlockAPI.saveLuckyBlock(dim, LuckyBlockAPI.luck.get(dim), chance, "null", id);
                            String g = getMessage("SetChance.Success");
                            g = g.replace("%Chance%", "" + chance);
                            player.sendMessage(g);
                        } catch (NumberFormatException ex) {
                            player.sendMessage(num);
                        }
                    } else {
                        player.sendMessage(getMessage("SetChance.InvalidBlock"));
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("rider")) {
                if (!sender.hasPermission("lb.commands.rider")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                LuckyBlock.instance.SpawnRider(player);

            } else if (args[0].equalsIgnoreCase("setowner")) {
                if (!sender.hasPermission("lb.commands.setowner")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                if (args.length == 2) {
                    Player player = (Player) sender;
                    Block block = player.getTargetBlock((Set<Material>) null, 200);
                    if (block.getType() != Material.AIR && block.getType() != Material.WATER && block.getType()
                            != Material.STATIONARY_WATER && block.getType() != Material.LAVA && block.getType() != Material.STATIONARY_LAVA) {
                        String dim = LuckyBlockAPI.createDim(block);
                        if (LuckyBlockAPI.chances.containsKey(dim) && LuckyBlockAPI.luck.containsKey(dim)) {
                            LuckyBlockAPI.BlockOwner.put(dim, args[1]);
                            LuckyBlockAPI.saveLuckyBlock(dim, LuckyBlockAPI.luck.get(dim), LuckyBlockAPI.chances.get(dim), args[1], 1);
                            String g = getMessage("SetOwner.Success");
                            g = g.replace("%Owner%", args[1]);
                            player.sendMessage(g);
                        } else {
                            player.sendMessage(getMessage("SetOwner.NotLuckyBlock"));
                        }
                    } else {
                        String g = getMessage("SetOwner.InvalidBlock");
                        g = g.replace("%BlockType%", "" + block.getType());
                        player.sendMessage(g);
                    }
                } else {
                    sender.sendMessage(getMessage("SetOwner.InvalidUsage"));
                }

            } else if (args[0].equalsIgnoreCase("gift")) {
                if (!sender.hasPermission("lb.commands.gift")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    UUID uuid = player.getUniqueId();
                    if (!LuckyBlock.gifts.containsKey(uuid)) {
                        LuckyBlock.gifts.put(uuid, 0);
                    }
                    if (LuckyBlock.gifts.get(uuid) > 0 || player.isOp()) {
                        player.sendMessage(getMessage("Gift.Success"));
                        LuckyBlock.instance.Gift(player);
                        LuckyBlock.gifts.put(uuid, LuckyBlock.gifts.get(uuid) - 1);
                    } else {
                        player.sendMessage(getMessage("Gift.NoGifts.Sender"));
                    }
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        String g = getMessage("InvalidPlayer");
                        g = g.replace("%Target%", args[1]);
                        sender.sendMessage(g);
                    } else {
                        UUID uuid = target.getUniqueId();
                        if (LuckyBlock.gifts.containsKey(uuid)) {
                            if (LuckyBlock.gifts.get(uuid) > 0 || target.isOp()) {
                                sender.sendMessage(getMessage("Gift.Success"));
                                LuckyBlock.instance.Gift(target);
                                LuckyBlock.gifts.put(uuid, LuckyBlock.gifts.get(uuid) - 1);
                            } else {
                                String g = getMessage("Gift.NoGifts.Target");
                                g = g.replace("%Target%", target.getName());
                                sender.sendMessage(g);
                            }
                        } else {
                            LuckyBlock.gifts.put(uuid, 0);
                        }
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("lb.commands.reload")) {
                    sender.sendMessage(noperm);
                    return false;
                }
                LuckyBlock.instance.reloadConfig();
                LuckyBlock.instance.config = LuckyBlock.instance.getConfig();
//Bukkit.getPluginManager().disablePlugin(LuckyBlock.instance);
//Bukkit.getPluginManager().enablePlugin(LuckyBlock.instance);
                sender.sendMessage(getMessage("Reload"));

            } else if (args[0].equalsIgnoreCase("clearlbs")) {
                if (!(sender.hasPermission("lb.clearlbs"))) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (LuckyBlockAPI.lbs.getStringList("LuckyBlocks").size() > 0) {
                    List<String> list = LuckyBlockAPI.lbs.getStringList("LuckyBlocks");
                    LuckyBlockAPI.chances.clear();
                    LuckyBlockAPI.luck.clear();
                    list.clear();
                    LuckyBlockAPI.lbs.set("LuckyBlocks", list);
                    LuckyBlockAPI.saveConfigs();
                    sender.sendMessage(getMessage("RemoveLuckyBlocks.Success"));
                } else {
                    sender.sendMessage(getMessage("RemoveLuckyBlocks.NoLuckyBlock"));
                }

            } else if (args[0].equalsIgnoreCase("say")) {
//TODO
                if (args.length > 2) {
                    String s = "";
                    for (int x = 1; x < args.length; x++) {
                        if (x > 1) {
                            if (x == 2) {
                                s = args[x];
                            } else {
                                s = s + " " + args[x];
                            }
                        }
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    target.chat(ChatColor.translateAlternateColorCodes('&', s));
                }

            } else if (args[0].equalsIgnoreCase("money")) {
                if (!(sender.hasPermission("lb.commands.money"))) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    UUID uuid = player.getUniqueId();
                    if (!LuckyBlockAPI.money.containsKey(uuid)) {
                        LuckyBlockAPI.money.put(uuid, 0);
                    }
                    String g = getMessage("Money.You");
                    g = g.replace("%Money%", LuckyBlockAPI.money.get(uuid) + "");
                    player.sendMessage(g);
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        String g = invalidplayer;
                        g = g.replace("%Target%", args[1]);
                        sender.sendMessage(g);
                        return true;
                    }
                    UUID uuid = target.getUniqueId();
                    if (!LuckyBlockAPI.money.containsKey(uuid)) {
                        LuckyBlockAPI.money.put(uuid, 0);
                    }
                    String g = getMessage("Money.Others");
                    g = g.replace("%Money%", LuckyBlockAPI.money.get(uuid) + "");
                    g = g.replace("%Target%", target.getName());
                    sender.sendMessage(g);
                } else {
                    Player player = (Player) sender;
                    player.sendMessage("" + player.getWalkSpeed());
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setmoney")) {
                if (!(sender.hasPermission("lb.commands.setmoney"))) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("SetMoney.InvalidUsage"));
                } else if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    UUID uuid = player.getUniqueId();
                    try {
                        int value = Integer.parseInt(args[1]);
                        LuckyBlockAPI.money.put(uuid, value);
                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                        String g = getMessage("SetMoney.Success");
                        g = g.replace("%Player%", player.getName());
                        g = g.replace("%Money%", "" + value);
                        player.sendMessage(g);
                    } catch (NumberFormatException ex) {
                        player.sendMessage(num);
                    }
                } else if (args.length == 3) {
                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null) {
                        String g = invalidplayer;
                        g = g.replace("%Target%", args[2]);
                        sender.sendMessage(g);
                        return true;
                    }
                    UUID uuid = target.getUniqueId();
                    try {
                        int value = Integer.parseInt(args[1]);
                        LuckyBlockAPI.money.put(uuid, value);
                        LuckyBlockAPI.savePlayerData(uuid, target.getName());
                        String g = getMessage("SetMoney.Success");
                        g = g.replace("%Player%", target.getName());
                        g = g.replace("%Money%", "" + value);
                        sender.sendMessage(g);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("gold")) {
                if (!(sender.hasPermission("lb.commands.gold"))) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(error);
                        return true;
                    }
                    Player player = (Player) sender;
                    LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
                    String g = getMessage("Gold.You");
                    g = g.replace("%Gold%", LuckyBlockAPI.golds.get(player.getUniqueId()) + "");
                    player.sendMessage(g);
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        String g = invalidplayer;
                        g = g.replace("%Target%", args[2]);
                        sender.sendMessage(g);
                        return true;
                    }
                    LuckyBlockAPI.savePlayerData(target.getUniqueId(), target.getName());
                    String g = getMessage("Gold.Others");
                    g = g.replace("%Target%", target.getName());
                    g = g.replace("%Gold%", LuckyBlockAPI.golds.get(target.getUniqueId()) + "");
                    sender.sendMessage(g);
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setgold")) {
                if (!sender.hasPermission("lb.commands.setgold")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("SetGold.InvalidUsage"));
                } else if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    try {
                        short n = Short.parseShort(args[1]);
                        LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
                        LuckyBlockAPI.setGold(player, n);
                        String g = getMessage("SetGold.Success");
                        g = g.replace("%Gold%", n + "");
                        sender.sendMessage(g);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else if (args.length == 3) {
                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null) {
                        String g = invalidplayer;
                        g = g.replace("%Target%", args[2]);
                        sender.sendMessage(g);
                        return true;
                    }
                    try {
                        short n = Short.parseShort(args[1]);
                        LuckyBlockAPI.savePlayerData(target.getUniqueId(), target.getName());
                        LuckyBlockAPI.setGold(target, n);
                        String g = getMessage("SetGold.Success");
                        g = g.replace("%Gold%", n + "");
                        sender.sendMessage(g);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }

                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("thoraxe")) {
                if (!sender.hasPermission("lb.commands.thoraxe")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    Map<Enchantment, Integer> m = new HashMap<Enchantment, Integer>();
                    m.put(LuckyBlock.lightning, 10);
                    ItemStack item = LuckyBlockAPI.createItemStack(Material.IRON_AXE, 1, (short) 0, aqua + "" + bold + "Thor's Axe", null, m);
                    player.getInventory().addItem(item);
                    player.sendMessage(getMessage("ThoraxeCommand.Success"));
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        String g = invalidplayer;
                        g = g.replace("%Target%", args[1]);
                        sender.sendMessage(g);
                        return true;
                    }
                    Map<Enchantment, Integer> m = new HashMap<Enchantment, Integer>();
                    ItemStack item = LuckyBlockAPI.createItemStack(Material.IRON_AXE, 1, (short) 0, aqua + "" + bold + "Thor's Axe", null, m);
                    target.getInventory().addItem(item);
                    sender.sendMessage(getMessage("ThoraxeCommand.Success"));
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
                if (!sender.hasPermission("lb.version")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(green + "Plugin's Version is " + gold + LuckyBlock.version);
                } else {
                    Player player = (Player) sender;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " {text:\"Plugin's Version is \",color:\"green\""
                            + ",extra:[{text:\"" + LuckyBlock.version + "\",color:gold,hoverEvent:{action:show_text,value:{text:\"Plugin's Version\",color:yellow}}}]}");
                }

            } else if (args[0].equalsIgnoreCase("detector")) {
                if (!sender.hasPermission("lb.commands.detector")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    ItemStack item = ItemMaker.createItem(Material.PISTON_BASE, 1, (short) 0, blue + "" + bold + "Detector", Arrays.asList(gray + "Place it"));
                    player.getInventory().addItem(item);
                    player.sendMessage(getMessage("DetectorCommand.Success"));
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        String g = invalidplayer;
                        g = g.replace("%Target%", args[1]);
                        sender.sendMessage(g);
                        return true;
                    }
                    ItemStack item = ItemMaker.createItem(Material.PISTON_BASE, 1, (short) 0, blue + "" + bold + "Detector", Arrays.asList(gray + "Place it"));
                    target.getInventory().addItem(item);
                    sender.sendMessage(getMessage("DetectorCommand.Success"));
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setrange")) {
                if (!sender.hasPermission("lb.commands.setrange")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetRange.InvalidUsage"));
                } else {
                    try {
                        int id = Integer.parseInt(args[1]);
                        int range = Integer.parseInt(args[2]);
                        boolean found = false;
                        Detector d = null;
                        for (int x = 0; x < LuckyBlockAPI.detectors.size(); x++) {
                            if (LuckyBlockAPI.detectors.get(x).getId() == id) {
                                found = true;
                                d = LuckyBlockAPI.detectors.get(x);
                            }
                        }
                        if (found == true) {
                            d.setRange(new Range(range, range, range));
                            d.save();
                            sender.sendMessage(getMessage("SetRange.Success"));
                        } else {
                            sender.sendMessage(getMessage("SetRange.NotFound"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                }

            } else if (args[0].equalsIgnoreCase("types")) {
                if (!sender.hasPermission("lb.commands.types")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (LuckyBlock.lbs.size() == 0) {
                    sender.sendMessage(red + "No type found!");
                } else {
                    if (args.length == 1) {
                        sender.sendMessage(aqua + "Showing page " + white + 1);
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            if (LuckyBlock.bukkitVersion[1] > 7) {
                                int i = 0;
                                for (int x = 0; x < LuckyBlock.lbs.size(); x++) {
                                    if (i < 6) {
                                        Types t = LuckyBlock.lbs.get(x);
                                        short data = 0;
                                        if (t.getData() > -1) {
                                            data = t.getData();
                                        }
                                        String cmd = "tellraw " + player.getName() + " {text:\"" + t.getId() + "\",color:red,extra:[{text:\", \",color:green},{text:\"" + t.getName()
                                                + "\",hoverEvent:{action:show_item,value:\"{id:minecraft:" + t.getType().toString().toLowerCase() + ",Damage:" + data +
                                                ",tag:{display:{Name:" + t.getName() + "}}}\"}" + ",clickEvent:{action:run_command,value:\"/" +
                                                LuckyBlock.instance.config.getString("LuckyBlockCommand.Command") + " lb " + player.getName() + " 1 0 " + t.getId() + "\"}}]}";
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                                        i++;
                                    }
                                }
                            } else {
                                int i = 0;
                                for (int x = 0; x < LuckyBlock.lbs.size(); x++) {
                                    if (i < 6) {
                                        Types t = LuckyBlock.lbs.get(x);
                                        String msg = red + "" + t.getId() + green + ", " + white + t.getName();
                                        player.sendMessage(msg);
                                        i++;
                                    }
                                }
                            }
                        } else {
                            int i = 0;
                            for (int x = 0; x < LuckyBlock.lbs.size(); x++) {
                                if (i < 6) {
                                    Types t = LuckyBlock.lbs.get(x);
                                    String msg = red + "" + t.getId() + green + ", " + white + t.getName();
                                    sender.sendMessage(msg);
                                    i++;
                                }
                            }
                        }
                    } else if (args.length == 2) {
                        int page = 1;
                        try {
                            page = Integer.parseInt(args[1]);
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(num);
                            return true;
                        }
                        if (page < 1 || page > 128) {
                            sender.sendMessage(num);
                            return true;
                        }
                        sender.sendMessage(aqua + "Showing page " + white + page);
                        if (sender instanceof Player) {
                            if (LuckyBlock.bukkitVersion[1] > 7) {
                                Player player = (Player) sender;
                                for (int x = ((page - 1) * 5); x < ((page) * 5) + 1; x++) {
                                    if (x < LuckyBlock.lbs.size()) {
                                        Types t = LuckyBlock.lbs.get(x);
                                        short data = 0;
                                        if (t.getData() > -1) {
                                            data = t.getData();
                                        }
                                        String cmd = "tellraw " + player.getName() + " {text:\"" + t.getId() + "\",color:red,extra:[{text:\", \",color:green},{text:\"" + t.getName()
                                                + "\",hoverEvent:{action:show_item,value:\"{id:minecraft:" + t.getType().toString().toLowerCase() + ",Damage:" + data +
                                                ",tag:{display:{Name:" + t.getName() + "}}}\"}" + ",clickEvent:{action:run_command,value:\"/" +
                                                LuckyBlock.instance.config.getString("LuckyBlockCommand.Command") + " lb " + player.getName() + " 1 0 " + t.getId() + "\"}}]}";
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                                    }
                                }
                            } else {
                                for (int x = ((page - 1) * 5); x < ((page) * 5) + 1; x++) {
                                    if (x < LuckyBlock.lbs.size()) {
                                        Types t = LuckyBlock.lbs.get(x);
                                        String msg = red + "" + t.getId() + green + ", " + white + t.getName();
                                        sender.sendMessage(msg);
                                    }
                                }
                            }
                        } else {
                            if (page == 1) {
                                for (int x = ((page - 1) * 5); x < ((page) * 5) + 1; x++) {
                                    if (x < LuckyBlock.lbs.size()) {
                                        Types t = LuckyBlock.lbs.get(x);
                                        String msg = red + "" + t.getId() + green + ", " + white + t.getName();
                                        sender.sendMessage(msg);
                                    }
                                }
                            } else {
                                for (int x = ((page - 1) * 5) - 1; x < ((page) * 5) + 1; x++) {
                                    if (x < LuckyBlock.lbs.size()) {
                                        Types t = LuckyBlock.lbs.get(x);
                                        String msg = red + "" + t.getId() + green + ", " + white + t.getName();
                                        sender.sendMessage(msg);
                                    }
                                }
                            }
                        }
                    } else {
                        sender.sendMessage(error);
                    }
                }

            } else if (args[0].equalsIgnoreCase("world")) {
                if (!sender.hasPermission("lb.commands.world")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 3) {
                    int id = Types.getTypes().get(0).getId();
                    if (args.length == 2) {
                        try {
                            id = Integer.parseInt(args[1]);
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(num);
                        }
                    }
                    boolean found = false;
                    for (World world : Bukkit.getWorlds()) {
                        if (world.getGenerator() != null && world.getGenerator().getClass().getName().equalsIgnoreCase(LuckyBlockWorld.class.getName())) {
                            found = true;
                        }
                    }
                    if (!found) {
                        String g = "";
                        int t = -1;
                        for (World w : Bukkit.getWorlds()) {
                            if (w.getGenerator() != null && w.getGenerator().getClass().getName().equalsIgnoreCase(LuckyBlockWorld.class.getName())) {
                                if (w.getName().startsWith("luckyblockworld")) {
                                    String[] d = w.getName().split("luckyblockworld");
                                    if (d.length == 2) {
                                        try {
                                            int h = Integer.parseInt(d[1]);
                                            if (h > t) {
                                                h = t;
                                            }
                                        } catch (NumberFormatException ex) {
                                            //
                                        }
                                    }
                                }
                            }
                        }
                        if (t > -1) {
                            g = "" + t;
                        }
                        WorldCreator creator = new WorldCreator("luckyblockworld");
                        World world = Bukkit.createWorld(creator);
                        world.setMonsterSpawnLimit(25);
                        world.setAnimalSpawnLimit(30);
                        world.save();
                        sender.sendMessage(green + "Created world!");
                    }
                    if (sender instanceof Player) {
                        World world = null;
                        for (int x = 0; x < Bukkit.getWorlds().size(); x++) {
                            World w = Bukkit.getWorlds().get(x);
                            if (w.getGenerator() != null && w.getGenerator().getClass().getName().equalsIgnoreCase(LuckyBlockWorld.class.getName())) {
                                world = w;
                                x = Bukkit.getWorlds().size();
                            }
                        }
                        if (world != null) {
                            Player player = (Player) sender;
                            player.teleport(new Location(world, 0, world.getHighestBlockYAt(0, 0), 0));
                        }
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("rules")) {
                if (!sender.hasPermission("lb.commands.rules")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                sender.sendMessage(LuckyBlock.rules);

            } else if (args[0].equalsIgnoreCase("getfile")) {
                if (!sender.hasPermission("lb.commands.getfile")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 4 || args.length > 4) {
                    sender.sendMessage(getMessage("InvalidArgs"));
                } else {
                    File[] files = LuckyBlock.instance.configf.getParentFile().listFiles();
                    File file = null;
                    for (File f : files) {
                        if (args[1].equalsIgnoreCase(f.getName())) {
                            file = f;
                        }
                    }
                    if (file != null) {
                        String type = "string";
                        boolean done = false;
                        try {
                            int g = Integer.parseInt(args[3]);
                            type = "int";
                            done = true;
                        } catch (Exception ex) {
//
                        }
                        if (!done) {
                            try {
                                double g = Double.parseDouble(args[3]);
                                type = "double";
                                done = true;
                            } catch (Exception ex) {
//
                            }
                        }
                        if (!done) {
                            if (args[3].startsWith("[") && args[3].endsWith("]")) {
                                type = "list";
                            }
                        }
                        if (args[3].equalsIgnoreCase("false") || args[3].equalsIgnoreCase("true")) {
                            type = "boolean";
                        }
                        FileConfiguration f = YamlConfiguration.loadConfiguration(file);
                        if (type.equalsIgnoreCase("string")) {
                            f.set(args[2], args[3]);
                        } else if (type.equalsIgnoreCase("boolean")) {
                            f.set(args[2], Boolean.parseBoolean(args[3]));
                        } else if (type.equalsIgnoreCase("int")) {
                            f.set(args[2], Integer.parseInt(args[3]));
                        } else if (type.equalsIgnoreCase("double")) {
                            f.set(args[2], Double.parseDouble(args[3]));
                        } else if (type.equalsIgnoreCase("list")) {
                            String str = args[3];
                            str = str.replaceAll("\\[", "").replaceAll("\\]", "");
                            String[] d = str.split(",,");
                            List<String> list = new ArrayList<String>();
                            for (String s : d) {
                                list.add(s);
                            }
                            f.set(args[2], list);
                        }
                        try {
                            f.save(file);
                            sender.sendMessage(getMessage("GetFile.Success"));
                        } catch (IOException e) {
                            sender.sendMessage(getMessage("UnknownError"));
                        }
                    }
                }

            } else if (args[0].equalsIgnoreCase("gui")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                Gui.openLBGui(player, true);

            } else {
                sender.sendMessage(thelp);
            }
        }
        return false;
    }
}
