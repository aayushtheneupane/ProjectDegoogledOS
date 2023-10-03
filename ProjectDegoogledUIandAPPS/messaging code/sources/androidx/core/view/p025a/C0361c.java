package androidx.core.view.p025a;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: androidx.core.view.a.c */
public class C0361c {
    final Object mInfo;

    C0361c(Object obj) {
        this.mInfo = obj;
    }

    public static C0361c obtain(int i, int i2, boolean z, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        return new C0361c(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3));
    }
}
