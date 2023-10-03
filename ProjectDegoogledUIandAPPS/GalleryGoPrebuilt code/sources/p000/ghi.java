package p000;

import android.graphics.Matrix;
import android.graphics.Path;

/* renamed from: ghi */
/* compiled from: PG */
public final class ghi extends ghj {

    /* renamed from: a */
    public float f11376a;

    /* renamed from: b */
    public float f11377b;

    /* renamed from: a */
    public final void mo6676a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f11378g;
        matrix.invert(matrix2);
        path.transform(matrix2);
        path.lineTo(this.f11376a, this.f11377b);
        path.transform(matrix);
    }
}
