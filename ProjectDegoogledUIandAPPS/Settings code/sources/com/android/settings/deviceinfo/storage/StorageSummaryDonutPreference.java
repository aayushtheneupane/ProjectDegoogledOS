package com.android.settings.deviceinfo.storage;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.widget.DonutView;
import com.havoc.config.center.C1715R;

public class StorageSummaryDonutPreference extends Preference implements View.OnClickListener {
    private double mPercent;

    public StorageSummaryDonutPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    public StorageSummaryDonutPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPercent = -1.0d;
        setLayoutResource(C1715R.layout.storage_summary_donut);
        setEnabled(false);
    }

    public void setPercent(long j, long j2) {
        if (j2 != 0) {
            this.mPercent = ((double) j) / ((double) j2);
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setClickable(false);
        DonutView donutView = (DonutView) preferenceViewHolder.findViewById(C1715R.C1718id.donut);
        if (donutView != null) {
            donutView.setPercentage(this.mPercent);
        }
        Button button = (Button) preferenceViewHolder.findViewById(C1715R.C1718id.deletion_helper_button);
        if (button != null) {
            button.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        if (view != null && C1715R.C1718id.deletion_helper_button == view.getId()) {
            Context context = getContext();
            FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 840, (Pair<Integer, Object>[]) new Pair[0]);
            getContext().startActivity(new Intent("android.os.storage.action.MANAGE_STORAGE"));
        }
    }

    private static class BoldLinkSpan extends StyleSpan {
        public BoldLinkSpan() {
            super(1);
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(textPaint.linkColor);
        }
    }
}
