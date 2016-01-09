package com.LuckyBlock.logic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Resources.SchedulerTask;

public class SoundPlayer {
	
	
	private List<SoundFile> sounds = new ArrayList<SoundFile>();
	
	
	public SoundPlayer(List<SoundFile> sounds){
	this.sounds = sounds;
	}
	
	public List<SoundFile> getSounds(){
	return sounds;
	}
	
	public void play(final int times, int delay, final Location loc){
	final SchedulerTask task = new SchedulerTask();
	task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable(){
	int timer = times;
	@Override
	public void run(){
	if(timer > 0){
	for(int x = 0; x < sounds.size(); x++){
	sounds.get(x).play(loc);
	timer--;
	}
	} else {
	task.run();
	}
	}
	}, 0, delay));
	}
	
	public void play(final int times, int delay, final Player player){
	final SchedulerTask task = new SchedulerTask();
	task.setId(LuckyBlock.instance.getServer().getScheduler().scheduleSyncRepeatingTask(LuckyBlock.instance, new Runnable(){
	int timer = times;
	@Override
	public void run(){
	if(timer > 0){
	for(int x = 0; x < sounds.size(); x++){
	sounds.get(x).play(player);
	timer--;
	}
	} else {
	task.run();
	}
	}
	}, 0, delay));
	}
	
	
	
	
}
