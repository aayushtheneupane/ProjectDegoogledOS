package com.android.messaging.sms;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.p016v4.media.session.C0107q;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.mmslib.InvalidHeaderValueException;
import com.android.messaging.mmslib.MmsException;
import com.android.messaging.mmslib.p039a.C0975e;
import com.android.messaging.mmslib.p039a.C0976f;
import com.android.messaging.mmslib.p039a.C0978h;
import com.android.messaging.mmslib.p039a.C0980j;
import com.android.messaging.mmslib.p039a.C0985o;
import com.android.messaging.mmslib.p039a.C0988r;
import com.android.messaging.mmslib.p039a.C0989s;
import com.android.messaging.mmslib.p039a.C0990t;
import com.android.messaging.mmslib.p039a.C0993w;
import com.android.messaging.mmslib.p039a.C0994x;
import com.android.messaging.mmslib.p039a.C0995y;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1415T;
import com.android.messaging.util.C1416U;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1431ea;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1481w;
import com.google.common.base.C1504A;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.y */
public class C1029y {

    /* renamed from: RE */
    private static String[] f1546RE;

    /* renamed from: SE */
    private static final String f1547SE = String.format(Locale.US, "(%s IN (%d, %d, %d, %d, %d))", new Object[]{"type", 1, 4, 6, 5, 2});
    public static final C1028x STATUS_PENDING = new C1028x(-1, -1, (Uri) null);

    /* renamed from: TE */
    private static final String f1548TE = String.format(Locale.US, "((%s IN (%d, %d, %d, %d)) AND (%s IN (%d, %d, %d)))", new Object[]{"msg_box", 1, 4, 2, 5, "m_type", 128, 130, 132});

    /* renamed from: UE */
    private static final Uri f1549UE = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();

    /* renamed from: VE */
    private static final String[] f1550VE = {"_id", "recipient_ids"};

    /* renamed from: WE */
    private static final Uri f1551WE = Uri.parse("content://mms-sms/canonical-address");

    /* renamed from: XE */
    private static final String[] f1552XE = {"date_sent"};

    /* renamed from: YE */
    private static Boolean f1553YE = null;

    /* renamed from: ZE */
    private static final String[] f1554ZE = {"mmsc"};

    /* renamed from: _E */
    private static Boolean f1555_E = null;

    /* renamed from: aF */
    public static final Uri f1556aF = Uri.parse("content://mms/part");

    /* renamed from: A */
    public static List m2400A(long j) {
        Cursor cursor;
        String z = m2453z(j);
        if (TextUtils.isEmpty(z)) {
            return null;
        }
        Context applicationContext = C0967f.get().getApplicationContext();
        ArrayList arrayList = new ArrayList();
        for (String parseLong : z.split(" ")) {
            try {
                long parseLong2 = Long.parseLong(parseLong);
                if (parseLong2 < 0) {
                    C1430e.m3622e("MessagingApp", "MmsUtils.getAddresses: invalid id " + parseLong2);
                } else {
                    try {
                        cursor = applicationContext.getContentResolver().query(ContentUris.withAppendedId(f1551WE, parseLong2), (String[]) null, (String) null, (String[]) null, (String) null);
                    } catch (Exception e) {
                        C1430e.m3623e("MessagingApp", "MmsUtils.getAddresses: query failed for id " + parseLong2, e);
                        cursor = null;
                    }
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                String string = cursor.getString(0);
                                if (!TextUtils.isEmpty(string)) {
                                    arrayList.add(string);
                                } else {
                                    C1430e.m3630w("MessagingApp", "Canonical MMS/SMS address is empty for id: " + parseLong2);
                                }
                            }
                        } finally {
                            cursor.close();
                        }
                    }
                }
            } catch (NumberFormatException e2) {
                C0632a.m1020a("MmsUtils.getAddresses: invalid id. ", (Object) e2, "MessagingApp", (Throwable) e2);
            }
        }
        if (arrayList.isEmpty()) {
            C1430e.m3630w("MessagingApp", "No MMS addresses found from ids string [" + z + "]");
        }
        return arrayList;
    }

    /* renamed from: Ca */
    public static final String m2401Ca(int i) {
        if (i == 0) {
            return "SUCCEEDED";
        }
        if (i == 1) {
            return "AUTO_RETRY";
        }
        if (i == 2) {
            return "MANUAL_RETRY";
        }
        if (i == 3) {
            return "NO_RETRY";
        }
        return String.valueOf(i) + " (check MmsUtils)";
    }

    /* renamed from: Da */
    public static String m2402Da(int i) {
        if (i <= 0) {
            return null;
        }
        StringBuilder Pa = C0632a.m1011Pa("(?");
        for (int i2 = 0; i2 < i - 1; i2++) {
            Pa.append(",?");
        }
        Pa.append(")");
        return Pa.toString();
    }

    /* renamed from: Ea */
    public static boolean m2403Ea(int i) {
        Resources resources = C0967f.get().getApplicationContext().getResources();
        return C1024t.get(i).mo6841li() && C1451h.m3725ha(i).getBoolean(resources.getString(R.string.group_mms_pref_key), resources.getBoolean(R.bool.group_mms_pref_default));
    }

    /* renamed from: Fa */
    public static boolean m2404Fa(int i) {
        if (!C1024t.get(i).mo6849ui()) {
            return false;
        }
        Resources resources = C0967f.get().getApplicationContext().getResources();
        return C1451h.m3725ha(i).getBoolean(resources.getString(R.string.delivery_reports_pref_key), resources.getBoolean(R.bool.delivery_reports_pref_default));
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: Fi */
    public static int m2405Fi() {
        String[] strArr;
        Locale locale = Locale.US;
        String format = String.format(locale, "%s AND (%s IN (SELECT %s FROM part WHERE %s))", new Object[]{f1548TE, "_id", "mid", String.format(locale, "((%s LIKE '%s') OR (%s LIKE '%s') OR (%s LIKE '%s') OR (%s='%s'))", new Object[]{"ct", "image/%", "ct", "video/%", "ct", "audio/%", "ct", "application/ogg"})});
        ContentResolver Pk = C0632a.m1012Pk();
        Cursor query = Pk.query(Telephony.Mms.CONTENT_URI, new String[]{"_id"}, format, (String[]) null, (String) null);
        if (query == null) {
            return 0;
        }
        long[] jArr = new long[query.getCount()];
        int i = 0;
        while (query.moveToNext()) {
            try {
                int i2 = i + 1;
                jArr[i] = query.getLong(0);
                i = i2;
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        query.close();
        int length = jArr.length;
        if (length <= 0) {
            return 0;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int i5 = i3 + 128;
            int min = Math.min(i5, length) - i3;
            String format2 = String.format(Locale.US, "%s IN %s", new Object[]{"_id", m2402Da(min)});
            if (min <= 0) {
                strArr = null;
            } else {
                String[] strArr2 = new String[min];
                for (int i6 = 0; i6 < min; i6++) {
                    strArr2[i6] = Long.toString(jArr[i3 + i6]);
                }
                strArr = strArr2;
            }
            int delete = Pk.delete(Telephony.Mms.CONTENT_URI, format2, strArr);
            if (Log.isLoggable("MessagingApp", 3)) {
                StringBuilder Pa = C0632a.m1011Pa("deleteMediaMessages: deleting IDs = ");
                Pa.append(C1504A.m3945e(',').mo8510Vk().mo8516c(strArr));
                Pa.append(", deleted = ");
                Pa.append(delete);
                C1430e.m3620d("MessagingApp", Pa.toString());
            }
            i4 += delete;
            i3 = i5;
        }
        return i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return com.android.messaging.R.string.mms_failure_outgoing_corrupt;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return com.android.messaging.R.string.mms_failure_outgoing_service;
     */
    /* renamed from: Ga */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m2406Ga(int r1) {
        /*
            r0 = 135(0x87, float:1.89E-43)
            if (r1 == r0) goto L_0x0032
            r0 = 136(0x88, float:1.9E-43)
            if (r1 == r0) goto L_0x002e
            r0 = 193(0xc1, float:2.7E-43)
            if (r1 == r0) goto L_0x002a
            r0 = 229(0xe5, float:3.21E-43)
            if (r1 == r0) goto L_0x0032
            r0 = 10000(0x2710, float:1.4013E-41)
            if (r1 == r0) goto L_0x0026
            switch(r1) {
                case 130: goto L_0x0022;
                case 131: goto L_0x001e;
                case 132: goto L_0x002a;
                default: goto L_0x0017;
            }
        L_0x0017:
            switch(r1) {
                case 225: goto L_0x0022;
                case 226: goto L_0x001e;
                case 227: goto L_0x002a;
                default: goto L_0x001a;
            }
        L_0x001a:
            r1 = 2131820837(0x7f110125, float:1.92744E38)
            goto L_0x0035
        L_0x001e:
            r1 = 2131820849(0x7f110131, float:1.9274425E38)
            goto L_0x0035
        L_0x0022:
            r1 = 2131820850(0x7f110132, float:1.9274427E38)
            goto L_0x0035
        L_0x0026:
            r1 = 2131820851(0x7f110133, float:1.9274429E38)
            goto L_0x0035
        L_0x002a:
            r1 = 2131820847(0x7f11012f, float:1.927442E38)
            goto L_0x0035
        L_0x002e:
            r1 = 2131820852(0x7f110134, float:1.927443E38)
            goto L_0x0035
        L_0x0032:
            r1 = 2131820848(0x7f110130, float:1.9274423E38)
        L_0x0035:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1029y.m2406Ga(int):int");
    }

    /* renamed from: Gb */
    private static boolean m2407Gb(int i) {
        if (C1464na.m3759Zj()) {
            return true;
        }
        C1474sa saVar = C1474sa.get(i);
        if (saVar.mo8227jk() || !saVar.isMobileDataEnabled()) {
            return false;
        }
        return true;
    }

    /* renamed from: Gi */
    public static String m2408Gi() {
        return f1548TE;
    }

    /* renamed from: Hi */
    public static String m2409Hi() {
        return f1547SE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (r0 == null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0024, code lost:
        if (r0 != null) goto L_0x003a;
     */
    /* renamed from: Ii */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m2410Ii() {
        /*
            java.lang.Boolean r0 = f1553YE
            if (r0 != 0) goto L_0x0044
            r0 = 0
            com.android.messaging.f r1 = com.android.messaging.C0967f.get()     // Catch:{ SQLiteException -> 0x0029 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ SQLiteException -> 0x0029 }
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch:{ SQLiteException -> 0x0029 }
            android.net.Uri r3 = android.provider.Telephony.Sms.CONTENT_URI     // Catch:{ SQLiteException -> 0x0029 }
            java.lang.String[] r4 = f1552XE     // Catch:{ SQLiteException -> 0x0029 }
            r5 = 0
            r6 = 0
            java.lang.String r7 = "date_sent ASC LIMIT 1"
            android.database.Cursor r0 = android.support.p016v4.media.session.C0107q.query(r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0029 }
            r1 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ SQLiteException -> 0x0029 }
            f1553YE = r1     // Catch:{ SQLiteException -> 0x0029 }
            if (r0 == 0) goto L_0x0044
            goto L_0x003a
        L_0x0027:
            r1 = move-exception
            goto L_0x003e
        L_0x0029:
            r1 = move-exception
            java.lang.String r2 = "MessagingApp"
            java.lang.String r3 = "date_sent in sms table does not exist"
            com.android.messaging.util.C1430e.m3631w(r2, r3, r1)     // Catch:{ all -> 0x0027 }
            r1 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x0027 }
            f1553YE = r1     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0044
        L_0x003a:
            r0.close()
            goto L_0x0044
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.close()
        L_0x0043:
            throw r1
        L_0x0044:
            java.lang.Boolean r0 = f1553YE
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1029y.m2410Ii():boolean");
    }

    /* renamed from: Ji */
    public static boolean m2411Ji() {
        C1410N.m3547Nj();
        return false;
    }

    /* renamed from: Ki */
    public static boolean m2412Ki() {
        C1410N.m3547Nj();
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (r0 == null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0023, code lost:
        if (r0 != null) goto L_0x0039;
     */
    /* renamed from: Li */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m2413Li() {
        /*
            java.lang.Boolean r0 = f1555_E
            if (r0 != 0) goto L_0x0043
            r0 = 0
            com.android.messaging.f r1 = com.android.messaging.C0967f.get()     // Catch:{ SecurityException -> 0x0028 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ SecurityException -> 0x0028 }
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch:{ SecurityException -> 0x0028 }
            android.net.Uri r3 = android.provider.Telephony.Carriers.SIM_APN_URI     // Catch:{ SecurityException -> 0x0028 }
            java.lang.String[] r4 = f1554ZE     // Catch:{ SecurityException -> 0x0028 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = android.support.p016v4.media.session.C0107q.query(r2, r3, r4, r5, r6, r7)     // Catch:{ SecurityException -> 0x0028 }
            r1 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ SecurityException -> 0x0028 }
            f1555_E = r1     // Catch:{ SecurityException -> 0x0028 }
            if (r0 == 0) goto L_0x0043
            goto L_0x0039
        L_0x0026:
            r1 = move-exception
            goto L_0x003d
        L_0x0028:
            r1 = move-exception
            java.lang.String r2 = "MessagingApp"
            java.lang.String r3 = "Can't access system APN, using internal table"
            com.android.messaging.util.C1430e.m3631w(r2, r3, r1)     // Catch:{ all -> 0x0026 }
            r1 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x0026 }
            f1555_E = r1     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0043
        L_0x0039:
            r0.close()
            goto L_0x0043
        L_0x003d:
            if (r0 == 0) goto L_0x0042
            r0.close()
        L_0x0042:
            throw r1
        L_0x0043:
            java.lang.Boolean r0 = f1555_E
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1029y.m2413Li():boolean");
    }

    /* renamed from: a */
    public static void m2432a(Context context, int i, byte[] bArr, String str, int i2) {
        try {
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "MmsUtils: Sending M-NotifyResp.ind for received MMS, status: " + String.format("0x%X", new Object[]{Integer.valueOf(i2)}));
            }
            if (str == null) {
                C1430e.m3630w("MessagingApp", "MmsUtils: Can't send NotifyResp; contentLocation is null");
            } else if (bArr == null) {
                C1430e.m3630w("MessagingApp", "MmsUtils: Can't send NotifyResp; transaction id is null");
            } else if (!m2407Gb(i)) {
                C1430e.m3630w("MessagingApp", "MmsUtils: Can't send NotifyResp; no data available");
            } else {
                C1025u.m2397a(context, i, bArr, str, i2);
            }
        } catch (MmsFailureException e) {
            C0632a.m1020a("sendNotifyResponseForMmsDownload: failed to retrieve message ", (Object) e, "MessagingApp", (Throwable) e);
        } catch (InvalidHeaderValueException e2) {
            C0632a.m1020a("sendNotifyResponseForMmsDownload: failed to retrieve message ", (Object) e2, "MessagingApp", (Throwable) e2);
        }
    }

    /* renamed from: b */
    public static int m2437b(boolean z, boolean z2, int i) {
        return z ? (i == 4 || i == 5) ? 8 : 1 : z2 ? 101 : 100;
    }

    /* renamed from: b */
    public static boolean m2440b(Context context, Uri uri, int i, long j) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("type", Integer.valueOf(i));
            contentValues.put("date", Long.valueOf(j));
            if (contentResolver.update(uri, contentValues, (String) null, (String[]) null) != 1) {
                return false;
            }
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "Mmsutils: Updated sending SMS " + uri + "; type = " + i + ", date = " + j + " (millis since epoch)");
            }
            return true;
        } catch (SQLiteException e) {
            C0632a.m1020a("MmsUtils: update sms message failure ", (Object) e, "MessagingApp", (Throwable) e);
            return false;
        } catch (IllegalArgumentException e2) {
            C0632a.m1020a("MmsUtils: update sms message failure ", (Object) e2, "MessagingApp", (Throwable) e2);
            return false;
        }
    }

    /* renamed from: c */
    public static void m2441c(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.putNull("st");
        C0107q.m122a(context.getContentResolver(), uri, contentValues, (String) null, (String[]) null);
    }

    /* renamed from: g */
    public static long m2442g(Context context, String str) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        try {
            return C1026v.getOrCreateThreadId(context, (Set) hashSet);
        } catch (IllegalArgumentException e) {
            C1430e.m3622e("MessagingApp", "MmsUtils: getting thread id failed: " + e);
            return -1;
        }
    }

    /* renamed from: h */
    public static byte[] m2443h(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            C1430e.m3623e("MessagingApp", "MmsUtils.stringToBytes: " + e, e);
            return str.getBytes();
        }
    }

    /* renamed from: i */
    private static int m2444i(Context context, Uri uri) {
        StringBuilder sb;
        int i = 0;
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                try {
                    i = inputStream.available();
                } catch (IOException e) {
                    C1430e.m3623e("MessagingApp", "getDataLength couldn't stream: " + uri, e);
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        sb = new StringBuilder();
                    }
                    return 0;
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    C0632a.m1020a("getDataLength couldn't close: ", (Object) uri, "MessagingApp", (Throwable) e3);
                }
            }
            return i;
            sb.append("getDataLength couldn't close: ");
            sb.append(uri);
            C1430e.m3623e("MessagingApp", sb.toString(), e);
            return 0;
        } catch (FileNotFoundException e4) {
            C1430e.m3623e("MessagingApp", "getDataLength couldn't open: " + uri, e4);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e = e5;
                    sb = new StringBuilder();
                }
            }
            return 0;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                    C0632a.m1020a("getDataLength couldn't close: ", (Object) uri, "MessagingApp", (Throwable) e6);
                }
            }
            throw th;
        }
    }

    /* renamed from: ib */
    private static String m2445ib(String str) {
        return str == null ? "" : str.replace(12, 10);
    }

    /* renamed from: l */
    public static int m2446l(Uri uri) {
        return C0967f.get().getApplicationContext().getContentResolver().delete(uri, (String) null, (String[]) null);
    }

    /* renamed from: m */
    public static long m2447m(Uri uri) {
        StringBuilder sb;
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            AssetFileDescriptor openAssetFileDescriptor = C0967f.get().getApplicationContext().getContentResolver().openAssetFileDescriptor(uri, "r");
            if (openAssetFileDescriptor != null) {
                long statSize = openAssetFileDescriptor.getParcelFileDescriptor().getStatSize();
                try {
                    openAssetFileDescriptor.close();
                } catch (IOException e) {
                    C0632a.m1020a("MmsUtils.getMediaFileSize: failed to close ", (Object) e, "MessagingApp", (Throwable) e);
                }
                return statSize;
            } else if (openAssetFileDescriptor == null) {
                return 0;
            } else {
                try {
                    openAssetFileDescriptor.close();
                    return 0;
                } catch (IOException e2) {
                    e = e2;
                    sb = new StringBuilder();
                }
            }
            sb.append("MmsUtils.getMediaFileSize: failed to close ");
            sb.append(e);
            C1430e.m3623e("MessagingApp", sb.toString(), e);
            return 0;
        } catch (FileNotFoundException e3) {
            C1430e.m3623e("MessagingApp", "MmsUtils.getMediaFileSize: cound not find media file: " + e3, e3);
            if (assetFileDescriptor == null) {
                return 0;
            }
            try {
                assetFileDescriptor.close();
                return 0;
            } catch (IOException e4) {
                e = e4;
                sb = new StringBuilder();
            }
        } catch (Throwable th) {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException e5) {
                    C0632a.m1020a("MmsUtils.getMediaFileSize: failed to close ", (Object) e5, "MessagingApp", (Throwable) e5);
                }
            }
            throw th;
        }
    }

    /* renamed from: n */
    public static String m2448n(Uri uri) {
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        int indexOf = schemeSpecificPart.indexOf(63);
        if (indexOf != -1) {
            schemeSpecificPart = schemeSpecificPart.substring(0, indexOf);
        }
        StringBuilder sb = new StringBuilder(schemeSpecificPart.length());
        for (char c : schemeSpecificPart.toCharArray()) {
            int digit = Character.digit(c, 10);
            if (digit != -1) {
                sb.append(digit);
            } else {
                sb.append(c);
            }
        }
        return sb.toString().replace(',', ';');
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [com.android.messaging.sms.DatabaseMessages$MmsMessage, android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r8v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0098, code lost:
        if (r8 == 0) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b2, code lost:
        if (r8 == 0) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b4, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b7, code lost:
        return r9;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008a A[Catch:{ SQLiteException -> 0x009d, all -> 0x009b }, LOOP:0: B:32:0x008a->B:34:0x0090, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c2  */
    /* renamed from: o */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.sms.DatabaseMessages$MmsMessage m2449o(android.net.Uri r12) {
        /*
            java.lang.String r0 = "MessagingApp"
            android.content.ContentResolver r7 = p026b.p027a.p030b.p031a.C0632a.m1012Pk()
            r8 = 0
            java.lang.String[] r3 = com.android.messaging.sms.DatabaseMessages$MmsMessage.getProjection()     // Catch:{ SQLiteException -> 0x002e, all -> 0x002b }
            r4 = 0
            r5 = 0
            r6 = 0
            r1 = r7
            r2 = r12
            android.database.Cursor r1 = android.support.p016v4.media.session.C0107q.query(r1, r2, r3, r4, r5, r6)     // Catch:{ SQLiteException -> 0x002e, all -> 0x002b }
            if (r1 == 0) goto L_0x0023
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0021 }
            if (r2 == 0) goto L_0x0023
            com.android.messaging.sms.DatabaseMessages$MmsMessage r2 = com.android.messaging.sms.DatabaseMessages$MmsMessage.m2335l(r1)     // Catch:{ SQLiteException -> 0x0021 }
            goto L_0x0024
        L_0x0021:
            r2 = move-exception
            goto L_0x0031
        L_0x0023:
            r2 = r8
        L_0x0024:
            if (r1 == 0) goto L_0x0029
            r1.close()
        L_0x0029:
            r9 = r2
            goto L_0x004b
        L_0x002b:
            r12 = move-exception
            goto L_0x00c0
        L_0x002e:
            r1 = move-exception
            r2 = r1
            r1 = r8
        L_0x0031:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00be }
            r3.<init>()     // Catch:{ all -> 0x00be }
            java.lang.String r4 = "MmsLoader: query pdu failure: "
            r3.append(r4)     // Catch:{ all -> 0x00be }
            r3.append(r2)     // Catch:{ all -> 0x00be }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00be }
            com.android.messaging.util.C1430e.m3623e(r0, r3, r2)     // Catch:{ all -> 0x00be }
            if (r1 == 0) goto L_0x004a
            r1.close()
        L_0x004a:
            r9 = r8
        L_0x004b:
            if (r9 != 0) goto L_0x004e
            return r8
        L_0x004e:
            if (r12 == 0) goto L_0x0055
            long r1 = android.content.ContentUris.parseId(r12)     // Catch:{ NumberFormatException | UnsupportedOperationException -> 0x0055 }
            goto L_0x0057
        L_0x0055:
            r1 = -1
        L_0x0057:
            java.util.Locale r12 = java.util.Locale.US
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            java.lang.String r5 = "ct"
            r3[r4] = r5
            r10 = 1
            java.lang.String r5 = "application/smil"
            r3[r10] = r5
            r5 = 2
            java.lang.String r6 = "mid"
            r3[r5] = r6
            java.lang.String r5 = "%s != '%s' AND %s = ?"
            java.lang.String r12 = java.lang.String.format(r12, r5, r3)
            android.net.Uri r3 = f1556aF     // Catch:{ SQLiteException -> 0x009d }
            java.lang.String[] r5 = com.android.messaging.sms.DatabaseMessages$MmsPart.f1504Wu     // Catch:{ SQLiteException -> 0x009d }
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x009d }
            java.lang.String r1 = java.lang.Long.toString(r1)     // Catch:{ SQLiteException -> 0x009d }
            r6[r4] = r1     // Catch:{ SQLiteException -> 0x009d }
            r11 = 0
            r1 = r7
            r2 = r3
            r3 = r5
            r4 = r12
            r5 = r6
            r6 = r11
            android.database.Cursor r8 = android.support.p016v4.media.session.C0107q.query(r1, r2, r3, r4, r5, r6)     // Catch:{ SQLiteException -> 0x009d }
            if (r8 == 0) goto L_0x0098
        L_0x008a:
            boolean r12 = r8.moveToNext()     // Catch:{ SQLiteException -> 0x009d }
            if (r12 == 0) goto L_0x0098
            com.android.messaging.sms.DatabaseMessages$MmsPart r12 = com.android.messaging.sms.DatabaseMessages$MmsPart.m2340a(r8, r10)     // Catch:{ SQLiteException -> 0x009d }
            r9.mo6793a(r12)     // Catch:{ SQLiteException -> 0x009d }
            goto L_0x008a
        L_0x0098:
            if (r8 == 0) goto L_0x00b7
            goto L_0x00b4
        L_0x009b:
            r12 = move-exception
            goto L_0x00b8
        L_0x009d:
            r12 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009b }
            r1.<init>()     // Catch:{ all -> 0x009b }
            java.lang.String r2 = "MmsLoader: query parts failure: "
            r1.append(r2)     // Catch:{ all -> 0x009b }
            r1.append(r12)     // Catch:{ all -> 0x009b }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x009b }
            com.android.messaging.util.C1430e.m3623e(r0, r1, r12)     // Catch:{ all -> 0x009b }
            if (r8 == 0) goto L_0x00b7
        L_0x00b4:
            r8.close()
        L_0x00b7:
            return r9
        L_0x00b8:
            if (r8 == 0) goto L_0x00bd
            r8.close()
        L_0x00bd:
            throw r12
        L_0x00be:
            r12 = move-exception
            r8 = r1
        L_0x00c0:
            if (r8 == 0) goto L_0x00c5
            r8.close()
        L_0x00c5:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1029y.m2449o(android.net.Uri):com.android.messaging.sms.DatabaseMessages$MmsMessage");
    }

    /* renamed from: s */
    private static C0993w m2450s(byte[] bArr) {
        C0976f fVar;
        try {
            fVar = new C0988r(bArr, true).parse();
        } catch (RuntimeException e) {
            C1430e.m3621d("MessagingApp", "parsePduForAnyCarrier: Failed to parse PDU with content disposition", e);
            fVar = null;
        }
        if (fVar == null) {
            try {
                fVar = new C0988r(bArr, false).parse();
            } catch (RuntimeException e2) {
                C1430e.m3621d("MessagingApp", "parsePduForAnyCarrier: Failed to parse PDU without content disposition", e2);
            }
        }
        if (fVar != null && (fVar instanceof C0993w)) {
            return (C0993w) fVar;
        }
        C1430e.m3622e("MessagingApp", "receiveFromDumpFile: Parsing retrieved PDU failure");
        throw new MmsFailureException(2, "Failed reading dump file");
    }

    /* renamed from: sa */
    public static byte[] m2451sa(String str) {
        byte[] bArr = null;
        try {
            Context applicationContext = C0967f.get().getApplicationContext();
            byte[] Fa = C1410N.m3544Fa(str);
            C0993w s = m2450s(Fa);
            C0978h hVar = new C0978h();
            hVar.mo6685m(str.getBytes());
            hVar.mo6673wa(s.mo6669_h());
            hVar.mo6670b(s.getFrom());
            hVar.mo6680c(s.getSubject());
            hVar.mo6686t((System.currentTimeMillis() / 1000) + 600);
            hVar.mo6687u((long) Fa.length);
            hVar.mo6684l(s.getMessageClass());
            Uri.Builder Ta = MediaScratchFileProvider.m1255Ta();
            Ta.appendPath(str);
            hVar.mo6681e(Ta.build().toString().getBytes());
            bArr = new C0985o(applicationContext, hVar).mo6702di();
            if (bArr != null && bArr.length >= 1) {
                return bArr;
            }
            throw new IllegalArgumentException("Empty or zero length PDU data");
        } catch (InvalidHeaderValueException | MmsFailureException unused) {
        }
    }

    /* renamed from: y */
    public static int m2452y(long j) {
        ContentResolver Pk = C0632a.m1012Pk();
        String format = String.format(Locale.US, "%s AND (%s<=%d)", new Object[]{m2409Hi(), "date", Long.valueOf(j)});
        return Pk.delete(Telephony.Mms.CONTENT_URI, String.format(Locale.US, "%s AND (%s<=%d)", new Object[]{f1548TE, "date", Long.valueOf(j / 1000)}), (String[]) null) + Pk.delete(Telephony.Sms.CONTENT_URI, format, (String[]) null) + 0;
    }

    /* renamed from: z */
    public static String m2453z(long j) {
        Cursor query;
        if (j > 0 && (query = C0632a.m1012Pk().query(f1549UE, f1550VE, "_id=?", new String[]{String.valueOf(j)}, (String) null)) != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getString(1);
                }
                query.close();
            } finally {
                query.close();
            }
        }
        return null;
    }

    /* renamed from: b */
    public static String m2438b(Resources resources, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (f1546RE == null) {
            f1546RE = resources.getStringArray(R.array.empty_subject_strings);
        }
        for (String equalsIgnoreCase : f1546RE) {
            if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                return null;
            }
        }
        return str;
    }

    /* renamed from: a */
    private static int m2415a(Context context, MessagePartData messagePartData, int i) {
        C1424b.m3594t(context);
        C1424b.m3594t(messagePartData);
        C1424b.m3592ia(C1481w.m3831za(messagePartData.getContentType()) || C1481w.m3830Ea(messagePartData.getContentType()));
        C1431ea eaVar = new C1431ea();
        try {
            eaVar.mo8063v(messagePartData.getContentUri());
            return eaVar.mo8058H(9, i);
        } catch (IOException e) {
            C1430e.m3626i("MessagingApp", "Error extracting duration from " + messagePartData.getContentUri(), e);
            return i;
        } finally {
            eaVar.release();
        }
    }

    /* renamed from: b */
    public static void m2439b(long j, long j2) {
        ContentResolver Pk = C0632a.m1012Pk();
        ContentValues contentValues = new ContentValues();
        contentValues.put("read", 1);
        contentValues.put("seen", 1);
        Pk.update(Telephony.Sms.CONTENT_URI, contentValues, String.format(Locale.US, "%s=%d AND %s<=%d AND %s=0", new Object[]{"thread_id", Long.valueOf(j), "date", Long.valueOf(j2), "read"}), (String[]) null);
        Pk.update(Telephony.Mms.CONTENT_URI, contentValues, String.format(Locale.US, "%s=%d AND %s<=%d AND %s=0", new Object[]{"thread_id", Long.valueOf(j), "date", Long.valueOf(j2 / 1000), "read"}), (String[]) null);
    }

    /* renamed from: a */
    private static void m2435a(C0989s sVar, String str) {
        sVar.mo6726e(str.getBytes());
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf != -1) {
            str = str.substring(0, lastIndexOf);
        }
        sVar.mo6725d(str.getBytes());
    }

    /* renamed from: a */
    private static void m2434a(C0980j jVar, String str, Uri uri, String str2) {
        C0989s sVar = new C0989s();
        sVar.mo6735h(uri);
        sVar.mo6728g(str2.getBytes());
        m2435a(sVar, str);
        jVar.mo6689a(sVar);
    }

    /* renamed from: a */
    private static int m2416a(Context context, C0980j jVar, MessagePartData messagePartData, String str) {
        Uri contentUri = messagePartData.getContentUri();
        String contentType = messagePartData.getContentType();
        if (Log.isLoggable("MessagingApp", 2)) {
            StringBuilder Pa = C0632a.m1011Pa("addPart attachmentUrl: ");
            Pa.append(contentUri.toString());
            C1430e.m3628v("MessagingApp", Pa.toString());
        }
        int m = (int) m2447m(contentUri);
        m2434a(jVar, str, contentUri, contentType);
        return m;
    }

    /* renamed from: a */
    public static long m2417a(Context context, List list) {
        if (!(list == null || list.size() == 0)) {
            try {
                return C1026v.getOrCreateThreadId(context, (Set) new HashSet(list));
            } catch (IllegalArgumentException e) {
                C1430e.m3622e("MessagingApp", "MmsUtils: getting thread id failed: " + e);
            }
        }
        return -1;
    }

    /* renamed from: a */
    private static Uri m2419a(ContentResolver contentResolver, Uri uri, int i, String str, String str2, String str3, Long l, boolean z, boolean z2, int i2, int i3, long j) {
        ContentValues contentValues = new ContentValues(7);
        contentValues.put("address", str);
        if (l != null) {
            contentValues.put("date", l);
        }
        contentValues.put("read", Integer.valueOf(z ? 1 : 0));
        contentValues.put("seen", Integer.valueOf(z2 ? 1 : 0));
        contentValues.put("subject", str3);
        contentValues.put("body", str2);
        if (C1464na.m3759Zj()) {
            contentValues.put("sub_id", Integer.valueOf(i));
        }
        if (i2 != -1) {
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i2));
        }
        if (i3 != 0) {
            contentValues.put("type", Integer.valueOf(i3));
        }
        if (j != -1) {
            contentValues.put("thread_id", Long.valueOf(j));
        }
        return contentResolver.insert(uri, contentValues);
    }

    /* renamed from: a */
    public static Uri m2420a(Context context, Uri uri, int i, String str, String str2, long j, int i2, int i3, long j2) {
        Uri uri2 = null;
        try {
            uri2 = m2419a(context.getContentResolver(), uri, i, str, str2, (String) null, Long.valueOf(j), true, true, i2, i3, j2);
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "Mmsutils: Inserted SMS message into telephony (type = " + i3 + "), uri: " + uri2);
            }
        } catch (SQLiteException e) {
            C0632a.m1020a("MmsUtils: persist sms message failure ", (Object) e, "MessagingApp", (Throwable) e);
        } catch (IllegalArgumentException e2) {
            C0632a.m1020a("MmsUtils: persist sms message failure ", (Object) e2, "MessagingApp", (Throwable) e2);
        }
        return uri2;
    }

    /* renamed from: a */
    public static boolean m2436a(Context context, Uri uri, int i, long j) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            long j2 = j / 1000;
            contentValues.put("msg_box", Integer.valueOf(i));
            contentValues.put("date", Long.valueOf(j2));
            if (contentResolver.update(uri, contentValues, (String) null, (String[]) null) != 1) {
                return false;
            }
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "Mmsutils: Updated sending MMS " + uri + "; box = " + i + ", date = " + j2 + " (secs since epoch)");
            }
            return true;
        } catch (SQLiteException e) {
            C0632a.m1020a("MmsUtils: update mms message failure ", (Object) e, "MessagingApp", (Throwable) e);
            return false;
        } catch (IllegalArgumentException e2) {
            C0632a.m1020a("MmsUtils: update mms message failure ", (Object) e2, "MessagingApp", (Throwable) e2);
            return false;
        }
    }

    /* renamed from: a */
    public static ContentValues m2418a(SmsMessage[] smsMessageArr, int i) {
        String str;
        SmsMessage smsMessage = smsMessageArr[0];
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", smsMessage.getDisplayOriginatingAddress());
        if (smsMessageArr.length == 1) {
            str = m2445ib(smsMessageArr[0].getDisplayMessageBody());
        } else {
            StringBuilder sb = new StringBuilder();
            for (SmsMessage displayMessageBody : smsMessageArr) {
                try {
                    sb.append(displayMessageBody.getDisplayMessageBody());
                } catch (NullPointerException unused) {
                }
            }
            str = m2445ib(sb.toString());
        }
        contentValues.put("body", str);
        if (m2410Ii()) {
            contentValues.put("date_sent", Long.valueOf(smsMessage.getTimestampMillis()));
        }
        contentValues.put("protocol", Integer.valueOf(smsMessage.getProtocolIdentifier()));
        if (smsMessage.getPseudoSubject().length() > 0) {
            contentValues.put("subject", smsMessage.getPseudoSubject());
        }
        contentValues.put("reply_path_present", Integer.valueOf(smsMessage.isReplyPathPresent() ? 1 : 0));
        contentValues.put("service_center", smsMessage.getServiceCenterAddress());
        contentValues.put("error_code", Integer.valueOf(i));
        return contentValues;
    }

    /* renamed from: a */
    public static SmsMessage m2422a(Intent intent) {
        return SmsMessage.createFromPdu(intent.getByteArrayExtra("pdu"), intent.getStringExtra("format"));
    }

    /* renamed from: a */
    public static void m2433a(Uri uri, int i, long j) {
        if (uri != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i));
            if (m2410Ii()) {
                contentValues.put("date_sent", Long.valueOf(j));
            }
            C0967f.get().getApplicationContext().getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        }
    }

    /* renamed from: a */
    public static String m2431a(List list, String str) {
        Context applicationContext = C0967f.get().getApplicationContext();
        if (list != null && list.size() == 1) {
            return (String) list.get(0);
        }
        Cursor query = C0107q.query(applicationContext.getContentResolver(), Uri.withAppendedPath(Uri.parse(str), "addr"), new String[]{"address", "charset"}, "type=137", (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        try {
            if (query.moveToFirst()) {
                return C0107q.m125a(C0107q.m132c(query.getString(C1019o.f1538AE), 4), query.getInt(C1019o.f1539EE));
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    /* renamed from: a */
    public static MessageData m2424a(DatabaseMessages$MmsMessage databaseMessages$MmsMessage, String str, String str2, String str3, int i) {
        MessagePartData messagePartData;
        DatabaseMessages$MmsMessage databaseMessages$MmsMessage2 = databaseMessages$MmsMessage;
        C1424b.m3594t(databaseMessages$MmsMessage);
        MessageData a = MessageData.m1755a(databaseMessages$MmsMessage.getUri(), str2, str3, str, databaseMessages$MmsMessage2.f1493bE == 130, i, databaseMessages$MmsMessage2.f1489_D, databaseMessages$MmsMessage2.f1492aE, databaseMessages$MmsMessage2.mPriority, databaseMessages$MmsMessage2.f1499tA, databaseMessages$MmsMessage2.f1485BA, databaseMessages$MmsMessage2.f1490_b, databaseMessages$MmsMessage.getSize(), i < 100 ? databaseMessages$MmsMessage2.f1497fE : databaseMessages$MmsMessage2.f1496eE, databaseMessages$MmsMessage2.f1494cE, databaseMessages$MmsMessage2.f1488ZD, databaseMessages$MmsMessage2.f1487YD);
        for (DatabaseMessages$MmsPart databaseMessages$MmsPart : databaseMessages$MmsMessage2.f1491_l) {
            if (databaseMessages$MmsPart.isText()) {
                C1449g.get().getInt("bugle_mms_text_limit", 2000);
                String str4 = databaseMessages$MmsPart.mText;
                if (str4 != null && str4.length() > 2000) {
                    str4 = str4.substring(0, 2000);
                }
                messagePartData = MessagePartData.m1808fa(str4);
            } else {
                messagePartData = databaseMessages$MmsPart.isMedia() ? MessagePartData.m1806a(databaseMessages$MmsPart.mContentType, databaseMessages$MmsPart.getDataUri(), -1, -1) : null;
            }
            if (messagePartData != null) {
                a.mo6267g(messagePartData);
            }
        }
        if (!a.getParts().iterator().hasNext()) {
            a.mo6267g(MessagePartData.m1805Zg());
        }
        return a;
    }

    /* renamed from: a */
    public static C1028x m2428a(Context context, Uri uri, int i, String str, String str2, String str3, boolean z, long j, long j2, Bundle bundle) {
        Uri uri2 = uri;
        int i2 = i;
        String str4 = str3;
        if (TextUtils.isEmpty(str3)) {
            C1430e.m3622e("MessagingApp", "MmsUtils: Download from empty content location URL");
            return new C1028x(3, 0, (Uri) null);
        }
        int i3 = 2;
        if (!m2407Gb(i)) {
            C1430e.m3622e("MessagingApp", "MmsUtils: failed to download message, no data available");
            return new C1028x(2, 0, (Uri) null, 8);
        }
        try {
            C1410N.m3547Nj();
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "MmsUtils: Downloading MMS via MMS lib API; notification message: " + uri);
            }
            if (!C1464na.m3759Zj()) {
                C1424b.m3592ia(i2 == -1);
            } else if (i2 < 0) {
                C1430e.m3622e("MessagingApp", "MmsUtils: Incoming MMS came from unknown SIM");
                throw new MmsFailureException(3, "Message from unknown SIM");
            }
            Bundle bundle2 = bundle == null ? new Bundle() : bundle;
            bundle2.putParcelable("notification_uri", uri);
            bundle2.putInt("sub_id", i);
            String str5 = str;
            bundle2.putString("sub_phone_number", str);
            bundle2.putString("transaction_id", str2);
            bundle2.putString("content_location", str4);
            bundle2.putBoolean("auto_download", z);
            bundle2.putLong("received_timestamp", j);
            bundle2.putLong("expiry", j2);
            Context context2 = context;
            C1025u.m2395a(context, i, str4, bundle2);
            return STATUS_PENDING;
        } catch (MmsFailureException e) {
            C0632a.m1020a("MmsUtils: failed to download message ", (Object) uri, "MessagingApp", (Throwable) e);
            i3 = e.retryHint;
            return new C1028x(i3, 0, (Uri) null);
        } catch (InvalidHeaderValueException e2) {
            C0632a.m1020a("MmsUtils: failed to download message ", (Object) uri, "MessagingApp", (Throwable) e2);
            return new C1028x(i3, 0, (Uri) null);
        }
    }

    /* renamed from: a */
    public static C1028x m2429a(Context context, Uri uri, int i, String str, String str2, String str3, boolean z, long j, long j2, C0993w wVar) {
        int i2;
        Uri uri2;
        Context context2 = context;
        int i3 = i;
        String str4 = str2;
        String str5 = str3;
        byte[] h = m2443h(str4, "UTF-8");
        int ai = wVar.mo6746ai();
        if (ai == 128) {
            i2 = 0;
        } else if (ai < 192 || ai >= 224) {
            C1430e.m3622e("MessagingApp", "MmsUtils: failed to retrieve message; retrieveStatus: " + ai);
            i2 = 3;
        } else {
            i2 = 1;
        }
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("retr_st", Integer.valueOf(wVar.mo6746ai()));
        Uri uri3 = null;
        Uri uri4 = uri;
        C0107q.m122a(context.getContentResolver(), uri, contentValues, (String) null, (String[]) null);
        if (i2 == 0) {
            if (z) {
                m2432a(context, i3, h, str5, 129);
            } else {
                byte[] transactionId = wVar.getTransactionId();
                try {
                    if (Log.isLoggable("MessagingApp", 3)) {
                        C1430e.m3620d("MessagingApp", "MmsUtils: Sending M-Acknowledge.ind for received MMS");
                    }
                    if (str5 == null) {
                        C1430e.m3630w("MessagingApp", "MmsUtils: Can't send AckInd; contentLocation is null");
                    } else if (transactionId == null) {
                        C1430e.m3630w("MessagingApp", "MmsUtils: Can't send AckInd; transaction id is null");
                    } else if (!m2407Gb(i)) {
                        C1430e.m3630w("MessagingApp", "MmsUtils: Can't send AckInd; no data available");
                    } else {
                        C1025u.m2396a(context, i3, transactionId, str5);
                    }
                } catch (MmsFailureException e) {
                    C0632a.m1020a("sendAcknowledgeForMmsDownload: failed to retrieve message ", (Object) e, "MessagingApp", (Throwable) e);
                } catch (InvalidHeaderValueException e2) {
                    C0632a.m1020a("sendAcknowledgeForMmsDownload: failed to retrieve message ", (Object) e2, "MessagingApp", (Throwable) e2);
                }
            }
            try {
                uri2 = C0990t.m2289o(context).mo6743a(wVar, Telephony.Mms.Inbox.CONTENT_URI, i, str, (Map) null);
                try {
                    ContentValues contentValues2 = new ContentValues(3);
                    contentValues2.put("date", Long.valueOf(j));
                    contentValues2.put("tr_id", str4);
                    contentValues2.put("exp", Long.valueOf(j2));
                    C0107q.m122a(context.getContentResolver(), uri2, contentValues2, (String) null, (String[]) null);
                    if (Log.isLoggable("MessagingApp", 3)) {
                        C1430e.m3620d("MessagingApp", "MmsUtils: Inserted MMS message into telephony, uri: " + uri2);
                    }
                } catch (MmsException e3) {
                    e = e3;
                    C0632a.m1020a("MmsUtils: persist mms received message failure ", (Object) e, "MessagingApp", (Throwable) e);
                    uri3 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ContentUris.parseId(uri2));
                    return new C1028x(i2, ai, uri3);
                } catch (SQLiteException e4) {
                    e = e4;
                    C0632a.m1020a("MmsUtils: update mms received message failure ", (Object) e, "MessagingApp", (Throwable) e);
                    uri3 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ContentUris.parseId(uri2));
                    return new C1028x(i2, ai, uri3);
                }
            } catch (MmsException e5) {
                e = e5;
                uri2 = null;
                C0632a.m1020a("MmsUtils: persist mms received message failure ", (Object) e, "MessagingApp", (Throwable) e);
                uri3 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ContentUris.parseId(uri2));
                return new C1028x(i2, ai, uri3);
            } catch (SQLiteException e6) {
                e = e6;
                uri2 = null;
                C0632a.m1020a("MmsUtils: update mms received message failure ", (Object) e, "MessagingApp", (Throwable) e);
                uri3 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ContentUris.parseId(uri2));
                return new C1028x(i2, ai, uri3);
            }
            uri3 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ContentUris.parseId(uri2));
        } else if (i2 != 1 && i2 == 2 && z) {
            m2432a(context, i3, h, str5, 131);
        }
        return new C1028x(i2, ai, uri3);
    }

    /* renamed from: a */
    public static C1028x m2427a(Context context, int i, Uri uri, Bundle bundle) {
        int i2 = 2;
        int i3 = 0;
        if (!m2407Gb(i)) {
            C1430e.m3630w("MessagingApp", "MmsUtils: failed to send message, no data available");
            return new C1028x(2, 0, uri, 8);
        }
        try {
            C0995y yVar = (C0995y) C0990t.m2289o(context).mo6745i(uri);
            if (yVar == null) {
                C1430e.m3630w("MessagingApp", "MmsUtils: Sending MMS was deleted; uri = " + uri);
                return new C1028x(3, 0, uri);
            }
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", String.format("MmsUtils: Sending MMS, message uri: %s", new Object[]{uri}));
            }
            bundle.putInt("sub_id", i);
            C1025u.m2393a(context, i, uri, yVar, bundle);
            return STATUS_PENDING;
        } catch (MmsFailureException e) {
            i2 = e.retryHint;
            i3 = e.rawStatus;
            C0632a.m1020a("MmsUtils: failed to send message ", (Object) e, "MessagingApp", (Throwable) e);
            return new C1028x(i2, i3, uri);
        } catch (InvalidHeaderValueException e2) {
            C0632a.m1020a("MmsUtils: failed to send message ", (Object) e2, "MessagingApp", (Throwable) e2);
            return new C1028x(i2, i3, uri);
        } catch (IllegalArgumentException e3) {
            C0632a.m1020a("MmsUtils: invalid message to send ", (Object) e3, "MessagingApp", (Throwable) e3);
            return new C1028x(i2, i3, uri);
        } catch (MmsException e4) {
            C0632a.m1020a("MmsUtils: failed to send message ", (Object) e4, "MessagingApp", (Throwable) e4);
            return new C1028x(i2, i3, uri);
        }
    }

    /* renamed from: a */
    public static C1028x m2430a(Context context, Uri uri, C0994x xVar) {
        int hc = xVar.mo6751hc();
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("resp_st", Integer.valueOf(hc));
        byte[] messageId = xVar.getMessageId();
        if (messageId != null && messageId.length > 0) {
            contentValues.put("m_id", C0990t.m2290o(messageId));
        }
        C0107q.m122a(context.getContentResolver(), uri, contentValues, (String) null, (String[]) null);
        int i = 1;
        if (hc == 128) {
            i = 0;
        } else if (!(hc == 192 || hc == 195 || hc == 196)) {
            StringBuilder Pa = C0632a.m1011Pa("MmsUtils: failed to send message; respStatus = ");
            Pa.append(String.format("0x%X", new Object[]{Integer.valueOf(hc)}));
            C1430e.m3622e("MessagingApp", Pa.toString());
            i = 2;
        }
        return new C1028x(i, hc, uri);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x011a A[SYNTHETIC, Splitter:B:49:0x011a] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.sms.DatabaseMessages$MmsMessage m2426a(android.content.Context r17, byte[] r18, int r19, java.lang.String r20) {
        /*
            com.android.messaging.mmslib.a.r r0 = new com.android.messaging.mmslib.a.r
            com.android.messaging.sms.t r1 = com.android.messaging.sms.C1024t.get(r19)
            boolean r1 = r1.mo6854zi()
            r2 = r18
            r0.<init>(r2, r1)
            com.android.messaging.mmslib.a.f r3 = r0.parse()
            r1 = 0
            java.lang.String r8 = "MessagingApp"
            if (r3 != 0) goto L_0x001e
            java.lang.String r0 = "Invalid PUSH data"
            com.android.messaging.util.C1430e.m3622e(r8, r0)
            return r1
        L_0x001e:
            com.android.messaging.mmslib.a.t r2 = com.android.messaging.mmslib.p039a.C0990t.m2289o((android.content.Context) r17)
            int r9 = r3.getMessageType()
            r0 = 130(0x82, float:1.82E-43)
            if (r9 == r0) goto L_0x005e
            r0 = 134(0x86, float:1.88E-43)
            if (r9 == r0) goto L_0x0048
            r0 = 136(0x88, float:1.9E-43)
            if (r9 == r0) goto L_0x0048
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Received unrecognized WAP Push, type="
            r0.append(r2)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3622e(r8, r0)
            goto L_0x0178
        L_0x0048:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Received unsupported WAP Push, type="
            r0.append(r2)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3630w(r8, r0)
            goto L_0x0178
        L_0x005e:
            r4 = r3
            com.android.messaging.mmslib.a.h r4 = (com.android.messaging.mmslib.p039a.C0978h) r4
            com.android.messaging.sms.t r5 = com.android.messaging.sms.C1024t.get(r19)
            boolean r5 = r5.mo6832Ai()
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x008e
            byte[] r5 = r4.mo6683jc()
            r10 = 61
            int r11 = r5.length
            int r11 = r11 - r6
            byte r11 = r5[r11]
            if (r10 != r11) goto L_0x008e
            byte[] r10 = r4.getTransactionId()
            int r11 = r5.length
            int r12 = r10.length
            int r11 = r11 + r12
            byte[] r11 = new byte[r11]
            int r12 = r5.length
            java.lang.System.arraycopy(r5, r7, r11, r7, r12)
            int r5 = r5.length
            int r12 = r10.length
            java.lang.System.arraycopy(r10, r7, r11, r5, r12)
            r4.mo6681e(r11)
        L_0x008e:
            byte[] r5 = r4.getTransactionId()
            r10 = 2
            if (r5 == 0) goto L_0x0117
            java.lang.String r14 = "((m_type=?) OR (m_type=?)) AND (exp>?) AND (tr_id=?)"
            long r11 = java.lang.System.currentTimeMillis()
            r15 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 / r15
            r13 = 4
            java.lang.String[] r15 = new java.lang.String[r13]
            java.lang.String r0 = java.lang.Integer.toString(r0)
            r15[r7] = r0
            r0 = 132(0x84, float:1.85E-43)
            java.lang.String r0 = java.lang.Integer.toString(r0)
            r15[r6] = r0
            java.lang.String r0 = java.lang.Long.toString(r11)
            r15[r10] = r0
            r0 = 3
            java.lang.String r6 = new java.lang.String
            r6.<init>(r5)
            r15[r0] = r6
            android.content.ContentResolver r11 = r17.getContentResolver()     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f5 }
            android.net.Uri r12 = android.provider.Telephony.Mms.CONTENT_URI     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f5 }
            java.lang.String r0 = "_id"
            java.lang.String[] r13 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f5 }
            r16 = 0
            android.database.Cursor r5 = android.support.p016v4.media.session.C0107q.query(r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x00f7, all -> 0x00f5 }
            int r0 = r5.getCount()     // Catch:{ SQLiteException -> 0x00f3 }
            if (r0 <= 0) goto L_0x010d
            r6 = 32
            if (r0 >= r6) goto L_0x00da
            goto L_0x00db
        L_0x00da:
            r0 = r6
        L_0x00db:
            java.lang.String[] r6 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00f3 }
            r11 = r7
        L_0x00de:
            boolean r12 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x00f3 }
            if (r12 == 0) goto L_0x00ef
            if (r11 >= r0) goto L_0x00ef
            java.lang.String r12 = r5.getString(r7)     // Catch:{ SQLiteException -> 0x00f3 }
            r6[r11] = r12     // Catch:{ SQLiteException -> 0x00f3 }
            int r11 = r11 + 1
            goto L_0x00de
        L_0x00ef:
            r5.close()
            goto L_0x0118
        L_0x00f3:
            r0 = move-exception
            goto L_0x00f9
        L_0x00f5:
            r0 = move-exception
            goto L_0x0113
        L_0x00f7:
            r0 = move-exception
            r5 = r1
        L_0x00f9:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
            r6.<init>()     // Catch:{ all -> 0x0111 }
            java.lang.String r7 = "query failure: "
            r6.append(r7)     // Catch:{ all -> 0x0111 }
            r6.append(r0)     // Catch:{ all -> 0x0111 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0111 }
            com.android.messaging.util.C1430e.m3623e(r8, r6, r0)     // Catch:{ all -> 0x0111 }
        L_0x010d:
            r5.close()
            goto L_0x0117
        L_0x0111:
            r0 = move-exception
            r1 = r5
        L_0x0113:
            r1.close()
            throw r0
        L_0x0117:
            r6 = r1
        L_0x0118:
            if (r6 != 0) goto L_0x0146
            android.net.Uri r4 = android.provider.Telephony.Mms.Inbox.CONTENT_URI     // Catch:{ MmsException -> 0x0130 }
            r7 = 0
            r5 = r19
            r6 = r20
            android.net.Uri r0 = r2.mo6743a(r3, r4, r5, r6, r7)     // Catch:{ MmsException -> 0x0130 }
            android.net.Uri r2 = android.provider.Telephony.Mms.CONTENT_URI     // Catch:{ MmsException -> 0x0130 }
            long r3 = android.content.ContentUris.parseId(r0)     // Catch:{ MmsException -> 0x0130 }
            android.net.Uri r0 = android.content.ContentUris.withAppendedId(r2, r3)     // Catch:{ MmsException -> 0x0130 }
            goto L_0x0179
        L_0x0130:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to save the data from PUSH: type="
            r2.append(r3)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            com.android.messaging.util.C1430e.m3623e(r8, r2, r0)
            goto L_0x0178
        L_0x0146:
            java.lang.String r0 = "Received WAP Push is a dup: "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            r2 = 44
            com.google.common.base.A r2 = com.google.common.base.C1504A.m3945e(r2)
            java.lang.String r2 = r2.mo8516c(r6)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3630w(r8, r0)
            boolean r0 = android.util.Log.isLoggable(r8, r10)
            if (r0 == 0) goto L_0x0178
            java.lang.String r0 = "Dup Transaction Id="
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r2 = new java.lang.String
            byte[] r3 = r4.getTransactionId()
            r2.<init>(r3)
            p026b.p027a.p030b.p031a.C0632a.m1021a((java.lang.StringBuilder) r0, (java.lang.String) r2, (java.lang.String) r8)
        L_0x0178:
            r0 = r1
        L_0x0179:
            if (r0 == 0) goto L_0x017f
            com.android.messaging.sms.DatabaseMessages$MmsMessage r1 = m2449o(r0)
        L_0x017f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1029y.m2426a(android.content.Context, byte[], int, java.lang.String):com.android.messaging.sms.DatabaseMessages$MmsMessage");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.String[]} */
    /* JADX WARNING: type inference failed for: r15v4, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e5  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri m2421a(android.content.Context r16, java.util.List r17, com.android.messaging.datamodel.data.MessageData r18, int r19, java.lang.String r20, long r21) {
        /*
            java.lang.String r1 = "MessagingApp"
            int r0 = r17.size()
            java.lang.String[] r0 = new java.lang.String[r0]
            r2 = r17
            java.lang.Object[] r0 = r2.toArray(r0)
            java.lang.String[] r0 = (java.lang.String[]) r0
            com.android.messaging.util.C1424b.m3594t(r16)
            if (r0 == 0) goto L_0x00eb
            int r2 = r0.length
            r3 = 1
            if (r2 < r3) goto L_0x00eb
            int r2 = r0.length
            java.lang.String[] r6 = new java.lang.String[r2]
            r2 = 0
            r4 = r2
        L_0x001e:
            int r5 = r0.length
            r15 = 0
            if (r4 >= r5) goto L_0x0063
            r5 = r0[r4]
            r7 = r0[r4]
            boolean r7 = com.android.messaging.util.C1411O.m3555Ga(r7)
            if (r7 == 0) goto L_0x002f
            r6[r4] = r5
            goto L_0x0060
        L_0x002f:
            if (r5 != 0) goto L_0x0032
            goto L_0x005e
        L_0x0032:
            int r7 = r5.length()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r7)
            r9 = r2
        L_0x003c:
            if (r9 >= r7) goto L_0x005a
            char r10 = r5.charAt(r9)
            boolean r11 = java.lang.Character.isLetterOrDigit(r10)
            if (r11 != 0) goto L_0x0054
            r11 = 43
            if (r10 == r11) goto L_0x0054
            r11 = 42
            if (r10 == r11) goto L_0x0054
            r11 = 35
            if (r10 != r11) goto L_0x0057
        L_0x0054:
            r8.append(r10)
        L_0x0057:
            int r9 = r9 + 1
            goto L_0x003c
        L_0x005a:
            java.lang.String r15 = r8.toString()
        L_0x005e:
            r6[r4] = r15
        L_0x0060:
            int r4 = r4 + 1
            goto L_0x001e
        L_0x0063:
            r8 = 0
            r9 = 0
            r10 = 604800(0x93a80, double:2.98811E-318)
            r12 = 129(0x81, float:1.81E-43)
            r4 = r16
            r5 = r19
            r7 = r18
            r13 = r21
            com.android.messaging.mmslib.a.y r0 = m2425a((android.content.Context) r4, (int) r5, (java.lang.String[]) r6, (com.android.messaging.datamodel.data.MessageData) r7, (boolean) r8, (boolean) r9, (long) r10, (int) r12, (long) r13)     // Catch:{ InvalidHeaderValueException -> 0x007e, OutOfMemoryError -> 0x0078 }
            r5 = r0
            goto L_0x0084
        L_0x0078:
            java.lang.String r0 = "Out of memory error creating sendReq PDU"
            com.android.messaging.util.C1430e.m3622e(r1, r0)
            goto L_0x0083
        L_0x007e:
            java.lang.String r0 = "InvalidHeaderValue creating sendReq PDU"
            com.android.messaging.util.C1430e.m3622e(r1, r0)
        L_0x0083:
            r5 = r15
        L_0x0084:
            if (r5 == 0) goto L_0x00ea
            com.android.messaging.mmslib.a.t r4 = com.android.messaging.mmslib.p039a.C0990t.m2289o((android.content.Context) r16)
            android.net.Uri r6 = android.provider.Telephony.Mms.Sent.CONTENT_URI     // Catch:{ MmsException -> 0x00b6 }
            r9 = 0
            r7 = r19
            r8 = r20
            android.net.Uri r2 = r4.mo6743a(r5, r6, r7, r8, r9)     // Catch:{ MmsException -> 0x00b6 }
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ MmsException -> 0x00b4 }
            r0.<init>(r3)     // Catch:{ MmsException -> 0x00b4 }
            java.lang.String r4 = "read"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)     // Catch:{ MmsException -> 0x00b4 }
            r0.put(r4, r5)     // Catch:{ MmsException -> 0x00b4 }
            java.lang.String r4 = "seen"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ MmsException -> 0x00b4 }
            r0.put(r4, r3)     // Catch:{ MmsException -> 0x00b4 }
            android.content.ContentResolver r3 = r16.getContentResolver()     // Catch:{ MmsException -> 0x00b4 }
            android.support.p016v4.media.session.C0107q.m122a((android.content.ContentResolver) r3, (android.net.Uri) r2, (android.content.ContentValues) r0, (java.lang.String) r15, (java.lang.String[]) r15)     // Catch:{ MmsException -> 0x00b4 }
            goto L_0x00bd
        L_0x00b4:
            r0 = move-exception
            goto L_0x00b8
        L_0x00b6:
            r0 = move-exception
            r2 = r15
        L_0x00b8:
            java.lang.String r3 = "MmsUtils: persist mms sent message failure "
            p026b.p027a.p030b.p031a.C0632a.m1020a((java.lang.String) r3, (java.lang.Object) r0, (java.lang.String) r1, (java.lang.Throwable) r0)
        L_0x00bd:
            if (r2 == 0) goto L_0x00e5
            android.net.Uri r0 = android.provider.Telephony.Mms.CONTENT_URI
            long r3 = android.content.ContentUris.parseId(r2)
            android.net.Uri r15 = android.content.ContentUris.withAppendedId(r0, r3)
            r0 = 3
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            if (r0 == 0) goto L_0x00ea
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "Mmsutils: Inserted sending MMS message into telephony, uri: "
            r0.append(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3620d(r1, r0)
            goto L_0x00ea
        L_0x00e5:
            java.lang.String r0 = "insertSendingMmsMessage: failed to persist message into telephony"
            com.android.messaging.util.C1430e.m3622e(r1, r0)
        L_0x00ea:
            return r15
        L_0x00eb:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "MMS sendReq no recipient"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1029y.m2421a(android.content.Context, java.util.List, com.android.messaging.datamodel.data.MessageData, int, java.lang.String, long):android.net.Uri");
    }

    /* renamed from: a */
    public static MessageData m2423a(Uri uri, String str, String str2, String str3) {
        DatabaseMessages$MmsMessage o;
        if (uri == null || (o = m2449o(uri)) == null) {
            return null;
        }
        return m2424a(o, str, str2, str3, 6);
    }

    /* renamed from: a */
    static C0995y m2425a(Context context, int i, String[] strArr, MessageData messageData, boolean z, boolean z2, long j, int i2, long j2) {
        int i3;
        int i4;
        int i5;
        C0995y yVar;
        StringBuilder sb;
        MessagePartData messagePartData;
        Context context2;
        int i6;
        int i7;
        String format;
        int i8;
        int i9;
        MessagePartData messagePartData2;
        StringBuilder sb2;
        int i10;
        String str;
        C0989s sVar;
        Context context3 = context;
        C0995y yVar2 = new C0995y();
        String la = C1474sa.get(i).mo8229la(true);
        if (!TextUtils.isEmpty(la)) {
            yVar2.mo6670b(new C0975e(106, la));
        }
        C0975e[] a = C0975e.m2211a(strArr);
        if (a != null) {
            yVar2.mo6752a(a);
        }
        if (!TextUtils.isEmpty(messageData.mo6279mg())) {
            yVar2.mo6675c(new C0975e(106, messageData.mo6279mg()));
        }
        yVar2.setDate(j2 / 1000);
        C0980j jVar = new C0980j();
        boolean z3 = false;
        int i11 = 0;
        int i12 = 0;
        for (MessagePartData messagePartData3 : messageData.getParts()) {
            if (messagePartData3.mo6300dh()) {
                String contentType = messagePartData3.getContentType();
                if (C1481w.isImageType(contentType)) {
                    i11++;
                } else if (C1481w.m3829Da(contentType)) {
                    i12 += m2444i(context3, messagePartData3.getContentUri());
                } else {
                    i12 = (int) (m2447m(messagePartData3.getContentUri()) + ((long) i12));
                }
            }
        }
        long j3 = (long) (i11 * 16384);
        int maxMessageSize = (C1024t.get(i).getMaxMessageSize() - i12) - 1024;
        double d = 1.0d;
        if (j3 > 0) {
            d = Math.max(1.0d, ((double) maxMessageSize) / ((double) j3));
        }
        int i13 = (int) (d * 16384.0d);
        int oi = C1024t.get(i).mo6843oi();
        int mi = C1024t.get(i).mo6842mi();
        StringBuilder sb3 = new StringBuilder();
        char c = 0;
        int i14 = 0;
        boolean z4 = false;
        int i15 = 0;
        boolean z5 = false;
        Context context4 = context3;
        for (MessagePartData messagePartData4 : messageData.getParts()) {
            if (messagePartData4.mo6300dh()) {
                String contentType2 = messagePartData4.getContentType();
                String extensionFromMimeType = C1481w.getExtensionFromMimeType(contentType2);
                yVar = yVar2;
                if (C1481w.isImageType(contentType2)) {
                    String str2 = "image/gif";
                    if (extensionFromMimeType != null) {
                        Object[] objArr = new Object[2];
                        objArr[c] = Integer.valueOf(i14);
                        objArr[1] = extensionFromMimeType;
                        format = String.format("image%06d.%s", objArr);
                        i7 = 1;
                    } else {
                        boolean b = C1416U.m3569b(contentType2, messagePartData4.getContentUri());
                        if (b) {
                            contentType2 = str2;
                        }
                        String str3 = b ? "image%06d.gif" : "image%06d.jpg";
                        i7 = 1;
                        Object[] objArr2 = new Object[1];
                        objArr2[c] = Integer.valueOf(i14);
                        format = String.format(str3, objArr2);
                    }
                    String str4 = format;
                    String str5 = contentType2;
                    Object[] objArr3 = new Object[i7];
                    objArr3[c] = str4;
                    sb3.append(String.format("<par dur=\"5000ms\"><img src=\"%s\" region=\"Image\" /></par>", objArr3));
                    Uri contentUri = messagePartData4.getContentUri();
                    int width = messagePartData4.getWidth();
                    int height = messagePartData4.getHeight();
                    MessagePartData messagePartData5 = messagePartData4;
                    int i16 = i14;
                    if ((height > width) != (mi > oi)) {
                        i8 = oi;
                        i9 = mi;
                    } else {
                        i9 = oi;
                        i8 = mi;
                    }
                    int h = C1416U.m3571h(context4, contentUri);
                    int i17 = m2444i(context4, contentUri);
                    if (i17 <= 0) {
                        C1430e.m3623e("MessagingApp", "Can't get image", new Exception());
                        i17 = 0;
                        messagePartData2 = messagePartData5;
                        i10 = i16;
                        i5 = i13;
                        i4 = oi;
                        i3 = mi;
                        sb2 = sb3;
                    } else {
                        i4 = oi;
                        i3 = mi;
                        if (Log.isLoggable("MessagingApp", 2)) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("addPicturePart size: ");
                            sb4.append(i17);
                            sb2 = sb3;
                            sb4.append(" width: ");
                            sb4.append(width);
                            sb4.append(" widthLimit: ");
                            sb4.append(i9);
                            sb4.append(" height: ");
                            sb4.append(height);
                            sb4.append(" heightLimit: ");
                            sb4.append(i8);
                            C1430e.m3628v("MessagingApp", sb4.toString());
                        } else {
                            sb2 = sb3;
                        }
                        if (i17 > i13 || width > i9 || height > i8 || !(h == 0 || h == 1)) {
                            C0989s sVar2 = new C0989s();
                            Uri uri = contentUri;
                            String str6 = str5;
                            messagePartData2 = messagePartData5;
                            i5 = i13;
                            str = "MessagingApp";
                            i10 = i16;
                            String str7 = str6;
                            byte[] a2 = C1415T.m3560a(width, height, h, i9, i8, i13, uri, context, str6);
                            if (a2 == null) {
                                if (Log.isLoggable(str, 2)) {
                                    C1430e.m3628v(str, "Resize image failed.");
                                }
                                sVar = null;
                            } else {
                                sVar2.setData(a2);
                                if (!C1416U.m3569b(str7, uri)) {
                                    str2 = "image/jpeg";
                                }
                                sVar2.mo6728g(str2.getBytes());
                                sVar = sVar2;
                            }
                            if (sVar != null) {
                                i17 = sVar.getData().length;
                            } else {
                                OutOfMemoryError outOfMemoryError = new OutOfMemoryError();
                                C1430e.m3623e(str, "Can't resize image: not enough memory?", outOfMemoryError);
                                throw outOfMemoryError;
                            }
                        } else {
                            if (Log.isLoggable("MessagingApp", 2)) {
                                C1430e.m3628v("MessagingApp", "addPicturePart - already sized");
                            }
                            sVar = new C0989s();
                            sVar.mo6735h(contentUri);
                            sVar.mo6728g(str5.getBytes());
                            messagePartData2 = messagePartData5;
                            i10 = i16;
                            i5 = i13;
                            str = "MessagingApp";
                        }
                        m2435a(sVar, str4);
                        jVar.mo6688a(i10, sVar);
                        if (Log.isLoggable(str, 2)) {
                            C1430e.m3628v(str, "addPicturePart size: " + i17);
                        }
                    }
                    i15 += i17;
                    z3 = true;
                    context2 = context;
                    i6 = i10;
                    sb = sb2;
                    messagePartData = messagePartData2;
                } else {
                    MessagePartData messagePartData6 = messagePartData4;
                    int i18 = i14;
                    i5 = i13;
                    i4 = oi;
                    i3 = mi;
                    StringBuilder sb5 = sb3;
                    String str8 = "MessagingApp";
                    if (C1481w.m3830Ea(contentType2)) {
                        Object[] objArr4 = new Object[2];
                        objArr4[0] = Integer.valueOf(i18);
                        if (extensionFromMimeType == null) {
                            extensionFromMimeType = "mp4";
                        }
                        objArr4[1] = extensionFromMimeType;
                        String format2 = String.format("video%06d.%s", objArr4);
                        Uri contentUri2 = messagePartData6.getContentUri();
                        String contentType3 = messagePartData6.getContentType();
                        if (Log.isLoggable(str8, 2)) {
                            StringBuilder Pa = C0632a.m1011Pa("addPart attachmentUrl: ");
                            Pa.append(contentUri2.toString());
                            C1430e.m3628v(str8, Pa.toString());
                        }
                        if (TextUtils.isEmpty(contentType3)) {
                            contentType3 = "video/3gpp2";
                        }
                        m2434a(jVar, format2, contentUri2, contentType3);
                        i15 += (int) m2447m(contentUri2);
                        context2 = context;
                        i6 = i18;
                        messagePartData = messagePartData6;
                        sb = sb5;
                        sb.append(String.format("<par dur=\"%2$dms\"><video src=\"%1$s\" dur=\"%2$dms\" region=\"Image\" /></par>", new Object[]{format2, Integer.valueOf(m2415a(context2, messagePartData, 5000))}));
                        z3 = true;
                    } else {
                        context2 = context;
                        i6 = i18;
                        sb = sb5;
                        messagePartData = messagePartData6;
                        if (C1481w.m3829Da(contentType2)) {
                            String format3 = String.format("contact%06d.vcf", new Object[]{Integer.valueOf(i6)});
                            Uri contentUri3 = messagePartData.getContentUri();
                            String contentType4 = messagePartData.getContentType();
                            int i19 = m2444i(context2, contentUri3);
                            if (i19 <= 0) {
                                C1430e.m3623e(str8, "Can't get vcard", new Exception());
                                i19 = 0;
                            } else {
                                m2434a(jVar, format3, contentUri3, contentType4);
                                if (Log.isLoggable(str8, 2)) {
                                    C1430e.m3628v(str8, "addVCardPart size: " + i19);
                                }
                            }
                            i15 += i19;
                            sb.append(String.format("<par dur=\"5000ms\"><ref src=\"%s\" /></par>", new Object[]{format3}));
                            z5 = true;
                        } else if (C1481w.m3831za(contentType2)) {
                            Object[] objArr5 = new Object[2];
                            objArr5[0] = Integer.valueOf(i6);
                            if (extensionFromMimeType == null) {
                                extensionFromMimeType = "amr";
                            }
                            objArr5[1] = extensionFromMimeType;
                            String format4 = String.format("recording%06d.%s", objArr5);
                            i15 += m2416a(context2, jVar, messagePartData, format4);
                            int a3 = m2415a(context2, messagePartData, -1);
                            C1424b.m3592ia(a3 != -1);
                            sb.append(String.format("<par dur=\"%2$dms\"><audio src=\"%1$s\" dur=\"%2$dms\" /></par>", new Object[]{format4, Integer.valueOf(a3)}));
                            z5 = true;
                        } else {
                            String format5 = String.format("other%06d.dat", new Object[]{Integer.valueOf(i6)});
                            i15 += m2416a(context2, jVar, messagePartData, format5);
                            sb.append(String.format("<par dur=\"5000ms\"><ref src=\"%s\" /></par>", new Object[]{format5}));
                        }
                    }
                }
                i14 = i6 + 1;
                context4 = context2;
            } else {
                messagePartData = messagePartData4;
                int i20 = i14;
                context2 = context3;
                yVar = yVar2;
                i5 = i13;
                i4 = oi;
                i3 = mi;
                sb = sb3;
            }
            if (!TextUtils.isEmpty(messagePartData.getText())) {
                z4 = true;
            }
            oi = i4;
            mi = i3;
            context3 = context2;
            c = 0;
            sb3 = sb;
            yVar2 = yVar;
            i13 = i5;
        }
        int i21 = i14;
        C0995y yVar3 = yVar2;
        StringBuilder sb6 = sb3;
        if (z4) {
            String format6 = String.format("text.%06d.txt", new Object[]{Integer.valueOf(i21)});
            String hf = messageData.mo6274hf();
            C0989s sVar3 = new C0989s();
            sVar3.mo6723H(106);
            sVar3.mo6728g("text/plain".getBytes());
            m2435a(sVar3, format6);
            sVar3.setData(hf.getBytes());
            jVar.mo6689a(sVar3);
            i15 += sVar3.getData().length;
            sb6.append(String.format("<par dur=\"5000ms\"><text src=\"%s\" region=\"Text\" /></par>", new Object[]{format6}));
        }
        int i22 = i15;
        String str9 = z3 ? z4 ? "<smil><head><layout><root-layout/><region id=\"Image\" fit=\"meet\" top=\"0\" left=\"0\" height=\"80%%\" width=\"100%%\"/><region id=\"Text\" top=\"80%%\" left=\"0\" height=\"20%%\" width=\"100%%\"/></layout></head><body>%s</body></smil>" : "<smil><head><layout><root-layout/><region id=\"Image\" fit=\"meet\" top=\"0\" left=\"0\" height=\"100%%\" width=\"100%%\"/></layout></head><body>%s</body></smil>" : (!z5 || z4) ? "<smil><head><layout><root-layout/><region id=\"Text\" top=\"0\" left=\"0\" height=\"100%%\" width=\"100%%\"/></layout></head><body>%s</body></smil>" : "<smil><head><layout><root-layout/></layout></head><body>%s</body></smil>";
        String sb7 = sb6.toString();
        C0989s sVar4 = new C0989s();
        sVar4.mo6725d("smil".getBytes());
        sVar4.mo6726e("smil.xml".getBytes());
        sVar4.mo6728g("application/smil".getBytes());
        sVar4.setData(String.format(str9, new Object[]{sb7}).getBytes());
        jVar.mo6688a(0, sVar4);
        C0995y yVar4 = yVar3;
        yVar4.mo6674a(jVar);
        yVar4.mo6755u((long) i22);
        yVar4.mo6753l("personal".getBytes());
        yVar4.mo6754t(j);
        yVar4.setPriority(i2);
        int i23 = 128;
        yVar4.mo6756xa(z ? 128 : 129);
        if (!z2) {
            i23 = 129;
        }
        yVar4.mo6757ya(i23);
        return yVar4;
    }

    /* renamed from: a */
    public static int m2414a(long j, long j2) {
        ContentResolver Pk = C0632a.m1012Pk();
        Uri withAppendedId = ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, j);
        if (j2 >= Long.MAX_VALUE) {
            return Pk.delete(withAppendedId, (String) null, (String[]) null);
        }
        return Pk.delete(withAppendedId, "date<=?", new String[]{Long.toString(j2)});
    }
}
