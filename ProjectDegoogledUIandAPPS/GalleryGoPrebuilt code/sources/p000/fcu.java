package p000;

import android.os.StrictMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: fcu */
/* compiled from: PG */
public final class fcu {

    /* renamed from: a */
    public final iek f9282a;

    /* renamed from: b */
    public final Set f9283b;

    /* renamed from: c */
    public final fcq f9284c;

    /* renamed from: d */
    private final iqk f9285d;

    /* renamed from: e */
    private final exm f9286e;

    /* renamed from: f */
    private final Executor f9287f;

    /* renamed from: g */
    private volatile hsu f9288g;

    public fcu(iek iek, iqk iqk, Set set, fcq fcq, exm exm) {
        this.f9282a = iek;
        this.f9285d = iqk;
        this.f9283b = set;
        this.f9284c = fcq;
        this.f9286e = exm;
        this.f9287f = ife.m12837a((Executor) iek);
        ife.m12876b(!set.isEmpty(), (Object) "No logging result handlers provided.");
    }

    /* renamed from: a */
    public final List mo5489a(Class cls) {
        hsu hsu = this.f9288g;
        if (hsu == null) {
            synchronized (this) {
                hsu = this.f9288g;
                if (hsu == null) {
                    HashMap hashMap = new HashMap();
                    ArrayList arrayList = new ArrayList();
                    for (fcv fcv : (Set) this.f9285d.mo2097a()) {
                        if (fcv.mo5492a().isEmpty()) {
                            arrayList.add(fcv);
                        }
                        for (Class cls2 : fcv.mo5492a()) {
                            List list = (List) hashMap.get(cls2);
                            if (list == null) {
                                list = new ArrayList();
                                hashMap.put(cls2, list);
                            }
                            list.add(fcv);
                        }
                    }
                    hashMap.put(fcx.class, arrayList);
                    hsu = hsu.m12069a((Map) hashMap);
                    this.f9288g = hsu;
                }
            }
        }
        return (List) hsu.getOrDefault(cls, hso.m12047f());
    }

    /* renamed from: a */
    public static ieh m8582a(fcz fcz, fcv fcv) {
        return fcv.mo5491a(fcz);
    }

    /* renamed from: a */
    private final void m8583a(ieh ieh) {
        hvr a = ((hvo) this.f9283b).iterator();
        while (a.hasNext()) {
            ((fcw) a.next()).mo5494a(ieh);
        }
    }

    /* renamed from: a */
    public final void mo5490a(fct fct) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).detectAll().build());
        try {
            long b = this.f9286e.mo5371b();
            fcy fcy = new fcy();
            List a = fct.mo5488a(fcy);
            if (a != null && !a.isEmpty()) {
                iev f = iev.m12774f();
                m8583a(ife.m12816a(hmq.m11743a((ice) new fcr(this, a, f, fcy)), this.f9287f));
                f.mo8346b((Object) Long.valueOf(this.f9286e.mo5371b() - b));
            }
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
            throw th;
        }
        StrictMode.setThreadPolicy(threadPolicy);
    }
}
