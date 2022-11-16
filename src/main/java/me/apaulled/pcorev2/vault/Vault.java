package me.apaulled.pcorev2.vault;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Vault {
    private Inventory inventory;
    private Player player;

    public Vault(Inventory inventory, Player player) {
        this.inventory = inventory;
        this.player = player;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Player getPlayer() {
        return this.player;
    }

}
