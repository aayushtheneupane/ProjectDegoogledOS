package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
public class FlexboxLayout extends ViewGroup implements eik {

    /* renamed from: a */
    private int f4939a;

    /* renamed from: b */
    private int f4940b;

    /* renamed from: c */
    private int f4941c;

    /* renamed from: d */
    private int f4942d;

    /* renamed from: e */
    private int f4943e;

    /* renamed from: f */
    private int f4944f;

    /* renamed from: g */
    private Drawable f4945g;

    /* renamed from: h */
    private Drawable f4946h;

    /* renamed from: i */
    private int f4947i;

    /* renamed from: j */
    private int f4948j;

    /* renamed from: k */
    private int f4949k;

    /* renamed from: l */
    private int f4950l;

    /* renamed from: m */
    private int[] f4951m;

    /* renamed from: n */
    private SparseIntArray f4952n;

    /* renamed from: o */
    private eip f4953o;

    /* renamed from: p */
    private List f4954p;

    /* renamed from: q */
    private ein f4955q;

    public FlexboxLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: b */
    public final int mo3469b() {
        return this.f4939a;
    }

    /* renamed from: c */
    public final int mo3472c() {
        return this.f4940b;
    }

    /* renamed from: d */
    public final int mo3474d() {
        return this.f4943e;
    }

    /* renamed from: e */
    public final int mo3475e() {
        return this.f4942d;
    }

    /* renamed from: f */
    public final boolean mo3476f() {
        int i = this.f4939a;
        return i == 0 || i == 1;
    }

    /* renamed from: i */
    public final int mo3481i() {
        return this.f4944f;
    }

    /* renamed from: j */
    public final List mo3482j() {
        return this.f4954p;
    }

    /* renamed from: k */
    public final int mo3483k() {
        return 0;
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4944f = -1;
        this.f4953o = new eip(this);
        this.f4954p = new ArrayList();
        this.f4955q = new ein();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, eis.f8378a, i, 0);
        this.f4939a = obtainStyledAttributes.getInt(5, 0);
        this.f4940b = obtainStyledAttributes.getInt(6, 0);
        this.f4941c = obtainStyledAttributes.getInt(7, 0);
        this.f4942d = obtainStyledAttributes.getInt(1, 4);
        this.f4943e = obtainStyledAttributes.getInt(0, 5);
        this.f4944f = obtainStyledAttributes.getInt(8, -1);
        Drawable drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            m4908a(drawable);
            m4913b(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(3);
        if (drawable2 != null) {
            m4908a(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(4);
        if (drawable3 != null) {
            m4913b(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(9, 0);
        if (i2 != 0) {
            this.f4948j = i2;
            this.f4947i = i2;
        }
        int i3 = obtainStyledAttributes.getInt(11, 0);
        if (i3 != 0) {
            this.f4948j = i3;
        }
        int i4 = obtainStyledAttributes.getInt(10, 0);
        if (i4 != 0) {
            this.f4947i = i4;
        }
        obtainStyledAttributes.recycle();
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.f4952n == null) {
            this.f4952n = new SparseIntArray(getChildCount());
        }
        eip eip = this.f4953o;
        SparseIntArray sparseIntArray = this.f4952n;
        int a = eip.f8366a.mo3461a();
        List a2 = eip.mo4848a(a);
        eio eio = new eio((byte[]) null);
        if (view == null || !(layoutParams instanceof eil)) {
            eio.f8365b = 1;
        } else {
            eio.f8365b = ((eil) layoutParams).mo4829c();
        }
        if (i == -1 || i == a) {
            eio.f8364a = a;
        } else if (i >= eip.f8366a.mo3461a()) {
            eio.f8364a = a;
        } else {
            eio.f8364a = i;
            for (int i2 = i; i2 < a; i2++) {
                ((eio) a2.get(i2)).f8364a++;
            }
        }
        a2.add(eio);
        this.f4951m = eip.m7582a(a + 1, a2, sparseIntArray);
        super.addView(view, i, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof eir;
    }

    /* renamed from: a */
    private final void m4907a(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.f4954p.size();
        for (int i3 = 0; i3 < size; i3++) {
            eim eim = (eim) this.f4954p.get(i3);
            for (int i4 = 0; i4 < eim.f8354h; i4++) {
                int i5 = eim.f8361o + i4;
                View c = m4915c(i5);
                if (!(c == null || c.getVisibility() == 8)) {
                    eir eir = (eir) c.getLayoutParams();
                    if (m4914b(i5, i4)) {
                        if (!z) {
                            i2 = (c.getLeft() - eir.leftMargin) - this.f4950l;
                        } else {
                            i2 = c.getRight() + eir.rightMargin;
                        }
                        m4906a(canvas, i2, eim.f8348b, eim.f8353g);
                    }
                    if (i4 == eim.f8354h - 1 && (this.f4948j & 4) > 0) {
                        m4906a(canvas, z ? (c.getLeft() - eir.leftMargin) - this.f4950l : c.getRight() + eir.rightMargin, eim.f8348b, eim.f8353g);
                    }
                }
            }
            if (m4916d(i3)) {
                if (!z2) {
                    i = eim.f8348b - this.f4949k;
                } else {
                    i = eim.f8350d;
                }
                m4911b(canvas, paddingLeft, i, max);
            }
            if (m4917e(i3) && (this.f4947i & 4) > 0) {
                m4911b(canvas, paddingLeft, z2 ? eim.f8348b - this.f4949k : eim.f8350d, max);
            }
        }
    }

    /* renamed from: b */
    private final void m4912b(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.f4954p.size();
        for (int i3 = 0; i3 < size; i3++) {
            eim eim = (eim) this.f4954p.get(i3);
            for (int i4 = 0; i4 < eim.f8354h; i4++) {
                int i5 = eim.f8361o + i4;
                View c = m4915c(i5);
                if (!(c == null || c.getVisibility() == 8)) {
                    eir eir = (eir) c.getLayoutParams();
                    if (m4914b(i5, i4)) {
                        if (!z2) {
                            i2 = (c.getTop() - eir.topMargin) - this.f4949k;
                        } else {
                            i2 = c.getBottom() + eir.bottomMargin;
                        }
                        m4911b(canvas, eim.f8347a, i2, eim.f8353g);
                    }
                    if (i4 == eim.f8354h - 1 && (this.f4947i & 4) > 0) {
                        m4911b(canvas, eim.f8347a, z2 ? (c.getTop() - eir.topMargin) - this.f4949k : c.getBottom() + eir.bottomMargin, eim.f8353g);
                    }
                }
            }
            if (m4916d(i3)) {
                if (!z) {
                    i = eim.f8347a - this.f4950l;
                } else {
                    i = eim.f8349c;
                }
                m4906a(canvas, i, paddingTop, max);
            }
            if (m4917e(i3) && (this.f4948j & 4) > 0) {
                m4906a(canvas, z ? eim.f8347a - this.f4950l : eim.f8349c, paddingTop, max);
            }
        }
    }

    /* renamed from: b */
    private final void m4911b(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.f4945g;
        if (drawable != null) {
            drawable.setBounds(i, i2, i3 + i, this.f4949k + i2);
            this.f4945g.draw(canvas);
        }
    }

    /* renamed from: a */
    private final void m4906a(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.f4946h;
        if (drawable != null) {
            drawable.setBounds(i, i2, this.f4950l + i, i3 + i2);
            this.f4946h.draw(canvas);
        }
    }

    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new eir(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof eir) {
            return new eir((eir) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new eir((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new eir(layoutParams);
    }

    /* renamed from: b */
    public final int mo3470b(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    /* renamed from: a */
    public final int mo3463a(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    /* renamed from: a */
    public final int mo3462a(int i, int i2) {
        int i3 = 0;
        if (mo3476f()) {
            if (m4914b(i, i2)) {
                i3 = this.f4950l;
            }
            if ((this.f4948j & 4) > 0) {
                return i3 + this.f4950l;
            }
        } else {
            if (m4914b(i, i2)) {
                i3 = this.f4949k;
            }
            if ((this.f4947i & 4) > 0) {
                return i3 + this.f4949k;
            }
        }
        return i3;
    }

    /* renamed from: a */
    public final View mo3464a(int i) {
        return getChildAt(i);
    }

    /* renamed from: a */
    public final int mo3461a() {
        return getChildCount();
    }

    /* renamed from: g */
    public final int mo3477g() {
        List list = this.f4954p;
        int size = list.size();
        int i = RecyclerView.UNDEFINED_DURATION;
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, ((eim) list.get(i2)).f8351e);
        }
        return i;
    }

    /* renamed from: c */
    private final View m4915c(int i) {
        if (i < 0) {
            return null;
        }
        int[] iArr = this.f4951m;
        if (i < iArr.length) {
            return getChildAt(iArr[i]);
        }
        return null;
    }

    /* renamed from: b */
    public final View mo3471b(int i) {
        return m4915c(i);
    }

    /* renamed from: h */
    public final int mo3480h() {
        int size = this.f4954p.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            eim eim = (eim) this.f4954p.get(i2);
            if (m4916d(i2)) {
                if (!mo3476f()) {
                    i += this.f4950l;
                } else {
                    i += this.f4949k;
                }
            }
            if (m4917e(i2)) {
                if (!mo3476f()) {
                    i += this.f4950l;
                } else {
                    i += this.f4949k;
                }
            }
            i += eim.f8353g;
        }
        return i;
    }

    /* renamed from: b */
    private final boolean m4914b(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View c = m4915c(i - i3);
            if (c != null && c.getVisibility() != 8) {
                return mo3476f() ? (this.f4948j & 2) != 0 : (this.f4947i & 2) != 0;
            }
        }
        return mo3476f() ? (this.f4948j & 1) != 0 : (this.f4947i & 1) != 0;
    }

    /* renamed from: d */
    private final boolean m4916d(int i) {
        if (i >= 0 && i < this.f4954p.size()) {
            for (int i2 = 0; i2 < i; i2++) {
                if (((eim) this.f4954p.get(i2)).mo4843a() > 0) {
                    return mo3476f() ? (this.f4947i & 2) != 0 : (this.f4948j & 2) != 0;
                }
            }
            if (mo3476f()) {
                return (this.f4947i & 1) != 0;
            }
            if ((this.f4948j & 1) != 0) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    private final boolean m4917e(int i) {
        if (i < 0 || i >= this.f4954p.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.f4954p.size(); i2++) {
            if (((eim) this.f4954p.get(i2)).mo4843a() > 0) {
                return false;
            }
        }
        return mo3476f() ? (this.f4947i & 4) != 0 : (this.f4948j & 4) != 0;
    }

    /* renamed from: a */
    private final void m4909a(boolean z, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        int i5;
        int i6;
        int i7;
        float f4;
        int i8;
        eir eir;
        float f5;
        float f6;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i9 = i3 - i;
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        int paddingTop = getPaddingTop();
        int size = this.f4954p.size();
        int i10 = 0;
        while (i10 < size) {
            eim eim = (eim) this.f4954p.get(i10);
            if (m4916d(i10)) {
                int i11 = this.f4949k;
                paddingBottom -= i11;
                paddingTop += i11;
            }
            int i12 = this.f4941c;
            int i13 = 4;
            if (i12 != 0) {
                if (i12 == 1) {
                    int i14 = eim.f8351e;
                    f5 = (float) ((i9 - i14) + paddingRight);
                    f = (float) (i14 - paddingLeft);
                } else if (i12 == 2) {
                    float f7 = ((float) (i9 - eim.f8351e)) / 2.0f;
                    f5 = ((float) paddingLeft) + f7;
                    f = ((float) (i9 - paddingRight)) - f7;
                } else if (i12 == 3) {
                    f3 = (float) paddingLeft;
                    int a = eim.mo4843a();
                    if (a != 1) {
                        f6 = (float) (a - 1);
                    } else {
                        f6 = 1.0f;
                    }
                    f2 = ((float) (i9 - eim.f8351e)) / f6;
                    f = (float) (i9 - paddingRight);
                } else if (i12 == 4) {
                    int a2 = eim.mo4843a();
                    f2 = a2 != 0 ? ((float) (i9 - eim.f8351e)) / ((float) a2) : 0.0f;
                    float f8 = f2 / 2.0f;
                    f = ((float) (i9 - paddingRight)) - f8;
                    f3 = ((float) paddingLeft) + f8;
                } else if (i12 == 5) {
                    int a3 = eim.mo4843a();
                    f2 = a3 != 0 ? ((float) (i9 - eim.f8351e)) / ((float) (a3 + 1)) : 0.0f;
                    f3 = ((float) paddingLeft) + f2;
                    f = ((float) (i9 - paddingRight)) - f2;
                } else {
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.f4941c);
                }
                f3 = f5;
                f2 = 0.0f;
            } else {
                f3 = (float) paddingLeft;
                f = (float) (i9 - paddingRight);
                f2 = 0.0f;
            }
            float max = Math.max(f2, 0.0f);
            int i15 = 0;
            while (i15 < eim.f8354h) {
                int i16 = eim.f8361o + i15;
                View c = m4915c(i16);
                if (c == null || c.getVisibility() == 8) {
                    i5 = paddingLeft;
                    i6 = i15;
                } else {
                    eir eir2 = (eir) c.getLayoutParams();
                    float f9 = f3 + ((float) eir2.leftMargin);
                    float f10 = f - ((float) eir2.rightMargin);
                    if (m4914b(i16, i15)) {
                        int i17 = this.f4950l;
                        float f11 = (float) i17;
                        f9 += f11;
                        i7 = i17;
                        f4 = f10 - f11;
                    } else {
                        f4 = f10;
                        i7 = 0;
                    }
                    if (i15 != eim.f8354h - 1 || (this.f4948j & i13) <= 0) {
                        i8 = 0;
                    } else {
                        i8 = this.f4950l;
                    }
                    if (this.f4940b == 2) {
                        i5 = paddingLeft;
                        i6 = i15;
                        eir = eir2;
                        if (!z) {
                            this.f4953o.mo4852a(c, eim, Math.round(f9), paddingBottom - c.getMeasuredHeight(), Math.round(f9) + c.getMeasuredWidth(), paddingBottom);
                        } else {
                            this.f4953o.mo4852a(c, eim, Math.round(f4) - c.getMeasuredWidth(), paddingBottom - c.getMeasuredHeight(), Math.round(f4), paddingBottom);
                        }
                    } else if (!z) {
                        i6 = i15;
                        i5 = paddingLeft;
                        eir = eir2;
                        this.f4953o.mo4852a(c, eim, Math.round(f9), paddingTop, Math.round(f9) + c.getMeasuredWidth(), paddingTop + c.getMeasuredHeight());
                    } else {
                        i5 = paddingLeft;
                        i6 = i15;
                        eir = eir2;
                        this.f4953o.mo4852a(c, eim, Math.round(f4) - c.getMeasuredWidth(), paddingTop, Math.round(f4), paddingTop + c.getMeasuredHeight());
                    }
                    f3 = f9 + ((float) c.getMeasuredWidth()) + max + ((float) eir.rightMargin);
                    float measuredWidth = f4 - ((((float) c.getMeasuredWidth()) + max) + ((float) eir.leftMargin));
                    if (z) {
                        eim.mo4844a(c, i8, 0, i7, 0);
                    } else {
                        eim.mo4844a(c, i7, 0, i8, 0);
                    }
                    f = measuredWidth;
                }
                i15 = i6 + 1;
                paddingLeft = i5;
                i13 = 4;
            }
            int i18 = paddingLeft;
            int i19 = eim.f8353g;
            paddingTop += i19;
            paddingBottom -= i19;
            i10++;
            paddingLeft = i18;
        }
    }

    /* renamed from: a */
    private final void m4910a(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        int i5;
        int i6;
        float f4;
        float f5;
        int i7;
        float f6;
        float f7;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int i8 = i4 - i2;
        int i9 = (i3 - i) - paddingRight;
        int size = this.f4954p.size();
        for (int i10 = 0; i10 < size; i10++) {
            eim eim = (eim) this.f4954p.get(i10);
            if (m4916d(i10)) {
                int i11 = this.f4950l;
                paddingLeft += i11;
                i9 -= i11;
            }
            int i12 = this.f4941c;
            int i13 = 4;
            if (i12 != 0) {
                if (i12 == 1) {
                    int i14 = eim.f8351e;
                    f6 = (float) ((i8 - i14) + paddingBottom);
                    f = (float) (i14 - paddingTop);
                } else if (i12 == 2) {
                    float f8 = ((float) (i8 - eim.f8351e)) / 2.0f;
                    f6 = ((float) paddingTop) + f8;
                    f = ((float) (i8 - paddingBottom)) - f8;
                } else if (i12 == 3) {
                    f3 = (float) paddingTop;
                    int a = eim.mo4843a();
                    if (a != 1) {
                        f7 = (float) (a - 1);
                    } else {
                        f7 = 1.0f;
                    }
                    f2 = ((float) (i8 - eim.f8351e)) / f7;
                    f = (float) (i8 - paddingBottom);
                } else if (i12 == 4) {
                    int a2 = eim.mo4843a();
                    f2 = a2 != 0 ? ((float) (i8 - eim.f8351e)) / ((float) a2) : 0.0f;
                    float f9 = f2 / 2.0f;
                    f = ((float) (i8 - paddingBottom)) - f9;
                    f3 = ((float) paddingTop) + f9;
                } else if (i12 == 5) {
                    int a3 = eim.mo4843a();
                    f2 = a3 != 0 ? ((float) (i8 - eim.f8351e)) / ((float) (a3 + 1)) : 0.0f;
                    f3 = ((float) paddingTop) + f2;
                    f = ((float) (i8 - paddingBottom)) - f2;
                } else {
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.f4941c);
                }
                f3 = f6;
                f2 = 0.0f;
            } else {
                f3 = (float) paddingTop;
                f = (float) (i8 - paddingBottom);
                f2 = 0.0f;
            }
            float max = Math.max(f2, 0.0f);
            int i15 = 0;
            while (i15 < eim.f8354h) {
                int i16 = eim.f8361o + i15;
                View c = m4915c(i16);
                if (c == null || c.getVisibility() == 8) {
                    i5 = i15;
                } else {
                    eir eir = (eir) c.getLayoutParams();
                    float f10 = f3 + ((float) eir.topMargin);
                    float f11 = f - ((float) eir.bottomMargin);
                    if (m4914b(i16, i15)) {
                        int i17 = this.f4949k;
                        float f12 = (float) i17;
                        f5 = f10 + f12;
                        i6 = i17;
                        f4 = f11 - f12;
                    } else {
                        f5 = f10;
                        f4 = f11;
                        i6 = 0;
                    }
                    if (i15 != eim.f8354h - 1 || (this.f4947i & i13) <= 0) {
                        i7 = 0;
                    } else {
                        i7 = this.f4949k;
                    }
                    if (z) {
                        i5 = i15;
                        if (!z2) {
                            this.f4953o.mo4853a(c, eim, true, i9 - c.getMeasuredWidth(), Math.round(f5), i9, Math.round(f5) + c.getMeasuredHeight());
                        } else {
                            this.f4953o.mo4853a(c, eim, true, i9 - c.getMeasuredWidth(), Math.round(f4) - c.getMeasuredHeight(), i9, Math.round(f4));
                        }
                    } else if (!z2) {
                        i5 = i15;
                        this.f4953o.mo4853a(c, eim, false, paddingLeft, Math.round(f5), paddingLeft + c.getMeasuredWidth(), Math.round(f5) + c.getMeasuredHeight());
                    } else {
                        i5 = i15;
                        this.f4953o.mo4853a(c, eim, false, paddingLeft, Math.round(f4) - c.getMeasuredHeight(), paddingLeft + c.getMeasuredWidth(), Math.round(f4));
                    }
                    float measuredHeight = f5 + ((float) c.getMeasuredHeight()) + max + ((float) eir.bottomMargin);
                    float measuredHeight2 = f4 - ((((float) c.getMeasuredHeight()) + max) + ((float) eir.topMargin));
                    if (z2) {
                        eim.mo4844a(c, 0, i7, 0, i6);
                    } else {
                        eim.mo4844a(c, 0, i6, 0, i7);
                    }
                    f3 = measuredHeight;
                    f = measuredHeight2;
                }
                i15 = i5 + 1;
                i13 = 4;
            }
            int i18 = eim.f8353g;
            paddingLeft += i18;
            i9 -= i18;
        }
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        if (this.f4946h != null || this.f4945g != null) {
            if (this.f4947i != 0 || this.f4948j != 0) {
                int f = C0340mj.m14714f(this);
                int i = this.f4939a;
                boolean z = false;
                boolean z2 = true;
                if (i == 0) {
                    boolean z3 = f == 1;
                    if (this.f4940b == 2) {
                        z = true;
                    }
                    m4907a(canvas, z3, z);
                } else if (i == 1) {
                    boolean z4 = f != 1;
                    if (this.f4940b == 2) {
                        z = true;
                    }
                    m4907a(canvas, z4, z);
                } else if (i == 2) {
                    boolean z5 = f != 1;
                    if (f != 1) {
                        z2 = false;
                    }
                    if (this.f4940b != 2) {
                        z5 = z2;
                    }
                    m4912b(canvas, z5, false);
                } else if (i == 3) {
                    boolean z6 = f != 1;
                    if (f == 1) {
                        z = true;
                    }
                    if (this.f4940b != 2) {
                        z6 = z;
                    }
                    m4912b(canvas, z6, true);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int f = C0340mj.m14714f(this);
        int i5 = this.f4939a;
        boolean z2 = false;
        if (i5 == 0) {
            m4909a(f == 1, i, i2, i3, i4);
        } else if (i5 == 1) {
            m4909a(f != 1, i, i2, i3, i4);
        } else if (i5 == 2) {
            boolean z3 = f != 1;
            if (f == 1) {
                z2 = true;
            }
            if (this.f4940b != 2) {
                z3 = z2;
            }
            m4910a(z3, false, i, i2, i3, i4);
        } else if (i5 == 3) {
            boolean z4 = f != 1;
            if (f == 1) {
                z2 = true;
            }
            if (this.f4940b != 2) {
                z4 = z2;
            }
            m4910a(z4, true, i, i2, i3, i4);
        } else {
            throw new IllegalStateException("Invalid flex direction is set: " + this.f4939a);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r17, int r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            android.util.SparseIntArray r3 = r0.f4952n
            if (r3 == 0) goto L_0x000b
            goto L_0x0016
        L_0x000b:
            android.util.SparseIntArray r3 = new android.util.SparseIntArray
            int r4 = r16.getChildCount()
            r3.<init>(r4)
            r0.f4952n = r3
        L_0x0016:
            eip r3 = r0.f4953o
            android.util.SparseIntArray r4 = r0.f4952n
            eik r5 = r3.f8366a
            int r5 = r5.mo3461a()
            int r6 = r4.size()
            r7 = 0
            if (r6 == r5) goto L_0x0028
            goto L_0x0044
        L_0x0028:
            r6 = 0
        L_0x0029:
            if (r6 >= r5) goto L_0x005c
            eik r8 = r3.f8366a
            android.view.View r8 = r8.mo3464a((int) r6)
            if (r8 == 0) goto L_0x0059
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            eil r8 = (p000.eil) r8
            int r8 = r8.mo4829c()
            int r9 = r4.get(r6)
            if (r8 != r9) goto L_0x0044
            goto L_0x0059
        L_0x0044:
            eip r3 = r0.f4953o
            android.util.SparseIntArray r4 = r0.f4952n
            eik r5 = r3.f8366a
            int r5 = r5.mo3461a()
            java.util.List r3 = r3.mo4848a((int) r5)
            int[] r3 = p000.eip.m7582a((int) r5, (java.util.List) r3, (android.util.SparseIntArray) r4)
            r0.f4951m = r3
            goto L_0x005c
        L_0x0059:
            int r6 = r6 + 1
            goto L_0x0029
        L_0x005c:
            int r3 = r0.f4939a
            r4 = 3
            r5 = 2
            if (r3 == 0) goto L_0x00bc
            r6 = 1
            if (r3 == r6) goto L_0x00bc
            if (r3 == r5) goto L_0x0083
            if (r3 != r4) goto L_0x006a
            goto L_0x0083
        L_0x006a:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid value for the flex direction is set: "
            r2.append(r3)
            int r3 = r0.f4939a
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0083:
            java.util.List r3 = r0.f4954p
            r3.clear()
            ein r3 = r0.f4955q
            r3.mo4845a()
            eip r3 = r0.f4953o
            ein r4 = r0.f4955q
            r3.mo4854a((p000.ein) r4, (int) r2, (int) r1)
            ein r3 = r0.f4955q
            java.util.List r3 = r3.f8362a
            r0.f4954p = r3
            eip r3 = r0.f4953o
            r3.mo4850a((int) r1, (int) r2)
            eip r3 = r0.f4953o
            int r4 = r16.getPaddingLeft()
            int r5 = r16.getPaddingRight()
            int r4 = r4 + r5
            r3.mo4851a((int) r1, (int) r2, (int) r4)
            eip r3 = r0.f4953o
            r3.mo4849a()
            int r3 = r0.f4939a
            ein r4 = r0.f4955q
            int r4 = r4.f8363b
            r0.m4905a((int) r3, (int) r1, (int) r2, (int) r4)
            return
        L_0x00bc:
            java.util.List r3 = r0.f4954p
            r3.clear()
            ein r3 = r0.f4955q
            r3.mo4845a()
            eip r3 = r0.f4953o
            ein r6 = r0.f4955q
            r3.mo4854a((p000.ein) r6, (int) r1, (int) r2)
            ein r3 = r0.f4955q
            java.util.List r3 = r3.f8362a
            r0.f4954p = r3
            eip r3 = r0.f4953o
            r3.mo4850a((int) r1, (int) r2)
            int r3 = r0.f4942d
            if (r3 == r4) goto L_0x00de
            goto L_0x0153
        L_0x00de:
            java.util.List r3 = r0.f4954p
            int r4 = r3.size()
            r6 = 0
        L_0x00e5:
            if (r6 >= r4) goto L_0x0153
            java.lang.Object r8 = r3.get(r6)
            eim r8 = (p000.eim) r8
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r10 = 0
        L_0x00f0:
            int r11 = r8.f8354h
            if (r10 >= r11) goto L_0x014d
            int r11 = r8.f8361o
            int r11 = r11 + r10
            android.view.View r11 = r0.m4915c(r11)
            if (r11 != 0) goto L_0x00fe
            goto L_0x0149
        L_0x00fe:
            int r12 = r11.getVisibility()
            r13 = 8
            if (r12 == r13) goto L_0x0149
            android.view.ViewGroup$LayoutParams r12 = r11.getLayoutParams()
            eir r12 = (p000.eir) r12
            int r13 = r0.f4940b
            if (r13 == r5) goto L_0x012a
            int r13 = r8.f8358l
            int r14 = r11.getBaseline()
            int r15 = r12.topMargin
            int r13 = r13 - r14
            int r13 = java.lang.Math.max(r13, r15)
            int r11 = r11.getMeasuredHeight()
            int r11 = r11 + r13
            int r12 = r12.bottomMargin
            int r11 = r11 + r12
            int r9 = java.lang.Math.max(r9, r11)
            goto L_0x0149
        L_0x012a:
            int r13 = r8.f8358l
            int r14 = r11.getMeasuredHeight()
            int r15 = r11.getBaseline()
            int r5 = r12.bottomMargin
            int r13 = r13 - r14
            int r13 = r13 + r15
            int r5 = java.lang.Math.max(r13, r5)
            int r11 = r11.getMeasuredHeight()
            int r12 = r12.topMargin
            int r11 = r11 + r12
            int r11 = r11 + r5
            int r5 = java.lang.Math.max(r9, r11)
            r9 = r5
        L_0x0149:
            int r10 = r10 + 1
            r5 = 2
            goto L_0x00f0
        L_0x014d:
            r8.f8353g = r9
            int r6 = r6 + 1
            r5 = 2
            goto L_0x00e5
        L_0x0153:
            eip r3 = r0.f4953o
            int r4 = r16.getPaddingTop()
            int r5 = r16.getPaddingBottom()
            int r4 = r4 + r5
            r3.mo4851a((int) r1, (int) r2, (int) r4)
            eip r3 = r0.f4953o
            r3.mo4849a()
            int r3 = r0.f4939a
            ein r4 = r0.f4955q
            int r4 = r4.f8363b
            r0.m4905a((int) r3, (int) r1, (int) r2, (int) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.onMeasure(int, int):void");
    }

    /* renamed from: a */
    public final void mo3465a(int i, int i2, eim eim) {
        if (!m4914b(i, i2)) {
            return;
        }
        if (mo3476f()) {
            int i3 = eim.f8351e;
            int i4 = this.f4950l;
            eim.f8351e = i3 + i4;
            eim.f8352f += i4;
            return;
        }
        int i5 = eim.f8351e;
        int i6 = this.f4949k;
        eim.f8351e = i5 + i6;
        eim.f8352f += i6;
    }

    /* renamed from: a */
    public final void mo3466a(eim eim) {
        if (!mo3476f()) {
            if ((this.f4947i & 4) > 0) {
                int i = eim.f8351e;
                int i2 = this.f4949k;
                eim.f8351e = i + i2;
                eim.f8352f += i2;
            }
        } else if ((this.f4948j & 4) > 0) {
            int i3 = eim.f8351e;
            int i4 = this.f4950l;
            eim.f8351e = i3 + i4;
            eim.f8352f += i4;
        }
    }

    /* renamed from: a */
    private final void m4908a(Drawable drawable) {
        if (drawable != this.f4945g) {
            this.f4945g = drawable;
            this.f4949k = drawable.getIntrinsicHeight();
            m4918l();
            requestLayout();
        }
    }

    /* renamed from: b */
    private final void m4913b(Drawable drawable) {
        if (drawable != this.f4946h) {
            this.f4946h = drawable;
            this.f4950l = drawable.getIntrinsicWidth();
            m4918l();
            requestLayout();
        }
    }

    /* renamed from: a */
    public final void mo3467a(List list) {
        this.f4954p = list;
    }

    /* renamed from: a */
    private final void m4905a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (i == 0 || i == 1) {
            i5 = mo3480h() + getPaddingTop() + getPaddingBottom();
            i6 = mo3477g();
        } else if (i == 2 || i == 3) {
            i5 = mo3477g();
            i6 = mo3480h() + getPaddingLeft() + getPaddingRight();
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + i);
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < i6) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            } else {
                size = i6;
            }
            i7 = View.resolveSizeAndState(size, i2, i4);
        } else if (mode == 0) {
            i7 = View.resolveSizeAndState(i6, i2, i4);
        } else if (mode == 1073741824) {
            if (size < i6) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            }
            i7 = View.resolveSizeAndState(size, i2, i4);
        } else {
            throw new IllegalStateException("Unknown width mode is set: " + mode);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < i5) {
                i4 = View.combineMeasuredStates(i4, 256);
            } else {
                size2 = i5;
            }
            i8 = View.resolveSizeAndState(size2, i3, i4);
        } else if (mode2 == 0) {
            i8 = View.resolveSizeAndState(i5, i3, i4);
        } else if (mode2 == 1073741824) {
            if (size2 < i5) {
                i4 = View.combineMeasuredStates(i4, 256);
            }
            i8 = View.resolveSizeAndState(size2, i3, i4);
        } else {
            throw new IllegalStateException("Unknown height mode is set: " + mode2);
        }
        setMeasuredDimension(i7, i8);
    }

    /* renamed from: l */
    private final void m4918l() {
        if (this.f4945g == null && this.f4946h == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }
}
