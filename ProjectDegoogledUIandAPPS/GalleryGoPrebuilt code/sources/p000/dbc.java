package p000;

import android.net.Uri;
import java.util.concurrent.Callable;

/* renamed from: dbc */
/* compiled from: PG */
final /* synthetic */ class dbc implements Callable {

    /* renamed from: a */
    private final ebi f6173a;

    /* renamed from: b */
    private final Uri f6174b;

    public dbc(ebi ebi, Uri uri) {
        this.f6173a = ebi;
        this.f6174b = uri;
    }

    public final Object call() {
        return this.f6173a.mo4665a(this.f6174b, fqz.f10297a);
    }
}
