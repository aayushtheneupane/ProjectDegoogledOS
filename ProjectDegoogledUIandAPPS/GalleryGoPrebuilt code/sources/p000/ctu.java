package p000;

import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: ctu */
/* compiled from: PG */
final /* synthetic */ class ctu implements cue {

    /* renamed from: a */
    private final cue[] f5643a;

    public ctu(cue[] cueArr) {
        this.f5643a = cueArr;
    }

    /* renamed from: a */
    public final ieh mo3142a() {
        ArrayList arrayList = new ArrayList(r1);
        for (cue a : this.f5643a) {
            arrayList.add(a.mo3142a());
        }
        return gte.m10770a(ife.m12819a((Iterable) arrayList), ctt.f5642a, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo3143a(ice ice, Executor executor) {
        return cun.m5445a((cue) this, ice, executor);
    }
}
