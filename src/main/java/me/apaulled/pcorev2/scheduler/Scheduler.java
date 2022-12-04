package me.apaulled.pcorev2.scheduler;

import me.apaulled.pcorev2.PCorev2;

import java.util.ArrayList;
import java.util.Comparator;

public class Scheduler {
    private ArrayList<PTask> queue;
    public Scheduler() {
        this.queue = new ArrayList<>();
    }
    public void runTask(String id, PRunnable task) {
        queue.add(new PTask(id, task));
        queue.sort(Comparator.comparingLong(PTask::getRunTime));
    }

    public void runTaskLater(String id, PRunnable task, long delay) {
        queue.add(new PTask(id, task, delay));
        queue.sort(Comparator.comparingLong(PTask::getRunTime));
    }

    public void runTaskTimer(String id, PRunnable task, long delay, long repeat) {
        queue.add(new PTask(id, task, delay, repeat));
        queue.sort(Comparator.comparingLong(PTask::getRunTime));
    }

    public void checkRun() {
        if (!queue.isEmpty()) {
            if (queue.get(0).getRunTime() <= PCorev2.ticks) {
                if (queue.get(0).isRepeatable()) {
                    queue.add(new PTask(queue.get(0).getId(), queue.get(0).getTask(), queue.get(0).getRepeat(), queue.get(0).getRepeat()));
                }
                queue.get(0).run();
                queue.remove(0);
            }
        }
    }
}
