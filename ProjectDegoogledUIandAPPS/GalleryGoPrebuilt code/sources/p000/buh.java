package p000;

import java.util.concurrent.Executor;

/* renamed from: buh */
/* compiled from: PG */
public final class buh implements gud {

    /* renamed from: a */
    public final /* synthetic */ bui f3616a;

    /* renamed from: b */
    private final Object f3617b = new Object();

    /* renamed from: c */
    private ieh f3618c = null;

    /* renamed from: d */
    private final /* synthetic */ cxd f3619d;

    public buh(bui bui, cxd cxd) {
        this.f3616a = bui;
        this.f3619d = cxd;
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        ieh b;
        synchronized (this.f3617b) {
            ieh ieh = this.f3618c;
            if (ieh != null) {
                ieh.cancel(true);
            }
            b = this.f3616a.f3620a.mo2731b(this.f3619d);
            this.f3618c = b;
        }
        cwn.m5509a(b, "SingleDeviceFolderFragmentDataService: Failed to fetch folder [filter=%s].", this.f3619d);
        return this.f3618c;
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return guj.m10828a("SINGLE_DEVICE_FOLDER_FRAGMENT_DATA_SERVICE_KEY", "DEVICE_FOLDERS_DATA_SERVICE_KEY");
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        synchronized (this.f3617b) {
            ieh ieh = this.f3618c;
            if (ieh == null) {
                gpc a = gpc.m10579a((Object) guc.f12067a);
                return a;
            } else if (!ieh.isCancelled()) {
                gpc a2 = gpc.m10577a(gte.m10770a(this.f3618c, (hpr) new bug(this), (Executor) idh.f13918a));
                return a2;
            } else {
                gpc a3 = gpc.m10579a((Object) guc.f12067a);
                return a3;
            }
        }
    }
}
