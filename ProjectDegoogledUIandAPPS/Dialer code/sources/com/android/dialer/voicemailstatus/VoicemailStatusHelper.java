package com.android.dialer.voicemailstatus;

import android.database.Cursor;

public final class VoicemailStatusHelper {
    public static int getNumberActivityVoicemailSources(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return 0;
        }
        int i = 0;
        do {
            boolean z = true;
            if (cursor.getString(0) == null || cursor.isNull(3) || cursor.getInt(3) == 1) {
                z = false;
            }
            if (z) {
                i++;
            }
        } while (cursor.moveToNext());
        return i;
    }
}
