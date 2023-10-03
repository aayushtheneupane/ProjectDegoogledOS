package com.android.contacts;

import android.content.Context;
import android.content.res.Resources;

public class ContactStatusUtil {
    public static String getStatusString(Context context, int i) {
        Resources resources = context.getResources();
        if (i == 2 || i == 3) {
            return resources.getString(R.string.status_away);
        }
        if (i == 4) {
            return resources.getString(R.string.status_busy);
        }
        if (i != 5) {
            return null;
        }
        return resources.getString(R.string.status_available);
    }
}
