package p000;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.List;

/* renamed from: bl */
/* compiled from: PG */
final class C0041bl extends SQLiteOpenHelper {

    /* renamed from: a */
    private final C0039bj[] f3088a;

    /* renamed from: b */
    private final C0030ba f3089b;

    /* renamed from: c */
    private boolean f3090c;

    public C0041bl(Context context, String str, C0039bj[] bjVarArr, C0030ba baVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 10, new C0040bk(bjVarArr));
        this.f3089b = baVar;
        this.f3088a = bjVarArr;
    }

    public final synchronized void close() {
        super.close();
        this.f3088a[0] = null;
    }

    /* renamed from: a */
    private final C0039bj m3184a(SQLiteDatabase sQLiteDatabase) {
        return m3185a(this.f3088a, sQLiteDatabase);
    }

    /* renamed from: a */
    static C0039bj m3185a(C0039bj[] bjVarArr, SQLiteDatabase sQLiteDatabase) {
        C0039bj bjVar = bjVarArr[0];
        if (bjVar != null && bjVar.f2518a == sQLiteDatabase) {
            return bjVar;
        }
        C0039bj bjVar2 = new C0039bj(sQLiteDatabase);
        bjVarArr[0] = bjVar2;
        return bjVar2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized C0028az mo2541a() {
        this.f3090c = false;
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        if (this.f3090c) {
            close();
            return mo2541a();
        }
        return m3184a(writableDatabase);
    }

    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        m3184a(sQLiteDatabase);
    }

    /* JADX INFO: finally extract failed */
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        C0030ba baVar = this.f3089b;
        C0039bj a = m3184a(sQLiteDatabase);
        Cursor b = a.mo1733b("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = b.moveToFirst() && b.getInt(0) == 0;
            b.close();
            baVar.f1929b.mo520a(a);
            if (!z) {
                C0055bz b2 = baVar.f1929b.mo521b(a);
                if (!b2.f3923a) {
                    throw new IllegalStateException("Pre-packaged database has an invalid schema: " + b2.f3924b);
                }
            }
            baVar.mo1749a((C0028az) a);
            aio aio = (aio) baVar.f1929b;
            List list = aio.f548a.f3806g;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    cun cun = (cun) aio.f548a.f3806g.get(i);
                }
            }
        } catch (Throwable th) {
            b.close();
            throw th;
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f3090c = true;
        this.f3089b.mo1750a(m3184a(sQLiteDatabase), i, i2);
    }

    /* JADX INFO: finally extract failed */
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        boolean z;
        if (!this.f3090c) {
            C0030ba baVar = this.f3089b;
            C0039bj a = m3184a(sQLiteDatabase);
            Cursor b = a.mo1733b("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
            try {
                if (!b.moveToFirst() || b.getInt(0) == 0) {
                    z = false;
                } else {
                    z = true;
                }
                b.close();
                if (!z) {
                    C0055bz b2 = baVar.f1929b.mo521b(a);
                    if (b2.f3923a) {
                        baVar.mo1749a((C0028az) a);
                    } else {
                        throw new IllegalStateException("Pre-packaged database has an invalid schema: " + b2.f3924b);
                    }
                } else {
                    Cursor a2 = a.mo1729a((C0036bg) new C0027ay("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"));
                    try {
                        String string = a2.moveToFirst() ? a2.getString(0) : null;
                        a2.close();
                        if (!baVar.f1930c.equals(string) && !baVar.f1931d.equals(string)) {
                            throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
                        }
                    } catch (Throwable th) {
                        a2.close();
                        throw th;
                    }
                }
                aio aio = (aio) baVar.f1929b;
                aio.f548a.f3800a = a;
                a.mo1736c("PRAGMA foreign_keys = ON");
                C0050bu buVar = aio.f548a.f3803d;
                synchronized (buVar) {
                    if (!buVar.f3601c) {
                        a.mo1736c("PRAGMA temp_store = MEMORY;");
                        a.mo1736c("PRAGMA recursive_triggers='ON';");
                        a.mo1736c("CREATE TEMP TABLE room_table_modification_log(table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
                        buVar.mo2761a(a);
                        buVar.f3602d = a.mo1730a("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1 ");
                        buVar.f3601c = true;
                    } else {
                        Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                    }
                }
                List list = aio.f548a.f3806g;
                if (list != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        ((cun) aio.f548a.f3806g.get(i)).mo518a((C0028az) a);
                    }
                }
                baVar.f1928a = null;
            } catch (Throwable th2) {
                b.close();
                throw th2;
            }
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f3090c = true;
        this.f3089b.mo1750a(m3184a(sQLiteDatabase), i, i2);
    }
}
