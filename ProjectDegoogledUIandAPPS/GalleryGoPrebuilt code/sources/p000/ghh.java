package p000;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/* renamed from: ghh */
/* compiled from: PG */
public final class ghh extends ghj {

    /* renamed from: h */
    private static final RectF f11369h = new RectF();
    @Deprecated

    /* renamed from: a */
    public final float f11370a;
    @Deprecated

    /* renamed from: b */
    public final float f11371b;
    @Deprecated

    /* renamed from: c */
    public final float f11372c;
    @Deprecated

    /* renamed from: d */
    public final float f11373d;
    @Deprecated

    /* renamed from: e */
    public float f11374e;
    @Deprecated

    /* renamed from: f */
    public float f11375f;

    public ghh(float f, float f2, float f3, float f4) {
        this.f11370a = f;
        this.f11371b = f2;
        this.f11372c = f3;
        this.f11373d = f4;
    }

    /* renamed from: a */
    public final void mo6676a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f11378g;
        matrix.invert(matrix2);
        path.transform(matrix2);
        f11369h.set(this.f11370a, this.f11371b, this.f11372c, this.f11373d);
        path.arcTo(f11369h, this.f11374e, this.f11375f, false);
        path.transform(matrix);
    }
}
