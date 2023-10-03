package p000;

import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* renamed from: ghl */
/* compiled from: PG */
public final class ghl {
    @Deprecated

    /* renamed from: a */
    public float f11380a;
    @Deprecated

    /* renamed from: b */
    public float f11381b;
    @Deprecated

    /* renamed from: c */
    public float f11382c;

    /* renamed from: d */
    public final List f11383d = new ArrayList();
    @Deprecated

    /* renamed from: e */
    private float f11384e;
    @Deprecated

    /* renamed from: f */
    private float f11385f;

    /* renamed from: g */
    private final List f11386g = new ArrayList();

    public ghl() {
        mo6679a();
    }

    /* renamed from: a */
    private final void m10347a(float f) {
        float f2 = this.f11384e;
        if (f2 != f) {
            float f3 = ((f - f2) + 360.0f) % 360.0f;
            if (f3 <= 180.0f) {
                float f4 = this.f11381b;
                float f5 = this.f11382c;
                ghh ghh = new ghh(f4, f5, f4, f5);
                ghh.f11374e = this.f11384e;
                ghh.f11375f = f3;
                this.f11386g.add(new ghf(ghh));
                this.f11384e = f;
            }
        }
    }

    /* renamed from: a */
    public final void mo6683a(ghk ghk, float f, float f2) {
        m10347a(f);
        this.f11386g.add(ghk);
        this.f11384e = f2;
    }

    /* renamed from: a */
    public final void mo6682a(Matrix matrix, Path path) {
        int size = this.f11383d.size();
        for (int i = 0; i < size; i++) {
            ((ghj) this.f11383d.get(i)).mo6676a(matrix, path);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ghk mo6678a(Matrix matrix) {
        m10347a(this.f11385f);
        return new ghe(new ArrayList(this.f11386g), matrix);
    }

    /* renamed from: a */
    public final void mo6680a(float f, float f2) {
        ghi ghi = new ghi();
        ghi.f11376a = f;
        ghi.f11377b = f2;
        this.f11383d.add(ghi);
        ghg ghg = new ghg(ghi, this.f11381b, this.f11382c);
        mo6683a((ghk) ghg, ghg.mo6675a() + 270.0f, ghg.mo6675a() + 270.0f);
        this.f11381b = f;
        this.f11382c = f2;
    }

    /* renamed from: a */
    public final void mo6679a() {
        mo6681a(0.0f, 270.0f, 0.0f);
    }

    /* renamed from: a */
    public final void mo6681a(float f, float f2, float f3) {
        this.f11380a = f;
        this.f11381b = 0.0f;
        this.f11382c = f;
        this.f11384e = f2;
        this.f11385f = (f2 + f3) % 360.0f;
        this.f11383d.clear();
        this.f11386g.clear();
    }
}
