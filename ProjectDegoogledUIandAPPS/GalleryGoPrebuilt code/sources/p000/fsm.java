package p000;

import java.util.HashMap;
import java.util.Map;

/* renamed from: fsm */
/* compiled from: PG */
public final class fsm {

    /* renamed from: c */
    public static final int[] f10531c = {0, 1, 2, 3, 4};

    /* renamed from: a */
    public final int f10532a;

    /* renamed from: b */
    public int f10533b = 0;

    /* renamed from: d */
    private final Map f10534d = new HashMap();

    public fsm(int i) {
        this.f10532a = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof fsm)) {
            fsm fsm = (fsm) obj;
            if (fsm.f10532a == this.f10532a && fsm.mo6150b() == mo6150b()) {
                for (fsl fsl : fsm.mo6149a()) {
                    if (!fsc.m9496a(fsl.f10524a) && !fsl.equals((fsl) this.f10534d.get(Short.valueOf(fsl.f10524a)))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final fsl[] mo6149a() {
        return (fsl[]) this.f10534d.values().toArray(new fsl[this.f10534d.size()]);
    }

    /* renamed from: a */
    public final fsl mo6147a(short s) {
        return (fsl) this.f10534d.get(Short.valueOf(s));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final int mo6150b() {
        return this.f10534d.size();
    }

    public final int hashCode() {
        return ((this.f10532a + 527) * 31) + this.f10534d.hashCode();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6151b(short s) {
        this.f10534d.remove(Short.valueOf(s));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6148a(fsl fsl) {
        fsl.f10528e = this.f10532a;
        fsl fsl2 = (fsl) this.f10534d.put(Short.valueOf(fsl.f10524a), fsl);
    }
}
