package p000;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.google.android.apps.photosgo.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: mj */
/* compiled from: PG */
public final class C0340mj {

    /* renamed from: a */
    private static WeakHashMap f15234a = null;

    /* renamed from: b */
    private static Field f15235b;

    /* renamed from: c */
    private static boolean f15236c = false;

    static {
        new AtomicInteger(1);
        new C0336mf();
    }

    /* renamed from: a */
    public static C0337mg m14685a() {
        return new C0335me(Boolean.class);
    }

    /* renamed from: k */
    public static C0344mn m14720k(View view) {
        if (f15234a == null) {
            f15234a = new WeakHashMap();
        }
        C0344mn mnVar = (C0344mn) f15234a.get(view);
        if (mnVar != null) {
            return mnVar;
        }
        C0344mn mnVar2 = new C0344mn(view);
        f15234a.put(view, mnVar2);
        return mnVar2;
    }

    /* renamed from: b */
    static boolean m14707b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        C0339mi a = C0339mi.m14670a(view);
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = a.f15231b;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            if (!C0339mi.f15230a.isEmpty()) {
                synchronized (C0339mi.f15230a) {
                    if (a.f15231b == null) {
                        a.f15231b = new WeakHashMap();
                    }
                    for (int size = C0339mi.f15230a.size() - 1; size >= 0; size--) {
                        View view2 = (View) ((WeakReference) C0339mi.f15230a.get(size)).get();
                        if (view2 != null) {
                            a.f15231b.put(view2, Boolean.TRUE);
                            for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                a.f15231b.put((View) parent, Boolean.TRUE);
                            }
                        } else {
                            C0339mi.f15230a.remove(size);
                        }
                    }
                }
            }
        }
        View a2 = a.mo9394a(view, keyEvent);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (a2 != null && !KeyEvent.isModifierKey(keyCode)) {
                a.mo9393a().put(keyCode, new WeakReference(a2));
            }
        }
        if (a2 != null) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.ref.WeakReference} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean m14704a(android.view.View r4, android.view.KeyEvent r5) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 28
            if (r0 >= r2) goto L_0x0065
            mi r4 = p000.C0339mi.m14670a(r4)
            java.lang.ref.WeakReference r0 = r4.f15232c
            r2 = 1
            if (r0 != 0) goto L_0x0011
            goto L_0x0018
        L_0x0011:
            java.lang.Object r0 = r0.get()
            if (r0 != r5) goto L_0x0018
        L_0x0017:
            goto L_0x0064
        L_0x0018:
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r5)
            r4.f15232c = r0
            android.util.SparseArray r4 = r4.mo9393a()
            int r0 = r5.getAction()
            r3 = 0
            if (r0 != r2) goto L_0x003f
            int r0 = r5.getKeyCode()
            int r0 = r4.indexOfKey(r0)
            if (r0 < 0) goto L_0x003e
            java.lang.Object r3 = r4.valueAt(r0)
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3
            r4.removeAt(r0)
            goto L_0x0040
        L_0x003e:
        L_0x003f:
        L_0x0040:
            if (r3 != 0) goto L_0x004d
            int r5 = r5.getKeyCode()
            java.lang.Object r4 = r4.get(r5)
            r3 = r4
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3
        L_0x004d:
            if (r3 == 0) goto L_0x0017
            java.lang.Object r4 = r3.get()
            android.view.View r4 = (android.view.View) r4
            if (r4 == 0) goto L_0x0062
            boolean r5 = m14735z(r4)
            if (r5 != 0) goto L_0x005e
            goto L_0x0063
        L_0x005e:
            p000.C0339mi.m14671b(r4)
            return r2
        L_0x0062:
        L_0x0063:
            r1 = 1
        L_0x0064:
            return r1
        L_0x0065:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0340mj.m14704a(android.view.View, android.view.KeyEvent):boolean");
    }

    /* renamed from: b */
    public static C0315ll m14705b(View view) {
        View.AccessibilityDelegate I = m14682I(view);
        if (I == null) {
            return null;
        }
        if (!(I instanceof C0314lk)) {
            return new C0315ll(I);
        }
        return ((C0314lk) I).f15202a;
    }

    /* renamed from: I */
    private static View.AccessibilityDelegate m14682I(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return view.getAccessibilityDelegate();
        }
        if (f15236c) {
            return null;
        }
        if (f15235b == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f15235b = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable th) {
                f15236c = true;
                return null;
            }
        }
        try {
            Object obj = f15235b.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable th2) {
            f15236c = true;
            return null;
        }
    }

    /* renamed from: D */
    public static CharSequence m14677D(View view) {
        return (CharSequence) new C0334md(CharSequence.class).mo9391b(view);
    }

    /* renamed from: J */
    private static List m14683J(View view) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_accessibility_actions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        view.setTag(R.id.tag_accessibility_actions, arrayList2);
        return arrayList2;
    }

    /* renamed from: s */
    public static ColorStateList m14728s(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getBackgroundTintList();
    }

    /* renamed from: t */
    public static PorterDuff.Mode m14729t(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getBackgroundTintMode();
    }

    /* renamed from: y */
    public static Rect m14734y(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getClipBounds();
    }

    /* renamed from: B */
    public static Display m14675B(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getDisplay();
    }

    /* renamed from: l */
    public static float m14721l(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getElevation();
    }

    /* renamed from: p */
    public static boolean m14725p(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getFitsSystemWindows();
    }

    /* renamed from: e */
    public static int m14712e(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getImportantForAccessibility();
    }

    /* renamed from: a */
    public static int m14684a(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getImportantForAutofill();
    }

    /* renamed from: f */
    public static int m14714f(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getLayoutDirection();
    }

    /* renamed from: j */
    public static int m14719j(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getMinimumHeight();
    }

    /* renamed from: i */
    public static int m14718i(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getMinimumWidth();
    }

    /* renamed from: E */
    public static void m14678E(View view) {
        C0315ll b = m14705b(view);
        if (b == null) {
            b = new C0315ll();
        }
        m14698a(view, b);
    }

    /* renamed from: h */
    public static int m14717h(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getPaddingEnd();
    }

    /* renamed from: g */
    public static int m14716g(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getPaddingStart();
    }

    /* renamed from: m */
    public static String m14722m(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getTransitionName();
    }

    /* renamed from: n */
    public static int m14723n(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getWindowSystemUiVisibility();
    }

    /* renamed from: x */
    public static float m14733x(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.getZ();
    }

    /* renamed from: A */
    public static boolean m14674A(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasOnClickListeners();
    }

    /* renamed from: q */
    public static boolean m14726q(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasOverlappingRendering();
    }

    /* renamed from: c */
    public static boolean m14709c(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.hasTransientState();
    }

    /* renamed from: z */
    public static boolean m14735z(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isAttachedToWindow();
    }

    /* renamed from: w */
    public static boolean m14732w(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isLaidOut();
    }

    /* renamed from: u */
    public static boolean m14730u(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isNestedScrollingEnabled();
    }

    /* renamed from: r */
    public static boolean m14727r(View view) {
        int i = Build.VERSION.SDK_INT;
        return view.isPaddingRelative();
    }

    /* renamed from: C */
    public static boolean m14676C(View view) {
        Boolean bool = (Boolean) new C0333mc(Boolean.class).mo9391b(view);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* renamed from: e */
    public static void m14713e(View view, int i) {
        int i2;
        if (((AccessibilityManager) view.getContext().getSystemService("accessibility")).isEnabled()) {
            CharSequence D = m14677D(view);
            int i3 = Build.VERSION.SDK_INT;
            if (view.getAccessibilityLiveRegion() != 0 || (D != null && view.getVisibility() == 0)) {
                if (D == null) {
                    i2 = 2048;
                } else {
                    i2 = 32;
                }
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                obtain.setEventType(i2);
                obtain.setContentChangeTypes(i);
                view.sendAccessibilityEventUnchecked(obtain);
            } else if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i);
                } catch (AbstractMethodError e) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e);
                }
            }
        }
    }

    /* renamed from: d */
    public static void m14711d(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        view.offsetLeftAndRight(i);
    }

    /* renamed from: c */
    public static void m14708c(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        view.offsetTopAndBottom(i);
    }

    /* renamed from: a */
    public static C0348mr m14686a(View view, C0348mr mrVar) {
        int i = Build.VERSION.SDK_INT;
        Object obj = mrVar.f15240a;
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets((WindowInsets) obj);
        boolean equals = onApplyWindowInsets.equals(obj);
        WindowInsets windowInsets = obj;
        if (!equals) {
            windowInsets = new WindowInsets(onApplyWindowInsets);
        }
        return C0348mr.m14751a((WindowInsets) windowInsets);
    }

    /* renamed from: a */
    public static void m14701a(View view, C0354mx mxVar) {
        view.onInitializeAccessibilityNodeInfo(mxVar.f15257a);
    }

    /* renamed from: a */
    public static boolean m14703a(View view, int i, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        return view.performAccessibilityAction(i, bundle);
    }

    /* renamed from: d */
    public static void m14710d(View view) {
        int i = Build.VERSION.SDK_INT;
        view.postInvalidateOnAnimation();
    }

    /* renamed from: a */
    public static void m14695a(View view, Runnable runnable) {
        int i = Build.VERSION.SDK_INT;
        view.postOnAnimation(runnable);
    }

    /* renamed from: a */
    public static void m14696a(View view, Runnable runnable, long j) {
        int i = Build.VERSION.SDK_INT;
        view.postOnAnimationDelayed(runnable, j);
    }

    /* renamed from: b */
    public static void m14706b(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        m14687a(i, view);
        m14713e(view, 0);
    }

    /* renamed from: a */
    private static void m14687a(int i, View view) {
        List J = m14683J(view);
        int i2 = 0;
        while (i2 < J.size()) {
            if (((C0351mu) J.get(i2)).mo9416a() != i) {
                i2++;
            } else {
                J.remove(i2);
                return;
            }
        }
    }

    /* renamed from: a */
    public static void m14700a(View view, C0351mu muVar, C0366ni niVar) {
        C0351mu muVar2 = new C0351mu((Object) null, muVar.f15252i, niVar, muVar.f15253j);
        int i = Build.VERSION.SDK_INT;
        m14678E(view);
        m14687a(muVar2.mo9416a(), view);
        m14683J(view).add(muVar2);
        m14713e(view, 0);
    }

    /* renamed from: o */
    public static void m14724o(View view) {
        int i = Build.VERSION.SDK_INT;
        view.requestApplyInsets();
    }

    /* renamed from: a */
    public static void m14698a(View view, C0315ll llVar) {
        View.AccessibilityDelegate accessibilityDelegate;
        if (llVar == null && (m14682I(view) instanceof C0314lk)) {
            llVar = new C0315ll();
        }
        if (llVar != null) {
            accessibilityDelegate = llVar.f15204a;
        } else {
            accessibilityDelegate = null;
        }
        view.setAccessibilityDelegate(accessibilityDelegate);
    }

    /* renamed from: F */
    public static void m14679F(View view) {
        int i = Build.VERSION.SDK_INT;
        view.setAccessibilityLiveRegion(1);
    }

    /* renamed from: a */
    public static void m14694a(View view, Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        view.setBackground(drawable);
    }

    /* renamed from: a */
    public static void m14691a(View view, ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        view.setBackgroundTintList(colorStateList);
        int i2 = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static void m14692a(View view, PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        view.setBackgroundTintMode(mode);
        int i2 = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static void m14693a(View view, Rect rect) {
        int i = Build.VERSION.SDK_INT;
        view.setClipBounds(rect);
    }

    /* renamed from: a */
    public static void m14688a(View view, float f) {
        int i = Build.VERSION.SDK_INT;
        view.setElevation(f);
    }

    @Deprecated
    /* renamed from: G */
    public static void m14680G(View view) {
        view.setFitsSystemWindows(true);
    }

    /* renamed from: a */
    public static void m14702a(View view, boolean z) {
        int i = Build.VERSION.SDK_INT;
        view.setHasTransientState(z);
    }

    /* renamed from: a */
    public static void m14689a(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        view.setImportantForAccessibility(i);
    }

    /* renamed from: H */
    public static void m14681H(View view) {
        int i = Build.VERSION.SDK_INT;
        view.setImportantForAutofill(8);
    }

    /* renamed from: a */
    public static void m14699a(View view, C0329lz lzVar) {
        int i = Build.VERSION.SDK_INT;
        if (lzVar != null) {
            view.setOnApplyWindowInsetsListener(new C0332mb(lzVar));
        } else {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        }
    }

    /* renamed from: a */
    public static void m14690a(View view, int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        view.setPaddingRelative(i, i2, i3, i4);
    }

    /* renamed from: f */
    public static void m14715f(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        view.setScrollIndicators(i, 3);
    }

    /* renamed from: a */
    public static void m14697a(View view, String str) {
        int i = Build.VERSION.SDK_INT;
        view.setTransitionName(str);
    }

    /* renamed from: v */
    public static void m14731v(View view) {
        int i = Build.VERSION.SDK_INT;
        view.stopNestedScroll();
    }
}
