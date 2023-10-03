package com.android.messaging.p041ui.conversation;

import android.view.View;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.C1037D;
import com.android.messaging.p041ui.PersonItemView;

/* renamed from: com.android.messaging.ui.conversation.ma */
class C1182ma implements C1184na {
    final /* synthetic */ ConversationMessageView this$0;

    C1182ma(ConversationMessageView conversationMessageView) {
        this.this$0 = conversationMessageView;
    }

    /* renamed from: a */
    public void mo7496a(View view, MessagePartData messagePartData) {
        int i;
        PersonItemView personItemView = (PersonItemView) view;
        personItemView.mo7066d(C0947h.get().mo6600a(this.this$0.getContext(), messagePartData));
        personItemView.setBackground(C1037D.get().mo6944a(this.this$0.isSelected(), this.this$0.mData.mo6546gg(), false, this.this$0.mData.mo6532Ig(), this.this$0.mData.mo6562ug()));
        boolean isSelected = this.this$0.isSelected();
        int i2 = R.color.message_text_color_incoming;
        if (isSelected) {
            i = R.color.message_text_color_incoming;
        } else {
            if (!this.this$0.mData.mo6546gg()) {
                i2 = R.color.message_text_color_outgoing;
            }
            i = this.this$0.mData.mo6546gg() ? R.color.timestamp_text_incoming : R.color.timestamp_text_outgoing;
        }
        personItemView.mo7074z(this.this$0.getResources().getColor(i2));
        personItemView.mo7073y(this.this$0.getResources().getColor(i));
    }
}
