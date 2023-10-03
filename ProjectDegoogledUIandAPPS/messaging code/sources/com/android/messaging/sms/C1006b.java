package com.android.messaging.sms;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.android.messaging.R;
import com.android.messaging.util.C1430e;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: com.android.messaging.sms.b */
public class C1006b extends SQLiteOpenHelper {

    /* renamed from: tc */
    private static Context f1527tc;

    /* renamed from: uc */
    private static C1006b f1528uc;

    static {
        new String[]{"type", "mmsc", "mmsproxy", "mmsport", "_id", "current", "numeric", "name", "mcc", "mnc", "apn", "sub_id"};
        new String[]{"name", "mcc", "mnc", "apn", "user", "server", "password", "proxy", "port", "mmsc", "mmsproxy", "mmsport", "authtype", "type", "protocol", "roaming_protocol", "carrier_enabled", "bearer", "mvno_type", "mvno_match_data", "current", "sub_id"};
        new String[]{"_id"};
    }

    private C1006b() {
        super(f1527tc, "apn.db", (SQLiteDatabase.CursorFactory) null, 3);
    }

    /* renamed from: b */
    private void m2345b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS apn;");
        sQLiteDatabase.execSQL("CREATE TABLE apn(_id INTEGER PRIMARY KEY,name TEXT,numeric TEXT,mcc TEXT,mnc TEXT,apn TEXT,user TEXT,server TEXT,password TEXT,proxy TEXT,port TEXT,mmsproxy TEXT,mmsport TEXT,mmsc TEXT,authtype INTEGER,type TEXT,current INTEGER,protocol TEXT,roaming_protocol TEXT,carrier_enabled BOOLEAN,bearer INTEGER,mvno_type TEXT,mvno_match_data TEXT,sub_id INTEGER DEFAULT -1);");
        m2347e(sQLiteDatabase);
    }

    /* renamed from: e */
    public static void m2346e(Context context) {
        f1527tc = context;
    }

    /* renamed from: nb */
    public static boolean m2348nb() {
        return f1527tc.getDatabasePath("apn.db").exists();
    }

    /* renamed from: ob */
    public static void m2349ob() {
        SQLiteDatabase writableDatabase = m2350pb().getWritableDatabase();
        writableDatabase.execSQL("DROP TABLE IF EXISTS apn");
        writableDatabase.execSQL("CREATE TABLE apn(_id INTEGER PRIMARY KEY,name TEXT,numeric TEXT,mcc TEXT,mnc TEXT,apn TEXT,user TEXT,server TEXT,password TEXT,proxy TEXT,port TEXT,mmsproxy TEXT,mmsport TEXT,mmsc TEXT,authtype INTEGER,type TEXT,current INTEGER,protocol TEXT,roaming_protocol TEXT,carrier_enabled BOOLEAN,bearer INTEGER,mvno_type TEXT,mvno_match_data TEXT,sub_id INTEGER DEFAULT -1);");
        m2347e(writableDatabase);
    }

    /* renamed from: pb */
    public static C1006b m2350pb() {
        if (f1528uc == null) {
            f1528uc = new C1006b();
        }
        return f1528uc;
    }

    public void close() {
        super.close();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        m2345b(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        m2345b(sQLiteDatabase);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        m2345b(sQLiteDatabase);
    }

    /* renamed from: e */
    private static void m2347e(SQLiteDatabase sQLiteDatabase) {
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", "ApnDatabase loadApnTable");
        }
        XmlResourceParser xml = f1527tc.getResources().getXml(R.xml.apns);
        C1009e a = C1009e.m2351a((XmlPullParser) xml);
        a.mo6815a((C1007c) new C1005a(sQLiteDatabase));
        try {
            a.mo6817gi();
        } catch (Exception e) {
            Log.e("MessagingApp", "Got exception while loading APN database.", e);
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
        xml.close();
    }
}
