package p000;

import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: gyt */
/* compiled from: PG */
public final class gyt {

    /* renamed from: a */
    public final gyc f12320a;

    /* renamed from: b */
    public final gyc f12321b;

    /* renamed from: c */
    public final Map f12322c;

    /* renamed from: d */
    private final fqq f12323d;

    public gyt(gyc gyc, gyc gyc2, Map map, fqq fqq) {
        this.f12320a = gyc;
        this.f12321b = gyc2;
        this.f12322c = map;
        this.f12323d = fqq;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo7214a(String str) {
        hlj a = hnb.m11765a("Fetching experiments for account");
        try {
            ieh a2 = a.mo7548a(ibv.m12658a(this.f12323d.mo6033a(str, ""), hmq.m11744a((icf) new gyk(this, str)), (Executor) idh.f13918a));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
