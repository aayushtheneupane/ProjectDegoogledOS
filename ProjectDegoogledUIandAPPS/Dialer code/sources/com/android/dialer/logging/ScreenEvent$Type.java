package com.android.dialer.logging;

import com.google.protobuf.Internal;

public enum ScreenEvent$Type implements Internal.EnumLite {
    UNKNOWN(0),
    DIALPAD(1),
    SPEED_DIAL(2),
    CALL_LOG(3),
    VOICEMAIL_LOG(4),
    ALL_CONTACTS(5),
    REGULAR_SEARCH(6),
    SMART_DIAL_SEARCH(7),
    CALL_LOG_FILTER(8),
    SETTINGS(9),
    IMPORT_EXPORT_CONTACTS(10),
    CLEAR_FREQUENTS(11),
    SEND_FEEDBACK(12),
    INCALL(13),
    INCOMING_CALL(14),
    CONFERENCE_MANAGEMENT(15),
    INCALL_DIALPAD(16),
    CALL_LOG_CONTEXT_MENU(17),
    BLOCKED_NUMBER_MANAGEMENT(18),
    BLOCKED_NUMBER_ADD_NUMBER(19),
    CALL_DETAILS(20),
    MAIN_SPEED_DIAL(21),
    MAIN_CALL_LOG(22),
    MAIN_CONTACTS(23),
    MAIN_VOICEMAIL(24),
    MAIN_DIALPAD(25),
    MAIN_SEARCH(26);
    
    private final int value;

    private ScreenEvent$Type(int i) {
        this.value = i;
    }
}
