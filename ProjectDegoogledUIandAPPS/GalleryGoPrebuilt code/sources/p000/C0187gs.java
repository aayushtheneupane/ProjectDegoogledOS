package p000;

import android.graphics.Rect;
import android.view.View;

/* renamed from: gs */
/* compiled from: PG */
final class C0187gs implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ View f11935a;

    /* renamed from: b */
    private final /* synthetic */ Rect f11936b;

    public C0187gs(View view, Rect rect) {
        this.f11935a = view;
        this.f11936b = rect;
    }

    public final void run() {
        int i = C0191gw.f12166a;
        View view = this.f11935a;
        if (view != null) {
            C0202hg.m11410a(view, this.f11936b);
        }
    }
}
