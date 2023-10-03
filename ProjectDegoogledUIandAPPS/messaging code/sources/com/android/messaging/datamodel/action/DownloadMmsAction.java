package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.sms.C1028x;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

public class DownloadMmsAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0822l();

    private DownloadMmsAction() {
    }

    /* renamed from: b */
    static boolean m1358b(String str, Action action) {
        return new DownloadMmsAction().mo5967a(str, action);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ce */
    public Object mo5942Ce() {
        ProcessDownloadedMmsAction.m1409a(this.f1057Oy.getString("message_id"), 2, 0, this.f1057Oy.getString("conversation_id"), this.f1057Oy.getString("participant_id"), this.f1057Oy.getInt("failure_status"), this.f1057Oy.getInt("sub_id"), this.f1057Oy.getString("transaction_id"));
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo5967a(java.lang.String r17, com.android.messaging.datamodel.action.Action r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            android.os.Bundle r2 = r0.f1057Oy
            java.lang.String r3 = "message_id"
            r2.putString(r3, r1)
            com.android.messaging.datamodel.h r2 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r2 = r2.getDatabase()
            com.android.messaging.datamodel.data.MessageData r3 = com.android.messaging.datamodel.C0887c.m1671h(r2, r1)
            r4 = 0
            if (r3 == 0) goto L_0x0146
            boolean r5 = r3.mo6244Og()
            if (r5 == 0) goto L_0x0146
            android.net.Uri r5 = r3.mo6253Wg()
            java.lang.String r6 = r3.mo6250Ue()
            int r7 = r3.getStatus()
            java.lang.String r8 = r3.mo6275kf()
            com.android.messaging.datamodel.data.ParticipantData r2 = com.android.messaging.datamodel.C0887c.m1662c(r2, r8)
            int r8 = r2.getSubId()
            android.os.Bundle r9 = r0.f1057Oy
            java.lang.String r10 = "sub_id"
            r9.putInt(r10, r8)
            android.os.Bundle r9 = r0.f1057Oy
            java.lang.String r10 = "conversation_id"
            r9.putString(r10, r6)
            android.os.Bundle r9 = r0.f1057Oy
            java.lang.String r10 = r3.mo6283pg()
            java.lang.String r11 = "participant_id"
            r9.putString(r11, r10)
            android.os.Bundle r9 = r0.f1057Oy
            java.lang.String r10 = r3.mo6251Ug()
            java.lang.String r11 = "content_location"
            r9.putString(r11, r10)
            android.os.Bundle r9 = r0.f1057Oy
            java.lang.String r10 = r3.mo6252Vg()
            java.lang.String r11 = "transaction_id"
            r9.putString(r11, r10)
            android.os.Bundle r9 = r0.f1057Oy
            java.lang.String r10 = "notification_uri"
            r9.putParcelable(r10, r5)
            android.os.Bundle r9 = r0.f1057Oy
            java.lang.String r10 = "isAutoDownload: invalid input status "
            r12 = 102(0x66, float:1.43E-43)
            r13 = 104(0x68, float:1.46E-43)
            if (r7 == r12) goto L_0x008f
            if (r7 == r13) goto L_0x008d
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r10)
            r14.append(r7)
            java.lang.String r14 = r14.toString()
            com.android.messaging.util.C1424b.fail(r14)
            goto L_0x008f
        L_0x008d:
            r14 = 1
            goto L_0x0090
        L_0x008f:
            r14 = r4
        L_0x0090:
            java.lang.String r15 = "auto_download"
            r9.putBoolean(r15, r14)
            android.os.Bundle r9 = r0.f1057Oy
            long r14 = r3.mo6277lg()
            java.lang.String r11 = "expiry"
            r9.putLong(r11, r14)
            long r14 = java.lang.System.currentTimeMillis()
            boolean r9 = r3.mo6276l(r14)
            java.lang.String r11 = "MessagingAppDataModel"
            if (r9 == 0) goto L_0x0119
            android.os.Bundle r3 = r0.f1057Oy
            java.lang.String r2 = r2.mo6353sf()
            java.lang.String r8 = "sub_phone_number"
            r3.putString(r8, r2)
            r2 = 105(0x69, float:1.47E-43)
            r3 = 103(0x67, float:1.44E-43)
            if (r7 == r12) goto L_0x00d4
            if (r7 == r13) goto L_0x00d2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r10)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            com.android.messaging.util.C1424b.fail(r7)
            goto L_0x00d4
        L_0x00d2:
            r7 = r2
            goto L_0x00d5
        L_0x00d4:
            r7 = r3
        L_0x00d5:
            m1357a(r5, r1, r6, r7, r4)
            android.os.Bundle r4 = r0.f1057Oy
            if (r7 == r3) goto L_0x00f2
            if (r7 == r2) goto L_0x00f1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            com.android.messaging.util.C1424b.fail(r2)
            goto L_0x00f2
        L_0x00f1:
            r12 = r13
        L_0x00f2:
            java.lang.String r2 = "failure_status"
            r4.putInt(r2, r12)
            r2 = r18
            r2.mo5949b(r0)
            r0 = 3
            boolean r0 = android.util.Log.isLoggable(r11, r0)
            if (r0 == 0) goto L_0x0117
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "DownloadMmsAction: Queued download of MMS message "
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3620d(r11, r0)
        L_0x0117:
            r0 = 1
            return r0
        L_0x0119:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "DownloadMmsAction: Download of MMS message "
            r0.append(r2)
            r0.append(r1)
            java.lang.String r2 = " failed (outside download window)"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3630w(r11, r0)
            r0 = 106(0x6a, float:1.49E-43)
            m1357a(r5, r1, r6, r0, r4)
            if (r7 != r13) goto L_0x0146
            java.lang.String r0 = r3.mo6252Vg()
            java.lang.String r2 = r3.mo6251Ug()
            com.android.messaging.datamodel.action.ProcessDownloadedMmsAction.m1411a(r1, r0, r2, r8)
            r0 = 1
            return r0
        L_0x0146:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.DownloadMmsAction.mo5967a(java.lang.String, com.android.messaging.datamodel.action.Action):boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public Object mo5951i(Bundle bundle) {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: ue */
    public Bundle mo5955ue() {
        Context applicationContext = C0967f.get().getApplicationContext();
        int i = this.f1057Oy.getInt("sub_id");
        String string = this.f1057Oy.getString("message_id");
        Uri uri = (Uri) this.f1057Oy.getParcelable("notification_uri");
        String string2 = this.f1057Oy.getString("sub_phone_number");
        String string3 = this.f1057Oy.getString("transaction_id");
        String string4 = this.f1057Oy.getString("content_location");
        boolean z = this.f1057Oy.getBoolean("auto_download");
        String string5 = this.f1057Oy.getString("conversation_id");
        String string6 = this.f1057Oy.getString("participant_id");
        int i2 = this.f1057Oy.getInt("failure_status");
        long j = this.f1057Oy.getLong("expiry");
        long currentTimeMillis = ((System.currentTimeMillis() + 500) / 1000) * 1000;
        StringBuilder sb = new StringBuilder();
        sb.append("DownloadMmsAction: Downloading MMS message ");
        sb.append(string);
        String str = "DownloadMmsAction: Downloading MMS message ";
        sb.append(" (");
        sb.append(z ? "auto" : "manual");
        sb.append(")");
        C1430e.m3625i("MessagingAppDataModel", sb.toString());
        Bundle bundle = new Bundle();
        bundle.putString("message_id", string);
        bundle.putString("conversation_id", string5);
        bundle.putString("participant_id", string6);
        bundle.putInt("status_if_failed", i2);
        int i3 = i;
        String str2 = "MessagingAppDataModel";
        Uri uri2 = uri;
        String str3 = str;
        long j2 = currentTimeMillis;
        String str4 = string6;
        int i4 = i2;
        String str5 = string5;
        C1028x a = C1029y.m2428a(applicationContext, uri, i, string2, string3, string4, z, currentTimeMillis / 1000, j / 1000, bundle);
        if (a != C1029y.STATUS_PENDING) {
            C0947h.get().mo6606be().mo5917h(j2);
            ProcessDownloadedMmsAction.m1410a(string, uri2, str5, str4, string4, i3, string2, i4, z, string3, a.resultCode);
            return null;
        } else if (!Log.isLoggable(str2, 3)) {
            return null;
        } else {
            C1430e.m3620d(str2, str3 + string + " asynchronously; waiting for pending intent to signal completion");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C1424b.fail("DownloadMmsAction must be queued rather than started");
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ DownloadMmsAction(Parcel parcel, C0822l lVar) {
        super(parcel);
    }

    /* renamed from: a */
    static void m1357a(Uri uri, String str, String str2, int i, int i2) {
        Context applicationContext = C0967f.get().getApplicationContext();
        if (i == 105 || i == 103) {
            C1029y.m2441c(applicationContext, uri);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_status", Integer.valueOf(i));
        contentValues.put("raw_status", Integer.valueOf(i2));
        C0887c.m1666d(C0947h.get().getDatabase(), str, contentValues);
        MessagingContentProvider.m1273r(str2);
    }
}
