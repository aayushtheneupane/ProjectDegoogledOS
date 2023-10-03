package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.Qa */
class C1064Qa implements View.OnClickListener {

    /* renamed from: fG */
    final /* synthetic */ int f1671fG;
    final /* synthetic */ ViewPagerTabs this$0;

    C1064Qa(ViewPagerTabs viewPagerTabs, int i) {
        this.this$0 = viewPagerTabs;
        this.f1671fG = i;
    }

    public void onClick(View view) {
        ViewPagerTabs viewPagerTabs = this.this$0;
        viewPagerTabs.f1691_e.setCurrentItem(viewPagerTabs.m2664A(this.f1671fG));
    }
}
