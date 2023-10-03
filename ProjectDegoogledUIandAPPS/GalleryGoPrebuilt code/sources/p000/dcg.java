package p000;

import android.content.Context;
import android.net.Uri;
import java.util.concurrent.TimeUnit;

/* renamed from: dcg */
/* compiled from: PG */
final /* synthetic */ class dcg implements abc {

    /* renamed from: a */
    private final Context f6243a;

    /* renamed from: b */
    private final Uri f6244b;

    /* renamed from: c */
    private final exm f6245c;

    /* renamed from: d */
    private final iel f6246d;

    public dcg(Context context, Uri uri, exm exm, iel iel) {
        this.f6243a = context;
        this.f6244b = uri;
        this.f6245c = exm;
        this.f6246d = iel;
    }

    /* renamed from: a */
    public final Object mo71a(aba aba) {
        Context context = this.f6243a;
        Uri uri = this.f6244b;
        exm exm = this.f6245c;
        iel iel = this.f6246d;
        ieh a = ife.m12821a(hmq.m11748a((Runnable) new dcb(context, uri, aba)), 100, TimeUnit.MILLISECONDS, exm, iel);
        aba.mo67a(new dcc(a), iel);
        return a;
    }
}
