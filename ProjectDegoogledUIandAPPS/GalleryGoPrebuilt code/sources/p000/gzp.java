package p000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: gzp */
/* compiled from: PG */
public final /* synthetic */ class gzp implements hbg {

    /* renamed from: a */
    private final Map f12365a;

    /* renamed from: b */
    private final gzw f12366b;

    /* renamed from: c */
    private final fqq f12367c;

    /* renamed from: d */
    private final gxw f12368d;

    public gzp(Map map, gzw gzw, fqq fqq, gxw gxw) {
        this.f12365a = map;
        this.f12366b = gzw;
        this.f12367c = fqq;
        this.f12368d = gxw;
    }

    /* renamed from: a */
    public final void mo6819a() {
        ieh ieh;
        Map map = this.f12365a;
        gzw gzw = this.f12366b;
        fqq fqq = this.f12367c;
        gxw gxw = this.f12368d;
        hlj a = hnb.m11765a("Registering packages with phenotype");
        try {
            ArrayList arrayList = new ArrayList();
            for (String str : map.keySet()) {
                fqq.getClass();
                gzr gzr = new gzr(fqq);
                String a2 = gzw.f12384g.mo7210a(str);
                ice ice = (ice) ((Map) gzw.f12383f.mo9034a()).get(a2);
                ifq ifq = (ifq) ((Map) gzw.f12379b.mo9034a()).get(a2);
                byte[] bArr = null;
                if (ifq != null && ice == null && ((Boolean) gzw.f12378a.mo9034a()).booleanValue()) {
                    if (hto.m12125a((Collection) ifq.f14009d).containsAll(gzw.f12382e)) {
                        ieh = ife.m12820a((Object) null);
                        ieh a3 = ibv.m12658a(ieh, hmq.m11744a((icf) new gzs(gxw, str)), (Executor) idh.f13918a);
                        goo.m10562a(a3, "Failed to register for %s", str);
                        arrayList.add(a3);
                    }
                }
                if (ice != null) {
                    ieh = ibv.m12658a(ife.m12816a(hmq.m11743a(ice), gzw.f12380c), hmq.m11744a((icf) new gzu(gzw, gzr, a2, ifq)), (Executor) idh.f13918a);
                } else {
                    int i = ifq == null ? gzw.f12381d : ifq.f14008c;
                    String[] a4 = gzw.m11094a(gzw.f12382e, ifq);
                    if (ifq != null) {
                        bArr = ifq.f14011f.mo8625j();
                    }
                    ieh = gzr.mo7226a(a2, i, a4, bArr);
                }
                ieh a32 = ibv.m12658a(ieh, hmq.m11744a((icf) new gzs(gxw, str)), (Executor) idh.f13918a);
                goo.m10562a(a32, "Failed to register for %s", str);
                arrayList.add(a32);
            }
            a.mo7548a(ife.m12866b((Iterable) arrayList).mo8443a(gzt.f12373a, (Executor) idh.f13918a));
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
