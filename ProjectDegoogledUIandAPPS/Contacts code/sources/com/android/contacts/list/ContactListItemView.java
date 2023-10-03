package com.android.contacts.list;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.ContactPresenceIconUtil;
import com.android.contacts.ContactStatusUtil;
import com.android.contacts.R;
import com.android.contacts.R$styleable;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.compat.PhoneNumberUtilsCompat;
import com.android.contacts.format.TextHighlighter;
import com.android.contacts.list.MultiSelectEntryContactListAdapter;
import com.android.contacts.list.PhoneNumberListAdapter;
import com.android.contacts.util.ContactDisplayUtils;
import com.android.contacts.util.SearchUtil;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactListItemView extends ViewGroup implements AbsListView.SelectionBoundsAdjuster {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})|[\\w]+");
    private static final String TAG = "ContactListItemView";
    private Drawable mActivatedBackgroundDrawable;
    private boolean mActivatedStateSupported;
    private boolean mAdjustSelectionBoundsEnabled;
    private int mAvatarOffsetTop;
    private Rect mBoundsWithoutHeader;
    private AppCompatCheckBox mCheckBox;
    private int mCheckBoxHeight;
    private int mCheckBoxWidth;
    private final CharArrayBuffer mDataBuffer;
    private TextView mDataView;
    private int mDataViewHeight;
    private int mDataViewWidthWeight;
    private int mDefaultPhotoViewSize;
    private AppCompatImageButton mDeleteImageButton;
    private int mDeleteImageButtonHeight;
    private int mDeleteImageButtonWidth;
    private int mGapBetweenImageAndText;
    private int mGapBetweenIndexerAndImage;
    private int mGapBetweenLabelAndData;
    private int mGapFromScrollBar;
    private View mHeaderView;
    private int mHeaderWidth;
    private String mHighlightedPrefix;
    private boolean mIsSectionHeaderEnabled;
    private boolean mKeepHorizontalPaddingForPhotoView;
    private boolean mKeepVerticalPaddingForPhotoView;
    private int mLabelAndDataViewMaxHeight;
    private TextView mLabelView;
    private int mLabelViewHeight;
    private int mLabelViewWidthWeight;
    private int mLeftOffset;
    private ArrayList<HighlightSequence> mNameHighlightSequence;
    private TextView mNameTextView;
    private int mNameTextViewHeight;
    private int mNameTextViewTextColor;
    private int mNameTextViewTextSize;
    private ArrayList<HighlightSequence> mNumberHighlightSequence;
    /* access modifiers changed from: private */
    public PhoneNumberListAdapter.Listener mPhoneNumberListAdapterListener;
    private final CharArrayBuffer mPhoneticNameBuffer;
    private TextView mPhoneticNameTextView;
    private int mPhoneticNameTextViewHeight;
    private PhotoPosition mPhotoPosition;
    private ImageView mPhotoView;
    private int mPhotoViewHeight;
    private int mPhotoViewWidth;
    private boolean mPhotoViewWidthAndHeightAreReady;
    /* access modifiers changed from: private */
    public int mPosition;
    private int mPreferredHeight;
    private ImageView mPresenceIcon;
    private int mPresenceIconMargin;
    private int mPresenceIconSize;
    private QuickContactBadge mQuickContact;
    private boolean mQuickContactEnabled;
    private int mRightOffset;
    private ColorStateList mSecondaryTextColor;
    private boolean mShowVideoCallIcon;
    private int mSnippetTextViewHeight;
    private TextView mSnippetView;
    private int mStatusTextViewHeight;
    private TextView mStatusView;
    private boolean mSupportVideoCallIcon;
    private final TextHighlighter mTextHighlighter;
    private int mTextIndent;
    private int mTextOffsetTop;
    private CharSequence mUnknownNameText;
    private ImageView mVideoCallIcon;
    private int mVideoCallIconMargin;
    private int mVideoCallIconSize;
    private ImageView mWorkProfileIcon;

    public enum PhotoPosition {
        LEFT,
        RIGHT
    }

    protected static class HighlightSequence {
        /* access modifiers changed from: private */
        public final int end;
        /* access modifiers changed from: private */
        public final int start;

        HighlightSequence(int i, int i2) {
            this.start = i;
            this.end = i2;
        }
    }

    public static final PhotoPosition getDefaultPhotoPosition(boolean z) {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1 ? z ? PhotoPosition.RIGHT : PhotoPosition.LEFT : z ? PhotoPosition.LEFT : PhotoPosition.RIGHT;
    }

    public ContactListItemView(Context context) {
        super(context);
        this.mPreferredHeight = 0;
        this.mGapBetweenImageAndText = 0;
        this.mGapBetweenIndexerAndImage = 0;
        this.mGapBetweenLabelAndData = 0;
        this.mPresenceIconMargin = 4;
        this.mPresenceIconSize = 16;
        this.mTextIndent = 0;
        this.mVideoCallIconSize = 32;
        this.mVideoCallIconMargin = 16;
        this.mGapFromScrollBar = 20;
        this.mLabelViewWidthWeight = 3;
        this.mDataViewWidthWeight = 5;
        this.mShowVideoCallIcon = false;
        this.mSupportVideoCallIcon = false;
        this.mPhotoPosition = getDefaultPhotoPosition(false);
        this.mQuickContactEnabled = true;
        this.mDefaultPhotoViewSize = 0;
        this.mPhotoViewWidthAndHeightAreReady = false;
        this.mNameTextViewTextColor = -16777216;
        this.mDataBuffer = new CharArrayBuffer(128);
        this.mPhoneticNameBuffer = new CharArrayBuffer(128);
        this.mAdjustSelectionBoundsEnabled = true;
        this.mBoundsWithoutHeader = new Rect();
        this.mTextHighlighter = new TextHighlighter(1);
        this.mNameHighlightSequence = new ArrayList<>();
        this.mNumberHighlightSequence = new ArrayList<>();
    }

    public ContactListItemView(Context context, AttributeSet attributeSet, boolean z) {
        this(context, attributeSet);
        this.mSupportVideoCallIcon = z;
    }

    public ContactListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPreferredHeight = 0;
        this.mGapBetweenImageAndText = 0;
        this.mGapBetweenIndexerAndImage = 0;
        this.mGapBetweenLabelAndData = 0;
        this.mPresenceIconMargin = 4;
        this.mPresenceIconSize = 16;
        this.mTextIndent = 0;
        this.mVideoCallIconSize = 32;
        this.mVideoCallIconMargin = 16;
        this.mGapFromScrollBar = 20;
        this.mLabelViewWidthWeight = 3;
        this.mDataViewWidthWeight = 5;
        this.mShowVideoCallIcon = false;
        this.mSupportVideoCallIcon = false;
        this.mPhotoPosition = getDefaultPhotoPosition(false);
        this.mQuickContactEnabled = true;
        this.mDefaultPhotoViewSize = 0;
        this.mPhotoViewWidthAndHeightAreReady = false;
        this.mNameTextViewTextColor = -16777216;
        this.mDataBuffer = new CharArrayBuffer(128);
        this.mPhoneticNameBuffer = new CharArrayBuffer(128);
        this.mAdjustSelectionBoundsEnabled = true;
        this.mBoundsWithoutHeader = new Rect();
        if (R$styleable.ContactListItemView != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ContactListItemView);
            this.mPreferredHeight = obtainStyledAttributes.getDimensionPixelSize(11, this.mPreferredHeight);
            this.mActivatedBackgroundDrawable = obtainStyledAttributes.getDrawable(0);
            this.mGapBetweenImageAndText = obtainStyledAttributes.getDimensionPixelOffset(4, this.mGapBetweenImageAndText);
            this.mGapBetweenIndexerAndImage = obtainStyledAttributes.getDimensionPixelOffset(5, this.mGapBetweenIndexerAndImage);
            this.mGapBetweenLabelAndData = obtainStyledAttributes.getDimensionPixelOffset(6, this.mGapBetweenLabelAndData);
            this.mPresenceIconMargin = obtainStyledAttributes.getDimensionPixelOffset(21, this.mPresenceIconMargin);
            this.mPresenceIconSize = obtainStyledAttributes.getDimensionPixelOffset(22, this.mPresenceIconSize);
            this.mDefaultPhotoViewSize = obtainStyledAttributes.getDimensionPixelOffset(19, this.mDefaultPhotoViewSize);
            this.mTextIndent = obtainStyledAttributes.getDimensionPixelOffset(24, this.mTextIndent);
            this.mTextOffsetTop = obtainStyledAttributes.getDimensionPixelOffset(25, this.mTextOffsetTop);
            this.mAvatarOffsetTop = obtainStyledAttributes.getDimensionPixelOffset(1, this.mAvatarOffsetTop);
            this.mDataViewWidthWeight = obtainStyledAttributes.getInteger(3, this.mDataViewWidthWeight);
            this.mLabelViewWidthWeight = obtainStyledAttributes.getInteger(12, this.mLabelViewWidthWeight);
            this.mNameTextViewTextColor = obtainStyledAttributes.getColor(13, this.mNameTextViewTextColor);
            this.mNameTextViewTextSize = (int) obtainStyledAttributes.getDimension(14, (float) ((int) getResources().getDimension(R.dimen.contact_browser_list_item_text_size)));
            this.mVideoCallIconSize = obtainStyledAttributes.getDimensionPixelOffset(27, this.mVideoCallIconSize);
            this.mVideoCallIconMargin = obtainStyledAttributes.getDimensionPixelOffset(26, this.mVideoCallIconMargin);
            setPaddingRelative(obtainStyledAttributes.getDimensionPixelOffset(16, 0), obtainStyledAttributes.getDimensionPixelOffset(18, 0), obtainStyledAttributes.getDimensionPixelOffset(17, 0), obtainStyledAttributes.getDimensionPixelOffset(15, 0));
            obtainStyledAttributes.recycle();
        }
        this.mTextHighlighter = new TextHighlighter(1);
        if (R$styleable.Theme != null) {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(R$styleable.Theme);
            this.mSecondaryTextColor = obtainStyledAttributes2.getColorStateList(0);
            obtainStyledAttributes2.recycle();
        }
        this.mHeaderWidth = getResources().getDimensionPixelSize(R.dimen.contact_list_section_header_width);
        Drawable drawable = this.mActivatedBackgroundDrawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.mNameHighlightSequence = new ArrayList<>();
        this.mNumberHighlightSequence = new ArrayList<>();
        setLayoutDirection(3);
    }

    public void setUnknownNameText(CharSequence charSequence) {
        this.mUnknownNameText = charSequence;
    }

    public void setQuickContactEnabled(boolean z) {
        this.mQuickContactEnabled = z;
    }

    public void setShowVideoCallIcon(boolean z, PhoneNumberListAdapter.Listener listener, int i) {
        this.mShowVideoCallIcon = z;
        this.mPhoneNumberListAdapterListener = listener;
        this.mPosition = i;
        if (this.mShowVideoCallIcon) {
            if (this.mVideoCallIcon == null) {
                this.mVideoCallIcon = new ImageView(getContext());
                addView(this.mVideoCallIcon);
            }
            this.mVideoCallIcon.setContentDescription(getContext().getString(R.string.description_search_video_call));
            this.mVideoCallIcon.setImageResource(R.drawable.quantum_ic_videocam_vd_theme_24);
            this.mVideoCallIcon.setScaleType(ImageView.ScaleType.CENTER);
            this.mVideoCallIcon.setVisibility(0);
            this.mVideoCallIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ContactListItemView.this.mPhoneNumberListAdapterListener != null) {
                        ContactListItemView.this.mPhoneNumberListAdapterListener.onVideoCallIconClicked(ContactListItemView.this.mPosition);
                    }
                }
            });
            return;
        }
        ImageView imageView = this.mVideoCallIcon;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    public void setSupportVideoCallIcon(boolean z) {
        this.mSupportVideoCallIcon = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int resolveSize = ViewGroup.resolveSize(0, i);
        int i7 = this.mPreferredHeight;
        this.mNameTextViewHeight = 0;
        this.mPhoneticNameTextViewHeight = 0;
        this.mLabelViewHeight = 0;
        this.mDataViewHeight = 0;
        this.mLabelAndDataViewMaxHeight = 0;
        this.mSnippetTextViewHeight = 0;
        this.mStatusTextViewHeight = 0;
        this.mCheckBoxWidth = 0;
        this.mCheckBoxHeight = 0;
        this.mDeleteImageButtonWidth = 0;
        this.mDeleteImageButtonHeight = 0;
        ensurePhotoViewSize();
        if (this.mPhotoViewWidth > 0 || this.mKeepHorizontalPaddingForPhotoView) {
            i4 = (resolveSize - getPaddingLeft()) - getPaddingRight();
            i3 = this.mPhotoViewWidth + this.mGapBetweenImageAndText + this.mGapBetweenIndexerAndImage;
        } else {
            i4 = resolveSize - getPaddingLeft();
            i3 = getPaddingRight();
        }
        int i8 = i4 - i3;
        if (this.mIsSectionHeaderEnabled) {
            i8 -= this.mHeaderWidth;
        }
        if (this.mSupportVideoCallIcon) {
            i8 -= this.mVideoCallIconSize + this.mVideoCallIconMargin;
        }
        if (isVisible(this.mCheckBox)) {
            this.mCheckBox.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mCheckBoxWidth = this.mCheckBox.getMeasuredWidth();
            this.mCheckBoxHeight = this.mCheckBox.getMeasuredHeight();
            i8 -= this.mCheckBoxWidth + this.mGapBetweenImageAndText;
        }
        if (isVisible(this.mDeleteImageButton)) {
            this.mDeleteImageButton.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mDeleteImageButtonWidth = this.mDeleteImageButton.getMeasuredWidth();
            this.mDeleteImageButtonHeight = this.mDeleteImageButton.getMeasuredHeight();
            i8 -= this.mDeleteImageButtonWidth + this.mGapBetweenImageAndText;
        }
        if (isVisible(this.mNameTextView)) {
            this.mNameTextView.measure(View.MeasureSpec.makeMeasureSpec(this.mPhotoPosition != PhotoPosition.LEFT ? i8 - this.mTextIndent : i8, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mNameTextViewHeight = this.mNameTextView.getMeasuredHeight();
        }
        if (isVisible(this.mPhoneticNameTextView)) {
            this.mPhoneticNameTextView.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mPhoneticNameTextViewHeight = this.mPhoneticNameTextView.getMeasuredHeight();
        }
        if (!isVisible(this.mDataView)) {
            i6 = isVisible(this.mLabelView) ? i8 : 0;
            i5 = 0;
        } else if (isVisible(this.mLabelView)) {
            int i9 = i8 - this.mGapBetweenLabelAndData;
            int i10 = this.mDataViewWidthWeight;
            int i11 = this.mLabelViewWidthWeight;
            i5 = (i9 * i10) / (i10 + i11);
            i6 = (i9 * i11) / (i10 + i11);
        } else {
            i5 = i8;
            i6 = 0;
        }
        if (isVisible(this.mDataView)) {
            this.mDataView.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mDataViewHeight = this.mDataView.getMeasuredHeight();
        }
        if (isVisible(this.mLabelView)) {
            this.mLabelView.measure(View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mLabelViewHeight = this.mLabelView.getMeasuredHeight();
        }
        this.mLabelAndDataViewMaxHeight = Math.max(this.mLabelViewHeight, this.mDataViewHeight);
        if (isVisible(this.mSnippetView)) {
            this.mSnippetView.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mSnippetTextViewHeight = this.mSnippetView.getMeasuredHeight();
        }
        if (isVisible(this.mPresenceIcon)) {
            this.mPresenceIcon.measure(View.MeasureSpec.makeMeasureSpec(this.mPresenceIconSize, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mPresenceIconSize, 1073741824));
            this.mStatusTextViewHeight = this.mPresenceIcon.getMeasuredHeight();
        }
        if (this.mSupportVideoCallIcon && isVisible(this.mVideoCallIcon)) {
            this.mVideoCallIcon.measure(View.MeasureSpec.makeMeasureSpec(this.mVideoCallIconSize, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mVideoCallIconSize, 1073741824));
        }
        if (isVisible(this.mWorkProfileIcon)) {
            this.mWorkProfileIcon.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mNameTextViewHeight = Math.max(this.mNameTextViewHeight, this.mWorkProfileIcon.getMeasuredHeight());
        }
        if (isVisible(this.mStatusView)) {
            if (isVisible(this.mPresenceIcon)) {
                i8 = (i8 - this.mPresenceIcon.getMeasuredWidth()) - this.mPresenceIconMargin;
            }
            this.mStatusView.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mStatusTextViewHeight = Math.max(this.mStatusTextViewHeight, this.mStatusView.getMeasuredHeight());
        }
        int max = Math.max(Math.max(this.mNameTextViewHeight + this.mPhoneticNameTextViewHeight + this.mLabelAndDataViewMaxHeight + this.mSnippetTextViewHeight + this.mStatusTextViewHeight + getPaddingBottom() + getPaddingTop(), this.mPhotoViewHeight + getPaddingBottom() + getPaddingTop()), i7);
        View view = this.mHeaderView;
        if (view != null && view.getVisibility() == 0) {
            this.mHeaderView.measure(View.MeasureSpec.makeMeasureSpec(this.mHeaderWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        setMeasuredDimension(resolveSize, max);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0279  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x02b8  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02ff  */
    /* JADX WARNING: Removed duplicated region for block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01ed  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
        /*
            r6 = this;
            int r11 = r11 - r9
            int r10 = r10 - r8
            int r7 = r6.getPaddingLeft()
            int r9 = r6.getPaddingRight()
            int r10 = r10 - r9
            boolean r9 = com.android.contacts.util.ViewUtil.isViewLayoutRtl(r6)
            boolean r0 = r6.mIsSectionHeaderEnabled
            if (r0 == 0) goto L_0x0041
            android.view.View r0 = r6.mHeaderView
            if (r0 == 0) goto L_0x0038
            int r0 = r0.getMeasuredHeight()
            int r1 = r11 + 0
            int r1 = r1 - r0
            int r1 = r1 / 2
            int r2 = r6.mTextOffsetTop
            int r1 = r1 + r2
            android.view.View r2 = r6.mHeaderView
            if (r9 == 0) goto L_0x002c
            int r3 = r6.mHeaderWidth
            int r3 = r10 - r3
            goto L_0x002d
        L_0x002c:
            r3 = r7
        L_0x002d:
            if (r9 == 0) goto L_0x0031
            r4 = r10
            goto L_0x0034
        L_0x0031:
            int r4 = r6.mHeaderWidth
            int r4 = r4 + r7
        L_0x0034:
            int r0 = r0 + r1
            r2.layout(r3, r1, r4, r0)
        L_0x0038:
            if (r9 == 0) goto L_0x003e
            int r0 = r6.mHeaderWidth
            int r10 = r10 - r0
            goto L_0x0041
        L_0x003e:
            int r0 = r6.mHeaderWidth
            int r7 = r7 + r0
        L_0x0041:
            android.graphics.Rect r0 = r6.mBoundsWithoutHeader
            int r1 = r8 + r7
            int r8 = r8 + r10
            r2 = 0
            r0.set(r1, r2, r8, r11)
            r6.mLeftOffset = r1
            r6.mRightOffset = r8
            if (r9 == 0) goto L_0x0054
            int r8 = r6.mGapBetweenIndexerAndImage
            int r10 = r10 - r8
            goto L_0x0057
        L_0x0054:
            int r8 = r6.mGapBetweenIndexerAndImage
            int r7 = r7 + r8
        L_0x0057:
            boolean r8 = r6.mActivatedStateSupported
            if (r8 == 0) goto L_0x0068
            boolean r8 = r6.isActivated()
            if (r8 == 0) goto L_0x0068
            android.graphics.drawable.Drawable r8 = r6.mActivatedBackgroundDrawable
            android.graphics.Rect r0 = r6.mBoundsWithoutHeader
            r8.setBounds(r0)
        L_0x0068:
            androidx.appcompat.widget.AppCompatCheckBox r8 = r6.mCheckBox
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x009c
            int r8 = r11 + 0
            int r0 = r6.mCheckBoxHeight
            int r8 = r8 - r0
            int r8 = r8 / 2
            int r8 = r8 + r2
            com.android.contacts.list.ContactListItemView$PhotoPosition r1 = r6.mPhotoPosition
            com.android.contacts.list.ContactListItemView$PhotoPosition r3 = com.android.contacts.list.ContactListItemView.PhotoPosition.LEFT
            if (r1 != r3) goto L_0x008e
            androidx.appcompat.widget.AppCompatCheckBox r1 = r6.mCheckBox
            int r3 = r6.mGapFromScrollBar
            int r4 = r10 - r3
            int r5 = r6.mCheckBoxWidth
            int r4 = r4 - r5
            int r3 = r10 - r3
            int r0 = r0 + r8
            r1.layout(r4, r8, r3, r0)
            goto L_0x009c
        L_0x008e:
            androidx.appcompat.widget.AppCompatCheckBox r1 = r6.mCheckBox
            int r3 = r6.mGapFromScrollBar
            int r4 = r7 + r3
            int r3 = r3 + r7
            int r5 = r6.mCheckBoxWidth
            int r3 = r3 + r5
            int r0 = r0 + r8
            r1.layout(r4, r8, r3, r0)
        L_0x009c:
            androidx.appcompat.widget.AppCompatImageButton r8 = r6.mDeleteImageButton
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x00cb
            int r8 = r11 + 0
            int r0 = r6.mDeleteImageButtonHeight
            int r8 = r8 - r0
            int r8 = r8 / 2
            int r8 = r8 + r2
            int r1 = r6.mDeleteImageButtonWidth
            if (r0 <= r1) goto L_0x00b1
            goto L_0x00b2
        L_0x00b1:
            r0 = r1
        L_0x00b2:
            com.android.contacts.list.ContactListItemView$PhotoPosition r1 = r6.mPhotoPosition
            com.android.contacts.list.ContactListItemView$PhotoPosition r3 = com.android.contacts.list.ContactListItemView.PhotoPosition.LEFT
            if (r1 != r3) goto L_0x00c2
            androidx.appcompat.widget.AppCompatImageButton r1 = r6.mDeleteImageButton
            int r3 = r10 - r0
            int r0 = r0 + r8
            r1.layout(r3, r8, r10, r0)
            r10 = r3
            goto L_0x00cb
        L_0x00c2:
            androidx.appcompat.widget.AppCompatImageButton r1 = r6.mDeleteImageButton
            int r3 = r7 + r0
            int r0 = r0 + r8
            r1.layout(r7, r8, r3, r0)
            r7 = r3
        L_0x00cb:
            android.widget.QuickContactBadge r8 = r6.mQuickContact
            if (r8 == 0) goto L_0x00d0
            goto L_0x00d2
        L_0x00d0:
            android.widget.ImageView r8 = r6.mPhotoView
        L_0x00d2:
            com.android.contacts.list.ContactListItemView$PhotoPosition r0 = r6.mPhotoPosition
            com.android.contacts.list.ContactListItemView$PhotoPosition r1 = com.android.contacts.list.ContactListItemView.PhotoPosition.LEFT
            if (r0 != r1) goto L_0x00fc
            if (r8 == 0) goto L_0x00f3
            int r0 = r11 + 0
            int r1 = r6.mPhotoViewHeight
            int r0 = r0 - r1
            int r0 = r0 / 2
            int r0 = r0 + r2
            int r3 = r6.mAvatarOffsetTop
            int r0 = r0 + r3
            int r3 = r6.mPhotoViewWidth
            int r3 = r3 + r7
            int r1 = r1 + r0
            r8.layout(r7, r0, r3, r1)
            int r8 = r6.mPhotoViewWidth
            int r0 = r6.mGapBetweenImageAndText
        L_0x00f0:
            int r8 = r8 + r0
        L_0x00f1:
            int r7 = r7 + r8
            goto L_0x0124
        L_0x00f3:
            boolean r8 = r6.mKeepHorizontalPaddingForPhotoView
            if (r8 == 0) goto L_0x0124
            int r8 = r6.mPhotoViewWidth
            int r0 = r6.mGapBetweenImageAndText
            goto L_0x00f0
        L_0x00fc:
            if (r8 == 0) goto L_0x0118
            int r0 = r11 + 0
            int r1 = r6.mPhotoViewHeight
            int r0 = r0 - r1
            int r0 = r0 / 2
            int r0 = r0 + r2
            int r3 = r6.mAvatarOffsetTop
            int r0 = r0 + r3
            int r3 = r6.mPhotoViewWidth
            int r3 = r10 - r3
            int r1 = r1 + r0
            r8.layout(r3, r0, r10, r1)
            int r8 = r6.mPhotoViewWidth
            int r0 = r6.mGapBetweenImageAndText
        L_0x0115:
            int r8 = r8 + r0
            int r10 = r10 - r8
            goto L_0x0121
        L_0x0118:
            boolean r8 = r6.mKeepHorizontalPaddingForPhotoView
            if (r8 == 0) goto L_0x0121
            int r8 = r6.mPhotoViewWidth
            int r0 = r6.mGapBetweenImageAndText
            goto L_0x0115
        L_0x0121:
            int r8 = r6.mTextIndent
            goto L_0x00f1
        L_0x0124:
            boolean r8 = r6.mSupportVideoCallIcon
            if (r8 == 0) goto L_0x015e
            android.widget.ImageView r8 = r6.mVideoCallIcon
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x014b
            int r8 = r11 + 0
            int r0 = r6.mVideoCallIconSize
            int r8 = r8 - r0
            int r8 = r8 / 2
            int r8 = r8 + r2
            if (r9 != 0) goto L_0x0143
            android.widget.ImageView r1 = r6.mVideoCallIcon
            int r3 = r10 - r0
            int r0 = r0 + r8
            r1.layout(r3, r8, r10, r0)
            goto L_0x014b
        L_0x0143:
            android.widget.ImageView r1 = r6.mVideoCallIcon
            int r3 = r7 + r0
            int r0 = r0 + r8
            r1.layout(r7, r8, r3, r0)
        L_0x014b:
            com.android.contacts.list.ContactListItemView$PhotoPosition r8 = r6.mPhotoPosition
            com.android.contacts.list.ContactListItemView$PhotoPosition r0 = com.android.contacts.list.ContactListItemView.PhotoPosition.LEFT
            if (r8 != r0) goto L_0x0158
            int r8 = r6.mVideoCallIconSize
            int r0 = r6.mVideoCallIconMargin
            int r8 = r8 + r0
            int r10 = r10 - r8
            goto L_0x015e
        L_0x0158:
            int r8 = r6.mVideoCallIconSize
            int r0 = r6.mVideoCallIconMargin
            int r8 = r8 + r0
            int r7 = r7 + r8
        L_0x015e:
            int r8 = r6.mNameTextViewHeight
            int r0 = r6.mPhoneticNameTextViewHeight
            int r8 = r8 + r0
            int r0 = r6.mLabelAndDataViewMaxHeight
            int r8 = r8 + r0
            int r0 = r6.mSnippetTextViewHeight
            int r8 = r8 + r0
            int r0 = r6.mStatusTextViewHeight
            int r8 = r8 + r0
            int r11 = r11 + r2
            int r11 = r11 - r8
            int r11 = r11 / 2
            int r8 = r6.mTextOffsetTop
            int r11 = r11 + r8
            android.widget.ImageView r8 = r6.mWorkProfileIcon
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x01ac
            android.widget.ImageView r8 = r6.mWorkProfileIcon
            int r8 = r8.getMeasuredWidth()
            int r0 = r6.mCheckBoxWidth
            if (r0 <= 0) goto L_0x0189
            int r1 = r6.mGapBetweenImageAndText
            int r0 = r0 + r1
            goto L_0x018a
        L_0x0189:
            r0 = 0
        L_0x018a:
            com.android.contacts.list.ContactListItemView$PhotoPosition r1 = r6.mPhotoPosition
            com.android.contacts.list.ContactListItemView$PhotoPosition r3 = com.android.contacts.list.ContactListItemView.PhotoPosition.LEFT
            if (r1 != r3) goto L_0x019e
            android.widget.ImageView r1 = r6.mWorkProfileIcon
            int r3 = r10 - r8
            int r3 = r3 - r0
            int r0 = r10 - r0
            int r4 = r6.mNameTextViewHeight
            int r4 = r4 + r11
            r1.layout(r3, r11, r0, r4)
            goto L_0x01ad
        L_0x019e:
            android.widget.ImageView r1 = r6.mWorkProfileIcon
            int r3 = r7 + r0
            int r4 = r7 + r8
            int r4 = r4 + r0
            int r0 = r6.mNameTextViewHeight
            int r0 = r0 + r11
            r1.layout(r3, r11, r4, r0)
            goto L_0x01ad
        L_0x01ac:
            r8 = 0
        L_0x01ad:
            android.widget.TextView r0 = r6.mNameTextView
            boolean r0 = r6.isVisible(r0)
            if (r0 == 0) goto L_0x01d8
            int r0 = r6.mCheckBoxWidth
            if (r0 <= 0) goto L_0x01bd
            int r1 = r6.mGapBetweenImageAndText
            int r2 = r0 + r1
        L_0x01bd:
            int r8 = r8 + r2
            com.android.contacts.list.ContactListItemView$PhotoPosition r0 = r6.mPhotoPosition
            com.android.contacts.list.ContactListItemView$PhotoPosition r1 = com.android.contacts.list.ContactListItemView.PhotoPosition.LEFT
            if (r0 != r1) goto L_0x01cf
            android.widget.TextView r0 = r6.mNameTextView
            int r8 = r10 - r8
            int r1 = r6.mNameTextViewHeight
            int r1 = r1 + r11
            r0.layout(r7, r11, r8, r1)
            goto L_0x01d8
        L_0x01cf:
            android.widget.TextView r0 = r6.mNameTextView
            int r8 = r8 + r7
            int r1 = r6.mNameTextViewHeight
            int r1 = r1 + r11
            r0.layout(r8, r11, r10, r1)
        L_0x01d8:
            android.widget.TextView r8 = r6.mNameTextView
            boolean r8 = r6.isVisible(r8)
            if (r8 != 0) goto L_0x01e8
            android.widget.ImageView r8 = r6.mWorkProfileIcon
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x01eb
        L_0x01e8:
            int r8 = r6.mNameTextViewHeight
            int r11 = r11 + r8
        L_0x01eb:
            if (r9 == 0) goto L_0x021d
            android.widget.ImageView r8 = r6.mPresenceIcon
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x020b
            android.widget.ImageView r8 = r6.mPresenceIcon
            int r8 = r8.getMeasuredWidth()
            android.widget.ImageView r0 = r6.mPresenceIcon
            int r1 = r10 - r8
            int r2 = r6.mStatusTextViewHeight
            int r2 = r2 + r11
            r0.layout(r1, r11, r10, r2)
            int r0 = r6.mPresenceIconMargin
            int r8 = r8 + r0
            int r8 = r10 - r8
            goto L_0x020c
        L_0x020b:
            r8 = r10
        L_0x020c:
            android.widget.TextView r0 = r6.mStatusView
            boolean r0 = r6.isVisible(r0)
            if (r0 == 0) goto L_0x024b
            android.widget.TextView r0 = r6.mStatusView
            int r1 = r6.mStatusTextViewHeight
            int r1 = r1 + r11
            r0.layout(r7, r11, r8, r1)
            goto L_0x024b
        L_0x021d:
            android.widget.ImageView r8 = r6.mPresenceIcon
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x023a
            android.widget.ImageView r8 = r6.mPresenceIcon
            int r8 = r8.getMeasuredWidth()
            android.widget.ImageView r0 = r6.mPresenceIcon
            int r1 = r7 + r8
            int r2 = r6.mStatusTextViewHeight
            int r2 = r2 + r11
            r0.layout(r7, r11, r1, r2)
            int r0 = r6.mPresenceIconMargin
            int r8 = r8 + r0
            int r8 = r8 + r7
            goto L_0x023b
        L_0x023a:
            r8 = r7
        L_0x023b:
            android.widget.TextView r0 = r6.mStatusView
            boolean r0 = r6.isVisible(r0)
            if (r0 == 0) goto L_0x024b
            android.widget.TextView r0 = r6.mStatusView
            int r1 = r6.mStatusTextViewHeight
            int r1 = r1 + r11
            r0.layout(r8, r11, r10, r1)
        L_0x024b:
            android.widget.TextView r8 = r6.mStatusView
            boolean r8 = r6.isVisible(r8)
            if (r8 != 0) goto L_0x025b
            android.widget.ImageView r8 = r6.mPresenceIcon
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x025e
        L_0x025b:
            int r8 = r6.mStatusTextViewHeight
            int r11 = r11 + r8
        L_0x025e:
            android.widget.TextView r8 = r6.mPhoneticNameTextView
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x0271
            android.widget.TextView r8 = r6.mPhoneticNameTextView
            int r0 = r6.mPhoneticNameTextViewHeight
            int r0 = r0 + r11
            r8.layout(r7, r11, r10, r0)
            int r8 = r6.mPhoneticNameTextViewHeight
            int r11 = r11 + r8
        L_0x0271:
            android.widget.TextView r8 = r6.mLabelView
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x02b8
            if (r9 != 0) goto L_0x0293
            android.widget.TextView r8 = r6.mLabelView
            int r0 = r6.mLabelAndDataViewMaxHeight
            int r1 = r11 + r0
            int r2 = r6.mLabelViewHeight
            int r1 = r1 - r2
            int r0 = r0 + r11
            r8.layout(r7, r1, r10, r0)
            android.widget.TextView r8 = r6.mLabelView
            int r8 = r8.getMeasuredWidth()
            int r0 = r6.mGapBetweenLabelAndData
            int r8 = r8 + r0
            int r8 = r8 + r7
            goto L_0x02b9
        L_0x0293:
            android.widget.TextView r8 = r6.mLabelView
            int r8 = r8.getMeasuredWidth()
            int r8 = r8 + r7
            android.widget.TextView r0 = r6.mLabelView
            int r1 = r0.getMeasuredWidth()
            int r1 = r10 - r1
            int r2 = r6.mLabelAndDataViewMaxHeight
            int r3 = r11 + r2
            int r4 = r6.mLabelViewHeight
            int r3 = r3 - r4
            int r2 = r2 + r11
            r0.layout(r1, r3, r10, r2)
            android.widget.TextView r0 = r6.mLabelView
            int r0 = r0.getMeasuredWidth()
            int r1 = r6.mGapBetweenLabelAndData
            int r0 = r0 + r1
            int r10 = r10 - r0
            goto L_0x02b9
        L_0x02b8:
            r8 = r7
        L_0x02b9:
            android.widget.TextView r0 = r6.mDataView
            boolean r0 = r6.isVisible(r0)
            if (r0 == 0) goto L_0x02e4
            if (r9 != 0) goto L_0x02d1
            android.widget.TextView r9 = r6.mDataView
            int r0 = r6.mLabelAndDataViewMaxHeight
            int r1 = r11 + r0
            int r2 = r6.mDataViewHeight
            int r1 = r1 - r2
            int r0 = r0 + r11
            r9.layout(r8, r1, r10, r0)
            goto L_0x02e4
        L_0x02d1:
            android.widget.TextView r8 = r6.mDataView
            int r9 = r8.getMeasuredWidth()
            int r9 = r10 - r9
            int r0 = r6.mLabelAndDataViewMaxHeight
            int r1 = r11 + r0
            int r2 = r6.mDataViewHeight
            int r1 = r1 - r2
            int r0 = r0 + r11
            r8.layout(r9, r1, r10, r0)
        L_0x02e4:
            android.widget.TextView r8 = r6.mLabelView
            boolean r8 = r6.isVisible(r8)
            if (r8 != 0) goto L_0x02f4
            android.widget.TextView r8 = r6.mDataView
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x02f7
        L_0x02f4:
            int r8 = r6.mLabelAndDataViewMaxHeight
            int r11 = r11 + r8
        L_0x02f7:
            android.widget.TextView r8 = r6.mSnippetView
            boolean r8 = r6.isVisible(r8)
            if (r8 == 0) goto L_0x0307
            android.widget.TextView r8 = r6.mSnippetView
            int r9 = r6.mSnippetTextViewHeight
            int r9 = r9 + r11
            r8.layout(r7, r11, r10, r9)
        L_0x0307:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.list.ContactListItemView.onLayout(boolean, int, int, int, int):void");
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        if (this.mAdjustSelectionBoundsEnabled) {
            int i = rect.top;
            Rect rect2 = this.mBoundsWithoutHeader;
            rect.top = i + rect2.top;
            rect.bottom = rect.top + rect2.height();
            Rect rect3 = this.mBoundsWithoutHeader;
            rect.left = rect3.left;
            rect.right = rect3.right;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    private void ensurePhotoViewSize() {
        if (!this.mPhotoViewWidthAndHeightAreReady) {
            int defaultPhotoViewSize = getDefaultPhotoViewSize();
            this.mPhotoViewHeight = defaultPhotoViewSize;
            this.mPhotoViewWidth = defaultPhotoViewSize;
            if (!this.mQuickContactEnabled && this.mPhotoView == null) {
                if (!this.mKeepHorizontalPaddingForPhotoView) {
                    this.mPhotoViewWidth = 0;
                }
                if (!this.mKeepVerticalPaddingForPhotoView) {
                    this.mPhotoViewHeight = 0;
                }
            }
            this.mPhotoViewWidthAndHeightAreReady = true;
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultPhotoViewSize() {
        return this.mDefaultPhotoViewSize;
    }

    private ViewGroup.LayoutParams getDefaultPhotoLayoutParams() {
        ViewGroup.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.width = getDefaultPhotoViewSize();
        generateDefaultLayoutParams.height = generateDefaultLayoutParams.width;
        return generateDefaultLayoutParams;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mActivatedStateSupported) {
            this.mActivatedBackgroundDrawable.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mActivatedBackgroundDrawable || super.verifyDrawable(drawable);
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mActivatedStateSupported) {
            this.mActivatedBackgroundDrawable.jumpToCurrentState();
        }
    }

    public void dispatchDraw(Canvas canvas) {
        if (this.mActivatedStateSupported && isActivated()) {
            this.mActivatedBackgroundDrawable.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    public void setSectionHeader(String str) {
        if (str == null) {
            View view = this.mHeaderView;
            if (view != null) {
                view.setVisibility(8);
            }
        } else if (str.isEmpty()) {
            View view2 = this.mHeaderView;
            if (view2 == null) {
                addStarImageHeader();
            } else if (view2 instanceof TextView) {
                removeView(view2);
                addStarImageHeader();
            } else {
                view2.setVisibility(0);
            }
        } else {
            View view3 = this.mHeaderView;
            if (view3 == null) {
                addTextHeader(str);
            } else if (view3 instanceof ImageView) {
                removeView(view3);
                addTextHeader(str);
            } else {
                updateHeaderText((TextView) view3, str);
            }
        }
    }

    private void addTextHeader(String str) {
        this.mHeaderView = new TextView(getContext());
        TextView textView = (TextView) this.mHeaderView;
        textView.setTextAppearance(getContext(), R.style.SectionHeaderStyle);
        textView.setGravity(1);
        updateHeaderText(textView, str);
        addView(textView);
    }

    private void updateHeaderText(TextView textView, String str) {
        setMarqueeText(textView, str);
        textView.setAllCaps(true);
        if ("…".equals(str)) {
            textView.setContentDescription(getContext().getString(R.string.description_no_name_header));
        } else {
            textView.setContentDescription(str);
        }
        textView.setVisibility(0);
    }

    private void addStarImageHeader() {
        this.mHeaderView = new ImageView(getContext());
        ImageView imageView = (ImageView) this.mHeaderView;
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.quantum_ic_star_vd_theme_24, getContext().getTheme()));
        imageView.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.material_star_pink)));
        imageView.setContentDescription(getContext().getString(R.string.contactsFavoritesLabel));
        imageView.setVisibility(0);
        addView(imageView);
    }

    public void setIsSectionHeaderEnabled(boolean z) {
        this.mIsSectionHeaderEnabled = z;
    }

    public QuickContactBadge getQuickContact() {
        if (this.mQuickContactEnabled) {
            if (this.mQuickContact == null) {
                this.mQuickContact = new QuickContactBadge(getContext());
                if (CompatUtils.isLollipopCompatible()) {
                    this.mQuickContact.setOverlay((Drawable) null);
                }
                this.mQuickContact.setLayoutParams(getDefaultPhotoLayoutParams());
                if (this.mNameTextView != null) {
                    this.mQuickContact.setContentDescription(getContext().getString(R.string.description_quick_contact_for, new Object[]{this.mNameTextView.getText()}));
                }
                addView(this.mQuickContact);
                this.mPhotoViewWidthAndHeightAreReady = false;
            }
            return this.mQuickContact;
        }
        throw new IllegalStateException("QuickContact is disabled for this view");
    }

    public ImageView getPhotoView() {
        if (this.mPhotoView == null) {
            this.mPhotoView = new ImageView(getContext());
            this.mPhotoView.setLayoutParams(getDefaultPhotoLayoutParams());
            this.mPhotoView.setBackground((Drawable) null);
            addView(this.mPhotoView);
            this.mPhotoViewWidthAndHeightAreReady = false;
        }
        return this.mPhotoView;
    }

    public void removePhotoView() {
        removePhotoView(false, true);
    }

    public void removePhotoView(boolean z, boolean z2) {
        this.mPhotoViewWidthAndHeightAreReady = false;
        this.mKeepHorizontalPaddingForPhotoView = z;
        this.mKeepVerticalPaddingForPhotoView = z2;
        ImageView imageView = this.mPhotoView;
        if (imageView != null) {
            removeView(imageView);
            this.mPhotoView = null;
        }
        QuickContactBadge quickContactBadge = this.mQuickContact;
        if (quickContactBadge != null) {
            removeView(quickContactBadge);
            this.mQuickContact = null;
        }
    }

    public void setHighlightedPrefix(String str) {
        this.mHighlightedPrefix = str;
    }

    public void clearHighlightSequences() {
        this.mNameHighlightSequence.clear();
        this.mNumberHighlightSequence.clear();
        this.mHighlightedPrefix = null;
    }

    public void addNameHighlightSequence(int i, int i2) {
        this.mNameHighlightSequence.add(new HighlightSequence(i, i2));
    }

    public void addNumberHighlightSequence(int i, int i2) {
        this.mNumberHighlightSequence.add(new HighlightSequence(i, i2));
    }

    public TextView getNameTextView() {
        if (this.mNameTextView == null) {
            this.mNameTextView = new TextView(getContext());
            this.mNameTextView.setSingleLine(true);
            this.mNameTextView.setEllipsize(getTextEllipsis());
            this.mNameTextView.setTextColor(ResourcesCompat.getColorStateList(getResources(), R.color.contact_list_name_text_color, getContext().getTheme()));
            this.mNameTextView.setTextSize(0, (float) this.mNameTextViewTextSize);
            this.mNameTextView.setActivated(isActivated());
            this.mNameTextView.setGravity(16);
            this.mNameTextView.setTextAlignment(5);
            this.mNameTextView.setId(R.id.cliv_name_textview);
            if (CompatUtils.isLollipopCompatible()) {
                this.mNameTextView.setElegantTextHeight(false);
            }
            addView(this.mNameTextView);
        }
        return this.mNameTextView;
    }

    public void setPhoneticName(char[] cArr, int i) {
        if (cArr == null || i == 0) {
            TextView textView = this.mPhoneticNameTextView;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        getPhoneticNameTextView();
        setMarqueeText(this.mPhoneticNameTextView, cArr, i);
        this.mPhoneticNameTextView.setVisibility(0);
    }

    public TextView getPhoneticNameTextView() {
        if (this.mPhoneticNameTextView == null) {
            this.mPhoneticNameTextView = new TextView(getContext());
            this.mPhoneticNameTextView.setSingleLine(true);
            this.mPhoneticNameTextView.setEllipsize(getTextEllipsis());
            this.mPhoneticNameTextView.setTextAppearance(getContext(), 16973894);
            this.mPhoneticNameTextView.setTextAlignment(5);
            TextView textView = this.mPhoneticNameTextView;
            textView.setTypeface(textView.getTypeface(), 1);
            this.mPhoneticNameTextView.setActivated(isActivated());
            this.mPhoneticNameTextView.setId(R.id.cliv_phoneticname_textview);
            addView(this.mPhoneticNameTextView);
        }
        return this.mPhoneticNameTextView;
    }

    public void setLabel(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mLabelView;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        getLabelView();
        setMarqueeText(this.mLabelView, charSequence);
        this.mLabelView.setVisibility(0);
    }

    public TextView getLabelView() {
        if (this.mLabelView == null) {
            this.mLabelView = new TextView(getContext());
            this.mLabelView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            this.mLabelView.setSingleLine(true);
            this.mLabelView.setEllipsize(getTextEllipsis());
            this.mLabelView.setTextAppearance(getContext(), R.style.TextAppearanceSmall);
            if (this.mPhotoPosition == PhotoPosition.LEFT) {
                this.mLabelView.setAllCaps(true);
            } else {
                TextView textView = this.mLabelView;
                textView.setTypeface(textView.getTypeface(), 1);
            }
            this.mLabelView.setActivated(isActivated());
            this.mLabelView.setId(R.id.cliv_label_textview);
            addView(this.mLabelView);
        }
        return this.mLabelView;
    }

    public void setData(char[] cArr, int i) {
        if (cArr == null || i == 0) {
            TextView textView = this.mDataView;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        getDataView();
        setMarqueeText(this.mDataView, cArr, i);
        this.mDataView.setVisibility(0);
    }

    public void setPhoneNumber(String str, String str2) {
        if (str == null) {
            TextView textView = this.mDataView;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        getDataView();
        SpannableString spannableString = new SpannableString(str);
        if (this.mNumberHighlightSequence.size() != 0) {
            HighlightSequence highlightSequence = this.mNumberHighlightSequence.get(0);
            this.mTextHighlighter.applyMaskingHighlight(spannableString, highlightSequence.start, highlightSequence.end);
        }
        setMarqueeText(this.mDataView, spannableString);
        this.mDataView.setVisibility(0);
        this.mDataView.setTextDirection(3);
        this.mDataView.setTextAlignment(5);
    }

    private void setMarqueeText(TextView textView, char[] cArr, int i) {
        if (getTextEllipsis() == TextUtils.TruncateAt.MARQUEE) {
            setMarqueeText(textView, new String(cArr, 0, i));
        } else {
            textView.setText(cArr, 0, i);
        }
    }

    private void setMarqueeText(TextView textView, CharSequence charSequence) {
        if (getTextEllipsis() == TextUtils.TruncateAt.MARQUEE) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(TextUtils.TruncateAt.MARQUEE, 0, spannableString.length(), 33);
            textView.setText(spannableString);
            return;
        }
        textView.setText(charSequence);
    }

    public AppCompatCheckBox getCheckBox() {
        if (this.mCheckBox == null) {
            this.mCheckBox = new AppCompatCheckBox(getContext());
            this.mCheckBox.setFocusable(false);
            addView(this.mCheckBox);
        }
        return this.mCheckBox;
    }

    public AppCompatImageButton getDeleteImageButton(final MultiSelectEntryContactListAdapter.DeleteContactListener deleteContactListener, final int i) {
        if (this.mDeleteImageButton == null) {
            this.mDeleteImageButton = new AppCompatImageButton(getContext());
            this.mDeleteImageButton.setImageResource(R.drawable.quantum_ic_cancel_vd_theme_24);
            this.mDeleteImageButton.setScaleType(ImageView.ScaleType.CENTER);
            this.mDeleteImageButton.setBackgroundColor(0);
            this.mDeleteImageButton.setContentDescription(getResources().getString(R.string.description_delete_contact));
            if (CompatUtils.isLollipopCompatible()) {
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(16843868, typedValue, true);
                this.mDeleteImageButton.setBackgroundResource(typedValue.resourceId);
            }
            addView(this.mDeleteImageButton);
        }
        this.mDeleteImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MultiSelectEntryContactListAdapter.DeleteContactListener deleteContactListener = deleteContactListener;
                if (deleteContactListener != null) {
                    deleteContactListener.onContactDeleteClicked(i);
                }
            }
        });
        return this.mDeleteImageButton;
    }

    public TextView getDataView() {
        if (this.mDataView == null) {
            this.mDataView = new TextView(getContext());
            this.mDataView.setSingleLine(true);
            this.mDataView.setEllipsize(getTextEllipsis());
            this.mDataView.setTextAppearance(getContext(), R.style.TextAppearanceSmall);
            this.mDataView.setTextAlignment(5);
            this.mDataView.setActivated(isActivated());
            this.mDataView.setId(R.id.cliv_data_view);
            if (CompatUtils.isLollipopCompatible()) {
                this.mDataView.setElegantTextHeight(false);
            }
            addView(this.mDataView);
        }
        return this.mDataView;
    }

    public void setSnippet(String str) {
        if (TextUtils.isEmpty(str)) {
            TextView textView = this.mSnippetView;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        this.mTextHighlighter.setPrefixText(getSnippetView(), str, this.mHighlightedPrefix);
        this.mSnippetView.setVisibility(0);
        if (ContactDisplayUtils.isPossiblePhoneNumber(str)) {
            this.mSnippetView.setContentDescription(PhoneNumberUtilsCompat.createTtsSpannable(str));
        } else {
            this.mSnippetView.setContentDescription((CharSequence) null);
        }
    }

    public TextView getSnippetView() {
        if (this.mSnippetView == null) {
            this.mSnippetView = new TextView(getContext());
            this.mSnippetView.setSingleLine(true);
            this.mSnippetView.setEllipsize(getTextEllipsis());
            this.mSnippetView.setTextAppearance(getContext(), 16973894);
            this.mSnippetView.setTextAlignment(5);
            this.mSnippetView.setActivated(isActivated());
            addView(this.mSnippetView);
        }
        return this.mSnippetView;
    }

    public TextView getStatusView() {
        if (this.mStatusView == null) {
            this.mStatusView = new TextView(getContext());
            this.mStatusView.setSingleLine(true);
            this.mStatusView.setEllipsize(getTextEllipsis());
            this.mStatusView.setTextAppearance(getContext(), 16973894);
            this.mStatusView.setTextColor(this.mSecondaryTextColor);
            this.mStatusView.setActivated(isActivated());
            this.mStatusView.setTextAlignment(5);
            addView(this.mStatusView);
        }
        return this.mStatusView;
    }

    public void setStatus(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mStatusView;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        getStatusView();
        setMarqueeText(this.mStatusView, charSequence);
        this.mStatusView.setVisibility(0);
    }

    public void setPresence(Drawable drawable) {
        if (drawable != null) {
            if (this.mPresenceIcon == null) {
                this.mPresenceIcon = new ImageView(getContext());
                addView(this.mPresenceIcon);
            }
            this.mPresenceIcon.setImageDrawable(drawable);
            this.mPresenceIcon.setScaleType(ImageView.ScaleType.CENTER);
            this.mPresenceIcon.setVisibility(0);
            return;
        }
        ImageView imageView = this.mPresenceIcon;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    public void setWorkProfileIconEnabled(boolean z) {
        ImageView imageView = this.mWorkProfileIcon;
        int i = 0;
        if (imageView != null) {
            if (!z) {
                i = 8;
            }
            imageView.setVisibility(i);
        } else if (z) {
            this.mWorkProfileIcon = new ImageView(getContext());
            addView(this.mWorkProfileIcon);
            this.mWorkProfileIcon.setImageResource(R.drawable.ic_work_profile);
            this.mWorkProfileIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.mWorkProfileIcon.setVisibility(0);
        }
    }

    private TextUtils.TruncateAt getTextEllipsis() {
        return TextUtils.TruncateAt.MARQUEE;
    }

    public void showDisplayName(Cursor cursor, int i, int i2) {
        setDisplayName(cursor.getString(i));
        QuickContactBadge quickContactBadge = this.mQuickContact;
        if (quickContactBadge != null) {
            quickContactBadge.setContentDescription(getContext().getString(R.string.description_quick_contact_for, new Object[]{this.mNameTextView.getText()}));
        }
    }

    public void setDisplayName(CharSequence charSequence, boolean z) {
        if (!TextUtils.isEmpty(charSequence) && z) {
            clearHighlightSequences();
            addNameHighlightSequence(0, charSequence.length());
        }
        setDisplayName(charSequence);
    }

    public void setDisplayName(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            String str = this.mHighlightedPrefix;
            if (str != null) {
                charSequence = this.mTextHighlighter.applyPrefixHighlight(charSequence, str);
            } else if (this.mNameHighlightSequence.size() != 0) {
                SpannableString spannableString = new SpannableString(charSequence);
                Iterator<HighlightSequence> it = this.mNameHighlightSequence.iterator();
                while (it.hasNext()) {
                    HighlightSequence next = it.next();
                    this.mTextHighlighter.applyMaskingHighlight(spannableString, next.start, next.end);
                }
                charSequence = spannableString;
            }
        } else {
            charSequence = this.mUnknownNameText;
        }
        setMarqueeText(getNameTextView(), charSequence);
        if (ContactDisplayUtils.isPossiblePhoneNumber(charSequence)) {
            this.mNameTextView.setTextDirection(3);
            this.mNameTextView.setContentDescription(PhoneNumberUtilsCompat.createTtsSpannable(charSequence.toString()));
            return;
        }
        this.mNameTextView.setContentDescription(charSequence.toString());
    }

    public void hideCheckBox() {
        AppCompatCheckBox appCompatCheckBox = this.mCheckBox;
        if (appCompatCheckBox != null) {
            removeView(appCompatCheckBox);
            this.mCheckBox = null;
        }
    }

    public void hideDeleteImageButton() {
        AppCompatImageButton appCompatImageButton = this.mDeleteImageButton;
        if (appCompatImageButton != null) {
            removeView(appCompatImageButton);
            this.mDeleteImageButton = null;
        }
    }

    public void hideDisplayName() {
        TextView textView = this.mNameTextView;
        if (textView != null) {
            removeView(textView);
            this.mNameTextView = null;
        }
    }

    public void showPhoneticName(Cursor cursor, int i) {
        cursor.copyStringToBuffer(i, this.mPhoneticNameBuffer);
        CharArrayBuffer charArrayBuffer = this.mPhoneticNameBuffer;
        int i2 = charArrayBuffer.sizeCopied;
        if (i2 != 0) {
            setPhoneticName(charArrayBuffer.data, i2);
        } else {
            setPhoneticName((char[]) null, 0);
        }
    }

    public void hidePhoneticName() {
        TextView textView = this.mPhoneticNameTextView;
        if (textView != null) {
            removeView(textView);
            this.mPhoneticNameTextView = null;
        }
    }

    public void showPresenceAndStatusMessage(Cursor cursor, int i, int i2) {
        int i3;
        Drawable drawable;
        String str = null;
        if (!cursor.isNull(i)) {
            i3 = cursor.getInt(i);
            drawable = ContactPresenceIconUtil.getPresenceIcon(getContext(), i3);
        } else {
            i3 = 0;
            drawable = null;
        }
        setPresence(drawable);
        if (i2 != 0 && !cursor.isNull(i2)) {
            str = cursor.getString(i2);
        }
        if (str == null && i3 != 0) {
            str = ContactStatusUtil.getStatusString(getContext(), i3);
        }
        setStatus(str);
    }

    public void showSnippet(Cursor cursor, String str, int i) {
        String string = cursor.getString(i);
        if (string == null) {
            setSnippet((String) null);
            return;
        }
        String string2 = cursor.getColumnIndex("display_name") >= 0 ? cursor.getString(cursor.getColumnIndex("display_name")) : null;
        if (string.equals(string2)) {
            setSnippet((String) null);
        } else {
            setSnippet(updateSnippet(string, str, string2));
        }
    }

    public void showSnippet(Cursor cursor, int i) {
        int indexOf;
        String str = null;
        if (cursor.getColumnCount() <= i || !"snippet".equals(cursor.getColumnName(i))) {
            setSnippet((String) null);
            return;
        }
        String string = cursor.getString(i);
        Bundle extras = cursor.getExtras();
        if (extras.getBoolean("deferred_snippeting")) {
            String string2 = extras.getString("deferred_snippeting_query");
            int columnIndex = cursor.getColumnIndex("display_name");
            if (columnIndex >= 0) {
                str = cursor.getString(columnIndex);
            }
            str = updateSnippet(string, string2, str);
        } else if (string != null) {
            int i2 = 0;
            int length = string.length();
            int indexOf2 = string.indexOf(91);
            if (indexOf2 != -1) {
                int lastIndexOf = string.lastIndexOf(10, indexOf2);
                if (lastIndexOf != -1) {
                    i2 = lastIndexOf + 1;
                }
                int lastIndexOf2 = string.lastIndexOf(93);
                if (!(lastIndexOf2 == -1 || (indexOf = string.indexOf(10, lastIndexOf2)) == -1)) {
                    length = indexOf;
                }
                StringBuilder sb = new StringBuilder();
                while (i2 < length) {
                    char charAt = string.charAt(i2);
                    if (!(charAt == '[' || charAt == ']')) {
                        sb.append(charAt);
                    }
                    i2++;
                }
                str = sb.toString();
            }
        } else {
            str = string;
        }
        setSnippet(str);
    }

    private String updateSnippet(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String cleanStartAndEndOfSearchQuery = SearchUtil.cleanStartAndEndOfSearchQuery(str2.toLowerCase());
            if (!TextUtils.isEmpty(str3)) {
                for (String startsWith : split(str3.toLowerCase())) {
                    if (startsWith.startsWith(cleanStartAndEndOfSearchQuery)) {
                        return null;
                    }
                }
            }
            SearchUtil.MatchedLine findMatchingLine = SearchUtil.findMatchingLine(str, cleanStartAndEndOfSearchQuery);
            if (!(findMatchingLine == null || findMatchingLine.line == null)) {
                int integer = getResources().getInteger(R.integer.snippet_length_before_tokenize);
                if (findMatchingLine.line.length() > integer) {
                    return snippetize(findMatchingLine.line, findMatchingLine.startIndex, integer);
                }
                return findMatchingLine.line;
            }
        }
        return null;
    }

    private String snippetize(String str, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        while (true) {
            if (i3 >= str.length()) {
                i3 = i;
                break;
            } else if (!Character.isLetterOrDigit(str.charAt(i3))) {
                i2 = i4;
                break;
            } else {
                i4--;
                i3++;
            }
        }
        int i5 = i;
        int i6 = i2;
        for (int i7 = i - 1; i7 > -1 && i2 > 0; i7--) {
            if (!Character.isLetterOrDigit(str.charAt(i7))) {
                i6 = i2;
                i5 = i7;
            }
            i2--;
        }
        int i8 = i3;
        while (i3 < str.length() && i6 > 0) {
            if (!Character.isLetterOrDigit(str.charAt(i3))) {
                i8 = i3;
            }
            i6--;
            i3++;
        }
        StringBuilder sb = new StringBuilder();
        if (i5 > 0) {
            sb.append("...");
        }
        sb.append(str.substring(i5, i8));
        if (i8 < str.length()) {
            sb.append("...");
        }
        return sb.toString();
    }

    private static List<String> split(String str) {
        Matcher matcher = SPLIT_PATTERN.matcher(str);
        ArrayList newArrayList = Lists.newArrayList();
        while (matcher.find()) {
            newArrayList.add(matcher.group());
        }
        return newArrayList;
    }

    public void showData(Cursor cursor, int i) {
        cursor.copyStringToBuffer(i, this.mDataBuffer);
        CharArrayBuffer charArrayBuffer = this.mDataBuffer;
        setData(charArrayBuffer.data, charArrayBuffer.sizeCopied);
    }

    public void setActivatedStateSupported(boolean z) {
        this.mActivatedStateSupported = z;
    }

    public void setAdjustSelectionBoundsEnabled(boolean z) {
        this.mAdjustSelectionBoundsEnabled = z;
    }

    public void requestLayout() {
        forceLayout();
    }

    public void setPhotoPosition(PhotoPosition photoPosition) {
        this.mPhotoPosition = photoPosition;
    }

    public PhotoPosition getPhotoPosition() {
        return this.mPhotoPosition;
    }

    public void setDrawableResource(int i) {
        ImageView photoView = getPhotoView();
        photoView.setScaleType(ImageView.ScaleType.CENTER);
        Drawable drawable = ContextCompat.getDrawable(getContext(), i);
        int color = ContextCompat.getColor(getContext(), R.color.search_shortcut_icon_color);
        if (CompatUtils.isLollipopCompatible()) {
            photoView.setImageDrawable(drawable);
            photoView.setImageTintList(ColorStateList.valueOf(color));
            return;
        }
        Drawable mutate = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(mutate, color);
        photoView.setImageDrawable(mutate);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.mBoundsWithoutHeader.contains((int) x, (int) y) || !pointIsInView(x, y)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    private final boolean pointIsInView(float f, float f2) {
        return f >= ((float) this.mLeftOffset) && f < ((float) this.mRightOffset) && f2 >= ContactPhotoManager.OFFSET_DEFAULT && f2 < ((float) (getBottom() - getTop()));
    }
}
