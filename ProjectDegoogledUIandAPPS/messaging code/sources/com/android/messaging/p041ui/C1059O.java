package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.O */
public final class C1059O extends FrameLayout.LayoutParams {
    public C1059O(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: bc */
    public int mo7053bc() {
        if (C1464na.m3756Wj()) {
            return getMarginEnd();
        }
        return this.rightMargin;
    }

    /* renamed from: cc */
    public int mo7054cc() {
        if (C1464na.m3756Wj()) {
            return getMarginStart();
        }
        return this.leftMargin;
    }

    public C1059O(int i, int i2) {
        super(i, i2);
    }

    public C1059O(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }
}
