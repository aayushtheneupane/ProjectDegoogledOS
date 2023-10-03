package p000;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import java.util.concurrent.Executor;

/* renamed from: gaj */
/* compiled from: PG */
public final /* synthetic */ class gaj implements ico {

    /* renamed from: a */
    private final gay f10790a;

    public gaj(gay gay) {
        this.f10790a = gay;
    }

    /* renamed from: a */
    public final idb mo6373a(icw icw, Object obj) {
        gag gag;
        gay gay = this.f10790a;
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
        Executor executor = gay.f10814n;
        int i = Build.VERSION.SDK_INT;
        if (sQLiteDatabase.isWriteAheadLoggingEnabled()) {
            gag = new gag(sQLiteDatabase, gay.f10803c, executor, gay.f10812l);
        } else {
            gag = new gag(sQLiteDatabase, executor, executor, gay.f10812l);
        }
        ieh a = ife.m12820a((Object) gag);
        gag.getClass();
        return gay.m9964a(a, new gap(gag));
    }
}
