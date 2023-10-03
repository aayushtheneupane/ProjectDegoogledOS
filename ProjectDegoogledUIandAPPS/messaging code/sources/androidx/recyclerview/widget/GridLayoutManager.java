package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.p025a.C0362d;
import androidx.core.view.p025a.C0363e;

public class GridLayoutManager extends LinearLayoutManager {

    /* renamed from: bs */
    boolean f541bs = false;

    /* renamed from: cs */
    int f542cs = -1;

    /* renamed from: ds */
    int[] f543ds;

    /* renamed from: es */
    View[] f544es;

    /* renamed from: fs */
    final SparseIntArray f545fs = new SparseIntArray();

    /* renamed from: gs */
    final SparseIntArray f546gs = new SparseIntArray();

    /* renamed from: hs */
    C0599x f547hs = new C0599x();

    /* renamed from: is */
    private boolean f548is;
    final Rect mDecorInsets = new Rect();

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo4709Q(C0558ca.getProperties(context, attributeSet, i, i2).spanCount);
    }

    /* renamed from: Pn */
    private void m598Pn() {
        View[] viewArr = this.f544es;
        if (viewArr == null || viewArr.length != this.f542cs) {
            this.f544es = new View[this.f542cs];
        }
    }

    /* renamed from: Qn */
    private void m599Qn() {
        int i;
        int i2;
        if (getOrientation() == 1) {
            i2 = getWidth() - getPaddingRight();
            i = getPaddingLeft();
        } else {
            i2 = getHeight() - getPaddingBottom();
            i = getPaddingTop();
        }
        m607sb(i2 - i);
    }

    /* renamed from: n */
    private int m605n(C0582oa oaVar) {
        int i;
        if (!(getChildCount() == 0 || oaVar.getItemCount() == 0)) {
            ensureLayoutState();
            boolean isSmoothScrollbarEnabled = isSmoothScrollbarEnabled();
            View g = mo4766g(!isSmoothScrollbarEnabled, true);
            View f = mo4760f(!isSmoothScrollbarEnabled, true);
            if (!(g == null || f == null)) {
                int f2 = this.f547hs.mo5264f(getPosition(g), this.f542cs);
                int f3 = this.f547hs.mo5264f(getPosition(f), this.f542cs);
                int min = Math.min(f2, f3);
                int max = Math.max(f2, f3);
                int f4 = this.f547hs.mo5264f(oaVar.getItemCount() - 1, this.f542cs) + 1;
                if (this.mShouldReverseLayout) {
                    i = Math.max(0, (f4 - max) - 1);
                } else {
                    i = Math.max(0, min);
                }
                if (!isSmoothScrollbarEnabled) {
                    return i;
                }
                return Math.round((((float) i) * (((float) Math.abs(this.mOrientationHelper.getDecoratedEnd(f) - this.mOrientationHelper.getDecoratedStart(g))) / ((float) ((this.f547hs.mo5264f(getPosition(f), this.f542cs) - this.f547hs.mo5264f(getPosition(g), this.f542cs)) + 1)))) + ((float) (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(g))));
            }
        }
        return 0;
    }

    /* renamed from: o */
    private int m606o(C0582oa oaVar) {
        if (!(getChildCount() == 0 || oaVar.getItemCount() == 0)) {
            ensureLayoutState();
            View g = mo4766g(!isSmoothScrollbarEnabled(), true);
            View f = mo4760f(!isSmoothScrollbarEnabled(), true);
            if (!(g == null || f == null)) {
                if (!isSmoothScrollbarEnabled()) {
                    return this.f547hs.mo5264f(oaVar.getItemCount() - 1, this.f542cs) + 1;
                }
                return (int) ((((float) (this.mOrientationHelper.getDecoratedEnd(f) - this.mOrientationHelper.getDecoratedStart(g))) / ((float) ((this.f547hs.mo5264f(getPosition(f), this.f542cs) - this.f547hs.mo5264f(getPosition(g), this.f542cs)) + 1))) * ((float) (this.f547hs.mo5264f(oaVar.getItemCount() - 1, this.f542cs) + 1)));
            }
        }
        return 0;
    }

    /* renamed from: sb */
    private void m607sb(int i) {
        int i2;
        int[] iArr = this.f543ds;
        int i3 = this.f542cs;
        if (!(iArr != null && iArr.length == i3 + 1 && iArr[iArr.length - 1] == i)) {
            iArr = new int[(i3 + 1)];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.f543ds = iArr;
    }

    /* renamed from: Q */
    public void mo4709Q(int i) {
        if (i != this.f542cs) {
            this.f541bs = true;
            if (i >= 1) {
                this.f542cs = i;
                this.f547hs.f677Yq.clear();
                requestLayout();
                return;
            }
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
    }

    /* renamed from: a */
    public int mo4711a(C0572ja jaVar, C0582oa oaVar) {
        if (this.mOrientation == 1) {
            return this.f542cs;
        }
        if (oaVar.getItemCount() < 1) {
            return 0;
        }
        return m600a(jaVar, oaVar, oaVar.getItemCount() - 1) + 1;
    }

    /* renamed from: b */
    public int mo4722b(C0572ja jaVar, C0582oa oaVar) {
        if (this.mOrientation == 0) {
            return this.f542cs;
        }
        if (oaVar.getItemCount() < 1) {
            return 0;
        }
        return m600a(jaVar, oaVar, oaVar.getItemCount() - 1) + 1;
    }

    /* renamed from: c */
    public void mo4723c(RecyclerView recyclerView, int i, int i2) {
        this.f547hs.f677Yq.clear();
        this.f547hs.f678Zq.clear();
    }

    /* renamed from: d */
    public void mo4725d(RecyclerView recyclerView, int i, int i2) {
        this.f547hs.f677Yq.clear();
        this.f547hs.f678Zq.clear();
    }

    /* renamed from: e */
    public int mo4726e(C0582oa oaVar) {
        if (this.f548is) {
            return m606o(oaVar);
        }
        return super.mo4726e(oaVar);
    }

    /* renamed from: g */
    public void mo4729g(RecyclerView recyclerView) {
        this.f547hs.f677Yq.clear();
        this.f547hs.f678Zq.clear();
    }

    public C0560da generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new C0601y(-2, -1);
        }
        return new C0601y(-1, -2);
    }

    public C0560da generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new C0601y(context, attributeSet);
    }

    /* renamed from: h */
    public int mo4733h(C0582oa oaVar) {
        if (this.f548is) {
            return m606o(oaVar);
        }
        return super.mo4733h(oaVar);
    }

    /* renamed from: i */
    public void mo4734i(C0582oa oaVar) {
        super.mo4734i(oaVar);
        this.f541bs = false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public int mo4735m(int i, int i2) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            int[] iArr = this.f543ds;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.f543ds;
        int i3 = this.f542cs;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int i3;
        int i4;
        if (this.f543ds == null) {
            setMeasuredDimension(C0558ca.chooseSize(i, getPaddingRight() + getPaddingLeft() + rect.width(), getMinimumWidth()), C0558ca.chooseSize(i2, getPaddingBottom() + getPaddingTop() + rect.height(), getMinimumHeight()));
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            i4 = C0558ca.chooseSize(i2, rect.height() + paddingBottom, getMinimumHeight());
            int[] iArr = this.f543ds;
            i3 = C0558ca.chooseSize(i, iArr[iArr.length - 1] + paddingRight, getMinimumWidth());
        } else {
            i3 = C0558ca.chooseSize(i, rect.width() + paddingRight, getMinimumWidth());
            int[] iArr2 = this.f543ds;
            i4 = C0558ca.chooseSize(i2, iArr2[iArr2.length - 1] + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(i3, i4);
    }

    public void setStackFromEnd(boolean z) {
        if (!z) {
            super.setStackFromEnd(false);
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.f541bs;
    }

    public C0560da generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0601y((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0601y(layoutParams);
    }

    /* renamed from: e */
    public void mo4727e(C0572ja jaVar, C0582oa oaVar) {
        if (oaVar.mInPreLayout) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                C0601y yVar = (C0601y) getChildAt(i).getLayoutParams();
                int viewLayoutPosition = yVar.getViewLayoutPosition();
                this.f545fs.put(viewLayoutPosition, yVar.f684Wk);
                this.f546gs.put(viewLayoutPosition, yVar.f683Vk);
            }
        }
        super.mo4727e(jaVar, oaVar);
        this.f545fs.clear();
        this.f546gs.clear();
    }

    /* renamed from: c */
    private int m604c(C0572ja jaVar, C0582oa oaVar, int i) {
        if (!oaVar.mInPreLayout) {
            this.f547hs.mo5263P(i);
            return 1;
        }
        int i2 = this.f545fs.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int convertPreLayoutPositionToPostLayout = jaVar.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
            return 1;
        }
        this.f547hs.mo5263P(convertPreLayoutPositionToPostLayout);
        return 1;
    }

    /* renamed from: a */
    public void mo4716a(C0572ja jaVar, C0582oa oaVar, View view, C0363e eVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof C0601y)) {
            super.mo5014a(view, eVar);
            return;
        }
        C0601y yVar = (C0601y) layoutParams;
        int a = m600a(jaVar, oaVar, yVar.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            eVar.mo3858h(C0362d.obtain(yVar.f683Vk, yVar.f684Wk, a, 1, false, false));
            return;
        }
        eVar.mo3858h(C0362d.obtain(a, 1, yVar.f683Vk, yVar.f684Wk, false, false));
    }

    /* renamed from: b */
    public int mo4721b(int i, C0572ja jaVar, C0582oa oaVar) {
        m599Qn();
        m598Pn();
        if (this.mOrientation == 0) {
            return 0;
        }
        return mo4751c(i, jaVar, oaVar);
    }

    /* renamed from: d */
    public int mo4724d(C0582oa oaVar) {
        if (this.f548is) {
            return m605n(oaVar);
        }
        return super.mo4724d(oaVar);
    }

    /* renamed from: g */
    public int mo4728g(C0582oa oaVar) {
        if (this.f548is) {
            return m605n(oaVar);
        }
        return super.mo4728g(oaVar);
    }

    /* renamed from: b */
    private void m603b(View view, int i, boolean z) {
        int i2;
        int i3;
        C0601y yVar = (C0601y) view.getLayoutParams();
        Rect rect = yVar.mDecorInsets;
        int i4 = rect.top + rect.bottom + yVar.topMargin + yVar.bottomMargin;
        int i5 = rect.left + rect.right + yVar.leftMargin + yVar.rightMargin;
        int m = mo4735m(yVar.f683Vk, yVar.f684Wk);
        if (this.mOrientation == 1) {
            i2 = C0558ca.getChildMeasureSpec(m, i, i5, yVar.width, false);
            i3 = C0558ca.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), i4, yVar.height, true);
        } else {
            int childMeasureSpec = C0558ca.getChildMeasureSpec(m, i, i4, yVar.height, false);
            int childMeasureSpec2 = C0558ca.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), i5, yVar.width, true);
            i3 = childMeasureSpec;
            i2 = childMeasureSpec2;
        }
        m601a(view, i2, i3, z);
    }

    /* renamed from: a */
    public void mo4715a(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.f547hs.f677Yq.clear();
        this.f547hs.f678Zq.clear();
    }

    /* renamed from: b */
    private int m602b(C0572ja jaVar, C0582oa oaVar, int i) {
        if (!oaVar.mInPreLayout) {
            return this.f547hs.mo5265g(i, this.f542cs);
        }
        int i2 = this.f546gs.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int convertPreLayoutPositionToPostLayout = jaVar.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.f547hs.mo5265g(convertPreLayoutPositionToPostLayout, this.f542cs);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    /* renamed from: a */
    public void mo4714a(RecyclerView recyclerView, int i, int i2, int i3) {
        this.f547hs.f677Yq.clear();
        this.f547hs.f678Zq.clear();
    }

    /* renamed from: a */
    public boolean mo4720a(C0560da daVar) {
        return daVar instanceof C0601y;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4717a(C0572ja jaVar, C0582oa oaVar, C0521A a, int i) {
        m599Qn();
        if (oaVar.getItemCount() > 0 && !oaVar.mInPreLayout) {
            boolean z = i == 1;
            int b = m602b(jaVar, oaVar, a.mPosition);
            if (z) {
                while (b > 0) {
                    int i2 = a.mPosition;
                    if (i2 <= 0) {
                        break;
                    }
                    a.mPosition = i2 - 1;
                    b = m602b(jaVar, oaVar, a.mPosition);
                }
            } else {
                int itemCount = oaVar.getItemCount() - 1;
                int i3 = a.mPosition;
                while (i3 < itemCount) {
                    int i4 = i3 + 1;
                    int b2 = m602b(jaVar, oaVar, i4);
                    if (b2 <= b) {
                        break;
                    }
                    i3 = i4;
                    b = b2;
                }
                a.mPosition = i3;
            }
        }
        m598Pn();
    }

    /* renamed from: a */
    public int mo4710a(int i, C0572ja jaVar, C0582oa oaVar) {
        m599Qn();
        m598Pn();
        if (this.mOrientation == 1) {
            return 0;
        }
        return mo4751c(i, jaVar, oaVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public View mo4713a(C0572ja jaVar, C0582oa oaVar, int i, int i2, int i3) {
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3 && m602b(jaVar, oaVar, position) == 0) {
                if (((C0560da) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) >= startAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4719a(C0582oa oaVar, C0525C c, C0554aa aaVar) {
        int i = this.f542cs;
        for (int i2 = 0; i2 < this.f542cs && c.mo4637b(oaVar) && i > 0; i2++) {
            int i3 = c.mCurrentPosition;
            ((C0593u) aaVar).addPosition(i3, Math.max(0, c.mScrollingOffset));
            this.f547hs.mo5263P(i3);
            i--;
            c.mCurrentPosition += c.mItemDirection;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0254  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4718a(androidx.recyclerview.widget.C0572ja r21, androidx.recyclerview.widget.C0582oa r22, androidx.recyclerview.widget.C0525C r23, androidx.recyclerview.widget.C0523B r24) {
        /*
            r20 = this;
            r6 = r20
            r0 = r21
            r1 = r22
            r2 = r23
            r7 = r24
            androidx.recyclerview.widget.I r3 = r6.mOrientationHelper
            int r3 = r3.getModeInOther()
            r4 = 1073741824(0x40000000, float:2.0)
            r8 = 1
            r5 = 0
            if (r3 == r4) goto L_0x0018
            r9 = r8
            goto L_0x0019
        L_0x0018:
            r9 = r5
        L_0x0019:
            int r10 = r20.getChildCount()
            if (r10 <= 0) goto L_0x0026
            int[] r10 = r6.f543ds
            int r11 = r6.f542cs
            r10 = r10[r11]
            goto L_0x0027
        L_0x0026:
            r10 = r5
        L_0x0027:
            if (r9 == 0) goto L_0x002c
            r20.m599Qn()
        L_0x002c:
            int r11 = r2.mItemDirection
            if (r11 != r8) goto L_0x0032
            r11 = r8
            goto L_0x0033
        L_0x0032:
            r11 = r5
        L_0x0033:
            int r12 = r6.f542cs
            if (r11 != 0) goto L_0x0044
            int r12 = r2.mCurrentPosition
            int r12 = r6.m602b((androidx.recyclerview.widget.C0572ja) r0, (androidx.recyclerview.widget.C0582oa) r1, (int) r12)
            int r13 = r2.mCurrentPosition
            int r13 = r6.m604c((androidx.recyclerview.widget.C0572ja) r0, (androidx.recyclerview.widget.C0582oa) r1, (int) r13)
            int r12 = r12 + r13
        L_0x0044:
            r13 = r12
            r12 = r5
        L_0x0046:
            int r14 = r6.f542cs
            if (r12 >= r14) goto L_0x009e
            boolean r14 = r2.mo4637b(r1)
            if (r14 == 0) goto L_0x009e
            if (r13 <= 0) goto L_0x009e
            int r14 = r2.mCurrentPosition
            int r15 = r6.m604c((androidx.recyclerview.widget.C0572ja) r0, (androidx.recyclerview.widget.C0582oa) r1, (int) r14)
            int r4 = r6.f542cs
            if (r15 > r4) goto L_0x0070
            int r13 = r13 - r15
            if (r13 >= 0) goto L_0x0060
            goto L_0x009e
        L_0x0060:
            android.view.View r4 = r2.mo4635a(r0)
            if (r4 != 0) goto L_0x0067
            goto L_0x009e
        L_0x0067:
            android.view.View[] r14 = r6.f544es
            r14[r12] = r4
            int r12 = r12 + 1
            r4 = 1073741824(0x40000000, float:2.0)
            goto L_0x0046
        L_0x0070:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Item at position "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r2 = " requires "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r2 = " spans but GridLayoutManager has only "
            r1.append(r2)
            int r2 = r6.f542cs
            r1.append(r2)
            java.lang.String r2 = " spans."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x009e:
            if (r12 != 0) goto L_0x00a3
            r7.mFinished = r8
            return
        L_0x00a3:
            if (r11 == 0) goto L_0x00ab
            r4 = r5
            r14 = r4
            r17 = r8
            r15 = r12
            goto L_0x00b1
        L_0x00ab:
            int r14 = r12 + -1
            r4 = r5
            r15 = -1
            r17 = -1
        L_0x00b1:
            if (r14 == r15) goto L_0x00d2
            android.view.View[] r13 = r6.f544es
            r13 = r13[r14]
            android.view.ViewGroup$LayoutParams r18 = r13.getLayoutParams()
            r8 = r18
            androidx.recyclerview.widget.y r8 = (androidx.recyclerview.widget.C0601y) r8
            int r13 = r6.getPosition(r13)
            int r13 = r6.m604c((androidx.recyclerview.widget.C0572ja) r0, (androidx.recyclerview.widget.C0582oa) r1, (int) r13)
            r8.f684Wk = r13
            r8.f683Vk = r4
            int r8 = r8.f684Wk
            int r4 = r4 + r8
            int r14 = r14 + r17
            r8 = 1
            goto L_0x00b1
        L_0x00d2:
            r0 = r5
            r1 = r0
            r16 = 0
        L_0x00d6:
            if (r0 >= r12) goto L_0x0121
            android.view.View[] r4 = r6.f544es
            r4 = r4[r0]
            java.util.List r8 = r2.mScrapList
            if (r8 != 0) goto L_0x00ea
            if (r11 == 0) goto L_0x00e6
            r6.addView(r4)
            goto L_0x00f3
        L_0x00e6:
            r6.addView(r4, r5)
            goto L_0x00f3
        L_0x00ea:
            if (r11 == 0) goto L_0x00f0
            r6.addDisappearingView(r4)
            goto L_0x00f3
        L_0x00f0:
            r6.addDisappearingView(r4, r5)
        L_0x00f3:
            android.graphics.Rect r8 = r6.mDecorInsets
            r6.calculateItemDecorationsForChild(r4, r8)
            r6.m603b((android.view.View) r4, (int) r3, (boolean) r5)
            androidx.recyclerview.widget.I r8 = r6.mOrientationHelper
            int r8 = r8.getDecoratedMeasurement(r4)
            if (r8 <= r1) goto L_0x0104
            r1 = r8
        L_0x0104:
            android.view.ViewGroup$LayoutParams r8 = r4.getLayoutParams()
            androidx.recyclerview.widget.y r8 = (androidx.recyclerview.widget.C0601y) r8
            r13 = 1065353216(0x3f800000, float:1.0)
            androidx.recyclerview.widget.I r14 = r6.mOrientationHelper
            int r4 = r14.getDecoratedMeasurementInOther(r4)
            float r4 = (float) r4
            float r4 = r4 * r13
            int r8 = r8.f684Wk
            float r8 = (float) r8
            float r4 = r4 / r8
            int r8 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r8 <= 0) goto L_0x011e
            r16 = r4
        L_0x011e:
            int r0 = r0 + 1
            goto L_0x00d6
        L_0x0121:
            if (r9 == 0) goto L_0x014d
            int r0 = r6.f542cs
            float r0 = (float) r0
            float r16 = r16 * r0
            int r0 = java.lang.Math.round(r16)
            int r0 = java.lang.Math.max(r0, r10)
            r6.m607sb(r0)
            r0 = r5
            r1 = r0
        L_0x0135:
            if (r0 >= r12) goto L_0x014d
            android.view.View[] r3 = r6.f544es
            r3 = r3[r0]
            r4 = 1073741824(0x40000000, float:2.0)
            r8 = 1
            r6.m603b((android.view.View) r3, (int) r4, (boolean) r8)
            androidx.recyclerview.widget.I r4 = r6.mOrientationHelper
            int r3 = r4.getDecoratedMeasurement(r3)
            if (r3 <= r1) goto L_0x014a
            r1 = r3
        L_0x014a:
            int r0 = r0 + 1
            goto L_0x0135
        L_0x014d:
            r0 = r5
        L_0x014e:
            if (r0 >= r12) goto L_0x01af
            android.view.View[] r3 = r6.f544es
            r3 = r3[r0]
            androidx.recyclerview.widget.I r4 = r6.mOrientationHelper
            int r4 = r4.getDecoratedMeasurement(r3)
            if (r4 == r1) goto L_0x01a9
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            androidx.recyclerview.widget.y r4 = (androidx.recyclerview.widget.C0601y) r4
            android.graphics.Rect r8 = r4.mDecorInsets
            int r9 = r8.top
            int r10 = r8.bottom
            int r9 = r9 + r10
            int r10 = r4.topMargin
            int r9 = r9 + r10
            int r10 = r4.bottomMargin
            int r9 = r9 + r10
            int r10 = r8.left
            int r8 = r8.right
            int r10 = r10 + r8
            int r8 = r4.leftMargin
            int r10 = r10 + r8
            int r8 = r4.rightMargin
            int r10 = r10 + r8
            int r8 = r4.f683Vk
            int r11 = r4.f684Wk
            int r8 = r6.mo4735m(r8, r11)
            int r11 = r6.mOrientation
            r13 = 1
            if (r11 != r13) goto L_0x0196
            int r4 = r4.width
            r11 = 1073741824(0x40000000, float:2.0)
            int r4 = androidx.recyclerview.widget.C0558ca.getChildMeasureSpec(r8, r11, r10, r4, r5)
            int r8 = r1 - r9
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r11)
            goto L_0x01a5
        L_0x0196:
            r11 = 1073741824(0x40000000, float:2.0)
            int r10 = r1 - r10
            int r10 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11)
            int r4 = r4.height
            int r8 = androidx.recyclerview.widget.C0558ca.getChildMeasureSpec(r8, r11, r9, r4, r5)
            r4 = r10
        L_0x01a5:
            r6.m601a((android.view.View) r3, (int) r4, (int) r8, (boolean) r13)
            goto L_0x01ac
        L_0x01a9:
            r11 = 1073741824(0x40000000, float:2.0)
            r13 = 1
        L_0x01ac:
            int r0 = r0 + 1
            goto L_0x014e
        L_0x01af:
            r13 = 1
            r7.mConsumed = r1
            int r0 = r6.mOrientation
            if (r0 != r13) goto L_0x01ca
            int r0 = r2.mLayoutDirection
            r3 = -1
            if (r0 != r3) goto L_0x01c2
            int r0 = r2.mOffset
            int r1 = r0 - r1
            r3 = r0
            r2 = r1
            goto L_0x01c7
        L_0x01c2:
            int r0 = r2.mOffset
            int r1 = r1 + r0
            r2 = r0
            r3 = r1
        L_0x01c7:
            r0 = r5
            r1 = r0
            goto L_0x01e0
        L_0x01ca:
            r3 = -1
            int r0 = r2.mLayoutDirection
            if (r0 != r3) goto L_0x01db
            int r0 = r2.mOffset
            int r1 = r0 - r1
            r2 = r5
            r3 = r2
            r19 = r1
            r1 = r0
            r0 = r19
            goto L_0x01e0
        L_0x01db:
            int r0 = r2.mOffset
            int r1 = r1 + r0
            r2 = r5
            r3 = r2
        L_0x01e0:
            r8 = r5
        L_0x01e1:
            if (r8 >= r12) goto L_0x0268
            android.view.View[] r4 = r6.f544es
            r9 = r4[r8]
            android.view.ViewGroup$LayoutParams r4 = r9.getLayoutParams()
            r10 = r4
            androidx.recyclerview.widget.y r10 = (androidx.recyclerview.widget.C0601y) r10
            int r4 = r6.mOrientation
            r5 = 1
            if (r4 != r5) goto L_0x0225
            boolean r0 = r20.isLayoutRTL()
            if (r0 == 0) goto L_0x0212
            int r0 = r20.getPaddingLeft()
            int[] r1 = r6.f543ds
            int r4 = r6.f542cs
            int r5 = r10.f683Vk
            int r4 = r4 - r5
            r1 = r1[r4]
            int r0 = r0 + r1
            androidx.recyclerview.widget.I r1 = r6.mOrientationHelper
            int r1 = r1.getDecoratedMeasurementInOther(r9)
            int r1 = r0 - r1
            r14 = r0
            r11 = r1
            goto L_0x0239
        L_0x0212:
            int r0 = r20.getPaddingLeft()
            int[] r1 = r6.f543ds
            int r4 = r10.f683Vk
            r1 = r1[r4]
            int r0 = r0 + r1
            androidx.recyclerview.widget.I r1 = r6.mOrientationHelper
            int r1 = r1.getDecoratedMeasurementInOther(r9)
            int r1 = r1 + r0
            goto L_0x0237
        L_0x0225:
            int r2 = r20.getPaddingTop()
            int[] r3 = r6.f543ds
            int r4 = r10.f683Vk
            r3 = r3[r4]
            int r2 = r2 + r3
            androidx.recyclerview.widget.I r3 = r6.mOrientationHelper
            int r3 = r3.getDecoratedMeasurementInOther(r9)
            int r3 = r3 + r2
        L_0x0237:
            r11 = r0
            r14 = r1
        L_0x0239:
            r13 = r2
            r15 = r3
            r0 = r20
            r1 = r9
            r2 = r11
            r3 = r13
            r4 = r14
            r5 = r15
            r0.layoutDecoratedWithMargins(r1, r2, r3, r4, r5)
            boolean r0 = r10.isItemRemoved()
            if (r0 != 0) goto L_0x0254
            boolean r0 = r10.isItemChanged()
            if (r0 == 0) goto L_0x0252
            goto L_0x0254
        L_0x0252:
            r0 = 1
            goto L_0x0257
        L_0x0254:
            r0 = 1
            r7.mIgnoreConsumed = r0
        L_0x0257:
            boolean r1 = r7.mFocusable
            boolean r2 = r9.hasFocusable()
            r1 = r1 | r2
            r7.mFocusable = r1
            int r8 = r8 + 1
            r0 = r11
            r2 = r13
            r1 = r14
            r3 = r15
            goto L_0x01e1
        L_0x0268:
            android.view.View[] r0 = r6.f544es
            r1 = 0
            java.util.Arrays.fill(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.mo4718a(androidx.recyclerview.widget.ja, androidx.recyclerview.widget.oa, androidx.recyclerview.widget.C, androidx.recyclerview.widget.B):void");
    }

    /* renamed from: a */
    private void m601a(View view, int i, int i2, boolean z) {
        boolean z2;
        C0560da daVar = (C0560da) view.getLayoutParams();
        if (z) {
            z2 = mo5039b(view, i, i2, daVar);
        } else {
            z2 = mo5023a(view, i, i2, daVar);
        }
        if (z2) {
            view.measure(i, i2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d7, code lost:
        if (r13 == (r2 > r8)) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f7, code lost:
        if (r13 == r11) goto L_0x00b7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0105  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View mo4712a(android.view.View r24, int r25, androidx.recyclerview.widget.C0572ja r26, androidx.recyclerview.widget.C0582oa r27) {
        /*
            r23 = this;
            r0 = r23
            r1 = r26
            r2 = r27
            android.view.View r3 = r23.findContainingItemView(r24)
            r4 = 0
            if (r3 != 0) goto L_0x000e
            return r4
        L_0x000e:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            androidx.recyclerview.widget.y r5 = (androidx.recyclerview.widget.C0601y) r5
            int r6 = r5.f683Vk
            int r5 = r5.f684Wk
            int r5 = r5 + r6
            android.view.View r7 = super.mo4712a((android.view.View) r24, (int) r25, (androidx.recyclerview.widget.C0572ja) r26, (androidx.recyclerview.widget.C0582oa) r27)
            if (r7 != 0) goto L_0x0020
            return r4
        L_0x0020:
            r7 = r25
            int r7 = r0.convertFocusDirectionToLayoutDirection(r7)
            r9 = 1
            if (r7 != r9) goto L_0x002b
            r7 = r9
            goto L_0x002c
        L_0x002b:
            r7 = 0
        L_0x002c:
            boolean r10 = r0.mShouldReverseLayout
            if (r7 == r10) goto L_0x0032
            r7 = r9
            goto L_0x0033
        L_0x0032:
            r7 = 0
        L_0x0033:
            r10 = -1
            if (r7 == 0) goto L_0x003e
            int r7 = r23.getChildCount()
            int r7 = r7 - r9
            r11 = r10
            r12 = r11
            goto L_0x0045
        L_0x003e:
            int r7 = r23.getChildCount()
            r11 = r7
            r12 = r9
            r7 = 0
        L_0x0045:
            int r13 = r0.mOrientation
            if (r13 != r9) goto L_0x0051
            boolean r13 = r23.isLayoutRTL()
            if (r13 == 0) goto L_0x0051
            r13 = r9
            goto L_0x0052
        L_0x0051:
            r13 = 0
        L_0x0052:
            int r14 = r0.m600a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0582oa) r2, (int) r7)
            r8 = r10
            r17 = r8
            r15 = 0
            r16 = 0
            r10 = r4
        L_0x005d:
            if (r7 == r11) goto L_0x0147
            int r9 = r0.m600a((androidx.recyclerview.widget.C0572ja) r1, (androidx.recyclerview.widget.C0582oa) r2, (int) r7)
            android.view.View r1 = r0.getChildAt(r7)
            if (r1 != r3) goto L_0x006b
            goto L_0x0147
        L_0x006b:
            boolean r18 = r1.hasFocusable()
            if (r18 == 0) goto L_0x0085
            if (r9 == r14) goto L_0x0085
            if (r4 == 0) goto L_0x0077
            goto L_0x0147
        L_0x0077:
            r18 = r3
            r19 = r8
            r21 = r10
            r20 = r11
            r8 = r16
            r10 = r17
            goto L_0x0133
        L_0x0085:
            android.view.ViewGroup$LayoutParams r9 = r1.getLayoutParams()
            androidx.recyclerview.widget.y r9 = (androidx.recyclerview.widget.C0601y) r9
            int r2 = r9.f683Vk
            r18 = r3
            int r3 = r9.f684Wk
            int r3 = r3 + r2
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x009d
            if (r2 != r6) goto L_0x009d
            if (r3 != r5) goto L_0x009d
            return r1
        L_0x009d:
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x00a5
            if (r4 == 0) goto L_0x00ad
        L_0x00a5:
            boolean r19 = r1.hasFocusable()
            if (r19 != 0) goto L_0x00b9
            if (r10 != 0) goto L_0x00b9
        L_0x00ad:
            r19 = r8
            r21 = r10
        L_0x00b1:
            r20 = r11
            r8 = r16
            r10 = r17
        L_0x00b7:
            r11 = 1
            goto L_0x0103
        L_0x00b9:
            int r19 = java.lang.Math.max(r2, r6)
            int r20 = java.lang.Math.min(r3, r5)
            r21 = r10
            int r10 = r20 - r19
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x00da
            if (r10 <= r15) goto L_0x00d0
        L_0x00cd:
            r19 = r8
            goto L_0x00b1
        L_0x00d0:
            if (r10 != r15) goto L_0x00fa
            if (r2 <= r8) goto L_0x00d6
            r10 = 1
            goto L_0x00d7
        L_0x00d6:
            r10 = 0
        L_0x00d7:
            if (r13 != r10) goto L_0x00fa
            goto L_0x00cd
        L_0x00da:
            if (r4 != 0) goto L_0x00fa
            r19 = r8
            r20 = r11
            r8 = 0
            r11 = 1
            boolean r22 = r0.mo5024a((android.view.View) r1, (boolean) r8, (boolean) r11)
            r8 = r16
            if (r22 == 0) goto L_0x0100
            if (r10 <= r8) goto L_0x00ef
            r10 = r17
            goto L_0x0103
        L_0x00ef:
            if (r10 != r8) goto L_0x0100
            r10 = r17
            if (r2 <= r10) goto L_0x00f6
            goto L_0x00f7
        L_0x00f6:
            r11 = 0
        L_0x00f7:
            if (r13 != r11) goto L_0x0102
            goto L_0x00b7
        L_0x00fa:
            r19 = r8
            r20 = r11
            r8 = r16
        L_0x0100:
            r10 = r17
        L_0x0102:
            r11 = 0
        L_0x0103:
            if (r11 == 0) goto L_0x0133
            boolean r11 = r1.hasFocusable()
            if (r11 == 0) goto L_0x0120
            int r4 = r9.f683Vk
            int r3 = java.lang.Math.min(r3, r5)
            int r2 = java.lang.Math.max(r2, r6)
            int r3 = r3 - r2
            r15 = r3
            r16 = r8
            r17 = r10
            r10 = r21
            r8 = r4
            r4 = r1
            goto L_0x013b
        L_0x0120:
            int r8 = r9.f683Vk
            int r3 = java.lang.Math.min(r3, r5)
            int r2 = java.lang.Math.max(r2, r6)
            int r3 = r3 - r2
            r10 = r1
            r16 = r3
            r17 = r8
            r8 = r19
            goto L_0x013b
        L_0x0133:
            r16 = r8
            r17 = r10
            r8 = r19
            r10 = r21
        L_0x013b:
            int r7 = r7 + r12
            r1 = r26
            r2 = r27
            r3 = r18
            r11 = r20
            r9 = 1
            goto L_0x005d
        L_0x0147:
            r21 = r10
            if (r4 == 0) goto L_0x014c
            goto L_0x014e
        L_0x014c:
            r4 = r21
        L_0x014e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.mo4712a(android.view.View, int, androidx.recyclerview.widget.ja, androidx.recyclerview.widget.oa):android.view.View");
    }

    /* renamed from: a */
    private int m600a(C0572ja jaVar, C0582oa oaVar, int i) {
        if (!oaVar.mInPreLayout) {
            return this.f547hs.mo5264f(i, this.f542cs);
        }
        int convertPreLayoutPositionToPostLayout = jaVar.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.f547hs.mo5264f(convertPreLayoutPositionToPostLayout, this.f542cs);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }
}
