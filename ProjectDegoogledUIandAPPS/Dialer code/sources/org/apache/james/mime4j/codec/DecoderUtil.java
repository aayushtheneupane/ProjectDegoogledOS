package org.apache.james.mime4j.codec;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class DecoderUtil {
    private static final Pattern PATTERN_ENCODED_WORD = Pattern.compile("(.*?)=\\?(.+?)\\?(\\w)\\?(.+?)\\?=", 32);

    static String decodeB(String str, String str2, DecodeMonitor decodeMonitor) throws UnsupportedEncodingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Base64InputStream base64InputStream = new Base64InputStream(new ByteArrayInputStream(str.getBytes("US-ASCII")), decodeMonitor);
            while (true) {
                int read = base64InputStream.read();
                if (read == -1) {
                    return new String(byteArrayOutputStream.toByteArray(), str2);
                }
                byteArrayOutputStream.write(read);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0116, code lost:
        if (r2 == false) goto L_0x0121;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String decodeEncodedWords(java.lang.String r21, org.apache.james.mime4j.codec.DecodeMonitor r22) throws java.lang.IllegalArgumentException {
        /*
            r1 = r21
            r8 = r22
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.util.regex.Pattern r0 = PATTERN_ENCODED_WORD
            java.util.regex.Matcher r10 = r0.matcher(r1)
            r11 = 0
            r0 = r11
            r12 = r0
        L_0x0012:
            boolean r2 = r10.find()
            if (r2 == 0) goto L_0x0133
            r13 = 1
            java.lang.String r14 = r10.group(r13)
            r15 = 2
            java.lang.String r16 = r10.group(r15)
            r7 = 3
            java.lang.String r6 = r10.group(r7)
            r0 = 4
            java.lang.String r5 = r10.group(r0)
            java.nio.charset.Charset r0 = org.apache.james.mime4j.util.CharsetUtil.lookup(r16)
            r17 = 0
            if (r0 != 0) goto L_0x004f
            java.lang.String[] r7 = new java.lang.String[r7]
            java.lang.String r0 = "Mime charser '"
            r7[r11] = r0
            r7[r13] = r16
            java.lang.String r0 = "' doesn't have a corresponding Java charset"
            r7[r15] = r0
            java.lang.String r0 = "leaving word encoded"
            r2 = r22
            r3 = r16
            r4 = r6
            r6 = r0
            monitor(r2, r3, r4, r5, r6, r7)
        L_0x004b:
            r0 = r17
            goto L_0x00f1
        L_0x004f:
            int r2 = r5.length()
            if (r2 != 0) goto L_0x0067
            java.lang.String r0 = "Missing encoded text in encoded word"
            java.lang.String[] r7 = new java.lang.String[]{r0}
            java.lang.String r0 = "leaving word encoded"
            r2 = r22
            r3 = r16
            r4 = r6
            r6 = r0
            monitor(r2, r3, r4, r5, r6, r7)
            goto L_0x004b
        L_0x0067:
            java.lang.String r2 = "Q"
            boolean r2 = r6.equalsIgnoreCase(r2)     // Catch:{ UnsupportedEncodingException -> 0x00cb, RuntimeException -> 0x00a6 }
            if (r2 == 0) goto L_0x0078
            java.lang.String r0 = r0.name()     // Catch:{ UnsupportedEncodingException -> 0x00cb, RuntimeException -> 0x00a6 }
            java.lang.String r17 = decodeQ(r5, r0, r8)     // Catch:{ UnsupportedEncodingException -> 0x00cb, RuntimeException -> 0x00a6 }
            goto L_0x004b
        L_0x0078:
            java.lang.String r2 = "B"
            boolean r2 = r6.equalsIgnoreCase(r2)     // Catch:{ UnsupportedEncodingException -> 0x00cb, RuntimeException -> 0x00a6 }
            if (r2 == 0) goto L_0x0089
            java.lang.String r0 = r0.name()     // Catch:{ UnsupportedEncodingException -> 0x00cb, RuntimeException -> 0x00a6 }
            java.lang.String r17 = decodeB(r5, r0, r8)     // Catch:{ UnsupportedEncodingException -> 0x00cb, RuntimeException -> 0x00a6 }
            goto L_0x004b
        L_0x0089:
            java.lang.String r0 = "leaving word encoded"
            java.lang.String r2 = "Warning: Unknown encoding in encoded word"
            java.lang.String[] r18 = new java.lang.String[]{r2}     // Catch:{ UnsupportedEncodingException -> 0x00cb, RuntimeException -> 0x00a6 }
            r2 = r22
            r3 = r16
            r4 = r6
            r19 = r5
            r20 = r6
            r6 = r0
            r15 = r7
            r7 = r18
            monitor(r2, r3, r4, r5, r6, r7)     // Catch:{ UnsupportedEncodingException -> 0x00a4, RuntimeException -> 0x00a2 }
            goto L_0x004b
        L_0x00a2:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a4:
            r0 = move-exception
            goto L_0x00d1
        L_0x00a6:
            r0 = move-exception
            r19 = r5
            r20 = r6
            r15 = r7
        L_0x00ac:
            java.lang.String[] r7 = new java.lang.String[r15]
            java.lang.String r2 = "Could not decode ("
            r7[r11] = r2
            java.lang.String r0 = r0.getMessage()
            r7[r13] = r0
            java.lang.String r0 = ") encoded word"
            r2 = 2
            r7[r2] = r0
            java.lang.String r6 = "leaving word encoded"
            r2 = r22
            r3 = r16
            r4 = r20
            r5 = r19
            monitor(r2, r3, r4, r5, r6, r7)
            goto L_0x004b
        L_0x00cb:
            r0 = move-exception
            r19 = r5
            r20 = r6
            r15 = r7
        L_0x00d1:
            java.lang.String[] r7 = new java.lang.String[r15]
            java.lang.String r2 = "Unsupported encoding ("
            r7[r11] = r2
            java.lang.String r0 = r0.getMessage()
            r7[r13] = r0
            java.lang.String r0 = ") in encoded word"
            r2 = 2
            r7[r2] = r0
            java.lang.String r6 = "leaving word encoded"
            r2 = r22
            r3 = r16
            r4 = r20
            r5 = r19
            monitor(r2, r3, r4, r5, r6, r7)
            goto L_0x004b
        L_0x00f1:
            if (r0 != 0) goto L_0x00fb
            java.lang.String r2 = r10.group(r11)
            r9.append(r2)
            goto L_0x0127
        L_0x00fb:
            if (r12 == 0) goto L_0x0121
            if (r14 == 0) goto L_0x0119
            int r2 = r14.length()
            r3 = r11
        L_0x0104:
            if (r3 >= r2) goto L_0x0115
            char r4 = r14.charAt(r3)
            boolean r4 = org.apache.james.mime4j.util.CharsetUtil.isWhitespace(r4)
            if (r4 != 0) goto L_0x0112
            r2 = r11
            goto L_0x0116
        L_0x0112:
            int r3 = r3 + 1
            goto L_0x0104
        L_0x0115:
            r2 = r13
        L_0x0116:
            if (r2 != 0) goto L_0x0124
            goto L_0x0121
        L_0x0119:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "String may not be null"
            r0.<init>(r1)
            throw r0
        L_0x0121:
            r9.append(r14)
        L_0x0124:
            r9.append(r0)
        L_0x0127:
            int r2 = r10.end()
            if (r0 == 0) goto L_0x012f
            r12 = r13
            goto L_0x0130
        L_0x012f:
            r12 = r11
        L_0x0130:
            r0 = r2
            goto L_0x0012
        L_0x0133:
            if (r0 != 0) goto L_0x0136
            return r1
        L_0x0136:
            java.lang.String r0 = r1.substring(r0)
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.codec.DecoderUtil.decodeEncodedWords(java.lang.String, org.apache.james.mime4j.codec.DecodeMonitor):java.lang.String");
    }

    static String decodeQ(String str, String str2, DecodeMonitor decodeMonitor) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(128);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '_') {
                sb.append("=20");
            } else {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            QuotedPrintableInputStream quotedPrintableInputStream = new QuotedPrintableInputStream(new ByteArrayInputStream(sb2.getBytes("US-ASCII")), decodeMonitor);
            while (true) {
                int read = quotedPrintableInputStream.read();
                if (read == -1) {
                    return new String(byteArrayOutputStream.toByteArray(), str2);
                }
                byteArrayOutputStream.write(read);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void monitor(DecodeMonitor decodeMonitor, String str, String str2, String str3, String str4, String... strArr) throws IllegalArgumentException {
        if (decodeMonitor.isListening()) {
            StringBuilder sb = new StringBuilder();
            sb.append("=?");
            sb.append(str);
            sb.append("?");
            sb.append(str2);
            sb.append("?");
            String outline12 = GeneratedOutlineSupport.outline12(sb, str3, "?=");
            StringBuilder sb2 = new StringBuilder();
            for (String append : strArr) {
                sb2.append(append);
            }
            sb2.append(" (");
            sb2.append(outline12);
            sb2.append(")");
            if (decodeMonitor.warn(sb2.toString(), str4)) {
                throw new IllegalArgumentException(sb2.toString());
            }
        }
    }
}
