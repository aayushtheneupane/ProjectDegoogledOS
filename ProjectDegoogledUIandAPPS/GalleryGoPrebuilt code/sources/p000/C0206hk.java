package p000;

import java.util.List;

/* renamed from: hk */
/* compiled from: PG */
public final class C0206hk extends C0010aj implements C0223ia {

    /* renamed from: h */
    public final int f12902h = 54321;

    /* renamed from: i */
    public final C0224ib f12903i;

    /* renamed from: j */
    public C0207hl f12904j;

    /* renamed from: k */
    private C0681z f12905k;

    public C0206hk(C0224ib ibVar) {
        this.f12903i = ibVar;
        if (ibVar.f13817d == null) {
            ibVar.f13817d = this;
            ibVar.f13816c = 54321;
            return;
        }
        throw new IllegalStateException("There is already a listener registered");
    }

    /* renamed from: d */
    public final void mo7503d() {
        if (C0210ho.m11828a(3)) {
            "  Destroying: " + this;
        }
        this.f12903i.mo8242c();
        this.f12903i.f13820g = true;
        C0207hl hlVar = this.f12904j;
        if (hlVar != null) {
            mo511a((C0011ak) hlVar);
            if (hlVar.f12955c) {
                if (C0210ho.m11828a(2)) {
                    "  Resetting: " + hlVar.f12953a;
                }
                fsw fsw = (fsw) hlVar.f12954b;
                fsw.f10549a.clear();
                fsw.f10549a.notifyDataSetChanged();
            }
        }
        C0224ib ibVar = this.f12903i;
        C0223ia iaVar = ibVar.f13817d;
        if (iaVar == null) {
            throw new IllegalStateException("No listener register");
        } else if (iaVar == this) {
            ibVar.f13817d = null;
            ibVar.f13821h = true;
            ibVar.f13819f = false;
            ibVar.f13820g = false;
            ibVar.f13822i = false;
        } else {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo7502c() {
        C0681z zVar = this.f12905k;
        C0207hl hlVar = this.f12904j;
        if (zVar != null && hlVar != null) {
            super.mo511a((C0011ak) hlVar);
            mo513a(zVar, hlVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo509a() {
        if (C0210ho.m11828a(2)) {
            "  Starting: " + this;
        }
        C0224ib ibVar = this.f12903i;
        ibVar.f13819f = true;
        ibVar.f13821h = false;
        ibVar.f13820g = false;
        fst fst = (fst) ibVar;
        List list = fst.f10547j;
        if (list == null) {
            ibVar.mo8242c();
            C0219hx hxVar = (C0219hx) ibVar;
            hxVar.f13568a = new C0218hw(hxVar);
            hxVar.mo8240a();
            return;
        }
        fst.mo6169a(list);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo514b() {
        if (C0210ho.m11828a(2)) {
            "  Stopping: " + this;
        }
        C0224ib ibVar = this.f12903i;
        ibVar.f13819f = false;
        ibVar.mo8242c();
    }

    /* renamed from: a */
    public final void mo511a(C0011ak akVar) {
        super.mo511a(akVar);
        this.f12905k = null;
        this.f12904j = null;
    }

    /* renamed from: a */
    public final void mo7501a(C0681z zVar, C0204hi hiVar) {
        C0207hl hlVar = new C0207hl(this.f12903i, hiVar);
        mo513a(zVar, hlVar);
        C0207hl hlVar2 = this.f12904j;
        if (hlVar2 != null) {
            mo511a((C0011ak) hlVar2);
        }
        this.f12905k = zVar;
        this.f12904j = hlVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("LoaderInfo{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" #");
        sb.append(this.f12902h);
        sb.append(" : ");
        sb.append(this.f12903i.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this.f12903i)));
        sb.append("}}");
        return sb.toString();
    }
}
