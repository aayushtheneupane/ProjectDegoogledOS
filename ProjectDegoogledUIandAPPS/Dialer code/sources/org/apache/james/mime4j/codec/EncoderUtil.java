package org.apache.james.mime4j.codec;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.BitSet;

public class EncoderUtil {
    private static final BitSet ATEXT_CHARS = initChars("()<>@.,;:\\\"[]");
    private static final byte[] BASE64_TABLE = Base64OutputStream.BASE64_TABLE;
    private static final BitSet Q_REGULAR_CHARS = initChars("=_?");
    private static final BitSet Q_RESTRICTED_CHARS = initChars("=_?\"#$%&'(),.:;<>@[\\]^`{|}~");

    public enum Encoding {
        B,
        Q
    }

    public enum Usage {
        TEXT_TOKEN,
        WORD_ENTITY
    }

    static {
        initChars("()<>@,;:\\\"/[]?=");
    }

    private static byte[] encode(String str, Charset charset) {
        ByteBuffer encode = charset.encode(str);
        byte[] bArr = new byte[encode.limit()];
        encode.get(bArr);
        return bArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        r0 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encodeAddressDisplayName(java.lang.String r13) {
        /*
            int r0 = r13.length()
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0007:
            r4 = 1
            if (r2 >= r0) goto L_0x0023
            char r5 = r13.charAt(r2)
            java.util.BitSet r6 = ATEXT_CHARS
            boolean r6 = r6.get(r5)
            if (r6 == 0) goto L_0x0018
            r3 = r4
            goto L_0x0020
        L_0x0018:
            boolean r5 = org.apache.james.mime4j.util.CharsetUtil.isWhitespace(r5)
            if (r5 != 0) goto L_0x0020
            r3 = r1
            goto L_0x0023
        L_0x0020:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0023:
            if (r3 == 0) goto L_0x0026
            return r13
        L_0x0026:
            r0 = r1
            r2 = r0
        L_0x0028:
            int r3 = r13.length()
            r5 = 127(0x7f, float:1.78E-43)
            r6 = 32
            if (r0 >= r3) goto L_0x004d
            char r3 = r13.charAt(r0)
            r7 = 9
            if (r3 == r7) goto L_0x0049
            if (r3 != r6) goto L_0x003d
            goto L_0x0049
        L_0x003d:
            int r2 = r2 + r4
            r7 = 77
            if (r2 <= r7) goto L_0x0043
            goto L_0x0047
        L_0x0043:
            if (r3 < r6) goto L_0x0047
            if (r3 < r5) goto L_0x004a
        L_0x0047:
            r0 = r4
            goto L_0x004e
        L_0x0049:
            r2 = r1
        L_0x004a:
            int r0 = r0 + 1
            goto L_0x0028
        L_0x004d:
            r0 = r1
        L_0x004e:
            if (r0 == 0) goto L_0x00e1
            org.apache.james.mime4j.codec.EncoderUtil$Usage r9 = org.apache.james.mime4j.codec.EncoderUtil.Usage.WORD_ENTITY
            r0 = 255(0xff, float:3.57E-43)
            int r2 = r13.length()
            r3 = r1
        L_0x0059:
            if (r3 >= r2) goto L_0x006a
            char r7 = r13.charAt(r3)
            if (r7 <= r0) goto L_0x0064
            java.nio.charset.Charset r2 = org.apache.james.mime4j.util.CharsetUtil.UTF_8
            goto L_0x0071
        L_0x0064:
            if (r7 <= r5) goto L_0x0067
            r4 = r1
        L_0x0067:
            int r3 = r3 + 1
            goto L_0x0059
        L_0x006a:
            if (r4 == 0) goto L_0x006f
            java.nio.charset.Charset r2 = org.apache.james.mime4j.util.CharsetUtil.US_ASCII
            goto L_0x0071
        L_0x006f:
            java.nio.charset.Charset r2 = org.apache.james.mime4j.util.CharsetUtil.ISO_8859_1
        L_0x0071:
            r11 = r2
            byte[] r12 = encode(r13, r11)
            int r2 = r12.length
            if (r2 != 0) goto L_0x007c
            org.apache.james.mime4j.codec.EncoderUtil$Encoding r0 = org.apache.james.mime4j.codec.EncoderUtil.Encoding.Q
            goto L_0x00a7
        L_0x007c:
            org.apache.james.mime4j.codec.EncoderUtil$Usage r2 = org.apache.james.mime4j.codec.EncoderUtil.Usage.TEXT_TOKEN
            if (r9 != r2) goto L_0x0083
            java.util.BitSet r2 = Q_REGULAR_CHARS
            goto L_0x0085
        L_0x0083:
            java.util.BitSet r2 = Q_RESTRICTED_CHARS
        L_0x0085:
            r3 = r1
            r4 = r3
        L_0x0087:
            int r5 = r12.length
            if (r3 >= r5) goto L_0x009a
            byte r5 = r12[r3]
            r5 = r5 & r0
            if (r5 == r6) goto L_0x0097
            boolean r5 = r2.get(r5)
            if (r5 != 0) goto L_0x0097
            int r4 = r4 + 1
        L_0x0097:
            int r3 = r3 + 1
            goto L_0x0087
        L_0x009a:
            int r4 = r4 * 100
            int r0 = r12.length
            int r4 = r4 / r0
            r0 = 30
            if (r4 <= r0) goto L_0x00a5
            org.apache.james.mime4j.codec.EncoderUtil$Encoding r0 = org.apache.james.mime4j.codec.EncoderUtil.Encoding.B
            goto L_0x00a7
        L_0x00a5:
            org.apache.james.mime4j.codec.EncoderUtil$Encoding r0 = org.apache.james.mime4j.codec.EncoderUtil.Encoding.Q
        L_0x00a7:
            org.apache.james.mime4j.codec.EncoderUtil$Encoding r2 = org.apache.james.mime4j.codec.EncoderUtil.Encoding.B
            java.lang.String r3 = "=?"
            if (r0 != r2) goto L_0x00c6
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r3)
            java.lang.String r2 = r11.name()
            r0.append(r2)
            java.lang.String r2 = "?B?"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r13 = encodeB(r0, r13, r1, r11, r12)
            goto L_0x00e0
        L_0x00c6:
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r3)
            java.lang.String r1 = r11.name()
            r0.append(r1)
            java.lang.String r1 = "?Q?"
            r0.append(r1)
            java.lang.String r7 = r0.toString()
            r10 = 0
            r8 = r13
            java.lang.String r13 = encodeQ(r7, r8, r9, r10, r11, r12)
        L_0x00e0:
            return r13
        L_0x00e1:
            java.lang.String r0 = "[\\\\\"]"
            java.lang.String r1 = "\\\\$0"
            java.lang.String r13 = r13.replaceAll(r0, r1)
            java.lang.String r0 = "\""
            java.lang.String r13 = com.android.tools.p006r8.GeneratedOutlineSupport.outline9(r0, r13, r0)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.codec.EncoderUtil.encodeAddressDisplayName(java.lang.String):java.lang.String");
    }

    private static String encodeB(String str, String str2, int i, Charset charset, byte[] bArr) {
        int i2;
        int i3 = 0;
        if (str.length() + (((bArr.length + 2) / 3) * 4) + 2 <= 75 - i) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
            StringBuilder sb = new StringBuilder();
            int length = bArr.length;
            while (true) {
                i2 = length - 2;
                if (i3 >= i2) {
                    break;
                }
                byte b = ((bArr[i3] & 255) << 16) | ((bArr[i3 + 1] & 255) << 8) | (bArr[i3 + 2] & 255);
                sb.append((char) BASE64_TABLE[(b >> 18) & 63]);
                sb.append((char) BASE64_TABLE[(b >> 12) & 63]);
                sb.append((char) BASE64_TABLE[(b >> 6) & 63]);
                sb.append((char) BASE64_TABLE[b & 63]);
                i3 += 3;
            }
            if (i3 == i2) {
                int i4 = ((bArr[i3] & 255) << 16) | ((bArr[i3 + 1] & 255) << 8);
                sb.append((char) BASE64_TABLE[(i4 >> 18) & 63]);
                sb.append((char) BASE64_TABLE[(i4 >> 12) & 63]);
                sb.append((char) BASE64_TABLE[(i4 >> 6) & 63]);
                sb.append('=');
            } else if (i3 == length - 1) {
                int i5 = (bArr[i3] & 255) << 16;
                sb.append((char) BASE64_TABLE[(i5 >> 18) & 63]);
                sb.append((char) BASE64_TABLE[(i5 >> 12) & 63]);
                sb.append('=');
                sb.append('=');
            }
            outline13.append(sb.toString());
            outline13.append("?=");
            return outline13.toString();
        }
        String substring = str2.substring(0, str2.length() / 2);
        ByteBuffer encode = charset.encode(substring);
        byte[] bArr2 = new byte[encode.limit()];
        encode.get(bArr2);
        String encodeB = encodeB(str, substring, i, charset, bArr2);
        String substring2 = str2.substring(str2.length() / 2);
        ByteBuffer encode2 = charset.encode(substring2);
        byte[] bArr3 = new byte[encode2.limit()];
        encode2.get(bArr3);
        return GeneratedOutlineSupport.outline9(encodeB, " ", encodeB(str, substring2, 0, charset, bArr3));
    }

    private static String encodeQ(String str, String str2, Usage usage, int i, Charset charset, byte[] bArr) {
        BitSet bitSet = usage == Usage.TEXT_TOKEN ? Q_REGULAR_CHARS : Q_RESTRICTED_CHARS;
        int i2 = 0;
        for (byte b : bArr) {
            byte b2 = b & 255;
            i2 = (b2 != 32 && !bitSet.get(b2)) ? i2 + 3 : i2 + 1;
        }
        if (str.length() + i2 + 2 <= 75 - i) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
            BitSet bitSet2 = usage == Usage.TEXT_TOKEN ? Q_REGULAR_CHARS : Q_RESTRICTED_CHARS;
            StringBuilder sb = new StringBuilder();
            for (byte b3 : bArr) {
                byte b4 = b3 & 255;
                if (b4 == 32) {
                    sb.append('_');
                } else if (!bitSet2.get(b4)) {
                    sb.append('=');
                    sb.append(hexDigit(b4 >>> 4));
                    sb.append(hexDigit(b4 & 15));
                } else {
                    sb.append((char) b4);
                }
            }
            outline13.append(sb.toString());
            outline13.append("?=");
            return outline13.toString();
        }
        String substring = str2.substring(0, str2.length() / 2);
        ByteBuffer encode = charset.encode(substring);
        byte[] bArr2 = new byte[encode.limit()];
        encode.get(bArr2);
        String encodeQ = encodeQ(str, substring, usage, i, charset, bArr2);
        String substring2 = str2.substring(str2.length() / 2);
        ByteBuffer encode2 = charset.encode(substring2);
        byte[] bArr3 = new byte[encode2.limit()];
        encode2.get(bArr3);
        return GeneratedOutlineSupport.outline9(encodeQ, " ", encodeQ(str, substring2, usage, 0, charset, bArr3));
    }

    private static char hexDigit(int i) {
        return (char) (i < 10 ? i + 48 : (i - 10) + 65);
    }

    private static BitSet initChars(String str) {
        BitSet bitSet = new BitSet(128);
        for (char c = '!'; c < 127; c = (char) (c + 1)) {
            if (str.indexOf(c) == -1) {
                bitSet.set(c);
            }
        }
        return bitSet;
    }
}
