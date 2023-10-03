package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.conversation.MessageBubbleBackground */
public class MessageBubbleBackground extends LinearLayout {

    /* renamed from: Ph */
    private final int f1826Ph;

    public MessageBubbleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1826Ph = context.getResources().getDimensionPixelSize(R.dimen.conversation_bubble_width_snap);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i) - paddingRight, (int) (Math.ceil((double) (((float) (getMeasuredWidth() - paddingRight)) / ((float) this.f1826Ph))) * ((double) this.f1826Ph))) + paddingRight, 1073741824), i2);
    }
}
