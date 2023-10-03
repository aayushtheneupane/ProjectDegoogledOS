package androidx.appcompat.mms.pdu;

import java.util.HashMap;
import java.util.Map;

/* renamed from: androidx.appcompat.mms.pdu.n */
public class C0197n {

    /* renamed from: om */
    static final byte[] f189om = "from-data".getBytes();

    /* renamed from: qm */
    static final byte[] f190qm = "attachment".getBytes();

    /* renamed from: rm */
    static final byte[] f191rm = "inline".getBytes();

    /* renamed from: mm */
    private Map f192mm;

    /* renamed from: nm */
    private byte[] f193nm;

    public C0197n() {
        this.f192mm = null;
        this.f193nm = null;
        this.f192mm = new HashMap();
    }

    /* renamed from: H */
    public void mo1255H(int i) {
        this.f192mm.put(129, Integer.valueOf(i));
    }

    /* renamed from: c */
    public void mo1256c(byte[] bArr) {
        if (bArr != null) {
            this.f192mm.put(197, bArr);
            return;
        }
        throw new NullPointerException("null content-disposition");
    }

    /* renamed from: d */
    public void mo1257d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("Content-Id may not be null or empty.");
        } else if (bArr.length > 1 && ((char) bArr[0]) == '<' && ((char) bArr[bArr.length - 1]) == '>') {
            this.f192mm.put(192, bArr);
        } else {
            byte[] bArr2 = new byte[(bArr.length + 2)];
            bArr2[0] = 60;
            bArr2[bArr2.length - 1] = 62;
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            this.f192mm.put(192, bArr2);
        }
    }

    /* renamed from: e */
    public void mo1258e(byte[] bArr) {
        if (bArr != null) {
            this.f192mm.put(142, bArr);
            return;
        }
        throw new NullPointerException("null content-location");
    }

    /* renamed from: f */
    public void mo1259f(byte[] bArr) {
        if (bArr != null) {
            this.f192mm.put(200, bArr);
            return;
        }
        throw new NullPointerException("null content-transfer-encoding");
    }

    /* renamed from: g */
    public void mo1260g(byte[] bArr) {
        if (bArr != null) {
            this.f192mm.put(145, bArr);
            return;
        }
        throw new NullPointerException("null content-type");
    }

    public byte[] getContentType() {
        return (byte[]) this.f192mm.get(145);
    }

    public byte[] getName() {
        return (byte[]) this.f192mm.get(151);
    }

    /* renamed from: h */
    public void mo1263h(byte[] bArr) {
        if (bArr != null) {
            this.f192mm.put(152, bArr);
            return;
        }
        throw new NullPointerException("null content-id");
    }

    /* renamed from: i */
    public void mo1264i(byte[] bArr) {
        if (bArr != null) {
            this.f192mm.put(151, bArr);
            return;
        }
        throw new NullPointerException("null content-id");
    }

    /* renamed from: ic */
    public byte[] mo1265ic() {
        return (byte[]) this.f192mm.get(192);
    }

    /* renamed from: jc */
    public byte[] mo1266jc() {
        return (byte[]) this.f192mm.get(142);
    }

    /* renamed from: kc */
    public byte[] mo1267kc() {
        return (byte[]) this.f192mm.get(200);
    }

    /* renamed from: lc */
    public byte[] mo1268lc() {
        return (byte[]) this.f192mm.get(152);
    }

    public void setData(byte[] bArr) {
        if (bArr != null) {
            this.f193nm = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.f193nm, 0, bArr.length);
        }
    }
}
