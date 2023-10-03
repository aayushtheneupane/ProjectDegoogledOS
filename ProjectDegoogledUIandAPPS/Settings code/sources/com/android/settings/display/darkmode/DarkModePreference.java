package com.android.settings.display.darkmode;

import android.app.UiModeManager;
import android.content.Context;
import android.os.PowerManager;
import android.util.AttributeSet;
import com.android.settings.widget.MasterSwitchPreference;
import com.havoc.config.center.C1715R;

public class DarkModePreference extends MasterSwitchPreference {
    private Runnable mCallback = new Runnable() {
        public final void run() {
            DarkModePreference.this.lambda$new$0$DarkModePreference();
        }
    };
    private DarkModeObserver mDarkModeObserver;
    private PowerManager mPowerManager;
    private UiModeManager mUiModeManager;

    public DarkModePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDarkModeObserver = new DarkModeObserver(context);
        this.mUiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mDarkModeObserver.subscribe(this.mCallback);
    }

    public /* synthetic */ void lambda$new$0$DarkModePreference() {
        boolean isPowerSaveMode = this.mPowerManager.isPowerSaveMode();
        boolean z = (getContext().getResources().getConfiguration().uiMode & 32) != 0;
        setSwitchEnabled(!isPowerSaveMode);
        updateSummary(isPowerSaveMode, z);
    }

    public void onAttached() {
        super.onAttached();
        this.mDarkModeObserver.subscribe(this.mCallback);
    }

    public void onDetached() {
        super.onDetached();
        this.mDarkModeObserver.unsubscribe();
    }

    private void updateSummary(boolean z, boolean z2) {
        String str;
        if (z) {
            setSummary((CharSequence) getContext().getString(z2 ? C1715R.string.dark_ui_mode_disabled_summary_dark_theme_on : C1715R.string.dark_ui_mode_disabled_summary_dark_theme_off));
            return;
        }
        boolean z3 = this.mUiModeManager.getNightMode() == 0;
        if (z2) {
            str = getContext().getString(z3 ? C1715R.string.dark_ui_summary_on_auto_mode_auto : C1715R.string.dark_ui_summary_on_auto_mode_never);
        } else {
            str = getContext().getString(z3 ? C1715R.string.dark_ui_summary_off_auto_mode_auto : C1715R.string.dark_ui_summary_off_auto_mode_never);
        }
        setSummary((CharSequence) getContext().getString(z2 ? C1715R.string.dark_ui_summary_on : C1715R.string.dark_ui_summary_off, new Object[]{str}));
    }
}
