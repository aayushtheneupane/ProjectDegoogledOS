package com.android.messaging.p041ui.conversation;

import android.content.res.Resources;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.util.C1464na;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversation.l */
class C1179l extends View.AccessibilityDelegate {
    final /* synthetic */ ComposeMessageView this$0;

    C1179l(ComposeMessageView composeMessageView) {
        this.this$0 = composeMessageView;
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        if (accessibilityEvent.getEventType() == 2) {
            accessibilityEvent.getText().clear();
            List text = accessibilityEvent.getText();
            Resources resources = this.this$0.getResources();
            C0931n nVar = (C0931n) this.this$0.f1774Ih.getData();
            boolean z = true;
            if (!C1464na.m3759Zj() || nVar.mo6457J(true) <= 1) {
                z = false;
            }
            text.add(resources.getText(z ? R.string.send_button_long_click_description_with_sim_selector : R.string.send_button_long_click_description_no_sim_selector));
            accessibilityEvent.setEventType(16384);
        }
    }
}
