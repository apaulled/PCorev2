package me.apaulled.pcorev2.hci.project2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

import static me.apaulled.pcorev2.hci.project2.VaultUtils.getNumberItem;

public class VaultGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(args[0]);
        openGui(player);
        return true;
    }

    public static void openGui(Player player) {
        int size = 54;

        Inventory gui = Bukkit.createInventory(null, size,ChatColor.GOLD + "Vault Keypad");

        gui.setItem(0, VaultUtils.getUnbreakableItem((short) 393, Material.DIAMOND_HOE));
        gui.setItem(8, VaultUtils.getUnbreakableItem((short) 394, Material.DIAMOND_HOE));
        gui.setItem(4, VaultUtils.getUnbreakableItem((short) 404, Material.DIAMOND_HOE));
        gui.setItem(25, VaultUtils.getCheckmark());
        gui.setItem(19, VaultUtils.getBackspace());
        for (int i = 10; i < 17; i++) {
            if (i != 13) gui.setItem(i, VaultUtils.getUnbreakableItem((short) 381, Material.DIAMOND_SPADE));
        }
        Map<Integer, ItemStack> phoneGrid = Map.of(
                21, getNumberItem(1), 22, getNumberItem(2), 23, getNumberItem(3),
                30, getNumberItem(4), 31, getNumberItem(5), 32, getNumberItem(6),
                39, getNumberItem(7), 40, getNumberItem(8), 41, getNumberItem(9));
        phoneGrid.forEach(gui::setItem);

        player.openInventory(gui);
    }

}
