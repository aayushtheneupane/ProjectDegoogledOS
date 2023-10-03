package com.android.dialer.shortcuts;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import java.util.concurrent.TimeUnit;

@TargetApi(25)
public final class PeriodicJobService extends JobService {
    private RefreshShortcutsTask refreshShortcutsTask;

    static {
        TimeUnit.HOURS.toMillis(24);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        Assert.isMainThread();
        LogUtil.enterBlock("PeriodicJobService.onStartJob");
        int i = Build.VERSION.SDK_INT;
        RefreshShortcutsTask refreshShortcutsTask2 = new RefreshShortcutsTask(this);
        this.refreshShortcutsTask = refreshShortcutsTask2;
        refreshShortcutsTask2.execute(new JobParameters[]{jobParameters});
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        Assert.isMainThread();
        LogUtil.enterBlock("PeriodicJobService.onStopJob");
        RefreshShortcutsTask refreshShortcutsTask2 = this.refreshShortcutsTask;
        if (refreshShortcutsTask2 != null) {
            refreshShortcutsTask2.cancel(false);
        }
        return false;
    }
}
