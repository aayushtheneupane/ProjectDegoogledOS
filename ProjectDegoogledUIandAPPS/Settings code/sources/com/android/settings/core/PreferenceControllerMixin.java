package com.android.settings.core;

import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

public interface PreferenceControllerMixin {
    void updateNonIndexableKeys(List<String> list) {
        if (this instanceof AbstractPreferenceController) {
            AbstractPreferenceController abstractPreferenceController = (AbstractPreferenceController) this;
            if (!abstractPreferenceController.isAvailable()) {
                String preferenceKey = abstractPreferenceController.getPreferenceKey();
                if (TextUtils.isEmpty(preferenceKey)) {
                    Log.w("PrefControllerMixin", "Skipping updateNonIndexableKeys due to empty key " + toString());
                } else if (list.contains(preferenceKey)) {
                    Log.w("PrefControllerMixin", "Skipping updateNonIndexableKeys, key already in list. " + toString());
                } else {
                    list.add(preferenceKey);
                }
            }
        }
    }
}
