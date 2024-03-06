package me.apaulled.pcorev2.friend;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FriendManager {
    private final Map<UUID, Friend> friendMap;

    public FriendManager() {
        this.friendMap = new HashMap<>();
    }

    public void createFriend(Player player) {
        this.friendMap.put(player.getUniqueId(), new Friend(player));
    }

    public Friend getFriend(Player player) {
        return this.friendMap.get(player.getUniqueId());
    }

}
