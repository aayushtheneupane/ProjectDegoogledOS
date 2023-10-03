package p000;

/* renamed from: frx */
/* compiled from: PG */
public class frx {

    /* renamed from: a */
    public float f10337a;

    /* renamed from: b */
    public float f10338b;

    /* renamed from: c */
    public float f10339c;

    /* renamed from: d */
    public float f10340d;

    /* renamed from: e */
    public float f10341e;

    /* renamed from: f */
    public float f10342f;

    public frx() {
    }

    /* renamed from: a */
    public final float mo6085a(float f) {
        if (f == 0.0f) {
            return 0.0f;
        }
        if (f == 1.0f) {
            return this.f10341e;
        }
        float f2 = this.f10337a;
        float f3 = ((f2 + 0.0f) * f) + 0.0f;
        float f4 = this.f10339c;
        float f5 = f2 + ((f4 - f2) * f);
        float f6 = f3 + ((f5 - f3) * f);
        return f6 + (f * ((f5 + (((f4 + ((this.f10341e - f4) * f)) - f5) * f)) - f6));
    }

    private frx(frx frx) {
        this.f10337a = frx.f10337a;
        this.f10338b = frx.f10338b;
        this.f10339c = frx.f10339c;
        this.f10340d = frx.f10340d;
        this.f10341e = frx.f10341e;
        this.f10342f = frx.f10342f;
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        return new frx(this);
    }
}
