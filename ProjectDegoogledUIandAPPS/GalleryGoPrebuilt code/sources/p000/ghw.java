package p000;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

/* renamed from: ghw */
/* compiled from: PG */
final class ghw implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ gik f11395a;

    public ghw(gik gik) {
        this.f11395a = gik;
    }

    public final void run() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f11395a.f11417d.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        gik gik = this.f11395a;
        int[] iArr = new int[2];
        gik.f11418e.getLocationOnScreen(iArr);
        int height = i - (iArr[1] + gik.f11418e.getHeight());
        gik gik2 = this.f11395a;
        if (height < gik2.f11425l) {
            ViewGroup.LayoutParams layoutParams = gik2.f11418e.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                Log.w(gik.f11414b, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                return;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += this.f11395a.f11425l - height;
            this.f11395a.f11418e.requestLayout();
        }
    }
}
