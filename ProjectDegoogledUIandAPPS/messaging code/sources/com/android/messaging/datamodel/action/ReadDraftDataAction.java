package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.util.C1430e;

public class ReadDraftDataAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0789C();

    class DraftData {

        /* renamed from: Zy */
        public final C0934q f1080Zy;
        public final MessageData message;

        DraftData(ReadDraftDataAction readDraftDataAction, MessageData messageData, C0934q qVar) {
            this.message = messageData;
            this.f1080Zy = qVar;
        }
    }

    private ReadDraftDataAction(String str, MessageData messageData, String str2) {
        super(str2);
        this.f1057Oy.putString("conversationId", str);
        this.f1057Oy.putParcelable("draftMessage", messageData);
    }

    /* renamed from: a */
    public static C0791E m1430a(String str, MessageData messageData, Object obj, C0790D d) {
        C0791E e = new C0791E(obj, d);
        new ReadDraftDataAction(str, messageData, e.mo6033Ne()).mo5947a((C0813c) e);
        return e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C0955p database = C0947h.get().getDatabase();
        String string = this.f1057Oy.getString("conversationId");
        MessageData messageData = (MessageData) this.f1057Oy.getParcelable("draftMessage");
        C0934q j = C0934q.m1992j(database, string);
        MessageData messageData2 = null;
        if (j == null) {
            return null;
        }
        if (messageData == null) {
            messageData2 = C0887c.m1657b(database, string, j.mo6522kf());
        }
        if (messageData2 == null) {
            messageData2 = MessageData.m1752a(string, j.mo6522kf(), messageData);
            C1430e.m3620d("MessagingApp", "ReadDraftMessage: created draft. conversationId=" + string + " selfId=" + j.mo6522kf());
        } else {
            C1430e.m3620d("MessagingApp", "ReadDraftMessage: read draft. conversationId=" + string + " selfId=" + j.mo6522kf());
        }
        return new DraftData(this, messageData2, j);
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ ReadDraftDataAction(Parcel parcel, C0789C c) {
        super(parcel);
    }
}
