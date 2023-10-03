package com.android.settings.accounts;

import android.util.Log;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;

public class AccountPreference extends Preference {
    private boolean mShowTypeIcon;
    private int mStatus;
    private ImageView mSyncStatusIcon;

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (!this.mShowTypeIcon) {
            this.mSyncStatusIcon = (ImageView) preferenceViewHolder.findViewById(16908294);
            this.mSyncStatusIcon.setImageResource(getSyncStatusIcon(this.mStatus));
            this.mSyncStatusIcon.setContentDescription(getSyncContentDescription(this.mStatus));
        }
    }

    private int getSyncStatusIcon(int i) {
        if (i != 0) {
            if (i == 1) {
                return C1715R.C1717drawable.ic_sync_grey_holo;
            }
            if (i == 2) {
                return C1715R.C1717drawable.ic_sync_red_holo;
            }
            if (i != 3) {
                Log.e("AccountPreference", "Unknown sync status: " + i);
                return C1715R.C1717drawable.ic_sync_red_holo;
            }
        }
        return C1715R.C1717drawable.ic_settings_sync;
    }

    private String getSyncContentDescription(int i) {
        if (i == 0) {
            return getContext().getString(C1715R.string.accessibility_sync_enabled);
        }
        if (i == 1) {
            return getContext().getString(C1715R.string.accessibility_sync_disabled);
        }
        if (i == 2) {
            return getContext().getString(C1715R.string.accessibility_sync_error);
        }
        if (i == 3) {
            return getContext().getString(C1715R.string.accessibility_sync_in_progress);
        }
        Log.e("AccountPreference", "Unknown sync status: " + i);
        return getContext().getString(C1715R.string.accessibility_sync_error);
    }
}
