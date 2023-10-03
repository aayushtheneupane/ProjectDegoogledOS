package com.android.settings.deviceinfo;

import android.content.Context;
import android.util.MathUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;

public class StorageSummaryPreference extends Preference {
    private int mPercent = -1;

    public StorageSummaryPreference(Context context) {
        super(context);
        setLayoutResource(C1715R.layout.storage_summary);
        setEnabled(false);
    }

    public void setPercent(long j, long j2) {
        this.mPercent = MathUtils.constrain((int) ((100 * j) / j2), j > 0 ? 1 : 0, 100);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        ProgressBar progressBar = (ProgressBar) preferenceViewHolder.findViewById(16908301);
        if (this.mPercent != -1) {
            progressBar.setVisibility(0);
            progressBar.setProgress(this.mPercent);
            progressBar.setScaleY(7.0f);
        } else {
            progressBar.setVisibility(8);
        }
        ((TextView) preferenceViewHolder.findViewById(16908304)).setTextColor(Utils.getColorAttrDefaultColor(getContext(), 16842808));
        super.onBindViewHolder(preferenceViewHolder);
    }
}
