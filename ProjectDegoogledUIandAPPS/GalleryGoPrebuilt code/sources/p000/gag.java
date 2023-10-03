package p000;

import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.Executor;

/* renamed from: gag */
/* compiled from: PG */
public final class gag {

    /* renamed from: a */
    public final SQLiteDatabase f10783a;

    /* renamed from: b */
    public final Executor f10784b;

    /* renamed from: c */
    public final Executor f10785c;

    /* renamed from: d */
    public final gaf f10786d;

    /* renamed from: e */
    public volatile boolean f10787e = false;

    public gag(SQLiteDatabase sQLiteDatabase, Executor executor, Executor executor2, gaf gaf) {
        this.f10783a = sQLiteDatabase;
        this.f10784b = executor;
        this.f10785c = executor2;
        this.f10786d = gaf;
    }

    /* renamed from: a */
    public final void mo6371a() {
        if (this.f10787e) {
            throw new IllegalStateException("Already closed");
        }
    }
}
