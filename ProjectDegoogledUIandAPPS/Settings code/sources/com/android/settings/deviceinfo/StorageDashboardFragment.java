package com.android.settings.deviceinfo;

import android.app.Activity;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.provider.SearchIndexableResource;
import android.util.SparseArray;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.settings.Utils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.deviceinfo.StorageDashboardFragment;
import com.android.settings.deviceinfo.storage.AutomaticStorageManagementSwitchPreferenceController;
import com.android.settings.deviceinfo.storage.CachedStorageValuesHelper;
import com.android.settings.deviceinfo.storage.SecondaryUserController;
import com.android.settings.deviceinfo.storage.StorageAsyncLoader;
import com.android.settings.deviceinfo.storage.StorageItemPreferenceController;
import com.android.settings.deviceinfo.storage.StorageSummaryDonutPreferenceController;
import com.android.settings.deviceinfo.storage.UserIconLoader;
import com.android.settings.deviceinfo.storage.VolumeSizesLoader;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StorageDashboardFragment extends DashboardFragment implements LoaderManager.LoaderCallbacks<SparseArray<StorageAsyncLoader.AppsStorageResult>> {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.storage_dashboard_fragment;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StorageSummaryDonutPreferenceController(context));
            arrayList.add(new StorageItemPreferenceController(context, (Fragment) null, (VolumeInfo) null, new StorageManagerVolumeProvider((StorageManager) context.getSystemService(StorageManager.class))));
            arrayList.addAll(SecondaryUserController.getSecondaryUserControllers(context, (UserManager) context.getSystemService(UserManager.class)));
            return arrayList;
        }
    };
    private SparseArray<StorageAsyncLoader.AppsStorageResult> mAppsResult;
    private CachedStorageValuesHelper mCachedStorageValuesHelper;
    private PrivateVolumeOptionMenuController mOptionMenuController;
    private StorageItemPreferenceController mPreferenceController;
    /* access modifiers changed from: private */
    public List<AbstractPreferenceController> mSecondaryUsers;
    /* access modifiers changed from: private */
    public PrivateStorageInfo mStorageInfo;
    private StorageSummaryDonutPreferenceController mSummaryController;
    /* access modifiers changed from: private */
    public VolumeInfo mVolume;

    public int getHelpResource() {
        return C1715R.string.help_url_storage_dashboard;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "StorageDashboardFrag";
    }

    public int getMetricsCategory() {
        return 745;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.storage_dashboard_fragment;
    }

    public void onLoaderReset(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mVolume = Utils.maybeInitializeVolume((StorageManager) activity.getSystemService(StorageManager.class), getArguments());
        if (this.mVolume == null) {
            activity.finish();
        } else {
            initializeOptionsMenu(activity);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((AutomaticStorageManagementSwitchPreferenceController) use(AutomaticStorageManagementSwitchPreferenceController.class)).setFragmentManager(getFragmentManager());
    }

    /* access modifiers changed from: package-private */
    public void initializeOptionsMenu(Activity activity) {
        this.mOptionMenuController = new PrivateVolumeOptionMenuController(activity, this.mVolume, activity.getPackageManager());
        getSettingsLifecycle().addObserver(this.mOptionMenuController);
        setHasOptionsMenu(true);
        activity.invalidateOptionsMenu();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initializeCacheProvider();
        maybeSetLoading(isQuotaSupported());
        FragmentActivity activity = getActivity();
        EntityHeaderController.newInstance(activity, this, (View) null).setRecyclerView(getListView(), getSettingsLifecycle()).styleActionBar(activity);
    }

    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, Bundle.EMPTY, this);
        getLoaderManager().restartLoader(2, Bundle.EMPTY, new VolumeSizeCallbacks());
        getLoaderManager().initLoader(1, Bundle.EMPTY, new IconLoaderCallbacks());
    }

    /* access modifiers changed from: private */
    public void onReceivedSizes() {
        PrivateStorageInfo privateStorageInfo = this.mStorageInfo;
        if (privateStorageInfo != null) {
            long j = privateStorageInfo.totalBytes;
            long j2 = j - privateStorageInfo.freeBytes;
            this.mSummaryController.updateBytes(j2, j);
            this.mPreferenceController.setVolume(this.mVolume);
            this.mPreferenceController.setUsedSize(j2);
            this.mPreferenceController.setTotalSize(this.mStorageInfo.totalBytes);
            int size = this.mSecondaryUsers.size();
            for (int i = 0; i < size; i++) {
                AbstractPreferenceController abstractPreferenceController = this.mSecondaryUsers.get(i);
                if (abstractPreferenceController instanceof SecondaryUserController) {
                    ((SecondaryUserController) abstractPreferenceController).setTotalSize(this.mStorageInfo.totalBytes);
                }
            }
        }
        SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray = this.mAppsResult;
        if (sparseArray != null) {
            this.mPreferenceController.onLoadFinished(sparseArray, UserHandle.myUserId());
            updateSecondaryUserControllers(this.mSecondaryUsers, this.mAppsResult);
            if (getView().findViewById(C1715R.C1718id.loading_container).getVisibility() == 0) {
                setLoading(false, true);
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mSummaryController = new StorageSummaryDonutPreferenceController(context);
        arrayList.add(this.mSummaryController);
        this.mPreferenceController = new StorageItemPreferenceController(context, this, this.mVolume, new StorageManagerVolumeProvider((StorageManager) context.getSystemService(StorageManager.class)));
        arrayList.add(this.mPreferenceController);
        this.mSecondaryUsers = SecondaryUserController.getSecondaryUserControllers(context, (UserManager) context.getSystemService(UserManager.class));
        arrayList.addAll(this.mSecondaryUsers);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void setVolume(VolumeInfo volumeInfo) {
        this.mVolume = volumeInfo;
    }

    private void updateSecondaryUserControllers(List<AbstractPreferenceController> list, SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AbstractPreferenceController abstractPreferenceController = list.get(i);
            if (abstractPreferenceController instanceof StorageAsyncLoader.ResultHandler) {
                ((StorageAsyncLoader.ResultHandler) abstractPreferenceController).handleResult(sparseArray);
            }
        }
    }

    public Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> onCreateLoader(int i, Bundle bundle) {
        Context context = getContext();
        return new StorageAsyncLoader(context, (UserManager) context.getSystemService(UserManager.class), this.mVolume.fsUuid, new StorageStatsSource(context), context.getPackageManager());
    }

    public void onLoadFinished(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader, SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        this.mAppsResult = sparseArray;
        maybeCacheFreshValues();
        onReceivedSizes();
    }

    public void setCachedStorageValuesHelper(CachedStorageValuesHelper cachedStorageValuesHelper) {
        this.mCachedStorageValuesHelper = cachedStorageValuesHelper;
    }

    public PrivateStorageInfo getPrivateStorageInfo() {
        return this.mStorageInfo;
    }

    public void setPrivateStorageInfo(PrivateStorageInfo privateStorageInfo) {
        this.mStorageInfo = privateStorageInfo;
    }

    public SparseArray<StorageAsyncLoader.AppsStorageResult> getAppsStorageResult() {
        return this.mAppsResult;
    }

    public void setAppsStorageResult(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        this.mAppsResult = sparseArray;
    }

    public void initializeCachedValues() {
        PrivateStorageInfo cachedPrivateStorageInfo = this.mCachedStorageValuesHelper.getCachedPrivateStorageInfo();
        SparseArray<StorageAsyncLoader.AppsStorageResult> cachedAppsStorageResult = this.mCachedStorageValuesHelper.getCachedAppsStorageResult();
        if (cachedPrivateStorageInfo != null && cachedAppsStorageResult != null) {
            this.mStorageInfo = cachedPrivateStorageInfo;
            this.mAppsResult = cachedAppsStorageResult;
        }
    }

    public void maybeSetLoading(boolean z) {
        if ((z && (this.mStorageInfo == null || this.mAppsResult == null)) || (!z && this.mStorageInfo == null)) {
            setLoading(true, false);
        }
    }

    private void initializeCacheProvider() {
        this.mCachedStorageValuesHelper = new CachedStorageValuesHelper(getContext(), UserHandle.myUserId());
        initializeCachedValues();
        onReceivedSizes();
    }

    /* access modifiers changed from: private */
    public void maybeCacheFreshValues() {
        SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray;
        PrivateStorageInfo privateStorageInfo = this.mStorageInfo;
        if (privateStorageInfo != null && (sparseArray = this.mAppsResult) != null) {
            this.mCachedStorageValuesHelper.cacheResult(privateStorageInfo, sparseArray.get(UserHandle.myUserId()));
        }
    }

    private boolean isQuotaSupported() {
        return ((StorageStatsManager) getActivity().getSystemService(StorageStatsManager.class)).isQuotaSupported(this.mVolume.fsUuid);
    }

    public final class IconLoaderCallbacks implements LoaderManager.LoaderCallbacks<SparseArray<Drawable>> {
        public void onLoaderReset(Loader<SparseArray<Drawable>> loader) {
        }

        public IconLoaderCallbacks() {
        }

        public Loader<SparseArray<Drawable>> onCreateLoader(int i, Bundle bundle) {
            return new UserIconLoader(StorageDashboardFragment.this.getContext(), new UserIconLoader.FetchUserIconTask() {
                public final SparseArray getUserIcons() {
                    return StorageDashboardFragment.IconLoaderCallbacks.this.mo9492x4a454274();
                }
            });
        }

        /* renamed from: lambda$onCreateLoader$0$StorageDashboardFragment$IconLoaderCallbacks */
        public /* synthetic */ SparseArray mo9492x4a454274() {
            return UserIconLoader.loadUserIconsWithContext(StorageDashboardFragment.this.getContext());
        }

        public void onLoadFinished(Loader<SparseArray<Drawable>> loader, SparseArray<Drawable> sparseArray) {
            StorageDashboardFragment.this.mSecondaryUsers.stream().filter(C0747x26de6e35.INSTANCE).forEach(new Consumer(sparseArray) {
                private final /* synthetic */ SparseArray f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj) {
                    ((UserIconLoader.UserIconHandler) ((AbstractPreferenceController) obj)).handleUserIcons(this.f$0);
                }
            });
        }

        static /* synthetic */ boolean lambda$onLoadFinished$1(AbstractPreferenceController abstractPreferenceController) {
            return abstractPreferenceController instanceof UserIconLoader.UserIconHandler;
        }
    }

    public final class VolumeSizeCallbacks implements LoaderManager.LoaderCallbacks<PrivateStorageInfo> {
        public void onLoaderReset(Loader<PrivateStorageInfo> loader) {
        }

        public VolumeSizeCallbacks() {
        }

        public Loader<PrivateStorageInfo> onCreateLoader(int i, Bundle bundle) {
            Context context = StorageDashboardFragment.this.getContext();
            return new VolumeSizesLoader(context, new StorageManagerVolumeProvider((StorageManager) context.getSystemService(StorageManager.class)), (StorageStatsManager) context.getSystemService(StorageStatsManager.class), StorageDashboardFragment.this.mVolume);
        }

        public void onLoadFinished(Loader<PrivateStorageInfo> loader, PrivateStorageInfo privateStorageInfo) {
            if (privateStorageInfo == null) {
                StorageDashboardFragment.this.getActivity().finish();
                return;
            }
            PrivateStorageInfo unused = StorageDashboardFragment.this.mStorageInfo = privateStorageInfo;
            StorageDashboardFragment.this.maybeCacheFreshValues();
            StorageDashboardFragment.this.onReceivedSizes();
        }
    }
}
