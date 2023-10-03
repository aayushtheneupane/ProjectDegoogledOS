package com.android.contacts;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class ContactsJobService extends JobService {
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        if (jobParameters.getJobId() != 1) {
            return false;
        }
        DynamicShortcuts.updateFromJob(this, jobParameters);
        return true;
    }
}
