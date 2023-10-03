package com.android.voicemail.impl.scheduling;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.scheduling.Task;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTask implements Task {
    public static final String EXTRA_PHONE_ACCOUNT_HANDLE = "extra_phone_account_handle";
    private Context context;
    private long executionTime;
    private Bundle extras;
    private volatile boolean hasFailed;
    private boolean hasStarted;

    /* renamed from: id */
    private int f51id;
    private PhoneAccountHandle phoneAccountHandle;
    private final List<Policy> policies = new ArrayList();

    static class Clock {
        public static long getTimeMillis() {
            return SystemClock.elapsedRealtime();
        }
    }

    protected BaseTask(int i) {
        this.f51id = i;
        this.executionTime = getTimeMillis();
    }

    public static Intent createIntent(Context context2, Class<? extends BaseTask> cls, PhoneAccountHandle phoneAccountHandle2) {
        Intent intent = new Intent(context2, TaskReceiver.class);
        intent.setPackage(context2.getPackageName());
        intent.putExtra("extra_class_name", cls.getName());
        intent.putExtra(EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandle2);
        return intent;
    }

    public BaseTask addPolicy(Policy policy) {
        Assert.isMainThread();
        this.policies.add(policy);
        return this;
    }

    public Intent createRestartIntent() {
        return createIntent(getContext(), getClass(), this.phoneAccountHandle);
    }

    public void fail() {
        Assert.isNotMainThread();
        this.hasFailed = true;
    }

    public Context getContext() {
        return this.context;
    }

    public Task.TaskId getId() {
        return new Task.TaskId(this.f51id, this.phoneAccountHandle);
    }

    public PhoneAccountHandle getPhoneAccountHandle() {
        return this.phoneAccountHandle;
    }

    public long getReadyInMilliSeconds() {
        return this.executionTime - getTimeMillis();
    }

    public long getTimeMillis() {
        return Clock.getTimeMillis();
    }

    public boolean hasFailed() {
        Assert.isMainThread();
        return this.hasFailed;
    }

    public boolean hasStarted() {
        Assert.isMainThread();
        return this.hasStarted;
    }

    public void onBeforeExecute() {
        for (Policy onBeforeExecute : this.policies) {
            onBeforeExecute.onBeforeExecute();
        }
        this.hasStarted = true;
    }

    public void onCompleted() {
        if (this.hasFailed) {
            for (Policy onFail : this.policies) {
                onFail.onFail();
            }
        }
        for (Policy onCompleted : this.policies) {
            onCompleted.onCompleted();
        }
    }

    public void onCreate(Context context2, Bundle bundle) {
        this.context = context2;
        this.extras = bundle;
        this.phoneAccountHandle = (PhoneAccountHandle) bundle.getParcelable(EXTRA_PHONE_ACCOUNT_HANDLE);
        for (Policy onCreate : this.policies) {
            onCreate.onCreate(this, bundle);
        }
    }

    public void onDuplicatedTaskAdded(Task task) {
        for (Policy onDuplicatedTaskAdded : this.policies) {
            onDuplicatedTaskAdded.onDuplicatedTaskAdded();
        }
    }

    public void onRestore(Bundle bundle) {
        if (this.extras.containsKey("extra_execution_time")) {
            this.executionTime = bundle.getLong("extra_execution_time");
        }
    }

    public void setExecutionTime(long j) {
        Assert.isMainThread();
        this.executionTime = j;
    }

    public void setId(int i) {
        Assert.isMainThread();
        this.f51id = i;
    }

    public Bundle toBundle() {
        this.extras.putLong("extra_execution_time", this.executionTime);
        return this.extras;
    }
}
