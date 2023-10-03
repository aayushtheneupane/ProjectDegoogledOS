package com.android.messaging.p041ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0916b;
import com.android.messaging.util.C1424b;
import p000a.p006c.p007a.C0031c;

/* renamed from: com.android.messaging.ui.t */
class C1381t extends C0031c {
    final /* synthetic */ BlockedParticipantsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1381t(BlockedParticipantsFragment blockedParticipantsFragment, Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.this$0 = blockedParticipantsFragment;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        C1424b.m3592ia(view instanceof BlockedParticipantListItemView);
        ((BlockedParticipantListItemView) view).mo6900a(((C0916b) this.this$0.mBinding.getData()).mo6401j(cursor));
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.blocked_participant_list_item_view, viewGroup, false);
    }
}
