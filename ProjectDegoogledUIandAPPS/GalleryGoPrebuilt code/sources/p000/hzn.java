package p000;

import java.security.MessageDigest;
import java.util.Arrays;

/* renamed from: hzn */
/* compiled from: PG */
final class hzn extends hzd {

    /* renamed from: a */
    private final MessageDigest f13682a;

    /* renamed from: b */
    private final int f13683b;

    /* renamed from: c */
    private boolean f13684c;

    public /* synthetic */ hzn(MessageDigest messageDigest, int i) {
        this.f13682a = messageDigest;
        this.f13683b = i;
    }

    /* renamed from: b */
    private final void m12531b() {
        ife.m12876b(!this.f13684c, (Object) "Cannot re-use a Hasher after calling hash() on it");
    }

    /* renamed from: a */
    public final hzi mo8300a() {
        m12531b();
        this.f13684c = true;
        if (this.f13683b == this.f13682a.getDigestLength()) {
            return hzi.m12516a(this.f13682a.digest());
        }
        return hzi.m12516a(Arrays.copyOf(this.f13682a.digest(), this.f13683b));
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo8285b(byte[] bArr, int i) {
        m12531b();
        this.f13682a.update(bArr, 0, i);
    }
}
