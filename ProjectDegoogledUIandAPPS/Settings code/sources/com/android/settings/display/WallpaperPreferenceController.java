package com.android.settings.display;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class WallpaperPreferenceController extends BasePreferenceController {
    private static final String TAG = "WallpaperPrefController";
    private final String mStylesAndWallpaperClass = this.mContext.getString(C1715R.string.config_styles_and_wallpaper_picker_class);
    private final String mWallpaperClass = this.mContext.getString(C1715R.string.config_wallpaper_picker_class);
    private final String mWallpaperPackage = this.mContext.getString(C1715R.string.config_wallpaper_picker_package);

    public WallpaperPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        preferenceScreen.findPreference(getPreferenceKey()).setTitle((CharSequence) getTitle());
    }

    public String getTitle() {
        return this.mContext.getString(areStylesAvailable() ? C1715R.string.style_and_wallpaper_settings_title : C1715R.string.wallpaper_settings_title);
    }

    public ComponentName getComponentName() {
        return new ComponentName(this.mWallpaperPackage, areStylesAvailable() ? this.mStylesAndWallpaperClass : this.mWallpaperClass);
    }

    public String getKeywords() {
        StringBuilder sb = new StringBuilder(this.mContext.getString(C1715R.string.keywords_wallpaper));
        if (areStylesAvailable()) {
            sb.append(", ");
            sb.append(this.mContext.getString(C1715R.string.theme_customization_category));
            sb.append(", ");
            sb.append(this.mContext.getString(C1715R.string.keywords_dark_ui_mode));
        }
        return sb.toString();
    }

    public int getAvailabilityStatus() {
        if (!TextUtils.isEmpty(this.mWallpaperPackage) && !TextUtils.isEmpty(this.mWallpaperClass)) {
            return canResolveWallpaperComponent(this.mWallpaperClass) ? 1 : 2;
        }
        Log.e(TAG, "No Wallpaper picker specified!");
        return 3;
    }

    public void updateState(Preference preference) {
        disablePreferenceIfManaged((RestrictedPreference) preference);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return super.handlePreferenceTreeClick(preference);
        }
        preference.getContext().startActivity(new Intent().setComponent(getComponentName()));
        return true;
    }

    public boolean areStylesAvailable() {
        return !TextUtils.isEmpty(this.mStylesAndWallpaperClass) && canResolveWallpaperComponent(this.mStylesAndWallpaperClass);
    }

    private boolean canResolveWallpaperComponent(String str) {
        List<ResolveInfo> queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(new Intent().setComponent(new ComponentName(this.mWallpaperPackage, str)), 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return false;
        }
        return true;
    }

    private void disablePreferenceIfManaged(RestrictedPreference restrictedPreference) {
        if (restrictedPreference != null) {
            restrictedPreference.setDisabledByAdmin((RestrictedLockUtils.EnforcedAdmin) null);
            if (RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, "no_set_wallpaper", UserHandle.myUserId())) {
                restrictedPreference.setEnabled(false);
            } else {
                restrictedPreference.checkRestrictionAndSetDisabled("no_set_wallpaper");
            }
        }
    }
}
