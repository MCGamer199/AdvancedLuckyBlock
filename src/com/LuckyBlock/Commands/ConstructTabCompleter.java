package com.LuckyBlock.Commands;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.Types;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConstructTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<String>();
        if (args.length == 1) {
            list.add("clearlbs");
            list.add("detector");
            list.add("getfile");
            list.add("gift");
            list.add("gold");
            list.add("help");
            list.add("luckyblock");
            list.add("money");
            list.add("reload");
            list.add("setchance");
            list.add("setgold");
            list.add("setmoney");
            list.add("setowner");
            list.add("setluck");
            list.add("setrange");
            list.add("thoraxe");
            list.add("types");
            list.add("version");
        } else {
            if (args[0].equalsIgnoreCase("luckyblock") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("lb")) {
                if (args.length == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                } else if (args.length == 3) {
                    list.add("1");
                } else if (args.length == 4) {
                    list.add("0");
                } else if (args.length == 5) {
                    for (Types type : Types.getTypes()) {
                        list.add(type.getId() + "");
                    }
                }
            } else if (args[0].equalsIgnoreCase("detector")) {
                if (args.length == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                }
            } else if (args[0].equalsIgnoreCase("gold")) {
                if (args.length == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                if (args.length == 2) {
                    for (int x = 1; x < (LuckyBlockCommand.pages + 1); x++) {
                        list.add("" + x);
                    }
                }
            } else if (args[0].equalsIgnoreCase("money")) {
                if (args.length == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                }
            } else if (args[0].equalsIgnoreCase("setchance")) {
                if (args.length == 2) {
                    list.add("1");
                }
            } else if (args[0].equalsIgnoreCase("setluck") || args[0].equalsIgnoreCase("setgold") || args[0].equalsIgnoreCase("setmoney")) {
                if (args.length == 2) {
                    list.add("0");
                }
            } else if (args[0].equalsIgnoreCase("setowner")) {
                if (args.length == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add("" + p.getUniqueId());
                    }
                }
            } else if (args[0].equalsIgnoreCase("setrange")) {
                if (args.length == 2) {
                    list.add("7");
                }
            } else if (args[0].equalsIgnoreCase("thoraxe")) {
                if (args.length == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                }
            } else if (args[0].equalsIgnoreCase("getfile")) {
                if (args.length == 2) {
                    File f1 = LuckyBlock.instance.configf;
                    File[] f = f1.getParentFile().listFiles();
                    for (File file : f) {
                        if (file.listFiles() != null) {
                            list.add(file.getName() + "/");
                        } else {
                            list.add(file.getName());
                        }
                    }
                } else if (args.length == 3) {
                    File[] f = LuckyBlock.instance.configf.getParentFile().listFiles();
                    File file = null;
                    for (File ff : f) {
                        if (args[1].equalsIgnoreCase(ff.getName())) {
                            file = ff;
                        }
                    }
                    if (file != null) {
                        FileConfiguration c = YamlConfiguration.loadConfiguration(file);
                        for (String s : c.getKeys(false)) {
                            if (c.getConfigurationSection(s) != null && c.getConfigurationSection(s).getKeys(false).size() > 0) {
                                list.add(s + ".");
                            } else {
                                list.add(s);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }


}
