package com.android.contacts.interactions;

import com.google.common.base.Preconditions;

public class ContactInteractionUtil {
    public static String questionMarks(int i) {
        Preconditions.checkArgument(i > 0);
        StringBuilder sb = new StringBuilder("(?");
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(",?");
        }
        sb.append(")");
        return sb.toString();
    }
}
