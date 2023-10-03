package p000;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/* renamed from: bi */
/* compiled from: PG */
final class C0038bi implements SQLiteDatabase.CursorFactory {

    /* renamed from: a */
    private final /* synthetic */ C0036bg f2426a;

    public C0038bi(C0036bg bgVar) {
        this.f2426a = bgVar;
    }

    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        this.f2426a.mo1727a(new C0044bo(sQLiteQuery));
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }
}
