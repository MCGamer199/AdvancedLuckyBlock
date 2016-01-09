package com.LuckyBlock.Structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;

public class Structure {
	
	
	private int id;
	private Location loc;
	Random random = new Random();
	private static List<Structure> structures = new ArrayList<Structure>();
	
	
	public Structure(){
	//
	}
	
	public int getId(){
	return id;
	}
	
	public void build(Location loc){
	this.loc = loc;
	structures.add(this);
	}
	
	public Location getLocation(){
	return loc;
	}
	
	public static List<Structure> getStructures(){
	return structures;
	}
	
	
	
	
	
	
}
