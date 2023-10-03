package com.android.settings.search.actionbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.android.settings.Utils;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu;
import com.havoc.config.center.C1715R;

public class SearchMenuController implements LifecycleObserver, OnCreateOptionsMenu {
    private final Fragment mHost;
    private final int mPageId;

    public static void init(InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        instrumentedPreferenceFragment.getSettingsLifecycle().addObserver(new SearchMenuController(instrumentedPreferenceFragment, instrumentedPreferenceFragment.getMetricsCategory()));
    }

    public static void init(InstrumentedFragment instrumentedFragment) {
        instrumentedFragment.getSettingsLifecycle().addObserver(new SearchMenuController(instrumentedFragment, instrumentedFragment.getMetricsCategory()));
    }

    private SearchMenuController(Fragment fragment, int i) {
        this.mHost = fragment;
        this.mPageId = i;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Context context = this.mHost.getContext();
        String string = context.getString(C1715R.string.config_settingsintelligence_package_name);
        if (Utils.isDeviceProvisioned(this.mHost.getContext()) && Utils.isPackageEnabled(this.mHost.getContext(), string) && menu != null) {
            Bundle arguments = this.mHost.getArguments();
            if (arguments == null || arguments.getBoolean("need_search_icon_in_action_bar", true)) {
                MenuItem add = menu.add(0, 0, 0, C1715R.string.search_menu);
                add.setIcon(C1715R.C1717drawable.ic_search_24dp);
                add.setShowAsAction(2);
                add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(context) {
                    private final /* synthetic */ Context f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        return SearchMenuController.this.lambda$onCreateOptionsMenu$0$SearchMenuController(this.f$1, menuItem);
                    }
                });
            }
        }
    }

    public /* synthetic */ boolean lambda$onCreateOptionsMenu$0$SearchMenuController(Context context, MenuItem menuItem) {
        Intent buildSearchIntent = FeatureFactory.getFactory(context).getSearchFeatureProvider().buildSearchIntent(context, this.mPageId);
        if (context.getPackageManager().queryIntentActivities(buildSearchIntent, 65536).isEmpty()) {
            return true;
        }
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 226, (Pair<Integer, Object>[]) new Pair[0]);
        this.mHost.startActivityForResult(buildSearchIntent, 501);
        return true;
    }
}
