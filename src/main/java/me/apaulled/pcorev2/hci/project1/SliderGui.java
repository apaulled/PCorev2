package me.apaulled.pcorev2.hci.project1;

import me.apaulled.pcorev2.centergui.CenterList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SliderGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);
        int size = Integer.parseInt(args[1]) * 9;
        int itemCount = Integer.parseInt(args[2]);

        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Click to set day progress");
        item.setItemMeta(meta);

        Inventory gui = Bukkit.createInventory(null, size,"Time of Day Slider");

        List<Integer> slots = CenterList.slots(Integer.parseInt(args[1]), itemCount);
        for (int i: slots) {
            gui.setItem(i, item);
        }
        player.openInventory(gui);
        return true;
    }

}
