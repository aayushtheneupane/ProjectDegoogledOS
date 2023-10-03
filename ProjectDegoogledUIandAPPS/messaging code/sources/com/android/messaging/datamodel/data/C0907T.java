package com.android.messaging.datamodel.data;

import android.content.Context;
import android.database.Cursor;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.data.T */
public class C0907T {

    /* renamed from: hC */
    private ParticipantData f1208hC;
    private final Context mContext;
    private int mItemId;
    private String mTitle;

    public C0907T(Context context) {
        this.mContext = context;
    }

    /* renamed from: Ye */
    public ParticipantData mo6373Ye() {
        return this.f1208hC;
    }

    /* renamed from: a */
    public void mo6374a(Cursor cursor, ParticipantData participantData, int i) {
        this.mItemId = i;
        this.f1208hC = participantData;
        if (i == 0) {
            this.mTitle = this.mContext.getString(R.string.notifications_enabled_conversation_pref_title);
        } else if (i != 1) {
            C1424b.fail("Unsupported conversation option type!");
        } else {
            C1424b.m3594t(participantData);
            this.mTitle = this.mContext.getString(participantData.isBlocked() ? R.string.unblock_contact_title : R.string.block_contact_title, new Object[]{participantData.mo6344mh()});
        }
    }

    public int getItemId() {
        return this.mItemId;
    }

    public String getTitle() {
        return this.mTitle;
    }
}
