package p000;

/* renamed from: fcy */
/* compiled from: PG */
public final class fcy {

    /* renamed from: a */
    public long f9289a = -1;

    /* renamed from: b */
    private C0309lf f9290b;

    /* renamed from: a */
    public final synchronized void mo5495a(fej fej, Object obj) {
        if (this.f9290b == null) {
            this.f9290b = new C0309lf();
        }
        ife.m12878b(this.f9290b.put(fej, obj) == null, "Metric %s is already set", (Object) fej);
    }
}
