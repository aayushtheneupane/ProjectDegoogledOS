package com.android.settings.deviceinfo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.storage.DiskInfo;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.os.storage.VolumeRecord;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorageSettings extends SettingsPreferenceFragment implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.title = context.getString(C1715R.string.storage_settings);
            searchIndexableRaw.key = "storage_settings";
            searchIndexableRaw.screenTitle = context.getString(C1715R.string.storage_settings);
            searchIndexableRaw.keywords = context.getString(C1715R.string.keywords_storage_settings);
            arrayList.add(searchIndexableRaw);
            SearchIndexableRaw searchIndexableRaw2 = new SearchIndexableRaw(context);
            searchIndexableRaw2.title = context.getString(C1715R.string.internal_storage);
            searchIndexableRaw2.key = "storage_settings_internal_storage";
            searchIndexableRaw2.screenTitle = context.getString(C1715R.string.storage_settings);
            arrayList.add(searchIndexableRaw2);
            SearchIndexableRaw searchIndexableRaw3 = new SearchIndexableRaw(context);
            StorageManager storageManager = (StorageManager) context.getSystemService(StorageManager.class);
            for (VolumeInfo volumeInfo : storageManager.getVolumes()) {
                if (StorageSettings.isInteresting(volumeInfo)) {
                    searchIndexableRaw3.title = storageManager.getBestVolumeDescription(volumeInfo);
                    searchIndexableRaw3.key = "storage_settings_volume_" + volumeInfo.id;
                    searchIndexableRaw3.screenTitle = context.getString(C1715R.string.storage_settings);
                    arrayList.add(searchIndexableRaw3);
                }
            }
            SearchIndexableRaw searchIndexableRaw4 = new SearchIndexableRaw(context);
            searchIndexableRaw4.title = context.getString(C1715R.string.memory_size);
            searchIndexableRaw4.key = "storage_settings_memory_size";
            searchIndexableRaw4.screenTitle = context.getString(C1715R.string.storage_settings);
            arrayList.add(searchIndexableRaw4);
            SearchIndexableRaw searchIndexableRaw5 = new SearchIndexableRaw(context);
            searchIndexableRaw5.title = context.getString(C1715R.string.memory_available);
            searchIndexableRaw5.key = "storage_settings_memory_available";
            searchIndexableRaw5.screenTitle = context.getString(C1715R.string.storage_settings);
            arrayList.add(searchIndexableRaw5);
            SearchIndexableRaw searchIndexableRaw6 = new SearchIndexableRaw(context);
            searchIndexableRaw6.title = context.getString(C1715R.string.memory_apps_usage);
            searchIndexableRaw6.key = "storage_settings_apps_space";
            searchIndexableRaw6.screenTitle = context.getString(C1715R.string.storage_settings);
            arrayList.add(searchIndexableRaw6);
            SearchIndexableRaw searchIndexableRaw7 = new SearchIndexableRaw(context);
            searchIndexableRaw7.title = context.getString(C1715R.string.memory_dcim_usage);
            searchIndexableRaw7.key = "storage_settings_dcim_space";
            searchIndexableRaw7.screenTitle = context.getString(C1715R.string.storage_settings);
            arrayList.add(searchIndexableRaw7);
            SearchIndexableRaw searchIndexableRaw8 = new SearchIndexableRaw(context);
            searchIndexableRaw8.title = context.getString(C1715R.string.memory_music_usage);
            searchIndexableRaw8.key = "storage_settings_music_space";
            searchIndexableRaw8.screenTitle = context.getString(C1715R.string.storage_settings);
            arrayList.add(searchIndexableRaw8);
            SearchIndexableRaw searchIndexableRaw9 = new SearchIndexableRaw(context);
            searchIndexableRaw9.title = context.getString(C1715R.string.memory_media_misc_usage);
            searchIndexableRaw9.key = "storage_settings_misc_space";
            searchIndexableRaw9.screenTitle = context.getString(C1715R.string.storage_settings);
            arrayList.add(searchIndexableRaw9);
            SearchIndexableRaw searchIndexableRaw10 = new SearchIndexableRaw(context);
            searchIndexableRaw10.title = context.getString(C1715R.string.storage_menu_free);
            searchIndexableRaw10.key = "storage_settings_free_space";
            searchIndexableRaw10.screenTitle = context.getString(C1715R.string.storage_menu_free);
            searchIndexableRaw10.intentAction = "android.os.storage.action.MANAGE_STORAGE";
            searchIndexableRaw10.intentTargetPackage = context.getString(C1715R.string.config_deletion_helper_package);
            searchIndexableRaw10.intentTargetClass = context.getString(C1715R.string.config_deletion_helper_class);
            arrayList.add(searchIndexableRaw10);
            return arrayList;
        }
    };
    private static long sTotalInternalStorage;
    private PreferenceCategory mExternalCategory;
    private boolean mHasLaunchedPrivateVolumeSettings = false;
    private PreferenceCategory mInternalCategory;
    private StorageSummaryPreference mInternalSummary;
    private final StorageEventListener mStorageListener = new StorageEventListener() {
        public void onVolumeStateChanged(VolumeInfo volumeInfo, int i, int i2) {
            if (StorageSettings.isInteresting(volumeInfo)) {
                StorageSettings.this.refresh();
            }
        }

        public void onDiskDestroyed(DiskInfo diskInfo) {
            StorageSettings.this.refresh();
        }
    };
    private StorageManager mStorageManager;

    public int getHelpResource() {
        return C1715R.string.help_uri_storage;
    }

    public int getMetricsCategory() {
        return 42;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mStorageManager = (StorageManager) getActivity().getSystemService(StorageManager.class);
        if (sTotalInternalStorage <= 0) {
            sTotalInternalStorage = this.mStorageManager.getPrimaryStorageSize();
        }
        addPreferencesFromResource(C1715R.xml.device_info_storage);
        this.mInternalCategory = (PreferenceCategory) findPreference("storage_internal");
        this.mExternalCategory = (PreferenceCategory) findPreference("storage_external");
        this.mInternalSummary = new StorageSummaryPreference(getPrefContext());
        setHasOptionsMenu(true);
    }

    /* access modifiers changed from: private */
    public static boolean isInteresting(VolumeInfo volumeInfo) {
        int type = volumeInfo.getType();
        return type == 0 || type == 1 || type == 5;
    }

    /* access modifiers changed from: private */
    public synchronized void refresh() {
        Context prefContext = getPrefContext();
        getPreferenceScreen().removeAll();
        this.mInternalCategory.removeAll();
        this.mExternalCategory.removeAll();
        this.mInternalCategory.addPreference(this.mInternalSummary);
        PrivateStorageInfo privateStorageInfo = PrivateStorageInfo.getPrivateStorageInfo(new StorageManagerVolumeProvider(this.mStorageManager));
        long j = privateStorageInfo.totalBytes;
        long j2 = privateStorageInfo.totalBytes - privateStorageInfo.freeBytes;
        List<VolumeInfo> volumes = this.mStorageManager.getVolumes();
        Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
        for (VolumeInfo volumeInfo : volumes) {
            if (volumeInfo.getType() == 1) {
                if (volumeInfo.getState() == 6) {
                    this.mInternalCategory.addPreference(new StorageVolumePreference(prefContext, volumeInfo, 0));
                } else {
                    this.mInternalCategory.addPreference(new StorageVolumePreference(prefContext, volumeInfo, PrivateStorageInfo.getTotalSize(volumeInfo, sTotalInternalStorage)));
                }
            } else if (volumeInfo.getType() == 0 || volumeInfo.getType() == 5) {
                this.mExternalCategory.addPreference(new StorageVolumePreference(prefContext, volumeInfo, 0));
            }
        }
        for (VolumeRecord volumeRecord : this.mStorageManager.getVolumeRecords()) {
            if (volumeRecord.getType() == 1 && this.mStorageManager.findVolumeByUuid(volumeRecord.getFsUuid()) == null) {
                Preference preference = new Preference(prefContext);
                preference.setKey(volumeRecord.getFsUuid());
                preference.setTitle((CharSequence) volumeRecord.getNickname());
                preference.setSummary(17039987);
                preference.setIcon((int) C1715R.C1717drawable.ic_sim_sd);
                this.mInternalCategory.addPreference(preference);
            }
        }
        for (DiskInfo diskInfo : this.mStorageManager.getDisks()) {
            if (diskInfo.volumeCount == 0 && diskInfo.size > 0) {
                Preference preference2 = new Preference(prefContext);
                preference2.setKey(diskInfo.getId());
                preference2.setTitle((CharSequence) diskInfo.getDescription());
                preference2.setSummary(17039993);
                preference2.setIcon((int) C1715R.C1717drawable.ic_sim_sd);
                this.mExternalCategory.addPreference(preference2);
            }
        }
        Formatter.BytesResult formatBytes = Formatter.formatBytes(getResources(), j2, 0);
        this.mInternalSummary.setTitle(TextUtils.expandTemplate(getText(C1715R.string.storage_size_large), new CharSequence[]{formatBytes.value, formatBytes.units}));
        this.mInternalSummary.setSummary((CharSequence) getString(C1715R.string.storage_volume_used_total, Formatter.formatFileSize(prefContext, j)));
        if (this.mInternalCategory.getPreferenceCount() > 0) {
            getPreferenceScreen().addPreference(this.mInternalCategory);
        }
        if (this.mExternalCategory.getPreferenceCount() > 0) {
            getPreferenceScreen().addPreference(this.mExternalCategory);
        }
        if (this.mInternalCategory.getPreferenceCount() == 2 && this.mExternalCategory.getPreferenceCount() == 0 && !this.mHasLaunchedPrivateVolumeSettings) {
            this.mHasLaunchedPrivateVolumeSettings = true;
            Bundle bundle = new Bundle();
            bundle.putString("android.os.storage.extra.VOLUME_ID", "private");
            new SubSettingLauncher(getActivity()).setDestination(StorageDashboardFragment.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.storage_settings).setSourceMetricsCategory(getMetricsCategory()).launch();
            finish();
        }
    }

    public void onResume() {
        super.onResume();
        this.mStorageManager.registerListener(this.mStorageListener);
        refresh();
    }

    public void onPause() {
        super.onPause();
        this.mStorageManager.unregisterListener(this.mStorageListener);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();
        if (preference instanceof StorageVolumePreference) {
            VolumeInfo findVolumeById = this.mStorageManager.findVolumeById(key);
            if (findVolumeById == null) {
                return false;
            }
            if (findVolumeById.getState() == 0) {
                VolumeUnmountedFragment.show(this, findVolumeById.getId());
                return true;
            } else if (findVolumeById.getState() == 6) {
                DiskInitFragment.show(this, C1715R.string.storage_dialog_unmountable, findVolumeById.getDiskId());
                return true;
            } else if (findVolumeById.getType() == 1) {
                Bundle bundle = new Bundle();
                bundle.putString("android.os.storage.extra.VOLUME_ID", findVolumeById.getId());
                if ("private".equals(findVolumeById.getId())) {
                    new SubSettingLauncher(getContext()).setDestination(StorageDashboardFragment.class.getCanonicalName()).setTitleRes(C1715R.string.storage_settings).setSourceMetricsCategory(getMetricsCategory()).setArguments(bundle).launch();
                } else {
                    PrivateVolumeSettings.setVolumeSize(bundle, PrivateStorageInfo.getTotalSize(findVolumeById, sTotalInternalStorage));
                    new SubSettingLauncher(getContext()).setDestination(PrivateVolumeSettings.class.getCanonicalName()).setTitleRes(-1).setSourceMetricsCategory(getMetricsCategory()).setArguments(bundle).launch();
                }
                return true;
            } else if (findVolumeById.getType() == 0) {
                return handlePublicVolumeClick(getContext(), findVolumeById);
            } else {
                if (findVolumeById.getType() == 5) {
                    return handleStubVolumeClick(getContext(), findVolumeById);
                }
                return false;
            }
        } else if (key.startsWith("disk:")) {
            DiskInitFragment.show(this, C1715R.string.storage_dialog_unsupported, key);
            return true;
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.os.storage.extra.FS_UUID", key);
            new SubSettingLauncher(getContext()).setDestination(PrivateVolumeForget.class.getCanonicalName()).setTitleRes(C1715R.string.storage_menu_forget).setSourceMetricsCategory(getMetricsCategory()).setArguments(bundle2).launch();
            return true;
        }
    }

    static boolean handleStubVolumeClick(Context context, VolumeInfo volumeInfo) {
        Intent buildBrowseIntent = volumeInfo.buildBrowseIntent();
        if (!volumeInfo.isMountedReadable() || buildBrowseIntent == null) {
            return false;
        }
        context.startActivity(buildBrowseIntent);
        return true;
    }

    static boolean handlePublicVolumeClick(Context context, VolumeInfo volumeInfo) {
        Intent buildBrowseIntent = volumeInfo.buildBrowseIntent();
        if (!volumeInfo.isMountedReadable() || buildBrowseIntent == null) {
            Bundle bundle = new Bundle();
            bundle.putString("android.os.storage.extra.VOLUME_ID", volumeInfo.getId());
            new SubSettingLauncher(context).setDestination(PublicVolumeSettings.class.getCanonicalName()).setTitleRes(-1).setSourceMetricsCategory(42).setArguments(bundle).launch();
            return true;
        }
        context.startActivity(buildBrowseIntent);
        return true;
    }

    public static class MountTask extends AsyncTask<Void, Void, Exception> {
        private final Context mContext;
        private final String mDescription;
        private final StorageManager mStorageManager = ((StorageManager) this.mContext.getSystemService(StorageManager.class));
        private final String mVolumeId;

        public MountTask(Context context, VolumeInfo volumeInfo) {
            this.mContext = context.getApplicationContext();
            this.mVolumeId = volumeInfo.getId();
            this.mDescription = this.mStorageManager.getBestVolumeDescription(volumeInfo);
        }

        /* access modifiers changed from: protected */
        public Exception doInBackground(Void... voidArr) {
            try {
                this.mStorageManager.mount(this.mVolumeId);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Exception exc) {
            if (exc == null) {
                Context context = this.mContext;
                Toast.makeText(context, context.getString(C1715R.string.storage_mount_success, new Object[]{this.mDescription}), 0).show();
                return;
            }
            Log.e("StorageSettings", "Failed to mount " + this.mVolumeId, exc);
            Context context2 = this.mContext;
            Toast.makeText(context2, context2.getString(C1715R.string.storage_mount_failure, new Object[]{this.mDescription}), 0).show();
        }
    }

    public static class UnmountTask extends AsyncTask<Void, Void, Exception> {
        private final Context mContext;
        private final String mDescription;
        private final StorageManager mStorageManager = ((StorageManager) this.mContext.getSystemService(StorageManager.class));
        private final String mVolumeId;

        public UnmountTask(Context context, VolumeInfo volumeInfo) {
            this.mContext = context.getApplicationContext();
            this.mVolumeId = volumeInfo.getId();
            this.mDescription = this.mStorageManager.getBestVolumeDescription(volumeInfo);
        }

        /* access modifiers changed from: protected */
        public Exception doInBackground(Void... voidArr) {
            try {
                this.mStorageManager.unmount(this.mVolumeId);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Exception exc) {
            if (exc == null) {
                Context context = this.mContext;
                Toast.makeText(context, context.getString(C1715R.string.storage_unmount_success, new Object[]{this.mDescription}), 0).show();
                return;
            }
            Log.e("StorageSettings", "Failed to unmount " + this.mVolumeId, exc);
            Context context2 = this.mContext;
            Toast.makeText(context2, context2.getString(C1715R.string.storage_unmount_failure, new Object[]{this.mDescription}), 0).show();
        }
    }

    public static class VolumeUnmountedFragment extends InstrumentedDialogFragment {
        public int getMetricsCategory() {
            return 562;
        }

        public static void show(Fragment fragment, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("android.os.storage.extra.VOLUME_ID", str);
            VolumeUnmountedFragment volumeUnmountedFragment = new VolumeUnmountedFragment();
            volumeUnmountedFragment.setArguments(bundle);
            volumeUnmountedFragment.setTargetFragment(fragment, 0);
            volumeUnmountedFragment.show(fragment.getFragmentManager(), "volume_unmounted");
        }

        public Dialog onCreateDialog(Bundle bundle) {
            final FragmentActivity activity = getActivity();
            final VolumeInfo findVolumeById = ((StorageManager) activity.getSystemService(StorageManager.class)).findVolumeById(getArguments().getString("android.os.storage.extra.VOLUME_ID"));
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(TextUtils.expandTemplate(getText(C1715R.string.storage_dialog_unmounted), new CharSequence[]{findVolumeById.getDisk().getDescription()}));
            builder.setPositiveButton((int) C1715R.string.storage_menu_mount, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                private boolean wasAdminSupportIntentShown(String str) {
                    RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(VolumeUnmountedFragment.this.getActivity(), str, UserHandle.myUserId());
                    boolean hasBaseUserRestriction = RestrictedLockUtilsInternal.hasBaseUserRestriction(VolumeUnmountedFragment.this.getActivity(), str, UserHandle.myUserId());
                    if (checkIfRestrictionEnforced == null || hasBaseUserRestriction) {
                        return false;
                    }
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(VolumeUnmountedFragment.this.getActivity(), checkIfRestrictionEnforced);
                    return true;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!wasAdminSupportIntentShown("no_physical_media")) {
                        DiskInfo diskInfo = findVolumeById.disk;
                        if (diskInfo == null || !diskInfo.isUsb() || !wasAdminSupportIntentShown("no_usb_file_transfer")) {
                            new MountTask(activity, findVolumeById).execute(new Void[0]);
                        }
                    }
                }
            });
            builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }

    public static class DiskInitFragment extends InstrumentedDialogFragment {
        public int getMetricsCategory() {
            return 561;
        }

        public static void show(Fragment fragment, int i, String str) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.intent.extra.TEXT", i);
            bundle.putString("android.os.storage.extra.DISK_ID", str);
            DiskInitFragment diskInitFragment = new DiskInitFragment();
            diskInitFragment.setArguments(bundle);
            diskInitFragment.setTargetFragment(fragment, 0);
            diskInitFragment.show(fragment.getFragmentManager(), "disk_init");
        }

        public Dialog onCreateDialog(Bundle bundle) {
            final FragmentActivity activity = getActivity();
            int i = getArguments().getInt("android.intent.extra.TEXT");
            final String string = getArguments().getString("android.os.storage.extra.DISK_ID");
            DiskInfo findDiskById = ((StorageManager) activity.getSystemService(StorageManager.class)).findDiskById(string);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(TextUtils.expandTemplate(getText(i), new CharSequence[]{findDiskById.getDescription()}));
            builder.setPositiveButton((int) C1715R.string.storage_menu_set_up, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(activity, StorageWizardInit.class);
                    intent.putExtra("android.os.storage.extra.DISK_ID", string);
                    DiskInitFragment.this.startActivity(intent);
                }
            });
            builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }
}
