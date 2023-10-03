package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
public class CoordinatorLayout extends ViewGroup implements C0326lw, C0327lx {

    /* renamed from: f */
    private static final String f1044f;

    /* renamed from: g */
    private static final Class[] f1045g = {Context.class, AttributeSet.class};

    /* renamed from: h */
    private static final ThreadLocal f1046h = new ThreadLocal();

    /* renamed from: i */
    private static final Comparator f1047i = new abq();

    /* renamed from: j */
    private static final C0306lc f1048j = new C0308le(12);

    /* renamed from: a */
    public final abr f1049a;

    /* renamed from: b */
    public final List f1050b;

    /* renamed from: c */
    public C0348mr f1051c;

    /* renamed from: d */
    public boolean f1052d;

    /* renamed from: e */
    public ViewGroup.OnHierarchyChangeListener f1053e;

    /* renamed from: k */
    private final List f1054k;

    /* renamed from: l */
    private final List f1055l;

    /* renamed from: m */
    private final int[] f1056m;

    /* renamed from: n */
    private final int[] f1057n;

    /* renamed from: o */
    private boolean f1058o;

    /* renamed from: p */
    private boolean f1059p;

    /* renamed from: q */
    private int[] f1060q;

    /* renamed from: r */
    private View f1061r;

    /* renamed from: s */
    private View f1062s;

    /* renamed from: t */
    private abn f1063t;

    /* renamed from: u */
    private boolean f1064u;

    /* renamed from: v */
    private Drawable f1065v;

    /* renamed from: w */
    private C0329lz f1066w;

    /* renamed from: x */
    private final C0328ly f1067x;

    static {
        String str;
        Package packageR = CoordinatorLayout.class.getPackage();
        if (packageR != null) {
            str = packageR.getName();
        } else {
            str = null;
        }
        f1044f = str;
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: c */
    private static int m970c(int i) {
        if ((i & 7) == 0) {
            i |= 8388611;
        }
        return (i & 112) == 0 ? i | 48 : i;
    }

    /* renamed from: d */
    private static int m973d(int i) {
        if (i == 0) {
            return 8388661;
        }
        return i;
    }

    public CoordinatorLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArray;
        this.f1054k = new ArrayList();
        this.f1049a = new abr();
        this.f1055l = new ArrayList();
        this.f1050b = new ArrayList();
        this.f1056m = new int[2];
        this.f1057n = new int[2];
        this.f1067x = new C0328ly();
        if (i == 0) {
            typedArray = context.obtainStyledAttributes(attributeSet, abg.f76a, 0, 2131952630);
        } else {
            typedArray = context.obtainStyledAttributes(attributeSet, abg.f76a, i, 0);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (i != 0) {
                saveAttributeDataForStyleable(context, abg.f76a, attributeSet, typedArray, i, 0);
            } else {
                saveAttributeDataForStyleable(context, abg.f76a, attributeSet, typedArray, 0, 2131952630);
            }
        }
        int resourceId = typedArray.getResourceId(0, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.f1060q = resources.getIntArray(resourceId);
            float f = resources.getDisplayMetrics().density;
            int length = this.f1060q.length;
            for (int i2 = 0; i2 < length; i2++) {
                int[] iArr = this.f1060q;
                iArr[i2] = (int) (((float) iArr[i2]) * f);
            }
        }
        this.f1065v = typedArray.getDrawable(1);
        typedArray.recycle();
        m971c();
        super.setOnHierarchyChangeListener(new abl(this));
        if (C0340mj.m14712e(this) == 0) {
            C0340mj.m14689a((View) this, 1);
        }
    }

    /* renamed from: a */
    private static Rect m959a() {
        Rect rect = (Rect) f1048j.mo1971a();
        return rect == null ? new Rect() : rect;
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof abm) && super.checkLayoutParams(layoutParams);
    }

    /* renamed from: a */
    private final void m962a(abm abm, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + abm.leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - abm.rightMargin));
        int max2 = Math.max(getPaddingTop() + abm.topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - abm.bottomMargin));
        rect.set(max, max2, i + max, i2 + max2);
    }

    /* access modifiers changed from: protected */
    public final boolean drawChild(Canvas canvas, View view, long j) {
        abm abm = (abm) view.getLayoutParams();
        return super.drawChild(canvas, view, j);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1065v;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new abm();
    }

    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new abm(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof abm) {
            return new abm((abm) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new abm((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new abm(layoutParams);
    }

    /* renamed from: a */
    private final void m964a(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (!z) {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            abs.m162a((ViewGroup) this, view, rect);
        }
    }

    /* renamed from: a */
    public final List mo1119a(View view) {
        abr abr = this.f1049a;
        int i = abr.f100b.f15193b;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList2 = (ArrayList) abr.f100b.mo9321c(i2);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(abr.f100b.mo9320b(i2));
            }
        }
        this.f1050b.clear();
        if (arrayList != null) {
            this.f1050b.addAll(arrayList);
        }
        return this.f1050b;
    }

    /* renamed from: a */
    private static final void m961a(int i, Rect rect, Rect rect2, abm abm, int i2, int i3) {
        int i4;
        int i5;
        int i6 = abm.f82c;
        if (i6 == 0) {
            i6 = 17;
        }
        int a = C0321lr.m14621a(i6, i);
        int a2 = C0321lr.m14621a(m970c(abm.f83d), i);
        int i7 = a & 7;
        int i8 = a & 112;
        int i9 = a2 & 112;
        int i10 = a2 & 7;
        if (i10 == 1) {
            i4 = rect.left + (rect.width() / 2);
        } else if (i10 != 5) {
            i4 = rect.left;
        } else {
            i4 = rect.right;
        }
        if (i9 == 16) {
            i5 = (rect.height() / 2) + rect.top;
        } else if (i9 != 80) {
            i5 = rect.top;
        } else {
            i5 = rect.bottom;
        }
        if (i7 == 1) {
            i4 -= i2 / 2;
        } else if (i7 != 5) {
            i4 -= i2;
        }
        if (i8 == 16) {
            i5 -= i3 / 2;
        } else if (i8 != 80) {
            i5 -= i3;
        }
        rect2.set(i4, i5, i2 + i4, i3 + i5);
    }

    /* renamed from: b */
    private final int m967b(int i) {
        int[] iArr = this.f1060q;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i);
            return 0;
        } else if (i >= 0 && i < iArr.length) {
            return iArr[i];
        } else {
            Log.e("CoordinatorLayout", "Keyline index " + i + " out of range for " + this);
            return 0;
        }
    }

    public final int getNestedScrollAxes() {
        return this.f1067x.mo9379a();
    }

    /* renamed from: b */
    private static final abm m968b(View view) {
        abm abm = (abm) view.getLayoutParams();
        if (!abm.f81b) {
            if (view instanceof abi) {
                abm.mo103a(((abi) view).mo80a());
                abm.f81b = true;
            } else {
                abk abk = null;
                for (Class cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    abk = (abk) cls.getAnnotation(abk.class);
                    if (abk != null) {
                        break;
                    }
                }
                if (abk != null) {
                    try {
                        abm.mo103a((abj) abk.mo98a().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e) {
                        Log.e("CoordinatorLayout", "Default behavior class " + abk.mo98a().getName() + " could not be instantiated. Did you forget a default constructor?", e);
                    }
                }
                abm.f81b = true;
            }
        }
        return abm;
    }

    /* access modifiers changed from: protected */
    public final int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public final int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    /* renamed from: a */
    public final boolean mo1122a(View view, int i, int i2) {
        Rect a = m959a();
        abs.m162a((ViewGroup) this, view, a);
        try {
            return a.contains(i, i2);
        } finally {
            m963a(a);
        }
    }

    /* renamed from: a */
    private static final MotionEvent m960a(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        return obtain;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        m969b();
        if (this.f1064u) {
            if (this.f1063t == null) {
                this.f1063t = new abn(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1063t);
        }
        if (this.f1051c == null && C0340mj.m14725p(this)) {
            C0340mj.m14724o(this);
        }
        this.f1059p = true;
    }

    /* renamed from: a */
    public final void mo1120a(int i) {
        int i2;
        Rect rect;
        int i3;
        boolean z;
        boolean z2;
        int width;
        int i4;
        int height;
        int i5;
        abm abm;
        int i6;
        Rect rect2;
        int i7;
        int i8;
        abj abj;
        int i9 = i;
        int f = C0340mj.m14714f(this);
        int size = this.f1054k.size();
        Rect a = m959a();
        Rect a2 = m959a();
        Rect a3 = m959a();
        int i10 = 0;
        while (i10 < size) {
            View view = (View) this.f1054k.get(i10);
            abm abm2 = (abm) view.getLayoutParams();
            if (i9 != 0 || view.getVisibility() != 8) {
                int i11 = 0;
                while (i11 < i10) {
                    if (abm2.f91l == ((View) this.f1054k.get(i11))) {
                        abm abm3 = (abm) view.getLayoutParams();
                        if (abm3.f90k != null) {
                            Rect a4 = m959a();
                            Rect a5 = m959a();
                            Rect a6 = m959a();
                            abs.m162a((ViewGroup) this, abm3.f90k, a4);
                            m964a(view, false, a5);
                            int measuredWidth = view.getMeasuredWidth();
                            i8 = size;
                            int measuredHeight = view.getMeasuredHeight();
                            i7 = i10;
                            Rect rect3 = a5;
                            Rect rect4 = a4;
                            abm abm4 = abm3;
                            int i12 = measuredWidth;
                            rect2 = a3;
                            i6 = i11;
                            abm = abm2;
                            m961a(f, a4, a6, abm4, i12, measuredHeight);
                            boolean z3 = (a6.left == rect3.left && a6.top == rect3.top) ? false : true;
                            abm abm5 = abm4;
                            m962a(abm5, a6, i12, measuredHeight);
                            int i13 = a6.left - rect3.left;
                            int i14 = a6.top - rect3.top;
                            if (i13 != 0) {
                                C0340mj.m14711d(view, i13);
                            }
                            if (i14 != 0) {
                                C0340mj.m14708c(view, i14);
                            }
                            if (z3 && (abj = abm5.f80a) != null) {
                                abj.mo92a(this, view, abm5.f90k);
                            }
                            m963a(rect4);
                            m963a(rect3);
                            m963a(a6);
                        } else {
                            i6 = i11;
                            abm = abm2;
                            i8 = size;
                            rect2 = a3;
                            i7 = i10;
                        }
                    } else {
                        i6 = i11;
                        abm = abm2;
                        i8 = size;
                        rect2 = a3;
                        i7 = i10;
                    }
                    i11 = i6 + 1;
                    size = i8;
                    i10 = i7;
                    a3 = rect2;
                    abm2 = abm;
                }
                int i15 = size;
                Rect rect5 = a3;
                i2 = i10;
                m964a(view, true, a2);
                abm abm6 = abm2;
                if (abm6.f86g != 0 && !a2.isEmpty()) {
                    int a7 = C0321lr.m14621a(abm6.f86g, f);
                    int i16 = a7 & 112;
                    if (i16 == 48) {
                        a.top = Math.max(a.top, a2.bottom);
                    } else if (i16 == 80) {
                        a.bottom = Math.max(a.bottom, getHeight() - a2.top);
                    }
                    int i17 = a7 & 7;
                    if (i17 == 3) {
                        a.left = Math.max(a.left, a2.right);
                    } else if (i17 == 5) {
                        a.right = Math.max(a.right, getWidth() - a2.left);
                    }
                }
                if (abm6.f87h != 0 && view.getVisibility() == 0) {
                    if (C0340mj.m14732w(view)) {
                        if (view.getWidth() > 0 && view.getHeight() > 0) {
                            abm abm7 = (abm) view.getLayoutParams();
                            abj abj2 = abm7.f80a;
                            Rect a8 = m959a();
                            Rect a9 = m959a();
                            a9.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                            if (abj2 != null) {
                                abj2.mo97d(view);
                            }
                            a8.set(a9);
                            m963a(a9);
                            if (a8.isEmpty()) {
                                m963a(a8);
                            } else {
                                int a10 = C0321lr.m14621a(abm7.f87h, f);
                                if ((a10 & 48) != 48 || (i5 = (a8.top - abm7.topMargin) - abm7.f89j) >= a.top) {
                                    z = false;
                                } else {
                                    m974d(view, a.top - i5);
                                    z = true;
                                }
                                if ((a10 & 80) == 80 && (height = ((getHeight() - a8.bottom) - abm7.bottomMargin) + abm7.f89j) < a.bottom) {
                                    m974d(view, height - a.bottom);
                                } else if (!z) {
                                    m974d(view, 0);
                                }
                                if ((a10 & 3) != 3 || (i4 = (a8.left - abm7.leftMargin) - abm7.f88i) >= a.left) {
                                    z2 = false;
                                } else {
                                    m972c(view, a.left - i4);
                                    z2 = true;
                                }
                                if ((a10 & 5) == 5 && (width = ((getWidth() - a8.right) - abm7.rightMargin) + abm7.f88i) < a.right) {
                                    m972c(view, width - a.right);
                                } else if (!z2) {
                                    m972c(view, 0);
                                }
                                m963a(a8);
                            }
                        }
                    }
                }
                if (i9 != 2) {
                    rect = rect5;
                    rect.set(((abm) view.getLayoutParams()).f94o);
                    if (!rect.equals(a2)) {
                        ((abm) view.getLayoutParams()).f94o.set(a2);
                    } else {
                        i3 = i15;
                    }
                } else {
                    rect = rect5;
                }
                int i18 = i2 + 1;
                while (true) {
                    i3 = i15;
                    if (i18 >= i3) {
                        break;
                    }
                    View view2 = (View) this.f1054k.get(i18);
                    abm abm8 = (abm) view2.getLayoutParams();
                    abj abj3 = abm8.f80a;
                    if (abj3 != null) {
                        if (abj3.mo87a(view)) {
                            if (i9 != 0 || !abm8.f93n) {
                                boolean a11 = i9 != 2 ? abj3.mo92a(this, view2, view) : true;
                                if (i9 == 1) {
                                    abm8.f93n = a11;
                                }
                            } else {
                                abm8.mo101a();
                            }
                        }
                    }
                    i18++;
                    i15 = i3;
                }
            } else {
                i3 = size;
                rect = a3;
                i2 = i10;
            }
            i10 = i2 + 1;
            size = i3;
            a3 = rect;
        }
        m963a(a);
        m963a(a2);
        m963a(a3);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m969b();
        if (this.f1064u && this.f1063t != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1063t);
        }
        View view = this.f1062s;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.f1059p = false;
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f1052d && this.f1065v != null) {
            C0348mr mrVar = this.f1051c;
            int b = mrVar != null ? mrVar.mo9409b() : 0;
            if (b > 0) {
                this.f1065v.setBounds(0, 0, getWidth(), b);
                this.f1065v.draw(canvas);
            }
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            m969b();
        }
        boolean a = m966a(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1061r = null;
            m969b();
        }
        return a;
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        abj abj;
        int f = C0340mj.m14714f(this);
        int size = this.f1054k.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = (View) this.f1054k.get(i5);
            if (view.getVisibility() != 8 && ((abj = ((abm) view.getLayoutParams()).f80a) == null || !abj.mo88a(this, view, f))) {
                mo1123b(view, f);
            }
        }
    }

    /* renamed from: b */
    public final void mo1123b(View view, int i) {
        int i2;
        abm abm = (abm) view.getLayoutParams();
        View view2 = abm.f90k;
        if (view2 == null && abm.f85f != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        } else if (view2 != null) {
            Rect a = m959a();
            Rect a2 = m959a();
            try {
                abs.m162a((ViewGroup) this, view2, a);
                abm abm2 = (abm) view.getLayoutParams();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                m961a(i, a, a2, abm2, measuredWidth, measuredHeight);
                m962a(abm2, a2, measuredWidth, measuredHeight);
                view.layout(a2.left, a2.top, a2.right, a2.bottom);
            } finally {
                m963a(a);
                m963a(a2);
            }
        } else {
            int i3 = abm.f84e;
            if (i3 >= 0) {
                abm abm3 = (abm) view.getLayoutParams();
                int a3 = C0321lr.m14621a(m973d(abm3.f82c), i);
                int i4 = a3 & 7;
                int i5 = a3 & 112;
                int width = getWidth();
                int height = getHeight();
                int measuredWidth2 = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                if (i == 1) {
                    i3 = width - i3;
                }
                int b = m967b(i3) - measuredWidth2;
                if (i4 == 1) {
                    b += measuredWidth2 / 2;
                } else if (i4 == 5) {
                    b += measuredWidth2;
                }
                if (i5 != 16) {
                    i2 = i5 != 80 ? 0 : measuredHeight2;
                } else {
                    i2 = measuredHeight2 / 2;
                }
                int max = Math.max(getPaddingLeft() + abm3.leftMargin, Math.min(b, ((width - getPaddingRight()) - measuredWidth2) - abm3.rightMargin));
                int max2 = Math.max(getPaddingTop() + abm3.topMargin, Math.min(i2, ((height - getPaddingBottom()) - measuredHeight2) - abm3.bottomMargin));
                view.layout(max, max2, measuredWidth2 + max, measuredHeight2 + max2);
                return;
            }
            abm abm4 = (abm) view.getLayoutParams();
            Rect a4 = m959a();
            a4.set(getPaddingLeft() + abm4.leftMargin, getPaddingTop() + abm4.topMargin, (getWidth() - getPaddingRight()) - abm4.rightMargin, (getHeight() - getPaddingBottom()) - abm4.bottomMargin);
            if (this.f1051c != null && C0340mj.m14725p(this) && !C0340mj.m14725p(view)) {
                a4.left += this.f1051c.mo9408a();
                a4.top += this.f1051c.mo9409b();
                a4.right -= this.f1051c.mo9410c();
                a4.bottom -= this.f1051c.mo9411d();
            }
            Rect a5 = m959a();
            C0321lr.m14627a(m970c(abm4.f82c), view.getMeasuredWidth(), view.getMeasuredHeight(), a4, a5, i);
            view.layout(a5.left, a5.top, a5.right, a5.bottom);
            m963a(a4);
            m963a(a5);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0329, code lost:
        r9.f91l = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x035c, code lost:
        r2 = p000.C0340mj.m14714f(r30);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x013c, code lost:
        if (r11 != 1) goto L_0x0148;
     */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r31, int r32) {
        /*
            r30 = this;
            r6 = r30
            java.util.List r0 = r6.f1054k
            r0.clear()
            abr r0 = r6.f1049a
            lf r1 = r0.f100b
            int r1 = r1.f15193b
            r7 = 0
            r2 = 0
        L_0x000f:
            if (r2 < r1) goto L_0x03d2
            lf r0 = r0.f100b
            r0.clear()
            int r3 = r30.getChildCount()
            r4 = 0
        L_0x001b:
            if (r4 < r3) goto L_0x0277
            java.util.List r0 = r6.f1054k
            abr r1 = r6.f1049a
            java.util.ArrayList r2 = r1.f101c
            r2.clear()
            java.util.HashSet r2 = r1.f102d
            r2.clear()
            lf r2 = r1.f100b
            int r2 = r2.f15193b
            r3 = 0
        L_0x0030:
            if (r3 < r2) goto L_0x025f
            java.util.ArrayList r1 = r1.f101c
            r0.addAll(r1)
            java.util.List r0 = r6.f1054k
            java.util.Collections.reverse(r0)
            int r0 = r30.getChildCount()
            r1 = 0
        L_0x0041:
            r8 = 1
            if (r1 >= r0) goto L_0x006a
            android.view.View r2 = r6.getChildAt(r1)
            abr r3 = r6.f1049a
            lf r4 = r3.f100b
            int r4 = r4.f15193b
            r5 = 0
        L_0x004f:
            if (r5 >= r4) goto L_0x0067
            lf r9 = r3.f100b
            java.lang.Object r9 = r9.mo9321c(r5)
            java.util.ArrayList r9 = (java.util.ArrayList) r9
            if (r9 != 0) goto L_0x005c
            goto L_0x0064
        L_0x005c:
            boolean r9 = r9.contains(r2)
            if (r9 == 0) goto L_0x0064
            r0 = 1
            goto L_0x006c
        L_0x0064:
            int r5 = r5 + 1
            goto L_0x004f
        L_0x0067:
            int r1 = r1 + 1
            goto L_0x0041
        L_0x006a:
            r0 = 0
        L_0x006c:
            boolean r1 = r6.f1064u
            if (r0 == r1) goto L_0x00a2
            if (r0 != 0) goto L_0x0087
            boolean r0 = r6.f1059p
            if (r0 == 0) goto L_0x0083
            abn r0 = r6.f1063t
            if (r0 == 0) goto L_0x0083
            android.view.ViewTreeObserver r0 = r30.getViewTreeObserver()
            abn r1 = r6.f1063t
            r0.removeOnPreDrawListener(r1)
        L_0x0083:
            r6.f1064u = r7
            goto L_0x00a2
        L_0x0087:
            boolean r0 = r6.f1059p
            if (r0 == 0) goto L_0x009f
            abn r0 = r6.f1063t
            if (r0 != 0) goto L_0x0096
            abn r0 = new abn
            r0.<init>(r6)
            r6.f1063t = r0
        L_0x0096:
            android.view.ViewTreeObserver r0 = r30.getViewTreeObserver()
            abn r1 = r6.f1063t
            r0.addOnPreDrawListener(r1)
        L_0x009f:
            r6.f1064u = r8
        L_0x00a2:
            int r9 = r30.getPaddingLeft()
            int r0 = r30.getPaddingTop()
            int r10 = r30.getPaddingRight()
            int r1 = r30.getPaddingBottom()
            int r11 = p000.C0340mj.m14714f(r30)
            int r12 = android.view.View.MeasureSpec.getMode(r31)
            int r13 = android.view.View.MeasureSpec.getSize(r31)
            int r14 = android.view.View.MeasureSpec.getMode(r32)
            int r15 = android.view.View.MeasureSpec.getSize(r32)
            int r16 = r9 + r10
            int r17 = r0 + r1
            int r0 = r30.getSuggestedMinimumWidth()
            int r1 = r30.getSuggestedMinimumHeight()
            mr r2 = r6.f1051c
            if (r2 == 0) goto L_0x00e0
            boolean r2 = p000.C0340mj.m14725p(r30)
            if (r2 == 0) goto L_0x00df
            r18 = 1
            goto L_0x00e2
        L_0x00df:
        L_0x00e0:
            r18 = 0
        L_0x00e2:
            java.util.List r2 = r6.f1054k
            int r5 = r2.size()
            r4 = r0
            r3 = r1
            r1 = 0
            r2 = 0
        L_0x00ec:
            if (r2 >= r5) goto L_0x0247
            java.util.List r0 = r6.f1054k
            java.lang.Object r0 = r0.get(r2)
            android.view.View r0 = (android.view.View) r0
            int r7 = r0.getVisibility()
            r8 = 8
            if (r7 == r8) goto L_0x0227
            android.view.ViewGroup$LayoutParams r7 = r0.getLayoutParams()
            abm r7 = (p000.abm) r7
            int r8 = r7.f84e
            if (r8 >= 0) goto L_0x0110
            r21 = r1
            r22 = r2
            r1 = 0
            r2 = 1
        L_0x010e:
            r8 = 0
            goto L_0x0163
        L_0x0110:
            if (r12 == 0) goto L_0x015c
            int r8 = r6.m967b((int) r8)
            r21 = r1
            int r1 = r7.f82c
            int r1 = m973d(r1)
            int r1 = p000.C0321lr.m14621a((int) r1, (int) r11)
            r1 = r1 & 7
            r22 = r2
            r2 = 3
            if (r1 == r2) goto L_0x012b
            r2 = 1
            goto L_0x012f
        L_0x012b:
            r2 = 1
            if (r11 != r2) goto L_0x0152
        L_0x012f:
            r2 = 5
            if (r1 != r2) goto L_0x0137
            r2 = 1
            if (r11 != r2) goto L_0x0138
            r1 = 0
            goto L_0x0153
        L_0x0137:
            r2 = 1
        L_0x0138:
            r2 = 5
            if (r1 != r2) goto L_0x013f
            r2 = 1
            if (r11 == r2) goto L_0x0140
            goto L_0x0148
        L_0x013f:
            r2 = 1
        L_0x0140:
            r2 = 3
            if (r1 != r2) goto L_0x014f
            r2 = 1
            if (r11 == r2) goto L_0x0148
            r1 = 0
            goto L_0x010e
        L_0x0148:
            int r8 = r8 - r9
            r1 = 0
            int r8 = java.lang.Math.max(r1, r8)
            goto L_0x0163
        L_0x014f:
            r1 = 0
            r2 = 1
            goto L_0x0162
        L_0x0152:
            r1 = 0
        L_0x0153:
            int r19 = r13 - r10
            int r8 = r19 - r8
            int r8 = java.lang.Math.max(r1, r8)
            goto L_0x0163
        L_0x015c:
            r21 = r1
            r22 = r2
            r1 = 0
            r2 = 1
        L_0x0162:
            goto L_0x010e
        L_0x0163:
            if (r18 != 0) goto L_0x016a
            r23 = r0
            r24 = r3
            goto L_0x01a1
        L_0x016a:
            boolean r19 = p000.C0340mj.m14725p(r0)
            if (r19 != 0) goto L_0x019d
            mr r1 = r6.f1051c
            int r1 = r1.mo9408a()
            mr r2 = r6.f1051c
            int r2 = r2.mo9410c()
            r23 = r0
            mr r0 = r6.f1051c
            int r0 = r0.mo9409b()
            r24 = r3
            mr r3 = r6.f1051c
            int r3 = r3.mo9411d()
            int r1 = r1 + r2
            int r1 = r13 - r1
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r12)
            int r0 = r0 + r3
            int r0 = r15 - r0
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r14)
            r3 = r0
            r2 = r1
            goto L_0x01a5
        L_0x019d:
            r23 = r0
            r24 = r3
        L_0x01a1:
            r2 = r31
            r3 = r32
        L_0x01a5:
            abj r0 = r7.f80a
            if (r0 == 0) goto L_0x01d7
            r1 = r23
            r23 = r9
            r9 = r21
            r19 = 0
            r21 = r1
            r1 = r30
            r20 = r22
            r25 = 1
            r22 = r2
            r2 = r21
            r26 = r10
            r10 = r24
            r24 = r3
            r3 = r22
            r27 = r11
            r11 = r4
            r4 = r8
            r28 = r5
            r5 = r24
            boolean r0 = r0.mo89a((androidx.coordinatorlayout.widget.CoordinatorLayout) r1, (android.view.View) r2, (int) r3, (int) r4, (int) r5)
            if (r0 != 0) goto L_0x01d4
            goto L_0x01f2
        L_0x01d4:
            r0 = r21
            goto L_0x01fb
        L_0x01d7:
            r28 = r5
            r26 = r10
            r27 = r11
            r20 = r22
            r10 = r24
            r19 = 0
            r25 = 1
            r22 = r2
            r24 = r3
            r11 = r4
            r29 = r23
            r23 = r9
            r9 = r21
            r21 = r29
        L_0x01f2:
            r0 = r21
            r1 = r22
            r2 = r24
            r6.mo1121a((android.view.View) r0, (int) r1, (int) r8, (int) r2)
        L_0x01fb:
            int r1 = r0.getMeasuredWidth()
            int r1 = r16 + r1
            int r2 = r7.leftMargin
            int r1 = r1 + r2
            int r2 = r7.rightMargin
            int r1 = r1 + r2
            int r1 = java.lang.Math.max(r11, r1)
            int r2 = r0.getMeasuredHeight()
            int r2 = r17 + r2
            int r3 = r7.topMargin
            int r2 = r2 + r3
            int r3 = r7.bottomMargin
            int r2 = r2 + r3
            int r2 = java.lang.Math.max(r10, r2)
            int r0 = r0.getMeasuredState()
            int r0 = android.view.View.combineMeasuredStates(r9, r0)
            r4 = r1
            r3 = r2
            r1 = r0
            goto L_0x0238
        L_0x0227:
            r20 = r2
            r28 = r5
            r23 = r9
            r26 = r10
            r27 = r11
            r19 = 0
            r25 = 1
            r9 = r1
            r10 = r3
            r11 = r4
        L_0x0238:
            int r2 = r20 + 1
            r9 = r23
            r10 = r26
            r11 = r27
            r5 = r28
            r7 = 0
            r8 = 1
            goto L_0x00ec
        L_0x0247:
            r9 = r1
            r10 = r3
            r11 = r4
            r0 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r9
            r5 = r31
            int r0 = android.view.View.resolveSizeAndState(r11, r5, r0)
            int r1 = r9 << 16
            r7 = r32
            int r1 = android.view.View.resolveSizeAndState(r10, r7, r1)
            r6.setMeasuredDimension(r0, r1)
            return
        L_0x025f:
            r5 = r31
            r7 = r32
            r19 = 0
            lf r4 = r1.f100b
            java.lang.Object r4 = r4.mo9320b((int) r3)
            java.util.ArrayList r8 = r1.f101c
            java.util.HashSet r9 = r1.f102d
            r1.mo119a(r4, r8, r9)
            int r3 = r3 + 1
            r7 = 0
            goto L_0x0030
        L_0x0277:
            r5 = r31
            r7 = r32
            r19 = 0
            android.view.View r8 = r6.getChildAt(r4)
            abm r9 = m968b((android.view.View) r8)
            int r0 = r9.f85f
            r1 = -1
            r2 = 0
            if (r0 == r1) goto L_0x033f
            android.view.View r0 = r9.f90k
            if (r0 != 0) goto L_0x0290
            goto L_0x02bb
        L_0x0290:
            int r0 = r0.getId()
            int r1 = r9.f85f
            if (r0 != r1) goto L_0x02bb
            android.view.View r0 = r9.f90k
            android.view.ViewParent r1 = r0.getParent()
        L_0x029e:
            if (r1 != r6) goto L_0x02a4
            r9.f91l = r0
            goto L_0x0344
        L_0x02a4:
            if (r1 != 0) goto L_0x02a7
            goto L_0x02b6
        L_0x02a7:
            if (r1 == r8) goto L_0x02b6
            boolean r10 = r1 instanceof android.view.View
            if (r10 != 0) goto L_0x02ae
            goto L_0x02b1
        L_0x02ae:
            r0 = r1
            android.view.View r0 = (android.view.View) r0
        L_0x02b1:
            android.view.ViewParent r1 = r1.getParent()
            goto L_0x029e
        L_0x02b6:
            r9.f91l = r2
            r9.f90k = r2
        L_0x02bb:
            int r0 = r9.f85f
            android.view.View r0 = r6.findViewById(r0)
            r9.f90k = r0
            android.view.View r0 = r9.f90k
            if (r0 != 0) goto L_0x02fc
            boolean r0 = r30.isInEditMode()
            if (r0 == 0) goto L_0x02d3
            r9.f91l = r2
            r9.f90k = r2
            goto L_0x0344
        L_0x02d3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Could not find CoordinatorLayout descendant view with id "
            r1.append(r2)
            android.content.res.Resources r2 = r30.getResources()
            int r3 = r9.f85f
            java.lang.String r2 = r2.getResourceName(r3)
            r1.append(r2)
            java.lang.String r2 = " to anchor view "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x02fc:
            if (r0 == r6) goto L_0x032c
            android.view.ViewParent r1 = r0.getParent()
        L_0x0302:
            if (r1 != r6) goto L_0x0305
            goto L_0x0329
        L_0x0305:
            if (r1 == 0) goto L_0x0329
            if (r1 == r8) goto L_0x0316
            boolean r10 = r1 instanceof android.view.View
            if (r10 != 0) goto L_0x030e
            goto L_0x0311
        L_0x030e:
            r0 = r1
            android.view.View r0 = (android.view.View) r0
        L_0x0311:
            android.view.ViewParent r1 = r1.getParent()
            goto L_0x0302
        L_0x0316:
            boolean r0 = r30.isInEditMode()
            if (r0 == 0) goto L_0x0321
            r9.f91l = r2
            r9.f90k = r2
            goto L_0x0344
        L_0x0321:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Anchor must not be a descendant of the anchored view"
            r0.<init>(r1)
            throw r0
        L_0x0329:
            r9.f91l = r0
            goto L_0x0344
        L_0x032c:
            boolean r0 = r30.isInEditMode()
            if (r0 == 0) goto L_0x0337
            r9.f91l = r2
            r9.f90k = r2
            goto L_0x0344
        L_0x0337:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "View can not be anchored to the the parent CoordinatorLayout"
            r0.<init>(r1)
            throw r0
        L_0x033f:
            r9.f91l = r2
            r9.f90k = r2
        L_0x0344:
            abr r0 = r6.f1049a
            r0.mo118a(r8)
            r1 = 0
        L_0x034a:
            if (r1 < r3) goto L_0x0351
            int r4 = r4 + 1
            r7 = 0
            goto L_0x001b
        L_0x0351:
            if (r1 == r4) goto L_0x03ce
            android.view.View r0 = r6.getChildAt(r1)
            android.view.View r2 = r9.f91l
            if (r0 != r2) goto L_0x035c
            goto L_0x0381
        L_0x035c:
            int r2 = p000.C0340mj.m14714f(r30)
            android.view.ViewGroup$LayoutParams r10 = r0.getLayoutParams()
            abm r10 = (p000.abm) r10
            int r10 = r10.f86g
            int r10 = p000.C0321lr.m14621a((int) r10, (int) r2)
            if (r10 == 0) goto L_0x0377
            int r11 = r9.f87h
            int r2 = p000.C0321lr.m14621a((int) r11, (int) r2)
            r2 = r2 & r10
            if (r2 == r10) goto L_0x0381
        L_0x0377:
            abj r2 = r9.f80a
            if (r2 == 0) goto L_0x03ce
            boolean r2 = r2.mo87a((android.view.View) r0)
            if (r2 == 0) goto L_0x03ce
        L_0x0381:
            abr r2 = r6.f1049a
            lf r2 = r2.f100b
            boolean r2 = r2.containsKey(r0)
            if (r2 == 0) goto L_0x038c
            goto L_0x0391
        L_0x038c:
            abr r2 = r6.f1049a
            r2.mo118a(r0)
        L_0x0391:
            abr r2 = r6.f1049a
            lf r10 = r2.f100b
            boolean r10 = r10.containsKey(r0)
            if (r10 == 0) goto L_0x03c6
            lf r10 = r2.f100b
            boolean r10 = r10.containsKey(r8)
            if (r10 == 0) goto L_0x03c6
            lf r10 = r2.f100b
            java.lang.Object r10 = r10.get(r0)
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            if (r10 != 0) goto L_0x03c2
            lc r10 = r2.f99a
            java.lang.Object r10 = r10.mo1971a()
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            if (r10 == 0) goto L_0x03b8
            goto L_0x03bd
        L_0x03b8:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x03bd:
            lf r2 = r2.f100b
            r2.put(r0, r10)
        L_0x03c2:
            r10.add(r8)
            goto L_0x03ce
        L_0x03c6:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "All nodes must be present in the graph before being added as an edge"
            r0.<init>(r1)
            throw r0
        L_0x03ce:
            int r1 = r1 + 1
            goto L_0x034a
        L_0x03d2:
            r5 = r31
            r7 = r32
            r19 = 0
            lf r3 = r0.f100b
            java.lang.Object r3 = r3.mo9321c(r2)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            if (r3 == 0) goto L_0x03ea
            r3.clear()
            lc r4 = r0.f99a
            r4.mo1972a(r3)
        L_0x03ea:
            int r2 = r2 + 1
            r7 = 0
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    /* renamed from: a */
    public final void mo1121a(View view, int i, int i2, int i3) {
        measureChildWithMargins(view, i, i2, i3, 0);
    }

    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                abm abm = (abm) childAt.getLayoutParams();
            }
        }
        return false;
    }

    public final boolean onNestedPreFling(View view, float f, float f2) {
        abj abj;
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                abm abm = (abm) childAt.getLayoutParams();
                if (abm.mo104a(0) && (abj = abm.f80a) != null) {
                    z |= abj.mo94b(view);
                }
            }
        }
        return z;
    }

    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        mo707a(view, i, i2, iArr, 0);
    }

    /* renamed from: a */
    public final void mo707a(View view, int i, int i2, int[] iArr, int i3) {
        abj abj;
        int childCount = getChildCount();
        boolean z = false;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                abm abm = (abm) childAt.getLayoutParams();
                if (abm.mo104a(i3) && (abj = abm.f80a) != null) {
                    int[] iArr2 = this.f1056m;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    abj.mo86a(this, childAt, view, i2, iArr2, i3);
                    if (i <= 0) {
                        i4 = Math.min(i4, this.f1056m[0]);
                    } else {
                        i4 = Math.max(i4, this.f1056m[0]);
                    }
                    i5 = i2 <= 0 ? Math.min(i5, this.f1056m[1]) : Math.max(i5, this.f1056m[1]);
                    z = true;
                }
            } else {
                int i7 = i3;
            }
        }
        iArr[0] = i4;
        iArr[1] = i5;
        if (z) {
            mo1120a(1);
        }
    }

    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        mo705a(view, i, i2, i3, i4, 0);
    }

    /* renamed from: a */
    public final void mo705a(View view, int i, int i2, int i3, int i4, int i5) {
        mo706a(view, i, i2, i3, i4, 0, this.f1057n);
    }

    /* renamed from: a */
    public final void mo706a(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        abj abj;
        int childCount = getChildCount();
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                abm abm = (abm) childAt.getLayoutParams();
                if (abm.mo104a(i5) && (abj = abm.f80a) != null) {
                    int[] iArr2 = this.f1056m;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    abj.mo84a(this, childAt, i2, i3, i4, iArr2);
                    if (i3 <= 0) {
                        i6 = Math.min(i6, this.f1056m[0]);
                    } else {
                        i6 = Math.max(i6, this.f1056m[0]);
                    }
                    i7 = i4 <= 0 ? Math.min(i7, this.f1056m[1]) : Math.max(i7, this.f1056m[1]);
                    z = true;
                }
            } else {
                int i9 = i5;
            }
        }
        iArr[0] = iArr[0] + i6;
        iArr[1] = iArr[1] + i7;
        if (z) {
            mo1120a(1);
        }
    }

    public final void onNestedScrollAccepted(View view, View view2, int i) {
        mo714b(view, view2, i, 0);
    }

    /* renamed from: b */
    public final void mo714b(View view, View view2, int i, int i2) {
        this.f1067x.mo9381a(i, i2);
        this.f1062s = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            abm abm = (abm) getChildAt(i3).getLayoutParams();
        }
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof abp)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        abp abp = (abp) parcelable;
        super.onRestoreInstanceState(abp.f15201b);
        SparseArray sparseArray = abp.f98c;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            abj abj = m968b(childAt).f80a;
            if (!(id == -1 || abj == null || (parcelable2 = (Parcelable) sparseArray.get(id)) == null)) {
                abj.mo83a(childAt, parcelable2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Parcelable onSaveInstanceState() {
        Parcelable c;
        abp abp = new abp(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            abj abj = ((abm) childAt.getLayoutParams()).f80a;
            if (!(id == -1 || abj == null || (c = abj.mo96c(childAt)) == null)) {
                sparseArray.append(id, c);
            }
        }
        abp.f98c = sparseArray;
        return abp;
    }

    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return mo709a(view, view2, i, 0);
    }

    /* renamed from: a */
    public final boolean mo709a(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                abm abm = (abm) childAt.getLayoutParams();
                abj abj = abm.f80a;
                if (abj != null) {
                    boolean a = abj.mo93a(this, childAt, view, i, i2);
                    z |= a;
                    abm.mo102a(i2, a);
                } else {
                    abm.mo102a(i2, false);
                }
            }
        }
        return z;
    }

    public final void onStopNestedScroll(View view) {
        mo704a(view, 0);
    }

    /* renamed from: a */
    public final void mo704a(View view, int i) {
        this.f1067x.mo9380a(i);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            abm abm = (abm) childAt.getLayoutParams();
            if (abm.mo104a(i)) {
                abj abj = abm.f80a;
                if (abj != null) {
                    abj.mo85a(this, childAt, view, i);
                }
                abm.mo102a(i, false);
                abm.mo101a();
            }
        }
        this.f1062s = null;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        View view = this.f1061r;
        boolean z2 = false;
        if (view == null) {
            z = m966a(motionEvent, 1);
            if (actionMasked != 0 && z) {
                z2 = true;
            }
        } else {
            abj abj = ((abm) view.getLayoutParams()).f80a;
            z = abj != null ? abj.mo95b(this, this.f1061r, motionEvent) : false;
        }
        if (this.f1061r == null || actionMasked == 3) {
            z |= super.onTouchEvent(motionEvent);
        } else if (z2) {
            MotionEvent a = m960a(motionEvent);
            super.onTouchEvent(a);
            a.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1061r = null;
            m969b();
        }
        return z;
    }

    /* renamed from: a */
    public static abj m958a(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0 && !TextUtils.isEmpty(f1044f)) {
            str = f1044f + '.' + str;
        }
        try {
            Map map = (Map) f1046h.get();
            if (map == null) {
                map = new HashMap();
                f1046h.set(map);
            }
            Constructor<?> constructor = (Constructor) map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(f1045g);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return (abj) constructor.newInstance(new Object[]{context, attributeSet});
        } catch (Exception e) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e);
        }
    }

    /* renamed from: a */
    private final boolean m965a(abj abj, View view, MotionEvent motionEvent, int i) {
        if (i != 0) {
            return abj.mo95b(this, view, motionEvent);
        }
        return abj.mo91a(this, view, motionEvent);
    }

    /* renamed from: a */
    private final boolean m966a(MotionEvent motionEvent, int i) {
        int actionMasked = motionEvent.getActionMasked();
        List list = this.f1055l;
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            list.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        Comparator comparator = f1047i;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        int size = list.size();
        MotionEvent motionEvent2 = null;
        boolean z = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) list.get(i3);
            abm abm = (abm) view.getLayoutParams();
            abj abj = abm.f80a;
            if (!z || actionMasked == 0) {
                if (!z && abj != null && (z = m965a(abj, view, motionEvent, i))) {
                    this.f1061r = view;
                    if (!(actionMasked == 3 || actionMasked == 1)) {
                        for (int i4 = 0; i4 < i3; i4++) {
                            View view2 = (View) list.get(i4);
                            abj abj2 = ((abm) view2.getLayoutParams()).f80a;
                            if (abj2 != null) {
                                if (motionEvent2 == null) {
                                    motionEvent2 = m960a(motionEvent);
                                }
                                m965a(abj2, view2, motionEvent2, i);
                            }
                        }
                    }
                }
                if (abm.f80a == null) {
                    abm.f92m = false;
                }
            } else if (abj != null) {
                if (motionEvent2 == null) {
                    motionEvent2 = m960a(motionEvent);
                }
                m965a(abj, view, motionEvent2, i);
            }
        }
        list.clear();
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        return z;
    }

    /* renamed from: a */
    private static void m963a(Rect rect) {
        rect.setEmpty();
        f1048j.mo1972a(rect);
    }

    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        abj abj = ((abm) view.getLayoutParams()).f80a;
        if (abj == null || !abj.mo90a(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    public final void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z && !this.f1058o) {
            if (this.f1061r == null) {
                int childCount = getChildCount();
                MotionEvent motionEvent = null;
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    abj abj = ((abm) childAt.getLayoutParams()).f80a;
                    if (abj != null) {
                        if (motionEvent == null) {
                            long uptimeMillis = SystemClock.uptimeMillis();
                            motionEvent = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                        }
                        abj.mo91a(this, childAt, motionEvent);
                    }
                }
                if (motionEvent != null) {
                    motionEvent.recycle();
                }
            }
            m969b();
            this.f1058o = true;
        }
    }

    /* renamed from: b */
    private final void m969b() {
        View view = this.f1061r;
        if (view != null) {
            abj abj = ((abm) view.getLayoutParams()).f80a;
            if (abj != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                abj.mo95b(this, this.f1061r, obtain);
                obtain.recycle();
            }
            this.f1061r = null;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((abm) getChildAt(i).getLayoutParams()).f92m = false;
        }
        this.f1058o = false;
    }

    public final void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        m971c();
    }

    /* renamed from: c */
    private static final void m972c(View view, int i) {
        abm abm = (abm) view.getLayoutParams();
        int i2 = abm.f88i;
        if (i2 != i) {
            C0340mj.m14711d(view, i - i2);
            abm.f88i = i;
        }
    }

    /* renamed from: d */
    private static final void m974d(View view, int i) {
        abm abm = (abm) view.getLayoutParams();
        int i2 = abm.f89j;
        if (i2 != i) {
            C0340mj.m14708c(view, i - i2);
            abm.f89j = i;
        }
    }

    public final void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f1053e = onHierarchyChangeListener;
    }

    public final void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f1065v;
        if (drawable != null && drawable.isVisible() != z) {
            this.f1065v.setVisible(z, false);
        }
    }

    /* renamed from: c */
    private final void m971c() {
        int i = Build.VERSION.SDK_INT;
        if (C0340mj.m14725p(this)) {
            if (this.f1066w == null) {
                this.f1066w = new abh(this);
            }
            C0340mj.m14699a((View) this, this.f1066w);
            setSystemUiVisibility(1280);
            return;
        }
        C0340mj.m14699a((View) this, (C0329lz) null);
    }

    /* access modifiers changed from: protected */
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1065v;
    }
}
