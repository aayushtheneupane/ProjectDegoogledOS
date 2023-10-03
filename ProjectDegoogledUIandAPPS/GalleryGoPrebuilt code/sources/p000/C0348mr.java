package p000;

import android.os.Build;
import android.view.WindowInsets;

/* renamed from: mr */
/* compiled from: PG */
public final class C0348mr {

    /* renamed from: a */
    public final Object f15240a;

    public C0348mr(Object obj) {
        this.f15240a = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0348mr) {
            return C0321lr.m14631a(this.f15240a, ((C0348mr) obj).f15240a);
        }
        return false;
    }

    /* renamed from: d */
    public final int mo9411d() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f15240a).getSystemWindowInsetBottom();
    }

    /* renamed from: a */
    public final int mo9408a() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f15240a).getSystemWindowInsetLeft();
    }

    /* renamed from: c */
    public final int mo9410c() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f15240a).getSystemWindowInsetRight();
    }

    /* renamed from: b */
    public final int mo9409b() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f15240a).getSystemWindowInsetTop();
    }

    public final int hashCode() {
        Object obj = this.f15240a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: e */
    public final boolean mo9412e() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.f15240a).isConsumed();
    }

    /* renamed from: a */
    public static C0348mr m14751a(WindowInsets windowInsets) {
        windowInsets.getClass();
        return new C0348mr(windowInsets);
    }
}
