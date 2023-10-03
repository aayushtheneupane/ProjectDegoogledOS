package p000;

import android.content.Context;
import java.util.concurrent.Executor;

/* renamed from: fqp */
/* compiled from: PG */
public final class fqp implements fqq {

    /* renamed from: a */
    public final fbq f10283a;

    /* renamed from: b */
    public final eyg f10284b;

    /* renamed from: c */
    public final fbp f10285c;

    /* renamed from: d */
    public final fbm f10286d;

    /* renamed from: e */
    public final eyj f10287e;

    /* renamed from: f */
    public final Executor f10288f;

    /* renamed from: g */
    public final Context f10289g;

    public fqp(Context context, eyg eyg, fbq fbq, fbn fbn, fbm fbm, eyj eyj, Executor executor) {
        this.f10284b = eyg;
        this.f10285c = fbn.mo5476a();
        this.f10283a = fbq;
        this.f10286d = fbm;
        this.f10287e = eyj;
        this.f10289g = context;
        this.f10288f = executor;
    }

    /* renamed from: a */
    public final ieh mo6033a(String str, String str2) {
        ife.m12898e((Object) str);
        ife.m12898e((Object) str2);
        return m9426a(gqr.m10639a(this.f10287e.mo5393a(this.f10289g), (eyh) this.f10283a, (gqo) new fqn(this, str, str2), this.f10288f).mo6895a((hpr) new fqk(), (Executor) idh.f13918a));
    }

    /* renamed from: a */
    public static ieh m9426a(ieh ieh) {
        return ibd.m12604a(ieh, gqq.class, fql.f10272a, (Executor) idh.f13918a);
    }
}
