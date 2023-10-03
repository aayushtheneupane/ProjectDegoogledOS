package com.android.messaging.datamodel.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p016v4.media.session.C0107q;
import android.telephony.SubscriptionInfo;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.sms.C1027w;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1474sa;
import com.android.p032ex.chips.C0699ra;
import p000a.p005b.C0015b;

public class ParticipantData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0900L();

    /* renamed from: gC */
    private static final C0015b f1191gC = new C0015b();

    /* renamed from: Jv */
    private String f1192Jv;

    /* renamed from: Rj */
    private long f1193Rj;

    /* renamed from: WB */
    private int f1194WB;

    /* renamed from: XB */
    private String f1195XB;

    /* renamed from: YB */
    private String f1196YB;

    /* renamed from: ZB */
    private String f1197ZB;

    /* renamed from: _B */
    private String f1198_B;

    /* renamed from: aC */
    private String f1199aC;

    /* renamed from: bC */
    private String f1200bC;

    /* renamed from: cC */
    private int f1201cC;

    /* renamed from: dC */
    private String f1202dC;

    /* renamed from: eC */
    private boolean f1203eC;

    /* renamed from: fC */
    private boolean f1204fC;
    private String mNormalizedDestination;
    private String mParticipantId;
    private int mSubId;

    private ParticipantData() {
    }

    /* renamed from: b */
    public static ParticipantData m1833b(C0699ra raVar) {
        String str;
        String str2;
        String str3;
        ParticipantData participantData = new ParticipantData();
        participantData.mParticipantId = null;
        participantData.mSubId = -2;
        participantData.f1194WB = -1;
        participantData.f1195XB = C0107q.replaceUnicodeDigits(raVar.getDestination());
        participantData.f1203eC = C1027w.isEmailAddress(participantData.f1195XB);
        if (participantData.f1203eC) {
            str = participantData.f1195XB;
        } else {
            str = C1474sa.getDefault().mo8222Ka(participantData.f1195XB);
        }
        participantData.mNormalizedDestination = str;
        if (participantData.f1203eC) {
            str2 = participantData.mNormalizedDestination;
        } else {
            str2 = C1474sa.getDefault().mo8220Ia(participantData.mNormalizedDestination);
        }
        participantData.f1196YB = str2;
        participantData.f1198_B = raVar.getDisplayName();
        participantData.f1199aC = null;
        if (raVar.mo5666yd() == null) {
            str3 = null;
        } else {
            str3 = raVar.mo5666yd().toString();
        }
        participantData.f1200bC = str3;
        participantData.f1193Rj = raVar.getContactId();
        if (participantData.f1193Rj < 0) {
            participantData.f1193Rj = -1;
        }
        participantData.f1192Jv = raVar.mo5659m();
        participantData.f1204fC = false;
        participantData.f1201cC = 0;
        participantData.f1202dC = null;
        participantData.m1838jo();
        return participantData;
    }

    /* renamed from: g */
    public static ParticipantData m1835g(String str, int i) {
        String str2;
        String str3;
        ParticipantData gb = m1836gb(str);
        if (gb.f1203eC) {
            str2 = gb.f1195XB;
        } else {
            str2 = C1474sa.get(i).mo8221Ja(gb.f1195XB);
        }
        gb.mNormalizedDestination = str2;
        if (gb.f1203eC) {
            str3 = gb.mNormalizedDestination;
        } else {
            str3 = C1474sa.getDefault().mo8220Ia(gb.mNormalizedDestination);
        }
        gb.f1196YB = str3;
        gb.m1838jo();
        return gb;
    }

    /* renamed from: gb */
    private static ParticipantData m1836gb(String str) {
        C1424b.m3592ia(str != null);
        ParticipantData participantData = new ParticipantData();
        participantData.mParticipantId = null;
        participantData.mSubId = -2;
        participantData.f1194WB = -1;
        participantData.f1195XB = C0107q.replaceUnicodeDigits(str);
        participantData.f1203eC = C1027w.isEmailAddress(participantData.f1195XB);
        participantData.f1198_B = null;
        participantData.f1199aC = null;
        participantData.f1200bC = null;
        participantData.f1193Rj = -1;
        participantData.f1192Jv = null;
        participantData.f1204fC = false;
        participantData.f1201cC = 0;
        participantData.f1202dC = null;
        return participantData;
    }

    /* renamed from: ia */
    public static ParticipantData m1837ia(String str) {
        String str2;
        String str3;
        ParticipantData gb = m1836gb(str);
        if (gb.f1203eC) {
            str2 = gb.f1195XB;
        } else {
            str2 = C1474sa.getDefault().mo8222Ka(gb.f1195XB);
        }
        gb.mNormalizedDestination = str2;
        if (gb.f1203eC) {
            str3 = gb.mNormalizedDestination;
        } else {
            str3 = C1474sa.getDefault().mo8220Ia(gb.mNormalizedDestination);
        }
        gb.f1196YB = str3;
        gb.m1838jo();
        return gb;
    }

    /* renamed from: jo */
    private void m1838jo() {
        if (mo6328Ah()) {
            this.f1196YB = C0967f.get().getApplicationContext().getResources().getString(R.string.unknown_sender);
            this.f1198_B = this.f1196YB;
        }
    }

    /* renamed from: k */
    public static ParticipantData m1839k(Cursor cursor) {
        ParticipantData participantData = new ParticipantData();
        boolean z = false;
        participantData.mParticipantId = cursor.getString(0);
        participantData.mSubId = cursor.getInt(1);
        participantData.f1194WB = cursor.getInt(2);
        participantData.mNormalizedDestination = cursor.getString(3);
        participantData.f1195XB = cursor.getString(4);
        participantData.f1196YB = cursor.getString(5);
        participantData.f1197ZB = cursor.getString(14);
        participantData.f1198_B = cursor.getString(6);
        participantData.f1199aC = cursor.getString(7);
        participantData.f1200bC = cursor.getString(8);
        participantData.f1193Rj = cursor.getLong(9);
        participantData.f1192Jv = cursor.getString(10);
        participantData.f1203eC = C1027w.isEmailAddress(participantData.f1195XB);
        if (cursor.getInt(11) != 0) {
            z = true;
        }
        participantData.f1204fC = z;
        participantData.f1201cC = cursor.getInt(12);
        participantData.f1202dC = cursor.getString(13);
        participantData.m1838jo();
        return participantData;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002d  */
    /* renamed from: l */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.datamodel.data.ParticipantData m1840l(com.android.messaging.datamodel.C0955p r9, java.lang.String r10) {
        /*
            r0 = 0
            java.lang.String r2 = "participants"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.C0901M.f1157Wu     // Catch:{ all -> 0x002a }
            java.lang.String r4 = "_id =?"
            r1 = 1
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch:{ all -> 0x002a }
            r1 = 0
            r5[r1] = r10     // Catch:{ all -> 0x002a }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r9
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x002a }
            boolean r10 = r9.moveToFirst()     // Catch:{ all -> 0x0027 }
            if (r10 == 0) goto L_0x0023
            com.android.messaging.datamodel.data.ParticipantData r10 = m1839k(r9)     // Catch:{ all -> 0x0027 }
            r9.close()
            return r10
        L_0x0023:
            r9.close()
            return r0
        L_0x0027:
            r10 = move-exception
            r0 = r9
            goto L_0x002b
        L_0x002a:
            r10 = move-exception
        L_0x002b:
            if (r0 == 0) goto L_0x0030
            r0.close()
        L_0x0030:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.data.ParticipantData.m1840l(com.android.messaging.datamodel.p, java.lang.String):com.android.messaging.datamodel.data.ParticipantData");
    }

    /* renamed from: sa */
    public static ParticipantData m1841sa(int i) {
        C1424b.m3592ia(i != -2);
        ParticipantData participantData = new ParticipantData();
        participantData.mParticipantId = null;
        participantData.mSubId = i;
        participantData.f1194WB = -1;
        participantData.f1203eC = false;
        participantData.f1195XB = null;
        participantData.mNormalizedDestination = null;
        participantData.f1196YB = null;
        participantData.f1198_B = null;
        participantData.f1199aC = null;
        participantData.f1200bC = null;
        participantData.f1193Rj = -1;
        participantData.f1192Jv = null;
        participantData.f1204fC = false;
        participantData.f1201cC = 0;
        participantData.f1202dC = null;
        return participantData;
    }

    /* renamed from: vh */
    public static String m1842vh() {
        return "ʼUNKNOWN_SENDER!ʼ";
    }

    /* renamed from: Ah */
    public boolean mo6328Ah() {
        return TextUtils.equals(this.f1195XB, "ʼUNKNOWN_SENDER!ʼ");
    }

    /* renamed from: Bh */
    public boolean mo6329Bh() {
        String la = C1474sa.get(this.mSubId).mo8229la(true);
        if (!mo6362zh() || TextUtils.equals(la, this.mNormalizedDestination)) {
            return false;
        }
        this.mNormalizedDestination = la;
        this.f1195XB = la;
        if (!this.f1203eC) {
            la = C1474sa.getDefault().mo8220Ia(la);
        }
        this.f1196YB = la;
        return true;
    }

    /* renamed from: P */
    public String mo6330P(boolean z) {
        if (z) {
            if (!TextUtils.isEmpty(this.f1198_B)) {
                return this.f1198_B;
            }
            if (!TextUtils.isEmpty(this.f1199aC)) {
                return this.f1199aC;
            }
        } else if (!TextUtils.isEmpty(this.f1199aC)) {
            return this.f1199aC;
        } else {
            if (!TextUtils.isEmpty(this.f1198_B)) {
                return this.f1198_B;
            }
        }
        if (!TextUtils.isEmpty(this.f1196YB)) {
            return this.f1196YB;
        }
        return C0967f.get().getApplicationContext().getResources().getString(R.string.unknown_sender);
    }

    /* renamed from: a */
    public boolean mo6331a(SubscriptionInfo subscriptionInfo) {
        if (mo6362zh()) {
            if (subscriptionInfo != null) {
                int simSlotIndex = subscriptionInfo.getSimSlotIndex();
                int iconTint = subscriptionInfo.getIconTint();
                CharSequence displayName = subscriptionInfo.getDisplayName();
                if (!(this.f1194WB == simSlotIndex && this.f1201cC == iconTint && this.f1202dC == displayName)) {
                    this.f1194WB = simSlotIndex;
                    this.f1201cC = iconTint;
                    this.f1202dC = displayName.toString();
                    return true;
                }
            } else if (mo6358wh()) {
                this.f1194WB = -1;
                this.f1201cC = 0;
                this.f1202dC = "";
                return true;
            }
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public long getContactId() {
        return this.f1193Rj;
    }

    public String getId() {
        return this.mParticipantId;
    }

    public int getSubId() {
        return this.mSubId;
    }

    public boolean isBlocked() {
        return this.f1204fC;
    }

    public boolean isEmail() {
        return this.f1203eC;
    }

    /* renamed from: ja */
    public void mo6338ja(String str) {
        this.f1197ZB = str;
    }

    /* renamed from: ka */
    public void mo6339ka(String str) {
        this.f1199aC = str;
    }

    /* renamed from: la */
    public void mo6340la(String str) {
        this.f1198_B = str;
    }

    /* renamed from: lh */
    public String mo6341lh() {
        return this.f1197ZB;
    }

    /* renamed from: m */
    public String mo6342m() {
        return this.f1192Jv;
    }

    /* renamed from: ma */
    public void mo6343ma(String str) {
        this.f1192Jv = str;
    }

    /* renamed from: mh */
    public String mo6344mh() {
        return this.f1196YB;
    }

    /* renamed from: na */
    public void mo6345na(String str) {
        this.f1200bC = str;
    }

    /* renamed from: nh */
    public int mo6346nh() {
        return mo6354sh() + 1;
    }

    /* renamed from: oa */
    public void mo6347oa(String str) {
        this.f1195XB = str;
    }

    /* renamed from: oh */
    public String mo6348oh() {
        return this.f1199aC;
    }

    /* renamed from: ph */
    public String mo6349ph() {
        return this.f1198_B;
    }

    /* renamed from: qh */
    public String mo6350qh() {
        return this.f1200bC;
    }

    /* renamed from: rh */
    public String mo6351rh() {
        return this.f1195XB;
    }

    public void setContactId(long j) {
        this.f1193Rj = j;
    }

    /* renamed from: sf */
    public String mo6353sf() {
        return this.mNormalizedDestination;
    }

    /* renamed from: sh */
    public int mo6354sh() {
        return this.f1194WB;
    }

    /* renamed from: th */
    public int mo6355th() {
        C1424b.m3592ia(mo6358wh());
        return this.f1201cC | ViewCompat.MEASURED_STATE_MASK;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sub_id", Integer.valueOf(this.mSubId));
        contentValues.put("sim_slot_id", Integer.valueOf(this.f1194WB));
        contentValues.put("send_destination", this.f1195XB);
        if (!mo6328Ah()) {
            contentValues.put("display_destination", this.f1196YB);
            contentValues.put("normalized_destination", this.mNormalizedDestination);
            contentValues.put("full_name", this.f1198_B);
            contentValues.put("first_name", this.f1199aC);
        }
        contentValues.put("profile_photo_uri", this.f1200bC);
        contentValues.put("contact_id", Long.valueOf(this.f1193Rj));
        contentValues.put("lookup_key", this.f1192Jv);
        contentValues.put("blocked", Boolean.valueOf(this.f1204fC));
        contentValues.put("subscription_color", Integer.valueOf(this.f1201cC));
        contentValues.put("subscription_name", this.f1202dC);
        return contentValues;
    }

    /* renamed from: uh */
    public String mo6357uh() {
        C1424b.m3592ia(mo6358wh());
        return this.f1202dC;
    }

    /* renamed from: wh */
    public boolean mo6358wh() {
        return this.f1194WB != -1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mParticipantId);
        parcel.writeInt(this.mSubId);
        parcel.writeInt(this.f1194WB);
        parcel.writeString(this.mNormalizedDestination);
        parcel.writeString(this.f1195XB);
        parcel.writeString(this.f1196YB);
        parcel.writeString(this.f1198_B);
        parcel.writeString(this.f1199aC);
        parcel.writeString(this.f1200bC);
        parcel.writeLong(this.f1193Rj);
        parcel.writeString(this.f1192Jv);
        parcel.writeInt(this.f1203eC ? 1 : 0);
        parcel.writeInt(this.f1204fC ? 1 : 0);
        parcel.writeInt(this.f1201cC);
        parcel.writeString(this.f1202dC);
    }

    /* renamed from: xh */
    public boolean mo6360xh() {
        return this.f1193Rj != -1;
    }

    /* renamed from: yh */
    public boolean mo6361yh() {
        return this.mSubId == -1;
    }

    /* renamed from: zh */
    public boolean mo6362zh() {
        return this.mSubId != -2;
    }

    public ParticipantData(Parcel parcel) {
        this.mParticipantId = parcel.readString();
        this.mSubId = parcel.readInt();
        this.f1194WB = parcel.readInt();
        this.mNormalizedDestination = parcel.readString();
        this.f1195XB = parcel.readString();
        this.f1196YB = parcel.readString();
        this.f1198_B = parcel.readString();
        this.f1199aC = parcel.readString();
        this.f1200bC = parcel.readString();
        this.f1193Rj = parcel.readLong();
        this.f1192Jv = parcel.readString();
        boolean z = true;
        this.f1203eC = parcel.readInt() != 0;
        this.f1204fC = parcel.readInt() == 0 ? false : z;
        this.f1201cC = parcel.readInt();
        this.f1202dC = parcel.readString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0051, code lost:
        if (r11 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0057, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0058, code lost:
        r12.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
        throw r0;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1834b(com.android.messaging.datamodel.C0955p r11, int r12) {
        /*
            a.b.b r0 = f1191gC
            monitor-enter(r0)
            a.b.b r1 = f1191gC     // Catch:{ all -> 0x005c }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x005c }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x005c }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x005c }
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            if (r1 == 0) goto L_0x0013
            return r1
        L_0x0013:
            java.lang.String r0 = "_id"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r0 = 1
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r0 = java.lang.Integer.toString(r12)
            r10 = 0
            r6[r10] = r0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r3 = "participants"
            java.lang.String r5 = "sub_id =?"
            r2 = r11
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)
            boolean r0 = r11.moveToFirst()     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x004a
            java.lang.String r1 = r11.getString(r10)     // Catch:{ all -> 0x004e }
            a.b.b r0 = f1191gC     // Catch:{ all -> 0x004e }
            monitor-enter(r0)     // Catch:{ all -> 0x004e }
            a.b.b r2 = f1191gC     // Catch:{ all -> 0x0047 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x0047 }
            r2.put(r12, r1)     // Catch:{ all -> 0x0047 }
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            goto L_0x004a
        L_0x0047:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r12     // Catch:{ all -> 0x004e }
        L_0x004a:
            r11.close()
            return r1
        L_0x004e:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r0 = move-exception
            if (r11 == 0) goto L_0x005b
            r11.close()     // Catch:{ all -> 0x0057 }
            goto L_0x005b
        L_0x0057:
            r11 = move-exception
            r12.addSuppressed(r11)
        L_0x005b:
            throw r0
        L_0x005c:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.data.ParticipantData.m1834b(com.android.messaging.datamodel.p, int):java.lang.String");
    }
}
