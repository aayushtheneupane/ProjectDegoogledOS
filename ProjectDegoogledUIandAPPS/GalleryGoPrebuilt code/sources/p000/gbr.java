package p000;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.CancellationSignal;

/* renamed from: gbr */
/* compiled from: PG */
public final class gbr {

    /* renamed from: c */
    public static /* synthetic */ int f10846c;

    /* renamed from: a */
    public final CancellationSignal f10847a = new CancellationSignal();

    /* renamed from: b */
    public final SQLiteDatabase f10848b;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public gbr(SQLiteDatabase sQLiteDatabase) {
        this.f10848b = sQLiteDatabase;
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static void m9983a() {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    /* renamed from: a */
    public final void mo6392a(gbm gbm) {
        m9983a();
        String valueOf = String.valueOf(gbm.f10841a);
        hlj a = hnb.m11766a(valueOf.length() == 0 ? new String("execSQL: ") : "execSQL: ".concat(valueOf), hnf.f13084a);
        try {
            this.f10848b.execSQL(gbm.f10841a, gbm.f10842b);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
