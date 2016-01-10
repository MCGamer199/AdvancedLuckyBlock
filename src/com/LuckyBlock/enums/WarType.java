package com.LuckyBlock.enums;


public enum WarType {

    DEFAULT(0),

    VIP(1),

    INSANE(2),

    TEAMS(3),

    BREAK(4);

    private int id;

    private WarType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
