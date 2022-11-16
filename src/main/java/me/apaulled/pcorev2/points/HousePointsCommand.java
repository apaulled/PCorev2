package me.apaulled.pcorev2.points;

import me.apaulled.pcorev2.houses.House;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HousePointsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player source = (Player) sender;

        HousePointsRequest request = new HousePointsRequest();
        HousePointsResponse pointsResponse = new HousePointsResponse(request.getRawJsonText());

        source.sendTitle(ChatColor.GOLD + "House Points", ChatColor.translateAlternateColorCodes('&',
                    "&4G: " + pointsResponse.getHousePoints(House.griffin) +
                                " &8| &eH: " + pointsResponse.getHousePoints(House.honeybadger) +
                                " &8| &2S: " + pointsResponse.getHousePoints(House.serpent) +
                                " &8| &9R: " + pointsResponse.getHousePoints(House.raven)), 10, 70, 20);
        return true;
    }
}
