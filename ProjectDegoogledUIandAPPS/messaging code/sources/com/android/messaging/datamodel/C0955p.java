package com.android.messaging.datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.util.SparseArray;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1486ya;
import java.util.Locale;
import java.util.Stack;
import java.util.regex.Pattern;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.p */
public class C0955p {

    /* renamed from: Nx */
    private static ThreadLocal f1370Nx = new C0953n();

    /* renamed from: Ox */
    private static String[] f1371Ox = {"took %d ms to %s", "   took %d ms to %s", "      took %d ms to %s"};

    /* renamed from: Lx */
    private final String f1372Lx;

    /* renamed from: Mx */
    private final SparseArray f1373Mx;

    /* renamed from: ha */
    private final SQLiteDatabase f1374ha;
    private final Context mContext;
    private final boolean mLog = Log.isLoggable("MessagingAppDbPerf", 2);

    C0955p(Context context, SQLiteDatabase sQLiteDatabase) {
        C0967f.get().mo6646Id().getString("bugle_query_plan_regexp", (String) null);
        this.f1372Lx = null;
        this.f1374ha = sQLiteDatabase;
        this.mContext = context;
        this.f1373Mx = new SparseArray();
    }

    /* renamed from: a */
    private static void m2162a(long j, String str) {
        int size = ((Stack) f1370Nx.get()).size();
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis > 50) {
            Locale locale = Locale.US;
            String[] strArr = f1371Ox;
            C1430e.m3628v("MessagingAppDbPerf", String.format(locale, strArr[Math.min(strArr.length - 1, size)], new Object[]{Long.valueOf(currentTimeMillis), str}));
        }
    }

    /* renamed from: ao */
    private void m2165ao() {
        C1410N.m3552g(this.mContext, 1);
    }

    /* renamed from: b */
    public SQLiteStatement mo6624b(int i, String str) {
        C1424b.m3592ia(this.f1374ha.inTransaction());
        SQLiteStatement sQLiteStatement = (SQLiteStatement) this.f1373Mx.get(i);
        if (sQLiteStatement != null) {
            return sQLiteStatement;
        }
        SQLiteStatement compileStatement = this.f1374ha.compileStatement(str);
        C1424b.m3592ia(compileStatement.toString().contains(str.trim()));
        this.f1373Mx.put(i, compileStatement);
        return compileStatement;
    }

    public void beginTransaction() {
        long currentTimeMillis = System.currentTimeMillis();
        C0954o oVar = new C0954o();
        oVar.time = currentTimeMillis;
        ((Stack) f1370Nx.get()).push(oVar);
        this.f1374ha.beginTransaction();
    }

    public int delete(String str, String str2, String[] strArr) {
        int i;
        long currentTimeMillis = this.mLog ? System.currentTimeMillis() : 0;
        m2165ao();
        try {
            i = this.f1374ha.delete(str, str2, strArr);
        } catch (SQLiteFullException e) {
            C1430e.m3623e("MessagingAppDb", "Database full, unable to delete", e);
            C1486ya.m3848Pa(R.string.db_full);
            i = 0;
        }
        if (this.mLog) {
            m2162a(currentTimeMillis, String.format(Locale.US, "delete from %s with %s ==> %d", new Object[]{str, str2, Integer.valueOf(i)}));
        }
        return i;
    }

    public void endTransaction() {
        long j;
        C0954o oVar = (C0954o) ((Stack) f1370Nx.get()).pop();
        if (!oVar.f1369Kx) {
            C1430e.m3630w("MessagingAppDb", "endTransaction without setting successful");
            for (StackTraceElement stackTraceElement : new Exception().getStackTrace()) {
                StringBuilder Pa = C0632a.m1011Pa("    ");
                Pa.append(stackTraceElement.toString());
                C1430e.m3630w("MessagingAppDb", Pa.toString());
            }
        }
        long j2 = 0;
        if (this.mLog) {
            j2 = oVar.time;
            j = System.currentTimeMillis();
        } else {
            j = 0;
        }
        try {
            this.f1374ha.endTransaction();
        } catch (SQLiteFullException e) {
            C1430e.m3623e("MessagingAppDb", "Database full, unable to endTransaction", e);
            C1486ya.m3848Pa(R.string.db_full);
        }
        if (this.mLog) {
            m2162a(j, String.format(Locale.US, ">>> endTransaction (total for this transaction: %d)", new Object[]{Long.valueOf(System.currentTimeMillis() - j2)}));
        }
    }

    public void execSQL(String str) {
        long currentTimeMillis = this.mLog ? System.currentTimeMillis() : 0;
        m2165ao();
        try {
            this.f1374ha.execSQL(str);
        } catch (SQLiteFullException e) {
            C1430e.m3623e("MessagingAppDb", "Database full, unable to execSQL", e);
            C1486ya.m3848Pa(R.string.db_full);
        }
        if (this.mLog) {
            m2162a(currentTimeMillis, String.format(Locale.US, "execSQL %s", new Object[]{str}));
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public SQLiteDatabase getDatabase() {
        return this.f1374ha;
    }

    public long insert(String str, String str2, ContentValues contentValues) {
        long currentTimeMillis = this.mLog ? System.currentTimeMillis() : 0;
        m2165ao();
        long j = -1;
        try {
            j = this.f1374ha.insert(str, str2, contentValues);
        } catch (SQLiteFullException e) {
            C1430e.m3623e("MessagingAppDb", "Database full, unable to insert", e);
            C1486ya.m3848Pa(R.string.db_full);
        }
        if (this.mLog) {
            m2162a(currentTimeMillis, String.format(Locale.US, "insert to %s", new Object[]{str}));
        }
        return j;
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        String str7 = str;
        if (this.f1372Lx != null) {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(str);
            m2164a(sQLiteQueryBuilder, this.f1374ha, strArr, str2, strArr2, str3, str5, str6);
        }
        m2165ao();
        long j = 0;
        if (this.mLog) {
            j = System.currentTimeMillis();
        }
        long j2 = j;
        Cursor query = this.f1374ha.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        if (this.mLog) {
            m2162a(j2, String.format(Locale.US, "query %s with %s ==> %d", new Object[]{str7, str2, Integer.valueOf(query.getCount())}));
        }
        return query;
    }

    public Cursor rawQuery(String str, String[] strArr) {
        if (this.f1372Lx != null) {
            m2163a(this.f1374ha, str, strArr);
        }
        long j = 0;
        if (this.mLog) {
            j = System.currentTimeMillis();
        }
        m2165ao();
        Cursor rawQuery = this.f1374ha.rawQuery(str, strArr);
        if (this.mLog) {
            m2162a(j, String.format(Locale.US, "rawQuery %s ==> %d", new Object[]{str, Integer.valueOf(rawQuery.getCount())}));
        }
        return rawQuery;
    }

    public void setTransactionSuccessful() {
        ((C0954o) ((Stack) f1370Nx.get()).peek()).f1369Kx = true;
        this.f1374ha.setTransactionSuccessful();
    }

    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        int i;
        long currentTimeMillis = this.mLog ? System.currentTimeMillis() : 0;
        m2165ao();
        try {
            i = this.f1374ha.update(str, contentValues, str2, strArr);
        } catch (SQLiteFullException e) {
            C1430e.m3623e("MessagingAppDb", "Database full, unable to update", e);
            C1486ya.m3848Pa(R.string.db_full);
            i = 0;
        }
        if (this.mLog) {
            m2162a(currentTimeMillis, String.format(Locale.US, "update %s with %s ==> %d", new Object[]{str, str2, Integer.valueOf(i)}));
        }
        return i;
    }

    /* renamed from: a */
    private void m2164a(SQLiteQueryBuilder sQLiteQueryBuilder, SQLiteDatabase sQLiteDatabase, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        m2163a(sQLiteDatabase, sQLiteQueryBuilder.buildQuery(strArr, str, str2, (String) null, str3, str4), strArr2);
    }

    /* renamed from: a */
    private void m2163a(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        if (Pattern.matches(this.f1372Lx, str)) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("explain query plan " + str, strArr);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        int columnIndex = rawQuery.getColumnIndex("detail");
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(rawQuery.getString(columnIndex));
                            sb.append("\n");
                        } while (rawQuery.moveToNext());
                        if (sb.length() > 0) {
                            sb.setLength(sb.length() - 1);
                        }
                        C1430e.m3628v("MessagingAppDb", "for query " + str + "\nplan is: " + sb.toString());
                    }
                } catch (Exception e) {
                    C1430e.m3631w("MessagingAppDb", "Query plan failed ", e);
                } catch (Throwable th) {
                    rawQuery.close();
                    throw th;
                }
            }
            if (rawQuery == null) {
                return;
            }
            rawQuery.close();
        }
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return query(str, strArr, str2, strArr2, str3, str4, str5, (String) null);
    }

    /* renamed from: a */
    public Cursor mo6623a(SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        if (this.f1372Lx != null) {
            m2164a(sQLiteQueryBuilder, this.f1374ha, strArr, str, strArr2, str2, str4, str5);
        }
        m2165ao();
        long j = 0;
        if (this.mLog) {
            j = System.currentTimeMillis();
        }
        Cursor query = sQLiteQueryBuilder.query(this.f1374ha, strArr, str, strArr2, str2, str3, str4, str5);
        if (this.mLog) {
            m2162a(j, String.format(Locale.US, "query %s with %s ==> %d", new Object[]{sQLiteQueryBuilder.getTables(), str, Integer.valueOf(query.getCount())}));
        }
        return query;
    }

    /* renamed from: a */
    public long mo6622a(String str, String str2, String[] strArr) {
        long currentTimeMillis = this.mLog ? System.currentTimeMillis() : 0;
        m2165ao();
        long queryNumEntries = DatabaseUtils.queryNumEntries(this.f1374ha, str, str2, strArr);
        if (this.mLog) {
            m2162a(currentTimeMillis, String.format(Locale.US, "queryNumEntries %s with %s ==> %d", new Object[]{str, str2, Long.valueOf(queryNumEntries)}));
        }
        return queryNumEntries;
    }
}
