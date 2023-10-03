package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import p003j$.util.Optional;

/* renamed from: cmk */
/* compiled from: PG */
final class cmk implements gud {

    /* renamed from: a */
    public final AtomicReference f4676a = new AtomicReference(Optional.empty());

    /* renamed from: b */
    public final /* synthetic */ cml f4677b;

    public /* synthetic */ cmk(cml cml) {
        this.f4677b = cml;
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return "storage_volume_data_service";
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        ieh a = this.f4677b.f4681d.mo5933a(hmq.m11749a((Callable) new cmj(this)));
        ieh a2 = gte.m10770a(a, (hpr) new cmi(this), (Executor) this.f4677b.f4681d);
        this.f4677b.f4680c.mo7096a(a, (Object) "storage_volume_data_service");
        this.f4677b.f4680c.mo7096a(a2, (Object) "storage_volume_data_service");
        return ife.m12823a(a, a2);
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        guc guc;
        Optional optional = (Optional) this.f4676a.get();
        if (optional.isPresent()) {
            guc = guc.m10815a((List) optional.get(), this.f4677b.f4682e.mo5370a());
        } else {
            guc = guc.f12067a;
        }
        return gpc.m10579a((Object) guc);
    }
}
