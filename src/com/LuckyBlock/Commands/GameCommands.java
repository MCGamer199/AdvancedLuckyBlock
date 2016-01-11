package com.LuckyBlock.Commands;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Events.Game;
import com.LuckyBlock.Events.Gui;
import com.LuckyBlock.War.War;
import com.LuckyBlock.enums.WarType;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * <b>GameCommands</b>
 * <p>
 * This library was created by @MCGamer199 for Lucky Block
 * <p>
 * You are can use it and modify it under the following conditions:
 * <ul>
 * <li>Don't claim this class as your own
 * <li>Don't remove this disclaimer
 * </ul>
 * <p>
 * <i>Game Commands</i>
 *
 * @author MCGamer199
 */

@SuppressWarnings({"unused"})
public class GameCommands implements CommandExecutor {


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
    String invalid = LuckyBlockCommand.invalid;
    String error = LuckyBlockCommand.error;
    String noperm = LuckyBlockCommand.noperm;
    String num = LuckyBlockCommand.num;
    String wcmd = LuckyBlockCommand.lwcmd;
    int pages = 4;
    String thelp = getMessage("InvalidCommand1");

    /**
     * Returns Message from Messages.yml file.
     *
     * @param loc
     * @return
     */
    public static String getMessage(String loc) {
        String s = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString(loc));
        s = s.replace("<lbwcmd>", LuckyBlockCommand.lwcmd);
        s = s.replace("<lbcmd>", LuckyBlockCommand.lcmd);
        s = s.replace("<tcmd>", LuckyBlockCommand.tcmd);
        return s;
    }

    /**
     * Lucky Block War Commands.
     */
    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lbw.commands")) {
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
                    sender.sendMessage(blue + "/" + wcmd + darkpurple + " addDBlock " + green + "[ID] [MaterialID]; " + yellow + "");
                    sender.sendMessage(blue + "/" + wcmd + darkpurple + " changename; " + yellow + "Changes Player's Name.");
                    sender.sendMessage(blue + "/" + wcmd + darkpurple + " cleargames; " + yellow + "Removes All Games.");
                    sender.sendMessage(blue + "/" + wcmd + darkpurple + " clearspawns " + green + "[ID]; " + yellow + "Removes All Spawns.");
                    sender.sendMessage(blue + "/" + wcmd + darkpurple + " endgame " + green + "[ID]; " + yellow + "Ends Game.");
                    sender.sendMessage(blue + "/" + wcmd + darkpurple + " forcestart " + green + "[ID]; " + yellow + "Forces the game to start.");
                    sender.sendMessage(blue + "/" + wcmd + darkpurple + " getplayer " + green + "[Player]" + darkpurple + "; " + yellow + "Shows player info.");
                    sender.sendMessage(aqua + "-------------------------------");

                } else if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("1")) {
                        sender.sendMessage(aqua + "------ Showing help page ------");
                        sender.sendMessage(aqua + "Page 1/" + pages);
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " addDBlock " + green + "[ID] [MaterialID]; " + yellow + "");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " changename; " + yellow + "Changes Player's Name.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " cleargames; " + yellow + "Removes All Games.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " clearspawns " + green + "[ID]; " + yellow + "Removes All Spawns.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " endgame " + green + "[ID]" + "; " + yellow + "Ends Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " forcestart " + green + "[ID]; " + yellow + "Forces the game to start.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " getplayer " + green + "[Player]" + darkpurple + "; " + yellow + "Shows player info.");
                        sender.sendMessage(aqua + "-------------------------------");

                    } else if (args[1].equalsIgnoreCase("2")) {
                        sender.sendMessage(aqua + "------ Showing help page ------");
                        sender.sendMessage(aqua + "Page " + args[1] + "/" + pages);
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " join " + green + "[ID]" + darkpurple + "; " + yellow + "Join Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " leave; " + yellow + "To Leave the Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " lobby " + green + "[Player]" + darkpurple + "; " + yellow + "Teleports player to main lobby.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " removegame " + green + "[ID]" + darkpurple + "; " + yellow + "Removes Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " save " + green + "[ID]" + darkpurple + "; " + yellow + "Saves World for arena.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setallowgates " + green + "[ID] [true/false]; " + yellow + "Changes allowgates option.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setbounds " + green + "[ID] [1/2]" + darkpurple + "; " + yellow + "Sets Bounds for game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setcenter " + green + "[ID]" + darkpurple + "; " + yellow + "Sets the center.");
                        sender.sendMessage(aqua + "-------------------------------");

                    } else if (args[1].equalsIgnoreCase("3")) {
                        sender.sendMessage(aqua + "------ Showing help page ------");
                        sender.sendMessage(aqua + "Page " + args[1] + "/" + pages);
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setenabled " + green + "[ID] [true/false]" + darkpurple + "; " + yellow
                                + "Enables or Disables Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setgamename " + green + "[ID] (Name)" + darkpurple + "; " + yellow + "Sets name for game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setlevel " + green + "(Level) [Player]" + darkpurple + "; " + yellow + "Sets Level.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setlobby " + green + "[ID]" + darkpurple + "; " + yellow + "Sets Lobby for Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setmainlobby; " + yellow + "Sets Main Lobby.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setmaxplayers " + green + "[ID] (Number)" + darkpurple + "; " + yellow +
                                "Sets Maximum Players For Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setminplayers " + green + "[ID] (Number)" + darkpurple + "; " + yellow +
                                "Sets Mininmum Players For Game.");
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " setspawn " + green + "[ID] [Number]" + darkpurple + "; " + yellow + "Sets Player's Spawn.");
                        sender.sendMessage(aqua + "-------------------------------");

                    } else if (args[1].equalsIgnoreCase("4")) {
                        sender.sendMessage(aqua + "------ Showing help page ------");
                        sender.sendMessage(aqua + "Page " + args[1] + "/" + pages);
                        sender.sendMessage(blue + "/" + wcmd + darkpurple + " settype " + green + "[ID] [Type]; " + yellow + "Changes type option.");
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
            } else if (args[0].equalsIgnoreCase("leave")) {
                if (!sender.hasPermission("lbw.leave")) {
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
                    if (War.getGame(uuid) != null) {
                        War war = War.getGame(uuid);
                        if (war.inGame()) {
                            if (war.getPlayers().size() > 1) {
                                war.leaveGame(player.getUniqueId(), true, "false");
                            } else {
                                return true;
                            }
                        } else {
                            war.leaveGame(player.getUniqueId(), true, "else");
                        }
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            String g = getMessage("LeaveGame.Success.Players.1");
                            g = g.replace("%Player%", player.getName());
                            String h = getMessage("LeaveGame.Success.Players.2");
                            h = h.replace("%PlayersLeft%", "" + war.getPlayers().size());
                            if (p.getUniqueId() != player.getUniqueId()) {
                                if (War.containPlayer(p.getUniqueId())) {
                                    p.sendMessage(g);
                                }
                            }
                            if (war.inGame() == true) {
                                p.sendMessage(h);
                            }
                        }
                        player.sendMessage(getMessage("LeaveGame.Success.Player"));
                    } else {
                        player.sendMessage(getMessage("LeaveGame.Fail"));
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("forcestart")) {
                if (!sender.hasPermission("lbw.commands.forcestart")) {
                    sender.sendMessage(noperm);
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("ForceStart.InvalidUsage"));
                } else if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            if (war.getPlayers().size() > 0) {
                                if (!war.inGame()) {
                                    String g = getMessage("ForceStart.Success");
                                    g = g.replace("%ID%", "" + id);
                                    sender.sendMessage(g);
                                    war.StartGame();
                                }
                            }
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setmainlobby")) {
                if (!sender.hasPermission("lbw.commands.setmainlobby")) {
                    sender.sendMessage(noperm);
                }
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    Location loc = player.getLocation();
                    LuckyBlockAPI.mainlobby = loc;
                    double x = loc.getX();
                    double y = loc.getY();
                    double z = loc.getZ();
                    String world = loc.getWorld().getName();
                    float pitch = loc.getPitch();
                    float yaw = loc.getYaw();
                    LuckyBlock.instance.game.set("Location.world", world);
                    LuckyBlock.instance.game.set("Location.x", x);
                    LuckyBlock.instance.game.set("Location.y", y);
                    LuckyBlock.instance.game.set("Location.z", z);
                    LuckyBlock.instance.game.set("Location.pitch", pitch);
                    LuckyBlock.instance.game.set("Location.yaw", yaw);
                    LuckyBlockAPI.saveConfigs();
                    player.sendMessage(getMessage("SetMainLobby.Success"));
                } else if (args.length > 1 && args.length < 5) {
                    sender.sendMessage(getMessage("SetMainLobby.InvalidUsage"));
                } else if (args.length == 5) {
                    try {
                        String world = args[1];
                        int x = Integer.parseInt(args[2]);
                        int y = Integer.parseInt(args[3]);
                        int z = Integer.parseInt(args[4]);
                        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
                        LuckyBlockAPI.mainlobby = loc;
                        LuckyBlock.instance.game.set("Location.world", world);
                        LuckyBlock.instance.game.set("Location.x", x);
                        LuckyBlock.instance.game.set("Location.y", y);
                        LuckyBlock.instance.game.set("Location.z", z);
                        LuckyBlock.instance.game.set("Location.pitch", loc.getPitch());
                        LuckyBlock.instance.game.set("Location.yaw", loc.getYaw());
                        LuckyBlockAPI.saveConfigs();
                        sender.sendMessage(getMessage("SetMainLobby.Success"));
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setlobby")) {
                if (!sender.hasPermission("lbw.commands.setlobby")) {
                    sender.sendMessage(noperm);
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length == 1) {
                    player.sendMessage(getMessage("SetLobby.InvalidUsage"));
                } else if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            war.setLobby(player.getLocation());
                            war.save();
                            String g = getMessage("SetLobby.Success");
                            g = g.replace("%ID%", "" + id);
                            player.sendMessage(g);
                        } else {
                            player.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    player.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setmaxplayers")) {
                if (!sender.hasPermission("lbw.commands.setmaxplayers")) {
                    sender.sendMessage(noperm);
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetMaxPlayers.InvalidUsage"));
                } else if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        int num = Integer.parseInt(args[2]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            war.setMaxPlayers(num);
                            String loc = LuckyBlockAPI.getGameFile(id);
                            war.reloadSigns();
                            war.save();
                            String g = getMessage("SetMaxPlayers.Success");
                            g = g.replace("%MaxPlayers%", "" + num);
                            g = g.replace("%ID%", "" + id);
                            sender.sendMessage(g);
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("lobby")) {
                if (!sender.hasPermission("lbw.commands.lobby")) {
                    sender.sendMessage(noperm);
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                if (args.length == 1) {
                    Player player = (Player) sender;
                    if (LuckyBlockAPI.mainlobby != null) {
                        Location loc = LuckyBlockAPI.mainlobby;
                        player.sendMessage(getMessage("Lobby.Success.1"));
                        if (War.getGame(player.getUniqueId()) != null) {
                            War war = War.getGame(player.getUniqueId());
                            if (war.inGame()) {
                                if (war.getPlayers().size() > 1) {
                                    war.leaveGame(player.getUniqueId(), true, "false");
                                } else {
                                    return true;
                                }
                            } else {
                                war.leaveGame(player.getUniqueId(), true, "else");
                            }
                        }
                        player.teleport(loc);
                    } else {
                        player.sendMessage(getMessage("Lobby.InvalidLobby"));
                    }
                } else if (args.length == 2) {
                    Player player = (Player) sender;
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        String g = getMessage("InvalidPlayer");
                        g = g.replace("%Target%", args[1]);
                        player.sendMessage(g);
                        return true;
                    }
                    if (LuckyBlockAPI.mainlobby != null) {
                        Location loc = LuckyBlockAPI.mainlobby;
                        target.teleport(loc);
                        if (target.getUniqueId() != player.getUniqueId()) {
                            String g = getMessage("Lobby.Success.2");
                            g = g.replace("%Target%", target.getName());
                            player.sendMessage(g);
                        }
                        target.sendMessage(getMessage("Lobby.Success.1"));
                    } else {
                        player.sendMessage(getMessage("Lobby.InvalidLobby"));
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("removegame")) {
                if (!sender.hasPermission("lbw.commands.removegame")) {
                    sender.sendMessage(noperm);
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("RemoveGame.InvalidUsage"));
                } else if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            String g = getMessage("RemoveGame.Success");
                            g = g.replace("%ID%", "" + id);
                            sender.sendMessage(g);
                            war.remove();
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("cleargames")) {
                if (!sender.hasPermission("lbw.commands.cleargames")) {
                    sender.sendMessage(noperm);
                }
                if (War.listWars.size() > 0) {
                    int count = LuckyBlock.instance.game.getConfigurationSection("Games").getKeys(false).size();
                    LuckyBlockAPI.saveConfigs();
                    String g = getMessage("ClearGames.Success");
                    g = g.replace("%Count%", "" + count);
                    for (int x = 0; x < War.listWars.size(); x = 0) {
                        War.listWars.get(x).remove();
                    }
                    LuckyBlock.instance.game.set("Games", null);
                    sender.sendMessage(g);
                } else {
                    sender.sendMessage(getMessage("ClearGames.NoGame"));
                }

            } else if (args[0].equalsIgnoreCase("setspawn")) {
                if (!sender.hasPermission("lbw.commands.setspawn")) {
                    sender.sendMessage(noperm);
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length < 3) {
                    player.sendMessage(getMessage("SetSpawn.InvalidUsage"));
                } else if (args.length == 3) {
                    Location loc = player.getLocation();
                    try {
                        int id = Integer.parseInt(args[1]);
                        int num = Integer.parseInt(args[2]);
                        if (num < 1) {
                            player.sendMessage(this.num);
                            return true;
                        }
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            if (num > 1) {
                                if (war.getSpawns()[num - 2] == null) {
                                    sender.sendMessage(this.num);
                                    return true;
                                }
                            }
                            List<Location> lc = new ArrayList<Location>();
                            int po = 0;
                            for (int o = 1; o < num; o++) {
                                if (war.getSpawns()[o - 1] != null) {
                                    lc.add(war.getSpawns()[o - 1]);
                                    po++;
                                }
                            }
                            lc.add(loc);
                            po++;
                            for (int p = po; p < war.getSpawns().length; p++) {
                                lc.add(war.getSpawns()[p]);
                            }
                            war.setSpawns(lc);
                            if (war.getWorld() == null) {
                                war.setWorld(war.getSpawns()[0].getWorld());
                            }
                            List<String> locations = new ArrayList<String>();
                            for (int a = 0; a < lc.size(); a++) {
                                if (lc.get(a) != null) {
                                    String na = lc.get(a).getWorld().getName();
                                    double bx = lc.get(a).getX();
                                    double by = lc.get(a).getY();
                                    double bz = lc.get(a).getZ();
                                    float pi = lc.get(a).getPitch();
                                    float ya = lc.get(a).getYaw();
                                    locations.add(na + "," + bx + "," + by + "," + bz + "," + pi + "," + ya);
                                }
                            }
                            war.save();
                            String g = getMessage("SetSpawn.Success");
                            g = g.replace("%ID%", "" + id);
                            g = g.replace("%Number%", "" + num);
                            player.sendMessage(g);
                        } else {
                            player.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        player.sendMessage(num);
                    }
                } else {
                    player.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setenabled")) {
                if (!sender.hasPermission("lbw.commands.setenabled")) {
                    sender.sendMessage(noperm);
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetEnabled.InvalidUsage"));
                } else if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            boolean is = false;
                            if (args[2].equalsIgnoreCase("true")) {
                                is = true;
                            } else if (args[2].equalsIgnoreCase("false")) {
                                is = false;
                            } else {
                                sender.sendMessage(getMessage("SetEnabled.InvalidUsage"));
                                return true;
                            }
                            war.setEnabled(is);
                            war.save();
                            String g = getMessage("SetEnabled.Success");
                            g = g.replace("%ID%", "" + id);
                            g = g.replace("%IsEnabled%", "" + is);
                            sender.sendMessage(g);
                            war.reloadSigns();
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }
            } else if (args[0].equalsIgnoreCase("endgame")) {
                if (!sender.hasPermission("lbw.commands.endgame")) {
                    sender.sendMessage(noperm);
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("EndGame.InvalidUsage"));
                } else if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            if (war.inGame() == true) {
                                if (war.getPlayers().size() > 1) {
                                    war.EndGame(false);
                                    String g = getMessage("EndGame.Success");
                                    g = g.replace("%ID%", "" + id);
                                    sender.sendMessage(g);
                                } else {
                                    return true;
                                }
                            } else {
                                sender.sendMessage(getMessage("EndGame.AlreadyEnded"));
                            }
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setminplayers")) {
                if (!(sender.hasPermission("lbw.commands.setminplayers"))) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetMinPlayers.InvalidUsage"));
                } else if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        int num = Integer.parseInt(args[2]);
                        if (War.getGame(id) == null) {
                            sender.sendMessage(getMessage("InvalidGame"));
                            return true;
                        }
                        War war = War.getGame(id);
                        war.setMinPlayers(num);
                        war.save();
                        String g = getMessage("SetMinPlayers.Success");
                        g = g.replace("%ID%", "" + id);
                        g = g.replace("%Number%", "" + num);
                        sender.sendMessage(g);
                        LuckyBlockAPI.saveConfigs();
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("join")) {
                if (!(sender.hasPermission("lbw.join"))) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) == null) {
                            player.sendMessage(getMessage("InvalidGame"));
                            return true;
                        }
                        Game.joinGame(player, id, true);
                    } catch (NumberFormatException ex) {
                        player.sendMessage(num);
                    }
                } else {
                    player.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("changename")) {
                if (!sender.hasPermission("lbw.changname.me")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("ChangeName.InvalidUsage"));
                    return true;
                } else if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    if (!player.hasPermission("lbw.colorcodes")) {
                        LuckyBlockAPI.cname.put(player.getUniqueId(), ChatColor.stripColor(args[1]));
                    } else {
                        LuckyBlockAPI.cname.put(player.getUniqueId(), ChatColor.translateAlternateColorCodes('&', args[1]));
                    }
                    LuckyBlockAPI.savePlayerData(player.getUniqueId(), player.getName());
                } else if (args.length == 3) {
                    if (!sender.hasPermission("lbw.changename.others")) {
                        sender.sendMessage(noperm);
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null) {
                        String g = getMessage("InvalidPlayer");
                        g = g.replace("%Target%", args[2]);
                        g = ChatColor.translateAlternateColorCodes('&', g);
                        sender.sendMessage(g);
                        return true;
                    }
                    if (!sender.hasPermission("lbw.colorcodes")) {
                        LuckyBlockAPI.cname.put(target.getUniqueId(), ChatColor.stripColor(args[1]));
                    } else {
                        LuckyBlockAPI.cname.put(target.getUniqueId(), ChatColor.translateAlternateColorCodes('&', args[1]));
                    }
                    LuckyBlockAPI.savePlayerData(target.getUniqueId(), target.getName());
                } else {
                    sender.sendMessage(error);
                    return true;
                }
                String g = getMessage("ChangeName.Success");
                g = g.replace("%Name%", args[1]);
                g = ChatColor.translateAlternateColorCodes('&', g);
                sender.sendMessage(g);

            } else if (args[0].equalsIgnoreCase("setlevel")) {
                if (!sender.hasPermission("lbw.commands.setlevel")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("SetLevel.InvalidUsage"));
                } else if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    UUID uuid = player.getUniqueId();
                    try {
                        int level = Integer.parseInt(args[1]);
                        if (LuckyBlockAPI.playerlevel.containsKey(uuid)) {
                            int[] i = LuckyBlockAPI.playerlevel.get(uuid);
                            i[0] = level;
                            LuckyBlockAPI.playerlevel.put(uuid, i);
                        } else {
                            int[] i = new int[2];
                            i[0] = level;
                            i[1] = 0;
                            LuckyBlockAPI.playerlevel.put(uuid, i);
                        }
                        LuckyBlockAPI.savePlayerData(uuid, player.getName());
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else if (args.length == 3) {

                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("getplayer")) {
                if (!sender.hasPermission("lbw.getplayer")) {
                    sender.sendMessage(noperm);
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length == 1) {
                    player.sendMessage(getMessage("GetPlayer.InvalidUsage"));
                } else if (args.length > 1) {
                    String t = "";
                    for (int x = 0; x < args.length; x++) {
                        if (x > 0) {
                            if (x == 1) {
                                t = args[x];
                            } else {
                                t = t + " " + args[x];
                            }
                        }
                    }
                    int loc = -1;
                    ConfigurationSection cs = LuckyBlock.instance.data.getConfigurationSection("Players");
                    try {
                        for (int x = 0; x < cs.getKeys(false).toArray().length; x++) {
                            if (cs.getString(cs.getKeys(false).toArray()[x].toString() + ".Name").equalsIgnoreCase(t)) {
                                loc = x;
                            }
                        }
                    } catch (Exception ex) {
                        //
                    }
                    if (loc != -1) {
                        LuckyBlockAPI.openGui(player, loc);
                        String g = getMessage("GetPlayer.Success");
                        g = g.replace("%Player%", t);
                        player.sendMessage(g);
                    } else {
                        String g = getMessage("InvalidPlayer");
                        g = g.replace("%Target%", t);
                        sender.sendMessage(g);
                    }
                }

            } else if (args[0].equalsIgnoreCase("save")) {
                if (!sender.hasPermission("lbw.save")) {
                    sender.sendMessage(noperm);
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("Save.InvalidUsage"));
                } else if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            if (war.getSpawns().length > 0) {
                                Location loc = war.getSpawns()[0];
                                World world = loc.getWorld();
                                try {
                                    world.save();
                                } catch (Exception ex) {
                                    //
                                }
                                world.setAutoSave(false);
                                LuckyBlock.instance.registerChests(war);
                                String g = getMessage("Save.Success");
                                g = g.replace("%ID%", "" + id);
                                sender.sendMessage(g);
                            } else {
                                sender.sendMessage(getMessage("InvalidGame"));
                            }
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setbounds")) {
                if (!sender.hasPermission("lbw.commands.setbounds")) {
                    sender.sendMessage(noperm);
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetBounds.InvalidUsage"));
                } else if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            try {
                                int number = Integer.parseInt(args[2]);
                                Location loc = player.getLocation();
                                if (number == 1) {
                                    war.setFirstpos(loc);
                                } else if (number == 2) {
                                    war.setSecpos(loc);
                                } else {
                                    player.sendMessage(getMessage("SetBounds.Error"));
                                    return true;
                                }
                                String g = getMessage("SetBounds.Success");
                                g = g.replace("%ID%", "" + id);
                                sender.sendMessage(g);
                                if (number == 1) {
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".FirstPos.world", loc.getWorld().getName());
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".FirstPos.x", loc.getBlockX());
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".FirstPos.y", loc.getBlockY());
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".FirstPos.z", loc.getBlockZ());
                                } else {
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".SecPos.world", loc.getWorld().getName());
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".SecPos.x", loc.getBlockX());
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".SecPos.y", loc.getBlockY());
                                    LuckyBlock.instance.game.set(LuckyBlockAPI.getGameFile(id) + ".SecPos.z", loc.getBlockZ());
                                }
                                LuckyBlockAPI.saveConfigs();
                            } catch (NumberFormatException ex) {
                                sender.sendMessage(num);
                                return true;
                            }
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("replay")) {
                if (!(sender instanceof Player)) {
                    return true;
                }
                Player player = (Player) sender;
                if (LuckyBlock.instance.data.getString("Players." + player.getUniqueId() + ".LastGameID") != null) {
                    int id = LuckyBlock.instance.data.getInt("Players." + player.getUniqueId() + ".LastGameID");
                    try {
                        Game.joinGame(player, id, true);
                    } catch (Exception ex) {
                        //
                    }
                } else {
                    player.sendMessage(getMessage("InvalidGame"));
                }

            } else if (args[0].equalsIgnoreCase("setgamename")) {
                if (!sender.hasPermission("lbw.commands.setgamename")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetGameName.InvalidUsage"));
                } else {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            String d = "";
                            for (int x = 0; x < args.length; x++) {
                                if (x > 1) {
                                    if (x == 2) {
                                        d = args[x];
                                    } else {
                                        d = d + " " + args[x];
                                    }
                                }
                            }
                            War war = War.getGame(id);
                            war.setName(d);
                            war.reloadSigns();
                            war.save();
                            String g = getMessage("SetGameName.Success");
                            g = g.replace("%Name%", d);
                            sender.sendMessage(g);
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                }

            } else if (args[0].equalsIgnoreCase("setcenter")) {
                if (!sender.hasPermission("lbw.commands.setcenter")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length == 1) {
                    player.sendMessage(getMessage("SetCenter.InvalidUsage"));
                } else if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            war.setCenter(player.getLocation());
                            player.sendMessage(getMessage("SetCenter.Success"));
                        } else {
                            player.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("endall")) {
                if (!sender.hasPermission("lbw.commands.endall")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                int total = 0;
                for (int x = 0; x < War.listWars.size(); x++) {
                    War war = War.listWars.get(x);
                    if (war.inGame()) {
                        war.EndGame(false);
                        total++;
                    }
                }
                String g = getMessage("Endall.Success");
                g = g.replace("%Total%", "" + total);
                sender.sendMessage(g);

            } else if (args[0].equalsIgnoreCase("randommap")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    int found = 0;
                    if (War.listWars.size() < 1) {
                        player.sendMessage(red + "No game found!");
                        return true;
                    }
                    int r = LuckyBlock.randoms.nextInt(War.listWars.size());
                    if (Game.joinGame(player, War.listWars.get(r).getId(), false)) {
                        found = 1;
                    }
                    if (found == 0) {
                        for (int x = 0; x < War.listWars.size(); x++) {
                            War war = War.listWars.get(x);
                            if (Game.joinGame(player, war.getId(), false)) {
                                found = 1;
                            }
                        }
                    }
                    if (found == 0) {
                        player.sendMessage(getMessage("RandomMap.Error"));
                    }
                }

            } else if (args[0].equalsIgnoreCase("tpworld")) {
                if (!sender.hasPermission("lbw.commands.tpworld")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (args.length == 2) {
                        try {
                            int id = Integer.parseInt(args[1]);
                            if (War.getGame(id) != null) {
                                War war = War.getGame(id);
                                if (war.getSpawns()[0] != null) {
                                    player.teleport(war.getSpawns()[0]);
                                    player.sendMessage(getMessage("TpWorld.Success"));
                                }
                            }
                        } catch (NumberFormatException ex) {
                            player.sendMessage(num);
                        }
                    }
                }

            } else if (args[0].equalsIgnoreCase("adddblock")) {
                if (!sender.hasPermission("lbw.commands.adddblock")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("AddDBlock.InvalidUsage"));
                } else if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            int type = Integer.parseInt(args[2]);
                            if (Material.getMaterial(type) != null) {
                                War war = War.getGame(id);
                                war.addDangerBlock(type);
                                war.save();
                                String g = getMessage("AddDBlock.Success");
                                g = g.replace("%Block%", Material.getMaterial(type).toString());
                                sender.sendMessage(g);
                            } else {
                                sender.sendMessage(getMessage("AddDBlock.InvalidBlock"));
                            }
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("clearspawns")) {
                if (!sender.hasPermission("lbw.commands.clearspawns")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("ClearSpawns.InvalidUsage"));
                } else if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            War war = War.getGame(id);
                            for (int x = 0; x < war.getSpawns().length; x++) {
                                war.getSpawns()[x] = null;
                            }
                            war.save();
                            sender.sendMessage(getMessage("ClearSpawns.Success"));
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("settype")) {
                if (!sender.hasPermission("lbw.commands.settype")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetType.InvalidUsage"));
                } else if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            String type = args[2];
                            try {
                                War.getGame(id).setType(WarType.valueOf(type.toUpperCase()));
                                War.getGame(id).save();
                                sender.sendMessage(getMessage("SetType.Success"));
                            } catch (Exception ex) {
                                sender.sendMessage(getMessage("SetType.InvalidType"));
                            }
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("setallowgates")) {
                if (!sender.hasPermission("lbw.commands.setallowgates")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length < 3) {
                    sender.sendMessage(getMessage("SetAllowGates.InvalidUsage"));
                } else if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        if (War.getGame(id) != null) {
                            if (args[2].equalsIgnoreCase("true")) {
                                War.getGame(id).setAllowGates(true);
                                War.getGame(id).save();
                                sender.sendMessage(getMessage("SetAllowGates.Success"));
                            } else if (args[2].equalsIgnoreCase("false")) {
                                War.getGame(id).setAllowGates(false);
                                War.getGame(id).save();
                                sender.sendMessage(getMessage("SetAllowGates.Success"));
                            } else {
                                sender.sendMessage(getMessage("SetAllowGates.InvalidUsage"));
                            }
                        } else {
                            sender.sendMessage(getMessage("InvalidGame"));
                        }
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(num);
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("options")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (War.containPlayer(player.getUniqueId())) {
                        Gui.openOptions(player);
                    }
                }

            } else if (args[0].equalsIgnoreCase("generate")) {
                if (sender instanceof Player) {
                    LuckyBlockAPI.GenerateLand(((Player) sender).getLocation());
                }

            } else {
                sender.sendMessage(thelp);
            }
        }
        return false;
    }


}
