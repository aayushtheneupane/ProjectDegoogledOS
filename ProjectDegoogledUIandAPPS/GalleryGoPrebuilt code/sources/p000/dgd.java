package p000;

import android.content.ContentUris;
import android.net.Uri;

/* renamed from: dgd */
/* compiled from: PG */
final /* synthetic */ class dgd implements icf {

    /* renamed from: a */
    private final dge f6492a;

    /* renamed from: b */
    private final Uri f6493b;

    public dgd(dge dge, Uri uri) {
        this.f6492a = dge;
        this.f6493b = uri;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        dge dge = this.f6492a;
        Uri uri = this.f6493b;
        bpt bpt = dge.f6495b.f6045a;
        long parseId = ContentUris.parseId(uri);
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT * FROM mt WHERE b = ?");
        hgn.mo7408a(Long.valueOf(parseId));
        return bpt.mo2655a(hgn.mo7407a(), cyn.f6041a).mo6899b();
    }
}
