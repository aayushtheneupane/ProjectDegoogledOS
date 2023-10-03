package p000;

import java.util.Set;

/* renamed from: glw */
/* compiled from: PG */
final class glw implements idw {

    /* renamed from: a */
    private final /* synthetic */ glx f11593a;

    public glw(glx glx) {
        this.f11593a = glx;
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
    }

    /* renamed from: a */
    public final void mo3867a(Object obj) {
        for (glk a : (Set) this.f11593a.f11597d.mo2097a()) {
            goo.m10562a(a.mo6820a(), "AvailableAccountsInvalidatedObserver failed", new Object[0]);
        }
    }
}
