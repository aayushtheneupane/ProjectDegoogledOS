package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.sms.C1026v;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;

public class ReceiveSmsMessageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0793G();

    public ReceiveSmsMessageAction(ContentValues contentValues) {
        this.f1057Oy.putParcelable("message_values", contentValues);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        MessageData messageData;
        String str;
        boolean z;
        int i;
        Context applicationContext = C0967f.get().getApplicationContext();
        ContentValues contentValues = (ContentValues) this.f1057Oy.getParcelable("message_values");
        C0955p database = C0947h.get().getDatabase();
        int asInteger = contentValues.getAsInteger("sub_id");
        if (asInteger == null) {
            asInteger = -1;
        }
        Integer num = asInteger;
        String asString = contentValues.getAsString("address");
        if (TextUtils.isEmpty(asString)) {
            C1430e.m3630w("MessagingAppDataModel", "Received an SMS without an address; using unknown sender.");
            ParticipantData.m1842vh();
            asString = "ʼUNKNOWN_SENDER!ʼ";
            contentValues.put("address", asString);
        }
        ParticipantData g = ParticipantData.m1835g(asString, num.intValue());
        long longValue = contentValues.getAsLong("date").longValue();
        C0947h.get().mo6606be().mo5917h(longValue);
        long orCreateThreadId = C1026v.getOrCreateThreadId(applicationContext, asString);
        contentValues.put("thread_id", Long.valueOf(orCreateThreadId));
        boolean g2 = C0887c.m1670g(database, g.mo6353sf());
        String a = C0887c.m1636a(database, orCreateThreadId, g2, g);
        boolean L = C0947h.get().mo6588L(a);
        boolean M = C0947h.get().mo6589M(a);
        if (!C1464na.m3762ak()) {
            boolean z2 = contentValues.getAsBoolean("read").booleanValue() || L;
            boolean z3 = z2 || M || g2;
            contentValues.put("read", z2 ? 1 : 0);
            contentValues.put("seen", 1);
            Uri insert = applicationContext.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, contentValues);
            if (insert == null) {
                C1430e.m3622e("MessagingAppDataModel", "ReceiveSmsMessageAction: Failed to insert SMS into telephony!");
            } else if (Log.isLoggable("MessagingAppDataModel", 3)) {
                C1430e.m3620d("MessagingAppDataModel", "ReceiveSmsMessageAction: Inserted SMS message into telephony, uri = " + insert);
            }
            String asString2 = contentValues.getAsString("body");
            String asString3 = contentValues.getAsString("subject");
            long longValue2 = contentValues.getAsLong("date_sent").longValue();
            ParticipantData sa = ParticipantData.m1841sa(num.intValue());
            Integer asInteger2 = contentValues.getAsInteger("reply_path_present");
            String asString4 = contentValues.getAsString("service_center");
            if (asInteger2 == null || asInteger2.intValue() != 1 || TextUtils.isEmpty(asString4)) {
                asString4 = null;
            }
            database.beginTransaction();
            try {
                String a2 = C0887c.m1639a(database, g);
                messageData = MessageData.m1751a(insert, a, a2, C0887c.m1639a(database, sa), asString2, asString3, longValue2, longValue, z3, z2);
                C0887c.m1643a(database, messageData);
                String str2 = a2;
                boolean z4 = g2;
                String str3 = asString4;
                str = a;
                C0887c.m1646a(database, a, messageData.getMessageId(), messageData.mo6288rg(), z4, str3, true);
                C0819i.m1493a(str, ParticipantData.m1840l(database, str2), messageData);
                database.setTransactionSuccessful();
                database.endTransaction();
                C1430e.m3625i("MessagingAppDataModel", "ReceiveSmsMessageAction: Received SMS message " + messageData.getMessageId() + " in conversation " + messageData.mo6250Ue() + ", uri = " + insert);
                this.f1057Oy.putInt("sub_id", num.intValue());
                z = false;
                ProcessPendingMessagesAction.m1420a(false, this);
                i = 3;
            } catch (Throwable th) {
                database.endTransaction();
                throw th;
            }
        } else {
            str = a;
            i = 3;
            if (Log.isLoggable("MessagingAppDataModel", 3)) {
                C1430e.m3620d("MessagingAppDataModel", "ReceiveSmsMessageAction: Not inserting received SMS message for secondary user.");
            }
            z = false;
            messageData = null;
        }
        C0944e.m2090a(z, str, i);
        MessagingContentProvider.m1273r(str);
        MessagingContentProvider.m1265Ya();
        return messageData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ ReceiveSmsMessageAction(Parcel parcel, C0793G g) {
        super(parcel);
    }
}
