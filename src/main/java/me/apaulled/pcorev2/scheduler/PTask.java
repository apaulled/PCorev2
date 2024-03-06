package me.apaulled.pcorev2.scheduler;

import me.apaulled.pcorev2.PCorev2;
import org.bukkit.Bukkit;

public class PTask implements Comparable<PTask> {
    private Long runTime;
    private long repeat;
    private boolean repeatable;
    private final PRunnable task;

    public PTask(PRunnable task, long delay, long repeat) {
        this.task = task;
        this.repeatable = true;
        this.runTime = PCorev2.getScheduler().getTicks() + delay;
        this.repeat = repeat;
    }

    public PTask(PRunnable task, long delay) {
        this.task = task;
        this.runTime = PCorev2.getScheduler().getTicks() + delay;
    }

    public PTask(PRunnable task) {
        this.task = task;
        this.runTime = PCorev2.getScheduler().getTicks();
    }



    public void run() {
        task.run();
    }

    public Long getRunTime() {
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

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    @Override
    public int compareTo(PTask o) {
        return this.runTime.compareTo(o.getRunTime());
    }
}
