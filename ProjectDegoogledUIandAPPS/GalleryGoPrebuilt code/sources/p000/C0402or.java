package p000;

/* renamed from: or */
/* compiled from: PG */
final class C0402or implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C0416pe f15422a;

    public C0402or(C0416pe peVar) {
        this.f15422a = peVar;
    }

    public final void run() {
        C0416pe peVar = this.f15422a;
        peVar.f15491j.showAtLocation(peVar.f15490i, 55, 0, 0);
        this.f15422a.mo9616s();
        if (this.f15422a.mo9615r()) {
            this.f15422a.f15490i.setAlpha(0.0f);
            C0416pe peVar2 = this.f15422a;
            C0344mn k = C0340mj.m14720k(peVar2.f15490i);
            k.mo9400a(1.0f);
            peVar2.f15493l = k;
            this.f15422a.f15493l.mo9402a((C0345mo) new C0401oq(this));
            return;
        }
        this.f15422a.f15490i.setAlpha(1.0f);
        this.f15422a.f15490i.setVisibility(0);
    }
}
