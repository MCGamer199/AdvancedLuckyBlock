package com.LuckyBlock.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;


public class CustomEntities implements Listener {
	
	protected EntityType type;
	protected ItemStack[] equipments = new ItemStack[5];
	protected LivingEntity entity;
	
	
	public EntityType getType(){
	return type;
	}
	
	public LivingEntity getEntity(){
	return entity;
	}
	
	public void spawn(Location loc){
	if(equipments[0] != null){
	entity.getEquipment().setItemInHand(equipments[0]);
	}
	if(equipments[1] != null){
	entity.getEquipment().setHelmet(equipments[1]);
	}
	if(equipments[2] != null){
	entity.getEquipment().setChestplate(equipments[2]);
	}
	if(equipments[3] != null){
	entity.getEquipment().setLeggings(equipments[3]);
	}
	if(equipments[4] != null){
	entity.getEquipment().setBoots(equipments[4]);
	}
	}
	
	public void setItemInHand(ItemStack item){
	equipments[0] = item;
	}
	
	public void setHelmet(ItemStack item){
	equipments[1] = item;
	}
	
	public void setChestplate(ItemStack item){
	equipments[2] = item;
	}
	
	public void setLeggings(ItemStack item){
	equipments[3] = item;
	}
	
	public void setBoots(ItemStack item){
	equipments[4] = item;
	}
	
	public ItemStack[] getEquipments(){
	return equipments;
	}
	
	@EventHandler
	private void onDeath(EntityDeathEvent event){
	UUID uuid = event.getEntity().getUniqueId();
	if(Soldier.soldiers.contains(uuid)){
	Soldier.soldiers.remove(uuid);
	}
	if(SuperSlime.all.contains(uuid)){
	SuperSlime.all.remove(uuid);
	}
	if(SuperBlaze.blazes.contains(uuid)){
	SuperBlaze.blazes.remove(uuid);
	}
	}
	
	@EventHandler
	private void onDamage(EntityDamageEvent event){
	Entity entity = event.getEntity();
	if(Soldier.soldiers.contains(entity.getUniqueId())){
	if(event.getCause() == DamageCause.FALL || event.getCause() == DamageCause.DROWNING || event.getCause() == DamageCause.FIRE || event.getCause()
	== DamageCause.FIRE_TICK || event.getCause() == DamageCause.LAVA || event.getCause() == DamageCause.LIGHTNING){
	event.setCancelled(true);
	}
	if(event.getCause() == DamageCause.ENTITY_EXPLOSION || event.getCause() == DamageCause.BLOCK_EXPLOSION || event.getCause() == DamageCause.CONTACT){
	event.setDamage(event.getDamage() * 5);
	} else if(event.getCause() == DamageCause.MAGIC){
	event.setDamage(event.getDamage() * 9);
	}
	}
	}
	
	protected void setNearbyEntityAsTarget(Creature creature, int range){
	List<UUID> uuids = new ArrayList<UUID>();
	if(creature.getTarget() == null){
	for(Entity e : creature.getNearbyEntities(range, range, range)){
	if(e instanceof LivingEntity){
	LivingEntity l = (LivingEntity)e;
	if(!(l instanceof Player)){
	if(l.getUniqueId() != creature.getUniqueId()){
	if(l.getType() != creature.getType())
	uuids.add(l.getUniqueId());
	}
	} else {
	Player p = (Player)l;
	if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR){
	uuids.add(p.getUniqueId());
	}
	}
	}
	}
	}
	if(uuids.size() > 0){
	UUID u = uuids.get(new Random().nextInt(uuids.size()));
	for(Entity e : creature.getNearbyEntities(50, 50, 50)){
	if(e instanceof LivingEntity){
	LivingEntity l = (LivingEntity)e;
	if(l.getUniqueId() == u){
	creature.setTarget(l);
	}
	}
	}
	}
	}
	
	
	
}
