package com.google.zxing.datamatrix.encoder;

import com.android.tools.p006r8.GeneratedOutlineSupport;

final class ASCIIEncoder implements Encoder {
    ASCIIEncoder() {
    }

    public void encode(EncoderContext encoderContext) {
        int i;
        String message = encoderContext.getMessage();
        int i2 = encoderContext.pos;
        int length = message.length();
        if (i2 < length) {
            char charAt = message.charAt(i2);
            i = 0;
            while (HighLevelEncoder.isDigit(charAt) && i2 < length) {
                i++;
                i2++;
                if (i2 < length) {
                    charAt = message.charAt(i2);
                }
            }
        } else {
            i = 0;
        }
        if (i >= 2) {
            char charAt2 = encoderContext.getMessage().charAt(encoderContext.pos);
            char charAt3 = encoderContext.getMessage().charAt(encoderContext.pos + 1);
            if (!HighLevelEncoder.isDigit(charAt2) || !HighLevelEncoder.isDigit(charAt3)) {
                throw new IllegalArgumentException("not digits: " + charAt2 + charAt3);
            }
            encoderContext.writeCodeword((char) ((charAt3 - '0') + ((charAt2 - '0') * 10) + 130));
            encoderContext.pos += 2;
            return;
        }
        char currentChar = encoderContext.getCurrentChar();
        int lookAheadTest = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, 0);
        if (lookAheadTest != 0) {
            if (lookAheadTest == 1) {
                encoderContext.writeCodeword(230);
                encoderContext.signalEncoderChange(1);
            } else if (lookAheadTest == 2) {
                encoderContext.writeCodeword(239);
                encoderContext.signalEncoderChange(2);
            } else if (lookAheadTest == 3) {
                encoderContext.writeCodeword(238);
                encoderContext.signalEncoderChange(3);
            } else if (lookAheadTest == 4) {
                encoderContext.writeCodeword(240);
                encoderContext.signalEncoderChange(4);
            } else if (lookAheadTest == 5) {
                encoderContext.writeCodeword(231);
                encoderContext.signalEncoderChange(5);
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport.outline5("Illegal mode: ", lookAheadTest));
            }
        } else if (HighLevelEncoder.isExtendedASCII(currentChar)) {
            encoderContext.writeCodeword(235);
            encoderContext.writeCodeword((char) ((currentChar - 128) + 1));
            encoderContext.pos++;
        } else {
            encoderContext.writeCodeword((char) (currentChar + 1));
            encoderContext.pos++;
        }
    }
}
