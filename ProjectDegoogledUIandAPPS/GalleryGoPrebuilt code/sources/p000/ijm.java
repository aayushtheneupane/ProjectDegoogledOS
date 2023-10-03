package p000;

/* renamed from: ijm */
/* compiled from: PG */
public class ijm {

    /* renamed from: a */
    public volatile ikf f14351a;

    /* renamed from: b */
    public volatile ihw f14352b;

    static {
        iij.m13501a();
    }

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ijm)) {
            return false;
        }
        ijm ijm = (ijm) obj;
        ikf ikf = this.f14351a;
        ikf ikf2 = ijm.f14351a;
        if (ikf == null && ikf2 == null) {
            return mo8816a().equals(ijm.mo8816a());
        }
        if (ikf != null && ikf2 != null) {
            return ikf.equals(ikf2);
        }
        if (ikf == null) {
            return m13663a(ikf2.mo8774h()).equals(ikf2);
        }
        return ikf.equals(ijm.m13663a(ikf.mo8774h()));
    }

    /* renamed from: a */
    private final ikf m13663a(ikf ikf) {
        if (this.f14351a == null) {
            synchronized (this) {
                if (this.f14351a == null) {
                    try {
                        this.f14351a = ikf;
                        this.f14352b = ihw.f14203b;
                    } catch (ijh e) {
                        this.f14351a = ikf;
                        this.f14352b = ihw.f14203b;
                    }
                }
            }
        }
        return this.f14351a;
    }

    /* renamed from: a */
    public final ihw mo8816a() {
        if (this.f14352b != null) {
            return this.f14352b;
        }
        synchronized (this) {
            if (this.f14352b != null) {
                ihw ihw = this.f14352b;
                return ihw;
            }
            if (this.f14351a != null) {
                this.f14352b = this.f14351a.mo8513b();
            } else {
                this.f14352b = ihw.f14203b;
            }
            ihw ihw2 = this.f14352b;
            return ihw2;
        }
    }
}
