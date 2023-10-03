package com.android.dialer.app.calllog;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.provider.VoicemailContract;
import com.android.dialer.common.LogUtil;

public class VoicemailNotificationJobService extends JobService {
    private static JobInfo jobInfo;

    public static void cancelJob(Context context) {
        ((JobScheduler) context.getSystemService(JobScheduler.class)).cancel(205);
        LogUtil.m9i("VoicemailNotificationJobService.scheduleJob", "job canceled", new Object[0]);
    }

    public static void scheduleJob(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        if (jobInfo == null) {
            jobInfo = new JobInfo.Builder(205, new ComponentName(context, VoicemailNotificationJobService.class)).addTriggerContentUri(new JobInfo.TriggerContentUri(VoicemailContract.Voicemails.CONTENT_URI, 1)).setTriggerContentMaxDelay(0).build();
        }
        jobScheduler.schedule(jobInfo);
        LogUtil.m9i("VoicemailNotificationJobService.scheduleJob", "job scheduled", new Object[0]);
    }

    public /* synthetic */ void lambda$onStartJob$0$VoicemailNotificationJobService(JobParameters jobParameters) {
        jobFinished(jobParameters, false);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        LogUtil.m9i("VoicemailNotificationJobService.onStartJob", "updating notification", new Object[0]);
        VisualVoicemailUpdateTask.scheduleTask(this, new Runnable(jobParameters) {
            private final /* synthetic */ JobParameters f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                VoicemailNotificationJobService.this.lambda$onStartJob$0$VoicemailNotificationJobService(this.f$1);
            }
        });
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
