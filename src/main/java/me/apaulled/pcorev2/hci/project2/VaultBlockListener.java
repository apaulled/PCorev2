package me.apaulled.pcorev2.hci.project2;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class VaultBlockListener implements Listener {

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Location validLocation = new Location(player.getWorld(), -101.0, 70.0, 49.0);
        if (block.getLocation().equals(validLocation)) {
            event.setCancelled(true);
            VaultGui.openGui(player);
        }
    }
}
