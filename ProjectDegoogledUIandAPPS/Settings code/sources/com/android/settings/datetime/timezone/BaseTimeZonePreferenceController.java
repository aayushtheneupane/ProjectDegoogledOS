package com.android.settings.datetime.timezone;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.google.common.base.Objects;

public abstract class BaseTimeZonePreferenceController extends BasePreferenceController {
    private OnPreferenceClickListener mOnClickListener;

    public int getAvailabilityStatus() {
        return 0;
    }

    protected BaseTimeZonePreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (this.mOnClickListener == null || !Objects.equal(getPreferenceKey(), preference.getKey())) {
            return false;
        }
        this.mOnClickListener.onClick();
        return true;
    }

    public void setOnClickListener(OnPreferenceClickListener onPreferenceClickListener) {
        this.mOnClickListener = onPreferenceClickListener;
    }
}
