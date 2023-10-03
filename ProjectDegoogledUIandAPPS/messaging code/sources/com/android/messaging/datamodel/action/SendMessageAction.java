package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.sms.C1003B;
import com.android.messaging.sms.C1004C;
import com.android.messaging.sms.C1028x;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import java.util.ArrayList;
import p026b.p027a.p030b.p031a.C0632a;

public class SendMessageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0796J();

    private SendMessageAction() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c4 A[SYNTHETIC, Splitter:B:33:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cf A[Catch:{ all -> 0x0152 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0116 A[Catch:{ all -> 0x0152 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x014e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m1440a(boolean r12, com.android.messaging.datamodel.data.MessageData r13, android.net.Uri r14, boolean r15) {
        /*
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            com.android.messaging.datamodel.h r1 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r1 = r1.getDatabase()
            int r2 = r13.getStatus()
            r3 = 5
            r4 = 0
            r5 = 2
            switch(r2) {
                case 1: goto L_0x001c;
                case 2: goto L_0x001c;
                case 3: goto L_0x001a;
                case 4: goto L_0x001c;
                case 5: goto L_0x001c;
                case 6: goto L_0x001c;
                case 7: goto L_0x001c;
                case 8: goto L_0x001d;
                case 9: goto L_0x001d;
                default: goto L_0x001a;
            }
        L_0x001a:
            r3 = r4
            goto L_0x001d
        L_0x001c:
            r3 = r5
        L_0x001d:
            if (r12 == 0) goto L_0x0037
            if (r3 == 0) goto L_0x0056
            android.net.Uri r2 = r13.mo6253Wg()
            long r6 = r13.mo6288rg()
            boolean r0 = com.android.messaging.sms.C1029y.m2440b(r0, r2, r3, r6)
            if (r0 != 0) goto L_0x0056
            long r2 = r13.mo6242Cg()
            r13.mo6280n(r2)
            goto L_0x0054
        L_0x0037:
            android.net.Uri r2 = r13.mo6253Wg()
            if (r2 == 0) goto L_0x0056
            if (r3 == 0) goto L_0x0056
            android.net.Uri r2 = r13.mo6253Wg()
            long r6 = r13.mo6288rg()
            boolean r0 = com.android.messaging.sms.C1029y.m2436a((android.content.Context) r0, (android.net.Uri) r2, (int) r3, (long) r6)
            if (r0 != 0) goto L_0x0056
            long r2 = r13.mo6242Cg()
            r13.mo6280n(r2)
        L_0x0054:
            r0 = r4
            goto L_0x0057
        L_0x0056:
            r0 = 1
        L_0x0057:
            java.lang.String r2 = " in telephony ("
            java.lang.String r3 = "SendMessageAction: Updated "
            java.lang.String r6 = " message "
            java.lang.String r7 = "SMS"
            java.lang.String r8 = "MMS"
            java.lang.String r9 = "MessagingAppDataModel"
            if (r0 == 0) goto L_0x0098
            boolean r10 = android.util.Log.isLoggable(r9, r5)
            if (r10 == 0) goto L_0x00bf
            java.lang.StringBuilder r10 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r3)
            if (r12 == 0) goto L_0x0073
            r11 = r7
            goto L_0x0074
        L_0x0073:
            r11 = r8
        L_0x0074:
            r10.append(r11)
            r10.append(r6)
            java.lang.String r11 = r13.getMessageId()
            r10.append(r11)
            r10.append(r2)
            android.net.Uri r2 = r13.mo6253Wg()
            r10.append(r2)
            java.lang.String r2 = ")"
            r10.append(r2)
            java.lang.String r2 = r10.toString()
            com.android.messaging.util.C1430e.m3628v(r9, r2)
            goto L_0x00bf
        L_0x0098:
            java.lang.String r10 = "SendMessageAction: Failed to update "
            java.lang.StringBuilder r10 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r10)
            if (r12 == 0) goto L_0x00a2
            r11 = r7
            goto L_0x00a3
        L_0x00a2:
            r11 = r8
        L_0x00a3:
            r10.append(r11)
            r10.append(r6)
            java.lang.String r11 = r13.getMessageId()
            r10.append(r11)
            r10.append(r2)
            android.net.Uri r2 = r13.mo6253Wg()
            r10.append(r2)
            java.lang.String r2 = "); marking message failed"
            p026b.p027a.p030b.p031a.C0632a.m1021a((java.lang.StringBuilder) r10, (java.lang.String) r2, (java.lang.String) r9)
        L_0x00bf:
            r1.beginTransaction()
            if (r14 == 0) goto L_0x00cf
            com.android.messaging.datamodel.C0887c.m1659b((com.android.messaging.datamodel.C0955p) r1, (com.android.messaging.datamodel.data.MessageData) r13)     // Catch:{ all -> 0x0152 }
            java.lang.String r15 = r13.mo6250Ue()     // Catch:{ all -> 0x0152 }
            com.android.messaging.datamodel.C0887c.m1651a((com.android.messaging.datamodel.C0955p) r1, (java.lang.String) r15, (boolean) r4, (boolean) r4)     // Catch:{ all -> 0x0152 }
            goto L_0x010d
        L_0x00cf:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ all -> 0x0152 }
            r2.<init>()     // Catch:{ all -> 0x0152 }
            java.lang.String r10 = "message_status"
            int r11 = r13.getStatus()     // Catch:{ all -> 0x0152 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0152 }
            r2.put(r10, r11)     // Catch:{ all -> 0x0152 }
            if (r15 == 0) goto L_0x00ec
            java.lang.String r15 = "seen"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0152 }
            r2.put(r15, r4)     // Catch:{ all -> 0x0152 }
        L_0x00ec:
            java.lang.String r15 = "received_timestamp"
            long r10 = r13.mo6288rg()     // Catch:{ all -> 0x0152 }
            java.lang.Long r4 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0152 }
            r2.put(r15, r4)     // Catch:{ all -> 0x0152 }
            java.lang.String r15 = "raw_status"
            int r4 = r13.mo6285qg()     // Catch:{ all -> 0x0152 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0152 }
            r2.put(r15, r4)     // Catch:{ all -> 0x0152 }
            java.lang.String r15 = r13.getMessageId()     // Catch:{ all -> 0x0152 }
            com.android.messaging.datamodel.C0887c.m1666d(r1, r15, r2)     // Catch:{ all -> 0x0152 }
        L_0x010d:
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0152 }
            boolean r15 = android.util.Log.isLoggable(r9, r5)     // Catch:{ all -> 0x0152 }
            if (r15 == 0) goto L_0x0142
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x0152 }
            r15.<init>()     // Catch:{ all -> 0x0152 }
            r15.append(r3)     // Catch:{ all -> 0x0152 }
            if (r12 == 0) goto L_0x0121
            goto L_0x0122
        L_0x0121:
            r7 = r8
        L_0x0122:
            r15.append(r7)     // Catch:{ all -> 0x0152 }
            r15.append(r6)     // Catch:{ all -> 0x0152 }
            java.lang.String r12 = r13.getMessageId()     // Catch:{ all -> 0x0152 }
            r15.append(r12)     // Catch:{ all -> 0x0152 }
            java.lang.String r12 = " in local db. Timestamp = "
            r15.append(r12)     // Catch:{ all -> 0x0152 }
            long r2 = r13.mo6288rg()     // Catch:{ all -> 0x0152 }
            r15.append(r2)     // Catch:{ all -> 0x0152 }
            java.lang.String r12 = r15.toString()     // Catch:{ all -> 0x0152 }
            com.android.messaging.util.C1430e.m3628v(r9, r12)     // Catch:{ all -> 0x0152 }
        L_0x0142:
            r1.endTransaction()
            java.lang.String r12 = r13.mo6250Ue()
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r12)
            if (r14 == 0) goto L_0x0151
            com.android.messaging.datamodel.MessagingContentProvider.m1265Ya()
        L_0x0151:
            return r0
        L_0x0152:
            r12 = move-exception
            r1.endTransaction()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.SendMessageAction.m1440a(boolean, com.android.messaging.datamodel.data.MessageData, android.net.Uri, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0119  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean m1441c(java.lang.String r20, com.android.messaging.datamodel.action.Action r21) {
        /*
            r0 = r20
            r1 = r21
            com.android.messaging.datamodel.action.SendMessageAction r2 = new com.android.messaging.datamodel.action.SendMessageAction
            r2.<init>()
            java.lang.String r3 = "sms_service_center"
            android.os.Bundle r4 = r2.f1057Oy
            java.lang.String r5 = "message_id"
            r4.putString(r5, r0)
            long r4 = java.lang.System.currentTimeMillis()
            com.android.messaging.datamodel.h r6 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r7 = r6.getDatabase()
            com.android.messaging.datamodel.data.MessageData r6 = com.android.messaging.datamodel.C0887c.m1671h(r7, r0)
            r15 = 0
            if (r6 == 0) goto L_0x0141
            boolean r8 = r6.mo6247Rg()
            if (r8 == 0) goto L_0x0141
            int r8 = r6.getProtocol()
            r14 = 1
            if (r8 != 0) goto L_0x0034
            r8 = r14
            goto L_0x0035
        L_0x0034:
            r8 = r15
        L_0x0035:
            java.lang.String r9 = r6.mo6275kf()
            com.android.messaging.datamodel.data.ParticipantData r9 = com.android.messaging.datamodel.C0887c.m1662c(r7, r9)
            android.net.Uri r10 = r6.mo6253Wg()
            java.lang.String r11 = r6.mo6250Ue()
            boolean r12 = r6.mo6254Xg()
            if (r12 == 0) goto L_0x004f
            r6.mo6286r(r4)
            goto L_0x0052
        L_0x004f:
            r6.mo6284q(r4)
        L_0x0052:
            r4 = 0
            boolean r5 = m1440a(r8, r6, r4, r15)
            if (r5 != 0) goto L_0x005b
            goto L_0x0143
        L_0x005b:
            java.util.ArrayList r5 = com.android.messaging.datamodel.C0887c.m1665d(r7, r11)
            android.os.Bundle r12 = r2.f1057Oy
            java.lang.String r13 = "message_uri"
            r12.putParcelable(r13, r10)
            android.os.Bundle r10 = r2.f1057Oy
            java.lang.String r12 = "message"
            r10.putParcelable(r12, r6)
            android.os.Bundle r6 = r2.f1057Oy
            java.lang.String r10 = "recipients"
            r6.putStringArrayList(r10, r5)
            android.os.Bundle r6 = r2.f1057Oy
            int r10 = r9.getSubId()
            java.lang.String r12 = "sub_id"
            r6.putInt(r12, r10)
            android.os.Bundle r6 = r2.f1057Oy
            java.lang.String r9 = r9.mo6353sf()
            java.lang.String r10 = "sub_phone_number"
            r6.putString(r10, r9)
            java.lang.String r6 = " for sending"
            java.lang.String r13 = "MessagingAppDataModel"
            r12 = 3
            if (r8 == 0) goto L_0x011d
            com.android.messaging.util.C1424b.m3584Gj()
            java.lang.String r8 = "conversations"
            java.lang.String[] r9 = new java.lang.String[]{r3}     // Catch:{ all -> 0x0114 }
            java.lang.String r10 = "_id=?"
            java.lang.String[] r4 = new java.lang.String[r14]     // Catch:{ all -> 0x0114 }
            r4[r15] = r11     // Catch:{ all -> 0x0114 }
            r17 = 0
            r18 = 0
            r19 = 0
            r11 = r4
            r4 = r12
            r12 = r17
            r4 = r13
            r13 = r18
            r15 = r14
            r14 = r19
            android.database.Cursor r7 = r7.query(r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0114 }
            int r8 = r7.getCount()     // Catch:{ all -> 0x0110 }
            r9 = 0
            com.android.messaging.util.C1424b.m3588b(r8, r9, r15)     // Catch:{ all -> 0x0110 }
            boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x0110 }
            if (r8 == 0) goto L_0x00ca
            java.lang.String r8 = r7.getString(r9)     // Catch:{ all -> 0x0110 }
            r7.close()
            goto L_0x00ce
        L_0x00ca:
            r7.close()
            r8 = 0
        L_0x00ce:
            android.os.Bundle r7 = r2.f1057Oy
            r7.putString(r3, r8)
            int r3 = r5.size()
            if (r3 != r15) goto L_0x0109
            r3 = 0
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            android.os.Bundle r5 = r2.f1057Oy
            java.lang.String r7 = "recipient"
            r5.putString(r7, r3)
            r1.mo5949b(r2)
            r1 = 3
            boolean r1 = android.util.Log.isLoggable(r4, r1)
            if (r1 == 0) goto L_0x0143
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SendMessageAction: Queued SMS message "
            r1.append(r2)
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            com.android.messaging.util.C1430e.m3620d(r4, r0)
            goto L_0x0143
        L_0x0109:
            r3 = 0
            java.lang.String r0 = "Trying to resend a broadcast SMS - not allowed"
            com.android.messaging.util.C1430e.wtf(r4, r0)
            goto L_0x0142
        L_0x0110:
            r0 = move-exception
            r16 = r7
            goto L_0x0117
        L_0x0114:
            r0 = move-exception
            r16 = 0
        L_0x0117:
            if (r16 == 0) goto L_0x011c
            r16.close()
        L_0x011c:
            throw r0
        L_0x011d:
            r4 = r13
            r15 = r14
            r1.mo5949b(r2)
            r1 = 3
            boolean r1 = android.util.Log.isLoggable(r4, r1)
            if (r1 == 0) goto L_0x0143
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SendMessageAction: Queued MMS message "
            r1.append(r2)
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            com.android.messaging.util.C1430e.m3620d(r4, r0)
            goto L_0x0143
        L_0x0141:
            r3 = r15
        L_0x0142:
            r15 = r3
        L_0x0143:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.SendMessageAction.m1441c(java.lang.String, com.android.messaging.datamodel.action.Action):boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ce */
    public Object mo5942Ce() {
        String string = this.f1057Oy.getString("message_id");
        boolean z = ((MessageData) this.f1057Oy.getParcelable("message")).getProtocol() == 0;
        int i = this.f1057Oy.getInt("sub_id", -1);
        this.f1057Oy.getInt(FontsContractCompat.Columns.RESULT_CODE);
        this.f1057Oy.getInt("http_status_code");
        ProcessSentMessageAction.m1427a(string, (Uri) null, 2, 0, z, this, i);
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public Object mo5951i(Bundle bundle) {
        return null;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: ue */
    public Bundle mo5955ue() {
        int i;
        int i2;
        int i3;
        Uri uri;
        Uri uri2;
        Uri uri3;
        int i4;
        Uri uri4;
        boolean z;
        MessageData messageData = (MessageData) this.f1057Oy.getParcelable("message");
        String string = this.f1057Oy.getString("message_id");
        Uri uri5 = (Uri) this.f1057Oy.getParcelable("message_uri");
        int i5 = 1;
        boolean z2 = messageData.getProtocol() == 0;
        int i6 = this.f1057Oy.getInt("sub_id", -1);
        String string2 = this.f1057Oy.getString("sub_phone_number");
        StringBuilder Pa = C0632a.m1011Pa("SendMessageAction: Sending ");
        Pa.append(z2 ? "SMS" : "MMS");
        Pa.append(" message ");
        Pa.append(string);
        Pa.append(" in conversation ");
        Pa.append(messageData.mo6250Ue());
        C1430e.m3625i("MessagingAppDataModel", Pa.toString());
        if (z2) {
            C1424b.m3594t(uri5);
            String string3 = this.f1057Oy.getString("recipient");
            String hf = messageData.mo6274hf();
            String string4 = this.f1057Oy.getString("sms_service_center");
            boolean Fa = C1029y.m2404Fa(i6);
            if (C1464na.m3759Zj()) {
                z = true;
            } else {
                z = !C1474sa.get(i6).mo8227jk();
            }
            if (!z) {
                C1430e.m3630w("MessagingApp", "MmsUtils: can't send SMS without radio");
            } else {
                try {
                    C1003B a = C1004C.m2331a(C0967f.get().getApplicationContext(), i6, string3, hf, string4, Fa, uri5);
                    if (!a.mo6784Oi()) {
                        int Ni = a.mo6783Ni();
                        if (Ni == 0) {
                            i5 = 0;
                        } else if (Ni == 1) {
                            try {
                                C1430e.m3622e("MessagingApp", "MmsUtils: SMS temporary failure");
                            } catch (Exception e) {
                                e = e;
                            }
                        } else if (Ni == 2) {
                            C1430e.m3622e("MessagingApp", "MmsUtils: SMS permanent failure");
                        }
                        uri2 = uri5;
                        i3 = i6;
                        i2 = 0;
                        i = 0;
                        uri = null;
                    } else {
                        C1430e.m3622e("MessagingApp", "MmsUtils: sending SMS timed out");
                    }
                } catch (Exception e2) {
                    e = e2;
                    i5 = 2;
                    C0632a.m1020a("MmsUtils: failed to send SMS ", (Object) e, "MessagingApp", (Throwable) e);
                    uri2 = uri5;
                    i3 = i6;
                    i2 = 0;
                    i = 0;
                    uri = null;
                    ProcessSentMessageAction.m1428a(string, uri2, uri, i3, z2, i5, i2, i);
                    return null;
                }
            }
            i5 = 2;
            uri2 = uri5;
            i3 = i6;
            i2 = 0;
            i = 0;
            uri = null;
        } else {
            Context applicationContext = C0967f.get().getApplicationContext();
            ArrayList<String> stringArrayList = this.f1057Oy.getStringArrayList("recipients");
            if (uri5 == null) {
                long rg = messageData.mo6288rg();
                C0947h.get().mo6606be().mo5917h(rg);
                Uri uri6 = uri5;
                i3 = i6;
                i4 = 2;
                uri4 = C1029y.m2421a(applicationContext, stringArrayList, messageData, i6, string2, rg);
                if (uri4 != null) {
                    C0955p database = C0947h.get().getDatabase();
                    database.beginTransaction();
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("sms_message_uri", uri4.toString());
                        C0887c.m1663c(database, string, contentValues);
                        database.setTransactionSuccessful();
                        database.endTransaction();
                        if (Log.isLoggable("MessagingAppDataModel", 2)) {
                            C1430e.m3628v("MessagingAppDataModel", "SendMessageAction: Updated message " + string + " with new uri " + uri4);
                        }
                        uri3 = uri4;
                    } catch (Throwable th) {
                        database.endTransaction();
                        throw th;
                    }
                } else {
                    uri3 = uri4;
                    uri4 = uri6;
                }
            } else {
                Uri uri7 = uri5;
                i3 = i6;
                i4 = 2;
                uri4 = uri7;
                uri3 = null;
            }
            if (uri4 != null) {
                Bundle bundle = new Bundle();
                bundle.putString("message_id", string);
                bundle.putParcelable("updated_message_uri", uri3);
                C1028x a2 = C1029y.m2427a(applicationContext, i3, uri4, bundle);
                if (a2 == C1029y.STATUS_PENDING) {
                    C1430e.m3620d("MessagingAppDataModel", "SendMessageAction: Sending MMS message " + string + " asynchronously; waiting for callback to finish processing");
                    return null;
                }
                i5 = a2.status;
                int i7 = a2.rawStatus;
                uri2 = uri4;
                i = a2.resultCode;
                uri = uri3;
                i2 = i7;
            } else {
                uri2 = uri4;
                i5 = i4;
                uri = uri3;
                i2 = 0;
                i = 0;
            }
        }
        ProcessSentMessageAction.m1428a(string, uri2, uri, i3, z2, i5, i2, i);
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C1424b.fail("SendMessageAction must be queued rather than started");
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ SendMessageAction(Parcel parcel, C0796J j) {
        super(parcel);
    }
}
