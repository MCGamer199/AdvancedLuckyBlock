package com.LuckyBlock.Resources;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.logic.Range;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Detector {


    private int id;
    private String name;
    private String[] blocks = new String[64];
    private List<String> rblocks = new ArrayList<String>();
    private Range range;
    private Location loc;
    private UUID uuid;


    public Detector(int id) {
        this.id = id;
        uuid = UUID.randomUUID();
        range = new Range(7, 7, 7);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getBlocks() {
        return blocks;
    }


    public List<String> getRblocks() {
        return rblocks;
    }


    public void addRangedBlock(Block block) {
        rblocks.add(LuckyBlockAPI.createDim(block));
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public UUID getUuid() {
        return uuid;
    }


    public void addBlock(Block block) {
        for (int x = 0; x < blocks.length; x++) {
            if (blocks[x] == null) {
                blocks[x] = LuckyBlockAPI.createDim(block);
                x = blocks.length;
            }
        }
    }


    public void addBlock(String dim) {
        for (int x = 0; x < blocks.length; x++) {
            if (blocks[x] == null) {
                blocks[x] = dim;
                x = blocks.length;
            }
        }
    }


    public void dispose() {
        if (LuckyBlockAPI.detectors.contains(this)) {
            LuckyBlock.instance.data.set(LuckyBlockAPI.getDet(id), null);
            LuckyBlockAPI.detectors.remove(this);
        }
        for (String s : blocks) {
            if (s != null) {
                if (LuckyBlockAPI.getBlock(s) != null) {
                    Block block = LuckyBlockAPI.getBlock(s);
                    block.setType(Material.AIR);
                }
            }
        }
        LuckyBlockAPI.saveConfigs();
    }


    public void save() {
        for (int x = 0; x < LuckyBlockAPI.detectors.size(); x++) {
            Detector d = LuckyBlockAPI.detectors.get(x);
            if (d.getId() == id) {
                LuckyBlockAPI.detectors.remove(d);
            }
        }
        LuckyBlock.instance.data.set("Detectors.Detector" + uuid + ".ID", id);
        if (name != null) {
            LuckyBlock.instance.data.set("Detectors.Detector" + uuid + ".Name", name);
        }
        LuckyBlock.instance.data.set("Detectors.Detector" + uuid + ".Range.x", range.getX());
        LuckyBlock.instance.data.set("Detectors.Detector" + uuid + ".Range.y", range.getY());
        LuckyBlock.instance.data.set("Detectors.Detector" + uuid + ".Range.z", range.getZ());
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] != null) {
                list.add(blocks[i]);
            }
        }
        LuckyBlock.instance.data.set("Detectors.Detector" + uuid + ".Blocks", list);
        LuckyBlockAPI.saveConfigs();
        LuckyBlockAPI.detectors.add(this);
    }


    public void registerBlocks() {
        if (rblocks.size() == 0) {
            int i = 0;
            for (int x = range.getX() * -1; x < (range.getX() + 1); x++) {
                for (int y = range.getY() * -1; y < (range.getY() + 1); y++) {
                    for (int z = range.getZ() * -1; z < (range.getZ() + 1); z++) {
                        Block block = Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z);
                        addRangedBlock(block);
                        i++;
                    }
                }
            }
            LuckyBlock.instance.getLogger().info("Registered " + i + " blocks!");
        }
    }


}
