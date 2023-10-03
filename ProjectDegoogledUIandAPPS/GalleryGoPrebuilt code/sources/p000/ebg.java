package p000;

import android.net.Uri;

/* renamed from: ebg */
/* compiled from: PG */
final class ebg extends ebh {

    /* renamed from: a */
    private final Uri f7845a;

    public ebg(Uri uri) {
        if (uri != null) {
            this.f7845a = uri;
            return;
        }
        throw new NullPointerException("Null uri");
    }

    /* renamed from: a */
    public final Uri mo4656a() {
        return this.f7845a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ebh) {
            return this.f7845a.equals(((ebh) obj).mo4656a());
        }
        return false;
    }

    public final int hashCode() {
        return this.f7845a.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7845a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 17);
        sb.append("ExternalUri{uri=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
