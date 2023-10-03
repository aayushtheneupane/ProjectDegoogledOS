package p000;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: auc */
/* compiled from: PG */
final class auc implements aqu {

    /* renamed from: b */
    private static final bfl f1694b = new bfl(50);

    /* renamed from: c */
    private final aui f1695c;

    /* renamed from: d */
    private final aqu f1696d;

    /* renamed from: e */
    private final aqu f1697e;

    /* renamed from: f */
    private final int f1698f;

    /* renamed from: g */
    private final int f1699g;

    /* renamed from: h */
    private final Class f1700h;

    /* renamed from: i */
    private final aqz f1701i;

    /* renamed from: j */
    private final ard f1702j;

    public auc(aui aui, aqu aqu, aqu aqu2, int i, int i2, ard ard, Class cls, aqz aqz) {
        this.f1695c = aui;
        this.f1696d = aqu;
        this.f1697e = aqu2;
        this.f1698f = i;
        this.f1699g = i2;
        this.f1702j = ard;
        this.f1700h = cls;
        this.f1701i = aqz;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof auc) {
            auc auc = (auc) obj;
            if (this.f1699g != auc.f1699g || this.f1698f != auc.f1698f || !bfp.m2432a((Object) this.f1702j, (Object) auc.f1702j) || !this.f1700h.equals(auc.f1700h) || !this.f1696d.equals(auc.f1696d) || !this.f1697e.equals(auc.f1697e) || !this.f1701i.equals(auc.f1701i)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((((this.f1696d.hashCode() * 31) + this.f1697e.hashCode()) * 31) + this.f1698f) * 31) + this.f1699g;
        ard ard = this.f1702j;
        if (ard != null) {
            hashCode = (hashCode * 31) + ard.hashCode();
        }
        return (((hashCode * 31) + this.f1700h.hashCode()) * 31) + this.f1701i.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f1696d);
        String valueOf2 = String.valueOf(this.f1697e);
        int i = this.f1698f;
        int i2 = this.f1699g;
        String valueOf3 = String.valueOf(this.f1700h);
        String valueOf4 = String.valueOf(this.f1702j);
        String valueOf5 = String.valueOf(this.f1701i);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(valueOf3).length();
        StringBuilder sb = new StringBuilder(length + 131 + length2 + length3 + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length());
        sb.append("ResourceCacheKey{sourceKey=");
        sb.append(valueOf);
        sb.append(", signature=");
        sb.append(valueOf2);
        sb.append(", width=");
        sb.append(i);
        sb.append(", height=");
        sb.append(i2);
        sb.append(", decodedResourceClass=");
        sb.append(valueOf3);
        sb.append(", transformation='");
        sb.append(valueOf4);
        sb.append("', options=");
        sb.append(valueOf5);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.f1695c.mo1635a(byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.f1698f).putInt(this.f1699g).array();
        this.f1697e.mo1494a(messageDigest);
        this.f1696d.mo1494a(messageDigest);
        messageDigest.update(bArr);
        ard ard = this.f1702j;
        if (ard != null) {
            ard.mo1494a(messageDigest);
        }
        this.f1701i.mo1494a(messageDigest);
        byte[] bArr2 = (byte[]) f1694b.mo1957b(this.f1700h);
        if (bArr2 == null) {
            bArr2 = this.f1700h.getName().getBytes(f1466a);
            f1694b.mo1958b(this.f1700h, bArr2);
        }
        messageDigest.update(bArr2);
        this.f1695c.mo1638a((Object) bArr);
    }
}
