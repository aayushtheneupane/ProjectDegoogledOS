package com.android.dialer.shortcuts;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;

@TargetApi(25)
final class RefreshShortcutsTask extends AsyncTask<JobParameters, Void, JobParameters> {
    private final JobService jobService;

    RefreshShortcutsTask(JobService jobService2) {
        this.jobService = jobService2;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Assert.isWorkerThread();
        LogUtil.enterBlock("RefreshShortcutsTask.doInBackground");
        JobService jobService2 = this.jobService;
        new DynamicShortcuts(jobService2, new IconFactory(jobService2)).updateIcons();
        new PinnedShortcuts(this.jobService).refresh();
        return ((JobParameters[]) objArr)[0];
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Assert.isMainThread();
        LogUtil.enterBlock("RefreshShortcutsTask.onPostExecute");
        this.jobService.jobFinished((JobParameters) obj, false);
    }
}
