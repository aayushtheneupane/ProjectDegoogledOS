package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.firebase.jobdispatcher.GooglePlayReceiver;
import com.google.apps.tiktok.sync.impl.gcm.SyncFirebaseJobService;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/* renamed from: hjt */
/* compiled from: PG */
final /* synthetic */ class hjt implements icf {

    /* renamed from: a */
    private final hju f12868a;

    /* renamed from: b */
    private final Set f12869b;

    /* renamed from: c */
    private final long f12870c;

    /* renamed from: d */
    private final Map f12871d;

    public hjt(hju hju, Set set, long j, Map map) {
        this.f12868a = hju;
        this.f12869b = set;
        this.f12870c = j;
        this.f12871d = map;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        int i;
        long j;
        Set set;
        boolean z;
        long j2;
        long j3;
        Set set2;
        long j4;
        Iterator it;
        boolean z2;
        boolean z3;
        hju hju = this.f12868a;
        Set set3 = this.f12869b;
        long j5 = this.f12870c;
        Map map = this.f12871d;
        Map map2 = (Map) obj;
        if (map2.isEmpty()) {
            return ife.m12820a((Object) null);
        }
        hju.mo7500a(false);
        long a = hju.f12873b.mo5370a();
        Iterator it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            hjj hjj = (hjj) entry.getValue();
            long a2 = hjo.m11591a(TimeUnit.MILLISECONDS);
            long j6 = a2 + a;
            if (hjj.mo7450b() < j6) {
                long max = Math.max(a, hjj.mo7450b());
                hji d = hjj.m11582d();
                Iterator it3 = it2;
                d.mo7496a(hjj.mo7449a());
                d.f12852a = j6;
                if (hjj.mo7451c().mo7646a()) {
                    long j7 = j6 - max;
                    ife.m12896d(j7 > 0);
                    if (j7 <= a2) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    ife.m12896d(z3);
                    d.f12853b = hpy.m11893b(Long.valueOf(((Long) hjj.mo7451c().mo7647b()).longValue() + j7));
                }
                map2.put((Set) entry.getKey(), d.mo7495a());
                it2 = it3;
            } else {
                Iterator it4 = it2;
            }
        }
        hpy hpy = hph.f13219a;
        for (hjj hjj2 : map2.values()) {
            if (hjj2.mo7451c().mo7646a()) {
                if (hpy.mo7646a()) {
                    hpy = hpy.m11893b(Long.valueOf(Math.min(((Long) hpy.mo7647b()).longValue(), ((Long) hjj2.mo7451c().mo7647b()).longValue())));
                } else {
                    hpy = hjj2.mo7451c();
                }
            }
        }
        if (hpy.mo7646a()) {
            hvf hvf = hvf.f13465a;
            hji d2 = hjj.m11582d();
            d2.f12852a = ((Long) hpy.mo7647b()).longValue();
            d2.f12853b = hpy;
            d2.mo7496a(hvf);
            hjj a3 = d2.mo7495a();
            hjj hjj3 = (hjj) map2.get(hvf);
            if (hjj3 == null) {
                map2.put(hvf, a3);
            } else {
                map2.put(hvf, hjj.m11581a(hjj3, a3));
            }
        }
        synchronized (hju) {
            try {
                i = hju.f12876e.getPackageInfo("com.google.android.gms", 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                i = 0;
            }
            if (i < 8400000) {
                set = set3;
                j = j5;
                z = true;
            } else {
                Iterator it5 = map2.entrySet().iterator();
                boolean z4 = true;
                while (it5.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it5.next();
                    Set set4 = (Set) entry2.getKey();
                    hjj hjj4 = (hjj) entry2.getValue();
                    int a4 = hju.f12875d.mo5387a(hju.f12872a);
                    if (a4 == 0) {
                        bhe bhe = hju.f12879h;
                        bhr bhr = new bhr(bhe.f2369b);
                        Set a5 = hjj4.mo7449a();
                        if (a5.contains(hgz.ON_NETWORK_UNMETERED) && a5.contains(hgz.ON_NETWORK_CONNECTED)) {
                            HashSet hashSet = new HashSet(a5);
                            hashSet.remove(hgz.ON_NETWORK_CONNECTED);
                            a5 = hashSet;
                        }
                        Iterator it6 = a5.iterator();
                        while (it6.hasNext()) {
                            hgz hgz = (hgz) it6.next();
                            Iterator it7 = it6;
                            hhf hhf = (hhf) hju.f12874c.get(hgz);
                            if (hhf != null) {
                                it = it5;
                                z2 = true;
                            } else {
                                it = it5;
                                z2 = false;
                            }
                            ife.m12878b(z2, "No constraint handler bound for constraint type: %s", (Object) hgz);
                            bhr = hhf.mo7441a(bhr);
                            it6 = it7;
                            it5 = it;
                            z4 = z4;
                        }
                        Iterator it8 = it5;
                        boolean z5 = z4;
                        long j8 = a;
                        long convert = TimeUnit.SECONDS.convert(Math.max(0, hjj4.mo7450b() - a), TimeUnit.MILLISECONDS);
                        TimeUnit timeUnit = TimeUnit.SECONDS;
                        if (!foj.m9314a(hjo.f12864a)) {
                            j2 = timeUnit.convert(15, TimeUnit.MINUTES);
                        } else {
                            j2 = timeUnit.convert(5, TimeUnit.SECONDS);
                        }
                        long j9 = j2 + convert;
                        hjm hjm = hju.f12878g;
                        TimeUnit timeUnit2 = TimeUnit.SECONDS;
                        if (!foj.m9314a(hjo.f12864a)) {
                            set2 = set3;
                            j3 = j5;
                            j4 = timeUnit2.convert(15, TimeUnit.MINUTES);
                        } else {
                            set2 = set3;
                            j3 = j5;
                            j4 = timeUnit2.convert(5, TimeUnit.SECONDS);
                        }
                        long a6 = hjm.mo7498a(j9, j4);
                        StringBuilder sb = new StringBuilder("SyncTask");
                        Iterator it9 = new TreeSet(set4).iterator();
                        while (it9.hasNext()) {
                            sb.append(((hgz) it9.next()).f12723d);
                            sb.append('_');
                        }
                        bhr.f2382a = SyncFirebaseJobService.class.getName();
                        bhr.f2384c = sb.toString();
                        bhr.f2389h = false;
                        bhr.f2385d = 2;
                        bhr.f2390i = bii.m2610a(ife.m12806a(convert), ife.m12806a(a6));
                        bhr.mo2055j();
                        bhr.mo2045a();
                        bhe.f2368a.mo2029a(bhr.mo2045a());
                        hhs hhs = hju.f12877f;
                        hhs.f12761e.cancel(hhs.mo7455a());
                        hhs.mo7457a(false);
                        set3 = set2;
                        it5 = it8;
                        z4 = z5;
                        a = j8;
                        j5 = j3;
                    } else if (a4 != 18) {
                        z4 = false;
                    } else {
                        hju.mo7500a(true);
                        z4 = false;
                    }
                }
                set = set3;
                j = j5;
                z = z4;
            }
            if (i >= 8400000) {
                if (z) {
                    hjp hjp = (hjp) hju.f12880i.mo2097a();
                    for (hhf b : hju.f12874c.values()) {
                        hjp.mo7476b(b);
                    }
                    ieh a7 = ife.m12820a((Object) null);
                    return a7;
                }
            }
            bgy bgy = hju.f12879h.f2368a;
            Context context = ((bhg) bgy).f2372b;
            Intent b2 = ((bhg) bgy).mo2038b("CANCEL_ALL");
            b2.putExtra("component", new ComponentName(((bhg) bgy).f2372b, GooglePlayReceiver.class));
            context.sendBroadcast(b2);
            ieh a8 = hju.f12877f.mo7456a(set, j, map);
            return a8;
        }
    }
}
