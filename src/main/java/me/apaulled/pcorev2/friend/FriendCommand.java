package me.apaulled.pcorev2.friend;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FriendCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        FriendManager friendManager = PCorev2.getFriendManager();
        Player source = (Player) sender;
        Friend sourceFriend = friendManager.getFriend(source);

        //friend list
        if (args[0].equalsIgnoreCase("list")) {
            source.sendMessage(ChatColor.BLUE + "Friends:");
            source.sendMessage(ChatColor.BLUE + "--------");
            for (Friend f: sourceFriend.getFriends()) {
                source.sendMessage(f.getPlayer().getDisplayName());
            }
            return true;
        }

        //friend invites
        if (args[0].equalsIgnoreCase("invites")) {
            source.sendMessage(ChatColor.BLUE + "Pending Friend Invites:");
            source.sendMessage(ChatColor.BLUE + "------------------------");
            for (Friend f: sourceFriend.getFriendInvites()) {
                source.sendMessage(f.getPlayer().getDisplayName());
            }
            return true;
        }

        //friend requests
        if (args[0].equalsIgnoreCase("requests")) {
            source.sendMessage(ChatColor.BLUE + "Pending Friend Requests:");
            source.sendMessage(ChatColor.BLUE + "------------------------");
            for (Friend f: sourceFriend.getFriendRequests()) {
                source.sendMessage(f.getPlayer().getDisplayName());
            }
            return true;
        }

        //friend add
        if (args[0].equalsIgnoreCase("add")) {
            Player target = Bukkit.getPlayerExact(args[1]);
            Friend targetFriend = friendManager.getFriend(target);
            if (sourceFriend.isFriend(target)) {
                source.sendMessage(ChatColor.RED + "You are already friends with " + target.getDisplayName() + ".");
                return true;
            }
            if (sourceFriend.hasFriendRequest(target)) {
                sourceFriend.addFriend(target);
                targetFriend.addFriend(source);
                sourceFriend.removeFriendRequest(target);
                targetFriend.removeFriendInvite(source);
                source.sendMessage(ChatColor.GREEN + "You are now friends with " + target.getDisplayName() + "!");
                target.sendMessage(ChatColor.GREEN + "You are now friends with " + source.getDisplayName() + "!");
            } else {
                sourceFriend.addFriendInvite(target);
                targetFriend.addFriendRequest(source);
                source.sendMessage(ChatColor.GREEN + "You have sent a friend invite to " + target.getDisplayName() + "!");
                target.sendMessage(ChatColor.GREEN + "You have a new friend request from " + source.getDisplayName() + "!");
            }
            return true;
        }

        //friend remove
        if (args[0].equalsIgnoreCase("remove")) {
            Player target = Bukkit.getPlayerExact(args[1]);
            Friend targetFriend = friendManager.getFriend(target);

            if (!(sourceFriend.isFriend(target))) {
                source.sendMessage(ChatColor.RED + "You are already not friends with " + target.getDisplayName() + ".");
                return true;
            }
            if (sourceFriend.isFriend(target)) {
                sourceFriend.removeFriend(target);
                targetFriend.removeFriend(source);
                source.sendMessage(ChatColor.GREEN + "You are no longer friends with " + target.getDisplayName() + ".");
            }
        }
        return true;
    }
}
