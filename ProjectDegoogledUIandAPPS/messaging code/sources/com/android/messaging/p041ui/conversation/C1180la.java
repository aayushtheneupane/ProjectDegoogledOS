package com.android.messaging.p041ui.conversation;

import android.view.View;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.AudioAttachmentView;
import com.android.messaging.p041ui.C1037D;

/* renamed from: com.android.messaging.ui.conversation.la */
class C1180la implements C1184na {
    final /* synthetic */ ConversationMessageView this$0;

    C1180la(ConversationMessageView conversationMessageView) {
        this.this$0 = conversationMessageView;
    }

    /* renamed from: a */
    public void mo7496a(View view, MessagePartData messagePartData) {
        AudioAttachmentView audioAttachmentView = (AudioAttachmentView) view;
        audioAttachmentView.mo6878a(messagePartData, this.this$0.mData.mo6546gg(), this.this$0.isSelected());
        audioAttachmentView.setBackground(C1037D.get().mo6944a(this.this$0.isSelected(), this.this$0.mData.mo6546gg(), false, this.this$0.mData.mo6532Ig(), this.this$0.mData.mo6562ug()));
    }
}
