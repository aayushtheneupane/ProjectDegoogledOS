package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: s */
/* compiled from: PG */
final class C0492s implements C0627x {

    /* renamed from: a */
    private final C0465r f15831a;

    /* renamed from: b */
    private final C0627x f15832b;

    public C0492s(C0465r rVar, C0627x xVar) {
        this.f15831a = rVar;
        this.f15832b = xVar;
    }

    /* renamed from: a */
    public final void mo3a(C0681z zVar, C0546u uVar) {
        C0546u uVar2 = C0546u.ON_CREATE;
        switch (uVar.ordinal()) {
            case 0:
                this.f15831a.mo2556a();
                break;
            case 1:
                this.f15831a.mo2557a(zVar);
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                this.f15831a.mo2560c();
                break;
            case 3:
                this.f15831a.mo2558b();
                break;
            case 4:
                this.f15831a.mo2559b(zVar);
                break;
            case 5:
                this.f15831a.mo2561c(zVar);
                break;
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        C0627x xVar = this.f15832b;
        if (xVar != null) {
            xVar.mo3a(zVar, uVar);
        }
    }
}
