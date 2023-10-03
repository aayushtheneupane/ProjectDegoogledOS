package p000;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dfj */
/* compiled from: PG */
public final class dfj {

    /* renamed from: a */
    public static /* synthetic */ int f6440a;

    /* renamed from: b */
    private static final String[] f6441b;

    /* renamed from: c */
    private static final String[] f6442c;

    static {
        String[] strArr = {"_id", "date_modified", "date_added", "datetaken", "mime_type", "height", "width", "orientation", "_size", "media_type", "duration", "bucket_id", "bucket_display_name", "_data"};
        f6441b = strArr;
        f6442c = (String[]) ife.m12860a((Object[]) strArr, (Object[]) new String[]{"is_pending"}, String.class);
    }

    /* renamed from: a */
    public static String m6038a(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: a */
    static boolean m6042a(boolean z, boolean z2, boolean z3) {
        return z || z2 || z3;
    }

    /* renamed from: a */
    static ieh m6030a(cjh cjh) {
        return bip.m2623a(cjh.mo3168a());
    }

    /* renamed from: a */
    static ieh m6031a(cyr cyr) {
        bpt bpt = cyr.f6045a;
        hgn a = cyr.m5742a();
        a.mo7409a(" ORDER BY b ASC");
        return bpt.mo2655a(a.mo7407a(), cyi.f6036a).mo6899b();
    }

    /* renamed from: a */
    static ieh m6029a(Context context, iek iek) {
        return iek.mo5933a(hmq.m11749a((Callable) new dfi(context)));
    }

    /* renamed from: a */
    static ieh m6035a(gtt gtt, iek iek, cjr cjr) {
        String[] strArr;
        hgn hgn = new hgn();
        if (cjr.mo3175a()) {
            hgn.mo7409a("(media_type = 1 OR media_type = 3 OR media_type = 0)");
        } else if (m6040a()) {
            hgn.mo7409a("((media_type = 1 OR media_type = 3) AND is_pending = 0)");
        } else {
            hgn.mo7409a("(media_type = 1 OR media_type = 3)");
        }
        Uri contentUri = MediaStore.Files.getContentUri("external");
        if (m6040a() && cjr.mo3175a()) {
            contentUri = MediaStore.setIncludePending(contentUri);
        }
        if (!m6040a()) {
            strArr = f6441b;
        } else {
            strArr = f6442c;
        }
        return gtt.mo7041a(contentUri, strArr, hgn.mo7407a(), "_id ASC").mo6897b((hpr) new dex(cjr), (Executor) iek).mo6899b();
    }

    /* renamed from: a */
    static ieh m6034a(dhv dhv) {
        return dhv.mo4148a();
    }

    /* renamed from: a */
    static dgb m6028a(List list, iop iop, Map map) {
        List list2;
        Optional optional;
        long j;
        List list3 = list;
        Map map2 = map;
        try {
            List list4 = (List) iop.mo9037a();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i = 0;
            int i2 = 0;
            while (i < list.size() && i2 < list4.size()) {
                cyg cyg = (cyg) list3.get(i);
                cyg cyg2 = (cyg) list4.get(i2);
                if (!m6041a(cyg2, map2)) {
                    i2++;
                } else {
                    if (cyg.mo3908b() < cyg2.mo3908b()) {
                        arrayList3.add(cyg);
                        i++;
                        list2 = list4;
                    } else if (cyg.mo3908b() > cyg2.mo3908b()) {
                        arrayList.add(m6043b(cyg2, map2));
                        i2++;
                        list2 = list4;
                    } else {
                        cyg b = m6043b(cyg2, map2);
                        if (cyg.mo3909c() == b.mo3909c() && cyg.mo3910d().equals(b.mo3910d()) && cyg.mo3911e() == b.mo3911e() && cyg.mo3913f() == b.mo3913f() && cyg.mo3914g() == b.mo3914g() && cyg.mo3915h() == b.mo3915h() && cyg.mo3918j() == b.mo3918j() && cyg.mo3921m().equals(b.mo3921m()) && cyg.mo3917i().equals(b.mo3917i()) && cyg.mo3922n().equals(b.mo3922n()) && cyg.mo3920l() == b.mo3920l() && cyg.mo3923o().equals(b.mo3923o()) && cyg.mo3897D().equals(b.mo3897D())) {
                            optional = Optional.empty();
                            list2 = list4;
                        } else {
                            if (cyg.mo3918j() == b.mo3918j()) {
                                j = cyg.mo3919k();
                            } else {
                                j = b.mo3919k();
                            }
                            cyf R = cyg.m5687R();
                            Optional a = cyg.mo3907a();
                            if (a != null) {
                                R.f6009a = a;
                                list2 = list4;
                                R.mo3976e(cyg.mo3908b());
                                R.mo3986h(b.mo3909c());
                                R.mo3977e(b.mo3910d());
                                R.mo3955a(b.mo3911e());
                                R.mo3971d(b.mo3913f());
                                R.mo3960b(b.mo3914g());
                                R.mo3972d(b.mo3915h());
                                R.mo3968c(b.mo3918j());
                                R.mo3961b(j);
                                R.mo3980f(b.mo3920l());
                                R.mo3957a(b.mo3921m());
                                R.mo3969c(b.mo3922n());
                                R.mo3962b(b.mo3923o());
                                R.mo3963b(true);
                                R.mo3979f(0);
                                Optional D = b.mo3897D();
                                R.getClass();
                                D.ifPresent(new dff(R));
                                Optional i3 = b.mo3917i();
                                R.getClass();
                                i3.ifPresent(new dfg(R));
                                optional = Optional.m16285of(R.mo3966c());
                            } else {
                                throw new NullPointerException("Null id");
                            }
                        }
                        arrayList2.getClass();
                        optional.ifPresent(new dfe(arrayList2));
                        i++;
                        i2++;
                    }
                    list4 = list2;
                }
            }
            List list5 = list4;
            while (i < list.size()) {
                arrayList3.add((cyg) list3.get(i));
                i++;
            }
            while (i2 < list5.size()) {
                List list6 = list5;
                cyg cyg3 = (cyg) list6.get(i2);
                if (m6041a(cyg3, map2)) {
                    arrayList.add(m6043b(cyg3, map2));
                }
                i2++;
                list5 = list6;
            }
            Object[] objArr = {Integer.valueOf(arrayList.size()), Integer.valueOf(arrayList2.size()), Integer.valueOf(arrayList3.size())};
            dga d = dgb.m6076d();
            d.mo4112b(arrayList);
            d.mo4113c(arrayList2);
            d.mo4111a(arrayList3);
            return d.mo4110a();
        } catch (ExecutionException e) {
            if (!(e.getCause() instanceof IllegalStateException)) {
                cwn.m5515b((Throwable) e, "Unexpected exception when loading all media", new Object[0]);
            }
            dga d2 = dgb.m6076d();
            d2.mo4112b(hso.m12047f());
            d2.mo4113c(hso.m12047f());
            d2.mo4111a(hso.m12047f());
            return d2.mo4110a();
        }
    }

    /* renamed from: a */
    private static boolean m6040a() {
        return Build.VERSION.SDK_INT >= 29;
    }

    /* renamed from: a */
    static Map m6039a(List list) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            dhu dhu = (dhu) it.next();
            new Object[1][0] = Long.valueOf(dhu.f6568b);
            hashMap.put(Long.valueOf(dhu.f6568b), dhu);
        }
        return hashMap;
    }

    /* renamed from: a */
    static ieh m6036a(ice ice) {
        return ice.mo2539a();
    }

    /* renamed from: a */
    static ieh m6037a(String str, icf icf, cyu cyu, cyr cyr, cjh cjh, cie cie) {
        if (cyu.f6051b.equals(str)) {
            return ife.m12820a((Object) null);
        }
        Object[] objArr = {cyu.f6051b, str};
        return gte.m10778b(cyr.f6045a.mo2656a(cyp.f6043a), cjh.mo3171b(), cie.mo3154a()).mo7611a((ice) new dfh(icf, str), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    private static boolean m6041a(cyg cyg, Map map) {
        boolean containsKey = map.containsKey(Long.valueOf(cyg.mo3908b()));
        boolean z = cyg.mo3909c() == 3 || cyg.mo3909c() == 1;
        if (!z && cyg.mo3909c() != 0) {
            return false;
        }
        if (cyg.mo3909c() != 0 || containsKey) {
            return !z || !cyg.mo3905L() || containsKey;
        }
        return false;
    }

    /* renamed from: b */
    static ieh m6044b(cyr cyr, dgb dgb) {
        List<cyg> c = dgb.mo4094c();
        if (c.isEmpty()) {
            return ife.m12820a((Object) false);
        }
        ArrayList arrayList = new ArrayList();
        for (cyg a : c) {
            arrayList.add((Long) a.mo3907a().get());
        }
        return gte.m10770a(cyr.mo3999c(arrayList), dfd.f6433a, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    static ieh m6033a(cyr cyr, dgb dgb, inw inw, iek iek, cjr cjr) {
        List a = dgb.mo4092a();
        if (a.isEmpty()) {
            return ife.m12820a((Object) false);
        }
        if (!cjr.mo3175a() || a.size() > 10) {
            return gte.m10770a(cyr.mo3997a(a), dew.f6417a, (Executor) idh.f13918a);
        }
        return gte.m10771a(iek.mo5933a(hmq.m11749a((Callable) new dfa(inw, a))), (icf) new dfb(a, cyr), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    static ieh m6032a(cyr cyr, dgb dgb) {
        List b = dgb.mo4093b();
        if (!b.isEmpty()) {
            return gte.m10770a(cyr.f6045a.mo2656a(new cyk(b)), dfc.f6432a, (Executor) idh.f13918a);
        }
        return ife.m12820a((Object) false);
    }

    /* renamed from: b */
    private static cyg m6043b(cyg cyg, Map map) {
        dhu dhu = (dhu) map.get(Long.valueOf(cyg.mo3908b()));
        if (dhu != null) {
            cyf M = cyg.mo3906M();
            M.mo3986h(1);
            M.mo3977e("image/jpeg");
            M.mo3981f(dhu.f6572f);
            if ((dhu.f6567a & 8) != 0) {
                M.mo3984g(dhu.f6571e);
            }
            new Object[1][0] = Long.valueOf(cyg.mo3908b());
            return M.mo3966c();
        } else if (!cyg.mo3897D().isPresent()) {
            return cyg;
        } else {
            cyf M2 = cyg.mo3906M();
            M2.mo3981f((String) null);
            return M2.mo3966c();
        }
    }
}
