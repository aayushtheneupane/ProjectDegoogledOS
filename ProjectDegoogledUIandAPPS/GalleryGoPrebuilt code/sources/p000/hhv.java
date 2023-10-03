package p000;

import java.util.Iterator;
import java.util.Map;

/* renamed from: hhv */
/* compiled from: PG */
final /* synthetic */ class hhv implements hpr {

    /* renamed from: a */
    private final hiq f12769a;

    /* renamed from: b */
    private final long f12770b;

    /* renamed from: c */
    private final long f12771c;

    /* renamed from: d */
    private final Map f12772d;

    /* renamed from: e */
    private final Map f12773e;

    public hhv(hiq hiq, long j, long j2, Map map, Map map2) {
        this.f12769a = hiq;
        this.f12770b = j;
        this.f12771c = j2;
        this.f12772d = map;
        this.f12773e = map2;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        long j;
        long j2;
        hiq hiq = this.f12769a;
        long j3 = this.f12770b;
        long j4 = this.f12771c;
        Map map = this.f12772d;
        Map map2 = this.f12773e;
        Map map3 = (Map) obj;
        synchronized (hiq.f12817i) {
            synchronized (hiq.f12816h) {
                for (Map.Entry entry : hiq.f12816h.entrySet()) {
                    hjh hjh = (hjh) entry.getKey();
                    if (!hiq.f12817i.containsKey(hjh)) {
                        long longValue = hiq.f12818j.containsKey(hjh) ? ((Long) hiq.f12818j.get(hjh)).longValue() : j3;
                        if (map3.containsKey(hjh)) {
                            j2 = ((Long) map3.get(hjh)).longValue();
                            j = j3;
                        } else {
                            j = j3;
                            j2 = j;
                        }
                        long max = Math.max(longValue, j2);
                        hgw b = ((hhe) entry.getValue()).mo7425b();
                        if (b.mo7413a() + max <= j4) {
                            Iterator it = b.mo7415c().entrySet().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    iev f = iev.m12774f();
                                    hiq.f12817i.put(hjh, f);
                                    map2.put(hjh, f);
                                    j3 = j;
                                    break;
                                }
                                Map.Entry entry2 = (Map.Entry) it.next();
                                hgy hgy = (hgy) entry2.getValue();
                                long b2 = hgy.mo7420b();
                                long j5 = j4 - max;
                                long b3 = hgy.mo7420b() + b.mo7413a();
                                if (b2 == -1 || j5 <= b3) {
                                    hgz hgz = (hgz) entry2.getKey();
                                    if (!map.containsKey(hgz)) {
                                        map.put(hgz, Boolean.valueOf(((hhf) ((iqk) hiq.f12814f.get(hgz)).mo2097a()).mo7443a()));
                                    }
                                    if (!((Boolean) map.get(hgz)).booleanValue()) {
                                        j3 = j;
                                        break;
                                    }
                                }
                            }
                        } else {
                            j3 = j;
                        }
                    } else {
                        long j6 = j3;
                    }
                }
            }
        }
        return map2;
    }
}
