package p000;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: hde */
/* compiled from: PG */
public final class hde implements hfl {

    /* renamed from: e */
    public static /* synthetic */ int f12516e;

    /* renamed from: f */
    private static final ido f12517f = ido.m12729a();

    /* renamed from: a */
    public final Context f12518a;

    /* renamed from: b */
    public final ext f12519b;

    /* renamed from: c */
    public final hdh f12520c;

    /* renamed from: d */
    public final iek f12521d;

    public hde(Context context, ext ext, hdh hdh, iek iek) {
        this.f12518a = context;
        this.f12519b = ext;
        this.f12520c = hdh;
        this.f12521d = iek;
    }

    /* renamed from: a */
    public static ieh m11232a(byte[] bArr, int i, String str, Context context, hdh hdh, ext ext, iek iek, String str2) {
        hdh hdh2 = hdh;
        long length = (long) bArr.length;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = -86400000 + elapsedRealtime;
        if (j > 0) {
            hdg hdg = (hdg) hdh2.f12522a.peek();
            while (hdg != null && hdg.mo7288a() <= j) {
                if (hdh2.f12522a.remove(hdg)) {
                    hdh2.f12523b.addAndGet(-hdg.mo7289b());
                }
                hdg = (hdg) hdh2.f12522a.peek();
            }
        }
        long j2 = hdh2.f12523b.get();
        for (int i2 = 0; i2 < 10 && j2 + length < 1048576; i2++) {
            j2 = hdh2.f12523b.get();
            if (hdh2.f12523b.compareAndSet(j2, j2 + length)) {
                hdh2.f12522a.offer(new hct(elapsedRealtime, length));
                ieh a = gpc.m10575a((gpd) new hcz(ext, context, str2, str, bArr, i), (Executor) iek).mo6894a();
                a.mo53a(new hda(a), idh.f13918a);
                return ibd.m12603a(a, Exception.class, hdb.f12514a, (Executor) idh.f13918a);
            }
            iek iek2 = iek;
        }
        Log.w("ClientLoggingReceiver", "Log rate too high, dropping logs.");
        return ife.m12820a((Object) null);
    }

    /* renamed from: a */
    public final ieh mo2555a(Intent intent) {
        return f12517f.mo8417a(new hcv(this, intent), this.f12521d);
    }

    /* renamed from: a */
    static ieh m11231a(hdd hdd, Context context, hdh hdh, ext ext, iek iek, String str) {
        Context applicationContext = context.getApplicationContext();
        ieh a = iek.mo5933a((Callable) new hcw(hdd));
        a.mo53a(new hcx(a), iek);
        return ibv.m12658a(a, hmq.m11744a((icf) new hcy(hdd, applicationContext, hdh, ext, iek, str)), (Executor) iek);
    }
}
