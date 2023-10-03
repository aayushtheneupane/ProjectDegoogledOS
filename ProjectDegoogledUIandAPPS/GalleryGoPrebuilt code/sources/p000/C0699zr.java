package p000;

import android.view.View;

/* renamed from: zr */
/* compiled from: PG */
final class C0699zr {

    /* renamed from: a */
    private final C0698zq f16472a;

    /* renamed from: b */
    private final C0697zp f16473b = new C0697zp();

    public C0699zr(C0698zq zqVar) {
        this.f16472a = zqVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final View mo10749a(int i, int i2, int i3, int i4) {
        int i5;
        int a = this.f16472a.mo10559a();
        int b = this.f16472a.mo10562b();
        if (i2 > i) {
            i5 = 1;
        } else {
            i5 = -1;
        }
        View view = null;
        while (i != i2) {
            View a2 = this.f16472a.mo10561a(i);
            this.f16473b.mo10747a(a, b, this.f16472a.mo10560a(a2), this.f16472a.mo10563b(a2));
            this.f16473b.mo10745a();
            this.f16473b.mo10746a(i3);
            if (this.f16473b.mo10748b()) {
                return a2;
            }
            this.f16473b.mo10745a();
            this.f16473b.mo10746a(i4);
            if (this.f16473b.mo10748b()) {
                view = a2;
            }
            i += i5;
        }
        return view;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo10750a(View view) {
        this.f16473b.mo10747a(this.f16472a.mo10559a(), this.f16472a.mo10562b(), this.f16472a.mo10560a(view), this.f16472a.mo10563b(view));
        this.f16473b.mo10745a();
        this.f16473b.mo10746a(24579);
        return this.f16473b.mo10748b();
    }
}
