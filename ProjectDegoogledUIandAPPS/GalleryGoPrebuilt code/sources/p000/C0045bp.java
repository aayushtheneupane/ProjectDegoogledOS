package p000;

import android.database.sqlite.SQLiteStatement;

/* renamed from: bp */
/* compiled from: PG */
final class C0045bp extends C0044bo implements C0037bh {

    /* renamed from: b */
    private final SQLiteStatement f3284b;

    public C0045bp(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.f3284b = sQLiteStatement;
    }

    /* renamed from: a */
    public final void mo2032a() {
        this.f3284b.executeInsert();
    }

    /* renamed from: b */
    public final void mo2033b() {
        this.f3284b.executeUpdateDelete();
    }
}
