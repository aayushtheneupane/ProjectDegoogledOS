package p000;

import android.view.View;

/* renamed from: agc */
/* compiled from: PG */
class agc extends gbz {

    /* renamed from: a */
    private static boolean f368a = true;

    /* renamed from: a */
    public float mo345a(View view) {
        if (f368a) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError e) {
                f368a = false;
            }
        }
        return view.getAlpha();
    }

    /* renamed from: a */
    public void mo346a(View view, float f) {
        if (f368a) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError e) {
                f368a = false;
            }
        }
        view.setAlpha(f);
    }
}
