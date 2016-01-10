package com.LuckyBlock.Events;


import net.minecraft.server.v1_8_R3.ItemFood;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    String f = ChatColor.GOLD + "" + ChatColor.BOLD + "Fast Food!";

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getItem() == null) {
            return;
        }
        Player player = event.getPlayer();
        Action a = event.getAction();
        ItemStack item = event.getItem();
        int value = 0;
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        if (nmsStack.getItem() instanceof ItemFood) {
            ItemFood food = (ItemFood) nmsStack.getItem();
            if (item.getType() != Material.RAW_FISH && item.getType() != Material.COOKED_FISH) {
                value = food.getNutrition(null);
            } else {
                if (item.getType() == Material.RAW_FISH) {
                    value = 2;
                } else if (item.getType() == Material.COOKED_FISH) {
                    value = 5;
                }
            }
        }
        if (!(player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName())) {
            return;
        }
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            int food = player.getFoodLevel();
            if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
                if (food < 20) {
                    if (item.getItemMeta().getDisplayName().equalsIgnoreCase(f)) {
                        if (item.getAmount() > 1) {
                            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                        } else {
                            player.setItemInHand(new ItemStack(Material.AIR));
                            player.updateInventory();
                        }
                        player.setFoodLevel(food + value);
                        player.playSound(player.getLocation(), Sound.BURP, 1, 1);
                    }
                }
            }
        }
    }

}
