package com.android.messaging.p041ui.conversation;

import android.animation.Animator;
import android.animation.ObjectAnimator;

/* renamed from: com.android.messaging.ui.conversation.da */
class C1164da implements Animator.AnimatorListener {
    final /* synthetic */ ConversationMessageBubbleView this$0;

    C1164da(ConversationMessageBubbleView conversationMessageBubbleView) {
        this.this$0 = conversationMessageBubbleView;
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        ObjectAnimator unused = this.this$0.mAnimator = null;
        int unused2 = this.this$0.f1788Lh = 0;
        this.this$0.f1791Oh.getLayoutParams().width = -2;
        this.this$0.f1791Oh.requestLayout();
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }
}
