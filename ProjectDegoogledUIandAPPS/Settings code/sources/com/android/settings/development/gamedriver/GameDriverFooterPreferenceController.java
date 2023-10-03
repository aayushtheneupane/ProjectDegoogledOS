package com.android.settings.development.gamedriver;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.development.gamedriver.GameDriverContentObserver;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.widget.FooterPreference;

public class GameDriverFooterPreferenceController extends BasePreferenceController implements GameDriverContentObserver.OnGameDriverContentChangedListener, LifecycleObserver, OnStart, OnStop {
    private final ContentResolver mContentResolver;
    GameDriverContentObserver mGameDriverContentObserver = new GameDriverContentObserver(new Handler(Looper.getMainLooper()), this);
    private FooterPreference mPreference;

    public GameDriverFooterPreferenceController(Context context) {
        super(context, "footer_preference");
        this.mContentResolver = context.getContentResolver();
    }

    public int getAvailabilityStatus() {
        return Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) == 3 ? 1 : 2;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (FooterPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        this.mGameDriverContentObserver.register(this.mContentResolver);
    }

    public void onStop() {
        this.mGameDriverContentObserver.unregister(this.mContentResolver);
    }

    public void updateState(Preference preference) {
        preference.setVisible(isAvailable());
    }

    public void onGameDriverContentChanged() {
        updateState(this.mPreference);
    }
}
