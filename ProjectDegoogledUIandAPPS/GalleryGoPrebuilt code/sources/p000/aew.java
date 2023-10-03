package p000;

import android.view.ViewGroup;

/* renamed from: aew */
/* compiled from: PG */
final class aew extends afm {

    /* renamed from: a */
    private boolean f281a = false;

    /* renamed from: b */
    private final /* synthetic */ ViewGroup f282b;

    public aew(ViewGroup viewGroup) {
        this.f282b = viewGroup;
    }

    /* renamed from: a */
    public final void mo264a() {
        afy.m414a(this.f282b, false);
        this.f281a = true;
    }

    /* renamed from: a */
    public final void mo265a(afl afl) {
        if (!this.f281a) {
            afy.m414a(this.f282b, false);
        }
        afl.mo312b((afk) this);
    }

    /* renamed from: b */
    public final void mo266b() {
        afy.m414a(this.f282b, false);
    }

    /* renamed from: c */
    public final void mo267c() {
        afy.m414a(this.f282b, true);
    }
}
