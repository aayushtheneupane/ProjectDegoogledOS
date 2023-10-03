package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.support.p002v7.view.menu.ActionMenuItemView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: android.support.v7.widget.ActionMenuView */
/* compiled from: PG */
public class ActionMenuView extends C0601wa implements C0471rf, C0488rw {

    /* renamed from: a */
    public C0472rg f934a;

    /* renamed from: b */
    public boolean f935b;

    /* renamed from: c */
    public C0512st f936c;

    /* renamed from: d */
    public C0470re f937d;

    /* renamed from: e */
    public C0517sy f938e;

    /* renamed from: i */
    private Context f939i;

    /* renamed from: j */
    private int f940j;

    /* renamed from: k */
    private C0485rt f941k;

    /* renamed from: l */
    private boolean f942l;

    /* renamed from: m */
    private int f943m;

    /* renamed from: n */
    private int f944n;

    /* renamed from: o */
    private int f945o;

    public ActionMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo10442e();
        float f = context.getResources().getDisplayMetrics().density;
        this.f944n = (int) (56.0f * f);
        this.f945o = (int) (f * 4.0f);
        this.f939i = context;
        this.f940j = 0;
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0515sw;
    }

    /* renamed from: b */
    public final void mo849b() {
        C0512st stVar = this.f936c;
        if (stVar != null) {
            stVar.mo10073f();
        }
    }

    /* renamed from: d */
    public static final C0515sw m895d() {
        C0515sw swVar = new C0515sw();
        swVar.f16180h = 16;
        return swVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final /* bridge */ /* synthetic */ C0599vz mo850c() {
        return m895d();
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m895d();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final C0515sw generateLayoutParams(AttributeSet attributeSet) {
        return new C0515sw(getContext(), attributeSet);
    }

    /* renamed from: b */
    public static final C0515sw m894b(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return m895d();
        }
        C0515sw swVar = layoutParams instanceof C0515sw ? new C0515sw((C0515sw) layoutParams) : new C0515sw(layoutParams);
        if (swVar.f16180h <= 0) {
            swVar.f16180h = 16;
        }
        return swVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C0599vz mo845a(ViewGroup.LayoutParams layoutParams) {
        return m894b(layoutParams);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m894b(layoutParams);
    }

    /* renamed from: a */
    public final Menu mo843a() {
        if (this.f934a == null) {
            Context context = getContext();
            C0472rg rgVar = new C0472rg(context);
            this.f934a = rgVar;
            rgVar.mo9832a((C0470re) new C0516sx(this));
            C0512st stVar = new C0512st(context);
            this.f936c = stVar;
            stVar.mo10075h();
            C0512st stVar2 = this.f936c;
            C0485rt rtVar = this.f941k;
            if (rtVar == null) {
                rtVar = new C0514sv();
            }
            stVar2.f15694e = rtVar;
            this.f934a.mo9834a((C0486ru) this.f936c, this.f939i);
            this.f936c.mo10069a(this);
        }
        return this.f934a;
    }

    /* renamed from: d */
    private final boolean m896d(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof C0513su)) {
            z = ((C0513su) childAt).mo767e();
        }
        return (i <= 0 || !(childAt2 instanceof C0513su)) ? z : ((C0513su) childAt2).mo766d() | z;
    }

    /* renamed from: a */
    public final void mo774a(C0472rg rgVar) {
        this.f934a = rgVar;
    }

    /* renamed from: a */
    public final boolean mo775a(C0475rj rjVar) {
        return this.f934a.mo9836a((MenuItem) rjVar, 0);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C0512st stVar = this.f936c;
        if (stVar != null) {
            stVar.mo9791b();
            if (this.f936c.mo10072e()) {
                this.f936c.mo10071d();
                this.f936c.mo10070c();
            }
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mo849b();
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (this.f942l) {
            int childCount = getChildCount();
            int i7 = (i4 - i2) / 2;
            int i8 = this.f16188h;
            int i9 = i3 - i;
            int paddingRight = (i9 - getPaddingRight()) - getPaddingLeft();
            boolean a = C0703zv.m16280a(this);
            int i10 = 0;
            int i11 = 0;
            for (int i12 = 0; i12 < childCount; i12++) {
                View childAt = getChildAt(i12);
                if (childAt.getVisibility() != 8) {
                    C0515sw swVar = (C0515sw) childAt.getLayoutParams();
                    if (swVar.f15893a) {
                        int measuredWidth = childAt.getMeasuredWidth();
                        if (m896d(i12)) {
                            measuredWidth += i8;
                        }
                        int measuredHeight = childAt.getMeasuredHeight();
                        if (!a) {
                            i5 = (getWidth() - getPaddingRight()) - swVar.rightMargin;
                            i6 = i5 - measuredWidth;
                        } else {
                            i6 = swVar.leftMargin + getPaddingLeft();
                            i5 = i6 + measuredWidth;
                        }
                        int i13 = i7 - (measuredHeight / 2);
                        childAt.layout(i6, i13, i5, measuredHeight + i13);
                        paddingRight -= measuredWidth;
                        i10 = 1;
                    } else {
                        paddingRight -= (childAt.getMeasuredWidth() + swVar.leftMargin) + swVar.rightMargin;
                        m896d(i12);
                        i11++;
                    }
                }
            }
            if (childCount == 1 && i10 == 0) {
                View childAt2 = getChildAt(0);
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                int i14 = (i9 / 2) - (measuredWidth2 / 2);
                int i15 = i7 - (measuredHeight2 / 2);
                childAt2.layout(i14, i15, measuredWidth2 + i14, measuredHeight2 + i15);
                return;
            }
            int i16 = i11 - (i10 ^ 1);
            int max = Math.max(0, i16 > 0 ? paddingRight / i16 : 0);
            if (a) {
                int width = getWidth() - getPaddingRight();
                for (int i17 = 0; i17 < childCount; i17++) {
                    View childAt3 = getChildAt(i17);
                    C0515sw swVar2 = (C0515sw) childAt3.getLayoutParams();
                    if (childAt3.getVisibility() != 8 && !swVar2.f15893a) {
                        int i18 = width - swVar2.rightMargin;
                        int measuredWidth3 = childAt3.getMeasuredWidth();
                        int measuredHeight3 = childAt3.getMeasuredHeight();
                        int i19 = i7 - (measuredHeight3 / 2);
                        childAt3.layout(i18 - measuredWidth3, i19, i18, measuredHeight3 + i19);
                        width = i18 - ((measuredWidth3 + swVar2.leftMargin) + max);
                    }
                }
                return;
            }
            int paddingLeft = getPaddingLeft();
            for (int i20 = 0; i20 < childCount; i20++) {
                View childAt4 = getChildAt(i20);
                C0515sw swVar3 = (C0515sw) childAt4.getLayoutParams();
                if (childAt4.getVisibility() != 8 && !swVar3.f15893a) {
                    int i21 = paddingLeft + swVar3.leftMargin;
                    int measuredWidth4 = childAt4.getMeasuredWidth();
                    int measuredHeight4 = childAt4.getMeasuredHeight();
                    int i22 = i7 - (measuredHeight4 / 2);
                    childAt4.layout(i21, i22, i21 + measuredWidth4, measuredHeight4 + i22);
                    paddingLeft = i21 + measuredWidth4 + swVar3.rightMargin + max;
                }
            }
            return;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        boolean z2;
        int i4;
        boolean z3;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z4;
        ActionMenuItemView actionMenuItemView;
        int i9;
        C0472rg rgVar;
        boolean z5 = this.f942l;
        boolean z6 = View.MeasureSpec.getMode(i) == 1073741824;
        this.f942l = z6;
        if (z5 != z6) {
            this.f943m = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (!(!this.f942l || (rgVar = this.f934a) == null || size == this.f943m)) {
            this.f943m = size;
            rgVar.mo9851b(true);
        }
        int childCount = getChildCount();
        if (this.f942l && childCount > 0) {
            int mode = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i);
            int size3 = View.MeasureSpec.getSize(i2);
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
            int i10 = size2 - (paddingLeft + paddingRight);
            int i11 = this.f944n;
            int i12 = i10 / i11;
            int i13 = i10 % i11;
            if (i12 == 0) {
                setMeasuredDimension(i10, 0);
                return;
            }
            int i14 = i11 + (i13 / i12);
            int childCount2 = getChildCount();
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            boolean z7 = false;
            long j = 0;
            int i19 = 0;
            while (i18 < childCount2) {
                View childAt = getChildAt(i18);
                int i20 = size3;
                if (childAt.getVisibility() == 8) {
                    i5 = mode;
                    i6 = i10;
                    i7 = paddingTop;
                } else {
                    boolean z8 = childAt instanceof ActionMenuItemView;
                    int i21 = i16 + 1;
                    if (z8) {
                        int i22 = this.f945o;
                        i8 = i21;
                        z4 = false;
                        childAt.setPadding(i22, 0, i22, 0);
                    } else {
                        i8 = i21;
                        z4 = false;
                    }
                    C0515sw swVar = (C0515sw) childAt.getLayoutParams();
                    swVar.f15898f = z4;
                    swVar.f15895c = z4 ? 1 : 0;
                    swVar.f15894b = z4;
                    swVar.f15896d = z4;
                    swVar.leftMargin = z4;
                    swVar.rightMargin = z4;
                    swVar.f15897e = z8 && ((ActionMenuItemView) childAt).mo765c();
                    int i23 = !swVar.f15893a ? i12 : 1;
                    i6 = i10;
                    C0515sw swVar2 = (C0515sw) childAt.getLayoutParams();
                    i5 = mode;
                    i7 = paddingTop;
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingTop, View.MeasureSpec.getMode(childMeasureSpec));
                    if (z8) {
                        actionMenuItemView = (ActionMenuItemView) childAt;
                    } else {
                        actionMenuItemView = null;
                    }
                    boolean z9 = actionMenuItemView != null && actionMenuItemView.mo765c();
                    if (i23 > 0 && (!z9 || i23 >= 2)) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i23 * i14, RecyclerView.UNDEFINED_DURATION), makeMeasureSpec);
                        int measuredWidth = childAt.getMeasuredWidth();
                        i9 = measuredWidth / i14;
                        if (measuredWidth % i14 != 0) {
                            i9++;
                        }
                        if (z9 && i9 < 2) {
                            i9 = 2;
                        }
                    } else {
                        i9 = 0;
                    }
                    swVar2.f15896d = !swVar2.f15893a && z9;
                    swVar2.f15894b = i9;
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i9 * i14, 1073741824), makeMeasureSpec);
                    i15 = Math.max(i15, i9);
                    if (swVar.f15896d) {
                        i19++;
                    }
                    z7 |= swVar.f15893a;
                    i12 -= i9;
                    i17 = Math.max(i17, childAt.getMeasuredHeight());
                    if (i9 == 1) {
                        j |= (long) (1 << i18);
                        i16 = i8;
                    } else {
                        i16 = i8;
                    }
                }
                i18++;
                size3 = i20;
                paddingTop = i7;
                i10 = i6;
                mode = i5;
            }
            int i24 = mode;
            int i25 = i10;
            int i26 = size3;
            if (z7 && i16 == 2) {
                z = true;
            } else {
                z = false;
            }
            boolean z10 = false;
            while (true) {
                if (i19 > 0) {
                    if (i12 <= 0) {
                        break;
                    }
                    int i27 = Integer.MAX_VALUE;
                    int i28 = 0;
                    long j2 = 0;
                    for (int i29 = 0; i29 < childCount2; i29++) {
                        C0515sw swVar3 = (C0515sw) getChildAt(i29).getLayoutParams();
                        if (swVar3.f15896d) {
                            int i30 = swVar3.f15894b;
                            if (i30 < i27) {
                                j2 = 1 << i29;
                                i27 = i30;
                                i28 = 1;
                            } else if (i30 == i27) {
                                i28++;
                                j2 |= 1 << i29;
                            }
                        }
                    }
                    j |= j2;
                    if (i28 > i12) {
                        break;
                    }
                    int i31 = i27 + 1;
                    int i32 = 0;
                    while (i32 < childCount2) {
                        View childAt2 = getChildAt(i32);
                        C0515sw swVar4 = (C0515sw) childAt2.getLayoutParams();
                        int i33 = i17;
                        long j3 = (long) (1 << i32);
                        if ((j2 & j3) == 0) {
                            if (swVar4.f15894b == i31) {
                                j |= j3;
                            }
                            z3 = z;
                        } else {
                            if (!z) {
                                z3 = z;
                            } else if (!swVar4.f15897e || i12 != 1) {
                                z3 = z;
                            } else {
                                int i34 = this.f945o;
                                z3 = z;
                                childAt2.setPadding(i34 + i14, 0, i34, 0);
                            }
                            swVar4.f15894b++;
                            swVar4.f15898f = true;
                            i12--;
                        }
                        i32++;
                        i17 = i33;
                        z = z3;
                    }
                    boolean z11 = z;
                    int i35 = i17;
                    z10 = true;
                } else {
                    i3 = i17;
                    break;
                }
            }
            i3 = i17;
            if (!z7 && i16 == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (i12 > 0 && j != 0) {
                if (i12 < i16 - 1 || z2 || i15 > 1) {
                    float bitCount = (float) Long.bitCount(j);
                    if (!z2) {
                        if ((j & 1) != 0) {
                            if (!((C0515sw) getChildAt(0).getLayoutParams()).f15897e) {
                                bitCount -= 8.0f;
                            }
                        }
                        int i36 = childCount2 - 1;
                        if ((j & ((long) (1 << i36))) != 0 && !((C0515sw) getChildAt(i36).getLayoutParams()).f15897e) {
                            bitCount -= 8.0f;
                        }
                    }
                    if (bitCount > 0.0f) {
                        i4 = (int) (((float) (i12 * i14)) / bitCount);
                    } else {
                        i4 = 0;
                    }
                    for (int i37 = 0; i37 < childCount2; i37++) {
                        if ((j & ((long) (1 << i37))) != 0) {
                            View childAt3 = getChildAt(i37);
                            C0515sw swVar5 = (C0515sw) childAt3.getLayoutParams();
                            if (childAt3 instanceof ActionMenuItemView) {
                                swVar5.f15895c = i4;
                                swVar5.f15898f = true;
                                if (i37 == 0 && !swVar5.f15897e) {
                                    swVar5.leftMargin = (-i4) / 2;
                                }
                                z10 = true;
                            } else if (swVar5.f15893a) {
                                swVar5.f15895c = i4;
                                swVar5.f15898f = true;
                                swVar5.rightMargin = (-i4) / 2;
                                z10 = true;
                            } else {
                                if (i37 != 0) {
                                    swVar5.leftMargin = i4 / 2;
                                }
                                if (i37 != childCount2 - 1) {
                                    swVar5.rightMargin = i4 / 2;
                                }
                            }
                        }
                    }
                }
            }
            if (z10) {
                for (int i38 = 0; i38 < childCount2; i38++) {
                    View childAt4 = getChildAt(i38);
                    C0515sw swVar6 = (C0515sw) childAt4.getLayoutParams();
                    if (swVar6.f15898f) {
                        childAt4.measure(View.MeasureSpec.makeMeasureSpec((swVar6.f15894b * i14) + swVar6.f15895c, 1073741824), childMeasureSpec);
                    }
                }
            }
            setMeasuredDimension(i25, i24 == 1073741824 ? i26 : i3);
            return;
        }
        for (int i39 = 0; i39 < childCount; i39++) {
            C0515sw swVar7 = (C0515sw) getChildAt(i39).getLayoutParams();
            swVar7.rightMargin = 0;
            swVar7.leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    /* renamed from: a */
    public final void mo847a(C0485rt rtVar, C0470re reVar) {
        this.f941k = rtVar;
        this.f937d = reVar;
    }

    /* renamed from: a */
    public final void mo846a(int i) {
        if (this.f940j != i) {
            this.f940j = i;
            if (i != 0) {
                this.f939i = new ContextThemeWrapper(getContext(), i);
            } else {
                this.f939i = getContext();
            }
        }
    }

    /* renamed from: a */
    public final void mo848a(C0512st stVar) {
        this.f936c = stVar;
        stVar.mo10069a(this);
    }
}
