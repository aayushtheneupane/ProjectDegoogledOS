package com.android.messaging.datamodel.action;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import androidx.core.provider.FontsContractCompat;

public class ProcessSentMessageAction extends Action {
    public static final Parcelable.Creator CREATOR = new C0788B();

    private ProcessSentMessageAction() {
    }

    /* renamed from: a */
    public static void m1426a(int i, Uri uri, Bundle bundle) {
        ProcessSentMessageAction processSentMessageAction = new ProcessSentMessageAction();
        Bundle bundle2 = processSentMessageAction.f1057Oy;
        bundle2.putBoolean("is_sms", false);
        bundle2.putBoolean("sent_by_platform", true);
        bundle2.putString("message_id", bundle.getString("message_id"));
        bundle2.putParcelable("message_uri", uri);
        bundle2.putParcelable("updated_message_uri", bundle.getParcelable("updated_message_uri"));
        bundle2.putInt("sub_id", bundle.getInt("sub_id", -1));
        bundle2.putInt(FontsContractCompat.Columns.RESULT_CODE, i);
        bundle2.putInt("http_status_code", bundle.getInt("android.telephony.extra.MMS_HTTP_STATUS", 0));
        bundle2.putParcelable("content_uri", bundle.getParcelable("content_uri"));
        bundle2.putByteArray("response", bundle.getByteArray("android.telephony.extra.MMS_DATA"));
        bundle2.putBoolean("response_important", bundle.getBoolean("response_important"));
        processSentMessageAction.start();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x014b  */
    /* renamed from: ve */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo5956ve() {
        /*
            r19 = this;
            r5 = r19
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            android.os.Bundle r1 = r5.f1057Oy
            java.lang.String r2 = "message_id"
            java.lang.String r1 = r1.getString(r2)
            android.os.Bundle r2 = r5.f1057Oy
            java.lang.String r3 = "message_uri"
            android.os.Parcelable r2 = r2.getParcelable(r3)
            android.net.Uri r2 = (android.net.Uri) r2
            android.os.Bundle r3 = r5.f1057Oy
            java.lang.String r4 = "updated_message_uri"
            android.os.Parcelable r3 = r3.getParcelable(r4)
            android.net.Uri r3 = (android.net.Uri) r3
            android.os.Bundle r4 = r5.f1057Oy
            java.lang.String r6 = "is_sms"
            boolean r4 = r4.getBoolean(r6)
            android.os.Bundle r6 = r5.f1057Oy
            java.lang.String r7 = "sent_by_platform"
            boolean r6 = r6.getBoolean(r7)
            android.os.Bundle r7 = r5.f1057Oy
            r8 = 2
            java.lang.String r9 = "status"
            int r7 = r7.getInt(r9, r8)
            android.os.Bundle r9 = r5.f1057Oy
            r10 = 0
            java.lang.String r11 = "raw_status"
            int r9 = r9.getInt(r11, r10)
            android.os.Bundle r11 = r5.f1057Oy
            r12 = -1
            java.lang.String r13 = "sub_id"
            int r11 = r11.getInt(r13, r12)
            java.lang.String r14 = "http_status_code"
            java.lang.String r15 = "result_code"
            java.lang.String r10 = "MessagingAppDataModel"
            if (r6 == 0) goto L_0x0134
            android.os.Bundle r6 = r5.f1057Oy
            java.lang.String r13 = "content_uri"
            android.os.Parcelable r6 = r6.getParcelable(r13)
            android.net.Uri r6 = (android.net.Uri) r6
            if (r6 == 0) goto L_0x0069
            r13 = 1
            r16 = r13
            goto L_0x006b
        L_0x0069:
            r16 = 0
        L_0x006b:
            com.android.messaging.util.C1424b.m3592ia(r16)
            java.io.File r13 = com.android.messaging.datamodel.MmsFileProvider.m1278e(r6)
            r17 = 0
            boolean r16 = r13.exists()
            if (r16 == 0) goto L_0x009b
            long r17 = r13.length()
            r13.delete()
            boolean r13 = android.util.Log.isLoggable(r10, r8)
            if (r13 == 0) goto L_0x009b
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r8 = "ProcessSentMessageAction: Deleted temp file with outgoing MMS pdu: "
            r13.append(r8)
            r13.append(r6)
            java.lang.String r6 = r13.toString()
            com.android.messaging.util.C1430e.m3628v(r10, r6)
        L_0x009b:
            android.os.Bundle r6 = r5.f1057Oy
            int r6 = r6.getInt(r15)
            android.os.Bundle r8 = r5.f1057Oy
            java.lang.String r13 = "response_important"
            boolean r8 = r8.getBoolean(r13)
            if (r6 != r12) goto L_0x00eb
            if (r8 == 0) goto L_0x0134
            android.os.Bundle r6 = r5.f1057Oy
            java.lang.String r8 = "response"
            byte[] r6 = r6.getByteArray(r8)
            if (r6 == 0) goto L_0x00df
            com.android.messaging.mmslib.a.r r8 = new com.android.messaging.mmslib.a.r
            com.android.messaging.sms.t r12 = com.android.messaging.sms.C1024t.get(r11)
            boolean r12 = r12.mo6854zi()
            r8.<init>(r6, r12)
            com.android.messaging.mmslib.a.f r6 = r8.parse()
            java.lang.String r8 = "MessagingApp"
            if (r6 == 0) goto L_0x00da
            boolean r12 = r6 instanceof com.android.messaging.mmslib.p039a.C0994x
            if (r12 == 0) goto L_0x00d4
            r13 = r6
            com.android.messaging.mmslib.a.x r13 = (com.android.messaging.mmslib.p039a.C0994x) r13
            goto L_0x00e0
        L_0x00d4:
            java.lang.String r6 = "MmsSender: send response not SendConf"
            com.android.messaging.util.C1430e.m3622e(r8, r6)
            goto L_0x00df
        L_0x00da:
            java.lang.String r6 = "MmsSender: send invalid response"
            com.android.messaging.util.C1430e.m3622e(r8, r6)
        L_0x00df:
            r13 = 0
        L_0x00e0:
            if (r13 == 0) goto L_0x0134
            com.android.messaging.sms.x r0 = com.android.messaging.sms.C1029y.m2430a((android.content.Context) r0, (android.net.Uri) r2, (com.android.messaging.mmslib.p039a.C0994x) r13)
            int r7 = r0.status
            int r9 = r0.rawStatus
            goto L_0x0134
        L_0x00eb:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "ProcessSentMessageAction: Platform returned error resultCode: "
            r0.append(r2)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.os.Bundle r2 = r5.f1057Oy
            int r2 = r2.getInt(r14)
            if (r2 == 0) goto L_0x0118
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r0)
            java.lang.String r0 = ", HTTP status code: "
            r7.append(r0)
            r7.append(r2)
            java.lang.String r0 = r7.toString()
        L_0x0118:
            com.android.messaging.util.C1430e.m3630w(r10, r0)
            int r0 = com.android.messaging.sms.C1025u.m2398w(r6, r2)
            r2 = 5
            if (r6 != r2) goto L_0x0132
            com.android.messaging.sms.t r2 = com.android.messaging.sms.C1024t.get(r11)
            int r2 = r2.getMaxMessageSize()
            long r6 = (long) r2
            int r2 = (r17 > r6 ? 1 : (r17 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0132
            r2 = 10000(0x2710, float:1.4013E-41)
            r9 = r2
        L_0x0132:
            r2 = r0
            goto L_0x0135
        L_0x0134:
            r2 = r7
        L_0x0135:
            if (r1 == 0) goto L_0x014b
            android.os.Bundle r0 = r5.f1057Oy
            r0.getInt(r15)
            android.os.Bundle r0 = r5.f1057Oy
            r0.getInt(r14)
            r0 = r1
            r1 = r3
            r3 = r9
            r5 = r19
            r6 = r11
            m1427a(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x0157
        L_0x014b:
            r0 = 2
            boolean r0 = android.util.Log.isLoggable(r10, r0)
            if (r0 == 0) goto L_0x0157
            java.lang.String r0 = "ProcessSentMessageAction: No sent message to process (it was probably a notify response for an MMS download)"
            com.android.messaging.util.C1430e.m3628v(r10, r0)
        L_0x0157:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ProcessSentMessageAction.mo5956ve():java.lang.Object");
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ ProcessSentMessageAction(Parcel parcel, C0788B b) {
        super(parcel);
    }

    /* renamed from: a */
    public static void m1428a(String str, Uri uri, Uri uri2, int i, boolean z, int i2, int i3, int i4) {
        ProcessSentMessageAction processSentMessageAction = new ProcessSentMessageAction();
        Bundle bundle = processSentMessageAction.f1057Oy;
        bundle.putBoolean("is_sms", z);
        bundle.putBoolean("sent_by_platform", false);
        bundle.putString("message_id", str);
        bundle.putParcelable("message_uri", uri);
        bundle.putParcelable("updated_message_uri", uri2);
        bundle.putInt("sub_id", i);
        bundle.putInt(NotificationCompat.CATEGORY_STATUS, i2);
        bundle.putInt("raw_status", i3);
        bundle.putInt(FontsContractCompat.Columns.RESULT_CODE, i4);
        processSentMessageAction.start();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0119  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void m1427a(java.lang.String r17, android.net.Uri r18, int r19, int r20, boolean r21, com.android.messaging.datamodel.action.Action r22, int r23) {
        /*
            r0 = r17
            r1 = r18
            r2 = r20
            r6 = r21
            r7 = r22
            com.android.messaging.datamodel.h r3 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r3 = r3.getDatabase()
            com.android.messaging.datamodel.data.MessageData r4 = com.android.messaging.datamodel.C0887c.m1671h(r3, r0)
            java.lang.String r8 = "MessagingAppDataModel"
            r9 = 1
            if (r4 != 0) goto L_0x0038
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "ProcessSentMessageAction: Sent message "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = " missing from local database"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.android.messaging.util.C1430e.m3630w(r8, r0)
            com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1420a(r9, r7)
            return
        L_0x0038:
            java.lang.String r10 = r4.mo6250Ue()
            r0 = 2
            r5 = 0
            if (r1 == 0) goto L_0x007d
            java.lang.String r11 = r4.mo6283pg()
            java.lang.String r12 = r4.mo6275kf()
            com.android.messaging.datamodel.data.MessageData r11 = com.android.messaging.sms.C1029y.m2423a((android.net.Uri) r1, (java.lang.String) r10, (java.lang.String) r11, (java.lang.String) r12)
            if (r11 == 0) goto L_0x0074
            java.lang.String r12 = r4.getMessageId()
            r11.mo6265ea(r12)
            r11.mo6255Yg()
            java.lang.Iterable r4 = r4.getParts()
            java.util.Iterator r4 = r4.iterator()
        L_0x0060:
            boolean r12 = r4.hasNext()
            if (r12 == 0) goto L_0x0070
            java.lang.Object r12 = r4.next()
            com.android.messaging.datamodel.data.MessagePartData r12 = (com.android.messaging.datamodel.data.MessagePartData) r12
            r12.mo6295ah()
            goto L_0x0060
        L_0x0070:
            r12 = r11
            r11 = r19
            goto L_0x0080
        L_0x0074:
            java.lang.String r1 = "ProcessSentMessageAction: Unable to read sending message"
            com.android.messaging.util.C1430e.m3622e(r8, r1)
            r11 = r0
            r12 = r4
            r1 = r5
            goto L_0x0080
        L_0x007d:
            r11 = r19
            r12 = r4
        L_0x0080:
            long r13 = java.lang.System.currentTimeMillis()
            r15 = 0
            if (r11 != 0) goto L_0x008b
            r12.mo6289s(r13)
            goto L_0x0099
        L_0x008b:
            if (r11 != r9) goto L_0x009b
            boolean r4 = r12.mo6278m(r13)
            if (r4 == 0) goto L_0x009b
            r12.mo6282p(r13)
            r12.mo6287ra(r2)
        L_0x0099:
            r2 = r15
            goto L_0x00a5
        L_0x009b:
            r12.mo6280n(r13)
            r12.mo6287ra(r2)
            r12.mo6243M(r15)
            r2 = r9
        L_0x00a5:
            if (r11 == 0) goto L_0x00c8
            java.util.ArrayList r3 = com.android.messaging.datamodel.C0887c.m1665d(r3, r10)
            java.util.Iterator r3 = r3.iterator()
        L_0x00af:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x00c8
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r16 = android.telephony.PhoneNumberUtils.isEmergencyNumber(r4)
            if (r16 == 0) goto L_0x00af
            com.android.messaging.datamodel.C0944e.m2096f(r4, r10)
            r12.mo6281o(r13)
            r2 = r9
        L_0x00c8:
            boolean r1 = com.android.messaging.datamodel.action.SendMessageAction.m1440a(r6, r12, r1, r2)
            if (r1 == 0) goto L_0x00df
            if (r2 == 0) goto L_0x00d3
            com.android.messaging.datamodel.C0944e.m2090a((boolean) r15, (java.lang.String) r5, (int) r0)
        L_0x00d3:
            r1 = r2 ^ 1
            r5 = 1
            r0 = r10
            r2 = r11
            r3 = r21
            r4 = r23
            com.android.messaging.datamodel.action.C0819i.m1494a(r0, r1, r2, r3, r4, r5)
        L_0x00df:
            java.lang.String r0 = "ProcessSentMessageAction: Done sending "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            if (r6 == 0) goto L_0x00ea
            java.lang.String r1 = "SMS"
            goto L_0x00ec
        L_0x00ea:
            java.lang.String r1 = "MMS"
        L_0x00ec:
            r0.append(r1)
            java.lang.String r1 = " message "
            r0.append(r1)
            java.lang.String r1 = r12.getMessageId()
            r0.append(r1)
            java.lang.String r1 = " in conversation "
            r0.append(r1)
            r0.append(r10)
            java.lang.String r1 = "; status is "
            r0.append(r1)
            java.lang.String r1 = com.android.messaging.sms.C1029y.m2401Ca(r11)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3625i(r8, r0)
            if (r11 == 0) goto L_0x0119
            goto L_0x011a
        L_0x0119:
            r9 = r15
        L_0x011a:
            com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1420a(r9, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ProcessSentMessageAction.m1427a(java.lang.String, android.net.Uri, int, int, boolean, com.android.messaging.datamodel.action.Action, int):void");
    }
}
