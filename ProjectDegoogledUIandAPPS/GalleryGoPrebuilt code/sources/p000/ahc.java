package p000;

import android.net.Uri;

/* renamed from: ahc */
/* compiled from: PG */
public final class ahc {

    /* renamed from: a */
    public final Uri f483a;

    /* renamed from: b */
    public final boolean f484b;

    public ahc(Uri uri, boolean z) {
        this.f483a = uri;
        this.f484b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ahc ahc = (ahc) obj;
        return this.f484b == ahc.f484b && this.f483a.equals(ahc.f483a);
    }

    public final int hashCode() {
        return (this.f483a.hashCode() * 31) + (this.f484b ? 1 : 0);
    }
}
