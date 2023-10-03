package p000;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* renamed from: hyb */
/* compiled from: PG */
public final class hyb extends LogRecord implements hxh {

    /* renamed from: a */
    private final hwz f13610a;

    private hyb(hwz hwz) {
        super(hwz.mo8209d(), (String) null);
        this.f13610a = hwz;
        hwg g = hwz.mo8212g();
        setSourceClassName(g.mo8219a());
        setSourceMethodName(g.mo8220b());
        setLoggerName(hwz.mo8211f());
        setMillis(TimeUnit.NANOSECONDS.toMillis(hwz.mo8210e()));
    }

    /* renamed from: a */
    private static void m12444a(hwz hwz, StringBuilder sb) {
        sb.append("  original message: ");
        if (hwz.mo8213h() == null) {
            sb.append(hwz.mo8215j());
        } else {
            sb.append(hwz.mo8213h().f13586b);
            sb.append("\n  original arguments:");
            for (Object a : hwz.mo8214i()) {
                sb.append("\n    ");
                sb.append(hxi.m12392a(a));
            }
        }
        hxd l = hwz.mo8217l();
        if (l.mo8191a() > 0) {
            sb.append("\n  metadata:");
            for (int i = 0; i < l.mo8191a(); i++) {
                sb.append("\n    ");
                sb.append(l.mo8193a(i).f13524a);
                sb.append(": ");
                sb.append(l.mo8194b(i));
            }
        }
        sb.append("\n  level: ");
        sb.append(hwz.mo8209d());
        sb.append("\n  timestamp (nanos): ");
        sb.append(hwz.mo8210e());
        sb.append("\n  class: ");
        sb.append(hwz.mo8212g().mo8219a());
        sb.append("\n  method: ");
        sb.append(hwz.mo8212g().mo8220b());
        sb.append("\n  line number: ");
        sb.append(hwz.mo8212g().mo8221c());
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" {\n  message: ");
        sb.append(getMessage());
        sb.append("\n  arguments: ");
        if (getParameters() != null) {
            obj = Arrays.asList(getParameters());
        } else {
            obj = "<none>";
        }
        sb.append(obj);
        sb.append(10);
        m12444a(this.f13610a, sb);
        sb.append("\n}");
        return sb.toString();
    }

    public hyb(hwz hwz, byte[] bArr) {
        this(hwz);
        hxi.m12394a(hwz, (hxh) this, 1);
    }

    public hyb(RuntimeException runtimeException, hwz hwz) {
        this(hwz);
        setLevel(hwz.mo8209d().intValue() < Level.WARNING.intValue() ? Level.WARNING : hwz.mo8209d());
        setThrown(runtimeException);
        StringBuilder sb = new StringBuilder("LOGGING ERROR: ");
        sb.append(runtimeException.getMessage());
        sb.append(10);
        m12444a(hwz, sb);
        setMessage(sb.toString());
    }

    /* renamed from: a */
    public final void mo8252a(Level level, String str, Throwable th) {
        setMessage(str);
        setThrown(th);
    }
}
