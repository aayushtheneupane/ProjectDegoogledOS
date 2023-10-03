package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.android.messaging.p041ui.ContactIconView;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.conversation.SimIconView */
public class SimIconView extends ContactIconView {
    public SimIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (C1464na.m3758Yj()) {
            setOutlineProvider(new C1202wa(this));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Zb */
    public void mo6928Zb() {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isClickable()) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }
}
