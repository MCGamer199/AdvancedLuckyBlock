package com.LuckyBlock.Events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Inventory.ItemMaker;
import com.LuckyBlock.Inventory.ItemMetadata;
import com.LuckyBlock.Inventory.AttributeName.AttributeType;
import com.LuckyBlock.Inventory.AttributeName.OperationType;

@SuppressWarnings("unused")
public class Kits implements Listener {
	
	
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
	public static HashMap<String, List<ItemStack>> items = new HashMap<String, List<ItemStack>>();
	public static HashMap<String, List<String>> kititems = new HashMap<String, List<String>>();
	public static HashMap<UUID, String> ckit = new HashMap<UUID, String>();
	public static HashMap<String, Boolean> kitvip = new HashMap<String, Boolean>();
	public static HashMap<String, String> kitstate = new HashMap<String, String>();
	
	
	
	@EventHandler
	public void onOpenKitsGui(PlayerInteractEvent event){
	if(event.getItem() != null){
	if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
	if(event.getItem().getType() == Material.CHEST){
	if(event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()){
	if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(yellow + "" + bold + "Kits" + gray + " (Right Click)")){
	Player player = event.getPlayer();
	openKits(player);
	}
	}
	}
	}
	}
	}
	
	
	
	@EventHandler
	public void onPlaceKitsItem(BlockPlaceEvent event){
	if(event.getItemInHand() != null){
	if(event.getItemInHand().getType() == Material.CHEST){
	if(event.getItemInHand().hasItemMeta() && event.getItemInHand().getItemMeta().hasDisplayName()){
	if(event.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(yellow + "" + bold + "Kits" + gray + " (Right Click)")){
	event.setCancelled(true);
	}
	}
	}
	}
	}
	
	
	
	@EventHandler
	public void onChooseKit(InventoryClickEvent event){
	if(event.getInventory().getTitle() != null){
	if(event.getInventory().getTitle().equalsIgnoreCase(yellow + "" + bold + "Kits")){
	if(event.getWhoClicked() instanceof Player){
	Player player = (Player)event.getWhoClicked();
	UUID uuid = player.getUniqueId();
	if(event.getCurrentItem() != null){
	if(event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()){
	boolean sel = false;
	if(!ckit.containsKey(uuid)){
	ckit.put(uuid, "null");
	}
	ItemStack item = event.getCurrentItem();
	String name = null;
	for(String s : items.keySet()){
	if(items.get(s).contains(item)){
	name = s;
	}
	}
	if(name != null){
	if(!ckit.get(uuid).equalsIgnoreCase(name)){
	if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
	if(!kitvip.containsKey(name)){
	ckit.put(uuid, name);
	sel = true;
	String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("ChooseKit.Success"));
	g = g.replace("%ItemName%", item.getItemMeta().getDisplayName());
	player.sendMessage(g);
	} else {
	if(kitvip.get(name) == true){
	if(player.hasPermission("lbw.kits.vip")){
	ckit.put(uuid, name);
	sel = true;
	String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("ChooseKit.Success"));
	g = g.replace("%ItemName%", item.getItemMeta().getDisplayName());
	player.sendMessage(g);
	} else {
	sel = false;
	player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("ChooseKit.NoPermission")));
	player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5f, 1f);
	}
	} else {
	ckit.put(uuid, name);
	sel = true;
	String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("ChooseKit.Success"));
	g = g.replace("%ItemName%", item.getItemMeta().getDisplayName());
	player.sendMessage(g);
	}
	}
	}
	}
	}
	if(sel == true){
	player.playSound(player.getLocation(), Sound.CREEPER_DEATH, 100f, 2f);
	LuckyBlockAPI.savePlayerData(uuid, player.getName());
	}
	}
	}
	}
	event.setCancelled(true);
	}
	}
	}
	
	
	
	public static void startGame(Player player){
	UUID uuid = player.getUniqueId();
	if(ckit.containsKey(uuid)){
	if(kititems.get(ckit.get(uuid)) != null){
	List<String> list = kititems.get(ckit.get(uuid));
	for(int x = 0; x < list.size(); x++){
	ItemStack item = LuckyBlockAPI.getItem(list.get(x), ",");
	if(item != null){
	player.getInventory().addItem(item);
	}
	}
	}
	}
	}
	
	
	
	public static void openKits(Player player){
	Inventory kits = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "" + ChatColor.BOLD + "Kits");
	for(String s : items.keySet()){
	for(int x = 0; x < items.get(s).size(); x++){
	if(kitstate.containsKey(s)){
	if(kitstate.get(s).equalsIgnoreCase("true")){
	kits.addItem(items.get(s).get(x));
	} else if(kitstate.get(s).equalsIgnoreCase("VIP")){
	if(player.hasPermission("lbw.kits.vip")){
	kits.addItem(items.get(s).get(x));
	}
	}
	} else {
	kits.addItem(items.get(s).get(x));
	}
	}
	}
	for(int x = 0; x < kits.getSize(); x++){
	if(kits.getItem(x) == null){
	ItemStack glass = ItemMaker.createItem(Material.STAINED_GLASS_PANE, 1, (short) 15, ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Locked");
	kits.setItem(x, glass);
	}
	}
	player.openInventory(kits);
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static void loadKits(){
		ItemStack item = null;
		String kitname = null;
		try {
		FileConfiguration file = LuckyBlock.instance.kits;
		ConfigurationSection cs = file.getConfigurationSection("Kits");
		for(int x = 0; x < cs.getKeys(false).size(); x++){
		ConfigurationSection c = cs.getConfigurationSection(cs.getKeys(false).toArray()[x].toString());
		if(c.getString("Name") != null){
		kitname = c.getString("Name");
		}
		if(c.getString("VIP") != null){
		if(kitname != null){
		kitvip.put(kitname, c.getBoolean("VIP"));
		}
		}
		if(c.getString("Show") != null){
		if(kitname != null){
		kitstate.put(kitname, c.getString("Show"));
		}
		}
		if(c.getString("KitItem.Type") != null){
		item = new ItemStack(Material.getMaterial(c.getString("KitItem.Type").toUpperCase()));
		}
		if(c.getInt("KitItem.Data") > -1){
		item.setDurability((short) c.getInt("KitItem.Data"));
		}
		if(c.getInt("KitItem.Amount") != 0){
		item.setAmount(c.getInt("KitItem.Amount"));
		}
		if(c.getString("KitItem.DisplayName") != null){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', c.getString("KitItem.DisplayName")));
		item.setItemMeta(im);
		}
		if(c.getStringList("KitItem.Lore").size() > 0){
		ItemMeta im = item.getItemMeta();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < c.getStringList("KitItem.Lore").size(); i++){
		list.add(ChatColor.translateAlternateColorCodes('&', c.getStringList("KitItem.Lore").get(i)));
		}
		im.setLore(list);
		item.setItemMeta(im);
		}
		if(c.getStringList("KitItem.Enchants").size() > 0){
		for(int xx = 0; xx < c.getStringList("KitItem.Enchants").size(); xx++){
		String[] d = c.getStringList("KitItem.Enchants").get(xx).split(" ");
		try {
		int id = Integer.parseInt(d[0]);
		int level = Integer.parseInt(d[1]);
		ItemMeta im = item.getItemMeta();
		im.addEnchant(Enchantment.getById(id), level, true);
		item.setItemMeta(im);
		} catch(Exception ex){
		ex.printStackTrace();
		}
		}
		}
		if(c.getStringList("KitItem.Pages").size() > 0){
		BookMeta b = (BookMeta)item.getItemMeta();
		for(int g = 0; g < c.getStringList("KitItem.Pages").size(); g++){
		b.addPage(ChatColor.translateAlternateColorCodes('&', c.getStringList("KitItem.Pages").get(g)));
		}
		item.setItemMeta(b);
		}
		if(c.getString("KitItem.SkullOwner") != null){
		SkullMeta skull = (SkullMeta)item.getItemMeta();
		skull.setOwner(c.getString("KitItem.SkullOwner"));
		item.setItemMeta(skull);
		}
		if(c.getBoolean("KitItem.Unbreakable")){
		item = ItemMaker.makeUnbreakable(item);
		}
		if(c.getStringList("KitItem.Attributes").size() > 0){
		for(int i = 0; i < c.getStringList("KitItem.Attributes").size(); i++){
		String[] d = c.getStringList("KitItem.Attributes").get(i).split(",");
		OperationType otype = OperationType.ZERO;
		AttributeType atype = AttributeType.ATTACK_DAMAGE;
		int amount = 0;
		String name = "TEST";
		for(int f = 0; f < d.length; f++){
		if(d[f].startsWith("Operation#")){
		String[] g = d[f].split("Operation#");
		otype = OperationType.getById(Integer.parseInt(g[1]));
		}
		if(d[f].startsWith("AttributeName#")){
		atype = AttributeType.getByName(d[f].split("AttributeName#")[1]);
		}
		if(d[f].startsWith("Amount#")){
		amount = Integer.parseInt(d[f].split("Amount#")[1]);
		}
		if(d[f].startsWith("Name#")){
		name = d[f].split("Name#")[1];
		}
		}
		item = ItemMaker.addAttribute(item, atype, otype, name, amount);
		}
		}
		if(c.getConfigurationSection("KitItem.CustomTags") != null){
		for(int g = 0; g < c.getConfigurationSection("KitItem.CustomTags").getKeys(false).size(); g++){
		String tag = c.getConfigurationSection("KitItem.CustomTags").getKeys(false).toArray()[g].toString();
		String type = "string";
		String value = null;
		if(c.getString("KitItem.CustomTags." + tag + ".Type") != null){
		type = c.getString("KitItem.CustomTags." + tag + ".Type");
		}
		if(c.getString("KitItem.CustomTags." + tag + ".Value") != null){
		value = c.getString("KitItem.CustomTags." + tag + ".Value");
		}
		if(value != null){
		ItemMetadata i = new ItemMetadata(item);
		if(type.equalsIgnoreCase("string")){
		i.addMetadata(tag, value);
		}
		if(type.equalsIgnoreCase("boolean")){
		i.addMetadata(tag, Boolean.parseBoolean(value));
		}
		if(type.equalsIgnoreCase("integer") || type.equalsIgnoreCase("int")){
		i.addMetadata(tag, Integer.parseInt(value));
		}
		if(type.equalsIgnoreCase("long")){
		i.addMetadata(tag, Long.parseLong(value));
		}
		if(type.equalsIgnoreCase("double")){
		i.addMetadata(tag, Double.parseDouble(value));
		}
		item = i.getItem();
		}
		}
		}
	    if(c.getString("KitItem.Author") != null){
	    BookMeta b = (BookMeta)item.getItemMeta();
	    b.setAuthor(ChatColor.translateAlternateColorCodes('&', c.getString("KitItem.Author")));
	    item.setItemMeta(b);
	    }
	    if(c.getString("KitItem.Title") != null){
	    BookMeta b = (BookMeta)item.getItemMeta();
	    b.setTitle(ChatColor.translateAlternateColorCodes('&', c.getString("KitItem.Title")));
	    item.setItemMeta(b);
	    }
		if(c.getStringList("KitItem.PotionEffects").size() > 0){
		for(int xx = 0; xx < c.getStringList("KitItem.PotionEffects").size(); xx++){
		String[] d = c.getStringList("KitItem.PotionEffects").get(xx).split(" ");
		try {
		int id = Integer.parseInt(d[0]);
		int sec = Integer.parseInt(d[1]);
		int amplifier = Integer.parseInt(d[2]);
		PotionMeta im = (PotionMeta)item.getItemMeta();
	    im.addCustomEffect(new PotionEffect(PotionEffectType.getById(id), sec * 20, amplifier), true);
		item.setItemMeta(im);
		} catch(Exception ex){
		ex.printStackTrace();
		}
		}
		}
		if(item != null && kitname != null){
		if(items.containsKey(kitname)){
		List<ItemStack> i = Kits.items.get(kitname);
		i.add(item);
		items.put(kitname, i);
		} else {
		items.put(kitname, Arrays.asList(item));
		}
		}
		ConfigurationSection con = c.getConfigurationSection("Items");
		for(int y = 0; y < con.getKeys(false).size(); y++){
		ItemStack i = null;
		String p = con.getKeys(false).toArray()[y].toString();
		if(con.getString(p + ".Type") != null){
		i = new ItemStack(Material.getMaterial(con.getString(p + ".Type").toUpperCase()));
		}
		if(con.getInt(p + ".Amount") != 0){
		i.setAmount(con.getInt(p + ".Amount"));
		}
		if(con.getInt(p + ".Data") > -1){
		i.setDurability((short)con.getInt(p + ".Data"));
		}
		if(kitname != null && i != null){
		String n = "";
		n = n + "type:" + i.getType();
		n = n + ",amount:" + i.getAmount();
		n = n + ",data:" + i.getDurability();
	    if(con.getStringList(p + ".Lore").size() > 0){
	    n = n + ",Lore:";
	    for(int z = 0; z < con.getStringList(p + ".Lore").size(); z++){
	    if(z == 0){
	    n = n + con.getStringList(p + ".Lore").get(z);
	    } else {
	    n = n + "%%" + con.getStringList(p + ".Lore").get(z);
	    }
	    }
	    }
	    if(con.getStringList(p + ".Enchants").size() > 0){
	    n = n + ",Enchants:";
	    for(int z = 0; z < con.getStringList(p + ".Enchants").size(); z++){
	    if(z == 0){
	    n = n + con.getStringList(p + ".Enchants").get(z);
	    } else {
	    n = n + "%%" + con.getStringList(p + ".Enchants").get(z);
	    }
	    }
	    }
	    if(con.getString(p + ".SkullOwner") != null){
	    n = n + ",SkullOwner:" + con.getString(p + ".SkullOwner");
	    }
	    if(con.getBoolean(p + ".Unbreakable")){
	    n = n + ",Unbreakable:true";
	    }
	    if(con.getStringList(p + ".Attributes").size() > 0){
	    n = n + ",Attributes:";
	    for(int z = 0; z < con.getStringList(p + ".Attributes").size(); z++){
	    if(z == 0){
	    n = n + con.getStringList(p + ".Attributes").get(z).replace("#", ":").replace(",", " ");
	    } else {
	    n = n + "%%" + con.getStringList(p + ".Attributes").get(z).replace("#", ":").replace(",", " ");
	    }
	    }
	    }
	    if(con.getString(p + ".Author") != null){
	    n = n + ",Author:" + con.getString(p + ".Author");
	    }
	    if(con.getString(p + ".Title") != null){
	    n = n + ",Title:" + con.getString(p + ".Title");
	    }
	    if(con.getStringList(p + ".Pages").size() > 0){
	    n = n + ",Pages:";
	    for(int z = 0; z < con.getStringList(p + ".Pages").size(); z++){
	    if(z == 0){
	    n = n + con.getStringList(p + ".Pages").get(z);
	    } else {
	    n = n + "%%" + con.getStringList(p + ".Pages").get(z);
	    }
	    }
	    }
	    if(con.getStringList(p + ".PotionEffects").size() > 0){
	    n = n + ",PotionEffects:";
	    for(int z = 0; z < con.getStringList(p + ".PotionEffects").size(); z++){
	    if(z == 0){
	    n = n + con.getStringList(p + ".PotionEffects").get(z);
	    } else {
	    n = n + "%%" + con.getStringList(p + ".PotionEffects").get(z);
	    }
	    }
	    }
	    
		List<String> o = new ArrayList<String>();
		if(!Kits.kititems.containsKey(kitname)){
		o.add(n);
		Kits.kititems.put(kitname, Arrays.asList(n));
		} else {
		for(int t = 0; t < Kits.kititems.get(kitname).size(); t++){
		o.add(Kits.kititems.get(kitname).get(t));
		}
		o.add(n);
		Kits.kititems.put(kitname, o);
		}
		}
		}
		}
		} catch(Exception ex){
		//
		}
	}
	
	
	
	
	
	
}
