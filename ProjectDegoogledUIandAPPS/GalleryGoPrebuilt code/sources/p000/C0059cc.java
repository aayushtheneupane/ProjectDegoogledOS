package p000;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: cc */
/* compiled from: PG */
public abstract class C0059cc {

    /* renamed from: a */
    private final AtomicBoolean f4037a = new AtomicBoolean(false);

    /* renamed from: b */
    private final C0053bx f4038b;

    /* renamed from: c */
    private volatile C0037bh f4039c;

    public C0059cc(C0053bx bxVar) {
        this.f4038b = bxVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract String mo578a();

    /* renamed from: b */
    public final C0037bh mo3016b() {
        this.f4038b.mo2843c();
        if (!this.f4037a.compareAndSet(false, true)) {
            return m4021c();
        }
        if (this.f4039c == null) {
            this.f4039c = m4021c();
        }
        return this.f4039c;
    }

    /* renamed from: c */
    private final C0037bh m4021c() {
        return this.f4038b.mo2841a(mo578a());
    }

    /* renamed from: a */
    public final void mo3015a(C0037bh bhVar) {
        if (bhVar == this.f4039c) {
            this.f4037a.set(false);
        }
    }
}
