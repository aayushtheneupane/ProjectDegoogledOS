package p000;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: hjl */
/* compiled from: PG */
final /* synthetic */ class hjl implements hpr {

    /* renamed from: a */
    private final hjm f12855a;

    /* renamed from: b */
    private final Map f12856b;

    /* renamed from: c */
    private final Set f12857c;

    /* renamed from: d */
    private final long f12858d;

    public hjl(hjm hjm, Map map, Set set, long j) {
        this.f12855a = hjm;
        this.f12856b = map;
        this.f12857c = set;
        this.f12858d = j;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Iterator it;
        long j;
        Iterator it2;
        hgw hgw;
        Set set;
        hgw hgw2;
        Set set2;
        hjm hjm = this.f12855a;
        Map map = this.f12856b;
        Set set3 = this.f12857c;
        long j2 = this.f12858d;
        Map map2 = (Map) obj;
        ArrayList arrayList = new ArrayList();
        long a = hjm.f12859a.mo5370a();
        Iterator it3 = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            hjh hjh = (hjh) entry.getKey();
            hgw b = ((hhe) entry.getValue()).mo7425b();
            Long l = (Long) map2.get(hjh);
            if (!set3.contains(hjh)) {
                j = l != null ? l.longValue() : j2;
            } else {
                j = a;
            }
            htm j3 = hto.m12130j();
            hpy hpy = hph.f13219a;
            long a2 = b.mo7413a() + j;
            for (hgy hgy : b.mo7415c().values()) {
                long b2 = hgy.mo7420b();
                if (b2 != -1) {
                    it2 = it;
                    long a3 = b2 + b.mo7413a() + j;
                    if (a <= a3) {
                        if (!hpy.mo7646a()) {
                            hpy = hpy.m11893b(Long.valueOf(a3));
                            hgw2 = b;
                            set2 = set3;
                        } else {
                            hgw2 = b;
                            set2 = set3;
                            hpy = hpy.m11893b(Long.valueOf(Math.min(((Long) hpy.mo7647b()).longValue(), a3)));
                        }
                        j3.mo7874b(hgy.mo7419a());
                        it = it2;
                        set3 = set2;
                        b = hgw2;
                    } else {
                        hgw = b;
                        set = set3;
                    }
                } else {
                    it2 = it;
                    hgw = b;
                    set = set3;
                    j3.mo7874b(hgy.mo7419a());
                }
                it = it2;
                set3 = set;
                b = hgw;
            }
            Set set4 = set3;
            hji d = hjj.m11582d();
            d.f12852a = a2;
            d.f12853b = hpy;
            d.mo7496a(j3.mo7993a());
            arrayList.add(d.mo7495a());
            it3 = it;
        }
        C0290kn knVar = new C0290kn();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            hjj hjj = (hjj) arrayList.get(i);
            Set a4 = hjj.mo7449a();
            hjj hjj2 = (hjj) knVar.get(a4);
            if (hjj2 == null) {
                knVar.put(a4, hjj);
            } else {
                knVar.put(a4, hjj.m11581a(hjj2, hjj));
            }
        }
        return knVar;
    }
}
