package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.apps.photosgo.R;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: PG */
public class AppBarLayout extends LinearLayout implements abi {

    /* renamed from: a */
    public boolean f5123a;

    /* renamed from: b */
    public int f5124b;

    /* renamed from: c */
    public C0348mr f5125c;

    /* renamed from: d */
    public boolean f5126d;

    /* renamed from: e */
    private int f5127e;

    /* renamed from: f */
    private int f5128f;

    /* renamed from: g */
    private int f5129g;

    /* renamed from: h */
    private int f5130h;

    /* renamed from: i */
    private boolean f5131i;

    /* renamed from: j */
    private boolean f5132j;

    /* renamed from: k */
    private int f5133k;

    /* renamed from: l */
    private WeakReference f5134l;

    /* renamed from: m */
    private ValueAnimator f5135m;

    /* renamed from: n */
    private int[] f5136n;

    /* renamed from: o */
    private Drawable f5137o;

    /* compiled from: PG */
    public class Behavior extends BaseBehavior {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* compiled from: PG */
    public class ScrollingViewBehavior extends gcr {
        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gcs.f10967c);
            this.f10963c = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            obtainStyledAttributes.recycle();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ View mo3606a(List list) {
            return m5023b(list);
        }

        /* renamed from: b */
        private static final AppBarLayout m5023b(List list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = (View) list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        /* renamed from: e */
        public final float mo3607e(View view) {
            int i;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int b = appBarLayout.mo3579b();
                int c = appBarLayout.mo3580c();
                abj abj = ((abm) appBarLayout.getLayoutParams()).f80a;
                int b2 = abj instanceof BaseBehavior ? ((BaseBehavior) abj).mo3601b() : 0;
                if ((c == 0 || b + b2 > c) && (i = b - c) != 0) {
                    return (((float) b2) / ((float) i)) + 1.0f;
                }
            }
            return 0.0f;
        }

        /* renamed from: f */
        public final int mo3608f(View view) {
            if (view instanceof AppBarLayout) {
                return ((AppBarLayout) view).mo3579b();
            }
            return view.getMeasuredHeight();
        }

        /* renamed from: a */
        public final boolean mo87a(View view) {
            return view instanceof AppBarLayout;
        }

        /* renamed from: a */
        public final boolean mo92a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            abj abj = ((abm) view2.getLayoutParams()).f80a;
            if (abj instanceof BaseBehavior) {
                C0340mj.m14708c(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) abj).f5138a) + this.f10962b) - mo6402g(view2));
            }
            if (!(view2 instanceof AppBarLayout)) {
                return false;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            if (!appBarLayout.f5126d) {
                return false;
            }
            appBarLayout.mo3578a(appBarLayout.mo3577a(view));
            return false;
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ boolean mo89a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            View a;
            int i4;
            C0348mr mrVar;
            int i5 = view.getLayoutParams().height;
            if ((i5 != -1 && i5 != -2) || (a = mo3606a(coordinatorLayout.mo1119a(view))) == null) {
                return false;
            }
            int size = View.MeasureSpec.getSize(i3);
            if (size <= 0) {
                size = coordinatorLayout.getHeight();
            } else if (C0340mj.m14725p(a) && (mrVar = coordinatorLayout.f1051c) != null) {
                size += mrVar.mo9409b() + mrVar.mo9411d();
            }
            int f = (size + mo3608f(a)) - a.getMeasuredHeight();
            if (i5 == -1) {
                i4 = 1073741824;
            } else {
                i4 = RecyclerView.UNDEFINED_DURATION;
            }
            coordinatorLayout.mo1121a(view, i, i2, View.MeasureSpec.makeMeasureSpec(f, i4));
            return true;
        }

        /* renamed from: a */
        public final boolean mo90a(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout b = m5023b(coordinatorLayout.mo1119a(view));
            if (b != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.f10961a;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    b.mo3576a(false, !z);
                    return true;
                }
            }
            return false;
        }
    }

    public AppBarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* compiled from: PG */
    public class BaseBehavior extends gcq {

        /* renamed from: a */
        public int f5138a;

        /* renamed from: c */
        private int f5139c;

        /* renamed from: d */
        private ValueAnimator f5140d;

        /* renamed from: e */
        private int f5141e = -1;

        /* renamed from: f */
        private boolean f5142f;

        /* renamed from: g */
        private float f5143g;

        /* renamed from: h */
        private WeakReference f5144h;

        public BaseBehavior() {
        }

        /* renamed from: b */
        private static boolean m5008b(int i, int i2) {
            return (i & i2) == i2;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* renamed from: a */
        private final void m5006a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            int i2;
            int abs = Math.abs(mo3601b() - i);
            float abs2 = Math.abs(0.0f);
            if (abs2 > 0.0f) {
                i2 = Math.round((((float) abs) / abs2) * 1000.0f) * 3;
            } else {
                i2 = (int) (((((float) abs) / ((float) appBarLayout.getHeight())) + 1.0f) * 150.0f);
            }
            int b = mo3601b();
            if (b != i) {
                ValueAnimator valueAnimator = this.f5140d;
                if (valueAnimator == null) {
                    ValueAnimator valueAnimator2 = new ValueAnimator();
                    this.f5140d = valueAnimator2;
                    valueAnimator2.setInterpolator(gci.f10940e);
                    this.f5140d.addUpdateListener(new gcl(this, coordinatorLayout, appBarLayout));
                } else {
                    valueAnimator.cancel();
                }
                this.f5140d.setDuration((long) Math.min(i2, 600));
                this.f5140d.setIntValues(new int[]{b, i});
                this.f5140d.start();
                return;
            }
            ValueAnimator valueAnimator3 = this.f5140d;
            if (valueAnimator3 != null && valueAnimator3.isRunning()) {
                this.f5140d.cancel();
            }
        }

        /* renamed from: g */
        public final /* bridge */ /* synthetic */ boolean mo3605g(View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            WeakReference weakReference = this.f5144h;
            if (weakReference == null) {
                return true;
            }
            View view2 = (View) weakReference.get();
            return view2 != null && view2.isShown() && !view2.canScrollVertically(-1);
        }

        /* renamed from: a */
        private static final View m5004a(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof C0324lu) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        /* renamed from: f */
        public final /* bridge */ /* synthetic */ int mo3604f(View view) {
            return -((AppBarLayout) view).mo3582d();
        }

        /* renamed from: e */
        public final /* bridge */ /* synthetic */ int mo3603e(View view) {
            return ((AppBarLayout) view).mo3579b();
        }

        /* renamed from: b */
        public final int mo3601b() {
            return mo6403c() + this.f5138a;
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo3600a(CoordinatorLayout coordinatorLayout, View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            m5005a(coordinatorLayout, appBarLayout);
            if (appBarLayout.f5126d) {
                appBarLayout.mo3578a(appBarLayout.mo3577a(m5004a(coordinatorLayout)));
            }
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean a = super.mo88a(coordinatorLayout, appBarLayout, i);
            int i2 = appBarLayout.f5124b;
            int i3 = this.f5141e;
            if (i3 >= 0 && (i2 & 8) == 0) {
                View childAt = appBarLayout.getChildAt(i3);
                int i4 = -childAt.getBottom();
                mo6399b(coordinatorLayout, (View) appBarLayout, this.f5142f ? i4 + C0340mj.m14719j(childAt) + appBarLayout.mo3585e() : i4 + Math.round(((float) childAt.getHeight()) * this.f5143g));
            } else if (i2 != 0) {
                int i5 = i2 & 4;
                if ((i2 & 2) != 0) {
                    int i6 = -appBarLayout.mo3579b();
                    if (i5 != 0) {
                        m5006a(coordinatorLayout, appBarLayout, i6);
                    } else {
                        mo6399b(coordinatorLayout, (View) appBarLayout, i6);
                    }
                } else if ((i2 & 1) != 0) {
                    if (i5 == 0) {
                        mo6399b(coordinatorLayout, (View) appBarLayout, 0);
                    } else {
                        m5006a(coordinatorLayout, appBarLayout, 0);
                    }
                }
            }
            appBarLayout.f5124b = 0;
            this.f5141e = -1;
            mo6404c(C0257jh.m14468a(mo6403c(), -appBarLayout.mo3579b(), 0));
            m5007a(coordinatorLayout, appBarLayout, mo6403c(), 0, true);
            appBarLayout.mo3575a(mo6403c());
            return a;
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ boolean mo89a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((abm) appBarLayout.getLayoutParams()).height != -2) {
                return false;
            }
            coordinatorLayout.mo1121a((View) appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0));
            return true;
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo86a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int[] iArr, int i2) {
            int i3;
            int i4;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i != 0) {
                if (i >= 0) {
                    i4 = -appBarLayout.mo3579b();
                    i3 = 0;
                } else {
                    int i5 = -appBarLayout.mo3579b();
                    i4 = i5;
                    i3 = appBarLayout.mo3580c() + i5;
                }
                if (i4 != i3) {
                    iArr[1] = mo6400c(coordinatorLayout, appBarLayout, i, i4, i3);
                }
            }
            if (appBarLayout.f5126d) {
                appBarLayout.mo3578a(appBarLayout.mo3577a(view2));
            }
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo84a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i3 < 0) {
                iArr[1] = mo6400c(coordinatorLayout, appBarLayout, i3, -appBarLayout.mo3582d(), 0);
            }
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo83a(View view, Parcelable parcelable) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (parcelable instanceof gcn) {
                gcn gcn = (gcn) parcelable;
                this.f5141e = gcn.f10946c;
                this.f5143g = gcn.f10947d;
                this.f5142f = gcn.f10948e;
                return;
            }
            this.f5141e = -1;
        }

        /* renamed from: c */
        public final /* bridge */ /* synthetic */ Parcelable mo96c(View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            int c = mo6403c();
            int childCount = appBarLayout.getChildCount();
            boolean z = false;
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + c;
                if (childAt.getTop() + c <= 0 && bottom >= 0) {
                    gcn gcn = new gcn(absSavedState);
                    gcn.f10946c = i;
                    if (bottom == C0340mj.m14719j(childAt) + appBarLayout.mo3585e()) {
                        z = true;
                    }
                    gcn.f10948e = z;
                    gcn.f10947d = ((float) bottom) / ((float) childAt.getHeight());
                    return gcn;
                }
            }
            return absSavedState;
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ boolean mo93a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2) {
            ValueAnimator valueAnimator;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean z = true;
            if ((i & 2) == 0 || (!appBarLayout.f5126d && (appBarLayout.mo3579b() == 0 || coordinatorLayout.getHeight() - view2.getHeight() > appBarLayout.getHeight()))) {
                z = false;
            }
            if (z && (valueAnimator = this.f5140d) != null) {
                valueAnimator.cancel();
            }
            this.f5144h = null;
            this.f5139c = i2;
            return z;
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo85a(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.f5139c == 0 || i == 1) {
                m5005a(coordinatorLayout, appBarLayout);
                if (appBarLayout.f5126d) {
                    appBarLayout.mo3578a(appBarLayout.mo3577a(view2));
                }
            }
            this.f5144h = new WeakReference(view2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:39:0x00b3  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x00d3  */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x00d5  */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* bridge */ /* synthetic */ int mo3602b(androidx.coordinatorlayout.widget.CoordinatorLayout r8, android.view.View r9, int r10, int r11, int r12) {
            /*
                r7 = this;
                com.google.android.material.appbar.AppBarLayout r9 = (com.google.android.material.appbar.AppBarLayout) r9
                int r0 = r7.mo3601b()
                r1 = 0
                if (r11 != 0) goto L_0x000b
                goto L_0x00dd
            L_0x000b:
                if (r0 < r11) goto L_0x00dd
                if (r0 > r12) goto L_0x00dd
                int r10 = p000.C0257jh.m14468a((int) r10, (int) r11, (int) r12)
                if (r0 != r10) goto L_0x0017
                goto L_0x00e0
            L_0x0017:
                boolean r11 = r9.f5123a
                if (r11 == 0) goto L_0x008b
                int r11 = java.lang.Math.abs(r10)
                int r12 = r9.getChildCount()
                r2 = 0
            L_0x0024:
                if (r2 >= r12) goto L_0x008b
                android.view.View r3 = r9.getChildAt(r2)
                android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
                gco r4 = (p000.gco) r4
                android.view.animation.Interpolator r5 = r4.f10950b
                int r6 = r3.getTop()
                if (r11 >= r6) goto L_0x0039
            L_0x0038:
                goto L_0x0088
            L_0x0039:
                int r6 = r3.getBottom()
                if (r11 > r6) goto L_0x0038
                if (r5 == 0) goto L_0x008b
                int r12 = r4.f10949a
                r2 = r12 & 1
                if (r2 == 0) goto L_0x005c
                int r2 = r3.getHeight()
                int r6 = r4.topMargin
                int r2 = r2 + r6
                int r4 = r4.bottomMargin
                int r2 = r2 + r4
                r12 = r12 & 2
                if (r12 == 0) goto L_0x005b
                int r12 = p000.C0340mj.m14719j(r3)
                int r2 = r2 - r12
                goto L_0x005d
            L_0x005b:
                goto L_0x005d
            L_0x005c:
                r2 = 0
            L_0x005d:
                boolean r12 = p000.C0340mj.m14725p(r3)
                if (r12 == 0) goto L_0x0068
                int r12 = r9.mo3585e()
                int r2 = r2 - r12
            L_0x0068:
                if (r2 <= 0) goto L_0x008b
                int r12 = r3.getTop()
                float r2 = (float) r2
                int r11 = r11 - r12
                float r11 = (float) r11
                float r11 = r11 / r2
                float r11 = r5.getInterpolation(r11)
                float r2 = r2 * r11
                int r11 = java.lang.Math.round(r2)
                int r12 = java.lang.Integer.signum(r10)
                int r2 = r3.getTop()
                int r2 = r2 + r11
                int r12 = r12 * r2
                goto L_0x008d
            L_0x0088:
                int r2 = r2 + 1
                goto L_0x0024
            L_0x008b:
                r12 = r10
            L_0x008d:
                boolean r11 = r7.mo6404c(r12)
                int r2 = r0 - r10
                int r12 = r10 - r12
                r7.f5138a = r12
                if (r11 == 0) goto L_0x009a
                goto L_0x00ca
            L_0x009a:
                boolean r11 = r9.f5123a
                if (r11 == 0) goto L_0x00ca
                abr r11 = r8.f1049a
                java.util.List r11 = r11.mo120b(r9)
                if (r11 == 0) goto L_0x00ca
                boolean r12 = r11.isEmpty()
                if (r12 != 0) goto L_0x00ca
                r12 = 0
            L_0x00ad:
                int r3 = r11.size()
                if (r12 >= r3) goto L_0x00ca
                java.lang.Object r3 = r11.get(r12)
                android.view.View r3 = (android.view.View) r3
                android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
                abm r4 = (p000.abm) r4
                abj r4 = r4.f80a
                if (r4 != 0) goto L_0x00c4
                goto L_0x00c7
            L_0x00c4:
                r4.mo92a((androidx.coordinatorlayout.widget.CoordinatorLayout) r8, (android.view.View) r3, (android.view.View) r9)
            L_0x00c7:
                int r12 = r12 + 1
                goto L_0x00ad
            L_0x00ca:
                int r11 = r7.mo6403c()
                r9.mo3575a((int) r11)
                if (r10 >= r0) goto L_0x00d5
                r11 = -1
                goto L_0x00d7
            L_0x00d5:
                r11 = 1
            L_0x00d7:
                m5007a((androidx.coordinatorlayout.widget.CoordinatorLayout) r8, (com.google.android.material.appbar.AppBarLayout) r9, (int) r10, (int) r11, (boolean) r1)
                r1 = r2
                goto L_0x00e0
            L_0x00dd:
                r7.f5138a = r1
            L_0x00e0:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.mo3602b(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int, int, int):int");
        }

        /* renamed from: a */
        private final void m5005a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int b = mo3601b();
            int childCount = appBarLayout.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    i = -1;
                    break;
                }
                View childAt = appBarLayout.getChildAt(i);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                gco gco = (gco) childAt.getLayoutParams();
                if (m5008b(gco.f10949a, 32)) {
                    top -= gco.topMargin;
                    bottom += gco.bottomMargin;
                }
                int i2 = -b;
                if (top <= i2 && bottom >= i2) {
                    break;
                }
                i++;
            }
            if (i >= 0) {
                View childAt2 = appBarLayout.getChildAt(i);
                gco gco2 = (gco) childAt2.getLayoutParams();
                int i3 = gco2.f10949a;
                if ((i3 & 17) == 17) {
                    int i4 = -childAt2.getTop();
                    int i5 = -childAt2.getBottom();
                    if (i == appBarLayout.getChildCount() - 1) {
                        i5 += appBarLayout.mo3585e();
                    }
                    if (m5008b(i3, 2)) {
                        i5 += C0340mj.m14719j(childAt2);
                    } else if (m5008b(i3, 5)) {
                        int j = C0340mj.m14719j(childAt2) + i5;
                        if (b >= j) {
                            i5 = j;
                        } else {
                            i4 = j;
                        }
                    }
                    if (m5008b(i3, 32)) {
                        i4 += gco2.topMargin;
                        i5 -= gco2.bottomMargin;
                    }
                    if (b < (i5 + i4) / 2) {
                        i4 = i5;
                    }
                    m5006a(coordinatorLayout, appBarLayout, C0257jh.m14468a(i4, -appBarLayout.mo3579b(), 0));
                }
            }
        }

        /* renamed from: a */
        private static final void m5007a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, boolean z) {
            View view;
            int abs = Math.abs(i);
            int childCount = appBarLayout.getChildCount();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    view = null;
                    break;
                }
                view = appBarLayout.getChildAt(i4);
                if (abs >= view.getTop() && abs <= view.getBottom()) {
                    break;
                }
                i4++;
            }
            if (view != null) {
                int i5 = ((gco) view.getLayoutParams()).f10949a;
                boolean z2 = true;
                if ((i5 & 1) != 0) {
                    int j = C0340mj.m14719j(view);
                    if (i2 > 0) {
                    }
                }
                z2 = false;
                if (appBarLayout.f5126d) {
                    z2 = appBarLayout.mo3577a(m5004a(coordinatorLayout));
                }
                boolean a = appBarLayout.mo3578a(z2);
                if (!z) {
                    if (a) {
                        List b = coordinatorLayout.f1049a.mo120b(appBarLayout);
                        coordinatorLayout.f1050b.clear();
                        if (b != null) {
                            coordinatorLayout.f1050b.addAll(b);
                        }
                        List list = coordinatorLayout.f1050b;
                        int size = list.size();
                        while (i3 < size) {
                            abj abj = ((abm) ((View) list.get(i3)).getLayoutParams()).f80a;
                            if (!(abj instanceof ScrollingViewBehavior)) {
                                i3++;
                            } else if (((ScrollingViewBehavior) abj).f10963c == 0) {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                appBarLayout.jumpDrawablesToCurrentState();
            }
        }
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.appBarLayoutStyle);
    }

    public AppBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5128f = -1;
        this.f5129g = -1;
        this.f5130h = -1;
        this.f5124b = 0;
        boolean z = true;
        setOrientation(1);
        int i2 = Build.VERSION.SDK_INT;
        gcv.m10036a(this);
        gcv.m10038a(this, attributeSet, i);
        TypedArray a = gga.m10238a(context, attributeSet, gcs.f10965a, i, 2131952403, new int[0]);
        C0340mj.m14694a((View) this, a.getDrawable(0));
        if (getBackground() instanceof ColorDrawable) {
            ggu ggu = new ggu();
            ggu.mo6635a(ColorStateList.valueOf(((ColorDrawable) getBackground()).getColor()));
            ggu.mo6634a(context);
            C0340mj.m14694a((View) this, (Drawable) ggu);
        }
        if (a.hasValue(4)) {
            m4989a(a.getBoolean(4, false), false, false);
        }
        int i3 = Build.VERSION.SDK_INT;
        if (a.hasValue(3)) {
            gcv.m10037a(this, (float) a.getDimensionPixelSize(3, 0));
        }
        int i4 = Build.VERSION.SDK_INT;
        if (a.hasValue(2)) {
            setKeyboardNavigationCluster(a.getBoolean(2, false));
        }
        if (a.hasValue(1)) {
            setTouchscreenBlocksFocus(a.getBoolean(1, false));
        }
        this.f5126d = a.getBoolean(5, false);
        this.f5133k = a.getResourceId(6, -1);
        Drawable drawable = a.getDrawable(7);
        Drawable drawable2 = this.f5137o;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
            }
            drawable3 = drawable != null ? drawable.mutate() : drawable3;
            this.f5137o = drawable3;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.f5137o.setState(getDrawableState());
                }
                C0257jh.m14486b(this.f5137o, C0340mj.m14714f(this));
                this.f5137o.setVisible(getVisibility() != 0 ? false : z, false);
                this.f5137o.setCallback(this);
            }
            C0340mj.m14710d(this);
        }
        a.recycle();
        C0340mj.m14699a((View) this, (C0329lz) new gcj(this));
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof gco;
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f5137o != null && mo3585e() > 0) {
            int save = canvas.save();
            canvas.translate(0.0f, (float) (-this.f5127e));
            this.f5137o.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f5137o;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    /* renamed from: j */
    private static final gco m4992j() {
        return new gco();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final gco generateLayoutParams(AttributeSet attributeSet) {
        return new gco(getContext(), attributeSet);
    }

    /* renamed from: a */
    private static final gco m4988a(ViewGroup.LayoutParams layoutParams) {
        int i = Build.VERSION.SDK_INT;
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new gco((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new gco((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new gco(layoutParams);
    }

    /* renamed from: a */
    public final abj mo80a() {
        return new Behavior();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final int mo3580c() {
        int i;
        int i2 = this.f5129g;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            gco gco = (gco) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = gco.f10949a;
            if ((i4 & 5) == 5) {
                int i5 = gco.topMargin + gco.bottomMargin;
                if ((i4 & 8) != 0) {
                    i = i5 + C0340mj.m14719j(childAt);
                } else {
                    i = (i4 & 2) != 0 ? i5 + (measuredHeight - C0340mj.m14719j(childAt)) : i5 + measuredHeight;
                }
                if (childCount == 0 && C0340mj.m14725p(childAt)) {
                    i = Math.min(i, measuredHeight - mo3585e());
                }
                i3 += i;
            } else if (i3 > 0) {
                break;
            }
        }
        int max = Math.max(0, i3);
        this.f5129g = max;
        return max;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final int mo3582d() {
        int i = this.f5130h;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            gco gco = (gco) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + gco.topMargin + gco.bottomMargin;
            int i4 = gco.f10949a;
            if ((i4 & 1) == 0) {
                break;
            }
            i3 += measuredHeight;
            if ((i4 & 2) != 0) {
                i3 -= C0340mj.m14719j(childAt);
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.f5130h = max;
        return max;
    }

    /* renamed from: e */
    public final int mo3585e() {
        C0348mr mrVar = this.f5125c;
        if (mrVar == null) {
            return 0;
        }
        return mrVar.mo9409b();
    }

    /* renamed from: b */
    public final int mo3579b() {
        int i = this.f5128f;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            gco gco = (gco) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = gco.f10949a;
            if ((i4 & 1) == 0) {
                break;
            }
            i3 += measuredHeight + gco.topMargin + gco.bottomMargin;
            if (i2 == 0 && C0340mj.m14725p(childAt)) {
                i3 -= mo3585e();
            }
            if ((i4 & 2) != 0) {
                i3 -= C0340mj.m14719j(childAt);
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.f5128f = max;
        return max;
    }

    /* renamed from: h */
    private final void m4990h() {
        this.f5128f = -1;
        this.f5129g = -1;
        this.f5130h = -1;
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof ggu) {
            ggq.m10281a(this, (ggu) background);
        }
    }

    /* access modifiers changed from: protected */
    public final int[] onCreateDrawableState(int i) {
        int i2;
        int i3;
        if (this.f5136n == null) {
            this.f5136n = new int[4];
        }
        int[] iArr = this.f5136n;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z = this.f5131i;
        if (!z) {
            i2 = -2130969265;
        } else {
            i2 = R.attr.state_liftable;
        }
        iArr[0] = i2;
        int i4 = -2130969266;
        if (z && this.f5132j) {
            i4 = R.attr.state_lifted;
        }
        iArr[1] = i4;
        if (!z) {
            i3 = -2130969263;
        } else {
            i3 = R.attr.state_collapsible;
        }
        iArr[2] = i3;
        int i5 = -2130969262;
        if (z && this.f5132j) {
            i5 = R.attr.state_collapsed;
        }
        iArr[3] = i5;
        return mergeDrawableStates(onCreateDrawableState, iArr);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference weakReference = this.f5134l;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f5134l = null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        /*
            r1 = this;
            super.onLayout(r2, r3, r4, r5, r6)
            boolean r2 = p000.C0340mj.m14725p(r1)
            if (r2 == 0) goto L_0x0025
            boolean r2 = r1.m4991i()
            if (r2 == 0) goto L_0x0025
            int r2 = r1.mo3585e()
            int r3 = r1.getChildCount()
            int r3 = r3 + -1
        L_0x0019:
            if (r3 < 0) goto L_0x0025
            android.view.View r4 = r1.getChildAt(r3)
            p000.C0340mj.m14708c(r4, r2)
            int r3 = r3 + -1
            goto L_0x0019
        L_0x0025:
            r1.m4990h()
            r2 = 0
            r1.f5123a = r2
            int r3 = r1.getChildCount()
            r4 = 0
        L_0x0030:
            r5 = 1
            if (r4 >= r3) goto L_0x0046
            android.view.View r6 = r1.getChildAt(r4)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            gco r6 = (p000.gco) r6
            android.view.animation.Interpolator r6 = r6.f10950b
            if (r6 != 0) goto L_0x0044
            int r4 = r4 + 1
            goto L_0x0030
        L_0x0044:
            r1.f5123a = r5
        L_0x0046:
            android.graphics.drawable.Drawable r3 = r1.f5137o
            if (r3 == 0) goto L_0x0055
            int r4 = r1.getWidth()
            int r6 = r1.mo3585e()
            r3.setBounds(r2, r2, r4, r6)
        L_0x0055:
            boolean r3 = r1.f5126d
            if (r3 != 0) goto L_0x007b
            int r3 = r1.getChildCount()
            r4 = 0
        L_0x005e:
            if (r4 >= r3) goto L_0x0079
            android.view.View r6 = r1.getChildAt(r4)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            gco r6 = (p000.gco) r6
            int r6 = r6.f10949a
            r0 = r6 & 1
            if (r0 == r5) goto L_0x0071
        L_0x0070:
            goto L_0x0076
        L_0x0071:
            r6 = r6 & 10
            if (r6 == 0) goto L_0x0070
            goto L_0x007b
        L_0x0076:
            int r4 = r4 + 1
            goto L_0x005e
        L_0x0079:
            goto L_0x007c
        L_0x007b:
            r2 = 1
        L_0x007c:
            boolean r3 = r1.f5131i
            if (r3 == r2) goto L_0x0085
            r1.f5131i = r2
            r1.refreshDrawableState()
        L_0x0085:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && C0340mj.m14725p(this) && m4991i()) {
            int measuredHeight = getMeasuredHeight();
            if (mode == Integer.MIN_VALUE) {
                measuredHeight = C0257jh.m14468a(getMeasuredHeight() + mo3585e(), 0, View.MeasureSpec.getSize(i2));
            } else if (mode == 0) {
                measuredHeight += mo3585e();
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
        m4990h();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo3575a(int i) {
        this.f5127e = i;
        if (!willNotDraw()) {
            C0340mj.m14710d(this);
        }
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        Drawable background = getBackground();
        if (background instanceof ggu) {
            ((ggu) background).mo6637b(f);
        }
    }

    /* renamed from: f */
    public final void mo3586f() {
        mo3576a(true, C0340mj.m14732w(this));
    }

    /* renamed from: a */
    public final void mo3576a(boolean z, boolean z2) {
        m4989a(z, z2, true);
    }

    /* renamed from: a */
    private final void m4989a(boolean z, boolean z2, boolean z3) {
        int i = 0;
        int i2 = (!z ? 2 : 1) | (!z2 ? 0 : 4);
        if (z3) {
            i = 8;
        }
        this.f5124b = i2 | i;
        requestLayout();
    }

    /* renamed from: g */
    public final void mo3587g() {
        this.f5126d = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo3578a(boolean z) {
        if (this.f5132j == z) {
            return false;
        }
        this.f5132j = z;
        refreshDrawableState();
        if (this.f5126d && (getBackground() instanceof ggu)) {
            ggu ggu = (ggu) getBackground();
            float dimension = getResources().getDimension(R.dimen.design_appbar_elevation);
            float f = !z ? dimension : 0.0f;
            if (!z) {
                dimension = 0.0f;
            }
            ValueAnimator valueAnimator = this.f5135m;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, dimension});
            this.f5135m = ofFloat;
            ofFloat.setDuration((long) getResources().getInteger(R.integer.app_bar_elevation_anim_duration));
            this.f5135m.setInterpolator(gci.f10936a);
            this.f5135m.addUpdateListener(new gck(ggu));
            this.f5135m.start();
        }
        return true;
    }

    public final void setOrientation(int i) {
        if (i == 1) {
            super.setOrientation(1);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    public final void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f5137o;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.view.View} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo3577a(android.view.View r5) {
        /*
            r4 = this;
            java.lang.ref.WeakReference r0 = r4.f5134l
            r1 = 0
            r2 = -1
            if (r0 != 0) goto L_0x0033
            int r0 = r4.f5133k
            if (r0 != r2) goto L_0x000b
            goto L_0x0033
        L_0x000b:
            if (r5 == 0) goto L_0x0012
            android.view.View r0 = r5.findViewById(r0)
            goto L_0x0014
        L_0x0012:
            r0 = r1
        L_0x0014:
            if (r0 != 0) goto L_0x002a
            android.view.ViewParent r3 = r4.getParent()
            boolean r3 = r3 instanceof android.view.ViewGroup
            if (r3 == 0) goto L_0x002a
            android.view.ViewParent r0 = r4.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            int r3 = r4.f5133k
            android.view.View r0 = r0.findViewById(r3)
        L_0x002a:
            if (r0 == 0) goto L_0x0033
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            r3.<init>(r0)
            r4.f5134l = r3
        L_0x0033:
            java.lang.ref.WeakReference r0 = r4.f5134l
            if (r0 == 0) goto L_0x003f
            java.lang.Object r0 = r0.get()
            r1 = r0
            android.view.View r1 = (android.view.View) r1
            goto L_0x0040
        L_0x003f:
        L_0x0040:
            if (r1 == 0) goto L_0x0043
            r5 = r1
        L_0x0043:
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L_0x0055
            boolean r2 = r5.canScrollVertically(r2)
            if (r2 != 0) goto L_0x0056
            int r5 = r5.getScrollY()
            if (r5 > 0) goto L_0x0054
            goto L_0x0055
        L_0x0054:
            return r0
        L_0x0055:
            r0 = 0
        L_0x0056:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.mo3577a(android.view.View):boolean");
    }

    /* renamed from: i */
    private final boolean m4991i() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() == 8 || C0340mj.m14725p(childAt)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f5137o;
    }
}
