package p000;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* renamed from: rm */
/* compiled from: PG */
final class C0478rm extends FrameLayout implements C0444qf {

    /* renamed from: a */
    public final CollapsibleActionView f15809a;

    public C0478rm(View view) {
        super(view.getContext());
        this.f15809a = (CollapsibleActionView) view;
        addView(view);
    }

    /* renamed from: b */
    public final void mo9695b() {
        this.f15809a.onActionViewCollapsed();
    }

    /* renamed from: a */
    public final void mo9694a() {
        this.f15809a.onActionViewExpanded();
    }
}
