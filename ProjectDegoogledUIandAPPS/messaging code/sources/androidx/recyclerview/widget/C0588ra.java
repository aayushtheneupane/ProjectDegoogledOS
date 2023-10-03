package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.p025a.C0363e;

/* renamed from: androidx.recyclerview.widget.ra */
public class C0588ra extends AccessibilityDelegateCompat {

    /* renamed from: eo */
    final C0590sa f669eo;

    public C0588ra(C0590sa saVar) {
        this.f669eo = saVar;
    }

    public void onInitializeAccessibilityNodeInfo(View view, C0363e eVar) {
        super.onInitializeAccessibilityNodeInfo(view, eVar);
        if (!this.f669eo.shouldIgnore() && this.f669eo.mRecyclerView.getLayoutManager() != null) {
            this.f669eo.mRecyclerView.getLayoutManager().mo5014a(view, eVar);
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (this.f669eo.shouldIgnore() || this.f669eo.mRecyclerView.getLayoutManager() == null) {
            return false;
        }
        return this.f669eo.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view, i, bundle);
    }
}
