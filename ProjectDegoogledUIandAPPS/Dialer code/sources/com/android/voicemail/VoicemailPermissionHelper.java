package com.android.voicemail;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class VoicemailPermissionHelper {
    private static final String[] VOICEMAIL_PERMISSIONS = {"com.android.voicemail.permission.ADD_VOICEMAIL", "com.android.voicemail.permission.WRITE_VOICEMAIL", "com.android.voicemail.permission.READ_VOICEMAIL", "android.permission.READ_PHONE_STATE", "android.permission.SEND_SMS"};

    public static List<String> getMissingPermissions(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : VOICEMAIL_PERMISSIONS) {
            if (context.checkSelfPermission(str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
