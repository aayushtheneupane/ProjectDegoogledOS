package p000;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* renamed from: lk */
/* compiled from: PG */
final class C0314lk extends View.AccessibilityDelegate {

    /* renamed from: a */
    public final C0315ll f15202a;

    public C0314lk(C0315ll llVar) {
        this.f15202a = llVar;
    }

    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.f15202a.mo9361b(view, accessibilityEvent);
    }

    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        C0358na a = this.f15202a.mo9357a(view);
        if (a != null) {
            return (AccessibilityNodeProvider) a.f15263a;
        }
        return null;
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f15202a.mo6587d(view, accessibilityEvent);
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z;
        C0354mx a = C0354mx.m14778a(accessibilityNodeInfo);
        boolean C = C0340mj.m14676C(view);
        if (Build.VERSION.SDK_INT >= 28) {
            a.f15257a.setScreenReaderFocusable(C);
        } else {
            a.mo9421a(1, C);
        }
        Boolean bool = (Boolean) C0340mj.m14685a().mo9391b(view);
        if (bool != null) {
            z = bool.booleanValue();
        } else {
            z = false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            a.f15257a.setHeading(z);
        } else {
            a.mo9421a(2, z);
        }
        CharSequence D = C0340mj.m14677D(view);
        if (Build.VERSION.SDK_INT >= 28) {
            a.f15257a.setPaneTitle(D);
        } else {
            int i = Build.VERSION.SDK_INT;
            a.f15257a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", D);
        }
        this.f15202a.mo232a(view, a);
        accessibilityNodeInfo.getText();
        int i2 = Build.VERSION.SDK_INT;
        int i3 = Build.VERSION.SDK_INT;
        List b = C0315ll.m14603b(view);
        for (int i4 = 0; i4 < b.size(); i4++) {
            a.mo9425a((C0351mu) b.get(i4));
        }
    }

    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f15202a.mo6751c(view, accessibilityEvent);
    }

    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f15202a.mo9360a(viewGroup, view, accessibilityEvent);
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.f15202a.mo233a(view, i, bundle);
    }

    public final void sendAccessibilityEvent(View view, int i) {
        this.f15202a.mo9358a(view, i);
    }

    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.f15202a.mo9359a(view, accessibilityEvent);
    }
}
