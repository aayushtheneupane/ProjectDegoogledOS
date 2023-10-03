package com.google.zxing.oned;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Can only encode CODE_128, but got ", barcodeFormat));
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0041 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean[] encode(java.lang.String r14) {
        /*
            r13 = this;
            int r13 = r14.length()
            r0 = 1
            if (r13 < r0) goto L_0x0103
            r1 = 80
            if (r13 > r1) goto L_0x0103
            r1 = 0
            r2 = r1
        L_0x000d:
            r3 = 32
            if (r2 >= r13) goto L_0x0038
            char r4 = r14.charAt(r2)
            if (r4 < r3) goto L_0x001b
            r3 = 126(0x7e, float:1.77E-43)
            if (r4 <= r3) goto L_0x0035
        L_0x001b:
            switch(r4) {
                case 241: goto L_0x0035;
                case 242: goto L_0x0035;
                case 243: goto L_0x0035;
                case 244: goto L_0x0035;
                default: goto L_0x001e;
            }
        L_0x001e:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "Bad character in input: "
            r14.append(r0)
            r14.append(r4)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L_0x0035:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x0038:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r6 = r0
            r3 = r1
            r4 = r3
            r5 = r4
        L_0x0041:
            if (r3 >= r13) goto L_0x00ba
            r7 = 99
            if (r5 != r7) goto L_0x0049
            r8 = 2
            goto L_0x004a
        L_0x0049:
            r8 = 4
        L_0x004a:
            int r8 = r8 + r3
            int r9 = r14.length()
            r10 = r8
            r8 = r3
        L_0x0051:
            if (r8 >= r10) goto L_0x006b
            if (r8 >= r9) goto L_0x006b
            char r11 = r14.charAt(r8)
            r12 = 48
            if (r11 < r12) goto L_0x0061
            r12 = 57
            if (r11 <= r12) goto L_0x0068
        L_0x0061:
            r12 = 241(0xf1, float:3.38E-43)
            if (r11 == r12) goto L_0x0066
            goto L_0x006f
        L_0x0066:
            int r10 = r10 + 1
        L_0x0068:
            int r8 = r8 + 1
            goto L_0x0051
        L_0x006b:
            if (r10 > r9) goto L_0x006f
            r8 = r0
            goto L_0x0070
        L_0x006f:
            r8 = r1
        L_0x0070:
            r9 = 100
            if (r8 == 0) goto L_0x0075
            goto L_0x0076
        L_0x0075:
            r7 = r9
        L_0x0076:
            if (r7 != r5) goto L_0x009f
            if (r5 != r9) goto L_0x0081
            char r7 = r14.charAt(r3)
            int r9 = r7 + -32
            goto L_0x009c
        L_0x0081:
            char r7 = r14.charAt(r3)
            switch(r7) {
                case 241: goto L_0x009a;
                case 242: goto L_0x0097;
                case 243: goto L_0x0094;
                case 244: goto L_0x009c;
                default: goto L_0x0088;
            }
        L_0x0088:
            int r7 = r3 + 2
            java.lang.String r3 = r14.substring(r3, r7)
            int r9 = java.lang.Integer.parseInt(r3)
            r3 = r7
            goto L_0x00ac
        L_0x0094:
            r9 = 96
            goto L_0x009c
        L_0x0097:
            r9 = 97
            goto L_0x009c
        L_0x009a:
            r9 = 102(0x66, float:1.43E-43)
        L_0x009c:
            int r3 = r3 + 1
            goto L_0x00ac
        L_0x009f:
            if (r5 != 0) goto L_0x00aa
            if (r7 != r9) goto L_0x00a6
            r5 = 104(0x68, float:1.46E-43)
            goto L_0x00a8
        L_0x00a6:
            r5 = 105(0x69, float:1.47E-43)
        L_0x00a8:
            r9 = r5
            goto L_0x00ab
        L_0x00aa:
            r9 = r7
        L_0x00ab:
            r5 = r7
        L_0x00ac:
            int[][] r7 = com.google.zxing.oned.Code128Reader.CODE_PATTERNS
            r7 = r7[r9]
            r2.add(r7)
            int r9 = r9 * r6
            int r4 = r4 + r9
            if (r3 == 0) goto L_0x0041
            int r6 = r6 + 1
            goto L_0x0041
        L_0x00ba:
            int r4 = r4 % 103
            int[][] r13 = com.google.zxing.oned.Code128Reader.CODE_PATTERNS
            r13 = r13[r4]
            r2.add(r13)
            int[][] r13 = com.google.zxing.oned.Code128Reader.CODE_PATTERNS
            r14 = 106(0x6a, float:1.49E-43)
            r13 = r13[r14]
            r2.add(r13)
            java.util.Iterator r13 = r2.iterator()
            r14 = r1
        L_0x00d1:
            boolean r3 = r13.hasNext()
            if (r3 == 0) goto L_0x00ea
            java.lang.Object r3 = r13.next()
            int[] r3 = (int[]) r3
            int r4 = r3.length
            r5 = r14
            r14 = r1
        L_0x00e0:
            if (r14 >= r4) goto L_0x00e8
            r6 = r3[r14]
            int r5 = r5 + r6
            int r14 = r14 + 1
            goto L_0x00e0
        L_0x00e8:
            r14 = r5
            goto L_0x00d1
        L_0x00ea:
            boolean[] r13 = new boolean[r14]
            java.util.Iterator r14 = r2.iterator()
        L_0x00f0:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto L_0x0102
            java.lang.Object r2 = r14.next()
            int[] r2 = (int[]) r2
            int r2 = com.google.zxing.oned.OneDimensionalCodeWriter.appendPattern(r13, r1, r2, r0)
            int r1 = r1 + r2
            goto L_0x00f0
        L_0x0102:
            return r13
        L_0x0103:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Contents length should be between 1 and 80 characters, but got "
            java.lang.String r13 = com.android.tools.p006r8.GeneratedOutlineSupport.outline5(r0, r13)
            r14.<init>(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.encode(java.lang.String):boolean[]");
    }
}
