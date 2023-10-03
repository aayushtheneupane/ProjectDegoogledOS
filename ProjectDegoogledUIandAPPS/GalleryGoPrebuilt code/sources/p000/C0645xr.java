package p000;

import android.view.View;

/* renamed from: xr */
/* compiled from: PG */
final class C0645xr implements C0698zq {

    /* renamed from: a */
    private final /* synthetic */ C0647xt f16295a;

    public C0645xr(C0647xt xtVar) {
        this.f16295a = xtVar;
    }

    /* renamed from: a */
    public final View mo10561a(int i) {
        return this.f16295a.mo10582h(i);
    }

    /* renamed from: b */
    public final int mo10563b(View view) {
        return C0647xt.m15969c(view) + ((C0648xu) view.getLayoutParams()).bottomMargin;
    }

    /* renamed from: a */
    public final int mo10560a(View view) {
        return C0647xt.m15974h(view) - ((C0648xu) view.getLayoutParams()).topMargin;
    }

    /* renamed from: b */
    public final int mo10562b() {
        C0647xt xtVar = this.f16295a;
        return xtVar.f16311v - xtVar.mo10589v();
    }

    /* renamed from: a */
    public final int mo10559a() {
        return this.f16295a.mo10587t();
    }
}
