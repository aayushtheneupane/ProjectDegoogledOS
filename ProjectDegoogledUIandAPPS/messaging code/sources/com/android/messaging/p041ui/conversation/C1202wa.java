package com.android.messaging.p041ui.conversation;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* renamed from: com.android.messaging.ui.conversation.wa */
class C1202wa extends ViewOutlineProvider {
    C1202wa(SimIconView simIconView) {
    }

    public void getOutline(View view, Outline outline) {
        outline.setOval(0, 0, view.getWidth(), view.getHeight());
    }
}
