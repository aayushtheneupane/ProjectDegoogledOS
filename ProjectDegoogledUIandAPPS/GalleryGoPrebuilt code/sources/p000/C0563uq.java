package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: uq */
/* compiled from: PG */
public final class C0563uq {

    /* renamed from: a */
    public final C0562up f16027a;

    /* renamed from: b */
    public final C0561uo f16028b = new C0561uo();

    /* renamed from: c */
    public final List f16029c = new ArrayList();

    public C0563uq(C0562up upVar) {
        this.f16027a = upVar;
    }

    /* renamed from: a */
    public final void mo10313a(View view, int i, boolean z) {
        int i2;
        if (i < 0) {
            i2 = this.f16027a.mo10304a();
        } else {
            i2 = mo10310a(0);
        }
        this.f16028b.mo10298a(i2, z);
        if (z) {
            mo10311a(view);
        }
        C0632xe xeVar = (C0632xe) this.f16027a;
        xeVar.f16285a.addView(view, i2);
        xeVar.f16285a.dispatchChildAttached(view);
    }

    /* renamed from: a */
    public final void mo10312a(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int i2;
        if (i < 0) {
            i2 = this.f16027a.mo10304a();
        } else {
            i2 = mo10310a(i);
        }
        this.f16028b.mo10298a(i2, z);
        if (z) {
            mo10311a(view);
        }
        C0562up upVar = this.f16027a;
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.mo10654n() || childViewHolderInt.mo10642b()) {
                childViewHolderInt.mo10649i();
            } else {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt + ((C0632xe) upVar).f16285a.exceptionLabel());
            }
        }
        ((C0632xe) upVar).f16285a.attachViewToParent(view, i2, layoutParams);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo10319d(int i) {
        C0667ym childViewHolderInt;
        int a = mo10310a(i);
        this.f16028b.mo10301d(a);
        C0632xe xeVar = (C0632xe) this.f16027a;
        View b = xeVar.mo10307b(a);
        if (!(b == null || (childViewHolderInt = RecyclerView.getChildViewHolderInt(b)) == null)) {
            if (!childViewHolderInt.mo10654n() || childViewHolderInt.mo10642b()) {
                childViewHolderInt.mo10641b(256);
            } else {
                throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt + xeVar.f16285a.exceptionLabel());
            }
        }
        xeVar.f16285a.detachViewFromParent(a);
    }

    /* renamed from: b */
    public final View mo10316b(int i) {
        return this.f16027a.mo10307b(mo10310a(i));
    }

    /* renamed from: a */
    public final int mo10309a() {
        return this.f16027a.mo10304a() - this.f16029c.size();
    }

    /* renamed from: a */
    public final int mo10310a(int i) {
        if (i >= 0) {
            int a = this.f16027a.mo10304a();
            int i2 = i;
            while (i2 < a) {
                int e = i - (i2 - this.f16028b.mo10302e(i2));
                if (e != 0) {
                    i2 += e;
                } else {
                    while (this.f16028b.mo10300c(i2)) {
                        i2++;
                    }
                    return i2;
                }
            }
        }
        return -1;
    }

    /* renamed from: c */
    public final View mo10317c(int i) {
        return this.f16027a.mo10307b(i);
    }

    /* renamed from: b */
    public final int mo10314b() {
        return this.f16027a.mo10304a();
    }

    /* renamed from: a */
    public final void mo10311a(View view) {
        this.f16029c.add(view);
        C0562up upVar = this.f16027a;
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            RecyclerView recyclerView = ((C0632xe) upVar).f16285a;
            int i = childViewHolderInt.f16395n;
            if (i == -1) {
                childViewHolderInt.f16394m = C0340mj.m14712e(childViewHolderInt.f16382a);
            } else {
                childViewHolderInt.f16394m = i;
            }
            recyclerView.setChildImportantForAccessibilityInternal(childViewHolderInt, 4);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo10315b(View view) {
        int a = this.f16027a.mo10305a(view);
        if (a == -1 || this.f16028b.mo10300c(a)) {
            return -1;
        }
        return a - this.f16028b.mo10302e(a);
    }

    /* renamed from: c */
    public final boolean mo10318c(View view) {
        return this.f16029c.contains(view);
    }

    public final String toString() {
        return this.f16028b.toString() + ", hidden list:" + this.f16029c.size();
    }

    /* renamed from: d */
    public final void mo10320d(View view) {
        if (this.f16029c.remove(view)) {
            this.f16027a.mo10308b(view);
        }
    }
}
