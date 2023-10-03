package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: edb */
/* compiled from: PG */
final class edb implements gvc {

    /* renamed from: a */
    private final /* synthetic */ edc f8014a;

    public edb(edc edc) {
        this.f8014a = edc;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5511a(th, "SharingFragment: Error getting fragment data.", new Object[0]);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        efd efd = (efd) obj;
        ect ect = this.f8014a.f8015a;
        List a = efd.mo4762a();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a);
        arrayList.add(new ecr(ect));
        ect.f7939b.mo7129a((List) arrayList);
        if (this.f8014a.mo4718a()) {
            this.f8014a.f8018d.setChecked(efd.mo4763b());
        }
    }
}
