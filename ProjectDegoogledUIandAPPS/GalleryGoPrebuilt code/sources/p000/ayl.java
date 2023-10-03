package p000;

import android.content.ContentResolver;
import android.net.Uri;

/* renamed from: ayl */
/* compiled from: PG */
public final class ayl implements axp, ayn {

    /* renamed from: a */
    private final ContentResolver f1864a;

    public ayl(ContentResolver contentResolver) {
        this.f1864a = contentResolver;
    }

    /* renamed from: a */
    public final ari mo1728a(Uri uri) {
        return new are(this.f1864a, uri);
    }

    /* renamed from: a */
    public final axo mo1696a(axx axx) {
        return new ayp(this);
    }
}
