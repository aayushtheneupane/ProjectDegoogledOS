package p000;

import android.content.ContentResolver;
import android.net.Uri;

/* renamed from: aym */
/* compiled from: PG */
public final class aym implements axp, ayn {

    /* renamed from: a */
    private final ContentResolver f1865a;

    public aym(ContentResolver contentResolver) {
        this.f1865a = contentResolver;
    }

    /* renamed from: a */
    public final ari mo1728a(Uri uri) {
        return new arq(this.f1865a, uri);
    }

    /* renamed from: a */
    public final axo mo1696a(axx axx) {
        return new ayp(this);
    }
}
