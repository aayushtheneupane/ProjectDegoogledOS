package p000;

import com.google.apps.tiktok.contrib.work.TikTokListenableWorker;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/* renamed from: gtc */
/* compiled from: PG */
public final class gtc implements gsq {

    /* renamed from: a */
    private final ahq f12001a;

    /* renamed from: b */
    private final Map f12002b;

    /* renamed from: c */
    private final Pattern f12003c = Pattern.compile("^(tiktok_account_work$|unique_|account_id_).*");

    /* renamed from: d */
    private final Pattern f12004d = Pattern.compile("^(TikTokWorker#|tiktok_).*");

    public gtc(ahq ahq, Map map) {
        this.f12001a = ahq;
        this.f12002b = map;
    }

    /* renamed from: a */
    private static final ahr m10764a(gsv gsv, ahr ahr) {
        hvr a = ((gsm) gsv).f11974g.iterator();
        while (a.hasNext()) {
            ahr.mo497a((String) a.next());
        }
        return ahr;
    }

    /* renamed from: a */
    public final ieh mo7027a(gsv gsv) {
        gsm gsm = (gsm) gsv;
        hvr a = gsm.f11974g.iterator();
        while (a.hasNext()) {
            String str = (String) a.next();
            if (this.f12003c.matcher(str).matches()) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39);
                sb.append("Tag ");
                sb.append(str);
                sb.append(" is reserved by AccountWorkManager.");
                throw new gsx(sb.toString());
            }
        }
        hvr a2 = gsm.f11974g.iterator();
        while (a2.hasNext()) {
            String str2 = (String) a2.next();
            if (this.f12004d.matcher(str2).matches()) {
                StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 38);
                sb2.append("Tag ");
                sb2.append(str2);
                sb2.append(" is reserved by TikTokWorkManager.");
                throw new gsx(sb2.toString());
            }
        }
        String str3 = (String) ife.m12869b((Object) (String) this.f12002b.get(gsm.f11968a), (Object) "The input Worker wasn't annotated with @GenerateWorker. If it or any of its chained work is annotated with @GenerateAccountWorker, its work must be started with TikTokAccountWorkManager, instead.");
        hto a3 = hto.m12120a((Object) str3.length() == 0 ? new String("TikTokWorker#") : "TikTokWorker#".concat(str3));
        gsr gsr = new gsr(gsv);
        hto hto = gsm.f11974g;
        ife.m12869b((Object) hto, (Object) "set1");
        ife.m12869b((Object) a3, (Object) "set2");
        gsr.mo7031a((Set) new hvj(hto, a3));
        gsv a4 = gsr.mo7028a();
        gsm gsm2 = (gsm) a4;
        if (!gsm2.f11972e.mo7646a()) {
            ife.m12896d(!gsm2.f11972e.mo7646a());
            if (gsm2.f11973f.mo7646a()) {
                ife.m12896d(!gsm2.f11972e.mo7646a());
                ife.m12896d(gsm2.f11973f.mo7646a());
                ahs b = m10766b(a4);
                ahq ahq = this.f12001a;
                String a5 = ((gsu) gsm2.f11973f.mo7647b()).mo7022a();
                ((gsu) gsm2.f11973f.mo7647b()).mo7023b();
                return ibv.m12657a(ahq.mo492a(a5, Collections.singletonList(b)).mo489a(), (hpr) new gsz(b), (Executor) idh.f13918a);
            }
            ife.m12896d(!gsm2.f11972e.mo7646a());
            ife.m12896d(!gsm2.f11973f.mo7646a());
            ahs b2 = m10766b(a4);
            return ibv.m12657a(this.f12001a.mo490a(b2).mo489a(), (hpr) new gsy(b2), (Executor) idh.f13918a);
        }
        ife.m12896d(gsm2.f11972e.mo7646a());
        if (gsm2.f11973f.mo7646a()) {
            ife.m12896d(gsm2.f11972e.mo7646a());
            ife.m12896d(gsm2.f11973f.mo7646a());
            ahs a6 = m10765a(a4, ((gss) gsm2.f11972e.mo7647b()).mo7012a(), ((gss) gsm2.f11972e.mo7647b()).mo7013b());
            ahq ahq2 = this.f12001a;
            String a7 = ((gsu) gsm2.f11973f.mo7647b()).mo7022a();
            ((gsu) gsm2.f11973f.mo7647b()).mo7023b();
            return ibv.m12657a(ahq2.mo491a(a7, a6).mo489a(), (hpr) new gta(a6), (Executor) idh.f13918a);
        }
        ife.m12896d(gsm2.f11972e.mo7646a());
        ife.m12896d(!gsm2.f11973f.mo7646a());
        ahs a8 = m10765a(a4, ((gss) gsm2.f11972e.mo7647b()).mo7012a(), ((gss) gsm2.f11972e.mo7647b()).mo7013b());
        return ibv.m12657a(this.f12001a.mo490a(a8).mo489a(), (hpr) new gtb(a8), (Executor) idh.f13918a);
    }

    /* renamed from: b */
    private static final ahs m10766b(gsv gsv) {
        gsm gsm = (gsm) gsv;
        ife.m12896d(!gsm.f11972e.mo7646a());
        return m10764a(gsv, ((ahk) ((ahk) new ahk(TikTokListenableWorker.class).mo495a(gsm.f11969b)).mo494a(gsm.f11970c.mo7017a(), gsm.f11970c.mo7018b())).mo496a(gsm.f11971d)).mo498c();
    }

    /* renamed from: a */
    private static final ahs m10765a(gsv gsv, gst gst, hpy hpy) {
        gsm gsm = (gsm) gsv;
        ife.m12896d(gsm.f11972e.mo7646a());
        if (hpy.mo7646a()) {
            return m10764a(gsv, ((ahp) ((ahp) new ahp(TikTokListenableWorker.class, gst.mo7017a(), gst.mo7018b(), ((gst) hpy.mo7647b()).mo7017a(), ((gst) hpy.mo7647b()).mo7018b()).mo495a(gsm.f11969b)).mo494a(gsm.f11970c.mo7017a(), gsm.f11970c.mo7018b())).mo496a(gsm.f11971d)).mo498c();
        }
        return ((ahp) ((ahp) ((ahp) m10764a(gsv, new ahp(TikTokListenableWorker.class, gst.mo7017a(), gst.mo7018b())).mo495a(gsm.f11969b)).mo494a(gsm.f11970c.mo7017a(), gsm.f11970c.mo7018b())).mo496a(gsm.f11971d)).mo498c();
    }
}
