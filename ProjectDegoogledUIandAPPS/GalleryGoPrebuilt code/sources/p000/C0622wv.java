package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: wv */
/* compiled from: PG */
final class C0622wv extends C0624wx {
    public C0622wv(C0647xt xtVar) {
        super(xtVar);
    }

    /* renamed from: b */
    public final int mo10514b() {
        return this.f16276a.f16310u;
    }

    /* renamed from: f */
    public final int mo10522f() {
        return this.f16276a.f16308s;
    }

    /* renamed from: g */
    public final int mo10524g() {
        return this.f16276a.f16309t;
    }

    /* renamed from: c */
    public final int mo10517c(View view) {
        return C0647xt.m15973g(view) + ((C0648xu) view.getLayoutParams()).rightMargin;
    }

    /* renamed from: a */
    public final int mo10512a(View view) {
        C0648xu xuVar = (C0648xu) view.getLayoutParams();
        return C0647xt.m15972f(view) + xuVar.leftMargin + xuVar.rightMargin;
    }

    /* renamed from: b */
    public final int mo10515b(View view) {
        C0648xu xuVar = (C0648xu) view.getLayoutParams();
        return C0647xt.m15971e(view) + xuVar.topMargin + xuVar.bottomMargin;
    }

    /* renamed from: d */
    public final int mo10519d(View view) {
        return C0647xt.m15970d(view) - ((C0648xu) view.getLayoutParams()).leftMargin;
    }

    /* renamed from: a */
    public final int mo10511a() {
        C0647xt xtVar = this.f16276a;
        return xtVar.f16310u - xtVar.mo10588u();
    }

    /* renamed from: e */
    public final int mo10520e() {
        return this.f16276a.mo10588u();
    }

    /* renamed from: c */
    public final int mo10516c() {
        return this.f16276a.mo10586s();
    }

    /* renamed from: d */
    public final int mo10518d() {
        C0647xt xtVar = this.f16276a;
        return (xtVar.f16310u - xtVar.mo10586s()) - this.f16276a.mo10588u();
    }

    /* renamed from: e */
    public final int mo10521e(View view) {
        this.f16276a.mo10569a(view, this.f16278c);
        return this.f16278c.right;
    }

    /* renamed from: f */
    public final int mo10523f(View view) {
        this.f16276a.mo10569a(view, this.f16278c);
        return this.f16278c.left;
    }

    /* renamed from: a */
    public final void mo10513a(int i) {
        RecyclerView recyclerView = this.f16276a.f16299j;
        if (recyclerView != null) {
            recyclerView.offsetChildrenHorizontal(i);
        }
    }
}
