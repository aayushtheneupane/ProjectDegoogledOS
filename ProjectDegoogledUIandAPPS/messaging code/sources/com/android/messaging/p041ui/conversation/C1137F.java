package com.android.messaging.p041ui.conversation;

import android.graphics.Rect;
import com.android.messaging.R;
import com.android.messaging.p041ui.AttachmentPreview;

/* renamed from: com.android.messaging.ui.conversation.F */
class C1137F implements Runnable {

    /* renamed from: TG */
    final /* synthetic */ Rect f1819TG;

    /* renamed from: UG */
    final /* synthetic */ AttachmentPreview f1820UG;

    /* renamed from: VG */
    final /* synthetic */ ConversationMessageBubbleView f1821VG;

    C1137F(C1139H h, Rect rect, AttachmentPreview attachmentPreview, ConversationMessageBubbleView conversationMessageBubbleView) {
        this.f1819TG = rect;
        this.f1820UG = attachmentPreview;
        this.f1821VG = conversationMessageBubbleView;
    }

    public void run() {
        int width = this.f1819TG.width();
        this.f1820UG.mo6868Hb();
        ConversationMessageBubbleView conversationMessageBubbleView = this.f1821VG;
        conversationMessageBubbleView.mo7349d(width, conversationMessageBubbleView.findViewById(R.id.message_text_and_info).getMeasuredWidth());
    }
}
