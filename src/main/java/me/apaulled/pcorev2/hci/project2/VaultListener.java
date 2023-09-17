package me.apaulled.pcorev2.hci.project2;

import org.bukkit.Bukkit;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VaultListener implements Listener {

    private static final List<Integer> VALID_CODE = List.of(1, 1, 1, 1, 1, 1);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (inventory.getTitle().equals(ChatColor.GOLD + "Vault Keypad")) {
            event.setCancelled(true);
            int clickedSlot = event.getSlot();
            Player player = (Player) event.getWhoClicked();

            if (VaultUtils.DIAL_SLOTS.contains(clickedSlot)) {
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

            if (clickedSlot == VaultUtils.DELETE_SLOT) {
                for (int i = 7; i > 0; i--) {
                    if (i != 4) {
                        try {
                            inventory.getItem(i).getType();
                            inventory.setItem(i, VaultUtils.AIR);
                            break;
                        } catch (NullPointerException ignored) {}
                    }
                }
            }

            if (clickedSlot == VaultUtils.COMPLETE_SLOT) {
                player.closeInventory();
                if (checkCode(inventory)) {
                    openTreasure(player);
                } else {
                    Bukkit.broadcastMessage("§4Wrong code!");
                }
            }
        }
    }

    private boolean checkCode(Inventory inventory) {
        List<Integer> enteredCode = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (i != 4) {
                try {
                    ItemStack item = inventory.getItem(i);
                    String numString = item.getItemMeta().getDisplayName().split("0")[0].replace("§6", "");
                    enteredCode.add(Integer.parseInt(numString));
                } catch (Exception ignored) {
                    return false;
                }
            }
        }
        return enteredCode.equals(VALID_CODE);
    }

    private void openTreasure(Player player) {
        Random random = new Random();
        ItemStack gold = new ItemStack(Material.EMERALD);
        ItemMeta meta = gold.getItemMeta();
        meta.setDisplayName("§6Gold");
        gold.setItemMeta(meta);
        Inventory gui = Bukkit.createInventory(null, 54 ,ChatColor.GOLD + "Vault");
        for (int i = 0; i < 54; i++) {
            int rand = random.nextInt(100);
            if (rand < 67) gui.setItem(i, gold);
        }
        player.openInventory(gui);
    }

}
