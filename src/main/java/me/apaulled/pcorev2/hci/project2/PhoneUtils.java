package me.apaulled.pcorev2.hci.project2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PhoneUtils {

    public static final List<Integer> DIAL_SLOTS = List.of(21, 22, 23, 30, 31, 32, 39, 40, 41);
    public static final Integer DELETE_SLOT = 19;
    public static final Integer COMPLETE_SLOT = 25;
    public static final ItemStack AIR = new ItemStack(Material.AIR);

    public static ItemStack getNumberItem(Integer num) {
        Material material = Material.DIAMOND;
        switch (num) {
            case 1 -> material = Material.BOAT_ACACIA;
            case 2 -> material = Material.BOAT_BIRCH;
            case 3 -> material = Material.BOAT_DARK_OAK;
            case 4 -> material = Material.BOAT_JUNGLE;
            case 5 -> material = Material.BOAT;
            case 6 -> material = Material.BOAT_SPRUCE;
            case 7 -> material = Material.BEETROOT;
            case 8 -> material = Material.BOOK_AND_QUILL;
            case 9 -> material = Material.SADDLE;
            case 0 -> material = Material.BLAZE_ROD;
        }
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + num.toString());
        meta.setLore(List.of(ChatColor.GREEN + "Click to dial number"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getBackspace() {
        Material material = Material.BEETROOT_SEEDS;
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Delete");
        meta.setLore(List.of(ChatColor.GREEN + "Click to delete last number"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCheckmark() {
        ItemStack item = getUnbreakableItem((short) 11, Material.GOLD_SPADE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Dial Entered Phone Number");
        meta.setLore(List.of(ChatColor.GREEN + "Click to dial the phone!"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getUnbreakableItem(short durability, Material material) {
        ItemStack item = new ItemStack(material);
        item.setDurability(durability);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(new ArrayList<>());
        meta.setDisplayName("§7");
        item.setItemMeta(meta);
        return item;
    }

}
