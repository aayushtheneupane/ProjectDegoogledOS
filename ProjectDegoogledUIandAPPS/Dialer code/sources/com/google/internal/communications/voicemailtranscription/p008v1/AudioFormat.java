package com.google.internal.communications.voicemailtranscription.p008v1;

import com.google.protobuf.Internal;

/* renamed from: com.google.internal.communications.voicemailtranscription.v1.AudioFormat */
public enum AudioFormat implements Internal.EnumLite {
    AUDIO_FORMAT_UNSPECIFIED(0),
    AMR_NB_8KHZ(1);
    
    private final int value;

    private AudioFormat(int i) {
        this.value = i;
    }

    public static AudioFormat forNumber(int i) {
        if (i == 0) {
            return AUDIO_FORMAT_UNSPECIFIED;
        }
        if (i != 1) {
            return null;
        }
        return AMR_NB_8KHZ;
    }

    public final int getNumber() {
        return this.value;
    }
}
