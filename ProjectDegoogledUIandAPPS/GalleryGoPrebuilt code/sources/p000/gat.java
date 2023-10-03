package p000;

import android.database.sqlite.SQLiteException;

/* renamed from: gat */
/* compiled from: PG */
final class gat extends SQLiteException {
    public gat(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
