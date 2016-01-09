package com.LuckyBlock.War;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;


/**
 * <b>Hat</b>
 * <p>
 * This library was created by @MCGamer199 for Lucky Block
 * <p>
 * You can use it and modify it under the following conditions:
 * <ul>
 * <li>Don't claim this class as your own
 * <li>Don't remove this disclaimer
 * </ul>
 * <p>
 * <i>It would be nice if you provide credit to me if you use this class in a published project</i>
 * @author MCGamer199
 * @since 1.9
 */
public enum Hat {
	
	NONE(0, 0, false),
	
	GLASS_HAT(2000, 0, false),
	
	COLORFUL_HAT(150, 15, false),
	
	DIAMOND_HAT(6000, 20, false),
	
	LEATHER_HAT(200, 30, false),
	
	HARD_HAT(100, 20, false),
	
	CAGE_HAT(50, 40, false),
	
	TNT_HAT(15000, 35, false),
	
	NOTCH_HAT(200, 50, false),
	
	PUMPKIN_HAT(0, 0, false),
	
	SNOW_HAT(0, 0, false),
	
	CHEST_HAT(200, 40, true),
	
	END_HAT(10000, 41, true),
	
	ICE_HAT(8500, 41, true),
	
	MELON_HAT(9000, 41, true),
	
	HAY_HAT(150, 42, true),
	
	NOFALL_HAT(300, 45, true),
	
	CRAFTING_HAT(0, 0, false),
	
	ENDER_HAT(0, 0, false),
	
	ENCHANTING_HAT(0, 0, false),
	
	BRICK_HAT(0, 0, false),
	
	ENDERMAN_HAT(0, 0, false),
	
	HEROBRINE_HAT(0, 0, false)
	
	;
	
	private final int cost;
	private final int level;
	private final boolean requirelevel;
	private static HashMap<Hat, Material> BLOCK_IDS = new HashMap<Hat, Material>();
	
	
	private Hat(int cost, int level, boolean requirelevel){
	this.cost = cost;
	this.level = level;
	this.requirelevel = requirelevel;
	}
	
	static {
	BLOCK_IDS.put(NONE, Material.AIR);
	BLOCK_IDS.put(CAGE_HAT, Material.MOB_SPAWNER);
	BLOCK_IDS.put(GLASS_HAT, Material.GLASS);
	BLOCK_IDS.put(COLORFUL_HAT, Material.WOOL);
	BLOCK_IDS.put(DIAMOND_HAT, Material.DIAMOND_BLOCK);
	BLOCK_IDS.put(LEATHER_HAT, Material.LEATHER_HELMET);
	BLOCK_IDS.put(HARD_HAT, Material.OBSIDIAN);
	BLOCK_IDS.put(TNT_HAT, Material.TNT);
	BLOCK_IDS.put(NOTCH_HAT, Material.GOLD_BLOCK);
	BLOCK_IDS.put(CHEST_HAT, Material.CHEST);
	BLOCK_IDS.put(END_HAT, Material.ENDER_STONE);
	BLOCK_IDS.put(ICE_HAT, Material.ICE);
	BLOCK_IDS.put(MELON_HAT, Material.MELON_BLOCK);
	BLOCK_IDS.put(HAY_HAT, Material.HAY_BLOCK);
	BLOCK_IDS.put(NOFALL_HAT, Material.CHAINMAIL_HELMET);
	BLOCK_IDS.put(PUMPKIN_HAT, Material.PUMPKIN);
	BLOCK_IDS.put(SNOW_HAT, Material.SNOW_BLOCK);
	BLOCK_IDS.put(CRAFTING_HAT, Material.WORKBENCH);
	BLOCK_IDS.put(ENDER_HAT, Material.ENDER_CHEST);
	BLOCK_IDS.put(ENCHANTING_HAT, Material.ENCHANTMENT_TABLE);
	BLOCK_IDS.put(BRICK_HAT, Material.BRICK);
	BLOCK_IDS.put(ENDERMAN_HAT, Material.SKULL_ITEM);
	BLOCK_IDS.put(HEROBRINE_HAT, Material.SKULL_ITEM);
	}
	
	public int getCost(){
	return cost;
	}
	
	public Material getType(){
	return BLOCK_IDS.get(this);
	}
	
	public boolean isRequirelevel(){
	return requirelevel;
	}
	
	public int getLevel(){
	return level;
	}
	
	
	private static HashMap<UUID, List<Hat>> hats = new HashMap<UUID, List<Hat>>();
	private static HashMap<UUID, Hat> selected = new HashMap<UUID, Hat>();
	
	
	
	public static HashMap<UUID, List<Hat>> getHats(){
	return hats;
	}
	
	public static HashMap<UUID, Hat> getSelected(){
	return selected;
	}
	
	public void unlock(UUID uuid){
	if(hats.containsKey(uuid)){
	List<Hat> list = hats.get(uuid);
	if(list.contains(this)){
	list.remove(this);
	}
	list.add(this);
	hats.put(uuid, list);
	} else {
	List<Hat> list = new ArrayList<Hat>();
	list.add(this);
	hats.put(uuid, list);
	}
	}
	
	
	
	
	
}
