package p000;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: aiu */
/* compiled from: PG */
public final class aiu implements aia, ajk, ahw {

    /* renamed from: a */
    private final Context f592a;

    /* renamed from: b */
    private final aip f593b;

    /* renamed from: c */
    private final ajl f594c;

    /* renamed from: d */
    private final List f595d = new ArrayList();

    /* renamed from: e */
    private boolean f596e;

    /* renamed from: f */
    private final Object f597f;

    /* renamed from: g */
    private Boolean f598g;

    static {
        iol.m14236b("GreedyScheduler");
    }

    public aiu(Context context, amz amz, aip aip) {
        this.f592a = context;
        this.f593b = aip;
        this.f594c = new ajl(context, amz, this);
        this.f597f = new Object();
    }

    /* renamed from: a */
    public final void mo515a(String str) {
        if (this.f598g == null) {
            this.f598g = Boolean.valueOf(TextUtils.equals(this.f592a.getPackageName(), m565b()));
        }
        if (!this.f598g.booleanValue()) {
            iol.m14231a();
            return;
        }
        m564a();
        iol.m14231a();
        String.format("Cancelling work ID %s", new Object[]{str});
        this.f593b.mo525b(str);
    }

    /* renamed from: b */
    private final String m565b() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) this.f592a.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.isEmpty()) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == myPid) {
                return next.processName;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final void mo531a(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str = (String) list.get(i);
            iol.m14231a();
            String.format("Constraints met: Scheduling work ID %s", new Object[]{str});
            this.f593b.mo523a(str);
        }
    }

    /* renamed from: b */
    public final void mo532b(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str = (String) list.get(i);
            iol.m14231a();
            String.format("Constraints not met: Cancelling work ID %s", new Object[]{str});
            this.f593b.mo525b(str);
        }
    }

    /* renamed from: a */
    public final void mo503a(String str, boolean z) {
        synchronized (this.f597f) {
            int size = this.f595d.size();
            int i = 0;
            while (true) {
                if (i < size) {
                    if (((alg) this.f595d.get(i)).f713b.equals(str)) {
                        iol.m14231a();
                        String.format("Stopping tracking for %s", new Object[]{str});
                        this.f595d.remove(i);
                        this.f594c.mo552a((Iterable) this.f595d);
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
    }

    /* renamed from: a */
    private final void m564a() {
        if (!this.f596e) {
            this.f593b.f557f.mo506a(this);
            this.f596e = true;
        }
    }

    /* renamed from: a */
    public final void mo516a(alg... algArr) {
        if (this.f598g == null) {
            this.f598g = Boolean.valueOf(TextUtils.equals(this.f592a.getPackageName(), m565b()));
        }
        if (!this.f598g.booleanValue()) {
            iol.m14231a();
            return;
        }
        m564a();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (alg alg : algArr) {
            if (alg.f728q == 1 && !alg.mo595a() && alg.f718g == 0 && !alg.mo596b()) {
                if (alg.mo598d()) {
                    int i = Build.VERSION.SDK_INT;
                    if (alg.f721j.f476c) {
                        iol.m14231a();
                        String.format("Ignoring WorkSpec %s, Requires device idle.", new Object[]{alg});
                    } else {
                        int i2 = Build.VERSION.SDK_INT;
                        if (alg.f721j.mo459a()) {
                            iol.m14231a();
                            String.format("Ignoring WorkSpec %s, Requires ContentUri triggers.", new Object[]{alg});
                        } else {
                            arrayList.add(alg);
                            arrayList2.add(alg.f713b);
                        }
                    }
                } else {
                    iol.m14231a();
                    String.format("Starting work for %s", new Object[]{alg.f713b});
                    this.f593b.mo523a(alg.f713b);
                }
            }
        }
        synchronized (this.f597f) {
            if (!arrayList.isEmpty()) {
                iol.m14231a();
                String.format("Starting tracking for [%s]", new Object[]{TextUtils.join(",", arrayList2)});
                this.f595d.addAll(arrayList);
                this.f594c.mo552a((Iterable) this.f595d);
            }
        }
    }
}
