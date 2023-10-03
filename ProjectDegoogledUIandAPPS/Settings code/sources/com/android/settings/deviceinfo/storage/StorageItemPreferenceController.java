package com.android.settings.deviceinfo.storage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.storage.VolumeInfo;
import android.util.Log;
import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.Settings;
import com.android.settings.applications.manageapplications.ManageApplications;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.deviceinfo.StorageItemPreference;
import com.android.settings.deviceinfo.storage.StorageAsyncLoader;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.deviceinfo.StorageVolumeProvider;
import com.havoc.config.center.C1715R;

public class StorageItemPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    static final String AUDIO_KEY = "pref_music_audio";
    static final String FILES_KEY = "pref_files";
    static final String GAME_KEY = "pref_games";
    static final String MOVIES_KEY = "pref_movies";
    static final String OTHER_APPS_KEY = "pref_other_apps";
    static final String PHOTO_KEY = "pref_photos_videos";
    static final String SYSTEM_KEY = "pref_system";
    private StorageItemPreference mAppPreference;
    private StorageItemPreference mAudioPreference;
    private StorageItemPreference mFilePreference;
    private final Fragment mFragment;
    private StorageItemPreference mGamePreference;
    private boolean mIsWorkProfile;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private StorageItemPreference mMoviesPreference;
    private StorageItemPreference mPhotoPreference;
    private PreferenceScreen mScreen;
    private final StorageVolumeProvider mSvp;
    private StorageItemPreference mSystemPreference;
    private long mTotalSize;
    private long mUsedBytes;
    private int mUserId;
    private VolumeInfo mVolume;

    public String getPreferenceKey() {
        return null;
    }

    public boolean isAvailable() {
        return true;
    }

    public StorageItemPreferenceController(Context context, Fragment fragment, VolumeInfo volumeInfo, StorageVolumeProvider storageVolumeProvider) {
        super(context);
        this.mFragment = fragment;
        this.mVolume = volumeInfo;
        this.mSvp = storageVolumeProvider;
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        this.mUserId = UserHandle.myUserId();
    }

    public StorageItemPreferenceController(Context context, Fragment fragment, VolumeInfo volumeInfo, StorageVolumeProvider storageVolumeProvider, boolean z) {
        this(context, fragment, volumeInfo, storageVolumeProvider);
        this.mIsWorkProfile = z;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handlePreferenceTreeClick(androidx.preference.Preference r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            java.lang.String r2 = r7.getKey()
            if (r2 != 0) goto L_0x000c
            return r0
        L_0x000c:
            java.lang.String r2 = r7.getKey()
            r3 = -1
            int r4 = r2.hashCode()
            r5 = 1
            switch(r4) {
                case -1642571429: goto L_0x0056;
                case -1641885275: goto L_0x004c;
                case -1488779334: goto L_0x0042;
                case 283435296: goto L_0x0038;
                case 826139871: goto L_0x002e;
                case 1007071179: goto L_0x0024;
                case 1161100765: goto L_0x001a;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x0060
        L_0x001a:
            java.lang.String r4 = "pref_other_apps"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0060
            r2 = 4
            goto L_0x0061
        L_0x0024:
            java.lang.String r4 = "pref_system"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0060
            r2 = 6
            goto L_0x0061
        L_0x002e:
            java.lang.String r4 = "pref_movies"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0060
            r2 = 3
            goto L_0x0061
        L_0x0038:
            java.lang.String r4 = "pref_music_audio"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0060
            r2 = r5
            goto L_0x0061
        L_0x0042:
            java.lang.String r4 = "pref_photos_videos"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0060
            r2 = r0
            goto L_0x0061
        L_0x004c:
            java.lang.String r4 = "pref_games"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0060
            r2 = 2
            goto L_0x0061
        L_0x0056:
            java.lang.String r4 = "pref_files"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0060
            r2 = 5
            goto L_0x0061
        L_0x0060:
            r2 = r3
        L_0x0061:
            switch(r2) {
                case 0: goto L_0x00ac;
                case 1: goto L_0x00a7;
                case 2: goto L_0x00a2;
                case 3: goto L_0x009d;
                case 4: goto L_0x0093;
                case 5: goto L_0x007b;
                case 6: goto L_0x0065;
                default: goto L_0x0064;
            }
        L_0x0064:
            goto L_0x00b0
        L_0x0065:
            com.android.settings.deviceinfo.PrivateVolumeSettings$SystemInfoFragment r7 = new com.android.settings.deviceinfo.PrivateVolumeSettings$SystemInfoFragment
            r7.<init>()
            androidx.fragment.app.Fragment r1 = r6.mFragment
            r7.setTargetFragment(r1, r0)
            androidx.fragment.app.Fragment r6 = r6.mFragment
            androidx.fragment.app.FragmentManager r6 = r6.getFragmentManager()
            java.lang.String r0 = "SystemInfo"
            r7.show(r6, r0)
            return r5
        L_0x007b:
            android.content.Intent r1 = r6.getFilesIntent()
            android.content.Context r2 = r6.mContext
            com.android.settings.overlay.FeatureFactory r2 = com.android.settings.overlay.FeatureFactory.getFactory(r2)
            com.android.settingslib.core.instrumentation.MetricsFeatureProvider r2 = r2.getMetricsFeatureProvider()
            android.content.Context r3 = r6.mContext
            r4 = 841(0x349, float:1.178E-42)
            android.util.Pair[] r0 = new android.util.Pair[r0]
            r2.action((android.content.Context) r3, (int) r4, (android.util.Pair<java.lang.Integer, java.lang.Object>[]) r0)
            goto L_0x00b0
        L_0x0093:
            android.os.storage.VolumeInfo r0 = r6.mVolume
            if (r0 != 0) goto L_0x0098
            goto L_0x00b0
        L_0x0098:
            android.content.Intent r1 = r6.getAppsIntent()
            goto L_0x00b0
        L_0x009d:
            android.content.Intent r1 = r6.getMoviesIntent()
            goto L_0x00b0
        L_0x00a2:
            android.content.Intent r1 = r6.getGamesIntent()
            goto L_0x00b0
        L_0x00a7:
            android.content.Intent r1 = r6.getAudioIntent()
            goto L_0x00b0
        L_0x00ac:
            android.content.Intent r1 = r6.getPhotosIntent()
        L_0x00b0:
            if (r1 == 0) goto L_0x00bd
            int r7 = r6.mUserId
            java.lang.String r0 = "android.intent.extra.USER_ID"
            r1.putExtra(r0, r7)
            r6.launchIntent(r1)
            return r5
        L_0x00bd:
            boolean r6 = super.handlePreferenceTreeClick(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.deviceinfo.storage.StorageItemPreferenceController.handlePreferenceTreeClick(androidx.preference.Preference):boolean");
    }

    public void setVolume(VolumeInfo volumeInfo) {
        this.mVolume = volumeInfo;
        setFilesPreferenceVisibility();
    }

    private void setFilesPreferenceVisibility() {
        if (this.mScreen != null) {
            VolumeInfo findEmulatedForPrivate = this.mSvp.findEmulatedForPrivate(this.mVolume);
            if (findEmulatedForPrivate == null || !findEmulatedForPrivate.isMountedReadable()) {
                this.mScreen.removePreference(this.mFilePreference);
            } else {
                this.mScreen.addPreference(this.mFilePreference);
            }
        }
    }

    public void setUserId(UserHandle userHandle) {
        this.mUserId = userHandle.getIdentifier();
        tintPreference(this.mPhotoPreference);
        tintPreference(this.mMoviesPreference);
        tintPreference(this.mAudioPreference);
        tintPreference(this.mGamePreference);
        tintPreference(this.mAppPreference);
        tintPreference(this.mSystemPreference);
        tintPreference(this.mFilePreference);
    }

    private void tintPreference(Preference preference) {
        if (preference != null) {
            preference.setIcon(applyTint(this.mContext, preference.getIcon()));
        }
    }

    private static Drawable applyTint(Context context, Drawable drawable) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{16843817});
        Drawable mutate = drawable.mutate();
        mutate.setTint(obtainStyledAttributes.getColor(0, 0));
        obtainStyledAttributes.recycle();
        return mutate;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mScreen = preferenceScreen;
        this.mPhotoPreference = (StorageItemPreference) preferenceScreen.findPreference(PHOTO_KEY);
        this.mAudioPreference = (StorageItemPreference) preferenceScreen.findPreference(AUDIO_KEY);
        this.mGamePreference = (StorageItemPreference) preferenceScreen.findPreference(GAME_KEY);
        this.mMoviesPreference = (StorageItemPreference) preferenceScreen.findPreference(MOVIES_KEY);
        this.mAppPreference = (StorageItemPreference) preferenceScreen.findPreference(OTHER_APPS_KEY);
        this.mSystemPreference = (StorageItemPreference) preferenceScreen.findPreference(SYSTEM_KEY);
        this.mFilePreference = (StorageItemPreference) preferenceScreen.findPreference(FILES_KEY);
        setFilesPreferenceVisibility();
    }

    public void onLoadFinished(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray, int i) {
        StorageAsyncLoader.AppsStorageResult appsStorageResult = sparseArray.get(i);
        StorageItemPreference storageItemPreference = this.mPhotoPreference;
        long j = appsStorageResult.photosAppsSize;
        StorageStatsSource.ExternalStorageStats externalStorageStats = appsStorageResult.externalStats;
        storageItemPreference.setStorageSize(j + externalStorageStats.imageBytes + externalStorageStats.videoBytes, this.mTotalSize);
        this.mAudioPreference.setStorageSize(appsStorageResult.musicAppsSize + appsStorageResult.externalStats.audioBytes, this.mTotalSize);
        this.mGamePreference.setStorageSize(appsStorageResult.gamesSize, this.mTotalSize);
        this.mMoviesPreference.setStorageSize(appsStorageResult.videoAppsSize, this.mTotalSize);
        this.mAppPreference.setStorageSize(appsStorageResult.otherAppsSize, this.mTotalSize);
        StorageStatsSource.ExternalStorageStats externalStorageStats2 = appsStorageResult.externalStats;
        this.mFilePreference.setStorageSize((((externalStorageStats2.totalBytes - externalStorageStats2.audioBytes) - externalStorageStats2.videoBytes) - externalStorageStats2.imageBytes) - externalStorageStats2.appBytes, this.mTotalSize);
        if (this.mSystemPreference != null) {
            long j2 = 0;
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                StorageAsyncLoader.AppsStorageResult valueAt = sparseArray.valueAt(i2);
                StorageStatsSource.ExternalStorageStats externalStorageStats3 = valueAt.externalStats;
                j2 = j2 + valueAt.gamesSize + valueAt.musicAppsSize + valueAt.videoAppsSize + valueAt.photosAppsSize + valueAt.otherAppsSize + (externalStorageStats3.totalBytes - externalStorageStats3.appBytes);
            }
            this.mSystemPreference.setStorageSize(Math.max(1073741824, this.mUsedBytes - j2), this.mTotalSize);
        }
    }

    public void setUsedSize(long j) {
        this.mUsedBytes = j;
    }

    public void setTotalSize(long j) {
        this.mTotalSize = j;
    }

    private Intent getPhotosIntent() {
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(2);
        workAnnotatedBundle.putString("classname", Settings.PhotosStorageActivity.class.getName());
        workAnnotatedBundle.putInt("storageType", 3);
        return new SubSettingLauncher(this.mContext).setDestination(ManageApplications.class.getName()).setTitleRes(C1715R.string.storage_photos_videos).setArguments(workAnnotatedBundle).setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment)).toIntent();
    }

    private Intent getAudioIntent() {
        if (this.mVolume == null) {
            return null;
        }
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(4);
        workAnnotatedBundle.putString("classname", Settings.StorageUseActivity.class.getName());
        workAnnotatedBundle.putString("volumeUuid", this.mVolume.getFsUuid());
        workAnnotatedBundle.putString("volumeName", this.mVolume.getDescription());
        workAnnotatedBundle.putInt("storageType", 1);
        return new SubSettingLauncher(this.mContext).setDestination(ManageApplications.class.getName()).setTitleRes(C1715R.string.storage_music_audio).setArguments(workAnnotatedBundle).setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment)).toIntent();
    }

    private Intent getAppsIntent() {
        if (this.mVolume == null) {
            return null;
        }
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(3);
        workAnnotatedBundle.putString("classname", Settings.StorageUseActivity.class.getName());
        workAnnotatedBundle.putString("volumeUuid", this.mVolume.getFsUuid());
        workAnnotatedBundle.putString("volumeName", this.mVolume.getDescription());
        return new SubSettingLauncher(this.mContext).setDestination(ManageApplications.class.getName()).setTitleRes(C1715R.string.apps_storage).setArguments(workAnnotatedBundle).setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment)).toIntent();
    }

    private Intent getGamesIntent() {
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(1);
        workAnnotatedBundle.putString("classname", Settings.GamesStorageActivity.class.getName());
        return new SubSettingLauncher(this.mContext).setDestination(ManageApplications.class.getName()).setTitleRes(C1715R.string.game_storage_settings).setArguments(workAnnotatedBundle).setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment)).toIntent();
    }

    private Intent getMoviesIntent() {
        Bundle workAnnotatedBundle = getWorkAnnotatedBundle(1);
        workAnnotatedBundle.putString("classname", Settings.MoviesStorageActivity.class.getName());
        return new SubSettingLauncher(this.mContext).setDestination(ManageApplications.class.getName()).setTitleRes(C1715R.string.storage_movies_tv).setArguments(workAnnotatedBundle).setSourceMetricsCategory(this.mMetricsFeatureProvider.getMetricsCategory(this.mFragment)).toIntent();
    }

    private Bundle getWorkAnnotatedBundle(int i) {
        Bundle bundle = new Bundle(i + 2);
        bundle.putBoolean("workProfileOnly", this.mIsWorkProfile);
        bundle.putInt("workId", this.mUserId);
        return bundle;
    }

    private Intent getFilesIntent() {
        return this.mSvp.findEmulatedForPrivate(this.mVolume).buildBrowseIntent();
    }

    private void launchIntent(Intent intent) {
        try {
            int intExtra = intent.getIntExtra("android.intent.extra.USER_ID", -1);
            if (intExtra == -1) {
                this.mFragment.startActivityForResult(intent, 0);
            } else {
                this.mFragment.getActivity().startActivityForResultAsUser(intent, 0, new UserHandle(intExtra));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("StorageItemPreference", "No activity found for " + intent);
        }
    }
}
