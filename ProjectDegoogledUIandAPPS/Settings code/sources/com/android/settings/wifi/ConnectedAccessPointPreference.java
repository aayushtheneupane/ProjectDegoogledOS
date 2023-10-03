package com.android.settings.wifi;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.havoc.config.center.C1715R;

public class ConnectedAccessPointPreference extends LongPressAccessPointPreference implements View.OnClickListener {
    private boolean mIsCaptivePortal;
    private OnGearClickListener mOnGearClickListener;

    public interface OnGearClickListener {
        void onGearClick(ConnectedAccessPointPreference connectedAccessPointPreference);
    }

    /* access modifiers changed from: protected */
    public int getWidgetLayoutResourceId() {
        return C1715R.layout.preference_widget_gear_optional_background;
    }

    public ConnectedAccessPointPreference(AccessPoint accessPoint, Context context, AccessPointPreference.UserBadgeCache userBadgeCache, int i, boolean z, Fragment fragment) {
        super(accessPoint, context, userBadgeCache, z, i, fragment);
    }

    public void refresh() {
        super.refresh();
        setShowDivider(this.mIsCaptivePortal);
        if (this.mIsCaptivePortal) {
            setSummary((int) C1715R.string.wifi_tap_to_sign_in);
        }
    }

    public void setOnGearClickListener(OnGearClickListener onGearClickListener) {
        this.mOnGearClickListener = onGearClickListener;
        notifyChanged();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.settings_button);
        findViewById.setOnClickListener(this);
        int i = 4;
        preferenceViewHolder.findViewById(C1715R.C1718id.settings_button_no_background).setVisibility(this.mIsCaptivePortal ? 4 : 0);
        if (this.mIsCaptivePortal) {
            i = 0;
        }
        findViewById.setVisibility(i);
    }

    public void onClick(View view) {
        OnGearClickListener onGearClickListener;
        if (view.getId() == C1715R.C1718id.settings_button && (onGearClickListener = this.mOnGearClickListener) != null) {
            onGearClickListener.onGearClick(this);
        }
    }

    public void setCaptivePortal(boolean z) {
        if (this.mIsCaptivePortal != z) {
            this.mIsCaptivePortal = z;
            refresh();
        }
    }
}
