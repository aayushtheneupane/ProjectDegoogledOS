package com.android.dialer.phonelookup.database.contract;

import android.net.Uri;
import com.android.dialer.constants.Constants;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class PhoneLookupHistoryContract {
    public static final String AUTHORITY = Constants.get().getPhoneLookupHistoryProviderAuthority();
    public static final Uri CONTENT_URI;

    public static final class PhoneLookupHistory {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(PhoneLookupHistoryContract.CONTENT_URI, "PhoneLookupHistory");

        public static Uri contentUriForNumber(String str) {
            return CONTENT_URI.buildUpon().appendQueryParameter("number", Uri.encode(str)).build();
        }
    }

    static {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("content://");
        outline13.append(AUTHORITY);
        CONTENT_URI = Uri.parse(outline13.toString());
    }
}
