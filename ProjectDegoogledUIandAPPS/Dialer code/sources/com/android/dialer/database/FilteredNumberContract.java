package com.android.dialer.database;

import android.net.Uri;
import android.provider.BaseColumns;
import com.android.dialer.constants.Constants;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class FilteredNumberContract {
    public static final String AUTHORITY = Constants.get().getFilteredNumberProviderAuthority();
    public static final Uri AUTHORITY_URI;

    public static class FilteredNumber implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(FilteredNumberContract.AUTHORITY_URI, "filtered_numbers_table");
    }

    static {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("content://");
        outline13.append(AUTHORITY);
        AUTHORITY_URI = Uri.parse(outline13.toString());
    }
}
