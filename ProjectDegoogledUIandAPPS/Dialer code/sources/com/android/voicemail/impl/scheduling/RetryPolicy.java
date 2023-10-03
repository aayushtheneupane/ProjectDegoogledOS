package com.android.voicemail.impl.scheduling;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.VoicemailStatus$DeferredEditor;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;

public class RetryPolicy implements Policy {
    private boolean failed;
    private int retryCount;
    private final int retryDelayMillis;
    private final int retryLimit;
    private BaseTask task;
    private VoicemailStatus$DeferredEditor voicemailStatusEditor;

    public RetryPolicy(int i, int i2) {
        this.retryLimit = i;
        this.retryDelayMillis = i2;
    }

    public VoicemailStatus$Editor getVoicemailStatusEditor() {
        return this.voicemailStatusEditor;
    }

    public void onBeforeExecute() {
    }

    public void onCompleted() {
        boolean z = false;
        if (this.failed) {
            if (this.retryCount < this.retryLimit) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("discarding deferred status: ");
                outline13.append(this.voicemailStatusEditor.getValues());
                VvmLog.m45i("RetryPolicy", outline13.toString());
                Intent createRestartIntent = this.task.createRestartIntent();
                createRestartIntent.putExtra("extra_retry_count", this.retryCount + 1);
                this.task.getContext().sendBroadcast(createRestartIntent);
                return;
            }
        }
        if (!this.failed) {
            VvmLog.m45i("RetryPolicy", this.task + " completed successfully");
        }
        if (this.retryCount < this.retryLimit) {
            z = true;
        }
        if (!z) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("Retry limit for ");
            outline132.append(this.task);
            outline132.append(" reached");
            VvmLog.m45i("RetryPolicy", outline132.toString());
        }
        StringBuilder outline133 = GeneratedOutlineSupport.outline13("committing deferred status: ");
        outline133.append(this.voicemailStatusEditor.getValues());
        VvmLog.m45i("RetryPolicy", outline133.toString());
        this.voicemailStatusEditor.deferredApply();
    }

    public void onCreate(BaseTask baseTask, Bundle bundle) {
        this.task = baseTask;
        this.retryCount = bundle.getInt("extra_retry_count", 0);
        if (this.retryCount > 0) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("retry #");
            outline13.append(this.retryCount);
            outline13.append(" for ");
            outline13.append(this.task);
            outline13.append(" queued, executing in ");
            outline13.append(this.retryDelayMillis);
            VvmLog.m45i("RetryPolicy", outline13.toString());
            BaseTask baseTask2 = this.task;
            baseTask2.setExecutionTime(baseTask2.getTimeMillis() + ((long) this.retryDelayMillis));
        }
        PhoneAccountHandle phoneAccountHandle = baseTask.getPhoneAccountHandle();
        if (phoneAccountHandle == null) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("null phone account for phoneAccountHandle ");
            outline132.append(baseTask.getPhoneAccountHandle());
            VvmLog.m43e("RetryPolicy", outline132.toString());
        }
        this.voicemailStatusEditor = Assert.deferredEdit(baseTask.getContext(), phoneAccountHandle);
    }

    public void onDuplicatedTaskAdded() {
    }

    public void onFail() {
        this.failed = true;
    }
}
