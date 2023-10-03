package p000;

import android.os.Parcelable;

/* renamed from: dnm */
/* compiled from: PG */
final class dnm implements gry {

    /* renamed from: a */
    private final /* synthetic */ dnn f6869a;

    public dnm(dnn dnn) {
        this.f6869a = dnn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        imh imh = (imh) obj;
        cwn.m5515b(th, "OneUpFragment: Failed to get permission to delete file.", new Object[0]);
        this.f6869a.f6883M = false;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2649a(Object obj) {
        imh imh = (imh) obj;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2650a(Object obj, Object obj2) {
        imh imh = (imh) obj;
        if (!((Boolean) obj2).booleanValue()) {
            this.f6869a.f6883M = false;
            return;
        }
        cyd cyd = (cyd) imh.mo8995a(cyd.f5985i, this.f6869a.f6909r);
        this.f6869a.f6908q.mo6986a(grw.m10691f(this.f6869a.f6907p.mo4012a(cyd)), grt.m10682a((Parcelable) imi.m14101a((ikf) cyd)), this.f6869a.f6874D);
    }
}
