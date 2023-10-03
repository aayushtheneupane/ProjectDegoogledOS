package p000;

import android.util.Log;
import java.util.logging.Level;

/* renamed from: hxz */
/* compiled from: PG */
public final class hxz extends hxm implements hxh {

    /* renamed from: b */
    private final String f13606b;

    /* renamed from: c */
    private final Boolean f13607c;

    public hxz(String str, String str2) {
        this(str, str2, (byte[]) null);
    }

    private hxz(String str, String str2, byte[] bArr) {
        super(str2);
        if (str.length() + str2.length() <= 23) {
            String valueOf = String.valueOf(str2);
            this.f13606b = valueOf.length() == 0 ? new String(str) : str.concat(valueOf);
        } else {
            String replace = str2.replace('$', '.');
            String valueOf2 = String.valueOf(replace.substring(replace.lastIndexOf(46) + 1));
            String str3 = valueOf2.length() == 0 ? new String(str) : str.concat(valueOf2);
            this.f13606b = str3.substring(0, Math.min(str3.length(), 23));
        }
        this.f13607c = false;
    }

    public /* synthetic */ hxz(String str, String str2, char[] cArr) {
        this(str, str2, (byte[]) null);
    }

    /* renamed from: b */
    private static int m12432b(Level level) {
        int intValue = level.intValue();
        if (intValue >= 1000) {
            return 6;
        }
        if (intValue >= 900) {
            return 5;
        }
        if (intValue < 800) {
            return intValue >= 700 ? 3 : 2;
        }
        return 4;
    }

    /* renamed from: a */
    public final void mo8252a(Level level, String str, Throwable th) {
        int b = m12432b(level);
        if (b != 2 && b != 3 && b != 4) {
            if (b != 5) {
                Log.e(this.f13606b, str, th);
            } else {
                Log.w(this.f13606b, str, th);
            }
        }
    }

    /* renamed from: a */
    public final boolean mo7301a(Level level) {
        return Log.isLoggable(this.f13606b, m12432b(level)) || Log.isLoggable("all", m12432b(level));
    }

    /* renamed from: a */
    public final void mo7299a(hwz hwz) {
        int i;
        if (!this.f13607c.booleanValue()) {
            i = 1;
        } else {
            i = 2;
        }
        hxi.m12394a(hwz, (hxh) this, i);
    }
}
