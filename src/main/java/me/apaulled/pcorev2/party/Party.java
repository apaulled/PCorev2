package me.apaulled.pcorev2.party;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Party {
    private Player leader;
    private ArrayList<Player> members;
    private ArrayList<Player> invites;
    PartyManager partyManager = PCorev2.getPartyManager();

    public Party (Player leader) {
        this.leader = leader;
        this.members = new ArrayList<>();
        this.invites = new ArrayList<>();
        partyManager.addPlayer(leader, this);
    }

    public Player getLeader() {
        return this.leader;
    }

    public ArrayList<Player> getMembers() {
        return this.members;
    }

    public boolean hasInvite(Player player) {
        return this.invites.contains(player);
    }

    public boolean isMember(Player player) {
        return this.members.contains(player);
    }

    public boolean isLeader(Player player) {
        return this.leader.equals(player);
    }

    public void sendInvite(Player player) {
        this.invites.add(player);
    }

    public void addMember(Player player) {
        this.invites.remove(player);
        this.members.add(player);
        partyManager.addPlayer(player, this);
    }

    public void removeMember(Player player) {
        this.members.remove(player);
        partyManager.removePlayer(player);
    }

    public void promote(Player player) {
        this.leader = player;
    }
}
