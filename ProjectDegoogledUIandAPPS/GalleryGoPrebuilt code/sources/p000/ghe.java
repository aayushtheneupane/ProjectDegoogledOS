package p000;

import android.graphics.Canvas;
import android.graphics.Matrix;
import java.util.List;

/* renamed from: ghe */
/* compiled from: PG */
final class ghe extends ghk {

    /* renamed from: a */
    private final /* synthetic */ List f11363a;

    /* renamed from: b */
    private final /* synthetic */ Matrix f11364b;

    public ghe(List list, Matrix matrix) {
        this.f11363a = list;
        this.f11364b = matrix;
    }

    /* renamed from: a */
    public final void mo6674a(Matrix matrix, ggl ggl, int i, Canvas canvas) {
        List list = this.f11363a;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ghk) list.get(i2)).mo6674a(this.f11364b, ggl, i, canvas);
        }
    }
}
