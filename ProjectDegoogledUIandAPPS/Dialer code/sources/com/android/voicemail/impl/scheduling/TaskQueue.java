package com.android.voicemail.impl.scheduling;

import android.content.Context;
import android.os.Bundle;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.scheduling.Task;
import com.android.voicemail.impl.scheduling.Tasks;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

class TaskQueue implements Iterable<Task> {
    private final Queue<Task> queue = new ArrayDeque();

    static final class NextTask {
        final Long minimalWaitTimeMillis;
        final Task task;

        NextTask(Task task2, Long l) {
            this.task = task2;
            this.minimalWaitTimeMillis = l;
        }
    }

    TaskQueue() {
    }

    public boolean add(Task task) {
        Task task2;
        if (task.getId().f53id != -1) {
            if (task.getId().f53id != -2) {
                Task.TaskId id = task.getId();
                Assert.isMainThread();
                Iterator it = this.queue.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        task2 = null;
                        break;
                    }
                    task2 = (Task) it.next();
                    if (task2.getId().equals(id)) {
                        break;
                    }
                }
                if (task2 != null) {
                    task2.onDuplicatedTaskAdded(task);
                    VvmLog.m45i("TaskQueue.add", "duplicated task added");
                    return false;
                }
            }
            this.queue.add(task);
            return true;
        }
        throw new AssertionError("Task id was not set to a valid value before adding.");
    }

    public void clear() {
        this.queue.clear();
    }

    public void fromBundles(Context context, List<Bundle> list) {
        Assert.isTrue(this.queue.isEmpty());
        for (Bundle next : list) {
            try {
                Task createTask = Tasks.createTask(context, next);
                createTask.onRestore(next);
                add(createTask);
            } catch (Tasks.TaskCreationException e) {
                VvmLog.m44e("TaskQueue.fromBundles", "cannot create task", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public NextTask getNextTask(long j) {
        Long l = null;
        for (Task task : this.queue) {
            long readyInMilliSeconds = task.getReadyInMilliSeconds();
            if (readyInMilliSeconds < j) {
                return new NextTask(task, 0L);
            }
            if (l == null || readyInMilliSeconds < l.longValue()) {
                l = Long.valueOf(readyInMilliSeconds);
            }
        }
        return new NextTask((Task) null, l);
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public Iterator<Task> iterator() {
        return this.queue.iterator();
    }

    public void remove(Task task) {
        this.queue.remove(task);
    }

    public List<Bundle> toBundles() {
        ArrayList arrayList = new ArrayList(this.queue.size());
        for (Task task : this.queue) {
            Bundle bundle = task.toBundle();
            bundle.putString("extra_class_name", task.getClass().getName());
            arrayList.add(bundle);
        }
        return arrayList;
    }
}
