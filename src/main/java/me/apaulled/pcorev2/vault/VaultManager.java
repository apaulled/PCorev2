package me.apaulled.pcorev2.vault;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VaultManager {
    private HashMap<UUID, HashMap<Integer, Vault>> vaultMap;

    public VaultManager() {
        this.vaultMap = new HashMap<>();
    }

    public void giveVault(Player player, int vaultNumber) {
        HashMap<Integer, Vault> innerMap;
        if (this.hasVault(player, vaultNumber) < 2) {
            //Bukkit.broadcastMessage("New set of vaults created");
            innerMap = new HashMap<>();
        } else {
            innerMap = this.vaultMap.get(player.getUniqueId());
        }
        innerMap.put(vaultNumber, new Vault(Bukkit.createInventory(player, 54, ("Vault " + vaultNumber)), player));
        //Bukkit.broadcastMessage("Vault Created");
        this.vaultMap.put(player.getUniqueId(), innerMap);
    }

    public Vault getVault(Player player, int vaultNumber) {
        HashMap<Integer, Vault> innerMap = this.vaultMap.get(player.getUniqueId());
        //Bukkit.broadcastMessage("getVault innermap" + String.valueOf(innerMap));
        return innerMap.get(vaultNumber);
    }

    public int hasVault(Player player, int vaultNumber) {
        Map innerMap = this.vaultMap.get(player.getUniqueId());
        if (innerMap == null) {
            return 1;
        }
        if (innerMap.get(vaultNumber) == null) {
            return 2;
        }
        return 3;
    }
}
