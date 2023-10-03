package com.android.dialer.logging;

import com.google.protobuf.Internal;

public enum VideoTech$Type implements Internal.EnumLite {
    NONE(0),
    IMS_VIDEO_TECH(1),
    LIGHTBRINGER_VIDEO_TECH(2),
    RCS_VIDEO_SHARE(3);
    
    private final int value;

    private VideoTech$Type(int i) {
        this.value = i;
    }
}
