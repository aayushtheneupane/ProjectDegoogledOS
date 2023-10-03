package com.android.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;

public class ProgressCategory extends ProgressCategoryBase {
    private int mEmptyTextRes;
    private boolean mNoDeviceFoundAdded;
    private Preference mNoDeviceFoundPreference;
    private boolean mProgress = false;

    public ProgressCategory(Context context) {
        super(context);
        setLayoutResource(C1715R.layout.preference_progress_category);
    }

    public ProgressCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(C1715R.layout.preference_progress_category);
    }

    public ProgressCategory(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutResource(C1715R.layout.preference_progress_category);
    }

    public ProgressCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setLayoutResource(C1715R.layout.preference_progress_category);
    }

    public void setEmptyTextRes(int i) {
        this.mEmptyTextRes = i;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.scanning_progress);
        boolean z = getPreferenceCount() == 0 || (getPreferenceCount() == 1 && getPreference(0) == this.mNoDeviceFoundPreference);
        findViewById.setVisibility(this.mProgress ? 0 : 8);
        if (this.mProgress || !z) {
            if (this.mNoDeviceFoundAdded) {
                removePreference(this.mNoDeviceFoundPreference);
                this.mNoDeviceFoundAdded = false;
            }
        } else if (!this.mNoDeviceFoundAdded) {
            if (this.mNoDeviceFoundPreference == null) {
                this.mNoDeviceFoundPreference = new Preference(getContext());
                this.mNoDeviceFoundPreference.setLayoutResource(C1715R.layout.preference_empty_list);
                this.mNoDeviceFoundPreference.setTitle(this.mEmptyTextRes);
                this.mNoDeviceFoundPreference.setSelectable(false);
            }
            addPreference(this.mNoDeviceFoundPreference);
            this.mNoDeviceFoundAdded = true;
        }
    }

    public void setProgress(boolean z) {
        this.mProgress = z;
        notifyChanged();
    }
}
