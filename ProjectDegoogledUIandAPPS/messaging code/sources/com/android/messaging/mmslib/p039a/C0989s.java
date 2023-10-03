package com.android.messaging.mmslib.p039a;

import android.net.Uri;
import android.util.SparseArray;

/* renamed from: com.android.messaging.mmslib.a.s */
public class C0989s {

    /* renamed from: om */
    static final byte[] f1424om = "from-data".getBytes();

    /* renamed from: qm */
    static final byte[] f1425qm = "attachment".getBytes();

    /* renamed from: rm */
    static final byte[] f1426rm = "inline".getBytes();
    private Uri mUri;

    /* renamed from: mm */
    private SparseArray f1427mm;

    /* renamed from: nm */
    private byte[] f1428nm;

    public C0989s() {
        this.f1427mm = null;
        this.mUri = null;
        this.f1428nm = null;
        this.f1427mm = new SparseArray();
    }

    /* renamed from: H */
    public void mo6723H(int i) {
        this.f1427mm.put(129, Integer.valueOf(i));
    }

    /* renamed from: c */
    public void mo6724c(byte[] bArr) {
        if (bArr != null) {
            this.f1427mm.put(197, bArr);
            return;
        }
        throw new NullPointerException("null content-disposition");
    }

    /* renamed from: d */
    public void mo6725d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("Content-Id may not be null or empty.");
        } else if (bArr.length > 1 && ((char) bArr[0]) == '<' && ((char) bArr[bArr.length - 1]) == '>') {
            this.f1427mm.put(192, bArr);
        } else {
            byte[] bArr2 = new byte[(bArr.length + 2)];
            bArr2[0] = 60;
            bArr2[bArr2.length - 1] = 62;
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            this.f1427mm.put(192, bArr2);
        }
    }

    /* renamed from: e */
    public void mo6726e(byte[] bArr) {
        if (bArr != null) {
            this.f1427mm.put(142, bArr);
            return;
        }
        throw new NullPointerException("null content-location");
    }

    /* renamed from: f */
    public void mo6727f(byte[] bArr) {
        if (bArr != null) {
            this.f1427mm.put(200, bArr);
            return;
        }
        throw new NullPointerException("null content-transfer-encoding");
    }

    /* renamed from: g */
    public void mo6728g(byte[] bArr) {
        if (bArr != null) {
            this.f1427mm.put(145, bArr);
            return;
        }
        throw new NullPointerException("null content-type");
    }

    public int getCharset() {
        Integer num = (Integer) this.f1427mm.get(129);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public byte[] getContentDisposition() {
        return (byte[]) this.f1427mm.get(197);
    }

    public byte[] getContentType() {
        return (byte[]) this.f1427mm.get(145);
    }

    public byte[] getData() {
        return this.f1428nm;
    }

    public Uri getDataUri() {
        return this.mUri;
    }

    public byte[] getName() {
        return (byte[]) this.f1427mm.get(151);
    }

    /* renamed from: h */
    public void mo6735h(Uri uri) {
        this.mUri = uri;
    }

    /* renamed from: i */
    public void mo6737i(byte[] bArr) {
        if (bArr != null) {
            this.f1427mm.put(151, bArr);
            return;
        }
        throw new NullPointerException("null content-id");
    }

    /* renamed from: ic */
    public byte[] mo6738ic() {
        return (byte[]) this.f1427mm.get(192);
    }

    /* renamed from: jc */
    public byte[] mo6739jc() {
        return (byte[]) this.f1427mm.get(142);
    }

    /* renamed from: kc */
    public byte[] mo6740kc() {
        return (byte[]) this.f1427mm.get(200);
    }

    /* renamed from: lc */
    public byte[] mo6741lc() {
        return (byte[]) this.f1427mm.get(152);
    }

    public void setData(byte[] bArr) {
        this.f1428nm = bArr;
    }

    /* renamed from: h */
    public void mo6736h(byte[] bArr) {
        if (bArr != null) {
            this.f1427mm.put(152, bArr);
            return;
        }
        throw new NullPointerException("null content-id");
    }
}
