package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

public class MainlineModuleVersionPreferenceController extends BasePreferenceController {
    static final Intent MODULE_UPDATE_INTENT = new Intent("android.settings.MODULE_UPDATE_SETTINGS");
    private static final String TAG = "MainlineModuleControl";
    private static final List<String> VERSION_NAME_DATE_PATTERNS = Arrays.asList(new String[]{"yyyy-MM-dd", "yyyy-MM"});
    private String mModuleVersion;
    private final PackageManager mPackageManager = this.mContext.getPackageManager();

    public int getAvailabilityStatus() {
        return 3;
    }

    public MainlineModuleVersionPreferenceController(Context context, String str) {
        super(context, str);
        initModules();
    }

    private void initModules() {
        String string = this.mContext.getString(17039747);
        if (!TextUtils.isEmpty(string)) {
            try {
                this.mModuleVersion = this.mPackageManager.getPackageInfo(string, 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Failed to get mainline version.", e);
                this.mModuleVersion = null;
            }
        }
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mPackageManager.resolveActivity(MODULE_UPDATE_INTENT, 0) != null) {
            preference.setIntent(MODULE_UPDATE_INTENT);
        } else {
            preference.setIntent((Intent) null);
        }
    }

    public CharSequence getSummary() {
        if (TextUtils.isEmpty(this.mModuleVersion)) {
            return this.mModuleVersion;
        }
        Optional<Date> parseDateFromVersionName = parseDateFromVersionName(this.mModuleVersion);
        if (parseDateFromVersionName.isPresent()) {
            return DateFormat.getLongDateFormat(this.mContext).format(parseDateFromVersionName.get());
        }
        Log.w("Could not parse mainline versionName (%s) as date.", this.mModuleVersion);
        return this.mModuleVersion;
    }

    private Optional<Date> parseDateFromVersionName(String str) {
        for (String simpleDateFormat : VERSION_NAME_DATE_PATTERNS) {
            try {
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(simpleDateFormat, Locale.getDefault());
                simpleDateFormat2.setTimeZone(TimeZone.getDefault());
                return Optional.of(simpleDateFormat2.parse(str));
            } catch (ParseException unused) {
            }
        }
        return Optional.empty();
    }
}
