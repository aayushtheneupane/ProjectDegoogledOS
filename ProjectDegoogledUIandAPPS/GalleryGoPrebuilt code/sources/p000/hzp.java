package p000;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: hzp */
/* compiled from: PG */
final class hzp extends hze implements Serializable {

    /* renamed from: a */
    private final MessageDigest f13688a;

    /* renamed from: b */
    private final int f13689b;

    /* renamed from: c */
    private final boolean f13690c;

    /* renamed from: d */
    private final String f13691d;

    public hzp(String str, int i, String str2) {
        this.f13691d = (String) ife.m12898e((Object) str2);
        MessageDigest a = m12534a(str);
        this.f13688a = a;
        int digestLength = a.getDigestLength();
        boolean z = false;
        if (i >= 4 && i <= digestLength) {
            z = true;
        }
        ife.m12847a(z, "bytes (%s) must be >= 4 and < %s", i, digestLength);
        this.f13689b = i;
        this.f13690c = m12535a(this.f13688a);
    }

    public final String toString() {
        return this.f13691d;
    }

    public hzp(String str, String str2) {
        MessageDigest a = m12534a(str);
        this.f13688a = a;
        this.f13689b = a.getDigestLength();
        this.f13691d = (String) ife.m12898e((Object) str2);
        this.f13690c = m12535a(this.f13688a);
    }

    /* renamed from: a */
    private static MessageDigest m12534a(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public final hzk mo8299a() {
        if (this.f13690c) {
            try {
                return new hzn((MessageDigest) this.f13688a.clone(), this.f13689b);
            } catch (CloneNotSupportedException e) {
            }
        }
        return new hzn(m12534a(this.f13688a.getAlgorithm()), this.f13689b);
    }

    /* renamed from: a */
    private static boolean m12535a(MessageDigest messageDigest) {
        try {
            messageDigest.clone();
            return true;
        } catch (CloneNotSupportedException e) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hzo(this.f13688a.getAlgorithm(), this.f13689b, this.f13691d);
    }
}
