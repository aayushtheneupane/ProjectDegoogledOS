package p000;

import java.lang.ref.WeakReference;

/* renamed from: giq */
/* compiled from: PG */
final class giq {

    /* renamed from: a */
    public final WeakReference f11435a;

    /* renamed from: b */
    public int f11436b;

    /* renamed from: c */
    public boolean f11437c;

    public giq(int i, gip gip) {
        this.f11435a = new WeakReference(gip);
        this.f11436b = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo6722a(gip gip) {
        return gip != null && this.f11435a.get() == gip;
    }
}
