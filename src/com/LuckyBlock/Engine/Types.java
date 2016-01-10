package com.LuckyBlock.Engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.Resources.BlockAbilities;
import com.LuckyBlock.enums.LuckySkin;


public class Types {
	
	
	private String name;
	private boolean normalBlock;
	private boolean worksInCreative;
	private short maxLuck;
	private short minLuck;
	private String folder;
	private Material type;
	private short data;
	private List<String> worlds = new ArrayList<String>();
	private int id;
	private List<BlockAbilities> abilities = new ArrayList<BlockAbilities>();
	private String placesound;
	private String breaksound;
	private String placeparticles;
	private String breakparticles;
	private String tickparticles;
	private boolean allowplacesound;
	private boolean allowbreaksound;
	private boolean allowplaceparticles;
	private boolean allowbreakparticles;
	private boolean allowtickparticles;
	private boolean useSkin;
	private LuckySkin skin = LuckySkin.LUCKY;
	
	
	/**
	 * Creates new Lucky Block Type.
	 * 
	 * @param key The Password. this is required.
	 * @param name The Display Name for the item.
	 * @param normalBlock If true the name will be ignored.
	 * @param worksInCreative Does this work in creative mode.
	 * @param maxLuck The maximum luck.
	 * @param minLuck The minimum luck.
	 * @param folder The Folder.
	 * @param type The Item Type.
	 * @param data The data or durability. Ignored if set to -1.
	 * 
	 * @see Types
	 */
	public Types(char[] key, int id, String name, boolean normalBlock, boolean worksInCreative, short maxLuck, short minLuck, String folder, Material type
	, short data){
	boolean right = false;
	String p = "MCGamer199";
	if(key.length == p.length()){
	int t = 0;
	for(int x = 0; x < key.length; x++){
	if(key[x] == p.charAt(x)){
	t++;
	}
	if(t == key.length){
	right = true;
	}
	}
	}
	if(right){
	this.name = ChatColor.translateAlternateColorCodes('&', name);
	this.normalBlock = normalBlock;
	this.worksInCreative = worksInCreative;
	this.maxLuck = maxLuck;
	this.minLuck = minLuck;
	this.folder = folder;
	this.type = type;
	this.data = data;
	this.id = id;
	} else {
	throw new Error("Password is incorrect!");
	}
	}
	
	public boolean isNormalBlock(){
	return normalBlock;
	}
	
	public void setNormalBlock(boolean normalBlock){
	this.normalBlock = normalBlock;
	}
	
	public void setWorksInCreative(boolean worksInCreative){
	this.worksInCreative = worksInCreative;
	}
	
	/**
	 * Gets the display name for the lucky block.
	 * 
	 * @return The Display Name.
	 */
	public String getName(){
	return name;
	}
	
	public short getMinLuck(){
	return minLuck;
	}
	
	public LuckySkin getSkin(){
	return skin;
	}
	
	public void setSkin(LuckySkin skin){
	this.skin = skin;
	}
	
	public boolean UseLuckyBlockSkin(){
	return useSkin;
	}
	
	public void setUseLuckyBlockSkin(boolean useLuckyBlockSkin){
	this.useSkin = useLuckyBlockSkin;
	}
	
	public boolean isWorksInCreative(){
	return worksInCreative;
	}
	
	public short getMaxLuck(){
	return maxLuck;
	}
	
	public String getFolder(){
	return folder;
	}
	
	public short getData(){
	if(data > 0){
	return data;
	} else {
	return 0;
	}
	}
	
	public Material getType(){
	return type;
	}
	
	public List<String> getWorlds(){
	return worlds;
	}
	
	public void setTickparticles(String tickparticles){
	this.tickparticles = tickparticles;
	}
	
	public void setPlaceparticles(String placeparticles){
	this.placeparticles = placeparticles;
	}
	
	public void setPlacesound(String placesound){
	this.placesound = placesound;
	}
	
	public void setBreakparticles(String breakparticles){
	this.breakparticles = breakparticles;
	}
	
	public void setBreaksound(String breaksound){
	this.breaksound = breaksound;
	}
	
	public void setAllowbreakparticles(boolean allowbreakparticles){
	this.allowbreakparticles = allowbreakparticles;
	}
	
	public void setAllowplaceparticles(boolean allowplaceparticles){
	this.allowplaceparticles = allowplaceparticles;
	}
	
	public void setAllowplacesound(boolean allowplacesound){
	this.allowplacesound = allowplacesound;
	}
	
	public void setAllowbreaksound(boolean allowbreaksound){
	this.allowbreaksound = allowbreaksound;
	}
	
	public void setAllowtickparticles(boolean allowtickparticles){
	this.allowtickparticles = allowtickparticles;
	}
	
	public String getBreakparticles(){
	return breakparticles;
	}
	
	public String getTickparticles(){
	return tickparticles;
	}
	
	public boolean isAllowbreakparticles(){
	return allowbreakparticles;
	}
	
	public boolean isAllowplaceparticles(){
	return allowplaceparticles;
	}
	
	public boolean isAllowbreaksound(){
	return allowbreaksound;
	}
	
	public boolean isAllowplacesound(){
	return allowplacesound;
	}
	
	public String getBreaksound(){
	return breaksound;
	}
	
	public String getPlaceparticles(){
	return placeparticles;
	}
	
	public String getPlacesound(){
	return placesound;
	}
	
	public boolean isAllowtickparticles(){
	return allowtickparticles;
	}
	
	public void setWorlds(List<String> worlds){
	this.worlds = worlds;
	}
	
	public void addWorld(String world){
	worlds.add(world);
	}
	
	public void addWorld(World world){
	worlds.add(world.getName());
	}
	
	public List<BlockAbilities> getAbilities(){
	return abilities;
	}
	
	public void addAbility(BlockAbilities ability){
	abilities.add(ability);
	}
	
	public int getId(){
	return id;
	}
	
	public void save(){
	if(LuckyBlock.lbs.contains(this)){
	LuckyBlock.lbs.remove(this);
	}
	LuckyBlock.lbs.add(this);
	}
	
	
	
	public FileConfiguration getConfig(){
	FileConfiguration file = null;
	String path = LuckyBlock.instance.folder1.getPath();
	File f = new File(path);
	File[] files = f.listFiles();
	for(int x = 0; x < files.length; x++){
	FileConfiguration c = YamlConfiguration.loadConfiguration(files[x]);
	if(c.getInt("ID") == id){
	file = c;
	}
	}
	return file;
	}
	
	/**
	 * Translate type to item.
	 * @return The Item.
	 */
	public ItemStack toItemStack(){
	ItemStack item = ItemMaker.createItem(type, 1, data, name);
	if(type == Material.SKULL_ITEM){
	if(useSkin){
	item = ItemMaker.createSkull(item, skin.getId(), skin.getValue());
	}
	}
	return item;
	}
	
	/**
	 * Translate type to item.
	 * @return The Item.
	 */
	public ItemStack toItemStack(int luck){
	ItemStack item = ItemMaker.createItem(type, 1, data, name, Arrays.asList(getLuckString(luck)));
	if(type == Material.SKULL_ITEM){
	if(useSkin){
	item = ItemMaker.createSkull(item, skin.getId(), skin.getValue());
	}
	}
	return item;
	}
	
	/**
	 * 
	 */
    public static Types fromBlock(String dim){
    Types type = null;
    for(Types t : LuckyBlock.lbs){
    if(LuckyBlockAPI.ids.containsKey(dim)){
    if(LuckyBlockAPI.ids.get(dim) == t.getId()){
    type = t;
    }
    }
    }
    return type;
    }
    
    
    
    /**
     * 
     */
    public static Types fromId(int id){
    Types type = null;
    for(Types t : LuckyBlock.lbs){
    if(t.getId() == id){
    type = t;
    }
    }
    return type;
    }
    
    
    
    /**
     * 
     */
    public static Types fromItem(ItemStack item){
    Types type = null;
    for(int x = 0; x < LuckyBlock.lbs.size(); x++){
    Types t = LuckyBlock.lbs.get(x);
    if(item.getType() == t.getType()){
    if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
    if(item.getItemMeta().getDisplayName().equalsIgnoreCase(t.getName())){
    type = t;
    }
    }
    }
    }
    return type;
    }
    
    
    
    /**
     * 
     */
	public static boolean isLB(ItemStack item){
	boolean is = false;
	for(Types type : LuckyBlock.lbs){
	if(item.getType() == type.getType()){
	if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
	if(item.getItemMeta().getDisplayName().equalsIgnoreCase(type.getName())){
	is = true;
	}
	}
	}
	}
	return is;
	}
	
	/**
	 * 
	 */
	public static File getFolder(Types type){
	File f = null;
	String[] ad = LuckyBlock.instance.configf.getPath().split(LuckyBlock.instance.configf.getName());
	if(type != null){
	f = new File(ad[0] + "/Values/" + type.getFolder() + "/");
	}
	return f;
	}
	
	/**
	 * 
	 */
	public static FileConfiguration getFile(Types type, int number){
	File f = null;
	FileConfiguration file = null;
	String[] ad = LuckyBlock.instance.configf.getPath().split(LuckyBlock.instance.configf.getName());
	if(type != null){
	f = new File(ad[0] + "/Values/" + type.getFolder() + "/");
	f = new File(f.getPath() + "/" + f.listFiles()[number].getName());
	}
	file = YamlConfiguration.loadConfiguration(f);
	return file;
	}
	
	/**
	 * Returns list of types.
	 * 
	 * @return Types
	 * 
	 * @see Types
	 */
	public static List<Types> getTypes(){
	return LuckyBlock.lbs;
	}
	
	public static String getLuckString(int luck){
	String g = "";
	if(luck > 0 && luck < 40){
	g = ChatColor.YELLOW + "%" + luck;
	} else if(luck == 0){
	g = ChatColor.GOLD + "%" + luck;
	} else if(luck >= 40 && luck < 200){
	g = ChatColor.GREEN + "%" + luck;
	} else if(luck >= 200){
	g = ChatColor.DARK_PURPLE + "%" + luck;
	} else {
    g = ChatColor.RED + "%" + luck;
	}
	return g;
	}
	
	
	
	
	
}
