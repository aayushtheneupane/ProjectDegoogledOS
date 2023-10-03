package com.android.dialer.promotion.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.R;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.promotion.Promotion;
import com.android.dialer.spannable.ContentWithLearnMoreSpanner;

public final class RttPromotion implements Promotion {
    private final Context appContext;
    private final ConfigProvider configProvider;
    private final SharedPreferences sharedPreferences;

    RttPromotion(Context context, SharedPreferences sharedPreferences2, ConfigProvider configProvider2) {
        this.appContext = context;
        this.sharedPreferences = sharedPreferences2;
        this.configProvider = configProvider2;
    }

    public void dismiss() {
        this.sharedPreferences.edit().putBoolean("rtt_promotion_dismissed", true).apply();
    }

    public CharSequence getDetails() {
        return new ContentWithLearnMoreSpanner(this.appContext).create(this.appContext.getString(R.string.rtt_promotion_details), ((SharedPrefConfigProvider) this.configProvider).getString("rtt_promo_learn_more_link_full_url", "http://support.google.com/pixelphone/?p=dialer_rtt"));
    }

    public int getIconRes() {
        return R.drawable.quantum_ic_rtt_vd_theme_24;
    }

    public CharSequence getTitle() {
        return this.appContext.getString(R.string.rtt_promotion_title);
    }

    public int getType() {
        return 2;
    }

    public boolean isEligibleToBeShown() {
        if (!this.sharedPreferences.getBoolean("rtt_promotion_enabled", false) || this.sharedPreferences.getBoolean("rtt_promotion_dismissed", false)) {
            return false;
        }
        return true;
    }
}
