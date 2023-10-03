package com.android.settings.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.dashboard.profileselector.ProfileSelectDialog;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import com.android.settingslib.drawer.TileUtils;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.AdaptiveIcon;
import com.havoc.config.center.C1715R;
import java.util.List;

public class DashboardFeatureProviderImpl implements DashboardFeatureProvider {
    private final CategoryManager mCategoryManager;
    protected final Context mContext;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final PackageManager mPackageManager;

    public DashboardFeatureProviderImpl(Context context) {
        this.mContext = context.getApplicationContext();
        this.mCategoryManager = CategoryManager.get(context);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        this.mPackageManager = context.getPackageManager();
    }

    public DashboardCategory getTilesForCategory(String str) {
        return this.mCategoryManager.getTilesByCategory(this.mContext, str);
    }

    public List<DashboardCategory> getAllCategories() {
        return this.mCategoryManager.getCategories(this.mContext);
    }

    public String getDashboardKeyForTile(Tile tile) {
        if (tile == null) {
            return null;
        }
        if (tile.hasKey()) {
            return tile.getKey(this.mContext);
        }
        return "dashboard_tile_pref_" + tile.getIntent().getComponent().getClassName();
    }

    public void bindPreferenceToTile(FragmentActivity fragmentActivity, boolean z, int i, Preference preference, Tile tile, String str, int i2) {
        String str2;
        if (preference != null) {
            preference.setTitle(tile.getTitle(fragmentActivity.getApplicationContext()));
            if (!TextUtils.isEmpty(str)) {
                preference.setKey(str);
            } else {
                preference.setKey(getDashboardKeyForTile(tile));
            }
            bindSummary(preference, tile);
            bindIcon(preference, tile, z);
            Bundle metaData = tile.getMetaData();
            String str3 = null;
            if (metaData != null) {
                str3 = metaData.getString("com.android.settings.FRAGMENT_CLASS");
                str2 = metaData.getString("com.android.settings.intent.action");
            } else {
                str2 = null;
            }
            if (!TextUtils.isEmpty(str3)) {
                preference.setFragment(str3);
            } else {
                Intent intent = new Intent(tile.getIntent());
                intent.putExtra(":settings:source_metrics", i);
                if (str2 != null) {
                    intent.setAction(str2);
                }
                preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(fragmentActivity, tile, intent, i) {
                    private final /* synthetic */ FragmentActivity f$1;
                    private final /* synthetic */ Tile f$2;
                    private final /* synthetic */ Intent f$3;
                    private final /* synthetic */ int f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final boolean onPreferenceClick(Preference preference) {
                        return DashboardFeatureProviderImpl.this.lambda$bindPreferenceToTile$0$DashboardFeatureProviderImpl(this.f$1, this.f$2, this.f$3, this.f$4, preference);
                    }
                });
            }
            String packageName = fragmentActivity.getPackageName();
            if (tile.hasOrder()) {
                int order = tile.getOrder();
                if (TextUtils.equals(packageName, tile.getIntent().getComponent().getPackageName()) || i2 == Integer.MAX_VALUE) {
                    preference.setOrder(order);
                } else {
                    preference.setOrder(order + i2);
                }
            }
        }
    }

    public /* synthetic */ boolean lambda$bindPreferenceToTile$0$DashboardFeatureProviderImpl(FragmentActivity fragmentActivity, Tile tile, Intent intent, int i, Preference preference) {
        launchIntentOrSelectProfile(fragmentActivity, tile, intent, i);
        return true;
    }

    private void bindSummary(Preference preference, Tile tile) {
        CharSequence summary = tile.getSummary(this.mContext);
        if (summary != null) {
            preference.setSummary(summary);
        } else if (tile.getMetaData() == null || !tile.getMetaData().containsKey("com.android.settings.summary_uri")) {
            preference.setSummary((int) C1715R.string.summary_placeholder);
        } else {
            preference.setSummary((int) C1715R.string.summary_placeholder);
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(tile, preference) {
                private final /* synthetic */ Tile f$1;
                private final /* synthetic */ Preference f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    DashboardFeatureProviderImpl.this.lambda$bindSummary$2$DashboardFeatureProviderImpl(this.f$1, this.f$2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$bindSummary$2$DashboardFeatureProviderImpl(Tile tile, Preference preference) {
        ArrayMap arrayMap = new ArrayMap();
        ThreadUtils.postOnMainThread(new Runnable(TileUtils.getTextFromUri(this.mContext, tile.getMetaData().getString("com.android.settings.summary_uri"), arrayMap, "com.android.settings.summary")) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                Preference.this.setSummary((CharSequence) this.f$1);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void bindIcon(Preference preference, Tile tile, boolean z) {
        AdaptiveIcon adaptiveIcon;
        Icon icon = tile.getIcon(preference.getContext());
        if (icon != null) {
            Drawable loadDrawable = icon.loadDrawable(preference.getContext());
            if (!z || TextUtils.equals(this.mContext.getPackageName(), tile.getPackageName())) {
                adaptiveIcon = loadDrawable;
            } else {
                AdaptiveIcon adaptiveIcon2 = new AdaptiveIcon(this.mContext, loadDrawable);
                adaptiveIcon2.setBackgroundColor(this.mContext, tile);
                adaptiveIcon = adaptiveIcon2;
            }
            preference.setIcon(adaptiveIcon);
        } else if (tile.getMetaData() != null && tile.getMetaData().containsKey("com.android.settings.icon_uri")) {
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(tile, preference) {
                private final /* synthetic */ Tile f$1;
                private final /* synthetic */ Preference f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    DashboardFeatureProviderImpl.this.lambda$bindIcon$4$DashboardFeatureProviderImpl(this.f$1, this.f$2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$bindIcon$4$DashboardFeatureProviderImpl(Tile tile, Preference preference) {
        String str;
        Intent intent = tile.getIntent();
        if (!TextUtils.isEmpty(intent.getPackage())) {
            str = intent.getPackage();
        } else {
            str = intent.getComponent() != null ? intent.getComponent().getPackageName() : null;
        }
        ArrayMap arrayMap = new ArrayMap();
        String string = tile.getMetaData().getString("com.android.settings.icon_uri");
        Pair<String, Integer> iconFromUri = TileUtils.getIconFromUri(this.mContext, str, string, arrayMap);
        if (iconFromUri == null) {
            Log.w("DashboardFeatureImpl", "Failed to get icon from uri " + string);
            return;
        }
        ThreadUtils.postOnMainThread(new Runnable(Icon.createWithResource((String) iconFromUri.first, ((Integer) iconFromUri.second).intValue())) {
            private final /* synthetic */ Icon f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                Preference.this.setIcon(this.f$1.loadDrawable(Preference.this.getContext()));
            }
        });
    }

    private void launchIntentOrSelectProfile(FragmentActivity fragmentActivity, Tile tile, Intent intent, int i) {
        if (!isIntentResolvable(intent)) {
            Log.w("DashboardFeatureImpl", "Cannot resolve intent, skipping. " + intent);
            return;
        }
        ProfileSelectDialog.updateUserHandlesIfNeeded(this.mContext, tile);
        if (tile.userHandle == null || tile.isPrimaryProfileOnly()) {
            this.mMetricsFeatureProvider.logDashboardStartIntent(this.mContext, intent, i);
            fragmentActivity.startActivityForResult(intent, 0);
        } else if (tile.userHandle.size() == 1) {
            this.mMetricsFeatureProvider.logDashboardStartIntent(this.mContext, intent, i);
            fragmentActivity.startActivityForResultAsUser(intent, 0, tile.userHandle.get(0));
        } else {
            UserHandle userHandle = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER");
            if (userHandle == null || !tile.userHandle.contains(userHandle)) {
                ProfileSelectDialog.show(fragmentActivity.getSupportFragmentManager(), tile);
                return;
            }
            this.mMetricsFeatureProvider.logDashboardStartIntent(this.mContext, intent, i);
            fragmentActivity.startActivityForResultAsUser(intent, 0, userHandle);
        }
    }

    private boolean isIntentResolvable(Intent intent) {
        return this.mPackageManager.resolveActivity(intent, 0) != null;
    }
}
