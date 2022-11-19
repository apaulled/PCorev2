package me.apaulled.pcorev2.tips;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.Bukkit;

public class TipRunnable {
    private TipMenu tipMenu;

    public TipRunnable(TipMenu tipMenu) {
        this.tipMenu = tipMenu;
    }

    public void startTips() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(PCorev2.plugin, new Runnable() {
            @Override
            public void run () {
                Bukkit.broadcastMessage(tipMenu.getRandomTip());
                //test
            }
        },0L, tipMenu.getTipInterval());
    }
}
