package androidx.recyclerview.widget;

import android.view.View;

/* renamed from: androidx.recyclerview.widget.Ea */
class C0530Ea {

    /* renamed from: Et */
    C0526Ca f520Et = new C0526Ca();
    final C0528Da mCallback;

    C0530Ea(C0528Da da) {
        this.mCallback = da;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public View mo4659a(int i, int i2, int i3, int i4) {
        int f = this.mCallback.mo4644f();
        int w = this.mCallback.mo4646w();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View childAt = this.mCallback.getChildAt(i);
            int a = this.mCallback.mo4642a(childAt);
            int b = this.mCallback.mo4643b(childAt);
            C0526Ca ca = this.f520Et;
            ca.f510Ft = f;
            ca.f511Gt = w;
            ca.f512Ht = a;
            ca.f513It = b;
            if (i3 != 0) {
                ca.f509Et = 0;
                ca.f509Et |= i3;
                if (ca.mo4639jd()) {
                    return childAt;
                }
            }
            if (i4 != 0) {
                C0526Ca ca2 = this.f520Et;
                ca2.f509Et = 0;
                ca2.f509Et |= i4;
                if (ca2.mo4639jd()) {
                    view = childAt;
                }
            }
            i += i5;
        }
        return view;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo4660c(View view, int i) {
        C0526Ca ca = this.f520Et;
        int f = this.mCallback.mo4644f();
        int w = this.mCallback.mo4646w();
        int a = this.mCallback.mo4642a(view);
        int b = this.mCallback.mo4643b(view);
        ca.f510Ft = f;
        ca.f511Gt = w;
        ca.f512Ht = a;
        ca.f513It = b;
        if (i == 0) {
            return false;
        }
        C0526Ca ca2 = this.f520Et;
        ca2.f509Et = 0;
        ca2.f509Et |= i;
        return ca2.mo4639jd();
    }
}
