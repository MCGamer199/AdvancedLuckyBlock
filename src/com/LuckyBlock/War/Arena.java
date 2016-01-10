package com.LuckyBlock.War;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    protected int id = 0;
    protected boolean enabled = true;
    protected int MaxPlayers = 12;
    protected int MinPlayers = (int) (MaxPlayers * 0.6);
    protected List<UUID> Players = new ArrayList<UUID>();
    protected boolean ingame = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getMaxPlayers() {
        return MaxPlayers;
    }

    public void setMaxPlayers(int number) {
        MaxPlayers = number;
    }

    public int getMinPlayers() {
        return MinPlayers;
    }

    public void setMinPlayers(int number) {
        MinPlayers = number;
    }

    public List<UUID> getPlayers() {
        return Players;
    }

    public void addPlayer(UUID uuid) {
        Players.add(uuid);
    }

    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }

    public boolean inGame() {
        return ingame;
    }


}
