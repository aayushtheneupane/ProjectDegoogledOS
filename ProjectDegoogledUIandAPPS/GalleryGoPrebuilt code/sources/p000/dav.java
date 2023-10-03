package p000;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.MediaStore;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dav */
/* compiled from: PG */
public final class dav {

    /* renamed from: b */
    public static /* synthetic */ int f6148b;

    /* renamed from: c */
    private static final Uri f6149c = MediaStore.Files.getContentUri("external");

    /* renamed from: a */
    public final iel f6150a;

    /* renamed from: d */
    private final gtt f6151d;

    public dav(gtt gtt, iel iel) {
        this.f6151d = gtt;
        this.f6150a = iel;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo4022a(long j) {
        return this.f6151d.mo7042a(f6149c, new String[]{"_data"}, "_id = ?", new String[]{String.valueOf(j)}, (String) null).mo6897b(dau.f6147a, (Executor) this.f6150a).mo6899b();
    }

    /* renamed from: a */
    public final ieh mo4023a(Uri uri) {
        if (!fxk.m9827a(uri)) {
            return ife.m12820a((Object) Optional.m16285of(uri.toString()));
        }
        try {
            return mo4022a(ContentUris.parseId(uri));
        } catch (NumberFormatException | UnsupportedOperationException e) {
            return ife.m12820a((Object) Optional.m16285of(uri.toString()));
        }
    }
}
