package p000;

import android.view.View;

/* renamed from: xq */
/* compiled from: PG */
final class C0644xq implements C0698zq {

    /* renamed from: a */
    private final /* synthetic */ C0647xt f16294a;

    public C0644xq(C0647xt xtVar) {
        this.f16294a = xtVar;
    }

    /* renamed from: a */
    public final View mo10561a(int i) {
        return this.f16294a.mo10582h(i);
    }

    /* renamed from: b */
    public final int mo10563b(View view) {
        return C0647xt.m15973g(view) + ((C0648xu) view.getLayoutParams()).rightMargin;
    }

    /* renamed from: a */
    public final int mo10560a(View view) {
        return C0647xt.m15970d(view) - ((C0648xu) view.getLayoutParams()).leftMargin;
    }

    /* renamed from: b */
    public final int mo10562b() {
        C0647xt xtVar = this.f16294a;
        return xtVar.f16310u - xtVar.mo10588u();
    }

    /* renamed from: a */
    public final int mo10559a() {
        return this.f16294a.mo10586s();
    }
}
