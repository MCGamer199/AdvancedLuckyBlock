package com.LuckyBlock.Engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.LuckyBlock.Resources.SchedulerTask;
//import com.LuckyBlock.Structures.IlluminatiStructure;
import com.LuckyBlock.Structures.LuckyDungeon;
import com.LuckyBlock.Structures.LuckyTrap;
import com.LuckyBlock.Structures.LuckyTree;
import com.LuckyBlock.Structures.PumpkinTower;
import com.LuckyBlock.Structures.StructureFiles;
import com.LuckyBlock.Structures.WoolHouse;

public class LuckyBlockWorld extends ChunkGenerator implements Listener {


	public static String generatorName = LuckyBlockWorld.class.getName();
	//public static HashMap<String, Integer> worlds = new HashMap<String, Integer>();
	private boolean inited = false;


	public static boolean isEquals(ChunkGenerator generator){
		boolean is = false;
		if(generator != null){
			if(generator.getClass().getName().equalsIgnoreCase(generatorName)){
				is = true;
			}
		}
		return is;
	}

	/*
	@SuppressWarnings("deprecation")
	public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid){
	byte[][] result = new byte[world.getMaxHeight() / 16][];
	SimplexOctaveGenerator gen = new SimplexOctaveGenerator(world, 8);
	gen.setScale(1/12);
	for(int x = 0; x < 16; x++){
	for(int y = 1; y < 7; y++){
	for(int z = 0; z < 16; z++){
	setBlock(result, x, y, z, (byte)Material.BEDROCK.getId());
	}
	}
	}

	for(int x = 0; x < 16; x++){
	for(int z = 0; z < 16; z++){
	double noise = gen.noise(x+chunkX*16, z+chunkZ*16, 0.5, 0.5);
	for(int y = 1; y < 50+noise; y++){
	setBlock(result, x, y, z, (byte)Material.SPONGE.getId());
	}
	}
	}

	for(int x = 0; x < 16; x++){
	for(int y = 7; y < 13; y++){
	for(int z = 0; z < 16; z++){
	setBlock(result, x, y, z, (byte)Material.SPONGE.getId());
	}
	}
	}
	return result;
	}
*/



	@SuppressWarnings("deprecation")
	public byte[] generate(World world, Random random, int chunkX, int chunkZ){
		if(!inited){
			//init(world);
			inited = true;
		}
		byte[] blocks = new byte[32768];
		int x, y, z;
		Random rand = new Random(world.getSeed());
		SimplexOctaveGenerator octave = new SimplexOctaveGenerator(rand, 8);
		octave.setScale(1/64.0);
		for(x = 0; x < 16; x++){
			for(z = 0; z < 16; z++){
				blocks[this.coordsToByte(x, 0, z)] = (byte) Material.BEDROCK.getId();
				double noise = octave.noise(x + chunkX * 16, z + chunkZ * 16, 0.5, 0.5) * 12;
				for(y = 1; y < 32 + noise; y++){
					blocks[this.coordsToByte(x, y, z)] = (byte) Types.getTypes().get(0).getType().getId();
				}
			}
		}
		return blocks;
	}
	/*
	private void init(World world){
	boolean found = false;
	for(String s : worlds.keySet()){
	LuckyBlock.instance.getLogger().info(s + " " + world.getName());
	if(s.equalsIgnoreCase(world.getName())){
	LuckyBlock.instance.getLogger().info("t");
	found = true;
	}
	}
	if(!found){
	worlds.put(world.getName(), Types.getTypes().get(0).getId());
	LuckyBlock.instance.getLogger().info("test");
	} else {
	LuckyBlock.instance.getLogger().info("h");
	}
	}
	*/


	byte getBlock(int x, int y, int z, byte[][] chunk){
		if(chunk[y >> 4] == null){
			return 0;
		}
		if(!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0)){
			return 0;
		}
		try {
			return chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x];
		} catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}



	private int coordsToByte(int x, int y, int z){
		return (x * 16 + z) * 128 + y;
	}



	public List<BlockPopulator> getDefaultPopulators(World world){
		List<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		return populators;
	}



	void setBlock(byte[][] result, int x, int y, int z, byte blkid){
		if(result[y >> 4] == null){
			result[y >> 4] = new byte[4096];
		}
		result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
	}



	@EventHandler
	private void onGenerate(ChunkLoadEvent event){
		World world = event.getWorld();
		if(world.getGenerator() != null && world.getGenerator().getClass().getName().equalsIgnoreCase(getClass().getName())){
			//if(!world.canGenerateStructures()){
			//return;
			//}
			Random random = new Random();
			int x = random.nextInt(10) - 5;
			int z = random.nextInt(10) - 5;
			int x1 = event.getChunk().getX() + x;
			int z1 = event.getChunk().getZ() + z;
			int y = event.getWorld().getHighestBlockAt(x1, z1).getY();
			if(event.getWorld().getBlockAt(x1, y, z1).getType() != Material.AIR){
				y++;
			}
			Block block = event.getChunk().getBlock(x1, y, z1);
			Types type = LuckyBlock.lbs.get(0);
			for(int h = 0; h < 100; h++){
				if(block.getRelative(BlockFace.DOWN).getType() != type.getType()){
					block = block.getRelative(BlockFace.DOWN);
				}
			}
			for(int h = 0; h < 100; h++){
				if(block.getType() == type.getType()){
					block = block.getRelative(BlockFace.UP);
				}
			}
			if(random.nextInt(100) > (100 - StructureFiles.luckytree.getInt("SpawnRate"))){
				Location loc = new Location(world, block.getX(), block.getY(), block.getZ());
				LuckyTree tree = new LuckyTree();
				tree.build(loc);
			}
			if(random.nextInt(100) > (100 - StructureFiles.luckydungeon.getInt("SpawnRate"))){
				if(event.getWorld().getBlockAt(x1, y, z1).getType() != Material.AIR){
					y++;
				}
				Location loc = new Location(world, block.getX(), random.nextInt(4) + 6, block.getZ());
				LuckyDungeon dungeon = new LuckyDungeon();
				dungeon.build(loc);
			}
			if(random.nextInt(100) > (100 - StructureFiles.pumpkintower.getInt("SpawnRate"))){
				if(event.getWorld().getBlockAt(x1, y, z1).getType() != Material.AIR){
					y++;
				}
				Location loc = new Location(world, block.getX(), block.getY(), block.getZ());
				PumpkinTower tower = new PumpkinTower();
				tower.build(loc);
			}
			if(random.nextInt(100) > (100 - StructureFiles.luckytrap.getInt("SpawnRate"))){
				if(event.getWorld().getBlockAt(x1, y, z1).getType() != Material.AIR){
					y++;
				}
				Location loc = new Location(world, block.getX(), block.getY(), block.getZ());
				LuckyTrap trap = new LuckyTrap();
				trap.build(loc);
			}
			if(random.nextInt(100) > 98){
				if(event.getWorld().getBlockAt(x1, y, z1).getType() != Material.AIR){
					y++;
				}
				Location loc = new Location(world, block.getX(), block.getY(), block.getZ());
				WoolHouse whouse = new WoolHouse();
				whouse.build(loc);
			}
			/*if(random.nextInt(100) > 99){
				int g = random.nextInt(3) + 1;
				IlluminatiStructure i = new IlluminatiStructure();
				if(g > 1){
					Location loc = new Location(world, block.getX(), 200, block.getZ());
					i.build(loc);
				} else {
					Location loc = new Location(world, block.getX(), 2, block.getZ());
					i.build(loc);
				}
			}*/
		}
	}




	@EventHandler
	private void onMobSpawn(CreatureSpawnEvent event){
		World world = event.getLocation().getWorld();
		if(world.getGenerator() != null && world.getGenerator().getClass().getName().equalsIgnoreCase(getClass().getName())){
			if(event.getEntity() instanceof Zombie){
				if(event.getSpawnReason() == SpawnReason.NATURAL){
					if(LuckyBlock.randoms.nextInt(100) + 1 > 80){
						Zombie zombie = (Zombie)event.getEntity();
						zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
						zombie.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lucky Zombie");
						zombie.setCustomNameVisible(true);
						MobsEvent(zombie);
					} else {
						event.setCancelled(true);
					}
				} else {
					Zombie zombie = (Zombie)event.getEntity();
					zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
					zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
					zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
					zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
					zombie.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
					zombie.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lucky Zombie");
					zombie.setCustomNameVisible(true);
					MobsEvent(zombie);
				}
			} else if(event.getEntity() instanceof Skeleton){
				if(event.getSpawnReason() == SpawnReason.NATURAL){
					event.setCancelled(true);
					if(LuckyBlock.randoms.nextInt(100) + 1 > 90){
						IronGolem golem = (IronGolem)world.spawnEntity(event.getLocation(), EntityType.IRON_GOLEM);
						golem.setPlayerCreated(false);
						MobsEvent(golem);
					}
				}
			} else if(event.getEntity() instanceof Pig){
				event.getEntity().setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lucky Pig");
				event.getEntity().setMaxHealth(15);
				event.getEntity().setHealth(15);
				event.getEntity().setCustomNameVisible(true);
			} else {
				if(event.getSpawnReason() == SpawnReason.NATURAL){
					event.setCancelled(true);
				} else {
					if(event.getEntity() instanceof Creature){
						Creature creature = (Creature)event.getEntity();
						MobsEvent(creature);
					}
				}
			}
		}
	}



	@EventHandler
	private void onDamage(EntityDamageEvent event){
		World world = event.getEntity().getWorld();
		if(world.getGenerator() != null && world.getGenerator().getClass().getName().equalsIgnoreCase(getClass().getName())){
			if(event.getCause() == DamageCause.FALL){
				event.setDamage(event.getFinalDamage() / 10);
			}
		}
	}



	private void MobsEvent(final Creature creature){
		final SchedulerTask task = new SchedulerTask();
		task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable(){
			@Override
			public void run(){
				if(creature.isValid()){
					List<UUID> uuids = new ArrayList<UUID>();
					if(creature.getTarget() == null){
						for(Entity e : creature.getNearbyEntities(30, 30, 30)){
							if(e instanceof LivingEntity){
								LivingEntity l = (LivingEntity)e;
								if(!(l instanceof Player)){
									if(l.getUniqueId() != creature.getUniqueId()){
										uuids.add(l.getUniqueId());
									}
								} else {
									Player p = (Player)l;
									if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR){
										uuids.add(p.getUniqueId());
									}
								}
							}
						}
					}
					if(uuids.size() > 0){
						UUID u = uuids.get(new Random().nextInt(uuids.size()));
						for(Entity e : creature.getNearbyEntities(50, 50, 50)){
							if(e instanceof LivingEntity){
								LivingEntity l = (LivingEntity)e;
								if(l.getUniqueId() == u){
									creature.setTarget(l);
								}
							}
						}
					}
					if(creature.getTarget() != null){
						if(creature.getWorld().getName().equalsIgnoreCase(creature.getTarget().getWorld().getName())){
							if(creature.getLocation().distance(creature.getTarget().getLocation()) > 10 || creature.getTarget().isValid() == false){
								creature.setTarget(null);
							}
						}
					}
				} else {
					task.run();
				}
			}
		}, 20L, 40L));
	}
}