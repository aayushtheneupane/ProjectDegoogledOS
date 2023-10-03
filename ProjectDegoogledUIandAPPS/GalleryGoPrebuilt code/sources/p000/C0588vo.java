package p000;

import android.view.ViewParent;

/* renamed from: vo */
/* compiled from: PG */
final class C0588vo implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0590vq f16140a;

    public C0588vo(C0590vq vqVar) {
        this.f16140a = vqVar;
    }

    public final void run() {
        ViewParent parent = this.f16140a.f16142a.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
}
