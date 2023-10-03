package p000;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/* renamed from: fmy */
/* compiled from: PG */
final /* synthetic */ class fmy implements hqk {
    /* renamed from: a */
    public final Object mo2652a() {
        hpy hpy;
        hqk hqk = fmz.f10072a;
        Long l = (Long) fmz.m9252a().mo7645a(0L);
        if (l.longValue() > 0) {
            try {
                String str = new String(iab.m12562b(new File("/proc/self/stat")).mo8317a(), Charset.defaultCharset());
                hpy = hpz.m11899a(str) ? hph.f13219a : fmz.m9253a(str);
            } catch (IOException e) {
                hpy = hph.f13219a;
            }
            Long l2 = (Long) hpy.mo7645a(0L);
            if (l2.longValue() > 0) {
                return hpy.m11893b(Long.valueOf(TimeUnit.SECONDS.toMillis(l2.longValue()) / l.longValue()));
            }
        }
        return hph.f13219a;
    }
}
