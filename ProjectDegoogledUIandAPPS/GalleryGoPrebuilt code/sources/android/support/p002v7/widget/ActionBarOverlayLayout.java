package android.support.p002v7.widget;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import com.google.android.apps.photosgo.R;

/* renamed from: android.support.v7.widget.ActionBarOverlayLayout */
/* compiled from: PG */
public class ActionBarOverlayLayout extends ViewGroup implements C0565us, C0326lw, C0327lx {

    /* renamed from: A */
    private static final int[] f906A = {R.attr.actionBarSize, 16842841};

    /* renamed from: B */
    private final C0328ly f907B;

    /* renamed from: a */
    public int f908a;

    /* renamed from: b */
    public ActionBarContainer f909b;

    /* renamed from: c */
    public boolean f910c;

    /* renamed from: d */
    public boolean f911d;

    /* renamed from: e */
    public boolean f912e;

    /* renamed from: f */
    public int f913f;

    /* renamed from: g */
    public C0504sl f914g;

    /* renamed from: h */
    public ViewPropertyAnimator f915h;

    /* renamed from: i */
    public final AnimatorListenerAdapter f916i;

    /* renamed from: j */
    private int f917j;

    /* renamed from: k */
    private ContentFrameLayout f918k;

    /* renamed from: l */
    private C0566ut f919l;

    /* renamed from: m */
    private Drawable f920m;

    /* renamed from: n */
    private boolean f921n;

    /* renamed from: o */
    private boolean f922o;

    /* renamed from: p */
    private int f923p;

    /* renamed from: q */
    private final Rect f924q;

    /* renamed from: r */
    private final Rect f925r;

    /* renamed from: s */
    private final Rect f926s;

    /* renamed from: t */
    private final Rect f927t;

    /* renamed from: u */
    private final Rect f928u;

    /* renamed from: v */
    private final Rect f929v;

    /* renamed from: w */
    private final Rect f930w;

    /* renamed from: x */
    private OverScroller f931x;

    /* renamed from: y */
    private final Runnable f932y;

    /* renamed from: z */
    private final Runnable f933z;

    /* renamed from: a */
    public final void mo707a(View view, int i, int i2, int[] iArr, int i3) {
    }

    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f908a = 0;
        this.f924q = new Rect();
        this.f925r = new Rect();
        this.f926s = new Rect();
        this.f927t = new Rect();
        this.f928u = new Rect();
        this.f929v = new Rect();
        this.f930w = new Rect();
        this.f916i = new C0501si(this);
        this.f932y = new C0502sj(this);
        this.f933z = new C0503sk(this);
        m870a(context);
        this.f907B = new C0328ly();
    }

    /* renamed from: a */
    private static final boolean m871a(View view, Rect rect, boolean z) {
        boolean z2;
        C0505sm smVar = (C0505sm) view.getLayoutParams();
        if (smVar.leftMargin != rect.left) {
            smVar.leftMargin = rect.left;
            z2 = true;
        } else {
            z2 = false;
        }
        if (smVar.topMargin != rect.top) {
            smVar.topMargin = rect.top;
            z2 = true;
        }
        if (smVar.rightMargin != rect.right) {
            smVar.rightMargin = rect.right;
            z2 = true;
        }
        if (!z || smVar.bottomMargin == rect.bottom) {
            return z2;
        }
        smVar.bottomMargin = rect.bottom;
        return true;
    }

    /* renamed from: b */
    public final boolean mo815b() {
        m873i();
        return this.f919l.mo10336g();
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0505sm;
    }

    /* renamed from: h */
    public final void mo828h() {
        m873i();
        this.f919l.mo10342m();
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f920m != null && !this.f921n) {
            int bottom = this.f909b.getVisibility() == 0 ? (int) (((float) this.f909b.getBottom()) + this.f909b.getTranslationY() + 0.5f) : 0;
            this.f920m.setBounds(0, bottom, getWidth(), this.f920m.getIntrinsicHeight() + bottom);
            this.f920m.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0040, code lost:
        if (r0 != false) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean fitSystemWindows(android.graphics.Rect r4) {
        /*
            r3 = this;
            r3.m873i()
            p000.C0340mj.m14723n(r3)
            android.support.v7.widget.ActionBarContainer r0 = r3.f909b
            r1 = 0
            boolean r0 = m871a(r0, r4, r1)
            android.graphics.Rect r1 = r3.f927t
            r1.set(r4)
            android.graphics.Rect r4 = r3.f927t
            android.graphics.Rect r1 = r3.f924q
            p000.C0703zv.m16279a(r3, r4, r1)
            android.graphics.Rect r4 = r3.f928u
            android.graphics.Rect r1 = r3.f927t
            boolean r4 = r4.equals(r1)
            r1 = 1
            if (r4 != 0) goto L_0x002d
            android.graphics.Rect r4 = r3.f928u
            android.graphics.Rect r0 = r3.f927t
            r4.set(r0)
            r0 = 1
            goto L_0x002e
        L_0x002d:
        L_0x002e:
            android.graphics.Rect r4 = r3.f925r
            android.graphics.Rect r2 = r3.f924q
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0040
            android.graphics.Rect r4 = r3.f925r
            android.graphics.Rect r0 = r3.f924q
            r4.set(r0)
            goto L_0x0042
        L_0x0040:
            if (r0 == 0) goto L_0x0045
        L_0x0042:
            r3.requestLayout()
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.ActionBarOverlayLayout.fitSystemWindows(android.graphics.Rect):boolean");
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C0505sm();
    }

    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0505sm(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0505sm(layoutParams);
    }

    public final int getNestedScrollAxes() {
        return this.f907B.mo9379a();
    }

    /* renamed from: a */
    public final void mo809a() {
        removeCallbacks(this.f932y);
        removeCallbacks(this.f933z);
        ViewPropertyAnimator viewPropertyAnimator = this.f915h;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    /* renamed from: f */
    public final boolean mo821f() {
        m873i();
        return this.f919l.mo10340k();
    }

    /* renamed from: a */
    private final void m870a(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f906A);
        boolean z = false;
        this.f917j = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        this.f920m = drawable;
        setWillNotDraw(drawable == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19) {
            z = true;
        }
        this.f921n = z;
        this.f931x = new OverScroller(context);
    }

    /* renamed from: a */
    public final void mo810a(int i) {
        m873i();
        if (i == 2) {
            this.f919l.mo10334e();
        } else if (i == 5) {
            this.f919l.mo10335f();
        } else if (i == 109) {
            boolean z = true;
            this.f910c = true;
            if (getContext().getApplicationInfo().targetSdkVersion >= 19) {
                z = false;
            }
            this.f921n = z;
        }
    }

    /* renamed from: d */
    public final boolean mo818d() {
        m873i();
        return this.f919l.mo10338i();
    }

    /* renamed from: c */
    public final boolean mo816c() {
        m873i();
        return this.f919l.mo10337h();
    }

    /* access modifiers changed from: protected */
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m870a(getContext());
        C0340mj.m14724o(this);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mo809a();
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                C0505sm smVar = (C0505sm) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = smVar.leftMargin + paddingLeft;
                int i7 = smVar.topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        m873i();
        measureChildWithMargins(this.f909b, i, 0, i2, 0);
        C0505sm smVar = (C0505sm) this.f909b.getLayoutParams();
        int i3 = 0;
        int max = Math.max(0, this.f909b.getMeasuredWidth() + smVar.leftMargin + smVar.rightMargin);
        int max2 = Math.max(0, this.f909b.getMeasuredHeight() + smVar.topMargin + smVar.bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.f909b.getMeasuredState());
        int n = C0340mj.m14723n(this) & 256;
        if (n != 0) {
            i3 = this.f917j;
        } else if (this.f909b.getVisibility() != 8) {
            i3 = this.f909b.getMeasuredHeight();
        }
        this.f926s.set(this.f924q);
        this.f929v.set(this.f927t);
        if (!this.f910c && n == 0) {
            this.f926s.top += i3;
            Rect rect = this.f926s;
            rect.bottom = rect.bottom;
        } else {
            this.f929v.top += i3;
            Rect rect2 = this.f929v;
            rect2.bottom = rect2.bottom;
        }
        m871a(this.f918k, this.f926s, true);
        if (!this.f930w.equals(this.f929v)) {
            this.f930w.set(this.f929v);
            this.f918k.fitSystemWindows(this.f929v);
        }
        measureChildWithMargins(this.f918k, i, 0, i2, 0);
        C0505sm smVar2 = (C0505sm) this.f918k.getLayoutParams();
        int max3 = Math.max(max, this.f918k.getMeasuredWidth() + smVar2.leftMargin + smVar2.rightMargin);
        int max4 = Math.max(max2, this.f918k.getMeasuredHeight() + smVar2.topMargin + smVar2.bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.f918k.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, combineMeasuredStates2), View.resolveSizeAndState(Math.max(max4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, combineMeasuredStates2 << 16));
    }

    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.f922o || !z) {
            return false;
        }
        this.f931x.fling(0, 0, 0, (int) f2, 0, 0, RecyclerView.UNDEFINED_DURATION, Integer.MAX_VALUE);
        if (this.f931x.getFinalY() > this.f909b.getHeight()) {
            mo809a();
            this.f933z.run();
        } else {
            mo809a();
            this.f932y.run();
        }
        this.f912e = true;
        return true;
    }

    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.f923p + i2;
        this.f923p = i5;
        m872b(i5);
    }

    /* renamed from: a */
    public final void mo705a(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    /* renamed from: a */
    public final void mo706a(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        mo705a(view, i, i2, i3, i4, i5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001c, code lost:
        r1 = (p000.C0434pw) r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onNestedScrollAccepted(android.view.View r1, android.view.View r2, int r3) {
        /*
            r0 = this;
            ly r1 = r0.f907B
            r2 = 0
            r1.mo9381a(r3, r2)
            android.support.v7.widget.ActionBarContainer r1 = r0.f909b
            if (r1 == 0) goto L_0x0011
            float r1 = r1.getTranslationY()
            int r1 = (int) r1
            int r2 = -r1
            goto L_0x0013
        L_0x0011:
        L_0x0013:
            r0.f923p = r2
            r0.mo809a()
            sl r1 = r0.f914g
            if (r1 == 0) goto L_0x0028
            pw r1 = (p000.C0434pw) r1
            qo r2 = r1.f15561n
            if (r2 == 0) goto L_0x0028
            r2.mo9731b()
            r2 = 0
            r1.f15561n = r2
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.ActionBarOverlayLayout.onNestedScrollAccepted(android.view.View, android.view.View, int):void");
    }

    /* renamed from: b */
    public final void mo714b(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f909b.getVisibility() != 0) {
            return false;
        }
        return this.f922o;
    }

    /* renamed from: a */
    public final boolean mo709a(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    public final void onStopNestedScroll(View view) {
        if (this.f922o && !this.f912e) {
            if (this.f923p <= this.f909b.getHeight()) {
                mo809a();
                postDelayed(this.f932y, 600);
                return;
            }
            mo809a();
            postDelayed(this.f933z, 600);
        }
    }

    /* renamed from: a */
    public final void mo704a(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    public final void onWindowSystemUiVisibilityChanged(int i) {
        int i2 = Build.VERSION.SDK_INT;
        super.onWindowSystemUiVisibilityChanged(i);
        m873i();
        int i3 = this.f913f ^ i;
        this.f913f = i;
        int i4 = i & 4;
        int i5 = i & 256;
        C0504sl slVar = this.f914g;
        if (slVar != null) {
            C0434pw pwVar = (C0434pw) slVar;
            pwVar.f15558k = i5 == 0;
            if (i4 == 0 || i5 == 0) {
                if (pwVar.f15560m) {
                    pwVar.f15560m = false;
                    pwVar.mo9656f(true);
                }
            } else if (!pwVar.f15560m) {
                pwVar.f15560m = true;
                pwVar.mo9656f(true);
            }
        }
        if ((i3 & 256) != 0 && this.f914g != null) {
            C0340mj.m14724o(this);
        }
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f908a = i;
        C0504sl slVar = this.f914g;
        if (slVar != null) {
            ((C0434pw) slVar).f15557j = i;
        }
    }

    /* renamed from: i */
    private final void m873i() {
        C0566ut utVar;
        if (this.f918k == null) {
            this.f918k = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.f909b = (ActionBarContainer) findViewById(R.id.action_bar_container);
            View findViewById = findViewById(R.id.action_bar);
            if (findViewById instanceof C0566ut) {
                utVar = (C0566ut) findViewById;
            } else if (findViewById instanceof Toolbar) {
                utVar = ((Toolbar) findViewById).mo1104h();
            } else {
                throw new IllegalStateException("Can't make a decor toolbar out of " + findViewById.getClass().getSimpleName());
            }
            this.f919l = utVar;
        }
    }

    /* renamed from: b */
    private final void m872b(int i) {
        mo809a();
        this.f909b.setTranslationY((float) (-Math.max(0, Math.min(i, this.f909b.getHeight()))));
    }

    /* renamed from: a */
    public final void mo814a(boolean z) {
        if (z != this.f922o) {
            this.f922o = z;
            if (!z) {
                mo809a();
                m872b(0);
            }
        }
    }

    /* renamed from: a */
    public final void mo811a(Menu menu, C0485rt rtVar) {
        m873i();
        this.f919l.mo10326a(menu, rtVar);
    }

    /* renamed from: g */
    public final void mo823g() {
        m873i();
        this.f919l.mo10341l();
    }

    /* renamed from: a */
    public final void mo812a(Window.Callback callback) {
        m873i();
        this.f919l.mo10327a(callback);
    }

    /* renamed from: a */
    public final void mo813a(CharSequence charSequence) {
        m873i();
        this.f919l.mo10328a(charSequence);
    }

    /* renamed from: e */
    public final boolean mo820e() {
        m873i();
        return this.f919l.mo10339j();
    }
}
