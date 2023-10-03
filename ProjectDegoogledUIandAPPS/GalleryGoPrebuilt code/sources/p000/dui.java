package p000;

import java.util.concurrent.Executor;

/* renamed from: dui */
/* compiled from: PG */
final class dui implements hol {

    /* renamed from: a */
    private final /* synthetic */ dug f7410a;

    public dui(dug dug) {
        this.f7410a = dug;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        dug dug = this.f7410a;
        dug.f7408r = 1;
        hsj j = hso.m12048j();
        hvr a = ((dub) hoi).f7382a.iterator();
        while (a.hasNext()) {
            cia cia = (cia) a.next();
            if (cia.mo3107a().isPresent()) {
                j.mo7908c((Long) cia.mo3107a().get());
            }
        }
        ble ble = dug.f7397g;
        cjh cjh = dug.f7403m;
        hso a2 = j.mo7905a();
        cwn.m5509a(ble.mo2553a(gte.m10778b(cjh.f4490a.mo2656a(new cjg(a2)), cjh.f4490a.mo2656a(new cit(a2))).mo7613a(ciu.f4474a, (Executor) idh.f13918a), dug.f7398h), "PeopleGridFragmentPeer: failed to update person visibility.", new Object[0]);
        return hom.f13155a;
    }
}
