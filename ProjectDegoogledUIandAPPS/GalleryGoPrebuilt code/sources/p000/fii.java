package p000;

import java.util.List;

/* renamed from: fii */
/* compiled from: PG */
public final class fii extends flh {

    /* renamed from: a */
    private final boolean f9716a;

    /* renamed from: b */
    private final int f9717b;

    /* renamed from: c */
    private final hso f9718c;

    public /* synthetic */ fii(boolean z, int i, hso hso) {
        this.f9716a = z;
        this.f9717b = i;
        this.f9718c = hso;
    }

    /* renamed from: a */
    public final boolean mo5772a() {
        return this.f9716a;
    }

    /* renamed from: b */
    public final int mo5773b() {
        return this.f9717b;
    }

    /* renamed from: c */
    public final hso mo5774c() {
        return this.f9718c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof flh) {
            flh flh = (flh) obj;
            return this.f9716a == flh.mo5772a() && this.f9717b == flh.mo5773b() && ife.m12856a((List) this.f9718c, (Object) flh.mo5774c());
        }
    }

    public final int hashCode() {
        return (((((!this.f9716a ? 1237 : 1231) ^ 1000003) * 1000003) ^ this.f9717b) * 1000003) ^ this.f9718c.hashCode();
    }

    public final String toString() {
        boolean z = this.f9716a;
        int i = this.f9717b;
        String valueOf = String.valueOf(this.f9718c);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 91);
        sb.append("PrimesDirStatsConfigurations{enabled=");
        sb.append(z);
        sb.append(", maxFolderDepth=");
        sb.append(i);
        sb.append(", listFilesPatterns=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
