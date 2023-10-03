package com.android.voicemail.impl.scheduling;

import android.os.Bundle;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.VvmLog;

public class PostponePolicy implements Policy {
    private final int postponeMillis;
    private BaseTask task;

    public PostponePolicy(int i) {
        this.postponeMillis = i;
    }

    public void onBeforeExecute() {
    }

    public void onCompleted() {
    }

    public void onCreate(BaseTask baseTask, Bundle bundle) {
        this.task = baseTask;
        BaseTask baseTask2 = this.task;
        baseTask2.setExecutionTime(baseTask2.getTimeMillis() + ((long) this.postponeMillis));
    }

    public void onDuplicatedTaskAdded() {
        if (!this.task.hasStarted()) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("postponing ");
            outline13.append(this.task);
            VvmLog.m45i("PostponePolicy", outline13.toString());
            BaseTask baseTask = this.task;
            baseTask.setExecutionTime(baseTask.getTimeMillis() + ((long) this.postponeMillis));
        }
    }

    public void onFail() {
    }
}
