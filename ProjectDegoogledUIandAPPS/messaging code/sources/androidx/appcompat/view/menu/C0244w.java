package androidx.appcompat.view.menu;

import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.CollapsibleActionView;

/* renamed from: androidx.appcompat.view.menu.w */
class C0244w extends FrameLayout implements CollapsibleActionView {

    /* renamed from: mf */
    final android.view.CollapsibleActionView f302mf;

    C0244w(View view) {
        super(view.getContext());
        this.f302mf = (android.view.CollapsibleActionView) view;
        addView(view);
    }

    public void onActionViewCollapsed() {
        this.f302mf.onActionViewCollapsed();
    }

    public void onActionViewExpanded() {
        this.f302mf.onActionViewExpanded();
    }
}
