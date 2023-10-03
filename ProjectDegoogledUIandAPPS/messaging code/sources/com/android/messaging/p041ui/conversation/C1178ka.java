package com.android.messaging.p041ui.conversation;

import android.view.View;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.VideoThumbnailView;

/* renamed from: com.android.messaging.ui.conversation.ka */
class C1178ka implements C1184na {
    final /* synthetic */ ConversationMessageView this$0;

    C1178ka(ConversationMessageView conversationMessageView) {
        this.this$0 = conversationMessageView;
    }

    /* renamed from: a */
    public void mo7496a(View view, MessagePartData messagePartData) {
        ((VideoThumbnailView) view).mo7096b(messagePartData, this.this$0.mData.mo6546gg());
    }
}
