package androidx.core.view.p025a;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: androidx.core.view.a.d */
public class C0362d {
    final Object mInfo;

    C0362d(Object obj) {
        this.mInfo = obj;
    }

    public static C0362d obtain(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        int i5 = Build.VERSION.SDK_INT;
        return new C0362d(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
    }
}
