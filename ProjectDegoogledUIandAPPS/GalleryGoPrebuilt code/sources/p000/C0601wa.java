package p000;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: wa */
/* compiled from: PG */
public class C0601wa extends ViewGroup {

    /* renamed from: a */
    private boolean f16181a;

    /* renamed from: b */
    private int f16182b;

    /* renamed from: c */
    private int f16183c;

    /* renamed from: d */
    private int f16184d;

    /* renamed from: e */
    private int f16185e;

    /* renamed from: f */
    public int f16186f;

    /* renamed from: g */
    public Drawable f16187g;

    /* renamed from: h */
    public int f16188h;

    /* renamed from: i */
    private float f16189i;

    /* renamed from: j */
    private boolean f16190j;

    /* renamed from: k */
    private int[] f16191k;

    /* renamed from: l */
    private int[] f16192l;

    /* renamed from: m */
    private int f16193m;

    /* renamed from: n */
    private int f16194n;

    /* renamed from: o */
    private int f16195o;

    public C0601wa(Context context) {
        this(context, (AttributeSet) null);
    }

    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public C0601wa(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C0601wa(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean z = true;
        this.f16181a = true;
        this.f16182b = -1;
        this.f16183c = 0;
        this.f16186f = 8388659;
        C0684zc a = C0684zc.m16192a(context, attributeSet, C0435px.f15585m, i, 0);
        int a2 = a.mo10722a(1, -1);
        if (a2 >= 0) {
            mo10441c(a2);
        }
        int a3 = a.mo10722a(0, -1);
        if (a3 >= 0 && this.f16186f != a3) {
            a3 = (8388615 & a3) == 0 ? a3 | 8388611 : a3;
            this.f16186f = (a3 & 112) == 0 ? a3 | 48 : a3;
            requestLayout();
        }
        if (!a.mo10725a(2, true)) {
            mo10442e();
        }
        this.f16189i = a.f16436b.getFloat(4, -1.0f);
        this.f16182b = a.mo10722a(3, -1);
        this.f16190j = a.mo10725a(7, false);
        Drawable a4 = a.mo10723a(5);
        if (a4 != this.f16187g) {
            this.f16187g = a4;
            if (a4 != null) {
                this.f16188h = a4.getIntrinsicWidth();
                this.f16193m = a4.getIntrinsicHeight();
            } else {
                this.f16188h = 0;
                this.f16193m = 0;
            }
            setWillNotDraw(a4 != null ? false : z);
            requestLayout();
        }
        this.f16194n = a.mo10722a(8, 0);
        this.f16195o = a.mo10730d(6, 0);
        a.mo10724a();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0599vz;
    }

    /* renamed from: a */
    private final void m15680a(Canvas canvas, int i) {
        this.f16187g.setBounds(getPaddingLeft() + this.f16195o, i, (getWidth() - getPaddingRight()) - this.f16195o, this.f16193m + i);
        this.f16187g.draw(canvas);
    }

    /* renamed from: b */
    private final void m15682b(Canvas canvas, int i) {
        this.f16187g.setBounds(i, getPaddingTop() + this.f16195o, this.f16188h + i, (getHeight() - getPaddingBottom()) - this.f16195o);
        this.f16187g.draw(canvas);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C0599vz generateDefaultLayoutParams() {
        int i = this.f16184d;
        if (i == 0) {
            return new C0599vz(-2);
        }
        if (i == 1) {
            return new C0599vz(-1);
        }
        return null;
    }

    /* renamed from: a */
    public C0599vz generateLayoutParams(AttributeSet attributeSet) {
        return new C0599vz(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0599vz generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0599vz(layoutParams);
    }

    public final int getBaseline() {
        int i;
        if (this.f16182b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.f16182b;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.f16183c;
                if (this.f16184d == 1 && (i = this.f16186f & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f16185e) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f16185e;
                    }
                }
                return i3 + ((C0599vz) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.f16182b == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo10440b(int i) {
        if (i == 0) {
            return (this.f16194n & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.f16194n & 4) != 0;
        }
        if ((this.f16194n & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        if (this.f16187g != null) {
            int i4 = 0;
            if (this.f16184d != 1) {
                int childCount = getChildCount();
                boolean a = C0703zv.m16280a(this);
                while (i4 < childCount) {
                    View childAt = getChildAt(i4);
                    if (!(childAt == null || childAt.getVisibility() == 8 || !mo10440b(i4))) {
                        C0599vz vzVar = (C0599vz) childAt.getLayoutParams();
                        if (a) {
                            i3 = childAt.getRight() + vzVar.rightMargin;
                        } else {
                            i3 = (childAt.getLeft() - vzVar.leftMargin) - this.f16188h;
                        }
                        m15682b(canvas, i3);
                    }
                    i4++;
                }
                if (mo10440b(childCount)) {
                    View childAt2 = getChildAt(childCount - 1);
                    if (childAt2 != null) {
                        C0599vz vzVar2 = (C0599vz) childAt2.getLayoutParams();
                        if (a) {
                            i2 = (childAt2.getLeft() - vzVar2.leftMargin) - this.f16188h;
                        } else {
                            i2 = childAt2.getRight() + vzVar2.rightMargin;
                        }
                    } else if (!a) {
                        i2 = (getWidth() - getPaddingRight()) - this.f16188h;
                    } else {
                        i2 = getPaddingLeft();
                    }
                    m15682b(canvas, i2);
                    return;
                }
                return;
            }
            int childCount2 = getChildCount();
            while (i4 < childCount2) {
                View childAt3 = getChildAt(i4);
                if (!(childAt3 == null || childAt3.getVisibility() == 8 || !mo10440b(i4))) {
                    m15680a(canvas, (childAt3.getTop() - ((C0599vz) childAt3.getLayoutParams()).topMargin) - this.f16193m);
                }
                i4++;
            }
            if (mo10440b(childCount2)) {
                View childAt4 = getChildAt(childCount2 - 1);
                if (childAt4 != null) {
                    i = childAt4.getBottom() + ((C0599vz) childAt4.getLayoutParams()).bottomMargin;
                } else {
                    i = (getHeight() - getPaddingBottom()) - this.f16193m;
                }
                m15680a(canvas, i);
            }
        }
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.support.v7.widget.LinearLayoutCompat");
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.support.v7.widget.LinearLayoutCompat");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r23, int r24, int r25, int r26, int r27) {
        /*
            r22 = this;
            r0 = r22
            int r1 = r0.f16184d
            r2 = 8
            r3 = 5
            r6 = 8388615(0x800007, float:1.1754953E-38)
            r8 = 2
            r9 = 1
            if (r1 == r9) goto L_0x012c
            boolean r1 = p000.C0703zv.m16280a(r22)
            int r10 = r22.getPaddingTop()
            int r11 = r27 - r25
            int r12 = r22.getPaddingBottom()
            int r12 = r11 - r12
            int r11 = r11 - r10
            int r13 = r22.getPaddingBottom()
            int r11 = r11 - r13
            int r13 = r22.getChildCount()
            int r14 = r0.f16186f
            r15 = r14 & 112(0x70, float:1.57E-43)
            boolean r7 = r0.f16181a
            int[] r4 = r0.f16191k
            int[] r5 = r0.f16192l
            r6 = r6 & r14
            int r14 = p000.C0340mj.m14714f(r22)
            int r6 = p000.C0321lr.m14621a((int) r6, (int) r14)
            if (r6 == r9) goto L_0x0050
            if (r6 == r3) goto L_0x0044
            int r3 = r22.getPaddingLeft()
            goto L_0x005b
        L_0x0044:
            int r3 = r22.getPaddingLeft()
            int r3 = r3 + r26
            int r3 = r3 - r24
            int r6 = r0.f16185e
            int r3 = r3 - r6
            goto L_0x005b
        L_0x0050:
            int r3 = r22.getPaddingLeft()
            int r6 = r26 - r24
            int r14 = r0.f16185e
            int r6 = r6 - r14
            int r6 = r6 / r8
            int r3 = r3 + r6
        L_0x005b:
            if (r1 == 0) goto L_0x0061
            int r1 = r13 + -1
            r14 = -1
            goto L_0x0064
        L_0x0061:
            r1 = 0
            r14 = 1
        L_0x0064:
            r9 = 0
        L_0x0065:
            if (r9 >= r13) goto L_0x01d8
            int r17 = r14 * r9
            int r8 = r1 + r17
            android.view.View r6 = r0.getChildAt(r8)
            if (r6 != 0) goto L_0x007e
            r25 = r1
            r19 = r7
            r27 = r13
            r26 = r14
            r20 = r15
            r15 = -1
            goto L_0x011b
        L_0x007e:
            r25 = r1
            int r1 = r6.getVisibility()
            if (r1 == r2) goto L_0x0112
            int r1 = r6.getMeasuredWidth()
            int r2 = r6.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r19 = r6.getLayoutParams()
            r27 = r13
            r13 = r19
            vz r13 = (p000.C0599vz) r13
            if (r7 == 0) goto L_0x00aa
            r19 = r7
            int r7 = r13.height
            r26 = r14
            r14 = -1
            if (r7 == r14) goto L_0x00a9
            int r7 = r6.getBaseline()
            r14 = r7
            goto L_0x00af
        L_0x00a9:
            goto L_0x00ae
        L_0x00aa:
            r19 = r7
            r26 = r14
        L_0x00ae:
            r14 = -1
        L_0x00af:
            int r7 = r13.f16180h
            if (r7 < 0) goto L_0x00b4
            goto L_0x00b5
        L_0x00b4:
            r7 = r15
        L_0x00b5:
            r7 = r7 & 112(0x70, float:1.57E-43)
            r20 = r15
            r15 = 16
            if (r7 == r15) goto L_0x00f1
            r15 = 48
            if (r7 == r15) goto L_0x00e0
            r15 = 80
            if (r7 == r15) goto L_0x00c8
            r7 = r10
        L_0x00c6:
            r15 = -1
            goto L_0x00fd
        L_0x00c8:
            int r7 = r12 - r2
            int r15 = r13.bottomMargin
            int r7 = r7 - r15
            r15 = -1
            if (r14 == r15) goto L_0x00df
            int r15 = r6.getMeasuredHeight()
            r18 = 2
            r21 = r5[r18]
            int r15 = r15 - r14
            int r21 = r21 - r15
            int r7 = r7 - r21
            r15 = -1
            goto L_0x00fd
        L_0x00df:
            goto L_0x00c6
        L_0x00e0:
            int r7 = r13.topMargin
            int r7 = r7 + r10
            r15 = -1
            if (r14 != r15) goto L_0x00e7
            goto L_0x00fd
        L_0x00e7:
            r16 = 1
            r21 = r4[r16]
            int r21 = r21 - r14
            int r7 = r7 + r21
            goto L_0x00fd
        L_0x00f1:
            r15 = -1
            int r7 = r11 - r2
            r14 = 2
            int r7 = r7 / r14
            int r7 = r7 + r10
            int r14 = r13.topMargin
            int r7 = r7 + r14
            int r14 = r13.bottomMargin
            int r7 = r7 - r14
        L_0x00fd:
            boolean r8 = r0.mo10440b(r8)
            if (r8 != 0) goto L_0x0104
            goto L_0x0107
        L_0x0104:
            int r8 = r0.f16188h
            int r3 = r3 + r8
        L_0x0107:
            int r8 = r13.leftMargin
            int r3 = r3 + r8
            m15681a(r6, r3, r7, r1, r2)
            int r2 = r13.rightMargin
            int r1 = r1 + r2
            int r3 = r3 + r1
            goto L_0x011b
        L_0x0112:
            r19 = r7
            r27 = r13
            r26 = r14
            r20 = r15
            r15 = -1
        L_0x011b:
            int r9 = r9 + 1
            r1 = r25
            r14 = r26
            r13 = r27
            r7 = r19
            r15 = r20
            r2 = 8
            r8 = 2
            goto L_0x0065
        L_0x012c:
            int r1 = r22.getPaddingLeft()
            int r2 = r26 - r24
            int r4 = r22.getPaddingRight()
            int r4 = r2 - r4
            int r2 = r2 - r1
            int r5 = r22.getPaddingRight()
            int r2 = r2 - r5
            int r5 = r22.getChildCount()
            int r7 = r0.f16186f
            r6 = r6 & r7
            r7 = r7 & 112(0x70, float:1.57E-43)
            r8 = 16
            if (r7 == r8) goto L_0x0160
            r8 = 80
            if (r7 == r8) goto L_0x0154
            int r7 = r22.getPaddingTop()
            goto L_0x016c
        L_0x0154:
            int r7 = r22.getPaddingTop()
            int r7 = r7 + r27
            int r7 = r7 - r25
            int r8 = r0.f16185e
            int r7 = r7 - r8
            goto L_0x016c
        L_0x0160:
            int r7 = r22.getPaddingTop()
            int r8 = r27 - r25
            int r9 = r0.f16185e
            int r8 = r8 - r9
            r9 = 2
            int r8 = r8 / r9
            int r7 = r7 + r8
        L_0x016c:
            r8 = r7
            r7 = 0
        L_0x016e:
            if (r7 >= r5) goto L_0x01d8
            android.view.View r9 = r0.getChildAt(r7)
            if (r9 != 0) goto L_0x0179
            r14 = 2
            r15 = 1
            goto L_0x01d4
        L_0x0179:
            int r10 = r9.getVisibility()
            r11 = 8
            if (r10 == r11) goto L_0x01d2
            int r10 = r9.getMeasuredWidth()
            int r12 = r9.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r13 = r9.getLayoutParams()
            vz r13 = (p000.C0599vz) r13
            int r14 = r13.f16180h
            if (r14 < 0) goto L_0x0194
            goto L_0x0195
        L_0x0194:
            r14 = r6
        L_0x0195:
            int r15 = p000.C0340mj.m14714f(r22)
            int r14 = p000.C0321lr.m14621a((int) r14, (int) r15)
            r14 = r14 & 7
            r15 = 1
            if (r14 == r15) goto L_0x01b2
            if (r14 == r3) goto L_0x01aa
            int r14 = r13.leftMargin
            int r14 = r14 + r1
            r3 = r14
            r14 = 2
            goto L_0x01bd
        L_0x01aa:
            int r14 = r4 - r10
            int r3 = r13.rightMargin
            int r14 = r14 - r3
            r3 = r14
            r14 = 2
            goto L_0x01bd
        L_0x01b2:
            int r3 = r2 - r10
            r14 = 2
            int r3 = r3 / r14
            int r3 = r3 + r1
            int r11 = r13.leftMargin
            int r3 = r3 + r11
            int r11 = r13.rightMargin
            int r3 = r3 - r11
        L_0x01bd:
            boolean r11 = r0.mo10440b(r7)
            if (r11 != 0) goto L_0x01c4
            goto L_0x01c7
        L_0x01c4:
            int r11 = r0.f16193m
            int r8 = r8 + r11
        L_0x01c7:
            int r11 = r13.topMargin
            int r8 = r8 + r11
            m15681a(r9, r3, r8, r10, r12)
            int r3 = r13.bottomMargin
            int r12 = r12 + r3
            int r8 = r8 + r12
            goto L_0x01d4
        L_0x01d2:
            r14 = 2
            r15 = 1
        L_0x01d4:
            int r7 = r7 + 1
            r3 = 5
            goto L_0x016e
        L_0x01d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0601wa.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0157, code lost:
        if (r4.width == -1) goto L_0x015d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x02dd  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02e1  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x02fd  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0303  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0363  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x036b  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x0499  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x049e  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x04cb  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x04d1  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x04db  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x04e7  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x04f9  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x0504  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x050b  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0524  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x0554  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0561  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x0564  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x056c  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x057b  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x08a8  */
    /* JADX WARNING: Removed duplicated region for block: B:420:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r39, int r40) {
        /*
            r38 = this;
            r6 = r38
            r7 = r39
            r8 = r40
            int r0 = r6.f16184d
            r10 = 16777215(0xffffff, float:2.3509886E-38)
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            r12 = 8
            r13 = 0
            r15 = 1
            r4 = 0
            if (r0 != r15) goto L_0x03a9
            r6.f16185e = r4
            int r3 = r38.getChildCount()
            int r2 = android.view.View.MeasureSpec.getMode(r39)
            int r1 = android.view.View.MeasureSpec.getMode(r40)
            int r0 = r6.f16182b
            boolean r15 = r6.f16190j
            r5 = 0
            r9 = 0
            r17 = 1
            r18 = 0
            r19 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
        L_0x0039:
            if (r9 < r3) goto L_0x0240
            int r0 = r6.f16185e
            if (r0 <= 0) goto L_0x004c
            boolean r0 = r6.mo10440b(r3)
            if (r0 == 0) goto L_0x004c
            int r0 = r6.f16185e
            int r9 = r6.f16193m
            int r0 = r0 + r9
            r6.f16185e = r0
        L_0x004c:
            if (r15 != 0) goto L_0x004f
            goto L_0x0081
        L_0x004f:
            if (r1 == r11) goto L_0x0053
            if (r1 != 0) goto L_0x0081
        L_0x0053:
            r6.f16185e = r4
            r0 = 0
        L_0x0057:
            if (r0 >= r3) goto L_0x0081
            android.view.View r9 = r6.getChildAt(r0)
            if (r9 != 0) goto L_0x0060
            goto L_0x007e
        L_0x0060:
            int r11 = r9.getVisibility()
            if (r11 == r12) goto L_0x007e
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            vz r9 = (p000.C0599vz) r9
            int r11 = r6.f16185e
            int r20 = r11 + r5
            int r14 = r9.topMargin
            int r20 = r20 + r14
            int r9 = r9.bottomMargin
            int r9 = r20 + r9
            int r9 = java.lang.Math.max(r11, r9)
            r6.f16185e = r9
        L_0x007e:
            int r0 = r0 + 1
            goto L_0x0057
        L_0x0081:
            int r0 = r6.f16185e
            int r9 = r38.getPaddingTop()
            int r11 = r38.getPaddingBottom()
            int r9 = r9 + r11
            int r0 = r0 + r9
            r6.f16185e = r0
            int r9 = r38.getSuggestedMinimumHeight()
            int r0 = java.lang.Math.max(r0, r9)
            int r0 = android.view.View.resolveSizeAndState(r0, r8, r4)
            r9 = r0 & r10
            int r10 = r6.f16185e
            int r9 = r9 - r10
            if (r19 == 0) goto L_0x00a3
            goto L_0x00ac
        L_0x00a3:
            if (r9 != 0) goto L_0x00a7
            goto L_0x0191
        L_0x00a7:
            int r10 = (r24 > r13 ? 1 : (r24 == r13 ? 0 : -1))
            if (r10 <= 0) goto L_0x0191
        L_0x00ac:
            float r5 = r6.f16189i
            int r10 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r10 <= 0) goto L_0x00b4
            r24 = r5
        L_0x00b4:
            r6.f16185e = r4
            r5 = r21
            r10 = r22
            r11 = r25
            r14 = 0
        L_0x00bd:
            if (r14 < r3) goto L_0x00d1
            int r1 = r6.f16185e
            int r9 = r38.getPaddingTop()
            int r13 = r38.getPaddingBottom()
            int r9 = r9 + r13
            int r1 = r1 + r9
            r6.f16185e = r1
            r25 = r11
            goto L_0x01d4
        L_0x00d1:
            android.view.View r15 = r6.getChildAt(r14)
            int r4 = r15.getVisibility()
            if (r4 == r12) goto L_0x0187
            android.view.ViewGroup$LayoutParams r4 = r15.getLayoutParams()
            vz r4 = (p000.C0599vz) r4
            float r12 = r4.f16179g
            int r19 = (r12 > r13 ? 1 : (r12 == r13 ? 0 : -1))
            if (r19 <= 0) goto L_0x013d
            float r13 = (float) r9
            float r13 = r13 * r12
            float r13 = r13 / r24
            int r13 = (int) r13
            float r24 = r24 - r12
            int r9 = r9 - r13
            int r12 = r38.getPaddingLeft()
            int r19 = r38.getPaddingRight()
            int r12 = r12 + r19
            r19 = r9
            int r9 = r4.leftMargin
            int r12 = r12 + r9
            int r9 = r4.rightMargin
            int r12 = r12 + r9
            int r9 = r4.width
            int r9 = getChildMeasureSpec(r7, r12, r9)
            int r12 = r4.height
            if (r12 != 0) goto L_0x011d
            r12 = 1073741824(0x40000000, float:2.0)
            if (r1 != r12) goto L_0x011d
            if (r13 <= 0) goto L_0x0113
            goto L_0x0115
        L_0x0113:
            r13 = 0
        L_0x0115:
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r12)
            r15.measure(r9, r13)
            goto L_0x0130
        L_0x011d:
            int r12 = r15.getMeasuredHeight()
            int r12 = r12 + r13
            if (r12 < 0) goto L_0x0125
            goto L_0x0127
        L_0x0125:
            r12 = 0
        L_0x0127:
            r13 = 1073741824(0x40000000, float:2.0)
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r13)
            r15.measure(r9, r12)
        L_0x0130:
            int r9 = r15.getMeasuredState()
            r9 = r9 & -256(0xffffffffffffff00, float:NaN)
            int r5 = android.view.View.combineMeasuredStates(r5, r9)
            r9 = r19
            goto L_0x013e
        L_0x013d:
        L_0x013e:
            int r12 = r4.leftMargin
            int r13 = r4.rightMargin
            int r12 = r12 + r13
            int r13 = r15.getMeasuredWidth()
            int r13 = r13 + r12
            int r11 = java.lang.Math.max(r11, r13)
            r19 = r5
            r5 = 1073741824(0x40000000, float:2.0)
            if (r2 == r5) goto L_0x015a
            int r5 = r4.width
            r20 = r9
            r9 = -1
            if (r5 != r9) goto L_0x015c
            goto L_0x015d
        L_0x015a:
            r20 = r9
        L_0x015c:
            r12 = r13
        L_0x015d:
            int r5 = java.lang.Math.max(r10, r12)
            if (r17 == 0) goto L_0x016b
            int r9 = r4.width
            r10 = -1
            if (r9 != r10) goto L_0x016a
            r9 = 1
            goto L_0x016c
        L_0x016a:
        L_0x016b:
            r9 = 0
        L_0x016c:
            int r10 = r6.f16185e
            int r12 = r15.getMeasuredHeight()
            int r12 = r12 + r10
            int r13 = r4.topMargin
            int r12 = r12 + r13
            int r4 = r4.bottomMargin
            int r12 = r12 + r4
            int r4 = java.lang.Math.max(r10, r12)
            r6.f16185e = r4
            r10 = r5
            r17 = r9
            r5 = r19
            r9 = r20
            goto L_0x0188
        L_0x0187:
        L_0x0188:
            int r14 = r14 + 1
            r4 = 0
            r12 = 8
            r13 = 0
            goto L_0x00bd
        L_0x0191:
            r12 = r22
            r13 = r23
            int r10 = java.lang.Math.max(r12, r13)
            if (r15 == 0) goto L_0x01d2
            r4 = 1073741824(0x40000000, float:2.0)
            if (r1 == r4) goto L_0x01d2
            r1 = 0
        L_0x01a0:
            if (r1 >= r3) goto L_0x01d2
            android.view.View r4 = r6.getChildAt(r1)
            if (r4 != 0) goto L_0x01a9
            goto L_0x01cf
        L_0x01a9:
            int r9 = r4.getVisibility()
            r11 = 8
            if (r9 == r11) goto L_0x01cf
            android.view.ViewGroup$LayoutParams r9 = r4.getLayoutParams()
            vz r9 = (p000.C0599vz) r9
            float r9 = r9.f16179g
            r11 = 0
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 <= 0) goto L_0x01cf
            int r9 = r4.getMeasuredWidth()
            r11 = 1073741824(0x40000000, float:2.0)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r11)
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r11)
            r4.measure(r9, r12)
        L_0x01cf:
            int r1 = r1 + 1
            goto L_0x01a0
        L_0x01d2:
            r5 = r21
        L_0x01d4:
            if (r17 == 0) goto L_0x01d7
            goto L_0x01dc
        L_0x01d7:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r2 == r1) goto L_0x01dc
            goto L_0x01de
        L_0x01dc:
            r10 = r25
        L_0x01de:
            int r1 = r38.getPaddingLeft()
            int r2 = r38.getPaddingRight()
            int r1 = r1 + r2
            int r10 = r10 + r1
            int r1 = r38.getSuggestedMinimumWidth()
            int r1 = java.lang.Math.max(r10, r1)
            int r1 = android.view.View.resolveSizeAndState(r1, r7, r5)
            r6.setMeasuredDimension(r1, r0)
            if (r18 == 0) goto L_0x08e6
            int r0 = r38.getMeasuredWidth()
            r1 = 1073741824(0x40000000, float:2.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            r9 = 0
        L_0x0204:
            if (r9 >= r3) goto L_0x08e6
            android.view.View r1 = r6.getChildAt(r9)
            int r0 = r1.getVisibility()
            r2 = 8
            if (r0 == r2) goto L_0x023a
            android.view.ViewGroup$LayoutParams r0 = r1.getLayoutParams()
            r10 = r0
            vz r10 = (p000.C0599vz) r10
            int r0 = r10.width
            r5 = -1
            if (r0 != r5) goto L_0x0237
            int r11 = r10.height
            int r0 = r1.getMeasuredHeight()
            r10.height = r0
            r4 = 0
            r12 = 0
            r0 = r38
            r2 = r7
            r14 = r3
            r3 = r4
            r4 = r40
            r13 = -1
            r5 = r12
            r0.measureChildWithMargins(r1, r2, r3, r4, r5)
            r10.height = r11
            goto L_0x023c
        L_0x0237:
            r14 = r3
            r13 = -1
            goto L_0x023c
        L_0x023a:
            r14 = r3
            r13 = -1
        L_0x023c:
            int r9 = r9 + 1
            r3 = r14
            goto L_0x0204
        L_0x0240:
            r14 = r3
            r12 = r22
            r13 = r23
            r4 = -1
            android.view.View r22 = r6.getChildAt(r9)
            if (r22 != 0) goto L_0x025a
            r28 = r0
            r29 = r1
            r8 = r5
            r30 = r14
            r11 = r21
            r4 = r25
            r14 = r2
            goto L_0x038a
        L_0x025a:
            int r3 = r22.getVisibility()
            r4 = 8
            if (r3 == r4) goto L_0x037e
            boolean r3 = r6.mo10440b(r9)
            if (r3 == 0) goto L_0x026f
            int r3 = r6.f16185e
            int r4 = r6.f16193m
            int r3 = r3 + r4
            r6.f16185e = r3
        L_0x026f:
            android.view.ViewGroup$LayoutParams r3 = r22.getLayoutParams()
            r4 = r3
            vz r4 = (p000.C0599vz) r4
            float r3 = r4.f16179g
            float r24 = r24 + r3
            r3 = 1073741824(0x40000000, float:2.0)
            if (r1 != r3) goto L_0x02a5
            int r3 = r4.height
            if (r3 != 0) goto L_0x02a5
            float r3 = r4.f16179g
            r23 = 0
            int r3 = (r3 > r23 ? 1 : (r3 == r23 ? 0 : -1))
            if (r3 <= 0) goto L_0x02a5
            int r3 = r6.f16185e
            int r10 = r4.topMargin
            int r10 = r10 + r3
            int r11 = r4.bottomMargin
            int r10 = r10 + r11
            int r3 = java.lang.Math.max(r3, r10)
            r6.f16185e = r3
            r28 = r0
            r29 = r1
            r3 = r4
            r30 = r14
            r10 = 0
            r19 = 1
            r14 = r2
            goto L_0x0304
        L_0x02a5:
            int r3 = r4.height
            if (r3 != 0) goto L_0x02b5
            float r3 = r4.f16179g
            r10 = 0
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 <= 0) goto L_0x02b6
            r3 = -2
            r4.height = r3
            r11 = 0
            goto L_0x02b8
        L_0x02b5:
            r10 = 0
        L_0x02b6:
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x02b8:
            int r3 = (r24 > r10 ? 1 : (r24 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x02c0
            int r3 = r6.f16185e
            r10 = r3
            goto L_0x02c2
        L_0x02c0:
            r10 = 0
        L_0x02c2:
            r3 = 0
            r28 = r0
            r0 = r38
            r29 = r1
            r1 = r22
            r30 = r14
            r14 = r2
            r2 = r39
            r31 = r4
            r4 = r40
            r8 = r5
            r5 = r10
            r0.measureChildWithMargins(r1, r2, r3, r4, r5)
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r11 != r0) goto L_0x02e1
            r3 = r31
            r10 = 0
            goto L_0x02e7
        L_0x02e1:
            r3 = r31
            r10 = 0
            r3.height = r10
        L_0x02e7:
            int r0 = r22.getMeasuredHeight()
            int r1 = r6.f16185e
            int r2 = r1 + r0
            int r4 = r3.topMargin
            int r2 = r2 + r4
            int r4 = r3.bottomMargin
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r6.f16185e = r1
            if (r15 == 0) goto L_0x0303
            int r5 = java.lang.Math.max(r0, r8)
            goto L_0x0304
        L_0x0303:
            r5 = r8
        L_0x0304:
            r0 = r28
            if (r0 < 0) goto L_0x0310
            int r1 = r9 + 1
            if (r0 != r1) goto L_0x0310
            int r1 = r6.f16185e
            r6.f16183c = r1
        L_0x0310:
            if (r9 >= r0) goto L_0x0322
            float r1 = r3.f16179g
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x031a
            goto L_0x0322
        L_0x031a:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex."
            r0.<init>(r1)
            throw r0
        L_0x0322:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r14 != r1) goto L_0x0329
            r4 = 0
            r11 = -1
            goto L_0x0333
        L_0x0329:
            int r1 = r3.width
            r11 = -1
            if (r1 != r11) goto L_0x0332
            r4 = 1
            r18 = 1
            goto L_0x0333
        L_0x0332:
            r4 = 0
        L_0x0333:
            int r1 = r3.leftMargin
            int r2 = r3.rightMargin
            int r1 = r1 + r2
            int r2 = r22.getMeasuredWidth()
            int r2 = r2 + r1
            r8 = r25
            int r8 = java.lang.Math.max(r8, r2)
            int r10 = r22.getMeasuredState()
            r11 = r21
            int r10 = android.view.View.combineMeasuredStates(r11, r10)
            if (r17 == 0) goto L_0x0359
            int r11 = r3.width
            r28 = r0
            r0 = -1
            if (r11 != r0) goto L_0x0358
            r0 = 1
            goto L_0x035c
        L_0x0358:
            goto L_0x035b
        L_0x0359:
            r28 = r0
        L_0x035b:
            r0 = 0
        L_0x035c:
            float r3 = r3.f16179g
            r11 = 0
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 > 0) goto L_0x036b
            if (r4 != 0) goto L_0x0366
            r1 = r2
        L_0x0366:
            int r22 = java.lang.Math.max(r12, r1)
            goto L_0x0376
        L_0x036b:
            if (r4 != 0) goto L_0x036e
            r1 = r2
        L_0x036e:
            int r1 = java.lang.Math.max(r13, r1)
            r13 = r1
            r22 = r12
        L_0x0376:
            r17 = r0
            r25 = r8
            r21 = r10
            goto L_0x0391
        L_0x037e:
            r28 = r0
            r29 = r1
            r8 = r5
            r30 = r14
            r11 = r21
            r4 = r25
            r14 = r2
        L_0x038a:
            r25 = r4
            r5 = r8
            r21 = r11
            r22 = r12
        L_0x0391:
            int r9 = r9 + 1
            r8 = r40
            r23 = r13
            r2 = r14
            r0 = r28
            r1 = r29
            r3 = r30
            r4 = 0
            r10 = 16777215(0xffffff, float:2.3509886E-38)
            r11 = -2147483648(0xffffffff80000000, float:-0.0)
            r12 = 8
            r13 = 0
            goto L_0x0039
        L_0x03a9:
            r0 = 0
            r6.f16185e = r0
            int r8 = r38.getChildCount()
            int r9 = android.view.View.MeasureSpec.getMode(r39)
            int r10 = android.view.View.MeasureSpec.getMode(r40)
            int[] r0 = r6.f16191k
            r11 = 4
            if (r0 != 0) goto L_0x03bf
            goto L_0x03c3
        L_0x03bf:
            int[] r0 = r6.f16192l
            if (r0 != 0) goto L_0x03cc
        L_0x03c3:
            int[] r0 = new int[r11]
            r6.f16191k = r0
            int[] r0 = new int[r11]
            r6.f16192l = r0
        L_0x03cc:
            int[] r12 = r6.f16191k
            int[] r13 = r6.f16192l
            r14 = 3
            r0 = -1
            r12[r14] = r0
            r15 = 2
            r12[r15] = r0
            r1 = 1
            r12[r1] = r0
            r2 = 0
            r12[r2] = r0
            r13[r14] = r0
            r13[r15] = r0
            r13[r1] = r0
            r13[r2] = r0
            boolean r5 = r6.f16181a
            boolean r4 = r6.f16190j
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r14 = 0
            r15 = 0
            r17 = 1
            r18 = 0
            r19 = 0
            r24 = 0
        L_0x03f8:
            if (r3 >= r8) goto L_0x05ae
            android.view.View r11 = r6.getChildAt(r3)
            if (r11 != 0) goto L_0x040d
            r26 = r3
            r20 = r4
            r29 = r5
            r28 = -2
            r4 = r0
            r0 = r1
            r5 = r2
            goto L_0x05a2
        L_0x040d:
            r26 = r0
            int r0 = r11.getVisibility()
            r28 = r1
            r1 = 8
            if (r0 == r1) goto L_0x0595
            boolean r0 = r6.mo10440b(r3)
            if (r0 == 0) goto L_0x0426
            int r0 = r6.f16185e
            int r1 = r6.f16188h
            int r0 = r0 + r1
            r6.f16185e = r0
        L_0x0426:
            android.view.ViewGroup$LayoutParams r0 = r11.getLayoutParams()
            r1 = r0
            vz r1 = (p000.C0599vz) r1
            float r0 = r1.f16179g
            float r24 = r24 + r0
            r0 = 1073741824(0x40000000, float:2.0)
            if (r9 != r0) goto L_0x047d
            int r0 = r1.width
            if (r0 != 0) goto L_0x0478
            float r0 = r1.f16179g
            r27 = 0
            int r0 = (r0 > r27 ? 1 : (r0 == r27 ? 0 : -1))
            if (r0 <= 0) goto L_0x0478
            int r0 = r6.f16185e
            r29 = r2
            int r2 = r1.leftMargin
            r30 = r3
            int r3 = r1.rightMargin
            int r2 = r2 + r3
            int r0 = r0 + r2
            r6.f16185e = r0
            if (r5 == 0) goto L_0x0465
            r0 = 0
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0)
            r11.measure(r2, r2)
            r0 = r1
            r20 = r4
            r36 = r26
            r33 = r28
            r35 = r29
            r26 = r30
            goto L_0x0472
        L_0x0465:
            r0 = r1
            r20 = r4
            r36 = r26
            r33 = r28
            r35 = r29
            r26 = r30
            r19 = 1
        L_0x0472:
            r28 = -2
            r29 = r5
            goto L_0x0500
        L_0x0478:
            r29 = r2
            r30 = r3
            goto L_0x0481
        L_0x047d:
            r29 = r2
            r30 = r3
        L_0x0481:
            int r0 = r1.width
            if (r0 != 0) goto L_0x0491
            float r0 = r1.f16179g
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0492
            r3 = -2
            r1.width = r3
            r0 = 0
            goto L_0x0495
        L_0x0491:
            r2 = 0
        L_0x0492:
            r3 = -2
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x0495:
            int r20 = (r24 > r2 ? 1 : (r24 == r2 ? 0 : -1))
            if (r20 != 0) goto L_0x049e
            int r2 = r6.f16185e
            r20 = r2
            goto L_0x04a1
        L_0x049e:
            r20 = 0
        L_0x04a1:
            r31 = 0
            r32 = r0
            r2 = r26
            r0 = r38
            r34 = r1
            r33 = r28
            r1 = r11
            r36 = r2
            r35 = r29
            r2 = r39
            r26 = r30
            r28 = -2
            r3 = r20
            r20 = r4
            r4 = r40
            r29 = r5
            r5 = r31
            r0.measureChildWithMargins(r1, r2, r3, r4, r5)
            r0 = r32
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r1) goto L_0x04d1
            r0 = r34
            r1 = 0
            r0.width = r1
            goto L_0x04d3
        L_0x04d1:
            r0 = r34
        L_0x04d3:
            int r1 = r11.getMeasuredWidth()
            r2 = 1073741824(0x40000000, float:2.0)
            if (r9 != r2) goto L_0x04e7
            int r2 = r6.f16185e
            int r3 = r0.leftMargin
            int r3 = r3 + r1
            int r4 = r0.rightMargin
            int r3 = r3 + r4
            int r2 = r2 + r3
            r6.f16185e = r2
            goto L_0x04f7
        L_0x04e7:
            int r2 = r6.f16185e
            int r3 = r2 + r1
            int r4 = r0.leftMargin
            int r3 = r3 + r4
            int r4 = r0.rightMargin
            int r3 = r3 + r4
            int r2 = java.lang.Math.max(r2, r3)
            r6.f16185e = r2
        L_0x04f7:
            if (r20 == 0) goto L_0x04ff
            int r14 = java.lang.Math.max(r1, r14)
            goto L_0x0500
        L_0x04ff:
        L_0x0500:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r10 != r1) goto L_0x0506
        L_0x0504:
            r4 = 0
            goto L_0x050e
        L_0x0506:
            int r1 = r0.height
            r2 = -1
            if (r1 != r2) goto L_0x0504
            r4 = 1
            r18 = 1
        L_0x050e:
            int r1 = r0.topMargin
            int r2 = r0.bottomMargin
            int r1 = r1 + r2
            int r2 = r11.getMeasuredHeight()
            int r2 = r2 + r1
            int r3 = r11.getMeasuredState()
            r5 = r35
            int r3 = android.view.View.combineMeasuredStates(r5, r3)
            if (r29 == 0) goto L_0x0554
            int r5 = r11.getBaseline()
            r11 = -1
            if (r5 == r11) goto L_0x0551
            int r11 = r0.f16180h
            if (r11 < 0) goto L_0x0530
            goto L_0x0532
        L_0x0530:
            int r11 = r6.f16186f
        L_0x0532:
            r11 = r11 & 112(0x70, float:1.57E-43)
            r25 = 4
            int r11 = r11 >> 4
            r16 = 1
            int r11 = r11 >> 1
            r30 = r1
            r1 = r12[r11]
            int r1 = java.lang.Math.max(r1, r5)
            r12[r11] = r1
            r1 = r13[r11]
            int r5 = r2 - r5
            int r1 = java.lang.Math.max(r1, r5)
            r13[r11] = r1
            goto L_0x0556
        L_0x0551:
            r30 = r1
            goto L_0x0556
        L_0x0554:
            r30 = r1
        L_0x0556:
            int r1 = java.lang.Math.max(r15, r2)
            if (r17 == 0) goto L_0x0564
            int r5 = r0.height
            r11 = -1
            if (r5 != r11) goto L_0x0563
            r5 = 1
            goto L_0x0565
        L_0x0563:
        L_0x0564:
            r5 = 0
        L_0x0565:
            float r0 = r0.f16179g
            r11 = 0
            int r0 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r0 > 0) goto L_0x057b
            if (r4 != 0) goto L_0x056f
            goto L_0x0571
        L_0x056f:
            r2 = r30
        L_0x0571:
            r0 = r33
            int r0 = java.lang.Math.max(r0, r2)
            r2 = r0
            r0 = r36
            goto L_0x058e
        L_0x057b:
            r0 = r33
            if (r4 != 0) goto L_0x0580
            goto L_0x0582
        L_0x0580:
            r2 = r30
        L_0x0582:
            r4 = r36
            int r2 = java.lang.Math.max(r4, r2)
            r37 = r2
            r2 = r0
            r0 = r37
        L_0x058e:
            r15 = r1
            r1 = r2
            r2 = r3
            r17 = r5
            goto L_0x05a5
        L_0x0595:
            r20 = r4
            r29 = r5
            r4 = r26
            r0 = r28
            r28 = -2
            r5 = r2
            r26 = r3
        L_0x05a2:
            r1 = r0
            r0 = r4
            r2 = r5
        L_0x05a5:
            int r3 = r26 + 1
            r4 = r20
            r5 = r29
            r11 = 4
            goto L_0x03f8
        L_0x05ae:
            r20 = r4
            r29 = r5
            r4 = r0
            r0 = r1
            r5 = r2
            int r1 = r6.f16185e
            if (r1 <= 0) goto L_0x05c6
            boolean r1 = r6.mo10440b(r8)
            if (r1 == 0) goto L_0x05c6
            int r1 = r6.f16185e
            int r2 = r6.f16188h
            int r1 = r1 + r2
            r6.f16185e = r1
        L_0x05c6:
            r1 = 1
            r2 = r12[r1]
            r1 = -1
            if (r2 != r1) goto L_0x05e0
            r3 = 0
            r11 = r12[r3]
            if (r11 != r1) goto L_0x05e0
            r3 = 2
            r11 = r12[r3]
            if (r11 != r1) goto L_0x05e0
            r3 = 3
            r11 = r12[r3]
            if (r11 == r1) goto L_0x05dd
            goto L_0x05e0
        L_0x05dd:
            r35 = r5
            goto L_0x0614
        L_0x05e0:
            r1 = 3
            r3 = r12[r1]
            r11 = 0
            r1 = r12[r11]
            r22 = 2
            r11 = r12[r22]
            int r2 = java.lang.Math.max(r2, r11)
            int r1 = java.lang.Math.max(r1, r2)
            int r1 = java.lang.Math.max(r3, r1)
            r2 = 3
            r3 = r13[r2]
            r2 = 0
            r11 = r13[r2]
            r35 = r5
            r2 = 1
            r5 = r13[r2]
            r2 = r13[r22]
            int r2 = java.lang.Math.max(r5, r2)
            int r2 = java.lang.Math.max(r11, r2)
            int r2 = java.lang.Math.max(r3, r2)
            int r1 = r1 + r2
            int r15 = java.lang.Math.max(r15, r1)
        L_0x0614:
            if (r20 != 0) goto L_0x0617
            goto L_0x064c
        L_0x0617:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r9 == r1) goto L_0x061d
            if (r9 != 0) goto L_0x064c
        L_0x061d:
            r1 = 0
            r6.f16185e = r1
            r1 = 0
        L_0x0622:
            if (r1 >= r8) goto L_0x064c
            android.view.View r2 = r6.getChildAt(r1)
            if (r2 != 0) goto L_0x062b
            goto L_0x0649
        L_0x062b:
            int r3 = r2.getVisibility()
            r5 = 8
            if (r3 == r5) goto L_0x0649
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            vz r2 = (p000.C0599vz) r2
            int r3 = r6.f16185e
            int r5 = r3 + r14
            int r11 = r2.leftMargin
            int r5 = r5 + r11
            int r2 = r2.rightMargin
            int r5 = r5 + r2
            int r2 = java.lang.Math.max(r3, r5)
            r6.f16185e = r2
        L_0x0649:
            int r1 = r1 + 1
            goto L_0x0622
        L_0x064c:
            int r1 = r6.f16185e
            int r2 = r38.getPaddingLeft()
            int r3 = r38.getPaddingRight()
            int r2 = r2 + r3
            int r1 = r1 + r2
            r6.f16185e = r1
            int r2 = r38.getSuggestedMinimumWidth()
            int r1 = java.lang.Math.max(r1, r2)
            r2 = 0
            int r1 = android.view.View.resolveSizeAndState(r1, r7, r2)
            r2 = 16777215(0xffffff, float:2.3509886E-38)
            r2 = r2 & r1
            int r3 = r6.f16185e
            int r2 = r2 - r3
            if (r19 == 0) goto L_0x0672
            r5 = 0
            goto L_0x0682
        L_0x0672:
            if (r2 != 0) goto L_0x067c
            r26 = r1
            r21 = 0
            r1 = r40
            goto L_0x083b
        L_0x067c:
            r5 = 0
            int r11 = (r24 > r5 ? 1 : (r24 == r5 ? 0 : -1))
            if (r11 <= 0) goto L_0x0835
        L_0x0682:
            float r4 = r6.f16189i
            int r11 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r11 <= 0) goto L_0x068a
            r24 = r4
        L_0x068a:
            r4 = 3
            r5 = -1
            r12[r4] = r5
            r11 = 2
            r12[r11] = r5
            r14 = 1
            r12[r14] = r5
            r15 = 0
            r12[r15] = r5
            r13[r4] = r5
            r13[r11] = r5
            r13[r14] = r5
            r13[r15] = r5
            r6.f16185e = r15
            r4 = r35
            r5 = 0
            r11 = -1
        L_0x06a8:
            if (r5 < r8) goto L_0x070c
            int r2 = r6.f16185e
            int r5 = r38.getPaddingLeft()
            int r9 = r38.getPaddingRight()
            int r5 = r5 + r9
            int r2 = r2 + r5
            r6.f16185e = r2
            r2 = 1
            r5 = r12[r2]
            r2 = -1
            if (r5 != r2) goto L_0x06d2
            r9 = 0
            r14 = r12[r9]
            if (r14 != r2) goto L_0x06d2
            r9 = 2
            r14 = r12[r9]
            if (r14 != r2) goto L_0x06d2
            r9 = 3
            r14 = r12[r9]
            if (r14 == r2) goto L_0x06ce
            goto L_0x06d2
        L_0x06ce:
            r15 = r11
            r21 = 0
            goto L_0x0705
        L_0x06d2:
            r19 = 3
            r2 = r12[r19]
            r21 = 0
            r9 = r12[r21]
            r22 = 2
            r12 = r12[r22]
            int r5 = java.lang.Math.max(r5, r12)
            int r5 = java.lang.Math.max(r9, r5)
            int r2 = java.lang.Math.max(r2, r5)
            r5 = r13[r19]
            r9 = r13[r21]
            r12 = 1
            r12 = r13[r12]
            r13 = r13[r22]
            int r12 = java.lang.Math.max(r12, r13)
            int r9 = java.lang.Math.max(r9, r12)
            int r5 = java.lang.Math.max(r5, r9)
            int r2 = r2 + r5
            int r2 = java.lang.Math.max(r11, r2)
            r15 = r2
        L_0x0705:
            r26 = r1
            r2 = r4
            r1 = r40
            goto L_0x087d
        L_0x070c:
            r19 = 3
            r21 = 0
            r22 = 2
            android.view.View r14 = r6.getChildAt(r5)
            if (r14 != 0) goto L_0x0722
            r26 = r1
            r16 = 1
            r25 = 4
            r1 = r40
            goto L_0x082c
        L_0x0722:
            int r15 = r14.getVisibility()
            r3 = 8
            if (r15 == r3) goto L_0x0824
            android.view.ViewGroup$LayoutParams r3 = r14.getLayoutParams()
            vz r3 = (p000.C0599vz) r3
            float r15 = r3.f16179g
            r20 = 0
            int r26 = (r15 > r20 ? 1 : (r15 == r20 ? 0 : -1))
            if (r26 <= 0) goto L_0x0792
            float r7 = (float) r2
            float r7 = r7 * r15
            float r7 = r7 / r24
            int r7 = (int) r7
            float r24 = r24 - r15
            int r2 = r2 - r7
            int r15 = r38.getPaddingTop()
            int r20 = r38.getPaddingBottom()
            int r15 = r15 + r20
            r20 = r2
            int r2 = r3.topMargin
            int r15 = r15 + r2
            int r2 = r3.bottomMargin
            int r15 = r15 + r2
            int r2 = r3.height
            r26 = r1
            r1 = r40
            int r2 = getChildMeasureSpec(r1, r15, r2)
            int r15 = r3.width
            if (r15 != 0) goto L_0x0771
            r15 = 1073741824(0x40000000, float:2.0)
            if (r9 != r15) goto L_0x0771
            if (r7 <= 0) goto L_0x0768
            goto L_0x0769
        L_0x0768:
            r7 = 0
        L_0x0769:
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r15)
            r14.measure(r7, r2)
            goto L_0x0784
        L_0x0771:
            int r15 = r14.getMeasuredWidth()
            int r7 = r7 + r15
            if (r7 < 0) goto L_0x0779
            goto L_0x077b
        L_0x0779:
            r7 = 0
        L_0x077b:
            r15 = 1073741824(0x40000000, float:2.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r15)
            r14.measure(r7, r2)
        L_0x0784:
            int r2 = r14.getMeasuredState()
            r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2 = r2 & r7
            int r4 = android.view.View.combineMeasuredStates(r4, r2)
            r2 = r20
            goto L_0x0796
        L_0x0792:
            r26 = r1
            r1 = r40
        L_0x0796:
            r7 = 1073741824(0x40000000, float:2.0)
            if (r9 == r7) goto L_0x07b0
            int r7 = r6.f16185e
            int r15 = r14.getMeasuredWidth()
            int r15 = r15 + r7
            r20 = r2
            int r2 = r3.leftMargin
            int r15 = r15 + r2
            int r2 = r3.rightMargin
            int r15 = r15 + r2
            int r2 = java.lang.Math.max(r7, r15)
            r6.f16185e = r2
            goto L_0x07c1
        L_0x07b0:
            r20 = r2
            int r2 = r6.f16185e
            int r7 = r14.getMeasuredWidth()
            int r15 = r3.leftMargin
            int r7 = r7 + r15
            int r15 = r3.rightMargin
            int r7 = r7 + r15
            int r2 = r2 + r7
            r6.f16185e = r2
        L_0x07c1:
            r2 = 1073741824(0x40000000, float:2.0)
            if (r10 == r2) goto L_0x07cd
            int r2 = r3.height
            r7 = -1
            if (r2 != r7) goto L_0x07cc
            r2 = 1
            goto L_0x07ce
        L_0x07cc:
        L_0x07cd:
            r2 = 0
        L_0x07ce:
            int r7 = r3.topMargin
            int r15 = r3.bottomMargin
            int r7 = r7 + r15
            int r15 = r14.getMeasuredHeight()
            int r15 = r15 + r7
            int r11 = java.lang.Math.max(r11, r15)
            if (r2 != 0) goto L_0x07df
            r7 = r15
        L_0x07df:
            int r0 = java.lang.Math.max(r0, r7)
            if (r17 == 0) goto L_0x07ed
            int r2 = r3.height
            r7 = -1
            if (r2 != r7) goto L_0x07ec
            r2 = 1
            goto L_0x07ee
        L_0x07ec:
        L_0x07ed:
            r2 = 0
        L_0x07ee:
            if (r29 == 0) goto L_0x081b
            int r7 = r14.getBaseline()
            r14 = -1
            if (r7 == r14) goto L_0x081b
            int r3 = r3.f16180h
            if (r3 < 0) goto L_0x07fc
            goto L_0x07fe
        L_0x07fc:
            int r3 = r6.f16186f
        L_0x07fe:
            r3 = r3 & 112(0x70, float:1.57E-43)
            r25 = 4
            int r3 = r3 >> 4
            r16 = 1
            int r3 = r3 >> 1
            r14 = r12[r3]
            int r14 = java.lang.Math.max(r14, r7)
            r12[r3] = r14
            r14 = r13[r3]
            int r15 = r15 - r7
            int r7 = java.lang.Math.max(r14, r15)
            r13[r3] = r7
            goto L_0x081f
        L_0x081b:
            r16 = 1
            r25 = 4
        L_0x081f:
            r17 = r2
            r2 = r20
            goto L_0x082c
        L_0x0824:
            r26 = r1
            r16 = 1
            r25 = 4
            r1 = r40
        L_0x082c:
            int r5 = r5 + 1
            r7 = r39
            r1 = r26
            goto L_0x06a8
        L_0x0835:
            r26 = r1
            r21 = 0
            r1 = r40
        L_0x083b:
            int r0 = java.lang.Math.max(r0, r4)
            if (r20 == 0) goto L_0x087b
            r2 = 1073741824(0x40000000, float:2.0)
            if (r9 == r2) goto L_0x087b
            r4 = 0
        L_0x0846:
            if (r4 >= r8) goto L_0x087b
            android.view.View r2 = r6.getChildAt(r4)
            if (r2 != 0) goto L_0x0850
            r5 = 0
            goto L_0x0878
        L_0x0850:
            int r3 = r2.getVisibility()
            r5 = 8
            if (r3 == r5) goto L_0x0877
            android.view.ViewGroup$LayoutParams r3 = r2.getLayoutParams()
            vz r3 = (p000.C0599vz) r3
            float r3 = r3.f16179g
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x0878
            r3 = 1073741824(0x40000000, float:2.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r3)
            int r9 = r2.getMeasuredHeight()
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r3)
            r2.measure(r7, r9)
            goto L_0x0878
        L_0x0877:
            r5 = 0
        L_0x0878:
            int r4 = r4 + 1
            goto L_0x0846
        L_0x087b:
            r2 = r35
        L_0x087d:
            if (r17 == 0) goto L_0x0880
            goto L_0x0885
        L_0x0880:
            r3 = 1073741824(0x40000000, float:2.0)
            if (r10 == r3) goto L_0x0885
            goto L_0x0886
        L_0x0885:
            r0 = r15
        L_0x0886:
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3 = r3 & r2
            r3 = r26 | r3
            int r4 = r38.getPaddingTop()
            int r5 = r38.getPaddingBottom()
            int r4 = r4 + r5
            int r0 = r0 + r4
            int r4 = r38.getSuggestedMinimumHeight()
            int r0 = java.lang.Math.max(r0, r4)
            int r2 = r2 << 16
            int r0 = android.view.View.resolveSizeAndState(r0, r1, r2)
            r6.setMeasuredDimension(r3, r0)
            if (r18 == 0) goto L_0x08e6
            int r0 = r38.getMeasuredHeight()
            r1 = 1073741824(0x40000000, float:2.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            r9 = 0
        L_0x08b3:
            if (r9 >= r8) goto L_0x08e6
            android.view.View r1 = r6.getChildAt(r9)
            int r0 = r1.getVisibility()
            r10 = 8
            if (r0 == r10) goto L_0x08e2
            android.view.ViewGroup$LayoutParams r0 = r1.getLayoutParams()
            r11 = r0
            vz r11 = (p000.C0599vz) r11
            int r0 = r11.height
            r12 = -1
            if (r0 != r12) goto L_0x08e3
            int r13 = r11.width
            int r0 = r1.getMeasuredWidth()
            r11.width = r0
            r3 = 0
            r5 = 0
            r0 = r38
            r2 = r39
            r4 = r7
            r0.measureChildWithMargins(r1, r2, r3, r4, r5)
            r11.width = r13
            goto L_0x08e3
        L_0x08e2:
            r12 = -1
        L_0x08e3:
            int r9 = r9 + 1
            goto L_0x08b3
        L_0x08e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0601wa.onMeasure(int, int):void");
    }

    /* renamed from: e */
    public final void mo10442e() {
        this.f16181a = false;
    }

    /* renamed from: a */
    private static void m15681a(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    /* renamed from: c */
    public final void mo10441c(int i) {
        if (this.f16184d != i) {
            this.f16184d = i;
            requestLayout();
        }
    }
}
