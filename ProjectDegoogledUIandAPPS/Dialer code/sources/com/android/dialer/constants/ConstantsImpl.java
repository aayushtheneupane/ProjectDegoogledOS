package com.android.dialer.constants;

import android.content.Context;

public class ConstantsImpl extends Constants {
    public String getAnnotatedCallLogProviderAuthority() {
        return "com.android.dialer.annotatedcalllog";
    }

    public String getFileProviderAuthority() {
        return "com.android.dialer.files";
    }

    public String getFilteredNumberProviderAuthority() {
        return "com.android.dialer.blocking.filterednumberprovider";
    }

    public String getPhoneLookupHistoryProviderAuthority() {
        return "com.android.dialer.phonelookuphistory";
    }

    public String getPreferredSimFallbackProviderAuthority() {
        return "com.android.dialer.preferredsimfallback";
    }

    public String getUserAgent(Context context) {
        return null;
    }
}
