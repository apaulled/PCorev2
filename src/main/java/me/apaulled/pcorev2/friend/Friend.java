package me.apaulled.pcorev2.friend;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Friend {

    private Player player;
    private ArrayList<Friend> friends;
    private ArrayList<Friend> friendInvites;
    private ArrayList<Friend> friendRequests;

    public Friend(Player player) {
        this.player = player;
        this.friends = new ArrayList<>();
        this.friendInvites = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Friend> getFriends() {
        return this.friends;
    }

    public ArrayList<Friend> getFriendRequests() {
        return this.friendRequests;
    }

    public ArrayList<Friend> getFriendInvites() {
        return this.friendInvites;
    }

    public boolean isFriend(Player player) {
        return this.friends.contains(PCorev2.getFriendManager().getFriend(player));
    }

    public boolean hasFriendRequest(Player player) {
        return this.friendRequests.contains(PCorev2.getFriendManager().getFriend(player));
    }

    public void addFriendRequest(Player player) {
        this.friendRequests.add(PCorev2.getFriendManager().getFriend(player));
    }

    public void removeFriendRequest(Player player) {
        this.friendRequests.remove(PCorev2.getFriendManager().getFriend(player));
    }

    public void addFriendInvite(Player player) {
        this.friendInvites.add(PCorev2.getFriendManager().getFriend(player));
    }

    public void removeFriendInvite(Player player) {
        this.friendInvites.remove(PCorev2.getFriendManager().getFriend(player));
    }

    public void addFriend(Player player) {
        this.friends.add(PCorev2.getFriendManager().getFriend(player));
    }

    public void removeFriend(Player player) {
        this.friends.remove(PCorev2.getFriendManager().getFriend(player));
    }

}
