package me.apaulled.pcorev2.friend;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FriendListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent join) {
        PCorev2.getFriendManager().createFriend(join.getPlayer());
    }
}
