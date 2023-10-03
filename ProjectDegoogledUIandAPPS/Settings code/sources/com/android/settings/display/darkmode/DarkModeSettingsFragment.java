package com.android.settings.display.darkmode;

import android.os.Bundle;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.havoc.config.center.C1715R;

public class DarkModeSettingsFragment extends DashboardFragment {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider();
    private Runnable mCallback = new Runnable() {
        public final void run() {
            DarkModeSettingsFragment.this.lambda$new$0$DarkModeSettingsFragment();
        }
    };
    private DarkModeObserver mContentObserver;

    public int getHelpResource() {
        return C1715R.string.help_url_dark_theme;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "DarkModeSettingsFragment";
    }

    public int getMetricsCategory() {
        return 1698;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.dark_mode_settings;
    }

    public /* synthetic */ void lambda$new$0$DarkModeSettingsFragment() {
        updatePreferenceStates();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContentObserver = new DarkModeObserver(getContext());
    }

    public void onStart() {
        super.onStart();
        this.mContentObserver.subscribe(this.mCallback);
    }

    public void onStop() {
        super.onStop();
        this.mContentObserver.unsubscribe();
    }
}
