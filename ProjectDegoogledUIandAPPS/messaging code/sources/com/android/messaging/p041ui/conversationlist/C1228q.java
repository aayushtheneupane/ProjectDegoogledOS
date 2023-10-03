package com.android.messaging.p041ui.conversationlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.android.messaging.ui.conversationlist.q */
class C1228q extends AnimatorListenerAdapter {

    /* renamed from: D */
    final /* synthetic */ ConversationListItemView f1937D;
    final /* synthetic */ C1230s this$0;

    C1228q(C1230s sVar, ConversationListItemView conversationListItemView) {
        this.this$0 = sVar;
        this.f1937D = conversationListItemView;
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.m3134c(this.f1937D);
    }
}
