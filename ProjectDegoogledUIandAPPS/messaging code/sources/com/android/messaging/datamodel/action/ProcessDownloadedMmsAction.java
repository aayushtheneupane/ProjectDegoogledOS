package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.provider.FontsContractCompat;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.ParticipantData;
import com.android.messaging.sms.C1029y;
import com.android.messaging.sms.DatabaseMessages$MmsMessage;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

public class ProcessDownloadedMmsAction extends Action {
    public static final Parcelable.Creator CREATOR = new C0834x();

    private ProcessDownloadedMmsAction() {
    }

    /* renamed from: a */
    public static void m1408a(int i, Bundle bundle) {
        String string = bundle.getString("message_id");
        Uri uri = (Uri) bundle.getParcelable("content_uri");
        Uri uri2 = (Uri) bundle.getParcelable("notification_uri");
        String string2 = bundle.getString("conversation_id");
        String string3 = bundle.getString("participant_id");
        C1424b.m3594t(string);
        C1424b.m3594t(uri);
        C1424b.m3594t(uri2);
        C1424b.m3594t(string2);
        C1424b.m3594t(string3);
        ProcessDownloadedMmsAction processDownloadedMmsAction = new ProcessDownloadedMmsAction();
        Bundle bundle2 = processDownloadedMmsAction.f1057Oy;
        bundle2.putBoolean("downloaded_by_platform", true);
        bundle2.putString("message_id", string);
        bundle2.putInt(FontsContractCompat.Columns.RESULT_CODE, i);
        bundle2.putInt("http_status_code", bundle.getInt("android.telephony.extra.MMS_HTTP_STATUS", 0));
        bundle2.putParcelable("content_uri", uri);
        bundle2.putParcelable("notification_uri", uri2);
        bundle2.putInt("sub_id", bundle.getInt("sub_id", -1));
        bundle2.putString("sub_phone_number", bundle.getString("sub_phone_number"));
        bundle2.putString("transaction_id", bundle.getString("transaction_id"));
        bundle2.putString("content_location", bundle.getString("content_location"));
        bundle2.putBoolean("auto_download", bundle.getBoolean("auto_download"));
        bundle2.putLong("received_timestamp", bundle.getLong("received_timestamp"));
        bundle2.putString("conversation_id", string2);
        bundle2.putString("participant_id", string3);
        bundle2.putInt("status_if_failed", bundle.getInt("status_if_failed"));
        bundle2.putLong("expiry", bundle.getLong("expiry"));
        processDownloadedMmsAction.start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ce */
    public Object mo5942Ce() {
        if (this.f1057Oy.getBoolean("send_deferred_resp_status")) {
            C1430e.m3630w("MessagingAppDataModel", "ProcessDownloadedMmsAction: Exception while sending deferred NotifyRespInd");
            return null;
        }
        m1407a(2, 0, (Uri) null);
        ProcessPendingMessagesAction.m1420a(true, this);
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public Object mo5951i(Bundle bundle) {
        String str;
        boolean z = true;
        if (bundle == null) {
            C1424b.m3592ia(this.f1057Oy.getBoolean("send_deferred_resp_status"));
            ProcessPendingMessagesAction.m1420a(true, this);
            return null;
        }
        int i = bundle.getInt("request_status");
        int i2 = bundle.getInt("raw_status");
        Uri uri = (Uri) bundle.getParcelable("mms_uri");
        boolean z2 = this.f1057Oy.getBoolean("auto_download");
        String string = this.f1057Oy.getString("message_id");
        MessageData a = m1407a(i, i2, uri);
        int i3 = this.f1057Oy.getInt("sub_id", -1);
        boolean z3 = z2 && i == 2;
        if (z3) {
            m1411a(string, this.f1057Oy.getString("transaction_id"), this.f1057Oy.getString("content_location"), i3);
        }
        if (z2) {
            C0955p database = C0947h.get().getDatabase();
            MessageData i4 = a == null ? C0887c.m1673i(database, string) : a;
            if (i4 != null) {
                C0819i.m1493a(i4.mo6250Ue(), ParticipantData.m1840l(database, i4.mo6283pg()), i4);
            }
        } else {
            boolean z4 = a != null && i == 0;
            if (z4) {
                str = a.mo6250Ue();
            } else {
                str = this.f1057Oy.getString("conversation_id");
            }
            C0819i.m1494a(str, z4, i, false, i3, false);
        }
        if (uri != null) {
            z = false;
        }
        if (!z3) {
            ProcessPendingMessagesAction.m1420a(z, this);
        }
        if (z) {
            C0944e.m2090a(false, (String) null, 2);
        }
        return a;
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [android.os.Parcelable] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0128  */
    /* renamed from: ue */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle mo5955ue() {
        /*
            r21 = this;
            r1 = r21
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r2 = r0.getApplicationContext()
            android.os.Bundle r0 = r1.f1057Oy
            r3 = -1
            java.lang.String r4 = "sub_id"
            int r4 = r0.getInt(r4, r3)
            android.os.Bundle r0 = r1.f1057Oy
            java.lang.String r5 = "message_id"
            java.lang.String r0 = r0.getString(r5)
            android.os.Bundle r5 = r1.f1057Oy
            java.lang.String r6 = "transaction_id"
            java.lang.String r5 = r5.getString(r6)
            android.os.Bundle r6 = r1.f1057Oy
            java.lang.String r7 = "content_location"
            java.lang.String r6 = r6.getString(r7)
            android.os.Bundle r7 = r1.f1057Oy
            r8 = 0
            java.lang.String r9 = "send_deferred_resp_status"
            boolean r7 = r7.getBoolean(r9, r8)
            r9 = 2
            r10 = 0
            java.lang.String r11 = "MessagingAppDataModel"
            if (r7 == 0) goto L_0x0065
            boolean r1 = android.util.Log.isLoggable(r11, r9)
            if (r1 == 0) goto L_0x0059
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "DownloadMmsAction: Auto-download of message "
            r1.append(r3)
            r1.append(r0)
            java.lang.String r0 = " failed; sending DEFERRED NotifyRespInd"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.android.messaging.util.C1430e.m3628v(r11, r0)
        L_0x0059:
            java.lang.String r0 = "UTF-8"
            byte[] r0 = com.android.messaging.sms.C1029y.m2443h(r5, r0)
            r1 = 131(0x83, float:1.84E-43)
            com.android.messaging.sms.C1029y.m2432a((android.content.Context) r2, (int) r4, (byte[]) r0, (java.lang.String) r6, (int) r1)
            return r10
        L_0x0065:
            android.os.Bundle r0 = r1.f1057Oy
            java.lang.String r7 = "downloaded_by_platform"
            boolean r0 = r0.getBoolean(r7)
            java.lang.String r13 = "mms_uri"
            java.lang.String r14 = "raw_status"
            if (r0 == 0) goto L_0x01a5
            android.os.Bundle r0 = r1.f1057Oy
            java.lang.String r7 = "result_code"
            int r0 = r0.getInt(r7)
            if (r0 != r3) goto L_0x017c
            android.os.Bundle r0 = r1.f1057Oy
            java.lang.String r3 = "content_uri"
            android.os.Parcelable r0 = r0.getParcelable(r3)
            android.net.Uri r0 = (android.net.Uri) r0
            java.io.File r3 = com.android.messaging.datamodel.MmsFileProvider.m1278e(r0)
            byte[] r0 = com.google.common.p043io.C1720i.m4651c(r3)     // Catch:{ FileNotFoundException -> 0x00a7, IOException -> 0x0090 }
            goto L_0x00bc
        L_0x0090:
            r0 = move-exception
            r7 = r0
            java.lang.String r0 = "ProcessDownloadedMmsAction: Error reading MMS download file: "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r12 = r3.getAbsolutePath()
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3623e(r11, r0, r7)
            goto L_0x00bb
        L_0x00a7:
            java.lang.String r0 = "ProcessDownloadedMmsAction: MMS download file not found: "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r7 = r3.getAbsolutePath()
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3622e(r11, r0)
        L_0x00bb:
            r0 = r10
        L_0x00bc:
            boolean r7 = r3.exists()
            if (r7 == 0) goto L_0x00e0
            r3.delete()
            r7 = 3
            boolean r7 = android.util.Log.isLoggable(r11, r7)
            if (r7 == 0) goto L_0x00e0
            java.lang.String r7 = "ProcessDownloadedMmsAction: Deleted temp file with downloaded MMS pdu: "
            java.lang.StringBuilder r7 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r7)
            java.lang.String r3 = r3.getAbsolutePath()
            r7.append(r3)
            java.lang.String r3 = r7.toString()
            com.android.messaging.util.C1430e.m3620d(r11, r3)
        L_0x00e0:
            if (r0 == 0) goto L_0x0177
            java.lang.String r3 = "MessagingApp"
            com.android.messaging.mmslib.a.r r7 = new com.android.messaging.mmslib.a.r
            com.android.messaging.sms.t r11 = com.android.messaging.sms.C1024t.get(r4)
            boolean r11 = r11.mo6854zi()
            r7.<init>(r0, r11)
            com.android.messaging.mmslib.a.f r0 = r7.parse()
            if (r0 == 0) goto L_0x0118
            boolean r7 = r0 instanceof com.android.messaging.mmslib.p039a.C0993w
            if (r7 == 0) goto L_0x00ff
            com.android.messaging.mmslib.a.w r0 = (com.android.messaging.mmslib.p039a.C0993w) r0
            r12 = r0
            goto L_0x0123
        L_0x00ff:
            java.lang.String r7 = "MmsSender: downloaded pdu not RetrieveConf: "
            java.lang.StringBuilder r7 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r7)
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            com.android.messaging.util.C1430e.m3622e(r3, r0)
            goto L_0x011d
        L_0x0118:
            java.lang.String r0 = "MmsSender: downloaded pdu could not be parsed (invalid)"
            com.android.messaging.util.C1430e.m3622e(r3, r0)
        L_0x011d:
            java.lang.String r0 = "MmsSender: downloaded pdu is empty"
            com.android.messaging.util.C1430e.m3622e(r3, r0)
            r12 = r10
        L_0x0123:
            com.android.messaging.sms.C1029y.m2411Ji()
            if (r12 == 0) goto L_0x0177
            android.os.Bundle r0 = r1.f1057Oy
            java.lang.String r3 = "notification_uri"
            android.os.Parcelable r0 = r0.getParcelable(r3)
            android.net.Uri r0 = (android.net.Uri) r0
            android.os.Bundle r3 = r1.f1057Oy
            java.lang.String r7 = "sub_phone_number"
            java.lang.String r7 = r3.getString(r7)
            android.os.Bundle r3 = r1.f1057Oy
            java.lang.String r8 = "auto_download"
            boolean r8 = r3.getBoolean(r8)
            android.os.Bundle r3 = r1.f1057Oy
            java.lang.String r9 = "received_timestamp"
            long r9 = r3.getLong(r9)
            android.os.Bundle r1 = r1.f1057Oy
            java.lang.String r3 = "expiry"
            long r15 = r1.getLong(r3)
            com.android.messaging.datamodel.h r1 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.U r1 = r1.mo6606be()
            r17 = 1000(0x3e8, double:4.94E-321)
            r19 = r13
            r20 = r14
            long r13 = r9 * r17
            r1.mo5917h(r13)
            r1 = r2
            r2 = r0
            r3 = r4
            r4 = r7
            r7 = r8
            r8 = r9
            r10 = r15
            com.android.messaging.sms.x r0 = com.android.messaging.sms.C1029y.m2429a((android.content.Context) r1, (android.net.Uri) r2, (int) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (boolean) r7, (long) r8, (long) r10, (com.android.messaging.mmslib.p039a.C0993w) r12)
            int r9 = r0.status
            int r8 = r0.rawStatus
            android.net.Uri r10 = r0.uri
            goto L_0x01a0
        L_0x0177:
            r19 = r13
            r20 = r14
            goto L_0x01a0
        L_0x017c:
            r19 = r13
            r20 = r14
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "ProcessDownloadedMmsAction: Platform returned error resultCode: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.android.messaging.util.C1430e.m3630w(r11, r2)
            android.os.Bundle r1 = r1.f1057Oy
            java.lang.String r2 = "http_status_code"
            int r1 = r1.getInt(r2)
            int r9 = com.android.messaging.sms.C1025u.m2398w(r0, r1)
        L_0x01a0:
            r1 = r19
            r2 = r20
            goto L_0x01c4
        L_0x01a5:
            r19 = r13
            r20 = r14
            android.os.Bundle r0 = r1.f1057Oy
            java.lang.String r2 = "status"
            int r9 = r0.getInt(r2)
            android.os.Bundle r0 = r1.f1057Oy
            r2 = r20
            int r8 = r0.getInt(r2)
            android.os.Bundle r0 = r1.f1057Oy
            r1 = r19
            android.os.Parcelable r0 = r0.getParcelable(r1)
            r10 = r0
            android.net.Uri r10 = (android.net.Uri) r10
        L_0x01c4:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r3 = "request_status"
            r0.putInt(r3, r9)
            r0.putInt(r2, r8)
            r0.putParcelable(r1, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ProcessDownloadedMmsAction.mo5955ue():android.os.Bundle");
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

    /* synthetic */ ProcessDownloadedMmsAction(Parcel parcel, C0834x xVar) {
        super(parcel);
    }

    /* renamed from: a */
    public static void m1410a(String str, Uri uri, String str2, String str3, String str4, int i, String str5, int i2, boolean z, String str6, int i3) {
        C1424b.m3594t(str);
        C1424b.m3594t(uri);
        C1424b.m3594t(str2);
        C1424b.m3594t(str3);
        ProcessDownloadedMmsAction processDownloadedMmsAction = new ProcessDownloadedMmsAction();
        Bundle bundle = processDownloadedMmsAction.f1057Oy;
        bundle.putBoolean("downloaded_by_platform", true);
        bundle.putString("message_id", str);
        bundle.putInt(FontsContractCompat.Columns.RESULT_CODE, i3);
        bundle.putParcelable("notification_uri", uri);
        bundle.putInt("sub_id", i);
        bundle.putString("sub_phone_number", str5);
        bundle.putString("content_location", str4);
        bundle.putBoolean("auto_download", z);
        bundle.putString("conversation_id", str2);
        bundle.putString("participant_id", str3);
        bundle.putInt("status_if_failed", i2);
        bundle.putString("transaction_id", str6);
        processDownloadedMmsAction.start();
    }

    /* renamed from: a */
    public static void m1409a(String str, int i, int i2, String str2, String str3, int i3, int i4, String str4) {
        C1424b.m3594t(str);
        C1424b.m3594t(str2);
        C1424b.m3594t(str3);
        ProcessDownloadedMmsAction processDownloadedMmsAction = new ProcessDownloadedMmsAction();
        Bundle bundle = processDownloadedMmsAction.f1057Oy;
        bundle.putBoolean("downloaded_by_platform", false);
        bundle.putString("message_id", str);
        bundle.putInt(NotificationCompat.CATEGORY_STATUS, i);
        bundle.putInt("raw_status", i2);
        bundle.putString("conversation_id", str2);
        bundle.putString("participant_id", str3);
        bundle.putInt("status_if_failed", i3);
        bundle.putInt("sub_id", i4);
        bundle.putString("transaction_id", str4);
        processDownloadedMmsAction.start();
    }

    /* renamed from: a */
    public static void m1411a(String str, String str2, String str3, int i) {
        ProcessDownloadedMmsAction processDownloadedMmsAction = new ProcessDownloadedMmsAction();
        Bundle bundle = processDownloadedMmsAction.f1057Oy;
        bundle.putString("message_id", str);
        bundle.putString("transaction_id", str2);
        bundle.putString("content_location", str3);
        bundle.putBoolean("send_deferred_resp_status", true);
        bundle.putInt("sub_id", i);
        processDownloadedMmsAction.start();
    }

    /* renamed from: a */
    private MessageData m1407a(int i, int i2, Uri uri) {
        DatabaseMessages$MmsMessage databaseMessages$MmsMessage;
        String str;
        MessageData messageData;
        boolean z;
        int i3 = i;
        Uri uri2 = uri;
        Context applicationContext = C0967f.get().getApplicationContext();
        String string = this.f1057Oy.getString("message_id");
        Uri uri3 = (Uri) this.f1057Oy.getParcelable("notification_uri");
        String string2 = this.f1057Oy.getString("conversation_id");
        String string3 = this.f1057Oy.getString("participant_id");
        int i4 = this.f1057Oy.getInt("status_if_failed");
        int i5 = this.f1057Oy.getInt("sub_id", -1);
        C1424b.m3594t(string);
        C1430e.m3625i("MessagingAppDataModel", "ProcessDownloadedMmsAction: Processed MMS download of message " + string + "; status is " + C1029y.m2401Ca(i));
        if (i3 != 0 || uri2 == null) {
            databaseMessages$MmsMessage = null;
        } else {
            try {
                applicationContext.getContentResolver().delete(uri3, (String) null, (String[]) null);
            } catch (SQLiteException e) {
                C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when delete", e);
            } catch (IllegalArgumentException e2) {
                C1430e.m3623e("MessagingApp", "SqliteWrapper: catch an exception when delete", e2);
            }
            databaseMessages$MmsMessage = C1029y.m2449o(uri);
        }
        C0955p database = C0947h.get().getDatabase();
        database.beginTransaction();
        if (databaseMessages$MmsMessage != null) {
            try {
                String a = C0887c.m1639a(database, ParticipantData.m1841sa(databaseMessages$MmsMessage.getSubId()));
                String a2 = C1029y.m2431a(C1029y.m2400A(databaseMessages$MmsMessage.mThreadId), databaseMessages$MmsMessage.getUri());
                if (a2 == null) {
                    C1430e.m3630w("MessagingAppDataModel", "Downloaded an MMS without sender address; using unknown sender.");
                    a2 = "ʼUNKNOWN_SENDER!ʼ";
                }
                ParticipantData g = ParticipantData.m1835g(a2, i5);
                String a3 = C0887c.m1639a(database, g);
                if (!a3.equals(string3)) {
                    C1430e.m3622e("MessagingAppDataModel", "ProcessDownloadedMmsAction: Downloaded MMS message " + string + " has different sender (participantId = " + a3 + ") than notification (" + string3 + ")");
                }
                boolean g2 = C0887c.m1670g(database, g.mo6353sf());
                str = C0887c.m1635a(database, databaseMessages$MmsMessage.mThreadId, g2, i5);
                boolean L = C0947h.get().mo6588L(str);
                boolean M = C0947h.get().mo6589M(str);
                databaseMessages$MmsMessage.f1490_b = L;
                databaseMessages$MmsMessage.f1485BA = M;
                MessageData a4 = C1029y.m2424a(databaseMessages$MmsMessage, str, a3, a, 100);
                a4.mo6255Yg();
                C0947h.get().mo6606be().mo5917h(a4.mo6288rg());
                if (C0887c.m1673i(database, string) == null) {
                    C1430e.m3630w("MessagingAppDataModel", "Message deleted prior to update");
                    C0887c.m1643a(database, a4);
                } else {
                    a4.mo6265ea(string);
                    C0887c.m1659b(database, a4);
                }
                if (!TextUtils.equals(string2, str) && !C0887c.m1653a(database, string2)) {
                    C0887c.m1648a(database, string2, string, true, g2);
                }
                C0887c.m1651a(database, str, true, g2);
                messageData = a4;
                z = L;
            } catch (Throwable th) {
                database.endTransaction();
                throw th;
            }
        } else {
            z = C0947h.get().mo6588L(string2);
            if (i3 == 2) {
                i4 = 106;
            } else if (i3 == 3) {
                i4 = 107;
            }
            DownloadMmsAction.m1357a(uri3, string, string2, i4, i2);
            this.f1057Oy.getInt(FontsContractCompat.Columns.RESULT_CODE);
            this.f1057Oy.getInt("http_status_code");
            C0887c.m1651a(database, string2, true, false);
            messageData = null;
            str = null;
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        if (uri2 != null) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("read", Boolean.valueOf(z));
            C0107q.m122a(applicationContext.getContentResolver(), uri2, contentValues, (String) null, (String[]) null);
        }
        C0944e.m2090a(false, str, 3);
        if (str != null) {
            MessagingContentProvider.m1273r(str);
        }
        MessagingContentProvider.m1273r(string2);
        MessagingContentProvider.m1265Ya();
        return messageData;
    }
}
