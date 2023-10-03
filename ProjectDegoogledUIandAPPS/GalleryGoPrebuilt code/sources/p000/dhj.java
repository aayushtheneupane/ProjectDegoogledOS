package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import p003j$.util.Optional;

/* renamed from: dhj */
/* compiled from: PG */
public final class dhj implements gud {

    /* renamed from: a */
    private final String f6545a;

    /* renamed from: b */
    private ieh f6546b;

    /* renamed from: c */
    private ieh f6547c;

    /* renamed from: d */
    private final /* synthetic */ dhk f6548d;

    public dhj(dhk dhk, String str) {
        this.f6548d = dhk;
        this.f6545a = str;
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ Object mo2735c() {
        return "editor_data";
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        ieh ieh;
        ieh ieh2;
        String str = this.f6545a;
        if (str != null) {
            ieh = gte.m10770a(this.f6548d.f6551c.mo4154a((String) ife.m12898e((Object) str)), (hpr) new dhh(), (Executor) idh.f13918a);
        } else {
            ieh = ife.m12820a((Object) Optional.empty());
        }
        this.f6547c = ieh;
        dho dho = (dho) this.f6548d.f6550b;
        if (dho.f6557b.isPresent()) {
            ieh2 = ife.m12820a((Object) (Optional) dho.f6557b.get());
        } else {
            dht dht = dho.f6556a;
            hlj a = hnb.m11765a("Loading preferred editor data");
            try {
                ieh a2 = a.mo7548a(dht.f6564c.mo5933a(hmq.m11749a((Callable) new dhs(dht))));
                if (a != null) {
                    a.close();
                }
                ieh2 = gte.m10770a(a2, (hpr) new dhn(dho), (Executor) idh.f13918a);
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        this.f6546b = ieh2;
        return gte.m10769a((ieh) ife.m12898e((Object) this.f6547c), (ieh) ife.m12898e((Object) this.f6546b)).mo7613a(dhg.f6543a, (Executor) idh.f13918a);
        throw th;
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        guc guc;
        try {
            ieh ieh = this.f6547c;
            if (ieh != null && ieh.isDone()) {
                Optional optional = (Optional) ife.m12871b((Future) this.f6547c);
                if (optional.isPresent()) {
                    guc = guc.m10815a(optional, this.f6548d.f6549a.mo5370a());
                } else {
                    ieh ieh2 = this.f6546b;
                    if (ieh2 != null && ieh2.isDone()) {
                        guc = guc.m10815a((Optional) ife.m12871b((Future) this.f6546b), this.f6548d.f6549a.mo5370a());
                    }
                }
                return gpc.m10579a((Object) guc);
            }
            guc = guc.f12067a;
            return gpc.m10579a((Object) guc);
        } catch (ExecutionException e) {
            return gpc.m10579a((Object) guc.m10815a(Optional.empty(), this.f6548d.f6549a.mo5370a()));
        }
    }
}
