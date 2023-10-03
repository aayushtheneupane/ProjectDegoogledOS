package p000;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: mu */
/* compiled from: PG */
public final class C0351mu {

    /* renamed from: a */
    public static final C0351mu f15244a = new C0351mu(4096);

    /* renamed from: b */
    public static final C0351mu f15245b = new C0351mu(8192);

    /* renamed from: c */
    public static final C0351mu f15246c = new C0351mu(262144);

    /* renamed from: d */
    public static final C0351mu f15247d = new C0351mu(524288);

    /* renamed from: e */
    public static final C0351mu f15248e = new C0351mu(1048576);

    /* renamed from: f */
    public static final C0351mu f15249f = new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, 16908344, (C0366ni) null, (Class) null);

    /* renamed from: g */
    public static final C0351mu f15250g = new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, 16908346, (C0366ni) null, (Class) null);

    /* renamed from: h */
    public final Object f15251h;

    /* renamed from: i */
    public final int f15252i;

    /* renamed from: j */
    public final Class f15253j;

    /* renamed from: k */
    public final C0366ni f15254k;

    static {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        new C0351mu(1);
        new C0351mu(2);
        new C0351mu(4);
        new C0351mu(8);
        new C0351mu(16);
        new C0351mu(32);
        new C0351mu(64);
        new C0351mu(128);
        new C0351mu(256, C0359nb.class);
        new C0351mu(512, C0359nb.class);
        new C0351mu(1024, C0360nc.class);
        new C0351mu(2048, C0360nc.class);
        new C0351mu(16384);
        new C0351mu(32768);
        new C0351mu(65536);
        new C0351mu(131072, C0364ng.class);
        new C0351mu(2097152, C0365nh.class);
        int i = Build.VERSION.SDK_INT;
        new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, 16908342, (C0366ni) null, (Class) null);
        int i2 = Build.VERSION.SDK_INT;
        new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, 16908343, (C0366ni) null, C0362ne.class);
        int i3 = Build.VERSION.SDK_INT;
        int i4 = Build.VERSION.SDK_INT;
        new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, 16908345, (C0366ni) null, (Class) null);
        int i5 = Build.VERSION.SDK_INT;
        int i6 = Build.VERSION.SDK_INT;
        new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, 16908347, (C0366ni) null, (Class) null);
        new C0351mu(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, 16908358, (C0366ni) null, (Class) null);
        new C0351mu(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, 16908359, (C0366ni) null, (Class) null);
        new C0351mu(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, 16908360, (C0366ni) null, (Class) null);
        new C0351mu(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, 16908361, (C0366ni) null, (Class) null);
        int i7 = Build.VERSION.SDK_INT;
        new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, 16908348, (C0366ni) null, (Class) null);
        int i8 = Build.VERSION.SDK_INT;
        new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, 16908349, (C0366ni) null, C0363nf.class);
        int i9 = Build.VERSION.SDK_INT;
        new C0351mu(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, 16908354, (C0366ni) null, C0361nd.class);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
        } else {
            accessibilityAction = null;
        }
        new C0351mu(accessibilityAction, 16908356, (C0366ni) null, (Class) null);
        new C0351mu(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, 16908357, (C0366ni) null, (Class) null);
    }

    private C0351mu(int i) {
        this((Object) null, i, (C0366ni) null, (Class) null);
    }

    private C0351mu(int i, Class cls) {
        this((Object) null, i, (C0366ni) null, cls);
    }

    public C0351mu(Object obj) {
        this(obj, 0, (C0366ni) null, (Class) null);
    }

    public C0351mu(Object obj, int i, C0366ni niVar, Class cls) {
        this.f15252i = i;
        this.f15254k = niVar;
        int i2 = Build.VERSION.SDK_INT;
        if (obj == null) {
            this.f15251h = new AccessibilityNodeInfo.AccessibilityAction(i, (CharSequence) null);
        } else {
            this.f15251h = obj;
        }
        this.f15253j = cls;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C0351mu)) {
            return false;
        }
        C0351mu muVar = (C0351mu) obj;
        Object obj2 = this.f15251h;
        if (obj2 != null) {
            if (obj2.equals(muVar.f15251h)) {
                return true;
            }
            return false;
        } else if (muVar.f15251h != null) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    public final int mo9416a() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityNodeInfo.AccessibilityAction) this.f15251h).getId();
    }

    /* renamed from: b */
    public final CharSequence mo9417b() {
        int i = Build.VERSION.SDK_INT;
        return ((AccessibilityNodeInfo.AccessibilityAction) this.f15251h).getLabel();
    }

    public final int hashCode() {
        Object obj = this.f15251h;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
