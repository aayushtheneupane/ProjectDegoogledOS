package androidx.slice.builders.impl;

import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Pair;
import androidx.slice.Clock;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.SliceSpec;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import androidx.slice.core.SliceQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListBuilderImpl extends TemplateBuilderImpl implements ListBuilder {
    private boolean mFirstRowChecked;
    private boolean mFirstRowHasText;
    private boolean mIsError;
    private boolean mIsFirstRowTypeValid;
    private Set<String> mKeywords;
    private List<Slice> mSliceActions;
    private Slice mSliceHeader;

    public ListBuilderImpl(Slice.Builder builder, SliceSpec sliceSpec, Clock clock) {
        super(builder, sliceSpec, clock);
    }

    public void apply(Slice.Builder builder) {
        builder.addLong(getClock().currentTimeMillis(), "millis", "last_updated");
        Slice slice = this.mSliceHeader;
        if (slice != null) {
            builder.addSubSlice(slice);
        }
        if (this.mSliceActions != null) {
            Slice.Builder builder2 = new Slice.Builder(builder);
            for (int i = 0; i < this.mSliceActions.size(); i++) {
                builder2.addSubSlice(this.mSliceActions.get(i));
            }
            builder2.addHints("actions");
            builder.addSubSlice(builder2.build());
        }
        if (this.mIsError) {
            builder.addHints("error");
        }
        if (this.mKeywords != null) {
            Slice.Builder builder3 = new Slice.Builder(getBuilder());
            for (String addText : this.mKeywords) {
                builder3.addText((CharSequence) addText, (String) null, new String[0]);
            }
            Slice.Builder builder4 = getBuilder();
            builder3.addHints("keywords");
            builder4.addSubSlice(builder3.build());
        }
    }

    public Slice build() {
        Slice build = super.build();
        boolean z = true;
        boolean z2 = SliceQuery.find(build, (String) null, "partial", (String) null) != null;
        if (SliceQuery.find(build, "slice", "list_item", (String) null) != null) {
            z = false;
        }
        String[] strArr = {"shortcut", "title"};
        SliceItem find = SliceQuery.find(build, "action", strArr, (String[]) null);
        List<SliceItem> findAll = SliceQuery.findAll(build, "slice", strArr, (String[]) null);
        if (!z2 && !z && find == null && (findAll == null || findAll.isEmpty())) {
            throw new IllegalStateException("A slice requires a primary action; ensure one of your builders has called #setPrimaryAction with a valid SliceAction.");
        } else if (this.mFirstRowChecked && !this.mIsFirstRowTypeValid) {
            throw new IllegalStateException("A slice cannot have the first row be constructed from a GridRowBuilder, consider using #setHeader.");
        } else if (!this.mFirstRowChecked || this.mFirstRowHasText) {
            return build;
        } else {
            throw new IllegalStateException("A slice requires the first row to have some text.");
        }
    }

    public void addRow(ListBuilder.RowBuilder rowBuilder) {
        RowBuilderImpl rowBuilderImpl = new RowBuilderImpl(createChildBuilder());
        rowBuilderImpl.fillFrom(rowBuilder);
        checkRow(true, rowBuilderImpl.hasText());
        addRow(rowBuilderImpl);
    }

    public void addRow(RowBuilderImpl rowBuilderImpl) {
        checkRow(true, rowBuilderImpl.hasText());
        rowBuilderImpl.getBuilder().addHints("list_item");
        getBuilder().addSubSlice(rowBuilderImpl.build());
    }

    public void setHeader(ListBuilder.HeaderBuilder headerBuilder) {
        this.mIsFirstRowTypeValid = true;
        this.mFirstRowHasText = true;
        this.mFirstRowChecked = true;
        HeaderBuilderImpl headerBuilderImpl = new HeaderBuilderImpl(this);
        headerBuilderImpl.fillFrom(headerBuilder);
        this.mSliceHeader = headerBuilderImpl.build();
    }

    public void setTtl(long j) {
        long j2 = -1;
        if (j != -1) {
            j2 = getClock().currentTimeMillis() + j;
        }
        getBuilder().addTimestamp(j2, "millis", "ttl");
    }

    private void checkRow(boolean z, boolean z2) {
        if (!this.mFirstRowChecked) {
            this.mFirstRowChecked = true;
            this.mIsFirstRowTypeValid = z;
            this.mFirstRowHasText = z2;
        }
    }

    public static class RowBuilderImpl extends TemplateBuilderImpl {
        private CharSequence mContentDescr;
        private ArrayList<Slice> mEndItems = new ArrayList<>();
        private SliceAction mPrimaryAction;
        private Slice mStartItem;
        private SliceItem mSubtitleItem;
        private SliceItem mTitleItem;

        RowBuilderImpl(Slice.Builder builder) {
            super(builder, (SliceSpec) null);
        }

        /* access modifiers changed from: package-private */
        public void fillFrom(ListBuilder.RowBuilder rowBuilder) {
            if (rowBuilder.getUri() != null) {
                setBuilder(new Slice.Builder(rowBuilder.getUri()));
            }
            setPrimaryAction(rowBuilder.getPrimaryAction());
            if (rowBuilder.getLayoutDirection() != -1) {
                setLayoutDirection(rowBuilder.getLayoutDirection());
            }
            if (rowBuilder.getTitleAction() != null || rowBuilder.isTitleActionLoading()) {
                setTitleItem(rowBuilder.getTitleAction(), rowBuilder.isTitleActionLoading());
            } else if (rowBuilder.getTitleIcon() != null || rowBuilder.isTitleItemLoading()) {
                setTitleItem(rowBuilder.getTitleIcon(), rowBuilder.getTitleImageMode(), rowBuilder.isTitleItemLoading());
            } else if (rowBuilder.getTimeStamp() != -1) {
                setTitleItem(rowBuilder.getTimeStamp());
            }
            if (rowBuilder.getTitle() != null || rowBuilder.isTitleLoading()) {
                setTitle(rowBuilder.getTitle(), rowBuilder.isTitleLoading());
            }
            if (rowBuilder.getSubtitle() != null || rowBuilder.isSubtitleLoading()) {
                setSubtitle(rowBuilder.getSubtitle(), rowBuilder.isSubtitleLoading());
            }
            if (rowBuilder.getContentDescription() != null) {
                setContentDescription(rowBuilder.getContentDescription());
            }
            List<Object> endItems = rowBuilder.getEndItems();
            List<Integer> endTypes = rowBuilder.getEndTypes();
            List<Boolean> endLoads = rowBuilder.getEndLoads();
            for (int i = 0; i < endItems.size(); i++) {
                int intValue = endTypes.get(i).intValue();
                if (intValue == 0) {
                    addEndItem(((Long) endItems.get(i)).longValue());
                } else if (intValue == 1) {
                    Pair pair = (Pair) endItems.get(i);
                    addEndItem((IconCompat) pair.first, ((Integer) pair.second).intValue(), endLoads.get(i).booleanValue());
                } else if (intValue == 2) {
                    addEndItem((SliceAction) endItems.get(i), endLoads.get(i).booleanValue());
                }
            }
        }

        private void setTitleItem(long j) {
            Slice.Builder builder = new Slice.Builder(getBuilder());
            builder.addTimestamp(j, (String) null, new String[0]);
            builder.addHints("title");
            this.mStartItem = builder.build();
        }

        private void setTitleItem(IconCompat iconCompat, int i, boolean z) {
            ArrayList arrayList = new ArrayList();
            if (i != 0) {
                arrayList.add("no_tint");
            }
            if (i == 2) {
                arrayList.add("large");
            }
            if (z) {
                arrayList.add("partial");
            }
            Slice.Builder builder = new Slice.Builder(getBuilder());
            builder.addIcon(iconCompat, (String) null, (List<String>) arrayList);
            if (z) {
                builder.addHints("partial");
            }
            builder.addHints("title");
            this.mStartItem = builder.build();
        }

        private void setTitleItem(SliceAction sliceAction, boolean z) {
            Slice.Builder builder = new Slice.Builder(getBuilder());
            builder.addHints("title");
            if (z) {
                builder.addHints("partial");
            }
            this.mStartItem = sliceAction.buildSlice(builder);
        }

        private void setPrimaryAction(SliceAction sliceAction) {
            this.mPrimaryAction = sliceAction;
        }

        private void setTitle(CharSequence charSequence, boolean z) {
            this.mTitleItem = new SliceItem((Object) charSequence, "text", (String) null, new String[]{"title"});
            if (z) {
                this.mTitleItem.addHint("partial");
            }
        }

        private void setSubtitle(CharSequence charSequence, boolean z) {
            this.mSubtitleItem = new SliceItem((Object) charSequence, "text", (String) null, new String[0]);
            if (z) {
                this.mSubtitleItem.addHint("partial");
            }
        }

        /* access modifiers changed from: protected */
        public void addEndItem(long j) {
            ArrayList<Slice> arrayList = this.mEndItems;
            Slice.Builder builder = new Slice.Builder(getBuilder());
            builder.addTimestamp(j, (String) null, new String[0]);
            arrayList.add(builder.build());
        }

        private void addEndItem(IconCompat iconCompat, int i, boolean z) {
            ArrayList arrayList = new ArrayList();
            if (i != 0) {
                arrayList.add("no_tint");
            }
            if (i == 2) {
                arrayList.add("large");
            }
            if (z) {
                arrayList.add("partial");
            }
            Slice.Builder builder = new Slice.Builder(getBuilder());
            builder.addIcon(iconCompat, (String) null, (List<String>) arrayList);
            if (z) {
                builder.addHints("partial");
            }
            this.mEndItems.add(builder.build());
        }

        private void addEndItem(SliceAction sliceAction, boolean z) {
            Slice.Builder builder = new Slice.Builder(getBuilder());
            if (z) {
                builder.addHints("partial");
            }
            this.mEndItems.add(sliceAction.buildSlice(builder));
        }

        private void setContentDescription(CharSequence charSequence) {
            this.mContentDescr = charSequence;
        }

        private void setLayoutDirection(int i) {
            getBuilder().addInt(i, "layout_direction", new String[0]);
        }

        /* access modifiers changed from: package-private */
        public boolean hasText() {
            return (this.mTitleItem == null && this.mSubtitleItem == null) ? false : true;
        }

        public void apply(Slice.Builder builder) {
            Slice slice = this.mStartItem;
            if (slice != null) {
                builder.addSubSlice(slice);
            }
            SliceItem sliceItem = this.mTitleItem;
            if (sliceItem != null) {
                builder.addItem(sliceItem);
            }
            SliceItem sliceItem2 = this.mSubtitleItem;
            if (sliceItem2 != null) {
                builder.addItem(sliceItem2);
            }
            for (int i = 0; i < this.mEndItems.size(); i++) {
                builder.addSubSlice(this.mEndItems.get(i));
            }
            CharSequence charSequence = this.mContentDescr;
            if (charSequence != null) {
                builder.addText(charSequence, "content_description", new String[0]);
            }
            SliceAction sliceAction = this.mPrimaryAction;
            if (sliceAction != null) {
                sliceAction.setPrimaryAction(builder);
            }
        }
    }

    public static class HeaderBuilderImpl extends TemplateBuilderImpl {
        private CharSequence mContentDescr;
        private SliceAction mPrimaryAction;
        private SliceItem mSubtitleItem;
        private SliceItem mSummaryItem;
        private SliceItem mTitleItem;

        HeaderBuilderImpl(ListBuilderImpl listBuilderImpl) {
            super(listBuilderImpl.createChildBuilder(), (SliceSpec) null);
        }

        /* access modifiers changed from: package-private */
        public void fillFrom(ListBuilder.HeaderBuilder headerBuilder) {
            if (headerBuilder.getUri() != null) {
                setBuilder(new Slice.Builder(headerBuilder.getUri()));
            }
            setPrimaryAction(headerBuilder.getPrimaryAction());
            if (headerBuilder.getLayoutDirection() != -1) {
                setLayoutDirection(headerBuilder.getLayoutDirection());
            }
            if (headerBuilder.getTitle() != null || headerBuilder.isTitleLoading()) {
                setTitle(headerBuilder.getTitle(), headerBuilder.isTitleLoading());
            }
            if (headerBuilder.getSubtitle() != null || headerBuilder.isSubtitleLoading()) {
                setSubtitle(headerBuilder.getSubtitle(), headerBuilder.isSubtitleLoading());
            }
            if (headerBuilder.getSummary() != null || headerBuilder.isSummaryLoading()) {
                setSummary(headerBuilder.getSummary(), headerBuilder.isSummaryLoading());
            }
            if (headerBuilder.getContentDescription() != null) {
                setContentDescription(headerBuilder.getContentDescription());
            }
        }

        public void apply(Slice.Builder builder) {
            SliceItem sliceItem = this.mTitleItem;
            if (sliceItem != null) {
                builder.addItem(sliceItem);
            }
            SliceItem sliceItem2 = this.mSubtitleItem;
            if (sliceItem2 != null) {
                builder.addItem(sliceItem2);
            }
            SliceItem sliceItem3 = this.mSummaryItem;
            if (sliceItem3 != null) {
                builder.addItem(sliceItem3);
            }
            CharSequence charSequence = this.mContentDescr;
            if (charSequence != null) {
                builder.addText(charSequence, "content_description", new String[0]);
            }
            SliceAction sliceAction = this.mPrimaryAction;
            if (sliceAction != null) {
                sliceAction.setPrimaryAction(builder);
            }
            if (this.mSubtitleItem == null && this.mTitleItem == null) {
                throw new IllegalStateException("Header requires a title or subtitle to be set.");
            }
        }

        private void setTitle(CharSequence charSequence, boolean z) {
            this.mTitleItem = new SliceItem((Object) charSequence, "text", (String) null, new String[]{"title"});
            if (z) {
                this.mTitleItem.addHint("partial");
            }
        }

        private void setSubtitle(CharSequence charSequence, boolean z) {
            this.mSubtitleItem = new SliceItem((Object) charSequence, "text", (String) null, new String[0]);
            if (z) {
                this.mSubtitleItem.addHint("partial");
            }
        }

        private void setSummary(CharSequence charSequence, boolean z) {
            this.mSummaryItem = new SliceItem((Object) charSequence, "text", (String) null, new String[]{"summary"});
            if (z) {
                this.mSummaryItem.addHint("partial");
            }
        }

        private void setPrimaryAction(SliceAction sliceAction) {
            this.mPrimaryAction = sliceAction;
        }

        private void setContentDescription(CharSequence charSequence) {
            this.mContentDescr = charSequence;
        }

        private void setLayoutDirection(int i) {
            getBuilder().addInt(i, "layout_direction", new String[0]);
        }
    }
}
