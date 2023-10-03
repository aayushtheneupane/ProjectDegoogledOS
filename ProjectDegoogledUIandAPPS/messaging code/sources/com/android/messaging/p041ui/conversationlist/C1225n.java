package com.android.messaging.p041ui.conversationlist;

import android.net.Uri;
import android.view.View;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.conversationlist.n */
class C1225n implements View.OnClickListener {
    final /* synthetic */ ConversationListItemView this$0;

    C1225n(ConversationListItemView conversationListItemView) {
        this.this$0 = conversationListItemView;
    }

    public void onClick(View view) {
        Uri uri;
        String Hf = this.this$0.mData.mo6511Yf() ? this.this$0.mData.mo6492Hf() : this.this$0.mData.mo6510Xf();
        C1424b.m3592ia(C1481w.isImageType(Hf) || C1481w.m3830Ea(Hf));
        Uri If = this.this$0.mData.mo6511Yf() ? this.this$0.mData.mo6493If() : this.this$0.mData.getPreviewUri();
        if (C1481w.isImageType(Hf)) {
            if (this.this$0.mData.mo6511Yf()) {
                uri = MessagingContentProvider.m1271p(this.this$0.mData.mo6505Ue());
            } else {
                uri = MessagingContentProvider.m1267l(this.this$0.mData.mo6505Ue());
            }
            this.this$0.mHostInterface.mo7543a(If, C1486ya.m3858h(view), uri);
            return;
        }
        this.this$0.mHostInterface.mo7542a(If);
    }
}
