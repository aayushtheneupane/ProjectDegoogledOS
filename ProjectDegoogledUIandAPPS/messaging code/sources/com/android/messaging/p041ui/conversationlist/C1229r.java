package com.android.messaging.p041ui.conversationlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.android.messaging.ui.conversationlist.r */
class C1229r extends AnimatorListenerAdapter {

    /* renamed from: D */
    final /* synthetic */ ConversationListItemView f1938D;
    final /* synthetic */ C1230s this$0;

    C1229r(C1230s sVar, ConversationListItemView conversationListItemView) {
        this.this$0 = sVar;
        this.f1938D = conversationListItemView;
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.m3134c(this.f1938D);
    }
}
