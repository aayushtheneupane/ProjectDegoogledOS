package p000;

import android.app.PendingIntent;
import android.support.p002v7.widget.RecyclerView;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: hhr */
/* compiled from: PG */
final /* synthetic */ class hhr implements hpr {

    /* renamed from: a */
    private final hhs f12756a;

    public hhr(hhs hhs) {
        this.f12756a = hhs;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        boolean z;
        hhs hhs = this.f12756a;
        Map map = (Map) obj;
        if (map.isEmpty()) {
            return null;
        }
        hhs.mo7457a(true);
        long a = hhs.f12759c.mo5370a();
        HashSet<hgz> hashSet = new HashSet<>();
        long a2 = hjo.m11591a(TimeUnit.MILLISECONDS);
        Iterator it = map.values().iterator();
        long j = RecyclerView.FOREVER_NS;
        long j2 = RecyclerView.FOREVER_NS;
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            hjj hjj = (hjj) it.next();
            if (hjj.mo7450b() > a) {
                j = Math.min(j, Math.max(hjj.mo7450b(), a + a2));
                j2 = j2;
            } else {
                long j3 = j2;
                boolean z2 = true;
                for (hgz hgz : hjj.mo7449a()) {
                    if (!hhs.f12764h.containsKey(hgz)) {
                        hhs.f12764h.put(hgz, Boolean.valueOf(((hhf) hhs.f12762f.get(hgz)).mo7443a()));
                    }
                    if (!((Boolean) hhs.f12764h.get(hgz)).booleanValue()) {
                        hashSet.addAll(hjj.mo7449a());
                        z2 = false;
                    }
                }
                if (z2) {
                    j = Math.min(j, a + a2);
                }
                if (hjj.mo7451c().mo7646a()) {
                    j2 = Math.min(j3, Math.max(((Long) hjj.mo7451c().mo7647b()).longValue(), a + a2));
                } else {
                    j2 = j3;
                }
            }
        }
        HashSet hashSet2 = new HashSet();
        for (hgz hgz2 : hashSet) {
            hashSet2.add((hhf) hhs.f12762f.get(hgz2));
        }
        hjp hjp = (hjp) hhs.f12763g.mo2097a();
        HashSet hashSet3 = new HashSet(hhs.f12762f.values());
        hashSet3.removeAll(hashSet2);
        Iterator it2 = hashSet2.iterator();
        while (it2.hasNext()) {
            hjp.mo7470a((hhf) it2.next());
        }
        Iterator it3 = hashSet3.iterator();
        while (it3.hasNext()) {
            hjp.mo7476b((hhf) it3.next());
        }
        long min = Math.min(j2, j);
        if (min == RecyclerView.FOREVER_NS) {
            min = hhs.f12760d.mo7498a(a + hhs.f12757a, hhs.f12758b);
        }
        PendingIntent a3 = hhs.mo7455a();
        long a4 = hhs.f12760d.mo7498a(min, TimeUnit.MILLISECONDS.convert(15, TimeUnit.MINUTES));
        if (a4 > 0) {
            z = true;
        }
        ife.m12896d(z);
        hhs.f12761e.set(1, a4, a3);
        return null;
    }
}
