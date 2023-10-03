package p000;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;

/* renamed from: xt */
/* compiled from: PG */
public abstract class C0647xt {

    /* renamed from: a */
    private final C0698zq f16296a = new C0644xq(this);

    /* renamed from: b */
    private final C0698zq f16297b = new C0645xr(this);

    /* renamed from: i */
    public C0563uq f16298i;

    /* renamed from: j */
    public RecyclerView f16299j;

    /* renamed from: k */
    public final C0699zr f16300k = new C0699zr(this.f16296a);

    /* renamed from: l */
    public final C0699zr f16301l = new C0699zr(this.f16297b);

    /* renamed from: m */
    public C0663yi f16302m;

    /* renamed from: n */
    public boolean f16303n = false;

    /* renamed from: o */
    public final boolean f16304o = true;

    /* renamed from: p */
    public final boolean f16305p = true;

    /* renamed from: q */
    public int f16306q;

    /* renamed from: r */
    public boolean f16307r;

    /* renamed from: s */
    public int f16308s;

    /* renamed from: t */
    public int f16309t;

    /* renamed from: u */
    public int f16310u;

    /* renamed from: v */
    public int f16311v;

    /* renamed from: a */
    public int mo10421a(int i, C0656yb ybVar, C0664yj yjVar) {
        throw null;
    }

    /* renamed from: a */
    public View mo10423a(View view, int i, C0656yb ybVar, C0664yj yjVar) {
        throw null;
    }

    /* renamed from: a */
    public abstract C0648xu mo2617a();

    /* renamed from: a */
    public void mo4531a(int i, int i2, C0664yj yjVar, C0646xs xsVar) {
    }

    /* renamed from: a */
    public void mo10462a(int i, C0646xs xsVar) {
    }

    /* renamed from: a */
    public void mo10463a(Parcelable parcelable) {
    }

    /* renamed from: a */
    public void mo6547a(RecyclerView recyclerView, int i) {
        throw null;
    }

    /* renamed from: a */
    public void mo10464a(AccessibilityEvent accessibilityEvent) {
        throw null;
    }

    /* renamed from: a */
    public void mo10465a(String str) {
        throw null;
    }

    /* renamed from: a */
    public void mo10430a(C0664yj yjVar) {
    }

    /* renamed from: a */
    public boolean mo10432a(C0648xu xuVar) {
        return xuVar != null;
    }

    /* renamed from: b */
    public int mo10433b(int i, C0656yb ybVar, C0664yj yjVar) {
        throw null;
    }

    /* renamed from: b */
    public View mo10466b(int i) {
        throw null;
    }

    /* renamed from: b */
    public boolean mo4532b() {
        throw null;
    }

    /* renamed from: c */
    public int mo10467c(C0664yj yjVar) {
        throw null;
    }

    /* renamed from: c */
    public void mo10435c() {
    }

    /* renamed from: c */
    public void mo4533c(C0656yb ybVar, C0664yj yjVar) {
        throw null;
    }

    /* renamed from: d */
    public int mo10469d(C0664yj yjVar) {
        throw null;
    }

    /* renamed from: d */
    public void mo10436d() {
    }

    /* renamed from: d */
    public void mo10470d(int i) {
    }

    /* renamed from: e */
    public int mo10472e(C0664yj yjVar) {
        throw null;
    }

    /* renamed from: e */
    public void mo10437e() {
    }

    /* renamed from: f */
    public int mo10473f(C0664yj yjVar) {
        throw null;
    }

    /* renamed from: f */
    public void mo10438f() {
    }

    /* renamed from: g */
    public int mo10474g(C0664yj yjVar) {
        throw null;
    }

    /* renamed from: g */
    public void mo10439g() {
    }

    /* renamed from: h */
    public int mo10475h(C0664yj yjVar) {
        throw null;
    }

    /* renamed from: h */
    public Parcelable mo10476h() {
        throw null;
    }

    /* renamed from: i */
    public boolean mo2620i() {
        throw null;
    }

    /* renamed from: j */
    public boolean mo10477j() {
        throw null;
    }

    /* renamed from: m */
    public boolean mo10480m() {
        throw null;
    }

    /* renamed from: a */
    public final void mo10567a(View view) {
        mo10568a(view, -1);
    }

    /* renamed from: a */
    public final void mo10568a(View view, int i) {
        m15967a(view, i, true);
    }

    /* renamed from: b */
    public final void mo10576b(View view) {
        mo10577b(view, -1);
    }

    /* renamed from: b */
    public final void mo10577b(View view, int i) {
        m15967a(view, i, false);
    }

    /* renamed from: a */
    private final void m15967a(View view, int i, boolean z) {
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (!z && !childViewHolderInt.mo10653m()) {
            this.f16299j.mViewInfoStore.mo10758c(childViewHolderInt);
        } else {
            this.f16299j.mViewInfoStore.mo10756b(childViewHolderInt);
        }
        C0648xu xuVar = (C0648xu) view.getLayoutParams();
        if (childViewHolderInt.mo10647g() || childViewHolderInt.mo10645e()) {
            if (childViewHolderInt.mo10645e()) {
                childViewHolderInt.mo10646f();
            } else {
                childViewHolderInt.mo10648h();
            }
            this.f16298i.mo10312a(view, i, view.getLayoutParams(), false);
        } else if (view.getParent() == this.f16299j) {
            int b = this.f16298i.mo10315b(view);
            if (i == -1) {
                i = this.f16298i.mo10309a();
            }
            if (b == -1) {
                throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f16299j.indexOfChild(view) + this.f16299j.exceptionLabel());
            } else if (b != i) {
                C0647xt xtVar = this.f16299j.mLayout;
                View h = xtVar.mo10582h(b);
                if (h != null) {
                    xtVar.mo10581g(b);
                    C0648xu xuVar2 = (C0648xu) h.getLayoutParams();
                    C0667ym childViewHolderInt2 = RecyclerView.getChildViewHolderInt(h);
                    if (childViewHolderInt2.mo10653m()) {
                        xtVar.f16299j.mViewInfoStore.mo10756b(childViewHolderInt2);
                    } else {
                        xtVar.f16299j.mViewInfoStore.mo10758c(childViewHolderInt2);
                    }
                    xtVar.f16298i.mo10312a(h, i, xuVar2, childViewHolderInt2.mo10653m());
                } else {
                    throw new IllegalArgumentException("Cannot move a child from non-existing index:" + b + xtVar.f16299j.toString());
                }
            }
        } else {
            this.f16298i.mo10313a(view, i, false);
            xuVar.f16314e = true;
            C0663yi yiVar = this.f16302m;
            if (yiVar != null && yiVar.f16346e && yiVar.mo10622a(view) == yiVar.f16342a) {
                yiVar.f16347f = view;
            }
        }
        if (xuVar.f16315f) {
            childViewHolderInt.f16382a.invalidate();
            xuVar.f16315f = false;
        }
    }

    /* renamed from: a */
    public static int m15964a(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? Math.max(i2, i3) : size;
        }
        return Math.min(size, Math.max(i2, i3));
    }

    /* renamed from: g */
    public final void mo10581g(int i) {
        mo10582h(i);
        this.f16298i.mo10319d(i);
    }

    /* renamed from: a */
    public C0648xu mo2618a(Context context, AttributeSet attributeSet) {
        return new C0648xu(context, attributeSet);
    }

    /* renamed from: a */
    public C0648xu mo2619a(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C0648xu) {
            return new C0648xu((C0648xu) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0648xu((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0648xu(layoutParams);
    }

    /* renamed from: h */
    public final View mo10582h(int i) {
        C0563uq uqVar = this.f16298i;
        if (uqVar != null) {
            return uqVar.mo10316b(i);
        }
        return null;
    }

    /* renamed from: r */
    public final int mo10585r() {
        C0563uq uqVar = this.f16298i;
        if (uqVar == null) {
            return 0;
        }
        return uqVar.mo10309a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        if (r5 != 1073741824) goto L_0x0033;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m15965a(int r4, int r5, int r6, int r7, boolean r8) {
        /*
            int r4 = r4 - r6
            r6 = 0
            int r4 = java.lang.Math.max(r6, r4)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 != 0) goto L_0x0020
            if (r7 < 0) goto L_0x0011
            goto L_0x0036
        L_0x0011:
            if (r7 == r1) goto L_0x001f
            if (r7 != r0) goto L_0x0033
            if (r5 == r2) goto L_0x001b
            if (r5 == r3) goto L_0x001b
            r5 = 0
            goto L_0x001d
        L_0x001b:
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x001d:
            r7 = r4
            goto L_0x0038
        L_0x001f:
            goto L_0x002f
        L_0x0020:
            if (r7 >= 0) goto L_0x0036
            if (r7 != r1) goto L_0x0031
            if (r5 == r2) goto L_0x002f
            if (r5 == 0) goto L_0x002b
            if (r5 == r3) goto L_0x002f
            goto L_0x0033
        L_0x002b:
            r5 = 0
            r7 = 0
            goto L_0x0038
        L_0x002f:
            r7 = r4
            goto L_0x0038
        L_0x0031:
            if (r7 != r0) goto L_0x0033
        L_0x0033:
            r5 = 0
            r7 = 0
            goto L_0x0038
        L_0x0036:
            r5 = 1073741824(0x40000000, float:2.0)
        L_0x0038:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0647xt.m15965a(int, int, int, int, boolean):int");
    }

    /* renamed from: b */
    public int mo10434b(C0656yb ybVar, C0664yj yjVar) {
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView == null || recyclerView.mAdapter == null || !mo2620i()) {
            return 1;
        }
        return this.f16299j.mAdapter.mo220a();
    }

    /* renamed from: c */
    public static final int m15969c(View view) {
        return view.getBottom() + ((C0648xu) view.getLayoutParams()).f16313d.bottom;
    }

    /* renamed from: d */
    public static final int m15970d(View view) {
        return view.getLeft() - ((C0648xu) view.getLayoutParams()).f16313d.left;
    }

    /* renamed from: e */
    public static final int m15971e(View view) {
        Rect rect = ((C0648xu) view.getLayoutParams()).f16313d;
        return view.getMeasuredHeight() + rect.top + rect.bottom;
    }

    /* renamed from: f */
    public static final int m15972f(View view) {
        Rect rect = ((C0648xu) view.getLayoutParams()).f16313d;
        return view.getMeasuredWidth() + rect.left + rect.right;
    }

    /* renamed from: g */
    public static final int m15973g(View view) {
        return view.getRight() + ((C0648xu) view.getLayoutParams()).f16313d.right;
    }

    /* renamed from: h */
    public static final int m15974h(View view) {
        return view.getTop() - ((C0648xu) view.getLayoutParams()).f16313d.top;
    }

    /* renamed from: w */
    public final View mo10590w() {
        View focusedChild;
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f16298i.mo10318c(focusedChild)) {
            return null;
        }
        return focusedChild;
    }

    /* renamed from: x */
    public final int mo10591x() {
        C0634xg xgVar;
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView != null) {
            xgVar = recyclerView.getAdapter();
        } else {
            xgVar = null;
        }
        if (xgVar == null) {
            return 0;
        }
        return xgVar.mo220a();
    }

    /* renamed from: q */
    public final int mo10584q() {
        return C0340mj.m14714f(this.f16299j);
    }

    /* renamed from: z */
    public final int mo10593z() {
        return C0340mj.m14719j(this.f16299j);
    }

    /* renamed from: y */
    public final int mo10592y() {
        return C0340mj.m14718i(this.f16299j);
    }

    /* renamed from: v */
    public final int mo10589v() {
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView == null) {
            return 0;
        }
        return recyclerView.getPaddingBottom();
    }

    /* renamed from: s */
    public final int mo10586s() {
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView == null) {
            return 0;
        }
        return recyclerView.getPaddingLeft();
    }

    /* renamed from: u */
    public final int mo10588u() {
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView == null) {
            return 0;
        }
        return recyclerView.getPaddingRight();
    }

    /* renamed from: t */
    public final int mo10587t() {
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView == null) {
            return 0;
        }
        return recyclerView.getPaddingTop();
    }

    /* renamed from: i */
    public static final int m15975i(View view) {
        return ((C0648xu) view.getLayoutParams()).mo10596c();
    }

    /* renamed from: a */
    public int mo10422a(C0656yb ybVar, C0664yj yjVar) {
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView == null || recyclerView.mAdapter == null || !mo10477j()) {
            return 1;
        }
        return this.f16299j.mAdapter.mo220a();
    }

    /* renamed from: a */
    public final void mo10569a(View view, Rect rect) {
        Matrix matrix;
        Rect rect2 = ((C0648xu) view.getLayoutParams()).f16313d;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (!(this.f16299j == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
            RectF rectF = this.f16299j.mTempRectF;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    /* renamed from: b */
    public static boolean m15968b(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        return mode != Integer.MIN_VALUE ? mode == 0 || (mode == 1073741824 && size == i) : size >= i;
    }

    /* renamed from: a */
    public static final void m15966a(View view, int i, int i2, int i3, int i4) {
        C0648xu xuVar = (C0648xu) view.getLayoutParams();
        Rect rect = xuVar.f16313d;
        view.layout(i + rect.left + xuVar.leftMargin, i2 + rect.top + xuVar.topMargin, (i3 - rect.right) - xuVar.rightMargin, (i4 - rect.bottom) - xuVar.bottomMargin);
    }

    /* renamed from: a */
    public void mo10427a(C0656yb ybVar, C0664yj yjVar, View view, C0354mx mxVar) {
        mxVar.mo9429b((Object) C0353mw.m14775a(mo10477j() ? m15975i(view) : 0, 1, mo2620i() ? m15975i(view) : 0, 1, false, false));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10570a(View view, C0354mx mxVar) {
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null && !childViewHolderInt.mo10653m() && !this.f16298i.mo10318c(childViewHolderInt.f16382a)) {
            RecyclerView recyclerView = this.f16299j;
            mo10427a(recyclerView.mRecycler, recyclerView.mState, view, mxVar);
        }
    }

    /* renamed from: b */
    public final void mo10578b(C0656yb ybVar) {
        for (int r = mo10585r() - 1; r >= 0; r--) {
            if (!RecyclerView.getChildViewHolderInt(mo10582h(r)).mo10642b()) {
                mo10565a(r, ybVar);
            }
        }
    }

    /* renamed from: a */
    public final void mo10571a(C0656yb ybVar) {
        int size = ybVar.f16324a.size();
        for (int i = size - 1; i >= 0; i--) {
            View view = ((C0667ym) ybVar.f16324a.get(i)).f16382a;
            C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.mo10642b()) {
                childViewHolderInt.mo10639a(false);
                if (childViewHolderInt.mo10654n()) {
                    this.f16299j.removeDetachedView(view, false);
                }
                C0641xn xnVar = this.f16299j.mItemAnimator;
                if (xnVar != null) {
                    xnVar.mo10373c(childViewHolderInt);
                }
                childViewHolderInt.mo10639a(true);
                ybVar.mo10611b(view);
            }
        }
        ybVar.f16324a.clear();
        ArrayList arrayList = ybVar.f16325b;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (size > 0) {
            this.f16299j.invalidate();
        }
    }

    /* renamed from: a */
    public final void mo10565a(int i, C0656yb ybVar) {
        View h = mo10582h(i);
        mo10580f(i);
        ybVar.mo10606a(h);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r3.f16298i;
        r4 = r0.mo10310a(r4);
     */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo10580f(int r4) {
        /*
            r3 = this;
            android.view.View r0 = r3.mo10582h((int) r4)
            if (r0 == 0) goto L_0x0026
            uq r0 = r3.f16298i
            int r4 = r0.mo10310a((int) r4)
            up r1 = r0.f16027a
            android.view.View r1 = r1.mo10307b((int) r4)
            if (r1 == 0) goto L_0x0026
            uo r2 = r0.f16028b
            boolean r2 = r2.mo10301d(r4)
            if (r2 != 0) goto L_0x001d
            goto L_0x0020
        L_0x001d:
            r0.mo10320d((android.view.View) r1)
        L_0x0020:
            up r0 = r0.f16027a
            r0.mo10306a((int) r4)
            return
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0647xt.mo10580f(int):void");
    }

    /* renamed from: a */
    public boolean mo4640a(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return mo4641a(recyclerView, view, rect, z, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00b4, code lost:
        if ((r10.bottom - r15) > r5) goto L_0x00b6;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo4641a(android.support.p002v7.widget.RecyclerView r17, android.view.View r18, android.graphics.Rect r19, boolean r20, boolean r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = 2
            int[] r3 = new int[r3]
            int r4 = r16.mo10586s()
            int r5 = r16.mo10587t()
            int r6 = r0.f16310u
            int r7 = r16.mo10588u()
            int r8 = r0.f16311v
            int r9 = r16.mo10589v()
            int r10 = r18.getLeft()
            int r11 = r2.left
            int r10 = r10 + r11
            int r11 = r18.getScrollX()
            int r10 = r10 - r11
            int r11 = r18.getTop()
            int r12 = r2.top
            int r11 = r11 + r12
            int r12 = r18.getScrollY()
            int r11 = r11 - r12
            int r12 = r19.width()
            int r2 = r19.height()
            int r4 = r10 - r4
            r13 = 0
            int r14 = java.lang.Math.min(r13, r4)
            int r5 = r11 - r5
            int r15 = java.lang.Math.min(r13, r5)
            int r10 = r10 + r12
            int r6 = r6 - r7
            int r10 = r10 - r6
            int r6 = java.lang.Math.max(r13, r10)
            int r11 = r11 + r2
            int r8 = r8 - r9
            int r11 = r11 - r8
            int r2 = java.lang.Math.max(r13, r11)
            int r7 = r16.mo10584q()
            r8 = 1
            if (r7 == r8) goto L_0x0067
            if (r14 != 0) goto L_0x0066
            int r14 = java.lang.Math.min(r4, r6)
            goto L_0x006f
        L_0x0066:
            goto L_0x006f
        L_0x0067:
            if (r6 != 0) goto L_0x006e
            int r14 = java.lang.Math.max(r14, r10)
            goto L_0x006f
        L_0x006e:
            r14 = r6
        L_0x006f:
            if (r15 != 0) goto L_0x0076
            int r15 = java.lang.Math.min(r5, r2)
            goto L_0x0077
        L_0x0076:
        L_0x0077:
            r3[r13] = r14
            r3[r8] = r15
            r2 = r3[r13]
            if (r21 == 0) goto L_0x00b6
            android.view.View r3 = r17.getFocusedChild()
            if (r3 == 0) goto L_0x00bb
            int r4 = r16.mo10586s()
            int r5 = r16.mo10587t()
            int r6 = r0.f16310u
            int r7 = r16.mo10588u()
            int r9 = r0.f16311v
            int r10 = r16.mo10589v()
            int r9 = r9 - r10
            android.support.v7.widget.RecyclerView r10 = r0.f16299j
            android.graphics.Rect r10 = r10.mTempRect
            android.support.p002v7.widget.RecyclerView.getDecoratedBoundsWithMarginsInt(r3, r10)
            int r3 = r10.left
            int r3 = r3 - r2
            int r6 = r6 - r7
            if (r3 >= r6) goto L_0x00bb
            int r3 = r10.right
            int r3 = r3 - r2
            if (r3 <= r4) goto L_0x00bb
            int r3 = r10.top
            int r3 = r3 - r15
            if (r3 >= r9) goto L_0x00bb
            int r3 = r10.bottom
            int r3 = r3 - r15
            if (r3 <= r5) goto L_0x00bb
        L_0x00b6:
            if (r2 != 0) goto L_0x00bc
            if (r15 == 0) goto L_0x00bb
            goto L_0x00bc
        L_0x00bb:
            return r13
        L_0x00bc:
            if (r20 != 0) goto L_0x00c2
            r1.smoothScrollBy(r2, r15)
            goto L_0x00c5
        L_0x00c2:
            r1.scrollBy(r2, r15)
        L_0x00c5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0647xt.mo4641a(android.support.v7.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    /* renamed from: p */
    public final void mo10583p() {
        RecyclerView recyclerView = this.f16299j;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    /* renamed from: b */
    public final void mo10575b(RecyclerView recyclerView) {
        mo10564a(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    /* renamed from: a */
    public final void mo10564a(int i, int i2) {
        this.f16310u = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        this.f16308s = mode;
        if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.f16310u = 0;
        }
        this.f16311v = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.f16309t = mode2;
        if (mode2 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.f16311v = 0;
        }
    }

    /* renamed from: c */
    public final void mo10579c(int i, int i2) {
        this.f16299j.setMeasuredDimension(i, i2);
    }

    /* renamed from: a */
    public void mo10426a(Rect rect, int i, int i2) {
        int width = rect.width();
        int s = mo10586s();
        int u = mo10588u();
        int height = rect.height();
        int t = mo10587t();
        mo10579c(m15964a(i, width + s + u, mo10592y()), m15964a(i2, height + t + mo10589v(), mo10593z()));
    }

    /* renamed from: b */
    public final void mo10574b(int i, int i2) {
        int r = mo10585r();
        if (r != 0) {
            int i3 = RecyclerView.UNDEFINED_DURATION;
            int i4 = RecyclerView.UNDEFINED_DURATION;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < r; i7++) {
                View h = mo10582h(i7);
                Rect rect = this.f16299j.mTempRect;
                RecyclerView.getDecoratedBoundsWithMarginsInt(h, rect);
                if (rect.left < i5) {
                    i5 = rect.left;
                }
                if (rect.right > i3) {
                    i3 = rect.right;
                }
                if (rect.top < i6) {
                    i6 = rect.top;
                }
                if (rect.bottom > i4) {
                    i4 = rect.bottom;
                }
            }
            this.f16299j.mTempRect.set(i5, i6, i3, i4);
            mo10426a(this.f16299j.mTempRect, i, i2);
            return;
        }
        this.f16299j.defaultOnMeasure(i, i2);
    }

    /* renamed from: a */
    public final void mo10566a(RecyclerView recyclerView) {
        if (recyclerView != null) {
            this.f16299j = recyclerView;
            this.f16298i = recyclerView.mChildHelper;
            this.f16310u = recyclerView.getWidth();
            this.f16311v = recyclerView.getHeight();
        } else {
            this.f16299j = null;
            this.f16298i = null;
            this.f16310u = 0;
            this.f16311v = 0;
        }
        this.f16308s = 1073741824;
        this.f16309t = 1073741824;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo10573a(View view, int i, int i2, C0648xu xuVar) {
        return view.isLayoutRequested() || !this.f16304o || !m15968b(view.getWidth(), i, xuVar.width) || !m15968b(view.getHeight(), i2, xuVar.height);
    }

    /* renamed from: a */
    public final void mo10572a(C0663yi yiVar) {
        C0663yi yiVar2 = this.f16302m;
        if (!(yiVar2 == null || yiVar == yiVar2 || !yiVar2.f16346e)) {
            yiVar2.mo10623a();
        }
        this.f16302m = yiVar;
        RecyclerView recyclerView = this.f16299j;
        recyclerView.mViewFlinger.mo10632b();
        if (yiVar.f16348g) {
            Log.w(RecyclerView.TAG, "An instance of " + yiVar.getClass().getSimpleName() + " was started more than once. Each instance of" + yiVar.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
        }
        yiVar.f16343b = recyclerView;
        yiVar.f16344c = this;
        int i = yiVar.f16342a;
        if (i != -1) {
            RecyclerView recyclerView2 = yiVar.f16343b;
            recyclerView2.mState.f16358a = i;
            yiVar.f16346e = true;
            yiVar.f16345d = true;
            yiVar.f16347f = recyclerView2.mLayout.mo10466b(yiVar.f16342a);
            yiVar.f16343b.mViewFlinger.mo10630a();
            yiVar.f16348g = true;
            return;
        }
        throw new IllegalArgumentException("Invalid target position");
    }
}
