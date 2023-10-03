package p000;

import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: ctv */
/* compiled from: PG */
public final /* synthetic */ class ctv implements cue {

    /* renamed from: a */
    private final cue[] f5644a;

    public ctv(cue[] cueArr) {
        this.f5644a = cueArr;
    }

    /* renamed from: a */
    public final ieh mo3142a() {
        cue[] cueArr = this.f5644a;
        ArrayList arrayList = new ArrayList(2);
        for (int i = 0; i < 2; i++) {
            arrayList.add(cueArr[i].mo3142a());
        }
        return gte.m10770a(ife.m12819a((Iterable) arrayList), cts.f5641a, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo3143a(ice ice, Executor executor) {
        return cun.m5445a((cue) this, ice, executor);
    }
}
