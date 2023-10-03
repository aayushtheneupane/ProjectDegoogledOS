package com.android.contacts.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SectionIndexer;

public abstract class IndexerListAdapter extends PinnedHeaderListAdapter implements SectionIndexer {
    protected Context mContext;
    private View mHeader;
    private int mIndexedPartition = 0;
    private SectionIndexer mIndexer;
    private Placement mPlacementCache = new Placement();
    private boolean mSectionHeaderDisplayEnabled;

    /* access modifiers changed from: protected */
    public abstract View createPinnedSectionHeaderView(Context context, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public abstract void setPinnedSectionTitle(View view, String str);

    public static final class Placement {
        public boolean firstInSection;
        public boolean lastInSection;
        /* access modifiers changed from: private */
        public int position = -1;
        public String sectionHeader;

        public void invalidate() {
            this.position = -1;
        }
    }

    public IndexerListAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    public boolean isSectionHeaderDisplayEnabled() {
        return this.mSectionHeaderDisplayEnabled;
    }

    public void setSectionHeaderDisplayEnabled(boolean z) {
        this.mSectionHeaderDisplayEnabled = z;
    }

    public int getIndexedPartition() {
        return this.mIndexedPartition;
    }

    public void setIndexedPartition(int i) {
        this.mIndexedPartition = i;
    }

    public SectionIndexer getIndexer() {
        return this.mIndexer;
    }

    public void setIndexer(SectionIndexer sectionIndexer) {
        this.mIndexer = sectionIndexer;
        this.mPlacementCache.invalidate();
    }

    public Object[] getSections() {
        SectionIndexer sectionIndexer = this.mIndexer;
        if (sectionIndexer == null) {
            return new String[]{" "};
        }
        return sectionIndexer.getSections();
    }

    public int getPositionForSection(int i) {
        SectionIndexer sectionIndexer = this.mIndexer;
        if (sectionIndexer == null) {
            return -1;
        }
        return sectionIndexer.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        SectionIndexer sectionIndexer = this.mIndexer;
        if (sectionIndexer == null) {
            return -1;
        }
        return sectionIndexer.getSectionForPosition(i);
    }

    public int getPinnedHeaderCount() {
        if (isSectionHeaderDisplayEnabled()) {
            return super.getPinnedHeaderCount() + 1;
        }
        return super.getPinnedHeaderCount();
    }

    public View getPinnedHeaderView(int i, View view, ViewGroup viewGroup) {
        if (!isSectionHeaderDisplayEnabled() || i != getPinnedHeaderCount() - 1) {
            return super.getPinnedHeaderView(i, view, viewGroup);
        }
        if (this.mHeader == null) {
            this.mHeader = createPinnedSectionHeaderView(this.mContext, viewGroup);
        }
        return this.mHeader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        r5 = getOffsetInPartition(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void configurePinnedHeaders(com.android.contacts.list.PinnedHeaderListView r9) {
        /*
            r8 = this;
            super.configurePinnedHeaders(r9)
            boolean r0 = r8.isSectionHeaderDisplayEnabled()
            if (r0 != 0) goto L_0x000a
            return
        L_0x000a:
            int r0 = r8.getPinnedHeaderCount()
            r1 = 1
            int r0 = r0 - r1
            android.widget.SectionIndexer r2 = r8.mIndexer
            r3 = 0
            if (r2 == 0) goto L_0x0082
            int r2 = r8.getCount()
            if (r2 != 0) goto L_0x001c
            goto L_0x0082
        L_0x001c:
            int r2 = r9.getTotalTopPinnedHeaderHeight()
            int r2 = r9.getPositionAt(r2)
            int r4 = r9.getHeaderViewsCount()
            int r4 = r2 - r4
            int r5 = r8.getPartitionForPosition(r4)
            int r6 = r8.mIndexedPartition
            r7 = -1
            if (r5 != r6) goto L_0x003e
            int r5 = r8.getOffsetInPartition(r4)
            if (r5 == r7) goto L_0x003e
            int r5 = r8.getSectionForPosition(r5)
            goto L_0x003f
        L_0x003e:
            r5 = -1
        L_0x003f:
            if (r5 != r7) goto L_0x0045
            r9.setHeaderInvisible(r0, r3)
            goto L_0x0085
        L_0x0045:
            android.view.View r6 = r8.getViewAtVisiblePosition(r9, r2)
            if (r6 == 0) goto L_0x0054
            android.view.View r7 = r8.mHeader
            int r6 = r6.getMeasuredHeight()
            r7.setMinimumHeight(r6)
        L_0x0054:
            android.view.View r6 = r8.mHeader
            android.widget.SectionIndexer r7 = r8.mIndexer
            java.lang.Object[] r7 = r7.getSections()
            r7 = r7[r5]
            java.lang.String r7 = (java.lang.String) r7
            r8.setPinnedSectionTitle(r6, r7)
            int r6 = r8.mIndexedPartition
            int r6 = r8.getPositionForPartition(r6)
            int r7 = r8.mIndexedPartition
            boolean r7 = r8.hasHeader(r7)
            if (r7 == 0) goto L_0x0073
            int r6 = r6 + 1
        L_0x0073:
            int r5 = r5 + r1
            int r5 = r8.getPositionForSection(r5)
            int r6 = r6 + r5
            int r6 = r6 - r1
            if (r4 != r6) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            r1 = 0
        L_0x007e:
            r9.setFadingHeader(r0, r2, r1)
            goto L_0x0085
        L_0x0082:
            r9.setHeaderInvisible(r0, r3)
        L_0x0085:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.list.IndexerListAdapter.configurePinnedHeaders(com.android.contacts.list.PinnedHeaderListView):void");
    }

    private View getViewAtVisiblePosition(ListView listView, int i) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int childCount = listView.getChildCount();
        int i2 = i - firstVisiblePosition;
        if (i2 < 0 || i2 >= childCount) {
            return null;
        }
        return listView.getChildAt(i2);
    }

    public Placement getItemPlacementInSection(int i) {
        if (this.mPlacementCache.position == i) {
            return this.mPlacementCache;
        }
        int unused = this.mPlacementCache.position = i;
        boolean z = false;
        if (isSectionHeaderDisplayEnabled()) {
            int sectionForPosition = getSectionForPosition(i);
            if (sectionForPosition == -1 || getPositionForSection(sectionForPosition) != i) {
                Placement placement = this.mPlacementCache;
                placement.firstInSection = false;
                placement.sectionHeader = null;
            } else {
                Placement placement2 = this.mPlacementCache;
                placement2.firstInSection = true;
                placement2.sectionHeader = (String) getSections()[sectionForPosition];
            }
            Placement placement3 = this.mPlacementCache;
            if (getPositionForSection(sectionForPosition + 1) - 1 == i) {
                z = true;
            }
            placement3.lastInSection = z;
        } else {
            Placement placement4 = this.mPlacementCache;
            placement4.firstInSection = false;
            placement4.lastInSection = false;
            placement4.sectionHeader = null;
        }
        return this.mPlacementCache;
    }
}
