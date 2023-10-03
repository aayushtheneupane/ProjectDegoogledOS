package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: idg */
/* compiled from: PG */
final class idg extends ibz {

    /* renamed from: h */
    public static /* synthetic */ int f13916h;

    /* renamed from: g */
    public idf f13917g;

    public idg(hsf hsf, boolean z, Executor executor, ice ice) {
        super(hsf, z, false);
        this.f13917g = new idd(this, ice, executor);
        mo8368f();
    }

    /* renamed from: a */
    public final void mo8363a(int i, Object obj) {
    }

    public idg(hsf hsf, boolean z, Executor executor, Callable callable) {
        super(hsf, z, false);
        this.f13917g = new ide(this, callable, executor);
        mo8368f();
    }

    /* renamed from: g */
    public final void mo8369g() {
        idf idf = this.f13917g;
        if (idf != null) {
            idf.mo8411d();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo8347c() {
        idf idf = this.f13917g;
        if (idf != null) {
            idf.mo8452e();
        }
    }

    /* renamed from: a */
    public final void mo8366a(iby iby) {
        super.mo8366a(iby);
        if (iby == iby.OUTPUT_FUTURE_DONE) {
            this.f13917g = null;
        }
    }
}
