package com.LuckyBlock.Enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Lightning extends Enchantment {
	
	
	
	public Lightning(int id){
	super(id);
	}
	
	@Override
	public boolean canEnchantItem(ItemStack item){
	return true;
	}
	
	@Override
	public boolean conflictsWith(Enchantment other){
	return false;
	}
	
	@Override
	public EnchantmentTarget getItemTarget(){
	return null;
	}
	
	@Override
	public int getMaxLevel(){
	return 10;
	}
	
	@Override
	public String getName(){
	return "Lightning";
	}
	
	@Override
	public int getId(){
	return 25;
	}
	
	@Override
	public int getStartLevel(){
	return 1;
	}
	
	
	
}
