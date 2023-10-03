package p000;

import android.content.ComponentCallbacks2;
import java.util.HashSet;
import java.util.Set;

/* renamed from: hds */
/* compiled from: PG */
public final class hds implements hdu {

    /* renamed from: a */
    public final C0147fh f12548a;

    /* renamed from: b */
    public final hdv f12549b;

    public hds(C0147fh fhVar, hdv hdv) {
        this.f12548a = fhVar;
        this.f12549b = hdv;
    }

    /* renamed from: a */
    public final void mo7306a(hdt hdt) {
        ComponentCallbacks2 componentCallbacks2 = hdt.f12551b;
        C0149fj m = this.f12548a.mo5653m();
        ife.m12898e((Object) m);
        m.registerComponentCallbacks(componentCallbacks2);
        hdv hdv = this.f12549b;
        Set set = (Set) hdv.f12556a.get(m);
        if (set != null) {
            set.add(hdt);
        } else {
            HashSet hashSet = new HashSet();
            hashSet.add(hdt);
            hdv.f12556a.put(m, hashSet);
        }
        this.f12548a.mo5ad().mo64a(new hdr(this, m, hdt, componentCallbacks2));
    }
}
