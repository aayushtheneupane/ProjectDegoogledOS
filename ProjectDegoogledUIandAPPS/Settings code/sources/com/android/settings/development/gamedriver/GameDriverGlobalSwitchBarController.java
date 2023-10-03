package com.android.settings.development.gamedriver;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.settings.development.gamedriver.GameDriverContentObserver;
import com.android.settings.widget.SwitchWidgetController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.development.DevelopmentSettingsEnabler;

public class GameDriverGlobalSwitchBarController implements SwitchWidgetController.OnSwitchChangeListener, GameDriverContentObserver.OnGameDriverContentChangedListener, LifecycleObserver, OnStart, OnStop {
    private final ContentResolver mContentResolver;
    private final Context mContext;
    GameDriverContentObserver mGameDriverContentObserver = new GameDriverContentObserver(new Handler(Looper.getMainLooper()), this);
    SwitchWidgetController mSwitchWidgetController;

    GameDriverGlobalSwitchBarController(Context context, SwitchWidgetController switchWidgetController) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mSwitchWidgetController = switchWidgetController;
        this.mSwitchWidgetController.setEnabled(DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(context));
        this.mSwitchWidgetController.setChecked(Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) != 3);
        this.mSwitchWidgetController.setListener(this);
    }

    public void onStart() {
        this.mSwitchWidgetController.startListening();
        this.mGameDriverContentObserver.register(this.mContentResolver);
    }

    public void onStop() {
        this.mSwitchWidgetController.stopListening();
        this.mGameDriverContentObserver.unregister(this.mContentResolver);
    }

    public boolean onSwitchToggled(boolean z) {
        int i = 0;
        int i2 = Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0);
        if (z && (i2 == 0 || i2 == 1 || i2 == 2)) {
            return true;
        }
        if (!z && i2 == 3) {
            return true;
        }
        ContentResolver contentResolver = this.mContentResolver;
        if (!z) {
            i = 3;
        }
        Settings.Global.putInt(contentResolver, "game_driver_all_apps", i);
        return true;
    }

    public void onGameDriverContentChanged() {
        SwitchWidgetController switchWidgetController = this.mSwitchWidgetController;
        boolean z = false;
        if (Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) != 3) {
            z = true;
        }
        switchWidgetController.setChecked(z);
    }
}
