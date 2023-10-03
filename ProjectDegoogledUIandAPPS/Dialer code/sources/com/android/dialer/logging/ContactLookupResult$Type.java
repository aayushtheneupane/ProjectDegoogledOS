package com.android.dialer.logging;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.Internal;

public enum ContactLookupResult$Type implements Internal.EnumLite {
    UNKNOWN_LOOKUP_RESULT_TYPE(0),
    NOT_FOUND(1),
    LOCAL_CONTACT(2),
    LOCAL_CACHE(3),
    REMOTE(4),
    EMERGENCY(5),
    VOICEMAIL(6),
    REMOTE_PLACES(7),
    REMOTE_PROFILE(8),
    LOCAL_CACHE_UNKNOWN(9),
    LOCAL_CACHE_DIRECTORY(10),
    LOCAL_CACHE_EXTENDED(11),
    LOCAL_CACHE_PLACES(12),
    LOCAL_CACHE_PROFILE(13),
    LOCAL_CACHE_CNAP(14),
    LOCAL_CACHE_CEQUINT(15),
    REMOTE_OTHER(16),
    LOCAL_CACHE_REMOTE_OTHER(17),
    REMOTE_MANUAL(18),
    LOCAL_CACHE_REMOTE_MANUAL(19),
    REMOTE_GOOGLE_VOICE(20),
    LOCAL_CACHE_REMOTE_GOOGLE_VOICE(21),
    REMOTE_CSA(22),
    LOCAL_CACHE_REMOTE_CSA(23),
    REMOTE_KNOWLEDGE_GRAPH(24),
    LOCAL_CACHE_REMOTE_KNOWLEDGE_GRAPH(25),
    CEQUINT(26);
    
    private final int value;

    private ContactLookupResult$Type(int i) {
        this.value = i;
    }

    public static ContactLookupResult$Type forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_LOOKUP_RESULT_TYPE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return NOT_FOUND;
            case 2:
                return LOCAL_CONTACT;
            case 3:
                return LOCAL_CACHE;
            case 4:
                return REMOTE;
            case 5:
                return EMERGENCY;
            case 6:
                return VOICEMAIL;
            case 7:
                return REMOTE_PLACES;
            case 8:
                return REMOTE_PROFILE;
            case 9:
                return LOCAL_CACHE_UNKNOWN;
            case 10:
                return LOCAL_CACHE_DIRECTORY;
            case 11:
                return LOCAL_CACHE_EXTENDED;
            case 12:
                return LOCAL_CACHE_PLACES;
            case 13:
                return LOCAL_CACHE_PROFILE;
            case 14:
                return LOCAL_CACHE_CNAP;
            case 15:
                return LOCAL_CACHE_CEQUINT;
            case 16:
                return REMOTE_OTHER;
            case 17:
                return LOCAL_CACHE_REMOTE_OTHER;
            case 18:
                return REMOTE_MANUAL;
            case 19:
                return LOCAL_CACHE_REMOTE_MANUAL;
            case 20:
                return REMOTE_GOOGLE_VOICE;
            case 21:
                return LOCAL_CACHE_REMOTE_GOOGLE_VOICE;
            case 22:
                return REMOTE_CSA;
            case 23:
                return LOCAL_CACHE_REMOTE_CSA;
            case 24:
                return REMOTE_KNOWLEDGE_GRAPH;
            case 25:
                return LOCAL_CACHE_REMOTE_KNOWLEDGE_GRAPH;
            case 26:
                return CEQUINT;
            default:
                return null;
        }
    }
}
