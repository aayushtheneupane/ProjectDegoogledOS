package p000;

import java.io.IOException;
import java.util.concurrent.Callable;

/* renamed from: hit */
/* compiled from: PG */
final /* synthetic */ class hit implements Callable {

    /* renamed from: a */
    private final hiz f12826a;

    public hit(hiz hiz) {
        this.f12826a = hiz;
    }

    public final Object call() {
        hiz hiz = this.f12826a;
        htm j = hto.m12130j();
        try {
            ijc ijc = hiz.mo7490c().f12897e;
            int size = ijc.size();
            for (int i = 0; i < size; i++) {
                j.mo7874b(gkn.m10445a(((Integer) ijc.get(i)).intValue(), gtf.f12011a));
            }
            return j.mo7993a();
        } catch (IOException e) {
            hiz.mo7488a((Throwable) e);
            return j.mo7993a();
        }
    }
}
