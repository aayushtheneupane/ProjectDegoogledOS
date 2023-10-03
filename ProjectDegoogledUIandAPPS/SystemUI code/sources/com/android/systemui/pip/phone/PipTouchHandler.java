package com.android.systemui.pip.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.IActivityManager;
import android.app.IActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.util.Size;
import android.view.IPinnedStackController;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.os.logging.MetricsLoggerWrapper;
import com.android.internal.policy.PipSnapAlgorithm;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.pip.phone.PipAccessibilityInteractionConnection;
import com.android.systemui.pip.phone.PipMenuActivityController;
import com.android.systemui.shared.system.InputConsumerController;
import com.android.systemui.statusbar.FlingAnimationUtils;
import java.io.PrintWriter;

public class PipTouchHandler {
    private final AccessibilityManager mAccessibilityManager;
    /* access modifiers changed from: private */
    public final IActivityManager mActivityManager;
    private final IActivityTaskManager mActivityTaskManager;
    /* access modifiers changed from: private */
    public final Context mContext;
    private PipTouchGesture mDefaultMovementGesture = new PipTouchGesture() {
        private final PointF mDelta = new PointF();
        private final Point mStartPosition = new Point();
        private boolean mStartedOnLeft;

        public void onDown(PipTouchState pipTouchState) {
            if (pipTouchState.isUserInteracting()) {
                Rect bounds = PipTouchHandler.this.mMotionHelper.getBounds();
                this.mDelta.set(0.0f, 0.0f);
                this.mStartPosition.set(bounds.left, bounds.top);
                boolean z = false;
                this.mStartedOnLeft = bounds.left < PipTouchHandler.this.mMovementBounds.centerX();
                boolean unused = PipTouchHandler.this.mMovementWithinMinimize = true;
                PipTouchHandler pipTouchHandler = PipTouchHandler.this;
                if (pipTouchState.getDownTouchPosition().y >= ((float) PipTouchHandler.this.mMovementBounds.bottom)) {
                    z = true;
                }
                boolean unused2 = pipTouchHandler.mMovementWithinDismiss = z;
                if (PipTouchHandler.this.mMenuState != 0 && !PipTouchHandler.this.mIsMinimized) {
                    PipTouchHandler.this.mMenuController.pokeMenu();
                }
                if (PipTouchHandler.this.mEnableDimissDragToEdge) {
                    PipTouchHandler.this.mDismissViewController.createDismissTarget();
                    PipTouchHandler.this.mHandler.postDelayed(PipTouchHandler.this.mShowDismissAffordance, 225);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean onMove(PipTouchState pipTouchState) {
            float f;
            boolean z = false;
            if (!pipTouchState.isUserInteracting()) {
                return false;
            }
            if (pipTouchState.startedDragging()) {
                float unused = PipTouchHandler.this.mSavedSnapFraction = -1.0f;
                if (PipTouchHandler.this.mEnableDimissDragToEdge) {
                    PipTouchHandler.this.mHandler.removeCallbacks(PipTouchHandler.this.mShowDismissAffordance);
                    PipTouchHandler.this.mDismissViewController.showDismissTarget();
                }
            }
            if (!pipTouchState.isDragging()) {
                return false;
            }
            PointF lastTouchDelta = pipTouchState.getLastTouchDelta();
            Point point = this.mStartPosition;
            PointF pointF = this.mDelta;
            float f2 = ((float) point.x) + pointF.x;
            float f3 = ((float) point.y) + pointF.y;
            float f4 = lastTouchDelta.y + f3;
            pipTouchState.allowDraggingOffscreen();
            float max = Math.max((float) PipTouchHandler.this.mMovementBounds.left, Math.min((float) PipTouchHandler.this.mMovementBounds.right, lastTouchDelta.x + f2));
            if (PipTouchHandler.this.mEnableDimissDragToEdge) {
                f = Math.max((float) PipTouchHandler.this.mMovementBounds.top, f4);
            } else {
                f = Math.max((float) PipTouchHandler.this.mMovementBounds.top, Math.min((float) PipTouchHandler.this.mMovementBounds.bottom, f4));
            }
            PointF pointF2 = this.mDelta;
            pointF2.x += max - f2;
            pointF2.y += f - f3;
            PipTouchHandler.this.mTmpBounds.set(PipTouchHandler.this.mMotionHelper.getBounds());
            PipTouchHandler.this.mTmpBounds.offsetTo((int) max, (int) f);
            PipTouchHandler.this.mMotionHelper.movePip(PipTouchHandler.this.mTmpBounds);
            if (PipTouchHandler.this.mEnableDimissDragToEdge) {
                PipTouchHandler.this.updateDismissFraction();
            }
            PointF lastTouchPosition = pipTouchState.getLastTouchPosition();
            if (PipTouchHandler.this.mMovementWithinMinimize) {
                PipTouchHandler pipTouchHandler = PipTouchHandler.this;
                boolean unused2 = pipTouchHandler.mMovementWithinMinimize = !this.mStartedOnLeft ? lastTouchPosition.x >= ((float) pipTouchHandler.mMovementBounds.right) : lastTouchPosition.x <= ((float) (pipTouchHandler.mMovementBounds.left + PipTouchHandler.this.mTmpBounds.width()));
            }
            if (PipTouchHandler.this.mMovementWithinDismiss) {
                PipTouchHandler pipTouchHandler2 = PipTouchHandler.this;
                if (lastTouchPosition.y >= ((float) pipTouchHandler2.mMovementBounds.bottom)) {
                    z = true;
                }
                boolean unused3 = pipTouchHandler2.mMovementWithinDismiss = z;
            }
            return true;
        }

        public boolean onUp(PipTouchState pipTouchState) {
            C08431 r12;
            if (PipTouchHandler.this.mEnableDimissDragToEdge) {
                PipTouchHandler.this.cleanUpDismissTarget();
            }
            if (!pipTouchState.isUserInteracting()) {
                return false;
            }
            PointF velocity = pipTouchState.getVelocity();
            boolean z = Math.abs(velocity.x) > Math.abs(velocity.y);
            float length = PointF.length(velocity.x, velocity.y);
            boolean z2 = length > PipTouchHandler.this.mFlingAnimationUtils.getMinVelocityPxPerSecond();
            boolean z3 = z2 && velocity.y > 0.0f && !z && PipTouchHandler.this.mMovementWithinDismiss;
            if (!PipTouchHandler.this.mEnableDimissDragToEdge || (!PipTouchHandler.this.mMotionHelper.shouldDismissPip() && !z3)) {
                if (pipTouchState.isDragging()) {
                    if (z2 && z && PipTouchHandler.this.mMovementWithinMinimize) {
                        if (this.mStartedOnLeft) {
                            int i = (velocity.x > 0.0f ? 1 : (velocity.x == 0.0f ? 0 : -1));
                        } else {
                            int i2 = (velocity.x > 0.0f ? 1 : (velocity.x == 0.0f ? 0 : -1));
                        }
                    }
                    if (PipTouchHandler.this.mIsMinimized) {
                        PipTouchHandler.this.setMinimizedStateInternal(false);
                    }
                    if (PipTouchHandler.this.mMenuState != 0) {
                        PipTouchHandler.this.mMenuController.showMenu(PipTouchHandler.this.mMenuState, PipTouchHandler.this.mMotionHelper.getBounds(), PipTouchHandler.this.mMovementBounds, true, PipTouchHandler.this.willResizeMenu());
                        r12 = null;
                    } else {
                        r12 = new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animator) {
                                PipTouchHandler.this.mMenuController.hideMenu();
                            }
                        };
                    }
                    if (z2) {
                        PipTouchHandler.this.mMotionHelper.flingToSnapTarget(length, velocity.x, velocity.y, PipTouchHandler.this.mMovementBounds, PipTouchHandler.this.mUpdateScrimListener, r12, this.mStartPosition);
                    } else {
                        PipTouchHandler.this.mMotionHelper.animateToClosestSnapTarget(PipTouchHandler.this.mMovementBounds, PipTouchHandler.this.mUpdateScrimListener, r12);
                    }
                } else if (PipTouchHandler.this.mIsMinimized) {
                    PipTouchHandler.this.mMotionHelper.animateToClosestSnapTarget(PipTouchHandler.this.mMovementBounds, (ValueAnimator.AnimatorUpdateListener) null, (Animator.AnimatorListener) null);
                    PipTouchHandler.this.setMinimizedStateInternal(false);
                } else if (PipTouchHandler.this.mTouchState.isDoubleTap()) {
                    PipTouchHandler.this.mMotionHelper.expandPip();
                } else if (PipTouchHandler.this.mMenuState != 2) {
                    if (!PipTouchHandler.this.mTouchState.isWaitingForDoubleTap()) {
                        PipTouchHandler.this.mMenuController.showMenu(2, PipTouchHandler.this.mMotionHelper.getBounds(), PipTouchHandler.this.mMovementBounds, true, PipTouchHandler.this.willResizeMenu());
                    } else {
                        PipTouchHandler.this.mTouchState.scheduleDoubleTapTimeoutCallback();
                    }
                }
                return true;
            }
            MetricsLoggerWrapper.logPictureInPictureDismissByDrag(PipTouchHandler.this.mContext, PipUtils.getTopPinnedActivity(PipTouchHandler.this.mContext, PipTouchHandler.this.mActivityManager));
            PipTouchHandler.this.mMotionHelper.animateDismiss(PipTouchHandler.this.mMotionHelper.getBounds(), velocity.x, velocity.y, PipTouchHandler.this.mUpdateScrimListener);
            return true;
        }
    };
    private int mDeferResizeToNormalBoundsUntilRotation = -1;
    /* access modifiers changed from: private */
    public final PipDismissViewController mDismissViewController;
    private int mDisplayRotation;
    /* access modifiers changed from: private */
    public final boolean mEnableDimissDragToEdge;
    private Rect mExpandedBounds = new Rect();
    private Rect mExpandedMovementBounds = new Rect();
    private int mExpandedShortestEdgeSize;
    /* access modifiers changed from: private */
    public final FlingAnimationUtils mFlingAnimationUtils;
    private final PipTouchGesture[] mGestures;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private int mImeHeight;
    private int mImeOffset;
    private Rect mInsetBounds = new Rect();
    private boolean mIsImeShowing;
    /* access modifiers changed from: private */
    public boolean mIsMinimized;
    private boolean mIsShelfShowing;
    /* access modifiers changed from: private */
    public final PipMenuActivityController mMenuController;
    private final PipMenuListener mMenuListener = new PipMenuListener();
    /* access modifiers changed from: private */
    public int mMenuState = 0;
    /* access modifiers changed from: private */
    public final PipMotionHelper mMotionHelper;
    /* access modifiers changed from: private */
    public Rect mMovementBounds = new Rect();
    private int mMovementBoundsExtraOffsets;
    /* access modifiers changed from: private */
    public boolean mMovementWithinDismiss;
    /* access modifiers changed from: private */
    public boolean mMovementWithinMinimize;
    private Rect mNormalBounds = new Rect();
    private Rect mNormalMovementBounds = new Rect();
    private IPinnedStackController mPinnedStackController;
    /* access modifiers changed from: private */
    public float mSavedSnapFraction = -1.0f;
    private boolean mSendingHoverAccessibilityEvents;
    private int mShelfHeight;
    /* access modifiers changed from: private */
    public Runnable mShowDismissAffordance = new Runnable() {
        public void run() {
            if (PipTouchHandler.this.mEnableDimissDragToEdge) {
                PipTouchHandler.this.mDismissViewController.showDismissTarget();
            }
        }
    };
    private boolean mShowPipMenuOnAnimationEnd = false;
    private final PipSnapAlgorithm mSnapAlgorithm;
    /* access modifiers changed from: private */
    public final Rect mTmpBounds = new Rect();
    /* access modifiers changed from: private */
    public final PipTouchState mTouchState;
    /* access modifiers changed from: private */
    public ValueAnimator.AnimatorUpdateListener mUpdateScrimListener = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            PipTouchHandler.this.updateDismissFraction();
        }
    };
    private final ViewConfiguration mViewConfig;

    /* access modifiers changed from: private */
    public void setMinimizedStateInternal(boolean z) {
    }

    /* access modifiers changed from: package-private */
    public void setMinimizedState(boolean z, boolean z2) {
    }

    private class PipMenuListener implements PipMenuActivityController.Listener {
        private PipMenuListener() {
        }

        public void onPipMenuStateChanged(int i, boolean z) {
            PipTouchHandler.this.setMenuState(i, z);
        }

        public void onPipExpand() {
            if (!PipTouchHandler.this.mIsMinimized) {
                PipTouchHandler.this.mMotionHelper.expandPip();
            }
        }

        public void onPipMinimize() {
            PipTouchHandler.this.setMinimizedStateInternal(true);
            PipTouchHandler.this.mMotionHelper.animateToClosestMinimizedState(PipTouchHandler.this.mMovementBounds, (ValueAnimator.AnimatorUpdateListener) null);
        }

        public void onPipDismiss() {
            MetricsLoggerWrapper.logPictureInPictureDismissByTap(PipTouchHandler.this.mContext, PipUtils.getTopPinnedActivity(PipTouchHandler.this.mContext, PipTouchHandler.this.mActivityManager));
            PipTouchHandler.this.mMotionHelper.dismissPip();
        }

        public void onPipShowMenu() {
            PipTouchHandler.this.mMenuController.showMenu(2, PipTouchHandler.this.mMotionHelper.getBounds(), PipTouchHandler.this.mMovementBounds, true, PipTouchHandler.this.willResizeMenu());
        }
    }

    public PipTouchHandler(Context context, IActivityManager iActivityManager, IActivityTaskManager iActivityTaskManager, PipMenuActivityController pipMenuActivityController, InputConsumerController inputConsumerController) {
        this.mContext = context;
        this.mActivityManager = iActivityManager;
        this.mActivityTaskManager = iActivityTaskManager;
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.mViewConfig = ViewConfiguration.get(context);
        this.mMenuController = pipMenuActivityController;
        this.mMenuController.addListener(this.mMenuListener);
        this.mDismissViewController = new PipDismissViewController(context);
        this.mSnapAlgorithm = new PipSnapAlgorithm(this.mContext);
        this.mFlingAnimationUtils = new FlingAnimationUtils(context, 2.5f);
        this.mGestures = new PipTouchGesture[]{this.mDefaultMovementGesture};
        this.mMotionHelper = new PipMotionHelper(this.mContext, this.mActivityManager, this.mActivityTaskManager, this.mMenuController, this.mSnapAlgorithm, this.mFlingAnimationUtils);
        this.mTouchState = new PipTouchState(this.mViewConfig, this.mHandler, new Runnable() {
            public final void run() {
                PipTouchHandler.this.lambda$new$0$PipTouchHandler();
            }
        });
        Resources resources = context.getResources();
        this.mExpandedShortestEdgeSize = resources.getDimensionPixelSize(C1775R$dimen.pip_expanded_shortest_edge_size);
        this.mImeOffset = resources.getDimensionPixelSize(C1775R$dimen.pip_ime_offset);
        this.mEnableDimissDragToEdge = resources.getBoolean(C1773R$bool.config_pipEnableDismissDragToEdge);
        inputConsumerController.setInputListener(new InputConsumerController.InputListener() {
            public final boolean onInputEvent(InputEvent inputEvent) {
                return PipTouchHandler.this.handleTouchEvent(inputEvent);
            }
        });
        inputConsumerController.setRegistrationListener(new InputConsumerController.RegistrationListener() {
            public final void onRegistrationChanged(boolean z) {
                PipTouchHandler.this.onRegistrationChanged(z);
            }
        });
        onRegistrationChanged(inputConsumerController.isRegistered());
    }

    public /* synthetic */ void lambda$new$0$PipTouchHandler() {
        this.mMenuController.showMenu(2, this.mMotionHelper.getBounds(), this.mMovementBounds, true, willResizeMenu());
    }

    public void setTouchEnabled(boolean z) {
        this.mTouchState.setAllowTouches(z);
    }

    public void showPictureInPictureMenu() {
        if (!this.mTouchState.isUserInteracting()) {
            this.mMenuController.showMenu(2, this.mMotionHelper.getBounds(), this.mMovementBounds, false, willResizeMenu());
        }
    }

    public void onActivityPinned() {
        cleanUp();
        this.mShowPipMenuOnAnimationEnd = true;
    }

    public void onActivityUnpinned(ComponentName componentName) {
        if (componentName == null) {
            cleanUp();
        }
    }

    public void onPinnedStackAnimationEnded() {
        this.mMotionHelper.synchronizePinnedStackBounds();
        if (this.mShowPipMenuOnAnimationEnd) {
            this.mMenuController.showMenu(1, this.mMotionHelper.getBounds(), this.mMovementBounds, true, false);
            this.mShowPipMenuOnAnimationEnd = false;
        }
    }

    public void onConfigurationChanged() {
        this.mMotionHelper.onConfigurationChanged();
        this.mMotionHelper.synchronizePinnedStackBounds();
    }

    public void onImeVisibilityChanged(boolean z, int i) {
        this.mIsImeShowing = z;
        this.mImeHeight = i;
    }

    public void onShelfVisibilityChanged(boolean z, int i) {
        this.mIsShelfShowing = z;
        this.mShelfHeight = i;
    }

    public void onMovementBoundsChanged(Rect rect, Rect rect2, Rect rect3, boolean z, boolean z2, int i) {
        Rect rect4;
        int i2 = 0;
        int i3 = this.mIsImeShowing ? this.mImeHeight : 0;
        this.mNormalBounds = rect2;
        Rect rect5 = new Rect();
        this.mSnapAlgorithm.getMovementBounds(this.mNormalBounds, rect, rect5, i3);
        float width = ((float) rect2.width()) / ((float) rect2.height());
        Point point = new Point();
        this.mContext.getDisplay().getRealSize(point);
        Size sizeForAspectRatio = this.mSnapAlgorithm.getSizeForAspectRatio(width, (float) this.mExpandedShortestEdgeSize, point.x, point.y);
        this.mExpandedBounds.set(0, 0, sizeForAspectRatio.getWidth(), sizeForAspectRatio.getHeight());
        Rect rect6 = new Rect();
        this.mSnapAlgorithm.getMovementBounds(this.mExpandedBounds, rect, rect6, i3);
        int i4 = this.mIsImeShowing ? this.mImeOffset : 0;
        if (!this.mIsImeShowing && this.mIsShelfShowing) {
            i2 = this.mShelfHeight;
        }
        int max = Math.max(i4, i2);
        if ((z || z2) && !this.mTouchState.isUserInteracting()) {
            float f = this.mContext.getResources().getDisplayMetrics().density * 1.0f;
            if (this.mMenuState == 2) {
                rect4 = new Rect(rect6);
            } else {
                rect4 = new Rect(rect5);
            }
            int i5 = this.mMovementBounds.bottom - this.mMovementBoundsExtraOffsets;
            int i6 = rect4.bottom;
            if (i6 >= rect4.top) {
                i6 -= max;
            }
            int i7 = rect3.top;
            if (((float) Math.min(i5, i6)) - f <= ((float) i7) && ((float) i7) <= ((float) Math.max(i5, i6)) + f) {
                this.mMotionHelper.animateToOffset(rect3, i6 - rect3.top);
            }
        }
        this.mNormalMovementBounds = rect5;
        this.mExpandedMovementBounds = rect6;
        this.mDisplayRotation = i;
        this.mInsetBounds.set(rect);
        updateMovementBounds(this.mMenuState);
        this.mMovementBoundsExtraOffsets = max;
        if (this.mDeferResizeToNormalBoundsUntilRotation == i) {
            this.mMotionHelper.animateToUnexpandedState(rect2, this.mSavedSnapFraction, this.mNormalMovementBounds, this.mMovementBounds, this.mIsMinimized, true);
            this.mSavedSnapFraction = -1.0f;
            this.mDeferResizeToNormalBoundsUntilRotation = -1;
        }
    }

    /* access modifiers changed from: private */
    public void onRegistrationChanged(boolean z) {
        this.mAccessibilityManager.setPictureInPictureActionReplacingConnection(z ? new PipAccessibilityInteractionConnection(this.mMotionHelper, new PipAccessibilityInteractionConnection.AccessibilityCallbacks() {
            public final void onAccessibilityShowMenu() {
                PipTouchHandler.this.onAccessibilityShowMenu();
            }
        }, this.mHandler) : null);
        if (!z && this.mTouchState.isUserInteracting()) {
            cleanUpDismissTarget();
        }
    }

    /* access modifiers changed from: private */
    public void onAccessibilityShowMenu() {
        this.mMenuController.showMenu(2, this.mMotionHelper.getBounds(), this.mMovementBounds, true, willResizeMenu());
    }

    /* access modifiers changed from: private */
    public boolean handleTouchEvent(InputEvent inputEvent) {
        if (!(inputEvent instanceof MotionEvent) || this.mPinnedStackController == null) {
            return true;
        }
        MotionEvent motionEvent = (MotionEvent) inputEvent;
        this.mTouchState.onTouchEvent(motionEvent);
        int i = 0;
        boolean z = this.mMenuState != 0;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                updateMovementBounds(this.mMenuState);
                PipTouchGesture[] pipTouchGestureArr = this.mGestures;
                int length = pipTouchGestureArr.length;
                int i2 = 0;
                while (i2 < length && !pipTouchGestureArr[i2].onUp(this.mTouchState)) {
                    i2++;
                }
            } else if (action == 2) {
                PipTouchGesture[] pipTouchGestureArr2 = this.mGestures;
                int length2 = pipTouchGestureArr2.length;
                while (i < length2 && !pipTouchGestureArr2[i].onMove(this.mTouchState)) {
                    i++;
                }
                z = !this.mTouchState.isDragging();
            } else if (action != 3) {
                if (action == 7 || action == 9) {
                    if (this.mAccessibilityManager.isEnabled() && !this.mSendingHoverAccessibilityEvents) {
                        AccessibilityEvent obtain = AccessibilityEvent.obtain(128);
                        obtain.setImportantForAccessibility(true);
                        obtain.setSourceNodeId(AccessibilityNodeInfo.ROOT_NODE_ID);
                        obtain.setWindowId(-3);
                        this.mAccessibilityManager.sendAccessibilityEvent(obtain);
                        this.mSendingHoverAccessibilityEvents = true;
                    }
                } else if (action == 10 && this.mAccessibilityManager.isEnabled() && this.mSendingHoverAccessibilityEvents) {
                    AccessibilityEvent obtain2 = AccessibilityEvent.obtain(256);
                    obtain2.setImportantForAccessibility(true);
                    obtain2.setSourceNodeId(AccessibilityNodeInfo.ROOT_NODE_ID);
                    obtain2.setWindowId(-3);
                    this.mAccessibilityManager.sendAccessibilityEvent(obtain2);
                    this.mSendingHoverAccessibilityEvents = false;
                }
            }
            z = !this.mTouchState.startedDragging() && !this.mTouchState.isDragging();
            this.mTouchState.reset();
        } else {
            this.mMotionHelper.synchronizePinnedStackBounds();
            PipTouchGesture[] pipTouchGestureArr3 = this.mGestures;
            int length3 = pipTouchGestureArr3.length;
            while (i < length3) {
                pipTouchGestureArr3[i].onDown(this.mTouchState);
                i++;
            }
        }
        if (z) {
            MotionEvent obtain3 = MotionEvent.obtain(motionEvent);
            if (this.mTouchState.startedDragging()) {
                obtain3.setAction(3);
                this.mMenuController.pokeMenu();
            }
            this.mMenuController.handleTouchEvent(obtain3);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateDismissFraction() {
        if (this.mMenuController != null && !this.mIsImeShowing) {
            Rect bounds = this.mMotionHelper.getBounds();
            float f = (float) this.mInsetBounds.bottom;
            int i = bounds.bottom;
            float min = ((float) i) > f ? Math.min((((float) i) - f) / ((float) bounds.height()), 1.0f) : 0.0f;
            if (Float.compare(min, 0.0f) != 0 || this.mMenuController.isMenuActivityVisible()) {
                this.mMenuController.setDismissFraction(min);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setPinnedStackController(IPinnedStackController iPinnedStackController) {
        this.mPinnedStackController = iPinnedStackController;
    }

    /* access modifiers changed from: private */
    public void setMenuState(int i, boolean z) {
        if (i == 2 && this.mMenuState != 2) {
            Rect rect = new Rect(this.mExpandedBounds);
            if (z) {
                this.mSavedSnapFraction = this.mMotionHelper.animateToExpandedState(rect, this.mMovementBounds, this.mExpandedMovementBounds);
            }
        } else if (i == 0 && this.mMenuState == 2) {
            if (z) {
                if (this.mDeferResizeToNormalBoundsUntilRotation == -1) {
                    try {
                        int displayRotation = this.mPinnedStackController.getDisplayRotation();
                        if (this.mDisplayRotation != displayRotation) {
                            this.mDeferResizeToNormalBoundsUntilRotation = displayRotation;
                        }
                    } catch (RemoteException unused) {
                        Log.e("PipTouchHandler", "Could not get display rotation from controller");
                    }
                }
                if (this.mDeferResizeToNormalBoundsUntilRotation == -1) {
                    this.mMotionHelper.animateToUnexpandedState(new Rect(this.mNormalBounds), this.mSavedSnapFraction, this.mNormalMovementBounds, this.mMovementBounds, this.mIsMinimized, false);
                    this.mSavedSnapFraction = -1.0f;
                }
            } else {
                setTouchEnabled(false);
                this.mSavedSnapFraction = -1.0f;
            }
        }
        this.mMenuState = i;
        updateMovementBounds(i);
        boolean z2 = true;
        if (i != 1) {
            Context context = this.mContext;
            if (i != 2) {
                z2 = false;
            }
            MetricsLoggerWrapper.logPictureInPictureMenuVisible(context, z2);
        }
    }

    public PipMotionHelper getMotionHelper() {
        return this.mMotionHelper;
    }

    private void updateMovementBounds(int i) {
        Rect rect;
        int i2 = 0;
        boolean z = i == 2;
        if (z) {
            rect = this.mExpandedMovementBounds;
        } else {
            rect = this.mNormalMovementBounds;
        }
        this.mMovementBounds = rect;
        try {
            if (this.mPinnedStackController != null) {
                IPinnedStackController iPinnedStackController = this.mPinnedStackController;
                if (z) {
                    i2 = this.mExpandedShortestEdgeSize;
                }
                iPinnedStackController.setMinEdgeSize(i2);
            }
        } catch (RemoteException e) {
            Log.e("PipTouchHandler", "Could not set minimized state", e);
        }
    }

    /* access modifiers changed from: private */
    public void cleanUpDismissTarget() {
        this.mHandler.removeCallbacks(this.mShowDismissAffordance);
        this.mDismissViewController.destroyDismissTarget();
    }

    private void cleanUp() {
        if (this.mIsMinimized) {
            setMinimizedStateInternal(false);
        }
        cleanUpDismissTarget();
    }

    /* access modifiers changed from: private */
    public boolean willResizeMenu() {
        return (this.mExpandedBounds.width() == this.mNormalBounds.width() && this.mExpandedBounds.height() == this.mNormalBounds.height()) ? false : true;
    }

    public void dump(PrintWriter printWriter, String str) {
        String str2 = str + "  ";
        printWriter.println(str + "PipTouchHandler");
        printWriter.println(str2 + "mMovementBounds=" + this.mMovementBounds);
        printWriter.println(str2 + "mNormalBounds=" + this.mNormalBounds);
        printWriter.println(str2 + "mNormalMovementBounds=" + this.mNormalMovementBounds);
        printWriter.println(str2 + "mExpandedBounds=" + this.mExpandedBounds);
        printWriter.println(str2 + "mExpandedMovementBounds=" + this.mExpandedMovementBounds);
        printWriter.println(str2 + "mMenuState=" + this.mMenuState);
        printWriter.println(str2 + "mIsMinimized=" + this.mIsMinimized);
        printWriter.println(str2 + "mIsImeShowing=" + this.mIsImeShowing);
        printWriter.println(str2 + "mImeHeight=" + this.mImeHeight);
        printWriter.println(str2 + "mIsShelfShowing=" + this.mIsShelfShowing);
        printWriter.println(str2 + "mShelfHeight=" + this.mShelfHeight);
        printWriter.println(str2 + "mSavedSnapFraction=" + this.mSavedSnapFraction);
        printWriter.println(str2 + "mEnableDragToEdgeDismiss=" + this.mEnableDimissDragToEdge);
        printWriter.println(str2 + "mEnableMinimize=" + false);
        this.mSnapAlgorithm.dump(printWriter, str2);
        this.mTouchState.dump(printWriter, str2);
        this.mMotionHelper.dump(printWriter, str2);
    }
}
