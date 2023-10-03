package p000;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: gzn */
/* compiled from: PG */
final class gzn {

    /* renamed from: a */
    public final fzx f12355a;

    /* renamed from: b */
    public final Map f12356b;

    /* renamed from: c */
    public final grf f12357c = new grf(new gyx(this), idh.f13918a);

    /* renamed from: d */
    public final int f12358d;

    /* renamed from: e */
    public final gzm f12359e;

    /* renamed from: f */
    public final Executor f12360f;

    /* renamed from: g */
    public final iek f12361g;

    /* renamed from: h */
    public final gxs f12362h;

    /* renamed from: i */
    public final exm f12363i;

    /* renamed from: j */
    public final ice f12364j;

    public gzn(exm exm, gzm gzm, Map map, int i, iek iek, Executor executor, fzx fzx, gxs gxs, ice ice) {
        this.f12363i = exm;
        this.f12359e = gzm;
        this.f12361g = iek;
        this.f12360f = executor;
        this.f12355a = fzx;
        this.f12356b = map;
        this.f12358d = i;
        this.f12362h = gxs;
        this.f12364j = ice;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo7224a() {
        try {
            return this.f12357c.mo6950b() && ((gyu) ife.m12871b((Future) this.f12357c.mo6948a())).mo7193a();
        } catch (ExecutionException e) {
            return false;
        }
    }

    /* renamed from: a */
    public final ieh mo7222a(gwx gwx) {
        return this.f12361g.mo5933a(hmq.m11749a((Callable) new gza(this, gwx)));
    }

    /* renamed from: a */
    public static final void m11083a(ieh ieh, String str) {
        ife.m12841a(ieh, (idw) new gzl(str), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final void mo7223a(ieh ieh) {
        m11083a(ibv.m12658a(ieh, hmq.m11744a((icf) new gzi(this)), (Executor) this.f12361g), "Failed to commit to config");
    }
}
