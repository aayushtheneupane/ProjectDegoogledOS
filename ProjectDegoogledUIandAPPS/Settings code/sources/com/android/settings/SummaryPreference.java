package com.android.settings;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;

public class SummaryPreference extends Preference {
    private String mAmount;
    private boolean mChartEnabled = true;
    private String mEndLabel;
    private float mLeftRatio;
    private float mMiddleRatio;
    private float mRightRatio;
    private String mStartLabel;
    private String mUnits;

    public SummaryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(C1715R.layout.settings_summary_preference);
    }

    public void setAmount(String str) {
        this.mAmount = str;
        if (this.mAmount != null && this.mUnits != null) {
            setTitle(TextUtils.expandTemplate(getContext().getText(C1715R.string.storage_size_large), new CharSequence[]{this.mAmount, this.mUnits}));
        }
    }

    public void setUnits(String str) {
        this.mUnits = str;
        if (this.mAmount != null && this.mUnits != null) {
            setTitle(TextUtils.expandTemplate(getContext().getText(C1715R.string.storage_size_large), new CharSequence[]{this.mAmount, this.mUnits}));
        }
    }

    public void setRatios(float f, float f2, float f3) {
        this.mLeftRatio = f;
        this.mMiddleRatio = f2;
        this.mRightRatio = f3;
        notifyChanged();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ProgressBar progressBar = (ProgressBar) preferenceViewHolder.itemView.findViewById(C1715R.C1718id.color_bar);
        if (this.mChartEnabled) {
            progressBar.setVisibility(0);
            int i = (int) (this.mLeftRatio * 100.0f);
            progressBar.setProgress(i);
            progressBar.setSecondaryProgress(i + ((int) (this.mMiddleRatio * 100.0f)));
        } else {
            progressBar.setVisibility(8);
        }
        if (!this.mChartEnabled || (TextUtils.isEmpty(this.mStartLabel) && TextUtils.isEmpty(this.mEndLabel))) {
            preferenceViewHolder.findViewById(C1715R.C1718id.label_bar).setVisibility(8);
            return;
        }
        preferenceViewHolder.findViewById(C1715R.C1718id.label_bar).setVisibility(0);
        ((TextView) preferenceViewHolder.findViewById(16908308)).setText(this.mStartLabel);
        ((TextView) preferenceViewHolder.findViewById(16908309)).setText(this.mEndLabel);
    }
}
