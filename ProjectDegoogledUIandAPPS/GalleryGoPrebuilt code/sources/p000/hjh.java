package p000;

import java.util.Arrays;

/* renamed from: hjh */
/* compiled from: PG */
public final class hjh {

    /* renamed from: a */
    public final hka f12849a;

    /* renamed from: b */
    public final hha f12850b;

    /* renamed from: c */
    public final gkn f12851c;

    public hjh(hka hka) {
        this.f12849a = hka;
        hjz hjz = hka.f12909b;
        this.f12850b = new hha(hjz == null ? hjz.f12898c : hjz);
        if ((hka.f12908a & 2) != 0) {
            this.f12851c = gkn.m10445a(hka.f12910c, gtf.f12011a);
        } else {
            this.f12851c = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo7492a() {
        return this.f12851c != null;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hjh) {
            hjh hjh = (hjh) obj;
            if (this.f12850b.equals(hjh.f12850b)) {
                gkn gkn = this.f12851c;
                gkn gkn2 = hjh.f12851c;
                if (gkn != null) {
                    if (gkn.equals(gkn2)) {
                        return true;
                    }
                } else if (gkn2 != null) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    static hjh m11577a(hka hka) {
        return new hjh(hka);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f12850b, this.f12851c});
    }
}
