package p000;

import java.io.PrintWriter;
import java.io.StringWriter;

/* renamed from: gwo */
/* compiled from: PG */
final /* synthetic */ class gwo implements hpr {

    /* renamed from: a */
    private final gws f12203a;

    public gwo(gws gws) {
        this.f12203a = gws;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Throwable th = (Throwable) obj;
        gwh gwh = (gwh) this.f12203a;
        if (!gwh.f12187a.contains(gwi.TEXT)) {
            return gwq.f12205a;
        }
        if (gwh.f12188b) {
            StringWriter stringWriter = new StringWriter();
            ifn.m12934a(th, new PrintWriter(stringWriter));
            gwj d = gwk.m10956d();
            d.mo7146a(true);
            d.mo7145a(gwi.TEXT);
            d.f12196a = ihw.m13160a(stringWriter.toString());
            return d.mo7144a();
        }
        StringBuilder sb = new StringBuilder();
        gwp.m10964a(sb, th);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            sb.append("Caused by: ");
            gwp.m10964a(sb, cause);
        }
        gwj d2 = gwk.m10956d();
        d2.mo7146a(false);
        d2.mo7145a(gwi.TEXT);
        d2.f12196a = ihw.m13160a(sb.toString());
        return d2.mo7144a();
    }
}
