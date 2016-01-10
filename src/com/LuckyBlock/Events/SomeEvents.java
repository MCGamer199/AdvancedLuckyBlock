package com.LuckyBlock.Events;


import com.LuckyBlock.Commands.LuckyBlockCommand;
import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Engine.LuckyBlockAPI;
import com.LuckyBlock.Engine.LuckyBlockWorld;
import com.LuckyBlock.Engine.Types;
import com.LuckyBlock.Entities.SuperSlime;
import com.LuckyBlock.Resources.BlockAbilities;
import com.LuckyBlock.Resources.Detector;
import com.LuckyBlock.Resources.ParticleEffect;
import com.LuckyBlock.Resources.Team;
import com.LuckyBlock.Structures.LuckyTree;
import com.LuckyBlock.War.War;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@SuppressWarnings("deprecation")

public class SomeEvents implements Listener {
    ChatColor red = ChatColor.RED;
    ChatColor blue = ChatColor.BLUE;
    ChatColor aqua = ChatColor.AQUA;
    ChatColor black = ChatColor.BLACK;
    ChatColor bold = ChatColor.BOLD;
    ChatColor darkaqua = ChatColor.DARK_AQUA;
    ChatColor darkblue = ChatColor.DARK_BLUE;
    ChatColor darkgray = ChatColor.DARK_GRAY;
    ChatColor darkgreen = ChatColor.DARK_GREEN;
    ChatColor darkpurple = ChatColor.DARK_PURPLE;
    ChatColor darkred = ChatColor.DARK_RED;
    ChatColor gold = ChatColor.GOLD;
    ChatColor gray = ChatColor.GRAY;
    ChatColor green = ChatColor.GREEN;
    ChatColor italic = ChatColor.ITALIC;
    ChatColor lightpurple = ChatColor.LIGHT_PURPLE;
    ChatColor magic = ChatColor.MAGIC;
    ChatColor reset = ChatColor.RESET;
    ChatColor strikethrough = ChatColor.STRIKETHROUGH;
    ChatColor underline = ChatColor.UNDERLINE;
    ChatColor white = ChatColor.WHITE;
    ChatColor yellow = ChatColor.YELLOW;

    public static void testForBlock(Block block, int newCurrent) {
        if (newCurrent > 0) {
            Block where = null;
            boolean done = false;
            if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block.getRelative(BlockFace.DOWN)))) {
                done = true;
                where = block.getRelative(BlockFace.DOWN);
            }
            if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST)))) {
                done = true;
                where = block.getRelative(BlockFace.EAST);
            }
            if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST)))) {
                done = true;
                where = block.getRelative(BlockFace.WEST);
            }
            if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block.getRelative(BlockFace.SOUTH)))) {
                done = true;
                where = block.getRelative(BlockFace.SOUTH);
            }
            if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block.getRelative(BlockFace.NORTH)))) {
                done = true;
                where = block.getRelative(BlockFace.NORTH);
            }
            if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP)))) {
                done = true;
                where = block.getRelative(BlockFace.UP);
            }
            if (done) {
                BreakLuckyBlock.openLB(where, LuckyBlockAPI.getLB(LuckyBlockAPI.createDim(where)), null);
            }
        }
    }

    @EventHandler
    public void onPlaceRandomChest(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.CHEST) {
            if (player.getItemInHand().hasItemMeta()) {
                if (player.getItemInHand().getItemMeta().hasDisplayName()) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equals(green + "" + bold + italic + "Random Chest")) {
                        Chest chest = (Chest) block.getState();
                        Random placeR = new Random();
                        Random idR = new Random();
                        Random amountR = new Random();
                        Random TimesR = new Random();
                        Random dataR = new Random();
                        for (int Times = TimesR.nextInt(18); Times > 1; Times--) {
                            int id = idR.nextInt(80) + 1;
                            int place = placeR.nextInt(27);
                            if (id < 7) {
                                chest.getInventory().setItem(place, new ItemStack(Material.FLINT_AND_STEEL));
                            } else if (id > 6 && id < 13) {
                                chest.getInventory().setItem(place, new ItemStack(Material.WOOD_AXE));
                            } else if (id > 12 && id < 19) {
                                chest.getInventory().setItem(place, new ItemStack(Material.STONE_PICKAXE));
                            } else if (id > 18 && id < 25) {
                                chest.getInventory().setItem(place, new ItemStack(Material.WOOD_PICKAXE));
                            } else if (id > 24 && id < 31) {
                                chest.getInventory().setItem(place, new ItemStack(Material.WOOD_HOE));
                            } else if (id > 30 && id < 37) {
                                chest.getInventory().setItem(place, new ItemStack(Material.CHAINMAIL_LEGGINGS));
                            } else if (id > 36 && id < 43) {
                                chest.getInventory().setItem(place, new ItemStack(Material.WOOD_SPADE));
                            } else if (id > 42 && id < 49) {
                                chest.getInventory().setItem(place, new ItemStack(Material.LEASH));
                            } else if (id > 48 && id < 55) {
                                int amount = amountR.nextInt(4) + 2;
                                chest.getInventory().setItem(place, new ItemStack(Material.SEEDS, amount));
                            } else if (id > 54 && id < 61) {
                                int amount = amountR.nextInt(3) + 2;
                                chest.getInventory().setItem(place, new ItemStack(Material.WHEAT, amount));
                            } else if (id > 60 && id < 67) {
                                int amount = amountR.nextInt(4) + 2;
                                int data = dataR.nextInt(3);
                                chest.getInventory().setItem(place, new ItemStack(Material.LOG, amount, (short) data));
                            } else if (id > 66 && id < 73) {
                                ItemStack nametag = new ItemStack(Material.NAME_TAG, 1);
                                ItemMeta nametagM = nametag.getItemMeta();
                                nametagM.setDisplayName(ChatColor.RESET + "" + player.getName());
                                nametag.setItemMeta(nametagM);
                                chest.getInventory().setItem(place, nametag);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlaceBombBlock(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (player.getItemInHand().hasItemMeta()) {
            if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(darkgreen + "BombBlock")) {
                    block.setMetadata("bombed", new FixedMetadataValue(LuckyBlock.instance, "1"));
                    block.setMetadata("playerB", new FixedMetadataValue(LuckyBlock.instance, player.getName()));
                    String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("BombBlock.place"));
                    g = g.replace("%Player%", player.getName());
                    player.sendMessage(g);
                }
            }
        }
    }

    @EventHandler
    public void onBreakBombBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (block.hasMetadata("bombed")) {
            if (block.hasMetadata("playerB")) {
                String bombed = block.getMetadata("bombed").get(0).asString();
                String playerB = block.getMetadata("playerB").get(0).asString();
                int x = block.getX();
                int y = block.getY();
                int z = block.getZ();
                if (bombed.equalsIgnoreCase("1")) {
                    if (!(playerB.equalsIgnoreCase(player.getName()))) {
                        try {
                            boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                            boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                            boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                            if (damage == true) {
                                block.getWorld().createExplosion(x, y, z, 5F, setFire, breakBlocks);
                            } else {
                                block.getWorld().createExplosion(x, y, z, 0F, setFire, breakBlocks);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("BombBlock.break"));
                        g = g.replace("%Player%", player.getName());
                        player.sendMessage(g);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        Block block = event.getBlock();
        if (block.hasMetadata("bombed")) {
            String bombed = block.getMetadata("bombed").get(0).asString();
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            if (bombed.equalsIgnoreCase("1")) {
                try {
                    boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                    boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                    boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                    if (damage == true) {
                        block.getWorld().createExplosion(x, y, z, 5F, setFire, breakBlocks);
                    } else {
                        block.getWorld().createExplosion(x, y, z, 0F, setFire, breakBlocks);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @EventHandler
    public void onPlaceExplodingBlock(BlockPlaceEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.getItemInHand().getType() == Material.STAINED_CLAY) {
            if (player.getItemInHand().hasItemMeta()) {
                if (player.getItemInHand().getItemMeta().getDisplayName() != null)
                    if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(darkred + "Exploding!")) {
                        final Block block = event.getBlock();
                        LuckyBlock.instance.BombBlock(block, LuckyBlock.randoms.nextInt(100) + 200);
                    }
            }
        }
    }

    @EventHandler
    private void onBossHurt1(EntityDamageEvent event) {
        if (event.getEntity() instanceof Skeleton) {
            Skeleton s = (Skeleton) event.getEntity();
            if (s.getCustomName() != null) {
                if (s.getCustomName().equalsIgnoreCase(darkred + "" + bold + "Killer")) {
                    if (event.getCause() == DamageCause.MAGIC) {
                        event.setDamage(event.getDamage() * 4);
                    }
                    if (event.getCause() == DamageCause.FALL) {
                        event.setDamage(event.getDamage() * 3);
                    }
                    if (event.getCause() == DamageCause.CONTACT) {
                        event.setDamage(event.getDamage() * 2);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onBossHurt(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (skeleton.getCustomName() != null) {
                if (skeleton.getCustomName().equalsIgnoreCase(darkred + "" + bold + "Killer")) {
                    if (event.getDamager() instanceof Player) {
                        Player player = (Player) event.getDamager();
                        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                            if (player.getItemInHand().getType() == Material.COAL) {
                                skeleton.setFireTicks(50);
                                if (event.getDamage() < 300) {
                                    for (int x = (int) (LuckyBlock.randoms.nextInt(5) + event.getDamage()); x > 0; x--) {
                                        Item d = skeleton.getWorld().dropItem(skeleton.getLocation(), new ItemStack(Material.WOOL, 1, (short) 14));
                                        d.setPickupDelay(32787);
                                        LuckyBlock.instance.BloodW(d, (LuckyBlock.randoms.nextInt(20) + 6) * 3);
                                    }
                                } else {
                                    for (int x = (int) (LuckyBlock.randoms.nextInt(5) + 300); x > 0; x--) {
                                        Item d = skeleton.getWorld().dropItem(skeleton.getLocation(), new ItemStack(Material.WOOL, 1, (short) 14));
                                        d.setPickupDelay(32787);
                                        LuckyBlock.instance.BloodW(d, (LuckyBlock.randoms.nextInt(20) + 6) * 3);
                                    }
                                }
                            } else {
                                player.damage(3.0);
                                event.setCancelled(true);
                            }
                        } else {
                            if (event.getDamage() < 300) {
                                for (int x = (int) (LuckyBlock.randoms.nextInt(5) + event.getDamage()); x > 0; x--) {
                                    Item d = skeleton.getWorld().dropItem(skeleton.getLocation(), new ItemStack(Material.WOOL, 1, (short) 14));
                                    d.setPickupDelay(1000);
                                    LuckyBlock.instance.BloodW(d, (LuckyBlock.randoms.nextInt(20) + 6) * 3);
                                }
                            } else {
                                for (int x = (int) (LuckyBlock.randoms.nextInt(5) + 300); x > 0; x--) {
                                    Item d = skeleton.getWorld().dropItem(skeleton.getLocation(), new ItemStack(Material.WOOL, 1, (short) 14));
                                    d.setPickupDelay(1000);
                                    LuckyBlock.instance.BloodW(d, (LuckyBlock.randoms.nextInt(20) + 6) * 3);
                                }
                            }
                        }
                    } else {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Villager) {
            Villager villager = (Villager) event.getEntity();
            int x = villager.getLocation().getBlockX();
            int y = villager.getLocation().getBlockY();
            int z = villager.getLocation().getBlockZ();
            if (villager.getCustomName() != null) {
                if (villager.getCustomName().equalsIgnoreCase(gold + "" + bold + "Lucky Villager")) {
                    villager.setHealth(0.0);
                    try {
                        boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                        boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                        boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                        RegionContainer container = LuckyBlock.getWorldGuard().getRegionContainer();
                        RegionManager regions = container.get(villager.getWorld());
                        //boolean allow = false;
                        if (regions != null) {
                            RegionQuery query = container.createQuery();
                            ApplicableRegionSet set = query.getApplicableRegions(villager.getLocation());
                            if (!set.testState(null, DefaultFlag.PVP)) {
                                //villager.getWorld().createExplosion(x, y, z, 4f, setFire, false);
                                //return;
                            } else {

                            }
                        } else {

                            // The world has no region support or region data failed to load
                        }
                        if (damage == true) {
                            villager.getWorld().createExplosion(x, y, z, 4F, setFire, breakBlocks);
                        } else {
                            villager.getWorld().createExplosion(x, y, z, 0F, setFire, breakBlocks);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHorseJump(HorseJumpEvent event) {
        Horse horse = (Horse) event.getEntity();
        if (horse.hasMetadata("superhorse")) {
            event.setPower(20);
        }
    }

    @EventHandler
    public void onDMGSuperHorse(EntityDamageEvent event) {
        if (event.getEntity() instanceof Horse) {
            if (event.getEntity().hasMetadata("superhorse")) {
                if (event.getCause() == DamageCause.FALL) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onRightClickWolf(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Wolf) {
            if (player.getItemInHand().getType() == Material.EMERALD) {
                if (player.getItemInHand().hasItemMeta()) {
                    if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(gray + "Wolf Tool")) {
                            Wolf wolf = (Wolf) event.getRightClicked();
                            if (wolf.isTamed() == true) {
                                String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("TameItem.AlreadyTamed"));
                                g = g.replace("%Player%", player.getName());
                                player.sendMessage(g);
                            } else {
                                wolf.setTamed(true);
                                wolf.setOwner(player);
                                if (wolf.getCustomName() != null && wolf.getCustomName().equalsIgnoreCase(white + "" + bold + "Angry Wolf")) {
                                    wolf.setCustomName(red + "" + bold + "Dog");
                                }
                                String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("TameItem.Success"));
                                g = g.replace("%Player%", player.getName());
                                player.sendMessage(g);
                                Damageable dmg = (Damageable) wolf;
                                double whealth = dmg.getMaxHealth();
                                wolf.setHealth(whealth);
                                if (player.getItemInHand().getAmount() > 1) {
                                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                                } else {
                                    player.getInventory().remove(player.getItemInHand());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTakeLuckyBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action a = event.getAction();
        if (player.getItemInHand().getType() == Material.ARROW) {
            if (player.getItemInHand().hasItemMeta()) {
                if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(darkgray + "" + bold + "Lucky Block Taker")) {
                        if (a == Action.RIGHT_CLICK_BLOCK) {
                            Block block = (Block) event.getClickedBlock();
                            String dim = LuckyBlockAPI.createDim(block);
                            if (LuckyBlockAPI.luck.containsKey(dim) && LuckyBlockAPI.chances.containsKey(dim)) {
                                Types type = LuckyBlockAPI.getLB(dim);
                                if (type == null) {
                                    return;
                                }
                                String luckyB = gold + "%0";
                                int luck = LuckyBlockAPI.luck.get(dim);
                                luckyB = Types.getLuckString(luck);
                                ItemStack lb = new ItemStack(block.getType(), 1, block.getData());
                                ItemMeta lbM = lb.getItemMeta();
                                List<String> lbL = new ArrayList<String>();
                                lbL.add(luckyB);
                                lbL.add(gray + "Chance:" + LuckyBlockAPI.chances.get(dim));
                                if (LuckyBlockAPI.BlockOwner.containsKey(dim)) {
                                    lbL.add(gray + "Owner:" + green + LuckyBlockAPI.BlockOwner.get(dim));
                                }
                                lbM.setLore(lbL);
                                lbM.setDisplayName(type.getName());
                                lb.setItemMeta(lbM);
                                block.setType(Material.AIR);
                                Item item = (Item) block.getWorld().dropItem(block.getLocation(), lb);
                                item.setPickupDelay(20);
                                LuckyBlockAPI.removeLB(dim);
                                String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("TakerItem.use"));
                                player.sendMessage(g);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void WhenEatSuperCookie(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName() != null) {
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(gold + "Super Cookie")) {
                    player.setFoodLevel(player.getFoodLevel() + 6);
                    player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 100F, 0);
                    String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("SuperCookie.eat"));
                    if (!g.equalsIgnoreCase("null")) {
                        g = g.replace("%Player%", player.getName());
                        player.sendMessage(g);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHurtEntityBySuperCookie(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player player = (Player) damager;
            if (player.getItemInHand().getType() == Material.COOKIE) {
                if (player.getItemInHand().hasItemMeta()) {
                    if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(gold + "Super Cookie")) {
                            Entity damaged = event.getEntity();
                            if (damaged instanceof LivingEntity) {
                                LivingEntity l = (LivingEntity) event.getEntity();
                                l.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 0, true));
                                l.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 0, true));
                                l.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 0, true));
                                String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("SuperCookie.hit"));
                                g = g.replace("%Player%", player.getName());
                                g = g.replace("%Type%", l.getType().toString());
                                if (!g.equalsIgnoreCase("null")) {
                                    player.sendMessage(g);
                                }
                            }
                            if (!(player.getGameMode() == GameMode.CREATIVE)) {
                                if (player.getItemInHand().getAmount() > 1) {
                                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                                } else {
                                    player.getInventory().removeItem(player.getItemInHand());
                                    player.updateInventory();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onUseLuckyTool(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = (Player) event.getPlayer();
            Block block = event.getClickedBlock();
            if (player.getItemInHand().getType() == Material.NAME_TAG) {
                if (player.getItemInHand().hasItemMeta()) {
                    if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(yellow + "Lucky Block Tool")) {
                            String dim = LuckyBlockAPI.createDim(block);
                            if (LuckyBlockAPI.luck.containsKey(dim) && LuckyBlockAPI.chances.containsKey(dim)) {
                                int luckyb = LuckyBlockAPI.luck.get(dim);
                                player.sendMessage(blue + "Lucky Block : " + green + "true");
                                if (luckyb > 0 && luckyb < 40) {
                                    player.sendMessage(yellow + "%" + luckyb);
                                } else if (luckyb == 0) {
                                    player.sendMessage(gold + "%" + luckyb);
                                } else if (luckyb >= 40) {
                                    player.sendMessage(green + "%" + luckyb);
                                } else {
                                    player.sendMessage(red + "%" + luckyb);
                                }
                            } else {
                                player.sendMessage(blue + "Lucky Block : " + red + "false");
                            }
                        } else if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(yellow + "Advanced Lucky Block Tool")) {
                            String dim = LuckyBlockAPI.createDim(block);
                            if (LuckyBlockAPI.luck.containsKey(dim) && LuckyBlockAPI.chances.containsKey(dim)) {
                                int luckyb = LuckyBlockAPI.luck.get(dim);
                                int chancei = LuckyBlockAPI.chances.get(dim);
                                if (Types.fromBlock(dim) == null) {
                                    return;
                                }
                                Types type = Types.fromBlock(dim);
                                FileConfiguration file = null;
                                if (luckyb > -1 && luckyb < 20) {
                                    file = Types.getFile(type, 0);
                                } else if (luckyb > 19 && luckyb < 51) {
                                    file = Types.getFile(type, 1);
                                } else if (luckyb > 50 && luckyb < 100) {
                                    file = Types.getFile(type, 2);
                                } else if (luckyb > 99 && luckyb < 200) {
                                    file = Types.getFile(type, 3);
                                } else if (luckyb > 199) {
                                    file = Types.getFile(type, 4);
                                } else if (luckyb < 0 && luckyb > -50) {
                                    file = Types.getFile(type, 5);
                                } else if (luckyb < -49 && luckyb > -101) {
                                    file = Types.getFile(type, 6);
                                } else if (luckyb < -100) {
                                    file = Types.getFile(type, 7);
                                }
                                if (file != null) {
                                    int cc = chancei;
                                    List<String> lucks = file.getStringList("Luck.Values");
                                    int zz = -1;
                                    for (int x = 0; x < lucks.size(); x++) {
                                        String[] s = lucks.get(x).split("#");
                                        for (int y = 0; y < s.length; y++) {
                                            if (s[y].startsWith("Chance:")) {
                                                String[] d = s[y].split("Chance:");
                                                String[] dd = d[1].split("-");
                                                if (dd.length == 1) {
                                                    try {
                                                        if (Integer.parseInt(d[1]) == cc) {
                                                            zz = x;
                                                        }
                                                    } catch (Exception ex) {
                                                        ex.printStackTrace();
                                                    }
                                                } else if (dd.length == 2) {
                                                    List<Integer> l = new ArrayList<Integer>();
                                                    try {
                                                        for (int k = Integer.parseInt(dd[0]); k < (Integer.parseInt(dd[1]) + 1); k++) {
                                                            l.add(k);
                                                        }
                                                        if (l.contains(cc)) {
                                                            zz = x;
                                                        }
                                                    } catch (Exception ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (zz != -1) {
                                        String title = "<Mising Title!>";
                                        String gb = "null";
                                        String[] s = lucks.get(zz).split("#");
                                        for (int y = 0; y < s.length; y++) {
                                            if (s[y].startsWith("Title:")) {
                                                String[] a = s[y].split("Title:");
                                                if (a.length > 1) {
                                                    title = ChatColor.translateAlternateColorCodes('&', a[1]);
                                                }
                                            } else if (s[y].startsWith("Luck:")) {
                                                String[] a = s[y].split("Luck:");
                                                if (a.length > 1) {
                                                    gb = a[1];
                                                }
                                            }
                                        }
                                        player.sendMessage(title);
                                        if (!gb.equalsIgnoreCase("null")) {
                                            player.sendMessage(yellow + "Luck: " + gold + gb);
                                        }
                                    }
                                }
                                player.sendMessage(blue + "Lucky Block : " + green + "true");
                                if (luckyb > 0 && luckyb < 40) {
                                    player.sendMessage(yellow + "%" + luckyb);
                                } else if (luckyb == 0) {
                                    player.sendMessage(gold + "%" + luckyb);
                                } else if (luckyb >= 40) {
                                    player.sendMessage(green + "%" + luckyb);
                                } else {
                                    player.sendMessage(red + "%" + luckyb);
                                }
                            } else {
                                player.sendMessage(blue + "Lucky Block : " + red + "false");
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onActivateBomb(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            if (player.getItemInHand().hasItemMeta()) {
                if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(darkgray + "C4 Item")) {
                        for (Entity e : player.getNearbyEntities(75, 75, 75)) {
                            if (e.hasMetadata("controller")) {
                                String c = e.getMetadata("controller").get(0).asString();
                                int x = e.getLocation().getBlockX();
                                int y = e.getLocation().getBlockY();
                                int z = e.getLocation().getBlockZ();
                                if (c.equalsIgnoreCase(player.getName())) {
                                    try {
                                        boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                                        boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                                        boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                                        if (damage == true) {
                                            e.getWorld().createExplosion(x, y, z, 3.0F, setFire, breakBlocks);
                                        } else {
                                            e.getWorld().createExplosion(x, y, z, 0.0F, setFire, breakBlocks);
                                        }
                                        String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("C4.Explode"));
                                        if (!g.equalsIgnoreCase("null")) {
                                            g = g.replace("%Player%", player.getName());
                                            player.sendMessage(g);
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    e.remove();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlaceC4(BlockPlaceEvent event) {
        final Block block = event.getBlock();
        Player player = event.getPlayer();
        if (player.getItemInHand().hasItemMeta()) {
            if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(darkgray + "C4 Item")) {
                    if (player.getItemInHand().getAmount() < 2) {
                        if (player.getGameMode() != GameMode.CREATIVE) {
                            event.setCancelled(true);
                            player.updateInventory();
                            return;
                        }
                    }
                    event.setCancelled(true);
                    Item item = (Item) block.getWorld().dropItem(block.getLocation().add(0, 0, 0), new ItemStack(Material.DIAMOND));
                    item.setPickupDelay(30000);
                    LuckyBlock.instance.c4(item, block);
                    item.setMetadata("controller", new FixedMetadataValue(LuckyBlock.instance, player.getName()));
                    String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("C4.Place"));
                    if (!g.equalsIgnoreCase("null")) {
                        g = g.replace("%Player%", player.getName());
                        player.sendMessage(g);
                    }
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                }
            }
        }
    }

    @EventHandler
    public void onThrowBomb(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (event.getItemDrop().getItemStack().hasItemMeta()) {
            if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() != null) {
                if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(darkred + "Trap")) {
                    event.getItemDrop().setMetadata("trap", new FixedMetadataValue(LuckyBlock.instance, "true"));
                    String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Trap.Place"));
                    if (!g.equalsIgnoreCase("null")) {
                        g = g.replace("%Player%", player.getName());
                        player.sendMessage(g);
                    }
                    event.getItemDrop().setPickupDelay(100);
                }
            }
        }
    }

    @EventHandler
    public void onPickupBomb(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().hasMetadata("trap")) {
            String trap = event.getItem().getMetadata("trap").get(0).asString();
            if (trap.equalsIgnoreCase("true")) {
                player.getWorld().createExplosion(player.getLocation(), 3F);
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                event.setCancelled(true);
                event.getItem().remove();
                String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Trap.Pickup"));
                if (!g.equalsIgnoreCase("null")) {
                    g = g.replace("%Player%", player.getName());
                    player.sendMessage(g);
                    player.getWorld().createExplosion(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 10F, false, false);
                }
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball) {
            if (event.getEntity().getShooter() instanceof Player) {
                Location loc = event.getEntity().getLocation();
                if (event.getEntity().hasMetadata("shotfrom")) {
                    try {
                        String shotFrom = event.getEntity().getMetadata("shotfrom").get(0).asString();
                        if (shotFrom.startsWith("gun")) {
                            ParticleEffect.FLAME.display(0.1f, 0.1f, 0.1f, 0, 25, event.getEntity().getLocation(), 56);
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld() == loc.getWorld()) {
                                    double distance = p.getLocation().distance(loc);
                                    int maxdistance = 100;
                                    if (distance < 100) {
                                        float volume = (float) (1 - (distance / maxdistance));
                                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, volume, 2);
                                    }
                                }
                            }
                        } else if (shotFrom.equalsIgnoreCase("e")) {
                            Random explodeR = new Random();
                            int explode = explodeR.nextInt(4) + 3;
                            Entity e = event.getEntity();
                            int x = e.getLocation().getBlockX();
                            int y = e.getLocation().getBlockY();
                            int z = e.getLocation().getBlockZ();
                            try {
                                boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                                boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                                boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                                if (damage == true) {
                                    e.getWorld().createExplosion(x, y, z, explode, setFire, breakBlocks);
                                } else {
                                    e.getWorld().createExplosion(x, y, z, 0F, setFire, breakBlocks);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.FIREWORK_BLAST, 100, 1);
                            ParticleEffect.SPELL_INSTANT.display((float) 0.5, (float) 0.6, (float) 0.5, 1, 200, event.getEntity().getLocation(), 40);
                        } else if (shotFrom.equalsIgnoreCase("bomb")) {
                            Item item = (Item) event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.SLIME_BALL));
                            item.setPickupDelay(30000);
                            LuckyBlock.instance.Bomb(item);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (event.getEntity() instanceof Fireball) {
            if (event.getEntity().hasMetadata("shotfrom")) {
                String shotFrom = event.getEntity().getMetadata("shotfrom").get(0).asString();
                if (shotFrom.equalsIgnoreCase("Fireball")) {
                    if (event.getEntity().getShooter() instanceof Player) {
                        Player player = (Player) event.getEntity().getShooter();
                        ParticleEffect.VILLAGER_HAPPY.display((float) 0.3, (float) 0.6, (float) 0.3, 1, 15, player.getLocation(), 50);
                        Fireball fireball = (Fireball) event.getEntity();
                        fireball.getLocation().getBlock().setType(Material.LAVA);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onUseWeapons(PlayerInteractEvent event) {
        if (event.getItem() == null) {
            return;
        }
        final Player player = (Player) event.getPlayer();
        Material m = player.getItemInHand().getType();
        if (m == Material.BLAZE_POWDER) {
            if (!(player.getItemInHand().hasItemMeta())) {
                return;
            }
            if (player.getItemInHand().getItemMeta().getDisplayName() != null)
                if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Fireball")) {
                    final Vector direction = player.getEyeLocation().getDirection().multiply(2);
                    Fireball fireball = (Fireball) player.getWorld().spawnEntity(player.getEyeLocation().add(direction.getX(), direction.getY(),
                            direction.getZ()), EntityType.FIREBALL);
                    fireball.getFireTicks();
                } else if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Tnt")) {
                    final Vector direction = player.getEyeLocation().getDirection().multiply(2);
                    TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(player.getEyeLocation().add(direction.getX(), direction.getY(),
                            direction.getZ()), EntityType.PRIMED_TNT);
                    tnt.setFuseTicks(80);
                } else if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "shoot")) {
                    if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                        return;
                    }
                    if (!(player.getGameMode().equals(GameMode.CREATIVE))) {
                        if (player.getItemInHand().getAmount() > 1) {
                            Snowball s = player.launchProjectile(Snowball.class);
                            s.setShooter(event.getPlayer());
                            s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "e"));
                            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 100, 2);
                            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                        } else {
                            Snowball s = player.launchProjectile(Snowball.class);
                            s.getEntityId();
                            s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "e"));
                            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 100, 2);
                            player.getInventory().removeItem(player.getItemInHand());
                            player.updateInventory();
                        }

                    } else {
                        Snowball s = player.launchProjectile(Snowball.class);
                        s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "e"));
                        player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 100, 2);
                    }
                }
        }
        if (m == Material.STICK || m == Material.WOOD_HOE || m == Material.STONE_HOE || m == Material.GOLD_HOE || m == Material.IRON_HOE
                || m == Material.DIAMOND_HOE) {
            if (player.getItemInHand().hasItemMeta()) {
                if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                    String name = player.getItemInHand().getItemMeta().getDisplayName();
                    if (name.startsWith(gold + "Gun")
                            || name.equalsIgnoreCase(darkred + "Super") || name.equalsIgnoreCase(darkred + "Super X")
                            || name.equalsIgnoreCase(red + "" + bold + "Health Rod") || name.equalsIgnoreCase(aqua + "" + bold + "Lightning Rod")) {
                        if (player.getItemInHand().getAmount() > 1) {
                            final Snowball s = player.launchProjectile(Snowball.class);
                            s.getEntityId();
                            s.setShooter(player);
                            if (player.getItemInHand().getItemMeta().hasLore()) {
                                if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range I")) {
                                    s.setVelocity(s.getVelocity().multiply(2));
                                } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range II")) {
                                    s.setVelocity(s.getVelocity().multiply(3));
                                } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range III")) {
                                    s.setVelocity(s.getVelocity().multiply(4));
                                }
                            }
                            if (name.equalsIgnoreCase(gold + "Gun")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun"));
                                LuckyBlock.instance.Guns(s, 1);
                            } else if (name.equalsIgnoreCase(gold + "Gun I")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun1"));
                                LuckyBlock.instance.Guns(s, 2);
                            } else if (name.equalsIgnoreCase(gold + "Gun II")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun2"));
                                LuckyBlock.instance.Guns(s, 3);
                            } else if (name.equalsIgnoreCase(gold + "Gun III")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun3"));
                                LuckyBlock.instance.Guns(s, 4);
                            } else if (name.equalsIgnoreCase(gold + "Gun IV")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun4"));
                                LuckyBlock.instance.Guns(s, 5);
                            } else if (name.equalsIgnoreCase(gold + "Gun V")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun5"));
                                LuckyBlock.instance.Guns(s, 6);
                            } else if (name.equalsIgnoreCase(gold + "Gun VI")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun6"));
                                LuckyBlock.instance.Guns(s, 7);
                            } else if (name.equalsIgnoreCase(gold + "Gun VII")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun7"));
                                LuckyBlock.instance.Guns(s, 8);
                            } else if (name.equalsIgnoreCase(gold + "Gun VIII")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun8"));
                                LuckyBlock.instance.Guns(s, 9);
                            } else if (name.equalsIgnoreCase(gold + "Gun IX")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun9"));
                                LuckyBlock.instance.Guns(s, 10);
                            } else if (name.equalsIgnoreCase(gold + "Gun X")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun10"));
                                LuckyBlock.instance.Guns(s, 11);
                            } else if (name.equalsIgnoreCase(gold + "Gun XI")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun11"));
                                LuckyBlock.instance.Guns(s, 12);
                            } else if (name.equalsIgnoreCase(gold + "Gun XII")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun12"));
                                LuckyBlock.instance.Guns(s, 13);
                            } else if (name.equalsIgnoreCase(gold + "Gun XIII")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun13"));
                                LuckyBlock.instance.Guns(s, 14);
                            } else if (name.equalsIgnoreCase(gold + "Gun XIV")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun14"));
                                LuckyBlock.instance.Guns(s, 15);
                            } else if (name.equalsIgnoreCase(gold + "Gun XV")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun15"));
                                LuckyBlock.instance.Guns(s, 16);
                            } else if (name.equalsIgnoreCase(gold + "Gun XVI")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun15"));
                                LuckyBlock.instance.Guns(s, 17);
                            } else if (name.equalsIgnoreCase(darkred + "Super")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun16"));
                                LuckyBlock.instance.Guns(s, 18);
                            } else if (name.equalsIgnoreCase(darkred + "Super X")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gun17"));
                                LuckyBlock.instance.Guns(s, 19);
                            } else if (name.equals(red + "" + bold + "Health Rod")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gunh"));
                                LuckyBlock.instance.Guns(s, 20);
                            } else if (name.equals(aqua + "" + bold + "Lightning Rod")) {
                                s.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "gunl"));
                                LuckyBlock.instance.Guns(s, 21);
                            }
                            if (player.getItemInHand().getItemMeta().hasLore()) {
                                if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range I")) {
                                    s.setVelocity(s.getVelocity().multiply(3));
                                } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range II")) {
                                    s.setVelocity(s.getVelocity().multiply(3));
                                }
                            }
                            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 100, 2);
                            if (!(player.getGameMode().equals(GameMode.CREATIVE))) {
                                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                            }
                        }
                    }
                }
            }
        } else if (m == Material.SLIME_BALL) {
            if (player.getItemInHand().hasItemMeta()) {
                if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(darkgreen + "Timed Bomb")) {
                        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
                            return;
                        }
                        if (!(player.getGameMode() == GameMode.CREATIVE)) {
                            if (player.getItemInHand().getAmount() > 1) {
                                final Snowball snowball = player.launchProjectile(Snowball.class);
                                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                                if (player.getItemInHand().getItemMeta().hasLore())
                                    if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range I")) {
                                        snowball.setVelocity(snowball.getVelocity().multiply(2));
                                    } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range II")) {
                                        snowball.setVelocity(snowball.getVelocity().multiply(3));
                                    } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range III")) {
                                        snowball.setVelocity(snowball.getVelocity().multiply(4));
                                    }
                                snowball.setShooter(player);
                                snowball.setBounce(true);
                                snowball.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "bomb"));
                                LuckyBlock.instance.TBomb(snowball);
                            } else {
                                final Snowball snowball = player.launchProjectile(Snowball.class);
                                if (player.getItemInHand().getItemMeta().hasLore())
                                    if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range I")) {
                                        snowball.setVelocity(snowball.getVelocity().multiply(2));
                                    } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range II")) {
                                        snowball.setVelocity(snowball.getVelocity().multiply(3));
                                    } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range III")) {
                                        snowball.setVelocity(snowball.getVelocity().multiply(4));
                                    }
                                snowball.setShooter(player);
                                snowball.setBounce(true);
                                snowball.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "bomb"));
                                LuckyBlock.instance.TBomb(snowball);
                                player.getInventory().remove(player.getItemInHand());
                                player.updateInventory();
                            }
                        } else {
                            final Snowball snowball = player.launchProjectile(Snowball.class);
                            if (player.getItemInHand().getItemMeta().hasLore())
                                if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range I")) {
                                    snowball.setVelocity(snowball.getVelocity().multiply(2));
                                } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range II")) {
                                    snowball.setVelocity(snowball.getVelocity().multiply(3));
                                } else if (player.getItemInHand().getItemMeta().getLore().contains(gray + "Range III")) {
                                    snowball.setVelocity(snowball.getVelocity().multiply(4));
                                }
                            snowball.setShooter(player);
                            snowball.setBounce(true);
                            snowball.setMetadata("shotfrom", new FixedMetadataValue(LuckyBlock.instance, "bomb"));
                            player.getWorld().playSound(player.getLocation(), Sound.ITEM_PICKUP, 100F, 2);
                            LuckyBlock.instance.TBomb(snowball);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityHurt(EntityDamageByEntityEvent ev) {
        if (ev.getCause().equals(DamageCause.PROJECTILE)) {
            if (ev.getDamager() instanceof Arrow || ev.getDamager() instanceof Snowball) {
                if (ev.getDamager().hasMetadata("shotfrom")) {
                    String shotFrom = ev.getDamager().getMetadata("shotfrom").get(0).asString();
                    if (ev.getDamager() instanceof Arrow) {
                        if (shotFrom.equalsIgnoreCase("pistol")) {
                            ev.setDamage(20);
                        }
                    }
                    if (ev.getDamager() instanceof Snowball) {
                        Snowball s = (Snowball) ev.getDamager();
                        if (shotFrom.equalsIgnoreCase("gun")) {
                            ev.setDamage(1.0);
                        }
                        if (shotFrom.equalsIgnoreCase("gun1")) {
                            ev.setDamage(2.5);
                        }
                        if (shotFrom.equalsIgnoreCase("gun2")) {
                            ev.setDamage(5.5);
                        }
                        if (shotFrom.equalsIgnoreCase("gun3")) {
                            ev.setDamage(9.0);
                        }
                        if (shotFrom.equalsIgnoreCase("gun4")) {
                            ev.setDamage(11.0);
                        }
                        if (shotFrom.equalsIgnoreCase("gun5")) {
                            ev.setDamage(18.0);
                        } else if (shotFrom.equalsIgnoreCase("gun6")) {
                            ev.setDamage(22.0);
                        } else if (shotFrom.equalsIgnoreCase("gun7")) {
                            ev.setDamage(30.0);
                        } else if (shotFrom.equalsIgnoreCase("gun8")) {
                            ev.setDamage(39.0);
                        } else if (shotFrom.equalsIgnoreCase("gun9")) {
                            ev.setDamage(50.0);
                        } else if (shotFrom.equalsIgnoreCase("gun10")) {
                            ev.setDamage(75.0);
                        } else if (shotFrom.equalsIgnoreCase("gun11")) {
                            ev.setDamage(125.0);
                        } else if (shotFrom.equalsIgnoreCase("gun12")) {
                            ev.setDamage(210.0);
                        } else if (shotFrom.equalsIgnoreCase("gun13")) {
                            ev.setDamage(320.0);
                        } else if (shotFrom.equalsIgnoreCase("gun14")) {
                            ev.setDamage(500.0);
                        } else if (shotFrom.equalsIgnoreCase("gun15")) {
                            ev.setDamage(800.0);
                            ParticleEffect.EXPLOSION_LARGE.display(6, 0, 0, 1, 400, ev.getEntity().getLocation(), 32);
                            ParticleEffect.EXPLOSION_LARGE.display(0, 0, 6, 1, 400, ev.getEntity().getLocation(), 32);
                        } else if (shotFrom.equalsIgnoreCase("gun16")) {
                            ev.setDamage(1500);
                            ParticleEffect.EXPLOSION_LARGE.display(6, 0, 0, 1, 400, ev.getEntity().getLocation(), 32);
                            ParticleEffect.EXPLOSION_LARGE.display(0, 0, 6, 1, 400, ev.getEntity().getLocation(), 32);
                        } else if (shotFrom.equalsIgnoreCase("e")) {
                            ev.setDamage(6);
                        } else if (shotFrom.equalsIgnoreCase("gun17")) {
                            for (Entity e : s.getNearbyEntities(15, 15, 15)) {
                                if (e instanceof LivingEntity) {
                                    LivingEntity entity = (LivingEntity) e;
                                    ev.setDamage(25000.0);
                                    entity.damage(25000.0);
                                    ParticleEffect.EXPLOSION_LARGE.display(3, 0, 0, 1, 200, ev.getEntity().getLocation(), 32);
                                    ParticleEffect.EXPLOSION_LARGE.display(0, 0, 3, 1, 200, ev.getEntity().getLocation(), 32);
                                }
                            }
                        } else if (shotFrom.equals("gunh")) {
                            if (ev.getEntity() instanceof LivingEntity) {
                                LivingEntity entity = (LivingEntity) ev.getEntity();
                                ev.setCancelled(true);
                                Damageable health = (Damageable) entity;
                                try {
                                    entity.setHealth(health.getHealth() + 4);
                                } catch (Exception ex) {
                                    entity.setHealth(health.getMaxHealth());
                                }
                            }
                        } else if (shotFrom.equals("gunl")) {
                            ev.getEntity().getWorld().strikeLightning(ev.getEntity().getLocation());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void slimeSplitEvent(SlimeSplitEvent event) {
        Slime slime = (Slime) event.getEntity();
        if (SuperSlime.all.contains(slime.getUniqueId())) {
            event.setCancelled(true);
            if (slime.getSize() > 1) {
                for (int x = 0; x < 10; x++) {
                    SuperSlime s = new SuperSlime(10);
                    s.setSlimesize(slime.getSize() - 1);
                    s.addSlime();
                    s.spawn(slime.getLocation());
                }
            }
        }
    }

    @EventHandler
    public void rightclickrideablecow(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Cow) {
            if (event.getRightClicked().hasMetadata("rideable")) {
                if (event.getRightClicked().getMetadata("rideable").get(0).asBoolean() == true) {
                    Player player = event.getPlayer();
                    if (player.getVehicle() == null) {
                        event.getRightClicked().setPassenger(player);
                        String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Cow.Mount"));
                        if (!g.equalsIgnoreCase("null")) {
                            g = g.replace("%Player%", player.getName());
                            player.sendMessage(g);
                        }
                    } else {
                        String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Cow.AlreadyRiding"));
                        if (!g.equalsIgnoreCase("null")) {
                            g = g.replace("%Player%", player.getName());
                            player.sendMessage(g);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void Switch(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) {
            return;
        }
        if (event.getItem().getType() == Material.ENDER_PEARL) {
            ItemStack item = event.getItem();
            if (item.hasItemMeta()) {
                if (item.getItemMeta().hasDisplayName()) {
                    if (item.getItemMeta().getDisplayName().equals(blue + "Random Teleport")) {
                        event.setCancelled(true);
                        List<UUID> uuids = new ArrayList<UUID>();
                        for (Entity e : player.getNearbyEntities(20, 20, 20)) {
                            if (e instanceof LivingEntity) {
                                uuids.add(e.getUniqueId());
                            }
                        }
                        if (uuids.size() > 0) {
                            int thing = LuckyBlock.randoms.nextInt(uuids.size());
                            for (Entity e : player.getNearbyEntities(20, 20, 20)) {
                                if (e.getUniqueId() == uuids.get(thing)) {
                                    Location loc = e.getLocation();
                                    Location loca = player.getLocation();
                                    player.teleport(loc);
                                    e.teleport(loca);
                                    String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("TeleportItem.Teleport"));
                                    if (!g.equalsIgnoreCase("null")) {
                                        g = g.replace("%Player%", player.getName());
                                        player.sendMessage(g);
                                    }
                                    if (player.getGameMode() != GameMode.CREATIVE) {
                                        if (item.getAmount() > 1) {
                                            item.setAmount(item.getAmount() - 1);
                                        } else {
                                            player.getInventory().removeItem(item);
                                        }
                                    }
                                }
                            }
                        } else {
                            String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("TeleportItem.NoEntity"));
                            if (!g.equalsIgnoreCase("null")) {
                                g = g.replace("%Player%", player.getName());
                                player.sendMessage(g);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onCraftLB(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getType() == InventoryType.WORKBENCH) {
                CraftingInventory craft = (CraftingInventory) event.getInventory();
                LuckyBlock.instance.craft(player, craft, 1);
            }
        }
    }

    @EventHandler
    public void onCraftC4Block(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getType() == InventoryType.WORKBENCH) {
                CraftingInventory craft = (CraftingInventory) event.getInventory();
                LuckyBlock.instance.craft(player, craft, 2);
            }
        }
    }

    @EventHandler
    public void onCraftLuckyBlock(CraftItemEvent event) {
        if (event.getCurrentItem() != null) {
            if (event.getCurrentItem().hasItemMeta()) {
                if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    for (Types t : LuckyBlock.lbs) {
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(t.getName())) {
                            if (LuckyBlock.instance.config.getBoolean("UsePermission.craftluckyblock")) {
                                if (!(event.getWhoClicked().hasPermission("lb.craftlb"))) {
                                    if (event.getWhoClicked() instanceof Player) {
                                        Player player = (Player) event.getWhoClicked();
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.config.getString("Messages.NoPermission.craftluckyblock")));
                                    }
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onActivateC4Block(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (player.getItemInHand().hasItemMeta()) {
                if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(gray + "C4 Block")) {
                        if (LuckyBlock.c4.containsKey(uuid)) {
                            if (LuckyBlock.c4.get(uuid).size() > 0) {
                                for (int xx = 0; xx < LuckyBlock.c4.get(uuid).size(); xx++) {
                                    Location l = LuckyBlock.c4.get(uuid).get(xx);
                                    int x = l.getBlockX();
                                    int y = l.getBlockY();
                                    int z = l.getBlockZ();
                                    try {
                                        boolean breakBlocks = LuckyBlock.instance.config.getBoolean("Allow.ExplosionGrief");
                                        boolean setFire = LuckyBlock.instance.config.getBoolean("Allow.ExplosionFire");
                                        boolean damage = LuckyBlock.instance.config.getBoolean("Allow.ExplosionDamage");
                                        String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("C4Block.Explode"));
                                        if (!g.equalsIgnoreCase("null")) {
                                            g = g.replace("%Player%", player.getName());
                                            player.sendMessage(g);
                                        }
                                        if (damage == true) {
                                            l.getWorld().createExplosion(x, y, z, 4.0F, setFire, breakBlocks);
                                        } else {
                                            l.getWorld().createExplosion(x, y, z, 0.0F, setFire, breakBlocks);
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                LuckyBlock.c4.get(uuid).clear();
                                if (LuckyBlock.c4.get(uuid).size() < 1) {
                                    LuckyBlock.c4.remove(uuid);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlaceC4Block(BlockPlaceEvent event) {
        final Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName() != null) {
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(gray + "C4 Block")) {
                    if (item.getAmount() < 2) {
                        if (player.getGameMode() != GameMode.CREATIVE) {
                            event.setCancelled(true);
                            player.updateInventory();
                        }
                    }
                    if (item.getItemMeta().hasLore() && item.getItemMeta().getLore().size() > 0) {
                        String l = "Stone";
                        for (int x = 0; x < item.getItemMeta().getLore().size(); x++) {
                            l = item.getItemMeta().getLore().get(x);
                            if (l.startsWith("id:")) {
                                String[] d = l.split("id:");
                                try {
                                    event.getBlock().setType(Material.getMaterial(d[1].toUpperCase()));
                                } catch (Exception ex) {
                                    event.getBlock().setType(Material.STONE);
                                }
                            } else if (l.startsWith("data:")) {
                                String[] d = l.split("data:");
                                try {
                                    int data = Integer.parseInt(d[1]);
                                    event.getBlock().setData((byte) data);
                                } catch (Exception ex) {
                                    //
                                }
                            }
                        }
                    } else {
                        event.getBlock().setType(Material.STONE);
                    }
                    if (LuckyBlock.c4.containsKey(player.getUniqueId())) {
                        List<Location> list = LuckyBlock.c4.get(player.getUniqueId());
                        list.add(block.getLocation());
                        LuckyBlock.c4.put(player.getUniqueId(), list);
                    } else {
                        List<Location> list = new ArrayList<Location>();
                        list.add(block.getLocation());
                        LuckyBlock.c4.put(player.getUniqueId(), list);
                    }
                    String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("C4Block.Place"));
                    if (!g.equalsIgnoreCase("null")) {
                        g = g.replace("%Player%", player.getName());
                        player.sendMessage(g);
                    }
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                }
            }
        }
    }

    @EventHandler
    public void onBreakC4Block(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            if (player.getItemInHand() != null) {
                Material type = player.getItemInHand().getType();
                if (type == Material.STONE_SWORD || type == Material.WOOD_SWORD || type == Material.GOLD_SWORD || type == Material.IRON_SWORD
                        || type == Material.DIAMOND_SWORD) {
                    return;
                }
            }
        }
        UUID uuid = player.getUniqueId();
        Location loc = block.getLocation();
        if (LuckyBlock.c4.containsKey(uuid)) {
            if (LuckyBlock.c4.get(uuid).contains(loc)) {
                LuckyBlock.c4.get(uuid).remove(loc);
                if (LuckyBlock.c4.get(uuid).size() < 1) {
                    LuckyBlock.c4.remove(uuid);
                }
                String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("C4Block.Break"));
                if (!g.equalsIgnoreCase("null")) {
                    g = g.replace("%Player%", player.getName());
                    player.sendMessage(g);
                }
            }
        }
    }

    @EventHandler
    public void onShootSheep(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()) {
                    if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(red + "Sheep Shooter")) {
                        Sheep sheep = (Sheep) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.SHEEP);
                        sheep.setVelocity(event.getPlayer().getEyeLocation().getDirection().multiply(3));
                    }
                }
            }
        }
    }

    @EventHandler
    public void HopperTakeItem(InventoryPickupItemEvent event) {
        if (event.getItem().hasMetadata("controller")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void activatePortal(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getClickedBlock().getType() != Material.AIR) {
                    String dim = null;
                    Block block = null;
                    if (event.getBlockFace() == BlockFace.UP) {
                        dim = LuckyBlockAPI.createDim(event.getClickedBlock());
                        block = event.getClickedBlock();
                    } else if (event.getBlockFace() == BlockFace.EAST) {
                        dim = LuckyBlockAPI.createDim(event.getClickedBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN));
                        block = event.getClickedBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN);
                    } else if (event.getBlockFace() == BlockFace.WEST) {
                        dim = LuckyBlockAPI.createDim(event.getClickedBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN));
                        block = event.getClickedBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN);
                    }
                    if (LuckyBlockAPI.chances.containsKey(dim) && LuckyBlockAPI.luck.containsKey(dim)) {
                        if (event.getItem().getType() == Material.FLINT_AND_STEEL) {
                            Player player = event.getPlayer();
                            if (!player.hasPermission("lb.createportal")) {
                                return;
                            }
                            if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP)))) {
                                if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST)))) {
                                    if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getRelative(BlockFace.UP)))) {
                                        if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP)
                                                .getRelative(BlockFace.UP).getRelative(BlockFace.UP)))) {
                                            if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                    .getRelative(BlockFace.UP)))) {
                                                if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                        .getRelative(BlockFace.UP).getRelative(BlockFace.WEST)))) {
                                                    if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                            .getRelative(BlockFace.WEST).getRelative(BlockFace.WEST)))) {
                                                        if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                                .getRelative(BlockFace.WEST).getRelative(BlockFace.WEST)))) {
                                                            if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP)
                                                                    .getRelative(BlockFace.WEST).getRelative(BlockFace.WEST)))) {
                                                                block.getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                                        .getRelative(BlockFace.UP)), true);
                                                                player.sendMessage(green + "Activated!");
                                                                LuckyBlockAPI.savePortals();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP)))) {
                                if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST)))) {
                                    if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getRelative(BlockFace.UP)))) {
                                        if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP)
                                                .getRelative(BlockFace.UP).getRelative(BlockFace.UP)))) {
                                            if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                    .getRelative(BlockFace.UP)))) {
                                                if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                        .getRelative(BlockFace.UP).getRelative(BlockFace.EAST)))) {
                                                    if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                            .getRelative(BlockFace.EAST).getRelative(BlockFace.EAST)))) {
                                                        if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                                .getRelative(BlockFace.EAST).getRelative(BlockFace.EAST)))) {
                                                            if (LuckyBlockAPI.luck.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP)
                                                                    .getRelative(BlockFace.EAST).getRelative(BlockFace.EAST)))) {
                                                                block.getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getRelative(BlockFace.UP)), true);
                                                                block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.STAINED_GLASS);
                                                                block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getRelative(BlockFace.UP).getRelative(BlockFace.UP).setData((byte) 10);
                                                                LuckyBlockAPI.lbwblocks.put(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getRelative(BlockFace.UP)
                                                                        .getRelative(BlockFace.UP)), true);
                                                                player.sendMessage(green + "Activated!");
                                                                LuckyBlockAPI.savePortals();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onRightClickPortalToTeleportToLuckyBlockWorld(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() != Material.AIR) {
                if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(event.getClickedBlock()))) {
                    Player player = event.getPlayer();
                    if (player.hasPermission("lb.useportal")) {
                        player.playSound(player.getLocation(), Sound.CLICK, 100, 1);
                        if (player.getWorld().getGenerator() == null || !player.getWorld().getGenerator().getClass().getName().equalsIgnoreCase(LuckyBlockWorld.class.getName())) {
                            World w = null;
                            int x = 0;
                            for (World world : Bukkit.getWorlds()) {
                                if (x == 0) {
                                    if (world.getGenerator() != null && world.getGenerator().getClass().getName().equalsIgnoreCase(LuckyBlockWorld.class.getName())) {
                                        w = world;
                                        x = 1;
                                    }
                                }
                            }
                            if (w != null) {
                                player.teleport(new Location(w, LuckyBlock.randoms.nextInt(1000) - 500, w.getSpawnLocation().getY(), LuckyBlock.randoms.nextInt(1000) - 500));
                                player.sendMessage(green + "Teleporting!");
                            } else {
                                String g = "";
                                int t = -1;
                                for (World ww : Bukkit.getWorlds()) {
                                    if (ww.getGenerator() != null && ww.getGenerator().getClass().getName().equalsIgnoreCase(LuckyBlockWorld.class.getName())) {
                                        if (ww.getName().startsWith("luckyblockworld")) {
                                            String[] d = ww.getName().split("luckyblockworld");
                                            if (d.length == 2) {
                                                try {
                                                    int h = Integer.parseInt(d[1]);
                                                    if (h > t) {
                                                        h = t;
                                                    }
                                                } catch (NumberFormatException ex) {
//
                                                }
                                            }
                                        }
                                    }
                                }
                                if (t > -1) {
                                    g = "" + t;
                                }
                                World world = Bukkit.createWorld(WorldCreator.name("luckyblockworld" + g));
                                world.setMonsterSpawnLimit(25);
                                world.setAnimalSpawnLimit(30);
                                world.save();
                                player.teleport(new Location(world, LuckyBlock.randoms.nextInt(1000) - 500, world.getSpawnLocation().getY(), LuckyBlock.randoms.nextInt(1000) - 500));
                                LuckyBlock.instance.getLogger().info(green + "Created world!");
                            }
                        } else {
                            if (LuckyBlockAPI.mainlobby == null) {
                                player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                            } else {
                                player.teleport(LuckyBlockAPI.mainlobby);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBreakBlockWhichIsPortalBlockWhichTeleportsYouToLuckyBlockWorld(BlockBreakEvent event) {
        if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(event.getBlock()))) {
            LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(event.getBlock()));
            if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.UP)))) {
                LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.UP)));
                event.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
                removePortal(event.getBlock().getRelative(BlockFace.UP));
            }
            if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.EAST)))) {
                LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.EAST)));
                event.getBlock().getRelative(BlockFace.EAST).setType(Material.AIR);
                removePortal(event.getBlock().getRelative(BlockFace.EAST));
            }
            if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.WEST)))) {
                LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.WEST)));
                event.getBlock().getRelative(BlockFace.WEST).setType(Material.AIR);
                removePortal(event.getBlock().getRelative(BlockFace.WEST));
            }
            if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.DOWN)))) {
                LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(event.getBlock().getRelative(BlockFace.DOWN)));
                event.getBlock().getRelative(BlockFace.DOWN).setType(Material.AIR);
                removePortal(event.getBlock().getRelative(BlockFace.DOWN));
            }
        }
    }

    public void removePortal(Block block) {
        if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP)))) {
            LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(block.getRelative(BlockFace.UP)));
            block.getRelative(BlockFace.UP).setType(Material.AIR);
            removePortal(block.getRelative(BlockFace.UP));
        }
        if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST)))) {
            LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(block.getRelative(BlockFace.EAST)));
            block.getRelative(BlockFace.EAST).setType(Material.AIR);
            removePortal(block.getRelative(BlockFace.EAST));
        }
        if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST)))) {
            LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(block.getRelative(BlockFace.WEST)));
            block.getRelative(BlockFace.WEST).setType(Material.AIR);
            removePortal(block.getRelative(BlockFace.WEST));
        }
        if (LuckyBlockAPI.lbwblocks.containsKey(LuckyBlockAPI.createDim(block.getRelative(BlockFace.DOWN)))) {
            LuckyBlockAPI.lbwblocks.remove(LuckyBlockAPI.createDim(block.getRelative(BlockFace.DOWN)));
            block.getRelative(BlockFace.DOWN).setType(Material.AIR);
            removePortal(block.getRelative(BlockFace.DOWN));
        }
    }

    @EventHandler
    public void onExplodeSuperLuckyBlock(EntityExplodeEvent event) {
        for (int x = 0; x < event.blockList().size(); x++) {
            String dim = LuckyBlockAPI.createDim(event.blockList().get(x));
            if (Types.fromBlock(dim) != null && Types.fromBlock(dim).getAbilities().contains(BlockAbilities.RESISTANCE_EXPLOSIONS)) {
                ParticleEffect.CRIT_MAGIC.display(0.5f, 0.5f, 0.5f, 0, 150, event.blockList().get(x).getLocation(), 86);
                event.blockList().remove(x);
            }
        }
    }

    @EventHandler
    public void onLuckySquidDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Squid) {
            if (event.getEntity().hasMetadata("luckysquid")) {
                event.setDroppedExp(50);
                event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.DIAMOND, LuckyBlock.randoms.nextInt(2) + 1));
            }
        }
    }

    @EventHandler
    public void onClickItemInInventory(InventoryClickEvent event) {
        if (event.getInventory().getTitle() != null) {
            if (event.getInventory().getTitle().startsWith(darkred + "Player's Gui")) {
                if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                    ItemStack item = event.getCurrentItem();
                    if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(red + "" + bold + "Close")) {
                            event.getWhoClicked().closeInventory();
                        }
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null && event.getItem().getType() != Material.AIR) {
                ItemStack item = event.getItem();
                if (item.hasItemMeta()) {
                    if (item.getItemMeta().hasEnchant(LuckyBlock.lightning)) {
                        Player player = event.getPlayer();
                        if (player.getTargetBlock(null, 200) != null && player.getTargetBlock(null, 200).getType() != Material.AIR) {
                            int level = item.getEnchantmentLevel(LuckyBlock.lightning);
                            if (player.getGameMode() != GameMode.CREATIVE) {
                                if (level < 2) {
                                    player.getItemInHand().removeEnchantment(LuckyBlock.lightning);
                                } else {
                                    player.getItemInHand().removeEnchantment(LuckyBlock.lightning);
                                    player.getItemInHand().addEnchantment(LuckyBlock.lightning, level - 1);
                                    player.updateInventory();
                                }
                                player.sendMessage(red + "Lights Left: " + gold + (level - 1));
                            }
                            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
                        } else {
                            player.sendMessage(red + "No Block in your sight!");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSpongeChange(BlockPhysicsEvent event) {
        if (event.getBlock().getType() == Material.SPONGE) {
            String dim = LuckyBlockAPI.createDim(event.getBlock());
            if (LuckyBlockAPI.IsLuckyBlock(dim)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlaceTnt(BlockPlaceEvent event) {
        if (event.getItemInHand() != null) {
            if (event.getItemInHand().getType() == Material.TNT) {
                if (War.containPlayer(event.getPlayer().getUniqueId())) {
                    if (LuckyBlock.instance.config.getBoolean("AutoIgniteTnt")) {
                        event.getBlock().setType(Material.AIR);
                        ParticleEffect.CLOUD.display(0.8f, 0.7f, 0.8f, 0, 80, event.getBlock().getLocation(), 128);
                        TNTPrimed tnt = (TNTPrimed) event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
                        tnt.setFuseTicks(36);
                        tnt.setCustomName(darkred + "" + bold + "TNT");
                        tnt.setCustomNameVisible(true);
                        LuckyBlock.instance.LoopTnt(tnt);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onJoinServer(PlayerJoinEvent event) {
        String name = event.getPlayer().getName();
        for (int x = 0; x < LuckyBlockAPI.teams.size(); x++) {
            Team team = LuckyBlockAPI.teams.get(x);
            int i = 0;
            if (team.getAccepted().contains(name)) {
                team.searchPlayer(name);
                i = 1;
            }
            if (team.getKicked().contains(name)) {
                team.searchKickedPlayer(name);
                i = 1;
            }
            if (i == 1) {
                team.save();
            }
        }
    }

    @EventHandler
    public void onUseItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!LuckyBlock.instance.config.getBoolean("CustomItems")) {
            return;
        }
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) {
            return;
        }
        ItemStack item = event.getItem();
        for (int i = 0; i < LuckyBlock.Items.length; i++) {
            boolean b = false;
            if (LuckyBlock.Items[i] != null) {
                File fileF = LuckyBlock.Items[i];
                FileConfiguration file = YamlConfiguration.loadConfiguration(fileF);
                if (file.getString("Type") != null) {
                    if (!item.getType().toString().equalsIgnoreCase(file.getString("Type"))) {
                        return;
                    }
                }
                if (file.getString("Data") != null && !file.getString("Data").equalsIgnoreCase("none")) {
                    try {
                        short s = (short) file.getInt("Data");
                        if (item.getDurability() != s) {
                            return;
                        }
                    } catch (NumberFormatException ex) {
//
                    }
                }
                if (file.getString("DisplayName") != null) {
                    if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                        String st = ChatColor.translateAlternateColorCodes('&', file.getString("DisplayName"));
                        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(st)) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (file.getConfigurationSection("Actions") != null) {
                    ConfigurationSection cs = file.getConfigurationSection("Actions");
                    for (int x = 0; x < cs.getKeys(false).size(); x++) {
                        String s = cs.getKeys(false).toArray()[x].toString();
                        for (int y = 0; y < cs.getConfigurationSection(s).getKeys(false).size(); y++) {
                            String d = cs.getConfigurationSection(s).getKeys(false).toArray()[y].toString();
                            boolean is = false;
//player.sendMessage(d);
                            if (d.equalsIgnoreCase("RightClick")) {
                                if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                                    is = true;
                                }
//player.sendMessage(d);
                                if (d.equalsIgnoreCase("LeftClick")) {
                                    if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                                        is = true;
                                    }
                                }
//player.sendMessage("" + is);
                                if (is == true) {
                                    for (int z = 0; z < cs.getConfigurationSection(s).getConfigurationSection(d).getKeys(false).size(); z++) {
                                        String h = cs.getConfigurationSection(s).getConfigurationSection(d).getKeys(false).toArray()[z].toString();
                                        if (h.equalsIgnoreCase("RunCommands")) {
                                            List<String> list = cs.getConfigurationSection(s).getConfigurationSection(d).getStringList(h);
                                            if (list.size() > 0) {
                                                for (String f : list) {
                                                    f = f.replace("%Player%", player.getName());
                                                    f = ChatColor.translateAlternateColorCodes('&', f);
                                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), f);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (b == true) {
                    i = 256;
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof IronGolem) {
            IronGolem golem = (IronGolem) event.getEntity();
            Damageable d = (Damageable) golem;
            if ((d.getHealth() - event.getFinalDamage()) <= 0) {
                if (golem.getCustomName() != null && golem.getCustomName().startsWith(yellow + "Lucky Iron Golem")) {
                    ParticleEffect.FLAME.display(0.3f, 0.3f, 0.3f, 1, 100, d.getLocation(), 120);
                    golem.getWorld().dropItem(golem.getLocation(), LuckyBlockAPI.createItemStack(Material.DIAMOND, LuckyBlock.randoms.nextInt(3) + 1, (short) 0, aqua + "" + bold + "Diamond"));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getWorld().getName().equalsIgnoreCase(golem.getWorld().getName())) {
                            p.sendMessage(red + "A boss has been killed!");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            Player player = event.getPlayer();
            if (block.getType() == Material.GOLD_BLOCK) {
                Detector detector = null;
                for (Detector det : LuckyBlockAPI.detectors) {
                    for (String s : det.getBlocks()) {
                        if (s != null) {
                            if (LuckyBlockAPI.createDim(LuckyBlockAPI.getBlock(s)).equalsIgnoreCase(LuckyBlockAPI.createDim(block))) {
                                detector = det;
                            }
                        }
                    }
                }
                if (detector != null) {
                    if (!player.hasPermission("lb.usedetector")) {
                        return;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Detector.Search")));
                    int i = 0;
                    if (detector.getRange().getTotal() > 0) {
                        for (String s : detector.getRblocks()) {
                            String[] d = s.split(",");
                            String world = d[0];
                            int x = Integer.parseInt(d[1]);
                            int y = Integer.parseInt(d[2]);
                            int z = Integer.parseInt(d[3]);
                            Block b = Bukkit.getWorld(world).getBlockAt(x, y, z);
                            if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(b))) {
                                player.sendMessage(green + "Found Lucky Block : " + gold + x + "," + y + "," + z);
                                i++;
                            }
                        }
                    }
                    if (i == 0) {
                        String g = ChatColor.translateAlternateColorCodes('&', LuckyBlock.instance.Messages.getString("Detector.Fail"));
                        g = g.replace("%lb%", red + "Lucky Block");
                        player.sendMessage(g);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onEntityDamage1(EntityDamageEvent event) {
        if (LuckyBlock.instance.config.getBoolean("InvincibleItems")) {
            if (event.getEntity() instanceof Item) {
                ItemStack item = ((Item) event.getEntity()).getItemStack();
                Types type = null;
                for (Types t : Types.getTypes()) {
                    if (item.getType() == t.getType()) {
                        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(t.getName())) {
                                type = t;
                            }
                        }
                    }
                }
                if (type != null) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    private void onEntityDeath(EntityDeathEvent event) {
        if (LuckyBlock.bukkitVersion[1] > 7) {
            if (event.getEntity() instanceof ArmorStand) {
                if (LuckyTree.armorstands.contains(event.getEntity().getUniqueId())) {
                    LuckyTree.armorstands.remove(event.getEntity().getUniqueId());
                }
            }
        }
    }

    @EventHandler
    private void onRightClickArmorStand(PlayerInteractAtEntityEvent event) {
        if (LuckyBlock.bukkitVersion[1] > 7) {
            if (event.getRightClicked() instanceof ArmorStand) {
                if (LuckyTree.armorstands.contains(event.getRightClicked().getUniqueId())) {
                    event.setCancelled(true);
                    ArmorStand a = (ArmorStand) event.getRightClicked();
                    if (a.getHelmet() != null) {
                        a.getWorld().dropItem(a.getLocation(), a.getHelmet());
                    }
                    a.remove();
                    LuckyTree.armorstands.remove(a.getUniqueId());
                }
            }
        }
    }

    @EventHandler
    private void onBreakBlock(BlockBreakEvent event) {
        if (LuckyBlock.instance.config.getBoolean("GravityForFruits")) {
            if (LuckyBlock.bukkitVersion[1] > 7) {
                if (LuckyTree.armorstands.size() > 0) {
                    Block block = event.getBlock();
                    for (Entity e : block.getWorld().getEntities()) {
                        if (LuckyTree.armorstands.contains(e.getUniqueId())) {
                            if (e.getLocation().distance(block.getLocation()) < 1) {
                                if (e instanceof ArmorStand) {
                                    ArmorStand a = (ArmorStand) e;
                                    a.setGravity(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnRedstone(BlockRedstoneEvent event) {
        if (event.getNewCurrent() > 0) {
            testForBlock(event.getBlock(), event.getNewCurrent());
        }
    }

    @EventHandler
    private void onUseLuckyBlockItem(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().getType() == Material.STICK) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    ItemStack item = event.getItem();
                    if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(blue + "Lucky Block Item")) {
                            if (event.getClickedBlock().getType() != Material.AIR) {
                                if (LuckyBlockAPI.IsLuckyBlock(LuckyBlockAPI.createDim(event.getClickedBlock()))) {
                                    BreakLuckyBlock.openLB(event.getClickedBlock(), Types.fromBlock(LuckyBlockAPI.createDim(event.getClickedBlock())), event.getPlayer());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onHurtZombie(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Zombie) {
            if (event.getDamager() instanceof Player) {
                Zombie zombie = (Zombie) event.getEntity();
                if (zombie.getCustomName() != null && zombie.getCustomName().equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.BOLD + "[Illuminati]")) {
                    event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 20, true);
                }
            }
        }
    }

    @EventHandler
    public void onHurtEntity(EntityDamageEvent event) {
        if (event.getEntity() instanceof Squid) {
            Squid squid = (Squid) event.getEntity();
            if (squid.getCustomName() != null && squid.getCustomName().startsWith(yellow + "Lucky Squid")) {
                Damageable d = (Damageable) squid;
                double f = event.getFinalDamage();
                double x = d.getHealth() - f;
                if ((d.getHealth() - f) < 0) {
                    x = 0;
                }
                squid.setCustomName(yellow + "Lucky Squid " + red + x + white + "/" + red + d.getMaxHealth());
            }
        } else if (event.getEntity() instanceof Bat) {
            Bat bat = (Bat) event.getEntity();
            if (bat.hasMetadata("luckybat")) {
                if (event.getCause() == DamageCause.ENTITY_ATTACK) {
                    event.setCancelled(true);
                }
            }
            if (bat.hasMetadata("flyinglb")) {
                LuckyBlock.instance.FlyingBat(bat);
            }
        } else if (event.getEntity() instanceof Wolf) {
            Wolf wolf = (Wolf) event.getEntity();
            if (wolf.getCustomName() != null && wolf.getCustomName().startsWith(yellow + "" + bold + "Wolf")) {
                int h = (int) event.getFinalDamage();
                Damageable d = (Damageable) wolf;
                wolf.setCustomName(yellow + "" + bold + "Wolf " + green + (d.getHealth() - h) + white + "/" + green + d.getMaxHealth());
            }
        }
    }

    @EventHandler
    public void mobsRegenHealth(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Wolf) {
            Wolf wolf = (Wolf) event.getEntity();
            if (wolf.getCustomName() != null && wolf.getCustomName().startsWith(yellow + "" + bold + "Wolf")) {
                int h = (int) event.getAmount();
                Damageable d = (Damageable) wolf;
                wolf.setCustomName(yellow + "" + bold + "Wolf " + green + (d.getHealth() + h) + white + "/" + green + d.getMaxHealth());
            }
        }
    }

    @EventHandler
    public void onHurtEntitiesByPlayer(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Pig) {
            Player player = null;
            Pig pig = (Pig) event.getEntity();
            if (pig.getCustomName() != null && pig.getCustomName().startsWith(yellow + "Lucky Pig")) {
                Damageable pp = (Damageable) pig;
                if ((pp.getHealth() - event.getFinalDamage()) <= 0) {
                    String[] s = pig.getCustomName().split(yellow + "Lucky Pig ");
                    if (event.getDamager() instanceof Player) {
                        player = (Player) event.getDamager();
                    } else if (event.getDamager() instanceof Projectile) {
                        Projectile p = (Projectile) event.getDamager();
                        if (p.getShooter() instanceof Player) {
                            player = (Player) p.getShooter();
                        }
                    }
                    if (player != null) {
                        if (s.length == 2) {
                            String[] g = s[1].split(green + "+");
                            String[] h = g[1].split(" Health");
                            Damageable d = (Damageable) player;
                            int plus = Integer.parseInt(h[0]);
                            try {
                                player.setHealth(d.getHealth() + plus);
                            } catch (Exception ex) {
                                player.setHealth(d.getMaxHealth());
                            }
                            String a = LuckyBlockCommand.getMessage("GetMoreHealth");
                            a = a.replace("%health%", plus + "");
                            player.sendMessage(a);
                        }
                        if (pig.getVehicle() != null) {
                            pig.getVehicle().remove();
                        }
                    }
                }
            }
        }
    }


}
