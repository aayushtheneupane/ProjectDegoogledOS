package com.android.voicemail.impl.scheduling;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.scheduling.TaskQueue;
import java.util.List;

@TargetApi(26)
final class TaskExecutor {
    private static TaskExecutor instance;
    /* access modifiers changed from: private */
    public final Context appContext;
    private boolean isTerminating = false;
    /* access modifiers changed from: private */
    public boolean isWorkerThreadBusy = false;
    /* access modifiers changed from: private */
    public Job job;
    /* access modifiers changed from: private */
    public final MainThreadHandler mainThreadHandler;
    /* access modifiers changed from: private */
    public MessageSender messageSender = new MessageSender();
    private final Runnable stopServiceWithDelay = new Runnable() {
        public void run() {
            VvmLog.m45i("VvmTaskExecutor", "Stopping service");
            if (!TaskExecutor.access$000(TaskExecutor.this) || TaskExecutor.this.isTerminating()) {
                VvmLog.m43e("VvmTaskExecutor", "Service already stopped");
            } else {
                TaskExecutor.this.scheduleJobAndTerminate(0, true);
            }
        }
    };
    private boolean taskAutoRunDisabledForTesting = false;
    /* access modifiers changed from: private */
    public final TaskQueue tasks = new TaskQueue();
    private final WorkerThreadHandler workerThreadHandler;

    interface Job {
        void finishAsync();

        boolean isFinished();
    }

    private class JobFinishedPoller implements Runnable {
        private final long delayMillis;
        private int invocationCounter = 0;
        private final boolean isNewJob;

        JobFinishedPoller(long j, boolean z) {
            this.delayMillis = j;
            this.isNewJob = z;
        }

        public void run() {
            Assert.isTrue(this.invocationCounter < 10);
            this.invocationCounter++;
            if (TaskExecutor.this.job.isFinished()) {
                VvmLog.m45i("JobFinishedPoller.run", "Job finished");
                if (!TaskExecutor.this.getTasks().isEmpty()) {
                    TaskSchedulerJobService.scheduleJob(TaskExecutor.this.appContext, TaskExecutor.this.getTasks().toBundles(), this.delayMillis, this.isNewJob);
                    TaskExecutor.this.tasks.clear();
                }
                TaskExecutor.this.terminate();
                return;
            }
            VvmLog.m46w("JobFinishedPoller.run", "Job still running");
            TaskExecutor.this.mainThreadHandler.postDelayed(this, 1000);
        }
    }

    final class MainThreadHandler extends Handler {
        public MainThreadHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Assert.isMainThread();
            Task task = (Task) message.obj;
            TaskExecutor.this.getTasks().remove(task);
            task.onCompleted();
            boolean unused = TaskExecutor.this.isWorkerThreadBusy = false;
            if (TaskExecutor.access$000(TaskExecutor.this) && !TaskExecutor.this.isTerminating()) {
                TaskExecutor.this.maybeRunNextTask();
            }
        }
    }

    static class MessageSender {
        MessageSender() {
        }

        public void send(Message message) {
            message.sendToTarget();
        }
    }

    final class WorkerThreadHandler extends Handler {
        public WorkerThreadHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Assert.isNotMainThread();
            Task task = (Task) message.obj;
            try {
                VvmLog.m45i("VvmTaskExecutor", "executing task " + task);
                task.onExecuteInBackgroundThread();
            } catch (Throwable th) {
                VvmLog.m44e("VvmTaskExecutor", "Exception while executing task " + task + ":", th);
            }
            Message obtainMessage = TaskExecutor.this.mainThreadHandler.obtainMessage();
            obtainMessage.obj = task;
            TaskExecutor.this.messageSender.send(obtainMessage);
        }
    }

    private TaskExecutor(Context context) {
        this.appContext = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("VvmTaskExecutor");
        handlerThread.start();
        this.workerThreadHandler = new WorkerThreadHandler(handlerThread.getLooper());
        this.mainThreadHandler = new MainThreadHandler(Looper.getMainLooper());
    }

    static /* synthetic */ boolean access$000(TaskExecutor taskExecutor) {
        return taskExecutor.job != null;
    }

    static void createRunningInstance(Context context) {
        Assert.isMainThread();
        Assert.isTrue(instance == null);
        instance = new TaskExecutor(context);
    }

    static TaskExecutor getRunningInstance() {
        return instance;
    }

    /* access modifiers changed from: private */
    public void maybeRunNextTask() {
        Assert.isMainThread();
        if (!this.isWorkerThreadBusy && !this.taskAutoRunDisabledForTesting) {
            runNextTask();
        }
    }

    /* access modifiers changed from: package-private */
    public void addTask(Task task) {
        Assert.isMainThread();
        getTasks().add(task);
        VvmLog.m45i("VvmTaskExecutor", task + " added");
        this.mainThreadHandler.removeCallbacks(this.stopServiceWithDelay);
        maybeRunNextTask();
    }

    /* access modifiers changed from: package-private */
    public TaskQueue getTasks() {
        Assert.isMainThread();
        return this.tasks;
    }

    public boolean isTerminating() {
        return this.isTerminating;
    }

    public void onStartJob(Job job2, List<Bundle> list) {
        VvmLog.m45i("VvmTaskExecutor", "onStartJob");
        this.job = job2;
        this.tasks.fromBundles(this.appContext, list);
        maybeRunNextTask();
    }

    public void onStopJob() {
        VvmLog.m43e("VvmTaskExecutor", "onStopJob");
        if ((this.job != null) && !isTerminating()) {
            scheduleJobAndTerminate(0, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void runNextTask() {
        Long l;
        Assert.isMainThread();
        if (getTasks().isEmpty()) {
            VvmLog.m45i("VvmTaskExecutor", "no more tasks, stopping service if no task are added in 5000 millis");
            this.mainThreadHandler.postDelayed(this.stopServiceWithDelay, 5000);
            return;
        }
        TaskQueue.NextTask nextTask = getTasks().getNextTask(100);
        Task task = nextTask.task;
        if (task != null) {
            task.onBeforeExecute();
            Message obtainMessage = this.workerThreadHandler.obtainMessage();
            obtainMessage.obj = nextTask.task;
            this.isWorkerThreadBusy = true;
            this.messageSender.send(obtainMessage);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("minimal wait time:");
        outline13.append(nextTask.minimalWaitTimeMillis);
        VvmLog.m45i("VvmTaskExecutor", outline13.toString());
        if (!this.taskAutoRunDisabledForTesting && (l = nextTask.minimalWaitTimeMillis) != null) {
            long longValue = l.longValue();
            VvmLog.m45i("VvmTaskExecutor", "sleep for " + longValue + " millis");
            if (longValue < 10000) {
                this.mainThreadHandler.postDelayed(new Runnable() {
                    public void run() {
                        TaskExecutor.this.maybeRunNextTask();
                    }
                }, longValue);
            } else {
                scheduleJobAndTerminate(longValue, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleJobAndTerminate(long j, boolean z) {
        Assert.isMainThread();
        Assert.isTrue(!isTerminating());
        Assert.isMainThread();
        VvmLog.m45i("VvmTaskExecutor", "finishing Job");
        this.job.finishAsync();
        this.isTerminating = true;
        this.mainThreadHandler.removeCallbacks(this.stopServiceWithDelay);
        this.mainThreadHandler.post(new JobFinishedPoller(j, z));
    }

    /* access modifiers changed from: package-private */
    public void terminate() {
        VvmLog.m45i("VvmTaskExecutor", "terminated");
        Assert.isMainThread();
        this.job = null;
        this.workerThreadHandler.getLooper().quit();
        instance = null;
        TaskReceiver.resendDeferredBroadcasts(this.appContext);
    }
}
