package com.android.messaging.p041ui.conversation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.messaging.R;
import com.android.messaging.annotation.VisibleForAnimation;
import com.android.messaging.datamodel.data.C0935r;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.conversation.ConversationMessageBubbleView */
public class ConversationMessageBubbleView extends LinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: Lh */
    public int f1788Lh;

    /* renamed from: Mh */
    private boolean f1789Mh;

    /* renamed from: Nh */
    private int f1790Nh;
    /* access modifiers changed from: private */

    /* renamed from: Oh */
    public ViewGroup f1791Oh;
    /* access modifiers changed from: private */
    public ObjectAnimator mAnimator;
    private final C0935r mData = new C0935r();
    private int mIntrinsicWidth;

    public ConversationMessageBubbleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: d */
    public void mo7349d(int i, int i2) {
        ObjectAnimator objectAnimator = this.mAnimator;
        if (objectAnimator != null) {
            objectAnimator.setIntValues(new int[]{this.f1790Nh, i2});
            return;
        }
        this.f1790Nh = i;
        this.mAnimator = ObjectAnimator.ofInt(this, "morphWidth", new int[]{i, i2});
        this.mAnimator.setDuration((long) C1486ya.f2354YK);
        this.mAnimator.addListener(new C1164da(this));
        this.mAnimator.start();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1791Oh = (ViewGroup) findViewById(R.id.message_text_and_info);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int i3 = this.mIntrinsicWidth;
        if (i3 == 0 && measuredWidth != i3) {
            if (this.f1789Mh) {
                mo7349d(i3, measuredWidth);
            }
            this.mIntrinsicWidth = measuredWidth;
        }
        if (this.f1788Lh > 0) {
            this.f1791Oh.getLayoutParams().width = this.f1788Lh;
        } else {
            this.f1791Oh.getLayoutParams().width = -2;
        }
        this.f1791Oh.requestLayout();
    }

    @VisibleForAnimation
    public void setMorphWidth(int i) {
        this.f1788Lh = i;
        requestLayout();
    }

    /* renamed from: a */
    public void mo7348a(C0936s sVar) {
        this.f1789Mh = !this.mData.mo6524a(sVar) && !sVar.mo6553mf();
        if (this.mAnimator == null) {
            this.f1788Lh = 0;
        }
    }
}
