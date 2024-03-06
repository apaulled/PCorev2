package me.apaulled.pcorev2.centergui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CenterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        int size = Integer.parseInt(args[1]) * 9;
        int center = size / 2;
        int itemCount = Integer.parseInt(args[2]);
        ItemStack item = new ItemStack(Material.matchMaterial(args[0].toUpperCase()));
        Inventory gui = Bukkit.createInventory(null,size,"Centered GUI");

        long startTime = System.nanoTime();
        List<Integer> slots = CenterList.slots(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        long elapsedTime = System.nanoTime() - startTime;
        Bukkit.broadcastMessage(String.valueOf(elapsedTime));
        for (int i: slots) {
            gui.setItem(i, item);
        }
        player.openInventory(gui);
        return true;
    }
}
