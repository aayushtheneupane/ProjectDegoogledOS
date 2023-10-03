package com.google.zxing.aztec.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class HighLevelEncoder {
    private static final int[][] CHAR_MAP = ((int[][]) Array.newInstance(int.class, new int[]{5, 256}));
    static final int[][] LATCH_TABLE = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final String[] MODE_NAMES = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int[][] SHIFT_TABLE = ((int[][]) Array.newInstance(int.class, new int[]{6, 6}));
    private final byte[] text;

    static {
        CHAR_MAP[0][32] = 1;
        for (int i = 65; i <= 90; i++) {
            CHAR_MAP[0][i] = (i - 65) + 2;
        }
        CHAR_MAP[1][32] = 1;
        for (int i2 = 97; i2 <= 122; i2++) {
            CHAR_MAP[1][i2] = (i2 - 97) + 2;
        }
        CHAR_MAP[2][32] = 1;
        for (int i3 = 48; i3 <= 57; i3++) {
            CHAR_MAP[2][i3] = (i3 - 48) + 2;
        }
        int[][] iArr = CHAR_MAP;
        iArr[2][44] = 12;
        iArr[2][46] = 13;
        int[] iArr2 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            CHAR_MAP[3][iArr2[i4]] = i4;
        }
        int[] iArr3 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i5 = 0; i5 < iArr3.length; i5++) {
            if (iArr3[i5] > 0) {
                CHAR_MAP[4][iArr3[i5]] = i5;
            }
        }
        for (int[] fill : SHIFT_TABLE) {
            Arrays.fill(fill, -1);
        }
        int[][] iArr4 = SHIFT_TABLE;
        iArr4[0][4] = 0;
        iArr4[1][4] = 0;
        iArr4[1][0] = 28;
        iArr4[3][4] = 0;
        iArr4[2][4] = 0;
        iArr4[2][0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.text = bArr;
    }

    private static List<State> simplifyStates(Iterable<State> iterable) {
        LinkedList linkedList = new LinkedList();
        for (State next : iterable) {
            boolean z = true;
            Iterator it = linkedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                State state = (State) it.next();
                if (state.isBetterThanOrEqualTo(next)) {
                    z = false;
                    break;
                } else if (next.isBetterThanOrEqualTo(state)) {
                    it.remove();
                }
            }
            if (z) {
                linkedList.add(next);
            }
        }
        return linkedList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.common.BitArray encode() {
        /*
            r15 = this;
            com.google.zxing.aztec.encoder.State r0 = com.google.zxing.aztec.encoder.State.INITIAL_STATE
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1 = 0
            r2 = r0
            r0 = r1
        L_0x0009:
            byte[] r3 = r15.text
            int r4 = r3.length
            if (r0 >= r4) goto L_0x0126
            int r4 = r0 + 1
            int r5 = r3.length
            if (r4 >= r5) goto L_0x0016
            byte r3 = r3[r4]
            goto L_0x0017
        L_0x0016:
            r3 = r1
        L_0x0017:
            byte[] r5 = r15.text
            byte r5 = r5[r0]
            r6 = 13
            r7 = 3
            r8 = 4
            r9 = 2
            if (r5 == r6) goto L_0x003e
            r6 = 44
            r10 = 32
            if (r5 == r6) goto L_0x003a
            r6 = 46
            if (r5 == r6) goto L_0x0036
            r6 = 58
            if (r5 == r6) goto L_0x0032
        L_0x0030:
            r3 = r1
            goto L_0x0043
        L_0x0032:
            if (r3 != r10) goto L_0x0030
            r3 = 5
            goto L_0x0043
        L_0x0036:
            if (r3 != r10) goto L_0x0030
            r3 = r7
            goto L_0x0043
        L_0x003a:
            if (r3 != r10) goto L_0x0030
            r3 = r8
            goto L_0x0043
        L_0x003e:
            r5 = 10
            if (r3 != r5) goto L_0x0030
            r3 = r9
        L_0x0043:
            r5 = 1
            if (r3 <= 0) goto L_0x009e
            java.util.LinkedList r6 = new java.util.LinkedList
            r6.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x004f:
            boolean r10 = r2.hasNext()
            if (r10 == 0) goto L_0x0096
            java.lang.Object r10 = r2.next()
            com.google.zxing.aztec.encoder.State r10 = (com.google.zxing.aztec.encoder.State) r10
            com.google.zxing.aztec.encoder.State r11 = r10.endBinaryShift(r0)
            com.google.zxing.aztec.encoder.State r12 = r11.latchAndAppend(r8, r3)
            r6.add(r12)
            int r12 = r10.getMode()
            if (r12 == r8) goto L_0x0073
            com.google.zxing.aztec.encoder.State r12 = r11.shiftAndAppend(r8, r3)
            r6.add(r12)
        L_0x0073:
            if (r3 == r7) goto L_0x0077
            if (r3 != r8) goto L_0x0084
        L_0x0077:
            int r12 = 16 - r3
            com.google.zxing.aztec.encoder.State r11 = r11.latchAndAppend(r9, r12)
            com.google.zxing.aztec.encoder.State r11 = r11.latchAndAppend(r9, r5)
            r6.add(r11)
        L_0x0084:
            int r11 = r10.getBinaryShiftByteCount()
            if (r11 <= 0) goto L_0x004f
            com.google.zxing.aztec.encoder.State r10 = r10.addBinaryShiftChar(r0)
            com.google.zxing.aztec.encoder.State r10 = r10.addBinaryShiftChar(r4)
            r6.add(r10)
            goto L_0x004f
        L_0x0096:
            java.util.List r0 = simplifyStates(r6)
            r2 = r0
            r0 = r4
            goto L_0x0123
        L_0x009e:
            java.util.LinkedList r3 = new java.util.LinkedList
            r3.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x00a7:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x011f
            java.lang.Object r4 = r2.next()
            com.google.zxing.aztec.encoder.State r4 = (com.google.zxing.aztec.encoder.State) r4
            byte[] r6 = r15.text
            byte r6 = r6[r0]
            r6 = r6 & 255(0xff, float:3.57E-43)
            char r6 = (char) r6
            int[][] r7 = CHAR_MAP
            int r10 = r4.getMode()
            r7 = r7[r10]
            r7 = r7[r6]
            if (r7 <= 0) goto L_0x00c8
            r7 = r5
            goto L_0x00c9
        L_0x00c8:
            r7 = r1
        L_0x00c9:
            r10 = 0
            r11 = r10
            r10 = r1
        L_0x00cc:
            if (r10 > r8) goto L_0x0105
            int[][] r12 = CHAR_MAP
            r12 = r12[r10]
            r12 = r12[r6]
            if (r12 <= 0) goto L_0x0102
            if (r11 != 0) goto L_0x00dc
            com.google.zxing.aztec.encoder.State r11 = r4.endBinaryShift(r0)
        L_0x00dc:
            if (r7 == 0) goto L_0x00e6
            int r13 = r4.getMode()
            if (r10 == r13) goto L_0x00e6
            if (r10 != r9) goto L_0x00ed
        L_0x00e6:
            com.google.zxing.aztec.encoder.State r13 = r11.latchAndAppend(r10, r12)
            r3.add(r13)
        L_0x00ed:
            if (r7 != 0) goto L_0x0102
            int[][] r13 = SHIFT_TABLE
            int r14 = r4.getMode()
            r13 = r13[r14]
            r13 = r13[r10]
            if (r13 < 0) goto L_0x0102
            com.google.zxing.aztec.encoder.State r12 = r11.shiftAndAppend(r10, r12)
            r3.add(r12)
        L_0x0102:
            int r10 = r10 + 1
            goto L_0x00cc
        L_0x0105:
            int r7 = r4.getBinaryShiftByteCount()
            if (r7 > 0) goto L_0x0117
            int[][] r7 = CHAR_MAP
            int r10 = r4.getMode()
            r7 = r7[r10]
            r6 = r7[r6]
            if (r6 != 0) goto L_0x00a7
        L_0x0117:
            com.google.zxing.aztec.encoder.State r4 = r4.addBinaryShiftChar(r0)
            r3.add(r4)
            goto L_0x00a7
        L_0x011f:
            java.util.List r2 = simplifyStates(r3)
        L_0x0123:
            int r0 = r0 + r5
            goto L_0x0009
        L_0x0126:
            com.google.zxing.aztec.encoder.HighLevelEncoder$1 r0 = new com.google.zxing.aztec.encoder.HighLevelEncoder$1
            r0.<init>(r15)
            java.lang.Object r0 = java.util.Collections.min(r2, r0)
            com.google.zxing.aztec.encoder.State r0 = (com.google.zxing.aztec.encoder.State) r0
            byte[] r15 = r15.text
            com.google.zxing.common.BitArray r15 = r0.toBitArray(r15)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.encoder.HighLevelEncoder.encode():com.google.zxing.common.BitArray");
    }
}
