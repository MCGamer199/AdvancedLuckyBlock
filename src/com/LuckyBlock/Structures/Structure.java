package com.LuckyBlock.Structures;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Structure {


    private static List<Structure> structures = new ArrayList<Structure>();
    Random random = new Random();
    private int id;
    private Location loc;


    public Structure() {
        //
    }

    public static List<Structure> getStructures() {
        return structures;
    }

    public int getId() {
        return id;
    }

    public void build(Location loc) {
        this.loc = loc;
        structures.add(this);
    }

    public Location getLocation() {
        return loc;
    }


}
