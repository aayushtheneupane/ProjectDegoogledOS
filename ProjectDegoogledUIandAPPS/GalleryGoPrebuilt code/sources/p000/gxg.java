package p000;

import android.content.Context;
import java.util.concurrent.Executor;

/* renamed from: gxg */
/* compiled from: PG */
public final class gxg implements gxy {

    /* renamed from: a */
    private final inw f12245a;

    /* renamed from: b */
    private final inw f12246b;

    /* renamed from: c */
    private final Context f12247c;

    public gxg(inw inw, inw inw2, Context context) {
        this.f12245a = inw;
        this.f12246b = inw2;
        this.f12247c = context;
    }

    /* renamed from: a */
    public final ieh mo7174a(String str, gkn gkn) {
        return ibv.m12658a(ibv.m12657a(((gld) this.f12245a.mo9034a()).f11565a.mo6834a(gkn), glc.f11564a, (Executor) idh.f13918a), hmq.m11744a((icf) new gxd(this, str, gkn)), (Executor) idh.f13918a);
    }

    /* renamed from: b */
    public final ieh mo7175b(String str, gkn gkn) {
        hbb g = ((gxf) ggq.m10279a(this.f12247c, gxf.class, gkn)).mo2461g();
        gmg gmg = (gmg) this.f12246b.mo9034a();
        hlj a = hnb.m11765a("Fetching experiments for account");
        try {
            ieh a2 = a.mo7548a(ibv.m12658a(g.f12442c.mo6033a(str, ""), hmq.m11744a((icf) new hau(g, str)), (Executor) idh.f13918a));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final ieh mo7173a(String str) {
        return ibv.m12658a(((gld) this.f12245a.mo9034a()).f11565a.mo6833a(), hmq.m11744a((icf) new gxe(this, str)), (Executor) idh.f13918a);
    }
}
