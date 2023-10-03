package p000;

import com.google.android.apps.photosgo.environment.BuildType;

/* renamed from: cxo */
/* compiled from: PG */
public final class cxo {

    /* renamed from: a */
    public final cyr f5939a;

    /* renamed from: b */
    public final iel f5940b;

    /* renamed from: c */
    public final gus f5941c;

    /* renamed from: d */
    public final inw f5942d;

    /* renamed from: e */
    public final cjr f5943e;

    /* renamed from: f */
    public final cxn f5944f = new cxn(this);

    /* renamed from: g */
    private final BuildType f5945g;

    public cxo(cyr cyr, iel iel, gus gus, inw inw, BuildType buildType, cjr cjr) {
        this.f5939a = cyr;
        this.f5940b = iel;
        this.f5941c = gus;
        this.f5942d = inw;
        this.f5945g = buildType;
        this.f5943e = cjr;
    }

    /* renamed from: a */
    public final ieh mo3942a(cxd cxd) {
        if (cxd.f5887b == 1) {
            BuildType buildType = BuildType.DEV;
            int ordinal = this.f5945g.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                throw new IllegalArgumentException("Unexpected bucket filter in MediaDataService");
            } else if (ordinal == 2 || ordinal == 3) {
                cwn.m5515b((Throwable) new Exception(), "Unexpected bucket filter in MediaDataService.", new Object[0]);
            }
        }
        return ((grf) this.f5944f.mo9237a((Object) cxd)).mo6948a();
    }
}
