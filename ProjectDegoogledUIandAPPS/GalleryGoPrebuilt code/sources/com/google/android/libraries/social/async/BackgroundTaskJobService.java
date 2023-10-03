package com.google.android.libraries.social.async;

import android.app.job.JobParameters;
import android.app.job.JobService;

/* compiled from: PG */
public final class BackgroundTaskJobService extends JobService {
    public final void onCreate() {
        m4978a();
    }

    public final void onDestroy() {
        m4978a();
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        fxk.m9830b();
        m4978a();
        frz frz = (frz) fua.m9628a(this, frz.class);
        throw null;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        m4978a();
        return false;
    }

    /* renamed from: a */
    private final void m4978a() {
        fxk.m9830b();
        fsa fsa = (fsa) fua.m9628a(this, fsa.class);
        fxk.m9830b();
    }
}
