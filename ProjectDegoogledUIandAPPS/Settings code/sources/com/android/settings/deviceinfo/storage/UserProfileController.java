package com.android.settings.deviceinfo.storage;

import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.Preconditions;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.deviceinfo.StorageItemPreference;
import com.android.settings.deviceinfo.StorageProfileFragment;
import com.android.settings.deviceinfo.storage.StorageAsyncLoader;
import com.android.settings.deviceinfo.storage.UserIconLoader;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;

public class UserProfileController extends AbstractPreferenceController implements PreferenceControllerMixin, StorageAsyncLoader.ResultHandler, UserIconLoader.UserIconHandler {
    private final int mPreferenceOrder;
    private StorageItemPreference mStoragePreference;
    private long mTotalSizeBytes;
    private UserInfo mUser;

    public boolean isAvailable() {
        return true;
    }

    public UserProfileController(Context context, UserInfo userInfo, int i) {
        super(context);
        this.mUser = (UserInfo) Preconditions.checkNotNull(userInfo);
        this.mPreferenceOrder = i;
    }

    public String getPreferenceKey() {
        return "pref_profile_" + this.mUser.id;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mStoragePreference = new StorageItemPreference(preferenceScreen.getContext());
        this.mStoragePreference.setOrder(this.mPreferenceOrder);
        StorageItemPreference storageItemPreference = this.mStoragePreference;
        storageItemPreference.setKey("pref_profile_" + this.mUser.id);
        this.mStoragePreference.setTitle((CharSequence) this.mUser.name);
        preferenceScreen.addPreference(this.mStoragePreference);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (preference == null || this.mStoragePreference != preference) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("userId", this.mUser.id);
        bundle.putString("android.os.storage.extra.VOLUME_ID", "private");
        new SubSettingLauncher(this.mContext).setDestination(StorageProfileFragment.class.getName()).setArguments(bundle).setTitleText(this.mUser.name).setSourceMetricsCategory(42).launch();
        return true;
    }

    public void handleResult(SparseArray<StorageAsyncLoader.AppsStorageResult> sparseArray) {
        Preconditions.checkNotNull(sparseArray);
        StorageAsyncLoader.AppsStorageResult appsStorageResult = sparseArray.get(this.mUser.id);
        if (appsStorageResult != null) {
            setSize(appsStorageResult.externalStats.totalBytes + appsStorageResult.otherAppsSize + appsStorageResult.videoAppsSize + appsStorageResult.musicAppsSize + appsStorageResult.gamesSize, this.mTotalSizeBytes);
        }
    }

    public void setSize(long j, long j2) {
        StorageItemPreference storageItemPreference = this.mStoragePreference;
        if (storageItemPreference != null) {
            storageItemPreference.setStorageSize(j, j2);
        }
    }

    public void handleUserIcons(SparseArray<Drawable> sparseArray) {
        Drawable drawable = sparseArray.get(this.mUser.id);
        if (drawable != null) {
            this.mStoragePreference.setIcon(applyTint(this.mContext, drawable));
        }
    }

    private static Drawable applyTint(Context context, Drawable drawable) {
        Drawable mutate = drawable.mutate();
        mutate.setTintList(Utils.getColorAttr(context, 16843817));
        return mutate;
    }
}
