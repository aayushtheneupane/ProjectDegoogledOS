package p000a.p001a.p002a.p003a;

/* renamed from: a.a.a.a.c */
public class C0002c extends C0005f {
    private static volatile C0002c sInstance;

    /* renamed from: Mn */
    private C0005f f0Mn = new C0004e();
    private C0005f mDelegate = this.f0Mn;

    static {
        new C0000a();
        new C0001b();
    }

    private C0002c() {
    }

    public static C0002c getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (C0002c.class) {
            if (sInstance == null) {
                sInstance = new C0002c();
            }
        }
        return sInstance;
    }

    /* renamed from: e */
    public void mo3e(Runnable runnable) {
        this.mDelegate.mo3e(runnable);
    }

    /* renamed from: f */
    public void mo4f(Runnable runnable) {
        this.mDelegate.mo4f(runnable);
    }

    /* renamed from: oc */
    public boolean mo5oc() {
        return this.mDelegate.mo5oc();
    }
}
