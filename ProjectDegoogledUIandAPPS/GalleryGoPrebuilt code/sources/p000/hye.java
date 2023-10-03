package p000;

import java.io.PrintStream;
import java.util.logging.Level;

/* renamed from: hye */
/* compiled from: PG */
public final class hye extends hxg {

    /* renamed from: e */
    private final hyc f13611e;

    /* renamed from: f */
    private final hyg f13612f;

    /* renamed from: g */
    private final hyd f13613g;

    /* renamed from: h */
    private final hxf f13614h;

    public hye() {
        hyc hyc = (hyc) m12448a("backend_factory", hyc.class);
        this.f13611e = hyc == null ? hyh.f13616a : hyc;
        hyg hyg = (hyg) m12448a("logging_context", hyg.class);
        this.f13612f = hyg == null ? hyf.f13615a : hyg;
        hyd hyd = (hyd) m12448a("clock", hyd.class);
        this.f13613g = hyd == null ? hyj.f13618a : hyd;
        this.f13614h = hyi.f13617a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final hxf mo8247b() {
        return this.f13614h;
    }

    /* renamed from: a */
    private static void m12449a(String str, Object... objArr) {
        PrintStream printStream = System.err;
        String valueOf = String.valueOf(hye.class);
        String format = String.format(str, objArr);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(format).length());
        sb.append(valueOf);
        sb.append(": ");
        sb.append(format);
        printStream.println(sb.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final hxa mo8246b(String str) {
        return this.f13611e.mo8263a(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public final String mo8251h() {
        String name = getClass().getName();
        String valueOf = String.valueOf(this.f13611e);
        String valueOf2 = String.valueOf(this.f13613g);
        String valueOf3 = String.valueOf(this.f13612f);
        String valueOf4 = String.valueOf(this.f13614h);
        int length = String.valueOf(name).length();
        int length2 = String.valueOf(valueOf).length();
        int length3 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 71 + length2 + length3 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Platform: ");
        sb.append(name);
        sb.append("\nBackendFactory: ");
        sb.append(valueOf);
        sb.append("\nClock: ");
        sb.append(valueOf2);
        sb.append("\nLoggingContext: ");
        sb.append(valueOf3);
        sb.append("\nLogCallerFinder: ");
        sb.append(valueOf4);
        sb.append("\n");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public final long mo8250f() {
        return this.f13613g.mo8264a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final hxj mo8249d() {
        return this.f13612f.mo8265a();
    }

    /* renamed from: a */
    private static Object m12448a(String str, Class cls) {
        String str2;
        ife.m12827a((Object) str, "attribute name");
        String str3 = str.length() == 0 ? new String("flogger.") : "flogger.".concat(str);
        try {
            str2 = System.getProperty(str3);
        } catch (SecurityException e) {
            m12449a("cannot read property name %s: %s", str3, e);
            str2 = null;
        }
        if (str2 == null) {
            return null;
        }
        int indexOf = str2.indexOf(35);
        if (indexOf > 0 && indexOf != str2.length() - 1) {
            String substring = str2.substring(0, indexOf);
            String substring2 = str2.substring(indexOf + 1);
            try {
                return cls.cast(Class.forName(substring).getMethod(substring2, new Class[0]).invoke((Object) null, new Object[0]));
            } catch (ClassNotFoundException e2) {
                return null;
            } catch (ClassCastException e3) {
                m12449a("cannot cast result of calling '%s#%s' to '%s': %s\n", substring, substring2, cls.getName(), e3);
                return null;
            } catch (Exception e4) {
                m12449a("cannot call expected no-argument static method '%s#%s': %s\n", substring, substring2, e4);
                return null;
            }
        } else {
            m12449a("invalid getter (expected <class>#<method>): %s\n", str2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo8248b(String str, Level level, boolean z) {
        return this.f13612f.mo8266b();
    }
}
