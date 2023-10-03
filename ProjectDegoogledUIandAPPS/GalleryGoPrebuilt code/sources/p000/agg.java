package p000;

import android.graphics.Matrix;
import android.view.View;

/* renamed from: agg */
/* compiled from: PG */
final class agg extends agf {
    /* renamed from: a */
    public final float mo345a(View view) {
        return view.getTransitionAlpha();
    }

    /* renamed from: a */
    public final void mo349a(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    /* renamed from: a */
    public final void mo346a(View view, float f) {
        view.setTransitionAlpha(f);
    }

    /* renamed from: a */
    public final void mo350a(View view, int i) {
        view.setTransitionVisibility(i);
    }

    /* renamed from: a */
    public final void mo347a(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    /* renamed from: b */
    public final void mo348b(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
