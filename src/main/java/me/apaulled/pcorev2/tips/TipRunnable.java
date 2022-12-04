package me.apaulled.pcorev2.tips;

import me.apaulled.pcorev2.PCorev2;
import me.apaulled.pcorev2.scheduler.PRunnable;
import org.bukkit.Bukkit;

public class TipRunnable {
    private TipMenu tipMenu;

    public TipRunnable(TipMenu tipMenu) {
        this.tipMenu = tipMenu;
    }

    public void startTips() {
        PCorev2.getScheduler().runTaskTimer(new PRunnable() {
            @Override
            public void run () {
                Bukkit.broadcastMessage(tipMenu.getRandomTip());
            }

            public String getId() {
                return "tip";
            }
        },0, tipMenu.getTipInterval());
    }
}
