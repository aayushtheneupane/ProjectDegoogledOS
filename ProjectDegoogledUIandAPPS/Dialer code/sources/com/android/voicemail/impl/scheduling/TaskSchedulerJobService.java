package com.android.voicemail.impl.scheduling;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import com.android.dialer.function.Supplier;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.scheduling.TaskExecutor;
import com.android.voicemail.impl.scheduling.Tasks;
import java.util.ArrayList;
import java.util.List;

@TargetApi(26)
public class TaskSchedulerJobService extends JobService implements TaskExecutor.Job {
    private JobParameters jobParameters;

    private static List<Bundle> getBundleList(Parcelable[] parcelableArr) {
        ArrayList arrayList = new ArrayList(parcelableArr.length);
        for (Bundle add : parcelableArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static void scheduleJob(Context context, List<Bundle> list, long j, boolean z) {
        Assert.isMainThread();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        JobInfo pendingJob = jobScheduler.getPendingJob(200);
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("scheduling job with ");
        outline13.append(list.size());
        outline13.append(" tasks");
        VvmLog.m45i("TaskSchedulerJobService", outline13.toString());
        if (pendingJob != null) {
            if (z) {
                List<Bundle> bundleList = getBundleList(pendingJob.getTransientExtras().getParcelableArray("extra_task_extras_array"));
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("merging job with ");
                outline132.append(bundleList.size());
                outline132.append(" existing tasks");
                VvmLog.m45i("TaskSchedulerJobService", outline132.toString());
                TaskQueue taskQueue = new TaskQueue();
                taskQueue.fromBundles(context, bundleList);
                for (Bundle createTask : list) {
                    try {
                        taskQueue.add(Tasks.createTask(context, createTask));
                    } catch (Tasks.TaskCreationException e) {
                        VvmLog.m44e("TaskSchedulerJobService", "cannot create task", e);
                    }
                }
                list = taskQueue.toBundles();
            }
            VvmLog.m45i("TaskSchedulerJobService", "canceling existing job.");
            jobScheduler.cancel(200);
        }
        Bundle bundle = new Bundle();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int i = defaultSharedPreferences.getInt("com.android.voicemail.impl.scheduling.TaskSchedulerJobService.NEXT_JOB_ID", 0);
        defaultSharedPreferences.edit().putInt("com.android.voicemail.impl.scheduling.TaskSchedulerJobService.NEXT_JOB_ID", i + 1).apply();
        bundle.putInt("extra_job_id", i);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("com.android.voicemail.impl.scheduling.TaskSchedulerJobService.EXPECTED_JOB_ID", i).apply();
        bundle.putParcelableArray("extra_task_extras_array", (Parcelable[]) list.toArray(new Bundle[list.size()]));
        boolean z2 = true;
        JobInfo.Builder requiredNetworkType = new JobInfo.Builder(200, new ComponentName(context, TaskSchedulerJobService.class)).setTransientExtras(bundle).setMinimumLatency(j).setRequiredNetworkType(1);
        if (z) {
            if (j != 0) {
                z2 = false;
            }
            Assert.isTrue(z2);
            requiredNetworkType.setOverrideDeadline(0);
            VvmLog.m45i("TaskSchedulerJobService", "running job instantly.");
        }
        jobScheduler.schedule(requiredNetworkType.build());
        VvmLog.m45i("TaskSchedulerJobService", "job " + i + " scheduled");
    }

    public void finishAsync() {
        VvmLog.m45i("TaskSchedulerJobService", "finishing job");
        jobFinished(this.jobParameters, false);
        this.jobParameters = null;
    }

    public boolean isFinished() {
        Assert.isMainThread();
        return ((JobScheduler) getSystemService(JobScheduler.class)).getPendingJob(200) == null;
    }

    public /* synthetic */ Integer lambda$onStartJob$0$TaskSchedulerJobService() {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getInt("com.android.voicemail.impl.scheduling.TaskSchedulerJobService.EXPECTED_JOB_ID", 0));
    }

    public boolean onStartJob(JobParameters jobParameters2) {
        int i = jobParameters2.getTransientExtras().getInt("extra_job_id");
        int intValue = ((Integer) StrictModeUtils.bypass(new Supplier() {
            public final Object get() {
                return TaskSchedulerJobService.this.lambda$onStartJob$0$TaskSchedulerJobService();
            }
        })).intValue();
        if (i != intValue) {
            VvmLog.m43e("TaskSchedulerJobService", "Job " + i + " is not the last scheduled job " + intValue + ", ignoring");
            return false;
        }
        VvmLog.m45i("TaskSchedulerJobService", "starting " + i);
        this.jobParameters = jobParameters2;
        TaskExecutor.createRunningInstance(this);
        TaskExecutor.getRunningInstance().onStartJob(this, getBundleList(this.jobParameters.getTransientExtras().getParcelableArray("extra_task_extras_array")));
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters2) {
        TaskExecutor.getRunningInstance().onStopJob();
        this.jobParameters = null;
        return false;
    }
}
