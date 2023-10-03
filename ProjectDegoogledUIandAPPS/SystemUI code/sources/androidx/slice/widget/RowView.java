package androidx.slice.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceAction;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.core.SliceQuery;
import androidx.slice.view.R$dimen;
import androidx.slice.view.R$id;
import androidx.slice.view.R$layout;
import androidx.slice.view.R$plurals;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RowView extends SliceChildView implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final boolean sCanSpecifyLargerRangeBarHeight = (Build.VERSION.SDK_INT >= 23);
    private View mActionDivider;
    private ProgressBar mActionSpinner;
    private ArrayMap<SliceActionImpl, SliceActionView> mActions = new ArrayMap<>();
    private boolean mAllowTwoLines;
    private View mBottomDivider;
    private LinearLayout mContent;
    private LinearLayout mEndContainer;
    Handler mHandler;
    private List<SliceAction> mHeaderActions;
    private int mIconSize = getContext().getResources().getDimensionPixelSize(R$dimen.abc_slice_icon_size);
    private int mImageSize = getContext().getResources().getDimensionPixelSize(R$dimen.abc_slice_small_image_size);
    private boolean mIsHeader;
    boolean mIsRangeSliding;
    long mLastSentRangeUpdate;
    private TextView mLastUpdatedText;
    protected Set<SliceItem> mLoadingActions = new HashSet();
    private int mMeasuredRangeHeight;
    private TextView mPrimaryText;
    private ProgressBar mRangeBar;
    boolean mRangeHasPendingUpdate;
    private SliceItem mRangeItem;
    int mRangeMaxValue;
    int mRangeMinValue;
    Runnable mRangeUpdater = new Runnable() {
        public void run() {
            RowView.this.sendSliderValue();
            RowView.this.mRangeUpdaterRunning = false;
        }
    };
    boolean mRangeUpdaterRunning;
    int mRangeValue;
    private LinearLayout mRootView;
    private SliceActionImpl mRowAction;
    RowContent mRowContent;
    int mRowIndex;
    private TextView mSecondaryText;
    private View mSeeMoreView;
    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            RowView rowView = RowView.this;
            rowView.mRangeValue = i + rowView.mRangeMinValue;
            long currentTimeMillis = System.currentTimeMillis();
            RowView rowView2 = RowView.this;
            long j = rowView2.mLastSentRangeUpdate;
            if (j == 0 || currentTimeMillis - j <= 200) {
                RowView rowView3 = RowView.this;
                if (!rowView3.mRangeUpdaterRunning) {
                    rowView3.mRangeUpdaterRunning = true;
                    rowView3.mHandler.postDelayed(rowView3.mRangeUpdater, 200);
                    return;
                }
                return;
            }
            rowView2.mRangeUpdaterRunning = false;
            rowView2.mHandler.removeCallbacks(rowView2.mRangeUpdater);
            RowView.this.sendSliderValue();
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            RowView.this.mIsRangeSliding = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            RowView rowView = RowView.this;
            rowView.mIsRangeSliding = false;
            if (rowView.mRangeUpdaterRunning || rowView.mRangeHasPendingUpdate) {
                RowView rowView2 = RowView.this;
                rowView2.mRangeUpdaterRunning = false;
                rowView2.mRangeHasPendingUpdate = false;
                rowView2.mHandler.removeCallbacks(rowView2.mRangeUpdater);
                RowView rowView3 = RowView.this;
                int progress = seekBar.getProgress();
                RowView rowView4 = RowView.this;
                rowView3.mRangeValue = progress + rowView4.mRangeMinValue;
                rowView4.sendSliderValue();
            }
        }
    };
    private SliceItem mSelectionItem;
    private ArrayList<String> mSelectionOptionKeys;
    private ArrayList<CharSequence> mSelectionOptionValues;
    private Spinner mSelectionSpinner;
    boolean mShowActionSpinner;
    private LinearLayout mStartContainer;
    private SliceItem mStartItem;
    private ArrayMap<SliceActionImpl, SliceActionView> mToggles = new ArrayMap<>();

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public RowView(Context context) {
        super(context);
        this.mRootView = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.abc_slice_small_template, this, false);
        addView(this.mRootView);
        this.mStartContainer = (LinearLayout) findViewById(R$id.icon_frame);
        this.mContent = (LinearLayout) findViewById(16908290);
        this.mPrimaryText = (TextView) findViewById(16908310);
        this.mSecondaryText = (TextView) findViewById(16908304);
        this.mLastUpdatedText = (TextView) findViewById(R$id.last_updated);
        this.mBottomDivider = findViewById(R$id.bottom_divider);
        this.mActionDivider = findViewById(R$id.action_divider);
        this.mActionSpinner = (ProgressBar) findViewById(R$id.action_sent_indicator);
        SliceViewUtil.tintIndeterminateProgressBar(getContext(), this.mActionSpinner);
        this.mEndContainer = (LinearLayout) findViewById(16908312);
    }

    public void setStyle(SliceStyle sliceStyle) {
        super.setStyle(sliceStyle);
        applyRowStyle();
    }

    private void applyRowStyle() {
        SliceStyle sliceStyle = this.mSliceStyle;
        if (sliceStyle != null && sliceStyle.getRowStyle() != null) {
            RowStyle rowStyle = this.mSliceStyle.getRowStyle();
            setViewPaddingEnd(this.mStartContainer, rowStyle.getTitleItemEndPadding());
            setViewSidePaddings(this.mContent, rowStyle.getContentStartPadding(), rowStyle.getContentEndPadding());
            setViewSidePaddings(this.mEndContainer, rowStyle.getEndItemStartPadding(), rowStyle.getEndItemEndPadding());
            setViewSideMargins(this.mBottomDivider, rowStyle.getBottomDividerStartPadding(), rowStyle.getBottomDividerEndPadding());
            setViewHeight(this.mActionDivider, rowStyle.getActionDividerHeight());
        }
    }

    private void setViewPaddingEnd(View view, int i) {
        if (view != null && i >= 0) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), i, view.getPaddingBottom());
        }
    }

    private void setViewSidePaddings(View view, int i, int i2) {
        if (view != null && i >= 0 && i2 >= 0) {
            view.setPaddingRelative(i, view.getPaddingTop(), i2, view.getPaddingBottom());
        }
    }

    private void setViewSideMargins(View view, int i, int i2) {
        if (view != null && i >= 0 && i2 >= 0) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.setMarginEnd(i2);
            this.mBottomDivider.setLayoutParams(marginLayoutParams);
        }
    }

    private void setViewHeight(View view, int i) {
        if (view != null && i >= 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = i;
            view.setLayoutParams(layoutParams);
        }
    }

    public void setInsets(int i, int i2, int i3, int i4) {
        super.setInsets(i, i2, i3, i4);
        setPadding(i, i2, i3, i4);
    }

    private int getRowContentHeight() {
        int height = this.mRowContent.getHeight(this.mSliceStyle, this.mViewPolicy);
        if (this.mRangeBar != null) {
            height -= this.mSliceStyle.getRowRangeHeight();
        }
        return this.mSelectionSpinner != null ? height - this.mSliceStyle.getRowSelectionHeight() : height;
    }

    public void setTint(int i) {
        super.setTint(i);
        if (this.mRowContent != null) {
            populateViews(true);
        }
    }

    public void setSliceActions(List<SliceAction> list) {
        this.mHeaderActions = list;
        if (this.mRowContent != null) {
            updateEndItems();
        }
    }

    public void setShowLastUpdated(boolean z) {
        super.setShowLastUpdated(z);
        if (this.mRowContent != null) {
            populateViews(true);
        }
    }

    public void setAllowTwoLines(boolean z) {
        this.mAllowTwoLines = z;
        if (this.mRowContent != null) {
            populateViews(true);
        }
    }

    private void measureChildWithExactHeight(View view, int i, int i2) {
        measureChild(view, i, View.MeasureSpec.makeMeasureSpec(i2 + this.mInsetTop + this.mInsetBottom, 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int rowContentHeight = getRowContentHeight();
        if (rowContentHeight != 0) {
            this.mRootView.setVisibility(0);
            measureChildWithExactHeight(this.mRootView, i, rowContentHeight);
            i3 = this.mRootView.getMeasuredWidth();
        } else {
            this.mRootView.setVisibility(8);
            i3 = 0;
        }
        ProgressBar progressBar = this.mRangeBar;
        if (progressBar != null) {
            if (sCanSpecifyLargerRangeBarHeight) {
                measureChildWithExactHeight(progressBar, i, this.mSliceStyle.getRowRangeHeight());
            } else {
                measureChild(progressBar, i, View.MeasureSpec.makeMeasureSpec(0, 0));
            }
            this.mMeasuredRangeHeight = this.mRangeBar.getMeasuredHeight();
            i3 = Math.max(i3, this.mRangeBar.getMeasuredWidth());
        } else {
            Spinner spinner = this.mSelectionSpinner;
            if (spinner != null) {
                measureChildWithExactHeight(spinner, i, this.mSliceStyle.getRowSelectionHeight());
                i3 = Math.max(i3, this.mSelectionSpinner.getMeasuredWidth());
            }
        }
        int max = Math.max(i3 + this.mInsetStart + this.mInsetEnd, getSuggestedMinimumWidth());
        RowContent rowContent = this.mRowContent;
        setMeasuredDimension(FrameLayout.resolveSizeAndState(max, i, 0), (rowContent != null ? rowContent.getHeight(this.mSliceStyle, this.mViewPolicy) : 0) + this.mInsetTop + this.mInsetBottom);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        LinearLayout linearLayout = this.mRootView;
        linearLayout.layout(paddingLeft, this.mInsetTop, linearLayout.getMeasuredWidth() + paddingLeft, getRowContentHeight() + this.mInsetTop);
        if (this.mRangeBar != null) {
            int rowContentHeight = getRowContentHeight() + ((this.mSliceStyle.getRowRangeHeight() - this.mMeasuredRangeHeight) / 2) + this.mInsetTop;
            ProgressBar progressBar = this.mRangeBar;
            progressBar.layout(paddingLeft, rowContentHeight, progressBar.getMeasuredWidth() + paddingLeft, this.mMeasuredRangeHeight + rowContentHeight);
        } else if (this.mSelectionSpinner != null) {
            int rowContentHeight2 = getRowContentHeight() + this.mInsetTop;
            Spinner spinner = this.mSelectionSpinner;
            spinner.layout(paddingLeft, rowContentHeight2, spinner.getMeasuredWidth() + paddingLeft, this.mSelectionSpinner.getMeasuredHeight() + rowContentHeight2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0053, code lost:
        if (r2 != false) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSliceItem(androidx.slice.widget.SliceContent r5, boolean r6, int r7, int r8, androidx.slice.widget.SliceView.OnSliceActionListener r9) {
        /*
            r4 = this;
            r4.setSliceActionListener(r9)
            r8 = 0
            if (r5 == 0) goto L_0x0056
            androidx.slice.widget.RowContent r9 = r4.mRowContent
            if (r9 == 0) goto L_0x0056
            boolean r9 = r9.isValid()
            if (r9 == 0) goto L_0x0056
            androidx.slice.widget.RowContent r9 = r4.mRowContent
            if (r9 == 0) goto L_0x001e
            androidx.slice.SliceStructure r0 = new androidx.slice.SliceStructure
            androidx.slice.SliceItem r9 = r9.getSliceItem()
            r0.<init>((androidx.slice.SliceItem) r9)
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            androidx.slice.SliceStructure r9 = new androidx.slice.SliceStructure
            androidx.slice.SliceItem r1 = r5.getSliceItem()
            androidx.slice.Slice r1 = r1.getSlice()
            r9.<init>((androidx.slice.Slice) r1)
            r1 = 1
            if (r0 == 0) goto L_0x0037
            boolean r2 = r0.equals(r9)
            if (r2 == 0) goto L_0x0037
            r2 = r1
            goto L_0x0038
        L_0x0037:
            r2 = r8
        L_0x0038:
            if (r0 == 0) goto L_0x0050
            android.net.Uri r3 = r0.getUri()
            if (r3 == 0) goto L_0x0050
            android.net.Uri r0 = r0.getUri()
            android.net.Uri r9 = r9.getUri()
            boolean r9 = r0.equals(r9)
            if (r9 == 0) goto L_0x0050
            r9 = r1
            goto L_0x0051
        L_0x0050:
            r9 = r8
        L_0x0051:
            if (r9 == 0) goto L_0x0056
            if (r2 == 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            r1 = r8
        L_0x0057:
            r4.mShowActionSpinner = r8
            r4.mIsHeader = r6
            androidx.slice.widget.RowContent r5 = (androidx.slice.widget.RowContent) r5
            r4.mRowContent = r5
            r4.mRowIndex = r7
            r4.populateViews(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.RowView.setSliceItem(androidx.slice.widget.SliceContent, boolean, int, int, androidx.slice.widget.SliceView$OnSliceActionListener):void");
    }

    private void populateViews(boolean z) {
        int i;
        boolean z2 = z && this.mIsRangeSliding;
        if (!z2) {
            resetViewState();
        }
        if (this.mRowContent.getLayoutDir() != -1) {
            setLayoutDirection(this.mRowContent.getLayoutDir());
        }
        if (this.mRowContent.isDefaultSeeMore()) {
            showSeeMore();
            return;
        }
        CharSequence contentDescription = this.mRowContent.getContentDescription();
        if (contentDescription != null) {
            this.mContent.setContentDescription(contentDescription);
        }
        this.mStartItem = this.mRowContent.getStartItem();
        boolean z3 = this.mStartItem != null && (this.mRowIndex > 0 || this.mRowContent.hasTitleItems());
        if (z3) {
            z3 = addItem(this.mStartItem, this.mTintColor, true);
        }
        int i2 = 8;
        this.mStartContainer.setVisibility(z3 ? 0 : 8);
        SliceItem titleItem = this.mRowContent.getTitleItem();
        if (titleItem != null) {
            this.mPrimaryText.setText(titleItem.getSanitizedText());
        }
        SliceStyle sliceStyle = this.mSliceStyle;
        if (sliceStyle != null) {
            TextView textView = this.mPrimaryText;
            if (this.mIsHeader) {
                i = sliceStyle.getHeaderTitleSize();
            } else {
                i = sliceStyle.getTitleSize();
            }
            textView.setTextSize(0, (float) i);
            this.mPrimaryText.setTextColor(this.mSliceStyle.getTitleColor());
        }
        this.mPrimaryText.setVisibility(titleItem != null ? 0 : 8);
        addSubtitle(titleItem != null);
        View view = this.mBottomDivider;
        if (this.mRowContent.hasBottomDivider()) {
            i2 = 0;
        }
        view.setVisibility(i2);
        SliceItem primaryAction = this.mRowContent.getPrimaryAction();
        if (!(primaryAction == null || primaryAction == this.mStartItem)) {
            this.mRowAction = new SliceActionImpl(primaryAction);
            if (this.mRowAction.isToggle()) {
                addAction(this.mRowAction, this.mTintColor, this.mEndContainer, false);
                setViewClickable(this.mRootView, true);
                return;
            }
        }
        SliceItem range = this.mRowContent.getRange();
        if (range != null) {
            if (this.mRowAction != null) {
                setViewClickable(this.mRootView, true);
            }
            this.mRangeItem = range;
            if (!z2) {
                setRangeBounds();
                addRange();
                return;
            }
            return;
        }
        SliceItem selection = this.mRowContent.getSelection();
        if (selection != null) {
            this.mSelectionItem = selection;
            addSelection(selection);
            return;
        }
        updateEndItems();
        updateActionSpinner();
    }

    private void updateEndItems() {
        SliceItem sliceItem;
        if (this.mRowContent != null) {
            this.mEndContainer.removeAllViews();
            List endItems = this.mRowContent.getEndItems();
            List list = this.mHeaderActions;
            if (list != null) {
                endItems = list;
            }
            if (this.mRowIndex == 0 && this.mStartItem != null && endItems.isEmpty() && !this.mRowContent.hasTitleItems()) {
                endItems.add(this.mStartItem);
            }
            boolean z = false;
            SliceItem sliceItem2 = null;
            int i = 0;
            int i2 = 0;
            boolean z2 = false;
            boolean z3 = false;
            while (true) {
                boolean z4 = true;
                if (i >= endItems.size()) {
                    break;
                }
                if (endItems.get(i) instanceof SliceItem) {
                    sliceItem = endItems.get(i);
                } else {
                    sliceItem = ((SliceActionImpl) endItems.get(i)).getSliceItem();
                }
                if (i2 < 3 && addItem(sliceItem, this.mTintColor, false)) {
                    if (sliceItem2 == null && SliceQuery.find(sliceItem, "action") != null) {
                        sliceItem2 = sliceItem;
                    }
                    i2++;
                    if (i2 == 1) {
                        z2 = !this.mToggles.isEmpty() && SliceQuery.find(sliceItem.getSlice(), "image") == null;
                        if (endItems.size() != 1 || SliceQuery.find(sliceItem, "action") == null) {
                            z4 = false;
                        }
                        z3 = z4;
                    }
                }
                i++;
            }
            int i3 = 8;
            this.mEndContainer.setVisibility(i2 > 0 ? 0 : 8);
            View view = this.mActionDivider;
            if (this.mRowAction != null && (z2 || (this.mRowContent.hasActionDivider() && z3))) {
                i3 = 0;
            }
            view.setVisibility(i3);
            SliceItem sliceItem3 = this.mStartItem;
            boolean z5 = (sliceItem3 == null || SliceQuery.find(sliceItem3, "action") == null) ? false : true;
            boolean z6 = sliceItem2 != null;
            if (this.mRowAction != null) {
                setViewClickable(this.mRootView, true);
            } else if (z6 != z5 && (i2 == 1 || z5)) {
                if (!this.mToggles.isEmpty()) {
                    this.mRowAction = this.mToggles.keySet().iterator().next();
                } else if (!this.mActions.isEmpty() && this.mActions.size() == 1) {
                    this.mRowAction = this.mActions.valueAt(0).getAction();
                }
                setViewClickable(this.mRootView, true);
                z = true;
            }
            SliceActionImpl sliceActionImpl = this.mRowAction;
            if (sliceActionImpl != null && !z && this.mLoadingActions.contains(sliceActionImpl.getSliceItem())) {
                this.mShowActionSpinner = true;
            }
        }
    }

    public void setLastUpdated(long j) {
        super.setLastUpdated(j);
        RowContent rowContent = this.mRowContent;
        if (rowContent != null) {
            addSubtitle(rowContent.getTitleItem() != null && TextUtils.isEmpty(this.mRowContent.getTitleItem().getSanitizedText()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x011f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addSubtitle(boolean r10) {
        /*
            r9 = this;
            androidx.slice.widget.RowContent r0 = r9.mRowContent
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            int r0 = r9.getMode()
            r1 = 1
            if (r0 != r1) goto L_0x0013
            androidx.slice.widget.RowContent r0 = r9.mRowContent
            androidx.slice.SliceItem r0 = r0.getSummaryItem()
            goto L_0x0019
        L_0x0013:
            androidx.slice.widget.RowContent r0 = r9.mRowContent
            androidx.slice.SliceItem r0 = r0.getSubtitleItem()
        L_0x0019:
            boolean r2 = r9.mShowLastUpdated
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L_0x003c
            long r5 = r9.mLastUpdated
            r7 = -1
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x003c
            java.lang.CharSequence r2 = r9.getRelativeTimeString(r5)
            if (r2 == 0) goto L_0x003c
            android.content.res.Resources r5 = r9.getResources()
            int r6 = androidx.slice.view.R$string.abc_slice_updated
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r4] = r2
            java.lang.String r2 = r5.getString(r6, r7)
            goto L_0x003d
        L_0x003c:
            r2 = r3
        L_0x003d:
            if (r0 == 0) goto L_0x0043
            java.lang.CharSequence r3 = r0.getSanitizedText()
        L_0x0043:
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 == 0) goto L_0x0056
            if (r0 == 0) goto L_0x0054
            java.lang.String r5 = "partial"
            boolean r0 = r0.hasHint(r5)
            if (r0 == 0) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            r0 = r4
            goto L_0x0057
        L_0x0056:
            r0 = r1
        L_0x0057:
            if (r0 == 0) goto L_0x0096
            android.widget.TextView r5 = r9.mSecondaryText
            r5.setText(r3)
            androidx.slice.widget.SliceStyle r5 = r9.mSliceStyle
            if (r5 == 0) goto L_0x0096
            android.widget.TextView r6 = r9.mSecondaryText
            boolean r7 = r9.mIsHeader
            if (r7 == 0) goto L_0x006d
            int r5 = r5.getHeaderSubtitleSize()
            goto L_0x0071
        L_0x006d:
            int r5 = r5.getSubtitleSize()
        L_0x0071:
            float r5 = (float) r5
            r6.setTextSize(r4, r5)
            android.widget.TextView r5 = r9.mSecondaryText
            androidx.slice.widget.SliceStyle r6 = r9.mSliceStyle
            int r6 = r6.getSubtitleColor()
            r5.setTextColor(r6)
            boolean r5 = r9.mIsHeader
            if (r5 == 0) goto L_0x008b
            androidx.slice.widget.SliceStyle r5 = r9.mSliceStyle
            int r5 = r5.getVerticalHeaderTextPadding()
            goto L_0x0091
        L_0x008b:
            androidx.slice.widget.SliceStyle r5 = r9.mSliceStyle
            int r5 = r5.getVerticalTextPadding()
        L_0x0091:
            android.widget.TextView r6 = r9.mSecondaryText
            r6.setPadding(r4, r5, r4, r4)
        L_0x0096:
            r5 = 2
            if (r2 == 0) goto L_0x00e8
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00b0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = " · "
            r3.append(r6)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
        L_0x00b0:
            android.text.SpannableString r3 = new android.text.SpannableString
            r3.<init>(r2)
            android.text.style.StyleSpan r6 = new android.text.style.StyleSpan
            r6.<init>(r5)
            int r7 = r2.length()
            r3.setSpan(r6, r4, r7, r4)
            android.widget.TextView r6 = r9.mLastUpdatedText
            r6.setText(r3)
            androidx.slice.widget.SliceStyle r3 = r9.mSliceStyle
            if (r3 == 0) goto L_0x00e8
            android.widget.TextView r6 = r9.mLastUpdatedText
            boolean r7 = r9.mIsHeader
            if (r7 == 0) goto L_0x00d5
            int r3 = r3.getHeaderSubtitleSize()
            goto L_0x00d9
        L_0x00d5:
            int r3 = r3.getSubtitleSize()
        L_0x00d9:
            float r3 = (float) r3
            r6.setTextSize(r4, r3)
            android.widget.TextView r3 = r9.mLastUpdatedText
            androidx.slice.widget.SliceStyle r6 = r9.mSliceStyle
            int r6 = r6.getSubtitleColor()
            r3.setTextColor(r6)
        L_0x00e8:
            android.widget.TextView r3 = r9.mLastUpdatedText
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            r7 = 8
            if (r6 == 0) goto L_0x00f4
            r6 = r7
            goto L_0x00f5
        L_0x00f4:
            r6 = r4
        L_0x00f5:
            r3.setVisibility(r6)
            android.widget.TextView r3 = r9.mSecondaryText
            if (r0 == 0) goto L_0x00fd
            r7 = r4
        L_0x00fd:
            r3.setVisibility(r7)
            int r3 = r9.mRowIndex
            if (r3 > 0) goto L_0x010b
            boolean r3 = r9.mAllowTwoLines
            if (r3 == 0) goto L_0x0109
            goto L_0x010b
        L_0x0109:
            r3 = r4
            goto L_0x010c
        L_0x010b:
            r3 = r1
        L_0x010c:
            if (r3 == 0) goto L_0x0119
            if (r10 != 0) goto L_0x0119
            if (r0 == 0) goto L_0x0119
            boolean r10 = android.text.TextUtils.isEmpty(r2)
            if (r10 == 0) goto L_0x0119
            goto L_0x011a
        L_0x0119:
            r5 = r1
        L_0x011a:
            android.widget.TextView r10 = r9.mSecondaryText
            if (r5 != r1) goto L_0x011f
            goto L_0x0120
        L_0x011f:
            r1 = r4
        L_0x0120:
            r10.setSingleLine(r1)
            android.widget.TextView r10 = r9.mSecondaryText
            r10.setMaxLines(r5)
            android.widget.TextView r10 = r9.mSecondaryText
            r10.requestLayout()
            android.widget.TextView r9 = r9.mLastUpdatedText
            r9.requestLayout()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.RowView.addSubtitle(boolean):void");
    }

    private CharSequence getRelativeTimeString(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis > 31449600000L) {
            int i = (int) (currentTimeMillis / 31449600000L);
            return getResources().getQuantityString(R$plurals.abc_slice_duration_years, i, new Object[]{Integer.valueOf(i)});
        } else if (currentTimeMillis > 86400000) {
            int i2 = (int) (currentTimeMillis / 86400000);
            return getResources().getQuantityString(R$plurals.abc_slice_duration_days, i2, new Object[]{Integer.valueOf(i2)});
        } else if (currentTimeMillis <= 60000) {
            return null;
        } else {
            int i3 = (int) (currentTimeMillis / 60000);
            return getResources().getQuantityString(R$plurals.abc_slice_duration_min, i3, new Object[]{Integer.valueOf(i3)});
        }
    }

    private void setRangeBounds() {
        SliceItem findSubtype = SliceQuery.findSubtype(this.mRangeItem, "int", "min");
        int i = 0;
        int i2 = findSubtype != null ? findSubtype.getInt() : 0;
        this.mRangeMinValue = i2;
        SliceItem findSubtype2 = SliceQuery.findSubtype(this.mRangeItem, "int", "max");
        int i3 = 100;
        if (findSubtype2 != null) {
            i3 = findSubtype2.getInt();
        }
        this.mRangeMaxValue = i3;
        SliceItem findSubtype3 = SliceQuery.findSubtype(this.mRangeItem, "int", "value");
        if (findSubtype3 != null) {
            i = findSubtype3.getInt() - i2;
        }
        this.mRangeValue = i;
    }

    private void addRange() {
        ProgressBar progressBar;
        Drawable loadDrawable;
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        boolean equals = "action".equals(this.mRangeItem.getFormat());
        if (equals) {
            progressBar = new SeekBar(getContext());
        } else {
            progressBar = new ProgressBar(getContext(), (AttributeSet) null, 16842872);
        }
        Drawable wrap = DrawableCompat.wrap(progressBar.getProgressDrawable());
        int i = this.mTintColor;
        if (!(i == -1 || wrap == null)) {
            DrawableCompat.setTint(wrap, i);
            progressBar.setProgressDrawable(wrap);
        }
        progressBar.setMax(this.mRangeMaxValue - this.mRangeMinValue);
        progressBar.setProgress(this.mRangeValue);
        progressBar.setVisibility(0);
        addView(progressBar);
        this.mRangeBar = progressBar;
        if (equals) {
            SliceItem inputRangeThumb = this.mRowContent.getInputRangeThumb();
            SeekBar seekBar = (SeekBar) this.mRangeBar;
            if (!(inputRangeThumb == null || inputRangeThumb.getIcon() == null || (loadDrawable = inputRangeThumb.getIcon().loadDrawable(getContext())) == null)) {
                seekBar.setThumb(loadDrawable);
            }
            Drawable wrap2 = DrawableCompat.wrap(seekBar.getThumb());
            int i2 = this.mTintColor;
            if (!(i2 == -1 || wrap2 == null)) {
                DrawableCompat.setTint(wrap2, i2);
                seekBar.setThumb(wrap2);
            }
            seekBar.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void sendSliderValue() {
        if (this.mRangeItem != null) {
            try {
                this.mLastSentRangeUpdate = System.currentTimeMillis();
                this.mRangeItem.fireAction(getContext(), new Intent().addFlags(268435456).putExtra("android.app.slice.extra.RANGE_VALUE", this.mRangeValue));
                if (this.mObserver != null) {
                    EventInfo eventInfo = new EventInfo(getMode(), 2, 4, this.mRowIndex);
                    eventInfo.state = this.mRangeValue;
                    this.mObserver.onSliceAction(eventInfo, this.mRangeItem);
                }
            } catch (PendingIntent.CanceledException e) {
                Log.e("RowView", "PendingIntent for slice cannot be sent", e);
            }
        }
    }

    private void addSelection(SliceItem sliceItem) {
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mSelectionOptionKeys = new ArrayList<>();
        this.mSelectionOptionValues = new ArrayList<>();
        List<SliceItem> items = sliceItem.getSlice().getItems();
        for (int i = 0; i < items.size(); i++) {
            SliceItem sliceItem2 = items.get(i);
            if (sliceItem2.hasHint("selection_option")) {
                SliceItem findSubtype = SliceQuery.findSubtype(sliceItem2, "text", "selection_option_key");
                SliceItem findSubtype2 = SliceQuery.findSubtype(sliceItem2, "text", "selection_option_value");
                if (!(findSubtype == null || findSubtype2 == null)) {
                    this.mSelectionOptionKeys.add(findSubtype.getText().toString());
                    this.mSelectionOptionValues.add(findSubtype2.getSanitizedText());
                }
            }
        }
        this.mSelectionSpinner = (Spinner) LayoutInflater.from(getContext()).inflate(R$layout.abc_slice_row_selection, this, false);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R$layout.abc_slice_row_selection_text, this.mSelectionOptionValues);
        arrayAdapter.setDropDownViewResource(R$layout.abc_slice_row_selection_dropdown_text);
        this.mSelectionSpinner.setAdapter(arrayAdapter);
        addView(this.mSelectionSpinner);
        this.mSelectionSpinner.setOnItemSelectedListener(this);
    }

    private void addAction(SliceActionImpl sliceActionImpl, int i, ViewGroup viewGroup, boolean z) {
        SliceActionView sliceActionView = new SliceActionView(getContext());
        viewGroup.addView(sliceActionView);
        if (viewGroup.getVisibility() == 8) {
            viewGroup.setVisibility(0);
        }
        boolean isToggle = sliceActionImpl.isToggle();
        EventInfo eventInfo = new EventInfo(getMode(), !isToggle ? 1 : 0, isToggle ? 3 : 0, this.mRowIndex);
        if (z) {
            eventInfo.setPosition(0, 0, 1);
        }
        sliceActionView.setAction(sliceActionImpl, eventInfo, this.mObserver, i, this.mLoadingListener);
        if (this.mLoadingActions.contains(sliceActionImpl.getSliceItem())) {
            sliceActionView.setLoading(true);
        }
        if (isToggle) {
            this.mToggles.put(sliceActionImpl, sliceActionView);
        } else {
            this.mActions.put(sliceActionImpl, sliceActionView);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.widget.TextView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.widget.TextView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.widget.ImageView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: android.widget.TextView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: android.widget.TextView} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean addItem(androidx.slice.SliceItem r7, int r8, boolean r9) {
        /*
            r6 = this;
            if (r9 == 0) goto L_0x0005
            android.widget.LinearLayout r0 = r6.mStartContainer
            goto L_0x0007
        L_0x0005:
            android.widget.LinearLayout r0 = r6.mEndContainer
        L_0x0007:
            java.lang.String r1 = r7.getFormat()
            java.lang.String r2 = "slice"
            boolean r1 = r2.equals(r1)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0021
            java.lang.String r1 = r7.getFormat()
            java.lang.String r4 = "action"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x004f
        L_0x0021:
            java.lang.String r1 = "shortcut"
            boolean r1 = r7.hasHint(r1)
            if (r1 == 0) goto L_0x0032
            androidx.slice.core.SliceActionImpl r1 = new androidx.slice.core.SliceActionImpl
            r1.<init>(r7)
            r6.addAction(r1, r8, r0, r9)
            return r2
        L_0x0032:
            androidx.slice.Slice r9 = r7.getSlice()
            java.util.List r9 = r9.getItems()
            int r9 = r9.size()
            if (r9 != 0) goto L_0x0041
            return r3
        L_0x0041:
            androidx.slice.Slice r7 = r7.getSlice()
            java.util.List r7 = r7.getItems()
            java.lang.Object r7 = r7.get(r3)
            androidx.slice.SliceItem r7 = (androidx.slice.SliceItem) r7
        L_0x004f:
            java.lang.String r9 = r7.getFormat()
            java.lang.String r1 = "image"
            boolean r9 = r1.equals(r9)
            r1 = 0
            if (r9 == 0) goto L_0x0069
            androidx.core.graphics.drawable.IconCompat r9 = r7.getIcon()
            java.lang.String r4 = "no_tint"
            boolean r4 = r7.hasHint(r4)
            r5 = r4
            r4 = r1
            goto L_0x007b
        L_0x0069:
            java.lang.String r9 = r7.getFormat()
            java.lang.String r4 = "long"
            boolean r9 = r4.equals(r9)
            if (r9 == 0) goto L_0x0078
            r4 = r7
            r9 = r1
            goto L_0x007a
        L_0x0078:
            r9 = r1
            r4 = r9
        L_0x007a:
            r5 = r3
        L_0x007b:
            if (r9 == 0) goto L_0x00bc
            if (r5 != 0) goto L_0x0081
            r7 = r2
            goto L_0x0082
        L_0x0081:
            r7 = r3
        L_0x0082:
            android.widget.ImageView r1 = new android.widget.ImageView
            android.content.Context r4 = r6.getContext()
            r1.<init>(r4)
            android.content.Context r4 = r6.getContext()
            android.graphics.drawable.Drawable r9 = r9.loadDrawable(r4)
            r1.setImageDrawable(r9)
            if (r7 == 0) goto L_0x009e
            r9 = -1
            if (r8 == r9) goto L_0x009e
            r1.setColorFilter(r8)
        L_0x009e:
            r0.addView(r1)
            android.view.ViewGroup$LayoutParams r8 = r1.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r8 = (android.widget.LinearLayout.LayoutParams) r8
            int r9 = r6.mImageSize
            r8.width = r9
            r8.height = r9
            r1.setLayoutParams(r8)
            if (r7 == 0) goto L_0x00b7
            int r6 = r6.mIconSize
            int r6 = r6 / 2
            goto L_0x00b8
        L_0x00b7:
            r6 = r3
        L_0x00b8:
            r1.setPadding(r6, r6, r6, r6)
            goto L_0x00ee
        L_0x00bc:
            if (r4 == 0) goto L_0x00ee
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r8 = r6.getContext()
            r1.<init>(r8)
            android.content.Context r8 = r6.getContext()
            long r4 = r7.getLong()
            java.lang.CharSequence r7 = androidx.slice.widget.SliceViewUtil.getTimestampString(r8, r4)
            r1.setText(r7)
            androidx.slice.widget.SliceStyle r7 = r6.mSliceStyle
            if (r7 == 0) goto L_0x00eb
            int r7 = r7.getSubtitleSize()
            float r7 = (float) r7
            r1.setTextSize(r3, r7)
            androidx.slice.widget.SliceStyle r6 = r6.mSliceStyle
            int r6 = r6.getSubtitleColor()
            r1.setTextColor(r6)
        L_0x00eb:
            r0.addView(r1)
        L_0x00ee:
            if (r1 == 0) goto L_0x00f1
            goto L_0x00f2
        L_0x00f1:
            r2 = r3
        L_0x00f2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.widget.RowView.addItem(androidx.slice.SliceItem, int, boolean):boolean");
    }

    private void showSeeMore() {
        final Button button = (Button) LayoutInflater.from(getContext()).inflate(R$layout.abc_slice_row_show_more, this, false);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (RowView.this.mObserver != null) {
                        RowView.this.mObserver.onSliceAction(new EventInfo(RowView.this.getMode(), 4, 0, RowView.this.mRowIndex), RowView.this.mRowContent.getSliceItem());
                    }
                    RowView.this.mShowActionSpinner = RowView.this.mRowContent.getSliceItem().fireActionInternal(RowView.this.getContext(), (Intent) null);
                    if (RowView.this.mShowActionSpinner) {
                        if (RowView.this.mLoadingListener != null) {
                            RowView.this.mLoadingListener.onSliceActionLoading(RowView.this.mRowContent.getSliceItem(), RowView.this.mRowIndex);
                        }
                        RowView.this.mLoadingActions.add(RowView.this.mRowContent.getSliceItem());
                        button.setVisibility(8);
                    }
                    RowView.this.updateActionSpinner();
                } catch (PendingIntent.CanceledException e) {
                    Log.e("RowView", "PendingIntent for slice cannot be sent", e);
                }
            }
        });
        int i = this.mTintColor;
        if (i != -1) {
            button.setTextColor(i);
        }
        this.mSeeMoreView = button;
        this.mRootView.addView(this.mSeeMoreView);
        if (this.mLoadingActions.contains(this.mRowContent.getSliceItem())) {
            this.mShowActionSpinner = true;
            button.setVisibility(8);
            updateActionSpinner();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateActionSpinner() {
        this.mActionSpinner.setVisibility(this.mShowActionSpinner ? 0 : 8);
    }

    public void setLoadingActions(Set<SliceItem> set) {
        if (set == null) {
            this.mLoadingActions.clear();
            this.mShowActionSpinner = false;
        } else {
            this.mLoadingActions = set;
        }
        updateEndItems();
        updateActionSpinner();
    }

    public void onClick(View view) {
        SliceActionView sliceActionView;
        SliceActionImpl sliceActionImpl = this.mRowAction;
        if (sliceActionImpl != null && sliceActionImpl.getActionItem() != null) {
            if (this.mRowAction.isToggle()) {
                sliceActionView = this.mToggles.get(this.mRowAction);
            } else {
                sliceActionView = this.mActions.get(this.mRowAction);
            }
            if (sliceActionView != null && !(view instanceof SliceActionView)) {
                sliceActionView.sendAction();
            } else if (this.mRowIndex == 0) {
                performClick();
            } else {
                try {
                    this.mShowActionSpinner = this.mRowAction.getActionItem().fireActionInternal(getContext(), (Intent) null);
                    if (this.mObserver != null) {
                        this.mObserver.onSliceAction(new EventInfo(getMode(), 3, 0, this.mRowIndex), this.mRowAction.getSliceItem());
                    }
                    if (this.mShowActionSpinner && this.mLoadingListener != null) {
                        this.mLoadingListener.onSliceActionLoading(this.mRowAction.getSliceItem(), this.mRowIndex);
                        this.mLoadingActions.add(this.mRowAction.getSliceItem());
                    }
                    updateActionSpinner();
                } catch (PendingIntent.CanceledException e) {
                    Log.e("RowView", "PendingIntent for slice cannot be sent", e);
                }
            }
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mSelectionItem != null && adapterView == this.mSelectionSpinner && i >= 0 && i < this.mSelectionOptionKeys.size()) {
            if (this.mObserver != null) {
                this.mObserver.onSliceAction(new EventInfo(getMode(), 5, 6, this.mRowIndex), this.mSelectionItem);
            }
            try {
                if (this.mSelectionItem.fireActionInternal(getContext(), new Intent().addFlags(268435456).putExtra("android.app.slice.extra.SELECTION", this.mSelectionOptionKeys.get(i)))) {
                    this.mShowActionSpinner = true;
                    if (this.mLoadingListener != null) {
                        this.mLoadingListener.onSliceActionLoading(this.mRowAction.getSliceItem(), this.mRowIndex);
                        this.mLoadingActions.add(this.mRowAction.getSliceItem());
                    }
                    updateActionSpinner();
                }
            } catch (PendingIntent.CanceledException e) {
                Log.e("RowView", "PendingIntent for slice cannot be sent", e);
            }
        }
    }

    private void setViewClickable(View view, boolean z) {
        Drawable drawable = null;
        view.setOnClickListener(z ? this : null);
        if (z) {
            drawable = SliceViewUtil.getDrawable(getContext(), 16843534);
        }
        view.setBackground(drawable);
        view.setClickable(z);
    }

    public void resetView() {
        this.mRowContent = null;
        this.mLoadingActions.clear();
        resetViewState();
    }

    private void resetViewState() {
        this.mRootView.setVisibility(0);
        setLayoutDirection(2);
        setViewClickable(this.mRootView, false);
        setViewClickable(this.mContent, false);
        this.mStartContainer.removeAllViews();
        this.mEndContainer.removeAllViews();
        this.mEndContainer.setVisibility(8);
        this.mPrimaryText.setText((CharSequence) null);
        this.mSecondaryText.setText((CharSequence) null);
        this.mLastUpdatedText.setText((CharSequence) null);
        this.mLastUpdatedText.setVisibility(8);
        this.mToggles.clear();
        this.mActions.clear();
        this.mRowAction = null;
        this.mStartItem = null;
        this.mBottomDivider.setVisibility(8);
        this.mActionDivider.setVisibility(8);
        View view = this.mSeeMoreView;
        if (view != null) {
            this.mRootView.removeView(view);
            this.mSeeMoreView = null;
        }
        this.mIsRangeSliding = false;
        this.mRangeHasPendingUpdate = false;
        this.mRangeItem = null;
        this.mRangeMinValue = 0;
        this.mRangeMaxValue = 0;
        this.mRangeValue = 0;
        this.mLastSentRangeUpdate = 0;
        this.mHandler = null;
        ProgressBar progressBar = this.mRangeBar;
        if (progressBar != null) {
            removeView(progressBar);
            this.mRangeBar = null;
        }
        this.mActionSpinner.setVisibility(8);
        Spinner spinner = this.mSelectionSpinner;
        if (spinner != null) {
            removeView(spinner);
            this.mSelectionSpinner = null;
        }
        this.mSelectionItem = null;
    }
}
