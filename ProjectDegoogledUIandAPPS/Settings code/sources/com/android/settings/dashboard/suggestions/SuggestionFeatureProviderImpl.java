package com.android.settings.dashboard.suggestions;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.android.settings.Settings;
import com.android.settings.biometrics.fingerprint.FingerprintEnrollSuggestionActivity;
import com.android.settings.biometrics.fingerprint.FingerprintSuggestionActivity;
import com.android.settings.display.NightDisplayPreferenceController;
import com.android.settings.notification.ZenOnboardingActivity;
import com.android.settings.notification.ZenSuggestionActivity;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.password.ScreenLockSuggestionActivity;
import com.android.settings.wallpaper.StyleSuggestionActivity;
import com.android.settings.wallpaper.WallpaperSuggestionActivity;
import com.android.settings.wifi.calling.WifiCallingSuggestionActivity;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

public class SuggestionFeatureProviderImpl implements SuggestionFeatureProvider {
    private static final String SHARED_PREF_FILENAME = "suggestions";
    private static final String TAG = "SuggestionFeature";
    private final MetricsFeatureProvider mMetricsFeatureProvider;

    public boolean isSuggestionEnabled(Context context) {
        return !((ActivityManager) context.getSystemService("activity")).isLowRamDevice();
    }

    public ComponentName getSuggestionServiceComponent() {
        return new ComponentName("com.android.settings.intelligence", "com.android.settings.intelligence.suggestions.SuggestionService");
    }

    public boolean isSuggestionComplete(Context context, ComponentName componentName) {
        if (Settings.System.getInt(context.getContentResolver(), "settings_show_suggestions", 1) == 0) {
            return true;
        }
        String className = componentName.getClassName();
        if (className.equals(WallpaperSuggestionActivity.class.getName())) {
            return WallpaperSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(StyleSuggestionActivity.class.getName())) {
            return StyleSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(FingerprintSuggestionActivity.class.getName())) {
            return FingerprintSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(FingerprintEnrollSuggestionActivity.class.getName())) {
            return FingerprintEnrollSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(ScreenLockSuggestionActivity.class.getName())) {
            return ScreenLockSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(WifiCallingSuggestionActivity.class.getName())) {
            return WifiCallingSuggestionActivity.isSuggestionComplete(context);
        }
        if (className.equals(Settings.NightDisplaySuggestionActivity.class.getName())) {
            return NightDisplayPreferenceController.isSuggestionComplete(context);
        }
        if (className.equals(ZenSuggestionActivity.class.getName())) {
            return ZenOnboardingActivity.isSuggestionComplete(context);
        }
        return false;
    }

    public SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(SHARED_PREF_FILENAME, 0);
    }

    public SuggestionFeatureProviderImpl(Context context) {
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context.getApplicationContext()).getMetricsFeatureProvider();
    }
}
