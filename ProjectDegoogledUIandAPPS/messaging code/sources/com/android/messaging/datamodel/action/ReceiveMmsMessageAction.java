package com.android.messaging.datamodel.action;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.C0967f;
import com.android.messaging.sms.C1029y;

public class ReceiveMmsMessageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0792F();

    public ReceiveMmsMessageAction(int i, byte[] bArr) {
        this.f1057Oy.putInt("sub_id", i);
        this.f1057Oy.putByteArray("push_data", bArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ue */
    public Bundle mo5955ue() {
        Context applicationContext = C0967f.get().getApplicationContext();
        int i = this.f1057Oy.getInt("sub_id", -1);
        String string = this.f1057Oy.getString("transaction_id");
        C1029y.m2432a(applicationContext, i, C1029y.m2443h(string, "UTF-8"), this.f1057Oy.getString("content_location"), 131);
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e8 A[Catch:{ all -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00eb A[Catch:{ all -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f6 A[Catch:{ all -> 0x0163 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0113  */
    /* renamed from: ve */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo5956ve() {
        /*
            r15 = this;
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            android.os.Bundle r1 = r15.f1057Oy
            java.lang.String r2 = "sub_id"
            r3 = -1
            int r1 = r1.getInt(r2, r3)
            android.os.Bundle r2 = r15.f1057Oy
            java.lang.String r3 = "push_data"
            byte[] r2 = r2.getByteArray(r3)
            com.android.messaging.datamodel.h r3 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r3 = r3.getDatabase()
            com.android.messaging.datamodel.data.ParticipantData r4 = com.android.messaging.datamodel.C0887c.m1633a((com.android.messaging.datamodel.C0955p) r3, (int) r1)
            long r5 = java.lang.System.currentTimeMillis()
            com.android.messaging.datamodel.h r7 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.U r7 = r7.mo6606be()
            r7.mo5917h(r5)
            int r5 = r4.getSubId()
            java.lang.String r6 = r4.mo6353sf()
            com.android.messaging.sms.DatabaseMessages$MmsMessage r0 = com.android.messaging.sms.C1029y.m2426a((android.content.Context) r0, (byte[]) r2, (int) r5, (java.lang.String) r6)
            java.lang.String r2 = "MessagingAppDataModel"
            r11 = 0
            if (r0 == 0) goto L_0x0168
            long r5 = r0.mThreadId
            java.util.List r5 = com.android.messaging.sms.C1029y.m2400A(r5)
            java.lang.String r6 = r0.getUri()
            java.lang.String r5 = com.android.messaging.sms.C1029y.m2431a((java.util.List) r5, (java.lang.String) r6)
            if (r5 != 0) goto L_0x005c
            java.lang.String r5 = "Received an MMS without sender address; using unknown sender."
            com.android.messaging.util.C1430e.m3630w(r2, r5)
            java.lang.String r5 = "ʼUNKNOWN_SENDER!ʼ"
        L_0x005c:
            com.android.messaging.datamodel.data.ParticipantData r5 = com.android.messaging.datamodel.data.ParticipantData.m1835g(r5, r1)
            java.lang.String r6 = r5.mo6353sf()
            boolean r9 = com.android.messaging.datamodel.C0887c.m1670g(r3, r6)
            r6 = 1
            if (r9 != 0) goto L_0x00ba
            com.android.messaging.f r7 = com.android.messaging.C0967f.get()
            android.content.Context r7 = r7.getApplicationContext()
            android.content.res.Resources r7 = r7.getResources()
            com.android.messaging.util.h r8 = com.android.messaging.util.C1451h.m3725ha(r1)
            r10 = 2131820639(0x7f11005f, float:1.9273999E38)
            java.lang.String r10 = r7.getString(r10)
            r12 = 2131034115(0x7f050003, float:1.7678738E38)
            boolean r12 = r7.getBoolean(r12)
            boolean r10 = r8.getBoolean(r10, r12)
            if (r10 == 0) goto L_0x00b5
            r10 = 2131820642(0x7f110062, float:1.9274005E38)
            java.lang.String r10 = r7.getString(r10)
            r12 = 2131034116(0x7f050004, float:1.767874E38)
            boolean r7 = r7.getBoolean(r12)
            boolean r7 = r8.getBoolean(r10, r7)
            com.android.messaging.util.sa r8 = com.android.messaging.util.C1474sa.get(r1)
            if (r7 == 0) goto L_0x00ad
            boolean r7 = r8.isDataRoamingEnabled()
            if (r7 != 0) goto L_0x00b3
        L_0x00ad:
            boolean r7 = r8.isRoaming()
            if (r7 != 0) goto L_0x00b5
        L_0x00b3:
            r7 = r6
            goto L_0x00b6
        L_0x00b5:
            r7 = r11
        L_0x00b6:
            if (r7 == 0) goto L_0x00ba
            r12 = r6
            goto L_0x00bb
        L_0x00ba:
            r12 = r11
        L_0x00bb:
            long r7 = r0.mThreadId
            java.lang.String r1 = com.android.messaging.datamodel.C0887c.m1635a((com.android.messaging.datamodel.C0955p) r3, (long) r7, (boolean) r9, (int) r1)
            com.android.messaging.datamodel.h r7 = com.android.messaging.datamodel.C0947h.get()
            boolean r7 = r7.mo6588L(r1)
            com.android.messaging.datamodel.h r8 = com.android.messaging.datamodel.C0947h.get()
            boolean r8 = r8.mo6589M(r1)
            r0.f1490_b = r7
            if (r8 != 0) goto L_0x00d9
            if (r9 == 0) goto L_0x00d8
            goto L_0x00d9
        L_0x00d8:
            r6 = r11
        L_0x00d9:
            r0.f1485BA = r6
            r3.beginTransaction()
            java.lang.String r13 = com.android.messaging.datamodel.C0887c.m1639a((com.android.messaging.datamodel.C0955p) r3, (com.android.messaging.datamodel.data.ParticipantData) r5)     // Catch:{ all -> 0x0163 }
            java.lang.String r4 = com.android.messaging.datamodel.C0887c.m1639a((com.android.messaging.datamodel.C0955p) r3, (com.android.messaging.datamodel.data.ParticipantData) r4)     // Catch:{ all -> 0x0163 }
            if (r12 == 0) goto L_0x00eb
            r5 = 104(0x68, float:1.46E-43)
            goto L_0x00ed
        L_0x00eb:
            r5 = 101(0x65, float:1.42E-43)
        L_0x00ed:
            com.android.messaging.datamodel.data.MessageData r14 = com.android.messaging.sms.C1029y.m2424a((com.android.messaging.sms.DatabaseMessages$MmsMessage) r0, (java.lang.String) r1, (java.lang.String) r13, (java.lang.String) r4, (int) r5)     // Catch:{ all -> 0x0163 }
            com.android.messaging.datamodel.C0887c.m1643a((com.android.messaging.datamodel.C0955p) r3, (com.android.messaging.datamodel.data.MessageData) r14)     // Catch:{ all -> 0x0163 }
            if (r12 != 0) goto L_0x010b
            java.lang.String r6 = r14.getMessageId()     // Catch:{ all -> 0x0163 }
            long r7 = r14.mo6288rg()     // Catch:{ all -> 0x0163 }
            r10 = 1
            r4 = r3
            r5 = r1
            com.android.messaging.datamodel.C0887c.m1647a(r4, r5, r6, r7, r9, r10)     // Catch:{ all -> 0x0163 }
            com.android.messaging.datamodel.data.ParticipantData r4 = com.android.messaging.datamodel.data.ParticipantData.m1840l(r3, r13)     // Catch:{ all -> 0x0163 }
            com.android.messaging.datamodel.action.C0819i.m1493a(r1, r4, r14)     // Catch:{ all -> 0x0163 }
        L_0x010b:
            r3.setTransactionSuccessful()     // Catch:{ all -> 0x0163 }
            r3.endTransaction()
            if (r12 != 0) goto L_0x0136
            java.lang.String r3 = r14.mo6250Ue()
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r3)
            com.android.messaging.datamodel.MessagingContentProvider.m1265Ya()
            r3 = 3
            com.android.messaging.datamodel.C0944e.m2090a((boolean) r11, (java.lang.String) r1, (int) r3)
            android.os.Bundle r1 = r15.f1057Oy
            java.lang.String r3 = r0.f1492aE
            java.lang.String r4 = "transaction_id"
            r1.putString(r4, r3)
            android.os.Bundle r1 = r15.f1057Oy
            java.lang.String r0 = r0.f1489_D
            java.lang.String r3 = "content_location"
            r1.putString(r3, r0)
            r15.mo5944Ee()
        L_0x0136:
            java.lang.String r0 = "ReceiveMmsMessageAction: Received MMS message "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r1 = r14.getMessageId()
            r0.append(r1)
            java.lang.String r1 = " in conversation "
            r0.append(r1)
            java.lang.String r1 = r14.mo6250Ue()
            r0.append(r1)
            java.lang.String r1 = ", uri = "
            r0.append(r1)
            android.net.Uri r1 = r14.mo6253Wg()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3625i(r2, r0)
            goto L_0x016e
        L_0x0163:
            r15 = move-exception
            r3.endTransaction()
            throw r15
        L_0x0168:
            java.lang.String r0 = "ReceiveMmsMessageAction: Skipping processing of incoming PDU"
            com.android.messaging.util.C1430e.m3622e(r2, r0)
            r14 = 0
        L_0x016e:
            com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1420a(r11, r15)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ReceiveMmsMessageAction.mo5956ve():java.lang.Object");
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ ReceiveMmsMessageAction(Parcel parcel, C0792F f) {
        super(parcel);
    }
}
