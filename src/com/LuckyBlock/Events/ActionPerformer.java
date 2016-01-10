package com.LuckyBlock.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ActionPerformer {

    public static void perform(Actions action, String string, Player player, Block block) {
        String[] d = string.split(",");
        int value = 1;
        float f = 0;
        for (int x = 0; x < d.length; x++) {
            if (d[x].startsWith("Value:")) {
                value = Integer.parseInt(d[x].split("Value:")[1]);
            }
        }
        if (player != null) {
            if (action == Actions.SET_HEALTH) {
                player.setHealth(value);
            } else if (action == Actions.SET_MAX_HEALTH) {
                player.setMaxHealth(value);
            } else if (action == Actions.SET_FLY_SPEED) {
                player.setWalkSpeed(f);
            }
        }
    }

    public static enum Actions {
        SET_HEALTH("set_health"),
        SET_MAX_HEALTH("set_max_health"),
        SET_HUNGER("set_hunger"),
        SET_WALK_SPEED("set_walk_speed"),
        SET_FLY_SPEED("set_fly_speed"),
        SET_ALLOW_FLIGHT("set_allow_flight");
        private String name;

        private Actions(String name) {
            this.name = name;
        }

        public static Actions getByName(String name) {
            Actions ac = null;
            for (Actions a : values()) {
                if (a.getName().equalsIgnoreCase(name)) {
                    ac = a;
                }
            }
            return ac;
        }

        public String getName() {
            return name;
        }
    }


}
