package p000;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: gym */
/* compiled from: PG */
public final /* synthetic */ class gym implements gxb {

    /* renamed from: a */
    private final gyc f12309a;

    public gym(gyc gyc) {
        this.f12309a = gyc;
    }

    /* renamed from: a */
    public final gxc mo7170a(String str) {
        gyc gyc = this.f12309a;
        String a = gyc.m11035a(str);
        gzn gzn = (gzn) gyc.f12291b.get(gyc.f12290a.mo7210a(a));
        try {
            return (gxc) ((gyu) ife.m12832a((Future) gzn.f12357c.mo6948a())).mo7192a(gyc.m11036a(a, str));
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
