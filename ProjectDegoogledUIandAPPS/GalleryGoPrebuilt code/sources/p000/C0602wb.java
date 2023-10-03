package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: wb */
/* compiled from: PG */
final class C0602wb {

    /* renamed from: a */
    public C0624wx f16196a;

    /* renamed from: b */
    public int f16197b;

    /* renamed from: c */
    public int f16198c;

    /* renamed from: d */
    public boolean f16199d;

    /* renamed from: e */
    public boolean f16200e;

    public C0602wb() {
        mo10448a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo10450b() {
        int i;
        if (this.f16199d) {
            i = this.f16196a.mo10511a();
        } else {
            i = this.f16196a.mo10516c();
        }
        this.f16198c = i;
    }

    /* renamed from: b */
    public final void mo10451b(View view, int i) {
        if (this.f16199d) {
            this.f16198c = this.f16196a.mo10517c(view) + this.f16196a.mo10525h();
        } else {
            this.f16198c = this.f16196a.mo10519d(view);
        }
        this.f16197b = i;
    }

    /* renamed from: a */
    public final void mo10449a(View view, int i) {
        int h = this.f16196a.mo10525h();
        if (h >= 0) {
            mo10451b(view, i);
            return;
        }
        this.f16197b = i;
        if (this.f16199d) {
            int a = (this.f16196a.mo10511a() - h) - this.f16196a.mo10517c(view);
            this.f16198c = this.f16196a.mo10511a() - a;
            if (a > 0) {
                int a2 = this.f16196a.mo10512a(view);
                int i2 = this.f16198c;
                int c = this.f16196a.mo10516c();
                int min = (i2 - a2) - (c + Math.min(this.f16196a.mo10519d(view) - c, 0));
                if (min < 0) {
                    this.f16198c += Math.min(a, -min);
                    return;
                }
                return;
            }
            return;
        }
        int d = this.f16196a.mo10519d(view);
        int c2 = d - this.f16196a.mo10516c();
        this.f16198c = d;
        if (c2 > 0) {
            int a3 = this.f16196a.mo10512a(view);
            int a4 = (this.f16196a.mo10511a() - Math.min(0, (this.f16196a.mo10511a() - h) - this.f16196a.mo10517c(view))) - (d + a3);
            if (a4 < 0) {
                this.f16198c -= Math.min(c2, -a4);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10448a() {
        this.f16197b = -1;
        this.f16198c = RecyclerView.UNDEFINED_DURATION;
        this.f16199d = false;
        this.f16200e = false;
    }

    public final String toString() {
        return "AnchorInfo{mPosition=" + this.f16197b + ", mCoordinate=" + this.f16198c + ", mLayoutFromEnd=" + this.f16199d + ", mValid=" + this.f16200e + '}';
    }
}
