package com.android.messaging.p041ui.mediapicker;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.P */
class C1288P extends TouchDelegate {
    final /* synthetic */ C1289Q this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1288P(C1289Q q, Rect rect, View view) {
        super(rect, view);
        this.this$1 = q;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.this$1.this$0.setPressed(true);
        } else if (action == 1 || action == 3) {
            this.this$1.this$0.setPressed(false);
        }
        return super.onTouchEvent(motionEvent);
    }
}
