package com.android.messaging.datamodel.action;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.util.C1430e;

public class RedownloadMmsAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0794H();

    RedownloadMmsAction(String str) {
        this.f1057Oy.putString("message_id", str);
    }

    /* renamed from: R */
    public static void m1435R(String str) {
        new RedownloadMmsAction(str).start();
    }

    /* renamed from: e */
    public static PendingIntent m1436e(Context context, String str) {
        return ActionServiceImpl.m1341a(context, new RedownloadMmsAction(str), 101, false);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        String string = this.f1057Oy.getString("message_id");
        C0955p database = C0947h.get().getDatabase();
        MessageData h = C0887c.m1671h(database, string);
        if (h == null || !h.mo6245Pg()) {
            C1430e.m3622e("MessagingApp", "Attempt to download a missing or un-redownloadable message");
            h = null;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("message_status", 102);
            contentValues.put("retry_start_timestamp", Long.valueOf(currentTimeMillis));
            C0887c.m1663c(database, h.getMessageId(), contentValues);
            MessagingContentProvider.m1273r(h.mo6250Ue());
            this.f1057Oy.putInt("sub_id", C0887c.m1667e(database, h.mo6275kf()));
            ProcessPendingMessagesAction.m1420a(false, this);
        }
        C0944e.m2090a(false, (String) null, 3);
        return h;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ RedownloadMmsAction(Parcel parcel, C0794H h) {
        super(parcel);
    }
}
