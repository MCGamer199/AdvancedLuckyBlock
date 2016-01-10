package com.LuckyBlock.War;

import com.LuckyBlock.Resources.ParticleEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public enum Particle {

    NONE(null),

    REDSTONE(ParticleEffect.REDSTONE),

    FLAME(ParticleEffect.FLAME),

    LAVA(ParticleEffect.LAVA),

    HEART(ParticleEffect.HEART),

    PORTAL(ParticleEffect.PORTAL),

    SLIME(ParticleEffect.SLIME),

    SMOKE(ParticleEffect.SMOKE_NORMAL),

    LARGE_SMOKE(ParticleEffect.SMOKE_LARGE),

    CRIT(ParticleEffect.CRIT),

    NOTE(ParticleEffect.NOTE),

    SNOWBALL(ParticleEffect.SNOWBALL),

    SPELL(ParticleEffect.SPELL),

    VILLAGER_ANGRY(ParticleEffect.VILLAGER_ANGRY);

    public static HashMap<UUID, List<Particle>> unlocked = new HashMap<UUID, List<Particle>>();
    public static HashMap<UUID, Particle> selected = new HashMap<UUID, Particle>();
    private ParticleEffect effect;

    private Particle(ParticleEffect effect) {
        this.effect = effect;
    }

    public ParticleEffect getEffect() {
        return effect;
    }

    public void unlock(UUID uuid) {
        if (!unlocked.containsKey(uuid)) {
            unlocked.put(uuid, new ArrayList<Particle>());
        }
        unlocked.get(uuid).add(this);
    }

    public void select(UUID uuid) {
        selected.put(uuid, this);
    }


}
