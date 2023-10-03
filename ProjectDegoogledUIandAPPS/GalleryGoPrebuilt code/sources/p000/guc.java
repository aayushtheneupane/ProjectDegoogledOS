package p000;

/* renamed from: guc */
/* compiled from: PG */
public final class guc {

    /* renamed from: a */
    public static final guc f12067a = new guc((Object) null, 0, false);

    /* renamed from: b */
    public final gub f12068b;

    /* renamed from: c */
    private final Object f12069c;

    /* renamed from: b */
    public final boolean mo7081b() {
        return this.f12068b.f12065b;
    }

    private guc(Object obj, long j, boolean z) {
        boolean z2;
        this.f12069c = obj;
        if (this.f12069c != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f12068b = new gub(j, z2, z);
    }

    /* renamed from: a */
    public static guc m10815a(Object obj, long j) {
        ife.m12898e(obj);
        return new guc(obj, j, true);
    }

    /* renamed from: a */
    public final Object mo7080a() {
        ife.m12876b(mo7081b(), (Object) "Cannot get data for a CacheResult that does not have content");
        return this.f12069c;
    }

    /* renamed from: c */
    public final boolean mo7082c() {
        ife.m12876b(mo7081b(), (Object) "Cannot call isValid() for a CacheResult that does not have content");
        return this.f12068b.f12066c;
    }

    public final String toString() {
        gub gub = this.f12068b;
        if (!gub.f12065b) {
            return "CacheResult.cacheMiss";
        }
        if (gub.f12066c) {
            String valueOf = String.valueOf(this.f12069c);
            long j = this.f12068b.f12064a;
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 59);
            sb.append("CacheResult.cacheHit{data=");
            sb.append(valueOf);
            sb.append(", timestamp=");
            sb.append(j);
            sb.append("}");
            return sb.toString();
        }
        String valueOf2 = String.valueOf(this.f12069c);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 31);
        sb2.append("CacheResult.cacheInvalid{data=");
        sb2.append(valueOf2);
        sb2.append("}");
        return sb2.toString();
    }
}
