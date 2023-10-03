package com.android.messaging.p041ui.conversationlist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0586qa;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1045H;
import com.android.messaging.p041ui.PersonItemView;

/* renamed from: com.android.messaging.ui.conversationlist.z */
public class C1237z extends C1045H {
    /* access modifiers changed from: private */
    public final C1234w mHostInterface;

    public C1237z(Context context, Cursor cursor, C1234w wVar) {
        super(context, cursor, 0);
        this.mHostInterface = wVar;
        setHasStableIds(true);
    }

    /* renamed from: a */
    public void mo7006a(C0586qa qaVar, Context context, Cursor cursor) {
        ((C1236y) qaVar).mo7597c(cursor);
    }

    /* renamed from: a */
    public C0586qa mo7005a(Context context, ViewGroup viewGroup, int i) {
        return new C1236y(this, (PersonItemView) LayoutInflater.from(context).inflate(R.layout.people_list_item_view, (ViewGroup) null));
    }
}
