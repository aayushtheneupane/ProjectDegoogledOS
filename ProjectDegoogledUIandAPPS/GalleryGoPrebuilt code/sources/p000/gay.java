package p000;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOutOfMemoryException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import java.io.Closeable;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: gay */
/* compiled from: PG */
public final class gay implements ComponentCallbacks2 {

    /* renamed from: a */
    public static final hvy f10801a = hvy.m12261a("com/google/android/libraries/storage/sqlite/AsyncSQLiteOpenHelper");

    /* renamed from: b */
    public final Context f10802b;

    /* renamed from: c */
    public final ScheduledExecutorService f10803c;

    /* renamed from: d */
    public final gax f10804d;

    /* renamed from: e */
    public final hpy f10805e;

    /* renamed from: f */
    public final List f10806f;

    /* renamed from: g */
    public final List f10807g;

    /* renamed from: h */
    public final gbe f10808h;

    /* renamed from: i */
    public final ice f10809i;

    /* renamed from: j */
    public final Set f10810j = new HashSet();

    /* renamed from: k */
    public final Object f10811k = new Object();

    /* renamed from: l */
    public final gaf f10812l = new gaq(this);

    /* renamed from: m */
    public final idw f10813m = new gar(this);

    /* renamed from: n */
    public final Executor f10814n;

    /* renamed from: o */
    public ieh f10815o;

    /* renamed from: p */
    public int f10816p = 0;

    /* renamed from: q */
    public ScheduledFuture f10817q;

    /* renamed from: r */
    public boolean f10818r = false;

    /* renamed from: s */
    public boolean f10819s;

    /* renamed from: t */
    private boolean f10820t = false;

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public gay(Context context, ScheduledExecutorService scheduledExecutorService, gax gax, String str, gbi gbi) {
        gah gah = new gah(str);
        this.f10809i = gah;
        this.f10803c = scheduledExecutorService;
        this.f10804d = gax;
        this.f10814n = ife.m12837a((Executor) scheduledExecutorService);
        this.f10802b = context;
        this.f10805e = gbi.f10831a;
        this.f10806f = gbi.f10832b;
        this.f10807g = gbi.f10833c;
        this.f10808h = gbi.f10834d;
    }

    /* renamed from: a */
    public final void mo6378a() {
        if (this.f10816p == 0 && this.f10815o != null) {
            if (!this.f10820t) {
                this.f10817q = this.f10803c.schedule(new gan(this), 60, TimeUnit.SECONDS);
                if (!this.f10819s) {
                    ife.m12841a(this.f10815o, (idw) new gas(this), this.f10814n);
                    return;
                }
                return;
            }
            mo6379b();
        }
    }

    /* renamed from: b */
    public final void mo6379b() {
        this.f10814n.execute(new gao(this));
    }

    /* renamed from: a */
    private static boolean m9967a(SQLiteDatabase sQLiteDatabase, gbe gbe, hpy hpy, List list, List list2) {
        int i = Build.VERSION.SDK_INT;
        sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
        List list3 = gbe.f10828a;
        int size = list3.size();
        for (int i2 = 0; i2 < size; i2++) {
            String valueOf = String.valueOf((String) list3.get(i2));
            sQLiteDatabase.execSQL(valueOf.length() == 0 ? new String("PRAGMA ") : "PRAGMA ".concat(valueOf));
        }
        return m9968a(sQLiteDatabase, hpy, list, list2);
    }

    /* renamed from: a */
    public static void m9965a(File file) {
        File file2 = new File(String.valueOf(file.getPath()).concat("-wal"));
        File file3 = new File(String.valueOf(file.getPath()).concat("-journal"));
        File file4 = new File(String.valueOf(file.getPath()).concat("-shm"));
        try {
            if ((file2.exists() && !file2.delete()) || ((file3.exists() && !file3.delete()) || ((file4.exists() && !file4.delete()) || !file.delete()))) {
                throw new gau(String.format("Unable to clean up database %s", new Object[]{file.getAbsolutePath()}));
            }
        } catch (Throwable th) {
            throw new gau(String.format("Unable to clean up database %s", new Object[]{file.getAbsolutePath()}), th);
        }
    }

    /* renamed from: a */
    public static idb m9964a(ieh ieh, Closeable... closeableArr) {
        ife.m12898e((Object) ieh);
        return idb.m12697a((icq) new gak(closeableArr), (Executor) idh.f13918a).mo8396a((ico) new gal(ieh), (Executor) idh.f13918a);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public static SQLiteDatabase m9963a(Context context, File file, gbe gbe, hpy hpy, List list, List list2) {
        hlj a;
        hlj a2;
        SQLiteDatabase a3 = m9962a(context, file);
        try {
            if (hpy.mo7646a()) {
                if (((gbf) hpy.mo7647b()).f10829a > a3.getVersion()) {
                    a2 = hnb.m11765a("Dropping tables.");
                    a3.close();
                    m9965a(file);
                    a3 = m9962a(context, file);
                    a3.setVersion(((gbf) hpy.mo7647b()).f10829a);
                    if (a2 != null) {
                        a2.close();
                    }
                }
            }
            try {
                if (m9967a(a3, gbe, hpy, list, list2)) {
                    a3.close();
                    a3 = m9962a(context, file);
                    try {
                        a = hnb.m11765a("Configuring reopened database.");
                        ife.m12876b(!m9967a(a3, gbe, hpy, list, list2), (Object) "Reopen request for a database that was already reopened after upgrade. Upgrade did not take despite error-free completion of the upgrade transaction.");
                        if (a != null) {
                            a.close();
                        }
                    } catch (SQLiteException | IllegalStateException e) {
                        a3.close();
                        throw new gat("Failed to open database.", e);
                    } catch (Throwable th) {
                        a3.close();
                        throw th;
                    }
                }
                return a3;
            } catch (SQLiteException e2) {
                a3.close();
                throw new gat("Failed to open database.", e2);
            } catch (Throwable th2) {
                a3.close();
                throw th2;
            }
            throw th;
            throw th;
        } catch (gau e3) {
            throw new gat("Failed to drop tables to apply new schema.", e3);
        } catch (Throwable th3) {
            ifn.m12935a(th, th3);
        }
    }

    /* renamed from: a */
    public static boolean m9966a(Context context) {
        int i = Build.VERSION.SDK_INT;
        return ((ActivityManager) context.getSystemService("activity")).isLowRamDevice();
    }

    /* renamed from: a */
    private static int m9961a(SQLiteDatabase sQLiteDatabase, hpy hpy) {
        if (hpy.mo7646a()) {
            return sQLiteDatabase.getVersion() - ((gbf) hpy.mo7647b()).f10829a;
        }
        return sQLiteDatabase.getVersion();
    }

    public final void onLowMemory() {
        onTrimMemory(80);
    }

    public final void onTrimMemory(int i) {
        boolean z;
        synchronized (this.f10811k) {
            if (i >= 40) {
                z = true;
            } else {
                z = false;
            }
            this.f10820t = z;
            mo6378a();
        }
    }

    /* renamed from: a */
    private static SQLiteDatabase m9962a(Context context, File file) {
        int i;
        boolean z = !m9966a(context);
        if (!z) {
            i = 268435456;
        } else {
            int i2 = Build.VERSION.SDK_INT;
            i = 805306368;
        }
        file.getParentFile().mkdirs();
        try {
            String path = file.getPath();
            int i3 = gbr.f10846c;
            SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(path, (SQLiteDatabase.CursorFactory) null, i, (DatabaseErrorHandler) null);
            if (z) {
                openDatabase.enableWriteAheadLogging();
            }
            return openDatabase;
        } catch (Throwable th) {
            throw new gat("Failed to open database.", th);
        }
    }

    /* renamed from: a */
    private static boolean m9968a(SQLiteDatabase sQLiteDatabase, hpy hpy, List list, List list2) {
        hlj a;
        int a2 = m9961a(sQLiteDatabase, hpy);
        int size = list.size();
        int size2 = list.size();
        if (a2 <= size) {
            gbr gbr = new gbr(sQLiteDatabase);
            sQLiteDatabase.beginTransaction();
            try {
                if (a2 != list.size()) {
                    a = hnb.m11765a("Applying upgrade steps");
                    for (gbh a3 : list.subList(a2, list.size())) {
                        a3.mo4000a(gbr);
                    }
                    if (a != null) {
                        a.close();
                    }
                    if (hpy.mo7646a()) {
                        sQLiteDatabase.setVersion(((gbf) hpy.mo7647b()).f10829a + list.size());
                    } else {
                        sQLiteDatabase.setVersion(list.size());
                    }
                }
                Iterator it = list2.iterator();
                if (!it.hasNext()) {
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    return a2 != m9961a(sQLiteDatabase, hpy);
                }
                gbz gbz = (gbz) it.next();
                throw null;
            } catch (InterruptedException e) {
                throw new gaw("Thread interrupted during database upgrade. Upgrade transaction will be unsuccessful.", e);
            } catch (SQLiteDatabaseLockedException e2) {
                e = e2;
                throw new gaw("An Exception was thrown during upgrade. This is probably recoverable by the user clearing disk space or when another process releases a database lock.", e);
            } catch (SQLiteDiskIOException e3) {
                e = e3;
                throw new gaw("An Exception was thrown during upgrade. This is probably recoverable by the user clearing disk space or when another process releases a database lock.", e);
            } catch (SQLiteFullException e4) {
                e = e4;
                throw new gaw("An Exception was thrown during upgrade. This is probably recoverable by the user clearing disk space or when another process releases a database lock.", e);
            } catch (SQLiteOutOfMemoryException e5) {
                e = e5;
                throw new gaw("An Exception was thrown during upgrade. This is probably recoverable by the user clearing disk space or when another process releases a database lock.", e);
            } catch (SQLiteTableLockedException e6) {
                e = e6;
                throw new gaw("An Exception was thrown during upgrade. This is probably recoverable by the user clearing disk space or when another process releases a database lock.", e);
            } catch (Throwable th) {
                try {
                    throw new gav("An unknown error occurred during upgrade. The upgrade may fail repeatedly when retried.", th);
                } catch (Throwable th2) {
                    sQLiteDatabase.endTransaction();
                    throw th2;
                }
            }
        } else {
            throw new IllegalStateException(ife.m12834a("Can't downgrade from version %s to version %s", Integer.valueOf(a2), Integer.valueOf(size2)));
        }
        throw th;
    }
}
