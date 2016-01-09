package com.LuckyBlock.PluginEvents;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.LuckyBlock.War.War;

public class BlockBreakInGameEvent extends Event implements Cancellable {
	
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private boolean cancelled;
    private Block block;
    private War game;
	
	public BlockBreakInGameEvent(Block block, Player player, War war){
	this.block = block;
	this.player = player;
	game = war;
	}
		
	public boolean isCancelled(){
    return cancelled;
    }
	
    public void setCancelled(boolean cancel){
    cancelled = cancel;
    }
    
    public HandlerList getHandlers(){
    return handlers;
    }
    
    public Player getPlayer(){
    return player;
    }
    
    public Block getBlock(){
	return block;
	}
    
    public War getGame(){
	return game;
	}
    
    public static HandlerList getHandlerList(){
    return handlers;
    }
	
	
}
