package com.LuckyBlock.War;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;



/**
 * <b>Cage</b>
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
public class Cage {
	
	
	private Material type;
	private byte data;
	private int cost;
	private boolean buyable;
	private boolean Default;
	private String name;
	private String displayName;
	public static HashMap<UUID, List<Cage>> playercages = new HashMap<UUID, List<Cage>>();
	public static HashMap<UUID, Cage> selectedcage = new HashMap<UUID, Cage>();
	private static List<Cage> cages = new ArrayList<Cage>();
	
	
	
	public Cage(){
	//
	}
	
	public Cage(String name, Material type){
	this.name = name;
	this.type = type;
	}
	
	public Cage(String name, Material type, byte data){
	this.type = type;
	this.name = name;
	this.data = data;
	}
	
	public void setData(byte data){
	this.data = data;
	}
	
	public void setBuyable(boolean buyable){
	this.buyable = buyable;
	}
	
	public void setCost(int cost){
	this.cost = cost;
	}
	
	public void setDefault(boolean default1){
	Default = default1;
	}
	
	public void setDisplayName(String displayName){
	this.displayName = displayName;
	}
	
	public void setType(Material type){
	this.type = type;
	}
	
	public void setName(String name){
	this.name = name;
	}
	
	public byte getData(){
	return data;
	}
	
	public String getDisplayName(){
	return displayName;
	}
	
	public String getName(){
	return name;
	}
	
	public Material getType(){
	return type;
	}
	
	public int getCost(){
	return cost;
	}
	
	public boolean isBuyable(){
	return buyable;
	}
	
	public boolean isDefault(){
	return Default;
	}
	
	public void save(){
	boolean found = false;
	for(Cage c : cages){
	if(c.isDefault()){
	found = true;
	}
	}
	if(found){
	if(Default){
	Default = false;
	}
	}
	if(cages.contains(this)){
	cages.remove(this);
	}
	cages.add(this);
	}
	
	public static List<Cage> getCages(){
	return cages;
	}
	
	public static Cage getDefaultCage(){
	Cage c = null;
	for(Cage cc : getCages()){
	if(cc.isDefault()){
	c = cc;
	}
	}
	return c;
	}
	
	public static Cage fromName(String name){
	Cage cage = null;
	for(Cage c : getCages()){
	if(c.getName().equalsIgnoreCase(name)){
	cage = c;
	}
	}
	return cage;
	}
	
	
	
	
	
	
	
	
}
