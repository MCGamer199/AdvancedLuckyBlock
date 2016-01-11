package com.LuckyBlock.Commands;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Resources.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TeamCommands implements CommandExecutor {


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
    //String[] help = new String[10];
    String t = LuckyBlockCommand.tcmd;

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("teams.commands")) {
            sender.sendMessage(noperm);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(lightpurple + "Showing Help");
            sender.sendMessage(green + "/" + t + " accept " + gold + "[Player]");
            sender.sendMessage(green + "/" + t + " clear");
            sender.sendMessage(green + "/" + t + " create " + gold + "[TeamName]");
            sender.sendMessage(green + "/" + t + " join " + gold + "[Name]");
            sender.sendMessage(green + "/" + t + " kick " + gold + "[Player]");
            sender.sendMessage(green + "/" + t + " leave");
            sender.sendMessage(green + "/" + t + " remove " + gold + "[Name]");
            sender.sendMessage(green + "/" + t + " requests");
            sender.sendMessage(green + "/" + t + " setowner " + gold + "[Team] [Player]");

        } else {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(lightpurple + "Showing Help");
                sender.sendMessage(lightpurple + "Showing Help");
                sender.sendMessage(green + "/" + t + " accept " + gold + "[Player]");
                sender.sendMessage(green + "/" + t + " clear");
                sender.sendMessage(green + "/" + t + " create " + gold + "[TeamName]");
                sender.sendMessage(green + "/" + t + " join " + gold + "[Name]");
                sender.sendMessage(green + "/" + t + " kick " + gold + "[Player]");
                sender.sendMessage(green + "/" + t + " leave");
                sender.sendMessage(green + "/" + t + " remove " + gold + "[Name]");
                sender.sendMessage(green + "/" + t + " requests");
                sender.sendMessage(green + "/" + t + " setowner " + gold + "[Team] [Player]");

            } else if (args[0].equalsIgnoreCase("create")) {
                if (!sender.hasPermission("teams.create")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("CreateTeam.InvalidUsage"));
                } else if (args.length == 2) {
                    for (int x = 0; x < LuckyBlockAPI.teams.size(); x++) {
                        Team t = LuckyBlockAPI.teams.get(x);
                        if (t.getName().equalsIgnoreCase(args[1])) {
                            sender.sendMessage(getMessage("Team.Error3"));
                            return true;
                        }
                    }
                    Team team = new Team(args[1], LuckyBlock.randoms.nextInt(9999) + 1);
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        if (!Team.isPlayerInTeam(p.getUniqueId())) {
                            team.setOwner(p.getUniqueId());
                            team.addPlayer(p.getUniqueId());
                        }
                    }
                    team.save();
                    sender.sendMessage(getMessage("CreateTeam.Success"));
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("remove")) {
                if (!sender.hasPermission("teams.remove")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("RemoveTeam.InvalidUsage"));
                } else if (args.length == 2) {
                    String name = args[1];
                    if (Team.fromName(name) != null) {
                        Team team = Team.fromName(name);
                        team.dispose();
                        sender.sendMessage(getMessage("RemoveTeam.Success"));
                    } else {
                        sender.sendMessage(getMessage("InvalidTeam"));
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("clear")) {
                if (!sender.hasPermission("teams.clear")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    if (LuckyBlock.instance.data.getConfigurationSection("Teams") != null
                            && LuckyBlock.instance.data.getConfigurationSection("Teams").getKeys(false).toArray().length > 0) {
                        String g = getMessage("ClearTeams.Success");
                        g = g.replace("%Number%", "" + LuckyBlockAPI.teams.size());
                        sender.sendMessage(g);
                    } else {
                        sender.sendMessage(getMessage("ClearTeams.NoTeamFound"));
                    }
                    for (int x = 0; x < LuckyBlockAPI.teams.size(); x = 0) {
                        Team t = LuckyBlockAPI.teams.get(x);
                        t.dispose();
                    }
                    LuckyBlock.instance.data.set("Teams", null);
                    LuckyBlockAPI.saveConfigs();
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("join")) {
                if (!sender.hasPermission("teams.join")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                if (args.length == 1) {
                    sender.sendMessage(getMessage("JoinTeam.InvalidUsage"));
                } else if (args.length == 2) {
                    String name = args[1];
                    UUID uuid = player.getUniqueId();
                    if (Team.fromName(name) != null) {
                        if (Team.isPlayerInTeam(uuid) == false) {
                            if (Team.fromName(name).getPlayers().size() < 2) {
                                Team team = Team.fromName(name);
                                team.addRequest(player.getName());
                                team.save();
                                player.sendMessage(getMessage("JoinTeam.SendRequest"));
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getUniqueId() == team.getOwner()) {
                                        String g = getMessage("JoinTeam.SendRequest1");
                                        g = g.replace("%Player%", player.getName());
                                        p.sendMessage(g);
                                    }
                                }
                            } else {
                                sender.sendMessage(getMessage("Team.Full"));
                            }
                        } else {
                            sender.sendMessage(getMessage("Team.Error"));
                        }
                    } else {
                        sender.sendMessage(getMessage("InvalidTeam"));
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("leave")) {
                if (!sender.hasPermission("teams.leave")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                UUID uuid = player.getUniqueId();
                if (args.length == 1) {
                    if (Team.isPlayerInTeam(uuid)) {
                        Team team = Team.fromPlayer(uuid);
                        team.removePlayer(uuid);
                        team.save();
                        player.sendMessage(getMessage("LeaveTeam.Success"));
                    } else {
                        sender.sendMessage(getMessage("Team.Error1"));
                    }
                } else {
                    player.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("requests")) {
                if (!sender.hasPermission("teams.requests")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                UUID uuid = player.getUniqueId();
                if (Team.fromPlayer(uuid) != null) {
                    Team team = Team.fromPlayer(uuid);
                    if (team.getOwner() != null && team.getOwner().toString().equalsIgnoreCase(uuid.toString())) {
                        String g = getMessage("Requests.Error");
                        if (team.getRequests().size() > 0) {
                            if (args.length == 1) {
                                for (int x = 0; x < 8; x++) {
                                    if (team.getRequests().size() > x) {
                                        player.sendMessage(gold + team.getRequest(x));
                                    }
                                }
                            } else if (args.length == 2) {
                                try {
                                    int p = Integer.parseInt(args[1]);
                                    int s = p * 8;
                                    for (int x = (s - 8); x < s; x++) {
                                        if (team.getRequests().size() > x) {
                                            player.sendMessage(gold + team.getRequest(x));
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    sender.sendMessage(num);
                                }
                            } else {
                                sender.sendMessage(error);
                            }
                        } else {
                            player.sendMessage(g);
                        }
                    } else {
                        player.sendMessage(getMessage("Team.Error2"));
                    }
                } else {
                    player.sendMessage(getMessage("Team.Error1"));
                }

            } else if (args[0].equalsIgnoreCase("accept")) {
                if (!sender.hasPermission("teams.accept")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                UUID uuid = player.getUniqueId();
                if (args.length == 1) {
                    player.sendMessage(getMessage("Accept.InvalidUsage"));
                } else if (args.length == 2) {
                    if (Team.fromPlayer(uuid) != null) {
                        Team team = Team.fromPlayer(uuid);
                        if (team.isOwner(uuid)) {
                            String name = args[1];
                            if (team.getRequests().contains(name)) {
                                team.acceptPlayer(name);
                                team.save();
                                player.sendMessage(getMessage("Accept.Success"));
                            } else {
                                String g = getMessage("InvalidPlayer");
                                g = g.replace("%Target%", name);
                                player.sendMessage(g);
                            }
                        } else {
                            player.sendMessage(getMessage("Team.Error2"));
                        }
                    } else {
                        player.sendMessage(getMessage("Team.Error1"));
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else if (args[0].equalsIgnoreCase("kick")) {
                if (!sender.hasPermission("teams.kick")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage(invalid);
                    return true;
                }
                Player player = (Player) sender;
                UUID uuid = player.getUniqueId();
                if (Team.fromPlayer(uuid) != null) {
                    Team team = Team.fromPlayer(uuid);
                    if (team.getOwner() != null && team.getOwner().toString().equalsIgnoreCase(uuid.toString())) {
                        String name = args[1];
                        team.kick(name);
                        team.save();
                        String g = getMessage("TeamKick.Success");
                        g = g.replace("%Player%", name);
                        player.sendMessage(g);
                    } else {
                        player.sendMessage(getMessage("Team.Error2"));
                    }
                } else {
                    player.sendMessage(getMessage("Team.Error1"));
                }

            } else if (args[0].equalsIgnoreCase("setowner")) {
                if (!sender.hasPermission("teams.setowner")) {
                    sender.sendMessage(noperm);
                    return true;
                }
                if (args.length == 1) {
                    sender.sendMessage(getMessage("SetTeamOwner.InvalidUsage"));
                } else if (args.length == 2) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(invalid);
                        return true;
                    }
                    Player player = (Player) sender;
                    if (Team.fromPlayer(player.getUniqueId()) == null) {
                        player.sendMessage(getMessage("Team.Error1"));
                        return true;
                    }
                    Player p = null;
                    String name = args[1];
                    for (Player pp : Bukkit.getOnlinePlayers()) {
                        if (pp.getName().equalsIgnoreCase(name)) {
                            p = pp;
                        }
                    }
                    if (p != null) {
                        UUID uuid = p.getUniqueId();
                        Team team = Team.fromPlayer(player.getUniqueId());
                        if (!team.containsPlayer(uuid)) {
                            team.addPlayer(uuid);
                        }
                        team.setOwner(uuid);
                        team.save();
                        String g = getMessage("SetTeamOwner.Success");
                        g = g.replace("%Player%", name);
                        player.sendMessage(g);
                    } else {
                        String g = getMessage("InvalidPlayer");
                        g = g.replace("%Target%", args[1]);
                        sender.sendMessage(g);
                    }
                } else if (args.length == 3) {
                    String name = args[1];
                    Player p = null;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getName().equalsIgnoreCase(name)) {
                            p = player;
                        }
                    }
                    if (p != null) {
                        UUID uuid = p.getUniqueId();
                        if (Team.fromName(args[2]) == null) {
                            sender.sendMessage(getMessage("Team.Error1"));
                            return true;
                        } else {
                            Team team = Team.fromName(args[2]);
                            if (!team.containsPlayer(uuid)) {
                                team.addPlayer(uuid);
                            }
                            team.setOwner(uuid);
                            team.save();
                            String g = getMessage("SetTeamOwner.Success");
                            g = g.replace("%Player%", name);
                            sender.sendMessage(g);
                        }
                    }
                } else {
                    sender.sendMessage(error);
                }

            } else {
                getMessage("InvalidCommand2");
            }
        }


        return false;
    }


}
