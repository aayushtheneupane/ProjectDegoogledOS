package com.android.contacts.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.GradientDrawable;
import android.hardware.display.DisplayManager;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.util.SchedulingUtils;

public class MultiShrinkScroller extends FrameLayout {
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private GradientDrawable mActionBarGradientDrawable;
    private View mActionBarGradientView;
    private final int mActionBarSize;
    private final float[] mAlphaMatrixValues;
    private int mCollapsedTitleBottomMargin;
    private int mCollapsedTitleStartMargin;
    private final ColorMatrix mColorMatrix;
    private final int mDismissDistanceOnRelease;
    private final int mDismissDistanceOnScroll;
    private final EdgeEffect mEdgeGlowBottom;
    private final EdgeEffect mEdgeGlowTop;
    /* access modifiers changed from: private */
    public TextView mFullNameView;
    private final int[] mGradientColors;
    private boolean mHasEverTouchedTheTop;
    private int mHeaderTintColor;
    /* access modifiers changed from: private */
    public int mIntermediateHeaderHeight;
    private TextView mInvisiblePlaceholderTextView;
    private boolean mIsBeingDragged;
    private boolean mIsFullscreenDownwardsFling;
    private boolean mIsOpenContactSquare;
    private boolean mIsTouchDisabledForDismissAnimation;
    private boolean mIsTouchDisabledForSuppressLayout;
    /* access modifiers changed from: private */
    public final boolean mIsTwoPanel;
    /* access modifiers changed from: private */
    public final float mLandscapePhotoRatio;
    private float[] mLastEventPosition;
    /* access modifiers changed from: private */
    public MultiShrinkScrollerListener mListener;
    /* access modifiers changed from: private */
    public int mMaximumFullNameViewHeight;
    /* access modifiers changed from: private */
    public int mMaximumHeaderHeight;
    /* access modifiers changed from: private */
    public int mMaximumHeaderTextSize;
    /* access modifiers changed from: private */
    public int mMaximumPhoneticNameViewHeight;
    /* access modifiers changed from: private */
    public int mMaximumPortraitHeaderHeight;
    /* access modifiers changed from: private */
    public final int mMaximumTitleMargin;
    private final int mMaximumVelocity;
    /* access modifiers changed from: private */
    public int mMinimumHeaderHeight;
    private int mMinimumPortraitHeaderHeight;
    private final int mMinimumVelocity;
    private final ColorMatrix mMultiplyBlendMatrix;
    private final float[] mMultiplyBlendMatrixValues;
    /* access modifiers changed from: private */
    public TextView mPhoneticNameView;
    private View mPhotoTouchInterceptOverlay;
    private QuickContactImageView mPhotoView;
    /* access modifiers changed from: private */
    public View mPhotoViewContainer;
    private boolean mReceivedDown;
    private ScrollView mScrollView;
    private View mScrollViewChild;
    private final Scroller mScroller;
    private final Animator.AnimatorListener mSnapToBottomListener;
    private final int mSnapToTopSlopHeight;
    private View mStartColumn;
    private final Interpolator mTextSizePathInterpolator;
    /* access modifiers changed from: private */
    public View mTitleAndPhoneticNameView;
    private GradientDrawable mTitleGradientDrawable;
    private View mTitleGradientView;
    private View mToolbar;
    private final float mToolbarElevation;
    private final int mTouchSlop;
    private final int mTransparentStartHeight;
    private View mTransparentView;
    private VelocityTracker mVelocityTracker;
    private final ColorMatrix mWhitenessColorMatrix;

    public interface MultiShrinkScrollerListener {
        void onEnterFullscreen();

        void onEntranceAnimationDone();

        void onExitFullscreen();

        void onScrolledOffBottom();

        void onStartScrollOffBottom();

        void onTransparentViewHeightChange(float f);
    }

    private float multiplyBlend(int i, float f) {
        return ((((float) i) * f) / 255.0f) + (1.0f - f);
    }

    public MultiShrinkScroller(Context context) {
        this(context, (AttributeSet) null);
    }

    public MultiShrinkScroller(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiShrinkScroller(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLastEventPosition = new float[]{ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT};
        this.mIsBeingDragged = false;
        this.mReceivedDown = false;
        this.mIsFullscreenDownwardsFling = false;
        this.mWhitenessColorMatrix = new ColorMatrix();
        this.mColorMatrix = new ColorMatrix();
        this.mAlphaMatrixValues = new float[]{ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, 1.0f, ContactPhotoManager.OFFSET_DEFAULT};
        this.mMultiplyBlendMatrix = new ColorMatrix();
        this.mMultiplyBlendMatrixValues = new float[]{ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, 1.0f, ContactPhotoManager.OFFSET_DEFAULT};
        this.mTextSizePathInterpolator = PathInterpolatorCompat.create(0.16f, 0.4f, 0.2f, 1.0f);
        this.mGradientColors = new int[]{0, -2013265920};
        this.mTitleGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, this.mGradientColors);
        this.mActionBarGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, this.mGradientColors);
        this.mSnapToBottomListener = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (MultiShrinkScroller.this.getScrollUntilOffBottom() > 0 && MultiShrinkScroller.this.mListener != null) {
                    MultiShrinkScroller.this.mListener.onScrolledOffBottom();
                    MultiShrinkScrollerListener unused = MultiShrinkScroller.this.mListener = null;
                }
            }
        };
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        setFocusable(false);
        setWillNotDraw(false);
        this.mEdgeGlowBottom = new EdgeEffect(context);
        this.mEdgeGlowTop = new EdgeEffect(context);
        this.mScroller = new Scroller(context, sInterpolator);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mTransparentStartHeight = (int) getResources().getDimension(R.dimen.quickcontact_starting_empty_height);
        this.mToolbarElevation = getResources().getDimension(R.dimen.quick_contact_toolbar_elevation);
        this.mIsTwoPanel = getResources().getBoolean(R.bool.quickcontact_two_panel);
        this.mMaximumTitleMargin = (int) getResources().getDimension(R.dimen.quickcontact_title_initial_margin);
        this.mDismissDistanceOnScroll = (int) getResources().getDimension(R.dimen.quickcontact_dismiss_distance_on_scroll);
        this.mDismissDistanceOnRelease = (int) getResources().getDimension(R.dimen.quickcontact_dismiss_distance_on_release);
        this.mSnapToTopSlopHeight = (int) getResources().getDimension(R.dimen.quickcontact_snap_to_top_slop_height);
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.quickcontact_landscape_photo_ratio, typedValue, true);
        this.mLandscapePhotoRatio = typedValue.getFloat();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{16843499});
        this.mActionBarSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mMinimumHeaderHeight = this.mActionBarSize;
        this.mMinimumPortraitHeaderHeight = this.mMinimumHeaderHeight;
        obtainStyledAttributes.recycle();
    }

    public void initialize(MultiShrinkScrollerListener multiShrinkScrollerListener, boolean z, final int i, final boolean z2) {
        this.mScrollView = (ScrollView) findViewById(R.id.content_scroller);
        this.mScrollViewChild = findViewById(R.id.card_container);
        this.mToolbar = findViewById(R.id.toolbar_parent);
        this.mPhotoViewContainer = findViewById(R.id.toolbar_parent);
        this.mTransparentView = findViewById(R.id.transparent_view);
        this.mFullNameView = (TextView) findViewById(R.id.large_title);
        this.mPhoneticNameView = (TextView) findViewById(R.id.phonetic_name);
        this.mTitleAndPhoneticNameView = findViewById(R.id.title_and_phonetic_name);
        this.mInvisiblePlaceholderTextView = (TextView) findViewById(R.id.placeholder_textview);
        this.mStartColumn = findViewById(R.id.empty_start_column);
        View view = this.mStartColumn;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MultiShrinkScroller.this.scrollOffBottom();
                }
            });
            findViewById(R.id.empty_end_column).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MultiShrinkScroller.this.scrollOffBottom();
                }
            });
        }
        this.mListener = multiShrinkScrollerListener;
        this.mIsOpenContactSquare = z;
        this.mPhotoView = (QuickContactImageView) findViewById(R.id.photo);
        this.mTitleGradientView = findViewById(R.id.title_gradient);
        this.mTitleGradientView.setBackground(this.mTitleGradientDrawable);
        this.mActionBarGradientView = findViewById(R.id.action_bar_gradient);
        this.mActionBarGradientView.setBackground(this.mActionBarGradientDrawable);
        this.mCollapsedTitleStartMargin = ((Toolbar) findViewById(R.id.toolbar)).getContentInsetStart();
        this.mPhotoTouchInterceptOverlay = findViewById(R.id.photo_touch_intercept_overlay);
        if (!this.mIsTwoPanel) {
            this.mPhotoTouchInterceptOverlay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MultiShrinkScroller.this.expandHeader();
                }
            });
        }
        SchedulingUtils.doOnPreDraw(this, false, new Runnable() {
            public void run() {
                int i;
                if (!MultiShrinkScroller.this.mIsTwoPanel) {
                    MultiShrinkScroller multiShrinkScroller = MultiShrinkScroller.this;
                    int unused = multiShrinkScroller.mMaximumHeaderHeight = multiShrinkScroller.mPhotoViewContainer.getWidth();
                    MultiShrinkScroller multiShrinkScroller2 = MultiShrinkScroller.this;
                    int unused2 = multiShrinkScroller2.mIntermediateHeaderHeight = (int) (((float) multiShrinkScroller2.mMaximumHeaderHeight) * 0.6f);
                }
                MultiShrinkScroller multiShrinkScroller3 = MultiShrinkScroller.this;
                if (multiShrinkScroller3.mIsTwoPanel) {
                    i = MultiShrinkScroller.this.getHeight();
                } else {
                    i = MultiShrinkScroller.this.mPhotoViewContainer.getWidth();
                }
                int unused3 = multiShrinkScroller3.mMaximumPortraitHeaderHeight = i;
                MultiShrinkScroller multiShrinkScroller4 = MultiShrinkScroller.this;
                multiShrinkScroller4.setHeaderHeight(multiShrinkScroller4.getMaximumScrollableHeaderHeight());
                if (z2) {
                    MultiShrinkScroller multiShrinkScroller5 = MultiShrinkScroller.this;
                    int unused4 = multiShrinkScroller5.mMaximumHeaderTextSize = multiShrinkScroller5.mTitleAndPhoneticNameView.getHeight();
                    MultiShrinkScroller multiShrinkScroller6 = MultiShrinkScroller.this;
                    int unused5 = multiShrinkScroller6.mMaximumFullNameViewHeight = multiShrinkScroller6.mFullNameView.getHeight();
                    int dimensionPixelSize = MultiShrinkScroller.this.getResources().getDimensionPixelSize(R.dimen.quickcontact_maximum_phonetic_name_size);
                    int dimensionPixelSize2 = MultiShrinkScroller.this.getResources().getDimensionPixelSize(R.dimen.quickcontact_maximum_title_size);
                    MultiShrinkScroller multiShrinkScroller7 = MultiShrinkScroller.this;
                    int unused6 = multiShrinkScroller7.mMaximumPhoneticNameViewHeight = (multiShrinkScroller7.mMaximumFullNameViewHeight * dimensionPixelSize) / dimensionPixelSize2;
                }
                int i2 = i;
                if (i2 > 0) {
                    int unused7 = MultiShrinkScroller.this.mMaximumHeaderTextSize = i2;
                }
                if (MultiShrinkScroller.this.mIsTwoPanel) {
                    MultiShrinkScroller multiShrinkScroller8 = MultiShrinkScroller.this;
                    int unused8 = multiShrinkScroller8.mMaximumHeaderHeight = multiShrinkScroller8.getHeight();
                    MultiShrinkScroller multiShrinkScroller9 = MultiShrinkScroller.this;
                    int unused9 = multiShrinkScroller9.mMinimumHeaderHeight = multiShrinkScroller9.mMaximumHeaderHeight;
                    MultiShrinkScroller multiShrinkScroller10 = MultiShrinkScroller.this;
                    int unused10 = multiShrinkScroller10.mIntermediateHeaderHeight = multiShrinkScroller10.mMaximumHeaderHeight;
                    ViewGroup.LayoutParams layoutParams = MultiShrinkScroller.this.mPhotoViewContainer.getLayoutParams();
                    layoutParams.height = MultiShrinkScroller.this.mMaximumHeaderHeight;
                    layoutParams.width = (int) (((float) MultiShrinkScroller.this.mMaximumHeaderHeight) * MultiShrinkScroller.this.mLandscapePhotoRatio);
                    MultiShrinkScroller.this.mPhotoViewContainer.setLayoutParams(layoutParams);
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) MultiShrinkScroller.this.mTitleAndPhoneticNameView.getLayoutParams();
                    layoutParams2.width = (layoutParams.width - layoutParams2.leftMargin) - layoutParams2.rightMargin;
                    layoutParams2.gravity = 8388691;
                    MultiShrinkScroller.this.mTitleAndPhoneticNameView.setLayoutParams(layoutParams2);
                } else {
                    MultiShrinkScroller.this.mFullNameView.setWidth(MultiShrinkScroller.this.mPhotoViewContainer.getWidth() - (MultiShrinkScroller.this.mMaximumTitleMargin * 2));
                    MultiShrinkScroller.this.mPhoneticNameView.setWidth(MultiShrinkScroller.this.mPhotoViewContainer.getWidth() - (MultiShrinkScroller.this.mMaximumTitleMargin * 2));
                }
                MultiShrinkScroller.this.calculateCollapsedLargeTitlePadding();
                MultiShrinkScroller.this.updateHeaderTextSizeAndMargin();
                MultiShrinkScroller.this.configureGradientViewHeights();
            }
        });
    }

    /* access modifiers changed from: private */
    public void configureGradientViewHeights() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mActionBarGradientView.getLayoutParams();
        layoutParams.height = this.mActionBarSize;
        this.mActionBarGradientView.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mTitleGradientView.getLayoutParams();
        layoutParams2.height = (int) (((float) (this.mMaximumHeaderTextSize + ((FrameLayout.LayoutParams) this.mTitleAndPhoneticNameView.getLayoutParams()).bottomMargin)) * 1.25f);
        this.mTitleGradientView.setLayoutParams(layoutParams2);
    }

    public void setTitle(String str, boolean z) {
        this.mFullNameView.setText(str);
        if (z) {
            this.mFullNameView.setTextDirection(3);
        }
        this.mPhotoTouchInterceptOverlay.setContentDescription(str);
    }

    public void setPhoneticName(String str) {
        if (this.mPhoneticNameView.getVisibility() != 0 || !str.equals(this.mPhoneticNameView.getText())) {
            this.mPhoneticNameView.setText(str);
            this.mPhoneticNameView.setVisibility(0);
            initialize(this.mListener, this.mIsOpenContactSquare, (this.mMaximumFullNameViewHeight * this.mFullNameView.getLineCount()) + (this.mMaximumPhoneticNameViewHeight * this.mPhoneticNameView.getLineCount()), false);
        }
    }

    public void setPhoneticNameGone() {
        if (this.mPhoneticNameView.getVisibility() != 8) {
            this.mPhoneticNameView.setVisibility(8);
            initialize(this.mListener, this.mIsOpenContactSquare, this.mMaximumFullNameViewHeight * this.mFullNameView.getLineCount(), false);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return shouldStartDrag(motionEvent);
    }

    private boolean shouldStartDrag(MotionEvent motionEvent) {
        if (!this.mIsTouchDisabledForDismissAnimation && !this.mIsTouchDisabledForSuppressLayout) {
            if (this.mIsBeingDragged) {
                this.mIsBeingDragged = false;
                return false;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                updateLastEventPosition(motionEvent);
                if (!this.mScroller.isFinished()) {
                    startDrag();
                    return true;
                }
                this.mReceivedDown = true;
            } else if (action == 2 && motionShouldStartDrag(motionEvent)) {
                updateLastEventPosition(motionEvent);
                startDrag();
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        if (r0 != 3) goto L_0x0099;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.mIsTouchDisabledForDismissAnimation
            r1 = 1
            if (r0 != 0) goto L_0x0099
            boolean r0 = r5.mIsTouchDisabledForSuppressLayout
            if (r0 == 0) goto L_0x000b
            goto L_0x0099
        L_0x000b:
            int r0 = r6.getAction()
            android.view.VelocityTracker r2 = r5.mVelocityTracker
            if (r2 != 0) goto L_0x0019
            android.view.VelocityTracker r2 = android.view.VelocityTracker.obtain()
            r5.mVelocityTracker = r2
        L_0x0019:
            android.view.VelocityTracker r2 = r5.mVelocityTracker
            r2.addMovement(r6)
            boolean r2 = r5.mIsBeingDragged
            r3 = 0
            if (r2 != 0) goto L_0x0038
            boolean r6 = r5.shouldStartDrag(r6)
            if (r6 == 0) goto L_0x002a
            return r1
        L_0x002a:
            if (r0 != r1) goto L_0x0037
            boolean r6 = r5.mReceivedDown
            if (r6 == 0) goto L_0x0037
            r5.mReceivedDown = r3
            boolean r6 = r5.performClick()
            return r6
        L_0x0037:
            return r1
        L_0x0038:
            r2 = 3
            if (r0 == r1) goto L_0x008f
            r4 = 2
            if (r0 == r4) goto L_0x0041
            if (r0 == r2) goto L_0x008f
            goto L_0x0099
        L_0x0041:
            float r0 = r5.updatePositionAndComputeDelta(r6)
            int r2 = r5.getScroll()
            int r4 = (int) r0
            int r2 = r2 + r4
            r5.scrollTo(r3, r2)
            r5.mReceivedDown = r3
            boolean r2 = r5.mIsBeingDragged
            if (r2 == 0) goto L_0x0099
            int r2 = r5.getMaximumScrollUpwards()
            int r3 = r5.getScroll()
            int r2 = r2 - r3
            float r2 = (float) r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x007a
            android.widget.EdgeEffect r2 = r5.mEdgeGlowBottom
            int r3 = r5.getHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            r3 = 1065353216(0x3f800000, float:1.0)
            float r6 = r6.getX()
            int r4 = r5.getWidth()
            float r4 = (float) r4
            float r6 = r6 / r4
            float r3 = r3 - r6
            com.android.contacts.compat.EdgeEffectCompat.onPull(r2, r0, r3)
        L_0x007a:
            android.widget.EdgeEffect r6 = r5.mEdgeGlowBottom
            boolean r6 = r6.isFinished()
            if (r6 != 0) goto L_0x0085
            r5.postInvalidateOnAnimation()
        L_0x0085:
            boolean r6 = r5.shouldDismissOnScroll()
            if (r6 == 0) goto L_0x0099
            r5.scrollOffBottom()
            goto L_0x0099
        L_0x008f:
            if (r0 != r2) goto L_0x0093
            r6 = 1
            goto L_0x0094
        L_0x0093:
            r6 = 0
        L_0x0094:
            r5.stopDrag(r6)
            r5.mReceivedDown = r3
        L_0x0099:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.widget.MultiShrinkScroller.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setHeaderTintColor(int i) {
        this.mHeaderTintColor = i;
        updatePhotoTintAndDropShadow();
        if (CompatUtils.isLollipopCompatible()) {
            this.mEdgeGlowBottom.setColor((i & 16777215) | Color.argb(Color.alpha(this.mEdgeGlowBottom.getColor()), 0, 0, 0));
            this.mEdgeGlowTop.setColor(this.mEdgeGlowBottom.getColor());
        }
    }

    /* access modifiers changed from: private */
    public void expandHeader() {
        int headerHeight = getHeaderHeight();
        int i = this.mMaximumHeaderHeight;
        if (headerHeight != i) {
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "headerHeight", new int[]{i});
            ofInt.setDuration(300);
            ofInt.start();
            if (this.mScrollView.getScrollY() != 0) {
                ScrollView scrollView = this.mScrollView;
                ObjectAnimator.ofInt(scrollView, "scrollY", new int[]{-scrollView.getScrollY()}).start();
            }
        }
    }

    private void startDrag() {
        this.mIsBeingDragged = true;
        this.mScroller.abortAnimation();
    }

    private void stopDrag(boolean z) {
        this.mIsBeingDragged = false;
        if (z || getChildCount() <= 0) {
            onDragFinished(0);
        } else {
            float currentVelocity = getCurrentVelocity();
            int i = this.mMinimumVelocity;
            if (currentVelocity > ((float) i) || currentVelocity < ((float) (-i))) {
                fling(-currentVelocity);
                onDragFinished(this.mScroller.getFinalY() - this.mScroller.getStartY());
            } else {
                onDragFinished(0);
            }
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        this.mEdgeGlowBottom.onRelease();
    }

    private void onDragFinished(int i) {
        if (getTransparentViewHeight() > 0 && !snapToTopOnDragFinished(i)) {
            snapToBottomOnDragFinished();
        }
    }

    private boolean snapToTopOnDragFinished(int i) {
        if (!this.mHasEverTouchedTheTop) {
            if (((float) (getTransparentViewHeight() - i)) < ((float) (-this.mSnapToTopSlopHeight)) || getTransparentViewHeight() > this.mTransparentStartHeight) {
                return false;
            }
            this.mScroller.forceFinished(true);
            smoothScrollBy(getTransparentViewHeight());
            return true;
        } else if (getTransparentViewHeight() >= this.mDismissDistanceOnRelease) {
            return false;
        } else {
            this.mScroller.forceFinished(true);
            smoothScrollBy(getTransparentViewHeight());
            return true;
        }
    }

    private void snapToBottomOnDragFinished() {
        if (this.mHasEverTouchedTheTop) {
            if (getTransparentViewHeight() > this.mDismissDistanceOnRelease) {
                scrollOffBottom();
            }
        } else if (getTransparentViewHeight() > this.mTransparentStartHeight) {
            scrollOffBottom();
        }
    }

    private boolean shouldDismissOnScroll() {
        return this.mHasEverTouchedTheTop && getTransparentViewHeight() > this.mDismissDistanceOnScroll;
    }

    public float getStartingTransparentHeightRatio() {
        return getTransparentHeightRatio(this.mTransparentStartHeight);
    }

    private float getTransparentHeightRatio(int i) {
        return 1.0f - Math.max(Math.min(1.0f, ((float) i) / ((float) getHeight())), ContactPhotoManager.OFFSET_DEFAULT);
    }

    public void scrollOffBottom() {
        this.mIsTouchDisabledForDismissAnimation = true;
        AcceleratingFlingInterpolator acceleratingFlingInterpolator = new AcceleratingFlingInterpolator(250, getCurrentVelocity(), getScrollUntilOffBottom());
        this.mScroller.forceFinished(true);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "scroll", new int[]{getScroll() - getScrollUntilOffBottom()});
        ofInt.setRepeatCount(0);
        ofInt.setInterpolator(acceleratingFlingInterpolator);
        ofInt.setDuration(250);
        ofInt.addListener(this.mSnapToBottomListener);
        ofInt.start();
        MultiShrinkScrollerListener multiShrinkScrollerListener = this.mListener;
        if (multiShrinkScrollerListener != null) {
            multiShrinkScrollerListener.onStartScrollOffBottom();
        }
    }

    public void scrollUpForEntranceAnimation(boolean z) {
        int i;
        int scroll = getScroll();
        int height = (scroll - (getHeight() - getTransparentViewHeight())) + 1;
        Interpolator loadInterpolator = AnimationUtils.loadInterpolator(getContext(), 17563662);
        if (z) {
            i = scroll;
        } else {
            i = getTransparentViewHeight();
        }
        final int i2 = scroll + i;
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "scroll", new int[]{height, i2});
        ofInt.setInterpolator(loadInterpolator);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.getAnimatedValue().equals(Integer.valueOf(i2)) && MultiShrinkScroller.this.mListener != null) {
                    MultiShrinkScroller.this.mListener.onEntranceAnimationDone();
                }
            }
        });
        ofInt.start();
    }

    public void scrollTo(int i, int i2) {
        int scroll = i2 - getScroll();
        boolean z = true;
        boolean z2 = getScrollNeededToBeFullScreen() <= 0;
        if (scroll > 0) {
            scrollUp(scroll);
        } else {
            scrollDown(scroll);
        }
        updatePhotoTintAndDropShadow();
        updateHeaderTextSizeAndMargin();
        if (getScrollNeededToBeFullScreen() > 0) {
            z = false;
        }
        this.mHasEverTouchedTheTop |= z;
        MultiShrinkScrollerListener multiShrinkScrollerListener = this.mListener;
        if (multiShrinkScrollerListener != null) {
            if (z2 && !z) {
                multiShrinkScrollerListener.onExitFullscreen();
            } else if (!z2 && z) {
                this.mListener.onEnterFullscreen();
            }
            if (!z || !z2) {
                this.mListener.onTransparentViewHeightChange(getTransparentHeightRatio(getTransparentViewHeight()));
            }
        }
    }

    public void setToolbarHeight(int i) {
        ViewGroup.LayoutParams layoutParams = this.mToolbar.getLayoutParams();
        layoutParams.height = i;
        this.mToolbar.setLayoutParams(layoutParams);
        updatePhotoTintAndDropShadow();
        updateHeaderTextSizeAndMargin();
    }

    public int getToolbarHeight() {
        return this.mToolbar.getLayoutParams().height;
    }

    public void setHeaderHeight(int i) {
        ViewGroup.LayoutParams layoutParams = this.mToolbar.getLayoutParams();
        layoutParams.height = i;
        this.mToolbar.setLayoutParams(layoutParams);
        updatePhotoTintAndDropShadow();
        updateHeaderTextSizeAndMargin();
    }

    public int getHeaderHeight() {
        return this.mToolbar.getLayoutParams().height;
    }

    public void setScroll(int i) {
        scrollTo(0, i);
    }

    public int getScroll() {
        return (((this.mTransparentStartHeight - getTransparentViewHeight()) + getMaximumScrollableHeaderHeight()) - getToolbarHeight()) + this.mScrollView.getScrollY();
    }

    /* access modifiers changed from: private */
    public int getMaximumScrollableHeaderHeight() {
        return this.mIsOpenContactSquare ? this.mMaximumHeaderHeight : this.mIntermediateHeaderHeight;
    }

    private int getScroll_ignoreOversizedHeaderForSnapping() {
        return (this.mTransparentStartHeight - getTransparentViewHeight()) + Math.max(getMaximumScrollableHeaderHeight() - getToolbarHeight(), 0) + this.mScrollView.getScrollY();
    }

    public int getScrollNeededToBeFullScreen() {
        return getTransparentViewHeight();
    }

    /* access modifiers changed from: private */
    public int getScrollUntilOffBottom() {
        return (getHeight() + getScroll_ignoreOversizedHeaderForSnapping()) - this.mTransparentStartHeight;
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            int scroll = getScroll();
            scrollTo(0, this.mScroller.getCurrY());
            int currY = this.mScroller.getCurrY() - scroll;
            int maximumScrollUpwards = getMaximumScrollUpwards() - getScroll();
            if (currY > maximumScrollUpwards && maximumScrollUpwards > 0) {
                this.mEdgeGlowBottom.onAbsorb((int) this.mScroller.getCurrVelocity());
            }
            if (this.mIsFullscreenDownwardsFling && getTransparentViewHeight() > 0) {
                scrollTo(0, getScroll() + getTransparentViewHeight());
                this.mEdgeGlowTop.onAbsorb((int) this.mScroller.getCurrVelocity());
                this.mScroller.abortAnimation();
                this.mIsFullscreenDownwardsFling = false;
            }
            if (!awakenScrollBars()) {
                postInvalidateOnAnimation();
            }
            if (this.mScroller.getCurrY() >= getMaximumScrollUpwards()) {
                this.mScroller.abortAnimation();
                this.mIsFullscreenDownwardsFling = false;
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = getHeight();
        if (!this.mEdgeGlowBottom.isFinished()) {
            int save = canvas.save();
            canvas.translate((float) ((-width) + getPaddingLeft()), (float) ((getMaximumScrollUpwards() + height) - getScroll()));
            canvas.rotate(180.0f, (float) width, ContactPhotoManager.OFFSET_DEFAULT);
            if (this.mIsTwoPanel) {
                this.mEdgeGlowBottom.setSize(this.mScrollView.getWidth(), height);
                if (getLayoutDirection() == 1) {
                    canvas.translate((float) this.mPhotoViewContainer.getWidth(), ContactPhotoManager.OFFSET_DEFAULT);
                }
            } else {
                this.mEdgeGlowBottom.setSize(width, height);
            }
            if (this.mEdgeGlowBottom.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(save);
        }
        if (!this.mEdgeGlowTop.isFinished()) {
            int save2 = canvas.save();
            if (this.mIsTwoPanel) {
                this.mEdgeGlowTop.setSize(this.mScrollView.getWidth(), height);
                if (getLayoutDirection() != 1) {
                    canvas.translate((float) this.mPhotoViewContainer.getWidth(), ContactPhotoManager.OFFSET_DEFAULT);
                }
            } else {
                this.mEdgeGlowTop.setSize(width, height);
            }
            if (this.mEdgeGlowTop.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(save2);
        }
    }

    private float getCurrentVelocity() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            return ContactPhotoManager.OFFSET_DEFAULT;
        }
        velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
        return this.mVelocityTracker.getYVelocity();
    }

    private void fling(float f) {
        this.mScroller.fling(0, getScroll(), 0, (int) f, 0, 0, -2147483647, Integer.MAX_VALUE);
        if (f < ContactPhotoManager.OFFSET_DEFAULT && this.mTransparentView.getHeight() <= 0) {
            this.mIsFullscreenDownwardsFling = true;
        }
        invalidate();
    }

    private int getMaximumScrollUpwards() {
        int i;
        int max;
        if (!this.mIsTwoPanel) {
            i = (this.mTransparentStartHeight + getMaximumScrollableHeaderHeight()) - getFullyCompressedHeaderHeight();
            max = Math.max(0, (this.mScrollViewChild.getHeight() - getHeight()) + getFullyCompressedHeaderHeight());
        } else {
            i = this.mTransparentStartHeight;
            max = Math.max(0, this.mScrollViewChild.getHeight() - getHeight());
        }
        return i + max;
    }

    private int getTransparentViewHeight() {
        return this.mTransparentView.getLayoutParams().height;
    }

    private void setTransparentViewHeight(int i) {
        this.mTransparentView.getLayoutParams().height = i;
        View view = this.mTransparentView;
        view.setLayoutParams(view.getLayoutParams());
    }

    private void scrollUp(int i) {
        if (getTransparentViewHeight() != 0) {
            int transparentViewHeight = getTransparentViewHeight();
            setTransparentViewHeight(getTransparentViewHeight() - i);
            setTransparentViewHeight(Math.max(0, getTransparentViewHeight()));
            i -= transparentViewHeight - getTransparentViewHeight();
        }
        ViewGroup.LayoutParams layoutParams = this.mToolbar.getLayoutParams();
        if (layoutParams.height > getFullyCompressedHeaderHeight()) {
            int i2 = layoutParams.height;
            layoutParams.height = i2 - i;
            layoutParams.height = Math.max(layoutParams.height, getFullyCompressedHeaderHeight());
            this.mToolbar.setLayoutParams(layoutParams);
            i -= i2 - layoutParams.height;
        }
        this.mScrollView.scrollBy(0, i);
    }

    private int getFullyCompressedHeaderHeight() {
        return Math.min(Math.max(this.mToolbar.getLayoutParams().height - getOverflowingChildViewSize(), this.mMinimumHeaderHeight), getMaximumScrollableHeaderHeight());
    }

    private int getOverflowingChildViewSize() {
        return (-getHeight()) + this.mScrollViewChild.getHeight() + this.mToolbar.getLayoutParams().height;
    }

    private void scrollDown(int i) {
        if (this.mScrollView.getScrollY() > 0) {
            int scrollY = this.mScrollView.getScrollY();
            this.mScrollView.scrollBy(0, i);
            i -= this.mScrollView.getScrollY() - scrollY;
        }
        ViewGroup.LayoutParams layoutParams = this.mToolbar.getLayoutParams();
        if (layoutParams.height < getMaximumScrollableHeaderHeight()) {
            int i2 = layoutParams.height;
            layoutParams.height = i2 - i;
            layoutParams.height = Math.min(layoutParams.height, getMaximumScrollableHeaderHeight());
            this.mToolbar.setLayoutParams(layoutParams);
            i -= i2 - layoutParams.height;
        }
        setTransparentViewHeight(getTransparentViewHeight() - i);
        if (getScrollUntilOffBottom() <= 0) {
            post(new Runnable() {
                public void run() {
                    if (MultiShrinkScroller.this.mListener != null) {
                        MultiShrinkScroller.this.mListener.onScrolledOffBottom();
                        MultiShrinkScrollerListener unused = MultiShrinkScroller.this.mListener = null;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateHeaderTextSizeAndMargin() {
        if (!this.mIsTwoPanel) {
            boolean z = true;
            if (getLayoutDirection() == 1) {
                View view = this.mTitleAndPhoneticNameView;
                view.setPivotX((float) view.getWidth());
            } else {
                this.mTitleAndPhoneticNameView.setPivotX(ContactPhotoManager.OFFSET_DEFAULT);
            }
            this.mTitleAndPhoneticNameView.setPivotY((float) (this.mMaximumHeaderTextSize / 2));
            int i = this.mToolbar.getLayoutParams().height;
            View view2 = this.mPhotoTouchInterceptOverlay;
            if (i == this.mMaximumHeaderHeight) {
                z = false;
            }
            view2.setClickable(z);
            int i2 = this.mMaximumHeaderHeight;
            if (i >= i2) {
                this.mTitleAndPhoneticNameView.setScaleX(1.0f);
                this.mTitleAndPhoneticNameView.setScaleY(1.0f);
                setInterpolatedTitleMargins(1.0f);
                return;
            }
            int i3 = this.mMinimumHeaderHeight;
            float f = ((float) (i - i3)) / ((float) (i2 - i3));
            float height = (float) this.mInvisiblePlaceholderTextView.getHeight();
            float interpolation = this.mTextSizePathInterpolator.getInterpolation(f);
            int i4 = this.mMaximumHeaderTextSize;
            float min = Math.min(interpolation, 1.0f);
            float min2 = Math.min((height + ((((float) i4) - height) * interpolation)) / ((float) i4), 1.0f);
            this.mTitleAndPhoneticNameView.setScaleX(min2);
            this.mTitleAndPhoneticNameView.setScaleY(min2);
            setInterpolatedTitleMargins(min);
        }
    }

    /* access modifiers changed from: private */
    public void calculateCollapsedLargeTitlePadding() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.mInvisiblePlaceholderTextView.getLocationOnScreen(iArr);
        this.mToolbar.getLocationOnScreen(iArr2);
        this.mCollapsedTitleBottomMargin = ((iArr[1] + (this.mInvisiblePlaceholderTextView.getHeight() / 2)) - iArr2[1]) - (this.mMaximumHeaderTextSize / 2);
    }

    private void setInterpolatedTitleMargins(float f) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mTitleAndPhoneticNameView.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mToolbar.getLayoutParams();
        View view = this.mStartColumn;
        float f2 = 1.0f - f;
        layoutParams.setMarginStart(((int) ((((float) this.mCollapsedTitleStartMargin) * f2) + (((float) this.mMaximumTitleMargin) * f))) + (view == null ? 0 : view.getWidth()));
        layoutParams.topMargin = ((getTransparentViewHeight() + layoutParams2.height) - ((int) ((((float) this.mCollapsedTitleBottomMargin) * f2) + (((float) this.mMaximumTitleMargin) * f)))) - this.mMaximumHeaderTextSize;
        layoutParams.bottomMargin = 0;
        this.mTitleAndPhoneticNameView.setLayoutParams(layoutParams);
    }

    private void updatePhotoTintAndDropShadow() {
        Trace.beginSection("updatePhotoTintAndDropShadow");
        this.mPhotoView.setTint(this.mHeaderTintColor);
        if (!this.mIsTwoPanel || this.mPhotoView.isBasedOffLetterTile()) {
            int toolbarHeight = getToolbarHeight();
            if (toolbarHeight > this.mMinimumHeaderHeight || this.mIsTwoPanel) {
                ViewCompat.setElevation(this.mPhotoViewContainer, ContactPhotoManager.OFFSET_DEFAULT);
            } else {
                ViewCompat.setElevation(this.mPhotoViewContainer, this.mToolbarElevation);
            }
            this.mPhotoView.clearColorFilter();
            this.mColorMatrix.reset();
            int i = 0;
            if (!this.mPhotoView.isBasedOffLetterTile()) {
                double calculateHeightRatioToBlendingStartHeight = (double) calculateHeightRatioToBlendingStartHeight(toolbarHeight);
                float min = 1.0f - ((float) Math.min(Math.pow(calculateHeightRatioToBlendingStartHeight, 1.5d) * 2.0d, 1.0d));
                this.mColorMatrix.setSaturation(min);
                this.mColorMatrix.postConcat(alphaMatrix(min, -1));
                this.mColorMatrix.postConcat(multiplyBlendMatrix(this.mHeaderTintColor, (float) Math.min(Math.pow(calculateHeightRatioToBlendingStartHeight, 1.5d) * 3.0d, 1.0d)));
                i = (int) (min * 255.0f);
            } else if (this.mIsTwoPanel) {
                this.mColorMatrix.reset();
                this.mColorMatrix.postConcat(alphaMatrix(0.8f, this.mHeaderTintColor));
            } else {
                float calculateHeightRatioToFullyOpen = calculateHeightRatioToFullyOpen(toolbarHeight);
                float calculateHeightRatioToFullyOpen2 = calculateHeightRatioToFullyOpen((int) (((float) this.mMaximumPortraitHeaderHeight) * 0.6f));
                this.mColorMatrix.postConcat(alphaMatrix(1.0f - ((float) Math.pow((double) Math.max(1.0f - (((1.0f - calculateHeightRatioToFullyOpen) / calculateHeightRatioToFullyOpen2) / ((float) (((double) ((1.0f - calculateHeightRatioToFullyOpen2) / calculateHeightRatioToFullyOpen2)) / (1.0d - Math.pow(0.19999998807907104d, 0.3333333432674408d))))), ContactPhotoManager.OFFSET_DEFAULT), 3.0d)), this.mHeaderTintColor));
            }
            this.mPhotoView.setColorFilter(new ColorMatrixColorFilter(this.mColorMatrix));
            this.mTitleGradientDrawable.setAlpha(i);
            this.mActionBarGradientDrawable.setAlpha(i);
            Trace.endSection();
            return;
        }
        this.mTitleGradientDrawable.setAlpha(255);
        this.mActionBarGradientDrawable.setAlpha(255);
    }

    private float calculateHeightRatioToFullyOpen(int i) {
        int i2 = this.mMinimumPortraitHeaderHeight;
        return ((float) (i - i2)) / ((float) (this.mMaximumPortraitHeaderHeight - i2));
    }

    private float calculateHeightRatioToBlendingStartHeight(int i) {
        float f = ((float) this.mMaximumPortraitHeaderHeight) * 0.5f;
        float f2 = (float) i;
        return f2 > f ? ContactPhotoManager.OFFSET_DEFAULT : (f - f2) / (f - ((float) this.mMinimumPortraitHeaderHeight));
    }

    private ColorMatrix alphaMatrix(float f, int i) {
        this.mAlphaMatrixValues[0] = (((float) Color.red(i)) * f) / 255.0f;
        this.mAlphaMatrixValues[6] = (((float) Color.green(i)) * f) / 255.0f;
        this.mAlphaMatrixValues[12] = (((float) Color.blue(i)) * f) / 255.0f;
        float[] fArr = this.mAlphaMatrixValues;
        float f2 = (1.0f - f) * 255.0f;
        fArr[4] = f2;
        fArr[9] = f2;
        fArr[14] = f2;
        this.mWhitenessColorMatrix.set(fArr);
        return this.mWhitenessColorMatrix;
    }

    private ColorMatrix multiplyBlendMatrix(int i, float f) {
        this.mMultiplyBlendMatrixValues[0] = multiplyBlend(Color.red(i), f);
        this.mMultiplyBlendMatrixValues[6] = multiplyBlend(Color.green(i), f);
        this.mMultiplyBlendMatrixValues[12] = multiplyBlend(Color.blue(i), f);
        this.mMultiplyBlendMatrix.set(this.mMultiplyBlendMatrixValues);
        return this.mMultiplyBlendMatrix;
    }

    private void updateLastEventPosition(MotionEvent motionEvent) {
        this.mLastEventPosition[0] = motionEvent.getX();
        this.mLastEventPosition[1] = motionEvent.getY();
    }

    private boolean motionShouldStartDrag(MotionEvent motionEvent) {
        float y = motionEvent.getY() - this.mLastEventPosition[1];
        int i = this.mTouchSlop;
        if (y > ((float) i) || y < ((float) (-i))) {
            return true;
        }
        return false;
    }

    private float updatePositionAndComputeDelta(MotionEvent motionEvent) {
        float f = this.mLastEventPosition[1];
        updateLastEventPosition(motionEvent);
        float f2 = 1.0f;
        if (f < this.mLastEventPosition[1] && this.mHasEverTouchedTheTop) {
            f2 = 1.0f + (((float) this.mTransparentView.getHeight()) * 0.01f);
        }
        return (f - this.mLastEventPosition[1]) / f2;
    }

    private void smoothScrollBy(int i) {
        if (i != 0) {
            this.mScroller.startScroll(0, getScroll(), 0, i);
            invalidate();
            return;
        }
        throw new IllegalArgumentException("Smooth scrolling by delta=0 is pointless and harmful");
    }

    private class AcceleratingFlingInterpolator implements Interpolator {
        private final float mDurationMs;
        private final float mNumberFrames = (this.mDurationMs / ((float) getFrameIntervalMs()));
        private final int mPixelsDelta;
        private final float mStartingSpeedPixelsPerFrame;

        public AcceleratingFlingInterpolator(int i, float f, int i2) {
            this.mStartingSpeedPixelsPerFrame = f / getRefreshRate();
            this.mDurationMs = (float) i;
            this.mPixelsDelta = i2;
        }

        public float getInterpolation(float f) {
            float f2 = this.mStartingSpeedPixelsPerFrame;
            float f3 = ((this.mNumberFrames * f) * f2) / ((float) this.mPixelsDelta);
            if (f2 > ContactPhotoManager.OFFSET_DEFAULT) {
                return Math.min((f * f) + f3, 1.0f);
            }
            return Math.min((f * (f - f3)) + f3, 1.0f);
        }

        private float getRefreshRate() {
            return ((DisplayManager) MultiShrinkScroller.this.getContext().getSystemService("display")).getDisplay(0).getRefreshRate();
        }

        public long getFrameIntervalMs() {
            return (long) (1000.0f / getRefreshRate());
        }
    }

    public void prepareForShrinkingScrollChild(int i) {
        int i2 = (-getOverflowingChildViewSize()) + i;
        if (i2 > 0 && !this.mIsTwoPanel) {
            ObjectAnimator.ofInt(this, "toolbarHeight", new int[]{Math.min(getToolbarHeight() + i2, getMaximumScrollableHeaderHeight())}).setDuration(300).start();
        }
    }

    public void setDisableTouchesForSuppressLayout(boolean z) {
        this.mIsTouchDisabledForSuppressLayout = z;
    }
}
