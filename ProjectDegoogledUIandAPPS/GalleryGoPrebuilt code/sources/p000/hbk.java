package p000;

import android.content.Context;
import java.io.EOFException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: hbk */
/* compiled from: PG */
public final class hbk implements hbc {

    /* renamed from: a */
    public final Context f12451a;

    /* renamed from: b */
    public final iqk f12452b;

    /* renamed from: c */
    public final iek f12453c;

    /* renamed from: d */
    public final inw f12454d;

    /* renamed from: e */
    public final iqk f12455e;

    /* renamed from: f */
    public final iqk f12456f;

    public hbk(Context context, iqk iqk, iek iek, inw inw, iqk iqk2, iqk iqk3) {
        this.f12451a = context;
        this.f12452b = iqk;
        this.f12453c = iek;
        this.f12454d = inw;
        this.f12455e = iqk3;
        this.f12456f = iqk2;
    }

    /* renamed from: a */
    public final void mo7253a() {
        if (gte.m10775a() && gte.m10777a(this.f12451a)) {
            mo7256a(true);
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public static int m11136a(RandomAccessFile randomAccessFile) {
        try {
            int readInt = randomAccessFile.readInt();
            randomAccessFile.seek(0);
            return readInt;
        } catch (EOFException e) {
            randomAccessFile.seek(0);
            return -1;
        } catch (Throwable th) {
            randomAccessFile.seek(0);
            throw th;
        }
    }

    /* renamed from: a */
    public final void mo7256a(boolean z) {
        hlj a = hnb.m11765a("StartupAfterPackageReplaced");
        try {
            ieh a2 = ife.m12816a(hmq.m11743a((ice) new hbh(this, z)), (Executor) this.f12453c);
            goo goo = (goo) this.f12454d.mo9034a();
            ieh a3 = a.mo7548a(a2);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            a3.mo53a(hmq.m11748a((Runnable) new gol(goo.f11750c.mo5935a(hmq.m11748a((Runnable) new gok(a3, timeUnit)), 30, timeUnit), a3)), goo.f11749b);
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

    /* renamed from: a */
    static void m11137a(RandomAccessFile randomAccessFile, int i) {
        randomAccessFile.writeInt(i);
        randomAccessFile.seek(0);
    }
}
