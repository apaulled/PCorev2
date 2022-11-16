package me.apaulled.pcorev2.houses;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HousesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player source = (Player) sender;

        source.sendMessage(ChatColor.GOLD + "Houses:");
        for (House house : House.values()) {
            source.sendMessage(ChatColor.GRAY + String.valueOf(house));
        }
        return true;
    }
}
