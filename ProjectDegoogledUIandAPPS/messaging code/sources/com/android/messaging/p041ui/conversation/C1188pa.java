package com.android.messaging.p041ui.conversation;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/* renamed from: com.android.messaging.ui.conversation.pa */
class C1188pa implements View.OnLongClickListener, View.OnTouchListener {

    /* renamed from: jH */
    private boolean f1872jH;

    /* renamed from: kH */
    private final View.OnLongClickListener f1873kH;

    private C1188pa(View.OnLongClickListener onLongClickListener) {
        this.f1873kH = onLongClickListener;
    }

    /* renamed from: a */
    public static void m3025a(TextView textView, View.OnLongClickListener onLongClickListener) {
        C1188pa paVar = new C1188pa(onLongClickListener);
        textView.setOnLongClickListener(paVar);
        textView.setOnTouchListener(paVar);
    }

    public boolean onLongClick(View view) {
        this.f1872jH = true;
        View.OnLongClickListener onLongClickListener = this.f1873kH;
        if (onLongClickListener != null) {
            return onLongClickListener.onLongClick(view);
        }
        return false;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 1 || !this.f1872jH) {
            if (motionEvent.getActionMasked() == 0) {
                this.f1872jH = false;
            }
            return false;
        }
        this.f1872jH = false;
        return true;
    }
}
