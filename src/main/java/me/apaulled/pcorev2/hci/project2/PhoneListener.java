package me.apaulled.pcorev2.hci.project2;

import me.apaulled.pcorev2.hci.project1.GlassColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PhoneListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (inventory.getTitle().equals(ChatColor.GOLD + "Cellphone")) {
            event.setCancelled(true);
            int clickedSlot = event.getSlot();
            Player player = (Player) event.getWhoClicked();

            if (PhoneUtils.DIAL_SLOTS.contains(clickedSlot)) {
                for (int i = 1; i < 8; i++) {
                    if (i != 4) {
                        try {
                            inventory.getItem(i).getType();
                        } catch (NullPointerException e) {
                            inventory.setItem(i, inventory.getItem(clickedSlot));
                            break;
                        }
                    }
                }
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BELL, 1f, 1f);
            }

            if (clickedSlot == PhoneUtils.DELETE_SLOT) {
                for (int i = 7; i > 0; i--) {
                    if (i != 4) {
                        try {
                            inventory.getItem(i).getType();
                            inventory.setItem(i, PhoneUtils.AIR);
                            break;
                        } catch (NullPointerException ignored) {}
                    }
                }
            }

            if (clickedSlot == PhoneUtils.COMPLETE_SLOT) {
                player.closeInventory();
                Bukkit.broadcastMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY + " is calling the number " + ChatColor.GREEN + "test");
            }
        }
    }

}
