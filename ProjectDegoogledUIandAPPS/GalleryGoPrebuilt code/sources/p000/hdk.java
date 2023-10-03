package p000;

import android.util.Log;

/* renamed from: hdk */
/* compiled from: PG */
final class hdk implements idw {

    /* renamed from: a */
    public final /* synthetic */ hdm f12531a;

    /* renamed from: b */
    private final /* synthetic */ ifz f12532b;

    /* renamed from: c */
    private final /* synthetic */ int f12533c;

    public hdk(hdm hdm, ifz ifz, int i) {
        this.f12531a = hdm;
        this.f12532b = ifz;
        this.f12533c = i;
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
        Log.e("TikTokClientLogging", "Error while logging.", th);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo3867a(Object obj) {
        hcs hcs = new hcs(this.f12532b, this.f12533c, (String) obj);
        hdm hdm = this.f12531a;
        ieh a = hde.m11231a(hcs, hdm.f12535a, hdm.f12540f, hdm.f12539e, hdm.f12541g, "CLIENT_LOGGING_PROD");
        this.f12531a.f12542h.put(a, hcs);
        a.mo53a(hmq.m11748a((Runnable) new hdj(this, a)), idh.f13918a);
    }
}
