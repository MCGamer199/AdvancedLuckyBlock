package com.LuckyBlock.Resources;

import org.bukkit.Bukkit;

public class SchedulerTask implements Runnable {


private int id;


public int getId(){
return id;
}


public void setId(int id){
this.id = id;
}


@Override
public void run(){
// Do whatever
Bukkit.getScheduler().cancelTask(id);
}





}
