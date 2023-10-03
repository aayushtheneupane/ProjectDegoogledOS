package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class SystemJobService extends JobService implements ahw {

    /* renamed from: a */
    private static final String f1182a = iol.m14236b("SystemJobService");

    /* renamed from: b */
    private aip f1183b;

    /* renamed from: c */
    private final Map f1184c = new HashMap();

    /* renamed from: a */
    private static String m1134a(JobParameters jobParameters) {
        try {
            PersistableBundle extras = jobParameters.getExtras();
            if (extras == null || !extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return extras.getString("EXTRA_WORK_SPEC_ID");
        } catch (NullPointerException e) {
            return null;
        }
    }

    public final void onCreate() {
        super.onCreate();
        try {
            aip a = aip.m549a(getApplicationContext());
            this.f1183b = a;
            a.f557f.mo506a(this);
        } catch (IllegalStateException e) {
            if (Application.class.equals(getApplication().getClass())) {
                iol.m14231a();
                Log.w(f1182a, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.");
                return;
            }
            throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        aip aip = this.f1183b;
        if (aip != null) {
            aip.f557f.mo508b(this);
        }
    }

    /* renamed from: a */
    public final void mo503a(String str, boolean z) {
        JobParameters jobParameters;
        iol.m14231a();
        String.format("%s executed on JobScheduler", new Object[]{str});
        synchronized (this.f1184c) {
            jobParameters = (JobParameters) this.f1184c.remove(str);
        }
        if (jobParameters != null) {
            jobFinished(jobParameters, z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        r2 = android.os.Build.VERSION.SDK_INT;
        r2 = new p000.ckx();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0045, code lost:
        if (r6.getTriggeredContentUris() == null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0047, code lost:
        java.util.Arrays.asList(r6.getTriggeredContentUris());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r6.getTriggeredContentAuthorities() == null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        java.util.Arrays.asList(r6.getTriggeredContentAuthorities());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        if (android.os.Build.VERSION.SDK_INT < 28) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0061, code lost:
        r6.getNetwork();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0064, code lost:
        r5.f1183b.mo524a(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0069, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onStartJob(android.app.job.JobParameters r6) {
        /*
            r5 = this;
            aip r0 = r5.f1183b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x007b
            java.lang.String r0 = m1134a(r6)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L_0x001d
            p000.iol.m14231a()
            java.lang.String r6 = f1182a
            java.lang.Throwable[] r0 = new java.lang.Throwable[r2]
            java.lang.String r1 = "WorkSpec id not found!"
            p000.iol.m14234a((java.lang.String) r6, (java.lang.String) r1, (java.lang.Throwable[]) r0)
            return r2
        L_0x001d:
            java.util.Map r3 = r5.f1184c
            monitor-enter(r3)
            java.util.Map r4 = r5.f1184c     // Catch:{ all -> 0x0078 }
            boolean r4 = r4.containsKey(r0)     // Catch:{ all -> 0x0078 }
            if (r4 != 0) goto L_0x006a
            p000.iol.m14231a()     // Catch:{ all -> 0x0078 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ all -> 0x0078 }
            r4[r2] = r0     // Catch:{ all -> 0x0078 }
            java.lang.String r2 = "onStartJob for %s"
            java.lang.String.format(r2, r4)     // Catch:{ all -> 0x0078 }
            java.util.Map r2 = r5.f1184c     // Catch:{ all -> 0x0078 }
            r2.put(r0, r6)     // Catch:{ all -> 0x0078 }
            monitor-exit(r3)     // Catch:{ all -> 0x0078 }
            int r2 = android.os.Build.VERSION.SDK_INT
            ckx r2 = new ckx
            r2.<init>()
            android.net.Uri[] r3 = r6.getTriggeredContentUris()
            if (r3 == 0) goto L_0x004e
            android.net.Uri[] r3 = r6.getTriggeredContentUris()
            java.util.Arrays.asList(r3)
        L_0x004e:
            java.lang.String[] r3 = r6.getTriggeredContentAuthorities()
            if (r3 == 0) goto L_0x005b
            java.lang.String[] r3 = r6.getTriggeredContentAuthorities()
            java.util.Arrays.asList(r3)
        L_0x005b:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r3 < r4) goto L_0x0064
            r6.getNetwork()
        L_0x0064:
            aip r6 = r5.f1183b
            r6.mo524a((java.lang.String) r0, (p000.ckx) r2)
            return r1
        L_0x006a:
            p000.iol.m14231a()     // Catch:{ all -> 0x0078 }
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x0078 }
            r6[r2] = r0     // Catch:{ all -> 0x0078 }
            java.lang.String r0 = "Job is already being executed by SystemJobService: %s"
            java.lang.String.format(r0, r6)     // Catch:{ all -> 0x0078 }
            monitor-exit(r3)     // Catch:{ all -> 0x0078 }
            return r2
        L_0x0078:
            r6 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0078 }
            throw r6
        L_0x007b:
            p000.iol.m14231a()
            r5.jobFinished(r6, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.background.systemjob.SystemJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        boolean contains;
        if (this.f1183b != null) {
            String a = m1134a(jobParameters);
            if (TextUtils.isEmpty(a)) {
                iol.m14231a();
                iol.m14234a(f1182a, "WorkSpec id not found!", new Throwable[0]);
                return false;
            }
            iol.m14231a();
            String.format("onStopJob for %s", new Object[]{a});
            synchronized (this.f1184c) {
                this.f1184c.remove(a);
            }
            this.f1183b.mo525b(a);
            ahz ahz = this.f1183b.f557f;
            synchronized (ahz.f513e) {
                contains = ahz.f512d.contains(a);
            }
            return !contains;
        }
        iol.m14231a();
        return true;
    }
}
