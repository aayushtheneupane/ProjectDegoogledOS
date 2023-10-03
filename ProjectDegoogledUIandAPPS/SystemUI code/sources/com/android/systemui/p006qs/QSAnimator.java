package com.android.systemui.p006qs;

import android.util.Log;
import android.view.View;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.PagedTileLayout;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.QSPanel;
import com.android.systemui.p006qs.TouchAnimator;
import com.android.systemui.plugins.p005qs.C0862QS;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.plugins.p005qs.QSTileView;
import com.android.systemui.tuner.TunerService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.android.systemui.qs.QSAnimator */
public class QSAnimator implements QSHost.Callback, PagedTileLayout.PageListener, TouchAnimator.Listener, View.OnLayoutChangeListener, View.OnAttachStateChangeListener, TunerService.Tunable {
    private final ArrayList<View> mAllViews = new ArrayList<>();
    private boolean mAllowFancy;
    private TouchAnimator mBrightnessAnimator;
    private TouchAnimator mFirstPageAnimator;
    private TouchAnimator mFirstPageDelayedAnimator;
    private boolean mFullRows;
    private QSTileHost mHost;
    private float mLastPosition;
    private final TouchAnimator.Listener mNonFirstPageListener = new TouchAnimator.ListenerAdapter() {
        public void onAnimationAtEnd() {
            QSAnimator.this.mQuickQsPanel.setVisibility(4);
        }

        public void onAnimationStarted() {
            QSAnimator.this.mQuickQsPanel.setVisibility(0);
        }
    };
    private TouchAnimator mNonfirstPageAnimator;
    private TouchAnimator mNonfirstPageDelayedAnimator;
    private int mNumQuickTiles;
    private boolean mOnFirstPage = true;
    private boolean mOnKeyguard;
    private PagedTileLayout mPagedLayout;
    private final C0862QS mQs;
    private final QSPanel mQsPanel;
    /* access modifiers changed from: private */
    public final QuickQSPanel mQuickQsPanel;
    private final ArrayList<View> mQuickQsViews = new ArrayList<>();
    private boolean mShowCollapsedOnKeyguard;
    private TouchAnimator mTranslationXAnimator;
    private TouchAnimator mTranslationYAnimator;
    private Runnable mUpdateAnimators = new Runnable() {
        public void run() {
            QSAnimator.this.updateAnimators();
            QSAnimator.this.setCurrentPosition();
        }
    };

    public QSAnimator(C0862QS qs, QuickQSPanel quickQSPanel, QSPanel qSPanel) {
        this.mQs = qs;
        this.mQuickQsPanel = quickQSPanel;
        this.mQsPanel = qSPanel;
        this.mQsPanel.addOnAttachStateChangeListener(this);
        qs.getView().addOnLayoutChangeListener(this);
        if (this.mQsPanel.isAttachedToWindow()) {
            onViewAttachedToWindow((View) null);
        }
        QSPanel.QSTileLayout tileLayout = this.mQsPanel.getTileLayout();
        if (tileLayout instanceof PagedTileLayout) {
            this.mPagedLayout = (PagedTileLayout) tileLayout;
        } else {
            Log.w("QSAnimator", "QS Not using page layout");
        }
        qSPanel.setPageListener(this);
        updateSettings();
    }

    public void onRtlChanged() {
        updateAnimators();
    }

    public void setOnKeyguard(boolean z) {
        this.mOnKeyguard = z;
        updateQQSVisibility();
        if (this.mOnKeyguard) {
            clearAnimationState();
        }
    }

    /* access modifiers changed from: package-private */
    public void setShowCollapsedOnKeyguard(boolean z) {
        this.mShowCollapsedOnKeyguard = z;
        updateQQSVisibility();
        setCurrentPosition();
    }

    /* access modifiers changed from: private */
    public void setCurrentPosition() {
        setPosition(this.mLastPosition);
    }

    private void updateQQSVisibility() {
        this.mQuickQsPanel.setVisibility((!this.mOnKeyguard || this.mShowCollapsedOnKeyguard) ? 0 : 4);
    }

    public void setHost(QSTileHost qSTileHost) {
        this.mHost = qSTileHost;
        qSTileHost.addCallback(this);
        updateAnimators();
    }

    public void onViewAttachedToWindow(View view) {
        ((TunerService) Dependency.get(TunerService.class)).addTunable(this, "sysui_qs_fancy_anim", "sysui_qs_move_whole_rows");
    }

    public void onViewDetachedFromWindow(View view) {
        QSTileHost qSTileHost = this.mHost;
        if (qSTileHost != null) {
            qSTileHost.removeCallback(this);
        }
        ((TunerService) Dependency.get(TunerService.class)).removeTunable(this);
    }

    public void onTuningChanged(String str, String str2) {
        if ("sysui_qs_fancy_anim".equals(str)) {
            this.mAllowFancy = TunerService.parseIntegerSwitch(str2, true);
            if (!this.mAllowFancy) {
                clearAnimationState();
            }
        } else if ("sysui_qs_move_whole_rows".equals(str)) {
            this.mFullRows = TunerService.parseIntegerSwitch(str2, true);
        }
        updateAnimators();
    }

    public void onPageChanged(boolean z) {
        if (this.mOnFirstPage != z) {
            if (!z) {
                clearAnimationState();
            }
            this.mOnFirstPage = z;
        }
    }

    /* access modifiers changed from: private */
    public void updateAnimators() {
        QSPanel.QSTileLayout qSTileLayout;
        float f;
        int i;
        float f2;
        Collection<QSTile> collection;
        int i2;
        int i3;
        QSPanel.QSTileLayout qSTileLayout2;
        int[] iArr;
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        TouchAnimator.Builder builder2 = new TouchAnimator.Builder();
        TouchAnimator.Builder builder3 = new TouchAnimator.Builder();
        if (this.mQsPanel.getHost() != null) {
            Collection<QSTile> tiles = this.mQsPanel.getHost().getTiles();
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            clearAnimationState();
            this.mAllViews.clear();
            this.mQuickQsViews.clear();
            QSPanel.QSTileLayout tileLayout = this.mQsPanel.getTileLayout();
            this.mAllViews.add((View) tileLayout);
            int measuredHeight = this.mQs.getView() != null ? this.mQs.getView().getMeasuredHeight() : 0;
            int measuredWidth = this.mQs.getView() != null ? this.mQs.getView().getMeasuredWidth() : 0;
            int bottom = (measuredHeight - this.mQs.getHeader().getBottom()) + this.mQs.getHeader().getPaddingBottom();
            float f3 = (float) bottom;
            builder.addFloat(tileLayout, "translationY", f3, 0.0f);
            Iterator<QSTile> it = tiles.iterator();
            int i4 = 0;
            int i5 = 0;
            while (it.hasNext()) {
                QSTile next = it.next();
                Iterator<QSTile> it2 = it;
                QSTileView tileView = this.mQsPanel.getTileView(next);
                if (tileView == null) {
                    Log.e("QSAnimator", "tileView is null " + next.getTileSpec());
                    collection = tiles;
                    i2 = bottom;
                    i = measuredWidth;
                    f2 = f3;
                } else {
                    collection = tiles;
                    View iconView = tileView.getIcon().getIconView();
                    i2 = bottom;
                    View view = this.mQs.getView();
                    f2 = f3;
                    i = measuredWidth;
                    if (i4 >= this.mQuickQsPanel.getTileLayout().getNumVisibleTiles() || !this.mAllowFancy) {
                        qSTileLayout2 = tileLayout;
                        if (!this.mFullRows || !isIconInAnimatedRow(i4)) {
                            iArr = iArr2;
                            builder.addFloat(tileView, "alpha", 0.0f, 1.0f);
                            bottom = i2;
                            builder.addFloat(tileView, "translationY", (float) (-bottom), 0.0f);
                            this.mAllViews.add(tileView);
                            i4++;
                            it = it2;
                            tiles = collection;
                            f3 = f2;
                            i3 = i;
                            iArr2 = iArr;
                            tileLayout = qSTileLayout2;
                        } else {
                            iArr2[0] = iArr2[0] + i5;
                            getRelativePosition(iArr3, iconView, view);
                            iArr = iArr2;
                            builder.addFloat(tileView, "translationY", f2, 0.0f);
                            builder2.addFloat(tileView, "translationX", (float) (-(iArr3[0] - iArr2[0])), 0.0f);
                            float f4 = (float) (-(iArr3[1] - iArr2[1]));
                            builder3.addFloat(tileView, "translationY", f4, 0.0f);
                            builder3.addFloat(iconView, "translationY", f4, 0.0f);
                            this.mAllViews.add(iconView);
                        }
                    } else {
                        QSTileView tileView2 = this.mQuickQsPanel.getTileView(next);
                        if (tileView2 != null) {
                            int i6 = iArr2[0];
                            getRelativePosition(iArr2, tileView2.getIcon().getIconView(), view);
                            getRelativePosition(iArr3, iconView, view);
                            int i7 = iArr3[0] - iArr2[0];
                            int i8 = iArr3[1] - iArr2[1];
                            i5 = iArr2[0] - i6;
                            if (i4 < tileLayout.getNumVisibleTiles()) {
                                builder2.addFloat(tileView2, "translationX", 0.0f, (float) i7);
                                builder3.addFloat(tileView2, "translationY", 0.0f, (float) i8);
                                builder2.addFloat(tileView, "translationX", (float) (-i7), 0.0f);
                                builder3.addFloat(tileView, "translationY", (float) (-i8), 0.0f);
                                qSTileLayout2 = tileLayout;
                            } else {
                                qSTileLayout2 = tileLayout;
                                builder.addFloat(tileView2, "alpha", 1.0f, 0.0f);
                                builder3.addFloat(tileView2, "translationY", 0.0f, (float) i8);
                                builder2.addFloat(tileView2, "translationX", 0.0f, (float) (this.mQsPanel.isLayoutRtl() ? i7 - i : i7 + i));
                            }
                            this.mQuickQsViews.add(tileView.getIconWithBackground());
                            this.mAllViews.add(tileView.getIcon());
                            this.mAllViews.add(tileView2);
                            iArr = iArr2;
                        }
                    }
                    bottom = i2;
                    this.mAllViews.add(tileView);
                    i4++;
                    it = it2;
                    tiles = collection;
                    f3 = f2;
                    i3 = i;
                    iArr2 = iArr;
                    tileLayout = qSTileLayout2;
                }
                it = it2;
                bottom = i2;
                tiles = collection;
                f3 = f2;
                i3 = i;
            }
            Collection<QSTile> collection2 = tiles;
            QSPanel.QSTileLayout qSTileLayout3 = tileLayout;
            float f5 = f3;
            View brightnessView = this.mQsPanel.getBrightnessView();
            if (this.mAllowFancy) {
                if (brightnessView != null) {
                    builder.addFloat(brightnessView, "translationY", f5, 0.0f);
                    TouchAnimator.Builder builder4 = new TouchAnimator.Builder();
                    builder4.addFloat(brightnessView, "alpha", 0.0f, 1.0f);
                    builder4.setStartDelay(0.5f);
                    this.mBrightnessAnimator = builder4.build();
                    this.mAllViews.add(brightnessView);
                } else {
                    this.mBrightnessAnimator = null;
                }
                builder.setListener(this);
                this.mFirstPageAnimator = builder.build();
                TouchAnimator.Builder builder5 = new TouchAnimator.Builder();
                builder5.setStartDelay(0.86f);
                qSTileLayout = qSTileLayout3;
                builder5.addFloat(qSTileLayout, "alpha", 0.0f, 1.0f);
                builder5.addFloat(this.mQsPanel.getDivider(), "alpha", 0.0f, 1.0f);
                builder5.addFloat(this.mQsPanel.getFooter().getView(), "alpha", 0.0f, 1.0f);
                this.mAllViews.add(this.mQsPanel.getDivider());
                this.mAllViews.add(this.mQsPanel.getFooter().getView());
                if (brightnessView != null) {
                    builder5.addFloat(this.mQsPanel.getBrightnessView(), "alpha", 0.0f, 1.0f);
                    this.mAllViews.add(this.mQsPanel.getBrightnessView());
                }
                this.mFirstPageDelayedAnimator = builder5.build();
                if (collection2.size() <= 3) {
                    f = 1.0f;
                } else {
                    f = collection2.size() <= 6 ? 0.4f : 0.0f;
                }
                PathInterpolatorBuilder pathInterpolatorBuilder = new PathInterpolatorBuilder(0.0f, 0.0f, f, 1.0f);
                builder2.setInterpolator(pathInterpolatorBuilder.getXInterpolator());
                builder3.setInterpolator(pathInterpolatorBuilder.getYInterpolator());
                this.mTranslationXAnimator = builder2.build();
                this.mTranslationYAnimator = builder3.build();
            } else {
                qSTileLayout = qSTileLayout3;
            }
            TouchAnimator.Builder builder6 = new TouchAnimator.Builder();
            builder6.addFloat(this.mQuickQsPanel, "alpha", 1.0f, 0.0f);
            builder6.addFloat(this.mQsPanel.getDivider(), "alpha", 0.0f, 1.0f);
            builder6.setListener(this.mNonFirstPageListener);
            builder6.setEndDelay(0.5f);
            if (brightnessView != null) {
                builder6.addFloat(this.mQsPanel.getBrightnessView(), "alpha", 0.0f, 1.0f);
            }
            this.mNonfirstPageAnimator = builder6.build();
            TouchAnimator.Builder builder7 = new TouchAnimator.Builder();
            builder7.setStartDelay(0.14f);
            builder7.addFloat(qSTileLayout, "alpha", 0.0f, 1.0f);
            this.mNonfirstPageDelayedAnimator = builder7.build();
        }
    }

    private boolean isIconInAnimatedRow(int i) {
        PagedTileLayout pagedTileLayout = this.mPagedLayout;
        if (pagedTileLayout == null) {
            return false;
        }
        int columnCount = pagedTileLayout.getColumnCount();
        if (i < (((this.mNumQuickTiles + columnCount) - 1) / columnCount) * columnCount) {
            return true;
        }
        return false;
    }

    private void getRelativePosition(int[] iArr, View view, View view2) {
        iArr[0] = (view.getWidth() / 2) + 0;
        iArr[1] = 0;
        getRelativePositionInt(iArr, view, view2);
    }

    private void getRelativePositionInt(int[] iArr, View view, View view2) {
        if (view != view2 && view != null) {
            if (!(view instanceof PagedTileLayout.TilePage)) {
                iArr[0] = iArr[0] + view.getLeft();
                iArr[1] = iArr[1] + view.getTop();
            }
            getRelativePositionInt(iArr, (View) view.getParent(), view2);
        }
    }

    public void setPosition(float f) {
        if (this.mFirstPageAnimator != null) {
            if (this.mOnKeyguard) {
                f = this.mShowCollapsedOnKeyguard ? 0.0f : 1.0f;
            }
            this.mLastPosition = f;
            if (!this.mOnFirstPage || !this.mAllowFancy) {
                this.mNonfirstPageAnimator.setPosition(f);
                this.mNonfirstPageDelayedAnimator.setPosition(f);
                return;
            }
            this.mQuickQsPanel.setAlpha(1.0f);
            this.mFirstPageAnimator.setPosition(f);
            this.mFirstPageDelayedAnimator.setPosition(f);
            this.mTranslationXAnimator.setPosition(f);
            this.mTranslationYAnimator.setPosition(f);
            TouchAnimator touchAnimator = this.mBrightnessAnimator;
            if (touchAnimator != null) {
                touchAnimator.setPosition(f);
            }
        }
    }

    public void onAnimationAtStart() {
        this.mQuickQsPanel.setVisibility(0);
    }

    public void onAnimationAtEnd() {
        this.mQuickQsPanel.setVisibility(4);
        int size = this.mQuickQsViews.size();
        for (int i = 0; i < size; i++) {
            this.mQuickQsViews.get(i).setVisibility(0);
        }
    }

    public void onAnimationStarted() {
        updateQQSVisibility();
        if (this.mOnFirstPage) {
            int size = this.mQuickQsViews.size();
            for (int i = 0; i < size; i++) {
                this.mQuickQsViews.get(i).setVisibility(4);
            }
        }
    }

    private void clearAnimationState() {
        int size = this.mAllViews.size();
        this.mQuickQsPanel.setAlpha(0.0f);
        for (int i = 0; i < size; i++) {
            View view = this.mAllViews.get(i);
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }
        int size2 = this.mQuickQsViews.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.mQuickQsViews.get(i2).setVisibility(0);
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mQsPanel.post(this.mUpdateAnimators);
    }

    public void onTilesChanged() {
        this.mQsPanel.post(this.mUpdateAnimators);
    }

    public void updateSettings() {
        this.mNumQuickTiles = this.mQuickQsPanel.getNumQuickTiles();
        clearAnimationState();
    }
}
