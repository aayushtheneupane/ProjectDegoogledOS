package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.text.format.Formatter;
import android.widget.TextView;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.C1488za;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversation.m */
class C1181m extends C1478ua {

    /* renamed from: Nd */
    private final TextView f1871Nd;
    private final Context mContext;

    public C1181m(Context context, TextView textView) {
        this.mContext = context;
        this.f1871Nd = textView;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Object mo6323a(Object[] objArr) {
        long j = 0;
        for (MessagePartData messagePartData : ((List[]) objArr)[0]) {
            if (messagePartData.getContentUri() != null) {
                j += C1488za.m3874w(messagePartData.getContentUri());
            }
        }
        return Long.valueOf(j);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Long l = (Long) obj;
        TextView textView = this.f1871Nd;
        if (textView != null) {
            textView.setText(Formatter.formatFileSize(this.mContext, l.longValue()));
            this.f1871Nd.setVisibility(0);
        }
    }
}
