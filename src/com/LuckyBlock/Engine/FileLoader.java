package com.LuckyBlock.Engine;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    public static final String name = "FileLoader";
    public static final String version = "1.0.0";


    public static void load(FileConfiguration file, File fileF) {
        file.options().header("Lucky Block 0-19 Luck Options.");
        file.addDefault("SplitSymbol", "#");
        List<String> lucks = new ArrayList<String>();
        lucks.add("Chance:1-50#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:51-89#Type:Mob#Title:&2Mobs#Luck:Try your luck");
        lucks.add("Chance:90-92#Type:Potions#Title:&5Harmful Potions#Luck:Bad");
        lucks.add("Chance:93-99#Type:Falling Block#Title:&eFalling Block#Luck:Good");
        lucks.add("Chance:100-101#Type:Lava#Title:&6Lava#Luck:Bad");
        lucks.add("Chance:102-103#Type:Villager#Title:&9Villager#Luck:Bad");
        lucks.add("Chance:104-114#Type:Splash Potion#Title:&1Splash Potion#Luck:Try your luck");
        lucks.add("Chance:115-116#Type:Primed Tnt#Title:&4Primed Tnt#Luck:Bad");
        lucks.add("Chance:117-120#Type:Lightning#Title:&bLightning#Luck:Bad");
        lucks.add("Chance:121#Type:Fake Diamond#Title:&bFake Diamond#Luck:Bad");
        lucks.add("Chance:122#Type:Added Item#Title:&5Added Item#Luck:Good");
        lucks.add("Chance:123#Type:Firework#Title:&3Firework#Luck:Try your luck");
        lucks.add("Chance:124-125#Type:Changeable Block#Title:&1Changing Block#Luck:Good");
        lucks.add("Chance:126-189#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:190-191#Type:Stuck#Title:&7Stuck#Luck:Bad");
        lucks.add("Chance:192-193#Type:Damage#Title:&cDamage#Luck:Bad");
        lucks.add("Chance:194#Type:Tower#Title:&bDiamond Tower#Luck:Very Good");
        lucks.add("Chance:195#Type:FPigs#Title:&dFlying Pigs#Luck:Good");
        lucks.add("Chance:196#Type:Slimes#Title:&2Slimes#Luck:Bad");
        lucks.add("Chance:197#Type:Meteors#Title:&8Meteors#Luck:Very Bad");
        lucks.add("Chance:198-199#Type:FlyingLB#Title:&eFlying Lucky Block#Luck:Good");
        lucks.add("Chance:200-202#Type:Soldier#Title:&1&lSoldier#Luck:Bad");
        lucks.add("Chance:203-207#Type:LBItem#Title:&e&lLucky Block Item#Luck:Good");
        lucks.add("Chance:208-209#Type:SnowMovement#Title:&f&lSnow Moving#Luck:Good");
        lucks.add("Chance:210-212#Type:Bedrock#Title:&8&lBedrock#Luck:Bad");
        lucks.add("Chance:213-214#Type:Jail#Title:&2Trap#Luck:Bad");
        lucks.add("Chance:215-220#Type:Tree#TreeType:tree#Title:&aTree#Luck:Good");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 220);
        List<String> fblocks = new ArrayList<String>();
        fblocks.add("Chance:1-20#Type:sand");
        fblocks.add("Chance:21-30#Type:gold_block");
        fblocks.add("Chance:31-45#Type:iron_block");
        fblocks.add("Chance:46-60#Type:lapis_block");
        fblocks.add("Chance:61-80#Type:grass");
        fblocks.add("Chance:81-100#Type:wool#Data:0-15");
        file.addDefault("FallingBlocks.Chances", 100);
        file.addDefault("FallingBlocks.Blocks", fblocks);
        List<String> mobs = new ArrayList<String>();
        mobs.add("Chance:1-10#Type:zombie#CustomName:&4&lLucky Zombie#CustomNameVisible:true#maxHealth:200#Health:200"
                + "#Equipments:typeid:276,Enchants:16 5>>typeid:310,Enchants:0 4>>typeid:311,Enchants:0 4>>typeid:312,Enchants:0 4>>typeid:313,Enchants:0 4");
        mobs.add("Chance:11-20#Type:cavespider#CustomName:&d&lLucky Cave Spider#CustomNameVisible:true#maxHealth:200#Health:200");
        mobs.add("Chance:21-30#Type:creeper#CustomName:&a&lLucky Creeper#CustomNameVisible:true#maxHealth:150#Health:150#Powered:true");
        mobs.add("Chance:31-40#Type:sheep#CustomName:&e&lLucky Sheep &4&l&k@@@#CustomNameVisible:true#maxHealth:200#Health:200#Color:0-15");
        mobs.add("Chance:41-50#Type:giant#CustomName:&2&lLucky Giant#CustomNameVisible:true");
        mobs.add("Chance:51-60#Type:wolf#CustomName:&f&lAngry Wolf#CustomNameVisible:true#maxHealth:200#Health:200#Angry:true#Target:%player%");
        mobs.add("Chance:61-70#Type:skeleton#CustomName:&4&lKiller#CustomNameVisible:true#maxHealth:500#Health:500#SkeletonType:1#"
                + "Equipments:typeid:261>>typeid:20>>typeid:315>>typeid:316>>typeid:317#DropChances:0.4,0,0,0,0");
        mobs.add("Chance:71-80#Type:pigzombie#CustomName:&0&lHungry Zombie Pigman#CustomNameVisible:true#maxHealth:150#Health:150#"
                + "Equipments:typeid:276#Angry:true#Anger:5000#DropChances:0");
        mobs.add("Chance:81-90#Type:cow#CustomName:&6&lRideable Cow#CustomNameVisible:true#maxHealth:15#Health:15#Metadatas:rideable,true");
        mobs.add("Chance:91-100#Type:zombie#&cPowered Zombie#CustomNameVisible:true#maxHealth:120#Health:120#PotionEffects:14 999999 0"
                + "#CanPickupItems:true#Equipments:typeid:258>>typeid:397,data:1>>typeid:299#DropChances:1.0,1.0,1.0,1.0,1.0");
        mobs.add("Chance:101-110#Type:witch#maxHealth:40#Health:40//Tries:13-17#Type:bat#maxHealth:10#Health:10");
        mobs.add("Chance:111-120#Tries:8-16#Type:squid#maxHealth:30-60#Health:%maxHealth%#CustomName:&eLucky Squid &c%health%&f/&c%maxhealth%");
        mobs.add("Chance:121-130#Tries:3-5#Type:chicken#maxHealth:20#Health:20#CustomName:&e&lLucky Chicken#CustomNameVisible:true"
                + "#ParticleEffects:name:heart,rx:0.2,ry:0.2,rz:0.2,speed:0,amount:30,range:30//Type:droppeditem#ItemStack:"
                + "typeid:266,amount:%luck%");
        file.addDefault("Entities.Chances", 130);
        file.addDefault("Entities.Mobs", mobs);
        List<String> ai = new ArrayList<String>();
        ai.add("Chance:1-100#Title:&6Dirt#type:dirt#DisplayName:&6Dirt#Message:"
                + "&6You have got 1 Dirt!");
        file.addDefault("AddedItems.Chances", 100);
        file.addDefault("AddedItems.Items", ai);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:iron_sword#data:0");
        s.add("Chance:11-20#type:flint_and_steel");
        s.add("Chance:21-30#type:iron_pickaxe");
        s.add("Chance:31-40#type:iron_axe");
        s.add("Chance:41-50#type:gold_chestplate");
        s.add("Chance:51-60#type:arrow#amount:3-5");
        s.add("Chance:61-70#type:emerald#amount:1-2");
        s.add("Chance:71-80#type:cooked_chicken#amount:2-3");
        s.add("Chance:81-90#type:cooked_beef#amount:2-3");
        s.add("Chance:91-100#type:bone#amount:2-3");
        s.add("Chance:101-110#type:rotten_flesh");
        s.add("Chance:111-120#type:chest#data:0#hasItemMeta:true#DisplayName:&a&l&oRandom Chest#hasLore:true#Lore:&7Place It%%&7Random Chest");
        s.add("Chance:121-130#type:golden_apple#amount:1-3");
        s.add("Chance:131-140#type:gold_ingot#amount:2-6");
        s.add("Chance:141-150#type:eye_of_ender#amount:5-11");
        s.add("Chance:151-160#type:ender_pearl#amount:2-5");
        s.add("Chance:161-170#type:beacon");
        s.add("Chance:171-180#type:monster_egg#amount:2-5#data:90-93#tries:4-5");
        s.add("Chance:181-190#type:gold_sword#tries:1-3");
        s.add("Chance:191-200#typeid:298/299/300/301/302/303/304/305#tries:3-7");
        s.add("Chance:201-210#typeid:331/152/76#amount:7-20#tries:2-4");
        s.add("Chance:211-220#typeid:37#amount:3-9//typeid:38#amount:2-6//typeid:38#amount:2-6#data:1//typeid:38#amount:2-6#data:2//typeid:38#amount:2-6#data:3"
                + "//typeid:38#amount:2-6#data:4//typeid:38#amount:2-6#data:5//typeid:38#amount:2-6#data:4//typeid:38#amount:2-6#data:6//typeid:38#amount:2-6#data:7"
                + "//typeid:38#amount:2-6#data:8");
        s.add("Chance:221-230#type:raw_fish#data:0#amount:2-5//type:raw_fish#data:1#amount:2-5//type:raw_fish#data:2#amount:2-5"
                + "//type:raw_fish#data:3#amount:2-5//type:cooked_fish#data:0#amount:2-5//type:cooked_fish#data:1#amount:2-5");
        s.add("Chance:231-240#typeid:325/326/327#tries:1-3");
        s.add("Chance:241-250#type:iron_axe#DisplayName:&e&lLucky Axe#Enchants:16 1-2%%17 1-2");
        file.addDefault("DroppedItems.Chances", 250);
        file.addDefault("DroppedItems.Items", s);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:5-10");
        chest.add("Chances:290");
        chest.add("Chance:1-19#type:dirt#amount:8-14");
        chest.add("Chance:20-22#type:%lb%1#DisplayName:&eLucky Block");
        chest.add("Chance:23-28#type:torch#amount:4-9");
        chest.add("Chance:29-34#type:workbench");
        chest.add("Chance:35-38#type:ladder#amount:4-8");
        chest.add("Chance:39-47#type:tnt#amount:1-2");
        chest.add("Chance:48-54#type:ice#amount:6-10");
        chest.add("Chance:55-60#type:cactus#amount:6-10");
        chest.add("Chance:61-70#type:gold_ingot#amount:1-3");
        chest.add("Chance:71-74#type:coal_block");
        chest.add("Chance:75-78#type:bow");
        chest.add("Chance:79-89#type:arrow#amount:8-16");
        chest.add("Chance:90-91#type:diamond#amount:1-2");
        chest.add("Chance:92-94#type:iron_ingot#amount:3-5");
        chest.add("Chance:95-99#type:stone_sword");
        chest.add("Chance:100-102#type:golden_apple#amount:1-3");
        chest.add("Chance:103-113#type:water_bucket");
        chest.add("Chance:114-124#type:lava_bucket");
        chest.add("Chance:125-134#type:snow_ball#amount:8-16");
        chest.add("Chance:135-149#type:raw_fish#amount:3-5#data:0-2");
        chest.add("Chance:150-152#type:raw_fish#amount:3-5#data:0-2#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:153-164#type:ender_pearl#amount:1-2");
        chest.add("Chance:165-170#type:bread#amount:1-3");
        chest.add("Chance:171-173#type:bread#amount:1-3#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:174-180#type:carrot_item#amount:1-3");
        chest.add("Chance:181-183#type:carrot_item#amount:1-3#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:184-188#type:exp_bottle#amount:1-5");
        chest.add("Chance:189-195#type:mushroom_soup");
        chest.add("Chance:196-199#type:mushroom_soup#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:200-207#type:stick#amount:4-8");
        chest.add("Chance:208-212#type:glass_bottle#amount:1-3");
        chest.add("Chance:213-215#type:fishing_rod");
        chest.add("Chance:216-222#type:melon#amount:1-4");
        chest.add("Chance:223-225#type:melon#amount:1-4#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:226-230#type:iron_helmet");
        chest.add("Chance:231-235#type:iron_chestplate");
        chest.add("Chance:236-240#type:chainmail_leggings");
        chest.add("Chance:241-246#type:gold_boots");
        chest.add("Chance:247-250#type:diamond_boots");
        chest.add("Chance:251-256#type:leather_chestplate#LeatherArmor:0-200,0-200,0-200#Lore:"
                + "&c{RED}&6,&a{GREEN}&6,&9{BLUE}");
        chest.add("Chance:257-260#type:potion#data:8193-8270#PotionEffects:1 30-60 0-2");
        chest.add("Chance:261-264#type:potion#data:8193-8270#PotionEffects:3 30-60 0-2");
        chest.add("Chance:265-268#type:potion#data:8193-8270#PotionEffects:5 30-60 0-2");
        chest.add("Chance:269-272#type:potion#data:8193-8270#PotionEffects:8 30-60 0-2");
        chest.add("Chance:273-276#type:potion#data:8193-8270#PotionEffects:10 10-25 0-1");
        chest.add("Chance:277-280#type:potion#data:8193-8270#PotionEffects:13 30-60 0");
        chest.add("Chance:281-284#type:potion#data:8193-8270#PotionEffects:16 30-60 0");
        chest.add("Chance:285-290#type:potion#data:8193-8270#PotionEffects:23 30-60 0-1");
        List<String> chest1 = new ArrayList<String>();
        chest1.add("Times:6-8");
        chest1.add("Chances:110");
        chest1.add("Chance:1-10#typeid:306/307/308/309");
        chest1.add("Chance:11-20#type:blaze_powder#amount:1-3#DisplayName:&cshoot#Lore:&7Right Click to shoot");
        chest1.add("Chance:21-30#type:bow");
        chest1.add("Chance:31-40#type:cookie#amount:1-5#DisplayName:&6Super Cookie#Lore:&eKick Them all%%&9{playername}");
        chest1.add("Chance:41-50#type:lever#amount:2-3#DisplayName:&8C4 Item#Enchants:34 3#&c{playername}");
        chest1.add("Chance:51-52#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&79");
        chest1.add("Chance:53-56#type:enchanted_book#StoredEnchants:16-18 1-2");
        chest1.add("Chance:57-60#type:enchanted_book#StoredEnchants:0-3 1-2");
        chest1.add("Chance:61-65#type:enchanted_book#StoredEnchants:48 1-2");
        chest1.add("Chance:66-72#type:enchanted_book#StoredEnchants:34 1-2");
        chest1.add("Chance:73-80#type:enchanted_book#StoredEnchants:5 1-3");
        chest1.add("Chance:81-88#type:enchanted_book#StoredEnchants:6 1");
        chest1.add("Chance:89-95#type:enchanted_book#StoredEnchants:21 1-3");
        chest1.add("Chance:96-101#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb");
        chest1.add("Chance:102-105#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range I");
        chest1.add("Chance:106-108#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range II");
        chest1.add("Chance:109-110#typeid:303");
    /*
    chest1.add("Chance:111-113#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&718");
	chest1.add("Chance:114-115#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&727");
	chest1.add("Chance:116#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&736");
	*/
        file.addDefault("Chest.Items.Chest", chest);
        file.addDefault("Chest.Items.Chest1", chest1);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (Exception ex) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


    public static void load1(FileConfiguration file, File fileF) {
        file.addDefault("SplitSymbol", "#");
        file.options().header("Lucky Block 20-50 Luck Options.");
        List<String> lucks = new ArrayList<String>();
        lucks.add("Chance:1-50#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:51-89#Type:Mob#Title:&2Mobs#Luck:Try your luck");
        lucks.add("Chance:90-99#Type:Falling Block#Title:&eFalling Block#Luck:Good");
        lucks.add("Chance:100-101#Type:Lava#Title:&6Lava#Luck:Bad");
        lucks.add("Chance:102-103#Type:Villager#Title:&9Villager#Luck:Bad");
        lucks.add("Chance:104-114#Type:Splash Potion#Title:&1Splash Potion#Luck:Try your luck");
        lucks.add("Chance:115-116#Type:Primed Tnt#Title:&4Primed Tnt#Luck:Bad");
        lucks.add("Chance:117-120#Type:Lightning#Title:&bLightning#Luck:Bad");
        lucks.add("Chance:121#Type:Fake Diamond#Title:&bFake Diamond#Luck:Bad");
        lucks.add("Chance:122-123#Type:Firework#Title:&3Firework#Luck:Try your luck");
        lucks.add("Chance:124-125#Type:EBlock#Title:&cExploding Block#Luck:Good");
        lucks.add("Chance:126-189#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:190-191#Type:Stuck#Title:&7Stuck#Luck:Bad");
        lucks.add("Chance:192-193#Type:Damage#Title:&cDamage#Luck:Bad");
        lucks.add("Chance:194#Type:Tower#Title:&bDiamond Tower#Luck:Very Good");
        lucks.add("Chance:195#Type:FPigs#Title:&dFlying Pigs#Luck:Good");
        lucks.add("Chance:196#Type:Slimes#Title:&2Slimes#Luck:Bad");
        lucks.add("Chance:197#Type:Meteors#Title:&8Meteors#Luck:Very Bad");
        lucks.add("Chance:198-199#Type:FlyingLB#Title:&eFlying Lucky Block#Luck:Good");
        lucks.add("Chance:200-202#Type:Soldier#Title:&1&lSoldier#Luck:Bad");
        lucks.add("Chance:203-204#Type:LBItem#Title:&e&lLucky Block Item#Luck:Good");
        lucks.add("Chance:205-207#Type:SnowMovement#Title:&f&lSnow Moving#Luck:Good");
        lucks.add("Chance:208-212#Type:SIGN#Text:&cHello!,&dHow are you?#Title:&eSign#Luck:Bad");
        lucks.add("Chance:213-216#Type:Tree#TreeType:tree#Title:&aTree#Luck:Good");
        lucks.add("Chance:217-230#Type:Feed#Title:&6Feed#Luck:Good");
        lucks.add("Chance:231-235#Type:Heal#Title:&aHeal#Luck:Good");
        lucks.add("Chance:236-240#Type:Fireworks#Title:&dFireworks#Luck:Good");
        lucks.add("Chance:241-245#Type:Wolves or Ocelots#Title:&2Wolves or Ocelots#Luck:Good");
        lucks.add("Chance:246-250#Type:Changeable Block#Title:&4Changeable Block#Luck:Good");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 250);
        List<String> fblocks = new ArrayList<String>();
        fblocks.add("Chance:1-20#Type:quartz_block");
        fblocks.add("Chance:21-30#Type:gold_block");
        fblocks.add("Chance:31-45#Type:iron_block");
        fblocks.add("Chance:46-60#Type:lapis_block");
        fblocks.add("Chance:61-80#Type:glowstone");
        fblocks.add("Chance:81-100#Type:diamond_block");
        fblocks.add("Chance:101-110#Type:skull");
        file.addDefault("FallingBlocks.Chances", 110);
        file.addDefault("FallingBlocks.Blocks", fblocks);
        List<String> mobs = new ArrayList<String>();
        mobs.add("Chance:1-5#Type:zombie#CustomName:&4&lLucky Zombie#CustomNameVisible:true#maxHealth:80#Health:80"
                + "#Equipments:typeid:276,Enchants:16 5>>typeid:310,Enchants:0 4>>typeid:311,Enchants:0 4>>typeid:312,Enchants:0 4>>typeid:313,Enchants:0 4");
        mobs.add("Chance:6-12#Type:creeper#CustomName:&a&lLucky Creeper#CustomNameVisible:true#maxHealth:40#Health:40#Powered:true");
        mobs.add("Chance:13-20#Type:sheep#CustomName:&e&lLucky Sheep &4&l&k@@@#CustomNameVisible:true#maxHealth:200#Health:200#Color:0-15");
        mobs.add("Chance:21-30#Type:boat#WorkOnLand:true");
        mobs.add("Chance:31-40#Type:cow#CustomName:&6&lRideable Cow#CustomNameVisible:true#maxHealth:20#Health:20#Metadatas:rideable,true");
        mobs.add("Chance:41-46#Type:wolf#Tamed:true#Owner:{playername}#CollarColor:0-15#ParticleEffects:name:heart"
                + ",rx:0.3,ry:0.3,rz:0.3,speed:0,amount:10,range:64");
        mobs.add("Chance:47-60#Tries:8-16#Type:squid#maxHealth:30-60#Health:%maxHealth%#CustomName:&eLucky Squid &c%health%&f/&c%maxhealth%");
        mobs.add("Chance:61-70#Tries:3-5#Type:chicken#maxHealth:20#Health:20#CustomName:&e&lLucky Chicken#CustomNameVisible:true"
                + "#ParticleEffects:name:heart,rx:0.2,ry:0.2,rz:0.2,speed:0,amount:30,range:30//Type:droppeditem#ItemStack:"
                + "type:266,amount:15");
        mobs.add("Chance:71-80#Type:Horse#HorseColor:creamy#Style:whitedots#Saddle:true#CarryingChest:true#"
                + "Tamed:true#Owner:{playername}#CustomName:&1&l%playername%#maxHealth:80#Health:80#Metadatas:superhorse,true");
        file.addDefault("Entities.Chances", 80);
        file.addDefault("Entities.Mobs", mobs);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:iron_sword#data:0");
        s.add("Chance:11-20#type:fling_and_steel");
        s.add("Chance:21-30#type:iron_pickaxe");
        s.add("Chance:31-40#type:iron_axe");
        s.add("Chance:41-50#type:gold_chestplate");
        s.add("Chance:51-60#type:arrow#amount:3-5");
        s.add("Chance:61-70#type:emerald#amount:1-2");
        s.add("Chance:71-80#type:cooked_chicken#amount:2-3");
        s.add("Chance:81-90#type:cooked_beef#amount:2-3");
        s.add("Chance:91-100#type:bone#amount:2-3");
        s.add("Chance:101-110#type:rotten_flesh");
        s.add("Chance:111-120#type:chest#data:0#hasItemMeta:true#DisplayName:&a&l&oRandom Chest#hasLore:true#Lore:&7Place It%%&7Random Chest");
        s.add("Chance:121-130#type:golden_apple#amount:1-3");
        s.add("Chance:131-140#type:gold_ingot#amount:2-6");
        s.add("Chance:141-150#type:eye_of_ender#amount:5-11");
        s.add("Chance:151-160#type:ender_pearl#amount:2-5");
        s.add("Chance:161-170#type:beacon");
        s.add("Chance:171-180#type:monster_egg#amount:2-5#data:90-93#tries:4-5");
        s.add("Chance:181-190#type:gold_sword#tries:1-3");
        s.add("Chance:191-200#typeid:298/299/300/301/302/303/304/305#tries:3-7");
        s.add("Chance:201-210#typeid:331/152/76#amount:7-20#tries:2-4");
        s.add("Chance:211-220#typeid:37#amount:3-9//typeid:38#amount:2-6//typeid:38#amount:2-6#data:1//typeid:38#amount:2-6#data:2//typeid:38#amount:2-6#data:3"
                + "//typeid:38#amount:2-6#data:4//typeid:38#amount:2-6#data:5//typeid:38#amount:2-6#data:4//typeid:38#amount:2-6#data:6//typeid:38#amount:2-6#data:7"
                + "//typeid:38#amount:2-6#data:8");
        s.add("Chance:221-230#type:raw_fish#data:0#amount:2-5//type:raw_fish#data:1#amount:2-5//type:raw_fish#data:2#amount:2-5//"
                + "type:raw_fish#data:3#amount:2-5//type:cooked_fish#data:0#amount:2-5//type:cooked_fish#data:1#amount:2-5");
        s.add("Chance:231-240#typeid:325/326/327#tries:1-3");
        s.add("Chance:241-250#type:iron_axe#DisplayName:&e&lLucky Axe#Enchants:16 1-2%%17 1-2");
        file.addDefault("DroppedItems.Chances", 250);
        file.addDefault("DroppedItems.Items", s);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:6-12");
        chest.add("Chances:320");
        chest.add("Chance:1-15#type:emerald#amount:3-8");
        chest.add("Chance:16-20#type:%lb%1#DisplayName:&eLucky Block");
        chest.add("Chance:21-28#type:torch#amount:5-11");
        chest.add("Chance:29-34#type:workbench");
        chest.add("Chance:35-38#type:ladder#amount:6-12");
        chest.add("Chance:39-47#type:tnt#amount:1-3");
        chest.add("Chance:48-54#type:diamond_hoe");
        chest.add("Chance:55-60#type:cactus#amount:6-10");
        chest.add("Chance:61-72#type:gold_ingot#amount:3-6");
        chest.add("Chance:73-78#type:coal_block#amount:1-2");
        chest.add("Chance:79-82#type:bow#Enchants:48-52 1-3");
        chest.add("Chance:83-89#type:arrow#amount:10-17");
        chest.add("Chance:90-91#type:diamond#amount:2-5");
        chest.add("Chance:92-94#type:iron_ingot#amount:4-9");
        chest.add("Chance:95-99#type:stone_sword");
        chest.add("Chance:100-102#type:golden_apple#amount:2-4");
        chest.add("Chance:103-113#type:water_bucket");
        chest.add("Chance:114-124#type:lava_bucket");
        chest.add("Chance:125-134#type:snow_ball#amount:10-16");
        chest.add("Chance:135-149#type:raw_fish#amount:3-6#data:0-2");
        chest.add("Chance:150-152#type:raw_fish#amount:3-6#data:0-2#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:153-164#type:ender_pearl#amount:2-3");
        chest.add("Chance:165-170#type:bread#amount:2-6");
        chest.add("Chance:171-173#type:bread#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:174-180#type:carrot_item#amount:2-6");
        chest.add("Chance:181-183#type:carrot_item#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:184-188#type:exp_bottle#amount:3-7");
        chest.add("Chance:189-195#type:mushroom_soup");
        chest.add("Chance:196-199#type:mushroom_soup#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:200-207#type:wood#amount:24-32");
        chest.add("Chance:208-212#type:glass_bottle#amount:2-5");
        chest.add("Chance:213-215#type:fishing_rod#Enchants:34 1-2");
        chest.add("Chance:216-222#type:melon#amount:2-8");
        chest.add("Chance:223-225#type:melon#amount:2-8#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:226-230#type:iron_helmet");
        chest.add("Chance:231-235#type:iron_chestplate");
        chest.add("Chance:236-240#type:chainmail_leggings");
        chest.add("Chance:241-246#type:gold_boots");
        chest.add("Chance:247-250#type:diamond_boots");
        chest.add("Chance:251-256#type:leather_chestplate#LeatherArmor:0-200,0-200,0-200#Lore:"
                + "&c{RED}&6,&a{GREEN}&6,&9{BLUE}");
        chest.add("Chance:257-260#type:potion#data:8193-8270#PotionEffects:1 30-60 0-2");
        chest.add("Chance:261-264#type:potion#data:8193-8270#PotionEffects:3 30-60 0-2");
        chest.add("Chance:265-268#type:potion#data:8193-8270#PotionEffects:5 30-60 0-2");
        chest.add("Chance:269-272#type:potion#data:8193-8270#PotionEffects:8 30-60 0-2");
        chest.add("Chance:273-276#type:potion#data:8193-8270#PotionEffects:10 10-25 0-1");
        chest.add("Chance:277-280#type:potion#data:8193-8270#PotionEffects:13 30-60 0");
        chest.add("Chance:281-284#type:potion#data:8193-8270#PotionEffects:16 30-60 0");
        chest.add("Chance:285-290#type:potion#data:8193-8270#PotionEffects:23 30-60 0-1");
        chest.add("Chance:291-294#type:potion#data:8193-8270#PotionEffects:14 20-50 0");
        chest.add("Chance:295-299#type:potion#data:16385-16462#PotionEffects:7 0 0-1");
        chest.add("Chance:295-299#type:potion#data:16385-16462#PotionEffects:2 10-20 0-1");
        chest.add("Chance:300-303#type:potion#data:16385-16462#PotionEffects:4 10-20 0-1");
        chest.add("Chance:304-306#type:potion#data:16385-16462#PotionEffects:17 10-20 0-1");
        chest.add("Chance:307-310#type:potion#data:16385-16462#PotionEffects:18 10-20 0-1");
        chest.add("Chance:311-313#type:potion#data:16385-16462#PotionEffects:19 10-20 0-1");
        chest.add("Chance:314-318#type:emerald#amount:1-3#DisplayName:&7Wolf Tool");
        chest.add("Chance:319-320#type:%LuckyBow%");
        List<String> chest1 = new ArrayList<String>();
        chest1.add("Times:6-8");
        chest1.add("Chances:125");
        chest1.add("Chance:1-10#typeid:306/307/308/309");
        chest1.add("Chance:11-20#type:blaze_powder#amount:1-3#DisplayName:&cshoot#Lore:&7Right Click to shoot");
        chest1.add("Chance:21-30#type:bow");
        chest1.add("Chance:31-40#type:cookie#amount:1-5#DisplayName:&6Super Cookie#Lore:&eKick Them all%%&9{playername}");
        chest1.add("Chance:41-50#type:lever#amount:2-3#DisplayName:&8C4 Item#Enchants:34 3#&c{playername}");
        chest1.add("Chance:51-52#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&79");
        chest1.add("Chance:53-56#type:enchanted_book#StoredEnchants:16-18 1-2");
        chest1.add("Chance:57-60#type:enchanted_book#StoredEnchants:0-3 1-2");
        chest1.add("Chance:61-65#type:enchanted_book#StoredEnchants:48 1-2");
        chest1.add("Chance:66-72#type:enchanted_book#StoredEnchants:34 1-2");
        chest1.add("Chance:73-80#type:enchanted_book#StoredEnchants:5 1-3");
        chest1.add("Chance:81-88#type:enchanted_book#StoredEnchants:6 1");
        chest1.add("Chance:89-95#type:enchanted_book#StoredEnchants:21 1-3");
        chest1.add("Chance:96-101#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb");
        chest1.add("Chance:102-105#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range I");
        chest1.add("Chance:106-108#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range II");
        chest1.add("Chance:109-114#typeid:303");
        chest1.add("Chance:115-118#typeid:1-4#DisplayName:&2BombBlock#amount:3-9");
        chest1.add("Chance:119#typeid:35#data:0-15#DisplayName:&2BombBlock#amount:3-9");
        chest1.add("Chance:120#typeid:5#data:0-5#DisplayName:&2BombBlock#amount:3-9");
        chest1.add("Chance:121-122#type:name_tag#DisplayName:&eLucky Block Tool#Lore:&8Made in China!");
        chest1.add("Chance:123-125#type:ender_pearl#amount:1-2#Enchants:34 10#DisplayName:&9Random Teleport");
        file.addDefault("Chest.Items.Chest", chest);
        file.addDefault("Chest.Items.Chest1", chest1);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (IOException e) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


    public static void load2(FileConfiguration file, File fileF) {
        file.addDefault("SplitSymbol", "#");
        file.options().header("Lucky Block 51-99 Luck Options.");
        List<String> lucks = new ArrayList<String>();
        lucks.add("Chance:1-50#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:51-89#Type:Mob#Title:&2Mobs#Luck:Try your luck");
        lucks.add("Chance:90-99#Type:Falling Block#Title:&eFalling Block#Luck:Good");
        lucks.add("Chance:100-101#Type:Villager#Title:&9Villager#Luck:Bad");
        lucks.add("Chance:102-110#Type:Splash Potion#Title:&1Splash Potion#Luck:Try your luck");
        lucks.add("Chance:111-113#Type:Enchant#Title:&9Enchant Item in Hand#Luck:Good");
        lucks.add("Chance:114-118#Type:Repair#Title:&cRepair Items#Luck:Good");
        lucks.add("Chance:119-121#Type:XP#Title:&aXP#Amount:140-180#Luck:Good");
        lucks.add("Chance:122#Type:Firework#Title:&3Firework#Luck:Try your luck");
        lucks.add("Chance:123-125#Type:Poison Enemies#Title:&2Poison Enemies#Luck:Good");
        lucks.add("Chance:126-189#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:190-191#Type:Gift#Title:&dGift#Luck:Good");
        lucks.add("Chance:192-193#Type:Remove Harmful Effects#Title:&5Remove Harmful Effects#Luck:Good");
        lucks.add("Chance:194#Type:Tower#Title:&bDiamond Tower#Luck:Very Good");
        lucks.add("Chance:195#Type:FPigs#Title:&dFlying Pigs#Luck:Good");
        lucks.add("Chance:196#Type:Slimes#Title:&2Slimes#Luck:Bad");
        lucks.add("Chance:197-199#Type:FlyingLB#Title:&eFlying Lucky Block#Luck:Good");
        lucks.add("Chance:200-203#Type:LBItem#Title:&e&lLucky Block Item#Luck:Good");
        lucks.add("Chance:204-205#Type:SnowMovement#Title:&f&lSnow Moving#Luck:Good");
        lucks.add("Chance:206-210#Type:SIGN#Text:&cHello!,&dHow are you?#Title:&eSign#Luck:Bad");
        lucks.add("Chance:211-215#Type:Tree#TreeType:tree#Title:&aTree#Luck:Good");
        lucks.add("Chance:216-230#Type:Feed#Title:&6Feed#Luck:Good");
        lucks.add("Chance:231-235#Type:Heal#Title:&aHeal#Luck:Good");
        lucks.add("Chance:236-240#Type:Fireworks#Title:&dFireworks#Luck:Good");
        lucks.add("Chance:241-245#Type:Wolves or Ocelots#Title:&2Wolves or Ocelots#Luck:Good");
        lucks.add("Chance:246-250#Type:Changeable Block#Title:&4Changeable Block#Luck:Good");
        lucks.add("Chance:251-255#Type:Added Item#Title:&5Added Item#Luck:Good");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 255);
        List<String> fblocks = new ArrayList<String>();
        fblocks.add("Chance:1-20#Type:emerald_block");
        fblocks.add("Chance:21-30#Type:gold_block");
        fblocks.add("Chance:31-45#Type:iron_block");
        fblocks.add("Chance:46-60#Type:lapis_block");
        fblocks.add("Chance:61-80#Type:quartz_block");
        file.addDefault("FallingBlocks.Chances", 80);
        file.addDefault("FallingBlocks.Blocks", fblocks);
        List<String> mobs = new ArrayList<String>();
        mobs.add("Chance:1-5#Type:zombie#CustomName:&4&lLucky Zombie#CustomNameVisible:true#maxHealth:80#Health:80"
                + "#Equipments:typeid:276,Enchants:16 5>>typeid:310,Enchants:0 4>>typeid:311,Enchants:0 4>>typeid:312,Enchants:0 4>>typeid:313,Enchants:0 4");
        mobs.add("Chance:13-20#Type:sheep#CustomName:&e&lLucky Sheep &4&l&k@@@#CustomNameVisible:true#maxHealth:70#Health:70#Color:0-15");
        mobs.add("Chance:21-30#Type:cow#CustomName:&6&lRideable Cow#CustomNameVisible:true#maxHealth:20#Health:20#Metadatas:rideable,true");
        mobs.add("Chance:31-37#Type:wolf#Tamed:true#Owner:{playername}#CollarColor:0-15#ParticleEffects:name:heart"
                + ",rx:0.3,ry:0.3,rz:0.3,speed:0,amount:10,range:64");
        mobs.add("Chance:38-45#Tries:8-16#Type:squid#maxHealth:30-60#Health:%maxHealth%#CustomName:&eLucky Squid &c%health%&f/&c%maxhealth%");
        mobs.add("Chance:46-65#Tries:3-5#Type:chicken#maxHealth:20#Health:20#CustomName:&e&lLucky Chicken#CustomNameVisible:true"
                + "#ParticleEffects:name:heart,rx:0.2,ry:0.2,rz:0.2,speed:0,amount:30,range:30//Type:droppeditem#ItemStack:"
                + "type:266,amount:25");
        mobs.add("Chance:65-75#Type:Horse#HorseColor:creamy#Style:whitedots#Saddle:true#CarryingChest:true#"
                + "Tamed:true#Owner:{playername}#CustomName:&1&l%playername%#maxHealth:80#Health:80#Metadatas:superhorse,true");
        mobs.add("Chance:76-85#Type:Snowman#CustomName:&f&lSnowman#CustomNameVisible:true#maxHealth:20#Health:20");
        file.addDefault("Entities.Chances", 85);
        file.addDefault("Entities.Mobs", mobs);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:iron_sword#data:0");
        s.add("Chance:11-20#type:flint_and_steel#data:0-5");
        s.add("Chance:21-30#type:iron_pickaxe");
        s.add("Chance:31-40#type:iron_axe");
        s.add("Chance:41-45#type:gold_chestplate#data:0-20");
        s.add("Chance:46-50#type:arrow#amount:3-5");
        s.add("Chance:51-60#type:emerald#amount:1-2");
        s.add("Chance:61-65#type:cooked_chicken#amount:2-3//type:cooked_beef#amount:2-3");
        s.add("Chance:66-75#type:chest#data:0#hasItemMeta:true#DisplayName:&a&l&oRandom Chest#hasLore:true#Lore:&7Place It%%&7Random Chest");
        s.add("Chance:76-80#type:golden_apple#amount:1-3");
        s.add("Chance:81-90#type:gold_ingot#amount:2-6");
        s.add("Chance:91-100#type:ender_pearl#amount:2-5");
        s.add("Chance:101-105#type:beacon");
        s.add("Chance:106-110#type:monster_egg#amount:2-5#data:90-93#tries:4-5");
        s.add("Chance:111-120#typeid:417-419");
        file.addDefault("DroppedItems.Chances", 120);
        file.addDefault("DroppedItems.Items", s);
        List<String> ai = new ArrayList<String>();
        ai.add("Chance:1-40#Title:&3Lucky Block Taker#type:arrow#DisplayName:&8&lLucky Block Taker#Lore:&7Right Click to use#Message:"
                + "&aAdded (&8&lLucky Block Taker&a) To your Inventory!");
        ai.add("Chance:41-70#Title:&4Exploding Block#type:stained_clay#amount:15-30#data:0-15#DisplayName:&4Exploding!#Lore:&7Place It#"
                + "Message:&5Added Exploding Blocks To your Inventory!");
        ai.add("Chance:71-100#Title:&fCake#type:cake#Message:&aGiving Cake");
        file.addDefault("AddedItems.Chances", 100);
        file.addDefault("AddedItems.Items", ai);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:6-8");
        chest.add("Chances:350");
        chest.add("Chance:1-15#type:emerald#amount:4-8");
        chest.add("Chance:16-20#type:%lb%1#DisplayName:&eLucky Block");
        chest.add("Chance:21-28#type:torch#amount:5-11");
        chest.add("Chance:29-34#type:bow");
        chest.add("Chance:35-38#type:stick#DisplayName:&6Gun#Lore:&4Deals 1.0 Damage#amount:6-12");
        chest.add("Chance:39-47#type:tnt#amount:1-4");
        chest.add("Chance:48-54#type:bow#Enchants:48-52 1-3");
        chest.add("Chance:55-60#type:stick#DisplayName:&6Gun I#Lore:&4Deals 2.5 Damage#amount:5-10");
        chest.add("Chance:61-72#type:gold_ingot#amount:4-6");
        chest.add("Chance:73-78#type:coal_block#amount:1-3");
        chest.add("Chance:79-82#type:bow#Enchants:48-52 1-3");
        chest.add("Chance:83-89#type:arrow#amount:10-32");
        chest.add("Chance:90-91#type:diamond#amount:3-5");
        chest.add("Chance:92-94#type:iron_ingot#amount:4-10");
        chest.add("Chance:95-99#type:stone_sword");
        chest.add("Chance:100-102#type:golden_apple#amount:1-2#data:1");
        chest.add("Chance:103-113#type:water_bucket");
        chest.add("Chance:114-124#type:lava_bucket");
        chest.add("Chance:125-134#type:snow_ball#amount:10-16");
        chest.add("Chance:135-149#type:raw_fish#amount:3-6#data:0-2");
        chest.add("Chance:150-152#type:raw_fish#amount:3-6#data:0-2#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:153-164#type:ender_pearl#amount:2-3");
        chest.add("Chance:165-170#type:bread#amount:2-6");
        chest.add("Chance:171-173#type:bread#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:174-180#type:carrot_item#amount:2-6");
        chest.add("Chance:181-183#type:carrot_item#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:184-188#type:exp_bottle#amount:3-7");
        chest.add("Chance:189-195#type:mushroom_soup");
        chest.add("Chance:196-199#type:mushroom_soup#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:200-207#type:wood#amount:24-32");
        chest.add("Chance:208-212#type:glass_bottle#amount:2-5");
        chest.add("Chance:213-215#type:fishing_rod#Enchants:34 1-2");
        chest.add("Chance:216-222#type:melon#amount:2-8");
        chest.add("Chance:223-225#type:melon#amount:2-8#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:226-230#type:iron_helmet");
        chest.add("Chance:231-235#type:iron_chestplate");
        chest.add("Chance:236-240#type:chainmail_leggings");
        chest.add("Chance:241-246#type:gold_boots");
        chest.add("Chance:247-250#type:diamond_boots");
        chest.add("Chance:251-256#type:leather_chestplate#LeatherArmor:0-200,0-200,0-200#Lore:"
                + "&c{RED}&6,&a{GREEN}&6,&9{BLUE}");
        chest.add("Chance:257-260#type:potion#data:8193-8270#PotionEffects:1 30-60 0-2");
        chest.add("Chance:261-264#type:potion#data:8193-8270#PotionEffects:3 30-60 0-2");
        chest.add("Chance:265-268#type:potion#data:8193-8270#PotionEffects:5 30-60 0-2");
        chest.add("Chance:269-272#type:potion#data:8193-8270#PotionEffects:8 30-60 0-2");
        chest.add("Chance:273-276#type:potion#data:8193-8270#PotionEffects:10 10-25 0-1");
        chest.add("Chance:277-280#type:potion#data:8193-8270#PotionEffects:13 30-60 0");
        chest.add("Chance:281-284#type:potion#data:8193-8270#PotionEffects:16 30-60 0");
        chest.add("Chance:285-290#type:potion#data:8193-8270#PotionEffects:23 30-60 0-1");
        chest.add("Chance:291-294#type:potion#data:8193-8270#PotionEffects:14 20-50 0");
        chest.add("Chance:295-299#type:potion#data:16385-16462#PotionEffects:7 0 0-1");
        chest.add("Chance:295-299#type:potion#data:16385-16462#PotionEffects:2 10-20 0-1");
        chest.add("Chance:300-303#type:potion#data:16385-16462#PotionEffects:4 10-20 0-1");
        chest.add("Chance:304-306#type:potion#data:16385-16462#PotionEffects:17 10-20 0-1");
        chest.add("Chance:307-310#type:potion#data:16385-16462#PotionEffects:18 10-20 0-1");
        chest.add("Chance:311-313#type:potion#data:16385-16462#PotionEffects:19 10-20 0-1");
        chest.add("Chance:314-318#type:emerald#amount:1-3#DisplayName:&7Wolf Tool");
        chest.add("Chance:319-322#type:web#amount:3-6");
        chest.add("Chance:323-325#type:potion#data:8193-8270#PotionEffects:14 20-30 0");
        chest.add("Chance:326-330#type:exp_bottle#amount:3-8");
        chest.add("Chance:331-334#type:chainmail_helmet");
        chest.add("Chance:335-337#type:chainmail_chestplate");
        chest.add("Chance:338-340#type:chainmail_boots");
        chest.add("Chance:341-343#type:%LuckyBow%");
        chest.add("Chance:344-345#type:%LuckySword%");
        chest.add("Chance:346-350#type:%LuckyBoots%");
        file.addDefault("Chest.Items.Chest", chest);
        List<String> chest1 = new ArrayList<String>();
        chest1.add("Times:5-10");
        chest1.add("Chances:90");
        chest1.add("Chance:1-20#type:glass_bottle#amount:1-3");
        chest1.add("Chance:21-35#type:potion");
        chest1.add("Chance:36-37#type:nether_star");
        chest1.add("Chance:38-40#type:quartz");
        chest1.add("Chance:41-45#type:magma_cream#amount:1-3");
        chest1.add("Chance:46-50#type:golden_carrot#amount:1-2");
        chest1.add("Chance:51-55#type:speckled_melon#amount:1-2");
        chest1.add("Chance:56-60#type:blaze_rod#amount:1-2");
        chest1.add("Chance:61-65#type:gold_nugget#amount:1-4");
        chest1.add("Chance:66-70#type:nether_warts#amount:1-5");
        chest1.add("Chance:71-75#type:sulphur#amount:1-2");
        chest1.add("Chance:76-80#type:glowstone_dust#amount:2-4");
        chest1.add("Chance:81-90#type:redstone#amount:1-3");
        file.addDefault("Chest.Items.Chest1", chest1);
        List<String> chest2 = new ArrayList<String>();
        chest2.add("Times:5-7");
        chest2.add("Chances:175");
        chest2.add("Chance:1-20#type:gold_nugget#amount:1-6");
        chest2.add("Chance:21-39#type:coal#amount:1-3");
        chest2.add("Chance:39-46#type:quartz#amount:1-4");
        chest2.add("Chance:47-63#type:ink_sack#amount:1-4#data:4");
        chest2.add("Chance:64-80#type:iron_ingot#amount:1-3");
        chest2.add("Chance:81-96#type:gold_ingot#amount:1-3");
        chest2.add("Chance:97-111#type:diamond#amount:1-2");
        chest2.add("Chance:112-126#type:emerald#amount:1-2");
        chest2.add("Chance:127-137#type:quartz_block");
        chest2.add("Chance:138-148#type:lapis_block");
        chest2.add("Chance:149-158#type:iron_block");
        chest2.add("Chance:159-166#type:gold_block");
        chest2.add("Chance:167-171#type:diamond_block");
        chest2.add("Chance:172-175#type:emerald_block");
        file.addDefault("Chest.Items.Chest2", chest2);
        List<String> chest3 = new ArrayList<String>();
        chest3.add("Times:8-13");
        chest3.add("Chances:93");
        chest3.add("Chance:1-10#typeid:306/307/308/309");
        chest3.add("Chance:11-20#type:blaze_powder#amount:1-3#DisplayName:&cshoot#Lore:&7Right Click to shoot");
        chest3.add("Chance:21-30#type:bow");
        chest3.add("Chance:31-40#type:cookie#amount:1-5#DisplayName:&6Super Cookie#Lore:&eKick Them all%%&9{playername}");
        chest3.add("Chance:41-50#type:lever#amount:2-3#DisplayName:&8C4 Item#Enchants:34 3#&c{playername}");
        chest3.add("Chance:51-52#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&79");
        chest3.add("Chance:53-60#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb");
        chest3.add("Chance:61-64#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range I");
        chest3.add("Chance:65-67#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range II");
        chest3.add("Chance:68-73#type:diamond_helmet");
        chest3.add("Chance:74-77#type:diamond_chestplate");
        chest3.add("Chance:78-82#type:diamond_leggings");
        chest3.add("Chance:83-89#type:diamond_boots");
        chest3.add("Chance:90-93#type:name_tag#DisplayName:&eLucky Block Tool#Lore:&8Made in China");
        file.addDefault("Chest.Items.Chest2", chest3);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (IOException e) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


    public static void load3(FileConfiguration file, File fileF) {
        file.addDefault("SplitSymbol", "#");
        file.options().header("Lucky Block 100-199 Luck Options.");
        List<String> lucks = new ArrayList<String>();
        lucks.add("Chance:1-50#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:51-89#Type:Mob#Title:&2Mobs#Luck:Try your luck");
        lucks.add("Chance:90-99#Type:Falling Block#Title:&eFalling Block#Luck:Good");
        lucks.add("Chance:100-110#Type:Splash Potion#Title:&1Splash Potion#Luck:Try your luck");
        lucks.add("Chance:111-113#Type:Enchant#Title:&9Enchant Item in Hand#Luck:Good");
        lucks.add("Chance:114-118#Type:Repair#Title:&cRepair Items#Luck:Good");
        lucks.add("Chance:119-121#Type:XP#Title:&aXP#Amount:280-340#Luck:Good");
        lucks.add("Chance:122#Type:Firework#Title:&3Firework#Luck:Try your luck");
        lucks.add("Chance:123-125#Type:Poison Enemies#Title:&2Poison Enemies#Luck:Good");
        lucks.add("Chance:126-189#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:190-191#Type:Gift#Title:&dGift#Luck:Good");
        lucks.add("Chance:192-193#Type:Remove Harmful Effects#Title:&5Remove Harmful Effects#Luck:Good");
        lucks.add("Chance:194#Type:Tower#Title:&bDiamond Tower#Luck:Very Good");
        lucks.add("Chance:195#Type:FPigs#Title:&dFlying Pigs#Luck:Good");
        lucks.add("Chance:196-198#Type:FlyingLB#Title:&eFlying Lucky Block#Luck:Good");
        lucks.add("Chance:199-203#Type:Tree#TreeType:tree#Title:&aTree#Luck:Good");
        lucks.add("Chance:204-208#Type:Feed#Title:&6Feed#Luck:Good");
        lucks.add("Chance:209-212#Type:Heal#Title:&aHeal#Luck:Good");
        lucks.add("Chance:213-216#Type:Fireworks#Title:&dFireworks#Luck:Good");
        lucks.add("Chance:217-220#Type:Wolves or Ocelots#Title:&2Wolves or Ocelots#Luck:Good");
        lucks.add("Chance:221-225#Type:Changeable Block#Title:&4Changeable Block#Luck:Good");
        lucks.add("Chance:226-230#Type:Added Item#Title:&5Added Item#Luck:Good");
        lucks.add("Chance:231-240#Type:Custom Structure#Title:&dCustom Structure#Luck:Try your luck");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 240);
        List<String> fblocks = new ArrayList<String>();
        fblocks.add("Chance:1-20#Type:emerald_block");
        fblocks.add("Chance:21-30#Type:gold_block");
        fblocks.add("Chance:31-45#Type:iron_block");
        fblocks.add("Chance:46-60#Type:lapis_block");
        fblocks.add("Chance:61-80#Type:quartz_block");
        file.addDefault("FallingBlocks.Chances", 80);
        file.addDefault("FallingBlocks.Blocks", fblocks);
        List<String> mobs = new ArrayList<String>();
        mobs.add("Chance:1-12#Type:mooshroom#CustomName:&e&lLucky Mooshroom#CustomNameVisible:1#Tries:3-5");
        mobs.add("Chance:13-20#Type:sheep#CustomName:&e&lLucky Sheep &4&l&k@@@#CustomNameVisible:true#maxHealth:70#Health:70#Color:0-15");
        mobs.add("Chance:21-30#Type:cow#CustomName:&6&lRideable Cow#CustomNameVisible:true#maxHealth:20#Health:20#Metadatas:rideable,true");
        mobs.add("Chance:31-37#Type:wolf#Tamed:true#Owner:{playername}#CollarColor:0-15#ParticleEffects:name:heart"
                + ",rx:0.3,ry:0.3,rz:0.3,speed:0,amount:10,range:64");
        mobs.add("Chance:38-45#Tries:8-16#Type:squid#maxHealth:30-60#Health:%maxHealth%#CustomName:&eLucky Squid &c%health%&f/&c%maxhealth%#Metadatas:luckysquid,true");
        mobs.add("Chance:46-65#Title:&eChicken#Tries:3-5#Type:chicken#maxHealth:20#Health:20#CustomName:&e&lLucky Chicken#CustomNameVisible:true"
                + "#ParticleEffects:name:heart,rx:0.2,ry:0.2,rz:0.2,speed:0,amount:30,range:30//Type:droppeditem#ItemStack:"
                + "type:266,amount:10");
        mobs.add("Chance:65-75#Type:Horse#HorseColor:creamy#Style:whitedots#Saddle:true#CarryingChest:true#"
                + "Tamed:true#Owner:{playername}#CustomName:&1&l%playername%#maxHealth:120#Health:120#Metadatas:superhorse,true");
        file.addDefault("Entities.Chances", 85);
        file.addDefault("Entities.Mobs", mobs);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:diamond_sword#DisplayName:&eLucky Sword#Enchants:16 1-2%%19 1-2");
        s.add("Chance:11-20#type:flint_and_steel#Enchants:34 1-3");
        s.add("Chance:21-30#type:diamond_pickaxe");
        s.add("Chance:31-40#type:diamond_axe");
        s.add("Chance:41-45#type:diamond_helmet//type:diamond_chestplate//typeid:312/313");
        s.add("Chance:46-50#type:cooked_chicken#amount:8-16//type:cooked_beef#amount:8-16");
        s.add("Chance:55-65#type:chest#data:0#hasItemMeta:true#DisplayName:&a&l&oRandom Chest#hasLore:true#Lore:&7Place It%%&7Random Chest");
        s.add("Chance:66-70#type:golden_apple#amount:3-6//type:golden_apple#amount:1-2#data:1");
        s.add("Chance:71-75#type:gold_ingot#amount:3-6//type:diamond#amount:2-4//type:iron_ingot#amount:4-6//type:emerald#amount:2-4");
        s.add("Chance:76-80#type:ender_pearl#amount:3-6");
        s.add("Chance:81-85#type:beacon");
        s.add("Chance:86-90#type:monster_egg#amount:2-5#data:90-93#tries:4-5");
        s.add("Chance:91-100#typeid:417-419");
        file.addDefault("DroppedItems.Chances", 100);
        file.addDefault("DroppedItems.Items", s);
        List<String> ai = new ArrayList<String>();
        ai.add("Chance:1-40#Title:&3Lucky Block Taker#type:arrow#DisplayName:&8&lLucky Block Taker#Lore:&7Right Click to use#Message:"
                + "&aAdded (&8&lLucky Block Taker&a) To your Inventory!");
        ai.add("Chance:41-70#Title:&4Exploding Block#type:stained_clay#amount:15-30#data:0-15#DisplayName:&4Exploding!#Lore:&7Place It#"
                + "Message:&5Added Exploding Blocks To your Inventory!");
        ai.add("Chance:71-100#Title:&fCake#type:cake#Message:&aGiving Cake");
        file.addDefault("AddedItems.Chances", 100);
        file.addDefault("AddedItems.Items", ai);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:6-12");
        chest.add("Chances:250");
        chest.add("Chance:1-10#type:%lb%1#DisplayName:&eLucky Block#amount:1-5");
        chest.add("Chance:11-25#type:bow");
        chest.add("Chance:26-38#type:stick#DisplayName:&6Gun#Lore:&4Deals 1.0 Damage#amount:6-12");
        chest.add("Chance:39-42#type:bow#Enchants:48-52 1-4%%48-52 1-4");
        chest.add("Chance:43-53#type:stick#DisplayName:&6Gun I#Lore:&4Deals 2.5 Damage#amount:5-10");
        chest.add("Chance:54-58#type:stick#DisplayName:&6Gun II#Lore:&4Deals 5.5 Damage#amount:3-6");
        chest.add("Chance:59-72#type:gold_ingot#amount:4-6");
        chest.add("Chance:73-78#type:coal_block#amount:4-8");
        chest.add("Chance:79-89#type:arrow#amount:10-32");
        chest.add("Chance:90-91#type:diamond#amount:3-5");
        chest.add("Chance:92-94#type:iron_ingot#amount:4-10");
        chest.add("Chance:95-99#type:stone_sword");
        chest.add("Chance:100-102#type:golden_apple#amount:1-2#data:1");
        chest.add("Chance:103-113#type:water_bucket");
        chest.add("Chance:114-124#type:lava_bucket");
        chest.add("Chance:125-134#type:snow_ball#amount:10-16");
        chest.add("Chance:135-137#type:raw_fish#amount:3-6#data:0-2#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:138-149#type:ender_pearl#amount:2-3");
        chest.add("Chance:150-152#type:bread#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:153-155#type:carrot_item#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:156-160#type:exp_bottle#amount:3-7");
        chest.add("Chance:161-163#type:mushroom_soup#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:164-168#type:wood#amount:24-32");
        chest.add("Chance:169-171#type:fishing_rod#Enchants:34 1-2");
        chest.add("Chance:171-174#type:melon#amount:2-8#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:175-179#type:iron_helmet");
        chest.add("Chance:180-182#type:iron_chestplate");
        chest.add("Chance:183-186#type:iron_leggings");
        chest.add("Chance:187-192#type:iron_boots");
        chest.add("Chance:193-195#type:emerald#amount:1-3#DisplayName:&7Wolf Tool");
        chest.add("Chance:196-200#type:web#amount:3-6");
        chest.add("Chance:201-204#type:%LuckyBow%");
        chest.add("Chance:205-209#type:%LuckySword%");
        chest.add("Chance:210-215#type:%LuckyBoots%");
        chest.add("Chance:216-219#type:%LuckyLeggings%");
        chest.add("Chance:220-224#type:%LuckyHelmet%");
        chest.add("Chance:225-227#type:%LuckyChestplate%");
        chest.add("Chance:228-230#type:%LuckyTool%");
        chest.add("Chance:231-235#type:%ThorAxe%");
        chest.add("Chance:236-240#type:%LuckyShovel%");
        chest.add("Chance:241-245#type:%LuckyAxe%");
        chest.add("Chance:246-250#type:%LuckyPickaxe%");
        file.addDefault("Chest.Items.Chest", chest);
        List<String> chest1 = new ArrayList<String>();
        chest1.add("Times:6-12");
        chest1.add("Chances:90");
        chest1.add("Chance:1-20#type:glass_bottle#amount:1-3");
        chest1.add("Chance:21-35#type:potion");
        chest1.add("Chance:36-37#type:nether_star");
        chest1.add("Chance:38-40#type:quartz");
        chest1.add("Chance:41-45#type:magma_cream#amount:1-3");
        chest1.add("Chance:46-50#type:golden_carrot#amount:1-2");
        chest1.add("Chance:51-55#type:speckled_melon#amount:1-2");
        chest1.add("Chance:56-60#type:blaze_rod#amount:1-2");
        chest1.add("Chance:61-65#type:gold_nugget#amount:1-4");
        chest1.add("Chance:66-70#type:nether_warts#amount:1-5");
        chest1.add("Chance:71-75#type:sulphur#amount:1-2");
        chest1.add("Chance:76-80#type:glowstone_dust#amount:2-4");
        chest1.add("Chance:81-90#type:redstone#amount:1-3");
        file.addDefault("Chest.Items.Chest1", chest1);
        List<String> chest2 = new ArrayList<String>();
        chest2.add("Times:6-8");
        chest2.add("Chances:175");
        chest2.add("Chance:1-20#type:gold_nugget#amount:1-6");
        chest2.add("Chance:21-39#type:coal#amount:1-3");
        chest2.add("Chance:39-46#type:quartz#amount:1-4");
        chest2.add("Chance:47-63#type:ink_sack#amount:1-4#data:4");
        chest2.add("Chance:64-80#type:iron_ingot#amount:1-3");
        chest2.add("Chance:81-96#type:gold_ingot#amount:1-3");
        chest2.add("Chance:97-111#type:diamond#amount:1-2");
        chest2.add("Chance:112-126#type:emerald#amount:1-2");
        chest2.add("Chance:127-137#type:quartz_block");
        chest2.add("Chance:138-148#type:lapis_block");
        chest2.add("Chance:149-158#type:iron_block");
        chest2.add("Chance:159-166#type:gold_block");
        chest2.add("Chance:167-171#type:diamond_block");
        chest2.add("Chance:172-175#type:emerald_block");
        file.addDefault("Chest.Items.Chest2", chest2);
        List<String> chest3 = new ArrayList<String>();
        chest3.add("Times:6-8");
        chest3.add("Chances:93");
        chest3.add("Chance:1-10#typeid:306/307/308/309");
        chest3.add("Chance:11-20#type:blaze_powder#amount:1-3#DisplayName:&cshoot#Lore:&7Right Click to shoot");
        chest3.add("Chance:21-40#type:cookie#amount:1-5#DisplayName:&6Super Cookie#Lore:&eKick Them all%%&9{playername}");
        chest3.add("Chance:41-50#type:lever#amount:2-3#DisplayName:&8C4 Item#Enchants:34 3#&c{playername}");
        chest3.add("Chance:51-52#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&79");
        chest3.add("Chance:53-60#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb");
        chest3.add("Chance:61-64#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range I");
        chest3.add("Chance:65-67#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range II");
        chest3.add("Chance:68-73#type:diamond_helmet");
        chest3.add("Chance:74-77#type:diamond_chestplate");
        chest3.add("Chance:78-82#type:diamond_leggings");
        chest3.add("Chance:83-89#type:diamond_boots");
        chest3.add("Chance:90-93#type:name_tag#DisplayName:&eLucky Block Tool#Lore:&8Made in China");
        file.addDefault("Chest.Items.Chest2", chest3);
        List<String> cs = new ArrayList<String>();
        cs.add("Chance:1-100#LocationType:Player#Type:49#Loc:0,-1,0//Type:49#Loc:1,-1,0//Type:49#Loc:-1,-1,0//"
                + "Type:49#Loc:0,-1,-1//Type:49#Loc:1,-1,-1//Type:49#Loc:-1,-1,-1//Type:49#Loc:0,-1,1//Type:49#Loc:1,-1,1"
                + "//Type:49#Loc:-1,-1,1//Type:98#Loc:-2,0,2//Type:98#Loc:-1,0,2//Type:98#Loc:1,0,2//Type:98#Loc:2,0,2"
                + "//Type:98#Loc:-2,0,1//Type:98#Loc:-2,0,0//Type:98#Loc:-2,0,-1//Type:98#Loc:-2,0,-2//Type:98#Loc:-1,0,-2"
                + "//Type:98#Loc:0,0,-2//Type:98#Loc:1,0,-2//Type:98#Loc:2,0,-2//Type:98#Loc:2,0,1//Type:98#Loc:2,0,0//Type:98#Loc:2,0,-1"
                + "//Type:98#Loc:-2,1,2//Type:98#Loc:-2,2,2//Type:98#Loc:-2,3,2//Type:98#Loc:-2,1,-2//Type:98#Loc:-2,2,-2//Type:98#Loc:-2,3,-2"
                + "//Type:98#Loc:2,1,2//Type:98#Loc:2,2,2//Type:98#Loc:2,3,2//Type:98#Loc:2,1,-2//Type:98#Loc:2,2,-2//Type:98#Loc:2,3,-2"
                + "//Type:98#Loc:-2,3,1//Type:98#Loc:-2,3,0//Type:98#Loc:-2,3,-1//Type:98#Loc:-1,1,2//Type:98#Loc:-1,2,2//Type:98#Loc:-1,3,2"
                + "//Type:101#Loc:1,1,-2//Type:101#Loc:1,2,-2//Type:98#Loc:1,3,-2//Type:98#Loc:0,2,2//Type:98#Loc:0,3,2//Type:98#Loc:1,1,2"
                + "//Type:98#Loc:1,2,2//Type:98#Loc:1,3,2//Type:101#Loc:0,1,-2//Type:101#Loc:0,2,-2//Type:98#Loc:0,3,-2//Type:101#Loc:-1,1,-2"
                + "//Type:101#Loc:-1,2,-2//Type:98#Loc:-1,3,-2//Type:101#Loc:-1,1,-2//Type:101#Loc:-2,2,-1//Type:101#Loc:-2,1,0//Type:101#Loc:-2,2,0"
                + "//Type:101#Loc:-2,1,-1//Type:101#Loc:-2,1,1//Type:101#Loc:-2,2,1//Type:101#Loc:2,1,-1//Type:101#Loc:2,2,-1//Type:101#Loc:2,1,0"
                + "//Type:101#Loc:2,2,0//Type:101#Loc:2,1,1//Type:101#Loc:2,2,1//Type:98#Loc:2,3,-1//Type:98#Loc:2,3,0//Type:98#Loc:2,3,1"
                + "//Type:98#Loc:-1,4,-1//Type:98#Loc:0,4,-1//Type:98#Loc:1,4,-1//Type:98#Loc:-1,4,0//Type:98#Loc:0,4,0//Type:98#Loc:1,4,0"
                + "//Type:98#Loc:-1,4,1//Type:98#Loc:0,4,1//Type:98#Loc:1,4,1//Type:diamond_block#Loc:1,0,-1//Type:diamond_block#Loc:1,0,1"
                + "//Type:diamond_block#Loc:-1,0,-1//Type:diamond_block#Loc:-1,0,1");
        file.addDefault("CustomStructures.Chances", 100);
        file.addDefault("CustomStructures.Structures", cs);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (IOException e) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


    public static void load4(FileConfiguration file, File fileF) {
        file.addDefault("SplitSymbol", "#");
        List<String> lucks = new ArrayList<String>();
        file.options().header("Lucky Block 200+ Luck Options.");
        lucks.add("Chance:1-20#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:21-25#Type:Enchant#Title:&9Enchant Item in Hand#Luck:Good");
        lucks.add("Chance:26-30#Type:Repair#Title:&cRepair Items#Luck:Good");
        lucks.add("Chance:31-35#Type:XP#Title:&aXP#Amount:280-340#Luck:Good");
        lucks.add("Chance:36-40#Type:Fireworks#Title:&3Fireworks#Luck:Good");
        lucks.add("Chance:41-70#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:71-75#Type:Gift#Title:&dGift#Luck:Good");
        lucks.add("Chance:76-80#Type:Remove Harmful Effects#Title:&5Remove Harmful Effects#Luck:Good");
        lucks.add("Chance:81-88#Type:Feed#Title:&6Feed#Luck:Good");
        lucks.add("Chance:89-95#Type:Heal#Title:&aHeal#Luck:Good");
        lucks.add("Chance:96-100#Type:Wolves or Ocelots#Title:&2Wolves or Ocelots#Luck:Good");
        lucks.add("Chance:101-110#Type:Added Item#Title:&5Added Item#Luck:Good");
        lucks.add("Chance:111-120#Type:Custom Structure#Title:&dCustom Structure#Luck:Try your luck");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 120);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:diamond_sword#DisplayName:&eLucky Sword#Enchants:16 3-4%%19 2-3");
        s.add("Chance:11-30#type:diamond_pickaxe");
        s.add("Chance:31-40#type:diamond_axe");
        s.add("Chance:41-45#type:diamond_helmet//type:diamond_chestplate//typeid:312/313");
        s.add("Chance:46-50#type:cooked_chicken#amount:8-16//type:cooked_beef#amount:8-16");
        s.add("Chance:51-65#type:chest#data:0#hasItemMeta:true#DisplayName:&a&l&oRandom Chest#hasLore:true#Lore:&7Place It%%&7Random Chest");
        s.add("Chance:66-70#type:golden_apple#amount:3-6//type:golden_apple#amount:1-2#data:1");
        s.add("Chance:71-75#type:gold_ingot#amount:3-6//type:diamond#amount:2-4//type:iron_ingot#amount:4-6//type:emerald#amount:2-4");
        s.add("Chance:76-80#type:ender_pearl#amount:6");
        s.add("Chance:81-90#type:monster_egg#amount:2-5#data:90-93#tries:4-5");
        s.add("Chance:91-100#typeid:417-419#tries:1-3");
        file.addDefault("DroppedItems.Chances", 100);
        file.addDefault("DroppedItems.Items", s);
        List<String> ai = new ArrayList<String>();
        ai.add("Chance:1-40#Title:&3Lucky Block Taker#type:arrow#DisplayName:&8&lLucky Block Taker#Lore:&7Right Click to use#Message:"
                + "&aAdded (&8&lLucky Block Taker&a) To your Inventory!");
        ai.add("Chance:41-70#Title:&4Exploding Block#type:stained_clay#amount:15-30#data:0-15#DisplayName:&4Exploding!#Lore:&7Place It#"
                + "Message:&5Added Exploding Blocks To your Inventory!");
        ai.add("Chance:71-100#Title:&fCake#type:cake#Message:&aGiving Cake");
        file.addDefault("AddedItems.Chances", 100);
        file.addDefault("AddedItems.Items", ai);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:8-14");
        chest.add("Chances:250");
        chest.add("Chance:1-10#type:%lb%1#DisplayName:&eLucky Block#amount:6-16");
        chest.add("Chance:11-20#type:bow#Enchants:48-52 1-4%%48-52 1-4");
        chest.add("Chance:21-30#type:stick#DisplayName:&6Gun II#Lore:&4Deals 5.5 Damage#amount:6-16");
        chest.add("Chance:31-35#type:stick#DisplayName:&6Gun III#Lore:&4Deals 9 Damage#amount:4-6");
        chest.add("Chance:36-60#type:gold_ingot#amount:4-6");
        chest.add("Chance:61-78#type:coal_block#amount:4-8");
        chest.add("Chance:79-89#type:arrow#amount:16");
        chest.add("Chance:90-91#type:diamond#amount:3-5");
        chest.add("Chance:92-94#type:iron_ingot#amount:4-10");
        chest.add("Chance:95-99#type:iron_sword");
        chest.add("Chance:100-102#type:golden_apple#amount:1-2#data:1");
        chest.add("Chance:103-113#type:water_bucket");
        chest.add("Chance:114-124#type:lava_bucket");
        chest.add("Chance:125-134#type:snow_ball#amount:10-16");
        chest.add("Chance:135-137#type:raw_fish#amount:3-6#data:0-2#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:138-149#type:ender_pearl#amount:2-3");
        chest.add("Chance:150-152#type:bread#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:153-155#type:carrot_item#amount:2-6#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:156-160#type:exp_bottle#amount:3-7");
        chest.add("Chance:161-163#type:mushroom_soup#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:164-168#type:wood#amount:24-32");
        chest.add("Chance:169-171#type:fishing_rod#Enchants:34 1-2");
        chest.add("Chance:172-174#type:melon#amount:2-8#DisplayName:&6&lFast Food!#Lore:&7Right Click to eat");
        chest.add("Chance:175-179#type:iron_helmet");
        chest.add("Chance:180-182#type:iron_chestplate");
        chest.add("Chance:183-186#type:iron_leggings");
        chest.add("Chance:187-192#type:iron_boots");
        chest.add("Chance:193-195#type:emerald#amount:1-3#DisplayName:&7Wolf Tool");
        chest.add("Chance:196-200#type:web#amount:3-6");
        chest.add("Chance:201-204#type:%LuckyBow%");
        chest.add("Chance:205-209#type:%LuckySword%");
        chest.add("Chance:210-215#type:%LuckyBoots%");
        chest.add("Chance:216-219#type:%LuckyLeggings%");
        chest.add("Chance:220-224#type:%LuckyHelmet%");
        chest.add("Chance:225-227#type:%LuckyChestplate%");
        chest.add("Chance:228-230#type:%LuckyTool%");
        chest.add("Chance:231-235#type:%ThorAxe%");
        chest.add("Chance:236-240#type:%LuckyShovel%");
        chest.add("Chance:241-245#type:%LuckyAxe%");
        chest.add("Chance:246-250#type:%LuckyPickaxe%");
        file.addDefault("Chest.Items.Chest", chest);
        List<String> chest1 = new ArrayList<String>();
        chest1.add("Times:8-14");
        chest1.add("Chances:90");
        chest1.add("Chance:1-20#type:glass_bottle#amount:1-3");
        chest1.add("Chance:21-35#type:potion");
        chest1.add("Chance:36-37#type:nether_star");
        chest1.add("Chance:38-40#type:quartz");
        chest1.add("Chance:41-45#type:magma_cream#amount:1-3");
        chest1.add("Chance:46-50#type:golden_carrot#amount:1-2");
        chest1.add("Chance:51-55#type:speckled_melon#amount:1-2");
        chest1.add("Chance:56-60#type:blaze_rod#amount:1-2");
        chest1.add("Chance:61-65#type:gold_nugget#amount:1-4");
        chest1.add("Chance:66-70#type:nether_warts#amount:1-5");
        chest1.add("Chance:71-75#type:sulphur#amount:1-2");
        chest1.add("Chance:76-80#type:glowstone_dust#amount:2-4");
        chest1.add("Chance:81-90#type:redstone#amount:1-3");
        file.addDefault("Chest.Items.Chest1", chest1);
        List<String> chest2 = new ArrayList<String>();
        chest2.add("Times:10-14");
        chest2.add("Chances:175");
        chest2.add("Chance:1-20#type:gold_nugget#amount:1-6");
        chest2.add("Chance:21-39#type:coal#amount:1-3");
        chest2.add("Chance:39-46#type:quartz#amount:1-4");
        chest2.add("Chance:47-63#type:ink_sack#amount:1-4#data:4");
        chest2.add("Chance:64-80#type:iron_ingot#amount:1-3");
        chest2.add("Chance:81-96#type:gold_ingot#amount:1-3");
        chest2.add("Chance:97-111#type:diamond#amount:1-2");
        chest2.add("Chance:112-126#type:emerald#amount:1-2");
        chest2.add("Chance:127-137#type:quartz_block");
        chest2.add("Chance:138-148#type:lapis_block");
        chest2.add("Chance:149-158#type:iron_block");
        chest2.add("Chance:159-166#type:gold_block");
        chest2.add("Chance:167-171#type:diamond_block");
        chest2.add("Chance:172-175#type:emerald_block");
        file.addDefault("Chest.Items.Chest2", chest2);
        List<String> chest3 = new ArrayList<String>();
        chest3.add("Times:6-8");
        chest3.add("Chances:93");
        chest3.add("Chance:1-10#typeid:306/307/308/309");
        chest3.add("Chance:11-20#type:blaze_powder#amount:1-3#DisplayName:&cshoot#Lore:&7Right Click to shoot");
        chest3.add("Chance:21-40#type:cookie#amount:1-5#DisplayName:&6Super Cookie#Lore:&eKick Them all%%&9{playername}");
        chest3.add("Chance:41-50#type:lever#amount:2-3#DisplayName:&8C4 Item#Enchants:34 3#&c{playername}");
        chest3.add("Chance:51-52#type:chest#DisplayName:&6(BackPack)#Lore:{randomcolor}{randomcolor}{randomnumber}{playername}%%&79");
        chest3.add("Chance:53-60#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb");
        chest3.add("Chance:61-64#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range I");
        chest3.add("Chance:65-67#type:slime_ball#amount:1-3#Enchants:34 10#DisplayName:&2Timed Bomb#Lore:&7Range II");
        chest3.add("Chance:68-73#type:diamond_helmet");
        chest3.add("Chance:74-77#type:diamond_chestplate");
        chest3.add("Chance:78-82#type:diamond_leggings");
        chest3.add("Chance:83-89#type:diamond_boots");
        chest3.add("Chance:90-93#type:name_tag#DisplayName:&eLucky Block Tool#Lore:&8Made in China");
        file.addDefault("Chest.Items.Chest2", chest3);
        List<String> cs = new ArrayList<String>();
        cs.add("Chance:1-50#LocationType:Player#Type:57#Loc:0,-1,0//Type:57#Loc:1,-1,0//Type:57#Loc:-1,-1,0//"
                + "Type:57#Loc:0,-1,-1//Type:57#Loc:1,-1,-1//Type:57#Loc:-1,-1,-1//Type:57#Loc:0,-1,1//Type:57#Loc:1,-1,1"
                + "//Type:57#Loc:-1,-1,1//Type:98#Loc:-2,0,2//Type:98#Loc:-1,0,2//Type:98#Loc:1,0,2//Type:98#Loc:2,0,2"
                + "//Type:98#Loc:-2,0,1//Type:98#Loc:-2,0,0//Type:98#Loc:-2,0,-1//Type:98#Loc:-2,0,-2//Type:98#Loc:-1,0,-2"
                + "//Type:98#Loc:0,0,-2//Type:98#Loc:1,0,-2//Type:98#Loc:2,0,-2//Type:98#Loc:2,0,1//Type:98#Loc:2,0,0//Type:98#Loc:2,0,-1"
                + "//Type:98#Loc:-2,1,2//Type:98#Loc:-2,2,2//Type:98#Loc:-2,3,2//Type:98#Loc:-2,1,-2//Type:98#Loc:-2,2,-2//Type:98#Loc:-2,3,-2"
                + "//Type:98#Loc:2,1,2//Type:98#Loc:2,2,2//Type:98#Loc:2,3,2//Type:98#Loc:2,1,-2//Type:98#Loc:2,2,-2//Type:98#Loc:2,3,-2"
                + "//Type:98#Loc:-2,3,1//Type:98#Loc:-2,3,0//Type:98#Loc:-2,3,-1//Type:98#Loc:-1,1,2//Type:98#Loc:-1,2,2//Type:98#Loc:-1,3,2"
                + "//Type:101#Loc:1,1,-2//Type:101#Loc:1,2,-2//Type:98#Loc:1,3,-2//Type:98#Loc:0,2,2//Type:98#Loc:0,3,2//Type:98#Loc:1,1,2"
                + "//Type:98#Loc:1,2,2//Type:98#Loc:1,3,2//Type:101#Loc:0,1,-2//Type:101#Loc:0,2,-2//Type:98#Loc:0,3,-2//Type:101#Loc:-1,1,-2"
                + "//Type:101#Loc:-1,2,-2//Type:98#Loc:-1,3,-2//Type:101#Loc:-1,1,-2//Type:101#Loc:-2,2,-1//Type:101#Loc:-2,1,0//Type:101#Loc:-2,2,0"
                + "//Type:101#Loc:-2,1,-1//Type:101#Loc:-2,1,1//Type:101#Loc:-2,2,1//Type:101#Loc:2,1,-1//Type:101#Loc:2,2,-1//Type:101#Loc:2,1,0"
                + "//Type:101#Loc:2,2,0//Type:101#Loc:2,1,1//Type:101#Loc:2,2,1//Type:98#Loc:2,3,-1//Type:98#Loc:2,3,0//Type:98#Loc:2,3,1"
                + "//Type:98#Loc:-1,4,-1//Type:98#Loc:0,4,-1//Type:98#Loc:1,4,-1//Type:98#Loc:-1,4,0//Type:98#Loc:0,4,0//Type:98#Loc:1,4,0"
                + "//Type:98#Loc:-1,4,1//Type:98#Loc:0,4,1//Type:98#Loc:1,4,1");
        cs.add("Chance:51-100#LocationType:Player#Type:41#Loc:-1,-1,0//Type:41#Loc:-1,-1,1//Type:41#Loc:-1,-1,-1//Type:41#Loc:0,-1,0//"
                + "Type:41#Loc:0,-1,-1//Type:41#Loc:0,-1,0//Type:41#Loc:0,-1,1//Type:41#Loc:1,-1,-1//Type:41#Loc:1,-1,0//Type:41#Loc:1,-1,1//"
                + "Type:133#Loc:-1,0,-1//Type:133#Loc:-1,0,1//Type:133#Loc:1,0,-1//Type:133#Loc:1,0,1");
        file.addDefault("CustomStructures.Chances", 100);
        file.addDefault("CustomStructures.Structures", cs);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (IOException e) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


    public static void load5(FileConfiguration file, File fileF) {
        file.addDefault("SplitSymbol", "#");
        file.options().header("Lucky Block -1 to -50 Luck Options.");
        List<String> lucks = new ArrayList<String>();
        lucks.add("Chance:1-10#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:11-25#Type:Mob#Title:&2Mobs#Luck:Try your luck");
        lucks.add("Chance:26-30#Type:Lava#Title:&6Lava#Luck:Bad");
        lucks.add("Chance:31-35#Type:Villager#Title:&9Villager#Luck:Bad");
        lucks.add("Chance:36-40#Type:Splash Potion#Title:&1Splash Potion#Luck:Try your luck");
        lucks.add("Chance:41-45#Type:Primed Tnt#Title:&4Primed Tnt#Luck:Bad");
        lucks.add("Chance:46-50#Type:Lightning#Title:&bLightning#Luck:Bad");
        lucks.add("Chance:51-60#Type:Fake Diamond#Title:&bFake Diamond#Luck:Bad");
        lucks.add("Chance:61-70#Type:Dirt#Title:&6Dirt#Luck:Bad");
        lucks.add("Chance:71-75#Type:Firework#Title:&3Firework#Luck:Try your luck");
        lucks.add("Chance:76-90#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:91-100#Type:Stuck#Title:&7Stuck#Luck:Bad");
        lucks.add("Chance:101-105#Type:Damage#Title:&cDamage#Luck:Bad");
        lucks.add("Chance:106-110#Type:Slimes#Title:&2Slimes#Luck:Bad");
        lucks.add("Chance:111-112#Type:Meteors#Title:&8Meteors#Luck:Very Bad");
        lucks.add("Chance:113#Type:FlyingLB#Title:&eFlying Lucky Block#Luck:Good");
        lucks.add("Chance:114-120#Type:Soldier#Title:&1&lSoldier#Luck:Bad");
        lucks.add("Chance:121-125#Type:SIGN#Text:&cHello!,&dHow are you?#Title:&eSign#Luck:Bad");
        lucks.add("Chance:126-130#Type:Tree#TreeType:tree#Title:&aTree#Luck:Good");
        lucks.add("Chance:131-140#Type:Changeable Block#Title:&4Changeable Block#Luck:Good");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 140);
        List<String> mobs = new ArrayList<String>();
        mobs.add("Chance:1-5#Type:zombie#CustomName:&4&lLucky Zombie#CustomNameVisible:true#maxHealth:200#Health:200"
                + "#Equipments:typeid:276,Enchants:16 5>>typeid:310,Enchants:0 4>>typeid:311,Enchants:0 4>>typeid:312,Enchants:0 4>>typeid:313,Enchants:0 4");
        mobs.add("Chance:6-12#Type:creeper#CustomName:&a&lLucky Creeper#CustomNameVisible:true#maxHealth:100#Health:100#Powered:true");
        mobs.add("Chance:13-20#Type:sheep#CustomName:&e&lLucky Sheep &4&l&k@@@#CustomNameVisible:true#maxHealth:200#Health:200#Color:0-15");
        mobs.add("Chance:21-30#Type:wolf#Tamed:true#Owner:{playername}#CollarColor:0-15#ParticleEffects:name:heart"
                + ",rx:0.3,ry:0.3,rz:0.3,speed:0,amount:10,range:64");
        mobs.add("Chance:31-40#Tries:8-16#Type:squid#maxHealth:30-60#Health:%maxHealth%#CustomName:&eLucky Squid &c%health%&f/&c%maxhealth%");
        file.addDefault("Entities.Chances", 40);
        file.addDefault("Entities.Mobs", mobs);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:flint_and_steel");
        s.add("Chance:11-20#type:gold_chestplate");
        s.add("Chance:21-40#type:cooked_chicken#amount:2-3");
        s.add("Chance:41-50#type:bone#amount:2-3");
        s.add("Chance:51-60#type:rotten_flesh");
        s.add("Chance:61-65#type:ender_pearl#amount:2-5");
        s.add("Chance:66-70#typeid:298/299/300/301/302/303/304/305#tries:3-7");
        s.add("Chance:71-80#typeid:37#amount:3-9//typeid:38#amount:2-6//typeid:38#amount:2-6#data:1//typeid:38#amount:2-6#data:2//typeid:38#amount:2-6#data:3"
                + "//typeid:38#amount:2-6#data:4//typeid:38#amount:2-6#data:5//typeid:38#amount:2-6#data:4//typeid:38#amount:2-6#data:6//typeid:38#amount:2-6#data:7"
                + "//typeid:38#amount:2-6#data:8");
        s.add("Chance:81-90#type:raw_fish#data:0#amount:2-5//type:raw_fish#data:1#amount:2-5//type:raw_fish#data:2#amount:2-5//"
                + "type:raw_fish#data:3#amount:2-5//type:cooked_fish#data:0#amount:2-5//type:cooked_fish#data:1#amount:2-5");
        s.add("Chance:91-100#typeid:325/326/327#tries:1-3");
        file.addDefault("DroppedItems.Chances", 100);
        file.addDefault("DroppedItems.Items", s);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:6-10");
        chest.add("Chances:160");
        chest.add("Chance:1-10#type:torch#amount:5-11");
        chest.add("Chance:11-20#type:workbench");
        chest.add("Chance:21-25#type:gold_ingot#amount:3-6");
        chest.add("Chance:26-35#type:coal#amount:1-6");
        chest.add("Chance:36-40#type:bow#Enchants:48-52 1-3");
        chest.add("Chance:41-55#type:arrow#amount:10-17");
        chest.add("Chance:56-60#type:iron_ingot#amount:2-5");
        chest.add("Chance:61-70#type:stone_sword");
        chest.add("Chance:71-80#type:snow_ball#amount:6");
        chest.add("Chance:81-90#type:bread#amount:2-6");
        chest.add("Chance:91-100#type:carrot_item#amount:2-6");
        chest.add("Chance:101-110#type:mushroom_soup");
        chest.add("Chance:111-120#type:log#amount:4-8");
        chest.add("Chance:121-130#type:melon#amount:2-8");
        chest.add("Chance:131-140#type:iron_helmet");
        chest.add("Chance:141-150#type:chainmail_leggings");
        chest.add("Chance:151-160#type:gold_boots");
        file.addDefault("Chest.Items.Chest", chest);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (IOException e) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


    public static void load6(FileConfiguration file, File fileF) {
        file.addDefault("SplitSymbol", "#");
        file.options().header("Lucky Block -51 to -100 Luck Options.");
        List<String> lucks = new ArrayList<String>();
        lucks.add("Chance:1-5#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:6-30#Type:Mob#Title:&2Mobs#Luck:Try your luck");
        lucks.add("Chance:31-35#Type:Lava#Title:&6Lava#Luck:Bad");
        lucks.add("Chance:36-38#Type:Villager#Title:&9Villager#Luck:Bad");
        lucks.add("Chance:39-45#Type:Primed Tnt#Title:&4Primed Tnt#Luck:Bad");
        lucks.add("Chance:46-50#Type:Lightning#Title:&bLightning#Luck:Bad");
        lucks.add("Chance:51-60#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:61-65#Type:Stuck#Title:&7Stuck#Luck:Bad");
        lucks.add("Chance:66-70#Type:Damage#Title:&cDamage#Luck:Bad");
        lucks.add("Chance:71-75#Type:Slimes#Title:&2Slimes#Luck:Bad");
        lucks.add("Chance:76-79#Type:Meteors#Title:&8Meteors#Luck:Very Bad");
        lucks.add("Chance:80-90#Type:Soldier#Title:&1&lSoldier#Luck:Bad");
        lucks.add("Chance:91-100#Type:Custom Structure#Title:&4Structure#Luck:Try your luck");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 100);
        List<String> mobs = new ArrayList<String>();
        mobs.add("Chance:1-5#Type:zombie#CustomName:&4&lLucky Zombie#CustomNameVisible:true#maxHealth:250#Health:250"
                + "#Equipments:typeid:276,Enchants:16 5>>typeid:310,Enchants:0 4>>typeid:311,Enchants:0 4>>typeid:312,Enchants:0 4>>typeid:313,Enchants:0 4");
        mobs.add("Chance:6-12#Type:creeper#CustomName:&a&lLucky Creeper#CustomNameVisible:true#maxHealth:200#Health:200#Powered:true");
        mobs.add("Chance:13-20#Tries:8-16#Type:squid#maxHealth:30-60#Health:%maxHealth%#CustomName:&eLucky Squid &c%health%&f/&c%maxhealth%");
        mobs.add("Chance:21-24#Type:wither");
        mobs.add("Chance:25-30#Tries:5-10#Type:silverfish");
        mobs.add("Chance:31-40#Tries:8#Type:zombie");
        mobs.add("Chance:41-50#Tries:5#Type:blaze");
        file.addDefault("Entities.Chances", 50);
        file.addDefault("Entities.Mobs", mobs);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:pumpkin_seeds");
        s.add("Chance:11-20#type:gold_chestplate");
        s.add("Chance:21-30#type:cooked_chicken#amount:2-3");
        s.add("Chance:31-50#type:bone#amount:2-3");
        s.add("Chance:51-70#type:rotten_flesh");
        s.add("Chance:71-80#type:ender_pearl#amount:2-5");
        s.add("Chance:81-90#type:iron_ingot");
        s.add("Chance:91-100#type:stone_sword");
        file.addDefault("DroppedItems.Chances", 100);
        file.addDefault("DroppedItems.Items", s);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:6-10");
        chest.add("Chances:160");
        chest.add("Chance:1-10#type:torch#amount:5-11");
        chest.add("Chance:11-20#type:workbench");
        chest.add("Chance:21-25#type:gold_ingot#amount:1-4");
        chest.add("Chance:26-35#type:coal#amount:1-4");
        chest.add("Chance:36-40#type:bow#Enchants:48-52 1-3");
        chest.add("Chance:41-55#type:arrow#amount:10-17");
        chest.add("Chance:56-60#type:iron_ingot#amount:2-5");
        chest.add("Chance:61-70#type:stone_sword");
        chest.add("Chance:71-80#type:snow_ball#amount:6");
        chest.add("Chance:81-90#type:bread#amount:2-6");
        chest.add("Chance:91-100#type:carrot_item#amount:2-3");
        chest.add("Chance:101-110#type:mushroom_soup");
        chest.add("Chance:111-120#type:log#amount:4-8");
        chest.add("Chance:121-130#type:melon#amount:2-8");
        chest.add("Chance:131-140#type:iron_helmet");
        chest.add("Chance:141-150#type:chainmail_leggings");
        chest.add("Chance:151-160#type:gold_boots");
        file.addDefault("Chest.Items.Chest", chest);
        List<String> cs = new ArrayList<String>();
        cs.add("Chance:1-50#LocationType:Player#Type:10#Loc:0,3,0//Type:10#Loc:1,3,0//Type:10#Loc:0,3,1//Type:10#Loc:-1,3,0//Type:10#Loc:0,3,-1"
                + "//Type:10#Loc:-1,3,-1//Type:10#Loc:1,3,-1//Type:10#Loc:1,3,1//Type:10#Loc:-1,3,1");
        cs.add("Chance:51-100#LocationType:Player#Type:7#Loc:0,0,0//Type:7#Loc:0,1,0//Type:7#Loc:1,0,0//Type:7#Loc:-1,0,0//Type:7#Loc:0,0,1//Type:7#Loc:"
                + "0,0,-1//Type:7#Loc:1,1,0//Type:7#Loc:-1,1,0//Type:7#Loc:0,1,-1//Type:7#Loc:0,1,1//Type:7#Loc:-1,1,-1//Type:7#Loc:1,1,1"
                + "//Type:7#Loc:-1,0,-1//Type:7#Loc:1,0,-1//Type:7#Loc:1,1,-1//Type:7#Loc:1,0,1//Type:7#Loc:-1,0,1//Type:7#Loc:-1,1,1");
        file.addDefault("CustomStructures.Chances", 100);
        file.addDefault("CustomStructures.Structures", cs);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (IOException e) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


    public static void load7(FileConfiguration file, File fileF) {
        file.addDefault("SplitSymbol", "#");
        file.options().header("Lucky Block -101 to ... Luck Options.");
        List<String> lucks = new ArrayList<String>();
        lucks.add("Chance:1-4#Type:Chest#Title:&6Chest#Luck:Good");
        lucks.add("Chance:5-30#Type:Mob#Title:&2Mobs#Luck:Try your luck");
        lucks.add("Chance:31-40#Type:Lava#Title:&6Lava#Luck:Bad");
        lucks.add("Chance:41-50#Type:Villager#Title:&9Villager#Luck:Bad");
        lucks.add("Chance:51-55#Type:Primed Tnt#Title:&4Primed Tnt#Luck:Bad");
        lucks.add("Chance:56-58#Type:Dropped Items#Title:&dDropped Items#Luck:Good");
        lucks.add("Chance:59-70#Type:Meteors#Title:&8Meteors#Luck:Very Bad");
        lucks.add("Chance:71-80#Type:Soldier#Title:&1&lSoldier#Luck:Bad");
        lucks.add("Chance:81-100#Type:Custom Structure#Title:&4Structure#Luck:Try your luck");
        file.addDefault("Luck.Values", lucks);
        file.addDefault("Luck.Chances", 100);
        List<String> mobs = new ArrayList<String>();
        mobs.add("Chance:1-5#Tries:1-4#Type:zombie#CustomName:&4&lLucky Zombie#CustomNameVisible:true#maxHealth:500#Health:500"
                + "#Equipments:typeid:276,Enchants:16 10>>typeid:310,Enchants:0 10>>typeid:311,Enchants:0 10>>typeid:312,Enchants:0 10>>typeid:313,Enchants:0 10");
        mobs.add("Chance:6-12#Tries:1-3#Type:creeper#CustomName:&a&lLucky Creeper#CustomNameVisible:true#maxHealth:500#Health:500#Powered:true");
        mobs.add("Chance:21-24#Type:wither#CustomName:&e&lLucky Wither#CustomNameVisible:true#maxHealth:1000#Health:1000");
        mobs.add("Chance:25-30#Tries:15-30#Type:silverfish#maxHealth:100#Health:100");
        mobs.add("Chance:31-40#Tries:10-25#Type:zombie");
        mobs.add("Chance:41-50#Tries:10-25#Type:blaze");
        if (LuckyBlock.bukkitVersion[1] > 7) {
            mobs.add("Chance:51-60Tries:5-10#Type:guardian");
        }
        mobs.add("Chance:61-70#Tries:5-10#Type:irongolem#Target%player%");
        mobs.add("Chance:71-80#Tries:10-25#Type:skeleton");
        mobs.add("Chance:81-90#Tries:10-20#Type:slime#Size:5");
        mobs.add("Chance:91-100#Tries:5-10#Type:witch");
        file.addDefault("Entities.Chances", 100);
        file.addDefault("Entities.Mobs", mobs);
        List<String> s = new ArrayList<String>();
        s.add("Chance:1-10#type:pumpkin_seeds");
        s.add("Chance:11-20#type:gold_chestplate");
        s.add("Chance:21-30#type:cooked_chicken#amount:2-3");
        s.add("Chance:31-50#type:bone#amount:2-3");
        s.add("Chance:51-70#type:rotten_flesh");
        s.add("Chance:71-80#type:ender_pearl#amount:2-5");
        s.add("Chance:81-90#type:iron_ingot");
        s.add("Chance:91-100#type:stone_sword");
        file.addDefault("DroppedItems.Chances", 100);
        file.addDefault("DroppedItems.Items", s);
        List<String> chest = new ArrayList<String>();
        chest.add("Times:6-10");
        chest.add("Chances:160");
        chest.add("Chance:1-10#type:torch#amount:5-11");
        chest.add("Chance:11-20#type:workbench");
        chest.add("Chance:21-25#type:gold_ingot#amount:1-4");
        chest.add("Chance:26-35#type:coal#amount:1-4");
        chest.add("Chance:36-40#type:bow#Enchants:48-52 1-3");
        chest.add("Chance:41-55#type:arrow#amount:10-17");
        chest.add("Chance:56-60#type:iron_ingot#amount:2-5");
        chest.add("Chance:61-70#type:stone_sword");
        chest.add("Chance:71-80#type:snow_ball#amount:6");
        chest.add("Chance:81-90#type:bread#amount:2-6");
        chest.add("Chance:91-100#type:carrot_item#amount:2-3");
        chest.add("Chance:101-110#type:mushroom_soup");
        chest.add("Chance:111-120#type:log#amount:4-8");
        chest.add("Chance:121-130#type:melon#amount:2-8");
        chest.add("Chance:131-140#type:iron_helmet");
        chest.add("Chance:141-150#type:chainmail_leggings");
        chest.add("Chance:151-160#type:gold_boots");
        file.addDefault("Chest.Items.Chest", chest);
        List<String> cs = new ArrayList<String>();
        cs.add("Chance:1-50#LocationType:Player#Type:10#Loc:0,3,0//Type:10#Loc:1,3,0//Type:10#Loc:0,3,1//Type:10#Loc:-1,3,0//Type:10#Loc:0,3,-1"
                + "//Type:10#Loc:-1,3,-1//Type:10#Loc:1,3,-1//Type:10#Loc:1,3,1//Type:10#Loc:-1,3,1");
        cs.add("Chance:51-100#LocationType:Player#Type:7#Loc:0,0,0//Type:7#Loc:0,1,0//Type:7#Loc:1,0,0//Type:7#Loc:-1,0,0//Type:7#Loc:0,0,1//Type:7#Loc:"
                + "0,0,-1//Type:7#Loc:1,1,0//Type:7#Loc:-1,1,0//Type:7#Loc:0,1,-1//Type:7#Loc:0,1,1//Type:7#Loc:-1,1,-1//Type:7#Loc:1,1,1"
                + "//Type:7#Loc:-1,0,-1//Type:7#Loc:1,0,-1//Type:7#Loc:1,1,-1//Type:7#Loc:1,0,1//Type:7#Loc:-1,0,1//Type:7#Loc:-1,1,1");
        file.addDefault("CustomStructures.Chances", 100);
        file.addDefault("CustomStructures.Structures", cs);
        file.options().copyDefaults(true);
        try {
            file.save(fileF);
        } catch (IOException e) {
            LuckyBlock.instance.getLogger().info("Error with saving " + file.getName() + " perhaps you wrote something wrong.");
        }
    }


}
