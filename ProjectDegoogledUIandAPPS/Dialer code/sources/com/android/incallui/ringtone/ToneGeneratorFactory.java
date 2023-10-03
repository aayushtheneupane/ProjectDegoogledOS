package com.android.incallui.ringtone;

import android.media.ToneGenerator;

public class ToneGeneratorFactory {
    public ToneGenerator newInCallToneGenerator(int i, int i2) {
        return new ToneGenerator(i, i2);
    }
}
