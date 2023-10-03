package com.android.settings.security.applock;

import android.app.AppLockManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.SubSettings;
import com.android.settings.password.ChooseLockGeneric;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.android.settings.security.applock.AppLockSettings;
import com.android.settings.widget.AppLockPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class AppLockSettings extends SubSettings {
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", AppLockSettingsFragment.class.getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return AppLockSettingsFragment.class.getName().equals(str);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getText(C1715R.string.applock_title));
    }

    public static class AppLockSettingsFragment extends SettingsPreferenceFragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
        private final char DUPLICATE_CHAR = 6;
        private final String KEY_LOCKED_APPS = "locked_apps";
        private final String KEY_NOTIFICATION_HELP = "applock_notification_info";
        private final String KEY_SHOW_ON_WAKE = "show_only_on_wake";
        private final String KEY_UNLOCKED_APPS = "unlocked_apps";
        /* access modifiers changed from: private */
        public final ArrayList<AppLockInfo> mAllApps = new ArrayList<>();
        private AppLockManager mAppLockManager;
        private boolean mLaunchAuthenticated;
        private Preference mLocked;
        private final TreeMap<String, AppLockInfo> mLockedApps = new TreeMap<>();
        private Preference mNotifInfo;
        private PackageManager mPackageManager;
        private PreferenceScreen mPreferenceScreen;
        private SearchFilter mSearchFilter;
        private SearchView mSearchView;
        private boolean mSearching;
        private SwitchPreference mShowOnlyOnWake;
        private Preference mUnlocked;
        private final TreeMap<String, AppLockInfo> mUnlockedApps = new TreeMap<>();
        private int mUserId;

        public int getMetricsCategory() {
            return -1;
        }

        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        public void onResume() {
            super.onResume();
        }

        public void onPause() {
            super.onPause();
            if (this.mLaunchAuthenticated) {
                getActivity().finish();
            }
        }

        public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
            if (getActivity() != null) {
                menuInflater.inflate(C1715R.C1720menu.applock_menu, menu);
                MenuItem findItem = menu.findItem(C1715R.C1718id.search_app_list_menu);
                if (findItem != null) {
                    this.mSearchView = (SearchView) findItem.getActionView();
                    this.mSearchView.setQueryHint(getText(C1715R.string.search_settings));
                    this.mSearchView.setOnQueryTextListener(this);
                    this.mSearchView.setOnCloseListener(this);
                }
            }
        }

        public void onCreate(Bundle bundle) {
            Log.d("AppLockSettings", "onCreate");
            super.onCreate(bundle);
            setHasOptionsMenu(true);
            setAnimationAllowed(true);
            this.mUserId = getActivity().getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
            launchChooseOrConfirmLock();
            addPreferencesFromResource(C1715R.xml.security_settings_applock);
            this.mSearching = false;
            this.mLaunchAuthenticated = false;
            this.mAppLockManager = (AppLockManager) getContext().getSystemService("applock");
            this.mPackageManager = getPrefContext().getPackageManager();
            this.mPreferenceScreen = getPreferenceScreen();
            this.mShowOnlyOnWake = (SwitchPreference) this.mPreferenceScreen.findPreference("show_only_on_wake");
            this.mLocked = this.mPreferenceScreen.findPreference("locked_apps");
            this.mUnlocked = this.mPreferenceScreen.findPreference("unlocked_apps");
            this.mNotifInfo = this.mPreferenceScreen.findPreference("applock_notification_info");
            this.mShowOnlyOnWake.setChecked(this.mAppLockManager.getShowOnlyOnWake());
            this.mShowOnlyOnWake.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return AppLockSettings.AppLockSettingsFragment.this.lambda$onCreate$0$AppLockSettings$AppLockSettingsFragment(preference, obj);
                }
            });
            this.mNotifInfo.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public final boolean onPreferenceClick(Preference preference) {
                    return AppLockSettings.AppLockSettingsFragment.this.lambda$onCreate$1$AppLockSettings$AppLockSettingsFragment(preference);
                }
            });
            AppLockViewModel appLockViewModel = (AppLockViewModel) ViewModelProviders.m3of(this).get(AppLockViewModel.class);
            if (!appLockViewModel.getAppList().hasActiveObservers()) {
                appLockViewModel.getAppList().observeForever(new Observer() {
                    public final void onChanged(Object obj) {
                        AppLockSettings.AppLockSettingsFragment.this.lambda$onCreate$2$AppLockSettings$AppLockSettingsFragment((List) obj);
                    }
                });
            }
        }

        public /* synthetic */ boolean lambda$onCreate$0$AppLockSettings$AppLockSettingsFragment(Preference preference, Object obj) {
            this.mAppLockManager.setShowOnlyOnWake(((Boolean) obj).booleanValue());
            return true;
        }

        public /* synthetic */ boolean lambda$onCreate$1$AppLockSettings$AppLockSettingsFragment(Preference preference) {
            performNotifHintAnimation();
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: updateAppsList */
        public void lambda$onCreate$2$AppLockSettings$AppLockSettingsFragment(List<AppLockInfo> list) {
            for (AppLockInfo next : list) {
                if (next.isAppLocked()) {
                    addToLocked(next.getLabel(), next);
                } else {
                    addToUnlocked(next.getLabel(), next);
                }
            }
            this.mAllApps.addAll(this.mLockedApps.values());
            this.mAllApps.addAll(this.mUnlockedApps.values());
            addPreferences();
        }

        private void updateAppsLocked(Preference preference, boolean z) {
            String charSequence = preference.getTitle().toString();
            if (z) {
                this.mAppLockManager.addAppToList(preference.getKey());
                AppLockInfo appLockInfo = this.mUnlockedApps.get(charSequence);
                appLockInfo.setAppLocked(true);
                addToLocked(charSequence, appLockInfo);
                removeFromUnlocked(charSequence);
            } else {
                this.mAppLockManager.removeAppFromList(preference.getKey());
                AppLockInfo appLockInfo2 = this.mLockedApps.get(charSequence);
                appLockInfo2.setAppLocked(false);
                addToUnlocked(charSequence, appLockInfo2);
                removeFromLocked(charSequence);
            }
            updatePreferenceOrder();
            if (this.mSearching) {
                int preferenceCount = this.mPreferenceScreen.getPreferenceCount();
                int i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < preferenceCount; i3++) {
                    Preference preference2 = this.mPreferenceScreen.getPreference(i3);
                    if ((preference2 instanceof AppLockPreference) && preference2.isVisible()) {
                        i2++;
                        if ((preference2 == preference && z) || (preference2 != preference && ((AppLockPreference) preference2).isChecked())) {
                            i++;
                        }
                    }
                }
                updateCategoryVisibility(i, i2 - i);
                return;
            }
            updateCategoryVisibility(this.mLockedApps.size(), this.mUnlockedApps.size());
        }

        private void addToLocked(String str, AppLockInfo appLockInfo) {
            handleDuplicate(this.mLockedApps.put(str, appLockInfo), this.mLockedApps);
        }

        private void addToUnlocked(String str, AppLockInfo appLockInfo) {
            handleDuplicate(this.mUnlockedApps.put(str, appLockInfo), this.mUnlockedApps);
        }

        private void handleDuplicate(AppLockInfo appLockInfo, TreeMap<String, AppLockInfo> treeMap) {
            int i = 1;
            while (appLockInfo != null) {
                String label = appLockInfo.getLabel();
                for (int i2 = 0; i2 < i; i2++) {
                    label = label + 6;
                }
                appLockInfo.setLabel(label);
                appLockInfo = treeMap.put(label, appLockInfo);
                i++;
            }
        }

        private void removeFromLocked(String str) {
            this.mLockedApps.remove(str);
        }

        private void removeFromUnlocked(String str) {
            this.mUnlockedApps.remove(str);
        }

        private void updatePreferenceOrder() {
            int i = 0;
            for (AppLockInfo packageName : this.mLockedApps.values()) {
                ((AppLockPreference) this.mPreferenceScreen.findPreference(packageName.getPackageName())).setOrder(i);
                i++;
            }
            this.mUnlocked.setOrder(i);
            int i2 = i + 1;
            for (AppLockInfo packageName2 : this.mUnlockedApps.values()) {
                ((AppLockPreference) this.mPreferenceScreen.findPreference(packageName2.getPackageName())).setOrder(i2);
                i2++;
            }
        }

        private void addPreferences() {
            int i = 0;
            for (AppLockInfo createPreference : this.mLockedApps.values()) {
                AppLockPreference createPreference2 = createPreference(createPreference);
                this.mPreferenceScreen.addPreference(createPreference2);
                createPreference2.setOrder(i);
                i++;
            }
            this.mUnlocked.setOrder(i);
            int i2 = i + 1;
            for (AppLockInfo createPreference3 : this.mUnlockedApps.values()) {
                AppLockPreference createPreference4 = createPreference(createPreference3);
                this.mPreferenceScreen.addPreference(createPreference4);
                createPreference4.setOrder(i2);
                i2++;
            }
            updateCategoryVisibility(this.mLockedApps.size(), this.mUnlockedApps.size());
        }

        private void performNotifHintAnimation() {
            for (AppLockInfo packageName : this.mLockedApps.values()) {
                ((AppLockPreference) this.mPreferenceScreen.findPreference(packageName.getPackageName())).startHintAnimation();
            }
        }

        /* access modifiers changed from: private */
        public void updatePreferencesPostSearch(ArrayList<AppLockInfo> arrayList) {
            Iterator<AppLockInfo> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                AppLockInfo next = it.next();
                AppLockPreference appLockPreference = (AppLockPreference) this.mPreferenceScreen.findPreference(next.getPackageName());
                if (appLockPreference != null) {
                    appLockPreference.setVisible(true);
                }
                if (next.isAppLocked()) {
                    i++;
                }
            }
            ArrayList arrayList2 = new ArrayList(this.mAllApps);
            arrayList2.removeAll(arrayList);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                AppLockPreference appLockPreference2 = (AppLockPreference) this.mPreferenceScreen.findPreference(((AppLockInfo) it2.next()).getPackageName());
                if (appLockPreference2 != null) {
                    appLockPreference2.setVisible(false);
                }
            }
            updateCategoryVisibility(i, arrayList.size() - i);
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            super.onActivityResult(i, i2, intent);
            if (i != 102 && i != 101) {
                return;
            }
            if (i2 == 1 || i2 == -1) {
                this.mLaunchAuthenticated = true;
            } else {
                getActivity().finish();
            }
        }

        public boolean onQueryTextChange(String str) {
            if (this.mSearchFilter == null) {
                this.mSearchFilter = new SearchFilter();
            }
            this.mSearching = true;
            this.mSearchFilter.filter(str);
            return false;
        }

        public boolean onClose() {
            this.mSearching = false;
            updatePreferencesPostSearch(this.mAllApps);
            return false;
        }

        private void launchChooseOrConfirmLock() {
            Intent intent = new Intent();
            if (!new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(101, (CharSequence) getString(C1715R.string.applock_title), (CharSequence) null, (CharSequence) null, this.mUserId, true)) {
                intent.setClassName("com.android.settings", ChooseLockGeneric.class.getName());
                intent.putExtra("minimum_quality", 65536);
                intent.putExtra("hide_disabled_prefs", true);
                intent.putExtra("android.intent.extra.USER_ID", this.mUserId);
                startActivityForResult(intent, 102);
            }
        }

        private AppLockPreference createPreference(AppLockInfo appLockInfo) {
            String str = appLockInfo.getLabel().toString();
            Drawable icon = appLockInfo.getIcon();
            String packageName = appLockInfo.getPackageName();
            boolean isAppLocked = appLockInfo.isAppLocked();
            AppLockPreference appLockPreference = new AppLockPreference(getPrefContext(), this.mAppLockManager, packageName);
            appLockPreference.setTitle((CharSequence) str);
            appLockPreference.setIcon(icon);
            appLockPreference.setKey(packageName);
            appLockPreference.setChecked(isAppLocked);
            appLockPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return AppLockSettings.AppLockSettingsFragment.this.mo11608x70895209(preference, obj);
                }
            });
            return appLockPreference;
        }

        /* renamed from: lambda$createPreference$3$AppLockSettings$AppLockSettingsFragment */
        public /* synthetic */ boolean mo11608x70895209(Preference preference, Object obj) {
            updateAppsLocked(preference, ((Boolean) obj).booleanValue());
            return true;
        }

        private void updateCategoryVisibility(int i, int i2) {
            if (i == 0) {
                this.mLocked.setVisible(false);
                this.mNotifInfo.setVisible(false);
            } else {
                this.mLocked.setVisible(true);
                this.mNotifInfo.setVisible(true);
            }
            if (i2 == 0) {
                this.mUnlocked.setVisible(false);
            } else {
                this.mUnlocked.setVisible(true);
            }
        }

        private class SearchFilter extends Filter {
            private SearchFilter() {
            }

            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                ArrayList arrayList = new ArrayList();
                if (!TextUtils.isEmpty(charSequence)) {
                    Iterator it = AppLockSettingsFragment.this.mAllApps.iterator();
                    while (it.hasNext()) {
                        AppLockInfo appLockInfo = (AppLockInfo) it.next();
                        if (appLockInfo.getLabel().toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            arrayList.add(appLockInfo);
                        }
                    }
                } else {
                    arrayList.addAll(AppLockSettingsFragment.this.mAllApps);
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
                return filterResults;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                AppLockSettingsFragment.this.updatePreferencesPostSearch((ArrayList) filterResults.values);
            }
        }
    }
}
