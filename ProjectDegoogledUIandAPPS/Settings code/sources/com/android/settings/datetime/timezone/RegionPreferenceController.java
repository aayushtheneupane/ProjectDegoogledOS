package com.android.settings.datetime.timezone;

import android.content.Context;
import android.icu.text.LocaleDisplayNames;

public class RegionPreferenceController extends BaseTimeZonePreferenceController {
    private static final String PREFERENCE_KEY = "region";
    private final LocaleDisplayNames mLocaleDisplayNames;
    private String mRegionId = "";

    public RegionPreferenceController(Context context) {
        super(context, PREFERENCE_KEY);
        this.mLocaleDisplayNames = LocaleDisplayNames.getInstance(context.getResources().getConfiguration().getLocales().get(0));
    }

    public CharSequence getSummary() {
        return this.mLocaleDisplayNames.regionDisplayName(this.mRegionId);
    }

    public void setRegionId(String str) {
        this.mRegionId = str;
    }

    public String getRegionId() {
        return this.mRegionId;
    }
}
