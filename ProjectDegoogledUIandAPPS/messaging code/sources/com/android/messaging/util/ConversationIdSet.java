package com.android.messaging.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class ConversationIdSet extends HashSet {
    public ConversationIdSet() {
    }

    /* renamed from: Xa */
    public static ConversationIdSet m3538Xa(String str) {
        if (str != null) {
            return new ConversationIdSet(Arrays.asList(str.split("\\|")));
        }
        return null;
    }

    /* renamed from: Ul */
    public String mo8037Ul() {
        return C1464na.m3761a(this, "|");
    }

    public String first() {
        if (size() > 0) {
            return (String) iterator().next();
        }
        return null;
    }

    public ConversationIdSet(Collection collection) {
        super(collection);
    }
}
