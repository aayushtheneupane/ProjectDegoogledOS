package com.android.systemui.p006qs;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1778R$integer;
import com.android.systemui.DumpController;
import com.android.systemui.p006qs.QSPanel;
import com.android.systemui.p006qs.customize.QSCustomizer;
import com.android.systemui.plugins.p005qs.QSTile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.android.systemui.qs.QuickQSPanel */
public class QuickQSPanel extends QSPanel {
    private static int mDefaultMaxTiles;
    private boolean mDisabledByPolicy;
    protected QSPanel mFullPanel;
    private int mMaxTiles;

    /* access modifiers changed from: protected */
    public void addDivider() {
    }

    public void setPadding(int i, int i2, int i3, int i4) {
    }

    public QuickQSPanel(Context context, AttributeSet attributeSet, DumpController dumpController) {
        super(context, attributeSet, dumpController);
        QSSecurityFooter qSSecurityFooter = this.mFooter;
        if (qSSecurityFooter != null) {
            removeView(qSSecurityFooter.getView());
        }
        if (this.mTileLayout != null) {
            for (int i = 0; i < this.mRecords.size(); i++) {
                this.mTileLayout.removeTile(this.mRecords.get(i));
            }
            removeView((View) this.mTileLayout);
        }
        mDefaultMaxTiles = getResources().getInteger(C1778R$integer.quick_qs_panel_max_columns);
        this.mTileLayout = new HeaderTileLayout(context, this);
        this.mTileLayout.setListening(this.mListening);
        addView((View) this.mTileLayout, 0);
        super.setPadding(0, 0, 0, 0);
        updateSettings();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setQSPanelAndHeader(QSPanel qSPanel, View view) {
        this.mFullPanel = qSPanel;
        updateSettings();
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowDetail() {
        return !this.mExpanded;
    }

    /* access modifiers changed from: protected */
    public void drawTile(QSPanel.TileRecord tileRecord, QSTile.State state) {
        if (state instanceof QSTile.SignalState) {
            QSTile.SignalState signalState = new QSTile.SignalState();
            state.copyTo(signalState);
            signalState.activityIn = false;
            signalState.activityOut = false;
            state = signalState;
        }
        super.drawTile(tileRecord, state);
    }

    public void setHost(QSTileHost qSTileHost, QSCustomizer qSCustomizer) {
        super.setHost(qSTileHost, qSCustomizer);
        setTiles(this.mHost.getTiles());
    }

    private void setMaxTiles(int i) {
        this.mMaxTiles = i;
        QSTileHost qSTileHost = this.mHost;
        if (qSTileHost != null) {
            setTiles(qSTileHost.getTiles());
        }
    }

    public void onTuningChanged(String str, String str2) {
        if ("qs_show_brightness_slider".equals(str)) {
            super.onTuningChanged(str, "0");
        }
    }

    public void setTiles(Collection<QSTile> collection) {
        ArrayList arrayList = new ArrayList();
        for (QSTile add : collection) {
            arrayList.add(add);
            if (arrayList.size() == this.mMaxTiles) {
                break;
            }
        }
        super.setTiles(arrayList, true);
    }

    public int getNumQuickTiles() {
        return this.mMaxTiles;
    }

    /* access modifiers changed from: package-private */
    public void setDisabledByPolicy(boolean z) {
        if (z != this.mDisabledByPolicy) {
            this.mDisabledByPolicy = z;
            setVisibility(z ? 8 : 0);
        }
    }

    public void setVisibility(int i) {
        if (this.mDisabledByPolicy) {
            if (getVisibility() != 8) {
                i = 8;
            } else {
                return;
            }
        }
        super.setVisibility(i);
    }

    public int getNumColumns() {
        QSPanel qSPanel = this.mFullPanel;
        if (qSPanel != null) {
            return qSPanel.getNumColumns();
        }
        return 6;
    }

    public void updateSettings() {
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "qs_quickbar_columns", 6, -2);
        if (intForUser == -1) {
            setMaxTiles(Math.max(6, getNumColumns()));
        } else {
            setMaxTiles(Math.max(6, intForUser));
        }
    }

    public void updateResources() {
        QSPanel.QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout != null) {
            qSTileLayout.updateResources();
        }
    }

    /* renamed from: com.android.systemui.qs.QuickQSPanel$HeaderTileLayout */
    private static class HeaderTileLayout extends TileLayout {
        private Rect mClippingBounds = new Rect();
        private QuickQSPanel mPanel;

        public boolean isShowTitles() {
            return false;
        }

        public HeaderTileLayout(Context context, QuickQSPanel quickQSPanel) {
            super(context);
            this.mPanel = quickQSPanel;
            setClipChildren(false);
            setClipToPadding(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 1;
            setLayoutParams(layoutParams);
        }

        /* access modifiers changed from: protected */
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            updateResources();
        }

        public void onFinishInflate() {
            updateResources();
        }

        private ViewGroup.LayoutParams generateTileLayoutParams() {
            return new ViewGroup.LayoutParams(this.mCellWidth, this.mCellHeight);
        }

        /* access modifiers changed from: protected */
        public void addTileView(QSPanel.TileRecord tileRecord) {
            addView(tileRecord.tileView, getChildCount(), generateTileLayoutParams());
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            this.mClippingBounds.set(0, 0, i3 - i, 10000);
            setClipBounds(this.mClippingBounds);
            calculateColumns();
            int i5 = 0;
            while (i5 < this.mRecords.size()) {
                this.mRecords.get(i5).tileView.setVisibility(i5 < this.mColumns ? 0 : 8);
                i5++;
            }
            setAccessibilityOrder();
            layoutTileRecords(this.mColumns);
        }

        public boolean updateResources() {
            this.mCellWidth = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_quick_tile_size);
            this.mCellHeight = this.mCellWidth;
            updateSettings();
            return false;
        }

        private boolean calculateColumns() {
            int i;
            int i2 = this.mColumns;
            int size = this.mRecords.size();
            if (size == 0) {
                this.mColumns = 0;
                return true;
            }
            int measuredWidth = (getMeasuredWidth() - getPaddingStart()) - getPaddingEnd();
            int max = (measuredWidth - (this.mCellWidth * size)) / Math.max(1, size - 1);
            if (max > 0) {
                this.mCellMarginHorizontal = max;
                this.mColumns = size;
            } else {
                int i3 = this.mCellWidth;
                if (i3 == 0) {
                    i = 1;
                } else {
                    i = Math.min(size, measuredWidth / i3);
                }
                this.mColumns = i;
                int i4 = this.mColumns;
                this.mCellMarginHorizontal = (measuredWidth - (this.mCellWidth * i4)) / (i4 - 1);
            }
            if (this.mColumns != i2) {
                return true;
            }
            return false;
        }

        private void setAccessibilityOrder() {
            ArrayList<QSPanel.TileRecord> arrayList = this.mRecords;
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<QSPanel.TileRecord> it = this.mRecords.iterator();
                View view = this;
                while (it.hasNext()) {
                    QSPanel.TileRecord next = it.next();
                    if (next.tileView.getVisibility() != 8) {
                        view = next.tileView.updateAccessibilityOrder(view);
                    }
                }
                ArrayList<QSPanel.TileRecord> arrayList2 = this.mRecords;
                arrayList2.get(arrayList2.size() - 1).tileView.setAccessibilityTraversalBefore(C1777R$id.expand_indicator);
            }
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            Iterator<QSPanel.TileRecord> it = this.mRecords.iterator();
            while (it.hasNext()) {
                QSPanel.TileRecord next = it.next();
                if (next.tileView.getVisibility() != 8) {
                    next.tileView.measure(TileLayout.exactly(this.mCellWidth), TileLayout.exactly(this.mCellHeight));
                }
            }
            int i3 = this.mCellHeight;
            if (i3 < 0) {
                i3 = 0;
            }
            setMeasuredDimension(View.MeasureSpec.getSize(i), i3);
        }

        public int getNumVisibleTiles() {
            return this.mColumns;
        }

        /* access modifiers changed from: protected */
        public int getColumnStart(int i) {
            return getPaddingStart() + (i * (this.mCellWidth + this.mCellMarginHorizontal));
        }
    }
}
