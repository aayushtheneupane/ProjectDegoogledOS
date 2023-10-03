package p000;

import android.content.ContentResolver;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* renamed from: bkp */
/* compiled from: PG */
public final class bkp implements ftj, gxf, hbx, hel, hja, iod {

    /* renamed from: a */
    public final ftw f3060a;

    /* renamed from: b */
    public volatile iqk f3061b;

    /* renamed from: c */
    public volatile iqk f3062c;

    /* renamed from: d */
    public final /* synthetic */ bjw f3063d;

    /* renamed from: e */
    private volatile Object f3064e;

    /* renamed from: f */
    private volatile Object f3065f;

    /* renamed from: g */
    private volatile hlz f3066g;

    public /* synthetic */ bkp(bjw bjw, ftw ftw) {
        this.f3063d = bjw;
        this.f3064e = new iok();
        this.f3065f = new iok();
        this.f3060a = ftw;
    }

    /* renamed from: h */
    public final Map mo2462h() {
        return hvb.f13454a;
    }

    /* renamed from: f */
    public final hbz mo2460f() {
        return new bjm(this);
    }

    /* renamed from: j */
    private final gnn m3101j() {
        return (gnn) iol.m14229a((Object) new gnn(this.f3063d.mo2284c(), ftx.m9620a(this.f3060a), this.f3063d.mo2314cn()), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: b */
    public final gtt mo2456b() {
        ContentResolver au = this.f3063d.mo2225au();
        Object i = this.f3063d.mo2332i();
        iel cn = this.f3063d.mo2314cn();
        exn.m8328b();
        mo2455a();
        iel iel = (iel) this.f3063d.mo2333j();
        return gtu.m10790a(au, i, cn);
    }

    /* renamed from: e */
    public final hos mo2459e() {
        return hot.m11849a(mo2455a(), iom.f14602a, iom.f14602a);
    }

    /* renamed from: g */
    public final hbb mo2461g() {
        Object obj;
        Object obj2;
        Map x = this.f3063d.mo2347x();
        fqp z = this.f3063d.mo2349z();
        Object obj3 = this.f3064e;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj2 = this.f3064e;
                if (obj2 instanceof iok) {
                    hat bQ = this.f3063d.mo2248bQ();
                    gyd D = this.f3063d.mo2153D();
                    gkn a = ftx.m9620a(this.f3060a);
                    hsu hsu = hvb.f13454a;
                    gnn j = m3101j();
                    gxz F = this.f3063d.mo2155F();
                    gww gww = gww.UI_USER;
                    han han = new han(j);
                    gzm gzm = hao.f12419a;
                    gzm gzm2 = hap.f12420a;
                    haq haq = new haq(bQ, a);
                    har har = new har(F, a);
                    obj2 = (gyc) iol.m14229a((Object) D.mo7209a(gww, han, (iel) this.f3063d.mo2333j(), hsu, gzm, gzm2, haq, har), "Cannot return null from a non-@Nullable @Provides method");
                    this.f3064e = iog.m14219a(this.f3064e, obj2);
                }
            }
            obj3 = obj2;
        }
        gyc gyc = (gyc) obj3;
        Object obj4 = this.f3065f;
        if (obj4 instanceof iok) {
            synchronized (obj4) {
                obj = this.f3065f;
                if (obj instanceof iok) {
                    gyd D2 = this.f3063d.mo2153D();
                    ftz.m9622a(this.f3063d.f2702a);
                    iel g = this.f3063d.mo2330g();
                    gkn a2 = ftx.m9620a(this.f3060a);
                    hat bQ2 = this.f3063d.mo2248bQ();
                    gxz F2 = this.f3063d.mo2155F();
                    hsu hsu2 = hvb.f13454a;
                    gww gww2 = gww.USER;
                    haw haw = new haw((gnn) iol.m14229a((Object) new gnn(this.f3063d.mo2284c(), ftx.m9620a(this.f3060a), this.f3063d.mo2330g()), "Cannot return null from a non-@Nullable @Provides method"));
                    gzm gzm3 = hax.f12433a;
                    gzm gzm4 = hay.f12434a;
                    haz haz = new haz(bQ2, a2);
                    haz haz2 = haz;
                    obj = (gyc) iol.m14229a((Object) D2.mo7209a(gww2, haw, g, hsu2, gzm3, gzm4, haz2, new hba(F2, a2)), "Cannot return null from a non-@Nullable @Provides method");
                    this.f3065f = iog.m14219a(this.f3065f, obj);
                }
            }
            obj4 = obj;
        }
        return new hbb(x, z, gyc, (gyc) obj4);
    }

    /* renamed from: c */
    public final dav mo2457c() {
        return daw.m5836a(mo2456b(), this.f3063d.mo2314cn());
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, imp] */
    /* renamed from: d */
    public final dbs mo2458d() {
        return dcy.m5935a(this.f3063d.mo2223as(), this.f3063d.mo2224at(), mo2457c(), this.f3063d.mo2314cn());
    }

    /* renamed from: i */
    public final Set mo2463i() {
        goe goe = new goe(hvb.f13454a, m3101j(), this.f3063d.mo2314cn());
        hhd d = hhe.m11487d();
        d.f12727a = hha.m11482a("OrphanCacheAccountSynclet");
        d.mo7440a(goe);
        hgv d2 = hgw.m11470d();
        d2.mo7431a(14, TimeUnit.DAYS);
        hgx c = hgy.m11476c();
        c.f12717a = hgz.ON_CHARGER;
        c.mo7434a(7, TimeUnit.DAYS);
        d2.mo7432a(c.mo7433a());
        d.f12728b = d2.mo7430a();
        return hto.m12120a((Object) (hhe) iol.m14229a((Object) d.mo7439a(), "Cannot return null from a non-@Nullable @Provides method"));
    }

    /* renamed from: a */
    public final hlz mo2455a() {
        Set set;
        hlz hlz = this.f3066g;
        if (hlz != null) {
            return hlz;
        }
        exm b = exn.m8328b();
        Object P = this.f3063d.mo2165P();
        ftw ftw = this.f3060a;
        if (ftw.f10590a != null) {
            set = Collections.singleton(gko.m10448a(hln.m11704b(), ftw.f10590a, gtf.f12011a).mo7552a());
        } else {
            set = Collections.emptySet();
        }
        hlz a = hmb.m11726a(b, P, hto.m12125a((Collection) (Set) iol.m14229a((Object) set, "Cannot return null from a non-@Nullable @Provides method")), hma.m11724b());
        this.f3066g = a;
        return a;
    }

    public bkp() {
    }
}
