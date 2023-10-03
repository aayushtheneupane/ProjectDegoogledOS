package p000;

import java.util.List;

/* renamed from: efl */
/* compiled from: PG */
final class efl extends efq {

    /* renamed from: a */
    private final abt f8157a;

    /* renamed from: b */
    private final hso f8158b;

    public efl(abt abt, hso hso) {
        this.f8157a = abt;
        if (hso != null) {
            this.f8158b = hso;
            return;
        }
        throw new NullPointerException("Null segments");
    }

    /* renamed from: a */
    public final abt mo4775a() {
        return this.f8157a;
    }

    /* renamed from: b */
    public final hso mo4776b() {
        return this.f8158b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof efq) {
            efq efq = (efq) obj;
            return this.f8157a.equals(efq.mo4775a()) && ife.m12856a((List) this.f8158b, (Object) efq.mo4776b());
        }
    }

    public final int hashCode() {
        return ((this.f8157a.hashCode() ^ 1000003) * 1000003) ^ this.f8158b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f8157a);
        String valueOf2 = String.valueOf(this.f8158b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45 + String.valueOf(valueOf2).length());
        sb.append("VolumeRootAndSegments{volumeRoot=");
        sb.append(valueOf);
        sb.append(", segments=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
