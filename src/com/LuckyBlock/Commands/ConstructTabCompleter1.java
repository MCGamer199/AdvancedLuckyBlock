package com.LuckyBlock.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import com.LuckyBlock.War.War;

public class ConstructTabCompleter1 implements TabCompleter {
	
	
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
	List<String> list = new ArrayList<String>();
	if(args.length == 1){
	list.add("adddblock");
	list.add("changename");
	list.add("cleargames");
	list.add("clearspawns");
	list.add("endgame");
	list.add("forcestart");
	list.add("getplayer");
	list.add("join");
	list.add("leave");
	list.add("lobby");
	list.add("removegame");
	list.add("save");
	list.add("setallowgates");
	list.add("setbounds");
	list.add("setcenter");
	list.add("setenabled");
	list.add("setgamename");
	list.add("setlevel");
	list.add("setlobby");
	list.add("setmainlobby");
	list.add("setmaxplayers");
	list.add("setminplayers");
	list.add("setspawn");
	list.add("settype");
	} else {
	if(args[0].equalsIgnoreCase("adddblock")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	}
	} else if(args[0].equalsIgnoreCase("changename")){
	if(args.length == 3){
	for(Player p : Bukkit.getOnlinePlayers()){
	list.add(p.getName());
	}
	}
	} else if(args[0].equalsIgnoreCase("endgame")){
	if(args.length == 2){
	for(War war : War.listWars){
	if(war.inGame()){
	list.add("" + war.getId());
	}
	}
	}
	} else if(args[0].equalsIgnoreCase("getplayer")){
	if(args.length == 2){
	for(Player p : Bukkit.getOnlinePlayers()){
	list.add(p.getName());
	}
	}
	} else if(args[0].equalsIgnoreCase("lobby")){
	if(args.length == 2){
	for(Player p : Bukkit.getOnlinePlayers()){
	list.add(p.getName());
	}
	}
	} else if(args[0].equalsIgnoreCase("setbounds")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	} else if(args.length == 3){
	list.add("1");
	list.add("2");
	}
	} else if(args[0].equalsIgnoreCase("setenabled")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	} else if(args.length == 3){
	list.add("true");
	list.add("false");
	}
	} else if(args[0].equalsIgnoreCase("setlevel")){
	if(args.length == 3){
	for(Player p : Bukkit.getOnlinePlayers()){
	list.add(p.getName());
	}
	} else if(args.length == 2){
	list.add("0");
	}
	} else if(args[0].equalsIgnoreCase("setlobby")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	}
	} else if(args[0].equalsIgnoreCase("setmainlobby")){
	if(sender instanceof Player){
	Player player = (Player)sender;
	if(args.length == 2){
	list.add("" + player.getWorld().getName());
	} else if(args.length == 3){
	list.add("" + player.getLocation().getBlockX());
	} else if(args.length == 4){
	list.add("" + player.getLocation().getBlockY());
	} else if(args.length == 5){
	list.add("" + player.getLocation().getBlockZ());
	}
	}
	} else if(args[0].equalsIgnoreCase("setmaxplayers") || args[0].equalsIgnoreCase("setminplayers") || args[0].equalsIgnoreCase("setgamename")
	|| args[0].equalsIgnoreCase("setcenter") || args[0].equalsIgnoreCase("clearspawns") || args[0].equalsIgnoreCase("join") ||
	args[0].equalsIgnoreCase("removegame") || args[0].equalsIgnoreCase("save")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	}
	} else if(args[0].equalsIgnoreCase("setspawn")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	} else if(args.length == 3){
	list.add("1");
	}
	} else if(args[0].equalsIgnoreCase("setallowgates")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	} else if(args.length == 3){
	list.add("true");
	list.add("false");
	}
	} else if(args[0].equalsIgnoreCase("settype")){
	if(args.length == 2){
	for(War war : War.listWars){
	list.add("" + war.getId());
	}
	} else if(args.length == 3){
	//for(WarType type : WarType.values()){
	//list.add(type.toString());
	//}
	list.add("DEFAULT");
	list.add("INSANE");
	}
	}
	}
	return list;
	}
	
	
	
}
