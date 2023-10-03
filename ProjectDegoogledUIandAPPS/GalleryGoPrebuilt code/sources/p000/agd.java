package p000;

import android.graphics.Matrix;
import android.view.View;

/* renamed from: agd */
/* compiled from: PG */
class agd extends agc {

    /* renamed from: a */
    private static boolean f369a = true;

    /* renamed from: b */
    private static boolean f370b = true;

    /* renamed from: a */
    public void mo347a(View view, Matrix matrix) {
        if (f369a) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError e) {
                f369a = false;
            }
        }
    }

    /* renamed from: b */
    public void mo348b(View view, Matrix matrix) {
        if (f370b) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError e) {
                f370b = false;
            }
        }
    }
}
