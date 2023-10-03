package p000;

import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: fgt */
/* compiled from: PG */
public final class fgt {

    /* renamed from: a */
    public final CopyOnWriteArrayList f9532a;

    /* renamed from: b */
    public final C0309lf f9533b;

    /* renamed from: c */
    public int f9534c;

    /* renamed from: d */
    public long f9535d;

    /* renamed from: e */
    public boolean f9536e;

    /* renamed from: f */
    private boolean f9537f;

    /* renamed from: g */
    private final fgp f9538g = new fgr(this);

    /* renamed from: h */
    private final fgl f9539h = new fgs(this);

    public fgt(fgq... fgqArr) {
        this.f9532a = new CopyOnWriteArrayList(fgqArr);
        this.f9533b = new C0309lf(1);
        this.f9534c = 1;
        fgqArr[0].mo5598a(this.f9538g);
        this.f9533b.put(fgqArr[0], true);
    }

    /* renamed from: c */
    public final void mo5604c() {
        if (!this.f9536e && this.f9537f && this.f9534c != 0) {
            this.f9536e = true;
            fgn.m8785a().mo5591a(this.f9539h);
        }
    }

    /* renamed from: a */
    public final void mo5602a() {
        if (!this.f9537f) {
            this.f9537f = true;
            this.f9535d = -1;
            mo5604c();
        }
    }

    /* renamed from: b */
    public final void mo5603b() {
        if (this.f9537f) {
            if (this.f9536e) {
                this.f9536e = false;
                fgn.m8785a().mo5592b(this.f9539h);
            }
            this.f9537f = false;
        }
    }
}
