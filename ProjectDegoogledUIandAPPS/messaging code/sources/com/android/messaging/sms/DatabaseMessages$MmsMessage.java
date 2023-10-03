package com.android.messaging.sms;

import android.content.ContentUris;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Telephony;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMessages$MmsMessage extends C1017m implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1020p();

    /* renamed from: hE */
    private static int f1464hE;

    /* renamed from: iE */
    public static final int f1465iE;

    /* renamed from: jE */
    public static final int f1466jE;

    /* renamed from: ja */
    private static String[] f1467ja;

    /* renamed from: kE */
    public static final int f1468kE;

    /* renamed from: lB */
    public static final int f1469lB;

    /* renamed from: lE */
    public static final int f1470lE;

    /* renamed from: mE */
    public static final int f1471mE;

    /* renamed from: nB */
    public static final int f1472nB;

    /* renamed from: nE */
    public static final int f1473nE;

    /* renamed from: oE */
    public static final int f1474oE;

    /* renamed from: pB */
    public static final int f1475pB;

    /* renamed from: pE */
    public static final int f1476pE;

    /* renamed from: qE */
    public static final int f1477qE;

    /* renamed from: rE */
    public static final int f1478rE;

    /* renamed from: sE */
    public static final int f1479sE;

    /* renamed from: tE */
    public static final int f1480tE;

    /* renamed from: uE */
    public static final int f1481uE;

    /* renamed from: vE */
    public static final int f1482vE;

    /* renamed from: wE */
    public static final int f1483wE;

    /* renamed from: xE */
    public static final int f1484xE;

    /* renamed from: BA */
    public boolean f1485BA;

    /* renamed from: XD */
    public int f1486XD;

    /* renamed from: YD */
    public long f1487YD;

    /* renamed from: ZD */
    public long f1488ZD;

    /* renamed from: _D */
    public String f1489_D;

    /* renamed from: _b */
    public boolean f1490_b;

    /* renamed from: _l */
    public List f1491_l;

    /* renamed from: aE */
    public String f1492aE;

    /* renamed from: bE */
    public int f1493bE;

    /* renamed from: cE */
    public long f1494cE;

    /* renamed from: dE */
    public String f1495dE;

    /* renamed from: eE */
    public int f1496eE;

    /* renamed from: fE */
    public int f1497fE;

    /* renamed from: gE */
    private boolean f1498gE;
    public int mPriority;
    private long mRowId;
    private long mSize;
    public int mStatus;
    public int mSubId;
    public long mThreadId;
    public int mType;
    public String mUri;

    /* renamed from: tA */
    public String f1499tA;

    static {
        int i = f1464hE;
        f1464hE = i + 1;
        f1465iE = i;
        int i2 = f1464hE;
        f1464hE = i2 + 1;
        f1466jE = i2;
        int i3 = f1464hE;
        f1464hE = i3 + 1;
        f1468kE = i3;
        int i4 = f1464hE;
        f1464hE = i4 + 1;
        f1470lE = i4;
        int i5 = f1464hE;
        f1464hE = i5 + 1;
        f1471mE = i5;
        int i6 = f1464hE;
        f1464hE = i6 + 1;
        f1473nE = i6;
        int i7 = f1464hE;
        f1464hE = i7 + 1;
        f1474oE = i7;
        int i8 = f1464hE;
        f1464hE = i8 + 1;
        f1476pE = i8;
        int i9 = f1464hE;
        f1464hE = i9 + 1;
        f1477qE = i9;
        int i10 = f1464hE;
        f1464hE = i10 + 1;
        f1475pB = i10;
        int i11 = f1464hE;
        f1464hE = i11 + 1;
        f1472nB = i11;
        int i12 = f1464hE;
        f1464hE = i12 + 1;
        f1469lB = i12;
        int i13 = f1464hE;
        f1464hE = i13 + 1;
        f1478rE = i13;
        int i14 = f1464hE;
        f1464hE = i14 + 1;
        f1479sE = i14;
        int i15 = f1464hE;
        f1464hE = i15 + 1;
        f1480tE = i15;
        int i16 = f1464hE;
        f1464hE = i16 + 1;
        f1481uE = i16;
        int i17 = f1464hE;
        f1464hE = i17 + 1;
        f1482vE = i17;
        int i18 = f1464hE;
        f1464hE = i18 + 1;
        f1483wE = i18;
        int i19 = f1464hE;
        f1464hE = i19 + 1;
        f1484xE = i19;
    }

    private DatabaseMessages$MmsMessage() {
        this.f1491_l = new ArrayList();
        this.f1498gE = false;
    }

    public static String[] getProjection() {
        if (f1467ja == null) {
            String[] strArr = {"_id", "msg_box", "sub", "sub_cs", "m_size", "date", "date_sent", "thread_id", "pri", "st", "read", "seen", "ct_l", "tr_id", "m_type", "exp", "resp_st", "retr_st", "sub_id"};
            if (!C1464na.m3759Zj()) {
                C1424b.equals(f1484xE, strArr.length - 1);
                String[] strArr2 = new String[(strArr.length - 1)];
                System.arraycopy(strArr, 0, strArr2, 0, strArr2.length);
                strArr = strArr2;
            }
            f1467ja = strArr;
        }
        return f1467ja;
    }

    /* renamed from: l */
    public static DatabaseMessages$MmsMessage m2335l(Cursor cursor) {
        DatabaseMessages$MmsMessage databaseMessages$MmsMessage = new DatabaseMessages$MmsMessage();
        databaseMessages$MmsMessage.mo6798m(cursor);
        return databaseMessages$MmsMessage;
    }

    /* renamed from: a */
    public void mo6793a(DatabaseMessages$MmsPart databaseMessages$MmsPart) {
        this.f1491_l.add(databaseMessages$MmsPart);
    }

    public int describeContents() {
        return 0;
    }

    public long getId() {
        return this.mRowId;
    }

    public int getProtocol() {
        return 1;
    }

    public long getSize() {
        boolean z = this.f1498gE;
        if (!z && !z) {
            this.f1498gE = true;
            long j = 0;
            long j2 = 0;
            for (DatabaseMessages$MmsPart databaseMessages$MmsPart : this.f1491_l) {
                j2 += databaseMessages$MmsPart.mSize;
            }
            if (this.mSize <= 0) {
                String str = this.f1499tA;
                if (str != null) {
                    j = (long) str.getBytes().length;
                }
                this.mSize = j;
                this.mSize += j2;
            }
        }
        return this.mSize;
    }

    public int getSubId() {
        return this.mSubId;
    }

    public String getUri() {
        return this.mUri;
    }

    /* renamed from: hi */
    public long mo6791hi() {
        return this.f1487YD;
    }

    /* renamed from: m */
    public void mo6798m(Cursor cursor) {
        this.mRowId = cursor.getLong(f1465iE);
        this.mType = cursor.getInt(f1466jE);
        this.f1499tA = cursor.getString(f1468kE);
        this.f1486XD = cursor.getInt(f1470lE);
        if (!TextUtils.isEmpty(this.f1499tA)) {
            this.f1499tA = C0107q.m125a(C0107q.m132c(this.f1499tA, 4), this.f1486XD);
        }
        this.mSize = cursor.getLong(f1471mE);
        this.f1487YD = cursor.getLong(f1473nE) * 1000;
        this.f1488ZD = cursor.getLong(f1474oE) * 1000;
        this.mThreadId = cursor.getLong(f1476pE);
        this.mPriority = cursor.getInt(f1477qE);
        this.mStatus = cursor.getInt(f1475pB);
        boolean z = true;
        this.f1490_b = cursor.getInt(f1472nB) != 0;
        if (cursor.getInt(f1469lB) == 0) {
            z = false;
        }
        this.f1485BA = z;
        this.f1489_D = cursor.getString(f1478rE);
        this.f1492aE = cursor.getString(f1479sE);
        this.f1493bE = cursor.getInt(f1480tE);
        this.f1494cE = cursor.getLong(f1481uE) * 1000;
        this.f1496eE = cursor.getInt(f1482vE);
        this.f1497fE = cursor.getInt(f1483wE);
        this.f1491_l.clear();
        this.f1498gE = false;
        this.mUri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, this.mRowId).toString();
        this.mSubId = C1474sa.getDefault().mo8203a(cursor, f1484xE);
    }

    /* renamed from: qa */
    public void mo6799qa(String str) {
        this.f1495dE = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUri);
        parcel.writeLong(this.mRowId);
        parcel.writeLong(this.f1487YD);
        parcel.writeLong(this.f1488ZD);
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mThreadId);
        parcel.writeInt(this.mStatus);
        parcel.writeInt(this.f1490_b ? 1 : 0);
        parcel.writeInt(this.f1485BA ? 1 : 0);
        parcel.writeInt(this.mSubId);
        parcel.writeString(this.f1499tA);
        parcel.writeString(this.f1489_D);
        parcel.writeString(this.f1492aE);
        parcel.writeString(this.f1495dE);
        parcel.writeLong(this.mSize);
        parcel.writeLong(this.f1494cE);
        parcel.writeInt(this.f1486XD);
        parcel.writeInt(this.mPriority);
        parcel.writeInt(this.f1493bE);
        parcel.writeInt(this.f1496eE);
        parcel.writeInt(this.f1497fE);
        parcel.writeInt(this.f1491_l.size());
        for (DatabaseMessages$MmsPart writeParcelable : this.f1491_l) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }

    /* synthetic */ DatabaseMessages$MmsMessage(Parcel parcel, C1016l lVar) {
        this.f1491_l = new ArrayList();
        this.f1498gE = false;
        this.mUri = parcel.readString();
        this.mRowId = parcel.readLong();
        this.f1487YD = parcel.readLong();
        this.f1488ZD = parcel.readLong();
        this.mType = parcel.readInt();
        this.mThreadId = parcel.readLong();
        this.mStatus = parcel.readInt();
        boolean z = true;
        this.f1490_b = parcel.readInt() != 0;
        this.f1485BA = parcel.readInt() == 0 ? false : z;
        this.mSubId = parcel.readInt();
        this.f1499tA = parcel.readString();
        this.f1489_D = parcel.readString();
        this.f1492aE = parcel.readString();
        this.f1495dE = parcel.readString();
        this.mSize = parcel.readLong();
        this.f1494cE = parcel.readLong();
        this.f1486XD = parcel.readInt();
        this.mPriority = parcel.readInt();
        this.f1493bE = parcel.readInt();
        this.f1496eE = parcel.readInt();
        this.f1497fE = parcel.readInt();
        int readInt = parcel.readInt();
        this.f1491_l = new ArrayList();
        this.f1498gE = false;
        for (int i = 0; i < readInt; i++) {
            this.f1491_l.add((DatabaseMessages$MmsPart) parcel.readParcelable(DatabaseMessages$MmsMessage.class.getClassLoader()));
        }
    }
}
