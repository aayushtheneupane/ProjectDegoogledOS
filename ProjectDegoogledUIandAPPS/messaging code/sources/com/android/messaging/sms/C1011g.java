package com.android.messaging.sms;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.mms.ApnSettingsLoader;
import com.android.messaging.util.C1430e;
import java.util.Iterator;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.g */
class C1011g implements ApnSettingsLoader.Apn {

    /* renamed from: RD */
    private static final ContentValues f1532RD = new ContentValues(1);

    /* renamed from: SD */
    private static final ContentValues f1533SD = new ContentValues(1);

    /* renamed from: TD */
    private static final String[] f1534TD = {"1"};

    /* renamed from: Rn */
    private int f1535Rn;
    private final List mApns;
    private final C1010f mBase;
    private final long mRowId;

    static {
        f1532RD.putNull("current");
        f1533SD.put("current", "1");
    }

    public C1011g(List list, C1010f fVar, long j, int i) {
        this.mApns = list;
        this.mBase = fVar;
        this.mRowId = j;
        this.f1535Rn = i;
    }

    /* renamed from: a */
    public static C1011g m2359a(List list, String str, String str2, String str3, String str4, long j, int i) {
        C1010f from;
        if (list == null || (from = C1010f.from(str, str2, str3, str4)) == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ApnSettingsLoader.Apn apn = (ApnSettingsLoader.Apn) it.next();
            if ((apn instanceof C1011g) && ((C1011g) apn).mo6819a(from)) {
                return null;
            }
        }
        return new C1011g(list, from, j, i);
    }

    /* renamed from: mo */
    private void m2360mo() {
        boolean z;
        synchronized (this.mApns) {
            z = false;
            if (this.mApns.get(0) != this) {
                this.mApns.remove(this);
                this.mApns.add(0, this);
                z = true;
            }
        }
        if (z) {
            StringBuilder Pa = C0632a.m1011Pa("Set APN [MMSC=");
            Pa.append(this.mBase.getMmsc());
            Pa.append(", PROXY=");
            Pa.append(this.mBase.getMmsProxy());
            Pa.append(", PORT=");
            Pa.append(this.mBase.getMmsProxyPort());
            Pa.append("] to be first");
            C1430e.m3620d("MessagingApp", Pa.toString());
        }
    }

    /* renamed from: no */
    private void m2361no() {
        synchronized (this) {
            if (this.f1535Rn <= 0) {
                this.f1535Rn = 1;
                StringBuilder Pa = C0632a.m1011Pa("Set APN @");
                Pa.append(this.mRowId);
                Pa.append(" to be CURRENT in local db");
                C1430e.m3620d("MessagingApp", Pa.toString());
                SQLiteDatabase writableDatabase = C1006b.m2350pb().getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    writableDatabase.update("apn", f1532RD, "current =?", f1534TD);
                    writableDatabase.update("apn", f1533SD, "_id =?", new String[]{Long.toString(this.mRowId)});
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        }
    }

    public String getMmsProxy() {
        return this.mBase.getMmsProxy();
    }

    public int getMmsProxyPort() {
        return this.mBase.getMmsProxyPort();
    }

    public String getMmsc() {
        return this.mBase.getMmsc();
    }

    public void setSuccess() {
        m2360mo();
        m2361no();
    }

    /* renamed from: a */
    public boolean mo6819a(C1010f fVar) {
        if (fVar == null) {
            return false;
        }
        return this.mBase.mo6818a(fVar);
    }
}
