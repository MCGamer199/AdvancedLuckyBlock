package com.LuckyBlock.enums;


public enum ShopItems {

    LUCKY_BOW(1),

    LUCKY_SWORD(2),

    ADVANCED_LUCKY_BLOCK_TOOL(8),

    LUCKY_AXE(11),

    LUCKY_PICKAXE(12),

    LUCKY_SHOVEL(13),

    LUCKY_HELMET(15),

    LUCKY_CHESTPLATE(16),

    LUCKY_LEGGINGS(17),

    LUCKY_BOOTS(18),

    FIRE_RESISTANCE_SKILL(25),

    HIGH_JUMP_SKILL(26),

    SATURATION_SKILL(27),

    WATER_BREATHING_SKILL(28),

    THOR_AXE(35),

    BLOCKS(39);

    private final int id;

    private ShopItems(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}
