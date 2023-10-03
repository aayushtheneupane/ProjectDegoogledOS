package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.apps.photosgo.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
public class BottomSheetBehavior extends abj {

    /* renamed from: A */
    private float f5157A = -1.0f;

    /* renamed from: B */
    private boolean f5158B;

    /* renamed from: C */
    private int f5159C;

    /* renamed from: D */
    private boolean f5160D;

    /* renamed from: E */
    private int f5161E;

    /* renamed from: F */
    private VelocityTracker f5162F;

    /* renamed from: G */
    private int f5163G;

    /* renamed from: H */
    private Map f5164H;

    /* renamed from: I */
    private final C0379nv f5165I = new gdf(this);

    /* renamed from: a */
    public boolean f5166a = true;

    /* renamed from: b */
    public int f5167b;

    /* renamed from: c */
    public ggu f5168c;

    /* renamed from: d */
    public int f5169d;

    /* renamed from: e */
    public int f5170e;

    /* renamed from: f */
    public int f5171f;

    /* renamed from: g */
    public int f5172g;

    /* renamed from: h */
    public boolean f5173h;

    /* renamed from: i */
    public boolean f5174i;

    /* renamed from: j */
    public int f5175j = 4;

    /* renamed from: k */
    public C0380nw f5176k;

    /* renamed from: l */
    public int f5177l;

    /* renamed from: m */
    public WeakReference f5178m;

    /* renamed from: n */
    public WeakReference f5179n;

    /* renamed from: o */
    public final ArrayList f5180o = new ArrayList();

    /* renamed from: p */
    public int f5181p;

    /* renamed from: q */
    public boolean f5182q;

    /* renamed from: r */
    private int f5183r = 0;

    /* renamed from: s */
    private float f5184s;

    /* renamed from: t */
    private boolean f5185t;

    /* renamed from: u */
    private int f5186u;

    /* renamed from: v */
    private boolean f5187v;

    /* renamed from: w */
    private gha f5188w;

    /* renamed from: x */
    private boolean f5189x;

    /* renamed from: y */
    private ValueAnimator f5190y;

    /* renamed from: z */
    private float f5191z = 0.5f;

    public BottomSheetBehavior() {
    }

    /* renamed from: a */
    public final void mo84a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
    }

    /* renamed from: b */
    public final int mo3613b() {
        return this.f5166a ? this.f5170e : this.f5169d;
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gdp.f11036a);
        this.f5187v = obtainStyledAttributes.hasValue(9);
        if (obtainStyledAttributes.hasValue(1)) {
            m5042a(context, attributeSet, true, gqb.m10615a(context, obtainStyledAttributes, 1));
        } else {
            m5042a(context, attributeSet, false, (ColorStateList) null);
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.f5190y = ofFloat;
        ofFloat.setDuration(500);
        this.f5190y.addUpdateListener(new gde(this));
        int i = Build.VERSION.SDK_INT;
        this.f5157A = obtainStyledAttributes.getDimension(0, -1.0f);
        TypedValue peekValue = obtainStyledAttributes.peekValue(6);
        if (peekValue == null || peekValue.data != -1) {
            m5050f(obtainStyledAttributes.getDimensionPixelSize(6, -1));
        } else {
            m5050f(peekValue.data);
        }
        mo3615c(obtainStyledAttributes.getBoolean(5, false));
        int i2 = 3;
        boolean z = obtainStyledAttributes.getBoolean(3, true);
        if (this.f5166a != z) {
            this.f5166a = z;
            if (this.f5178m != null) {
                m5045d();
            }
            mo3616d((!this.f5166a || this.f5175j != 6) ? this.f5175j : i2);
            m5051g();
        }
        this.f5174i = obtainStyledAttributes.getBoolean(8, false);
        this.f5183r = obtainStyledAttributes.getInt(7, 0);
        float f = obtainStyledAttributes.getFloat(4, 0.5f);
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.f5191z = f;
        if (this.f5178m != null) {
            m5048e();
        }
        int i3 = obtainStyledAttributes.getInt(2, 0);
        if (i3 >= 0) {
            this.f5169d = i3;
            obtainStyledAttributes.recycle();
            this.f5184s = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
            return;
        }
        throw new IllegalArgumentException("offset must be greater than or equal to 0");
    }

    /* renamed from: a */
    private final void m5043a(View view, C0351mu muVar, int i) {
        C0340mj.m14700a(view, muVar, (C0366ni) new gdg(this, i));
    }

    /* renamed from: d */
    private final void m5045d() {
        int c = m5044c();
        if (this.f5166a) {
            this.f5172g = Math.max(this.f5177l - c, this.f5170e);
        } else {
            this.f5172g = this.f5177l - c;
        }
    }

    /* renamed from: e */
    private final void m5048e() {
        this.f5171f = (int) (((float) this.f5177l) * (1.0f - this.f5191z));
    }

    /* renamed from: c */
    private final int m5044c() {
        return this.f5185t ? Math.max(this.f5186u, this.f5177l - ((this.f5161E * 9) / 16)) : this.f5167b;
    }

    /* renamed from: a */
    private final void m5042a(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.f5187v) {
            this.f5188w = gha.m10331a(context, attributeSet, (int) R.attr.bottomSheetStyle, 2131952405).mo6660a();
            ggu ggu = new ggu(this.f5188w);
            this.f5168c = ggu;
            ggu.mo6634a(context);
            if (z && colorStateList != null) {
                this.f5168c.mo6635a(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16842801, typedValue, true);
            this.f5168c.setTint(typedValue.data);
        }
    }

    /* renamed from: e */
    public final void mo3617e(int i) {
        if (((View) this.f5178m.get()) != null && !this.f5180o.isEmpty()) {
            int i2 = this.f5172g;
            if (i > i2 || i2 == mo3613b()) {
            }
            for (int i3 = 0; i3 < this.f5180o.size(); i3++) {
                gdh gdh = (gdh) this.f5180o.get(i3);
            }
        }
    }

    /* renamed from: e */
    private final View m5047e(View view) {
        if (C0340mj.m14730u(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View e = m5047e(viewGroup.getChildAt(i));
                if (e != null) {
                    return e;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public final void mo82a(abm abm) {
        this.f5178m = null;
        this.f5176k = null;
    }

    /* renamed from: a */
    public final void mo81a() {
        this.f5178m = null;
        this.f5176k = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo91a(androidx.coordinatorlayout.widget.CoordinatorLayout r10, android.view.View r11, android.view.MotionEvent r12) {
        /*
            r9 = this;
            boolean r0 = r11.isShown()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x000b
            r9.f5158B = r2
            return r1
        L_0x000b:
            int r0 = r12.getActionMasked()
            if (r0 != 0) goto L_0x0014
            r9.m5049f()
        L_0x0014:
            android.view.VelocityTracker r3 = r9.f5162F
            if (r3 == 0) goto L_0x0019
            goto L_0x001f
        L_0x0019:
            android.view.VelocityTracker r3 = android.view.VelocityTracker.obtain()
            r9.f5162F = r3
        L_0x001f:
            android.view.VelocityTracker r3 = r9.f5162F
            r3.addMovement(r12)
            r3 = 0
            r4 = -1
            r5 = 2
            if (r0 == 0) goto L_0x003b
            if (r0 == r2) goto L_0x002f
            r11 = 3
            if (r0 == r11) goto L_0x002f
            goto L_0x007f
        L_0x002f:
            r9.f5182q = r1
            r9.f5181p = r4
            boolean r11 = r9.f5158B
            if (r11 == 0) goto L_0x007f
            r9.f5158B = r1
            return r1
        L_0x003b:
            float r6 = r12.getX()
            int r6 = (int) r6
            float r7 = r12.getY()
            int r7 = (int) r7
            r9.f5163G = r7
            int r7 = r9.f5175j
            if (r7 == r5) goto L_0x006e
            java.lang.ref.WeakReference r7 = r9.f5179n
            if (r7 == 0) goto L_0x0056
            java.lang.Object r7 = r7.get()
            android.view.View r7 = (android.view.View) r7
            goto L_0x0058
        L_0x0056:
            r7 = r3
        L_0x0058:
            if (r7 == 0) goto L_0x006e
            int r8 = r9.f5163G
            boolean r7 = r10.mo1122a((android.view.View) r7, (int) r6, (int) r8)
            if (r7 == 0) goto L_0x006e
            int r7 = r12.getActionIndex()
            int r7 = r12.getPointerId(r7)
            r9.f5181p = r7
            r9.f5182q = r2
        L_0x006e:
            int r7 = r9.f5181p
            if (r7 != r4) goto L_0x007c
            int r4 = r9.f5163G
            boolean r11 = r10.mo1122a((android.view.View) r11, (int) r6, (int) r4)
            if (r11 != 0) goto L_0x007c
            r11 = 1
            goto L_0x007d
        L_0x007c:
            r11 = 0
        L_0x007d:
            r9.f5158B = r11
        L_0x007f:
            boolean r11 = r9.f5158B
            if (r11 != 0) goto L_0x008f
            nw r11 = r9.f5176k
            if (r11 != 0) goto L_0x0088
            goto L_0x008f
        L_0x0088:
            boolean r11 = r11.mo9482a((android.view.MotionEvent) r12)
            if (r11 == 0) goto L_0x008f
            return r2
        L_0x008f:
            java.lang.ref.WeakReference r11 = r9.f5179n
            if (r11 == 0) goto L_0x009b
            java.lang.Object r11 = r11.get()
            r3 = r11
            android.view.View r3 = (android.view.View) r3
            goto L_0x009d
        L_0x009b:
        L_0x009d:
            if (r0 != r5) goto L_0x00d3
            if (r3 == 0) goto L_0x00d3
            boolean r11 = r9.f5158B
            if (r11 != 0) goto L_0x00d3
            int r11 = r9.f5175j
            if (r11 == r2) goto L_0x00d3
            float r11 = r12.getX()
            int r11 = (int) r11
            float r0 = r12.getY()
            int r0 = (int) r0
            boolean r10 = r10.mo1122a((android.view.View) r3, (int) r11, (int) r0)
            if (r10 != 0) goto L_0x00d3
            nw r10 = r9.f5176k
            if (r10 == 0) goto L_0x00d3
            int r10 = r9.f5163G
            float r10 = (float) r10
            float r11 = r12.getY()
            float r10 = r10 - r11
            float r10 = java.lang.Math.abs(r10)
            nw r11 = r9.f5176k
            int r11 = r11.f15316b
            float r11 = (float) r11
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 <= 0) goto L_0x00d3
            return r2
        L_0x00d3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.mo91a(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    /* renamed from: a */
    public final boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        ggu ggu;
        if (C0340mj.m14725p(coordinatorLayout) && !C0340mj.m14725p(view)) {
            view.setFitsSystemWindows(true);
        }
        if (this.f5178m == null) {
            this.f5186u = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            this.f5178m = new WeakReference(view);
            if (this.f5187v && (ggu = this.f5168c) != null) {
                C0340mj.m14694a(view, (Drawable) ggu);
            }
            ggu ggu2 = this.f5168c;
            if (ggu2 != null) {
                float f = this.f5157A;
                if (f == -1.0f) {
                    f = C0340mj.m14721l(view);
                }
                ggu2.mo6637b(f);
                int i2 = this.f5175j;
                this.f5189x = i2 == 3;
                this.f5168c.mo6631a(i2 == 3 ? 0.0f : 1.0f);
            }
            m5051g();
            if (C0340mj.m14712e(view) == 0) {
                C0340mj.m14689a(view, 1);
            }
        }
        if (this.f5176k == null) {
            this.f5176k = C0380nw.m14844a((ViewGroup) coordinatorLayout, this.f5165I);
        }
        int top = view.getTop();
        coordinatorLayout.mo1123b(view, i);
        this.f5161E = coordinatorLayout.getWidth();
        int height = coordinatorLayout.getHeight();
        this.f5177l = height;
        this.f5170e = Math.max(0, height - view.getHeight());
        m5048e();
        m5045d();
        int i3 = this.f5175j;
        if (i3 == 3) {
            C0340mj.m14708c(view, mo3613b());
        } else if (i3 == 6) {
            C0340mj.m14708c(view, this.f5171f);
        } else if (this.f5173h && i3 == 5) {
            C0340mj.m14708c(view, this.f5177l);
        } else if (i3 == 4) {
            C0340mj.m14708c(view, this.f5172g);
        } else if (i3 == 1 || i3 == 2) {
            C0340mj.m14708c(view, top - view.getTop());
        }
        this.f5179n = new WeakReference(m5047e(view));
        return true;
    }

    /* renamed from: b */
    public final boolean mo94b(View view) {
        WeakReference weakReference = this.f5179n;
        return (weakReference == null || view != weakReference.get() || this.f5175j == 3) ? false : true;
    }

    /* renamed from: a */
    public final void mo86a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int[] iArr, int i2) {
        View view3;
        if (i2 != 1) {
            WeakReference weakReference = this.f5179n;
            if (weakReference != null) {
                view3 = (View) weakReference.get();
            } else {
                view3 = null;
            }
            if (view2 == view3) {
                int top = view.getTop();
                int i3 = top - i;
                if (i > 0) {
                    if (i3 < mo3613b()) {
                        int b = top - mo3613b();
                        iArr[1] = b;
                        C0340mj.m14708c(view, -b);
                        mo3616d(3);
                    } else {
                        iArr[1] = i;
                        C0340mj.m14708c(view, -i);
                        mo3616d(1);
                    }
                } else if (i < 0 && !view2.canScrollVertically(-1)) {
                    int i4 = this.f5172g;
                    if (i3 <= i4 || this.f5173h) {
                        iArr[1] = i;
                        C0340mj.m14708c(view, -i);
                        mo3616d(1);
                    } else {
                        int i5 = top - i4;
                        iArr[1] = i5;
                        C0340mj.m14708c(view, -i5);
                        mo3616d(4);
                    }
                }
                mo3617e(view.getTop());
                this.f5159C = i;
                this.f5160D = true;
            }
        }
    }

    /* renamed from: a */
    public final void mo83a(View view, Parcelable parcelable) {
        gdj gdj = (gdj) parcelable;
        int i = this.f5183r;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.f5167b = gdj.f11021d;
            }
            if (i == -1 || (i & 2) == 2) {
                this.f5166a = gdj.f11022e;
            }
            if (i == -1 || (i & 4) == 4) {
                this.f5173h = gdj.f11023f;
            }
            if (i == -1 || (i & 8) == 8) {
                this.f5174i = gdj.f11024g;
            }
        }
        int i2 = gdj.f11020c;
        if (i2 == 1 || i2 == 2) {
            this.f5175j = 4;
        } else {
            this.f5175j = i2;
        }
    }

    /* renamed from: c */
    public final Parcelable mo96c(View view) {
        return new gdj((Parcelable) View.BaseSavedState.EMPTY_STATE, this);
    }

    /* renamed from: a */
    public final boolean mo93a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2) {
        this.f5159C = 0;
        this.f5160D = false;
        if ((i & 2) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final void mo85a(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
        int i2;
        float f;
        int i3 = 3;
        if (view.getTop() != mo3613b()) {
            WeakReference weakReference = this.f5179n;
            if (weakReference != null && view2 == weakReference.get() && this.f5160D) {
                if (this.f5159C > 0) {
                    i2 = mo3613b();
                } else {
                    if (this.f5173h) {
                        VelocityTracker velocityTracker = this.f5162F;
                        if (velocityTracker != null) {
                            velocityTracker.computeCurrentVelocity(1000, this.f5184s);
                            f = this.f5162F.getYVelocity(this.f5181p);
                        } else {
                            f = 0.0f;
                        }
                        if (mo3612a(view, f)) {
                            i2 = this.f5177l;
                            i3 = 5;
                        }
                    }
                    if (this.f5159C != 0) {
                        if (!this.f5166a) {
                            int top = view.getTop();
                            if (Math.abs(top - this.f5171f) < Math.abs(top - this.f5172g)) {
                                i2 = this.f5171f;
                                i3 = 6;
                            }
                        }
                        i2 = this.f5172g;
                        i3 = 4;
                    } else {
                        int top2 = view.getTop();
                        if (!this.f5166a) {
                            int i4 = this.f5171f;
                            if (top2 >= i4) {
                                if (Math.abs(top2 - i4) < Math.abs(top2 - this.f5172g)) {
                                    i2 = this.f5171f;
                                    i3 = 6;
                                } else {
                                    i2 = this.f5172g;
                                    i3 = 4;
                                }
                            } else if (top2 < Math.abs(top2 - this.f5172g)) {
                                i2 = this.f5169d;
                            } else {
                                i2 = this.f5171f;
                                i3 = 6;
                            }
                        } else if (Math.abs(top2 - this.f5170e) < Math.abs(top2 - this.f5172g)) {
                            i2 = this.f5170e;
                        } else {
                            i2 = this.f5172g;
                            i3 = 4;
                        }
                    }
                }
                mo3611a(view, i3, i2, false);
                this.f5160D = false;
                return;
            }
            return;
        }
        mo3616d(3);
    }

    /* renamed from: b */
    public final boolean mo95b(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.f5175j == 1 && actionMasked == 0) {
            return true;
        }
        C0380nw nwVar = this.f5176k;
        if (nwVar != null) {
            nwVar.mo9483b(motionEvent);
        }
        if (actionMasked == 0) {
            m5049f();
        }
        if (this.f5162F == null) {
            this.f5162F = VelocityTracker.obtain();
        }
        this.f5162F.addMovement(motionEvent);
        if (actionMasked == 2 && !this.f5158B) {
            float abs = Math.abs(((float) this.f5163G) - motionEvent.getY());
            C0380nw nwVar2 = this.f5176k;
            if (abs > ((float) nwVar2.f15316b)) {
                nwVar2.mo9478a(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.f5158B;
    }

    /* renamed from: f */
    private final void m5049f() {
        this.f5181p = -1;
        VelocityTracker velocityTracker = this.f5162F;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f5162F = null;
        }
    }

    /* renamed from: c */
    public final void mo3615c(boolean z) {
        if (this.f5173h != z) {
            this.f5173h = z;
            if (!z && this.f5175j == 5) {
                mo3614c(4);
            }
            m5051g();
        }
    }

    /* renamed from: f */
    private final void m5050f(int i) {
        View view;
        if (i == -1) {
            if (!this.f5185t) {
                this.f5185t = true;
            } else {
                return;
            }
        } else if (this.f5185t || this.f5167b != i) {
            this.f5185t = false;
            this.f5167b = Math.max(0, i);
        } else {
            return;
        }
        if (this.f5178m != null) {
            m5045d();
            if (this.f5175j == 4 && (view = (View) this.f5178m.get()) != null) {
                view.requestLayout();
            }
        }
    }

    /* renamed from: c */
    public final void mo3614c(int i) {
        if (i != this.f5175j) {
            WeakReference weakReference = this.f5178m;
            if (weakReference != null) {
                View view = (View) weakReference.get();
                if (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent == null || !parent.isLayoutRequested() || !C0340mj.m14735z(view)) {
                        mo3610a(view, i);
                    } else {
                        view.post(new gdd(this, view, i));
                    }
                }
            } else if (i == 4 || i == 3 || i == 6 || (this.f5173h && i == 5)) {
                this.f5175j = i;
            }
        }
    }

    /* renamed from: d */
    public final void mo3616d(int i) {
        if (this.f5175j != i) {
            this.f5175j = i;
            WeakReference weakReference = this.f5178m;
            if (weakReference != null && ((View) weakReference.get()) != null) {
                if (i == 3) {
                    m5046d(true);
                } else if (i == 6 || i == 5 || i == 4) {
                    m5046d(false);
                }
                m5052g(i);
                for (int i2 = 0; i2 < this.f5180o.size(); i2++) {
                    gdh gdh = (gdh) this.f5180o.get(i2);
                    if (i == 5) {
                        gdh.f11019a.cancel();
                    }
                }
                m5051g();
            }
        }
    }

    /* renamed from: a */
    public final void mo3610a(View view, int i) {
        int i2;
        int i3;
        if (i == 4) {
            i2 = this.f5172g;
        } else if (i == 6) {
            int i4 = this.f5171f;
            if (!this.f5166a || i4 > (i3 = this.f5170e)) {
                i2 = i4;
            } else {
                i2 = i3;
                i = 3;
            }
        } else if (i == 3) {
            i2 = mo3613b();
        } else if (!this.f5173h || i != 5) {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Illegal state argument: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else {
            i2 = this.f5177l;
        }
        mo3611a(view, i, i2, false);
    }

    /* renamed from: a */
    public final boolean mo3612a(View view, float f) {
        if (this.f5174i) {
            return true;
        }
        if (view.getTop() < this.f5172g) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f * 0.1f)) - ((float) this.f5172g)) / ((float) m5044c()) > 0.5f;
    }

    /* renamed from: a */
    public final void mo3611a(View view, int i, int i2, boolean z) {
        boolean z2;
        if (!z) {
            C0380nw nwVar = this.f5176k;
            int left = view.getLeft();
            nwVar.f15318d = view;
            nwVar.f15317c = -1;
            z2 = nwVar.mo9481a(left, i2, 0, 0);
            if (!z2 && nwVar.f15315a == 0 && nwVar.f15318d != null) {
                nwVar.f15318d = null;
                mo3616d(i);
            }
        } else {
            z2 = this.f5176k.mo9480a(view.getLeft(), i2);
        }
        if (z2) {
            mo3616d(2);
            m5052g(i);
            C0340mj.m14695a(view, (Runnable) new gdk(this, view, i));
            return;
        }
        mo3616d(i);
    }

    /* renamed from: g */
    private final void m5051g() {
        View view;
        WeakReference weakReference = this.f5178m;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            C0340mj.m14706b(view, 524288);
            C0340mj.m14706b(view, 262144);
            C0340mj.m14706b(view, 1048576);
            if (this.f5173h && this.f5175j != 5) {
                m5043a(view, C0351mu.f15248e, 5);
            }
            int i = this.f5175j;
            int i2 = 6;
            if (i == 3) {
                if (this.f5166a) {
                    i2 = 4;
                }
                m5043a(view, C0351mu.f15247d, i2);
            } else if (i == 4) {
                if (this.f5166a) {
                    i2 = 3;
                }
                m5043a(view, C0351mu.f15246c, i2);
            } else if (i == 6) {
                m5043a(view, C0351mu.f15247d, 4);
                m5043a(view, C0351mu.f15246c, 3);
            }
        }
    }

    /* renamed from: g */
    private final void m5052g(int i) {
        ValueAnimator valueAnimator;
        if (i != 2) {
            boolean z = i == 3;
            if (this.f5189x != z) {
                this.f5189x = z;
                if (this.f5168c != null && (valueAnimator = this.f5190y) != null) {
                    if (!valueAnimator.isRunning()) {
                        float f = i == 3 ? 0.0f : 1.0f;
                        this.f5190y.setFloatValues(new float[]{1.0f - f, f});
                        this.f5190y.start();
                        return;
                    }
                    this.f5190y.reverse();
                }
            }
        }
    }

    /* renamed from: d */
    private final void m5046d(boolean z) {
        WeakReference weakReference = this.f5178m;
        if (weakReference != null) {
            ViewParent parent = ((View) weakReference.get()).getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                int i = Build.VERSION.SDK_INT;
                if (z) {
                    if (this.f5164H == null) {
                        this.f5164H = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = coordinatorLayout.getChildAt(i2);
                    if (childAt != this.f5178m.get() && z) {
                        int i3 = Build.VERSION.SDK_INT;
                        this.f5164H.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    }
                }
                if (!z) {
                    this.f5164H = null;
                }
            }
        }
    }
}
