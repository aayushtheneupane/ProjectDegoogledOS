package com.android.dialer;

import com.google.protobuf.Internal;

public enum Mode implements Internal.EnumLite {
    MODE_UNSPECIFIED(0),
    BUBBLE(1);
    
    private final int value;

    private Mode(int i) {
        this.value = i;
    }

    public static Mode forNumber(int i) {
        if (i == 0) {
            return MODE_UNSPECIFIED;
        }
        if (i != 1) {
            return null;
        }
        return BUBBLE;
    }
}
