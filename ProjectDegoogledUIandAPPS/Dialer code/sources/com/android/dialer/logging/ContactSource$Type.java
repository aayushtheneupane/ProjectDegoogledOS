package com.android.dialer.logging;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.Internal;

public enum ContactSource$Type implements Internal.EnumLite {
    UNKNOWN_SOURCE_TYPE(0),
    SOURCE_TYPE_DIRECTORY(1),
    SOURCE_TYPE_EXTENDED(2),
    SOURCE_TYPE_PLACES(3),
    SOURCE_TYPE_PROFILE(4),
    SOURCE_TYPE_CNAP(5),
    SOURCE_TYPE_CEQUINT_CALLER_ID(6),
    SOURCE_TYPE_REMOTE_OTHER(7),
    SOURCE_TYPE_REMOTE_MANUAL(8),
    SOURCE_TYPE_REMOTE_GOOGLE_VOICE(9),
    SOURCE_TYPE_REMOTE_CSA(10),
    SOURCE_TYPE_REMOTE_KNOWLEDGE_GRAPH(11);
    
    private final int value;

    private ContactSource$Type(int i) {
        this.value = i;
    }

    public static ContactSource$Type forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_SOURCE_TYPE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return SOURCE_TYPE_DIRECTORY;
            case 2:
                return SOURCE_TYPE_EXTENDED;
            case 3:
                return SOURCE_TYPE_PLACES;
            case 4:
                return SOURCE_TYPE_PROFILE;
            case 5:
                return SOURCE_TYPE_CNAP;
            case 6:
                return SOURCE_TYPE_CEQUINT_CALLER_ID;
            case 7:
                return SOURCE_TYPE_REMOTE_OTHER;
            case 8:
                return SOURCE_TYPE_REMOTE_MANUAL;
            case 9:
                return SOURCE_TYPE_REMOTE_GOOGLE_VOICE;
            case 10:
                return SOURCE_TYPE_REMOTE_CSA;
            case 11:
                return SOURCE_TYPE_REMOTE_KNOWLEDGE_GRAPH;
            default:
                return null;
        }
    }

    public final int getNumber() {
        return this.value;
    }
}
