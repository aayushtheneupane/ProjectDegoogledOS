package p000;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: mw */
/* compiled from: PG */
public final class C0353mw {

    /* renamed from: a */
    public final Object f15256a;

    public C0353mw(Object obj) {
        this.f15256a = obj;
    }

    /* renamed from: a */
    public static C0353mw m14775a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int i5 = Build.VERSION.SDK_INT;
        return new C0353mw(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
    }
}
