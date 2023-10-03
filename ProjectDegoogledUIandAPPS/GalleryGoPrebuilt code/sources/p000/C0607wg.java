package p000;

import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* renamed from: wg */
/* compiled from: PG */
public class C0607wg extends C0647xt implements C0662yh {

    /* renamed from: a */
    private C0604wd f16221a;

    /* renamed from: b */
    private boolean f16222b;

    /* renamed from: c */
    public int f16223c;

    /* renamed from: d */
    public C0624wx f16224d;

    /* renamed from: e */
    public boolean f16225e;

    /* renamed from: f */
    public int f16226f;

    /* renamed from: g */
    public int f16227g;

    /* renamed from: h */
    public C0606wf f16228h;

    /* renamed from: w */
    private final C0602wb f16229w;

    /* renamed from: x */
    private final C0603wc f16230x;

    /* renamed from: y */
    private int f16231y;

    /* renamed from: z */
    private int[] f16232z;

    public C0607wg() {
        this(1);
    }

    /* renamed from: a */
    public void mo10428a(C0656yb ybVar, C0664yj yjVar, C0602wb wbVar, int i) {
    }

    /* renamed from: b */
    public boolean mo4532b() {
        return this.f16228h == null;
    }

    /* renamed from: i */
    public boolean mo2620i() {
        return (this.f16223c ^ 1) != 0;
    }

    /* renamed from: j */
    public final boolean mo10477j() {
        return this.f16223c != 0;
    }

    public C0607wg(int i) {
        C0624wx wxVar;
        this.f16223c = 1;
        this.f16225e = false;
        this.f16222b = true;
        this.f16226f = -1;
        this.f16227g = RecyclerView.UNDEFINED_DURATION;
        this.f16228h = null;
        this.f16229w = new C0602wb();
        this.f16230x = new C0603wc();
        this.f16231y = 2;
        this.f16232z = new int[2];
        i = i != 0 ? 1 : i;
        mo10465a((String) null);
        if (i != this.f16223c || this.f16224d == null) {
            if (i != 0) {
                wxVar = C0624wx.m15821b((C0647xt) this);
            } else {
                wxVar = C0624wx.m15820a((C0647xt) this);
            }
            this.f16224d = wxVar;
            this.f16229w.f16196a = wxVar;
            this.f16223c = i;
            mo10583p();
        }
        mo10465a((String) null);
    }

    /* renamed from: a */
    public final void mo10465a(String str) {
        RecyclerView recyclerView;
        if (this.f16228h == null && (recyclerView = this.f16299j) != null) {
            recyclerView.assertNotInLayoutOrScroll(str);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6513a(C0664yj yjVar, int[] iArr) {
        int b = mo4642b(yjVar);
        int i = this.f16221a.f16210f;
        int i2 = i == -1 ? 0 : b;
        if (i != -1) {
            b = 0;
        }
        iArr[0] = b;
        iArr[1] = i2;
    }

    /* renamed from: a */
    public void mo4531a(int i, int i2, C0664yj yjVar, C0646xs xsVar) {
        int i3;
        if (this.f16223c != 0) {
            i = i2;
        }
        if (mo10585r() != 0 && i != 0) {
            mo10479l();
            if (i > 0) {
                i3 = 1;
            } else {
                i3 = -1;
            }
            m15709a(i3, Math.abs(i), true, yjVar);
            mo10431a(yjVar, this.f16221a, xsVar);
        }
    }

    /* renamed from: a */
    public final void mo10462a(int i, C0646xs xsVar) {
        boolean z;
        int i2;
        C0606wf wfVar = this.f16228h;
        int i3 = -1;
        if (wfVar == null || !wfVar.mo10458a()) {
            m15699A();
            z = this.f16225e;
            i2 = this.f16226f;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            C0606wf wfVar2 = this.f16228h;
            z = wfVar2.f16220c;
            i2 = wfVar2.f16218a;
        }
        if (!z) {
            i3 = 1;
        }
        for (int i4 = 0; i4 < this.f16231y && i2 >= 0 && i2 < i; i4++) {
            xsVar.mo10410a(i2, 0);
            i2 += i3;
        }
    }

    /* renamed from: a */
    public void mo10431a(C0664yj yjVar, C0604wd wdVar, C0646xs xsVar) {
        int i = wdVar.f16208d;
        if (i >= 0 && i < yjVar.mo10626a()) {
            xsVar.mo10410a(i, Math.max(0, wdVar.f16211g));
        }
    }

    /* renamed from: e */
    public final int mo10472e(C0664yj yjVar) {
        return m15723j(yjVar);
    }

    /* renamed from: c */
    public final int mo10467c(C0664yj yjVar) {
        return m15722i(yjVar);
    }

    /* renamed from: g */
    public final int mo10474g(C0664yj yjVar) {
        return m15724k(yjVar);
    }

    /* renamed from: j */
    private final int m15723j(C0664yj yjVar) {
        if (mo10585r() == 0) {
            return 0;
        }
        mo10479l();
        C0624wx wxVar = this.f16224d;
        View b = m15714b(!this.f16222b);
        View a = m15708a(!this.f16222b);
        boolean z = this.f16222b;
        if (mo10585r() == 0 || yjVar.mo10626a() == 0 || b == null || a == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(C0647xt.m15975i(b) - C0647xt.m15975i(a)) + 1;
        }
        return Math.min(wxVar.mo10518d(), wxVar.mo10517c(a) - wxVar.mo10519d(b));
    }

    /* renamed from: i */
    private final int m15722i(C0664yj yjVar) {
        int i = 0;
        if (mo10585r() == 0) {
            return 0;
        }
        mo10479l();
        C0624wx wxVar = this.f16224d;
        View b = m15714b(!this.f16222b);
        View a = m15708a(!this.f16222b);
        boolean z = this.f16222b;
        boolean z2 = this.f16225e;
        if (!(mo10585r() == 0 || yjVar.mo10626a() == 0 || b == null || a == null)) {
            int min = Math.min(C0647xt.m15975i(b), C0647xt.m15975i(a));
            int max = Math.max(C0647xt.m15975i(b), C0647xt.m15975i(a));
            if (z2) {
                i = Math.max(0, (yjVar.mo10626a() - max) - 1);
            } else {
                i = Math.max(0, min);
            }
            if (z) {
                return Math.round((((float) i) * (((float) Math.abs(wxVar.mo10517c(a) - wxVar.mo10519d(b))) / ((float) (Math.abs(C0647xt.m15975i(b) - C0647xt.m15975i(a)) + 1)))) + ((float) (wxVar.mo10516c() - wxVar.mo10519d(b))));
            }
        }
        return i;
    }

    /* renamed from: k */
    private final int m15724k(C0664yj yjVar) {
        if (mo10585r() == 0) {
            return 0;
        }
        mo10479l();
        C0624wx wxVar = this.f16224d;
        View b = m15714b(!this.f16222b);
        View a = m15708a(!this.f16222b);
        boolean z = this.f16222b;
        if (mo10585r() == 0 || yjVar.mo10626a() == 0 || b == null || a == null) {
            return 0;
        }
        if (z) {
            return (int) ((((float) (wxVar.mo10517c(a) - wxVar.mo10519d(b))) / ((float) (Math.abs(C0647xt.m15975i(b) - C0647xt.m15975i(a)) + 1))) * ((float) yjVar.mo10626a()));
        }
        return yjVar.mo10626a();
    }

    /* renamed from: c */
    public final PointF mo10468c(int i) {
        if (mo10585r() == 0) {
            return null;
        }
        boolean z = false;
        int i2 = 1;
        if (i < m15975i(mo10582h(0))) {
            z = true;
        }
        if (z != this.f16225e) {
            i2 = -1;
        }
        if (this.f16223c != 0) {
            return new PointF(0.0f, (float) i2);
        }
        return new PointF((float) i2, 0.0f);
    }

    /* renamed from: f */
    public final int mo10473f(C0664yj yjVar) {
        return m15723j(yjVar);
    }

    /* renamed from: d */
    public final int mo10469d(C0664yj yjVar) {
        return m15722i(yjVar);
    }

    /* renamed from: h */
    public final int mo10475h(C0664yj yjVar) {
        return m15724k(yjVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final int mo10471e(int i) {
        if (i == 1) {
            return (this.f16223c == 1 || !mo10478k()) ? -1 : 1;
        }
        if (i == 2) {
            return (this.f16223c == 1 || !mo10478k()) ? 1 : -1;
        }
        if (i != 17) {
            if (i != 33) {
                if (i != 66) {
                    if (i == 130 && this.f16223c != 0) {
                        return 1;
                    }
                    return RecyclerView.UNDEFINED_DURATION;
                } else if (this.f16223c != 0) {
                    return RecyclerView.UNDEFINED_DURATION;
                } else {
                    return 1;
                }
            } else if (this.f16223c != 0) {
                return -1;
            } else {
                return RecyclerView.UNDEFINED_DURATION;
            }
        } else if (this.f16223c != 0) {
            return RecyclerView.UNDEFINED_DURATION;
        } else {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: l */
    public final void mo10479l() {
        if (this.f16221a == null) {
            this.f16221a = new C0604wd();
        }
    }

    /* renamed from: a */
    private final int m15706a(C0656yb ybVar, C0604wd wdVar, C0664yj yjVar, boolean z) {
        int i = wdVar.f16207c;
        int i2 = wdVar.f16211g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                wdVar.f16211g = i2 + i;
            }
            m15712a(ybVar, wdVar);
        }
        int i3 = wdVar.f16207c + wdVar.f16212h;
        C0603wc wcVar = this.f16230x;
        while (true) {
            if ((!wdVar.f16217m && i3 <= 0) || !wdVar.mo10455a(yjVar)) {
                break;
            }
            wcVar.f16201a = 0;
            wcVar.f16202b = false;
            wcVar.f16203c = false;
            wcVar.f16204d = false;
            mo10429a(ybVar, yjVar, wdVar, wcVar);
            if (wcVar.f16202b) {
                break;
            }
            int i4 = wdVar.f16206b;
            int i5 = wcVar.f16201a;
            wdVar.f16206b = i4 + (wdVar.f16210f * i5);
            if (!wcVar.f16203c || wdVar.f16216l != null || !yjVar.f16364g) {
                wdVar.f16207c -= i5;
                i3 -= i5;
            }
            int i6 = wdVar.f16211g;
            if (i6 != Integer.MIN_VALUE) {
                int i7 = i6 + i5;
                wdVar.f16211g = i7;
                int i8 = wdVar.f16207c;
                if (i8 < 0) {
                    wdVar.f16211g = i7 + i8;
                }
                m15712a(ybVar, wdVar);
            }
            if (z && wcVar.f16204d) {
                break;
            }
        }
        return i - wdVar.f16207c;
    }

    /* renamed from: E */
    private final View m15703E() {
        return m15721f(0, mo10585r());
    }

    /* renamed from: d */
    private final View m15717d(C0656yb ybVar, C0664yj yjVar) {
        return mo10424a(ybVar, yjVar, 0, mo10585r(), yjVar.mo10626a());
    }

    /* renamed from: a */
    private final View m15708a(boolean z) {
        if (!this.f16225e) {
            return m15707a(mo10585r() - 1, -1, z);
        }
        return m15707a(0, mo10585r(), z);
    }

    /* renamed from: b */
    private final View m15714b(boolean z) {
        if (this.f16225e) {
            return m15707a(mo10585r() - 1, -1, z);
        }
        return m15707a(0, mo10585r(), z);
    }

    /* renamed from: n */
    public final int mo10481n() {
        View a = m15707a(0, mo10585r(), false);
        if (a == null) {
            return -1;
        }
        return m15975i(a);
    }

    /* renamed from: F */
    private final View m15704F() {
        return m15721f(mo10585r() - 1, -1);
    }

    /* renamed from: e */
    private final View m15719e(C0656yb ybVar, C0664yj yjVar) {
        return mo10424a(ybVar, yjVar, mo10585r() - 1, -1, yjVar.mo10626a());
    }

    /* renamed from: o */
    public final int mo10482o() {
        View a = m15707a(mo10585r() - 1, -1, false);
        if (a != null) {
            return m15975i(a);
        }
        return -1;
    }

    /* renamed from: f */
    private final View m15721f(int i, int i2) {
        int i3;
        mo10479l();
        if (i2 <= i && i2 >= i) {
            return mo10582h(i);
        }
        int d = this.f16224d.mo10519d(mo10582h(i));
        int c = this.f16224d.mo10516c();
        int i4 = d < c ? 16388 : 4097;
        if (d < c) {
            i3 = 16644;
        } else {
            i3 = 4161;
        }
        if (this.f16223c != 0) {
            return this.f16301l.mo10749a(i, i2, i3, i4);
        }
        return this.f16300k.mo10749a(i, i2, i3, i4);
    }

    /* renamed from: a */
    private final View m15707a(int i, int i2, boolean z) {
        int i3;
        mo10479l();
        if (!z) {
            i3 = 320;
        } else {
            i3 = 24579;
        }
        if (this.f16223c != 0) {
            return this.f16301l.mo10749a(i, i2, i3, 320);
        }
        return this.f16300k.mo10749a(i, i2, i3, 320);
    }

    /* renamed from: a */
    public View mo10424a(C0656yb ybVar, C0664yj yjVar, int i, int i2, int i3) {
        int i4;
        mo10479l();
        int c = this.f16224d.mo10516c();
        int a = this.f16224d.mo10511a();
        if (i2 > i) {
            i4 = 1;
        } else {
            i4 = -1;
        }
        View view = null;
        View view2 = null;
        while (i != i2) {
            View h = mo10582h(i);
            int i5 = m15975i(h);
            if (i5 >= 0 && i5 < i3) {
                if (!((C0648xu) h.getLayoutParams()).mo10594a()) {
                    if (this.f16224d.mo10519d(h) < a && this.f16224d.mo10517c(h) >= c) {
                        return h;
                    }
                    if (view == null) {
                        view = h;
                    }
                } else if (view2 == null) {
                    view2 = h;
                }
            }
            i += i4;
        }
        return view == null ? view2 : view;
    }

    /* renamed from: b */
    public final View mo10466b(int i) {
        int r = mo10585r();
        if (r == 0) {
            return null;
        }
        int i2 = i - m15975i(mo10582h(0));
        if (i2 >= 0 && i2 < r) {
            View h = mo10582h(i2);
            if (m15975i(h) == i) {
                return h;
            }
        }
        int r2 = mo10585r();
        for (int i3 = 0; i3 < r2; i3++) {
            View h2 = mo10582h(i3);
            C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(h2);
            if (childViewHolderInt != null && childViewHolderInt.mo10643c() == i && !childViewHolderInt.mo10642b() && (this.f16299j.mState.f16364g || !childViewHolderInt.mo10653m())) {
                return h2;
            }
        }
        return null;
    }

    /* renamed from: a */
    private final int m15705a(int i, C0656yb ybVar, C0664yj yjVar, boolean z) {
        int a;
        int a2 = this.f16224d.mo10511a() - i;
        if (a2 <= 0) {
            return 0;
        }
        int i2 = -m15716c(-a2, ybVar, yjVar);
        int i3 = i + i2;
        if (!z || (a = this.f16224d.mo10511a() - i3) <= 0) {
            return i2;
        }
        this.f16224d.mo10513a(a);
        return a + i2;
    }

    /* renamed from: b */
    private final int m15713b(int i, C0656yb ybVar, C0664yj yjVar, boolean z) {
        int c;
        int c2 = i - this.f16224d.mo10516c();
        if (c2 <= 0) {
            return 0;
        }
        int i2 = -m15716c(c2, ybVar, yjVar);
        int i3 = i + i2;
        if (!z || (c = i3 - this.f16224d.mo10516c()) <= 0) {
            return i2;
        }
        this.f16224d.mo10513a(-c);
        return i2 - c;
    }

    /* renamed from: a */
    public C0648xu mo2617a() {
        return new C0648xu(-2, -2);
    }

    /* renamed from: D */
    private final View m15702D() {
        return mo10582h(!this.f16225e ? mo10585r() - 1 : 0);
    }

    /* renamed from: C */
    private final View m15701C() {
        return mo10582h(this.f16225e ? mo10585r() - 1 : 0);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    /* renamed from: b */
    public int mo4642b(C0664yj yjVar) {
        if (yjVar.f16358a != -1) {
            return this.f16224d.mo10518d();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public final boolean mo10478k() {
        return mo10584q() == 1;
    }

    /* renamed from: a */
    public void mo10429a(C0656yb ybVar, C0664yj yjVar, C0604wd wdVar, C0603wc wcVar) {
        int i;
        int i2;
        int i3;
        int i4;
        C0604wd wdVar2 = wdVar;
        C0603wc wcVar2 = wcVar;
        View a = wdVar2.mo10453a(ybVar);
        if (a == null) {
            wcVar2.f16202b = true;
            return;
        }
        C0648xu xuVar = (C0648xu) a.getLayoutParams();
        if (wdVar2.f16216l == null) {
            if (this.f16225e != (wdVar2.f16210f == -1)) {
                mo10577b(a, 0);
            } else {
                mo10576b(a);
            }
        } else {
            if (this.f16225e != (wdVar2.f16210f == -1)) {
                mo10568a(a, 0);
            } else {
                mo10567a(a);
            }
        }
        C0648xu xuVar2 = (C0648xu) a.getLayoutParams();
        Rect itemDecorInsetsForChild = this.f16299j.getItemDecorInsetsForChild(a);
        int i5 = itemDecorInsetsForChild.left;
        int i6 = itemDecorInsetsForChild.right;
        int i7 = itemDecorInsetsForChild.top;
        int i8 = itemDecorInsetsForChild.bottom;
        int a2 = C0647xt.m15965a(this.f16310u, this.f16308s, mo10586s() + mo10588u() + xuVar2.leftMargin + xuVar2.rightMargin + i5 + i6, xuVar2.width, mo2620i());
        int a3 = C0647xt.m15965a(this.f16311v, this.f16309t, mo10587t() + mo10589v() + xuVar2.topMargin + xuVar2.bottomMargin + i7 + i8, xuVar2.height, mo10477j());
        if (mo10573a(a, a2, a3, xuVar2)) {
            a.measure(a2, a3);
        }
        wcVar2.f16201a = this.f16224d.mo10512a(a);
        if (this.f16223c == 1) {
            if (mo10478k()) {
                i3 = this.f16310u - mo10588u();
                i2 = i3 - this.f16224d.mo10515b(a);
            } else {
                i2 = mo10586s();
                i3 = this.f16224d.mo10515b(a) + i2;
            }
            if (wdVar2.f16210f == -1) {
                i4 = wdVar2.f16206b;
                i = i4 - wcVar2.f16201a;
            } else {
                i = wdVar2.f16206b;
                i4 = wcVar2.f16201a + i;
            }
        } else {
            int t = mo10587t();
            int b = this.f16224d.mo10515b(a) + t;
            if (wdVar2.f16210f == -1) {
                int i9 = wdVar2.f16206b;
                int i10 = i9 - wcVar2.f16201a;
                int i11 = t;
                i3 = i9;
                i4 = b;
                i2 = i10;
                i = i11;
            } else {
                int i12 = wdVar2.f16206b;
                int i13 = wcVar2.f16201a + i12;
                int i14 = b;
                i2 = i12;
                i4 = i14;
                int i15 = i13;
                i = t;
                i3 = i15;
            }
        }
        m15966a(a, i2, i, i3, i4);
        if (xuVar.mo10594a() || xuVar.mo10595b()) {
            wcVar2.f16203c = true;
        }
        wcVar2.f16204d = a.hasFocusable();
    }

    /* renamed from: a */
    public View mo10423a(View view, int i, C0656yb ybVar, C0664yj yjVar) {
        int e;
        View view2;
        View view3;
        m15699A();
        if (mo10585r() == 0 || (e = mo10471e(i)) == Integer.MIN_VALUE) {
            return null;
        }
        mo10479l();
        m15709a(e, (int) (((float) this.f16224d.mo10518d()) * 0.33333334f), false, yjVar);
        C0604wd wdVar = this.f16221a;
        wdVar.f16211g = RecyclerView.UNDEFINED_DURATION;
        wdVar.f16205a = false;
        m15706a(ybVar, wdVar, yjVar, true);
        if (e == -1) {
            if (!this.f16225e) {
                view2 = m15703E();
            } else {
                view2 = m15704F();
            }
        } else if (!this.f16225e) {
            view2 = m15704F();
        } else {
            view2 = m15703E();
        }
        if (e != -1) {
            view3 = m15702D();
        } else {
            view3 = m15701C();
        }
        if (!view3.hasFocusable()) {
            return view2;
        }
        if (view2 == null) {
            return null;
        }
        return view3;
    }

    /* renamed from: a */
    public final void mo10464a(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.f16299j;
        C0656yb ybVar = recyclerView.mRecycler;
        C0664yj yjVar = recyclerView.mState;
        if (!(recyclerView == null || accessibilityEvent == null)) {
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f16299j.canScrollVertically(-1) && !this.f16299j.canScrollHorizontally(-1) && !this.f16299j.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            C0634xg xgVar = this.f16299j.mAdapter;
            if (xgVar != null) {
                accessibilityEvent.setItemCount(xgVar.mo220a());
            }
        }
        if (mo10585r() > 0) {
            accessibilityEvent.setFromIndex(mo10481n());
            accessibilityEvent.setToIndex(mo10482o());
        }
    }

    /* renamed from: c */
    public void mo4533c(C0656yb ybVar, C0664yj yjVar) {
        int i;
        int i2;
        int i3;
        View b;
        View view;
        int i4;
        int i5;
        int i6;
        if (!(this.f16228h == null && this.f16226f == -1) && yjVar.mo10626a() == 0) {
            mo10578b(ybVar);
            return;
        }
        C0606wf wfVar = this.f16228h;
        if (wfVar != null && wfVar.mo10458a()) {
            this.f16226f = this.f16228h.f16218a;
        }
        mo10479l();
        this.f16221a.f16205a = false;
        m15699A();
        View w = mo10590w();
        C0602wb wbVar = this.f16229w;
        if (!wbVar.f16200e || this.f16226f != -1 || this.f16228h != null) {
            wbVar.mo10448a();
            C0602wb wbVar2 = this.f16229w;
            wbVar2.f16199d = this.f16225e;
            if (!yjVar.f16364g && (i5 = this.f16226f) != -1) {
                if (i5 < 0 || i5 >= yjVar.mo10626a()) {
                    this.f16226f = -1;
                    this.f16227g = RecyclerView.UNDEFINED_DURATION;
                } else {
                    wbVar2.f16197b = this.f16226f;
                    C0606wf wfVar2 = this.f16228h;
                    if (wfVar2 == null || !wfVar2.mo10458a()) {
                        if (this.f16227g == Integer.MIN_VALUE) {
                            View b2 = mo10466b(this.f16226f);
                            if (b2 == null) {
                                if (mo10585r() > 0) {
                                    wbVar2.f16199d = (this.f16226f < m15975i(mo10582h(0))) == this.f16225e;
                                }
                                wbVar2.mo10450b();
                            } else if (this.f16224d.mo10512a(b2) > this.f16224d.mo10518d()) {
                                wbVar2.mo10450b();
                            } else if (this.f16224d.mo10519d(b2) - this.f16224d.mo10516c() < 0) {
                                wbVar2.f16198c = this.f16224d.mo10516c();
                                wbVar2.f16199d = false;
                            } else if (this.f16224d.mo10511a() - this.f16224d.mo10517c(b2) < 0) {
                                wbVar2.f16198c = this.f16224d.mo10511a();
                                wbVar2.f16199d = true;
                            } else {
                                if (wbVar2.f16199d) {
                                    i6 = this.f16224d.mo10517c(b2) + this.f16224d.mo10525h();
                                } else {
                                    i6 = this.f16224d.mo10519d(b2);
                                }
                                wbVar2.f16198c = i6;
                            }
                        } else {
                            boolean z = this.f16225e;
                            wbVar2.f16199d = z;
                            if (z) {
                                wbVar2.f16198c = this.f16224d.mo10511a() - this.f16227g;
                            } else {
                                wbVar2.f16198c = this.f16224d.mo10516c() + this.f16227g;
                            }
                        }
                        this.f16229w.f16200e = true;
                    } else {
                        boolean z2 = this.f16228h.f16220c;
                        wbVar2.f16199d = z2;
                        if (z2) {
                            wbVar2.f16198c = this.f16224d.mo10511a() - this.f16228h.f16219b;
                        } else {
                            wbVar2.f16198c = this.f16224d.mo10516c() + this.f16228h.f16219b;
                        }
                        this.f16229w.f16200e = true;
                    }
                }
            }
            if (mo10585r() != 0) {
                View w2 = mo10590w();
                if (w2 != null) {
                    C0648xu xuVar = (C0648xu) w2.getLayoutParams();
                    if (!xuVar.mo10594a() && xuVar.mo10596c() >= 0 && xuVar.mo10596c() < yjVar.mo10626a()) {
                        wbVar2.mo10449a(w2, m15975i(w2));
                        this.f16229w.f16200e = true;
                    }
                }
                if (!wbVar2.f16199d) {
                    if (!this.f16225e) {
                        view = m15717d(ybVar, yjVar);
                    } else {
                        view = m15719e(ybVar, yjVar);
                    }
                } else if (!this.f16225e) {
                    view = m15719e(ybVar, yjVar);
                } else {
                    view = m15717d(ybVar, yjVar);
                }
                if (view != null) {
                    wbVar2.mo10451b(view, m15975i(view));
                    if (!yjVar.f16364g && mo4532b() && (this.f16224d.mo10519d(view) >= this.f16224d.mo10511a() || this.f16224d.mo10517c(view) < this.f16224d.mo10516c())) {
                        if (wbVar2.f16199d) {
                            i4 = this.f16224d.mo10511a();
                        } else {
                            i4 = this.f16224d.mo10516c();
                        }
                        wbVar2.f16198c = i4;
                    }
                    this.f16229w.f16200e = true;
                }
            }
            wbVar2.mo10450b();
            wbVar2.f16197b = 0;
            this.f16229w.f16200e = true;
        } else if (w != null && (this.f16224d.mo10519d(w) >= this.f16224d.mo10511a() || this.f16224d.mo10517c(w) <= this.f16224d.mo10516c())) {
            this.f16229w.mo10449a(w, m15975i(w));
        }
        C0604wd wdVar = this.f16221a;
        wdVar.f16210f = wdVar.f16215k >= 0 ? 1 : -1;
        int[] iArr = this.f16232z;
        iArr[0] = 0;
        iArr[1] = 0;
        mo6513a(yjVar, iArr);
        int max = Math.max(0, this.f16232z[0]) + this.f16224d.mo10516c();
        int max2 = Math.max(0, this.f16232z[1]) + this.f16224d.mo10520e();
        if (!(!yjVar.f16364g || (i3 = this.f16226f) == -1 || this.f16227g == Integer.MIN_VALUE || (b = mo10466b(i3)) == null)) {
            int d = !this.f16225e ? this.f16227g - (this.f16224d.mo10519d(b) - this.f16224d.mo10516c()) : (this.f16224d.mo10511a() - this.f16224d.mo10517c(b)) - this.f16227g;
            if (d > 0) {
                max += d;
            } else {
                max2 -= d;
            }
        }
        C0602wb wbVar3 = this.f16229w;
        mo10428a(ybVar, yjVar, wbVar3, (wbVar3.f16199d ? !this.f16225e : this.f16225e) ? -1 : 1);
        for (int r = mo10585r() - 1; r >= 0; r--) {
            View h = mo10582h(r);
            C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(h);
            if (!childViewHolderInt.mo10642b()) {
                if (!childViewHolderInt.mo10650j() || childViewHolderInt.mo10653m() || this.f16299j.mAdapter.f16288b) {
                    mo10581g(r);
                    ybVar.mo10614c(h);
                    this.f16299j.mViewInfoStore.mo10758c(childViewHolderInt);
                } else {
                    mo10580f(r);
                    ybVar.mo10607a(childViewHolderInt);
                }
            }
        }
        this.f16221a.f16217m = m15700B();
        C0604wd wdVar2 = this.f16221a;
        wdVar2.f16214j = yjVar.f16364g;
        wdVar2.f16213i = 0;
        C0602wb wbVar4 = this.f16229w;
        if (wbVar4.f16199d) {
            m15715b(wbVar4);
            C0604wd wdVar3 = this.f16221a;
            wdVar3.f16212h = max;
            m15706a(ybVar, wdVar3, yjVar, false);
            C0604wd wdVar4 = this.f16221a;
            i2 = wdVar4.f16206b;
            int i7 = wdVar4.f16208d;
            int i8 = wdVar4.f16207c;
            if (i8 > 0) {
                max2 += i8;
            }
            m15710a(this.f16229w);
            C0604wd wdVar5 = this.f16221a;
            wdVar5.f16212h = max2;
            wdVar5.f16208d += wdVar5.f16209e;
            m15706a(ybVar, wdVar5, yjVar, false);
            C0604wd wdVar6 = this.f16221a;
            i = wdVar6.f16206b;
            int i9 = wdVar6.f16207c;
            if (i9 > 0) {
                m15720e(i7, i2);
                C0604wd wdVar7 = this.f16221a;
                wdVar7.f16212h = i9;
                m15706a(ybVar, wdVar7, yjVar, false);
                i2 = this.f16221a.f16206b;
            }
        } else {
            m15710a(wbVar4);
            C0604wd wdVar8 = this.f16221a;
            wdVar8.f16212h = max2;
            m15706a(ybVar, wdVar8, yjVar, false);
            C0604wd wdVar9 = this.f16221a;
            i = wdVar9.f16206b;
            int i10 = wdVar9.f16208d;
            int i11 = wdVar9.f16207c;
            if (i11 > 0) {
                max += i11;
            }
            m15715b(this.f16229w);
            C0604wd wdVar10 = this.f16221a;
            wdVar10.f16212h = max;
            wdVar10.f16208d += wdVar10.f16209e;
            m15706a(ybVar, wdVar10, yjVar, false);
            C0604wd wdVar11 = this.f16221a;
            i2 = wdVar11.f16206b;
            int i12 = wdVar11.f16207c;
            if (i12 > 0) {
                m15718d(i10, i);
                C0604wd wdVar12 = this.f16221a;
                wdVar12.f16212h = i12;
                m15706a(ybVar, wdVar12, yjVar, false);
                i = this.f16221a.f16206b;
            }
        }
        if (mo10585r() > 0) {
            if (!this.f16225e) {
                int b3 = m15713b(i2, ybVar, yjVar, true);
                int i13 = i + b3;
                int a = m15705a(i13, ybVar, yjVar, false);
                i2 = i2 + b3 + a;
                i = i13 + a;
            } else {
                int a2 = m15705a(i, ybVar, yjVar, true);
                int i14 = i2 + a2;
                int b4 = m15713b(i14, ybVar, yjVar, false);
                i2 = i14 + b4;
                i = i + a2 + b4;
            }
        }
        if (yjVar.f16368k && mo10585r() != 0 && !yjVar.f16364g && mo4532b()) {
            List list = ybVar.f16327d;
            int size = list.size();
            int i15 = m15975i(mo10582h(0));
            int i16 = 0;
            int i17 = 0;
            for (int i18 = 0; i18 < size; i18++) {
                C0667ym ymVar = (C0667ym) list.get(i18);
                if (!ymVar.mo10653m()) {
                    if ((ymVar.mo10643c() < i15) == this.f16225e) {
                        i17 += this.f16224d.mo10512a(ymVar.f16382a);
                    } else {
                        i16 += this.f16224d.mo10512a(ymVar.f16382a);
                    }
                }
            }
            this.f16221a.f16216l = list;
            if (i16 > 0) {
                m15720e(m15975i(m15701C()), i2);
                C0604wd wdVar13 = this.f16221a;
                wdVar13.f16212h = i16;
                wdVar13.f16207c = 0;
                wdVar13.mo10454a();
                m15706a(ybVar, this.f16221a, yjVar, false);
            }
            if (i17 > 0) {
                m15718d(m15975i(m15702D()), i);
                C0604wd wdVar14 = this.f16221a;
                wdVar14.f16212h = i17;
                wdVar14.f16207c = 0;
                wdVar14.mo10454a();
                m15706a(ybVar, this.f16221a, yjVar, false);
            }
            this.f16221a.f16216l = null;
        }
        if (yjVar.f16364g) {
            this.f16229w.mo10448a();
            return;
        }
        C0624wx wxVar = this.f16224d;
        wxVar.f16277b = wxVar.mo10518d();
    }

    /* renamed from: a */
    public void mo10430a(C0664yj yjVar) {
        this.f16228h = null;
        this.f16226f = -1;
        this.f16227g = RecyclerView.UNDEFINED_DURATION;
        this.f16229w.mo10448a();
    }

    /* renamed from: a */
    public final void mo10463a(Parcelable parcelable) {
        if (parcelable instanceof C0606wf) {
            this.f16228h = (C0606wf) parcelable;
            mo10583p();
        }
    }

    /* renamed from: h */
    public final Parcelable mo10476h() {
        C0606wf wfVar = this.f16228h;
        if (wfVar != null) {
            return new C0606wf(wfVar);
        }
        C0606wf wfVar2 = new C0606wf();
        if (mo10585r() > 0) {
            mo10479l();
            boolean z = this.f16225e;
            wfVar2.f16220c = z;
            if (z) {
                View D = m15702D();
                wfVar2.f16219b = this.f16224d.mo10511a() - this.f16224d.mo10517c(D);
                wfVar2.f16218a = m15975i(D);
            } else {
                View C = m15701C();
                wfVar2.f16218a = m15975i(C);
                wfVar2.f16219b = this.f16224d.mo10519d(C) - this.f16224d.mo10516c();
            }
        } else {
            wfVar2.mo10459b();
        }
        return wfVar2;
    }

    /* renamed from: a */
    private final void m15712a(C0656yb ybVar, C0604wd wdVar) {
        if (wdVar.f16205a && !wdVar.f16217m) {
            int i = wdVar.f16211g;
            int i2 = wdVar.f16213i;
            if (wdVar.f16210f == -1) {
                int r = mo10585r();
                if (i >= 0) {
                    int b = (this.f16224d.mo10514b() - i) + i2;
                    if (!this.f16225e) {
                        int i3 = r - 1;
                        for (int i4 = i3; i4 >= 0; i4--) {
                            View h = mo10582h(i4);
                            if (this.f16224d.mo10519d(h) < b || this.f16224d.mo10523f(h) < b) {
                                m15711a(ybVar, i3, i4);
                                return;
                            }
                        }
                        return;
                    }
                    for (int i5 = 0; i5 < r; i5++) {
                        View h2 = mo10582h(i5);
                        if (this.f16224d.mo10519d(h2) < b || this.f16224d.mo10523f(h2) < b) {
                            m15711a(ybVar, 0, i5);
                            return;
                        }
                    }
                }
            } else if (i >= 0) {
                int i6 = i - i2;
                int r2 = mo10585r();
                if (this.f16225e) {
                    int i7 = r2 - 1;
                    for (int i8 = i7; i8 >= 0; i8--) {
                        View h3 = mo10582h(i8);
                        if (this.f16224d.mo10517c(h3) > i6 || this.f16224d.mo10521e(h3) > i6) {
                            m15711a(ybVar, i7, i8);
                            return;
                        }
                    }
                    return;
                }
                for (int i9 = 0; i9 < r2; i9++) {
                    View h4 = mo10582h(i9);
                    if (this.f16224d.mo10517c(h4) > i6 || this.f16224d.mo10521e(h4) > i6) {
                        m15711a(ybVar, 0, i9);
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private final void m15711a(C0656yb ybVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 > i) {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                mo10565a(i3, ybVar);
            }
            return;
        }
        while (i > i2) {
            mo10565a(i, ybVar);
            i--;
        }
    }

    /* renamed from: B */
    private final boolean m15700B() {
        return this.f16224d.mo10522f() == 0 && this.f16224d.mo10514b() == 0;
    }

    /* renamed from: A */
    private final void m15699A() {
        if (this.f16223c == 1 || !mo10478k()) {
            this.f16225e = false;
        } else {
            this.f16225e = true;
        }
    }

    /* renamed from: c */
    private final int m15716c(int i, C0656yb ybVar, C0664yj yjVar) {
        int i2;
        if (!(mo10585r() == 0 || i == 0)) {
            mo10479l();
            this.f16221a.f16205a = true;
            if (i > 0) {
                i2 = 1;
            } else {
                i2 = -1;
            }
            int abs = Math.abs(i);
            m15709a(i2, abs, true, yjVar);
            C0604wd wdVar = this.f16221a;
            int a = wdVar.f16211g + m15706a(ybVar, wdVar, yjVar, false);
            if (a >= 0) {
                if (abs > a) {
                    i = i2 * a;
                }
                this.f16224d.mo10513a(-i);
                this.f16221a.f16215k = i;
                return i;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public int mo10421a(int i, C0656yb ybVar, C0664yj yjVar) {
        if (this.f16223c == 1) {
            return 0;
        }
        return m15716c(i, ybVar, yjVar);
    }

    /* renamed from: d */
    public final void mo10470d(int i) {
        this.f16226f = i;
        this.f16227g = RecyclerView.UNDEFINED_DURATION;
        C0606wf wfVar = this.f16228h;
        if (wfVar != null) {
            wfVar.mo10459b();
        }
        mo10583p();
    }

    /* renamed from: b */
    public int mo10433b(int i, C0656yb ybVar, C0664yj yjVar) {
        if (this.f16223c != 0) {
            return m15716c(i, ybVar, yjVar);
        }
        return 0;
    }

    /* renamed from: m */
    public final boolean mo10480m() {
        if (!(this.f16309t == 1073741824 || this.f16308s == 1073741824)) {
            int r = mo10585r();
            for (int i = 0; i < r; i++) {
                ViewGroup.LayoutParams layoutParams = mo10582h(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo6547a(RecyclerView recyclerView, int i) {
        C0663yi yiVar = new C0663yi(recyclerView.getContext());
        yiVar.f16342a = i;
        mo10572a(yiVar);
    }

    /* renamed from: a */
    private final void m15709a(int i, int i2, boolean z, C0664yj yjVar) {
        int i3;
        this.f16221a.f16217m = m15700B();
        this.f16221a.f16210f = i;
        int[] iArr = this.f16232z;
        iArr[0] = 0;
        int i4 = 1;
        iArr[1] = 0;
        mo6513a(yjVar, iArr);
        int max = Math.max(0, this.f16232z[0]);
        int max2 = Math.max(0, this.f16232z[1]);
        C0604wd wdVar = this.f16221a;
        int i5 = i == 1 ? max2 : max;
        wdVar.f16212h = i5;
        if (i != 1) {
            max = max2;
        }
        wdVar.f16213i = max;
        if (i == 1) {
            wdVar.f16212h = i5 + this.f16224d.mo10520e();
            View D = m15702D();
            C0604wd wdVar2 = this.f16221a;
            if (this.f16225e) {
                i4 = -1;
            }
            wdVar2.f16209e = i4;
            int i6 = m15975i(D);
            C0604wd wdVar3 = this.f16221a;
            wdVar2.f16208d = i6 + wdVar3.f16209e;
            wdVar3.f16206b = this.f16224d.mo10517c(D);
            i3 = this.f16224d.mo10517c(D) - this.f16224d.mo10511a();
        } else {
            View C = m15701C();
            this.f16221a.f16212h += this.f16224d.mo10516c();
            C0604wd wdVar4 = this.f16221a;
            if (!this.f16225e) {
                i4 = -1;
            }
            wdVar4.f16209e = i4;
            int i7 = m15975i(C);
            C0604wd wdVar5 = this.f16221a;
            wdVar4.f16208d = i7 + wdVar5.f16209e;
            wdVar5.f16206b = this.f16224d.mo10519d(C);
            i3 = (-this.f16224d.mo10519d(C)) + this.f16224d.mo10516c();
        }
        C0604wd wdVar6 = this.f16221a;
        wdVar6.f16207c = i2;
        if (z) {
            wdVar6.f16207c = i2 - i3;
        }
        wdVar6.f16211g = i3;
    }

    /* renamed from: d */
    private final void m15718d(int i, int i2) {
        int i3;
        this.f16221a.f16207c = this.f16224d.mo10511a() - i2;
        C0604wd wdVar = this.f16221a;
        if (!this.f16225e) {
            i3 = 1;
        } else {
            i3 = -1;
        }
        wdVar.f16209e = i3;
        wdVar.f16208d = i;
        wdVar.f16210f = 1;
        wdVar.f16206b = i2;
        wdVar.f16211g = RecyclerView.UNDEFINED_DURATION;
    }

    /* renamed from: a */
    private final void m15710a(C0602wb wbVar) {
        m15718d(wbVar.f16197b, wbVar.f16198c);
    }

    /* renamed from: e */
    private final void m15720e(int i, int i2) {
        int i3;
        this.f16221a.f16207c = i2 - this.f16224d.mo10516c();
        C0604wd wdVar = this.f16221a;
        wdVar.f16208d = i;
        if (!this.f16225e) {
            i3 = -1;
        } else {
            i3 = 1;
        }
        wdVar.f16209e = i3;
        wdVar.f16210f = -1;
        wdVar.f16206b = i2;
        wdVar.f16211g = RecyclerView.UNDEFINED_DURATION;
    }

    /* renamed from: b */
    private final void m15715b(C0602wb wbVar) {
        m15720e(wbVar.f16197b, wbVar.f16198c);
    }
}
