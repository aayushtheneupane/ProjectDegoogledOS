package p000;

import android.os.Build;
import android.view.View;

/* renamed from: agf */
/* compiled from: PG */
class agf extends age {

    /* renamed from: a */
    private static boolean f372a = true;

    /* renamed from: a */
    public void mo350a(View view, int i) {
        if (Build.VERSION.SDK_INT == 28) {
            super.mo350a(view, i);
        } else if (f372a) {
            try {
                view.setTransitionVisibility(i);
            } catch (NoSuchMethodError e) {
                f372a = false;
            }
        }
    }
}
