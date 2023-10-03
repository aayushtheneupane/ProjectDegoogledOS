package p000;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* renamed from: abs */
/* compiled from: PG */
public final class abs {

    /* renamed from: a */
    private static final ThreadLocal f103a = new ThreadLocal();

    /* renamed from: b */
    private static final ThreadLocal f104b = new ThreadLocal();

    /* renamed from: a */
    public static void m162a(ViewGroup viewGroup, View view, Rect rect) {
        rect.set(0, 0, view.getWidth(), view.getHeight());
        Matrix matrix = (Matrix) f103a.get();
        if (matrix == null) {
            matrix = new Matrix();
            f103a.set(matrix);
        } else {
            matrix.reset();
        }
        m163a((ViewParent) viewGroup, view, matrix);
        RectF rectF = (RectF) f104b.get();
        if (rectF == null) {
            rectF = new RectF();
            f104b.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    /* renamed from: a */
    private static void m163a(ViewParent viewParent, View view, Matrix matrix) {
        ViewParent parent = view.getParent();
        if ((parent instanceof View) && parent != viewParent) {
            View view2 = (View) parent;
            m163a(viewParent, view2, matrix);
            matrix.preTranslate((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
        }
        matrix.preTranslate((float) view.getLeft(), (float) view.getTop());
        if (!view.getMatrix().isIdentity()) {
            matrix.preConcat(view.getMatrix());
        }
    }
}
