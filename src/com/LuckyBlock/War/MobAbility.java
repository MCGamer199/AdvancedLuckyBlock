package com.LuckyBlock.War;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MobAbility {

    public static List<MobAbility> mobs = new ArrayList<MobAbility>();
    private ListMobs mob;
    private List<UUID> players;
    private int id;
    public MobAbility(String pass, ListMobs mob, int id) {
        if (pass.equalsIgnoreCase("MCGamer199")) {
            this.mob = mob;
            this.id = id;
            mobs.add(this);
        } else {
            throw new Error("Incorrect password!");
        }
    }

    public static MobAbility getByUUID(UUID uuid) {
        MobAbility m = null;
        for (MobAbility a : mobs) {
            if (a.getPlayers().contains(uuid)) {
                m = a;
            }
        }
        return m;
    }

    public ListMobs getMob() {
        return mob;
    }

    public void addPlayer(UUID uuid) {
        players.add(uuid);
        reload();
    }

    public int getId() {
        return id;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public void removePlayer(UUID uuid) {
        players.remove(uuid);
        reload();
    }

    public void reload() {
        for (MobAbility m : mobs) {
            if (m.getId() == id) {
                mobs.remove(m);
            }
        }
        mobs.add(this);
    }

    public static enum MobAbilities {

        FLY(0),

        FIRE_RESISTANCE(1),

        EXPLODE(2),

        INFINITE_ARROWS(3),

        MOBS_FRIENDLY(4),

        WATER_BREATHING(5),

        POISON(6),

        SHOOT_FIREBALL(7),

        NO_FALL(8);

        private int id;

        private MobAbilities(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

    }

    public static enum ListMobs {

        BAT(MobAbilities.FLY),

        CREEPER(MobAbilities.EXPLODE),

        BLAZE(MobAbilities.FIRE_RESISTANCE),

        SQUID(MobAbilities.WATER_BREATHING),

        CAVE_SPIDER(MobAbilities.POISON),

        GHAST(MobAbilities.FLY, MobAbilities.SHOOT_FIREBALL),

        CHICKEN(MobAbilities.NO_FALL),

        ZOMBIE(MobAbilities.MOBS_FRIENDLY);

        private MobAbilities[] abilities = new MobAbilities[16];

        private ListMobs(MobAbilities... abilities) {
            this.abilities = abilities;
        }

        public MobAbilities[] getAbilities() {
            return abilities;
        }

        public List<MobAbilities> getAbilitiesList() {
            List<MobAbilities> list = new ArrayList<MobAbilities>();
            for (int x = 0; x < abilities.length; x++) {
                if (abilities[x] != null) {
                    list.add(abilities[x]);
                }
            }
            return list;
        }

    }


}
