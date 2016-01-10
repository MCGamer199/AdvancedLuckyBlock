package com.LuckyBlock.Entities;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Resources.SchedulerTask;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuperBlaze extends CustomEntities {

    public static List<UUID> blazes = new ArrayList<UUID>();
    private LivingEntity target;


    public SuperBlaze() {
        type = EntityType.BLAZE;
    }

    @Override
    public void spawn(Location loc) {
        World world = loc.getWorld();
        entity = (Monster) world.spawnEntity(loc, type);
        entity.setMaxHealth(1000000);
        entity.setHealth(270);
        entity.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Super Blaze");
        blazes.add(entity.getUniqueId());
        refresh();
        super.spawn(loc);
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    private void refresh() {
        final Creature c = (Creature) entity;
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            @Override
            public void run() {
                if (entity.isValid()) {
                    if (target != null) {
                        c.setTarget(target);
                    }
                    if (c.getTarget() == null) {
                        setNearbyEntityAsTarget(c, 30);
                    }
                    if (c.getTarget() != null) {
                        if (c.getTarget().getLocation().distance(c.getLocation()) > 25) {
                            if (c.getTarget().getLocation().getY() > 2) {
                                c.teleport(c.getTarget());
                            }
                        }
                    }
                } else {
                    task.run();
                }
            }
        }, 10L, 4L));
    }


}
