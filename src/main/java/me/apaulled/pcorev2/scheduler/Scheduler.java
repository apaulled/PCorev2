package me.apaulled.pcorev2.scheduler;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Comparator;

public class Scheduler {
    private ArrayList<PTask> queue;
    private long ticks;
    public Scheduler() {
        queue = new ArrayList<>();
        ticks = 0;
        Bukkit.getScheduler().runTaskTimer(PCorev2.plugin, new Runnable() {
            public void run() {
                checkRun();
                ticks++;
            }
        }, 1, 1);
    }
    public void runTask(PRunnable task) {
        PTask pTask = new PTask(task);
        runTaskHelper(pTask);
    }

    public void runTaskLater(PRunnable task, long delay) {
        PTask pTask = new PTask(task, delay);
        runTaskHelper(pTask);
    }

    public void runTaskTimer(PRunnable task, long delay, long repeat) {
        PTask pTask = new PTask(task, delay, repeat);
        runTaskHelper(pTask);
    }

    private void runTaskHelper(PTask pTask) {
        queue.add(pTask);
        queue.sort(Comparator.comparingLong(PTask::getRunTime));
        Bukkit.getConsoleSender().sendMessage("Task with ID (" + pTask.getTask().getId() + ") has been registered.");
    }

    public void checkRun() {
        for (PTask task : new ArrayList<>(queue)) {
            if (task.getRunTime() <= PCorev2.getScheduler().getTicks()) {
                task.run();
                Bukkit.getConsoleSender().sendMessage("Task with ID (" + task.getTask().getId() + ") has been run at " + PCorev2.getScheduler().getTicks() + ".");
                if (task.isRepeatable()) {
                    task.setRunTime(task.getRunTime() + task.getRepeat());
                } else {
                    queue.remove(task);
                }
            }
        }
    }

    public long getTicks() {
        return ticks;
    }
}
