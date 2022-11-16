package me.apaulled.pcorev2.party;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PartyManager {
    private HashMap<UUID, Party> partyMap;

    public PartyManager() {
        this.partyMap = new HashMap<>();
    }

    public void addPlayer(Player player, Party party) {
        this.partyMap.put(player.getUniqueId(), party);
    }

    public void removePlayer(Player player) {
        this.partyMap.remove(player.getUniqueId());
    }

    public Party getParty(Player player) {
        return this.partyMap.get(player.getUniqueId());
    }
}
