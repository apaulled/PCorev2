package me.apaulled.pcorev2.scheduler;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.Bukkit;

public class PTask {
    private long runTime;
    private long repeat;
    private boolean repeatable;
    private String id;
    private PRunnable task;

    public PTask(String id, PRunnable task, long delay, long repeat) {
        this.id = id;
        this.task = task;
        this.repeatable = true;
        this.runTime = PCorev2.ticks + delay;
        this.repeat = repeat;
        Bukkit.getConsoleSender().sendMessage("Task with ID (" + id + ") has been registered.");
    }

    public PTask(String id, PRunnable task, long delay) {
        this.id = id;
        this.task = task;
        this.runTime = PCorev2.ticks + delay;
        Bukkit.getConsoleSender().sendMessage("Task with ID (" + id + ") has been registered.");
    }

    public PTask(String id, PRunnable task) {
        this.id = id;
        this.task = task;
        this.runTime = PCorev2.ticks;
        Bukkit.getConsoleSender().sendMessage("Task with ID (" + id + ") has been registered.");
    }

    public void run() {
        task.run();
    }

    public String getId() {
        return id;
    }

    public long getRunTime() {
        return runTime;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public long getRepeat() {
        return repeat;
    }

    public PRunnable getTask() {
        return task;
    }
}
