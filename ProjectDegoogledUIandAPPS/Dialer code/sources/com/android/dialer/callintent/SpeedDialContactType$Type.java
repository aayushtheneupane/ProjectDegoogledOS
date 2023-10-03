package com.android.dialer.callintent;

import com.google.protobuf.Internal;

public enum SpeedDialContactType$Type implements Internal.EnumLite {
    UNDEFINED(0),
    PINNED_CONTACT(1),
    STARRED_CONTACT(2),
    FREQUENT_CONTACT(3);
    
    private final int value;

    private SpeedDialContactType$Type(int i) {
        this.value = i;
    }

    public static SpeedDialContactType$Type forNumber(int i) {
        if (i == 0) {
            return UNDEFINED;
        }
        if (i == 1) {
            return PINNED_CONTACT;
        }
        if (i == 2) {
            return STARRED_CONTACT;
        }
        if (i != 3) {
            return null;
        }
        return FREQUENT_CONTACT;
    }

    public final int getNumber() {
        return this.value;
    }
}
