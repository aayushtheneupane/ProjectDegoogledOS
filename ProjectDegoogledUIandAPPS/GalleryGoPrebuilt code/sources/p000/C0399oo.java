package p000;

import android.graphics.Rect;
import android.os.Build;
import android.support.p002v7.widget.ActionBarContextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.google.android.apps.photosgo.R;

/* renamed from: oo */
/* compiled from: PG */
final class C0399oo implements C0329lz {

    /* renamed from: a */
    private final /* synthetic */ C0416pe f15419a;

    public C0399oo(C0416pe peVar) {
        this.f15419a = peVar;
    }

    /* renamed from: a */
    public final C0348mr mo79a(View view, C0348mr mrVar) {
        int i;
        boolean z;
        int i2;
        boolean z2;
        int b = mrVar.mo9409b();
        C0416pe peVar = this.f15419a;
        ActionBarContextView actionBarContextView = peVar.f15490i;
        int i3 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            i = b;
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) peVar.f15490i.getLayoutParams();
            boolean z3 = true;
            if (peVar.f15490i.isShown()) {
                if (peVar.f15504w == null) {
                    peVar.f15504w = new Rect();
                    peVar.f15505x = new Rect();
                }
                Rect rect = peVar.f15504w;
                Rect rect2 = peVar.f15505x;
                rect.set(0, b, 0, 0);
                C0703zv.m16279a(peVar.f15495n, rect, rect2);
                if (rect2.top == 0) {
                    i2 = b;
                } else {
                    i2 = 0;
                }
                if (marginLayoutParams.topMargin != i2) {
                    marginLayoutParams.topMargin = b;
                    View view2 = peVar.f15496o;
                    if (view2 == null) {
                        peVar.f15496o = new View(peVar.f15485d);
                        peVar.f15496o.setBackgroundColor(peVar.f15485d.getResources().getColor(R.color.abc_input_method_navigation_guard));
                        peVar.f15495n.addView(peVar.f15496o, -1, new ViewGroup.LayoutParams(-1, b));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                        if (layoutParams.height != b) {
                            layoutParams.height = b;
                            peVar.f15496o.setLayoutParams(layoutParams);
                            z2 = true;
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (peVar.f15496o == null) {
                    z3 = false;
                }
                if (peVar.f15498q || !z3) {
                    i = b;
                } else {
                    i = 0;
                }
                boolean z4 = z3;
                z3 = z2;
                z = z4;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                i = b;
                z = false;
            } else {
                i = b;
                z = false;
                z3 = false;
            }
            if (z3) {
                peVar.f15490i.setLayoutParams(marginLayoutParams);
            }
        }
        View view3 = peVar.f15496o;
        if (view3 != null) {
            if (!z) {
                i3 = 8;
            }
            view3.setVisibility(i3);
        }
        if (b != i) {
            int a = mrVar.mo9408a();
            int c = mrVar.mo9410c();
            int d = mrVar.mo9411d();
            int i4 = Build.VERSION.SDK_INT;
            mrVar = new C0348mr(((WindowInsets) mrVar.f15240a).replaceSystemWindowInsets(a, i, c, d));
        }
        return C0340mj.m14686a(view, mrVar);
    }
}
