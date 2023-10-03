package com.android.settings.datetime.timezone.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import libcore.timezone.CountryTimeZones;

public class FilteredCountryTimeZones {
    private final CountryTimeZones mCountryTimeZones;
    private final List<String> mTimeZoneIds;

    public FilteredCountryTimeZones(CountryTimeZones countryTimeZones) {
        this.mCountryTimeZones = countryTimeZones;
        this.mTimeZoneIds = Collections.unmodifiableList((List) countryTimeZones.getTimeZoneMappings().stream().filter($$Lambda$FilteredCountryTimeZones$ZNz2Mv2nKX1oBkvEJWubr7tgzck.INSTANCE).map($$Lambda$FilteredCountryTimeZones$FsMOOJ1705oUaAkdAvlcbIu0Itk.INSTANCE).collect(Collectors.toList()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r4 = r4.notUsedAfter;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ boolean lambda$new$0(libcore.timezone.CountryTimeZones.TimeZoneMapping r4) {
        /*
            boolean r0 = r4.showInPicker
            if (r0 == 0) goto L_0x0017
            java.lang.Long r4 = r4.notUsedAfter
            if (r4 == 0) goto L_0x0015
            long r0 = r4.longValue()
            r2 = 1546300800000(0x16806b5bc00, double:7.63974103417E-312)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x0017
        L_0x0015:
            r4 = 1
            goto L_0x0018
        L_0x0017:
            r4 = 0
        L_0x0018:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.datetime.timezone.model.FilteredCountryTimeZones.lambda$new$0(libcore.timezone.CountryTimeZones$TimeZoneMapping):boolean");
    }

    public List<String> getTimeZoneIds() {
        return this.mTimeZoneIds;
    }

    public String getRegionId() {
        return TimeZoneData.normalizeRegionId(this.mCountryTimeZones.getCountryIso());
    }
}
