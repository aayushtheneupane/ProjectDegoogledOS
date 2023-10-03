package p000;

import androidx.preference.Preference;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: ebv */
/* compiled from: PG */
final /* synthetic */ class ebv implements ada {

    /* renamed from: a */
    private final eby f7868a;

    /* renamed from: b */
    private final hew f7869b;

    /* renamed from: c */
    private final hed f7870c;

    public ebv(eby eby, hew hew, hed hed) {
        this.f7868a = eby;
        this.f7869b = hew;
        this.f7870c = hed;
    }

    /* renamed from: a */
    public final boolean mo189a(Preference preference) {
        eby eby = this.f7868a;
        hew hew = this.f7869b;
        hed hed = this.f7870c;
        het het = eby.f7887m;
        hey hey = (hey) het.f12615h.mo2097a();
        idq a = ((hem) het.f12614g.mo2097a()).mo7338a(het.f12613f, new hep(het, hed), ((hea) hed).f12566b);
        hpy hpy = het.f12613f;
        String str = ((heb) hew).f12568a;
        heq heq = new heq(hew);
        ieh a2 = hey.f12628c.mo7339a(hpy);
        fba a3 = hey.f12629d.mo5449a(str);
        heq.mo5576a(a3);
        het.f12610c.mo6987a(grw.m10686a(ife.m12867b(a2, a).mo8443a(hmq.m11749a((Callable) new hex(hey, a2, a3, a)), (Executor) hey.f12630e)), het.f12617j);
        return true;
    }
}
