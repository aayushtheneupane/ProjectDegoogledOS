package p000;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* renamed from: gdk */
/* compiled from: PG */
public final class gdk implements Runnable {

    /* renamed from: a */
    private final View f11025a;

    /* renamed from: b */
    private final int f11026b;

    /* renamed from: c */
    private final /* synthetic */ BottomSheetBehavior f11027c;

    public gdk(BottomSheetBehavior bottomSheetBehavior, View view, int i) {
        this.f11027c = bottomSheetBehavior;
        this.f11025a = view;
        this.f11026b = i;
    }

    public final void run() {
        C0380nw nwVar = this.f11027c.f5176k;
        if (nwVar != null && nwVar.mo9479a()) {
            C0340mj.m14695a(this.f11025a, (Runnable) this);
            return;
        }
        BottomSheetBehavior bottomSheetBehavior = this.f11027c;
        if (bottomSheetBehavior.f5175j == 2) {
            bottomSheetBehavior.mo3616d(this.f11026b);
        }
    }
}
