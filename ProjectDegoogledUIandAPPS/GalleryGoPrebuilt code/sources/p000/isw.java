package p000;

import android.os.Build;
import android.view.View;

/* renamed from: isw */
/* compiled from: PG */
public class isw {
    /* renamed from: a */
    public static int m14417a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i != 4) {
            return i != 5 ? 0 : 6;
        }
        return 5;
    }

    /* renamed from: a */
    public static void m14418a(View view, Runnable runnable) {
        int i = Build.VERSION.SDK_INT;
        view.postOnAnimation(runnable);
    }
}
