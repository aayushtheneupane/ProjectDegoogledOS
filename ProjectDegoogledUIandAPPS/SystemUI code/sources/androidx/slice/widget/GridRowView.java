package androidx.slice.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceQuery;
import androidx.slice.view.R$dimen;
import androidx.slice.view.R$layout;
import androidx.slice.widget.GridContent;
import androidx.slice.widget.SliceView;
import java.util.ArrayList;
import java.util.Iterator;

public class GridRowView extends SliceChildView implements View.OnClickListener, View.OnTouchListener {
    private static final int TEXT_LAYOUT = R$layout.abc_slice_secondary_text;
    private static final int TITLE_TEXT_LAYOUT = R$layout.abc_slice_title;
    private View mForeground;
    private GridContent mGridContent;
    private int mGutter;
    private int mIconSize;
    private int mLargeImageHeight;
    private int[] mLoc;
    boolean mMaxCellUpdateScheduled;
    int mMaxCells;
    private ViewTreeObserver.OnPreDrawListener mMaxCellsUpdater;
    private int mRowCount;
    private int mRowIndex;
    private int mSmallImageMinWidth;
    private int mSmallImageSize;
    private int mTextPadding;
    private LinearLayout mViewContainer;

    public GridRowView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GridRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxCells = -1;
        this.mLoc = new int[2];
        this.mMaxCellsUpdater = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                GridRowView gridRowView = GridRowView.this;
                gridRowView.mMaxCells = gridRowView.getMaxCells();
                GridRowView.this.populateViews();
                GridRowView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                GridRowView.this.mMaxCellUpdateScheduled = false;
                return true;
            }
        };
        Resources resources = getContext().getResources();
        this.mViewContainer = new LinearLayout(getContext());
        this.mViewContainer.setOrientation(0);
        addView(this.mViewContainer, new FrameLayout.LayoutParams(-1, -1));
        this.mViewContainer.setGravity(16);
        this.mIconSize = resources.getDimensionPixelSize(R$dimen.abc_slice_icon_size);
        this.mSmallImageSize = resources.getDimensionPixelSize(R$dimen.abc_slice_small_image_size);
        this.mLargeImageHeight = resources.getDimensionPixelSize(R$dimen.abc_slice_grid_image_only_height);
        this.mSmallImageMinWidth = resources.getDimensionPixelSize(R$dimen.abc_slice_grid_image_min_width);
        this.mGutter = resources.getDimensionPixelSize(R$dimen.abc_slice_grid_gutter);
        this.mTextPadding = resources.getDimensionPixelSize(R$dimen.abc_slice_grid_text_padding);
        this.mForeground = new View(getContext());
        addView(this.mForeground, new FrameLayout.LayoutParams(-1, -1));
    }

    public void setInsets(int i, int i2, int i3, int i4) {
        super.setInsets(i, i2, i3, i4);
        this.mViewContainer.setPadding(i, i2 + getExtraTopPadding(), i3, i4 + getExtraBottomPadding());
    }

    private int getExtraTopPadding() {
        SliceStyle sliceStyle;
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isAllImages() || this.mRowIndex != 0 || (sliceStyle = this.mSliceStyle) == null) {
            return 0;
        }
        return sliceStyle.getGridTopPadding();
    }

    private int getExtraBottomPadding() {
        SliceStyle sliceStyle;
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isAllImages()) {
            return 0;
        }
        if ((this.mRowIndex == this.mRowCount - 1 || getMode() == 1) && (sliceStyle = this.mSliceStyle) != null) {
            return sliceStyle.getGridBottomPadding();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int height = this.mGridContent.getHeight(this.mSliceStyle, this.mViewPolicy) + this.mInsetTop + this.mInsetBottom;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
        this.mViewContainer.getLayoutParams().height = height;
        super.onMeasure(i, makeMeasureSpec);
    }

    public void setTint(int i) {
        super.setTint(i);
        if (this.mGridContent != null) {
            resetView();
            populateViews();
        }
    }

    public void setSliceItem(SliceContent sliceContent, boolean z, int i, int i2, SliceView.OnSliceActionListener onSliceActionListener) {
        resetView();
        setSliceActionListener(onSliceActionListener);
        this.mRowIndex = i;
        this.mRowCount = i2;
        this.mGridContent = (GridContent) sliceContent;
        if (!scheduleMaxCellsUpdate()) {
            populateViews();
        }
        this.mViewContainer.setPadding(this.mInsetStart, this.mInsetTop + getExtraTopPadding(), this.mInsetEnd, this.mInsetBottom + getExtraBottomPadding());
    }

    private boolean scheduleMaxCellsUpdate() {
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isValid()) {
            return true;
        }
        if (getWidth() == 0) {
            this.mMaxCellUpdateScheduled = true;
            getViewTreeObserver().addOnPreDrawListener(this.mMaxCellsUpdater);
            return true;
        }
        this.mMaxCells = getMaxCells();
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getMaxCells() {
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isValid() || getWidth() == 0) {
            return -1;
        }
        if (this.mGridContent.getGridContent().size() <= 1) {
            return 1;
        }
        return getWidth() / ((this.mGridContent.getLargestImageMode() == 2 ? this.mLargeImageHeight : this.mSmallImageMinWidth) + this.mGutter);
    }

    /* access modifiers changed from: package-private */
    public void populateViews() {
        GridContent gridContent = this.mGridContent;
        if (gridContent == null || !gridContent.isValid()) {
            resetView();
        } else if (!scheduleMaxCellsUpdate()) {
            if (this.mGridContent.getLayoutDir() != -1) {
                setLayoutDirection(this.mGridContent.getLayoutDir());
            }
            boolean z = true;
            if (this.mGridContent.getContentIntent() != null) {
                this.mViewContainer.setTag(new Pair(this.mGridContent.getContentIntent(), new EventInfo(getMode(), 3, 1, this.mRowIndex)));
                makeEntireGridClickable(true);
            }
            CharSequence contentDescription = this.mGridContent.getContentDescription();
            if (contentDescription != null) {
                this.mViewContainer.setContentDescription(contentDescription);
            }
            ArrayList<GridContent.CellContent> gridContent2 = this.mGridContent.getGridContent();
            if (this.mGridContent.getLargestImageMode() == 2) {
                this.mViewContainer.setGravity(48);
            } else {
                this.mViewContainer.setGravity(16);
            }
            int i = this.mMaxCells;
            int i2 = 0;
            if (this.mGridContent.getSeeMoreItem() == null) {
                z = false;
            }
            while (i2 < gridContent2.size()) {
                if (this.mViewContainer.getChildCount() < i) {
                    addCell(gridContent2.get(i2), i2, Math.min(gridContent2.size(), i));
                    i2++;
                } else if (z) {
                    addSeeMoreCount(gridContent2.size() - i);
                    return;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addSeeMoreCount(int r12) {
        /*
            r11 = this;
            android.widget.LinearLayout r0 = r11.mViewContainer
            int r1 = r0.getChildCount()
            r2 = 1
            int r1 = r1 - r2
            android.view.View r0 = r0.getChildAt(r1)
            android.widget.LinearLayout r1 = r11.mViewContainer
            r1.removeView(r0)
            androidx.slice.widget.GridContent r1 = r11.mGridContent
            androidx.slice.SliceItem r1 = r1.getSeeMoreItem()
            android.widget.LinearLayout r3 = r11.mViewContainer
            int r3 = r3.getChildCount()
            int r4 = r11.mMaxCells
            java.lang.String r5 = r1.getFormat()
            java.lang.String r6 = "slice"
            boolean r5 = r6.equals(r5)
            if (r5 != 0) goto L_0x0037
            java.lang.String r5 = r1.getFormat()
            java.lang.String r6 = "action"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x004e
        L_0x0037:
            androidx.slice.Slice r5 = r1.getSlice()
            java.util.List r5 = r5.getItems()
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x004e
            androidx.slice.widget.GridContent$CellContent r12 = new androidx.slice.widget.GridContent$CellContent
            r12.<init>(r1)
            r11.addCell(r12, r3, r4)
            return
        L_0x004e:
            android.content.Context r5 = r11.getContext()
            android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r5)
            androidx.slice.widget.GridContent r6 = r11.mGridContent
            boolean r6 = r6.isAllImages()
            r7 = -1
            r8 = 0
            if (r6 == 0) goto L_0x007b
            int r6 = androidx.slice.view.R$layout.abc_slice_grid_see_more_overlay
            android.widget.LinearLayout r9 = r11.mViewContainer
            android.view.View r5 = r5.inflate(r6, r9, r8)
            android.widget.FrameLayout r5 = (android.widget.FrameLayout) r5
            android.widget.FrameLayout$LayoutParams r6 = new android.widget.FrameLayout$LayoutParams
            r6.<init>(r7, r7)
            r5.addView(r0, r8, r6)
            int r0 = androidx.slice.view.R$id.text_see_more_count
            android.view.View r0 = r5.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            goto L_0x00ab
        L_0x007b:
            int r0 = androidx.slice.view.R$layout.abc_slice_grid_see_more
            android.widget.LinearLayout r6 = r11.mViewContainer
            android.view.View r0 = r5.inflate(r0, r6, r8)
            r5 = r0
            android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
            int r0 = androidx.slice.view.R$id.text_see_more_count
            android.view.View r0 = r5.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            int r6 = androidx.slice.view.R$id.text_see_more
            android.view.View r6 = r5.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            androidx.slice.widget.SliceStyle r9 = r11.mSliceStyle
            if (r9 == 0) goto L_0x00ab
            int r9 = r9.getGridTitleSize()
            float r9 = (float) r9
            r6.setTextSize(r8, r9)
            androidx.slice.widget.SliceStyle r9 = r11.mSliceStyle
            int r9 = r9.getTitleColor()
            r6.setTextColor(r9)
        L_0x00ab:
            android.widget.LinearLayout r6 = r11.mViewContainer
            android.widget.LinearLayout$LayoutParams r9 = new android.widget.LinearLayout$LayoutParams
            r10 = 1065353216(0x3f800000, float:1.0)
            r9.<init>(r8, r7, r10)
            r6.addView(r5, r9)
            android.content.res.Resources r6 = r11.getResources()
            int r7 = androidx.slice.view.R$string.abc_slice_more_content
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r9[r8] = r12
            java.lang.String r12 = r6.getString(r7, r9)
            r0.setText(r12)
            androidx.slice.widget.EventInfo r12 = new androidx.slice.widget.EventInfo
            int r0 = r11.getMode()
            r6 = 4
            int r7 = r11.mRowIndex
            r12.<init>(r0, r6, r2, r7)
            r0 = 2
            r12.setPosition(r0, r3, r4)
            android.util.Pair r0 = new android.util.Pair
            r0.<init>(r1, r12)
            r5.setTag(r0)
            r11.makeClickable(r5, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.GridRowView.addSeeMoreCount(int):void");
    }

    private void addCell(GridContent.CellContent cellContent, int i, int i2) {
        ArrayList arrayList;
        ArrayList arrayList2;
        String str;
        int i3;
        SliceItem sliceItem;
        int i4;
        int i5;
        int i6 = i;
        int i7 = i2;
        int i8 = (getMode() != 1 || !this.mGridContent.hasImage()) ? 2 : 1;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        ArrayList<SliceItem> cellItems = cellContent.getCellItems();
        SliceItem contentIntent = cellContent.getContentIntent();
        boolean z = cellItems.size() == 1;
        String str2 = "text";
        if (z || getMode() != 1) {
            arrayList = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            Iterator<SliceItem> it = cellItems.iterator();
            while (it.hasNext()) {
                SliceItem next = it.next();
                if (str2.equals(next.getFormat())) {
                    arrayList3.add(next);
                }
            }
            Iterator it2 = arrayList3.iterator();
            while (arrayList3.size() > i8) {
                if (!((SliceItem) it2.next()).hasAnyHints("title", "large")) {
                    it2.remove();
                }
            }
            arrayList = arrayList3;
        }
        int i9 = 0;
        int i10 = 0;
        SliceItem sliceItem2 = null;
        int i11 = 0;
        boolean z2 = false;
        while (i11 < cellItems.size()) {
            SliceItem sliceItem3 = cellItems.get(i11);
            String format = sliceItem3.getFormat();
            int determinePadding = determinePadding(sliceItem2);
            if (i10 >= i8 || (!str2.equals(format) && !"long".equals(format))) {
                i5 = i9;
                i4 = i10;
                sliceItem = sliceItem2;
                i3 = i11;
                arrayList2 = arrayList;
                str = str2;
                if (i5 < 1 && "image".equals(sliceItem3.getFormat())) {
                    if (addItem(sliceItem3, this.mTintColor, linearLayout, 0, z)) {
                        i9 = i5 + 1;
                        sliceItem2 = sliceItem3;
                        i10 = i4;
                    }
                }
                i9 = i5;
                i10 = i4;
                sliceItem2 = sliceItem;
                i11 = i3 + 1;
                str2 = str;
                arrayList = arrayList2;
            } else if (arrayList == null || arrayList.contains(sliceItem3)) {
                i5 = i9;
                i4 = i10;
                sliceItem = sliceItem2;
                i3 = i11;
                arrayList2 = arrayList;
                int i12 = determinePadding;
                str = str2;
                if (addItem(sliceItem3, this.mTintColor, linearLayout, i12, z)) {
                    i10 = i4 + 1;
                    sliceItem2 = sliceItem3;
                    i9 = i5;
                }
                i9 = i5;
                i10 = i4;
                sliceItem2 = sliceItem;
                i11 = i3 + 1;
                str2 = str;
                arrayList = arrayList2;
            } else {
                i5 = i9;
                i4 = i10;
                sliceItem = sliceItem2;
                i3 = i11;
                arrayList2 = arrayList;
                str = str2;
                i9 = i5;
                i10 = i4;
                sliceItem2 = sliceItem;
                i11 = i3 + 1;
                str2 = str;
                arrayList = arrayList2;
            }
            z2 = true;
            i11 = i3 + 1;
            str2 = str;
            arrayList = arrayList2;
        }
        if (z2) {
            CharSequence contentDescription = cellContent.getContentDescription();
            if (contentDescription != null) {
                linearLayout.setContentDescription(contentDescription);
            }
            this.mViewContainer.addView(linearLayout, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (i6 != i7 - 1) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
                marginLayoutParams.setMarginEnd(this.mGutter);
                linearLayout.setLayoutParams(marginLayoutParams);
            }
            if (contentIntent != null) {
                EventInfo eventInfo = new EventInfo(getMode(), 1, 1, this.mRowIndex);
                eventInfo.setPosition(2, i6, i7);
                linearLayout.setTag(new Pair(contentIntent, eventInfo));
                makeClickable(linearLayout, true);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: android.widget.TextView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: android.widget.TextView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: android.widget.ImageView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: android.widget.TextView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: android.widget.TextView} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean addItem(androidx.slice.SliceItem r8, int r9, android.view.ViewGroup r10, int r11, boolean r12) {
        /*
            r7 = this;
            java.lang.String r0 = r8.getFormat()
            java.lang.String r1 = "text"
            boolean r1 = r1.equals(r0)
            r2 = 1
            java.lang.String r3 = "long"
            java.lang.String r4 = "large"
            r5 = 0
            r6 = 0
            if (r1 != 0) goto L_0x0087
            boolean r1 = r3.equals(r0)
            if (r1 == 0) goto L_0x001b
            goto L_0x0087
        L_0x001b:
            java.lang.String r11 = "image"
            boolean r11 = r11.equals(r0)
            if (r11 == 0) goto L_0x00ec
            androidx.core.graphics.drawable.IconCompat r11 = r8.getIcon()
            if (r11 == 0) goto L_0x00ec
            androidx.core.graphics.drawable.IconCompat r11 = r8.getIcon()
            android.content.Context r0 = r7.getContext()
            android.graphics.drawable.Drawable r11 = r11.loadDrawable(r0)
            if (r11 == 0) goto L_0x00ec
            android.widget.ImageView r5 = new android.widget.ImageView
            android.content.Context r0 = r7.getContext()
            r5.<init>(r0)
            r5.setImageDrawable(r11)
            boolean r11 = r8.hasHint(r4)
            java.lang.String r0 = "no_tint"
            r1 = -1
            if (r11 == 0) goto L_0x005d
            android.widget.ImageView$ScaleType r11 = android.widget.ImageView.ScaleType.CENTER_CROP
            r5.setScaleType(r11)
            if (r12 == 0) goto L_0x0055
            r7 = r1
            goto L_0x0057
        L_0x0055:
            int r7 = r7.mLargeImageHeight
        L_0x0057:
            android.widget.LinearLayout$LayoutParams r11 = new android.widget.LinearLayout$LayoutParams
            r11.<init>(r1, r7)
            goto L_0x0078
        L_0x005d:
            boolean r11 = r8.hasHint(r0)
            r11 = r11 ^ r2
            if (r11 == 0) goto L_0x0067
            int r7 = r7.mIconSize
            goto L_0x0069
        L_0x0067:
            int r7 = r7.mSmallImageSize
        L_0x0069:
            if (r11 == 0) goto L_0x006e
            android.widget.ImageView$ScaleType r11 = android.widget.ImageView.ScaleType.CENTER_INSIDE
            goto L_0x0070
        L_0x006e:
            android.widget.ImageView$ScaleType r11 = android.widget.ImageView.ScaleType.CENTER_CROP
        L_0x0070:
            r5.setScaleType(r11)
            android.widget.LinearLayout$LayoutParams r11 = new android.widget.LinearLayout$LayoutParams
            r11.<init>(r7, r7)
        L_0x0078:
            if (r9 == r1) goto L_0x0083
            boolean r7 = r8.hasHint(r0)
            if (r7 != 0) goto L_0x0083
            r5.setColorFilter(r9)
        L_0x0083:
            r10.addView(r5, r11)
            goto L_0x00ec
        L_0x0087:
            java.lang.String r9 = "title"
            java.lang.String[] r9 = new java.lang.String[]{r4, r9}
            boolean r9 = androidx.slice.core.SliceQuery.hasAnyHints(r8, r9)
            android.content.Context r12 = r7.getContext()
            android.view.LayoutInflater r12 = android.view.LayoutInflater.from(r12)
            if (r9 == 0) goto L_0x009e
            int r1 = TITLE_TEXT_LAYOUT
            goto L_0x00a0
        L_0x009e:
            int r1 = TEXT_LAYOUT
        L_0x00a0:
            android.view.View r12 = r12.inflate(r1, r5)
            r5 = r12
            android.widget.TextView r5 = (android.widget.TextView) r5
            androidx.slice.widget.SliceStyle r12 = r7.mSliceStyle
            if (r12 == 0) goto L_0x00cc
            if (r9 == 0) goto L_0x00b2
            int r12 = r12.getGridTitleSize()
            goto L_0x00b6
        L_0x00b2:
            int r12 = r12.getGridSubtitleSize()
        L_0x00b6:
            float r12 = (float) r12
            r5.setTextSize(r6, r12)
            if (r9 == 0) goto L_0x00c3
            androidx.slice.widget.SliceStyle r9 = r7.mSliceStyle
            int r9 = r9.getTitleColor()
            goto L_0x00c9
        L_0x00c3:
            androidx.slice.widget.SliceStyle r9 = r7.mSliceStyle
            int r9 = r9.getSubtitleColor()
        L_0x00c9:
            r5.setTextColor(r9)
        L_0x00cc:
            boolean r9 = r3.equals(r0)
            if (r9 == 0) goto L_0x00df
            android.content.Context r7 = r7.getContext()
            long r8 = r8.getLong()
            java.lang.CharSequence r7 = androidx.slice.widget.SliceViewUtil.getTimestampString(r7, r8)
            goto L_0x00e3
        L_0x00df:
            java.lang.CharSequence r7 = r8.getSanitizedText()
        L_0x00e3:
            r5.setText(r7)
            r10.addView(r5)
            r5.setPadding(r6, r11, r6, r6)
        L_0x00ec:
            if (r5 == 0) goto L_0x00ef
            goto L_0x00f0
        L_0x00ef:
            r2 = r6
        L_0x00f0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.GridRowView.addItem(androidx.slice.SliceItem, int, android.view.ViewGroup, int, boolean):boolean");
    }

    private int determinePadding(SliceItem sliceItem) {
        SliceStyle sliceStyle;
        if (sliceItem == null) {
            return 0;
        }
        if ("image".equals(sliceItem.getFormat())) {
            return this.mTextPadding;
        }
        if (("text".equals(sliceItem.getFormat()) || "long".equals(sliceItem.getFormat())) && (sliceStyle = this.mSliceStyle) != null) {
            return sliceStyle.getVerticalGridTextPadding();
        }
        return 0;
    }

    private void makeEntireGridClickable(boolean z) {
        Drawable drawable = null;
        this.mViewContainer.setOnTouchListener(z ? this : null);
        this.mViewContainer.setOnClickListener(z ? this : null);
        View view = this.mForeground;
        if (z) {
            drawable = SliceViewUtil.getDrawable(getContext(), 16843534);
        }
        view.setBackground(drawable);
        this.mViewContainer.setClickable(z);
    }

    private void makeClickable(View view, boolean z) {
        Drawable drawable = null;
        view.setOnClickListener(z ? this : null);
        int i = 16843534;
        if (Build.VERSION.SDK_INT >= 21) {
            i = 16843868;
        }
        if (z) {
            drawable = SliceViewUtil.getDrawable(getContext(), i);
        }
        view.setBackground(drawable);
        view.setClickable(z);
    }

    public void onClick(View view) {
        SliceItem find;
        Pair pair = (Pair) view.getTag();
        SliceItem sliceItem = (SliceItem) pair.first;
        EventInfo eventInfo = (EventInfo) pair.second;
        if (sliceItem != null && (find = SliceQuery.find(sliceItem, "action", (String) null, (String) null)) != null) {
            try {
                find.fireAction((Context) null, (Intent) null);
                if (this.mObserver != null) {
                    this.mObserver.onSliceAction(eventInfo, find);
                }
            } catch (PendingIntent.CanceledException e) {
                Log.e("GridRowView", "PendingIntent for slice cannot be sent", e);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        onForegroundActivated(motionEvent);
        return false;
    }

    private void onForegroundActivated(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mForeground.getLocationOnScreen(this.mLoc);
            this.mForeground.getBackground().setHotspot((float) ((int) (motionEvent.getRawX() - ((float) this.mLoc[0]))), (float) ((int) (motionEvent.getRawY() - ((float) this.mLoc[1]))));
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mForeground.setPressed(true);
        } else if (actionMasked == 3 || actionMasked == 1 || actionMasked == 2) {
            this.mForeground.setPressed(false);
        }
    }

    public void resetView() {
        if (this.mMaxCellUpdateScheduled) {
            this.mMaxCellUpdateScheduled = false;
            getViewTreeObserver().removeOnPreDrawListener(this.mMaxCellsUpdater);
        }
        this.mViewContainer.removeAllViews();
        setLayoutDirection(2);
        makeEntireGridClickable(false);
    }
}
