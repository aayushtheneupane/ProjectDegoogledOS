package com.android.voicemail.impl.scheduling;

import android.content.Context;
import android.os.Bundle;
import com.android.voicemail.impl.VvmLog;

public class BlockerTask extends BaseTask {
    public BlockerTask() {
        super(-1);
    }

    public void onCreate(Context context, Bundle bundle) {
        super.onCreate(context, bundle);
        setId(bundle.getInt("extra_task_id", -1));
        setExecutionTime(getTimeMillis() + ((long) bundle.getInt("extra_block_for_millis", 0)));
    }

    public void onDuplicatedTaskAdded(Task task) {
        VvmLog.m45i("BlockerTask", task + "blocked, " + getReadyInMilliSeconds() + "millis remaining");
    }

    public void onExecuteInBackgroundThread() {
    }
}
