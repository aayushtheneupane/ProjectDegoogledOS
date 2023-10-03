package p000;

/* renamed from: eau */
/* compiled from: PG */
public final class eau {

    /* renamed from: a */
    private eaq f7798a;

    /* renamed from: b */
    private final /* synthetic */ eav f7799b;

    /* renamed from: c */
    private int f7800c = 1;

    public /* synthetic */ eau(eav eav) {
        this.f7799b = eav;
    }

    /* renamed from: a */
    public final int mo4633a(int i) {
        int i2 = this.f7800c;
        int i3 = i2 - 1;
        if (i2 == 0) {
            throw null;
        } else if (i3 == 0) {
            this.f7798a = (eaq) this.f7799b.f7803c.get(i);
            this.f7800c = 2;
            return 3;
        } else if (i3 != 1) {
            if (i3 == 2) {
                return 2;
            }
            throw new IllegalStateException("Inconceivable!");
        } else if (this.f7798a.mo4234a((eaq) this.f7799b.f7803c.get(i))) {
            return 3;
        } else {
            this.f7800c = 3;
            return 2;
        }
    }
}
