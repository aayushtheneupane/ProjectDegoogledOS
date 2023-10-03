package com.android.settings.search;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import com.android.settings.Utils;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.search.SearchIndexableResources;
import com.havoc.config.center.C1715R;

public interface SearchFeatureProvider {
    Intent buildSearchIntent(Context context, int i);

    SearchIndexableResources getSearchIndexableResources();

    void verifyLaunchSearchResultPageCaller(Context context, ComponentName componentName) throws SecurityException, IllegalArgumentException;

    String getSettingsIntelligencePkgName(Context context) {
        return context.getString(C1715R.string.config_settingsintelligence_package_name);
    }

    void initSearchToolbar(Activity activity, Toolbar toolbar, int i) {
        if (activity != null && toolbar != null) {
            if (!Utils.isDeviceProvisioned(activity) || !Utils.isPackageEnabled(activity, getSettingsIntelligencePkgName(activity))) {
                ViewGroup viewGroup = (ViewGroup) toolbar.getParent();
                if (viewGroup != null) {
                    viewGroup.setVisibility(8);
                    return;
                }
                return;
            }
            View navigationView = toolbar.getNavigationView();
            navigationView.setClickable(false);
            navigationView.setImportantForAccessibility(2);
            navigationView.setBackground((Drawable) null);
            toolbar.setOnClickListener(new View.OnClickListener(activity, i) {
                private final /* synthetic */ Activity f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    SearchFeatureProvider.lambda$initSearchToolbar$0(SearchFeatureProvider.this, this.f$1, this.f$2, view);
                }
            });
        }
    }

    static /* synthetic */ void lambda$initSearchToolbar$0(SearchFeatureProvider _this, Activity activity, int i, View view) {
        Context applicationContext = activity.getApplicationContext();
        Intent buildSearchIntent = _this.buildSearchIntent(applicationContext, i);
        if (!activity.getPackageManager().queryIntentActivities(buildSearchIntent, 65536).isEmpty()) {
            FeatureFactory.getFactory(applicationContext).getSlicesFeatureProvider().indexSliceDataAsync(applicationContext);
            FeatureFactory.getFactory(applicationContext).getMetricsFeatureProvider().action(applicationContext, 226, (Pair<Integer, Object>[]) new Pair[0]);
            activity.startActivityForResult(buildSearchIntent, 501);
        }
    }
}
