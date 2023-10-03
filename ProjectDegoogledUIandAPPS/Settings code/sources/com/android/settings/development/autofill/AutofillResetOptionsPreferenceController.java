package com.android.settings.development.autofill;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.config.center.C1715R;

public final class AutofillResetOptionsPreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "autofill_reset_developer_options";
    }

    public AutofillResetOptionsPreferenceController(Context context) {
        super(context);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals("autofill_reset_developer_options", preference.getKey())) {
            return false;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Settings.Global.putInt(contentResolver, "autofill_logging_level", 0);
        Settings.Global.putInt(contentResolver, "autofill_max_partitions_size", 10);
        Settings.Global.putInt(contentResolver, "autofill_max_visible_datasets", 0);
        Toast.makeText(this.mContext, C1715R.string.autofill_reset_developer_options_complete, 0).show();
        return true;
    }
}
