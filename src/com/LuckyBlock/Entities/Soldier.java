package com.LuckyBlock.Entities;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Resources.SchedulerTask;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Soldier extends CustomEntities {

    public static List<UUID> soldiers = new ArrayList<UUID>();
    private LivingEntity target;
    public Soldier(SpawnWay way) {
        if (way == SpawnWay.DEFAULT) {
            setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
            setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        }
        type = EntityType.ZOMBIE;
    }

    @Override
    public void spawn(Location loc) {
        World world = loc.getWorld();
        entity = (Monster) world.spawnEntity(loc, type);
        entity.setMaxHealth(99999);
        entity.setHealth(50);
        entity.setCanPickupItems(true);
        entity.setCustomName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Soldier");
        soldiers.add(entity.getUniqueId());
        refresh();
        super.spawn(loc);
    }

    private void refresh() {
        final Creature c = (Creature) entity;
        final SchedulerTask task = new SchedulerTask();
        task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable() {
            int x = 0;

            @Override
            public void run() {
                if (entity.isValid()) {
                    if (target != null) {
                        c.setTarget(target);
                    }
                    Damageable d = (Damageable) entity;
                    String name = ChatColor.DARK_RED + "" + ChatColor.BOLD + "Soldier";
                    if (x == 0) {
                        name = ChatColor.DARK_RED + "" + ChatColor.BOLD + "Soldier ";
                    } else if (x == 1) {
                        name = ChatColor.RED + "" + ChatColor.BOLD + "Soldier ";
                    } else if (x == 2) {
                        name = ChatColor.GOLD + "" + ChatColor.BOLD + "Soldier ";
                    } else if (x == 3) {
                        name = ChatColor.YELLOW + "" + ChatColor.BOLD + "Soldier ";
                    } else if (x == 4) {
                        name = ChatColor.GREEN + "" + ChatColor.BOLD + "Soldier ";
                    } else if (x == 5) {
                        name = ChatColor.YELLOW + "" + ChatColor.BOLD + "Soldier ";
                    } else if (x == 6) {
                        name = ChatColor.GOLD + "" + ChatColor.BOLD + "Soldier ";
                    } else if (x == 4) {
                        name = ChatColor.RED + "" + ChatColor.BOLD + "Soldier ";
                    }
                    if (d.getHealth() > ((d.getHealth() / 10) * 9) - 1) {
                        name = name + ChatColor.GREEN + ChatColor.BOLD + ((int) d.getHealth());
                    } else if (d.getHealth() > ((d.getHealth() / 10) * 6) - 1) {
                        name = name + ChatColor.YELLOW + ChatColor.BOLD + ((int) d.getHealth());
                    } else if (d.getHealth() > ((d.getHealth() / 10) * 3) - 1) {
                        name = name + ChatColor.GOLD + ChatColor.BOLD + ((int) d.getHealth());
                    } else {
                        name = name + ChatColor.RED + ChatColor.BOLD + ((int) d.getHealth());
                    }
                    entity.setCustomName(name);
                    if (x < 5) {
                        x++;
                    } else {
                        x = 0;
                    }
                } else {
                    task.run();
                }
            }
        }, 10L, 4L));
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public static enum SpawnWay {DEFAULT, CUSTOM}


}
