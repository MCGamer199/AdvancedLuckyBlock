package com.LuckyBlock.Events;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Engine.Types;
import com.LuckyBlock.Resources.BlockAbilities;
import com.LuckyBlock.Resources.Detector;
import com.LuckyBlock.Resources.ParticleEffect;


public class PlaceLuckyBlock implements Listener {
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
	  
	  
	  
	@EventHandler
	public void onPlaceBlockLuckyBlock(BlockPlaceEvent event){
		Player player = (Player) event.getPlayer();
		ItemStack inh = player.getItemInHand();
		Block block = event.getBlock();
		String world = block.getWorld().getName();
		Location location = block.getLocation();
		if(inh == null || inh.getType() == Material.AIR){
	    return;
		}
		String folder = null;
		boolean found = false;
		int id = 0;
		List<String> worlds = new ArrayList<String>();
		Types types = null;
		for(Types type : LuckyBlock.lbs){
		if(inh.getType() == type.getType()){
		if(type.getData() > -1){
		if(type.getData() != inh.getDurability()){
		return;
		}
		}
		if(type.isNormalBlock()){
		found = true;
		types = type;
		worlds = type.getWorlds();
		folder = type.getFolder();
		id = type.getId();
		} else {
		if(inh.hasItemMeta() && inh.getItemMeta().hasDisplayName()){
		if(type.getName() != null){
		if(type.getName().equalsIgnoreCase(inh.getItemMeta().getDisplayName())){
		found = true;
		types = type;
		worlds = type.getWorlds();
		folder = type.getFolder();
		id = type.getId();
		}
		}
		}
		}
		}
		}
		if(found == true){
		if(!worlds.contains("*All*")){
		if(!worlds.contains(world)){
		player.sendMessage(red + "You are not allowed to place lucky block in this world.");
		event.setCancelled(true);
		return;
		}
		}
		if(!player.hasPermission("lb.place")){
	    event.setCancelled(true);
	    player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.config.getString("Messages.NoPermission.placeluckyblock")));
        return;
		}
	      String dim = LuckyBlockAPI.createDim(block);
			Random rand = new Random();
			String l = "%0";
			int luck = 0;
			if(player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().size() > 0){
			for(int x = 0; x < player.getItemInHand().getItemMeta().getLore().size(); x++){
			l = player.getItemInHand().getItemMeta().getLore().get(x);
			try {
			String[] s = l.split("%");
			if(s.length > 1){
			try {
			luck = Integer.parseInt(s[1]);
			} catch(NumberFormatException ex){
			//
			}
			}
			} catch(Exception ex){
			//
			}
			if(l.startsWith(gray + "Chance:")){
			String[] s = l.split("Chance:");
			if(s.length > 1){
			try {
			int chance = Integer.parseInt(s[1]);
			LuckyBlockAPI.chances.put(dim, chance);
			} catch(NumberFormatException ex){
			//
			}
			}
			}
			}
			}
			if(types.isAllowplaceparticles() == true){
			if(types.getPlaceparticles() != null){
			String particle = types.getPlaceparticles();
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
			ParticleEffect.valueOf(part[0].toUpperCase()).display(rx, ry, rz, speed, amount, location.add(lx, ly, lz), range);
			}
			}
			if(types.isAllowplacesound()){
			if(types.getPlacesound() != null){
			Sound sound = null;
			float vol = 100;
			float pit = 1;
			String[] s = types.getPlacesound().split(" ");
			try {
			sound = Sound.valueOf(s[0].toUpperCase());
			} catch(Exception ex){
			player.sendMessage(red + "Invalid Sound!");
			}
			try {
			vol = Float.parseFloat(s[1]);
			pit = Float.parseFloat(s[2]);
			} catch(NumberFormatException ex){
			player.sendMessage(red + "Error with sound!");
	}
	BreakLuckyBlock.playFixedSound(block.getLocation(), sound, vol, pit);
	}
	}
    LuckyBlock.instance.playEffects(block, location, (long)LuckyBlock.randoms.nextInt(60) + 1, 0);
    int i = luck;
    LuckyBlockAPI.luck.put(dim, i);
    LuckyBlockAPI.ids.put(dim, id);
	if(!LuckyBlockAPI.chances.containsKey(dim)){
	String[] d = LuckyBlock.instance.configf.getPath().split(LuckyBlock.instance.configf.getName());
	File fileF = new File(d[0] + "/Values/" + folder + "/");
	if(i < 20 && i > -1){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[0].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	} else if(i > 19 && i < 51){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[1].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	} else if(i > 50 && i < 100){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[2].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	} else if(i > 99 && i < 200){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[3].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	} else if(i > 199){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[4].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	} else if(i < 0 && i > -50){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[5].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	} else if(i < -49 && i > -101){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[6].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	} else if(i < -100){
	fileF = new File(fileF.getPath() + "/" + fileF.listFiles()[7].getName());
	FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
	int rando = rand.nextInt(file.getInt("Luck.Chances")) + 1;
	LuckyBlockAPI.chances.put(dim, rando);
	}
	}
	String o = null;
	String owner = null;
	if(player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().size() > 0){
	for(int xx = 0; xx < player.getItemInHand().getItemMeta().getLore().size(); xx++){
	if(player.getItemInHand().getItemMeta().getLore().get(xx).startsWith(gray + "Protected:")){
	o = player.getItemInHand().getItemMeta().getLore().get(xx);
	} else if(player.getItemInHand().getItemMeta().getLore().get(xx).startsWith(gray + "Owner:")){
	String[] d = player.getItemInHand().getItemMeta().getLore().get(xx).split("Owner: ");
	owner = d[0];
	}
	}
	}
	if(types != null){
	if(types.getAbilities().size() > 0){
	if(types.getAbilities().contains(BlockAbilities.RESISTANCE_EXPLOSIONS)){
	LuckyBlock.instance.Loops(block);
	}
	}
	}
	if(o != null){
	String[] s = o.split("Protected: ");
	if(s.length > 1 && s[1].equalsIgnoreCase(green + "true")){
	LuckyBlockAPI.BlockOwner.put(dim, player.getUniqueId().toString());
	LuckyBlockAPI.saveLuckyBlock(dim, i, LuckyBlockAPI.chances.get(dim), player.getUniqueId().toString(), id);
	} else {
		player.sendMessage("t");
	if(owner != null){
	LuckyBlockAPI.BlockOwner.put(dim, owner);
	LuckyBlockAPI.saveLuckyBlock(dim, i, LuckyBlockAPI.chances.get(dim), owner, id);
	} else {
	LuckyBlockAPI.saveLuckyBlock(dim, i, LuckyBlockAPI.chances.get(dim), "null", id);
	}
	}
	} else {
	if(owner != null){
	LuckyBlockAPI.BlockOwner.put(dim, owner);
	LuckyBlockAPI.saveLuckyBlock(dim, i, LuckyBlockAPI.chances.get(dim), owner, id);
	} else {
	LuckyBlockAPI.saveLuckyBlock(dim, i, LuckyBlockAPI.chances.get(dim), "null", id);
	}
	}
	}
	}
	
	
	
	/**
	 * Detector Event
	 * 
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
	public void placeDetector(BlockPlaceEvent event){
	if(event.getItemInHand() != null){
	if(event.getItemInHand().getType() == Material.PISTON_BASE){
	ItemStack item = event.getItemInHand();
	if(item.getItemMeta().hasDisplayName()){
	if(item.getItemMeta().getDisplayName().equalsIgnoreCase(blue + "" + bold + "Detector")){
	Block block = event.getBlock();
	Player player = event.getPlayer();
	Detector d = new Detector(LuckyBlock.randoms.nextInt(99999) + 1);
	d.setLoc(block.getLocation());
	block.setType(Material.OBSIDIAN);
	d.addBlock(block);
	block.getRelative(BlockFace.EAST).setType(Material.OBSIDIAN);
	d.addBlock(block.getRelative(BlockFace.EAST));
	block.getRelative(BlockFace.WEST).setType(Material.OBSIDIAN);
	d.addBlock(block.getRelative(BlockFace.WEST));
	block.getRelative(BlockFace.SOUTH).setType(Material.OBSIDIAN);
	d.addBlock(block.getRelative(BlockFace.SOUTH));
	block.getRelative(BlockFace.NORTH).setType(Material.OBSIDIAN);
	d.addBlock(block.getRelative(BlockFace.NORTH));
	block.getRelative(BlockFace.UP).setType(Material.GOLD_BLOCK);
	d.addBlock(block.getRelative(BlockFace.UP));
	block.getRelative(BlockFace.UP).setData((byte) 1);
	player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Detector.Place")));
	player.sendMessage(green + "ID:" + d.getId());
	d.registerBlocks();
	d.save();
	}
	}
	}
	}
	}
	
	
	/**
	 * Detector Break Event
	 * 
	 */
	@EventHandler
	public void breakDetector(BlockBreakEvent event){
	String dim = LuckyBlockAPI.createDim(event.getBlock());
	Player player = event.getPlayer();
    if(player.getGameMode() == GameMode.CREATIVE){
    if(player.getItemInHand() != null){
    ItemStack item = player.getItemInHand();
    if(item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.IRON_SWORD
    || item.getType() == Material.GOLD_SWORD || item.getType() == Material.DIAMOND_SWORD){
    return;
    }
    }
    }
	Detector d = null;
	for(Detector detector : LuckyBlockAPI.detectors){
	for(String s : detector.getBlocks()){
	if(s != null){
	if(s.equalsIgnoreCase(dim)){
	d = detector;
	}
	}
	}
	}
	if(d != null){
	d.dispose();
	player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Detector.Break")));
	}
	}
	
	
	
	
	
	
}
