package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: gok */
/* compiled from: PG */
public final /* synthetic */ class gok implements Runnable {

    /* renamed from: a */
    private final ieh f11739a;

    /* renamed from: b */
    private final TimeUnit f11740b;

    public gok(ieh ieh, TimeUnit timeUnit) {
        this.f11739a = ieh;
        this.f11740b = timeUnit;
    }

    public final void run() {
        ieh ieh = this.f11739a;
        TimeUnit timeUnit = this.f11740b;
        if (!ieh.isDone()) {
            ((hvv) ((hvv) ((hvv) goo.f11748a.mo8178a()).mo8202a((Throwable) hmw.m11757a())).mo8201a("com/google/apps/tiktok/concurrent/AndroidFutures", "lambda$crashApplicationOnFailure$1", 318, "AndroidFutures.java")).mo8207a("Timeout exceeded waiting on crashApplicationOnFailure future. Waited %s %s. Allowing future %s to continue anyway.", (Object) 30L, (Object) timeUnit, (Object) ieh);
        }
    }
}
