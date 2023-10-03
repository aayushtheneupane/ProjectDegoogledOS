package p000;

import android.view.View;
import android.view.ViewParent;

/* renamed from: lv */
/* compiled from: PG */
public final class C0325lv {

    /* renamed from: a */
    public boolean f15213a;

    /* renamed from: b */
    private ViewParent f15214b;

    /* renamed from: c */
    private ViewParent f15215c;

    /* renamed from: d */
    private final View f15216d;

    /* renamed from: e */
    private int[] f15217e;

    public C0325lv(View view) {
        this.f15216d = view;
    }

    /* renamed from: c */
    private final ViewParent m14640c(int i) {
        if (i == 0) {
            return this.f15214b;
        }
        if (i != 1) {
            return null;
        }
        return this.f15215c;
    }

    /* renamed from: a */
    public final boolean mo9372a(float f, float f2, boolean z) {
        ViewParent c;
        if (!this.f15213a || (c = m14640c(0)) == null) {
            return false;
        }
        return C0350mt.m14767a(c, this.f15216d, f, f2, z);
    }

    /* renamed from: a */
    public final boolean mo9371a(float f, float f2) {
        ViewParent c;
        if (!this.f15213a || (c = m14640c(0)) == null) {
            return false;
        }
        return C0350mt.m14766a(c, this.f15216d, f, f2);
    }

    /* renamed from: a */
    public final boolean mo9377a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent c;
        int i4;
        int i5;
        if (!this.f15213a || (c = m14640c(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            this.f15216d.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i4 = iArr2[1];
        } else {
            i5 = 0;
            i4 = 0;
        }
        if (iArr == null) {
            iArr = m14639a();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        C0350mt.m14764a(c, this.f15216d, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.f15216d.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i4;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    /* renamed from: a */
    public final boolean mo9375a(int i, int i2, int i3, int i4, int[] iArr) {
        return mo9376a(i, i2, i3, i4, iArr, 0, (int[]) null);
    }

    /* renamed from: a */
    public final boolean mo9376a(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent c;
        int i6;
        int i7;
        int[] iArr3;
        int[] iArr4 = iArr;
        if (!this.f15213a || (c = m14640c(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr4 != null) {
                iArr4[0] = 0;
                iArr4[1] = 0;
            }
            return false;
        }
        if (iArr4 != null) {
            this.f15216d.getLocationInWindow(iArr4);
            i7 = iArr4[0];
            i6 = iArr4[1];
        } else {
            i7 = 0;
            i6 = 0;
        }
        if (iArr2 == null) {
            int[] a = m14639a();
            a[0] = 0;
            a[1] = 0;
            iArr3 = a;
        } else {
            iArr3 = iArr2;
        }
        C0350mt.m14763a(c, this.f15216d, i, i2, i3, i4, i5, iArr3);
        if (iArr4 != null) {
            this.f15216d.getLocationInWindow(iArr4);
            iArr4[0] = iArr4[0] - i7;
            iArr4[1] = iArr4[1] - i6;
        }
        return true;
    }

    /* renamed from: a */
    private final int[] m14639a() {
        if (this.f15217e == null) {
            this.f15217e = new int[2];
        }
        return this.f15217e;
    }

    /* renamed from: a */
    public final boolean mo9373a(int i) {
        return m14640c(i) != null;
    }

    /* renamed from: a */
    public final void mo9370a(boolean z) {
        if (this.f15213a) {
            C0340mj.m14731v(this.f15216d);
        }
        this.f15213a = z;
    }

    /* renamed from: a */
    private final void m14638a(int i, ViewParent viewParent) {
        if (i == 0) {
            this.f15214b = viewParent;
        } else if (i == 1) {
            this.f15215c = viewParent;
        }
    }

    /* renamed from: a */
    public final boolean mo9374a(int i, int i2) {
        if (mo9373a(i2)) {
            return true;
        }
        if (!this.f15213a) {
            return false;
        }
        ViewParent parent = this.f15216d.getParent();
        View view = this.f15216d;
        while (parent != null) {
            if (!C0350mt.m14768a(parent, view, this.f15216d, i, i2)) {
                if (parent instanceof View) {
                    view = (View) parent;
                }
                parent = parent.getParent();
            } else {
                m14638a(i2, parent);
                C0350mt.m14772b(parent, view, this.f15216d, i, i2);
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public final void mo9378b(int i) {
        ViewParent c = m14640c(i);
        if (c != null) {
            C0350mt.m14762a(c, this.f15216d, i);
            m14638a(i, (ViewParent) null);
        }
    }
}
