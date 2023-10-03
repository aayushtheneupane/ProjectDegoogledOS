package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: hgv */
/* compiled from: PG */
public final class hgv {

    /* renamed from: a */
    private long f12714a;

    /* renamed from: b */
    private final long f12715b = TimeUnit.MILLISECONDS.convert(3, TimeUnit.MINUTES);

    /* renamed from: c */
    private final List f12716c = new ArrayList();

    private hgv() {
    }

    public /* synthetic */ hgv(byte[] bArr) {
    }

    /* renamed from: a */
    public final void mo7432a(hgy hgy) {
        this.f12716c.add(hgy);
    }

    /* renamed from: a */
    public final hgw mo7430a() {
        ife.m12876b(this.f12714a != 0, (Object) "You must specify a minimum sync interval for all syncs.");
        hsq g = hsu.m12070g();
        List list = this.f12716c;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            hgy hgy = (hgy) list.get(i);
            g.mo7932a(hgy.mo7419a(), hgy);
        }
        return new hgs(this.f12714a, this.f12715b, g.mo7930a());
    }

    /* renamed from: a */
    public final void mo7431a(long j, TimeUnit timeUnit) {
        this.f12714a = TimeUnit.MILLISECONDS.convert(j, timeUnit);
    }
}
