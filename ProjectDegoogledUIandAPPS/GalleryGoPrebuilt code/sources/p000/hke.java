package p000;

import java.util.UUID;

/* renamed from: hke */
/* compiled from: PG */
abstract class hke implements hlp {

    /* renamed from: a */
    private final hlp f12911a;

    /* renamed from: b */
    private final UUID f12912b;

    /* renamed from: c */
    private final String f12913c;

    public hke(String str, hlp hlp) {
        this.f12913c = str;
        this.f12911a = hlp;
        this.f12912b = hlp.mo7508b();
    }

    /* renamed from: a */
    public final hlp mo7507a() {
        return this.f12911a;
    }

    /* renamed from: b */
    public final UUID mo7508b() {
        return this.f12912b;
    }

    /* renamed from: c */
    public final String mo7509c() {
        return this.f12913c;
    }

    public hke(String str, UUID uuid) {
        this.f12913c = str;
        this.f12911a = null;
        this.f12912b = uuid;
    }

    public final void close() {
        hnb.m11772a((hlp) this);
    }

    public final String toString() {
        return hnb.m11780c((hlp) this);
    }
}
