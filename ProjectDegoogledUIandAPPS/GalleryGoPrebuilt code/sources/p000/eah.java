package p000;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: eah */
/* compiled from: PG */
public final class eah implements gud {

    /* renamed from: a */
    private final gus f7768a;

    /* renamed from: b */
    private final exm f7769b;

    /* renamed from: c */
    private final String f7770c;

    /* renamed from: d */
    private final AtomicInteger f7771d = new AtomicInteger(0);

    public eah(gus gus, exm exm) {
        this.f7768a = gus;
        this.f7769b = exm;
        String valueOf = String.valueOf(UUID.randomUUID());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
        sb.append("progress_data_source_");
        sb.append(valueOf);
        this.f7770c = sb.toString();
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ Object mo2735c() {
        return this.f7770c;
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        return ife.m12820a((Object) null);
    }

    /* renamed from: d */
    public final void mo4625d() {
        this.f7771d.incrementAndGet();
        this.f7768a.mo7096a(ife.m12820a((Object) null), (Object) this.f7770c);
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        return gpc.m10579a((Object) guc.m10815a(Integer.valueOf(this.f7771d.get()), this.f7769b.mo5370a()));
    }
}
