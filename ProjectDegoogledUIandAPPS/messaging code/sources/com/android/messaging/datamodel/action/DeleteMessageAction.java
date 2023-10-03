package com.android.messaging.datamodel.action;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

public class DeleteMessageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0821k();

    private DeleteMessageAction(String str) {
        this.f1057Oy.putString("message_id", str);
    }

    /* renamed from: f */
    public static void m1354f(String str) {
        new DeleteMessageAction(str).start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ue */
    public Bundle mo5955ue() {
        C0955p database = C0947h.get().getDatabase();
        String string = this.f1057Oy.getString("message_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        MessageData h = C0887c.m1671h(database, string);
        if (h != null) {
            C1424b.m3584Gj();
            database.beginTransaction();
            try {
                MessageData h2 = C0887c.m1671h(database, string);
                int i = 0;
                if (h2 != null) {
                    String Ue = h2.mo6250Ue();
                    int delete = database.delete("messages", "_id=?", new String[]{string});
                    if (!C0887c.m1653a(database, Ue)) {
                        C0887c.m1651a(database, Ue, false, false);
                    }
                    i = delete;
                }
                database.setTransactionSuccessful();
                if (i > 0) {
                    C1430e.m3625i("MessagingAppDataModel", "DeleteMessageAction: Deleted local message " + string);
                } else {
                    C1430e.m3630w("MessagingAppDataModel", "DeleteMessageAction: Could not delete local message " + string);
                }
                MessagingContentProvider.m1273r(h.mo6250Ue());
                MessagingContentProvider.m1263Wa();
                Uri Wg = h.mo6253Wg();
                if (Wg == null) {
                    C1430e.m3625i("MessagingAppDataModel", "DeleteMessageAction: Local message " + string + " has no telephony uri.");
                    return null;
                } else if (C1029y.m2446l(Wg) > 0) {
                    C1430e.m3625i("MessagingAppDataModel", "DeleteMessageAction: Deleted telephony message " + Wg);
                    return null;
                } else {
                    C1430e.m3630w("MessagingAppDataModel", "DeleteMessageAction: Could not delete message from telephony: messageId = " + string + ", telephony uri = " + Wg);
                    return null;
                }
            } finally {
                database.endTransaction();
            }
        } else {
            C1430e.m3630w("MessagingAppDataModel", "DeleteMessageAction: Message " + string + " no longer exists");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        mo5944Ee();
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ DeleteMessageAction(Parcel parcel, C0821k kVar) {
        super(parcel);
    }
}
