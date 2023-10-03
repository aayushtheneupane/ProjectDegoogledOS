package p000;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.google.apps.tiktok.contrib.work.TikTokListenableWorker;
import java.util.Map;

/* renamed from: gtd */
/* compiled from: PG */
public final class gtd extends ahv {

    /* renamed from: a */
    private final /* synthetic */ hlz f12005a;

    /* renamed from: b */
    private final /* synthetic */ Map f12006b;

    public gtd(hlz hlz, Map map) {
        this.f12005a = hlz;
        this.f12006b = map;
    }

    /* renamed from: a */
    public final ListenableWorker mo501a(Context context, String str, WorkerParameters workerParameters) {
        hlp a = this.f12005a.mo7579a("WorkerFactory.createWorker()", hnf.f13084a);
        try {
            C0292kp kpVar = new C0292kp(workerParameters.f1167c.size());
            for (String str2 : workerParameters.f1167c) {
                if (str2.startsWith("TikTokWorker#")) {
                    kpVar.add(str2.replace("TikTokWorker#", ""));
                }
            }
            if (kpVar.isEmpty()) {
                throw new IllegalArgumentException("Worker is untagged.");
            } else if (kpVar.f15147b <= 1) {
                iqk iqk = (iqk) this.f12006b.get((String) kpVar.iterator().next());
                if (iqk != null) {
                    TikTokListenableWorker tikTokListenableWorker = new TikTokListenableWorker(context, this.f12005a, (gsw) iqk.mo2097a(), workerParameters);
                    if (a != null) {
                        a.close();
                    }
                    return tikTokListenableWorker;
                }
                throw new IllegalArgumentException(String.valueOf(str).concat(" is not registered in this version of the application. This suggests that an upgrade has broken ABI. Reach out to #tiktok on YAQS, or g/tiktok-users for help with this error."));
            } else {
                throw new IllegalArgumentException("More than one type was set as the tag on a worker.");
            }
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
