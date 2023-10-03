package com.android.systemui.p006qs;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1778R$integer;
import com.android.systemui.p006qs.QSPanel;
import com.android.systemui.plugins.p005qs.QSTileView;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.android.systemui.qs.TileLayout */
public class TileLayout extends ViewGroup implements QSPanel.QSTileLayout {
    protected int mCellHeight;
    protected int mCellMarginHorizontal;
    private int mCellMarginTop;
    protected int mCellMarginVertical;
    protected int mCellWidth;
    protected int mColumns;
    private boolean mListening;
    protected final ArrayList<QSPanel.TileRecord> mRecords;
    protected int mRows;
    protected boolean mShowTitles;
    protected int mSidePadding;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public TileLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public TileLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRows = 1;
        this.mShowTitles = true;
        this.mRecords = new ArrayList<>();
        setFocusableInTouchMode(true);
        updateResources();
    }

    public int getOffsetTop(QSPanel.TileRecord tileRecord) {
        return getTop();
    }

    public void setListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
            Iterator<QSPanel.TileRecord> it = this.mRecords.iterator();
            while (it.hasNext()) {
                it.next().tile.setListening(this, this.mListening);
            }
        }
    }

    public void addTile(QSPanel.TileRecord tileRecord) {
        this.mRecords.add(tileRecord);
        tileRecord.tile.setListening(this, this.mListening);
        addTileView(tileRecord);
    }

    /* access modifiers changed from: protected */
    public void addTileView(QSPanel.TileRecord tileRecord) {
        addView(tileRecord.tileView);
    }

    public void removeTile(QSPanel.TileRecord tileRecord) {
        this.mRecords.remove(tileRecord);
        tileRecord.tile.setListening(this, false);
        removeView(tileRecord.tileView);
    }

    public void removeAllViews() {
        Iterator<QSPanel.TileRecord> it = this.mRecords.iterator();
        while (it.hasNext()) {
            it.next().tile.setListening(this, false);
        }
        this.mRecords.clear();
        super.removeAllViews();
    }

    public boolean updateResources() {
        Resources resources = this.mContext.getResources();
        this.mCellHeight = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_tile_height);
        this.mCellMarginHorizontal = resources.getDimensionPixelSize(C1775R$dimen.qs_tile_margin_horizontal);
        this.mCellMarginVertical = resources.getDimensionPixelSize(C1775R$dimen.qs_tile_margin_vertical);
        this.mCellMarginTop = resources.getDimensionPixelSize(C1775R$dimen.qs_tile_margin_top);
        this.mSidePadding = resources.getDimensionPixelOffset(C1775R$dimen.qs_tile_layout_margin_side);
        updateSettings();
        return false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = this.mRecords.size();
        int size2 = View.MeasureSpec.getSize(i);
        int paddingStart = (size2 - getPaddingStart()) - getPaddingEnd();
        if (View.MeasureSpec.getMode(i2) == 0) {
            int i3 = this.mColumns;
            this.mRows = ((size + i3) - 1) / i3;
        }
        int i4 = paddingStart - (this.mSidePadding * 2);
        int i5 = this.mCellMarginHorizontal;
        int i6 = this.mColumns;
        this.mCellWidth = (i4 - (i5 * i6)) / i6;
        Iterator<QSPanel.TileRecord> it = this.mRecords.iterator();
        View view = this;
        while (it.hasNext()) {
            QSPanel.TileRecord next = it.next();
            if (next.tileView.getVisibility() != 8) {
                next.tileView.measure(exactly(this.mCellWidth), exactly(this.mCellHeight));
                view = next.tileView.updateAccessibilityOrder(view);
            }
        }
        int i7 = this.mCellHeight;
        int i8 = this.mCellMarginVertical;
        int i9 = this.mRows;
        int i10 = ((i7 + i8) * i9) + (i9 != 0 ? this.mCellMarginTop - i8 : 0);
        if (i10 < 0) {
            i10 = 0;
        }
        setMeasuredDimension(size2, i10);
    }

    public boolean updateMaxRows(int i, int i2) {
        View.MeasureSpec.getSize(i);
        int i3 = this.mRows;
        int i4 = this.mColumns;
        if (i3 > ((i2 + i4) - 1) / i4) {
            this.mRows = ((i2 + i4) - 1) / i4;
        }
        if (i3 != this.mRows) {
            return true;
        }
        return false;
    }

    protected static int exactly(int i) {
        return View.MeasureSpec.makeMeasureSpec(i, 1073741824);
    }

    /* access modifiers changed from: protected */
    public void layoutTileRecords(int i) {
        boolean z = getLayoutDirection() == 1;
        int min = Math.min(i, this.mRows * this.mColumns);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < min) {
            if (i3 == this.mColumns) {
                i4++;
                i3 = 0;
            }
            QSPanel.TileRecord tileRecord = this.mRecords.get(i2);
            int rowTop = getRowTop(i4);
            int columnStart = getColumnStart(z ? (this.mColumns - i3) - 1 : i3);
            QSTileView qSTileView = tileRecord.tileView;
            qSTileView.layout(columnStart, rowTop, this.mCellWidth + columnStart, qSTileView.getMeasuredHeight() + rowTop);
            i2++;
            i3++;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutTileRecords(this.mRecords.size());
    }

    private int getRowTop(int i) {
        return (i * (this.mCellHeight + this.mCellMarginVertical)) + this.mCellMarginTop;
    }

    /* access modifiers changed from: protected */
    public int getColumnStart(int i) {
        int paddingStart = getPaddingStart() + this.mSidePadding;
        int i2 = this.mCellMarginHorizontal;
        return paddingStart + (i2 / 2) + (i * (this.mCellWidth + i2));
    }

    public int getNumVisibleTiles() {
        return this.mRecords.size();
    }

    public void updateSettings() {
        Resources resources = this.mContext.getResources();
        boolean z = true;
        int max = Math.max(1, resources.getInteger(C1778R$integer.quick_settings_num_columns));
        boolean z2 = resources.getConfiguration().orientation == 1;
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_columns", max, -2);
        int intForUser2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_columns_landscape", max, -2);
        int intForUser3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_rows", 3, -2);
        int intForUser4 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_layout_rows_landscape", 2, -2);
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_tile_title_visibility", 1, -2) != 1) {
            z = false;
        }
        if (z) {
            this.mCellHeight = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_tile_height);
        } else {
            this.mCellHeight = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_tile_height_wo_label);
        }
        if (!(this.mColumns == (z2 ? intForUser : intForUser2) && this.mShowTitles == z)) {
            if (z2) {
                intForUser2 = intForUser;
            }
            this.mColumns = intForUser2;
            this.mShowTitles = z;
            requestLayout();
        }
        if (this.mRows != (z2 ? intForUser3 : intForUser4) || this.mShowTitles != z) {
            if (!z2) {
                intForUser3 = intForUser4;
            }
            this.mRows = intForUser3;
            this.mShowTitles = z;
            requestLayout();
        }
    }

    public int getNumColumns() {
        return this.mColumns;
    }

    public boolean isShowTitles() {
        return this.mShowTitles;
    }
}
