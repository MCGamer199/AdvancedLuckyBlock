package com.LuckyBlock.logic;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SoundFile {


    private Sound sound;
    private float pitch = 1;
    private float volume = 1;


    public SoundFile(Sound sound) {
        this.sound = sound;
    }

    public void play(Player player) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public void play(Location loc) {
        World world = loc.getWorld();
        world.playSound(loc, sound, volume, pitch);
    }

    public String getString() {
        return sound.toString();
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }


}
