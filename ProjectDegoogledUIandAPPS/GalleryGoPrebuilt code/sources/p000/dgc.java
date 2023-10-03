package p000;

import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: dgc */
/* compiled from: PG */
final /* synthetic */ class dgc implements icf {

    /* renamed from: a */
    private final dge f6491a;

    public dgc(dge dge) {
        this.f6491a = dge;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        dge dge = this.f6491a;
        ArrayList arrayList = new ArrayList();
        for (dfy dfy : dfy.values()) {
            dfz dfz = (dfz) dge.f6494a.get(dfy);
            if (dfz != null) {
                arrayList.add(dfz);
            }
        }
        return cun.m5446a((Iterable) arrayList, cun.m5440a(), (Executor) dge.f6496c);
    }
}
