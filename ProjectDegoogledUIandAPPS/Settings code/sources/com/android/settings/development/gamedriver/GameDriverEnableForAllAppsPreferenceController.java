package com.android.settings.development.gamedriver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.development.gamedriver.GameDriverContentObserver;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.development.DevelopmentSettingsEnabler;
import com.havoc.config.center.C1715R;

public class GameDriverEnableForAllAppsPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener, GameDriverContentObserver.OnGameDriverContentChangedListener, LifecycleObserver, OnStart, OnStop {
    public static final int GAME_DRIVER_ALL_APPS = 1;
    public static final int GAME_DRIVER_DEFAULT = 0;
    public static final int GAME_DRIVER_OFF = 3;
    public static final int GAME_DRIVER_PRERELEASE_ALL_APPS = 2;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    GameDriverContentObserver mGameDriverContentObserver = new GameDriverContentObserver(new Handler(Looper.getMainLooper()), this);
    private ListPreference mPreference;
    private final String mPreferenceDefault;
    private final String mPreferenceGameDriver;
    private final String mPreferencePrereleaseDriver;

    public GameDriverEnableForAllAppsPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        Resources resources = context.getResources();
        this.mPreferenceDefault = resources.getString(C1715R.string.game_driver_app_preference_default);
        this.mPreferenceGameDriver = resources.getString(C1715R.string.game_driver_app_preference_game_driver);
        this.mPreferencePrereleaseDriver = resources.getString(C1715R.string.game_driver_app_preference_prerelease_driver);
    }

    public int getAvailabilityStatus() {
        if (!DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(this.mContext) || Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) == 3) {
            return 2;
        }
        return 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setOnPreferenceChangeListener(this);
    }

    public void onStart() {
        this.mGameDriverContentObserver.register(this.mContentResolver);
    }

    public void onStop() {
        this.mGameDriverContentObserver.unregister(this.mContentResolver);
    }

    public void updateState(Preference preference) {
        ListPreference listPreference = (ListPreference) preference;
        listPreference.setVisible(isAvailable());
        int i = Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0);
        if (i == 1) {
            listPreference.setValue(this.mPreferenceGameDriver);
            listPreference.setSummary(this.mPreferenceGameDriver);
        } else if (i == 2) {
            listPreference.setValue(this.mPreferencePrereleaseDriver);
            listPreference.setSummary(this.mPreferencePrereleaseDriver);
        } else {
            listPreference.setValue(this.mPreferenceDefault);
            listPreference.setSummary(this.mPreferenceDefault);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        ListPreference listPreference = (ListPreference) preference;
        String obj2 = obj.toString();
        int i = 0;
        int i2 = Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0);
        if (obj2.equals(this.mPreferenceGameDriver)) {
            i = 1;
        } else if (obj2.equals(this.mPreferencePrereleaseDriver)) {
            i = 2;
        }
        listPreference.setValue(obj2);
        listPreference.setSummary(obj2);
        if (i != i2) {
            Settings.Global.putInt(this.mContentResolver, "game_driver_all_apps", i);
        }
        return true;
    }

    public void onGameDriverContentChanged() {
        updateState(this.mPreference);
    }
}
