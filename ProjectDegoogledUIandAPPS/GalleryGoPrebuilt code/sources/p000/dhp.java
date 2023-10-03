package p000;

import android.net.Uri;

/* renamed from: dhp */
/* compiled from: PG */
public final class dhp {

    /* renamed from: a */
    private Uri f6558a;

    /* renamed from: b */
    private String f6559b;

    /* renamed from: c */
    private String f6560c;

    public dhp(byte[] bArr) {
    }

    /* renamed from: a */
    public final dhq mo4143a() {
        String str = this.f6558a == null ? " icon" : "";
        if (this.f6559b == null) {
            str = str.concat(" activityPackage");
        }
        if (this.f6560c == null) {
            str = String.valueOf(str).concat(" activityClass");
        }
        if (str.isEmpty()) {
            return new dhm(this.f6558a, this.f6559b, this.f6560c);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo4145a(String str) {
        if (str != null) {
            this.f6560c = str;
            return;
        }
        throw new NullPointerException("Null activityClass");
    }

    /* renamed from: b */
    public final void mo4146b(String str) {
        if (str != null) {
            this.f6559b = str;
            return;
        }
        throw new NullPointerException("Null activityPackage");
    }

    /* renamed from: a */
    public final void mo4144a(Uri uri) {
        if (uri != null) {
            this.f6558a = uri;
            return;
        }
        throw new NullPointerException("Null icon");
    }

    dhp() {
    }
}
