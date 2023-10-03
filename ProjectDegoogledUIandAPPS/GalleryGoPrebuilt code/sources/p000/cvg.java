package p000;

import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: cvg */
/* compiled from: PG */
final /* synthetic */ class cvg implements ice {

    /* renamed from: a */
    private final cvm f5729a;

    /* renamed from: b */
    private final List f5730b;

    /* renamed from: c */
    private final cue f5731c;

    /* renamed from: d */
    private final cvl f5732d;

    /* renamed from: e */
    private final Runnable f5733e;

    /* renamed from: f */
    private final cvk f5734f;

    public cvg(cvm cvm, List list, cue cue, cvl cvl, Runnable runnable, cvk cvk) {
        this.f5729a = cvm;
        this.f5730b = list;
        this.f5731c = cue;
        this.f5732d = cvl;
        this.f5733e = runnable;
        this.f5734f = cvk;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        cvm cvm = this.f5729a;
        List<Object> list = this.f5730b;
        cue cue = this.f5731c;
        cvl cvl = this.f5732d;
        Runnable runnable = this.f5733e;
        cvk cvk = this.f5734f;
        ieh a = ife.m12820a((Object) null);
        for (Object cvh : list) {
            ieh[] iehArr = {a};
            a = gte.m10769a(iehArr).mo7611a((ice) new cvh(cvm, cue, cvl, cvh), (Executor) cvm.f5746a);
        }
        return gte.m10769a(a).mo7611a((ice) new cvi(cvm, runnable, cvk, cvl, cue), (Executor) cvm.f5746a);
    }
}
