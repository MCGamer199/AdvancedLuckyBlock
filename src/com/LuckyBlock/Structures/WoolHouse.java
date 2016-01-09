package com.LuckyBlock.Structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;


public class WoolHouse extends Structure {
	
	
	
	@Override
	public int getId(){
	return 6;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void build(Location loc){
	Block block = loc.getBlock();
	int r = random.nextInt(10) + 11;
	int s = 0;
	for(int y = 0; y < r; y++){
	block.getLocation().add(0, y, 0).getBlock().setType(Material.WOOL);
	block.getLocation().add(0, y, 0).getBlock().setData((byte) 7);
	s++;
	}
	int g = random.nextInt(7) + 3;
	if(g % 2 == 0){
	g++;
	}
	byte b = (byte) random.nextInt(16);
	int k = random.nextInt(2) + 1;
	for(int h = g; h > 0; h--){
	for(int x = g * -1; x < g-1; x++){
	for(int z = g * -1; z < g-1; z++){
	block.getLocation().add(x+1, s, z+1).getBlock().setType(Material.WOOL);
	block.getLocation().add(x+1, s, z+1).getBlock().setData(b);
	}
	}
	if(k == 1){
	g-=1;
	} else if(k == 2){
	g-=2;
	}
	s++;
	}
	super.build(loc);
	}
	
	
}
