package com.LuckyBlock.Structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class LuckyTrap extends Structure {
	
	
	@Override
	public int getId(){
	return 4;
	}
	
	@Override
	public void build(Location loc){
	Block block = loc.getBlock();
	for(int x = -2; x < 3; x++){
	for(int z = -2; z < 3; z++){
	block.getLocation().add(x, 0, z).getBlock().setType(Material.COBBLESTONE);
	}
	}
	for(int x = -1; x < 2; x++){
	for(int z = -1; z < 2; z++){
	block.getLocation().add(x, 0, z).getBlock().setType(Material.AIR);
	}
	}
	for(int x = -2; x < 3; x++){
	for(int z = -2; z < 3; z++){
	block.getLocation().add(x, -1, z).getBlock().setType(Material.COBBLESTONE);
	}
	}
	block.getLocation().add(0, 0, -2).getBlock().setType(Material.AIR);
	for(int x = -1; x < 2; x++){
	for(int z = -1; z < 2; z++){
	block.getLocation().add(x, -2, z).getBlock().setType(Material.TNT);
	}
	}
	block.getLocation().add(0, 0, -1).getBlock().setType(Material.STONE_PLATE);
	super.build(loc);
	}
	
	
	
	
	
	
}
