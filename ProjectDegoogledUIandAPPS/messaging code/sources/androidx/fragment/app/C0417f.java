package androidx.fragment.app;

import android.view.View;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.fragment.app.f */
class C0417f extends C0426l {
    final /* synthetic */ C0424j this$0;

    C0417f(C0424j jVar) {
        this.this$0 = jVar;
    }

    public View onFindViewById(int i) {
        View view = this.this$0.mView;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException(C0632a.m1015a("Fragment ", (Object) this, " does not have a view"));
    }

    public boolean onHasView() {
        return this.this$0.mView != null;
    }
}
