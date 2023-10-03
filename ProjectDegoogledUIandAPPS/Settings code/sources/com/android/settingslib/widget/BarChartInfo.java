package com.android.settingslib.widget;

import android.view.View;
import java.util.List;

public class BarChartInfo {
    private BarViewInfo[] mBarViewInfos;
    private final int mDetails;
    private final View.OnClickListener mDetailsOnClickListener;
    private final int mEmptyText;
    private final int mTitle;

    public int getTitle() {
        return this.mTitle;
    }

    public int getDetails() {
        return this.mDetails;
    }

    public int getEmptyText() {
        return this.mEmptyText;
    }

    public View.OnClickListener getDetailsOnClickListener() {
        return this.mDetailsOnClickListener;
    }

    public BarViewInfo[] getBarViewInfos() {
        return this.mBarViewInfos;
    }

    /* access modifiers changed from: package-private */
    public void setBarViewInfos(BarViewInfo[] barViewInfoArr) {
        this.mBarViewInfos = barViewInfoArr;
    }

    private BarChartInfo(Builder builder) {
        this.mTitle = builder.mTitle;
        this.mDetails = builder.mDetails;
        this.mEmptyText = builder.mEmptyText;
        this.mDetailsOnClickListener = builder.mDetailsOnClickListener;
        if (builder.mBarViewInfos != null) {
            this.mBarViewInfos = (BarViewInfo[]) builder.mBarViewInfos.stream().toArray($$Lambda$BarChartInfo$2CrHVNAna8TvSeyBIL19oCkthVU.INSTANCE);
        }
    }

    static /* synthetic */ BarViewInfo[] lambda$new$0(int i) {
        return new BarViewInfo[i];
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public List<BarViewInfo> mBarViewInfos;
        /* access modifiers changed from: private */
        public int mDetails;
        /* access modifiers changed from: private */
        public View.OnClickListener mDetailsOnClickListener;
        /* access modifiers changed from: private */
        public int mEmptyText;
        /* access modifiers changed from: private */
        public int mTitle;

        public BarChartInfo build() {
            if (this.mTitle != 0) {
                return new BarChartInfo(this);
            }
            throw new IllegalStateException("You must call Builder#setTitle() once.");
        }

        public Builder setTitle(int i) {
            this.mTitle = i;
            return this;
        }

        public Builder setDetails(int i) {
            this.mDetails = i;
            return this;
        }

        public Builder setEmptyText(int i) {
            this.mEmptyText = i;
            return this;
        }

        public Builder setDetailsOnClickListener(View.OnClickListener onClickListener) {
            this.mDetailsOnClickListener = onClickListener;
            return this;
        }
    }
}
