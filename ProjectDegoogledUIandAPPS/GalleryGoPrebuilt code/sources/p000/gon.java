package p000;

import java.util.concurrent.TimeoutException;

/* renamed from: gon */
/* compiled from: PG */
final class gon implements idw {

    /* renamed from: a */
    private final /* synthetic */ ieh f11746a;

    /* renamed from: b */
    private final /* synthetic */ String f11747b;

    public gon(ieh ieh, String str) {
        this.f11746a = ieh;
        this.f11747b = str;
    }

    /* renamed from: a */
    public final void mo3867a(Object obj) {
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
        if ((th instanceof TimeoutException) && !this.f11746a.isDone()) {
            ((hvv) ((hvv) ((hvv) goo.f11748a.mo8178a()).mo8202a(th)).mo8201a("com/google/apps/tiktok/concurrent/AndroidFutures$1", "onFailure", 180, "AndroidFutures.java")).mo8206a("exceeded timeout: %s", (Object) this.f11747b);
        }
    }
}
