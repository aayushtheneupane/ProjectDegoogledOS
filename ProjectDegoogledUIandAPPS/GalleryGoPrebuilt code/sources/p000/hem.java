package p000;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: hem */
/* compiled from: PG */
public final class hem {

    /* renamed from: a */
    public static final hvy f12585a = hvy.m12261a("com/google/apps/tiktok/monitoring/feedback/FeedbackService");

    /* renamed from: b */
    public final Context f12586b;

    /* renamed from: c */
    public final iel f12587c;

    /* renamed from: d */
    public final iek f12588d;

    /* renamed from: e */
    public final eyj f12589e;

    /* renamed from: f */
    public final ezz f12590f;

    /* renamed from: g */
    public final ezx f12591g;

    /* renamed from: h */
    public final faf f12592h;

    /* renamed from: i */
    private final gwp f12593i;

    /* renamed from: j */
    private final iqk f12594j;

    /* renamed from: k */
    private final fac f12595k;

    /* renamed from: l */
    private final gmg f12596l;

    public hem(Context context, iel iel, iek iek, gwp gwp, iqk iqk, eyj eyj, ezz ezz, ezx ezx, fac fac, faf faf, gmg gmg) {
        this.f12586b = context;
        this.f12587c = iel;
        this.f12588d = iek;
        this.f12593i = gwp;
        this.f12594j = iqk;
        this.f12589e = eyj;
        this.f12590f = ezz;
        this.f12591g = ezx;
        this.f12595k = fac;
        this.f12592h = faf;
        this.f12596l = gmg;
    }

    /* renamed from: a */
    public final ieh mo7339a(hpy hpy) {
        if (!hpy.mo7646a()) {
            return ife.m12820a((Object) hph.f13219a);
        }
        return ibv.m12657a(this.f12596l.mo6844a((gkn) hpy.mo7647b(), gtf.f12011a), heg.f12576a, (Executor) this.f12587c);
    }

    /* renamed from: a */
    public final idq mo7338a(hpy hpy, C0293kq kqVar, hsu hsu) {
        Map map;
        ieh ieh;
        gwp gwp = this.f12593i;
        gwr gwr = new gwr((byte[]) null);
        gwr.mo7151a(false);
        gwr.mo7150a((long) RecyclerView.FOREVER_NS);
        gwr.f12209d = 2;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        ife.m12890c(true);
        gwr.mo7150a(timeUnit.convert(2000, TimeUnit.MILLISECONDS));
        gwr.mo7151a(true);
        hso a = hso.m12034a((Object) gwi.TEXT, (Object) gwi.KEY_VALUE);
        if (a != null) {
            gwr.f12206a = a;
            String str = gwr.f12206a == null ? " acceptTypes" : "";
            if (gwr.f12207b == null) {
                str = str.concat(" allowPii");
            }
            if (gwr.f12208c == null) {
                str = String.valueOf(str).concat(" timeLimitMs");
            }
            if (gwr.f12209d == 0) {
                str = String.valueOf(str).concat(" size");
            }
            if (!str.isEmpty()) {
                String valueOf = String.valueOf(str);
                throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
            }
            gwh gwh = new gwh(gwr.f12206a, gwr.f12207b.booleanValue(), gwr.f12208c.longValue(), gwr.f12209d);
            if (hpy.mo7646a()) {
                map = ((hel) ggq.m10279a(this.f12586b, hel.class, (gkn) hpy.mo7647b())).mo2462h();
            } else {
                map = (Map) ((ioi) this.f12594j).f14599a;
            }
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry entry : map.entrySet()) {
                String str2 = (String) entry.getKey();
                try {
                    ieh = ((gwq) entry.getValue()).mo7149a();
                } catch (Throwable th) {
                    ieh = ife.m12822a(th);
                }
                arrayList.add(gwp.mo7147a(gwh, str2, ieh));
            }
            for (Map.Entry entry2 : hsu.entrySet()) {
                arrayList.add(gwp.mo7147a(gwh, (String) entry2.getKey(), (ieh) entry2.getValue()));
            }
            ieh a2 = ibv.m12657a(ife.m12819a((Iterable) arrayList), gwl.f12199a, gwp.mo7148a(gwh));
            ieh a3 = mo7339a(hpy);
            fab a4 = this.f12595k.mo5438a();
            kqVar.mo5576a(a4);
            return (idq) ibv.m12657a((ieh) idq.m12731c(ife.m12884c(a3, a2).mo8443a(hmq.m11749a((Callable) new heh(this, a3, a4, a2)), (Executor) this.f12587c)), hef.f12575a, (Executor) this.f12587c);
        }
        throw new NullPointerException("Null acceptTypes");
    }
}
