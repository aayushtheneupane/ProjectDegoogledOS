package com.android.systemui.bubbles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.StatsLog;
import android.view.Choreographer;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.widget.ViewClippingUtil;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.bubbles.animation.ExpandedAnimationController;
import com.android.systemui.bubbles.animation.PhysicsAnimationLayout;
import com.android.systemui.bubbles.animation.StackAnimationController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class BubbleStackView extends FrameLayout {
    private static final SurfaceSynchronizer DEFAULT_SURFACE_SYNCHRONIZER = new SurfaceSynchronizer() {
        public void syncSurfaceAndRun(final Runnable runnable) {
            Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
                private int mFrameWait = 2;

                public void doFrame(long j) {
                    int i = this.mFrameWait - 1;
                    this.mFrameWait = i;
                    if (i > 0) {
                        Choreographer.getInstance().postFrameCallback(this);
                    } else {
                        runnable.run();
                    }
                }
            });
        }
    };
    @VisibleForTesting
    static final int FLYOUT_HIDE_AFTER = 5000;
    private final DynamicAnimation.OnAnimationEndListener mAfterFlyoutTransitionSpring = new DynamicAnimation.OnAnimationEndListener() {
        public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
            BubbleStackView.this.lambda$new$1$BubbleStackView(dynamicAnimation, z, f, f2);
        }
    };
    private Runnable mAfterMagnet;
    private Runnable mAnimateInFlyout;
    private boolean mAnimatingMagnet = false;
    private PhysicsAnimationLayout mBubbleContainer;
    private final BubbleData mBubbleData;
    private BubbleIconFactory mBubbleIconFactory;
    private int mBubblePaddingTop;
    private int mBubbleSize;
    private Bubble mBubbleToExpandAfterFlyoutCollapse = null;
    private int mBubbleTouchPadding;
    private ViewClippingUtil.ClippingParameters mClippingParameters = new ViewClippingUtil.ClippingParameters() {
        public boolean shouldFinish(View view) {
            return false;
        }

        public boolean isClippingEnablingAllowed(View view) {
            return !BubbleStackView.this.mIsExpanded;
        }
    };
    private final ValueAnimator mDesaturateAndDarkenAnimator;
    private final Paint mDesaturateAndDarkenPaint = new Paint();
    private View mDesaturateAndDarkenTargetView;
    private BubbleDismissView mDismissContainer;
    private Point mDisplaySize;
    private boolean mDraggingInDismissTarget = false;
    private BubbleController.BubbleExpandListener mExpandListener;
    private int mExpandedAnimateXDistance;
    private int mExpandedAnimateYDistance;
    private ExpandedAnimationController mExpandedAnimationController;
    private Bubble mExpandedBubble;
    private FrameLayout mExpandedViewContainer;
    private int mExpandedViewPadding;
    private final SpringAnimation mExpandedViewXAnim;
    private final SpringAnimation mExpandedViewYAnim;
    private BubbleFlyoutView mFlyout;
    private final FloatPropertyCompat mFlyoutCollapseProperty = new FloatPropertyCompat("FlyoutCollapseSpring") {
        public float getValue(Object obj) {
            return BubbleStackView.this.mFlyoutDragDeltaX;
        }

        public void setValue(Object obj, float f) {
            BubbleStackView.this.onFlyoutDragged(f);
        }
    };
    /* access modifiers changed from: private */
    public float mFlyoutDragDeltaX = 0.0f;
    private Runnable mFlyoutOnHide;
    private final SpringAnimation mFlyoutTransitionSpring = new SpringAnimation(this, this.mFlyoutCollapseProperty);
    private Runnable mHideFlyout = new Runnable() {
        public final void run() {
            BubbleStackView.this.lambda$new$0$BubbleStackView();
        }
    };
    private int mImeOffset;
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public boolean mIsExpanded;
    private boolean mIsExpansionAnimating = false;
    private boolean mIsGestureInProgress = false;
    private int mOrientation = 0;
    private View.OnLayoutChangeListener mOrientationChangedListener;
    private int mPointerHeight;
    private boolean mShowingDismiss = false;
    private StackAnimationController mStackAnimationController;
    private boolean mStackOnLeftOrWillBe = false;
    private int mStatusBarHeight;
    private final SurfaceSynchronizer mSurfaceSynchronizer;
    private ViewTreeObserver.OnDrawListener mSystemGestureExcludeUpdater = new ViewTreeObserver.OnDrawListener() {
        public final void onDraw() {
            BubbleStackView.this.updateSystemGestureExcludeRects();
        }
    };
    private final List<Rect> mSystemGestureExclusionRects = Collections.singletonList(new Rect());
    int[] mTempLoc = new int[2];
    RectF mTempRect = new RectF();
    private BubbleTouchHandler mTouchHandler;
    private float mVerticalPosPercentBeforeRotation = -1.0f;
    private final Vibrator mVibrator;
    /* access modifiers changed from: private */
    public boolean mViewUpdatedRequested = false;
    /* access modifiers changed from: private */
    public ViewTreeObserver.OnPreDrawListener mViewUpdater = new ViewTreeObserver.OnPreDrawListener() {
        public boolean onPreDraw() {
            BubbleStackView.this.getViewTreeObserver().removeOnPreDrawListener(BubbleStackView.this.mViewUpdater);
            BubbleStackView.this.updateExpandedView();
            boolean unused = BubbleStackView.this.mViewUpdatedRequested = false;
            return true;
        }
    };
    private boolean mWasOnLeftBeforeRotation = false;

    interface SurfaceSynchronizer {
        void syncSurfaceAndRun(Runnable runnable);
    }

    public /* synthetic */ void lambda$new$0$BubbleStackView() {
        animateFlyoutCollapsed(true, 0.0f);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("Stack view state:");
        printWriter.print("  gestureInProgress:    ");
        printWriter.println(this.mIsGestureInProgress);
        printWriter.print("  showingDismiss:       ");
        printWriter.println(this.mShowingDismiss);
        printWriter.print("  isExpansionAnimating: ");
        printWriter.println(this.mIsExpansionAnimating);
        printWriter.print("  draggingInDismiss:    ");
        printWriter.println(this.mDraggingInDismissTarget);
        printWriter.print("  animatingMagnet:      ");
        printWriter.println(this.mAnimatingMagnet);
        this.mStackAnimationController.dump(fileDescriptor, printWriter, strArr);
        this.mExpandedAnimationController.dump(fileDescriptor, printWriter, strArr);
    }

    public /* synthetic */ void lambda$new$1$BubbleStackView(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        if (this.mFlyoutDragDeltaX == 0.0f) {
            this.mFlyout.postDelayed(this.mHideFlyout, 5000);
        } else {
            this.mFlyout.hideFlyout();
        }
    }

    public BubbleStackView(Context context, BubbleData bubbleData, SurfaceSynchronizer surfaceSynchronizer) {
        super(context);
        this.mBubbleData = bubbleData;
        this.mInflater = LayoutInflater.from(context);
        this.mTouchHandler = new BubbleTouchHandler(this, bubbleData, context);
        setOnTouchListener(this.mTouchHandler);
        this.mInflater = LayoutInflater.from(context);
        Resources resources = getResources();
        this.mBubbleSize = resources.getDimensionPixelSize(C1775R$dimen.individual_bubble_size);
        this.mBubblePaddingTop = resources.getDimensionPixelSize(C1775R$dimen.bubble_padding_top);
        this.mBubbleTouchPadding = resources.getDimensionPixelSize(C1775R$dimen.bubble_touch_padding);
        this.mExpandedAnimateXDistance = resources.getDimensionPixelSize(C1775R$dimen.bubble_expanded_animate_x_distance);
        this.mExpandedAnimateYDistance = resources.getDimensionPixelSize(C1775R$dimen.bubble_expanded_animate_y_distance);
        this.mPointerHeight = resources.getDimensionPixelSize(C1775R$dimen.bubble_pointer_height);
        this.mStatusBarHeight = resources.getDimensionPixelSize(17105434);
        this.mImeOffset = resources.getDimensionPixelSize(C1775R$dimen.pip_ime_offset);
        this.mDisplaySize = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(this.mDisplaySize);
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
        this.mExpandedViewPadding = resources.getDimensionPixelSize(C1775R$dimen.bubble_expanded_view_padding);
        int dimensionPixelSize = resources.getDimensionPixelSize(C1775R$dimen.bubble_elevation);
        this.mStackAnimationController = new StackAnimationController();
        this.mExpandedAnimationController = new ExpandedAnimationController(this.mDisplaySize, this.mExpandedViewPadding, resources.getConfiguration().orientation);
        this.mSurfaceSynchronizer = surfaceSynchronizer == null ? DEFAULT_SURFACE_SYNCHRONIZER : surfaceSynchronizer;
        this.mBubbleContainer = new PhysicsAnimationLayout(context);
        this.mBubbleContainer.setActiveController(this.mStackAnimationController);
        float f = (float) dimensionPixelSize;
        this.mBubbleContainer.setElevation(f);
        this.mBubbleContainer.setClipChildren(false);
        addView(this.mBubbleContainer, new FrameLayout.LayoutParams(-1, -1));
        this.mBubbleIconFactory = new BubbleIconFactory(context);
        this.mExpandedViewContainer = new FrameLayout(context);
        this.mExpandedViewContainer.setElevation(f);
        FrameLayout frameLayout = this.mExpandedViewContainer;
        int i = this.mExpandedViewPadding;
        frameLayout.setPadding(i, i, i, i);
        this.mExpandedViewContainer.setClipChildren(false);
        addView(this.mExpandedViewContainer);
        setUpFlyout();
        SpringAnimation springAnimation = this.mFlyoutTransitionSpring;
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(200.0f);
        springForce.setDampingRatio(0.75f);
        springAnimation.setSpring(springForce);
        this.mFlyoutTransitionSpring.addEndListener(this.mAfterFlyoutTransitionSpring);
        this.mDismissContainer = new BubbleDismissView(this.mContext);
        this.mDismissContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(C1775R$dimen.pip_dismiss_gradient_height), 80));
        addView(this.mDismissContainer);
        this.mExpandedViewXAnim = new SpringAnimation(this.mExpandedViewContainer, DynamicAnimation.TRANSLATION_X);
        SpringAnimation springAnimation2 = this.mExpandedViewXAnim;
        SpringForce springForce2 = new SpringForce();
        springForce2.setStiffness(200.0f);
        springForce2.setDampingRatio(0.75f);
        springAnimation2.setSpring(springForce2);
        this.mExpandedViewYAnim = new SpringAnimation(this.mExpandedViewContainer, DynamicAnimation.TRANSLATION_Y);
        SpringAnimation springAnimation3 = this.mExpandedViewYAnim;
        SpringForce springForce3 = new SpringForce();
        springForce3.setStiffness(200.0f);
        springForce3.setDampingRatio(0.75f);
        springAnimation3.setSpring(springForce3);
        this.mExpandedViewYAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                BubbleStackView.this.lambda$new$2$BubbleStackView(dynamicAnimation, z, f, f2);
            }
        });
        setClipChildren(false);
        setFocusable(true);
        this.mBubbleContainer.bringToFront();
        setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return BubbleStackView.this.lambda$new$4$BubbleStackView(view, windowInsets);
            }
        });
        this.mOrientationChangedListener = new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BubbleStackView.this.lambda$new$6$BubbleStackView(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        };
        getViewTreeObserver().addOnDrawListener(this.mSystemGestureExcludeUpdater);
        ColorMatrix colorMatrix = new ColorMatrix();
        ColorMatrix colorMatrix2 = new ColorMatrix();
        this.mDesaturateAndDarkenAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.mDesaturateAndDarkenAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(colorMatrix, colorMatrix2) {
            private final /* synthetic */ ColorMatrix f$1;
            private final /* synthetic */ ColorMatrix f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BubbleStackView.this.lambda$new$7$BubbleStackView(this.f$1, this.f$2, valueAnimator);
            }
        });
    }

    public /* synthetic */ void lambda$new$2$BubbleStackView(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        Bubble bubble;
        if (this.mIsExpanded && (bubble = this.mExpandedBubble) != null) {
            bubble.getExpandedView().updateView();
        }
    }

    public /* synthetic */ WindowInsets lambda$new$4$BubbleStackView(View view, WindowInsets windowInsets) {
        if (!this.mIsExpanded || this.mIsExpansionAnimating) {
            return view.onApplyWindowInsets(windowInsets);
        }
        this.mExpandedAnimationController.updateYPosition(new Runnable(windowInsets) {
            private final /* synthetic */ WindowInsets f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                BubbleStackView.this.lambda$new$3$BubbleStackView(this.f$1);
            }
        });
        return view.onApplyWindowInsets(windowInsets);
    }

    public /* synthetic */ void lambda$new$3$BubbleStackView(WindowInsets windowInsets) {
        this.mExpandedBubble.getExpandedView().updateInsets(windowInsets);
    }

    public /* synthetic */ void lambda$new$6$BubbleStackView(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        int i10;
        this.mExpandedAnimationController.updateOrientation(this.mOrientation, this.mDisplaySize);
        this.mStackAnimationController.updateOrientation(this.mOrientation);
        if (this.mIsExpanded) {
            this.mExpandedViewContainer.setTranslationY(getExpandedViewY());
            this.mExpandedBubble.getExpandedView().updateView();
        }
        WindowInsets rootWindowInsets = getRootWindowInsets();
        int i11 = this.mExpandedViewPadding;
        if (rootWindowInsets != null) {
            DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
            int i12 = 0;
            if (displayCutout != null) {
                i12 = displayCutout.getSafeInsetLeft();
                i10 = displayCutout.getSafeInsetRight();
            } else {
                i10 = 0;
            }
            int max = Math.max(i12, rootWindowInsets.getStableInsetLeft()) + i11;
            i9 = i11 + Math.max(i10, rootWindowInsets.getStableInsetRight());
            i11 = max;
        } else {
            i9 = i11;
        }
        FrameLayout frameLayout = this.mExpandedViewContainer;
        int i13 = this.mExpandedViewPadding;
        frameLayout.setPadding(i11, i13, i9, i13);
        if (this.mIsExpanded) {
            this.mExpandedAnimationController.expandFromStack(new Runnable() {
                public final void run() {
                    BubbleStackView.this.lambda$new$5$BubbleStackView();
                }
            });
        }
        float f = this.mVerticalPosPercentBeforeRotation;
        if (f >= 0.0f) {
            this.mStackAnimationController.moveStackToSimilarPositionAfterRotation(this.mWasOnLeftBeforeRotation, f);
        }
        removeOnLayoutChangeListener(this.mOrientationChangedListener);
    }

    public /* synthetic */ void lambda$new$7$BubbleStackView(ColorMatrix colorMatrix, ColorMatrix colorMatrix2, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        colorMatrix.setSaturation(floatValue);
        float f = 1.0f - ((1.0f - floatValue) * 0.3f);
        colorMatrix2.setScale(f, f, f, 1.0f);
        colorMatrix.postConcat(colorMatrix2);
        this.mDesaturateAndDarkenPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        this.mDesaturateAndDarkenTargetView.setLayerPaint(this.mDesaturateAndDarkenPaint);
    }

    private void setUpFlyout() {
        BubbleFlyoutView bubbleFlyoutView = this.mFlyout;
        if (bubbleFlyoutView != null) {
            removeView(bubbleFlyoutView);
        }
        this.mFlyout = new BubbleFlyoutView(getContext());
        this.mFlyout.setVisibility(8);
        this.mFlyout.animate().setDuration(100).setInterpolator(new AccelerateDecelerateInterpolator());
        addView(this.mFlyout, new FrameLayout.LayoutParams(-2, -2));
    }

    public void onThemeChanged() {
        this.mBubbleIconFactory = new BubbleIconFactory(this.mContext);
        setUpFlyout();
        for (Bubble next : this.mBubbleData.getBubbles()) {
            next.getIconView().setBubbleIconFactory(this.mBubbleIconFactory);
            next.getIconView().updateViews();
            next.getExpandedView().applyThemeAttrs();
        }
    }

    public void onOrientationChanged(int i) {
        this.mOrientation = i;
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRealSize(this.mDisplaySize);
        Resources resources = getContext().getResources();
        this.mStatusBarHeight = resources.getDimensionPixelSize(17105434);
        this.mBubblePaddingTop = resources.getDimensionPixelSize(C1775R$dimen.bubble_padding_top);
        RectF allowableStackPositionRegion = this.mStackAnimationController.getAllowableStackPositionRegion();
        this.mWasOnLeftBeforeRotation = this.mStackAnimationController.isStackOnLeftSide();
        float f = this.mStackAnimationController.getStackPosition().y;
        float f2 = allowableStackPositionRegion.top;
        this.mVerticalPosPercentBeforeRotation = (f - f2) / (allowableStackPositionRegion.bottom - f2);
        addOnLayoutChangeListener(this.mOrientationChangedListener);
        hideFlyoutImmediate();
    }

    public void getBoundsOnScreen(Rect rect, boolean z) {
        getBoundsOnScreen(rect);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnPreDrawListener(this.mViewUpdater);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(C1777R$id.action_move_top_left, getContext().getResources().getString(C1784R$string.bubble_accessibility_action_move_top_left)));
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(C1777R$id.action_move_top_right, getContext().getResources().getString(C1784R$string.bubble_accessibility_action_move_top_right)));
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(C1777R$id.action_move_bottom_left, getContext().getResources().getString(C1784R$string.bubble_accessibility_action_move_bottom_left)));
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(C1777R$id.action_move_bottom_right, getContext().getResources().getString(C1784R$string.bubble_accessibility_action_move_bottom_right)));
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_DISMISS);
        if (this.mIsExpanded) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
        } else {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
        }
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        if (super.performAccessibilityActionInternal(i, bundle)) {
            return true;
        }
        RectF allowableStackPositionRegion = this.mStackAnimationController.getAllowableStackPositionRegion();
        if (i == 1048576) {
            this.mBubbleData.dismissAll(6);
            return true;
        } else if (i == 524288) {
            this.mBubbleData.setExpanded(false);
            return true;
        } else if (i == 262144) {
            this.mBubbleData.setExpanded(true);
            return true;
        } else if (i == C1777R$id.action_move_top_left) {
            this.mStackAnimationController.springStack(allowableStackPositionRegion.left, allowableStackPositionRegion.top);
            return true;
        } else if (i == C1777R$id.action_move_top_right) {
            this.mStackAnimationController.springStack(allowableStackPositionRegion.right, allowableStackPositionRegion.top);
            return true;
        } else if (i == C1777R$id.action_move_bottom_left) {
            this.mStackAnimationController.springStack(allowableStackPositionRegion.left, allowableStackPositionRegion.bottom);
            return true;
        } else if (i != C1777R$id.action_move_bottom_right) {
            return false;
        } else {
            this.mStackAnimationController.springStack(allowableStackPositionRegion.right, allowableStackPositionRegion.bottom);
            return true;
        }
    }

    public void updateContentDescription() {
        if (!this.mBubbleData.getBubbles().isEmpty()) {
            Bubble bubble = this.mBubbleData.getBubbles().get(0);
            String appName = bubble.getAppName();
            CharSequence charSequence = bubble.getEntry().notification.getNotification().extras.getCharSequence("android.title");
            String string = getResources().getString(C1784R$string.stream_notification);
            if (charSequence != null) {
                string = charSequence.toString();
            }
            int childCount = this.mBubbleContainer.getChildCount() - 1;
            String string2 = getResources().getString(C1784R$string.bubble_content_description_single, new Object[]{string, appName});
            String string3 = getResources().getString(C1784R$string.bubble_content_description_stack, new Object[]{string, appName, Integer.valueOf(childCount)});
            if (!this.mIsExpanded) {
                if (childCount > 0) {
                    this.mBubbleContainer.setContentDescription(string3);
                } else {
                    this.mBubbleContainer.setContentDescription(string2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateSystemGestureExcludeRects() {
        Rect rect = this.mSystemGestureExclusionRects.get(0);
        if (this.mBubbleContainer.getChildCount() > 0) {
            View childAt = this.mBubbleContainer.getChildAt(0);
            rect.set(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            rect.offset((int) (childAt.getTranslationX() + 0.5f), (int) (childAt.getTranslationY() + 0.5f));
            this.mBubbleContainer.setSystemGestureExclusionRects(this.mSystemGestureExclusionRects);
            return;
        }
        rect.setEmpty();
        this.mBubbleContainer.setSystemGestureExclusionRects(Collections.emptyList());
    }

    public void updateDotVisibility(String str) {
        Bubble bubbleWithKey = this.mBubbleData.getBubbleWithKey(str);
        if (bubbleWithKey != null) {
            bubbleWithKey.updateDotVisibility();
        }
    }

    public void setExpandListener(BubbleController.BubbleExpandListener bubbleExpandListener) {
        this.mExpandListener = bubbleExpandListener;
    }

    public boolean isExpanded() {
        return this.mIsExpanded;
    }

    public boolean isExpansionAnimating() {
        return this.mIsExpansionAnimating;
    }

    /* access modifiers changed from: package-private */
    public BubbleView getExpandedBubbleView() {
        Bubble bubble = this.mExpandedBubble;
        if (bubble != null) {
            return bubble.getIconView();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Bubble getExpandedBubble() {
        return this.mExpandedBubble;
    }

    /* access modifiers changed from: package-private */
    public void addBubble(Bubble bubble) {
        if (this.mBubbleContainer.getChildCount() == 0) {
            this.mStackOnLeftOrWillBe = this.mStackAnimationController.isStackOnLeftSide();
        }
        bubble.inflate(this.mInflater, this);
        bubble.getIconView().setBubbleIconFactory(this.mBubbleIconFactory);
        bubble.getIconView().updateViews();
        bubble.getIconView().setDotPosition(!this.mStackOnLeftOrWillBe, false);
        this.mBubbleContainer.addView(bubble.getIconView(), 0, new FrameLayout.LayoutParams(-2, -2));
        ViewClippingUtil.setClippingDeactivated(bubble.getIconView(), true, this.mClippingParameters);
        animateInFlyoutForBubble(bubble);
        requestUpdate();
        logBubbleEvent(bubble, 1);
        lambda$new$5$BubbleStackView();
    }

    /* access modifiers changed from: package-private */
    public void removeBubble(Bubble bubble) {
        int indexOfChild = this.mBubbleContainer.indexOfChild(bubble.getIconView());
        if (indexOfChild >= 0) {
            this.mBubbleContainer.removeViewAt(indexOfChild);
            bubble.cleanupExpandedState();
            logBubbleEvent(bubble, 5);
        } else {
            Log.d("Bubbles", "was asked to remove Bubble, but didn't find the view! " + bubble);
        }
        lambda$new$5$BubbleStackView();
    }

    /* access modifiers changed from: package-private */
    public void updateBubble(Bubble bubble) {
        animateInFlyoutForBubble(bubble);
        requestUpdate();
        logBubbleEvent(bubble, 2);
    }

    public void updateBubbleOrder(List<Bubble> list) {
        for (int i = 0; i < list.size(); i++) {
            this.mBubbleContainer.reorderView(list.get(i).getIconView(), i);
        }
        updateBubbleZOrdersAndDotPosition(false);
    }

    public void setSelectedBubble(Bubble bubble) {
        Bubble bubble2 = this.mExpandedBubble;
        if (bubble2 == null || !bubble2.equals(bubble)) {
            Bubble bubble3 = this.mExpandedBubble;
            this.mExpandedBubble = bubble;
            if (this.mIsExpanded) {
                this.mExpandedViewContainer.setAlpha(0.0f);
                this.mSurfaceSynchronizer.syncSurfaceAndRun(new Runnable(bubble3, bubble) {
                    private final /* synthetic */ Bubble f$1;
                    private final /* synthetic */ Bubble f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        BubbleStackView.this.lambda$setSelectedBubble$8$BubbleStackView(this.f$1, this.f$2);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$setSelectedBubble$8$BubbleStackView(Bubble bubble, Bubble bubble2) {
        if (bubble != null) {
            bubble.setContentVisibility(false);
        }
        updateExpandedBubble();
        lambda$new$5$BubbleStackView();
        requestUpdate();
        logBubbleEvent(bubble, 4);
        logBubbleEvent(bubble2, 3);
        notifyExpansionChanged(bubble, false);
        notifyExpansionChanged(bubble2, true);
    }

    public void setExpanded(boolean z) {
        boolean z2 = this.mIsExpanded;
        if (z != z2) {
            if (z2) {
                animateCollapse();
                logBubbleEvent(this.mExpandedBubble, 4);
            } else {
                animateExpansion();
                logBubbleEvent(this.mExpandedBubble, 3);
                logBubbleEvent(this.mExpandedBubble, 15);
            }
            notifyExpansionChanged(this.mExpandedBubble, this.mIsExpanded);
        }
    }

    public View getTargetView(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (!this.mIsExpanded) {
            return (this.mFlyout.getVisibility() != 0 || !isIntersecting(this.mFlyout, rawX, rawY)) ? this : this.mFlyout;
        }
        if (isIntersecting(this.mBubbleContainer, rawX, rawY)) {
            for (int i = 0; i < this.mBubbleContainer.getChildCount(); i++) {
                BubbleView bubbleView = (BubbleView) this.mBubbleContainer.getChildAt(i);
                if (isIntersecting(bubbleView, rawX, rawY)) {
                    return bubbleView;
                }
            }
        }
        BubbleExpandedView bubbleExpandedView = (BubbleExpandedView) this.mExpandedViewContainer.getChildAt(0);
        if (bubbleExpandedView.intersectingTouchableContent((int) rawX, (int) rawY)) {
            return bubbleExpandedView;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public View getFlyoutView() {
        return this.mFlyout;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void collapseStack() {
        this.mBubbleData.setExpanded(false);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void collapseStack(Runnable runnable) {
        collapseStack();
        runnable.run();
    }

    private void beforeExpandedViewAnimation() {
        hideFlyoutImmediate();
        updateExpandedBubble();
        updateExpandedView();
        this.mIsExpansionAnimating = true;
    }

    private void afterExpandedViewAnimation() {
        updateExpandedView();
        this.mIsExpansionAnimating = false;
        requestUpdate();
    }

    private void animateCollapse() {
        this.mIsExpanded = false;
        Bubble bubble = this.mExpandedBubble;
        beforeExpandedViewAnimation();
        this.mBubbleContainer.cancelAllAnimations();
        this.mExpandedAnimationController.collapseBackToStack(this.mStackAnimationController.getStackPositionAlongNearestHorizontalEdge(), new Runnable(bubble) {
            private final /* synthetic */ Bubble f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                BubbleStackView.this.lambda$animateCollapse$9$BubbleStackView(this.f$1);
            }
        });
        this.mExpandedViewXAnim.animateToFinalPosition(getCollapsedX());
        this.mExpandedViewYAnim.animateToFinalPosition(getCollapsedY());
        this.mExpandedViewContainer.animate().setDuration(100).alpha(0.0f);
    }

    public /* synthetic */ void lambda$animateCollapse$9$BubbleStackView(Bubble bubble) {
        this.mBubbleContainer.setActiveController(this.mStackAnimationController);
        afterExpandedViewAnimation();
        bubble.setContentVisibility(false);
    }

    private void animateExpansion() {
        this.mIsExpanded = true;
        beforeExpandedViewAnimation();
        this.mBubbleContainer.setActiveController(this.mExpandedAnimationController);
        this.mExpandedAnimationController.expandFromStack(new Runnable() {
            public final void run() {
                BubbleStackView.this.lambda$animateExpansion$10$BubbleStackView();
            }
        });
        this.mExpandedViewContainer.setTranslationX(getCollapsedX());
        this.mExpandedViewContainer.setTranslationY(getCollapsedY());
        this.mExpandedViewContainer.setAlpha(0.0f);
        this.mExpandedViewXAnim.animateToFinalPosition(0.0f);
        this.mExpandedViewYAnim.animateToFinalPosition(getExpandedViewY());
        this.mExpandedViewContainer.animate().setDuration(100).alpha(1.0f);
    }

    public /* synthetic */ void lambda$animateExpansion$10$BubbleStackView() {
        lambda$new$5$BubbleStackView();
        afterExpandedViewAnimation();
    }

    private float getCollapsedX() {
        int i;
        if (this.mStackAnimationController.getStackPosition().x < ((float) (getWidth() / 2))) {
            i = -this.mExpandedAnimateXDistance;
        } else {
            i = this.mExpandedAnimateXDistance;
        }
        return (float) i;
    }

    private float getCollapsedY() {
        return Math.min(this.mStackAnimationController.getStackPosition().y, (float) this.mExpandedAnimateYDistance);
    }

    private void notifyExpansionChanged(Bubble bubble, boolean z) {
        BubbleController.BubbleExpandListener bubbleExpandListener = this.mExpandListener;
        if (bubbleExpandListener != null && bubble != null) {
            bubbleExpandListener.onBubbleExpandChanged(z, bubble.getKey());
        }
    }

    public void onImeVisibilityChanged(boolean z, int i) {
        this.mStackAnimationController.setImeHeight(z ? i + this.mImeOffset : 0);
        if (!this.mIsExpanded) {
            this.mStackAnimationController.animateForImeVisibility(z);
        }
    }

    public void onBubbleDragStart(View view) {
        this.mExpandedAnimationController.prepareForBubbleDrag(view);
    }

    public void onBubbleDragged(View view, float f, float f2) {
        if (this.mIsExpanded && !this.mIsExpansionAnimating) {
            this.mExpandedAnimationController.dragBubbleOut(view, f, f2);
            springInDismissTarget();
        }
    }

    public void onBubbleDragFinish(View view, float f, float f2, float f3, float f4) {
        if (this.mIsExpanded && !this.mIsExpansionAnimating) {
            this.mExpandedAnimationController.snapBubbleBack(view, f3, f4);
            hideDismissTarget();
        }
    }

    /* access modifiers changed from: package-private */
    public void onDragStart() {
        if (!this.mIsExpanded && !this.mIsExpansionAnimating) {
            this.mStackAnimationController.cancelStackPositionAnimations();
            this.mBubbleContainer.setActiveController(this.mStackAnimationController);
            hideFlyoutImmediate();
            this.mDraggingInDismissTarget = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void onDragged(float f, float f2) {
        if (!this.mIsExpanded && !this.mIsExpansionAnimating) {
            springInDismissTarget();
            this.mStackAnimationController.moveStackFromTouch(f, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public void onDragFinish(float f, float f2, float f3, float f4) {
        if (!this.mIsExpanded && !this.mIsExpansionAnimating) {
            float flingStackThenSpringToEdge = this.mStackAnimationController.flingStackThenSpringToEdge(f, f3, f4);
            logBubbleEvent((Bubble) null, 7);
            this.mStackOnLeftOrWillBe = flingStackThenSpringToEdge <= 0.0f;
            updateBubbleZOrdersAndDotPosition(true);
            hideDismissTarget();
        }
    }

    /* access modifiers changed from: package-private */
    public void onFlyoutDragStart() {
        this.mFlyout.removeCallbacks(this.mHideFlyout);
    }

    /* access modifiers changed from: package-private */
    public void onFlyoutDragged(float f) {
        if (this.mFlyout.getWidth() > 0) {
            boolean isStackOnLeftSide = this.mStackAnimationController.isStackOnLeftSide();
            this.mFlyoutDragDeltaX = f;
            if (isStackOnLeftSide) {
                f = -f;
            }
            float width = f / ((float) this.mFlyout.getWidth());
            float f2 = 0.0f;
            this.mFlyout.setCollapsePercent(Math.min(1.0f, Math.max(0.0f, width)));
            int i = (width > 0.0f ? 1 : (width == 0.0f ? 0 : -1));
            if (i < 0 || width > 1.0f) {
                int i2 = (width > 1.0f ? 1 : (width == 1.0f ? 0 : -1));
                boolean z = false;
                int i3 = 1;
                boolean z2 = i2 > 0;
                if ((isStackOnLeftSide && i2 > 0) || (!isStackOnLeftSide && i < 0)) {
                    z = true;
                }
                float f3 = (z2 ? width - 1.0f : width * -1.0f) * ((float) (z ? -1 : 1));
                float width2 = (float) this.mFlyout.getWidth();
                if (z2) {
                    i3 = 2;
                }
                f2 = f3 * (width2 / (8.0f / ((float) i3)));
            }
            BubbleFlyoutView bubbleFlyoutView = this.mFlyout;
            bubbleFlyoutView.setTranslationX(bubbleFlyoutView.getRestingTranslationX() + f2);
        }
    }

    /* access modifiers changed from: package-private */
    public void onFlyoutTapped() {
        this.mBubbleToExpandAfterFlyoutCollapse = this.mBubbleData.getSelectedBubble();
        this.mFlyout.removeCallbacks(this.mHideFlyout);
        this.mHideFlyout.run();
    }

    /* access modifiers changed from: package-private */
    public void onFlyoutDragFinished(float f, float f2) {
        boolean isStackOnLeftSide = this.mStackAnimationController.isStackOnLeftSide();
        boolean z = true;
        boolean z2 = !isStackOnLeftSide ? f2 > 2000.0f : f2 < -2000.0f;
        boolean z3 = !isStackOnLeftSide ? f > ((float) this.mFlyout.getWidth()) * 0.25f : f < ((float) (-this.mFlyout.getWidth())) * 0.25f;
        boolean z4 = !isStackOnLeftSide ? f2 < 0.0f : f2 > 0.0f;
        if (!z2 && (!z3 || z4)) {
            z = false;
        }
        this.mFlyout.removeCallbacks(this.mHideFlyout);
        animateFlyoutCollapsed(z, f2);
    }

    /* access modifiers changed from: package-private */
    public void onGestureStart() {
        this.mIsGestureInProgress = true;
    }

    /* access modifiers changed from: package-private */
    public void onGestureFinished() {
        this.mIsGestureInProgress = false;
        if (this.mIsExpanded) {
            this.mExpandedAnimationController.onGestureFinished();
        }
    }

    private void animateDesaturateAndDarken(View view, boolean z) {
        this.mDesaturateAndDarkenTargetView = view;
        if (z) {
            this.mDesaturateAndDarkenTargetView.setLayerType(2, this.mDesaturateAndDarkenPaint);
            this.mDesaturateAndDarkenAnimator.removeAllListeners();
            this.mDesaturateAndDarkenAnimator.start();
            return;
        }
        this.mDesaturateAndDarkenAnimator.removeAllListeners();
        this.mDesaturateAndDarkenAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                BubbleStackView.this.resetDesaturationAndDarken();
            }
        });
        this.mDesaturateAndDarkenAnimator.reverse();
    }

    /* access modifiers changed from: private */
    public void resetDesaturationAndDarken() {
        this.mDesaturateAndDarkenAnimator.removeAllListeners();
        this.mDesaturateAndDarkenAnimator.cancel();
        this.mDesaturateAndDarkenTargetView.setLayerType(0, (Paint) null);
    }

    /* access modifiers changed from: package-private */
    public void animateMagnetToDismissTarget(View view, boolean z, float f, float f2, float f3, float f4) {
        this.mDraggingInDismissTarget = z;
        int i = 0;
        if (z) {
            float dismissTargetCenterY = this.mDismissContainer.getDismissTargetCenterY() - (((float) this.mBubbleSize) / 2.0f);
            this.mAnimatingMagnet = true;
            $$Lambda$BubbleStackView$oLhNqxGbPa3FqJeraIwHlBcS7tk r6 = new Runnable() {
                public final void run() {
                    BubbleStackView.this.lambda$animateMagnetToDismissTarget$11$BubbleStackView();
                }
            };
            if (view == this) {
                this.mStackAnimationController.magnetToDismiss(f3, f4, dismissTargetCenterY, r6);
                animateDesaturateAndDarken(this.mBubbleContainer, true);
            } else {
                this.mExpandedAnimationController.magnetBubbleToDismiss(view, f3, f4, dismissTargetCenterY, r6);
                animateDesaturateAndDarken(view, true);
            }
        } else {
            this.mAnimatingMagnet = false;
            if (view == this) {
                this.mStackAnimationController.demagnetizeFromDismissToPoint(f, f2, f3, f4);
                animateDesaturateAndDarken(this.mBubbleContainer, false);
            } else {
                this.mExpandedAnimationController.demagnetizeBubbleTo(f, f2, f3, f4);
                animateDesaturateAndDarken(view, false);
            }
        }
        Vibrator vibrator = this.mVibrator;
        if (!z) {
            i = 2;
        }
        vibrator.vibrate(VibrationEffect.get(i));
    }

    public /* synthetic */ void lambda$animateMagnetToDismissTarget$11$BubbleStackView() {
        this.mAnimatingMagnet = false;
        Runnable runnable = this.mAfterMagnet;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: package-private */
    public void magnetToStackIfNeededThenAnimateDismissal(View view, float f, float f2, Runnable runnable) {
        $$Lambda$BubbleStackView$wNBb9TcVorXyGaagZMMDs0nXEJw r1 = new Runnable(view, runnable, this.mExpandedAnimationController.getDraggedOutBubble()) {
            private final /* synthetic */ View f$1;
            private final /* synthetic */ Runnable f$2;
            private final /* synthetic */ View f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                BubbleStackView.this.mo9669x3f995e1b(this.f$1, this.f$2, this.f$3);
            }
        };
        if (this.mAnimatingMagnet) {
            this.mAfterMagnet = r1;
        } else if (this.mDraggingInDismissTarget) {
            r1.run();
        } else {
            animateMagnetToDismissTarget(view, true, -1.0f, -1.0f, f, f2);
            this.mAfterMagnet = r1;
        }
    }

    /* renamed from: lambda$magnetToStackIfNeededThenAnimateDismissal$14$BubbleStackView */
    public /* synthetic */ void mo9669x3f995e1b(View view, Runnable runnable, View view2) {
        this.mAfterMagnet = null;
        this.mVibrator.vibrate(VibrationEffect.get(0));
        this.mDismissContainer.springOut();
        if (view == this) {
            this.mStackAnimationController.implodeStack(new Runnable(runnable) {
                private final /* synthetic */ Runnable f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BubbleStackView.this.mo9667x9e45a219(this.f$1);
                }
            });
        } else {
            this.mExpandedAnimationController.dismissDraggedOutBubble(view2, new Runnable(runnable) {
                private final /* synthetic */ Runnable f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BubbleStackView.this.mo9668xeeef801a(this.f$1);
                }
            });
        }
    }

    /* renamed from: lambda$magnetToStackIfNeededThenAnimateDismissal$12$BubbleStackView */
    public /* synthetic */ void mo9667x9e45a219(Runnable runnable) {
        this.mAnimatingMagnet = false;
        this.mShowingDismiss = false;
        this.mDraggingInDismissTarget = false;
        runnable.run();
        resetDesaturationAndDarken();
    }

    /* renamed from: lambda$magnetToStackIfNeededThenAnimateDismissal$13$BubbleStackView */
    public /* synthetic */ void mo9668xeeef801a(Runnable runnable) {
        this.mAnimatingMagnet = false;
        this.mShowingDismiss = false;
        this.mDraggingInDismissTarget = false;
        resetDesaturationAndDarken();
        runnable.run();
    }

    private void springInDismissTarget() {
        if (!this.mShowingDismiss) {
            this.mShowingDismiss = true;
            this.mDismissContainer.springIn();
            this.mDismissContainer.bringToFront();
            this.mDismissContainer.setZ(32766.0f);
        }
    }

    private void hideDismissTarget() {
        if (this.mShowingDismiss) {
            this.mDismissContainer.springOut();
            this.mShowingDismiss = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInDismissTarget(MotionEvent motionEvent) {
        return isIntersecting(this.mDismissContainer.getDismissTarget(), motionEvent.getRawX(), motionEvent.getRawY());
    }

    private void animateFlyoutCollapsed(boolean z, float f) {
        float f2;
        boolean isStackOnLeftSide = this.mStackAnimationController.isStackOnLeftSide();
        this.mFlyoutTransitionSpring.getSpring().setStiffness(this.mBubbleToExpandAfterFlyoutCollapse != null ? 1500.0f : 200.0f);
        SpringAnimation springAnimation = this.mFlyoutTransitionSpring;
        springAnimation.setStartValue(this.mFlyoutDragDeltaX);
        SpringAnimation springAnimation2 = springAnimation;
        springAnimation2.setStartVelocity(f);
        SpringAnimation springAnimation3 = springAnimation2;
        if (z) {
            int width = this.mFlyout.getWidth();
            if (isStackOnLeftSide) {
                width = -width;
            }
            f2 = (float) width;
        } else {
            f2 = 0.0f;
        }
        springAnimation3.animateToFinalPosition(f2);
    }

    /* access modifiers changed from: package-private */
    public void updateDots() {
        int childCount = this.mBubbleContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((BubbleView) this.mBubbleContainer.getChildAt(i)).updateDotVisibility(true);
        }
    }

    /* access modifiers changed from: package-private */
    public float getExpandedViewY() {
        return (float) (getStatusBarHeight() + this.mBubbleSize + this.mBubblePaddingTop + this.mPointerHeight);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void animateInFlyoutForBubble(Bubble bubble) {
        CharSequence updateMessage = bubble.getUpdateMessage(getContext());
        if (!bubble.showFlyoutForBubble()) {
            bubble.setSuppressFlyout(false);
        } else if (updateMessage != null && !isExpanded() && !this.mIsExpansionAnimating && !this.mIsGestureInProgress && this.mBubbleToExpandAfterFlyoutCollapse == null && bubble.getIconView() != null) {
            this.mFlyoutDragDeltaX = 0.0f;
            clearFlyoutOnHide();
            this.mFlyoutOnHide = new Runnable(bubble) {
                private final /* synthetic */ Bubble f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BubbleStackView.this.lambda$animateInFlyoutForBubble$15$BubbleStackView(this.f$1);
                }
            };
            this.mFlyout.setVisibility(4);
            bubble.getIconView().setSuppressDot(true, false);
            post(new Runnable(updateMessage, bubble) {
                private final /* synthetic */ CharSequence f$1;
                private final /* synthetic */ Bubble f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    BubbleStackView.this.lambda$animateInFlyoutForBubble$18$BubbleStackView(this.f$1, this.f$2);
                }
            });
            this.mFlyout.removeCallbacks(this.mHideFlyout);
            this.mFlyout.postDelayed(this.mHideFlyout, 5000);
            logBubbleEvent(bubble, 16);
        }
    }

    public /* synthetic */ void lambda$animateInFlyoutForBubble$15$BubbleStackView(Bubble bubble) {
        resetDot(bubble);
        Bubble bubble2 = this.mBubbleToExpandAfterFlyoutCollapse;
        if (bubble2 != null) {
            this.mBubbleData.setSelectedBubble(bubble2);
            this.mBubbleData.setExpanded(true);
            this.mBubbleToExpandAfterFlyoutCollapse = null;
        }
    }

    public /* synthetic */ void lambda$animateInFlyoutForBubble$18$BubbleStackView(CharSequence charSequence, Bubble bubble) {
        if (!isExpanded()) {
            CharSequence charSequence2 = charSequence;
            this.mFlyout.setupFlyoutStartingAsDot(charSequence2, this.mStackAnimationController.getStackPosition(), (float) getWidth(), this.mStackAnimationController.isStackOnLeftSide(), bubble.getIconView().getBadgeColor(), new Runnable() {
                public final void run() {
                    BubbleStackView.this.lambda$animateInFlyoutForBubble$17$BubbleStackView();
                }
            }, this.mFlyoutOnHide, bubble.getIconView().getDotCenter());
            this.mFlyout.bringToFront();
        }
    }

    public /* synthetic */ void lambda$animateInFlyoutForBubble$17$BubbleStackView() {
        this.mAnimateInFlyout = new Runnable() {
            public final void run() {
                BubbleStackView.this.lambda$animateInFlyoutForBubble$16$BubbleStackView();
            }
        };
        this.mFlyout.postDelayed(this.mAnimateInFlyout, 200);
    }

    public /* synthetic */ void lambda$animateInFlyoutForBubble$16$BubbleStackView() {
        int i;
        this.mFlyout.setVisibility(0);
        if (this.mStackAnimationController.isStackOnLeftSide()) {
            i = -this.mFlyout.getWidth();
        } else {
            i = this.mFlyout.getWidth();
        }
        this.mFlyoutDragDeltaX = (float) i;
        animateFlyoutCollapsed(false, 0.0f);
        this.mFlyout.postDelayed(this.mHideFlyout, 5000);
    }

    private void resetDot(Bubble bubble) {
        boolean z = !bubble.showBubbleDot();
        if (z) {
            bubble.getIconView().setSuppressDot(false, false);
        }
        bubble.getIconView().setSuppressDot(z, z);
    }

    private void hideFlyoutImmediate() {
        clearFlyoutOnHide();
        this.mFlyout.removeCallbacks(this.mAnimateInFlyout);
        this.mFlyout.removeCallbacks(this.mHideFlyout);
        this.mFlyout.hideFlyout();
    }

    private void clearFlyoutOnHide() {
        this.mFlyout.removeCallbacks(this.mAnimateInFlyout);
        Runnable runnable = this.mFlyoutOnHide;
        if (runnable != null) {
            runnable.run();
            this.mFlyoutOnHide = null;
        }
    }

    public void getBoundsOnScreen(Rect rect) {
        if (!this.mIsExpanded) {
            if (this.mBubbleContainer.getChildCount() > 0) {
                this.mBubbleContainer.getChildAt(0).getBoundsOnScreen(rect);
            }
            int i = rect.top;
            int i2 = this.mBubbleTouchPadding;
            rect.top = i - i2;
            rect.left -= i2;
            rect.right += i2;
            rect.bottom += i2;
        } else {
            this.mBubbleContainer.getBoundsOnScreen(rect);
        }
        if (this.mFlyout.getVisibility() == 0) {
            Rect rect2 = new Rect();
            this.mFlyout.getBoundsOnScreen(rect2);
            rect.union(rect2);
        }
    }

    private int getStatusBarHeight() {
        int i = 0;
        if (getRootWindowInsets() == null) {
            return 0;
        }
        WindowInsets rootWindowInsets = getRootWindowInsets();
        int i2 = this.mStatusBarHeight;
        if (rootWindowInsets.getDisplayCutout() != null) {
            i = rootWindowInsets.getDisplayCutout().getSafeInsetTop();
        }
        return Math.max(i2, i);
    }

    private boolean isIntersecting(View view, float f, float f2) {
        this.mTempLoc = view.getLocationOnScreen();
        RectF rectF = this.mTempRect;
        int[] iArr = this.mTempLoc;
        rectF.set((float) iArr[0], (float) iArr[1], (float) (iArr[0] + view.getWidth()), (float) (this.mTempLoc[1] + view.getHeight()));
        return this.mTempRect.contains(f, f2);
    }

    private void requestUpdate() {
        if (!this.mViewUpdatedRequested && !this.mIsExpansionAnimating) {
            this.mViewUpdatedRequested = true;
            getViewTreeObserver().addOnPreDrawListener(this.mViewUpdater);
            invalidate();
        }
    }

    private void updateExpandedBubble() {
        this.mExpandedViewContainer.removeAllViews();
        Bubble bubble = this.mExpandedBubble;
        if (bubble != null && this.mIsExpanded) {
            this.mExpandedViewContainer.addView(bubble.getExpandedView());
            this.mExpandedBubble.getExpandedView().populateExpandedView();
            this.mExpandedViewContainer.setVisibility(this.mIsExpanded ? 0 : 8);
            this.mExpandedViewContainer.setAlpha(1.0f);
        }
    }

    /* access modifiers changed from: private */
    public void updateExpandedView() {
        this.mExpandedViewContainer.setVisibility(this.mIsExpanded ? 0 : 8);
        if (this.mIsExpanded) {
            this.mExpandedBubble.getExpandedView().updateView();
            float expandedViewY = getExpandedViewY();
            if (!this.mExpandedViewYAnim.isRunning()) {
                this.mExpandedViewContainer.setTranslationY(expandedViewY);
                this.mExpandedBubble.getExpandedView().updateView();
            } else {
                this.mExpandedViewYAnim.animateToFinalPosition(expandedViewY);
            }
        }
        this.mStackOnLeftOrWillBe = this.mStackAnimationController.isStackOnLeftSide();
        updateBubbleZOrdersAndDotPosition(false);
    }

    private void updateBubbleZOrdersAndDotPosition(boolean z) {
        int childCount = this.mBubbleContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            BubbleView bubbleView = (BubbleView) this.mBubbleContainer.getChildAt(i);
            bubbleView.updateDotVisibility(true);
            bubbleView.setZ((float) ((getResources().getDimensionPixelSize(C1775R$dimen.bubble_elevation) * 5) - i));
            boolean dotPositionOnLeft = bubbleView.getDotPositionOnLeft();
            boolean z2 = this.mStackOnLeftOrWillBe;
            if (dotPositionOnLeft == z2) {
                bubbleView.setDotPosition(!z2, z);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updatePointerPosition */
    public void lambda$new$5$BubbleStackView() {
        Bubble expandedBubble = getExpandedBubble();
        if (expandedBubble != null) {
            expandedBubble.getExpandedView().setPointerPosition((this.mExpandedAnimationController.getBubbleLeft(getBubbleIndex(expandedBubble)) + (((float) this.mBubbleSize) / 2.0f)) - ((float) this.mExpandedViewContainer.getPaddingLeft()));
        }
    }

    public int getBubbleCount() {
        return this.mBubbleContainer.getChildCount();
    }

    /* access modifiers changed from: package-private */
    public int getBubbleIndex(Bubble bubble) {
        if (bubble == null) {
            return 0;
        }
        return this.mBubbleContainer.indexOfChild(bubble.getIconView());
    }

    public float getNormalizedXPosition() {
        BigDecimal bigDecimal = new BigDecimal((double) (getStackPosition().x / ((float) this.mDisplaySize.x)));
        RoundingMode roundingMode = RoundingMode.CEILING;
        return bigDecimal.setScale(4, RoundingMode.HALF_UP).floatValue();
    }

    public float getNormalizedYPosition() {
        BigDecimal bigDecimal = new BigDecimal((double) (getStackPosition().y / ((float) this.mDisplaySize.y)));
        RoundingMode roundingMode = RoundingMode.CEILING;
        return bigDecimal.setScale(4, RoundingMode.HALF_UP).floatValue();
    }

    public PointF getStackPosition() {
        return this.mStackAnimationController.getStackPosition();
    }

    private void logBubbleEvent(Bubble bubble, int i) {
        if (bubble == null || bubble.getEntry() == null || bubble.getEntry().notification == null) {
            StatsLog.write(149, (String) null, (String) null, 0, 0, getBubbleCount(), i, getNormalizedXPosition(), getNormalizedYPosition(), false, false, false);
            return;
        }
        StatusBarNotification statusBarNotification = bubble.getEntry().notification;
        StatsLog.write(149, statusBarNotification.getPackageName(), statusBarNotification.getNotification().getChannelId(), statusBarNotification.getId(), getBubbleIndex(bubble), getBubbleCount(), i, getNormalizedXPosition(), getNormalizedYPosition(), bubble.showInShadeWhenBubble(), bubble.isOngoing(), false);
    }

    /* access modifiers changed from: package-private */
    public boolean performBackPressIfNeeded() {
        if (!isExpanded()) {
            return false;
        }
        return this.mExpandedBubble.getExpandedView().performBackPressIfNeeded();
    }
}
