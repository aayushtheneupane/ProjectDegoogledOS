package p000;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.lang.ref.WeakReference;

/* renamed from: gdf */
/* compiled from: PG */
public final class gdf extends C0379nv {

    /* renamed from: a */
    private final /* synthetic */ BottomSheetBehavior f11016a;

    public gdf(BottomSheetBehavior bottomSheetBehavior) {
        this.f11016a = bottomSheetBehavior;
    }

    /* renamed from: a */
    public final int mo6464a() {
        BottomSheetBehavior bottomSheetBehavior = this.f11016a;
        return bottomSheetBehavior.f5173h ? bottomSheetBehavior.f5177l : bottomSheetBehavior.f5172g;
    }

    /* renamed from: c */
    public final int mo6414c(View view, int i) {
        return view.getLeft();
    }

    /* renamed from: d */
    public final int mo6415d(View view, int i) {
        int i2;
        int b = this.f11016a.mo3613b();
        BottomSheetBehavior bottomSheetBehavior = this.f11016a;
        if (!bottomSheetBehavior.f5173h) {
            i2 = bottomSheetBehavior.f5172g;
        } else {
            i2 = bottomSheetBehavior.f5177l;
        }
        return C0257jh.m14468a(i, b, i2);
    }

    /* renamed from: a */
    public final void mo6409a(int i) {
        if (i == 1) {
            this.f11016a.mo3616d(1);
        }
    }

    /* renamed from: a */
    public final void mo6412a(View view, int i, int i2) {
        this.f11016a.mo3617e(i2);
    }

    /* renamed from: a */
    public final void mo6410a(View view, float f, float f2) {
        int i;
        int i2 = 4;
        if (f2 < 0.0f) {
            BottomSheetBehavior bottomSheetBehavior = this.f11016a;
            if (!bottomSheetBehavior.f5166a) {
                int top = view.getTop();
                BottomSheetBehavior bottomSheetBehavior2 = this.f11016a;
                i = bottomSheetBehavior2.f5171f;
                if (top <= i) {
                    i = bottomSheetBehavior2.f5169d;
                    i2 = 3;
                } else {
                    i2 = 6;
                }
            } else {
                i = bottomSheetBehavior.f5170e;
                i2 = 3;
            }
        } else {
            BottomSheetBehavior bottomSheetBehavior3 = this.f11016a;
            if (bottomSheetBehavior3.f5173h && bottomSheetBehavior3.mo3612a(view, f2)) {
                if (Math.abs(f) >= Math.abs(f2) || f2 <= 500.0f) {
                    int top2 = view.getTop();
                    BottomSheetBehavior bottomSheetBehavior4 = this.f11016a;
                    if (top2 <= (bottomSheetBehavior4.f5177l + bottomSheetBehavior4.mo3613b()) / 2) {
                        BottomSheetBehavior bottomSheetBehavior5 = this.f11016a;
                        if (bottomSheetBehavior5.f5166a) {
                            i = bottomSheetBehavior5.f5170e;
                        } else if (Math.abs(view.getTop() - this.f11016a.f5169d) < Math.abs(view.getTop() - this.f11016a.f5171f)) {
                            i = this.f11016a.f5169d;
                        } else {
                            i = this.f11016a.f5171f;
                            i2 = 6;
                        }
                        i2 = 3;
                    }
                }
                i = this.f11016a.f5177l;
                i2 = 5;
            } else if (f2 == 0.0f || Math.abs(f) > Math.abs(f2)) {
                int top3 = view.getTop();
                BottomSheetBehavior bottomSheetBehavior6 = this.f11016a;
                if (!bottomSheetBehavior6.f5166a) {
                    int i3 = bottomSheetBehavior6.f5171f;
                    if (top3 >= i3) {
                        if (Math.abs(top3 - i3) < Math.abs(top3 - this.f11016a.f5172g)) {
                            i = this.f11016a.f5171f;
                            i2 = 6;
                        } else {
                            i = this.f11016a.f5172g;
                        }
                    } else if (top3 < Math.abs(top3 - bottomSheetBehavior6.f5172g)) {
                        i = this.f11016a.f5169d;
                        i2 = 3;
                    } else {
                        i = this.f11016a.f5171f;
                        i2 = 6;
                    }
                } else if (Math.abs(top3 - bottomSheetBehavior6.f5170e) >= Math.abs(top3 - this.f11016a.f5172g)) {
                    i = this.f11016a.f5172g;
                } else {
                    i = this.f11016a.f5170e;
                    i2 = 3;
                }
            } else {
                BottomSheetBehavior bottomSheetBehavior7 = this.f11016a;
                if (!bottomSheetBehavior7.f5166a) {
                    int top4 = view.getTop();
                    if (Math.abs(top4 - this.f11016a.f5171f) < Math.abs(top4 - this.f11016a.f5172g)) {
                        i = this.f11016a.f5171f;
                        i2 = 6;
                    } else {
                        bottomSheetBehavior7 = this.f11016a;
                    }
                }
                i = bottomSheetBehavior7.f5172g;
            }
        }
        this.f11016a.mo3611a(view, i2, i, true);
    }

    /* renamed from: b */
    public final boolean mo6413b(View view, int i) {
        View view2;
        BottomSheetBehavior bottomSheetBehavior = this.f11016a;
        int i2 = bottomSheetBehavior.f5175j;
        if (i2 == 1 || bottomSheetBehavior.f5182q) {
            return false;
        }
        if (i2 == 3 && bottomSheetBehavior.f5181p == i) {
            WeakReference weakReference = bottomSheetBehavior.f5179n;
            if (weakReference != null) {
                view2 = (View) weakReference.get();
            } else {
                view2 = null;
            }
            if (view2 != null && view2.canScrollVertically(-1)) {
                return false;
            }
        }
        WeakReference weakReference2 = this.f11016a.f5178m;
        return weakReference2 != null && weakReference2.get() == view;
    }
}
