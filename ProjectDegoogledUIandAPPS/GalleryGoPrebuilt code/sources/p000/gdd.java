package p000;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* renamed from: gdd */
/* compiled from: PG */
public final class gdd implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ View f11012a;

    /* renamed from: b */
    private final /* synthetic */ int f11013b;

    /* renamed from: c */
    private final /* synthetic */ BottomSheetBehavior f11014c;

    public gdd(BottomSheetBehavior bottomSheetBehavior, View view, int i) {
        this.f11014c = bottomSheetBehavior;
        this.f11012a = view;
        this.f11013b = i;
    }

    public final void run() {
        this.f11014c.mo3610a(this.f11012a, this.f11013b);
    }
}
