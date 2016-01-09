package com.LuckyBlock.Structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class LuckyHouse extends Structure {
	
	
	
	@Override
	public int getId(){
	return 5;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void build(Location loc){
	Block block = loc.getBlock();
	byte data = (byte) random.nextInt(4);
	for(int x = -2; x < 3; x++){
	for(int z = -2; z < 3; z++){
	block.getLocation().add(x, -1, z).getBlock().setType(Material.LOG);
	block.getLocation().add(x, -1, z).getBlock().setData(data);
	}
	}
	for(int x = -2; x < 3; x++){
	for(int y = 0; y < 8; y++){
	block.getLocation().add(x, y, 3).getBlock().setType(Material.LOG);
	block.getLocation().add(x, y, 3).getBlock().setData(data);
	}
	}
	for(int x = -2; x < 3; x++){
	for(int y = 0; y < 8; y++){
	block.getLocation().add(x, y, -3).getBlock().setType(Material.LOG);
	block.getLocation().add(x, y, -3).getBlock().setData(data);
	}
	}
	for(int z = -2; z < 3; z++){
	for(int y = 0; y < 8; y++){
	block.getLocation().add(-3, y, z).getBlock().setType(Material.LOG);
	block.getLocation().add(-3, y, z).getBlock().setData(data);
	}
	}
	for(int z = -2; z < 3; z++){
	for(int y = 0; y < 8; y++){
	block.getLocation().add(3, y, z).getBlock().setType(Material.LOG);
	block.getLocation().add(3, y, z).getBlock().setData(data);
	}
	}
	super.build(loc);
	}
	
	
}
