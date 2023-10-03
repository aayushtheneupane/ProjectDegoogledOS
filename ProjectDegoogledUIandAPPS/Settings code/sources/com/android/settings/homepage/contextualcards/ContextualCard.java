package com.android.settings.homepage.contextualcards;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.havoc.config.center.C1715R;

public class ContextualCard {
    private final long mAppVersion;
    private final Builder mBuilder;
    private final int mCardAction;
    private final int mCardType;
    private final int mCategory;
    private final long mExpireTimeMS;
    private final boolean mHasInlineAction;
    private final Drawable mIconDrawable;
    private final int mIconResId;
    private final String mIconResName;
    private final boolean mIsLargeCard;
    private final boolean mIsPendingDismiss;
    private final String mLocalizedToLocale;
    private final String mName;
    private final String mPackageName;
    private final double mRankingScore;
    private final String mSliceUri;
    private final String mSummaryResName;
    private final String mSummaryText;
    private final String mTitleResName;
    private final String mTitleText;
    private final int mViewType;

    private int getViewTypeByCardType(int i) {
        if (i == 1) {
            return C1715R.layout.contextual_slice_full_tile;
        }
        return 0;
    }

    public String getName() {
        return this.mName;
    }

    public int getCardType() {
        return this.mCardType;
    }

    public double getRankingScore() {
        return this.mRankingScore;
    }

    public String getTextSliceUri() {
        return this.mSliceUri;
    }

    public Uri getSliceUri() {
        return Uri.parse(this.mSliceUri);
    }

    public int getCategory() {
        return this.mCategory;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public String getSummaryText() {
        return this.mSummaryText;
    }

    public Drawable getIconDrawable() {
        return this.mIconDrawable;
    }

    public boolean isLargeCard() {
        return this.mIsLargeCard;
    }

    /* access modifiers changed from: package-private */
    public boolean isCustomCard() {
        return TextUtils.isEmpty(this.mSliceUri);
    }

    public int getViewType() {
        return this.mViewType;
    }

    public boolean isPendingDismiss() {
        return this.mIsPendingDismiss;
    }

    public boolean hasInlineAction() {
        return this.mHasInlineAction;
    }

    public Builder mutate() {
        return this.mBuilder;
    }

    public ContextualCard(Builder builder) {
        this.mBuilder = builder;
        this.mName = builder.mName;
        this.mCardType = builder.mCardType;
        this.mRankingScore = builder.mRankingScore;
        this.mSliceUri = builder.mSliceUri;
        this.mCategory = builder.mCategory;
        this.mLocalizedToLocale = builder.mLocalizedToLocale;
        this.mPackageName = builder.mPackageName;
        this.mAppVersion = builder.mAppVersion;
        this.mTitleResName = builder.mTitleResName;
        this.mTitleText = builder.mTitleText;
        this.mSummaryResName = builder.mSummaryResName;
        this.mSummaryText = builder.mSummaryText;
        this.mIconResName = builder.mIconResName;
        this.mIconResId = builder.mIconResId;
        this.mCardAction = builder.mCardAction;
        this.mExpireTimeMS = builder.mExpireTimeMS;
        this.mIconDrawable = builder.mIconDrawable;
        this.mIsLargeCard = builder.mIsLargeCard;
        this.mViewType = builder.mViewType;
        this.mIsPendingDismiss = builder.mIsPendingDismiss;
        this.mHasInlineAction = builder.mHasInlineAction;
    }

    ContextualCard(Cursor cursor) {
        this.mBuilder = new Builder();
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
        this.mBuilder.setName(this.mName);
        this.mCardType = cursor.getInt(cursor.getColumnIndex("type"));
        this.mBuilder.setCardType(this.mCardType);
        this.mRankingScore = cursor.getDouble(cursor.getColumnIndex("score"));
        this.mBuilder.setRankingScore(this.mRankingScore);
        this.mSliceUri = cursor.getString(cursor.getColumnIndex("slice_uri"));
        this.mBuilder.setSliceUri(Uri.parse(this.mSliceUri));
        this.mCategory = cursor.getInt(cursor.getColumnIndex("category"));
        this.mBuilder.setCategory(this.mCategory);
        this.mLocalizedToLocale = cursor.getString(cursor.getColumnIndex("localized_to_locale"));
        this.mBuilder.setLocalizedToLocale(this.mLocalizedToLocale);
        this.mPackageName = cursor.getString(cursor.getColumnIndex("package_name"));
        this.mBuilder.setPackageName(this.mPackageName);
        this.mAppVersion = cursor.getLong(cursor.getColumnIndex("app_version"));
        this.mBuilder.setAppVersion(this.mAppVersion);
        this.mTitleResName = cursor.getString(cursor.getColumnIndex("title_res_name"));
        this.mBuilder.setTitleResName(this.mTitleResName);
        this.mTitleText = cursor.getString(cursor.getColumnIndex("title_text"));
        this.mBuilder.setTitleText(this.mTitleText);
        this.mSummaryResName = cursor.getString(cursor.getColumnIndex("summary_res_name"));
        this.mBuilder.setSummaryResName(this.mSummaryResName);
        this.mSummaryText = cursor.getString(cursor.getColumnIndex("summary_text"));
        this.mBuilder.setSummaryText(this.mSummaryText);
        this.mIconResName = cursor.getString(cursor.getColumnIndex("icon_res_name"));
        this.mBuilder.setIconResName(this.mIconResName);
        this.mIconResId = cursor.getInt(cursor.getColumnIndex("icon_res_id"));
        this.mBuilder.setIconResId(this.mIconResId);
        this.mCardAction = cursor.getInt(cursor.getColumnIndex("card_action"));
        this.mBuilder.setCardAction(this.mCardAction);
        this.mExpireTimeMS = cursor.getLong(cursor.getColumnIndex("expire_time_ms"));
        this.mBuilder.setExpireTimeMS(this.mExpireTimeMS);
        this.mIsLargeCard = false;
        this.mBuilder.setIsLargeCard(this.mIsLargeCard);
        this.mIconDrawable = null;
        this.mBuilder.setIconDrawable(this.mIconDrawable);
        this.mViewType = getViewTypeByCardType(this.mCardType);
        this.mBuilder.setViewType(this.mViewType);
        this.mIsPendingDismiss = false;
        this.mBuilder.setIsPendingDismiss(this.mIsPendingDismiss);
        this.mHasInlineAction = false;
        this.mBuilder.setHasInlineAction(this.mHasInlineAction);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContextualCard)) {
            return false;
        }
        return TextUtils.equals(this.mName, ((ContextualCard) obj).mName);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public long mAppVersion;
        /* access modifiers changed from: private */
        public int mCardAction;
        /* access modifiers changed from: private */
        public int mCardType;
        /* access modifiers changed from: private */
        public int mCategory;
        /* access modifiers changed from: private */
        public long mExpireTimeMS;
        /* access modifiers changed from: private */
        public boolean mHasInlineAction;
        /* access modifiers changed from: private */
        public Drawable mIconDrawable;
        /* access modifiers changed from: private */
        public int mIconResId;
        /* access modifiers changed from: private */
        public String mIconResName;
        /* access modifiers changed from: private */
        public boolean mIsLargeCard;
        /* access modifiers changed from: private */
        public boolean mIsPendingDismiss;
        /* access modifiers changed from: private */
        public String mLocalizedToLocale;
        /* access modifiers changed from: private */
        public String mName;
        /* access modifiers changed from: private */
        public String mPackageName;
        /* access modifiers changed from: private */
        public double mRankingScore;
        /* access modifiers changed from: private */
        public String mSliceUri;
        /* access modifiers changed from: private */
        public String mSummaryResName;
        /* access modifiers changed from: private */
        public String mSummaryText;
        /* access modifiers changed from: private */
        public String mTitleResName;
        /* access modifiers changed from: private */
        public String mTitleText;
        /* access modifiers changed from: private */
        public int mViewType;

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setCardType(int i) {
            this.mCardType = i;
            return this;
        }

        public Builder setRankingScore(double d) {
            this.mRankingScore = d;
            return this;
        }

        public Builder setSliceUri(Uri uri) {
            this.mSliceUri = uri.toString();
            return this;
        }

        public Builder setCategory(int i) {
            this.mCategory = i;
            return this;
        }

        public Builder setLocalizedToLocale(String str) {
            this.mLocalizedToLocale = str;
            return this;
        }

        public Builder setPackageName(String str) {
            this.mPackageName = str;
            return this;
        }

        public Builder setAppVersion(long j) {
            this.mAppVersion = j;
            return this;
        }

        public Builder setTitleResName(String str) {
            this.mTitleResName = str;
            return this;
        }

        public Builder setTitleText(String str) {
            this.mTitleText = str;
            return this;
        }

        public Builder setSummaryResName(String str) {
            this.mSummaryResName = str;
            return this;
        }

        public Builder setSummaryText(String str) {
            this.mSummaryText = str;
            return this;
        }

        public Builder setIconResName(String str) {
            this.mIconResName = str;
            return this;
        }

        public Builder setIconResId(int i) {
            this.mIconResId = i;
            return this;
        }

        public Builder setCardAction(int i) {
            this.mCardAction = i;
            return this;
        }

        public Builder setExpireTimeMS(long j) {
            this.mExpireTimeMS = j;
            return this;
        }

        public Builder setIconDrawable(Drawable drawable) {
            this.mIconDrawable = drawable;
            return this;
        }

        public Builder setIsLargeCard(boolean z) {
            this.mIsLargeCard = z;
            return this;
        }

        public Builder setViewType(int i) {
            this.mViewType = i;
            return this;
        }

        public Builder setIsPendingDismiss(boolean z) {
            this.mIsPendingDismiss = z;
            return this;
        }

        public Builder setHasInlineAction(boolean z) {
            this.mHasInlineAction = z;
            return this;
        }

        public ContextualCard build() {
            return new ContextualCard(this);
        }
    }
}
