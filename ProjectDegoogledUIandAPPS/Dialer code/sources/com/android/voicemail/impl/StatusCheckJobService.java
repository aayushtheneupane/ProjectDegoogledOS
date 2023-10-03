package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.voicemail.impl.sync.VvmAccountManager;
import java.util.concurrent.TimeUnit;

@TargetApi(26)
public class StatusCheckJobService extends JobService {
    public static void schedule(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        if (jobScheduler.getPendingJob(201) != null) {
            VvmLog.m45i("StatusCheckJobService.schedule", "job already scheduled");
        } else {
            jobScheduler.schedule(new JobInfo.Builder(201, new ComponentName(context, StatusCheckJobService.class)).setPeriodic(TimeUnit.DAYS.toMillis(1)).setRequiredNetworkType(1).setRequiresCharging(true).build());
        }
    }

    public boolean onStartJob(JobParameters jobParameters) {
        for (PhoneAccountHandle next : ((TelecomManager) getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()) {
            if (VvmAccountManager.isAccountActivated(this, next)) {
                StatusCheckTask.start(this, next);
            }
        }
        return false;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
