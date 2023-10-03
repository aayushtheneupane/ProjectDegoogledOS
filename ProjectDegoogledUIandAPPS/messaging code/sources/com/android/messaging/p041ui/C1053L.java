package com.android.messaging.p041ui;

import androidx.viewpager.widget.C0626k;

/* renamed from: com.android.messaging.ui.L */
class C1053L implements C0626k {
    final /* synthetic */ CustomHeaderViewPager this$0;

    C1053L(CustomHeaderViewPager customHeaderViewPager) {
        this.this$0 = customHeaderViewPager;
    }

    public void onPageScrollStateChanged(int i) {
        this.this$0.f1618oh.onPageScrollStateChanged(i);
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.this$0.f1618oh.onPageScrolled(i, f, i2);
    }

    public void onPageSelected(int i) {
        this.this$0.f1618oh.onPageSelected(i);
    }
}
