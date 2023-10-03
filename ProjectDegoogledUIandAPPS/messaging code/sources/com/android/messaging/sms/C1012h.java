package com.android.messaging.sms;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.Telephony;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.appcompat.mms.ApnSettingsLoader;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import java.util.ArrayList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.h */
public class C1012h implements ApnSettingsLoader {

    /* renamed from: UD */
    private static final String[] f1536UD = {"type", "mmsc", "mmsproxy", "mmsport"};

    /* renamed from: VD */
    private static final String[] f1537VD = {"type", "mmsc", "mmsproxy", "mmsport", "current", "_id"};
    private final SparseArray mApnsCache = new SparseArray();
    private final Context mContext;

    public C1012h(Context context) {
        this.mContext = context;
    }

    /* renamed from: a */
    private static Cursor m2363a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        String str3;
        String[] strArr;
        Cursor cursor;
        if (TextUtils.isEmpty(str2)) {
            strArr = new String[]{str};
            str3 = "numeric=?";
        } else {
            strArr = new String[]{str, str2};
            str3 = "numeric=? AND apn=?";
        }
        try {
            cursor = sQLiteDatabase.query("apn", f1537VD, str3, strArr, (String) null, (String) null, "current DESC", (String) null);
        } catch (SQLiteException e) {
            C1430e.m3631w("MessagingApp", "Local APN table does not exist. Try rebuilding.", e);
            C1006b.m2349ob();
            cursor = sQLiteDatabase.query("apn", f1537VD, str3, strArr, (String) null, (String) null, "current DESC", (String) null);
        }
        if (cursor != null && cursor.getCount() >= 1) {
            return cursor;
        }
        if (cursor != null) {
            cursor.close();
        }
        C1430e.m3630w("MessagingApp", "Query local APNs with apn " + str2 + " returned empty");
        return null;
    }

    static /* synthetic */ String access$000(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    static /* synthetic */ String access$100(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split("\\.");
        if (split.length != 4) {
            return str;
        }
        StringBuilder sb = new StringBuilder(16);
        int i = 0;
        while (i < 4) {
            try {
                if (split[i].length() > 3) {
                    return str;
                }
                sb.append(Integer.parseInt(split[i]));
                if (i < 3) {
                    sb.append('.');
                }
                i++;
            } catch (NumberFormatException unused) {
                return str;
            }
        }
        return sb.toString();
    }

    public static boolean isValidApnType(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        for (String str3 : str.split(",")) {
            if (str3.equals(str2) || str3.equals(DefaultApnSettingsLoader.APN_TYPE_ALL)) {
                return true;
            }
        }
        return false;
    }

    private void loadLocked(int i, String str, List list) {
        Uri uri;
        C1010f from;
        int i2 = i;
        String str2 = str;
        List list2 = list;
        C1449g gVar = C1449g.get();
        gVar.getString("bugle_mms_mmsc", (String) null);
        if (!TextUtils.isEmpty((CharSequence) null)) {
            C1430e.m3625i("MessagingApp", "Loading APNs from gservices");
            gVar.getString("bugle_mms_proxy_address", (String) null);
            gVar.getInt("bugle_mms_proxy_port", -1);
            C1010f from2 = C1010f.from(DefaultApnSettingsLoader.APN_TYPE_MMS, (String) null, (String) null, Integer.toString(-1));
            if (from2 != null) {
                list2.add(from2);
            }
        }
        if (list.size() <= 0) {
            if (!C1464na.m3759Zj() || i2 == -1) {
                uri = Telephony.Carriers.CONTENT_URI;
            } else {
                Uri uri2 = Telephony.Carriers.CONTENT_URI;
                uri = Uri.withAppendedPath(uri2, "/subId/" + i2);
            }
            try {
                Cursor querySystem = querySystem(uri, true, str2);
                if (querySystem == null) {
                    querySystem = querySystem(uri, false, str2);
                    if (querySystem == null) {
                        querySystem = querySystem(uri, true, (String) null);
                        if (querySystem == null) {
                            querySystem = querySystem(uri, false, (String) null);
                        }
                    }
                }
                if (querySystem != null) {
                    try {
                        if (querySystem.moveToFirst() && (from = C1010f.from(querySystem.getString(0), querySystem.getString(1), querySystem.getString(2), querySystem.getString(3))) != null) {
                            list2.add(from);
                        }
                    } finally {
                        querySystem.close();
                    }
                }
            } catch (SecurityException unused) {
            }
            if (list.size() <= 0) {
                C1430e.m3625i("MessagingApp", "Loading APNs from local APN table");
                SQLiteDatabase writableDatabase = C1006b.m2350pb().getWritableDatabase();
                String b = C1474sa.m3795b(C1474sa.getDefault().mo8207fk());
                Cursor a = m2363a(writableDatabase, b, str2);
                if (a == null) {
                    a = m2363a(writableDatabase, b, (String) null);
                }
                Cursor cursor = a;
                if (cursor == null) {
                    C1430e.m3630w("MessagingApp", "Could not find any APN in local table");
                } else {
                    while (cursor.moveToNext()) {
                        try {
                            C1011g a2 = C1011g.m2359a(list, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getLong(5), cursor.getInt(4));
                            if (a2 != null) {
                                list2.add(a2);
                            }
                        } finally {
                            cursor.close();
                        }
                    }
                }
                if (list.size() <= 0) {
                    C1430e.m3630w("MessagingApp", "Failed to load any APN");
                }
            }
        }
    }

    private Cursor querySystem(Uri uri, boolean z, String str) {
        String[] strArr;
        C1430e.m3625i("MessagingApp", "Loading APNs from system, checkCurrent=" + z + " apnName=" + str);
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("current IS NOT NULL");
        }
        String trim = str != null ? str.trim() : null;
        if (!TextUtils.isEmpty(trim)) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("apn=?");
            strArr = new String[]{trim};
        } else {
            strArr = null;
        }
        try {
            Cursor query = C0107q.query(this.mContext.getContentResolver(), uri, f1536UD, sb.toString(), strArr, (String) null);
            if (query != null) {
                if (query.getCount() >= 1) {
                    return query;
                }
            }
            if (query != null) {
                query.close();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Query ");
            sb2.append(uri);
            sb2.append(" with apn ");
            sb2.append(trim);
            sb2.append(" and ");
            sb2.append(z ? "checking CURRENT" : "not checking CURRENT");
            sb2.append(" returned empty");
            C1430e.m3630w("MessagingApp", sb2.toString());
            return null;
        } catch (SQLiteException e) {
            C1430e.m3630w("MessagingApp", "APN table query exception: " + e);
            return null;
        } catch (SecurityException e2) {
            C1430e.m3630w("MessagingApp", "Platform restricts APN table access: " + e2);
            throw e2;
        }
    }

    public List get(String str) {
        List list;
        boolean z;
        int Na = C1474sa.getDefault().mo8201Na(-1);
        synchronized (this) {
            list = (List) this.mApnsCache.get(Na);
            if (list == null) {
                list = new ArrayList();
                this.mApnsCache.put(Na, list);
                loadLocked(Na, str, list);
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            StringBuilder Pa = C0632a.m1011Pa("Loaded ");
            Pa.append(list.size());
            Pa.append(" APNs");
            C1430e.m3625i("MessagingApp", Pa.toString());
        }
        return list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        if (r1 != null) goto L_0x0011;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001b  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m2364a(android.database.sqlite.SQLiteDatabase r1, java.lang.String r2) {
        /*
            r0 = 0
            android.database.Cursor r1 = m2363a(r1, r2, r0)     // Catch:{ Exception -> 0x001f, all -> 0x0017 }
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x0020, all -> 0x0015 }
            if (r2 == 0) goto L_0x0011
            r2 = 4
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x0020, all -> 0x0015 }
            r0 = r2
        L_0x0011:
            r1.close()
            goto L_0x0023
        L_0x0015:
            r2 = move-exception
            goto L_0x0019
        L_0x0017:
            r2 = move-exception
            r1 = r0
        L_0x0019:
            if (r1 == 0) goto L_0x001e
            r1.close()
        L_0x001e:
            throw r2
        L_0x001f:
            r1 = r0
        L_0x0020:
            if (r1 == 0) goto L_0x0023
            goto L_0x0011
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1012h.m2364a(android.database.sqlite.SQLiteDatabase, java.lang.String):java.lang.String");
    }
}
