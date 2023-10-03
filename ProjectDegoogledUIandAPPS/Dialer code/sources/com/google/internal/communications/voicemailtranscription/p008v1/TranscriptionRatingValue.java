package com.google.internal.communications.voicemailtranscription.p008v1;

import com.google.protobuf.Internal;

/* renamed from: com.google.internal.communications.voicemailtranscription.v1.TranscriptionRatingValue */
public enum TranscriptionRatingValue implements Internal.EnumLite {
    TRANSCRIPTION_RATING_VALUE_UNSPECIFIED(0),
    GOOD_TRANSCRIPTION(1),
    BAD_TRANSCRIPTION(2);
    
    private final int value;

    private TranscriptionRatingValue(int i) {
        this.value = i;
    }

    public static TranscriptionRatingValue forNumber(int i) {
        if (i == 0) {
            return TRANSCRIPTION_RATING_VALUE_UNSPECIFIED;
        }
        if (i == 1) {
            return GOOD_TRANSCRIPTION;
        }
        if (i != 2) {
            return null;
        }
        return BAD_TRANSCRIPTION;
    }

    public final int getNumber() {
        return this.value;
    }
}
