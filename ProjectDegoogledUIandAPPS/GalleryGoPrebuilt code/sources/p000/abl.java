package p000;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: abl */
/* compiled from: PG */
public final class abl implements ViewGroup.OnHierarchyChangeListener {

    /* renamed from: a */
    private final /* synthetic */ CoordinatorLayout f79a;

    public abl(CoordinatorLayout coordinatorLayout) {
        this.f79a = coordinatorLayout;
    }

    public final void onChildViewAdded(View view, View view2) {
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f79a.f1053e;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    public final void onChildViewRemoved(View view, View view2) {
        this.f79a.mo1120a(2);
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f79a.f1053e;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
