package p000;

import android.net.Uri;
import java.util.concurrent.Callable;

/* renamed from: dbe */
/* compiled from: PG */
final /* synthetic */ class dbe implements Callable {

    /* renamed from: a */
    private final ebi f6176a;

    /* renamed from: b */
    private final Uri f6177b;

    public dbe(ebi ebi, Uri uri) {
        this.f6176a = ebi;
        this.f6177b = uri;
    }

    public final Object call() {
        return this.f6176a.mo4665a(this.f6177b, fqz.f10297a);
    }
}
