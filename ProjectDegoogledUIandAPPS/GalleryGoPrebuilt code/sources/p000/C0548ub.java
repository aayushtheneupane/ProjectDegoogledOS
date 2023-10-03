package p000;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* renamed from: ub */
/* compiled from: PG */
final class C0548ub implements PopupWindow.OnDismissListener {

    /* renamed from: a */
    private final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f15973a;

    /* renamed from: b */
    private final /* synthetic */ C0549uc f15974b;

    public C0548ub(C0549uc ucVar, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.f15974b = ucVar;
        this.f15973a = onGlobalLayoutListener;
    }

    public final void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.f15974b.f15978d.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f15973a);
        }
    }
}
