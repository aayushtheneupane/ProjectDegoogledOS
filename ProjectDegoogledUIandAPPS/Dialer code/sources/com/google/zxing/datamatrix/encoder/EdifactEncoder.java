package com.google.zxing.datamatrix.encoder;

final class EdifactEncoder implements Encoder {
    EdifactEncoder() {
    }

    private static String encodeToCodewords(CharSequence charSequence, int i) {
        int length = charSequence.length() - i;
        if (length != 0) {
            char charAt = charSequence.charAt(i);
            char c = 0;
            char charAt2 = length >= 2 ? charSequence.charAt(i + 1) : 0;
            char charAt3 = length >= 3 ? charSequence.charAt(i + 2) : 0;
            if (length >= 4) {
                c = charSequence.charAt(i + 3);
            }
            int i2 = (charAt << 18) + (charAt2 << 12) + (charAt3 << 6) + c;
            char c2 = (char) ((i2 >> 8) & 255);
            char c3 = (char) (i2 & 255);
            StringBuilder sb = new StringBuilder(3);
            sb.append((char) ((i2 >> 16) & 255));
            if (length >= 2) {
                sb.append(c2);
            }
            if (length >= 3) {
                sb.append(c3);
            }
            return sb.toString();
        }
        throw new IllegalStateException("StringBuilder must not be empty");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        throw null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(com.google.zxing.datamatrix.encoder.EncoderContext r8) {
        /*
            r7 = this;
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
        L_0x0005:
            boolean r0 = r8.hasMoreCharacters()
            r1 = 1
            r2 = 0
            r3 = 4
            if (r0 == 0) goto L_0x0056
            char r0 = r8.getCurrentChar()
            r4 = 32
            if (r0 < r4) goto L_0x001e
            r4 = 63
            if (r0 > r4) goto L_0x001e
            r7.append(r0)
            goto L_0x002c
        L_0x001e:
            r4 = 64
            if (r0 < r4) goto L_0x0051
            r4 = 94
            if (r0 > r4) goto L_0x0051
            int r0 = r0 + -64
            char r0 = (char) r0
            r7.append(r0)
        L_0x002c:
            int r0 = r8.pos
            int r0 = r0 + r1
            r8.pos = r0
            int r0 = r7.length()
            if (r0 < r3) goto L_0x0005
            java.lang.String r0 = encodeToCodewords(r7, r2)
            r8.writeCodewords(r0)
            r7.delete(r2, r3)
            java.lang.String r0 = r8.getMessage()
            int r4 = r8.pos
            int r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.lookAheadTest(r0, r4, r3)
            if (r0 == r3) goto L_0x0005
            r8.signalEncoderChange(r2)
            goto L_0x0056
        L_0x0051:
            com.google.zxing.datamatrix.encoder.HighLevelEncoder.illegalCharacter(r0)
            r7 = 0
            throw r7
        L_0x0056:
            r0 = 31
            r7.append(r0)
            int r0 = r7.length()     // Catch:{ all -> 0x00d1 }
            if (r0 != 0) goto L_0x0062
            goto L_0x00c5
        L_0x0062:
            r4 = 2
            if (r0 != r1) goto L_0x007e
            r8.updateSymbolInfo()     // Catch:{ all -> 0x00d1 }
            com.google.zxing.datamatrix.encoder.SymbolInfo r5 = r8.getSymbolInfo()     // Catch:{ all -> 0x00d1 }
            int r5 = r5.getDataCapacity()     // Catch:{ all -> 0x00d1 }
            int r6 = r8.getCodewordCount()     // Catch:{ all -> 0x00d1 }
            int r5 = r5 - r6
            int r6 = r8.getRemainingCharacters()     // Catch:{ all -> 0x00d1 }
            if (r6 != 0) goto L_0x007e
            if (r5 > r4) goto L_0x007e
            goto L_0x00c5
        L_0x007e:
            if (r0 > r3) goto L_0x00c9
            int r0 = r0 - r1
            java.lang.String r7 = encodeToCodewords(r7, r2)     // Catch:{ all -> 0x00d1 }
            boolean r3 = r8.hasMoreCharacters()     // Catch:{ all -> 0x00d1 }
            r3 = r3 ^ r1
            if (r3 == 0) goto L_0x008f
            if (r0 > r4) goto L_0x008f
            goto L_0x0090
        L_0x008f:
            r1 = r2
        L_0x0090:
            if (r0 > r4) goto L_0x00b7
            int r3 = r8.getCodewordCount()     // Catch:{ all -> 0x00d1 }
            int r3 = r3 + r0
            r8.updateSymbolInfo(r3)     // Catch:{ all -> 0x00d1 }
            com.google.zxing.datamatrix.encoder.SymbolInfo r3 = r8.getSymbolInfo()     // Catch:{ all -> 0x00d1 }
            int r3 = r3.getDataCapacity()     // Catch:{ all -> 0x00d1 }
            int r4 = r8.getCodewordCount()     // Catch:{ all -> 0x00d1 }
            int r3 = r3 - r4
            r4 = 3
            if (r3 < r4) goto L_0x00b7
            int r1 = r8.getCodewordCount()     // Catch:{ all -> 0x00d1 }
            int r3 = r7.length()     // Catch:{ all -> 0x00d1 }
            int r1 = r1 + r3
            r8.updateSymbolInfo(r1)     // Catch:{ all -> 0x00d1 }
            r1 = r2
        L_0x00b7:
            if (r1 == 0) goto L_0x00c2
            r8.resetSymbolInfo()     // Catch:{ all -> 0x00d1 }
            int r7 = r8.pos     // Catch:{ all -> 0x00d1 }
            int r7 = r7 - r0
            r8.pos = r7     // Catch:{ all -> 0x00d1 }
            goto L_0x00c5
        L_0x00c2:
            r8.writeCodewords(r7)     // Catch:{ all -> 0x00d1 }
        L_0x00c5:
            r8.signalEncoderChange(r2)
            return
        L_0x00c9:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00d1 }
            java.lang.String r0 = "Count must not exceed 4"
            r7.<init>(r0)     // Catch:{ all -> 0x00d1 }
            throw r7     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r7 = move-exception
            r8.signalEncoderChange(r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.EdifactEncoder.encode(com.google.zxing.datamatrix.encoder.EncoderContext):void");
    }
}
