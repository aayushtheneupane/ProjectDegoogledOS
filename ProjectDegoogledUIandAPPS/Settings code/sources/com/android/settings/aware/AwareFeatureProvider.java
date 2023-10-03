package com.android.settings.aware;

import androidx.fragment.app.Fragment;

public interface AwareFeatureProvider {
    void showRestrictionDialog(Fragment fragment);
}
