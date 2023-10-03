package com.google.internal.communications.voicemailtranscription.p008v1;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.Internal;

/* renamed from: com.google.internal.communications.voicemailtranscription.v1.TranscriptionStatus */
public enum TranscriptionStatus implements Internal.EnumLite {
    TRANSCRIPTION_STATUS_UNSPECIFIED(0),
    SUCCESS(1),
    PENDING(2),
    EXPIRED(3),
    FAILED_RETRY(4),
    FAILED_NO_RETRY(5),
    FAILED_LANGUAGE_NOT_SUPPORTED(6),
    FAILED_NO_SPEECH_DETECTED(7);
    
    private final int value;

    private TranscriptionStatus(int i) {
        this.value = i;
    }

    public static TranscriptionStatus forNumber(int i) {
        switch (i) {
            case 0:
                return TRANSCRIPTION_STATUS_UNSPECIFIED;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                return SUCCESS;
            case 2:
                return PENDING;
            case 3:
                return EXPIRED;
            case 4:
                return FAILED_RETRY;
            case 5:
                return FAILED_NO_RETRY;
            case 6:
                return FAILED_LANGUAGE_NOT_SUPPORTED;
            case 7:
                return FAILED_NO_SPEECH_DETECTED;
            default:
                return null;
        }
    }
}
