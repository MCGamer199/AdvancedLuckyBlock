package com.LuckyBlock.Inventory;

import com.LuckyBlock.Engine.LuckyBlock;
import com.LuckyBlock.Inventory.AttributeName.AttributeType;
import com.LuckyBlock.Inventory.AttributeName.OperationType;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.NBTTagInt;
import net.minecraft.server.v1_8_R1.NBTTagList;
import net.minecraft.server.v1_8_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMaker {


    public static ItemStack createItem(Material material) {
        ItemStack item = new ItemStack(material);
        return item;
    }

    public static ItemStack createItem(Material material, int amount) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, short data) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        item.setDurability(data);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, int data) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        item.setDurability((short) data);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, short data, String displayName) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        item.setDurability(data);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(displayName);
        item.setItemMeta(itemM);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, int data, String displayName) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        item.setDurability((short) data);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(displayName);
        item.setItemMeta(itemM);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, short data, String displayName, List<String> list) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        item.setDurability(data);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(displayName);
        itemM.setLore(list);
        item.setItemMeta(itemM);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, int data, String displayName, List<String> list) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        item.setDurability((short) data);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(displayName);
        itemM.setLore(list);
        item.setItemMeta(itemM);
        return item;
    }

    public static ItemStack setLore(ItemStack item, String[] strings) {
        ItemMeta itemM = item.getItemMeta();
        List<String> list = new ArrayList<String>();
        for (int x = 0; x < strings.length; x++) {
            if (strings[x] != null) {
                list.add(strings[x]);
            }
        }
        itemM.setLore(list);
        item.setItemMeta(itemM);
        return item;
    }

    public static ItemStack setLore(ItemStack item, List<String> list) {
        ItemMeta itemM = item.getItemMeta();
        itemM.setLore(list);
        item.setItemMeta(itemM);
        return item;
    }

    public static ItemStack addLore(ItemStack item, String lore) {
        ItemMeta itemM = item.getItemMeta();
        List<String> list;
        if (itemM != null && itemM.hasLore()) {
            list = itemM.getLore();
        } else {
            list = new ArrayList<String>();
        }
        list.add(lore);
        itemM.setLore(list);
        item.setItemMeta(itemM);
        return item;
    }

    /**
     * Sets display name for the item.
     *
     * @param item        The Item
     * @param displayName The display name
     * @see ItemMaker
     */
    public static ItemStack setDisplayName(ItemStack item, String displayName) {
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(displayName);
        item.setItemMeta(itemM);
        return item;
    }

    /**
     * Adds Stored Enchantment (Like enchanted book).
     *
     * @param item        The Item
     * @param enchantment The Enchantment
     * @param level       The Level
     * @see ItemMaker
     */
    public static ItemStack addStoredEnchantment(ItemStack item, Enchantment enchantment, int level) {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Sets the owner for the skull.
     *
     * @param item  The Item
     * @param owner The owner of the skull
     * @see ItemMaker
     */
    public static ItemStack setSkullOwner(ItemStack item, String owner) {
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setOwner(owner);
        item.setItemMeta(skull);
        return item;
    }

    /**
     * Creates non-player skull (required id and value).
     *
     * @param item  The Item
     * @param id    The ID
     * @param value The value
     * @see ItemMaker
     */
    public static ItemStack createSkull(ItemStack item, String id, String value) {
        if (LuckyBlock.bukkitVersion[1] > 7) {
            net.minecraft.server.v1_8_R1.ItemStack ii = CraftItemStack.asNMSCopy(item);
            NBTTagCompound tag;
            if (ii.hasTag()) {
                tag = ii.getTag();
            } else {
                tag = new NBTTagCompound();
            }
            NBTTagList tl = new NBTTagList();
            NBTTagCompound com = new NBTTagCompound();
            com.set("Id", new NBTTagString(id));
            NBTTagCompound com2 = new NBTTagCompound();
            com2.set("Value", new NBTTagString(value));
            tl.add(com2);
            NBTTagCompound com1 = new NBTTagCompound();
            com1.set("textures", tl);
            com.set("Properties", com1);
            tag.set("SkullOwner", com);
            ii.setTag(tag);
            item = CraftItemStack.asBukkitCopy(ii);
        }
        return item;
    }

    /**
     * Makes the item unbreakable.
     *
     * @param item The Item
     * @see ItemMaker
     */
    public static ItemStack makeUnbreakable(ItemStack item) {
        net.minecraft.server.v1_8_R1.ItemStack it = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag;
        if (it.hasTag()) {
            tag = it.getTag();
        } else {
            tag = new NBTTagCompound();
        }
        tag.setBoolean("Unbreakable", true);
        it.setTag(tag);
        item = CraftItemStack.asBukkitCopy(it);
        return item;
    }

    /**
     * Adds Attribute to an item.
     *
     * @param item          The Item
     * @param attributeType The attribute type
     * @param operationType The operation type
     * @param name          The name
     * @param amount        The amount
     * @see ItemMaker
     */
    public static ItemStack addAttribute(ItemStack item, AttributeType attributeType, OperationType operationType, String name, int amount) {
        net.minecraft.server.v1_8_R1.ItemStack it = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag;
        if (it.hasTag()) {
            tag = it.getTag();
        } else {
            tag = new NBTTagCompound();
        }
        NBTTagList modifiers = new NBTTagList();
        NBTTagCompound tag1 = new NBTTagCompound();
        tag1.set("AttributeName", new NBTTagString("generic." + attributeType.getName()));
        tag1.set("Name", new NBTTagString(name));
        tag1.set("Amount", new NBTTagInt(amount));
        tag1.set("Operation", new NBTTagInt(operationType.getId()));
        tag1.set("UUIDLeast", new NBTTagInt(1));
        tag1.set("UUIDMost", new NBTTagInt(1));
        modifiers.add(tag1);
        tag.set("AttributeModifiers", modifiers);
        it.setTag(tag);
        item = CraftItemStack.asBukkitCopy(it);
        return item;
    }

    /**
     * Adds Enchantment to an item.
     *
     * @param item  The Item
     * @param ench  The Enchantment
     * @param level The Level
     * @see ItemMaker
     */
    public static ItemStack addEnchant(ItemStack item, Enchantment ench, int level) {
        ItemMeta itemM = item.getItemMeta();
        itemM.addEnchant(ench, level, true);
        item.setItemMeta(itemM);
        return item;
    }


}
