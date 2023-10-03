package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.AppStoreUtil;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu;
import com.android.settingslib.core.lifecycle.events.OnOptionsItemSelected;
import com.android.settingslib.core.lifecycle.events.OnPrepareOptionsMenu;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class InstantAppButtonsPreferenceController extends BasePreferenceController implements LifecycleObserver, OnCreateOptionsMenu, OnPrepareOptionsMenu, OnOptionsItemSelected {
    private static final String KEY_INSTANT_APP_BUTTONS = "instant_app_buttons";
    private static final String META_DATA_DEFAULT_URI = "default-url";
    private MenuItem mInstallMenu;
    private String mLaunchUri = getDefaultLaunchUri();
    private final String mPackageName;
    private final AppInfoDashboardFragment mParent;
    private LayoutPreference mPreference;

    public InstantAppButtonsPreferenceController(Context context, AppInfoDashboardFragment appInfoDashboardFragment, String str, Lifecycle lifecycle) {
        super(context, KEY_INSTANT_APP_BUTTONS);
        this.mParent = appInfoDashboardFragment;
        this.mPackageName = str;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public int getAvailabilityStatus() {
        return AppUtils.isInstant(this.mParent.getPackageInfo().applicationInfo) ? 0 : 4;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (LayoutPreference) preferenceScreen.findPreference(KEY_INSTANT_APP_BUTTONS);
        initButtons(this.mPreference.findViewById(C1715R.C1718id.instant_app_button_container));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!TextUtils.isEmpty(this.mLaunchUri)) {
            menu.add(0, 3, 2, C1715R.string.install_text).setShowAsAction(0);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 3) {
            return false;
        }
        Intent appStoreLink = AppStoreUtil.getAppStoreLink(this.mContext, this.mPackageName);
        if (appStoreLink == null) {
            return true;
        }
        this.mParent.startActivity(appStoreLink);
        return true;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        this.mInstallMenu = menu.findItem(3);
        if (this.mInstallMenu != null && AppStoreUtil.getAppStoreLink(this.mContext, this.mPackageName) == null) {
            this.mInstallMenu.setEnabled(false);
        }
    }

    private void initButtons(View view) {
        Button button = (Button) view.findViewById(C1715R.C1718id.install);
        Button button2 = (Button) view.findViewById(C1715R.C1718id.clear_data);
        Button button3 = (Button) view.findViewById(C1715R.C1718id.launch);
        if (!TextUtils.isEmpty(this.mLaunchUri)) {
            button.setVisibility(8);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setPackage(this.mPackageName);
            intent.setData(Uri.parse(this.mLaunchUri));
            intent.addFlags(268435456);
            button3.setOnClickListener(new View.OnClickListener(intent) {
                private final /* synthetic */ Intent f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    InstantAppButtonsPreferenceController.this.lambda$initButtons$0$InstantAppButtonsPreferenceController(this.f$1, view);
                }
            });
        } else {
            button3.setVisibility(8);
            Intent appStoreLink = AppStoreUtil.getAppStoreLink(this.mContext, this.mPackageName);
            if (appStoreLink != null) {
                button.setOnClickListener(new View.OnClickListener(appStoreLink) {
                    private final /* synthetic */ Intent f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        InstantAppButtonsPreferenceController.this.lambda$initButtons$1$InstantAppButtonsPreferenceController(this.f$1, view);
                    }
                });
            } else {
                button.setEnabled(false);
            }
        }
        button2.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InstantAppButtonsPreferenceController.this.lambda$initButtons$2$InstantAppButtonsPreferenceController(view);
            }
        });
    }

    public /* synthetic */ void lambda$initButtons$0$InstantAppButtonsPreferenceController(Intent intent, View view) {
        this.mParent.startActivity(intent);
    }

    public /* synthetic */ void lambda$initButtons$1$InstantAppButtonsPreferenceController(Intent intent, View view) {
        this.mParent.startActivity(intent);
    }

    public /* synthetic */ void lambda$initButtons$2$InstantAppButtonsPreferenceController(View view) {
        showDialog();
    }

    private void showDialog() {
        InstantAppButtonDialogFragment newInstance = InstantAppButtonDialogFragment.newInstance(this.mPackageName);
        newInstance.setTargetFragment(this.mParent, 0);
        newInstance.show(this.mParent.getFragmentManager(), KEY_INSTANT_APP_BUTTONS);
    }

    private String getDefaultLaunchUri() {
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(this.mPackageName);
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 8388736)) {
            Bundle bundle = resolveInfo.activityInfo.metaData;
            if (bundle != null) {
                String string = bundle.getString(META_DATA_DEFAULT_URI);
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
        }
        return null;
    }
}
