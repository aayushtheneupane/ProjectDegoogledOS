package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: PG */
public class ConstraintLayout extends ViewGroup {

    /* renamed from: a */
    private SparseArray f815a = new SparseArray();

    /* renamed from: b */
    private final ArrayList f816b = new ArrayList(100);

    /* renamed from: c */
    private C0117ee f817c = new C0117ee();

    /* renamed from: d */
    private int f818d = 0;

    /* renamed from: e */
    private int f819e = 0;

    /* renamed from: f */
    private int f820f = Integer.MAX_VALUE;

    /* renamed from: g */
    private int f821g = Integer.MAX_VALUE;

    /* renamed from: h */
    private boolean f822h = true;

    /* renamed from: i */
    private int f823i = 2;

    /* renamed from: j */
    private C0104ds f824j = null;

    public ConstraintLayout(Context context) {
        super(context);
        m805a((AttributeSet) null);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m805a(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m805a(attributeSet);
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        int i2 = Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0102dq;
    }

    /* renamed from: a */
    protected static C0102dq m802a() {
        return new C0102dq();
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m802a();
    }

    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0102dq(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0102dq(layoutParams);
    }

    /* renamed from: a */
    private final C0116ed m803a(int i) {
        if (i == 0) {
            return this.f817c;
        }
        View view = (View) this.f815a.get(i);
        if (view == this) {
            return this.f817c;
        }
        if (view != null) {
            return ((C0102dq) view.getLayoutParams()).f7068V;
        }
        return null;
    }

    /* renamed from: a */
    private final C0116ed m804a(View view) {
        if (view == this) {
            return this.f817c;
        }
        if (view != null) {
            return ((C0102dq) view.getLayoutParams()).f7068V;
        }
        return null;
    }

    /* renamed from: a */
    private final void m805a(AttributeSet attributeSet) {
        this.f817c.f7960G = this;
        this.f815a.put(getId(), this);
        this.f824j = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0105dt.f7309a);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 3) {
                    this.f818d = obtainStyledAttributes.getDimensionPixelOffset(3, this.f818d);
                } else if (index == 4) {
                    this.f819e = obtainStyledAttributes.getDimensionPixelOffset(4, this.f819e);
                } else if (index == 1) {
                    this.f820f = obtainStyledAttributes.getDimensionPixelOffset(1, this.f820f);
                } else if (index == 2) {
                    this.f821g = obtainStyledAttributes.getDimensionPixelOffset(2, this.f821g);
                } else if (index == 48) {
                    this.f823i = obtainStyledAttributes.getInt(48, this.f823i);
                } else if (index == 5) {
                    int resourceId = obtainStyledAttributes.getResourceId(5, 0);
                    C0104ds dsVar = new C0104ds();
                    this.f824j = dsVar;
                    dsVar.mo4383a(getContext(), resourceId);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.f817c.f8078ac = this.f823i;
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            C0102dq dqVar = (C0102dq) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8 || dqVar.f7060N || isInEditMode) {
                C0116ed edVar = dqVar.f7068V;
                int g = edVar.mo4708g();
                int h = edVar.mo4710h();
                childAt.layout(g, h, edVar.mo4699c() + g, edVar.mo4706f() + h);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x04b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r33, int r34) {
        /*
            r32 = this;
            r1 = r32
            r2 = r33
            r3 = r34
            int r4 = r32.getPaddingLeft()
            int r5 = r32.getPaddingTop()
            ee r0 = r1.f817c
            r0.f8006t = r4
            r0.f8007u = r5
            int r0 = android.view.View.MeasureSpec.getMode(r33)
            int r6 = android.view.View.MeasureSpec.getSize(r33)
            int r7 = android.view.View.MeasureSpec.getMode(r34)
            int r8 = android.view.View.MeasureSpec.getSize(r34)
            int r9 = r32.getPaddingTop()
            int r10 = r32.getPaddingBottom()
            int r9 = r9 + r10
            int r10 = r32.getPaddingLeft()
            int r11 = r32.getPaddingRight()
            int r10 = r10 + r11
            r32.getLayoutParams()
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            r12 = 1073741824(0x40000000, float:2.0)
            r14 = 0
            if (r0 == r11) goto L_0x0057
            if (r0 == 0) goto L_0x0052
            if (r0 == r12) goto L_0x0047
            r0 = 1
            r6 = 0
            goto L_0x0058
        L_0x0047:
            int r0 = r1.f820f
            int r0 = java.lang.Math.min(r0, r6)
            int r6 = r0 - r10
            r0 = 1
            goto L_0x0058
        L_0x0052:
            r0 = 2
            r6 = 0
            goto L_0x0058
        L_0x0057:
            r0 = 2
        L_0x0058:
            if (r7 == r11) goto L_0x0071
            if (r7 == 0) goto L_0x006c
            if (r7 == r12) goto L_0x0061
            r7 = 1
            r8 = 0
            goto L_0x0072
        L_0x0061:
            int r7 = r1.f821g
            int r7 = java.lang.Math.min(r7, r8)
            int r8 = r7 - r9
            r7 = 1
            goto L_0x0072
        L_0x006c:
            r7 = 2
            r8 = 0
            goto L_0x0072
        L_0x0071:
            r7 = 2
        L_0x0072:
            ee r9 = r1.f817c
            r9.mo4700c(r14)
            ee r9 = r1.f817c
            r9.mo4703d(r14)
            ee r9 = r1.f817c
            r9.mo4707f(r0)
            ee r0 = r1.f817c
            r0.mo4691a((int) r6)
            ee r0 = r1.f817c
            r0.mo4709g(r7)
            ee r0 = r1.f817c
            r0.mo4696b(r8)
            ee r0 = r1.f817c
            int r6 = r1.f818d
            int r7 = r32.getPaddingLeft()
            int r6 = r6 - r7
            int r7 = r32.getPaddingRight()
            int r6 = r6 - r7
            r0.mo4700c(r6)
            ee r0 = r1.f817c
            int r6 = r1.f819e
            int r7 = r32.getPaddingTop()
            int r6 = r6 - r7
            int r7 = r32.getPaddingBottom()
            int r6 = r6 - r7
            r0.mo4703d(r6)
            boolean r0 = r1.f822h
            r8 = -1
            if (r0 == 0) goto L_0x04ff
            r1.f822h = r14
            int r0 = r32.getChildCount()
            r9 = 0
        L_0x00be:
            if (r9 >= r0) goto L_0x04fd
            android.view.View r10 = r1.getChildAt(r9)
            boolean r10 = r10.isLayoutRequested()
            if (r10 == 0) goto L_0x04f4
            java.util.ArrayList r0 = r1.f816b
            r0.clear()
            ds r0 = r1.f824j
            if (r0 == 0) goto L_0x0192
            int r9 = r32.getChildCount()
            java.util.HashSet r10 = new java.util.HashSet
            java.util.HashMap r11 = r0.f7264a
            java.util.Set r11 = r11.keySet()
            r10.<init>(r11)
            r11 = 0
        L_0x00e3:
            if (r11 >= r9) goto L_0x015b
            android.view.View r12 = r1.getChildAt(r11)
            int r16 = r12.getId()
            java.util.HashMap r14 = r0.f7264a
            java.lang.Integer r13 = java.lang.Integer.valueOf(r16)
            boolean r14 = r14.containsKey(r13)
            if (r14 == 0) goto L_0x0155
            r10.remove(r13)
            java.util.HashMap r14 = r0.f7264a
            java.lang.Object r13 = r14.get(r13)
            dr r13 = (p000.C0103dr) r13
            android.view.ViewGroup$LayoutParams r14 = r12.getLayoutParams()
            dq r14 = (p000.C0102dq) r14
            r13.mo4360a(r14)
            r12.setLayoutParams(r14)
            int r14 = r13.f7146G
            r12.setVisibility(r14)
            int r14 = android.os.Build.VERSION.SDK_INT
            float r14 = r13.f7157R
            r12.setAlpha(r14)
            float r14 = r13.f7160U
            r12.setRotationX(r14)
            float r14 = r13.f7161V
            r12.setRotationY(r14)
            float r14 = r13.f7162W
            r12.setScaleX(r14)
            float r14 = r13.f7163X
            r12.setScaleY(r14)
            float r14 = r13.f7164Y
            r12.setPivotX(r14)
            float r14 = r13.f7165Z
            r12.setPivotY(r14)
            float r14 = r13.f7167aa
            r12.setTranslationX(r14)
            float r14 = r13.f7168ab
            r12.setTranslationY(r14)
            int r14 = android.os.Build.VERSION.SDK_INT
            float r14 = r13.f7169ac
            r12.setTranslationZ(r14)
            boolean r14 = r13.f7158S
            if (r14 != 0) goto L_0x0150
            goto L_0x0155
        L_0x0150:
            float r13 = r13.f7159T
            r12.setElevation(r13)
        L_0x0155:
            int r11 = r11 + 1
            r12 = 1073741824(0x40000000, float:2.0)
            r14 = 0
            goto L_0x00e3
        L_0x015b:
            java.util.Iterator r9 = r10.iterator()
        L_0x015f:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0192
            java.lang.Object r10 = r9.next()
            java.lang.Integer r10 = (java.lang.Integer) r10
            java.util.HashMap r11 = r0.f7264a
            java.lang.Object r11 = r11.get(r10)
            dr r11 = (p000.C0103dr) r11
            boolean r12 = r11.f7166a
            if (r12 == 0) goto L_0x015f
            android.support.constraint.Guideline r12 = new android.support.constraint.Guideline
            android.content.Context r13 = r32.getContext()
            r12.<init>(r13)
            int r10 = r10.intValue()
            r12.setId(r10)
            dq r10 = m802a()
            r11.mo4360a(r10)
            r1.addView(r12, r10)
            goto L_0x015f
        L_0x0192:
            int r9 = r32.getChildCount()
            ee r0 = r1.f817c
            java.util.ArrayList r0 = r0.f8330af
            r0.clear()
            r10 = 0
        L_0x019e:
            if (r10 >= r9) goto L_0x04f2
            android.view.View r0 = r1.getChildAt(r10)
            ed r11 = r1.m804a((android.view.View) r0)
            if (r11 == 0) goto L_0x04e8
            android.view.ViewGroup$LayoutParams r12 = r0.getLayoutParams()
            dq r12 = (p000.C0102dq) r12
            r11.mo4690a()
            int r13 = r0.getVisibility()
            r11.f7961H = r13
            r11.f7960G = r0
            ee r0 = r1.f817c
            java.util.ArrayList r13 = r0.f8330af
            r13.add(r11)
            ed r13 = r11.f8001o
            if (r13 == 0) goto L_0x01cb
            ei r13 = (p000.C0121ei) r13
            r13.mo4818a((p000.C0116ed) r11)
        L_0x01cb:
            r11.f8001o = r0
            boolean r0 = r12.f7058L
            if (r0 != 0) goto L_0x01d2
        L_0x01d1:
            goto L_0x01d7
        L_0x01d2:
            boolean r0 = r12.f7057K
            if (r0 != 0) goto L_0x01dc
            goto L_0x01d1
        L_0x01d7:
            java.util.ArrayList r0 = r1.f816b
            r0.add(r11)
        L_0x01dc:
            boolean r0 = r12.f7060N
            if (r0 == 0) goto L_0x0216
            ef r11 = (p000.C0118ef) r11
            int r0 = r12.f7072a
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r0 == r8) goto L_0x01f0
            if (r0 < 0) goto L_0x01f0
            r11.f8133ac = r13
            r11.f8134ad = r0
            r11.f8135ae = r8
        L_0x01f0:
            int r0 = r12.f7073b
            if (r0 == r8) goto L_0x01fc
            if (r0 < 0) goto L_0x01fc
            r11.f8133ac = r13
            r11.f8134ad = r8
            r11.f8135ae = r0
        L_0x01fc:
            float r0 = r12.f7074c
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 != 0) goto L_0x0207
        L_0x0202:
            r24 = r9
            r13 = 0
            goto L_0x04eb
        L_0x0207:
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 <= 0) goto L_0x0202
            r11.f8133ac = r0
            r11.f8134ad = r8
            r11.f8135ae = r8
            r24 = r9
            r13 = 0
            goto L_0x04eb
        L_0x0216:
            int r0 = r12.f7061O
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7062P
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7063Q
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7064R
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7079h
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7080i
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7081j
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7082k
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7083l
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7054H
            if (r0 != r8) goto L_0x0250
            int r0 = r12.f7055I
            if (r0 != r8) goto L_0x0250
            int r0 = r12.width
            if (r0 == r8) goto L_0x0250
            int r0 = r12.height
            if (r0 != r8) goto L_0x024b
            goto L_0x0250
        L_0x024b:
            r24 = r9
            r13 = 0
            goto L_0x04eb
        L_0x0250:
            int r0 = r12.f7061O
            int r13 = r12.f7062P
            int r14 = r12.f7063Q
            int r7 = r12.f7064R
            int r6 = r12.f7065S
            int r15 = r12.f7066T
            float r8 = r12.f7067U
            int r18 = android.os.Build.VERSION.SDK_INT
            r24 = r9
            r9 = -1
            if (r0 == r9) goto L_0x027b
            ed r20 = r1.m803a((int) r0)
            if (r20 == 0) goto L_0x0293
            r19 = 2
            r21 = 2
            int r0 = r12.leftMargin
            r18 = r11
            r22 = r0
            r23 = r6
            r18.mo4693a(r19, r20, r21, r22, r23)
            goto L_0x0293
        L_0x027b:
            r9 = -1
            if (r13 == r9) goto L_0x0293
            ed r20 = r1.m803a((int) r13)
            if (r20 == 0) goto L_0x0293
            r19 = 2
            r21 = 4
            int r0 = r12.leftMargin
            r18 = r11
            r22 = r0
            r23 = r6
            r18.mo4693a(r19, r20, r21, r22, r23)
        L_0x0293:
            r6 = -1
            if (r14 == r6) goto L_0x02ac
            ed r20 = r1.m803a((int) r14)
            if (r20 == 0) goto L_0x02c4
            r19 = 4
            r21 = 2
            int r0 = r12.rightMargin
            r18 = r11
            r22 = r0
            r23 = r15
            r18.mo4693a(r19, r20, r21, r22, r23)
            goto L_0x02c4
        L_0x02ac:
            r6 = -1
            if (r7 == r6) goto L_0x02c4
            ed r20 = r1.m803a((int) r7)
            if (r20 == 0) goto L_0x02c4
            r19 = 4
            r21 = 4
            int r0 = r12.rightMargin
            r18 = r11
            r22 = r0
            r23 = r15
            r18.mo4693a(r19, r20, r21, r22, r23)
        L_0x02c4:
            int r0 = r12.f7079h
            r6 = -1
            if (r0 == r6) goto L_0x02e1
            ed r20 = r1.m803a((int) r0)
            if (r20 == 0) goto L_0x02fd
            r19 = 3
            r21 = 3
            int r0 = r12.topMargin
            int r6 = r12.f7088q
            r18 = r11
            r22 = r0
            r23 = r6
            r18.mo4693a(r19, r20, r21, r22, r23)
            goto L_0x02fd
        L_0x02e1:
            int r0 = r12.f7080i
            r6 = -1
            if (r0 == r6) goto L_0x02fd
            ed r20 = r1.m803a((int) r0)
            if (r20 == 0) goto L_0x02fd
            r19 = 3
            r21 = 5
            int r0 = r12.topMargin
            int r6 = r12.f7088q
            r18 = r11
            r22 = r0
            r23 = r6
            r18.mo4693a(r19, r20, r21, r22, r23)
        L_0x02fd:
            int r0 = r12.f7081j
            r6 = -1
            if (r0 == r6) goto L_0x031a
            ed r20 = r1.m803a((int) r0)
            if (r20 == 0) goto L_0x0336
            r19 = 5
            r21 = 3
            int r0 = r12.bottomMargin
            int r6 = r12.f7089r
            r18 = r11
            r22 = r0
            r23 = r6
            r18.mo4693a(r19, r20, r21, r22, r23)
            goto L_0x0336
        L_0x031a:
            int r0 = r12.f7082k
            r6 = -1
            if (r0 == r6) goto L_0x0336
            ed r20 = r1.m803a((int) r0)
            if (r20 == 0) goto L_0x0336
            r19 = 5
            r21 = 5
            int r0 = r12.bottomMargin
            int r6 = r12.f7089r
            r18 = r11
            r22 = r0
            r23 = r6
            r18.mo4693a(r19, r20, r21, r22, r23)
        L_0x0336:
            int r0 = r12.f7083l
            r6 = 3
            r7 = -1
            if (r0 == r7) goto L_0x0386
            android.util.SparseArray r7 = r1.f815a
            java.lang.Object r0 = r7.get(r0)
            android.view.View r0 = (android.view.View) r0
            int r7 = r12.f7083l
            ed r7 = r1.m803a((int) r7)
            if (r7 == 0) goto L_0x0386
            if (r0 == 0) goto L_0x0386
            android.view.ViewGroup$LayoutParams r9 = r0.getLayoutParams()
            boolean r9 = r9 instanceof p000.C0102dq
            if (r9 == 0) goto L_0x0386
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            dq r0 = (p000.C0102dq) r0
            r9 = 1
            r12.f7059M = r9
            r0.f7059M = r9
            r0 = 6
            ec r25 = r11.mo4705e(r0)
            ec r26 = r7.mo4705e(r0)
            r27 = 0
            r28 = -1
            r29 = 2
            r30 = 0
            r31 = 1
            r25.mo4668a(r26, r27, r28, r29, r30, r31)
            ec r0 = r11.mo4705e(r6)
            r0.mo4669b()
            r7 = 5
            ec r0 = r11.mo4705e(r7)
            r0.mo4669b()
        L_0x0386:
            r0 = 1056964608(0x3f000000, float:0.5)
            r7 = 0
            int r9 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x0395
            int r9 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r9 != 0) goto L_0x0393
            goto L_0x0395
        L_0x0393:
            r11.f7958E = r8
        L_0x0395:
            float r8 = r12.f7093v
            int r9 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x03a1
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x03a1
            r11.f7959F = r8
        L_0x03a1:
            boolean r0 = r32.isInEditMode()
            if (r0 == 0) goto L_0x03b6
            int r0 = r12.f7054H
            r8 = -1
            if (r0 != r8) goto L_0x03b0
            int r9 = r12.f7055I
            if (r9 == r8) goto L_0x03b6
        L_0x03b0:
            int r8 = r12.f7055I
            r11.f8006t = r0
            r11.f8007u = r8
        L_0x03b6:
            boolean r0 = r12.f7057K
            if (r0 != 0) goto L_0x03de
            int r0 = r12.width
            r8 = -1
            if (r0 != r8) goto L_0x03d5
            r8 = 4
            r11.mo4707f(r8)
            r9 = 2
            ec r0 = r11.mo4705e(r9)
            int r9 = r12.leftMargin
            r0.f7904c = r9
            ec r0 = r11.mo4705e(r8)
            int r8 = r12.rightMargin
            r0.f7904c = r8
            goto L_0x03e8
        L_0x03d5:
            r11.mo4707f(r6)
            r8 = 0
            r11.mo4691a((int) r8)
            goto L_0x03e8
        L_0x03de:
            r8 = 1
            r11.mo4707f(r8)
            int r0 = r12.width
            r11.mo4691a((int) r0)
        L_0x03e8:
            boolean r0 = r12.f7058L
            if (r0 != 0) goto L_0x0410
            int r0 = r12.height
            r8 = -1
            if (r0 != r8) goto L_0x0407
            r8 = 4
            r11.mo4709g(r8)
            ec r0 = r11.mo4705e(r6)
            int r6 = r12.topMargin
            r0.f7904c = r6
            r6 = 5
            ec r0 = r11.mo4705e(r6)
            int r6 = r12.bottomMargin
            r0.f7904c = r6
            goto L_0x041a
        L_0x0407:
            r11.mo4709g(r6)
            r6 = 0
            r11.mo4696b(r6)
            goto L_0x041a
        L_0x0410:
            r6 = 1
            r11.mo4709g(r6)
            int r0 = r12.height
            r11.mo4696b(r0)
        L_0x041a:
            java.lang.String r0 = r12.f7094w
            if (r0 == 0) goto L_0x04be
            int r6 = r0.length()
            if (r6 != 0) goto L_0x0429
            r11.f8004r = r7
            r13 = 0
            goto L_0x04bf
        L_0x0429:
            int r6 = r0.length()
            r8 = 44
            int r8 = r0.indexOf(r8)
            if (r8 > 0) goto L_0x0439
            r8 = 0
            r9 = -1
            r13 = 0
            goto L_0x045e
        L_0x0439:
            int r9 = r6 + -1
            if (r8 >= r9) goto L_0x045b
            r13 = 0
            java.lang.String r9 = r0.substring(r13, r8)
            java.lang.String r14 = "W"
            boolean r14 = r9.equalsIgnoreCase(r14)
            if (r14 != 0) goto L_0x0457
            java.lang.String r14 = "H"
            boolean r9 = r9.equalsIgnoreCase(r14)
            if (r9 == 0) goto L_0x0455
            r9 = 1
            goto L_0x0458
        L_0x0455:
            r9 = -1
            goto L_0x0458
        L_0x0457:
            r9 = 0
        L_0x0458:
            int r8 = r8 + 1
            goto L_0x045e
        L_0x045b:
            r13 = 0
            r8 = 0
            r9 = -1
        L_0x045e:
            r14 = 58
            int r14 = r0.indexOf(r14)
            if (r14 < 0) goto L_0x04a1
            int r6 = r6 + -1
            if (r14 < r6) goto L_0x046b
            goto L_0x04a1
        L_0x046b:
            java.lang.String r6 = r0.substring(r8, r14)
            int r14 = r14 + 1
            java.lang.String r0 = r0.substring(r14)
            int r8 = r6.length()
            if (r8 <= 0) goto L_0x04b4
            int r8 = r0.length()
            if (r8 <= 0) goto L_0x04b4
            float r6 = java.lang.Float.parseFloat(r6)     // Catch:{ NumberFormatException -> 0x04b0 }
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x04b0 }
            int r8 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r8 <= 0) goto L_0x04b4
            int r8 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r8 <= 0) goto L_0x04a0
            r8 = 1
            if (r9 == r8) goto L_0x049a
            float r6 = r6 / r0
            float r0 = java.lang.Math.abs(r6)     // Catch:{ NumberFormatException -> 0x04b0 }
            goto L_0x04b5
        L_0x049a:
            float r0 = r0 / r6
            float r0 = java.lang.Math.abs(r0)     // Catch:{ NumberFormatException -> 0x04b0 }
            goto L_0x04b5
        L_0x04a0:
            goto L_0x04b4
        L_0x04a1:
            java.lang.String r0 = r0.substring(r8)
            int r6 = r0.length()
            if (r6 <= 0) goto L_0x04b3
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x04b0 }
            goto L_0x04b5
        L_0x04b0:
            r0 = move-exception
            r0 = 0
            goto L_0x04b5
        L_0x04b3:
        L_0x04b4:
            r0 = 0
        L_0x04b5:
            int r6 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x04bf
            r11.f8004r = r0
            r11.f8005s = r9
            goto L_0x04bf
        L_0x04be:
            r13 = 0
        L_0x04bf:
            float r0 = r12.f7095x
            r11.f7976W = r0
            float r0 = r12.f7096y
            r11.f7977X = r0
            int r0 = r12.f7097z
            r11.f7972S = r0
            int r0 = r12.f7047A
            r11.f7973T = r0
            int r0 = r12.f7048B
            int r6 = r12.f7050D
            int r7 = r12.f7052F
            r11.f7989c = r0
            r11.f7991e = r6
            r11.f7992f = r7
            int r0 = r12.f7049C
            int r6 = r12.f7051E
            int r7 = r12.f7053G
            r11.f7990d = r0
            r11.f7993g = r6
            r11.f7994h = r7
            goto L_0x04eb
        L_0x04e8:
            r24 = r9
            r13 = 0
        L_0x04eb:
            int r10 = r10 + 1
            r9 = r24
            r8 = -1
            goto L_0x019e
        L_0x04f2:
            r13 = 0
            goto L_0x0500
        L_0x04f4:
            r13 = 0
            int r9 = r9 + 1
            r8 = -1
            r12 = 1073741824(0x40000000, float:2.0)
            r14 = 0
            goto L_0x00be
        L_0x04fd:
            r13 = 0
            goto L_0x0500
        L_0x04ff:
            r13 = 0
        L_0x0500:
            int r0 = r32.getPaddingTop()
            int r6 = r32.getPaddingBottom()
            int r0 = r0 + r6
            int r6 = r32.getPaddingLeft()
            int r7 = r32.getPaddingRight()
            int r6 = r6 + r7
            int r7 = r32.getChildCount()
            r8 = 0
        L_0x0517:
            r9 = 8
            if (r8 >= r7) goto L_0x05b0
            android.view.View r11 = r1.getChildAt(r8)
            int r12 = r11.getVisibility()
            if (r12 == r9) goto L_0x05ab
            android.view.ViewGroup$LayoutParams r9 = r11.getLayoutParams()
            dq r9 = (p000.C0102dq) r9
            ed r12 = r9.f7068V
            boolean r14 = r9.f7060N
            if (r14 != 0) goto L_0x05ab
            int r14 = r9.width
            int r15 = r9.height
            boolean r13 = r9.f7057K
            if (r13 != 0) goto L_0x055a
            boolean r13 = r9.f7058L
            if (r13 != 0) goto L_0x055a
            int r13 = r9.f7048B
            r10 = 1
            if (r13 == r10) goto L_0x055a
            int r13 = r9.width
            r10 = -1
            if (r13 == r10) goto L_0x055a
            boolean r13 = r9.f7058L
            if (r13 == 0) goto L_0x054f
        L_0x054b:
            r13 = r14
            r10 = 0
            r14 = 0
            goto L_0x058f
        L_0x054f:
            int r13 = r9.f7049C
            r10 = 1
            if (r13 == r10) goto L_0x055a
            int r13 = r9.height
            r10 = -1
            if (r13 == r10) goto L_0x055a
            goto L_0x054b
        L_0x055a:
            if (r14 == 0) goto L_0x0566
            r10 = -1
            if (r14 == r10) goto L_0x0566
            int r10 = getChildMeasureSpec(r2, r6, r14)
            r13 = r10
            r10 = 0
            goto L_0x056d
        L_0x0566:
            r10 = -2
            int r13 = getChildMeasureSpec(r2, r6, r10)
            r10 = 1
        L_0x056d:
            if (r15 == 0) goto L_0x057c
            r14 = -1
            if (r15 != r14) goto L_0x0574
            goto L_0x057c
        L_0x0574:
            int r14 = getChildMeasureSpec(r3, r0, r15)
            r15 = r14
            r14 = 0
            goto L_0x0584
        L_0x057c:
            r14 = -2
            int r14 = getChildMeasureSpec(r3, r0, r14)
            r15 = r14
            r14 = 1
        L_0x0584:
            r11.measure(r13, r15)
            int r13 = r11.getMeasuredWidth()
            int r15 = r11.getMeasuredHeight()
        L_0x058f:
            r12.mo4691a((int) r13)
            r12.mo4696b(r15)
            if (r10 == 0) goto L_0x0599
            r12.f7956C = r13
        L_0x0599:
            if (r14 == 0) goto L_0x059d
            r12.f7957D = r15
        L_0x059d:
            boolean r9 = r9.f7059M
            if (r9 != 0) goto L_0x05a2
            goto L_0x05ab
        L_0x05a2:
            int r9 = r11.getBaseline()
            r10 = -1
            if (r9 == r10) goto L_0x05ab
            r12.f8012z = r9
        L_0x05ab:
            int r8 = r8 + 1
            r13 = 0
            goto L_0x0517
        L_0x05b0:
            int r0 = r32.getChildCount()
            if (r0 > 0) goto L_0x05b7
            goto L_0x05ba
        L_0x05b7:
            r32.mo2453b()
        L_0x05ba:
            java.util.ArrayList r0 = r1.f816b
            int r0 = r0.size()
            int r6 = r32.getPaddingBottom()
            int r5 = r5 + r6
            int r6 = r32.getPaddingRight()
            int r4 = r4 + r6
            if (r0 <= 0) goto L_0x06e2
            ee r6 = r1.f817c
            int r7 = r6.f7981aa
            int r6 = r6.f7982ab
            r8 = 0
            r14 = 0
            r17 = 0
        L_0x05d6:
            if (r14 >= r0) goto L_0x06da
            java.util.ArrayList r10 = r1.f816b
            java.lang.Object r10 = r10.get(r14)
            ed r10 = (p000.C0116ed) r10
            boolean r11 = r10 instanceof p000.C0118ef
            if (r11 != 0) goto L_0x06ce
            java.lang.Object r11 = r10.f7960G
            if (r11 == 0) goto L_0x06ce
            android.view.View r11 = (android.view.View) r11
            int r12 = r11.getVisibility()
            if (r12 == r9) goto L_0x06ce
            android.view.ViewGroup$LayoutParams r12 = r11.getLayoutParams()
            dq r12 = (p000.C0102dq) r12
            int r13 = r12.width
            r15 = -2
            if (r13 != r15) goto L_0x0605
            int r13 = r12.width
            int r13 = getChildMeasureSpec(r2, r4, r13)
            r9 = 1073741824(0x40000000, float:2.0)
            goto L_0x060f
        L_0x0605:
            int r13 = r10.mo4699c()
            r9 = 1073741824(0x40000000, float:2.0)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r9)
        L_0x060f:
            int r9 = r12.height
            if (r9 != r15) goto L_0x061c
            int r9 = r12.height
            int r9 = getChildMeasureSpec(r3, r5, r9)
            r15 = 1073741824(0x40000000, float:2.0)
            goto L_0x0626
        L_0x061c:
            int r9 = r10.mo4706f()
            r15 = 1073741824(0x40000000, float:2.0)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r15)
        L_0x0626:
            r11.measure(r13, r9)
            int r9 = r11.getMeasuredWidth()
            int r13 = r11.getMeasuredHeight()
            int r15 = r10.mo4699c()
            if (r9 == r15) goto L_0x0671
            r10.mo4691a((int) r9)
            r9 = 2
            if (r7 != r9) goto L_0x066c
            int r9 = r10.mo4711i()
            ee r15 = r1.f817c
            int r15 = r15.mo4699c()
            if (r9 <= r15) goto L_0x0669
            int r9 = r10.mo4711i()
            r15 = 4
            ec r16 = r10.mo4705e(r15)
            int r16 = r16.mo4667a()
            ee r15 = r1.f817c
            r21 = r0
            int r0 = r1.f818d
            int r9 = r9 + r16
            int r0 = java.lang.Math.max(r0, r9)
            r15.mo4691a((int) r0)
            goto L_0x066f
        L_0x0669:
            r21 = r0
            goto L_0x066e
        L_0x066c:
            r21 = r0
        L_0x066e:
        L_0x066f:
            r9 = 1
            goto L_0x0675
        L_0x0671:
            r21 = r0
            r9 = r17
        L_0x0675:
            int r0 = r10.mo4706f()
            if (r13 == r0) goto L_0x06aa
            r10.mo4696b(r13)
            r13 = 2
            if (r6 != r13) goto L_0x06a8
            int r0 = r10.mo4712j()
            ee r9 = r1.f817c
            int r9 = r9.mo4706f()
            if (r0 <= r9) goto L_0x06a7
            int r0 = r10.mo4712j()
            r15 = 5
            ec r9 = r10.mo4705e(r15)
            int r9 = r9.mo4667a()
            ee r13 = r1.f817c
            int r15 = r1.f819e
            int r0 = r0 + r9
            int r0 = java.lang.Math.max(r15, r0)
            r13.mo4696b(r0)
            goto L_0x06a9
        L_0x06a7:
        L_0x06a8:
        L_0x06a9:
            r9 = 1
        L_0x06aa:
            boolean r0 = r12.f7059M
            if (r0 != 0) goto L_0x06b0
            r12 = -1
            goto L_0x06c0
        L_0x06b0:
            int r0 = r11.getBaseline()
            r12 = -1
            if (r0 == r12) goto L_0x06c0
            int r13 = r10.f8012z
            if (r0 == r13) goto L_0x06c0
            r10.f8012z = r0
            r9 = 1
        L_0x06c0:
            int r0 = android.os.Build.VERSION.SDK_INT
            int r0 = r11.getMeasuredState()
            int r8 = combineMeasuredStates(r8, r0)
            r17 = r9
            goto L_0x06d1
        L_0x06ce:
            r21 = r0
            r12 = -1
        L_0x06d1:
            int r14 = r14 + 1
            r0 = r21
            r9 = 8
            goto L_0x05d6
        L_0x06da:
            if (r17 != 0) goto L_0x06dd
            goto L_0x06e0
        L_0x06dd:
            r32.mo2453b()
        L_0x06e0:
            r14 = r8
            goto L_0x06e4
        L_0x06e2:
            r14 = 0
        L_0x06e4:
            ee r0 = r1.f817c
            int r0 = r0.mo4699c()
            ee r6 = r1.f817c
            int r6 = r6.mo4706f()
            int r7 = android.os.Build.VERSION.SDK_INT
            int r0 = r0 + r4
            int r0 = resolveSizeAndState(r0, r2, r14)
            int r6 = r6 + r5
            int r2 = r14 << 16
            int r2 = resolveSizeAndState(r6, r3, r2)
            int r3 = r1.f820f
            int r0 = java.lang.Math.min(r3, r0)
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r0 = r0 & r3
            int r4 = r1.f821g
            int r2 = java.lang.Math.min(r4, r2)
            r2 = r2 & r3
            ee r3 = r1.f817c
            boolean r4 = r3.f8079ad
            r5 = 16777216(0x1000000, float:2.3509887E-38)
            if (r4 == 0) goto L_0x0718
            r0 = r0 | r5
        L_0x0718:
            boolean r3 = r3.f8080ae
            if (r3 == 0) goto L_0x071d
            r2 = r2 | r5
        L_0x071d:
            r1.setMeasuredDimension(r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.onMeasure(int, int):void");
    }

    public final void onViewAdded(View view) {
        int i = Build.VERSION.SDK_INT;
        super.onViewAdded(view);
        C0116ed a = m804a(view);
        if ((view instanceof Guideline) && !(a instanceof C0118ef)) {
            C0102dq dqVar = (C0102dq) view.getLayoutParams();
            dqVar.f7068V = new C0118ef();
            dqVar.f7060N = true;
            ((C0118ef) dqVar.f7068V).mo4761h(dqVar.f7056J);
        }
        this.f815a.put(view.getId(), view);
        this.f822h = true;
    }

    public final void onViewRemoved(View view) {
        int i = Build.VERSION.SDK_INT;
        super.onViewRemoved(view);
        this.f815a.remove(view.getId());
        this.f817c.mo4818a(m804a(view));
        this.f822h = true;
    }

    public final void removeView(View view) {
        super.removeView(view);
        int i = Build.VERSION.SDK_INT;
    }

    public final void requestLayout() {
        super.requestLayout();
        this.f822h = true;
    }

    public final void setId(int i) {
        this.f815a.remove(getId());
        super.setId(i);
        this.f815a.put(getId(), this);
    }

    /* renamed from: b */
    private final void mo2453b() {
        this.f817c.mo4742n();
    }
}
