package com.android.messaging.p041ui.conversationlist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0586qa;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1045H;

/* renamed from: com.android.messaging.ui.conversationlist.h */
public class C1219h extends C1045H {
    private final C1227p mClivHostInterface;

    public C1219h(Context context, Cursor cursor, C1227p pVar) {
        super(context, cursor, 0);
        this.mClivHostInterface = pVar;
        setHasStableIds(true);
    }

    /* renamed from: a */
    public void mo7006a(C0586qa qaVar, Context context, Cursor cursor) {
        ((C1218g) qaVar).mView.mo7562a(cursor, this.mClivHostInterface);
    }

    /* renamed from: a */
    public C0586qa mo7005a(Context context, ViewGroup viewGroup, int i) {
        return new C1218g((ConversationListItemView) LayoutInflater.from(context).inflate(R.layout.conversation_list_item_view, (ViewGroup) null));
    }
}
