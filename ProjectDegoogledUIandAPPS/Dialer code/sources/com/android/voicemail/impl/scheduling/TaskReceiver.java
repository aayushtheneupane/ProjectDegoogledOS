package com.android.voicemail.impl.scheduling;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.scheduling.Tasks;
import java.util.ArrayList;
import java.util.List;

@TargetApi(26)
public class TaskReceiver extends BroadcastReceiver {
    private static final List<Intent> deferredBroadcasts = new ArrayList();

    public static void resendDeferredBroadcasts(Context context) {
        for (Intent sendBroadcast : deferredBroadcasts) {
            context.sendBroadcast(sendBroadcast);
        }
        deferredBroadcasts.clear();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            VvmLog.m46w("VvmTaskReceiver", "null intent received");
            return;
        }
        VvmLog.m45i("VvmTaskReceiver", "task received");
        TaskExecutor runningInstance = TaskExecutor.getRunningInstance();
        if (runningInstance != null) {
            VvmLog.m45i("VvmTaskReceiver", "TaskExecutor already running");
            if (runningInstance.isTerminating()) {
                VvmLog.m46w("VvmTaskReceiver", "TaskExecutor is terminating, bouncing task");
                deferredBroadcasts.add(intent);
                return;
            }
            try {
                runningInstance.addTask(Tasks.createTask(context.getApplicationContext(), intent.getExtras()));
            } catch (Tasks.TaskCreationException e) {
                VvmLog.m44e("VvmTaskReceiver", "cannot create task", e);
            }
        } else {
            VvmLog.m45i("VvmTaskReceiver", "scheduling new job");
            ArrayList arrayList = new ArrayList();
            arrayList.add(intent.getExtras());
            TaskSchedulerJobService.scheduleJob(context.getApplicationContext(), arrayList, 0, true);
        }
    }
}
