package com.LuckyBlock.Inventory;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemMetadata {


    private ItemStack item;


    public ItemMetadata(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public void addMetadata(String name, String value) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.setString(name, value);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }

    public void addMetadata(String name, int value) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.setInt(name, value);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }

    public void addMetadata(String name, boolean value) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.setBoolean(name, value);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }

    public void addMetadata(String name, float value) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.setFloat(name, value);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }

    public void addMetadata(String name, double value) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.setDouble(name, value);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }

    public void addMetadata(String name, int[] value) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.setIntArray(name, value);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }

    public void addMetadata(String name, long value) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.setLong(name, value);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }

    public boolean hasMetadata(String name) {
        boolean b = false;
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                if (tag.hasKey(name)) {
                    b = true;
                }
            }
        }
        return b;
    }

    public String getMetadataAsString(String name) {
        String value = "";
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                value = tag.getString(name);
            }
        }
        return value;
    }

    public int getMetadataAsInt(String name) {
        int value = 0;
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                value = tag.getInt(name);
            }
        }
        return value;
    }

    public boolean getMetadataAsBoolean(String name) {
        boolean value = false;
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                value = tag.getBoolean(name);
            }
        }
        return value;
    }

    public float getMetadataAsFloat(String name) {
        float value = 0;
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                value = tag.getFloat(name);
            }
        }
        return value;
    }

    public double getMetadataAsDouble(String name) {
        double value = 0;
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                value = tag.getDouble(name);
            }
        }
        return value;
    }

    public int[] getMetadataAsIntArray(String name) {
        int[] value = new int[1];
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                value = tag.getIntArray(name);
            }
        }
        return value;
    }

    public long getMetadataAsLong(String name) {
        long value = 0;
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            if (i.hasTag()) {
                NBTTagCompound tag = i.getTag();
                value = tag.getLong(name);
            }
        }
        return value;
    }

    public void removeMetadata(String name) {
        if (item != null) {
            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (i.hasTag()) {
                tag = i.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            tag.remove(name);
            i.setTag(tag);
            item = CraftItemStack.asBukkitCopy(i);
        } else {
            throw new NullPointerException("Invalid Item!");
        }
    }


}
