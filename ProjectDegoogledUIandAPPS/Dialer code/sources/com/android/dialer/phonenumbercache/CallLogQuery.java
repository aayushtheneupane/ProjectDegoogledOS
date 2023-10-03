package com.android.dialer.phonenumbercache;

import android.os.Build;
import java.util.ArrayList;
import java.util.Arrays;

public final class CallLogQuery {
    private static final String[] PROJECTION_N = {"_id", "number", "date", "duration", "type", "countryiso", "voicemail_uri", "geocoded_location", "name", "numbertype", "numberlabel", "lookup_uri", "matched_number", "normalized_number", "photo_id", "formatted_number", "is_read", "presentation", "subscription_component_name", "subscription_id", "features", "data_usage", "transcription", "photo_uri", "post_dial_digits", "via_number"};
    private static final String[] PROJECTION_O;

    static {
        ArrayList arrayList = new ArrayList(Arrays.asList(PROJECTION_N));
        arrayList.add("transcription_state");
        PROJECTION_O = (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] getProjection() {
        int i = Build.VERSION.SDK_INT;
        return PROJECTION_O;
    }
}
