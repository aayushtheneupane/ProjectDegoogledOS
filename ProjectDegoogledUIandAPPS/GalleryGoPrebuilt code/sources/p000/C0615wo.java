package p000;

/* renamed from: wo */
/* compiled from: PG */
final class C0615wo implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0616wp f16241a;

    public C0615wo(C0616wp wpVar) {
        this.f16241a = wpVar;
    }

    public final void run() {
        C0582vi viVar = this.f16241a.f16247e;
        if (viVar != null && C0340mj.m14735z(viVar) && this.f16241a.f16247e.getCount() > this.f16241a.f16247e.getChildCount()) {
            int childCount = this.f16241a.f16247e.getChildCount();
            C0616wp wpVar = this.f16241a;
            if (childCount <= wpVar.f16253k) {
                wpVar.f16259q.setInputMethodMode(2);
                this.f16241a.mo9805ab();
            }
        }
    }
}
