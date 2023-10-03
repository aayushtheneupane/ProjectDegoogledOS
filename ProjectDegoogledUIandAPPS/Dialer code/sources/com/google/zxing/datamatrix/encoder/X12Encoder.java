package com.google.zxing.datamatrix.encoder;

final class X12Encoder extends C40Encoder {
    X12Encoder() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0077, code lost:
        throw null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(com.google.zxing.datamatrix.encoder.EncoderContext r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
        L_0x0005:
            boolean r0 = r7.hasMoreCharacters()
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0078
            char r0 = r7.getCurrentChar()
            int r4 = r7.pos
            int r4 = r4 + r3
            r7.pos = r4
            r4 = 13
            r5 = 3
            if (r0 != r4) goto L_0x0020
            r6.append(r2)
            goto L_0x0059
        L_0x0020:
            r4 = 42
            if (r0 != r4) goto L_0x0028
            r6.append(r3)
            goto L_0x0059
        L_0x0028:
            r4 = 62
            if (r0 != r4) goto L_0x0030
            r6.append(r1)
            goto L_0x0059
        L_0x0030:
            r4 = 32
            if (r0 != r4) goto L_0x0038
            r6.append(r5)
            goto L_0x0059
        L_0x0038:
            r4 = 48
            if (r0 < r4) goto L_0x0049
            r4 = 57
            if (r0 > r4) goto L_0x0049
            int r0 = r0 + -48
            int r0 = r0 + 4
            char r0 = (char) r0
            r6.append(r0)
            goto L_0x0059
        L_0x0049:
            r4 = 65
            if (r0 < r4) goto L_0x0073
            r4 = 90
            if (r0 > r4) goto L_0x0073
            int r0 = r0 + -65
            int r0 = r0 + 14
            char r0 = (char) r0
            r6.append(r0)
        L_0x0059:
            int r0 = r6.length()
            int r0 = r0 % r5
            if (r0 != 0) goto L_0x0005
            com.google.zxing.datamatrix.encoder.C40Encoder.writeNextTriplet(r7, r6)
            java.lang.String r0 = r7.getMessage()
            int r4 = r7.pos
            int r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.lookAheadTest(r0, r4, r5)
            if (r0 == r5) goto L_0x0005
            r7.signalEncoderChange(r0)
            goto L_0x0078
        L_0x0073:
            com.google.zxing.datamatrix.encoder.HighLevelEncoder.illegalCharacter(r0)
            r6 = 0
            throw r6
        L_0x0078:
            r7.updateSymbolInfo()
            com.google.zxing.datamatrix.encoder.SymbolInfo r0 = r7.getSymbolInfo()
            int r0 = r0.getDataCapacity()
            int r4 = r7.getCodewordCount()
            int r0 = r0 - r4
            int r6 = r6.length()
            r4 = 254(0xfe, float:3.56E-43)
            if (r6 != r1) goto L_0x009c
            r7.writeCodeword(r4)
            int r6 = r7.pos
            int r6 = r6 - r1
            r7.pos = r6
            r7.signalEncoderChange(r2)
            goto L_0x00ab
        L_0x009c:
            if (r6 != r3) goto L_0x00ab
            int r6 = r7.pos
            int r6 = r6 - r3
            r7.pos = r6
            if (r0 <= r3) goto L_0x00a8
            r7.writeCodeword(r4)
        L_0x00a8:
            r7.signalEncoderChange(r2)
        L_0x00ab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.X12Encoder.encode(com.google.zxing.datamatrix.encoder.EncoderContext):void");
    }

    /* access modifiers changed from: package-private */
    public int encodeChar(char c, StringBuilder sb) {
        if (c == 13) {
            sb.append(0);
        } else if (c == '*') {
            sb.append(1);
        } else if (c == '>') {
            sb.append(2);
        } else if (c == ' ') {
            sb.append(3);
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
        } else if (c < 'A' || c > 'Z') {
            HighLevelEncoder.illegalCharacter(c);
            throw null;
        } else {
            sb.append((char) ((c - 'A') + 14));
        }
        return 1;
    }

    public int getEncodingMode() {
        return 3;
    }

    /* access modifiers changed from: package-private */
    public void handleEOD(EncoderContext encoderContext, StringBuilder sb) {
        encoderContext.updateSymbolInfo();
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
        int length = sb.length();
        if (length == 2) {
            encoderContext.writeCodeword(254);
            encoderContext.pos -= 2;
            encoderContext.signalEncoderChange(0);
        } else if (length == 1) {
            encoderContext.pos--;
            if (dataCapacity > 1) {
                encoderContext.writeCodeword(254);
            }
            encoderContext.signalEncoderChange(0);
        }
    }
}
