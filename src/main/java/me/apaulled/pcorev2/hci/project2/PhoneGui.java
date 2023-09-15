package me.apaulled.pcorev2.hci.project2;

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
import java.util.Map;

import static me.apaulled.pcorev2.hci.project2.PhoneUtils.getNumberItem;

public class PhoneGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);
        int size = 54;

        Inventory gui = Bukkit.createInventory(null, size,ChatColor.GOLD + "Cellphone");

        gui.setItem(0, PhoneUtils.getUnbreakableItem((short) 393, Material.DIAMOND_HOE));
        gui.setItem(8, PhoneUtils.getUnbreakableItem((short) 394, Material.DIAMOND_HOE));
        gui.setItem(4, PhoneUtils.getUnbreakableItem((short) 404, Material.DIAMOND_HOE));
        gui.setItem(25, PhoneUtils.getCheckmark());
        gui.setItem(19, PhoneUtils.getBackspace());
        for (int i = 10; i < 17; i++) {
            if (i != 13) gui.setItem(i, PhoneUtils.getUnbreakableItem((short) 381, Material.DIAMOND_SPADE));
        }
        Map<Integer, ItemStack> phoneGrid = Map.of(
                21, getNumberItem(1), 22, getNumberItem(2), 23, getNumberItem(3),
                30, getNumberItem(4), 31, getNumberItem(5), 32, getNumberItem(6),
                39, getNumberItem(7), 40, getNumberItem(8), 41, getNumberItem(9));
        phoneGrid.forEach(gui::setItem);

        player.openInventory(gui);
        return true;
    }

}
