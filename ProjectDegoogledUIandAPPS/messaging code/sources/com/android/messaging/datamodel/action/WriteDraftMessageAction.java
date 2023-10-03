package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.util.C1430e;

public class WriteDraftMessageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0810Y();

    private WriteDraftMessageAction(String str, MessageData messageData) {
        this.f1057Oy.putString("conversationId", str);
        this.f1057Oy.putParcelable("message", messageData);
    }

    /* renamed from: a */
    public static void m1467a(String str, MessageData messageData) {
        new WriteDraftMessageAction(str, messageData).start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C0955p database = C0947h.get().getDatabase();
        String string = this.f1057Oy.getString("conversationId");
        MessageData messageData = (MessageData) this.f1057Oy.getParcelable("message");
        if (messageData.mo6275kf() == null || messageData.mo6283pg() == null) {
            C0934q j = C0934q.m1992j(database, string);
            if (j != null) {
                String kf = j.mo6522kf();
                if (messageData.mo6275kf() == null) {
                    messageData.mo6261ca(kf);
                }
                if (messageData.mo6283pg() == null) {
                    messageData.mo6259ba(kf);
                }
            } else {
                C1430e.m3630w("MessagingAppDataModel", "Conversation " + string + "already deleted before saving draft message " + messageData.getMessageId() + ". Aborting WriteDraftMessageAction.");
                return null;
            }
        }
        String a = C0887c.m1640a(database, string, messageData, 2);
        MessagingContentProvider.m1263Wa();
        MessagingContentProvider.m1272q(string);
        return a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ WriteDraftMessageAction(Parcel parcel, C0810Y y) {
        super(parcel);
    }
}
