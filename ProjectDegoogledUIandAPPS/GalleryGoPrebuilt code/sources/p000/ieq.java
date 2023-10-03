package p000;

/* renamed from: ieq */
/* compiled from: PG */
final class ieq extends ibn implements Runnable {

    /* renamed from: a */
    private final Runnable f13965a;

    public ieq(Runnable runnable) {
        this.f13965a = (Runnable) ife.m12898e((Object) runnable);
    }

    public final void run() {
        try {
            this.f13965a.run();
        } catch (Throwable th) {
            mo6952a(th);
            throw hqo.m11927b(th);
        }
    }
}
