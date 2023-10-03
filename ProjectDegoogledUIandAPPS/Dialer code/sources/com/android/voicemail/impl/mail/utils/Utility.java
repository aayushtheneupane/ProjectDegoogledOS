package com.android.voicemail.impl.mail.utils;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Utility {
    public static final Charset ASCII = Charset.forName("US-ASCII");
    public static final String[] EMPTY_STRINGS = new String[0];

    public static String combine(Object[] objArr, char c) {
        if (objArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objArr.length; i++) {
            sb.append(objArr[i].toString());
            if (i < objArr.length - 1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static ByteArrayInputStream streamFromAsciiString(String str) {
        byte[] bArr;
        Charset charset = ASCII;
        if (str == null) {
            bArr = null;
        } else {
            ByteBuffer encode = charset.encode(CharBuffer.wrap(str));
            byte[] bArr2 = new byte[encode.limit()];
            encode.get(bArr2);
            bArr = bArr2;
        }
        return new ByteArrayInputStream(bArr);
    }
}
