package p000;

import java.util.logging.Level;

/* renamed from: hxl */
/* compiled from: PG */
final class hxl implements hwz {

    /* renamed from: a */
    private final String f13587a;

    /* renamed from: b */
    private final hwz f13588b;

    public /* synthetic */ hxl(RuntimeException runtimeException, hwz hwz) {
        StringBuilder sb = new StringBuilder("LOGGING ERROR: ");
        sb.append(runtimeException.getMessage());
        sb.append("\n  original message: ");
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
                sb.append(l.mo8193a(i));
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
        this.f13587a = sb.toString();
        this.f13588b = hwz;
    }

    /* renamed from: h */
    public final hxk mo8213h() {
        return null;
    }

    /* renamed from: j */
    public final Object mo8215j() {
        return this.f13587a;
    }

    /* renamed from: k */
    public final boolean mo8216k() {
        return false;
    }

    /* renamed from: l */
    public final hxd mo8217l() {
        return hxc.f13571a;
    }

    /* renamed from: i */
    public final Object[] mo8214i() {
        throw new IllegalStateException();
    }

    /* renamed from: d */
    public final Level mo8209d() {
        return this.f13588b.mo8209d().intValue() > Level.WARNING.intValue() ? this.f13588b.mo8209d() : Level.WARNING;
    }

    /* renamed from: g */
    public final hwg mo8212g() {
        return this.f13588b.mo8212g();
    }

    /* renamed from: f */
    public final String mo8211f() {
        return this.f13588b.mo8211f();
    }

    /* renamed from: e */
    public final long mo8210e() {
        return this.f13588b.mo8210e();
    }
}
