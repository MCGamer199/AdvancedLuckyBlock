package com.LuckyBlock.Structures;

import com.LuckyBlock.Engine.LuckyBlock;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class StructureFiles {

    protected static LuckyBlock clss = LuckyBlock.instance;

    static File luckytreeF = new File(clss.getDataFolder() + File.separator + "/Structures/LuckyTree.yml");
    public static FileConfiguration luckytree = YamlConfiguration.loadConfiguration(luckytreeF);
    public static FileConfiguration luckytrap = YamlConfiguration.loadConfiguration(luckytreeF);
    public static FileConfiguration pumpkintower = YamlConfiguration.loadConfiguration(luckytreeF);
    public static FileConfiguration luckydungeon = YamlConfiguration.loadConfiguration(luckytreeF);
    static File luckytrapF = new File(clss.getDataFolder() + File.separator + "/Structures/LuckyTrap.yml");
    static File pumpkintowerF = new File(clss.getDataFolder() + File.separator + "/Structures/PumpkinTower.yml");
    static File luckydungeonF = new File(clss.getDataFolder() + File.separator + "/Structures/LuckyDungeon.yml");

    public static void loadFiles() {
        LuckyTree.Tall[0] = 4;
        LuckyTree.Tall[1] = 9;
        luckytree.addDefault("SpawnRate", 10);
        luckytree.addDefault("MinTall", 4);
        luckytree.addDefault("MaxTall", 9);
        LuckyTree.Tall[0] = luckytree.getInt("MinTall");
        LuckyTree.Tall[1] = luckytree.getInt("MaxTall");
        luckytrap.addDefault("SpawnRate", 98);
        pumpkintower.addDefault("SpawnRate", 98);
        luckydungeon.addDefault("SpawnRate", 97);
        luckytree.options().copyDefaults(true);
        luckytrap.options().copyDefaults(true);
        pumpkintower.options().copyDefaults(true);
        luckydungeon.options().copyDefaults(true);
        try {
            luckytree.save(luckytreeF);
            luckytrap.save(luckytrapF);
            pumpkintower.save(pumpkintowerF);
            luckydungeon.save(luckydungeonF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
