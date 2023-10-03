package p000;

import android.net.Uri;

/* renamed from: big */
/* compiled from: PG */
public final class big {

    /* renamed from: a */
    public final Uri f2446a;

    /* renamed from: b */
    public final int f2447b;

    public big(Uri uri, int i) {
        if (uri != null) {
            this.f2446a = uri;
            this.f2447b = i;
            return;
        }
        throw new IllegalArgumentException("URI must not be null.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof big) {
            big big = (big) obj;
            return this.f2447b == big.f2447b && this.f2446a.equals(big.f2446a);
        }
    }

    public final int hashCode() {
        return this.f2446a.hashCode() ^ this.f2447b;
    }
}
