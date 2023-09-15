package me.apaulled.pcorev2.hci.project1;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SliderListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (inventory.getTitle().equals("Time of Day Slider")) {
            event.setCancelled(true);
            int clickedSlot = event.getSlot();

            for (int i = 0; i < inventory.getSize(); i++) {
                if (i <= clickedSlot) {
                    inventory.setItem(i, getMarker(clickedSlot+1, GlassColor.GREEN));
                } else {
                    inventory.setItem(i, getMarker(clickedSlot+1, GlassColor.RED));
                }
            }

            Player player = (Player) event.getWhoClicked();
            player.getWorld().setTime(clickedSlot * 2666L);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BELL, 1f, 1f);
        }
    }

    private static ItemStack getMarker(int currentLevel, GlassColor color) {
        ItemStack marker;
        if (color == GlassColor.GREEN) {
            marker = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 13);
        } else {
            marker = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
        }
        ItemMeta meta = marker.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Current Level: " + ChatColor.GOLD + currentLevel);
        meta.setLore(List.of(ChatColor.GREEN + "Click to change level"));
        marker.setItemMeta(meta);
        return marker;
    }
}
