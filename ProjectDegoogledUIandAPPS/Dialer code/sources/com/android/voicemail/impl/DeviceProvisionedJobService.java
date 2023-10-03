package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.telecom.PhoneAccountHandle;

@TargetApi(26)
public class DeviceProvisionedJobService extends JobService {
    static final String EXTRA_PHONE_ACCOUNT_HANDLE = "EXTRA_PHONE_ACCOUNT_HANDLE";

    public static void activateAfterProvisioned(Context context, PhoneAccountHandle phoneAccountHandle) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandle);
        ((JobScheduler) context.getSystemService(JobScheduler.class)).enqueue(createJobInfo(context), new JobWorkItem(intent));
    }

    private static JobInfo createJobInfo(Context context) {
        return new JobInfo.Builder(202, new ComponentName(context, DeviceProvisionedJobService.class)).addTriggerContentUri(new JobInfo.TriggerContentUri(Settings.Global.getUriFor("device_provisioned"), 0)).setTriggerContentMaxDelay(0).build();
    }

    public boolean onStartJob(JobParameters jobParameters) {
        boolean z = true;
        if (Settings.Global.getInt(getContentResolver(), "device_provisioned", 0) != 1) {
            z = false;
        }
        if (!z) {
            VvmLog.m45i("DeviceProvisionedJobService.onStartJob", "device not provisioned, rescheduling");
            ((JobScheduler) getSystemService(JobScheduler.class)).schedule(createJobInfo(this));
            return false;
        }
        VvmLog.m45i("DeviceProvisionedJobService.onStartJob", "device provisioned");
        while (true) {
            JobWorkItem dequeueWork = jobParameters.dequeueWork();
            if (dequeueWork == null) {
                return false;
            }
            PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) dequeueWork.getIntent().getParcelableExtra(EXTRA_PHONE_ACCOUNT_HANDLE);
            VvmLog.m45i("DeviceProvisionedJobService.onStartJob", "restarting activation for " + phoneAccountHandle);
            ActivationTask.start(this, phoneAccountHandle, (Bundle) null);
        }
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
