package p000;

import java.util.Map;

/* renamed from: gwt */
/* compiled from: PG */
final class gwt extends gwz {

    /* renamed from: a */
    private final gwy f12210a;

    /* renamed from: b */
    private final Map f12211b;

    public gwt(gwy gwy, Map map) {
        this.f12210a = gwy;
        if (map != null) {
            this.f12211b = map;
            return;
        }
        throw new NullPointerException("Null flagValues");
    }

    /* renamed from: a */
    public final gwy mo7152a() {
        return this.f12210a;
    }

    /* renamed from: b */
    public final Map mo7153b() {
        return this.f12211b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gwz) {
            gwz gwz = (gwz) obj;
            return this.f12210a.equals(gwz.mo7152a()) && this.f12211b.equals(gwz.mo7153b());
        }
    }

    public final int hashCode() {
        return ((this.f12210a.hashCode() ^ 1000003) * 1000003) ^ this.f12211b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12210a);
        String valueOf2 = String.valueOf(this.f12211b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 42 + String.valueOf(valueOf2).length());
        sb.append("FlagSnapshotHolder{metadata=");
        sb.append(valueOf);
        sb.append(", flagValues=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
