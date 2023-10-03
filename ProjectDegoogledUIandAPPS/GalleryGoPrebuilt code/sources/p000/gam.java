package p000;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Set;

/* renamed from: gam */
/* compiled from: PG */
public final /* synthetic */ class gam implements hpr {

    /* renamed from: a */
    private final gay f10793a;

    public gam(gay gay) {
        this.f10793a = gay;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        SQLiteDatabase sQLiteDatabase;
        gay gay = this.f10793a;
        File databasePath = gay.f10802b.getDatabasePath((String) obj);
        if (!gay.f10818r) {
            gax gax = gay.f10804d;
            String path = databasePath.getPath();
            if (gax.f10800a.add(path)) {
                gay.f10818r = true;
                boolean a = true ^ gay.m9966a(gay.f10802b);
                gay.f10819s = a;
                if (a) {
                    try {
                        gay.f10819s = databasePath.getCanonicalPath().startsWith(gay.f10802b.getCacheDir().getCanonicalPath());
                    } catch (IOException e) {
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(path).length() + 89);
                sb.append("DB ");
                sb.append(path);
                sb.append(" opened from different AsyncSQLiteOpenHelper. Are you missing a scope on your binding?");
                throw new IllegalStateException(sb.toString());
            }
        }
        Set set = gay.f10810j;
        if (!set.isEmpty()) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                SQLiteDatabase sQLiteDatabase2 = (SQLiteDatabase) ((WeakReference) it.next()).get();
                if (sQLiteDatabase2 == null) {
                    it.remove();
                } else if (sQLiteDatabase2.isOpen()) {
                    String path2 = sQLiteDatabase2.getPath();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(path2).length() + 103);
                    sb2.append("Open database reference to ");
                    sb2.append(path2);
                    sb2.append(" already exists. Follow instructions in source to file a bug against TikTok.");
                    throw new IllegalStateException(sb2.toString());
                }
            }
        }
        try {
            sQLiteDatabase = gay.m9963a(gay.f10802b, databasePath, gay.f10808h, gay.f10805e, gay.f10806f, gay.f10807g);
        } catch (gat | gav | gaw e2) {
            try {
                sQLiteDatabase = gay.m9963a(gay.f10802b, databasePath, gay.f10808h, gay.f10805e, gay.f10806f, gay.f10807g);
            } catch (gaw e3) {
                throw new gat("Probably-recoverable database upgrade failure.", e3);
            } catch (gav e4) {
                ((hvv) ((hvv) ((hvv) gay.f10801a.mo8178a()).mo8202a((Throwable) e4)).mo8201a("com/google/android/libraries/storage/sqlite/AsyncSQLiteOpenHelper", "lambda$innerOpenDatabase$5", 434, "AsyncSQLiteOpenHelper.java")).mo8203a("Fatal Exception when trying to upgrade database. Proceeding to delete.");
                gay.m9965a(databasePath);
                throw new gat("Failed to open the database with an unrecoverable Exception. Deleted its files so the next open attempt will create a new instance.", e4);
            } catch (Throwable th) {
                throw new gat("Recovery by deletion failed.", th);
            }
        }
        gay.f10810j.add(new WeakReference(sQLiteDatabase));
        gay.f10802b.registerComponentCallbacks(gay);
        return sQLiteDatabase;
    }
}
