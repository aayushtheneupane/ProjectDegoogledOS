package p000;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: qw */
/* compiled from: PG */
final class C0461qw implements View.OnAttachStateChangeListener {

    /* renamed from: a */
    private final /* synthetic */ C0466ra f15700a;

    public C0461qw(C0466ra raVar) {
        this.f15700a = raVar;
    }

    public final void onViewAttachedToWindow(View view) {
    }

    public final void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.f15700a.f15713e;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f15700a.f15713e = view.getViewTreeObserver();
            }
            C0466ra raVar = this.f15700a;
            raVar.f15713e.removeGlobalOnLayoutListener(raVar.f15711c);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
