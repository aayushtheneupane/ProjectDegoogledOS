package com.android.dialer.logging;

import com.google.protobuf.Internal;

public enum ReportingLocation$Type implements Internal.EnumLite {
    UNKNOWN_REPORTING_LOCATION(0),
    CALL_LOG_HISTORY(1),
    FEEDBACK_PROMPT(2),
    VOICEMAIL_HISTORY(3),
    CONTACT_DETAILS(4);
    
    private final int value;

    private ReportingLocation$Type(int i) {
        this.value = i;
    }

    public static ReportingLocation$Type forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_REPORTING_LOCATION;
        }
        if (i == 1) {
            return CALL_LOG_HISTORY;
        }
        if (i == 2) {
            return FEEDBACK_PROMPT;
        }
        if (i == 3) {
            return VOICEMAIL_HISTORY;
        }
        if (i != 4) {
            return null;
        }
        return CONTACT_DETAILS;
    }

    public final int getNumber() {
        return this.value;
    }
}
