package p000;

/* renamed from: asw */
/* compiled from: PG */
final class asw {

    /* renamed from: a */
    private boolean f1560a;

    /* renamed from: b */
    private boolean f1561b;

    /* renamed from: c */
    private boolean f1562c;

    /* renamed from: e */
    private final boolean m1582e() {
        return (this.f1562c || this.f1561b) && this.f1560a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized boolean mo1567a() {
        this.f1561b = true;
        return m1582e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized boolean mo1568b() {
        this.f1562c = true;
        return m1582e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final synchronized boolean mo1570d() {
        this.f1560a = true;
        return m1582e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final synchronized void mo1569c() {
        this.f1561b = false;
        this.f1560a = false;
        this.f1562c = false;
    }
}
