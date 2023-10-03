package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0586qa;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1045H;
import com.android.messaging.p041ui.C1114b;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversation.ca */
public class C1162ca extends C1045H {

    /* renamed from: Wf */
    private boolean f1867Wf;
    private final C1186oa mHost;

    /* renamed from: qr */
    private final View.OnClickListener f1868qr;

    /* renamed from: rr */
    private final View.OnLongClickListener f1869rr;

    /* renamed from: sr */
    private String f1870sr;

    public C1162ca(Context context, Cursor cursor, C1186oa oaVar, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        super(context, cursor, 0);
        this.mHost = oaVar;
        this.f1868qr = onClickListener;
        this.f1869rr = onLongClickListener;
        setHasStableIds(true);
    }

    /* renamed from: A */
    public void mo7480A(String str) {
        this.f1870sr = str;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void mo7006a(C0586qa qaVar, Context context, Cursor cursor) {
        C1160ba baVar = (C1160ba) qaVar;
        C1424b.m3592ia(baVar.mView instanceof ConversationMessageView);
        ((ConversationMessageView) baVar.mView).mo7353a(cursor, this.f1867Wf, this.f1870sr);
    }

    /* renamed from: e */
    public void mo7481e(boolean z, boolean z2) {
        if (this.f1867Wf != z) {
            this.f1867Wf = z;
            if (z2) {
                notifyDataSetChanged();
            }
        }
    }

    /* renamed from: a */
    public C0586qa mo7005a(Context context, ViewGroup viewGroup, int i) {
        ConversationMessageView conversationMessageView = (ConversationMessageView) LayoutInflater.from(context).inflate(R.layout.conversation_message_view, (ViewGroup) null);
        conversationMessageView.mo7355a(this.mHost);
        conversationMessageView.mo7354a((C1114b) null);
        return new C1160ba(conversationMessageView, this.f1868qr, this.f1869rr);
    }
}
