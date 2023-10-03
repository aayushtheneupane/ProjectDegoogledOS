package p000;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemjob.SystemJobService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: ajh */
/* compiled from: PG */
public final class ajh implements aia {

    /* renamed from: a */
    private static final String f636a = iol.m14236b("SystemJobScheduler");

    /* renamed from: b */
    private final Context f637b;

    /* renamed from: c */
    private final JobScheduler f638c;

    /* renamed from: d */
    private final aip f639d;

    /* renamed from: e */
    private final ajg f640e;

    public ajh(Context context, aip aip) {
        ajg ajg = new ajg(context);
        this.f637b = context;
        this.f639d = aip;
        this.f638c = (JobScheduler) context.getSystemService("jobscheduler");
        this.f640e = ajg;
    }

    /* renamed from: a */
    public final void mo515a(String str) {
        ArrayList arrayList;
        List a = m598a(this.f637b, this.f638c);
        if (a != null) {
            arrayList = new ArrayList(2);
            int size = a.size();
            for (int i = 0; i < size; i++) {
                JobInfo jobInfo = (JobInfo) a.get(i);
                if (str.equals(m597a(jobInfo))) {
                    arrayList.add(Integer.valueOf(jobInfo.getId()));
                }
            }
        } else {
            arrayList = null;
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                m599a(this.f638c, ((Integer) arrayList.get(i2)).intValue());
            }
            this.f639d.f554c.mo1229m().mo588b(str);
        }
    }

    /* renamed from: a */
    public static void m600a(Context context) {
        List a;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null && (a = m598a(context, jobScheduler)) != null && !a.isEmpty()) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                m599a(jobScheduler, ((JobInfo) a.get(i)).getId());
            }
        }
    }

    /* renamed from: b */
    public static void m601b(Context context) {
        List a;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null && (a = m598a(context, jobScheduler)) != null && !a.isEmpty()) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                JobInfo jobInfo = (JobInfo) a.get(i);
                if (m597a(jobInfo) == null) {
                    m599a(jobScheduler, jobInfo.getId());
                }
            }
        }
    }

    /* renamed from: a */
    private static void m599a(JobScheduler jobScheduler, int i) {
        try {
            jobScheduler.cancel(i);
        } catch (Throwable th) {
            iol.m14231a();
            iol.m14234a(f636a, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", new Object[]{Integer.valueOf(i)}), th);
        }
    }

    /* renamed from: a */
    private static List m598a(Context context, JobScheduler jobScheduler) {
        List<JobInfo> list;
        ArrayList arrayList = null;
        try {
            list = jobScheduler.getAllPendingJobs();
        } catch (Throwable th) {
            iol.m14231a();
            iol.m14234a(f636a, "getAllPendingJobs() is not reliable on this device.", th);
            list = null;
        }
        if (list != null) {
            arrayList = new ArrayList(list.size());
            ComponentName componentName = new ComponentName(context, SystemJobService.class);
            for (JobInfo next : list) {
                if (componentName.equals(next.getService())) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String m597a(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return extras.getString("EXTRA_WORK_SPEC_ID");
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /* renamed from: a */
    public final void mo516a(alg... algArr) {
        int i;
        int i2;
        WorkDatabase workDatabase = this.f639d.f554c;
        amb amb = new amb(workDatabase);
        for (alg alg : algArr) {
            workDatabase.mo2845e();
            try {
                alg b = workDatabase.mo1226j().mo608b(alg.f713b);
                if (b == null) {
                    iol.m14231a();
                    Log.w(f636a, "Skipping scheduling " + alg.f713b + " because it's no longer in the DB");
                    workDatabase.mo2847g();
                } else if (b.f728q != 1) {
                    iol.m14231a();
                    Log.w(f636a, "Skipping scheduling " + alg.f713b + " because it is no longer enqueued");
                    workDatabase.mo2847g();
                } else {
                    akr a = workDatabase.mo1229m().mo586a(alg.f713b);
                    if (a == null) {
                        synchronized (amb.class) {
                            i = amb.mo639a("next_job_scheduler_id");
                            if (i < 0) {
                                amb.mo640a("next_job_scheduler_id", 1);
                                i = 0;
                            }
                        }
                    } else {
                        i = a.f697b;
                    }
                    if (a == null) {
                        this.f639d.f554c.mo1229m().mo587a(new akr(alg.f713b, i));
                    }
                    ajg ajg = this.f640e;
                    ahb ahb = alg.f721j;
                    int i3 = ahb.f482i;
                    int i4 = i3 - 1;
                    if (i3 != 0) {
                        int i5 = 4;
                        if (i4 == 0) {
                            i5 = 0;
                        } else if (i4 == 1) {
                            i5 = 1;
                        } else if (i4 == 2) {
                            i5 = 2;
                        } else if (i4 == 3) {
                            int i6 = Build.VERSION.SDK_INT;
                            i5 = 3;
                        } else if (i4 != 4) {
                            iol.m14231a();
                            String.format("API version too low. Cannot convert network type value %s", new Object[]{fxk.m9822a(i3)});
                            i5 = 1;
                        } else {
                            int i7 = Build.VERSION.SDK_INT;
                        }
                        PersistableBundle persistableBundle = new PersistableBundle();
                        persistableBundle.putString("EXTRA_WORK_SPEC_ID", alg.f713b);
                        persistableBundle.putBoolean("EXTRA_IS_PERIODIC", alg.mo595a());
                        JobInfo.Builder extras = new JobInfo.Builder(i, ajg.f635a).setRequiredNetworkType(i5).setRequiresCharging(ahb.f475b).setRequiresDeviceIdle(ahb.f476c).setExtras(persistableBundle);
                        if (!ahb.f476c) {
                            extras.setBackoffCriteria(alg.f723l, alg.f729r == 2 ? 0 : 1);
                        }
                        long max = Math.max(alg.mo597c() - System.currentTimeMillis(), 0);
                        if (Build.VERSION.SDK_INT <= 28) {
                            extras.setMinimumLatency(max);
                        } else if (max <= 0) {
                            extras.setImportantWhileForeground(true);
                        } else {
                            extras.setMinimumLatency(max);
                        }
                        int i8 = Build.VERSION.SDK_INT;
                        if (ahb.mo459a()) {
                            for (ahc ahc : ahb.f481h.f485a) {
                                extras.addTriggerContentUri(new JobInfo.TriggerContentUri(ahc.f483a, ahc.f484b ? 1 : 0));
                            }
                            extras.setTriggerContentUpdateDelay(ahb.f479f);
                            extras.setTriggerContentMaxDelay(ahb.f480g);
                        }
                        extras.setPersisted(false);
                        int i9 = Build.VERSION.SDK_INT;
                        extras.setRequiresBatteryNotLow(ahb.f477d);
                        extras.setRequiresStorageNotLow(ahb.f478e);
                        JobInfo build = extras.build();
                        iol.m14231a();
                        String.format("Scheduling work ID %s Job ID %s", new Object[]{alg.f713b, Integer.valueOf(i)});
                        this.f638c.schedule(build);
                        int i10 = Build.VERSION.SDK_INT;
                        workDatabase.mo2847g();
                    } else {
                        throw null;
                    }
                }
            } catch (IllegalStateException e) {
                List a2 = m598a(this.f637b, this.f638c);
                if (a2 != null) {
                    i2 = a2.size();
                } else {
                    i2 = 0;
                }
                Locale locale = Locale.getDefault();
                agz.m469a();
                String format = String.format(locale, "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", new Object[]{Integer.valueOf(i2), Integer.valueOf(this.f639d.f554c.mo1226j().mo609b().size()), 20});
                iol.m14231a();
                iol.m14234a(f636a, format, new Throwable[0]);
                throw new IllegalStateException(format, e);
            } catch (Throwable th) {
                workDatabase.mo2846f();
                throw th;
            }
            workDatabase.mo2846f();
        }
    }
}
