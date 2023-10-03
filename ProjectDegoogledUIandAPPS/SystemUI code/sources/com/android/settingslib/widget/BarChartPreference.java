package com.android.settingslib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

public class BarChartPreference extends Preference {
    private static final int[] BAR_VIEWS = {R$id.bar_view1, R$id.bar_view2, R$id.bar_view3, R$id.bar_view4};
    private BarChartInfo mBarChartInfo;
    private int mMaxBarHeight;

    public BarChartPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.setDividerAllowedAbove(true);
        preferenceViewHolder.setDividerAllowedBelow(true);
        bindChartTitleView(preferenceViewHolder);
        throw null;
    }

    private void init() {
        setSelectable(false);
        setLayoutResource(R$layout.settings_bar_chart);
        this.mMaxBarHeight = getContext().getResources().getDimensionPixelSize(R$dimen.settings_bar_view_max_height);
    }

    private void bindChartTitleView(PreferenceViewHolder preferenceViewHolder) {
        TextView textView = (TextView) preferenceViewHolder.findViewById(R$id.bar_chart_title);
        this.mBarChartInfo.getTitle();
        throw null;
    }
}
