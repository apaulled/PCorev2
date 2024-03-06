package me.apaulled.pcorev2.scheduler;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.Bukkit;

import java.util.*;

public class Scheduler {
    private final Queue<PTask> queue;
    private long ticks;
    public Scheduler() {
        queue = new PriorityQueue<>();
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
        queue.offer(pTask);
        Bukkit.getConsoleSender().sendMessage("Task with ID (" + pTask.getTask().getId() + ") has been registered.");
    }

    public void checkRun() {
        PTask nextTask = queue.peek();
        while (nextTask != null && nextTask.getRunTime() <= PCorev2.getScheduler().getTicks()) {
            queue.poll();
            nextTask.run();
            Bukkit.getConsoleSender().sendMessage("Task with ID (" + nextTask.getTask().getId() + ") has been run at " + PCorev2.getScheduler().getTicks() + ".");
            if (nextTask.isRepeatable()) {
                nextTask.setRunTime(nextTask.getRunTime() + nextTask.getRepeat());
            }
            nextTask = queue.peek();
        }
    }

    public long getTicks() {
        return ticks;
    }
}
