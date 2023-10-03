package p000;

import android.net.Uri;
import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.Optional;

/* renamed from: cke */
/* compiled from: PG */
final /* synthetic */ class cke implements icf {

    /* renamed from: a */
    private final ckk f4548a;

    /* renamed from: b */
    private final AtomicBoolean f4549b;

    /* renamed from: c */
    private final Uri f4550c;

    /* renamed from: d */
    private final cyd f4551d;

    public cke(ckk ckk, AtomicBoolean atomicBoolean, Uri uri, cyd cyd) {
        this.f4548a = ckk;
        this.f4549b = atomicBoolean;
        this.f4550c = uri;
        this.f4551d = cyd;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        String str;
        ckk ckk = this.f4548a;
        AtomicBoolean atomicBoolean = this.f4549b;
        Uri uri = this.f4550c;
        cyd cyd = this.f4551d;
        String str2 = (String) obj;
        if (atomicBoolean.get()) {
            return ife.m12820a((Object) hso.m12047f());
        }
        dbs dbs = ckk.f4561b;
        Optional empty = Optional.empty();
        ebi ebi = ckk.f4560a;
        String str3 = (String) ife.m12898e((Object) Uri.parse(str2).getLastPathSegment());
        int lastIndexOf = str3.lastIndexOf(".");
        if (lastIndexOf != -1) {
            str = str3.substring(lastIndexOf);
        } else {
            str = "";
        }
        return dbs.mo4040a(empty, str2, new ckl(ebi, uri, str, cyd.f5993g), true);
    }
}
