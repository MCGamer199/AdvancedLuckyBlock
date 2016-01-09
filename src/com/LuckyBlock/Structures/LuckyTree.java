package com.LuckyBlock.Structures;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.Types;
import com.LuckyBlock.Inventory.ItemMaker;

public class LuckyTree extends Structure {
	
	
	private List<Location> locs = new ArrayList<Location>();
	public static List<UUID> armorstands = new ArrayList<UUID>();
	static int[] Tall = new int[2];
	
	
	@Override
	public void build(Location loc){
	Block block = loc.getBlock();
	block.setType(Material.IRON_BLOCK);
	int y = 1;
	int g = random.nextInt(Tall[0]) + (Tall[1] - Tall[0]);
	for(int i = 1; i < g; i++){
	block.getLocation().add(0, i, 0).getBlock().setType(Material.IRON_BLOCK);
	y++;
	}
	for(int x = -3; x < 4; x++){
	for(int z = -3; z < 4; z++){
	block.getLocation().add(x, y, z).getBlock().setType(Material.SPONGE);
	if(random.nextInt(100) > 90){
	locs.add(block.getLocation().add(x, y, z));
	}
	}
	}
	block.getLocation().add(-3, y, -3).getBlock().setType(Material.AIR);
	block.getLocation().add(-3, y, -2).getBlock().setType(Material.AIR);
	block.getLocation().add(-2, y, -3).getBlock().setType(Material.AIR);
	
	block.getLocation().add(-3, y, 3).getBlock().setType(Material.AIR);
	block.getLocation().add(-3, y, 2).getBlock().setType(Material.AIR);
	block.getLocation().add(-2, y, 3).getBlock().setType(Material.AIR);
	
	block.getLocation().add(3, y, 3).getBlock().setType(Material.AIR);
	block.getLocation().add(2, y, 3).getBlock().setType(Material.AIR);
	block.getLocation().add(3, y, 2).getBlock().setType(Material.AIR);
	
	block.getLocation().add(3, y, -3).getBlock().setType(Material.AIR);
	block.getLocation().add(3, y, -2).getBlock().setType(Material.AIR);
	block.getLocation().add(2, y, -3).getBlock().setType(Material.AIR);
	
	y++;
	
	for(int x = -2; x < 3; x++){
	for(int z = -2; z < 3; z++){
	block.getLocation().add(x, y, z).getBlock().setType(Material.SPONGE);
	if(random.nextInt(100) > 90){
	locs.add(block.getLocation().add(x, y, z));
	}
	}
	}
	block.getLocation().add(2, y, -2).getBlock().setType(Material.AIR);
	block.getLocation().add(-2, y, -2).getBlock().setType(Material.AIR);
	block.getLocation().add(-2, y, 2).getBlock().setType(Material.AIR);
	block.getLocation().add(2, y, 2).getBlock().setType(Material.AIR);
	
	y++;
	
	for(int x = -1; x < 2; x++){
	for(int z = -1; z < 2; z++){
	block.getLocation().add(x, y, z).getBlock().setType(Material.SPONGE);
	if(random.nextInt(100) > 90){
	locs.add(block.getLocation().add(x, y, z));
	}
	}
	}
	
	y++;
	
	block.getLocation().add(0, y, 0).getBlock().setType(Material.SPONGE);
	if(random.nextInt(100) > 90){
	locs.add(block.getLocation().add(0, y, 0));
	}
	
	if(LuckyBlock.bukkitVersion[1] > 7){
	if(LuckyBlock.instance.config.getBoolean("SpawnFruits"))
	for(int x = 0; x < locs.size(); x++){
	Block b = locs.get(x).getBlock();
	if(b.getType() == Types.getTypes().get(0).getType()){
	if(b.getRelative(BlockFace.UP).getType() == Material.AIR){
	b = b.getRelative(BlockFace.UP);
	Location l = new Location(b.getWorld(), b.getX() + 0.5, b.getY() - 0.7, b.getZ() + 0.5);
	ArmorStand as = (ArmorStand)b.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
	as.setSmall(true);
	as.setGravity(false);
	as.setCustomName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Fruit");
	as.setVisible(false);
	as.setHelmet(ItemMaker.createItem(Material.LAPIS_BLOCK, 1, (short) 0, ChatColor.BLUE + "Lapis Block"));
	as.setCustomNameVisible(true);
	armorstands.add(as.getUniqueId());
	}
	}
	}
	}
	
	super.build(loc);
	}
	
	
	
	@Override
	public int getId(){
	return 1;
	}
	
	
	
	
	
	
}
