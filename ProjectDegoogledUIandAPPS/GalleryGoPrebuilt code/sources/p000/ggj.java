package p000;

import android.graphics.Typeface;

/* renamed from: ggj */
/* compiled from: PG */
public final class ggj {

    /* renamed from: a */
    private final Typeface f11251a;

    /* renamed from: b */
    private final gge f11252b;

    /* renamed from: c */
    private boolean f11253c;

    public ggj(gge gge, Typeface typeface) {
        this.f11251a = typeface;
        this.f11252b = gge;
    }

    /* renamed from: a */
    public final void mo6617a() {
        this.f11253c = true;
    }

    /* renamed from: b */
    public final void mo6619b() {
        mo6618a(this.f11251a);
    }

    /* renamed from: a */
    public final void mo6618a(Typeface typeface) {
        if (!this.f11253c) {
            gfv gfv = ((gfu) this.f11252b).f11180a;
            if (gfv.mo6595a(typeface)) {
                gfv.mo6601d();
            }
        }
    }

    public ggj() {
    }
}
