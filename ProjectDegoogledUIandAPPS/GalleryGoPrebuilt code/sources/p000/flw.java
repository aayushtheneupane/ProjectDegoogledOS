package p000;

import android.app.Activity;
import android.app.Application;
import android.graphics.Path;
import android.util.Log;
import java.util.Locale;
import java.util.concurrent.Executor;

/* renamed from: flw */
/* compiled from: PG */
public final class flw {
    public flw() {
    }

    public flw(byte[] bArr) {
    }

    /* renamed from: a */
    public static Path m9186a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f3, f4);
        return path;
    }

    /* renamed from: a */
    public static int m9185a(Activity activity) {
        if (activity instanceof hbf) {
            Object n = ((hbf) activity).mo2635n();
            if (n instanceof blo) {
                return ((blo) n).mo2565b();
            }
            String valueOf = String.valueOf(n);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 53);
            sb.append("Activity peer must be an instance of BaseActivityPeer");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }
        String valueOf2 = String.valueOf(activity);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 26);
        sb2.append("Activity must have a peer ");
        sb2.append(valueOf2);
        throw new IllegalArgumentException(sb2.toString());
    }

    /* renamed from: b */
    public static int m9197b(cxi cxi, cxi cxi2) {
        return Integer.compare(m9196b(cxi), m9196b(cxi2));
    }

    /* renamed from: b */
    private static int m9196b(cxi cxi) {
        int a = cya.m5630a(cxi.f5928t);
        int i = 1;
        if (a == 0) {
            a = 1;
        }
        int i2 = a - 1;
        if (i2 != 1) {
            i = 2;
            if (i2 != 2) {
                i = 3;
                if (i2 != 3) {
                    i = 4;
                    if (i2 != 4) {
                        return 0;
                    }
                }
            }
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m9194a(cxi cxi) {
        return (cxi.f5909a & 131072) != 0 && cxi.f5929u.size() > 1;
    }

    /* renamed from: a */
    public static boolean m9195a(cxi cxi, cxi cxi2) {
        ije ije = cxi.f5929u;
        int size = ije.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (cyc.m5645a((cxi) ije.get(i), cxi2)) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    /* renamed from: a */
    public static fkv m9187a(Application application) {
        fkv fkv = new fkv(application);
        fkv.f9926b = new fhq();
        fkv.f9927c = new foc(application);
        return fkv;
    }

    /* renamed from: a */
    public static void m9191a(ieh ieh) {
        ife.m12841a(ieh, (idw) flj.f9983a, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public static void m9192a(String str, String str2, Throwable th, Object... objArr) {
        m9190a(3, str, th, str2, objArr);
    }

    /* renamed from: b */
    public static void m9199b(String str, String str2, Object... objArr) {
        m9189a(3, str, str2, objArr);
    }

    /* renamed from: c */
    public static void m9200c(String str, String str2, Throwable th, Object... objArr) {
        m9190a(6, str, th, str2, objArr);
    }

    /* renamed from: e */
    public static void m9203e(String str, String str2, Object... objArr) {
        m9189a(6, str, str2, objArr);
    }

    /* renamed from: c */
    public static void m9201c(String str, String str2, Object... objArr) {
        m9189a(4, str, str2, objArr);
    }

    /* renamed from: a */
    private static void m9189a(int i, String str, String str2, Object... objArr) {
        if (Log.isLoggable(str, i)) {
            Log.println(i, str, m9188a(str2, objArr));
        }
    }

    /* renamed from: a */
    private static void m9190a(int i, String str, Throwable th, String str2, Object... objArr) {
        if (!Log.isLoggable(str, i)) {
            return;
        }
        if (i == 3) {
            m9188a(str2, objArr);
        } else if (i == 4) {
            m9188a(str2, objArr);
        } else if (i != 5) {
            Log.e(str, m9188a(str2, objArr), th);
        } else {
            Log.w(str, m9188a(str2, objArr), th);
        }
    }

    /* renamed from: a */
    public static String m9188a(String str, Object... objArr) {
        ife.m12898e((Object) objArr);
        return objArr.length != 0 ? String.format(Locale.US, str, objArr) : str;
    }

    /* renamed from: a */
    public static void m9193a(String str, String str2, Object... objArr) {
        m9189a(2, str, str2, objArr);
    }

    /* renamed from: b */
    public static void m9198b(String str, String str2, Throwable th, Object... objArr) {
        m9190a(5, str, th, str2, objArr);
    }

    /* renamed from: d */
    public static void m9202d(String str, String str2, Object... objArr) {
        m9189a(5, str, str2, objArr);
    }
}
