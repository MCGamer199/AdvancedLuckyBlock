package com.LuckyBlock.Structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class PumpkinTower extends Structure {
	
	
	
	@Override
	public int getId(){
	return 3;
	}
	
	
	
	@Override
	public void build(Location loc){
	Block block = loc.getBlock();
	int r = random.nextInt(5) + 3;
	int s = 0;
	for(int y = 0; y < r; y++){
	block.getLocation().add(0, y, 0).getBlock().setType(Material.GOLD_BLOCK);
	s++;
	}
	block.getLocation().add(0, s, 0).getBlock().setType(Material.PUMPKIN);
	super.build(loc);
	}
	
	
	
	
	
}
