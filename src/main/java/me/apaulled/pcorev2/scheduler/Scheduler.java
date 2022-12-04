package me.apaulled.pcorev2.scheduler;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Comparator;

public class Scheduler {
    private ArrayList<PTask> queue;
    public Scheduler() {
        this.queue = new ArrayList<>();
    }
    public void runTask(PRunnable task) {
        queue.add(new PTask(task));
        queue.sort(Comparator.comparingLong(PTask::getRunTime));
    }

    public void runTaskLater(PRunnable task, long delay) {
        queue.add(new PTask(task, delay));
        queue.sort(Comparator.comparingLong(PTask::getRunTime));
    }

    public void runTaskTimer(PRunnable task, long delay, long repeat) {
        queue.add(new PTask(task, delay, repeat));
        queue.sort(Comparator.comparingLong(PTask::getRunTime));
    }

    public void checkRun() {
        if (!queue.isEmpty()) {
            if (queue.get(0).getRunTime() <= PCorev2.ticks) {
                Bukkit.getConsoleSender().sendMessage("Task with ID (" + queue.get(0).getTask().getId() + ") has been run at " + PCorev2.ticks + ".");
                if (queue.get(0).isRepeatable()) {
                    queue.add(new PTask(queue.get(0).getTask(), queue.get(0).getRepeat(), queue.get(0).getRepeat()));
                }
                queue.get(0).run();
                queue.remove(0);
            }
        }
    }
}
