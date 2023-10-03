package com.google.zxing.datamatrix.encoder;

import com.android.tools.p006r8.GeneratedOutlineSupport;

final class Base256Encoder implements Encoder {
    Base256Encoder() {
    }

    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(0);
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            sb.append(encoderContext.getCurrentChar());
            encoderContext.pos++;
            int lookAheadTest = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, 5);
            if (lookAheadTest != 5) {
                encoderContext.signalEncoderChange(lookAheadTest);
                break;
            }
        }
        int length = sb.length() - 1;
        int codewordCount = encoderContext.getCodewordCount() + length + 1;
        encoderContext.updateSymbolInfo(codewordCount);
        boolean z = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount > 0;
        if (encoderContext.hasMoreCharacters() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length <= 249 || length > 1555) {
                throw new IllegalStateException(GeneratedOutlineSupport.outline5("Message length not in valid ranges: ", length));
            } else {
                sb.setCharAt(0, (char) ((length / 250) + 249));
                sb.insert(1, (char) (length % 250));
            }
        }
        int length2 = sb.length();
        for (int i = 0; i < length2; i++) {
            int codewordCount2 = (((encoderContext.getCodewordCount() + 1) * 149) % 255) + 1 + sb.charAt(i);
            if (codewordCount2 > 255) {
                codewordCount2 -= 256;
            }
            encoderContext.writeCodeword((char) codewordCount2);
        }
    }
}
