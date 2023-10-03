package p000;

/* renamed from: eb */
/* compiled from: PG */
public final class C0114eb {

    /* renamed from: a */
    public int f7813a = -1;

    /* renamed from: b */
    public int f7814b = -1;

    /* renamed from: c */
    public int f7815c = 0;

    /* renamed from: d */
    public float f7816d;

    /* renamed from: e */
    public final float[] f7817e = new float[6];

    /* renamed from: f */
    public C0107dv[] f7818f = new C0107dv[8];

    /* renamed from: g */
    public int f7819g = 0;

    /* renamed from: h */
    public int f7820h;

    public C0114eb(int i) {
        this.f7820h = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4646a(C0107dv dvVar) {
        int i = 0;
        int i2 = 0;
        while (i2 < this.f7819g) {
            if (this.f7818f[i2] != dvVar) {
                i2++;
            } else {
                while (true) {
                    int i3 = this.f7819g;
                    if (i < (i3 - i2) - 1) {
                        C0107dv[] dvVarArr = this.f7818f;
                        int i4 = i2 + i;
                        dvVarArr[i4] = dvVarArr[i4 + 1];
                        i++;
                    } else {
                        this.f7819g = i3 - 1;
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo4645a() {
        this.f7820h = 5;
        this.f7815c = 0;
        this.f7813a = -1;
        this.f7814b = -1;
        this.f7816d = 0.0f;
        this.f7819g = 0;
    }

    public final String toString() {
        return "null";
    }
}
