package androidx.slice.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.SliceMetadata;
import androidx.slice.core.SliceAction;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.core.SliceQuery;
import androidx.slice.view.R$attr;
import androidx.slice.view.R$dimen;
import androidx.slice.view.R$style;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class SliceView extends ViewGroup implements Observer<Slice>, View.OnClickListener {
    public static final Comparator<SliceAction> SLICE_ACTION_PRIORITY_COMPARATOR = new Comparator<SliceAction>() {
        public int compare(SliceAction sliceAction, SliceAction sliceAction2) {
            int priority = sliceAction.getPriority();
            int priority2 = sliceAction2.getPriority();
            if (priority < 0 && priority2 < 0) {
                return 0;
            }
            if (priority < 0) {
                return 1;
            }
            if (priority2 < 0) {
                return -1;
            }
            if (priority2 < priority) {
                return 1;
            }
            return priority2 > priority ? -1 : 0;
        }
    };
    private ActionRow mActionRow;
    private int mActionRowHeight;
    private List<SliceAction> mActions;
    int[] mClickInfo;
    private Slice mCurrentSlice;
    private boolean mCurrentSliceLoggedVisible;
    private SliceMetrics mCurrentSliceMetrics;
    SliceChildView mCurrentView;
    private int mDownX;
    private int mDownY;
    Handler mHandler;
    boolean mInLongpress;
    private int mLargeHeight;
    ListContent mListContent;
    View.OnLongClickListener mLongClickListener;
    Runnable mLongpressCheck;
    private int mMinTemplateHeight;
    private View.OnClickListener mOnClickListener;
    boolean mPressing;
    Runnable mRefreshLastUpdated;
    private int mShortcutSize;
    private boolean mShowActionDividers;
    private boolean mShowActions;
    private boolean mShowHeaderDivider;
    private boolean mShowLastUpdated;
    private boolean mShowTitleItems;
    SliceMetadata mSliceMetadata;
    private OnSliceActionListener mSliceObserver;
    private SliceStyle mSliceStyle;
    private int mThemeTintColor;
    private int mTouchSlopSquared;
    private SliceViewPolicy mViewPolicy;

    public interface OnSliceActionListener {
        void onSliceAction(EventInfo eventInfo, SliceItem sliceItem);
    }

    public SliceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliceViewStyle);
    }

    public SliceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowActions = false;
        this.mShowLastUpdated = true;
        this.mCurrentSliceLoggedVisible = false;
        this.mShowTitleItems = false;
        this.mShowHeaderDivider = false;
        this.mShowActionDividers = false;
        this.mThemeTintColor = -1;
        this.mLongpressCheck = new Runnable() {
            public void run() {
                View.OnLongClickListener onLongClickListener;
                SliceView sliceView = SliceView.this;
                if (sliceView.mPressing && (onLongClickListener = sliceView.mLongClickListener) != null) {
                    sliceView.mInLongpress = true;
                    onLongClickListener.onLongClick(sliceView);
                    SliceView.this.performHapticFeedback(0);
                }
            }
        };
        this.mRefreshLastUpdated = new Runnable() {
            public void run() {
                SliceMetadata sliceMetadata = SliceView.this.mSliceMetadata;
                if (sliceMetadata != null && sliceMetadata.isExpired()) {
                    SliceView.this.mCurrentView.setShowLastUpdated(true);
                    SliceView sliceView = SliceView.this;
                    sliceView.mCurrentView.setSliceContent(sliceView.mListContent);
                }
                SliceView.this.mHandler.postDelayed(this, 60000);
            }
        };
        init(context, attributeSet, i, R$style.Widget_SliceView);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mSliceStyle = new SliceStyle(context, attributeSet, i, i2);
        this.mThemeTintColor = this.mSliceStyle.getTintColor();
        this.mShortcutSize = getContext().getResources().getDimensionPixelSize(R$dimen.abc_slice_shortcut_size);
        this.mMinTemplateHeight = getContext().getResources().getDimensionPixelSize(R$dimen.abc_slice_row_min_height);
        this.mLargeHeight = getResources().getDimensionPixelSize(R$dimen.abc_slice_large_height);
        this.mActionRowHeight = getResources().getDimensionPixelSize(R$dimen.abc_slice_action_row_height);
        this.mViewPolicy = new SliceViewPolicy();
        this.mCurrentView = new TemplateView(getContext());
        this.mCurrentView.setPolicy(this.mViewPolicy);
        SliceChildView sliceChildView = this.mCurrentView;
        addView(sliceChildView, getChildLp(sliceChildView));
        applyConfigurations();
        this.mActionRow = new ActionRow(getContext(), true);
        this.mActionRow.setBackground(new ColorDrawable(-1118482));
        ActionRow actionRow = this.mActionRow;
        addView(actionRow, getChildLp(actionRow));
        updateActions();
        int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mTouchSlopSquared = scaledTouchSlop * scaledTouchSlop;
        this.mHandler = new Handler();
        setClipToPadding(false);
        super.setOnClickListener(this);
    }

    /* access modifiers changed from: package-private */
    public void setSliceViewPolicy(SliceViewPolicy sliceViewPolicy) {
        this.mViewPolicy = sliceViewPolicy;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.mListContent;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isSliceViewClickable() {
        /*
            r1 = this;
            android.view.View$OnClickListener r0 = r1.mOnClickListener
            if (r0 != 0) goto L_0x0015
            androidx.slice.widget.ListContent r0 = r1.mListContent
            if (r0 == 0) goto L_0x0013
            android.content.Context r1 = r1.getContext()
            androidx.slice.core.SliceAction r1 = r0.getShortcut(r1)
            if (r1 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r1 = 0
            goto L_0x0016
        L_0x0015:
            r1 = 1
        L_0x0016:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.SliceView.isSliceViewClickable():boolean");
    }

    public void setClickInfo(int[] iArr) {
        this.mClickInfo = iArr;
    }

    public void onClick(View view) {
        ListContent listContent = this.mListContent;
        if (listContent == null || listContent.getShortcut(getContext()) == null) {
            View.OnClickListener onClickListener = this.mOnClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(this);
                return;
            }
            return;
        }
        try {
            SliceActionImpl sliceActionImpl = (SliceActionImpl) this.mListContent.getShortcut(getContext());
            SliceItem actionItem = sliceActionImpl.getActionItem();
            if (actionItem != null && actionItem.fireActionInternal(getContext(), (Intent) null)) {
                this.mCurrentView.setActionLoading(sliceActionImpl.getSliceItem());
            }
            if (actionItem != null && this.mSliceObserver != null && this.mClickInfo != null && this.mClickInfo.length > 1) {
                EventInfo eventInfo = new EventInfo(getMode(), 3, this.mClickInfo[0], this.mClickInfo[1]);
                this.mSliceObserver.onSliceAction(eventInfo, sliceActionImpl.getSliceItem());
                logSliceMetricsOnTouch(sliceActionImpl.getSliceItem(), eventInfo);
            }
        } catch (PendingIntent.CanceledException e) {
            Log.e("SliceView", "PendingIntent for slice cannot be sent", e);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
        this.mLongClickListener = onLongClickListener;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (this.mLongClickListener != null && handleTouchForLongpress(motionEvent)) || super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return (this.mLongClickListener != null && handleTouchForLongpress(motionEvent)) || super.onTouchEvent(motionEvent);
    }

    private boolean handleTouchForLongpress(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int rawX = ((int) motionEvent.getRawX()) - this.mDownX;
                    int rawY = ((int) motionEvent.getRawY()) - this.mDownY;
                    if ((rawX * rawX) + (rawY * rawY) > this.mTouchSlopSquared) {
                        this.mPressing = false;
                        this.mHandler.removeCallbacks(this.mLongpressCheck);
                    }
                    return this.mInLongpress;
                } else if (actionMasked != 3) {
                    return false;
                }
            }
            boolean z = this.mInLongpress;
            this.mPressing = false;
            this.mInLongpress = false;
            this.mHandler.removeCallbacks(this.mLongpressCheck);
            return z;
        }
        this.mHandler.removeCallbacks(this.mLongpressCheck);
        this.mDownX = (int) motionEvent.getRawX();
        this.mDownY = (int) motionEvent.getRawY();
        this.mPressing = true;
        this.mInLongpress = false;
        this.mHandler.postDelayed(this.mLongpressCheck, (long) ViewConfiguration.getLongPressTimeout());
        return false;
    }

    private void configureViewPolicy(int i) {
        ListContent listContent = this.mListContent;
        if (listContent != null && listContent.isValid() && getMode() != 3) {
            if (i <= 0 || i >= this.mSliceStyle.getRowMaxHeight()) {
                this.mViewPolicy.setMaxSmallHeight(0);
            } else {
                int i2 = this.mMinTemplateHeight;
                if (i <= i2) {
                    i = i2;
                }
                this.mViewPolicy.setMaxSmallHeight(i);
            }
            this.mViewPolicy.setMaxHeight(i);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007f, code lost:
        if (r2 >= (r9 + r0)) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0084, code lost:
        if (r2 <= r9) goto L_0x008c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            int r8 = android.view.View.MeasureSpec.getSize(r8)
            int r0 = r7.getMode()
            r1 = 3
            if (r1 != r0) goto L_0x0017
            int r8 = r7.mShortcutSize
            int r0 = r7.getPaddingLeft()
            int r8 = r8 + r0
            int r0 = r7.getPaddingRight()
            int r8 = r8 + r0
        L_0x0017:
            androidx.slice.widget.ActionRow r0 = r7.mActionRow
            int r0 = r0.getVisibility()
            r2 = 8
            r3 = 0
            if (r0 == r2) goto L_0x0025
            int r0 = r7.mActionRowHeight
            goto L_0x0026
        L_0x0025:
            r0 = r3
        L_0x0026:
            int r2 = android.view.View.MeasureSpec.getSize(r9)
            int r9 = android.view.View.MeasureSpec.getMode(r9)
            android.view.ViewGroup$LayoutParams r4 = r7.getLayoutParams()
            if (r4 == 0) goto L_0x0039
            int r4 = r4.height
            r5 = -2
            if (r4 == r5) goto L_0x003b
        L_0x0039:
            if (r9 != 0) goto L_0x003d
        L_0x003b:
            r4 = -1
            goto L_0x003e
        L_0x003d:
            r4 = r2
        L_0x003e:
            r7.configureViewPolicy(r4)
            int r4 = r7.getPaddingTop()
            int r2 = r2 - r4
            int r4 = r7.getPaddingBottom()
            int r2 = r2 - r4
            r4 = 1073741824(0x40000000, float:2.0)
            if (r9 == r4) goto L_0x008b
            androidx.slice.widget.ListContent r5 = r7.mListContent
            if (r5 == 0) goto L_0x0089
            boolean r5 = r5.isValid()
            if (r5 != 0) goto L_0x005a
            goto L_0x0089
        L_0x005a:
            int r5 = r7.getMode()
            if (r5 != r1) goto L_0x0064
            int r9 = r7.mShortcutSize
        L_0x0062:
            int r9 = r9 + r0
            goto L_0x008c
        L_0x0064:
            androidx.slice.widget.ListContent r1 = r7.mListContent
            androidx.slice.widget.SliceStyle r5 = r7.mSliceStyle
            androidx.slice.widget.SliceViewPolicy r6 = r7.mViewPolicy
            int r1 = r1.getHeight(r5, r6)
            int r1 = r1 + r0
            if (r2 > r1) goto L_0x0087
            if (r9 != 0) goto L_0x0074
            goto L_0x0087
        L_0x0074:
            int r9 = r7.getMode()
            r1 = 2
            if (r9 != r1) goto L_0x0082
            int r9 = r7.mLargeHeight
            int r1 = r9 + r0
            if (r2 < r1) goto L_0x0082
            goto L_0x0062
        L_0x0082:
            int r9 = r7.mMinTemplateHeight
            if (r2 > r9) goto L_0x008b
            goto L_0x008c
        L_0x0087:
            r9 = r1
            goto L_0x008c
        L_0x0089:
            r9 = r0
            goto L_0x008c
        L_0x008b:
            r9 = r2
        L_0x008c:
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4)
            if (r0 <= 0) goto L_0x0098
            int r2 = r7.getPaddingBottom()
            int r2 = r2 + r0
            goto L_0x0099
        L_0x0098:
            r2 = r3
        L_0x0099:
            androidx.slice.widget.ActionRow r5 = r7.mActionRow
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r4)
            r5.measure(r1, r2)
            int r2 = r7.getPaddingTop()
            int r9 = r9 + r2
            if (r0 <= 0) goto L_0x00aa
            goto L_0x00ae
        L_0x00aa:
            int r3 = r7.getPaddingBottom()
        L_0x00ae:
            int r9 = r9 + r3
            androidx.slice.widget.SliceChildView r0 = r7.mCurrentView
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r4)
            r0.measure(r1, r9)
            androidx.slice.widget.SliceChildView r9 = r7.mCurrentView
            int r9 = r9.getMeasuredHeight()
            androidx.slice.widget.ActionRow r0 = r7.mActionRow
            int r0 = r0.getMeasuredHeight()
            int r9 = r9 + r0
            r7.setMeasuredDimension(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.SliceView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        SliceChildView sliceChildView = this.mCurrentView;
        sliceChildView.layout(0, 0, sliceChildView.getMeasuredWidth(), sliceChildView.getMeasuredHeight());
        if (this.mActionRow.getVisibility() != 8) {
            int measuredHeight = sliceChildView.getMeasuredHeight();
            ActionRow actionRow = this.mActionRow;
            actionRow.layout(0, measuredHeight, actionRow.getMeasuredWidth(), this.mActionRow.getMeasuredHeight() + measuredHeight);
        }
    }

    public void onChanged(Slice slice) {
        setSlice(slice);
    }

    public void setSlice(Slice slice) {
        LocationBasedViewTracker.trackInputFocused(this);
        LocationBasedViewTracker.trackA11yFocus(this);
        initSliceMetrics(slice);
        boolean z = false;
        boolean z2 = (slice == null || this.mCurrentSlice == null || !slice.getUri().equals(this.mCurrentSlice.getUri())) ? false : true;
        SliceMetadata sliceMetadata = this.mSliceMetadata;
        this.mCurrentSlice = slice;
        this.mSliceMetadata = this.mCurrentSlice != null ? SliceMetadata.from(getContext(), this.mCurrentSlice) : null;
        if (z2) {
            SliceMetadata sliceMetadata2 = this.mSliceMetadata;
            if (sliceMetadata.getLoadingState() == 2 && sliceMetadata2.getLoadingState() == 0) {
                return;
            }
        } else {
            this.mCurrentView.resetView();
        }
        SliceMetadata sliceMetadata3 = this.mSliceMetadata;
        this.mListContent = sliceMetadata3 != null ? sliceMetadata3.getListContent() : null;
        if (this.mShowTitleItems) {
            showTitleItems(true);
        }
        if (this.mShowHeaderDivider) {
            showHeaderDivider(true);
        }
        if (this.mShowActionDividers) {
            showActionDividers(true);
        }
        ListContent listContent = this.mListContent;
        if (listContent == null || !listContent.isValid()) {
            this.mActions = null;
            this.mCurrentView.resetView();
            updateActions();
            return;
        }
        this.mCurrentView.setLoadingActions((Set<SliceItem>) null);
        this.mActions = this.mSliceMetadata.getSliceActions();
        this.mCurrentView.setLastUpdated(this.mSliceMetadata.getLastUpdatedTime());
        SliceChildView sliceChildView = this.mCurrentView;
        if (this.mShowLastUpdated && this.mSliceMetadata.isExpired()) {
            z = true;
        }
        sliceChildView.setShowLastUpdated(z);
        this.mCurrentView.setAllowTwoLines(this.mSliceMetadata.isPermissionSlice());
        this.mCurrentView.setTint(getTintColor());
        if (this.mListContent.getLayoutDir() != -1) {
            this.mCurrentView.setLayoutDirection(this.mListContent.getLayoutDir());
        } else {
            this.mCurrentView.setLayoutDirection(2);
        }
        this.mCurrentView.setSliceContent(this.mListContent);
        updateActions();
        logSliceMetricsVisibilityChange(true);
        refreshLastUpdatedLabel(true);
    }

    public int getMode() {
        return this.mViewPolicy.getMode();
    }

    public void setShowTitleItems(boolean z) {
        this.mShowTitleItems = z;
        ListContent listContent = this.mListContent;
        if (listContent != null) {
            listContent.showTitleItems(z);
        }
    }

    @Deprecated
    public void showTitleItems(boolean z) {
        setShowTitleItems(z);
    }

    public void setShowHeaderDivider(boolean z) {
        this.mShowHeaderDivider = z;
        ListContent listContent = this.mListContent;
        if (listContent != null) {
            listContent.showHeaderDivider(z);
        }
    }

    @Deprecated
    public void showHeaderDivider(boolean z) {
        setShowHeaderDivider(z);
    }

    public void setShowActionDividers(boolean z) {
        this.mShowActionDividers = z;
        ListContent listContent = this.mListContent;
        if (listContent != null) {
            listContent.showActionDividers(z);
        }
    }

    @Deprecated
    public void showActionDividers(boolean z) {
        setShowActionDividers(z);
    }

    private void applyConfigurations() {
        this.mCurrentView.setSliceActionListener(this.mSliceObserver);
        this.mCurrentView.setStyle(this.mSliceStyle);
        this.mCurrentView.setTint(getTintColor());
        ListContent listContent = this.mListContent;
        if (listContent == null || listContent.getLayoutDir() == -1) {
            this.mCurrentView.setLayoutDirection(2);
        } else {
            this.mCurrentView.setLayoutDirection(this.mListContent.getLayoutDir());
        }
    }

    private void updateActions() {
        List<SliceAction> list = this.mActions;
        if (list == null) {
            this.mActionRow.setVisibility(8);
            this.mCurrentView.setSliceActions((List<SliceAction>) null);
            this.mCurrentView.setInsets(getPaddingStart(), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, SLICE_ACTION_PRIORITY_COMPARATOR);
        if (!this.mShowActions || getMode() == 3 || this.mActions.size() < 2) {
            this.mCurrentView.setSliceActions(arrayList);
            this.mCurrentView.setInsets(getPaddingStart(), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
            this.mActionRow.setVisibility(8);
            return;
        }
        this.mActionRow.setActions(arrayList, getTintColor());
        this.mActionRow.setVisibility(0);
        this.mCurrentView.setSliceActions((List<SliceAction>) null);
        this.mCurrentView.setInsets(getPaddingStart(), getPaddingTop(), getPaddingEnd(), 0);
        this.mActionRow.setPaddingRelative(getPaddingStart(), 0, getPaddingEnd(), getPaddingBottom());
    }

    private int getTintColor() {
        int i = this.mThemeTintColor;
        if (i != -1) {
            return i;
        }
        SliceItem findSubtype = SliceQuery.findSubtype(this.mCurrentSlice, "int", "color");
        if (findSubtype != null) {
            return findSubtype.getInt();
        }
        return SliceViewUtil.getColorAccent(getContext());
    }

    private ViewGroup.LayoutParams getChildLp(View view) {
        return new ViewGroup.LayoutParams(-1, -1);
    }

    public static String modeToString(int i) {
        if (i == 1) {
            return "MODE SMALL";
        }
        if (i == 2) {
            return "MODE LARGE";
        }
        if (i == 3) {
            return "MODE SHORTCUT";
        }
        return "unknown mode: " + i;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isShown()) {
            logSliceMetricsVisibilityChange(true);
            refreshLastUpdatedLabel(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        logSliceMetricsVisibilityChange(false);
        refreshLastUpdatedLabel(false);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (isAttachedToWindow()) {
            boolean z = true;
            logSliceMetricsVisibilityChange(i == 0);
            if (i != 0) {
                z = false;
            }
            refreshLastUpdatedLabel(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        boolean z = true;
        logSliceMetricsVisibilityChange(i == 0);
        if (i != 0) {
            z = false;
        }
        refreshLastUpdatedLabel(z);
    }

    private void initSliceMetrics(Slice slice) {
        if (slice == null || slice.getUri() == null) {
            logSliceMetricsVisibilityChange(false);
            this.mCurrentSliceMetrics = null;
            return;
        }
        Slice slice2 = this.mCurrentSlice;
        if (slice2 == null || !slice2.getUri().equals(slice.getUri())) {
            logSliceMetricsVisibilityChange(false);
            this.mCurrentSliceMetrics = SliceMetrics.getInstance(getContext(), slice.getUri());
        }
    }

    private void logSliceMetricsVisibilityChange(boolean z) {
        SliceMetrics sliceMetrics = this.mCurrentSliceMetrics;
        if (sliceMetrics != null) {
            if (z && !this.mCurrentSliceLoggedVisible) {
                sliceMetrics.logVisible();
                this.mCurrentSliceLoggedVisible = true;
            }
            if (!z && this.mCurrentSliceLoggedVisible) {
                this.mCurrentSliceMetrics.logHidden();
                this.mCurrentSliceLoggedVisible = false;
            }
        }
    }

    private void logSliceMetricsOnTouch(SliceItem sliceItem, EventInfo eventInfo) {
        if (this.mCurrentSliceMetrics != null && sliceItem.getSlice() != null && sliceItem.getSlice().getUri() != null) {
            this.mCurrentSliceMetrics.logTouch(eventInfo.actionType, sliceItem.getSlice().getUri());
        }
    }

    private void refreshLastUpdatedLabel(boolean z) {
        SliceMetadata sliceMetadata;
        if (this.mShowLastUpdated && (sliceMetadata = this.mSliceMetadata) != null && !sliceMetadata.neverExpires()) {
            if (z) {
                Handler handler = this.mHandler;
                Runnable runnable = this.mRefreshLastUpdated;
                long j = 60000;
                if (!this.mSliceMetadata.isExpired()) {
                    j = 60000 + this.mSliceMetadata.getTimeToExpiry();
                }
                handler.postDelayed(runnable, j);
                return;
            }
            this.mHandler.removeCallbacks(this.mRefreshLastUpdated);
        }
    }
}
