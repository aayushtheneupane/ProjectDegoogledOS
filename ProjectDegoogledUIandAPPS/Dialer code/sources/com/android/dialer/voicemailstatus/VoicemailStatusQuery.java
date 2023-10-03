package com.android.dialer.voicemailstatus;

import android.os.Build;
import java.util.ArrayList;
import java.util.Arrays;

public class VoicemailStatusQuery {
    private static final String[] PROJECTION_N = {"source_package", "settings_uri", "voicemail_access_uri", "configuration_state", "data_channel_state", "notification_channel_state", "quota_occupied", "quota_total"};
    private static final String[] PROJECTION_NMR1;

    static {
        ArrayList arrayList = new ArrayList(Arrays.asList(PROJECTION_N));
        arrayList.add("phone_account_component_name");
        arrayList.add("phone_account_id");
        arrayList.add("source_type");
        PROJECTION_NMR1 = (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] getProjection() {
        int i = Build.VERSION.SDK_INT;
        return PROJECTION_NMR1;
    }
}
