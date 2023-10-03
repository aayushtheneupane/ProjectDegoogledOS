package p000;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* renamed from: my */
/* compiled from: PG */
class C0355my extends AccessibilityNodeProvider {

    /* renamed from: a */
    public final C0358na f15260a;

    public C0355my(C0358na naVar) {
        this.f15260a = naVar;
    }

    public final List findAccessibilityNodeInfosByText(String str, int i) {
        return null;
    }

    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        C0354mx a = this.f15260a.mo9453a(i);
        if (a != null) {
            return a.f15257a;
        }
        return null;
    }

    public final boolean performAction(int i, int i2, Bundle bundle) {
        return this.f15260a.mo9454a(i, i2, bundle);
    }
}
