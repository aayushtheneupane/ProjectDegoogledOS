package p000;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: ma */
/* compiled from: PG */
public final class C0331ma implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: a */
    private final View f15222a;

    /* renamed from: b */
    private ViewTreeObserver f15223b;

    /* renamed from: c */
    private final Runnable f15224c;

    private C0331ma(View view, Runnable runnable) {
        this.f15222a = view;
        this.f15223b = view.getViewTreeObserver();
        this.f15224c = runnable;
    }

    /* renamed from: a */
    public static void m14662a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        } else if (runnable != null) {
            C0331ma maVar = new C0331ma(view, runnable);
            view.getViewTreeObserver().addOnPreDrawListener(maVar);
            view.addOnAttachStateChangeListener(maVar);
        } else {
            throw new NullPointerException("runnable == null");
        }
    }

    public final boolean onPreDraw() {
        m14661a();
        this.f15224c.run();
        return true;
    }

    public final void onViewAttachedToWindow(View view) {
        this.f15223b = view.getViewTreeObserver();
    }

    public final void onViewDetachedFromWindow(View view) {
        m14661a();
    }

    /* renamed from: a */
    private final void m14661a() {
        if (this.f15223b.isAlive()) {
            this.f15223b.removeOnPreDrawListener(this);
        } else {
            this.f15222a.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f15222a.removeOnAttachStateChangeListener(this);
    }
}
