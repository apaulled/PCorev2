package me.apaulled.pcorev2.vault;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VaultCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player source = (Player) sender;
        VaultManager vaultManager = PCorev2.getVaultManager();

        if (args[0].equalsIgnoreCase("open")) {
            int vaultNumber = Integer.parseInt(args[1]);
            //Bukkit.broadcastMessage("hasVault " + vaultManager.hasVault(source, vaultNumber));
            if (vaultManager.hasVault(source, vaultNumber) < 3) {
                vaultManager.giveVault(source, vaultNumber);
            }

            Vault playerVault = vaultManager.getVault(source, vaultNumber);
            source.openInventory(playerVault.getInventory());
            return true;
        }

        return true;
    }
}
