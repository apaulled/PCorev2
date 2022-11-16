package me.apaulled.pcorev2.party;

import me.apaulled.pcorev2.PCorev2;
import me.apaulled.pcorev2.utils.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            //send help message
            return true;
        }

        PartyManager partyManager = PCorev2.getPartyManager();
        Player source = (Player) sender;

        if (args[0].equalsIgnoreCase("create")) {
            if (partyManager.getParty(source) != null) {
                source.sendMessage(Locale.prefix() + "You are already in a party.");
                return true;
            }
            Party party = new Party(source);
            source.sendMessage(Locale.prefix() + "You have successfully created a party! To invite members, type "
                    + ChatColor.GREEN + "\"/party add <IGN>\"");
            partyManager.getParty(source).addMember(source);
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            if (partyManager.getParty(source) == null) {
                source.sendMessage(Locale.prefix() + "You are not currently in a party. To create one, type "
                        + ChatColor.GREEN + "\"/party create\"");
                return true;
            }
            source.sendMessage("");
            source.sendMessage("-------" + ChatColor.LIGHT_PURPLE + "Party" + ChatColor.WHITE + "-------");
            for (Player player: partyManager.getParty(source).getMembers()) {
                if (partyManager.getParty(player).isLeader(player)) {
                    source.sendMessage(player.getDisplayName() + " (Leader)");
                } else {
                    source.sendMessage(player.getDisplayName());
                }
            }
            source.sendMessage("");
            return true;
        }

        if (args[0].equalsIgnoreCase("add")) {
            Player target = Bukkit.getPlayerExact(args[1]);
            if (partyManager.getParty(source) == null) {
                source.sendMessage(Locale.prefix() + "You are not currently in a party. To create one, type "
                        + ChatColor.GREEN + "\"/party create\"");
                return true;
            }
            if (!(partyManager.getParty(source).isLeader(source))) {
                source.sendMessage(Locale.prefix() + "You must be the leader to invite people to the party.");
                return true;
            }
            partyManager.getParty(source).sendInvite(target);
            target.sendMessage(Locale.prefix() + ChatColor.GREEN + source.getDisplayName() + ChatColor.GRAY
                    + " has invited you to join their party.");
            for (Player player: partyManager.getParty(source).getMembers()) {
                player.sendMessage(Locale.prefix() + ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
                        + " has been invited to your party.");
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("accept")) {
            Player target = Bukkit.getPlayerExact(args[1]);
            if (partyManager.getParty(source) != null) {
                source.sendMessage(Locale.prefix() + "You are already in a party.");
                return true;
            }
            if (!(partyManager.getParty(target).hasInvite(source)) || partyManager.getParty(target) == null) {
                source.sendMessage(Locale.prefix() + "You do not have a party invite from " + target.getDisplayName() + ".");
                return true;
            }
            partyManager.getParty(target).addMember(source);
            source.sendMessage(Locale.prefix() + ChatColor.GREEN + source.getDisplayName() + ChatColor.GRAY
                    + " has joined the party!");
            for (Player player: partyManager.getParty(source).getMembers()) {
                player.sendMessage(Locale.prefix() + ChatColor.GREEN + source.getDisplayName() + ChatColor.GRAY
                        + " has joined the party!");
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("promote")) {
            Player target = Bukkit.getPlayerExact(args[1]);
            if (partyManager.getParty(source) == null) {
                source.sendMessage(Locale.prefix() + "You are not currently in a party. To create one, type "
                        + ChatColor.GREEN + "\"/party create\"");
                return true;
            }
            if (!(partyManager.getParty(source).isLeader(source))) {
                source.sendMessage(Locale.prefix() + "You must be the party leader in order to promote people.");
                return true;
            }
            partyManager.getParty(source).promote(target);
            for (Player player: partyManager.getParty(source).getMembers()) {
                player.sendMessage(Locale.prefix() + ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
                        + " is now the party leader.");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("disband")) {
            if (partyManager.getParty(source) == null) {
                source.sendMessage(Locale.prefix() + "You are not currently in a party. To create one, type "
                        + ChatColor.GREEN + "\"/party create\"");
                return true;
            }
            if (!(partyManager.getParty(source).isLeader(source))) {
                source.sendMessage(Locale.prefix() + "You must be the leader in order to disband the party.");
                return true;
            }
            ArrayList<Player> partyList = new ArrayList<>(partyManager.getParty(source).getMembers());
            for (Player player: partyList) {
                player.sendMessage(Locale.prefix() + "Your party has been disbanded.");
                partyManager.getParty(player).removeMember(player);
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("kick")) {
            Player target = Bukkit.getPlayerExact(args[1]);
            if (partyManager.getParty(source) == null) {
                source.sendMessage(Locale.prefix() + "You are not currently in a party. To create one, type "
                        + ChatColor.GREEN + "\"/party create\"");
                return true;
            }
            if (!(partyManager.getParty(source).isLeader(source))) {
                source.sendMessage(Locale.prefix() + "You must be the party leader in order to kick people.");
                return true;
            }
            partyManager.getParty(source).removeMember(target);
            target.sendMessage(Locale.prefix() + "You have been kicked from the party.");
            for (Player player: partyManager.getParty(source).getMembers()) {
                player.sendMessage(Locale.prefix() + ChatColor.GREEN + target.getDisplayName() + ChatColor.GRAY
                        + " has been kicked from the party.");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("leave")) {
            if (partyManager.getParty(source) == null) {
                source.sendMessage(Locale.prefix() + "You are not currently in a party. To create one, type "
                        + ChatColor.GREEN + "\"/party create\"");
                return true;
            }
            if (partyManager.getParty(source).isLeader(source)) {
                source.sendMessage(Locale.prefix() + "You must promote someone before you leave the party.");
                return true;
            }
            Party party = partyManager.getParty(source);
            party.removeMember(source);
            source.sendMessage(Locale.prefix() + "You have left the party.");
            for (Player player: party.getMembers()) {
                player.sendMessage(Locale.prefix() + ChatColor.GREEN + source.getDisplayName() + ChatColor.GRAY
                        + " has left the party.");
            }
            return true;
        }
        return true;
    }
}
