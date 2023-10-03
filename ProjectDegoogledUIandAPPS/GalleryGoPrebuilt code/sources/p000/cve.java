package p000;

import java.util.List;

/* renamed from: cve */
/* compiled from: PG */
final /* synthetic */ class cve implements icf {

    /* renamed from: a */
    private final cvm f5723a;

    /* renamed from: b */
    private final cvk f5724b;

    /* renamed from: c */
    private final cvl f5725c;

    /* renamed from: d */
    private final cue f5726d;

    /* renamed from: e */
    private final Runnable f5727e;

    public cve(cvm cvm, cvk cvk, cvl cvl, cue cue, Runnable runnable) {
        this.f5723a = cvm;
        this.f5724b = cvk;
        this.f5725c = cvl;
        this.f5726d = cue;
        this.f5727e = runnable;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        cvm cvm = this.f5723a;
        cvk cvk = this.f5724b;
        cvl cvl = this.f5725c;
        cue cue = this.f5726d;
        Runnable runnable = this.f5727e;
        List list = (List) obj;
        if (list != null && !list.isEmpty()) {
            return cue.mo3143a(new cvg(cvm, list, cue, cvl, runnable, cvk), cvm.f5746a);
        }
        return ife.m12820a((Object) null);
    }
}
