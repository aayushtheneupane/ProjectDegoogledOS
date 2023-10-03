package com.android.dialer.calllog.database.contract;

import android.net.Uri;
import android.provider.BaseColumns;
import com.android.dialer.constants.Constants;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class AnnotatedCallLogContract {
    public static final String AUTHORITY = Constants.get().getAnnotatedCallLogProviderAuthority();
    public static final Uri CONTENT_URI;

    public static final class AnnotatedCallLog implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AnnotatedCallLogContract.CONTENT_URI, "AnnotatedCallLog");
        public static final Uri DISTINCT_NUMBERS_CONTENT_URI = Uri.withAppendedPath(AnnotatedCallLogContract.CONTENT_URI, "DistinctPhoneNumbers");
    }

    static {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("content://");
        outline13.append(AUTHORITY);
        CONTENT_URI = Uri.parse(outline13.toString());
    }
}
