package com.android.systemui.p006qs;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.metrics.LogMaker;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.Utils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.Dependency;
import com.android.systemui.DumpController;
import com.android.systemui.Dumpable;
import com.android.systemui.p006qs.PagedTileLayout;
import com.android.systemui.p006qs.QSDetail;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.customize.QSCustomizer;
import com.android.systemui.p006qs.external.CustomTile;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.DetailAdapter;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.plugins.p005qs.QSTileView;
import com.android.systemui.settings.BrightnessController;
import com.android.systemui.settings.ToggleSlider;
import com.android.systemui.settings.ToggleSliderView;
import com.android.systemui.statusbar.policy.BrightnessMirrorController;
import com.android.systemui.tuner.TunerService;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.android.systemui.qs.QSPanel */
public class QSPanel extends LinearLayout implements TunerService.Tunable, QSHost.Callback, BrightnessMirrorController.BrightnessMirrorListener, Dumpable {
    protected final ImageView mAutoBrightnessView;
    private BrightnessController mBrightnessController;
    private BrightnessMirrorController mBrightnessMirrorController;
    private int mBrightnessSlider;
    protected final View mBrightnessView;
    private QSDetail.Callback mCallback;
    protected final Context mContext;
    /* access modifiers changed from: private */
    public QSCustomizer mCustomizePanel;
    /* access modifiers changed from: private */
    public Record mDetailRecord;
    private View mDivider;
    private DumpController mDumpController;
    protected boolean mExpanded;
    protected QSSecurityFooter mFooter;
    private PageIndicator mFooterPageIndicator;
    private boolean mGridContentVisible;
    /* access modifiers changed from: private */
    public final C0907H mHandler;
    protected QSTileHost mHost;
    private boolean mIsAutomaticBrightnessAvailable;
    protected boolean mListening;
    private ImageView mMaxBrightness;
    private final MetricsLogger mMetricsLogger;
    private ImageView mMinBrightness;
    private final QSTileRevealController mQsTileRevealController;
    protected final ArrayList<TileRecord> mRecords;
    protected QSTileLayout mTileLayout;
    /* access modifiers changed from: private */
    public final Vibrator mVibrator;

    /* renamed from: com.android.systemui.qs.QSPanel$QSTileLayout */
    public interface QSTileLayout {
        void addTile(TileRecord tileRecord);

        int getNumColumns();

        int getNumVisibleTiles();

        int getOffsetTop(TileRecord tileRecord);

        boolean isShowTitles();

        void removeTile(TileRecord tileRecord);

        void restoreInstanceState(Bundle bundle) {
        }

        void saveInstanceState(Bundle bundle) {
        }

        void setExpansion(float f) {
        }

        void setListening(boolean z);

        boolean updateResources();

        void updateSettings();
    }

    /* renamed from: com.android.systemui.qs.QSPanel$TileRecord */
    public static final class TileRecord extends Record {
        public QSTile.Callback callback;
        public boolean scanState;
        public QSTile tile;
        public QSTileView tileView;
    }

    public QSPanel(Context context) {
        this(context, (AttributeSet) null);
    }

    public QSPanel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (DumpController) null);
    }

    public QSPanel(Context context, AttributeSet attributeSet, DumpController dumpController) {
        super(context, attributeSet);
        this.mRecords = new ArrayList<>();
        this.mHandler = new C0907H();
        this.mMetricsLogger = (MetricsLogger) Dependency.get(MetricsLogger.class);
        this.mIsAutomaticBrightnessAvailable = false;
        this.mGridContentVisible = true;
        this.mBrightnessSlider = 1;
        this.mContext = context;
        final ContentResolver contentResolver = context.getContentResolver();
        this.mVibrator = (Vibrator) this.mContext.getSystemService("vibrator");
        setOrientation(1);
        this.mBrightnessView = LayoutInflater.from(this.mContext).inflate(C1779R$layout.quick_settings_brightness_dialog, this, false);
        this.mTileLayout = (QSTileLayout) LayoutInflater.from(this.mContext).inflate(C1779R$layout.qs_paged_tile_layout, this, false);
        this.mTileLayout.setListening(this.mListening);
        this.mQsTileRevealController = new QSTileRevealController(this.mContext, this, (PagedTileLayout) this.mTileLayout);
        this.mMinBrightness = (ImageView) this.mBrightnessView.findViewById(C1777R$id.brightness_left);
        this.mMinBrightness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int intForUser = Settings.System.getIntForUser(contentResolver, "screen_brightness", 0, -2);
                int i = intForUser - 2;
                if (intForUser != 0) {
                    Settings.System.putIntForUser(contentResolver, "screen_brightness", Math.max(0, i), -2);
                }
            }
        });
        this.mMinBrightness.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                QSPanel.this.setBrightnessMinMax(true);
                QSPanel.this.mVibrator.vibrate(VibrationEffect.createOneShot(50, -1));
                return true;
            }
        });
        this.mMaxBrightness = (ImageView) this.mBrightnessView.findViewById(C1777R$id.brightness_right);
        this.mMaxBrightness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int intForUser = Settings.System.getIntForUser(contentResolver, "screen_brightness", 0, -2);
                int i = intForUser + 2;
                if (intForUser != 255) {
                    Settings.System.putIntForUser(contentResolver, "screen_brightness", Math.min(255, i), -2);
                }
            }
        });
        this.mMaxBrightness.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                QSPanel.this.setBrightnessMinMax(false);
                QSPanel.this.mVibrator.vibrate(VibrationEffect.createOneShot(50, -1));
                return true;
            }
        });
        this.mFooter = new QSSecurityFooter(this, context);
        addQSPanel();
        this.mBrightnessController = new BrightnessController(context, (ImageView) findViewById(C1777R$id.brightness_icon), (ToggleSlider) findViewById(C1777R$id.brightness_slider));
        this.mDumpController = dumpController;
        this.mAutoBrightnessView = (ImageView) findViewById(C1777R$id.brightness_icon);
        this.mIsAutomaticBrightnessAvailable = getResources().getBoolean(17891369);
        updateSettings();
    }

    private void addQSPanel() {
        if (this.mBrightnessSlider == 1) {
            addView(this.mBrightnessView);
            addView((View) this.mTileLayout);
        } else {
            addView((View) this.mTileLayout);
            addView(this.mBrightnessView);
        }
        addDivider();
        addView(this.mFooter.getView());
        updateResources();
    }

    private void restartQSPanel() {
        if (this.mFooter.getView() != null) {
            removeView(this.mFooter.getView());
        }
        View view = this.mDivider;
        if (view != null) {
            removeView(view);
        }
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (((View) qSTileLayout) != null) {
            removeView((View) qSTileLayout);
        }
        View view2 = this.mBrightnessView;
        if (view2 != null) {
            removeView(view2);
        }
        addQSPanel();
    }

    /* access modifiers changed from: protected */
    public void addDivider() {
        this.mDivider = LayoutInflater.from(this.mContext).inflate(C1779R$layout.qs_divider, this, false);
        View view = this.mDivider;
        view.setBackgroundColor(Utils.applyAlpha(view.getAlpha(), QSTileImpl.getColorForState(this.mContext, 2)));
        addView(this.mDivider);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                paddingBottom += childAt.getMeasuredHeight();
            }
        }
        setMeasuredDimension(getMeasuredWidth(), paddingBottom);
    }

    public View getDivider() {
        return this.mDivider;
    }

    public QSTileRevealController getQsTileRevealController() {
        return this.mQsTileRevealController;
    }

    public boolean isShowingCustomize() {
        QSCustomizer qSCustomizer = this.mCustomizePanel;
        return qSCustomizer != null && qSCustomizer.isCustomizing();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TunerService tunerService = (TunerService) Dependency.get(TunerService.class);
        tunerService.addTunable(this, "qs_show_auto_brightness");
        tunerService.addTunable(this, "qs_show_brightness_slider");
        tunerService.addTunable(this, "qs_show_brightness_buttons");
        QSTileHost qSTileHost = this.mHost;
        if (qSTileHost != null) {
            setTiles(qSTileHost.getTiles());
        }
        BrightnessMirrorController brightnessMirrorController = this.mBrightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.addCallback((BrightnessMirrorController.BrightnessMirrorListener) this);
        }
        DumpController dumpController = this.mDumpController;
        if (dumpController != null) {
            dumpController.addListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ((TunerService) Dependency.get(TunerService.class)).removeTunable(this);
        QSTileHost qSTileHost = this.mHost;
        if (qSTileHost != null) {
            qSTileHost.removeCallback(this);
        }
        Iterator<TileRecord> it = this.mRecords.iterator();
        while (it.hasNext()) {
            it.next().tile.removeCallbacks();
        }
        BrightnessMirrorController brightnessMirrorController = this.mBrightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.removeCallback((BrightnessMirrorController.BrightnessMirrorListener) this);
        }
        DumpController dumpController = this.mDumpController;
        if (dumpController != null) {
            dumpController.removeListener(this);
        }
        super.onDetachedFromWindow();
    }

    public void onTilesChanged() {
        setTiles(this.mHost.getTiles());
    }

    public void onTuningChanged(String str, String str2) {
        if ("qs_show_auto_brightness".equals(str) && this.mIsAutomaticBrightnessAvailable) {
            updateViewVisibilityForTuningValue(this.mAutoBrightnessView, str2);
        } else if ("qs_show_brightness_slider".equals(str)) {
            this.mBrightnessSlider = TunerService.parseInteger(str2, 1);
            this.mBrightnessView.setVisibility(this.mBrightnessSlider != 0 ? 0 : 8);
            restartQSPanel();
        } else if ("qs_show_brightness_buttons".equals(str)) {
            updateViewVisibilityForTuningValue(this.mMinBrightness, str2);
            updateViewVisibilityForTuningValue(this.mMaxBrightness, str2);
        }
    }

    private void updateViewVisibilityForTuningValue(View view, String str) {
        view.setVisibility(TunerService.parseIntegerSwitch(str, true) ? 0 : 8);
    }

    public void openDetails(String str) {
        QSTile tile = getTile(str);
        if (tile != null) {
            showDetailAdapter(true, tile.getDetailAdapter(), new int[]{getWidth() / 2, 0});
        }
    }

    private QSTile getTile(String str) {
        for (int i = 0; i < this.mRecords.size(); i++) {
            if (str.equals(this.mRecords.get(i).tile.getTileSpec())) {
                return this.mRecords.get(i).tile;
            }
        }
        return this.mHost.createTile(str);
    }

    public void setBrightnessMirror(BrightnessMirrorController brightnessMirrorController) {
        BrightnessMirrorController brightnessMirrorController2 = this.mBrightnessMirrorController;
        if (brightnessMirrorController2 != null) {
            brightnessMirrorController2.removeCallback((BrightnessMirrorController.BrightnessMirrorListener) this);
        }
        this.mBrightnessMirrorController = brightnessMirrorController;
        BrightnessMirrorController brightnessMirrorController3 = this.mBrightnessMirrorController;
        if (brightnessMirrorController3 != null) {
            brightnessMirrorController3.addCallback((BrightnessMirrorController.BrightnessMirrorListener) this);
        }
        updateBrightnessMirror();
    }

    public void onBrightnessMirrorReinflated(View view) {
        updateBrightnessMirror();
    }

    /* access modifiers changed from: package-private */
    public View getBrightnessView() {
        return this.mBrightnessView;
    }

    public void setCallback(QSDetail.Callback callback) {
        this.mCallback = callback;
    }

    public void setHost(QSTileHost qSTileHost, QSCustomizer qSCustomizer) {
        this.mHost = qSTileHost;
        this.mHost.addCallback(this);
        setTiles(this.mHost.getTiles());
        this.mFooter.setHostEnvironment(qSTileHost);
        this.mCustomizePanel = qSCustomizer;
        QSCustomizer qSCustomizer2 = this.mCustomizePanel;
        if (qSCustomizer2 != null) {
            qSCustomizer2.setHost(this.mHost);
        }
    }

    public void setFooterPageIndicator(PageIndicator pageIndicator) {
        if (this.mTileLayout instanceof PagedTileLayout) {
            this.mFooterPageIndicator = pageIndicator;
            updatePageIndicator();
        }
    }

    private void updatePageIndicator() {
        PageIndicator pageIndicator;
        if ((this.mTileLayout instanceof PagedTileLayout) && (pageIndicator = this.mFooterPageIndicator) != null) {
            pageIndicator.setVisibility(8);
            ((PagedTileLayout) this.mTileLayout).setPageIndicator(this.mFooterPageIndicator);
        }
    }

    public QSTileHost getHost() {
        return this.mHost;
    }

    public void updateResources() {
        Resources resources = this.mContext.getResources();
        setPadding(0, resources.getDimensionPixelSize(C1775R$dimen.qs_panel_padding_top), 0, resources.getDimensionPixelSize(C1775R$dimen.qs_panel_padding_bottom));
        updatePageIndicator();
        if (this.mListening) {
            refreshAllTiles();
        }
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout != null) {
            qSTileLayout.updateResources();
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFooter.onConfigurationChanged();
        updateResources();
        updateBrightnessMirror();
    }

    public void updateBrightnessMirror() {
        if (this.mBrightnessMirrorController != null) {
            ToggleSliderView toggleSliderView = (ToggleSliderView) findViewById(C1777R$id.brightness_slider);
            toggleSliderView.setMirror((ToggleSliderView) this.mBrightnessMirrorController.getMirror().findViewById(C1777R$id.brightness_slider));
            toggleSliderView.setMirrorController(this.mBrightnessMirrorController);
        }
    }

    public void setExpanded(boolean z) {
        if (this.mExpanded != z) {
            this.mExpanded = z;
            if (!this.mExpanded) {
                QSTileLayout qSTileLayout = this.mTileLayout;
                if (qSTileLayout instanceof PagedTileLayout) {
                    ((PagedTileLayout) qSTileLayout).setCurrentItem(0, false);
                }
            }
            this.mMetricsLogger.visibility(111, this.mExpanded);
            if (!this.mExpanded) {
                closeDetail();
            } else {
                logTiles();
            }
        }
    }

    public void setPageListener(PagedTileLayout.PageListener pageListener) {
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout instanceof PagedTileLayout) {
            ((PagedTileLayout) qSTileLayout).setPageListener(pageListener);
        }
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public void setListening(boolean z) {
        if (this.mListening != z) {
            this.mListening = z;
            QSTileLayout qSTileLayout = this.mTileLayout;
            if (qSTileLayout != null) {
                qSTileLayout.setListening(z);
            }
            if (this.mListening) {
                refreshAllTiles();
            }
        }
    }

    public void setListening(boolean z, boolean z2) {
        setListening(z && z2);
        getFooter().setListening(z);
        setBrightnessListening(z);
    }

    public void setBrightnessListening(boolean z) {
        if (z) {
            this.mBrightnessController.registerCallbacks();
        } else {
            this.mBrightnessController.unregisterCallbacks();
        }
    }

    public void refreshAllTiles() {
        this.mBrightnessController.checkRestrictionAndSetEnabled();
        Iterator<TileRecord> it = this.mRecords.iterator();
        while (it.hasNext()) {
            it.next().tile.refreshState();
        }
        this.mFooter.refreshState();
    }

    public void showDetailAdapter(boolean z, DetailAdapter detailAdapter, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        ((View) getParent()).getLocationInWindow(iArr);
        Record record = new Record();
        record.detailAdapter = detailAdapter;
        record.f58x = i - iArr[0];
        record.f59y = i2 - iArr[1];
        iArr[0] = i;
        iArr[1] = i2;
        showDetail(z, record);
    }

    /* access modifiers changed from: protected */
    public void showDetail(boolean z, Record record) {
        this.mHandler.obtainMessage(1, z ? 1 : 0, 0, record).sendToTarget();
    }

    public void setTiles(Collection<QSTile> collection) {
        setTiles(collection, false);
    }

    public void setTiles(Collection<QSTile> collection, boolean z) {
        if (!z) {
            this.mQsTileRevealController.updateRevealedTiles(collection);
        }
        Iterator<TileRecord> it = this.mRecords.iterator();
        while (it.hasNext()) {
            TileRecord next = it.next();
            this.mTileLayout.removeTile(next);
            next.tile.removeCallback(next.callback);
        }
        this.mRecords.clear();
        for (QSTile addTile : collection) {
            addTile(addTile, z);
        }
    }

    /* access modifiers changed from: protected */
    public void drawTile(TileRecord tileRecord, QSTile.State state) {
        tileRecord.tileView.onStateChanged(state);
    }

    /* access modifiers changed from: protected */
    public QSTileView createTileView(QSTile qSTile, boolean z) {
        return this.mHost.createTileView(qSTile, z);
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowDetail() {
        return this.mExpanded;
    }

    /* access modifiers changed from: protected */
    public TileRecord addTile(QSTile qSTile, boolean z) {
        final TileRecord tileRecord = new TileRecord();
        tileRecord.tile = qSTile;
        tileRecord.tileView = createTileView(qSTile, z);
        C09055 r2 = new QSTile.Callback() {
            public void onStateChanged(QSTile.State state) {
                QSPanel.this.drawTile(tileRecord, state);
            }

            public void onShowDetail(boolean z) {
                if (QSPanel.this.shouldShowDetail()) {
                    QSPanel.this.showDetail(z, tileRecord);
                }
            }

            public void onToggleStateChanged(boolean z) {
                if (QSPanel.this.mDetailRecord == tileRecord) {
                    QSPanel.this.fireToggleStateChanged(z);
                }
            }

            public void onScanStateChanged(boolean z) {
                tileRecord.scanState = z;
                Record access$300 = QSPanel.this.mDetailRecord;
                TileRecord tileRecord = tileRecord;
                if (access$300 == tileRecord) {
                    QSPanel.this.fireScanStateChanged(tileRecord.scanState);
                }
            }

            public void onAnnouncementRequested(CharSequence charSequence) {
                if (charSequence != null) {
                    QSPanel.this.mHandler.obtainMessage(3, charSequence).sendToTarget();
                }
            }
        };
        tileRecord.tile.addCallback(r2);
        tileRecord.callback = r2;
        tileRecord.tileView.init(tileRecord.tile);
        tileRecord.tile.refreshState();
        this.mRecords.add(tileRecord);
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout != null) {
            qSTileLayout.addTile(tileRecord);
            tileClickListener(tileRecord.tile, tileRecord.tileView);
        }
        return tileRecord;
    }

    public void showEdit(final View view) {
        view.post(new Runnable() {
            public void run() {
                if (QSPanel.this.mCustomizePanel != null && !QSPanel.this.mCustomizePanel.isCustomizing()) {
                    int[] locationOnScreen = view.getLocationOnScreen();
                    QSPanel.this.mCustomizePanel.show(locationOnScreen[0] + (view.getWidth() / 2), (locationOnScreen[1] + (view.getHeight() / 2)) - QSPanel.this.getTop());
                }
            }
        });
    }

    public void closeDetail() {
        QSCustomizer qSCustomizer = this.mCustomizePanel;
        if (qSCustomizer == null || !qSCustomizer.isShown()) {
            showDetail(false, this.mDetailRecord);
        } else {
            this.mCustomizePanel.hide();
        }
    }

    /* access modifiers changed from: protected */
    public void handleShowDetail(Record record, boolean z) {
        int i;
        if (record instanceof TileRecord) {
            handleShowDetailTile((TileRecord) record, z);
            return;
        }
        int i2 = 0;
        if (record != null) {
            i2 = record.f58x;
            i = record.f59y;
        } else {
            i = 0;
        }
        handleShowDetailImpl(record, z, i2, i);
    }

    private void handleShowDetailTile(TileRecord tileRecord, boolean z) {
        if ((this.mDetailRecord != null) != z || this.mDetailRecord != tileRecord) {
            if (z) {
                tileRecord.detailAdapter = tileRecord.tile.getDetailAdapter();
                if (tileRecord.detailAdapter == null) {
                    return;
                }
            }
            tileRecord.tile.setDetailListening(z);
            handleShowDetailImpl(tileRecord, z, tileRecord.tileView.getLeft() + (tileRecord.tileView.getWidth() / 2), tileRecord.tileView.getDetailY() + this.mTileLayout.getOffsetTop(tileRecord));
        }
    }

    private void handleShowDetailImpl(Record record, boolean z, int i, int i2) {
        DetailAdapter detailAdapter = null;
        setDetailRecord(z ? record : null);
        if (z) {
            detailAdapter = record.detailAdapter;
        }
        fireShowingDetail(detailAdapter, i, i2);
    }

    /* access modifiers changed from: protected */
    public void setDetailRecord(Record record) {
        if (record != this.mDetailRecord) {
            this.mDetailRecord = record;
            Record record2 = this.mDetailRecord;
            fireScanStateChanged((record2 instanceof TileRecord) && ((TileRecord) record2).scanState);
        }
    }

    /* access modifiers changed from: package-private */
    public void setGridContentVisibility(boolean z) {
        int i = z ? 0 : 4;
        setVisibility(i);
        if (this.mGridContentVisible != z) {
            this.mMetricsLogger.visibility(111, i);
        }
        this.mGridContentVisible = z;
    }

    private void logTiles() {
        for (int i = 0; i < this.mRecords.size(); i++) {
            QSTile qSTile = this.mRecords.get(i).tile;
            this.mMetricsLogger.write(qSTile.populate(new LogMaker(qSTile.getMetricsCategory()).setType(1)));
        }
    }

    private void fireShowingDetail(DetailAdapter detailAdapter, int i, int i2) {
        QSDetail.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onShowingDetail(detailAdapter, i, i2);
        }
    }

    /* access modifiers changed from: private */
    public void fireToggleStateChanged(boolean z) {
        QSDetail.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onToggleStateChanged(z);
        }
    }

    /* access modifiers changed from: private */
    public void fireScanStateChanged(boolean z) {
        QSDetail.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onScanStateChanged(z);
        }
    }

    public void clickTile(ComponentName componentName) {
        String spec = CustomTile.toSpec(componentName);
        int size = this.mRecords.size();
        for (int i = 0; i < size; i++) {
            if (this.mRecords.get(i).tile.getTileSpec().equals(spec)) {
                this.mRecords.get(i).tile.click();
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public QSTileLayout getTileLayout() {
        return this.mTileLayout;
    }

    /* access modifiers changed from: package-private */
    public QSTileView getTileView(QSTile qSTile) {
        Iterator<TileRecord> it = this.mRecords.iterator();
        while (it.hasNext()) {
            TileRecord next = it.next();
            if (next.tile == qSTile) {
                return next.tileView;
            }
        }
        return null;
    }

    public QSSecurityFooter getFooter() {
        return this.mFooter;
    }

    public void showDeviceMonitoringDialog() {
        this.mFooter.showDeviceMonitoringDialog();
    }

    public void setMargins(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt != this.mTileLayout) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                layoutParams.leftMargin = i;
                layoutParams.rightMargin = i;
            }
        }
    }

    /* renamed from: com.android.systemui.qs.QSPanel$H */
    private class C0907H extends Handler {
        private C0907H() {
        }

        public void handleMessage(Message message) {
            int i = message.what;
            boolean z = true;
            if (i == 1) {
                QSPanel qSPanel = QSPanel.this;
                Record record = (Record) message.obj;
                if (message.arg1 == 0) {
                    z = false;
                }
                qSPanel.handleShowDetail(record, z);
            } else if (i == 3) {
                QSPanel.this.announceForAccessibility((CharSequence) message.obj);
            }
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println(getClass().getSimpleName() + ":");
        printWriter.println("  Tile records:");
        Iterator<TileRecord> it = this.mRecords.iterator();
        while (it.hasNext()) {
            TileRecord next = it.next();
            if (next.tile instanceof Dumpable) {
                printWriter.print("    ");
                ((Dumpable) next.tile).dump(fileDescriptor, printWriter, strArr);
                printWriter.print("    ");
                printWriter.println(next.tileView.toString());
            }
        }
    }

    /* renamed from: com.android.systemui.qs.QSPanel$Record */
    protected static class Record {
        DetailAdapter detailAdapter;

        /* renamed from: x */
        int f58x;

        /* renamed from: y */
        int f59y;

        protected Record() {
        }
    }

    private void setAnimationTile(QSTileView qSTileView) {
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "anim_tile_style", 0, -2);
        int intForUser2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "anim_tile_duration", 2000, -2);
        int intForUser3 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "anim_tile_interpolator", 0, -2);
        ObjectAnimator ofFloat = intForUser == 1 ? ObjectAnimator.ofFloat(qSTileView, "rotationY", new float[]{0.0f, 360.0f}) : null;
        if (intForUser == 2) {
            ofFloat = ObjectAnimator.ofFloat(qSTileView, "rotation", new float[]{0.0f, 360.0f});
        }
        if (ofFloat != null) {
            switch (intForUser3) {
                case 0:
                    ofFloat.setInterpolator(new LinearInterpolator());
                    break;
                case 1:
                    ofFloat.setInterpolator(new AccelerateInterpolator());
                    break;
                case 2:
                    ofFloat.setInterpolator(new DecelerateInterpolator());
                    break;
                case 3:
                    ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
                    break;
                case 4:
                    ofFloat.setInterpolator(new BounceInterpolator());
                    break;
                case 5:
                    ofFloat.setInterpolator(new OvershootInterpolator());
                    break;
                case 6:
                    ofFloat.setInterpolator(new AnticipateInterpolator());
                    break;
                case 7:
                    ofFloat.setInterpolator(new AnticipateOvershootInterpolator());
                    break;
            }
            ofFloat.setDuration((long) intForUser2);
            ofFloat.start();
        }
    }

    private void configureTile(QSTile qSTile, QSTileView qSTileView) {
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout != null) {
            qSTileView.setHideLabel(!qSTileLayout.isShowTitles());
            if (!qSTile.isDualTarget()) {
                return;
            }
            if (!this.mTileLayout.isShowTitles()) {
                qSTileView.setOnLongClickListener(new View.OnLongClickListener(qSTile) {
                    private final /* synthetic */ QSTile f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean onLongClick(View view) {
                        return QSPanel.this.lambda$configureTile$0$QSPanel(this.f$1, view);
                    }
                });
            } else {
                qSTileView.setOnLongClickListener(new View.OnLongClickListener() {
                    public final boolean onLongClick(View view) {
                        return QSTile.this.longClick();
                    }
                });
            }
        }
    }

    public /* synthetic */ boolean lambda$configureTile$0$QSPanel(QSTile qSTile, View view) {
        qSTile.secondaryClick();
        this.mHost.openPanels();
        return true;
    }

    private void tileClickListener(QSTile qSTile, QSTileView qSTileView) {
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout != null) {
            qSTileView.setHideLabel(!qSTileLayout.isShowTitles());
            qSTileView.setOnClickListener(new View.OnClickListener(qSTile, qSTileView) {
                private final /* synthetic */ QSTile f$1;
                private final /* synthetic */ QSTileView f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    QSPanel.this.lambda$tileClickListener$2$QSPanel(this.f$1, this.f$2, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$tileClickListener$2$QSPanel(QSTile qSTile, QSTileView qSTileView, View view) {
        qSTile.click();
        setAnimationTile(qSTileView);
    }

    public void updateSettings() {
        QSTileLayout qSTileLayout = this.mTileLayout;
        if (qSTileLayout != null) {
            qSTileLayout.updateSettings();
            Iterator<TileRecord> it = this.mRecords.iterator();
            while (it.hasNext()) {
                TileRecord next = it.next();
                configureTile(next.tile, next.tileView);
            }
        }
    }

    public int getNumColumns() {
        return this.mTileLayout.getNumColumns();
    }

    /* access modifiers changed from: private */
    public void setBrightnessMinMax(boolean z) {
        this.mBrightnessController.setBrightnessFromSliderButtons(z ? 0 : 1023);
    }
}
