package com.android.dialer.preferredsim;

import android.net.Uri;
import com.android.dialer.constants.Constants;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class PreferredSimFallbackContract {
    public static final String AUTHORITY = Constants.get().getPreferredSimFallbackProviderAuthority();
    public static final Uri CONTENT_URI;

    static {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("content://");
        outline13.append(AUTHORITY);
        CONTENT_URI = Uri.parse(outline13.toString());
    }
}
