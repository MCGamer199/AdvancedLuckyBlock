package com.LuckyBlock.Commands;

import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Resources.Team;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConstructTabCompleter2 implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<String>();
        if (args.length == 1) {
            list.add("accept");
            list.add("clear");
            list.add("create");
            list.add("join");
            list.add("kick");
            list.add("leave");
            list.add("remove");
            list.add("requests");
            list.add("setowner");
        } else {
            if (args[0].equalsIgnoreCase("accept")) {
                if (args.length == 2) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        UUID uuid = player.getUniqueId();
                        Team team = null;
                        for (Team t : LuckyBlockAPI.teams) {
                            if (t.getOwner() == uuid) {
                                team = t;
                            }
                        }
                        if (team != null) {
                            for (String requests : team.getRequests()) {
                                list.add(requests);
                            }
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("join")) {
                if (args.length == 2) {
                    for (Team t : LuckyBlockAPI.teams) {
                        list.add(t.getName());
                    }
                }
            } else if (args[0].equalsIgnoreCase("kick")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (args.length == 2) {
                        Team team = null;
                        for (Team t : LuckyBlockAPI.teams) {
                            if (t.getOwner() == player.getUniqueId()) {
                                team = t;
                            }
                        }
                        if (team != null) {
                            List<UUID> u = new ArrayList<UUID>();
                            for (UUID uuid : team.getPlayers()) {
                                u.add(uuid);
                            }
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (u.contains(p.getUniqueId())) {
                                    list.add(p.getName());
                                }
                            }
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 2) {
                    for (Team t : LuckyBlockAPI.teams) {
                        list.add(t.getName());
                    }
                }
            } else if (args[0].equalsIgnoreCase("setowner")) {
                if (args.length == 2) {
                    for (Team t : LuckyBlockAPI.teams) {
                        list.add(t.getName());
                    }
                } else if (args.length == 3) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                }
            }
        }
        return list;
    }


}
