package com.android.dialer.promotion.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.R;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.duo.Duo;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.promotion.Promotion;
import com.android.dialer.spannable.ContentWithLearnMoreSpanner;

final class DuoPromotion implements Promotion {
    static final String FLAG_DUO_DISCLOSURE_AUTO_DISMISS_AFTER_VIEWED_TIME_MILLIS = "show_duo_disclosure_auto_dismiss_after_viewed_time_millis";
    static final String FLAG_SHOW_DUO_DISCLOSURE = "show_duo_disclosure";
    private final Context appContext;
    private final ConfigProvider configProvider;
    private final Duo duo;
    private final SharedPreferences sharedPreferences;

    DuoPromotion(Context context, ConfigProvider configProvider2, Duo duo2, SharedPreferences sharedPreferences2) {
        this.appContext = context;
        this.configProvider = configProvider2;
        this.duo = duo2;
        this.sharedPreferences = sharedPreferences2;
    }

    public void dismiss() {
        this.sharedPreferences.edit().putBoolean("duo_disclosure_dismissed", true).apply();
    }

    public CharSequence getDetails() {
        return new ContentWithLearnMoreSpanner(this.appContext).create(this.appContext.getString(R.string.duo_disclosure_details), ((SharedPrefConfigProvider) this.configProvider).getString("duo_disclosure_link_full_url", "http://support.google.com/pixelphone/?p=dialer_duo"));
    }

    public int getIconRes() {
        ((DuoStub) this.duo).getLogo();
        return -1;
    }

    public CharSequence getTitle() {
        return this.appContext.getString(R.string.duo_disclosure_title);
    }

    public int getType() {
        return 1;
    }

    public boolean isEligibleToBeShown() {
        if (!((SharedPrefConfigProvider) this.configProvider).getBoolean(FLAG_SHOW_DUO_DISCLOSURE, false)) {
            return false;
        }
        ((DuoStub) this.duo).isEnabled(this.appContext);
        return false;
    }

    public void onViewed() {
        if (!this.sharedPreferences.contains("duo_disclosure_first_viewed_time_ms")) {
            this.sharedPreferences.edit().putLong("duo_disclosure_first_viewed_time_ms", System.currentTimeMillis()).apply();
        }
    }
}
