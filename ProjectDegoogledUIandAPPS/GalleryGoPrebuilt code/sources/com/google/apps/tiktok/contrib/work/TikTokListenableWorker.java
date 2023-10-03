package com.google.apps.tiktok.contrib.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

/* compiled from: PG */
public final class TikTokListenableWorker extends ListenableWorker {

    /* renamed from: d */
    private final hlz f5307d;

    /* renamed from: e */
    private final gsw f5308e;

    /* renamed from: f */
    private final WorkerParameters f5309f;

    public TikTokListenableWorker(Context context, hlz hlz, gsw gsw, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.f5308e = gsw;
        this.f5307d = hlz;
        this.f5309f = workerParameters;
    }

    /* renamed from: c */
    public final ieh mo1221c() {
        hlj a;
        if (!hnb.m11774a(hnf.f13084a)) {
            hlp a2 = this.f5307d.mo7579a(String.valueOf(this.f5308e.getClass().getName()).concat(":startWork"), hnf.f13084a);
            try {
                String valueOf = String.valueOf(this.f5309f.f1165a);
                String a3 = m5170a(this.f5309f);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(a3).length());
                sb.append("Worker ");
                sb.append(valueOf);
                sb.append(" of type ");
                sb.append(a3);
                a = hnb.m11766a(sb.toString(), hnf.f13084a);
                ieh a4 = a.mo7548a(this.f5308e.mo3844a());
                if (a != null) {
                    a.close();
                }
                if (a2 != null) {
                    a2.close();
                }
                return a4;
            } catch (Throwable th) {
                if (a2 != null) {
                    try {
                        a2.close();
                    } catch (Throwable th2) {
                        ifn.m12935a(th, th2);
                    }
                }
                throw th;
            }
        } else {
            String valueOf2 = String.valueOf(this.f5309f.f1165a);
            String a5 = m5170a(this.f5309f);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 16 + String.valueOf(a5).length());
            sb2.append("Worker ");
            sb2.append(valueOf2);
            sb2.append(" of type ");
            sb2.append(a5);
            hlj a6 = hnb.m11766a(sb2.toString(), hnf.f13084a);
            try {
                ieh a7 = a6.mo7548a(this.f5308e.mo3844a());
                if (a6 != null) {
                    a6.close();
                }
                return a7;
            } catch (Throwable th3) {
                ifn.m12935a(th, th3);
            }
        }
        throw th;
        throw th;
    }

    /* renamed from: a */
    private static String m5170a(WorkerParameters workerParameters) {
        C0292kp kpVar = new C0292kp(workerParameters.f1167c.size());
        for (String str : workerParameters.f1167c) {
            if (str.startsWith("TikTokWorker#")) {
                kpVar.add(str);
            }
        }
        int i = kpVar.f15147b;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        ife.m12877b(z, "Worker has %s tags instead of exactly one.", i);
        return (String) kpVar.iterator().next();
    }
}
