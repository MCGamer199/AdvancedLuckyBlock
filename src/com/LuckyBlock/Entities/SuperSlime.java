package com.LuckyBlock.Entities;

import com.LuckyBlock.Engine.LuckyBlock;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Slime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuperSlime extends CustomEntities {


    public static List<UUID> all = new ArrayList<UUID>();
    private Slime[] slimes = new Slime[16];
    private int size = 0;
    private int starthealth = 20;
    private int slimesize = 4;


    public SuperSlime(int starthealth) {
        this.starthealth = starthealth;
        type = EntityType.SLIME;
    }

    @Override
    public void spawn(Location loc) {
        World world = loc.getWorld();
        for (int x = 0; x < size; x++) {
            Slime slime = (Slime) world.spawnEntity(loc, EntityType.SLIME);
            slime.setMaxHealth(starthealth);
            slime.setSize(slimesize);
            slime.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lucky Slime");
            all.add(slime.getUniqueId());
            Damageable d = (Damageable) slime;
            slime.setHealth(d.getMaxHealth());
            slimes[x] = slime;
            if (x > 0) {
                slime.setPassenger(slimes[x - 1]);
            }
            LuckyBlock.instance.Slimed(slime);
        }
        super.spawn(loc);
    }

    public Slime[] getSlimes() {
        return slimes;
    }

    public int getStarthealth() {
        return starthealth;
    }

    public int getSlimesize() {
        return slimesize;
    }

    public void setSlimesize(int slimesize) {
        this.slimesize = slimesize;
    }

    public void addSlime() {
        size++;
    }


}
