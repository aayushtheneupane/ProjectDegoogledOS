package com.android.p032ex.chips;

import android.graphics.drawable.StateListDrawable;
import android.view.View;

/* renamed from: com.android.ex.chips.r */
class C0698r implements View.OnClickListener {

    /* renamed from: gv */
    final /* synthetic */ StateListDrawable f812gv;
    final /* synthetic */ C0704v this$0;

    C0698r(C0704v vVar, StateListDrawable stateListDrawable) {
        this.this$0 = vVar;
        this.f812gv = stateListDrawable;
    }

    public void onClick(View view) {
        if (this.f812gv.getCurrent() != null) {
            this.this$0.mDeleteListener.onChipDelete();
        }
    }
}
