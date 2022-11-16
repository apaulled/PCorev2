package me.apaulled.pcorev2;

import me.apaulled.pcorev2.centergui.CenterCommand;
import me.apaulled.pcorev2.friend.FriendCommand;
import me.apaulled.pcorev2.friend.FriendListener;
import me.apaulled.pcorev2.friend.FriendManager;
import me.apaulled.pcorev2.party.PartyCommand;
import me.apaulled.pcorev2.party.PartyManager;
import me.apaulled.pcorev2.houses.HousesCommand;
import me.apaulled.pcorev2.singlecommands.DelayCommand;
import me.apaulled.pcorev2.points.HousePointsCommand;
import me.apaulled.pcorev2.vault.VaultCommand;
import me.apaulled.pcorev2.vault.VaultManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PCorev2 extends JavaPlugin {
    public static JavaPlugin plugin;
    public static FriendManager friendManager;
    public static VaultManager vaultManager;
    public static PartyManager partyManager;

    @Override
    public void onEnable() {
        plugin = this;
        friendManager = new FriendManager();
        vaultManager = new VaultManager();
        partyManager = new PartyManager();
        this.getCommand("Friend").setExecutor(new FriendCommand());
        this.getCommand("Cg").setExecutor(new CenterCommand());
        this.getCommand("Vault").setExecutor(new VaultCommand());
        this.getCommand("Party").setExecutor(new PartyCommand());
        this.getCommand("Houses").setExecutor(new HousesCommand());
        this.getCommand("Points").setExecutor(new HousePointsCommand());
        this.getCommand("Dcmd").setExecutor(new DelayCommand());
        this.getServer().getPluginManager().registerEvents(new FriendListener(), this);
    }
    @Override
    public void onDisable() {
    }
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static FriendManager getFriendManager() {
        return friendManager;
    }

    public static VaultManager getVaultManager() {
        return vaultManager;
    }

    public static PartyManager getPartyManager() {
        return partyManager;
    }
}
