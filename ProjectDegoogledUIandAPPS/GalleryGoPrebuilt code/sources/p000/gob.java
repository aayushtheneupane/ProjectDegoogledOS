package p000;

/* renamed from: gob */
/* compiled from: PG */
final /* synthetic */ class gob implements Runnable {

    /* renamed from: a */
    private final hlz f11719a;

    /* renamed from: b */
    private final String f11720b;

    /* renamed from: c */
    private final Runnable f11721c;

    public gob(hlz hlz, String str, Runnable runnable) {
        this.f11719a = hlz;
        this.f11720b = str;
        this.f11721c = runnable;
    }

    public final void run() {
        hlz hlz = this.f11719a;
        String str = this.f11720b;
        Runnable runnable = this.f11721c;
        hlp a = hlz.mo7577a(str);
        try {
            runnable.run();
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
