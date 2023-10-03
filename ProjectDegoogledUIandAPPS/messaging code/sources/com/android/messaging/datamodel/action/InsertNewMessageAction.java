package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.util.C1424b;

public class InsertNewMessageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0829s();

    /* renamed from: Ry */
    private static long f1060Ry = -1;

    private InsertNewMessageAction(MessageData messageData, int i) {
        this.f1057Oy.putParcelable("message", messageData);
        this.f1057Oy.putInt("sub_id", i);
    }

    /* renamed from: He */
    public static long m1376He() {
        return f1060Ry;
    }

    /* renamed from: a */
    public static void m1378a(MessageData messageData, int i) {
        C1424b.m3591ha(i == -1);
        new InsertNewMessageAction(messageData, i).start();
    }

    /* renamed from: c */
    public static void m1379c(MessageData messageData) {
        InsertNewMessageAction insertNewMessageAction = new InsertNewMessageAction(messageData, -1);
        insertNewMessageAction.f1057Oy.putParcelable("message", messageData);
        insertNewMessageAction.start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c3  */
    /* renamed from: ve */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo5956ve() {
        /*
            r30 = this;
            r0 = r30
            android.os.Bundle r1 = r0.f1057Oy
            java.lang.String r2 = "message"
            android.os.Parcelable r1 = r1.getParcelable(r2)
            com.android.messaging.datamodel.data.MessageData r1 = (com.android.messaging.datamodel.data.MessageData) r1
            r2 = -1
            java.lang.String r3 = "sub_id"
            r4 = 0
            r5 = 0
            java.lang.String r6 = "MessagingAppDataModel"
            if (r1 != 0) goto L_0x00c9
            java.lang.String r1 = "InsertNewMessageAction: Creating MessageData with provided data"
            com.android.messaging.util.C1430e.m3625i(r6, r1)
            android.os.Bundle r1 = r0.f1057Oy
            java.lang.String r7 = "recipients"
            java.lang.String r1 = r1.getString(r7)
            android.os.Bundle r7 = r0.f1057Oy
            java.lang.String r8 = "message_text"
            java.lang.String r7 = r7.getString(r8)
            android.os.Bundle r8 = r0.f1057Oy
            java.lang.String r9 = "subject_text"
            java.lang.String r8 = r8.getString(r9)
            android.os.Bundle r9 = r0.f1057Oy
            int r9 = r9.getInt(r3, r2)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r11 = ","
            java.lang.String[] r1 = r1.split(r11)
            int r11 = r1.length
            r12 = r4
        L_0x0045:
            if (r12 >= r11) goto L_0x0053
            r13 = r1[r12]
            com.android.messaging.datamodel.data.ParticipantData r13 = com.android.messaging.datamodel.data.ParticipantData.m1835g(r13, r9)
            r10.add(r13)
            int r12 = r12 + 1
            goto L_0x0045
        L_0x0053:
            int r1 = r10.size()
            if (r1 != 0) goto L_0x005f
            java.lang.String r1 = "InsertNewMessage: Empty participants"
            com.android.messaging.util.C1424b.fail(r1)
            goto L_0x00a0
        L_0x005f:
            com.android.messaging.datamodel.h r1 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r1 = r1.getDatabase()
            com.android.messaging.datamodel.C0887c.m1672h(r10)
            java.util.ArrayList r11 = com.android.messaging.datamodel.C0887c.m1669g(r10)
            int r12 = r11.size()
            if (r12 != 0) goto L_0x007a
            java.lang.String r1 = "InsertNewMessage: Empty recipients"
            com.android.messaging.util.C1424b.fail(r1)
            goto L_0x00a0
        L_0x007a:
            com.android.messaging.f r12 = com.android.messaging.C0967f.get()
            android.content.Context r12 = r12.getApplicationContext()
            long r12 = com.android.messaging.sms.C1029y.m2417a((android.content.Context) r12, (java.util.List) r11)
            r14 = 0
            int r14 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r14 >= 0) goto L_0x00a2
            java.lang.String r1 = "InsertNewMessage: Couldn't get threadId in SMS db for these recipients: "
            java.lang.StringBuilder r1 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r1)
            java.lang.String r7 = r11.toString()
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            com.android.messaging.util.C1424b.fail(r1)
        L_0x00a0:
            r1 = r5
            goto L_0x00c1
        L_0x00a2:
            java.lang.String r10 = com.android.messaging.datamodel.C0887c.m1637a((com.android.messaging.datamodel.C0955p) r1, (long) r12, (boolean) r4, (java.util.ArrayList) r10)
            com.android.messaging.datamodel.data.ParticipantData r1 = com.android.messaging.datamodel.C0887c.m1633a((com.android.messaging.datamodel.C0955p) r1, (int) r9)
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 == 0) goto L_0x00b9
            java.lang.String r1 = r1.getId()
            com.android.messaging.datamodel.data.MessageData r1 = com.android.messaging.datamodel.data.MessageData.m1757b(r10, r1, r7)
            goto L_0x00c1
        L_0x00b9:
            java.lang.String r1 = r1.getId()
            com.android.messaging.datamodel.data.MessageData r1 = com.android.messaging.datamodel.data.MessageData.m1753a(r10, r1, r7, r8)
        L_0x00c1:
            if (r1 != 0) goto L_0x00c9
            java.lang.String r0 = "InsertNewMessageAction: Could not create MessageData"
            com.android.messaging.util.C1430e.m3630w(r6, r0)
            return r5
        L_0x00c9:
            com.android.messaging.datamodel.h r7 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r7 = r7.getDatabase()
            java.lang.String r15 = r1.mo6250Ue()
            android.os.Bundle r8 = r0.f1057Oy
            int r8 = r8.getInt(r3, r2)
            if (r8 == r2) goto L_0x00e2
            com.android.messaging.datamodel.data.ParticipantData r2 = com.android.messaging.datamodel.C0887c.m1633a((com.android.messaging.datamodel.C0955p) r7, (int) r8)
            goto L_0x0138
        L_0x00e2:
            java.lang.String r8 = r1.mo6275kf()
            if (r8 != 0) goto L_0x011a
            com.android.messaging.datamodel.data.q r8 = com.android.messaging.datamodel.data.C0934q.m1992j(r7, r15)
            if (r8 == 0) goto L_0x00f3
            java.lang.String r8 = r8.mo6522kf()
            goto L_0x011a
        L_0x00f3:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r8 = "Conversation "
            r2.append(r8)
            r2.append(r15)
            java.lang.String r8 = "already deleted before sending draft message "
            r2.append(r8)
            java.lang.String r8 = r1.getMessageId()
            r2.append(r8)
            java.lang.String r8 = ". Aborting InsertNewMessageAction."
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            com.android.messaging.util.C1430e.m3630w(r6, r2)
            r2 = r5
            goto L_0x0138
        L_0x011a:
            com.android.messaging.datamodel.data.ParticipantData r8 = com.android.messaging.datamodel.C0887c.m1662c(r7, r8)
            int r9 = r8.getSubId()
            if (r9 != r2) goto L_0x0137
            boolean r2 = com.android.messaging.util.C1464na.m3759Zj()
            if (r2 == 0) goto L_0x0137
            com.android.messaging.util.sa r2 = com.android.messaging.util.C1474sa.getDefault()
            int r2 = r2.getDefaultSmsSubscriptionId()
            com.android.messaging.datamodel.data.ParticipantData r2 = com.android.messaging.datamodel.C0887c.m1633a((com.android.messaging.datamodel.C0955p) r7, (int) r2)
            goto L_0x0138
        L_0x0137:
            r2 = r8
        L_0x0138:
            if (r2 != 0) goto L_0x013b
            return r5
        L_0x013b:
            java.lang.String r8 = r2.getId()
            r1.mo6261ca(r8)
            java.lang.String r8 = r1.mo6283pg()
            if (r8 != 0) goto L_0x014f
            java.lang.String r8 = r2.getId()
            r1.mo6259ba(r8)
        L_0x014f:
            long r13 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r11 = com.android.messaging.datamodel.C0887c.m1665d(r7, r15)
            int r8 = r11.size()
            r9 = 1
            if (r8 >= r9) goto L_0x0164
            java.lang.String r0 = "InsertNewMessageAction: message recipients is empty"
            com.android.messaging.util.C1430e.m3630w(r6, r0)
            return r5
        L_0x0164:
            int r2 = r2.getSubId()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "InsertNewMessageAction: inserting new message for subId "
            r5.append(r8)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            com.android.messaging.util.C1430e.m3625i(r6, r5)
            android.os.Bundle r5 = r0.f1057Oy
            r5.putInt(r3, r2)
            int r3 = r1.getProtocol()
            if (r3 != 0) goto L_0x0188
            r4 = r9
        L_0x0188:
            java.lang.String r3 = ")"
            r5 = 3
            if (r4 == 0) goto L_0x035f
            int r4 = r11.size()
            if (r4 <= r9) goto L_0x0272
            r4 = 1
            long r4 = r4 + r13
            r8 = 2
            boolean r8 = android.util.Log.isLoggable(r6, r8)
            if (r8 == 0) goto L_0x01b1
            java.lang.String r8 = "InsertNewMessageAction: Inserting broadcast SMS message "
            java.lang.StringBuilder r8 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r8)
            java.lang.String r9 = r1.getMessageId()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.android.messaging.util.C1430e.m3628v(r6, r8)
        L_0x01b1:
            com.android.messaging.f r8 = com.android.messaging.C0967f.get()
            android.content.Context r16 = r8.getApplicationContext()
            com.android.messaging.datamodel.h r8 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r12 = r8.getDatabase()
            com.android.messaging.datamodel.h r8 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.U r8 = r8.mo6606be()
            r8.mo5917h(r4)
            long r25 = com.android.messaging.datamodel.C0887c.m1668f(r12, r15)
            java.lang.String r8 = " "
            java.lang.String r19 = android.text.TextUtils.join(r8, r11)
            java.lang.String r20 = r1.mo6274hf()
            android.net.Uri r17 = android.provider.Telephony.Sms.CONTENT_URI
            r23 = 0
            r24 = 2
            r18 = r2
            r21 = r4
            android.net.Uri r8 = com.android.messaging.sms.C1029y.m2420a((android.content.Context) r16, (android.net.Uri) r17, (int) r18, (java.lang.String) r19, (java.lang.String) r20, (long) r21, (int) r23, (int) r24, (long) r25)
            if (r8 == 0) goto L_0x0254
            java.lang.String r9 = r8.toString()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x0254
            r12.beginTransaction()
            r1.mo6257a((java.lang.String) r15, (android.net.Uri) r8, (long) r4)     // Catch:{ all -> 0x024d }
            r1.mo6289s(r4)     // Catch:{ all -> 0x024d }
            com.android.messaging.datamodel.C0887c.m1643a((com.android.messaging.datamodel.C0955p) r12, (com.android.messaging.datamodel.data.MessageData) r1)     // Catch:{ all -> 0x024d }
            java.lang.String r10 = r1.getMessageId()     // Catch:{ all -> 0x024d }
            r16 = 0
            r17 = 0
            r8 = r12
            r9 = r15
            r18 = r11
            r19 = r12
            r11 = r4
            r4 = r13
            r13 = r16
            r14 = r17
            com.android.messaging.datamodel.C0887c.m1647a(r8, r9, r10, r11, r13, r14)     // Catch:{ all -> 0x024b }
            r19.setTransactionSuccessful()     // Catch:{ all -> 0x024b }
            r19.endTransaction()
            r8 = 3
            boolean r8 = android.util.Log.isLoggable(r6, r8)
            if (r8 == 0) goto L_0x0244
            java.lang.String r8 = "InsertNewMessageAction: Inserted broadcast SMS message "
            java.lang.StringBuilder r8 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r8)
            java.lang.String r9 = r1.getMessageId()
            r8.append(r9)
            java.lang.String r9 = ", uri = "
            r8.append(r9)
            android.net.Uri r9 = r1.mo6253Wg()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.android.messaging.util.C1430e.m3620d(r6, r8)
        L_0x0244:
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r15)
            com.android.messaging.datamodel.MessagingContentProvider.m1265Ya()
            goto L_0x0270
        L_0x024b:
            r0 = move-exception
            goto L_0x0250
        L_0x024d:
            r0 = move-exception
            r19 = r12
        L_0x0250:
            r19.endTransaction()
            throw r0
        L_0x0254:
            r18 = r11
            r4 = r13
            java.lang.String r8 = "InsertNewMessageAction: No uri for broadcast SMS "
            java.lang.StringBuilder r8 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r8)
            java.lang.String r9 = r1.getMessageId()
            r8.append(r9)
            java.lang.String r9 = " inserted into telephony DB"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            com.android.messaging.util.C1430e.m3622e(r6, r8)
        L_0x0270:
            r8 = 0
            goto L_0x0276
        L_0x0272:
            r18 = r11
            r4 = r13
            r8 = r15
        L_0x0276:
            java.util.Iterator r9 = r18.iterator()
        L_0x027a:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0359
            java.lang.Object r10 = r9.next()
            java.lang.String r10 = (java.lang.String) r10
            f1060Ry = r4
            com.android.messaging.f r11 = com.android.messaging.C0967f.get()
            android.content.Context r11 = r11.getApplicationContext()
            com.android.messaging.datamodel.h r12 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.U r12 = r12.mo6606be()
            r12.mo5917h(r4)
            com.android.messaging.datamodel.h r12 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r12 = r12.getDatabase()
            if (r8 != 0) goto L_0x02b5
            long r13 = com.android.messaging.sms.C1029y.m2442g(r11, r10)
            r27 = r9
            com.android.messaging.datamodel.data.ParticipantData r9 = com.android.messaging.datamodel.data.ParticipantData.m1835g(r10, r2)
            r0 = 0
            java.lang.String r0 = com.android.messaging.datamodel.C0887c.m1636a((com.android.messaging.datamodel.C0955p) r12, (long) r13, (boolean) r0, (com.android.messaging.datamodel.data.ParticipantData) r9)
            goto L_0x02bc
        L_0x02b5:
            r27 = r9
            long r13 = com.android.messaging.datamodel.C0887c.m1668f(r12, r8)
            r0 = r8
        L_0x02bc:
            r25 = r13
            java.lang.String r9 = r1.mo6274hf()
            android.net.Uri r17 = android.provider.Telephony.Sms.CONTENT_URI
            r23 = -1
            r24 = 2
            r16 = r11
            r18 = r2
            r19 = r10
            r20 = r9
            r21 = r4
            android.net.Uri r10 = com.android.messaging.sms.C1029y.m2420a((android.content.Context) r16, (android.net.Uri) r17, (int) r18, (java.lang.String) r19, (java.lang.String) r20, (long) r21, (int) r23, (int) r24, (long) r25)
            if (r10 == 0) goto L_0x034e
            java.lang.String r11 = r10.toString()
            boolean r11 = android.text.TextUtils.isEmpty(r11)
            if (r11 != 0) goto L_0x034e
            r12.beginTransaction()
            java.lang.String r11 = r1.mo6275kf()     // Catch:{ all -> 0x0349 }
            com.android.messaging.datamodel.data.MessageData r9 = com.android.messaging.datamodel.data.MessageData.m1757b(r0, r11, r9)     // Catch:{ all -> 0x0349 }
            r9.mo6257a((java.lang.String) r0, (android.net.Uri) r10, (long) r4)     // Catch:{ all -> 0x0349 }
            com.android.messaging.datamodel.C0887c.m1643a((com.android.messaging.datamodel.C0955p) r12, (com.android.messaging.datamodel.data.MessageData) r9)     // Catch:{ all -> 0x0349 }
            if (r8 == 0) goto L_0x0306
            java.lang.String r18 = r9.getMessageId()     // Catch:{ all -> 0x0349 }
            r21 = 0
            r22 = 0
            r16 = r12
            r17 = r0
            r19 = r4
            com.android.messaging.datamodel.C0887c.m1647a(r16, r17, r18, r19, r21, r22)     // Catch:{ all -> 0x0349 }
        L_0x0306:
            r12.setTransactionSuccessful()     // Catch:{ all -> 0x0349 }
            r12.endTransaction()
            r10 = 3
            boolean r10 = android.util.Log.isLoggable(r6, r10)
            if (r10 == 0) goto L_0x0342
            java.lang.String r10 = "InsertNewMessageAction: Inserted SMS message "
            java.lang.StringBuilder r10 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r10)
            java.lang.String r11 = r9.getMessageId()
            r10.append(r11)
            java.lang.String r11 = " (uri = "
            r10.append(r11)
            android.net.Uri r11 = r9.mo6253Wg()
            r10.append(r11)
            java.lang.String r11 = ", timestamp = "
            r10.append(r11)
            long r11 = r9.mo6288rg()
            r10.append(r11)
            r10.append(r3)
            java.lang.String r9 = r10.toString()
            com.android.messaging.util.C1430e.m3620d(r6, r9)
        L_0x0342:
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r0)
            com.android.messaging.datamodel.MessagingContentProvider.m1265Ya()
            goto L_0x0353
        L_0x0349:
            r0 = move-exception
            r12.endTransaction()
            throw r0
        L_0x034e:
            java.lang.String r0 = "InsertNewMessageAction: No uri for SMS inserted into telephony DB"
            com.android.messaging.util.C1430e.m3622e(r6, r0)
        L_0x0353:
            r0 = r30
            r9 = r27
            goto L_0x027a
        L_0x0359:
            r0 = 0
            r2 = 1
            com.android.messaging.datamodel.C0887c.m1640a((com.android.messaging.datamodel.C0955p) r7, (java.lang.String) r15, (com.android.messaging.datamodel.data.MessageData) r0, (int) r2)
            goto L_0x03c8
        L_0x035f:
            r8 = r13
            r10 = 500(0x1f4, double:2.47E-321)
            long r13 = r8 + r10
            r8 = 1000(0x3e8, double:4.94E-321)
            long r13 = r13 / r8
            long r13 = r13 * r8
            com.android.messaging.datamodel.h r0 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r2 = r0.getDatabase()
            r2.beginTransaction()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            f1060Ry = r13     // Catch:{ all -> 0x03d2 }
            r0 = 0
            r1.mo6257a((java.lang.String) r15, (android.net.Uri) r0, (long) r13)     // Catch:{ all -> 0x03d2 }
            com.android.messaging.datamodel.C0887c.m1643a((com.android.messaging.datamodel.C0955p) r2, (com.android.messaging.datamodel.data.MessageData) r1)     // Catch:{ all -> 0x03d2 }
            java.lang.String r10 = r1.getMessageId()     // Catch:{ all -> 0x03d2 }
            r0 = 0
            r4 = 0
            r8 = r2
            r9 = r15
            r11 = r13
            r28 = r13
            r13 = r0
            r14 = r4
            com.android.messaging.datamodel.C0887c.m1647a(r8, r9, r10, r11, r13, r14)     // Catch:{ all -> 0x03d2 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x03d2 }
            r2.endTransaction()
            boolean r0 = android.util.Log.isLoggable(r6, r5)
            if (r0 == 0) goto L_0x03be
            java.lang.String r0 = "InsertNewMessageAction: Inserted MMS message "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r2 = r1.getMessageId()
            r0.append(r2)
            java.lang.String r2 = " (timestamp = "
            r0.append(r2)
            r13 = r28
            r0.append(r13)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3620d(r6, r0)
        L_0x03be:
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r15)
            com.android.messaging.datamodel.MessagingContentProvider.m1265Ya()
            r0 = 1
            com.android.messaging.datamodel.C0887c.m1640a((com.android.messaging.datamodel.C0955p) r7, (java.lang.String) r15, (com.android.messaging.datamodel.data.MessageData) r1, (int) r0)
        L_0x03c8:
            com.android.messaging.datamodel.MessagingContentProvider.m1263Wa()
            r0 = 0
            r2 = r30
            com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1420a(r0, r2)
            return r1
        L_0x03d2:
            r0 = move-exception
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.InsertNewMessageAction.mo5956ve():java.lang.Object");
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    private InsertNewMessageAction(int i, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C1424b.fail("InsertNewMessageAction: Can't have empty recipients or message");
        }
        this.f1057Oy.putInt("sub_id", i);
        this.f1057Oy.putString("recipients", str);
        this.f1057Oy.putString("message_text", str2);
        this.f1057Oy.putString("subject_text", str3);
    }

    /* renamed from: a */
    public static void m1377a(int i, String str, String str2, String str3) {
        new InsertNewMessageAction(i, str, str2, str3).start();
    }

    /* synthetic */ InsertNewMessageAction(Parcel parcel, C0829s sVar) {
        super(parcel);
    }
}
