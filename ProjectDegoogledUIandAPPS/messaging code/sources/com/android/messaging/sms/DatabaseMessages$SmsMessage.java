package com.android.messaging.sms;

import android.content.ContentUris;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Telephony;
import androidx.core.app.NotificationCompat;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;

public class DatabaseMessages$SmsMessage extends C1017m implements Parcelable {

    /* renamed from: AE */
    public static final int f1508AE;
    public static final Parcelable.Creator CREATOR = new C1022r();

    /* renamed from: DE */
    public static final int f1509DE;

    /* renamed from: hE */
    private static int f1510hE;

    /* renamed from: iE */
    public static final int f1511iE;

    /* renamed from: ja */
    private static String[] f1512ja;

    /* renamed from: lB */
    public static final int f1513lB;

    /* renamed from: nB */
    public static final int f1514nB;

    /* renamed from: nE */
    public static final int f1515nE;

    /* renamed from: oE */
    public static final int f1516oE;

    /* renamed from: pB */
    public static final int f1517pB;

    /* renamed from: pE */
    public static final int f1518pE;

    /* renamed from: xE */
    public static final int f1519xE;

    /* renamed from: zE */
    public static final int f1520zE;

    /* renamed from: BA */
    public boolean f1521BA;

    /* renamed from: YD */
    public long f1522YD;

    /* renamed from: _b */
    public boolean f1523_b;

    /* renamed from: im */
    public String f1524im;
    public String mAddress;
    private long mRowId;
    public int mStatus;
    public int mSubId;
    public long mThreadId;
    public int mType;
    public String mUri;

    /* renamed from: yE */
    public long f1525yE;

    static {
        int i = f1510hE;
        f1510hE = i + 1;
        f1511iE = i;
        int i2 = f1510hE;
        f1510hE = i2 + 1;
        f1520zE = i2;
        int i3 = f1510hE;
        f1510hE = i3 + 1;
        f1508AE = i3;
        int i4 = f1510hE;
        f1510hE = i4 + 1;
        f1509DE = i4;
        int i5 = f1510hE;
        f1510hE = i5 + 1;
        f1515nE = i5;
        int i6 = f1510hE;
        f1510hE = i6 + 1;
        f1518pE = i6;
        int i7 = f1510hE;
        f1510hE = i7 + 1;
        f1517pB = i7;
        int i8 = f1510hE;
        f1510hE = i8 + 1;
        f1514nB = i8;
        int i9 = f1510hE;
        f1510hE = i9 + 1;
        f1513lB = i9;
        int i10 = f1510hE;
        f1510hE = i10 + 1;
        f1516oE = i10;
        int i11 = f1510hE;
        f1510hE = i11 + 1;
        f1519xE = i11;
    }

    private DatabaseMessages$SmsMessage() {
    }

    public static String[] getProjection() {
        if (f1512ja == null) {
            String[] strArr = {"_id", "type", "address", "body", "date", "thread_id", NotificationCompat.CATEGORY_STATUS, "read", "seen", "date_sent", "sub_id"};
            if (!C1029y.m2410Ii()) {
                strArr[f1516oE] = "date";
            }
            if (!C1464na.m3759Zj()) {
                C1424b.equals(f1519xE, strArr.length - 1);
                String[] strArr2 = new String[(strArr.length - 1)];
                System.arraycopy(strArr, 0, strArr2, 0, strArr2.length);
                strArr = strArr2;
            }
            f1512ja = strArr;
        }
        return f1512ja;
    }

    /* renamed from: l */
    public static DatabaseMessages$SmsMessage m2342l(Cursor cursor) {
        DatabaseMessages$SmsMessage databaseMessages$SmsMessage = new DatabaseMessages$SmsMessage();
        databaseMessages$SmsMessage.mRowId = cursor.getLong(f1511iE);
        databaseMessages$SmsMessage.mAddress = cursor.getString(f1508AE);
        databaseMessages$SmsMessage.f1524im = cursor.getString(f1509DE);
        databaseMessages$SmsMessage.f1522YD = cursor.getLong(f1515nE);
        databaseMessages$SmsMessage.f1525yE = cursor.getLong(f1516oE);
        databaseMessages$SmsMessage.mType = cursor.getInt(f1520zE);
        databaseMessages$SmsMessage.mThreadId = cursor.getLong(f1518pE);
        databaseMessages$SmsMessage.mStatus = cursor.getInt(f1517pB);
        boolean z = false;
        databaseMessages$SmsMessage.f1523_b = cursor.getInt(f1514nB) != 0;
        if (cursor.getInt(f1513lB) != 0) {
            z = true;
        }
        databaseMessages$SmsMessage.f1521BA = z;
        databaseMessages$SmsMessage.mUri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, databaseMessages$SmsMessage.mRowId).toString();
        databaseMessages$SmsMessage.mSubId = C1474sa.getDefault().mo8203a(cursor, f1519xE);
        return databaseMessages$SmsMessage;
    }

    public int describeContents() {
        return 0;
    }

    public int getProtocol() {
        return 0;
    }

    public int getSubId() {
        return this.mSubId;
    }

    public String getUri() {
        return this.mUri;
    }

    /* renamed from: hi */
    public long mo6791hi() {
        return this.f1522YD;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUri);
        parcel.writeLong(this.mRowId);
        parcel.writeLong(this.f1522YD);
        parcel.writeLong(this.f1525yE);
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mThreadId);
        parcel.writeInt(this.mStatus);
        parcel.writeInt(this.f1523_b ? 1 : 0);
        parcel.writeInt(this.f1521BA ? 1 : 0);
        parcel.writeInt(this.mSubId);
        parcel.writeString(this.mAddress);
        parcel.writeString(this.f1524im);
    }

    /* synthetic */ DatabaseMessages$SmsMessage(Parcel parcel, C1016l lVar) {
        this.mUri = parcel.readString();
        this.mRowId = parcel.readLong();
        this.f1522YD = parcel.readLong();
        this.f1525yE = parcel.readLong();
        this.mType = parcel.readInt();
        this.mThreadId = parcel.readLong();
        this.mStatus = parcel.readInt();
        boolean z = true;
        this.f1523_b = parcel.readInt() != 0;
        this.f1521BA = parcel.readInt() == 0 ? false : z;
        this.mSubId = parcel.readInt();
        this.mAddress = parcel.readString();
        this.f1524im = parcel.readString();
    }
}
