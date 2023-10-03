package p000;

import android.net.Uri;
import p003j$.util.Optional;

/* renamed from: dbh */
/* compiled from: PG */
public final class dbh extends iox {

    /* renamed from: b */
    private final ioq f6185b;

    public dbh(iqk iqk, iqk iqk2, ioq ioq) {
        super(iqk2, iph.m14288a(dbh.class), iqk);
        this.f6185b = ipd.m14274a(ioq);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        Optional optional;
        Uri uri = (Uri) obj;
        String path = uri.getPath();
        String scheme = uri.getScheme();
        if (("file".equals(scheme) || scheme == null) && path != null) {
            optional = Optional.m16285of(path);
        } else {
            optional = Optional.empty();
        }
        return ife.m12820a((Object) optional);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return this.f6185b.mo9044b();
    }
}
