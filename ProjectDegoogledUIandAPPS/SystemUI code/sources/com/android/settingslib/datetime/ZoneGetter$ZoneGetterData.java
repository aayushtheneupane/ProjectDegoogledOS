package com.android.settingslib.datetime;

import java.util.List;
import libcore.timezone.TimeZoneFinder;

public final class ZoneGetter$ZoneGetterData {
    public List<String> lookupTimeZoneIdsByCountry(String str) {
        return TimeZoneFinder.getInstance().lookupTimeZoneIdsByCountry(str);
    }
}
