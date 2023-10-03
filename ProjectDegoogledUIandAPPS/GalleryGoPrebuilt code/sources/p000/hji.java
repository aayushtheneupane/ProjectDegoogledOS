package p000;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* renamed from: hji */
/* compiled from: PG */
public final class hji {

    /* renamed from: a */
    public long f12852a;

    /* renamed from: b */
    public hpy f12853b = hph.f13219a;

    /* renamed from: c */
    private final Set f12854c = new HashSet();

    private hji() {
    }

    public /* synthetic */ hji(byte[] bArr) {
    }

    /* renamed from: a */
    public final void mo7496a(Collection collection) {
        this.f12854c.addAll(collection);
    }

    /* renamed from: a */
    public final hjj mo7495a() {
        return new hhq(this.f12854c, this.f12852a, this.f12853b);
    }
}
