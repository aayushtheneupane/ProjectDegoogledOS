package com.android.settings.widget;

import android.content.Context;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class AppSwitchPreference extends SwitchPreference {
    public AppSwitchPreference(Context context) {
        super(context);
        setLayoutResource(C1715R.layout.preference_app);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(16908352);
        if (findViewById != null) {
            findViewById.getRootView().setFilterTouchesWhenObscured(true);
        }
    }
}
