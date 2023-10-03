package com.android.settings.deviceinfo;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.SparseArray;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.settings.Utils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.deviceinfo.storage.StorageAsyncLoader;
import com.android.settings.deviceinfo.storage.StorageItemPreferenceController;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class StorageProfileFragment extends DashboardFragment implements LoaderManager.LoaderCallbacks<SparseArray<StorageAsyncLoader.AppsStorageResult>> {
    private StorageItemPreferenceController mPreferenceController;
    private int mUserId;
    private VolumeInfo mVolume;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "StorageProfileFragment";
    }

    public int getMetricsCategory() {
        return 845;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.storage_profile_fragment;
    }

    public void onLoaderReset(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mVolume = Utils.maybeInitializeVolume((StorageManager) getActivity().getSystemService(StorageManager.class), arguments);
        VolumeInfo volumeInfo = this.mVolume;
        if (volumeInfo == null) {
            getActivity().finish();
            return;
        }
        this.mPreferenceController.setVolume(volumeInfo);
        this.mUserId = arguments.getInt("userId", UserHandle.myUserId());
        this.mPreferenceController.setUserId(UserHandle.of(this.mUserId));
    }

    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(0, Bundle.EMPTY, this);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mPreferenceController = new StorageItemPreferenceController(context, this, this.mVolume, new StorageManagerVolumeProvider((StorageManager) context.getSystemService(StorageManager.class)), true);
        arrayList.add(this.mPreferenceController);
        return arrayList;
    }

    public Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> onCreateLoader(int i, Bundle bundle) {
        Context context = getContext();
        return new StorageAsyncLoader(context, (UserManager) context.getSystemService(UserManager.class), this.mVolume.fsUuid, new StorageStatsSource(context), context.getPackageManager());
    }

    public void onLoadFinished(Loader<SparseArray<StorageAsyncLoader.AppsStorageResult>> loader, SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        this.mPreferenceController.onLoadFinished(sparseArray, this.mUserId);
    }

    /* access modifiers changed from: package-private */
    public void setPreferenceController(StorageItemPreferenceController storageItemPreferenceController) {
        this.mPreferenceController = storageItemPreferenceController;
    }
}
