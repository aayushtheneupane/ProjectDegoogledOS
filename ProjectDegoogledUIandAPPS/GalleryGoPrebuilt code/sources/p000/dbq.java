package p000;

import android.net.Uri;
import p003j$.util.Optional;

/* renamed from: dbq */
/* compiled from: PG */
final /* synthetic */ class dbq implements icf {

    /* renamed from: a */
    private final dbs f6208a;

    /* renamed from: b */
    private final cyd f6209b;

    /* renamed from: c */
    private final dbo f6210c;

    public dbq(dbs dbs, cyd cyd, dbo dbo) {
        this.f6208a = dbs;
        this.f6209b = cyd;
        this.f6210c = dbo;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        dbs dbs = this.f6208a;
        cyd cyd = this.f6209b;
        return dbs.mo4039a(Optional.m16285of(cyd), Uri.parse((String) ((Optional) obj).get()), this.f6210c, true, true, true, "replace");
    }
}
