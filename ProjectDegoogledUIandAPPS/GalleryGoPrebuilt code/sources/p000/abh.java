package p000;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: abh */
/* compiled from: PG */
public final class abh implements C0329lz {

    /* renamed from: a */
    private final /* synthetic */ CoordinatorLayout f78a;

    public abh(CoordinatorLayout coordinatorLayout) {
        this.f78a = coordinatorLayout;
    }

    /* renamed from: a */
    public final C0348mr mo79a(View view, C0348mr mrVar) {
        CoordinatorLayout coordinatorLayout = this.f78a;
        if (!C0321lr.m14631a((Object) coordinatorLayout.f1051c, (Object) mrVar)) {
            coordinatorLayout.f1051c = mrVar;
            int b = mrVar.mo9409b();
            boolean z = true;
            coordinatorLayout.f1052d = b > 0;
            if (b > 0 || coordinatorLayout.getBackground() != null) {
                z = false;
            }
            coordinatorLayout.setWillNotDraw(z);
            if (!mrVar.mo9412e()) {
                int childCount = coordinatorLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    if (C0340mj.m14725p(childAt) && ((abm) childAt.getLayoutParams()).f80a != null && mrVar.mo9412e()) {
                        break;
                    }
                }
            }
            coordinatorLayout.requestLayout();
        }
        return mrVar;
    }
}
