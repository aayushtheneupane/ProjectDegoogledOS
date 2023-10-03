package com.android.dialer.phonenumbercache;

final class PhoneQuery {
    static final String[] ADDITIONAL_CONTACT_INFO_PROJECTION = {"display_name_alt", "carrier_presence"};
    static final String[] DISPLAY_NAME_ALTERNATIVE_PROJECTION = {"display_name_alt"};
    private static final String[] PHONE_LOOKUP_PROJECTION = {"contact_id", "display_name", "type", "label", "number", "normalized_number", "photo_id", "lookup", "photo_uri"};

    static String[] getPhoneLookupProjection() {
        return PHONE_LOOKUP_PROJECTION;
    }
}
