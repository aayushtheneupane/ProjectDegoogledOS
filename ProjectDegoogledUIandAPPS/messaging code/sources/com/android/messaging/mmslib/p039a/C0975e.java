package com.android.messaging.mmslib.p039a;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* renamed from: com.android.messaging.mmslib.a.e */
public class C0975e implements Cloneable {

    /* renamed from: Yl */
    private int f1403Yl;
    private byte[] mData;

    public C0975e(int i, byte[] bArr) {
        if (bArr != null) {
            this.f1403Yl = i;
            this.mData = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.mData, 0, bArr.length);
            return;
        }
        throw new NullPointerException("EncodedStringValue: Text-string is null.");
    }

    /* renamed from: a */
    public static C0975e m2210a(C0975e eVar) {
        if (eVar == null) {
            return null;
        }
        return new C0975e(eVar.f1403Yl, eVar.mData);
    }

    /* renamed from: Zh */
    public int mo6663Zh() {
        return this.f1403Yl;
    }

    /* renamed from: b */
    public void mo6664b(byte[] bArr) {
        if (bArr != null) {
            this.mData = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.mData, 0, bArr.length);
            return;
        }
        throw new NullPointerException("EncodedStringValue: Text-string is null.");
    }

    public Object clone() {
        super.clone();
        byte[] bArr = this.mData;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        try {
            return new C0975e(this.f1403Yl, bArr2);
        } catch (Exception e) {
            Log.e("EncodedStringValue", "failed to clone an EncodedStringValue: " + this);
            e.printStackTrace();
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    /* renamed from: fc */
    public byte[] mo6666fc() {
        byte[] bArr = this.mData;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        return new java.lang.String(r3.mData);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        return new java.lang.String(r3.mData, "iso-8859-1");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getString() {
        /*
            r3 = this;
            int r0 = r3.f1403Yl
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = new java.lang.String
            byte[] r3 = r3.mData
            r0.<init>(r3)
            return r0
        L_0x000c:
            java.lang.String r0 = com.android.messaging.mmslib.p039a.C0973c.m2208va(r0)     // Catch:{ UnsupportedEncodingException -> 0x0018 }
            java.lang.String r1 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0018 }
            byte[] r2 = r3.mData     // Catch:{ UnsupportedEncodingException -> 0x0018 }
            r1.<init>(r2, r0)     // Catch:{ UnsupportedEncodingException -> 0x0018 }
            return r1
        L_0x0018:
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0022 }
            byte[] r1 = r3.mData     // Catch:{ UnsupportedEncodingException -> 0x0022 }
            java.lang.String r2 = "iso-8859-1"
            r0.<init>(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x0022 }
            return r0
        L_0x0022:
            java.lang.String r0 = new java.lang.String
            byte[] r3 = r3.mData
            r0.<init>(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0975e.getString():java.lang.String");
    }

    /* renamed from: k */
    public void mo6668k(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("Text-string is null.");
        } else if (this.mData == null) {
            this.mData = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.mData, 0, bArr.length);
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(this.mData);
                byteArrayOutputStream.write(bArr);
                this.mData = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                throw new NullPointerException("appendTextString: failed when write a new Text-string");
            }
        }
    }

    /* renamed from: a */
    public static C0975e[] m2211a(String[] strArr) {
        int length = strArr.length;
        if (length <= 0) {
            return null;
        }
        C0975e[] eVarArr = new C0975e[length];
        for (int i = 0; i < length; i++) {
            eVarArr[i] = new C0975e(106, strArr[i]);
        }
        return eVarArr;
    }

    public C0975e(int i, String str) {
        if (str != null) {
            this.f1403Yl = i;
            try {
                this.mData = str.getBytes(C0973c.m2208va(i));
            } catch (UnsupportedEncodingException e) {
                Log.e("EncodedStringValue", "Input encoding " + i + " must be supported.", e);
                this.mData = str.getBytes();
            }
        } else {
            throw new NullPointerException("EncodedStringValue: Text-string is null");
        }
    }
}
