package com.LuckyBlock.Structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.LuckyBlock.Engine.LuckyBlock;

public class LuckyDungeon extends Structure {
	
	
	
	@Override
	public int getId(){
	return 2;
	}
	
	
	
	@Override
	public void build(Location loc){
	Block block = loc.getBlock();
	for(int x = -3; x < 4; x++){
	for(int y = -3; y < 4; y++){
	for(int z = -3; z < 4; z++){
	block.getLocation().add(x, y, z).getBlock().setType(Material.AIR);
	}
	}
	}
	for(int x = -3; x < 4; x++){
	for(int z = -3; z < 4; z++){
	block.getLocation().add(x, -4, z).getBlock().setType(Material.MOSSY_COBBLESTONE);
	}
	}
	block.getLocation().add(0, -3, -2).getBlock().setType(Material.CHEST);
	block.getLocation().add(1, -3, -2).getBlock().setType(Material.TORCH);
	block.getLocation().add(-1, -3, -2).getBlock().setType(Material.TORCH);
	Chest chest = (Chest)block.getLocation().add(0, -3, -2).getBlock().getState();
	for(int x = 0; x < random.nextInt(7) + 10; x++){
	int r = random.nextInt(10) + 1;
	if(r == 1){
	chest.getBlockInventory().setItem(random.nextInt(27), new ItemStack(Material.DIAMOND, random.nextInt(3) + 1));
	} else if(r > 1 && r < 4){
	chest.getBlockInventory().setItem(random.nextInt(27), new ItemStack(Material.GOLD_INGOT, random.nextInt(4) + 1));
	} else if(r > 3 && r < 7){
	chest.getBlockInventory().setItem(random.nextInt(27), new ItemStack(Material.IRON_INGOT, random.nextInt(5) + 2));
	} else if(r > 6 && r < 8){
	chest.getBlockInventory().setItem(random.nextInt(27), new ItemStack(Material.EMERALD, random.nextInt(3) + 1));
	} else {
	ItemStack item = new ItemStack(LuckyBlock.lbs.get(0).getType());
	ItemMeta itemM = item.getItemMeta();
	itemM.setDisplayName(LuckyBlock.lbs.get(0).getName());
	item.setItemMeta(itemM);
	chest.getBlockInventory().setItem(random.nextInt(27), item);
	}
	}
	super.build(loc);
	}
	
	
	
	
	
	
	
}
