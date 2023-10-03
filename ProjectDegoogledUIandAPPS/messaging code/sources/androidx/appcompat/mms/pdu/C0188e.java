package androidx.appcompat.mms.pdu;

import android.util.Log;
import java.io.UnsupportedEncodingException;

/* renamed from: androidx.appcompat.mms.pdu.e */
public class C0188e implements Cloneable {

    /* renamed from: Yl */
    private int f174Yl;
    private byte[] mData;

    public C0188e(int i, byte[] bArr) {
        if (bArr != null) {
            this.f174Yl = i;
            this.mData = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.mData, 0, bArr.length);
            return;
        }
        throw new NullPointerException("EncodedStringValue: Text-string is null.");
    }

    /* renamed from: b */
    public void mo1236b(byte[] bArr) {
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
            return new C0188e(this.f174Yl, bArr2);
        } catch (Exception e) {
            Log.e("EncodedStringValue", "failed to clone an EncodedStringValue: " + this);
            e.printStackTrace();
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    /* renamed from: fc */
    public byte[] mo1238fc() {
        byte[] bArr = this.mData;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public C0188e(byte[] bArr) {
        this(106, bArr);
    }

    public C0188e(String str) {
        try {
            this.mData = str.getBytes("utf-8");
            this.f174Yl = 106;
        } catch (UnsupportedEncodingException e) {
            Log.e("EncodedStringValue", "Default encoding must be supported.", e);
        }
    }
}
