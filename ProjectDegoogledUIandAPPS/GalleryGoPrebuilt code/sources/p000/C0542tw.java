package p000;

import android.os.Build;
import android.view.ViewTreeObserver;

/* renamed from: tw */
/* compiled from: PG */
final class C0542tw implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    private final /* synthetic */ C0553ug f15964a;

    public C0542tw(C0553ug ugVar) {
        this.f15964a = ugVar;
    }

    public final void onGlobalLayout() {
        if (!this.f15964a.f15983b.mo10191e()) {
            this.f15964a.mo10215a();
        }
        ViewTreeObserver viewTreeObserver = this.f15964a.getViewTreeObserver();
        if (viewTreeObserver != null) {
            int i = Build.VERSION.SDK_INT;
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
    }
}
