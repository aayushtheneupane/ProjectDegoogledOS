package p000;

import android.net.Uri;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dbs */
/* compiled from: PG */
public final class dbs {

    /* renamed from: a */
    private final iqk f6215a;

    /* renamed from: b */
    private final imp f6216b;

    /* renamed from: c */
    private final dav f6217c;

    /* renamed from: d */
    private final iel f6218d;

    public dbs(iqk iqk, imp imp, dav dav, iel iel) {
        this.f6215a = iqk;
        this.f6216b = imp;
        this.f6217c = dav;
        this.f6218d = iel;
    }

    /* renamed from: a */
    public final ieh mo4039a(Optional optional, Uri uri, dbo dbo, boolean z, boolean z2, boolean z3, String str) {
        hlj a = hnb.m11765a(str);
        try {
            ieh d = ((dbt) this.f6215a.mo2097a()).mo2118a(this.f6216b.mo8999a("Saver")).mo2119a(optional).mo2116a(uri).mo2117a(dbo).mo2123c(z).mo2122b(z2).mo2120a(z3).mo2121a().mo2127d();
            cwn.m5509a(d, "Saver: Failed to save media to %s", uri.toString());
            ieh a2 = a.mo7548a(d);
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final ieh mo4041b(cyd cyd, dbo dbo) {
        return gte.m10771a(this.f6217c.mo4022a(cyd.f5989c), (icf) new dbq(this, cyd, dbo), (Executor) this.f6218d);
    }

    /* renamed from: a */
    public final ieh mo4040a(Optional optional, String str, dbo dbo, boolean z) {
        return gte.m10771a(this.f6217c.f6150a.mo5933a(hmq.m11749a((Callable) new dat((String) ife.m12898e((Object) str), dbo.mo3203a()))), (icf) new dbr(this, optional, dbo, z), (Executor) this.f6218d);
    }

    /* renamed from: a */
    public final ieh mo4038a(cyd cyd, dbo dbo) {
        return gte.m10771a(this.f6217c.mo4022a(cyd.f5989c), (icf) new dbp(this, cyd, dbo), (Executor) this.f6218d);
    }
}
