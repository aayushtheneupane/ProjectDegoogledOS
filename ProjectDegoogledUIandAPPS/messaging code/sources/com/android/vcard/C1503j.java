package com.android.vcard;

import com.android.vcard.VCardUtils;
import java.io.ByteArrayOutputStream;

/* renamed from: com.android.vcard.j */
class C1503j {
    /* renamed from: r */
    public static final byte[] m3942r(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == 61) {
                int i2 = i + 1;
                try {
                    int digit = Character.digit((char) bArr[i2], 16);
                    i = i2 + 1;
                    int digit2 = Character.digit((char) bArr[i], 16);
                    if (digit == -1 || digit2 == -1) {
                        throw new VCardUtils.DecoderException("Invalid quoted-printable encoding");
                    }
                    byteArrayOutputStream.write((char) ((digit << 4) + digit2));
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw new VCardUtils.DecoderException("Invalid quoted-printable encoding");
                }
            } else {
                byteArrayOutputStream.write(b);
            }
            i++;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
