package androidx.viewpager.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.p025a.C0363e;

/* renamed from: androidx.viewpager.widget.i */
class C0624i extends AccessibilityDelegateCompat {
    final /* synthetic */ ViewPager this$0;

    C0624i(ViewPager viewPager) {
        this.this$0 = viewPager;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        C0616a aVar;
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
        C0616a aVar2 = this.this$0.mAdapter;
        boolean z = true;
        if (aVar2 == null || aVar2.getCount() <= 1) {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        if (accessibilityEvent.getEventType() == 4096 && (aVar = this.this$0.mAdapter) != null) {
            accessibilityEvent.setItemCount(aVar.getCount());
            accessibilityEvent.setFromIndex(this.this$0.f704Ji);
            accessibilityEvent.setToIndex(this.this$0.f704Ji);
        }
    }

    public void onInitializeAccessibilityNodeInfo(View view, C0363e eVar) {
        super.onInitializeAccessibilityNodeInfo(view, eVar);
        eVar.setClassName("androidx.viewpager.widget.ViewPager");
        C0616a aVar = this.this$0.mAdapter;
        eVar.setScrollable(aVar != null && aVar.getCount() > 1);
        if (this.this$0.canScrollHorizontally(1)) {
            eVar.addAction(NotificationCompat.FLAG_BUBBLE);
        }
        if (this.this$0.canScrollHorizontally(-1)) {
            eVar.addAction(8192);
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        if (i != 4096) {
            if (i != 8192 || !this.this$0.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager = this.this$0;
            viewPager.setCurrentItem(viewPager.f704Ji - 1);
            return true;
        } else if (!this.this$0.canScrollHorizontally(1)) {
            return false;
        } else {
            ViewPager viewPager2 = this.this$0;
            viewPager2.setCurrentItem(viewPager2.f704Ji + 1);
            return true;
        }
    }
}
