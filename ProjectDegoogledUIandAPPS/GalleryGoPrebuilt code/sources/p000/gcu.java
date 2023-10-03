package p000;

import android.view.View;

/* renamed from: gcu */
/* compiled from: PG */
final class gcu {

    /* renamed from: a */
    public final View f10970a;

    /* renamed from: b */
    public int f10971b;

    /* renamed from: c */
    public int f10972c;

    /* renamed from: d */
    public int f10973d;

    public gcu(View view) {
        this.f10970a = view;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6405a() {
        View view = this.f10970a;
        C0340mj.m14708c(view, this.f10973d - (view.getTop() - this.f10971b));
        View view2 = this.f10970a;
        C0340mj.m14711d(view2, -(view2.getLeft() - this.f10972c));
    }

    /* renamed from: a */
    public final boolean mo6406a(int i) {
        if (this.f10973d == i) {
            return false;
        }
        this.f10973d = i;
        mo6405a();
        return true;
    }
}
