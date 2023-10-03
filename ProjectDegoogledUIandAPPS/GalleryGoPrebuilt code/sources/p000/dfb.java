package p000;

import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: dfb */
/* compiled from: PG */
final /* synthetic */ class dfb implements icf {

    /* renamed from: a */
    private final List f6430a;

    /* renamed from: b */
    private final cyr f6431b;

    public dfb(List list, cyr cyr) {
        this.f6430a = list;
        this.f6431b = cyr;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        List list = this.f6430a;
        cyr cyr = this.f6431b;
        int i = dfj.f6440a;
        if (((Boolean) obj).booleanValue()) {
            cyf M = ((cyg) list.get(0)).mo3906M();
            M.mo3988i(true);
            list.set(0, M.mo3966c());
        }
        return gte.m10770a(cyr.mo3997a(list), dez.f6420a, (Executor) idh.f13918a);
    }
}
