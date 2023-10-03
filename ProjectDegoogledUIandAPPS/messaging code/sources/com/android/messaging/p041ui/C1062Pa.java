package com.android.messaging.p041ui;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* renamed from: com.android.messaging.ui.Pa */
class C1062Pa extends ViewOutlineProvider {
    C1062Pa(ViewPagerTabs viewPagerTabs) {
    }

    public void getOutline(View view, Outline outline) {
        outline.setRect(0, 0, view.getWidth(), view.getHeight());
    }
}
