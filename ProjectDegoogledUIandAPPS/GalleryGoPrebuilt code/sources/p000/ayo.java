package p000;

import android.content.ContentResolver;
import android.net.Uri;

/* renamed from: ayo */
/* compiled from: PG */
public final class ayo implements axp, ayn {

    /* renamed from: a */
    private final ContentResolver f1866a;

    public ayo(ContentResolver contentResolver) {
        this.f1866a = contentResolver;
    }

    /* renamed from: a */
    public final ari mo1728a(Uri uri) {
        return new arz(this.f1866a, uri);
    }

    /* renamed from: a */
    public final axo mo1696a(axx axx) {
        return new ayp(this);
    }
}
