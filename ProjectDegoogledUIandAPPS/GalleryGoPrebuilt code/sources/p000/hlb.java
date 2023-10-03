package p000;

import java.util.UUID;

/* renamed from: hlb */
/* compiled from: PG */
final class hlb extends hke implements hkt {

    /* renamed from: a */
    private final Exception f12958a;

    public hlb(String str) {
        super(str, UUID.randomUUID());
        this.f12958a = hla.f12956a;
    }

    /* renamed from: a */
    public final void mo7544a(int i) {
    }

    /* renamed from: a */
    public final void mo7545a(boolean z) {
    }

    /* renamed from: d */
    public final Exception mo7535d() {
        return this.f12958a;
    }

    /* renamed from: e */
    public final boolean mo7546e() {
        return false;
    }

    public hlb(String str, hkt hkt) {
        super(str, (hlp) hkt);
        this.f12958a = hkt.mo7535d();
    }

    /* renamed from: a */
    public final hlp mo7543a(String str, hln hln) {
        return new hlb(str, this);
    }

    /* renamed from: a */
    public final hlk mo7536a(hok hok) {
        return hlk.m11693a(2);
    }
}
