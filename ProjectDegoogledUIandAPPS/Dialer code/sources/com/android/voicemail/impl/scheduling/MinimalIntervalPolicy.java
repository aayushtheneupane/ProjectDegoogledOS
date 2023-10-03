package com.android.voicemail.impl.scheduling;

import android.content.Intent;
import android.os.Bundle;
import com.android.voicemail.impl.scheduling.Task;

public class MinimalIntervalPolicy implements Policy {
    int blockForMillis;

    /* renamed from: id */
    Task.TaskId f52id;
    BaseTask task;

    public MinimalIntervalPolicy(int i) {
        this.blockForMillis = i;
    }

    public void onBeforeExecute() {
    }

    public void onCompleted() {
        if (!this.task.hasFailed()) {
            Intent createIntent = BaseTask.createIntent(this.task.getContext(), BlockerTask.class, this.f52id.phoneAccountHandle);
            createIntent.putExtra("extra_task_id", this.f52id.f53id);
            createIntent.putExtra("extra_block_for_millis", this.blockForMillis);
            this.task.getContext().sendBroadcast(createIntent);
        }
    }

    public void onCreate(BaseTask baseTask, Bundle bundle) {
        this.task = baseTask;
        this.f52id = this.task.getId();
    }

    public void onDuplicatedTaskAdded() {
    }

    public void onFail() {
    }
}
