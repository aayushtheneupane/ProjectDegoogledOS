package androidx.slice.builders;

import android.content.Context;
import android.net.Uri;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Pair;
import androidx.slice.Slice;
import androidx.slice.SliceSpecs;
import androidx.slice.builders.impl.ListBuilderBasicImpl;
import androidx.slice.builders.impl.ListBuilderImpl;
import androidx.slice.builders.impl.TemplateBuilderImpl;
import java.util.ArrayList;
import java.util.List;

public class ListBuilder extends TemplateSliceBuilder {
    private androidx.slice.builders.impl.ListBuilder mImpl;

    public ListBuilder(Context context, Uri uri, long j) {
        super(context, uri);
        this.mImpl.setTtl(j);
    }

    public Slice build() {
        return ((TemplateBuilderImpl) this.mImpl).build();
    }

    /* access modifiers changed from: package-private */
    public void setImpl(TemplateBuilderImpl templateBuilderImpl) {
        this.mImpl = (androidx.slice.builders.impl.ListBuilder) templateBuilderImpl;
    }

    public ListBuilder addRow(RowBuilder rowBuilder) {
        this.mImpl.addRow(rowBuilder);
        return this;
    }

    public ListBuilder setHeader(HeaderBuilder headerBuilder) {
        this.mImpl.setHeader(headerBuilder);
        return this;
    }

    /* access modifiers changed from: protected */
    public TemplateBuilderImpl selectImpl() {
        if (checkCompatible(SliceSpecs.LIST_V2)) {
            return new ListBuilderImpl(getBuilder(), SliceSpecs.LIST_V2, getClock());
        }
        if (checkCompatible(SliceSpecs.LIST)) {
            return new ListBuilderImpl(getBuilder(), SliceSpecs.LIST, getClock());
        }
        if (checkCompatible(SliceSpecs.BASIC)) {
            return new ListBuilderBasicImpl(getBuilder(), SliceSpecs.BASIC);
        }
        return null;
    }

    public static class RowBuilder {
        private CharSequence mContentDescription;
        private List<Object> mEndItems = new ArrayList();
        private List<Boolean> mEndLoads = new ArrayList();
        private List<Integer> mEndTypes = new ArrayList();
        private boolean mHasEndActionOrToggle;
        private boolean mHasEndImage;
        private int mLayoutDirection = -1;
        private SliceAction mPrimaryAction;
        private CharSequence mSubtitle;
        private boolean mSubtitleLoading;
        private long mTimeStamp = -1;
        private CharSequence mTitle;
        private SliceAction mTitleAction;
        private boolean mTitleActionLoading;
        private IconCompat mTitleIcon;
        private int mTitleImageMode;
        private boolean mTitleItemLoading;
        private boolean mTitleLoading;
        private final Uri mUri;

        public RowBuilder(Uri uri) {
            this.mUri = uri;
        }

        public RowBuilder setPrimaryAction(SliceAction sliceAction) {
            this.mPrimaryAction = sliceAction;
            return this;
        }

        public RowBuilder setTitle(CharSequence charSequence) {
            setTitle(charSequence, false);
            return this;
        }

        public RowBuilder setTitle(CharSequence charSequence, boolean z) {
            this.mTitle = charSequence;
            this.mTitleLoading = z;
            return this;
        }

        public RowBuilder addEndItem(IconCompat iconCompat, int i) {
            addEndItem(iconCompat, i, false);
            return this;
        }

        public RowBuilder addEndItem(IconCompat iconCompat, int i, boolean z) {
            if (!this.mHasEndActionOrToggle) {
                this.mEndItems.add(new Pair(iconCompat, Integer.valueOf(i)));
                this.mEndTypes.add(1);
                this.mEndLoads.add(Boolean.valueOf(z));
                this.mHasEndImage = true;
                return this;
            }
            throw new IllegalArgumentException("Trying to add an icon to end items when anaction has already been added. End items cannot have a mixture of actions and icons.");
        }

        public RowBuilder setContentDescription(CharSequence charSequence) {
            this.mContentDescription = charSequence;
            return this;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public long getTimeStamp() {
            return this.mTimeStamp;
        }

        public boolean isTitleItemLoading() {
            return this.mTitleItemLoading;
        }

        public int getTitleImageMode() {
            return this.mTitleImageMode;
        }

        public IconCompat getTitleIcon() {
            return this.mTitleIcon;
        }

        public SliceAction getTitleAction() {
            return this.mTitleAction;
        }

        public SliceAction getPrimaryAction() {
            return this.mPrimaryAction;
        }

        public CharSequence getTitle() {
            return this.mTitle;
        }

        public boolean isTitleLoading() {
            return this.mTitleLoading;
        }

        public CharSequence getSubtitle() {
            return this.mSubtitle;
        }

        public boolean isSubtitleLoading() {
            return this.mSubtitleLoading;
        }

        public CharSequence getContentDescription() {
            return this.mContentDescription;
        }

        public int getLayoutDirection() {
            return this.mLayoutDirection;
        }

        public List<Object> getEndItems() {
            return this.mEndItems;
        }

        public List<Integer> getEndTypes() {
            return this.mEndTypes;
        }

        public List<Boolean> getEndLoads() {
            return this.mEndLoads;
        }

        public boolean isTitleActionLoading() {
            return this.mTitleActionLoading;
        }
    }

    public static class HeaderBuilder {
        private CharSequence mContentDescription;
        private int mLayoutDirection;
        private SliceAction mPrimaryAction;
        private CharSequence mSubtitle;
        private boolean mSubtitleLoading;
        private CharSequence mSummary;
        private boolean mSummaryLoading;
        private CharSequence mTitle;
        private boolean mTitleLoading;
        private final Uri mUri;

        public HeaderBuilder(Uri uri) {
            this.mUri = uri;
        }

        public HeaderBuilder setTitle(CharSequence charSequence) {
            setTitle(charSequence, false);
            return this;
        }

        public HeaderBuilder setTitle(CharSequence charSequence, boolean z) {
            this.mTitle = charSequence;
            this.mTitleLoading = z;
            return this;
        }

        public HeaderBuilder setPrimaryAction(SliceAction sliceAction) {
            this.mPrimaryAction = sliceAction;
            return this;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public CharSequence getTitle() {
            return this.mTitle;
        }

        public boolean isTitleLoading() {
            return this.mTitleLoading;
        }

        public CharSequence getSubtitle() {
            return this.mSubtitle;
        }

        public boolean isSubtitleLoading() {
            return this.mSubtitleLoading;
        }

        public CharSequence getSummary() {
            return this.mSummary;
        }

        public boolean isSummaryLoading() {
            return this.mSummaryLoading;
        }

        public SliceAction getPrimaryAction() {
            return this.mPrimaryAction;
        }

        public CharSequence getContentDescription() {
            return this.mContentDescription;
        }

        public int getLayoutDirection() {
            return this.mLayoutDirection;
        }
    }
}
