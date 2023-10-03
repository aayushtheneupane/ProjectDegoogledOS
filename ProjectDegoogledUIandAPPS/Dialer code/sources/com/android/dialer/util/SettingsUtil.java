package com.android.dialer.util;

import android.provider.Settings;

public class SettingsUtil {
    private static final String DEFAULT_NOTIFICATION_URI_STRING = Settings.System.DEFAULT_NOTIFICATION_URI.toString();

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateRingtoneName(android.content.Context r4, android.os.Handler r5, int r6, java.lang.String r7, int r8) {
        /*
            r0 = 0
            r1 = 1
            if (r6 != r1) goto L_0x000a
            android.net.Uri r6 = android.media.RingtoneManager.getActualDefaultRingtoneUri(r4, r6)
        L_0x0008:
            r7 = r0
            goto L_0x002f
        L_0x000a:
            android.content.SharedPreferences r2 = android.preference.PreferenceManager.getDefaultSharedPreferences(r4)
            java.lang.String r3 = DEFAULT_NOTIFICATION_URI_STRING
            java.lang.String r7 = r2.getString(r7, r3)
            boolean r2 = android.text.TextUtils.isEmpty(r7)
            if (r2 == 0) goto L_0x001c
            r6 = 0
            goto L_0x0008
        L_0x001c:
            java.lang.String r2 = DEFAULT_NOTIFICATION_URI_STRING
            boolean r2 = r7.equals(r2)
            if (r2 == 0) goto L_0x002a
            android.net.Uri r6 = android.media.RingtoneManager.getActualDefaultRingtoneUri(r4, r6)
            r7 = r1
            goto L_0x002f
        L_0x002a:
            android.net.Uri r6 = android.net.Uri.parse(r7)
            goto L_0x0008
        L_0x002f:
            r2 = 2131821265(0x7f1102d1, float:1.9275268E38)
            java.lang.String r2 = r4.getString(r2)
            if (r6 != 0) goto L_0x0040
            r6 = 2131821263(0x7f1102cf, float:1.9275264E38)
            java.lang.String r2 = r4.getString(r6)
            goto L_0x0051
        L_0x0040:
            android.media.Ringtone r6 = android.media.RingtoneManager.getRingtone(r4, r6)
            if (r6 == 0) goto L_0x0051
            java.lang.String r6 = r6.getTitle(r4)     // Catch:{ SQLiteException -> 0x0051 }
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SQLiteException -> 0x0051 }
            if (r3 != 0) goto L_0x0051
            r2 = r6
        L_0x0051:
            if (r7 == 0) goto L_0x005e
            r6 = 2131820838(0x7f110126, float:1.9274402E38)
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r0] = r2
            java.lang.String r2 = r4.getString(r6, r7)
        L_0x005e:
            android.os.Message r4 = r5.obtainMessage(r8, r2)
            r5.sendMessage(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.util.SettingsUtil.updateRingtoneName(android.content.Context, android.os.Handler, int, java.lang.String, int):void");
    }
}
