package p000;

import android.os.Build;
import android.view.ViewGroup;

/* renamed from: afy */
/* compiled from: PG */
final class afy {

    /* renamed from: a */
    private static boolean f363a = true;

    /* renamed from: a */
    static afx m413a(ViewGroup viewGroup) {
        int i = Build.VERSION.SDK_INT;
        return new afw(viewGroup);
    }

    /* renamed from: b */
    private static void m415b(ViewGroup viewGroup, boolean z) {
        if (f363a) {
            try {
                viewGroup.suppressLayout(z);
            } catch (NoSuchMethodError e) {
                f363a = false;
            }
        }
    }

    /* renamed from: a */
    static void m414a(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            viewGroup.suppressLayout(z);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        m415b(viewGroup, z);
    }
}
