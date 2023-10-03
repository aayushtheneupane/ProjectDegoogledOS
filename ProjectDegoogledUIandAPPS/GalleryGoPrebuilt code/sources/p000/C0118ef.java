package p000;

/* renamed from: ef */
/* compiled from: PG */
public final class C0118ef extends C0116ed {

    /* renamed from: ac */
    public float f8133ac = -1.0f;

    /* renamed from: ad */
    public int f8134ad = -1;

    /* renamed from: ae */
    public int f8135ae = -1;

    /* renamed from: af */
    public int f8136af = 0;

    /* renamed from: ag */
    private C0115ec f8137ag = this.f7996j;

    public C0118ef() {
        this.f8000n.clear();
        this.f8000n.add(this.f8137ag);
    }

    /* renamed from: e */
    public final C0115ec mo4705e(int i) {
        int i2 = i - 1;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return null;
                    }
                }
            }
            if (this.f8136af == 0) {
                return this.f8137ag;
            }
            return null;
        }
        if (this.f8136af == 1) {
            return this.f8137ag;
        }
        return null;
    }

    /* renamed from: a */
    public final void mo4695a(C0110dy dyVar) {
        C0116ed edVar = this.f8001o;
        if (edVar != null) {
            C0115ec e = edVar.mo4705e(2);
            C0115ec e2 = edVar.mo4705e(4);
            if (this.f8136af == 0) {
                e = edVar.mo4705e(3);
                e2 = edVar.mo4705e(5);
            }
            if (this.f8134ad != -1) {
                dyVar.mo4556a(C0110dy.m6886a(dyVar, dyVar.mo4554a((Object) this.f8137ag), dyVar.mo4554a((Object) e), this.f8134ad, false));
            } else if (this.f8135ae != -1) {
                dyVar.mo4556a(C0110dy.m6886a(dyVar, dyVar.mo4554a((Object) this.f8137ag), dyVar.mo4554a((Object) e2), -this.f8135ae, false));
            } else if (this.f8133ac != -1.0f) {
                dyVar.mo4556a(C0110dy.m6887a(dyVar, dyVar.mo4554a((Object) this.f8137ag), dyVar.mo4554a((Object) e), dyVar.mo4554a((Object) e2), this.f8133ac));
            }
        }
    }

    /* renamed from: h */
    public final void mo4761h(int i) {
        if (this.f8136af != i) {
            this.f8136af = i;
            this.f8000n.clear();
            if (this.f8136af == 1) {
                this.f8137ag = this.f7995i;
            } else {
                this.f8137ag = this.f7996j;
            }
            this.f8000n.add(this.f8137ag);
        }
    }

    /* renamed from: m */
    public final void mo4715m() {
        if (this.f8001o != null) {
            int b = C0110dy.m6890b(this.f8137ag);
            if (this.f8136af != 1) {
                this.f8006t = 0;
                this.f8007u = b;
                mo4691a(this.f8001o.mo4699c());
                mo4696b(0);
                return;
            }
            this.f8006t = b;
            this.f8007u = 0;
            mo4696b(this.f8001o.mo4706f());
            mo4691a(0);
        }
    }
}
