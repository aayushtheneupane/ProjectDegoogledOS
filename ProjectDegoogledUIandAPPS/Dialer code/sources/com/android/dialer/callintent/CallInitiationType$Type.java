package com.android.dialer.callintent;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.Internal;

public enum CallInitiationType$Type implements Internal.EnumLite {
    UNKNOWN_INITIATION(0),
    INCOMING_INITIATION(1),
    DIALPAD(2),
    SPEED_DIAL(3),
    SPEED_DIAL_DISAMBIG_DIALOG(20),
    REMOTE_DIRECTORY(4),
    SMART_DIAL(5),
    REGULAR_SEARCH(6),
    CALL_LOG(7),
    CALL_LOG_FILTER(8),
    VOICEMAIL_LOG(9),
    CALL_DETAILS(10),
    QUICK_CONTACTS(11),
    EXTERNAL_INITIATION(12),
    LAUNCHER_SHORTCUT(13),
    CALL_COMPOSER(14),
    MISSED_CALL_NOTIFICATION(15),
    CALL_SUBJECT_DIALOG(16),
    IMS_VIDEO_BLOCKED_FALLBACK_TO_VOICE(17),
    LEGACY_VOICEMAIL_NOTIFICATION(18),
    VOICEMAIL_ERROR_MESSAGE(19);
    
    private final int value;

    private CallInitiationType$Type(int i) {
        this.value = i;
    }

    public static CallInitiationType$Type forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_INITIATION;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return INCOMING_INITIATION;
            case 2:
                return DIALPAD;
            case 3:
                return SPEED_DIAL;
            case 4:
                return REMOTE_DIRECTORY;
            case 5:
                return SMART_DIAL;
            case 6:
                return REGULAR_SEARCH;
            case 7:
                return CALL_LOG;
            case 8:
                return CALL_LOG_FILTER;
            case 9:
                return VOICEMAIL_LOG;
            case 10:
                return CALL_DETAILS;
            case 11:
                return QUICK_CONTACTS;
            case 12:
                return EXTERNAL_INITIATION;
            case 13:
                return LAUNCHER_SHORTCUT;
            case 14:
                return CALL_COMPOSER;
            case 15:
                return MISSED_CALL_NOTIFICATION;
            case 16:
                return CALL_SUBJECT_DIALOG;
            case 17:
                return IMS_VIDEO_BLOCKED_FALLBACK_TO_VOICE;
            case 18:
                return LEGACY_VOICEMAIL_NOTIFICATION;
            case 19:
                return VOICEMAIL_ERROR_MESSAGE;
            case 20:
                return SPEED_DIAL_DISAMBIG_DIALOG;
            default:
                return null;
        }
    }

    public final int getNumber() {
        return this.value;
    }
}
