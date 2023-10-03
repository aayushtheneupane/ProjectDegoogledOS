package com.android.settings.applications.manageapplications;

import android.app.Activity;
import android.app.AppLockManager;
import android.app.usage.IUsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.preference.PreferenceFrameLayout;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.Settings;
import com.android.settings.Utils;
import com.android.settings.applications.AppInfoBase;
import com.android.settings.applications.AppStateAppOpsBridge;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settings.applications.AppStateInstallAppsBridge;
import com.android.settings.applications.AppStateNotificationBridge;
import com.android.settings.applications.AppStateOverlayBridge;
import com.android.settings.applications.AppStatePowerBridge;
import com.android.settings.applications.AppStateUsageBridge;
import com.android.settings.applications.AppStateWriteSettingsBridge;
import com.android.settings.applications.AppStorageSettings;
import com.android.settings.applications.InstalledAppCounter;
import com.android.settings.applications.UsageAccessDetails;
import com.android.settings.applications.appinfo.AppInfoDashboardFragment;
import com.android.settings.applications.appinfo.DrawOverlayDetails;
import com.android.settings.applications.appinfo.ExternalSourcesDetails;
import com.android.settings.applications.appinfo.WriteSettingsDetails;
import com.android.settings.applications.manageapplications.ManageApplications;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.fuelgauge.HighPowerDetail;
import com.android.settings.notification.AppNotificationSettings;
import com.android.settings.notification.ConfigureNotificationSettings;
import com.android.settings.notification.NotificationBackend;
import com.android.settings.notification.NotificationStation;
import com.android.settings.widget.LoadingViewController;
import com.android.settings.wifi.AppStateChangeWifiStateBridge;
import com.android.settings.wifi.ChangeWifiStateDetails;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.fuelgauge.PowerWhitelistBackend;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.settingsspinner.SettingsSpinnerAdapter;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class ManageApplications extends InstrumentedFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener {
    public static final Set<Integer> LIST_TYPES_WITH_INSTANT = new ArraySet(Arrays.asList(new Integer[]{0, 3}));
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() {
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };
    private AppLockManager mAppLockManager;
    private ApplicationsAdapter mApplications;
    private ApplicationsState mApplicationsState;
    /* access modifiers changed from: private */
    public String mCurrentPkgName;
    private int mCurrentUid;
    /* access modifiers changed from: private */
    public View mEmptyView;
    boolean mExpandSearch;
    /* access modifiers changed from: private */
    public AppFilterItem mFilter;
    FilterSpinnerAdapter mFilterAdapter;
    /* access modifiers changed from: private */
    public Spinner mFilterSpinner;
    /* access modifiers changed from: private */
    public int mFilterType;
    CharSequence mInvalidSizeStr;
    private boolean mIsWorkOnly;
    /* access modifiers changed from: private */
    public View mListContainer;
    public int mListType;
    /* access modifiers changed from: private */
    public View mLoadingContainer;
    /* access modifiers changed from: private */
    public NotificationBackend mNotificationBackend;
    private Menu mOptionsMenu;
    RecyclerView mRecyclerView;
    private ResetAppsHelper mResetAppsHelper;
    private View mRootView;
    /* access modifiers changed from: private */
    public SearchView mSearchView;
    /* access modifiers changed from: private */
    public boolean mShowSystem;
    int mSortOrder = C1715R.C1718id.sort_order_alpha;
    View mSpinnerHeader;
    private int mStorageType;
    /* access modifiers changed from: private */
    public IUsageStatsManager mUsageStatsManager;
    /* access modifiers changed from: private */
    public UserManager mUserManager;
    private String mVolumeUuid;
    private int mWorkUserId;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.mAppLockManager = (AppLockManager) getContext().getSystemService("applock");
        FragmentActivity activity = getActivity();
        this.mApplicationsState = ApplicationsState.getInstance(activity.getApplication());
        Intent intent = activity.getIntent();
        Bundle arguments = getArguments();
        int intExtra = intent.getIntExtra(":settings:show_fragment_title_resid", C1715R.string.application_info_label);
        String string = arguments != null ? arguments.getString("classname") : null;
        if (string == null) {
            string = intent.getComponent().getClassName();
        }
        int i = -1;
        if (string.equals(Settings.StorageUseActivity.class.getName())) {
            if (arguments == null || !arguments.containsKey("volumeUuid")) {
                this.mListType = 0;
            } else {
                this.mVolumeUuid = arguments.getString("volumeUuid");
                this.mStorageType = arguments.getInt("storageType", 0);
                this.mListType = 3;
            }
            this.mSortOrder = C1715R.C1718id.sort_order_size;
        } else if (string.equals(Settings.UsageAccessSettingsActivity.class.getName())) {
            this.mListType = 4;
            intExtra = C1715R.string.usage_access;
        } else if (string.equals(Settings.HighPowerApplicationsActivity.class.getName())) {
            this.mListType = 5;
            this.mShowSystem = true;
            intExtra = C1715R.string.high_power_apps;
        } else if (string.equals(Settings.OverlaySettingsActivity.class.getName())) {
            this.mListType = 6;
            intExtra = C1715R.string.system_alert_window_settings;
        } else if (string.equals(Settings.WriteSettingsActivity.class.getName())) {
            this.mListType = 7;
            intExtra = C1715R.string.write_settings;
        } else if (string.equals(Settings.ManageExternalSourcesActivity.class.getName())) {
            this.mListType = 8;
            intExtra = C1715R.string.install_other_apps;
        } else if (string.equals(Settings.GamesStorageActivity.class.getName())) {
            this.mListType = 9;
            this.mSortOrder = C1715R.C1718id.sort_order_size;
        } else if (string.equals(Settings.MoviesStorageActivity.class.getName())) {
            this.mListType = 10;
            this.mSortOrder = C1715R.C1718id.sort_order_size;
        } else if (string.equals(Settings.PhotosStorageActivity.class.getName())) {
            this.mListType = 11;
            this.mSortOrder = C1715R.C1718id.sort_order_size;
            this.mStorageType = arguments.getInt("storageType", 0);
        } else if (string.equals(Settings.ChangeWifiStateActivity.class.getName())) {
            this.mListType = 13;
            intExtra = C1715R.string.change_wifi_state_title;
        } else if (string.equals(Settings.NotificationAppListActivity.class.getName())) {
            this.mListType = 1;
            this.mUsageStatsManager = IUsageStatsManager.Stub.asInterface(ServiceManager.getService("usagestats"));
            this.mUserManager = UserManager.get(getContext());
            this.mNotificationBackend = new NotificationBackend();
            this.mSortOrder = C1715R.C1718id.sort_order_recent_notification;
            intExtra = C1715R.string.app_notifications_title;
        } else {
            if (intExtra == -1) {
                intExtra = C1715R.string.application_info_label;
            }
            this.mListType = 0;
        }
        AppFilterRegistry instance = AppFilterRegistry.getInstance();
        this.mFilter = instance.get(instance.getDefaultFilterType(this.mListType));
        this.mIsWorkOnly = arguments != null ? arguments.getBoolean("workProfileOnly") : false;
        if (arguments != null) {
            i = arguments.getInt("workId");
        }
        this.mWorkUserId = i;
        this.mExpandSearch = activity.getIntent().getBooleanExtra("expand_search_view", false);
        if (bundle != null) {
            this.mSortOrder = bundle.getInt("sortOrder", this.mSortOrder);
            this.mShowSystem = bundle.getBoolean("showSystem", this.mShowSystem);
            this.mFilterType = bundle.getInt("filterType", 2);
            this.mExpandSearch = bundle.getBoolean("expand_search_view");
        }
        this.mInvalidSizeStr = activity.getText(C1715R.string.invalid_size_value);
        this.mResetAppsHelper = new ResetAppsHelper(activity);
        if (intExtra > 0) {
            activity.setTitle(intExtra);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mListType != 6 || Utils.isSystemAlertWindowEnabled(getContext())) {
            this.mRootView = layoutInflater.inflate(C1715R.layout.manage_applications_apps, (ViewGroup) null);
            this.mLoadingContainer = this.mRootView.findViewById(C1715R.C1718id.loading_container);
            this.mListContainer = this.mRootView.findViewById(C1715R.C1718id.list_container);
            View view = this.mListContainer;
            if (view != null) {
                this.mEmptyView = view.findViewById(16908292);
                this.mApplications = new ApplicationsAdapter(this.mApplicationsState, this, this.mFilter, bundle);
                if (bundle != null) {
                    boolean unused = this.mApplications.mHasReceivedLoadEntries = bundle.getBoolean("hasEntries", false);
                    boolean unused2 = this.mApplications.mHasReceivedBridgeCallback = bundle.getBoolean("hasBridge", false);
                }
                int userId = this.mIsWorkOnly ? this.mWorkUserId : UserHandle.getUserId(this.mCurrentUid);
                int i = this.mStorageType;
                if (i == 1) {
                    Context context = getContext();
                    this.mApplications.setExtraViewController(new MusicViewHolderController(context, new StorageStatsSource(context), this.mVolumeUuid, UserHandle.of(userId)));
                } else if (i == 3) {
                    Context context2 = getContext();
                    this.mApplications.setExtraViewController(new PhotosViewHolderController(context2, new StorageStatsSource(context2), this.mVolumeUuid, UserHandle.of(userId)));
                }
                this.mRecyclerView = (RecyclerView) this.mListContainer.findViewById(C1715R.C1718id.apps_list);
                this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
                this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
                this.mRecyclerView.setAdapter(this.mApplications);
            }
            if (viewGroup instanceof PreferenceFrameLayout) {
                this.mRootView.getLayoutParams().removeBorders = true;
            }
            createHeader();
            this.mResetAppsHelper.onRestoreInstanceState(bundle);
            return this.mRootView;
        }
        this.mRootView = layoutInflater.inflate(C1715R.layout.manage_applications_apps_unsupported, (ViewGroup) null);
        setHasOptionsMenu(false);
        return this.mRootView;
    }

    /* access modifiers changed from: package-private */
    public void createHeader() {
        FragmentActivity activity = getActivity();
        FrameLayout frameLayout = (FrameLayout) this.mRootView.findViewById(C1715R.C1718id.pinned_header);
        this.mSpinnerHeader = activity.getLayoutInflater().inflate(C1715R.layout.apps_filter_spinner, frameLayout, false);
        this.mFilterSpinner = (Spinner) this.mSpinnerHeader.findViewById(C1715R.C1718id.filter_spinner);
        this.mFilterAdapter = new FilterSpinnerAdapter(this);
        this.mFilterSpinner.setAdapter(this.mFilterAdapter);
        this.mFilterSpinner.setOnItemSelectedListener(this);
        frameLayout.addView(this.mSpinnerHeader, 0);
        AppFilterRegistry instance = AppFilterRegistry.getInstance();
        this.mFilterAdapter.enableFilter(instance.getDefaultFilterType(this.mListType));
        if (this.mListType == 0 && UserManager.get(getActivity()).getUserProfiles().size() > 1) {
            this.mFilterAdapter.enableFilter(8);
            this.mFilterAdapter.enableFilter(9);
        }
        if (this.mListType == 1) {
            this.mFilterAdapter.enableFilter(6);
            this.mFilterAdapter.enableFilter(7);
            this.mFilterAdapter.enableFilter(16);
            this.mFilterAdapter.disableFilter(2);
        }
        if (this.mListType == 5) {
            this.mFilterAdapter.enableFilter(1);
        }
        ApplicationsState.AppFilter compositeFilter = getCompositeFilter(this.mListType, this.mStorageType, this.mVolumeUuid);
        if (this.mIsWorkOnly) {
            compositeFilter = new ApplicationsState.CompoundFilter(compositeFilter, instance.get(9).getFilter());
        }
        if (compositeFilter != null) {
            this.mApplications.setCompositeFilter(compositeFilter);
        }
    }

    static ApplicationsState.AppFilter getCompositeFilter(int i, int i2, String str) {
        ApplicationsState.VolumeFilter volumeFilter = new ApplicationsState.VolumeFilter(str);
        if (i == 3) {
            if (i2 == 1) {
                return new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_AUDIO, volumeFilter);
            }
            return i2 == 0 ? new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_OTHER_APPS, volumeFilter) : volumeFilter;
        } else if (i == 9) {
            return new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_GAMES, volumeFilter);
        } else {
            if (i == 10) {
                return new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_MOVIES, volumeFilter);
            }
            if (i == 11) {
                return new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_PHOTOS, volumeFilter);
            }
            return null;
        }
    }

    public int getMetricsCategory() {
        switch (this.mListType) {
            case 0:
                return 65;
            case 1:
                return 133;
            case 3:
                return this.mStorageType == 1 ? 839 : 182;
            case 4:
                return 95;
            case 5:
                return 184;
            case 6:
            case 7:
                return 221;
            case 8:
                return 808;
            case 9:
                return 838;
            case 10:
                return 935;
            case 11:
                return 1092;
            case 13:
                return 338;
            default:
                return 0;
        }
    }

    public void onStart() {
        super.onStart();
        updateView();
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.resume(this.mSortOrder);
            this.mApplications.updateLoading();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mResetAppsHelper.onSaveInstanceState(bundle);
        bundle.putInt("sortOrder", this.mSortOrder);
        bundle.putBoolean("showSystem", this.mShowSystem);
        bundle.putBoolean("hasEntries", this.mApplications.mHasReceivedLoadEntries);
        bundle.putBoolean("hasBridge", this.mApplications.mHasReceivedBridgeCallback);
        bundle.putBoolean("expand_search_view", !this.mSearchView.isIconified());
        bundle.putInt("filterType", this.mFilter.getFilterType());
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.onSaveInstanceState(bundle);
        }
    }

    public void onStop() {
        super.onStop();
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.pause();
        }
        this.mResetAppsHelper.stop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.release();
        }
        this.mRootView = null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        if (i == 1 && (str = this.mCurrentPkgName) != null) {
            int i3 = this.mListType;
            if (i3 == 1) {
                this.mApplications.mExtraInfoBridge.forceUpdate(this.mCurrentPkgName, this.mCurrentUid);
            } else if (i3 == 5 || i3 == 6 || i3 == 7) {
                this.mApplications.mExtraInfoBridge.forceUpdate(this.mCurrentPkgName, this.mCurrentUid);
            } else {
                this.mApplicationsState.requestSize(str, UserHandle.getUserId(this.mCurrentUid));
            }
        }
    }

    private void startApplicationDetailsActivity() {
        switch (this.mListType) {
            case 1:
                startAppInfoFragment(AppNotificationSettings.class, C1715R.string.notifications_title);
                return;
            case 3:
                startAppInfoFragment(AppStorageSettings.class, C1715R.string.storage_settings);
                return;
            case 4:
                startAppInfoFragment(UsageAccessDetails.class, C1715R.string.usage_access);
                return;
            case 5:
                HighPowerDetail.show(this, this.mCurrentUid, this.mCurrentPkgName, 1);
                return;
            case 6:
                if (!this.mAppLockManager.isAppLocked(this.mCurrentPkgName)) {
                    startAppInfoFragment(DrawOverlayDetails.class, C1715R.string.overlay_settings);
                    return;
                }
                return;
            case 7:
                startAppInfoFragment(WriteSettingsDetails.class, C1715R.string.write_system_settings);
                return;
            case 8:
                startAppInfoFragment(ExternalSourcesDetails.class, C1715R.string.install_other_apps);
                return;
            case 9:
                startAppInfoFragment(AppStorageSettings.class, C1715R.string.game_storage_settings);
                return;
            case 10:
                startAppInfoFragment(AppStorageSettings.class, C1715R.string.storage_movies_tv);
                return;
            case 11:
                startAppInfoFragment(AppStorageSettings.class, C1715R.string.storage_photos_videos);
                return;
            case 13:
                startAppInfoFragment(ChangeWifiStateDetails.class, C1715R.string.change_wifi_state_title);
                return;
            default:
                startAppInfoFragment(AppInfoDashboardFragment.class, C1715R.string.application_info_label);
                return;
        }
    }

    private void startAppInfoFragment(Class<?> cls, int i) {
        AppInfoBase.startAppInfoFragment(cls, i, this.mCurrentPkgName, this.mCurrentUid, this, 1, getMetricsCategory());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            HelpUtils.prepareHelpMenuItem((Activity) activity, menu, getHelpResource(), ManageApplications.class.getName());
            this.mOptionsMenu = menu;
            menuInflater.inflate(C1715R.C1720menu.manage_apps, menu);
            MenuItem findItem = menu.findItem(C1715R.C1718id.search_app_list_menu);
            if (findItem != null) {
                this.mSearchView = (SearchView) findItem.getActionView();
                this.mSearchView.setQueryHint(getText(C1715R.string.search_settings));
                this.mSearchView.setOnQueryTextListener(this);
                if (this.mExpandSearch) {
                    findItem.expandActionView();
                }
            }
            updateOptionsMenu();
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        updateOptionsMenu();
    }

    public void onDestroyOptionsMenu() {
        this.mOptionsMenu = null;
    }

    /* access modifiers changed from: package-private */
    public int getHelpResource() {
        switch (this.mListType) {
            case 1:
                return C1715R.string.help_uri_notifications;
            case 3:
                return C1715R.string.help_uri_apps_storage;
            case 4:
                return C1715R.string.help_url_usage_access;
            case 5:
                return C1715R.string.help_uri_apps_high_power;
            case 6:
                return C1715R.string.help_uri_apps_overlay;
            case 7:
                return C1715R.string.help_uri_apps_write_settings;
            case 8:
                return C1715R.string.help_uri_apps_manage_sources;
            case 9:
                return C1715R.string.help_uri_apps_overlay;
            case 10:
                return C1715R.string.help_uri_apps_movies;
            case 11:
                return C1715R.string.help_uri_apps_photography;
            case 13:
                return C1715R.string.help_uri_apps_wifi_access;
            default:
                return C1715R.string.help_uri_apps;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateOptionsMenu() {
        Menu menu = this.mOptionsMenu;
        if (menu != null) {
            menu.findItem(C1715R.C1718id.advanced).setVisible(false);
            MenuItem findItem = this.mOptionsMenu.findItem(C1715R.C1718id.sort_order_alpha);
            int i = this.mListType;
            boolean z = true;
            findItem.setVisible((i == 3 || i == 0) && this.mSortOrder != C1715R.C1718id.sort_order_alpha);
            MenuItem findItem2 = this.mOptionsMenu.findItem(C1715R.C1718id.sort_order_size);
            int i2 = this.mListType;
            findItem2.setVisible((i2 == 3 || i2 == 0) && this.mSortOrder != C1715R.C1718id.sort_order_size);
            this.mOptionsMenu.findItem(C1715R.C1718id.show_system).setVisible(!this.mShowSystem && this.mListType != 5);
            this.mOptionsMenu.findItem(C1715R.C1718id.hide_system).setVisible(this.mShowSystem && this.mListType != 5);
            this.mOptionsMenu.findItem(C1715R.C1718id.notification_log).setVisible(this.mListType == 1);
            MenuItem findItem3 = this.mOptionsMenu.findItem(C1715R.C1718id.reset_app_preferences);
            if (this.mListType != 0) {
                z = false;
            }
            findItem3.setVisible(z);
            this.mOptionsMenu.findItem(C1715R.C1718id.sort_order_recent_notification).setVisible(false);
            this.mOptionsMenu.findItem(C1715R.C1718id.sort_order_frequent_notification).setVisible(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (menuItem.getItemId()) {
            case C1715R.C1718id.advanced /*2131361905*/:
                if (this.mListType == 1) {
                    new SubSettingLauncher(getContext()).setDestination(ConfigureNotificationSettings.class.getName()).setTitleRes(C1715R.string.configure_notification_settings).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 2).launch();
                } else {
                    startActivityForResult(new Intent("android.settings.MANAGE_DEFAULT_APPS_SETTINGS"), 2);
                }
                return true;
            case C1715R.C1718id.hide_system /*2131362406*/:
            case C1715R.C1718id.show_system /*2131362892*/:
                this.mShowSystem = !this.mShowSystem;
                this.mApplications.rebuild();
                break;
            case C1715R.C1718id.notification_log /*2131362649*/:
                new SubSettingLauncher(getContext()).setDestination(NotificationStation.class.getName()).setTitleRes(C1715R.string.notification_log_title).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 3).launch();
                return true;
            case C1715R.C1718id.reset_app_preferences /*2131362796*/:
                this.mResetAppsHelper.buildResetDialog();
                return true;
            case C1715R.C1718id.sort_order_alpha /*2131362921*/:
            case C1715R.C1718id.sort_order_size /*2131362924*/:
                ApplicationsAdapter applicationsAdapter = this.mApplications;
                if (applicationsAdapter != null) {
                    applicationsAdapter.rebuild(itemId);
                    break;
                }
                break;
            default:
                return false;
        }
        updateOptionsMenu();
        return true;
    }

    public void onClick(View view) {
        if (this.mApplications != null) {
            int childAdapterPosition = this.mRecyclerView.getChildAdapterPosition(view);
            if (childAdapterPosition == -1) {
                Log.w("ManageApplications", "Cannot find position for child, skipping onClick handling");
            } else if (this.mApplications.getApplicationCount() > childAdapterPosition) {
                ApplicationsState.AppEntry appEntry = this.mApplications.getAppEntry(childAdapterPosition);
                this.mCurrentPkgName = appEntry.info.packageName;
                this.mCurrentUid = appEntry.info.uid;
                startApplicationDetailsActivity();
            } else {
                this.mApplications.mExtraViewController.onClick(this);
            }
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.mFilter = this.mFilterAdapter.getFilter(i);
        this.mApplications.setFilter(this.mFilter);
    }

    public boolean onQueryTextChange(String str) {
        this.mApplications.filterSearch(str);
        return false;
    }

    public void updateView() {
        updateOptionsMenu();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    public void setHasDisabled(boolean z) {
        if (this.mListType == 0) {
            this.mFilterAdapter.setFilterEnabled(3, z);
            this.mFilterAdapter.setFilterEnabled(5, z);
        }
    }

    public void setHasInstant(boolean z) {
        if (LIST_TYPES_WITH_INSTANT.contains(Integer.valueOf(this.mListType))) {
            this.mFilterAdapter.setFilterEnabled(4, z);
        }
    }

    static class FilterSpinnerAdapter extends SettingsSpinnerAdapter<CharSequence> {
        private final Context mContext;
        private final ArrayList<AppFilterItem> mFilterOptions = new ArrayList<>();
        private final ManageApplications mManageApplications;

        public FilterSpinnerAdapter(ManageApplications manageApplications) {
            super(manageApplications.getContext());
            this.mContext = manageApplications.getContext();
            this.mManageApplications = manageApplications;
        }

        public AppFilterItem getFilter(int i) {
            return this.mFilterOptions.get(i);
        }

        public void setFilterEnabled(int i, boolean z) {
            if (z) {
                enableFilter(i);
            } else {
                disableFilter(i);
            }
        }

        public void enableFilter(int i) {
            int indexOf;
            AppFilterItem appFilterItem = AppFilterRegistry.getInstance().get(i);
            if (!this.mFilterOptions.contains(appFilterItem)) {
                this.mFilterOptions.add(appFilterItem);
                Collections.sort(this.mFilterOptions);
                updateFilterView(this.mFilterOptions.size() > 1);
                notifyDataSetChanged();
                if (this.mFilterOptions.size() == 1) {
                    this.mManageApplications.mFilterSpinner.setSelection(0);
                    this.mManageApplications.onItemSelected((AdapterView<?>) null, (View) null, 0, 0);
                }
                if (this.mFilterOptions.size() > 1 && (indexOf = this.mFilterOptions.indexOf(AppFilterRegistry.getInstance().get(this.mManageApplications.mFilterType))) != -1) {
                    this.mManageApplications.mFilterSpinner.setSelection(indexOf);
                    this.mManageApplications.onItemSelected((AdapterView<?>) null, (View) null, indexOf, 0);
                }
            }
        }

        public void disableFilter(int i) {
            AppFilterItem appFilterItem = AppFilterRegistry.getInstance().get(i);
            if (this.mFilterOptions.remove(appFilterItem)) {
                Collections.sort(this.mFilterOptions);
                boolean z = true;
                if (this.mFilterOptions.size() <= 1) {
                    z = false;
                }
                updateFilterView(z);
                notifyDataSetChanged();
                if (this.mManageApplications.mFilter == appFilterItem && this.mFilterOptions.size() > 0) {
                    this.mManageApplications.mFilterSpinner.setSelection(0);
                    this.mManageApplications.onItemSelected((AdapterView<?>) null, (View) null, 0, 0);
                }
            }
        }

        public int getCount() {
            return this.mFilterOptions.size();
        }

        public CharSequence getItem(int i) {
            return this.mContext.getText(this.mFilterOptions.get(i).getTitle());
        }

        /* access modifiers changed from: package-private */
        public void updateFilterView(boolean z) {
            if (z) {
                this.mManageApplications.mSpinnerHeader.setVisibility(0);
                this.mManageApplications.mRecyclerView.setPadding(0, this.mContext.getResources().getDimensionPixelSize(C1715R.dimen.app_bar_height), 0, 0);
                return;
            }
            this.mManageApplications.mSpinnerHeader.setVisibility(8);
            this.mManageApplications.mRecyclerView.setPadding(0, 0, 0, 0);
        }
    }

    static class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationViewHolder> implements ApplicationsState.Callbacks, AppStateBaseBridge.Callback {
        private AppFilterItem mAppFilter;
        private PowerWhitelistBackend mBackend;
        private ApplicationsState.AppFilter mCompositeFilter;
        private final Context mContext;
        /* access modifiers changed from: private */
        public ArrayList<ApplicationsState.AppEntry> mEntries;
        /* access modifiers changed from: private */
        public final AppStateBaseBridge mExtraInfoBridge;
        /* access modifiers changed from: private */
        public FileViewHolderController mExtraViewController;
        /* access modifiers changed from: private */
        public boolean mHasReceivedBridgeCallback;
        /* access modifiers changed from: private */
        public boolean mHasReceivedLoadEntries;
        private final IconDrawableFactory mIconDrawableFactory;
        private int mLastIndex = -1;
        private int mLastSortMode = -1;
        private final LoadingViewController mLoadingViewController;
        private final ManageApplications mManageApplications;
        OnScrollListener mOnScrollListener;
        /* access modifiers changed from: private */
        public ArrayList<ApplicationsState.AppEntry> mOriginalEntries;
        private RecyclerView mRecyclerView;
        private boolean mResumed;
        private SearchFilter mSearchFilter;
        private final ApplicationsState.Session mSession;
        private final ApplicationsState mState;
        private int mWhichSize = 0;

        public void onPackageIconChanged() {
        }

        public ApplicationsAdapter(ApplicationsState applicationsState, ManageApplications manageApplications, AppFilterItem appFilterItem, Bundle bundle) {
            setHasStableIds(true);
            this.mState = applicationsState;
            this.mSession = applicationsState.newSession(this);
            this.mManageApplications = manageApplications;
            this.mLoadingViewController = new LoadingViewController(this.mManageApplications.mLoadingContainer, this.mManageApplications.mListContainer);
            this.mContext = manageApplications.getActivity();
            this.mIconDrawableFactory = IconDrawableFactory.newInstance(this.mContext);
            this.mAppFilter = appFilterItem;
            this.mBackend = PowerWhitelistBackend.getInstance(this.mContext);
            int i = this.mManageApplications.mListType;
            if (i == 1) {
                this.mExtraInfoBridge = new AppStateNotificationBridge(this.mContext, this.mState, this, manageApplications.mUsageStatsManager, manageApplications.mUserManager, manageApplications.mNotificationBackend);
            } else if (i == 4) {
                this.mExtraInfoBridge = new AppStateUsageBridge(this.mContext, this.mState, this);
            } else if (i == 5) {
                this.mExtraInfoBridge = new AppStatePowerBridge(this.mContext, this.mState, this);
            } else if (i == 6) {
                this.mExtraInfoBridge = new AppStateOverlayBridge(this.mContext, this.mState, this);
            } else if (i == 7) {
                this.mExtraInfoBridge = new AppStateWriteSettingsBridge(this.mContext, this.mState, this);
            } else if (i == 8) {
                this.mExtraInfoBridge = new AppStateInstallAppsBridge(this.mContext, this.mState, this);
            } else if (i == 13) {
                this.mExtraInfoBridge = new AppStateChangeWifiStateBridge(this.mContext, this.mState, this);
            } else {
                this.mExtraInfoBridge = null;
            }
            if (bundle != null) {
                this.mLastIndex = bundle.getInt("state_last_scroll_index");
            }
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            this.mRecyclerView = recyclerView;
            this.mOnScrollListener = new OnScrollListener(this);
            this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
        }

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
            this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
            this.mOnScrollListener = null;
            this.mRecyclerView = null;
        }

        public void setCompositeFilter(ApplicationsState.AppFilter appFilter) {
            this.mCompositeFilter = appFilter;
            rebuild();
        }

        public void setFilter(AppFilterItem appFilterItem) {
            this.mAppFilter = appFilterItem;
            if (7 == appFilterItem.getFilterType()) {
                rebuild(C1715R.C1718id.sort_order_frequent_notification);
            } else if (6 == appFilterItem.getFilterType()) {
                rebuild(C1715R.C1718id.sort_order_recent_notification);
            } else if (16 == appFilterItem.getFilterType()) {
                rebuild(C1715R.C1718id.sort_order_alpha);
            } else {
                rebuild();
            }
        }

        public void setExtraViewController(FileViewHolderController fileViewHolderController) {
            this.mExtraViewController = fileViewHolderController;
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable() {
                public final void run() {
                    ManageApplications.ApplicationsAdapter.this.mo8329x2547e49b();
                }
            });
        }

        /* renamed from: lambda$setExtraViewController$1$ManageApplications$ApplicationsAdapter */
        public /* synthetic */ void mo8329x2547e49b() {
            this.mExtraViewController.queryStats();
            ThreadUtils.postOnMainThread(new Runnable() {
                public final void run() {
                    ManageApplications.ApplicationsAdapter.this.mo8328xce29f3bc();
                }
            });
        }

        public void resume(int i) {
            if (!this.mResumed) {
                this.mResumed = true;
                this.mSession.onResume();
                this.mLastSortMode = i;
                AppStateBaseBridge appStateBaseBridge = this.mExtraInfoBridge;
                if (appStateBaseBridge != null) {
                    appStateBaseBridge.resume();
                }
                rebuild();
                return;
            }
            rebuild(i);
        }

        public void pause() {
            if (this.mResumed) {
                this.mResumed = false;
                this.mSession.onPause();
                AppStateBaseBridge appStateBaseBridge = this.mExtraInfoBridge;
                if (appStateBaseBridge != null) {
                    appStateBaseBridge.pause();
                }
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            bundle.putInt("state_last_scroll_index", ((LinearLayoutManager) this.mManageApplications.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition());
        }

        public void release() {
            this.mSession.onDestroy();
            AppStateBaseBridge appStateBaseBridge = this.mExtraInfoBridge;
            if (appStateBaseBridge != null) {
                appStateBaseBridge.release();
            }
        }

        public void rebuild(int i) {
            if (i != this.mLastSortMode) {
                this.mManageApplications.mSortOrder = i;
                this.mLastSortMode = i;
                rebuild();
            }
        }

        public ApplicationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view;
            if (this.mManageApplications.mListType == 1) {
                view = ApplicationViewHolder.newView(viewGroup, true);
            } else {
                view = ApplicationViewHolder.newView(viewGroup, false);
            }
            return new ApplicationViewHolder(view);
        }

        public int getItemViewType(int i) {
            boolean z = getItemCount() - 1 == i;
            if (!hasExtraView() || !z) {
                return 0;
            }
            return 1;
        }

        public void rebuild() {
            ApplicationsState.AppFilter appFilter;
            Comparator<ApplicationsState.AppEntry> comparator;
            if (!this.mHasReceivedLoadEntries) {
                return;
            }
            if (this.mExtraInfoBridge == null || this.mHasReceivedBridgeCallback) {
                if (Environment.isExternalStorageEmulated()) {
                    this.mWhichSize = 0;
                } else {
                    this.mWhichSize = 1;
                }
                ApplicationsState.AppFilter filter = this.mAppFilter.getFilter();
                ApplicationsState.AppFilter appFilter2 = this.mCompositeFilter;
                if (appFilter2 != null) {
                    filter = new ApplicationsState.CompoundFilter(filter, appFilter2);
                }
                if (!this.mManageApplications.mShowSystem) {
                    appFilter = ManageApplications.LIST_TYPES_WITH_INSTANT.contains(Integer.valueOf(this.mManageApplications.mListType)) ? new ApplicationsState.CompoundFilter(filter, ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER_AND_INSTANT) : new ApplicationsState.CompoundFilter(filter, ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER);
                } else {
                    appFilter = filter;
                }
                switch (this.mLastSortMode) {
                    case C1715R.C1718id.sort_order_frequent_notification /*2131362922*/:
                        comparator = AppStateNotificationBridge.FREQUENCY_NOTIFICATION_COMPARATOR;
                        break;
                    case C1715R.C1718id.sort_order_recent_notification /*2131362923*/:
                        comparator = AppStateNotificationBridge.RECENT_NOTIFICATION_COMPARATOR;
                        break;
                    case C1715R.C1718id.sort_order_size /*2131362924*/:
                        int i = this.mWhichSize;
                        if (i != 1) {
                            if (i == 2) {
                                comparator = ApplicationsState.EXTERNAL_SIZE_COMPARATOR;
                                break;
                            } else {
                                comparator = ApplicationsState.SIZE_COMPARATOR;
                                break;
                            }
                        } else {
                            comparator = ApplicationsState.INTERNAL_SIZE_COMPARATOR;
                            break;
                        }
                    default:
                        comparator = ApplicationsState.ALPHA_COMPARATOR;
                        break;
                }
                ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(new ApplicationsState.CompoundFilter(appFilter, ApplicationsState.FILTER_NOT_HIDE), comparator) {
                    private final /* synthetic */ ApplicationsState.AppFilter f$1;
                    private final /* synthetic */ Comparator f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        ManageApplications.ApplicationsAdapter.this.lambda$rebuild$3$ManageApplications$ApplicationsAdapter(this.f$1, this.f$2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$rebuild$3$ManageApplications$ApplicationsAdapter(ApplicationsState.AppFilter appFilter, Comparator comparator) {
            ArrayList<ApplicationsState.AppEntry> rebuild = this.mSession.rebuild(appFilter, comparator, false);
            if (rebuild != null) {
                ThreadUtils.postOnMainThread(new Runnable(rebuild) {
                    private final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ManageApplications.ApplicationsAdapter.this.lambda$rebuild$2$ManageApplications$ApplicationsAdapter(this.f$1);
                    }
                });
            }
        }

        /* access modifiers changed from: package-private */
        public void filterSearch(String str) {
            if (this.mSearchFilter == null) {
                this.mSearchFilter = new SearchFilter();
            }
            if (this.mOriginalEntries == null) {
                Log.w("ManageApplications", "Apps haven't loaded completely yet, so nothing can be filtered");
            } else {
                this.mSearchFilter.filter(str);
            }
        }

        private static boolean packageNameEquals(PackageItemInfo packageItemInfo, PackageItemInfo packageItemInfo2) {
            String str;
            String str2;
            if (packageItemInfo == null || packageItemInfo2 == null || (str = packageItemInfo.packageName) == null || (str2 = packageItemInfo2.packageName) == null) {
                return false;
            }
            return str.equals(str2);
        }

        private ArrayList<ApplicationsState.AppEntry> removeDuplicateIgnoringUser(ArrayList<ApplicationsState.AppEntry> arrayList) {
            int size = arrayList.size();
            ArrayList<ApplicationsState.AppEntry> arrayList2 = new ArrayList<>(size);
            ApplicationInfo applicationInfo = null;
            int i = 0;
            while (i < size) {
                ApplicationsState.AppEntry appEntry = arrayList.get(i);
                ApplicationInfo applicationInfo2 = appEntry.info;
                if (!packageNameEquals(applicationInfo, applicationInfo2)) {
                    arrayList2.add(appEntry);
                }
                i++;
                applicationInfo = applicationInfo2;
            }
            arrayList2.trimToSize();
            return arrayList2;
        }

        /* renamed from: onRebuildComplete */
        public void lambda$rebuild$2$ManageApplications$ApplicationsAdapter(ArrayList<ApplicationsState.AppEntry> arrayList) {
            int filterType = this.mAppFilter.getFilterType();
            if (filterType == 0 || filterType == 1) {
                arrayList = removeDuplicateIgnoringUser(arrayList);
            }
            this.mEntries = arrayList;
            this.mOriginalEntries = arrayList;
            notifyDataSetChanged();
            if (getItemCount() == 0) {
                this.mManageApplications.mRecyclerView.setVisibility(8);
                this.mManageApplications.mEmptyView.setVisibility(0);
            } else {
                this.mManageApplications.mEmptyView.setVisibility(8);
                this.mManageApplications.mRecyclerView.setVisibility(0);
                if (this.mManageApplications.mSearchView != null && this.mManageApplications.mSearchView.isVisibleToUser()) {
                    CharSequence query = this.mManageApplications.mSearchView.getQuery();
                    if (!TextUtils.isEmpty(query)) {
                        filterSearch(query.toString());
                    }
                }
            }
            if (this.mLastIndex != -1 && getItemCount() > this.mLastIndex) {
                this.mManageApplications.mRecyclerView.getLayoutManager().scrollToPosition(this.mLastIndex);
                this.mLastIndex = -1;
            }
            if (!(this.mSession.getAllApps().size() == 0 || this.mManageApplications.mListContainer.getVisibility() == 0)) {
                this.mLoadingViewController.showContent(true);
            }
            ManageApplications manageApplications = this.mManageApplications;
            if (manageApplications.mListType != 4) {
                manageApplications.setHasDisabled(this.mState.haveDisabledApps());
                this.mManageApplications.setHasInstant(this.mState.haveInstantApps());
            }
        }

        /* access modifiers changed from: package-private */
        public void updateLoading() {
            if (this.mHasReceivedLoadEntries && this.mSession.getAllApps().size() != 0) {
                this.mLoadingViewController.showContent(false);
            } else {
                this.mLoadingViewController.showLoadingViewDelayed();
            }
        }

        public void onExtraInfoUpdated() {
            this.mHasReceivedBridgeCallback = true;
            rebuild();
        }

        public void onRunningStateChanged(boolean z) {
            this.mManageApplications.getActivity().setProgressBarIndeterminateVisibility(z);
        }

        public void onPackageListChanged() {
            rebuild();
        }

        public void onLoadEntriesCompleted() {
            this.mHasReceivedLoadEntries = true;
            rebuild();
        }

        public void onPackageSizeChanged(String str) {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ApplicationInfo applicationInfo = this.mEntries.get(i).info;
                    if (applicationInfo != null || TextUtils.equals(str, applicationInfo.packageName)) {
                        if (TextUtils.equals(this.mManageApplications.mCurrentPkgName, applicationInfo.packageName)) {
                            rebuild();
                            return;
                        }
                        this.mOnScrollListener.postNotifyItemChange(i);
                    }
                }
            }
        }

        public void onLauncherInfoChanged() {
            if (!this.mManageApplications.mShowSystem) {
                rebuild();
            }
        }

        public void onAllSizesComputed() {
            if (this.mLastSortMode == C1715R.C1718id.sort_order_size) {
                rebuild();
            }
        }

        /* renamed from: onExtraViewCompleted */
        public void mo8328xce29f3bc() {
            if (hasExtraView()) {
                notifyItemChanged(getItemCount() - 1);
            }
        }

        public int getItemCount() {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList == null) {
                return 0;
            }
            return arrayList.size() + (hasExtraView() ? 1 : 0);
        }

        public int getApplicationCount() {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        public ApplicationsState.AppEntry getAppEntry(int i) {
            return this.mEntries.get(i);
        }

        public long getItemId(int i) {
            if (i == this.mEntries.size()) {
                return -1;
            }
            return this.mEntries.get(i).f61id;
        }

        public boolean isEnabled(int i) {
            if (getItemViewType(i) == 1 || this.mManageApplications.mListType != 5) {
                return true;
            }
            ApplicationsState.AppEntry appEntry = this.mEntries.get(i);
            if (this.mBackend.isSysWhitelisted(appEntry.info.packageName) || this.mBackend.isDefaultActiveApp(appEntry.info.packageName)) {
                return false;
            }
            return true;
        }

        public void onBindViewHolder(ApplicationViewHolder applicationViewHolder, int i) {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList == null || this.mExtraViewController == null || i != arrayList.size()) {
                ApplicationsState.AppEntry appEntry = this.mEntries.get(i);
                synchronized (appEntry) {
                    applicationViewHolder.setEnabled(isEnabled(i));
                    applicationViewHolder.setTitle(appEntry.label);
                    applicationViewHolder.setIcon(this.mIconDrawableFactory.getBadgedIcon(appEntry.info));
                    updateSummary(applicationViewHolder, appEntry);
                    updateSwitch(applicationViewHolder, appEntry);
                    applicationViewHolder.updateDisableView(appEntry.info);
                }
            } else {
                this.mExtraViewController.setupView(applicationViewHolder);
            }
            applicationViewHolder.itemView.setOnClickListener(this.mManageApplications);
        }

        private void updateSummary(ApplicationViewHolder applicationViewHolder, ApplicationsState.AppEntry appEntry) {
            CharSequence charSequence;
            ManageApplications manageApplications = this.mManageApplications;
            int i = manageApplications.mListType;
            if (i == 1) {
                Object obj = appEntry.extraInfo;
                if (obj == null || !(obj instanceof AppStateNotificationBridge.NotificationsSentState)) {
                    applicationViewHolder.setSummary((CharSequence) null);
                } else {
                    applicationViewHolder.setSummary(AppStateNotificationBridge.getSummary(this.mContext, (AppStateNotificationBridge.NotificationsSentState) obj, this.mLastSortMode));
                }
            } else if (i != 13) {
                switch (i) {
                    case 4:
                        Object obj2 = appEntry.extraInfo;
                        if (obj2 != null) {
                            applicationViewHolder.setSummary(new AppStateUsageBridge.UsageState((AppStateAppOpsBridge.PermissionState) obj2).isPermissible() ? C1715R.string.app_permission_summary_allowed : C1715R.string.app_permission_summary_not_allowed);
                            return;
                        } else {
                            applicationViewHolder.setSummary((CharSequence) null);
                            return;
                        }
                    case 5:
                        applicationViewHolder.setSummary(HighPowerDetail.getSummary(this.mContext, appEntry));
                        return;
                    case 6:
                        if (((AppLockManager) this.mContext.getSystemService("applock")).isAppLocked(appEntry.info.packageName)) {
                            charSequence = this.mContext.getString(C1715R.string.applock_overlay_summary);
                            applicationViewHolder.setEnabled(false);
                        } else {
                            charSequence = DrawOverlayDetails.getSummary(this.mContext, appEntry);
                            applicationViewHolder.setEnabled(true);
                        }
                        applicationViewHolder.setSummary(charSequence);
                        return;
                    case 7:
                        applicationViewHolder.setSummary(WriteSettingsDetails.getSummary(this.mContext, appEntry));
                        return;
                    case 8:
                        applicationViewHolder.setSummary(ExternalSourcesDetails.getPreferenceSummary(this.mContext, appEntry));
                        return;
                    default:
                        applicationViewHolder.updateSizeText(appEntry, manageApplications.mInvalidSizeStr, this.mWhichSize);
                        return;
                }
            } else {
                applicationViewHolder.setSummary(ChangeWifiStateDetails.getSummary(this.mContext, appEntry));
            }
        }

        private void updateSwitch(ApplicationViewHolder applicationViewHolder, ApplicationsState.AppEntry appEntry) {
            if (this.mManageApplications.mListType == 1) {
                applicationViewHolder.updateSwitch(((AppStateNotificationBridge) this.mExtraInfoBridge).getSwitchOnClickListener(appEntry), AppStateNotificationBridge.enableSwitch(appEntry), AppStateNotificationBridge.checkSwitch(appEntry));
                Object obj = appEntry.extraInfo;
                if (obj == null || !(obj instanceof AppStateNotificationBridge.NotificationsSentState)) {
                    applicationViewHolder.setSummary((CharSequence) null);
                } else {
                    applicationViewHolder.setSummary(AppStateNotificationBridge.getSummary(this.mContext, (AppStateNotificationBridge.NotificationsSentState) obj, this.mLastSortMode));
                }
            }
        }

        private boolean hasExtraView() {
            FileViewHolderController fileViewHolderController = this.mExtraViewController;
            return fileViewHolderController != null && fileViewHolderController.shouldShow();
        }

        public static class OnScrollListener extends RecyclerView.OnScrollListener {
            private ApplicationsAdapter mAdapter;
            private boolean mDelayNotifyDataChange;
            private int mScrollState = 0;

            public OnScrollListener(ApplicationsAdapter applicationsAdapter) {
                this.mAdapter = applicationsAdapter;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                this.mScrollState = i;
                if (this.mScrollState == 0 && this.mDelayNotifyDataChange) {
                    this.mDelayNotifyDataChange = false;
                    this.mAdapter.notifyDataSetChanged();
                }
            }

            public void postNotifyItemChange(int i) {
                if (this.mScrollState == 0) {
                    this.mAdapter.notifyItemChanged(i);
                } else {
                    this.mDelayNotifyDataChange = true;
                }
            }
        }

        private class SearchFilter extends Filter {
            private SearchFilter() {
            }

            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                ArrayList arrayList;
                if (TextUtils.isEmpty(charSequence)) {
                    arrayList = ApplicationsAdapter.this.mOriginalEntries;
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it = ApplicationsAdapter.this.mOriginalEntries.iterator();
                    while (it.hasNext()) {
                        ApplicationsState.AppEntry appEntry = (ApplicationsState.AppEntry) it.next();
                        if (appEntry.label.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            arrayList2.add(appEntry);
                        }
                    }
                    arrayList = arrayList2;
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
                return filterResults;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                ArrayList unused = ApplicationsAdapter.this.mEntries = (ArrayList) filterResults.values;
                ApplicationsAdapter.this.notifyDataSetChanged();
            }
        }
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        /* access modifiers changed from: private */
        public final Context mContext;
        /* access modifiers changed from: private */
        public final SummaryLoader mLoader;

        private SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mLoader = summaryLoader;
        }

        public void setListening(boolean z) {
            if (z) {
                Context context = this.mContext;
                new InstalledAppCounter(context, -1, context.getPackageManager()) {
                    /* access modifiers changed from: protected */
                    public void onCountComplete(int i) {
                        SummaryLoader access$2000 = SummaryProvider.this.mLoader;
                        SummaryProvider summaryProvider = SummaryProvider.this;
                        access$2000.setSummary(summaryProvider, summaryProvider.mContext.getString(C1715R.string.apps_summary, new Object[]{Integer.valueOf(i)}));
                    }
                }.execute(new Void[0]);
            }
        }
    }
}
