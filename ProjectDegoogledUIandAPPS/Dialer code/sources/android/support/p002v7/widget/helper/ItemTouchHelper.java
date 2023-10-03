package android.support.p002v7.widget.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.p000v4.view.GestureDetectorCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.helper.ItemTouchHelper */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    private int mActionState = 0;
    /* access modifiers changed from: private */
    public int mActivePointerId = -1;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    /* access modifiers changed from: private */
    public float mDx;
    /* access modifiers changed from: private */
    public float mDy;
    /* access modifiers changed from: private */
    public GestureDetectorCompat mGestureDetector;
    /* access modifiers changed from: private */
    public float mInitialTouchX;
    /* access modifiers changed from: private */
    public float mInitialTouchY;
    private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    private float mMaxSwipeVelocity;
    private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int findPointerIndex;
            RecoverAnimation access$1000;
            ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                int unused = ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(0);
                float unused2 = ItemTouchHelper.this.mInitialTouchX = motionEvent.getX();
                float unused3 = ItemTouchHelper.this.mInitialTouchY = motionEvent.getY();
                ItemTouchHelper.access$900(ItemTouchHelper.this);
                if (ItemTouchHelper.this.mSelected == null && (access$1000 = ItemTouchHelper.access$1000(ItemTouchHelper.this, motionEvent)) != null) {
                    ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                    float unused4 = itemTouchHelper.mInitialTouchX = itemTouchHelper.mInitialTouchX - access$1000.f3mX;
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    float unused5 = itemTouchHelper2.mInitialTouchY = itemTouchHelper2.mInitialTouchY - access$1000.f4mY;
                    ItemTouchHelper.this.endRecoverAnimation(access$1000.mViewHolder, true);
                    if (ItemTouchHelper.this.mPendingCleanup.remove(access$1000.mViewHolder.itemView)) {
                        ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, access$1000.mViewHolder);
                    }
                    ItemTouchHelper.this.select(access$1000.mViewHolder, access$1000.mActionState);
                    ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                    itemTouchHelper3.updateDxDy(motionEvent, itemTouchHelper3.mSelectedFlags, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                int unused6 = ItemTouchHelper.this.mActivePointerId = -1;
                ItemTouchHelper.this.select((RecyclerView.ViewHolder) null, 0);
            } else if (ItemTouchHelper.this.mActivePointerId != -1 && (findPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId)) >= 0) {
                ItemTouchHelper.access$1600(ItemTouchHelper.this, actionMasked, motionEvent, findPointerIndex);
            }
            if (ItemTouchHelper.this.mVelocityTracker != null) {
                ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.mSelected != null) {
                return true;
            }
            return false;
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                ItemTouchHelper.this.select((RecyclerView.ViewHolder) null, 0);
            }
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
            if (ItemTouchHelper.this.mVelocityTracker != null) {
                ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.mActivePointerId != -1) {
                int actionMasked = motionEvent.getActionMasked();
                int findPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                if (findPointerIndex >= 0) {
                    ItemTouchHelper.access$1600(ItemTouchHelper.this, actionMasked, motionEvent, findPointerIndex);
                }
                RecyclerView.ViewHolder access$000 = ItemTouchHelper.this.mSelected;
                if (access$000 != null) {
                    int i = 0;
                    if (actionMasked != 1) {
                        if (actionMasked != 2) {
                            if (actionMasked != 3) {
                                if (actionMasked == 6) {
                                    int actionIndex = motionEvent.getActionIndex();
                                    if (motionEvent.getPointerId(actionIndex) == ItemTouchHelper.this.mActivePointerId) {
                                        if (actionIndex == 0) {
                                            i = 1;
                                        }
                                        int unused = ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(i);
                                        ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                                        itemTouchHelper.updateDxDy(motionEvent, itemTouchHelper.mSelectedFlags, actionIndex);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            } else if (ItemTouchHelper.this.mVelocityTracker != null) {
                                ItemTouchHelper.this.mVelocityTracker.clear();
                            }
                        } else if (findPointerIndex >= 0) {
                            ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                            itemTouchHelper2.updateDxDy(motionEvent, itemTouchHelper2.mSelectedFlags, findPointerIndex);
                            ItemTouchHelper.access$200(ItemTouchHelper.this, access$000);
                            ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                            ItemTouchHelper.this.mScrollRunnable.run();
                            ItemTouchHelper.this.mRecyclerView.invalidate();
                            return;
                        } else {
                            return;
                        }
                    }
                    ItemTouchHelper.this.select((RecyclerView.ViewHolder) null, 0);
                    int unused2 = ItemTouchHelper.this.mActivePointerId = -1;
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public View mOverdrawChild = null;
    private int mOverdrawChildPosition = -1;
    final List<View> mPendingCleanup = new ArrayList();
    List<RecoverAnimation> mRecoverAnimations = new ArrayList();
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public final Runnable mScrollRunnable = new Runnable() {
        public void run() {
            if (ItemTouchHelper.this.mSelected != null && ItemTouchHelper.access$100(ItemTouchHelper.this)) {
                if (ItemTouchHelper.this.mSelected != null) {
                    ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                    ItemTouchHelper.access$200(itemTouchHelper, itemTouchHelper.mSelected);
                }
                ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                ViewCompat.postOnAnimation(ItemTouchHelper.this.mRecyclerView, this);
            }
        }
    };
    /* access modifiers changed from: private */
    public RecyclerView.ViewHolder mSelected = null;
    /* access modifiers changed from: private */
    public int mSelectedFlags;
    private float mSelectedStartX;
    private float mSelectedStartY;
    private int mSlop;
    private List<RecyclerView.ViewHolder> mSwapTargets;
    private float mSwipeEscapeVelocity;
    private final float[] mTmpPosition = new float[2];
    private Rect mTmpRect;
    /* access modifiers changed from: private */
    public VelocityTracker mVelocityTracker;

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$Callback */
    public static abstract class Callback {
        private static final Interpolator sDragScrollInterpolator = new Interpolator() {
            public float getInterpolation(float f) {
                return f * f * f * f * f;
            }
        };
        private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        };
        private int mCachedMaxScrollSpeed = -1;

        public static int convertToRelativeDirection(int i, int i2) {
            int i3;
            int i4 = i & 789516;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 << 2;
            } else {
                int i6 = i4 << 1;
                i5 |= -789517 & i6;
                i3 = (i6 & 789516) << 2;
            }
            return i5 | i3;
        }

        public abstract boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder viewHolder, List<RecyclerView.ViewHolder> list, int i, int i2) {
            int bottom;
            int abs;
            int top;
            int abs2;
            int left;
            int abs3;
            int right;
            int abs4;
            int width = viewHolder.itemView.getWidth() + i;
            int height = viewHolder.itemView.getHeight() + i2;
            int left2 = i - viewHolder.itemView.getLeft();
            int top2 = i2 - viewHolder.itemView.getTop();
            int size = list.size();
            RecyclerView.ViewHolder viewHolder2 = null;
            int i3 = -1;
            for (int i4 = 0; i4 < size; i4++) {
                RecyclerView.ViewHolder viewHolder3 = list.get(i4);
                if (left2 > 0 && (right = viewHolder3.itemView.getRight() - width) < 0 && viewHolder3.itemView.getRight() > viewHolder.itemView.getRight() && (abs4 = Math.abs(right)) > i3) {
                    viewHolder2 = viewHolder3;
                    i3 = abs4;
                }
                if (left2 < 0 && (left = viewHolder3.itemView.getLeft() - i) > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft() && (abs3 = Math.abs(left)) > i3) {
                    viewHolder2 = viewHolder3;
                    i3 = abs3;
                }
                if (top2 < 0 && (top = viewHolder3.itemView.getTop() - i2) > 0 && viewHolder3.itemView.getTop() < viewHolder.itemView.getTop() && (abs2 = Math.abs(top)) > i3) {
                    viewHolder2 = viewHolder3;
                    i3 = abs2;
                }
                if (top2 > 0 && (bottom = viewHolder3.itemView.getBottom() - height) < 0 && viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom() && (abs = Math.abs(bottom)) > i3) {
                    viewHolder2 = viewHolder3;
                    i3 = abs;
                }
            }
            return viewHolder2;
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            ((ItemTouchUIUtilImpl) ItemTouchUIUtilImpl.INSTANCE).clearView(viewHolder.itemView);
        }

        public int convertToAbsoluteDirection(int i, int i2) {
            int i3;
            int i4 = i & 3158064;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 >> 2;
            } else {
                int i6 = i4 >> 1;
                i5 |= -3158065 & i6;
                i3 = (3158064 & i6) >> 2;
            }
            return i3 | i5;
        }

        /* access modifiers changed from: package-private */
        public final int getAbsoluteMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView));
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                return i == 8 ? 200 : 250;
            }
            if (i == 8) {
                return itemAnimator.getMoveDuration();
            }
            return itemAnimator.getRemoveDuration();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public abstract int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);

        public float getSwipeEscapeVelocity(float f) {
            return f;
        }

        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float f) {
            return f;
        }

        /* access modifiers changed from: package-private */
        public boolean hasDragFlag(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView)) & 16711680) != 0;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i, int i2, int i3, long j) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            int i4 = this.mCachedMaxScrollSpeed;
            float f = 1.0f;
            int interpolation = (int) (sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (((float) Math.abs(i2)) * 1.0f) / ((float) i))) * ((float) (((int) Math.signum((float) i2)) * i4)));
            if (j <= 2000) {
                f = ((float) j) / 2000.0f;
            }
            int interpolation2 = (int) (sDragScrollInterpolator.getInterpolation(f) * ((float) interpolation));
            if (interpolation2 != 0) {
                return interpolation2;
            }
            if (i2 > 0) {
                return 1;
            }
            return -1;
        }

        public abstract boolean isItemViewSwipeEnabled();

        public abstract boolean isLongPressDragEnabled();

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            ((ItemTouchUIUtilImpl) ItemTouchUIUtilImpl.INSTANCE).onDraw(canvas, recyclerView, viewHolder.itemView, f, f2, i, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
            ((ItemTouchUIUtilImpl) ItemTouchUIUtilImpl.INSTANCE).onDrawOver(canvas, recyclerView, viewHolder.itemView, f, f2, i, z);
        }

        /* access modifiers changed from: package-private */
        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i, float f, float f2) {
            Canvas canvas2 = canvas;
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                RecoverAnimation recoverAnimation = list.get(i2);
                recoverAnimation.update();
                int save = canvas.save();
                onChildDraw(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.f3mX, recoverAnimation.f4mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, true);
                canvas.restoreToCount(save2);
            }
        }

        /* access modifiers changed from: package-private */
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i, float f, float f2) {
            Canvas canvas2 = canvas;
            List<RecoverAnimation> list2 = list;
            int size = list.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                RecoverAnimation recoverAnimation = list2.get(i2);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.f3mX, recoverAnimation.f4mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i, true);
                canvas.restoreToCount(save2);
            }
            for (int i3 = size - 1; i3 >= 0; i3--) {
                RecoverAnimation recoverAnimation2 = list2.get(i3);
                if (recoverAnimation2.mEnded && !recoverAnimation2.mIsPendingCleanup) {
                    list2.remove(i3);
                } else if (!recoverAnimation2.mEnded) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public abstract boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

        public abstract void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4);

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder != null) {
                ((ItemTouchUIUtilImpl) ItemTouchUIUtilImpl.INSTANCE).onSelected(viewHolder.itemView);
            }
        }

        public abstract void onSwiped(RecyclerView.ViewHolder viewHolder, int i);
    }

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$ItemTouchHelperGestureListener */
    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mShouldReactToLongPress = true;

        ItemTouchHelperGestureListener() {
        }

        /* access modifiers changed from: package-private */
        public void doNotReactToLongPress() {
            this.mShouldReactToLongPress = false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            View access$2300;
            RecyclerView.ViewHolder childViewHolder;
            if (this.mShouldReactToLongPress && (access$2300 = ItemTouchHelper.this.findChildView(motionEvent)) != null && (childViewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(access$2300)) != null && ItemTouchHelper.this.mCallback.hasDragFlag(ItemTouchHelper.this.mRecyclerView, childViewHolder) && motionEvent.getPointerId(0) == ItemTouchHelper.this.mActivePointerId) {
                int findPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                float x = motionEvent.getX(findPointerIndex);
                float y = motionEvent.getY(findPointerIndex);
                float unused = ItemTouchHelper.this.mInitialTouchX = x;
                float unused2 = ItemTouchHelper.this.mInitialTouchY = y;
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                float unused3 = itemTouchHelper.mDy = 0.0f;
                float unused4 = itemTouchHelper.mDx = 0.0f;
                ItemTouchHelper.this.mCallback.isLongPressDragEnabled();
            }
        }
    }

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$RecoverAnimation */
    private static class RecoverAnimation implements Animator.AnimatorListener {
        final int mActionState;
        boolean mEnded = false;
        private float mFraction;
        boolean mIsPendingCleanup;
        boolean mOverridden = false;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        private final ValueAnimator mValueAnimator;
        final RecyclerView.ViewHolder mViewHolder;

        /* renamed from: mX */
        float f3mX;

        /* renamed from: mY */
        float f4mY;

        RecoverAnimation(RecyclerView.ViewHolder viewHolder, int i, int i2, float f, float f2, float f3, float f4) {
            this.mActionState = i2;
            this.mViewHolder = viewHolder;
            this.mStartDx = f;
            this.mStartDy = f2;
            this.mTargetX = f3;
            this.mTargetY = f4;
            this.mValueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    RecoverAnimation.this.setFraction(valueAnimator.getAnimatedFraction());
                }
            });
            this.mValueAnimator.setTarget(viewHolder.itemView);
            this.mValueAnimator.addListener(this);
            this.mFraction = 0.0f;
        }

        public void cancel() {
            this.mValueAnimator.cancel();
        }

        public void onAnimationCancel(Animator animator) {
            this.mFraction = 1.0f;
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void setDuration(long j) {
            this.mValueAnimator.setDuration(j);
        }

        public void setFraction(float f) {
            this.mFraction = f;
        }

        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }

        public void update() {
            float f = this.mStartDx;
            float f2 = this.mTargetX;
            if (f == f2) {
                this.f3mX = this.mViewHolder.itemView.getTranslationX();
            } else {
                this.f3mX = GeneratedOutlineSupport.outline0(f2, f, this.mFraction, f);
            }
            float f3 = this.mStartDy;
            float f4 = this.mTargetY;
            if (f3 == f4) {
                this.f4mY = this.mViewHolder.itemView.getTranslationY();
            } else {
                this.f4mY = GeneratedOutlineSupport.outline0(f4, f3, this.mFraction, f3);
            }
        }
    }

    /* renamed from: android.support.v7.widget.helper.ItemTouchHelper$ViewDropHandler */
    public interface ViewDropHandler {
        void prepareForDrop(View view, View view2, int i, int i2);
    }

    public ItemTouchHelper(Callback callback) {
        this.mCallback = callback;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c8, code lost:
        if (r1 > 0) goto L_0x00cc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0107 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0113  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ boolean access$100(android.support.p002v7.widget.helper.ItemTouchHelper r16) {
        /*
            r0 = r16
            android.support.v7.widget.RecyclerView$ViewHolder r1 = r0.mSelected
            r2 = 0
            r3 = -9223372036854775808
            if (r1 != 0) goto L_0x000d
            r0.mDragScrollStartTimeInMs = r3
            goto L_0x011b
        L_0x000d:
            long r5 = java.lang.System.currentTimeMillis()
            long r7 = r0.mDragScrollStartTimeInMs
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x001a
            r7 = 0
            goto L_0x001c
        L_0x001a:
            long r7 = r5 - r7
        L_0x001c:
            android.support.v7.widget.RecyclerView r1 = r0.mRecyclerView
            android.support.v7.widget.RecyclerView$LayoutManager r1 = r1.getLayoutManager()
            android.graphics.Rect r9 = r0.mTmpRect
            if (r9 != 0) goto L_0x002d
            android.graphics.Rect r9 = new android.graphics.Rect
            r9.<init>()
            r0.mTmpRect = r9
        L_0x002d:
            android.support.v7.widget.RecyclerView$ViewHolder r9 = r0.mSelected
            android.view.View r9 = r9.itemView
            android.graphics.Rect r10 = r0.mTmpRect
            r1.calculateItemDecorationsForChild(r9, r10)
            boolean r9 = r1.canScrollHorizontally()
            r10 = 0
            if (r9 == 0) goto L_0x0081
            float r9 = r0.mSelectedStartX
            float r11 = r0.mDx
            float r9 = r9 + r11
            int r9 = (int) r9
            android.graphics.Rect r11 = r0.mTmpRect
            int r11 = r11.left
            int r11 = r9 - r11
            android.support.v7.widget.RecyclerView r12 = r0.mRecyclerView
            int r12 = r12.getPaddingLeft()
            int r11 = r11 - r12
            float r12 = r0.mDx
            int r12 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r12 >= 0) goto L_0x005a
            if (r11 >= 0) goto L_0x005a
            r12 = r11
            goto L_0x0082
        L_0x005a:
            float r11 = r0.mDx
            int r11 = (r11 > r10 ? 1 : (r11 == r10 ? 0 : -1))
            if (r11 <= 0) goto L_0x0081
            android.support.v7.widget.RecyclerView$ViewHolder r11 = r0.mSelected
            android.view.View r11 = r11.itemView
            int r11 = r11.getWidth()
            int r11 = r11 + r9
            android.graphics.Rect r9 = r0.mTmpRect
            int r9 = r9.right
            int r11 = r11 + r9
            android.support.v7.widget.RecyclerView r9 = r0.mRecyclerView
            int r9 = r9.getWidth()
            android.support.v7.widget.RecyclerView r12 = r0.mRecyclerView
            int r12 = r12.getPaddingRight()
            int r9 = r9 - r12
            int r9 = r11 - r9
            if (r9 <= 0) goto L_0x0081
            r12 = r9
            goto L_0x0082
        L_0x0081:
            r12 = r2
        L_0x0082:
            boolean r1 = r1.canScrollVertically()
            if (r1 == 0) goto L_0x00cb
            float r1 = r0.mSelectedStartY
            float r9 = r0.mDy
            float r1 = r1 + r9
            int r1 = (int) r1
            android.graphics.Rect r9 = r0.mTmpRect
            int r9 = r9.top
            int r9 = r1 - r9
            android.support.v7.widget.RecyclerView r11 = r0.mRecyclerView
            int r11 = r11.getPaddingTop()
            int r9 = r9 - r11
            float r11 = r0.mDy
            int r11 = (r11 > r10 ? 1 : (r11 == r10 ? 0 : -1))
            if (r11 >= 0) goto L_0x00a5
            if (r9 >= 0) goto L_0x00a5
            r1 = r9
            goto L_0x00cc
        L_0x00a5:
            float r9 = r0.mDy
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 <= 0) goto L_0x00cb
            android.support.v7.widget.RecyclerView$ViewHolder r9 = r0.mSelected
            android.view.View r9 = r9.itemView
            int r9 = r9.getHeight()
            int r9 = r9 + r1
            android.graphics.Rect r1 = r0.mTmpRect
            int r1 = r1.bottom
            int r9 = r9 + r1
            android.support.v7.widget.RecyclerView r1 = r0.mRecyclerView
            int r1 = r1.getHeight()
            android.support.v7.widget.RecyclerView r10 = r0.mRecyclerView
            int r10 = r10.getPaddingBottom()
            int r1 = r1 - r10
            int r1 = r9 - r1
            if (r1 <= 0) goto L_0x00cb
            goto L_0x00cc
        L_0x00cb:
            r1 = r2
        L_0x00cc:
            if (r12 == 0) goto L_0x00e5
            android.support.v7.widget.helper.ItemTouchHelper$Callback r9 = r0.mCallback
            android.support.v7.widget.RecyclerView r10 = r0.mRecyclerView
            android.support.v7.widget.RecyclerView$ViewHolder r11 = r0.mSelected
            android.view.View r11 = r11.itemView
            int r11 = r11.getWidth()
            android.support.v7.widget.RecyclerView r13 = r0.mRecyclerView
            int r13 = r13.getWidth()
            r14 = r7
            int r12 = r9.interpolateOutOfBoundsScroll(r10, r11, r12, r13, r14)
        L_0x00e5:
            r14 = r12
            if (r1 == 0) goto L_0x0104
            android.support.v7.widget.helper.ItemTouchHelper$Callback r9 = r0.mCallback
            android.support.v7.widget.RecyclerView r10 = r0.mRecyclerView
            android.support.v7.widget.RecyclerView$ViewHolder r11 = r0.mSelected
            android.view.View r11 = r11.itemView
            int r11 = r11.getHeight()
            android.support.v7.widget.RecyclerView r12 = r0.mRecyclerView
            int r13 = r12.getHeight()
            r12 = r1
            r1 = r14
            r14 = r7
            int r7 = r9.interpolateOutOfBoundsScroll(r10, r11, r12, r13, r14)
            r12 = r1
            r1 = r7
            goto L_0x0105
        L_0x0104:
            r12 = r14
        L_0x0105:
            if (r12 != 0) goto L_0x010d
            if (r1 == 0) goto L_0x010a
            goto L_0x010d
        L_0x010a:
            r0.mDragScrollStartTimeInMs = r3
            goto L_0x011b
        L_0x010d:
            long r7 = r0.mDragScrollStartTimeInMs
            int r2 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0115
            r0.mDragScrollStartTimeInMs = r5
        L_0x0115:
            android.support.v7.widget.RecyclerView r0 = r0.mRecyclerView
            r0.scrollBy(r12, r1)
            r2 = 1
        L_0x011b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.helper.ItemTouchHelper.access$100(android.support.v7.widget.helper.ItemTouchHelper):boolean");
    }

    static /* synthetic */ RecoverAnimation access$1000(ItemTouchHelper itemTouchHelper, MotionEvent motionEvent) {
        RecoverAnimation recoverAnimation;
        if (itemTouchHelper.mRecoverAnimations.isEmpty()) {
            return null;
        }
        View findChildView = itemTouchHelper.findChildView(motionEvent);
        int size = itemTouchHelper.mRecoverAnimations.size();
        do {
            size--;
            if (size < 0) {
                return null;
            }
            recoverAnimation = itemTouchHelper.mRecoverAnimations.get(size);
        } while (recoverAnimation.mViewHolder.itemView != findChildView);
        return recoverAnimation;
    }

    static /* synthetic */ void access$1600(ItemTouchHelper itemTouchHelper, int i, MotionEvent motionEvent, int i2) {
        if (itemTouchHelper.mSelected == null && i == 2 && itemTouchHelper.mActionState != 2) {
            itemTouchHelper.mCallback.isItemViewSwipeEnabled();
        }
    }

    static /* synthetic */ void access$200(ItemTouchHelper itemTouchHelper, RecyclerView.ViewHolder viewHolder) {
        int i;
        int i2;
        ItemTouchHelper itemTouchHelper2 = itemTouchHelper;
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        if (!itemTouchHelper2.mRecyclerView.isLayoutRequested() && itemTouchHelper2.mActionState == 2) {
            itemTouchHelper2.mCallback.getMoveThreshold(viewHolder2);
            int i3 = (int) (itemTouchHelper2.mSelectedStartX + itemTouchHelper2.mDx);
            int i4 = (int) (itemTouchHelper2.mSelectedStartY + itemTouchHelper2.mDy);
            if (((float) Math.abs(i4 - viewHolder2.itemView.getTop())) >= ((float) viewHolder2.itemView.getHeight()) * 0.5f || ((float) Math.abs(i3 - viewHolder2.itemView.getLeft())) >= ((float) viewHolder2.itemView.getWidth()) * 0.5f) {
                List<RecyclerView.ViewHolder> list = itemTouchHelper2.mSwapTargets;
                if (list == null) {
                    itemTouchHelper2.mSwapTargets = new ArrayList();
                    itemTouchHelper2.mDistances = new ArrayList();
                } else {
                    list.clear();
                    itemTouchHelper2.mDistances.clear();
                }
                itemTouchHelper2.mCallback.getBoundingBoxMargin();
                int round = Math.round(itemTouchHelper2.mSelectedStartX + itemTouchHelper2.mDx) - 0;
                int round2 = Math.round(itemTouchHelper2.mSelectedStartY + itemTouchHelper2.mDy) - 0;
                int width = viewHolder2.itemView.getWidth() + round + 0;
                int height = viewHolder2.itemView.getHeight() + round2 + 0;
                int i5 = (round + width) / 2;
                int i6 = (round2 + height) / 2;
                RecyclerView.LayoutManager layoutManager = itemTouchHelper2.mRecyclerView.getLayoutManager();
                int childCount = layoutManager.getChildCount();
                int i7 = 0;
                while (i7 < childCount) {
                    View childAt = layoutManager.getChildAt(i7);
                    if (childAt != viewHolder2.itemView && childAt.getBottom() >= round2 && childAt.getTop() <= height && childAt.getRight() >= round && childAt.getLeft() <= width) {
                        RecyclerView.ViewHolder childViewHolder = itemTouchHelper2.mRecyclerView.getChildViewHolder(childAt);
                        i2 = round;
                        i = round2;
                        if (itemTouchHelper2.mCallback.canDropOver(itemTouchHelper2.mRecyclerView, itemTouchHelper2.mSelected, childViewHolder)) {
                            int abs = Math.abs(i5 - ((childAt.getRight() + childAt.getLeft()) / 2));
                            int abs2 = Math.abs(i6 - ((childAt.getBottom() + childAt.getTop()) / 2));
                            int i8 = (abs2 * abs2) + (abs * abs);
                            int size = itemTouchHelper2.mSwapTargets.size();
                            int i9 = 0;
                            int i10 = 0;
                            while (i10 < size) {
                                int i11 = size;
                                if (i8 <= itemTouchHelper2.mDistances.get(i10).intValue()) {
                                    break;
                                }
                                i9++;
                                i10++;
                                size = i11;
                            }
                            itemTouchHelper2.mSwapTargets.add(i9, childViewHolder);
                            itemTouchHelper2.mDistances.add(i9, Integer.valueOf(i8));
                        }
                    } else {
                        i2 = round;
                        i = round2;
                    }
                    i7++;
                    round = i2;
                    round2 = i;
                }
                List<RecyclerView.ViewHolder> list2 = itemTouchHelper2.mSwapTargets;
                if (list2.size() != 0) {
                    RecyclerView.ViewHolder chooseDropTarget = itemTouchHelper2.mCallback.chooseDropTarget(viewHolder2, list2, i3, i4);
                    if (chooseDropTarget == null) {
                        itemTouchHelper2.mSwapTargets.clear();
                        itemTouchHelper2.mDistances.clear();
                        return;
                    }
                    int adapterPosition = chooseDropTarget.getAdapterPosition();
                    int adapterPosition2 = viewHolder.getAdapterPosition();
                    if (itemTouchHelper2.mCallback.onMove(itemTouchHelper2.mRecyclerView, viewHolder2, chooseDropTarget)) {
                        itemTouchHelper2.mCallback.onMoved(itemTouchHelper2.mRecyclerView, viewHolder, adapterPosition2, chooseDropTarget, adapterPosition, i3, i4);
                    }
                }
            }
        }
    }

    static /* synthetic */ void access$900(ItemTouchHelper itemTouchHelper) {
        VelocityTracker velocityTracker = itemTouchHelper.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        itemTouchHelper.mVelocityTracker = VelocityTracker.obtain();
    }

    private int checkHorizontalSwipe(RecyclerView.ViewHolder viewHolder, int i) {
        if ((i & 12) == 0) {
            return 0;
        }
        int i2 = 8;
        int i3 = this.mDx > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            Callback callback = this.mCallback;
            float f = this.mMaxSwipeVelocity;
            callback.getSwipeVelocityThreshold(f);
            velocityTracker.computeCurrentVelocity(1000, f);
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            if (xVelocity <= 0.0f) {
                i2 = 4;
            }
            float abs = Math.abs(xVelocity);
            if ((i2 & i) != 0 && i3 == i2) {
                Callback callback2 = this.mCallback;
                float f2 = this.mSwipeEscapeVelocity;
                callback2.getSwipeEscapeVelocity(f2);
                if (abs >= f2 && abs > Math.abs(yVelocity)) {
                    return i2;
                }
            }
        }
        this.mCallback.getSwipeThreshold(viewHolder);
        float width = ((float) this.mRecyclerView.getWidth()) * 0.5f;
        if ((i & i3) == 0 || Math.abs(this.mDx) <= width) {
            return 0;
        }
        return i3;
    }

    private int checkVerticalSwipe(RecyclerView.ViewHolder viewHolder, int i) {
        if ((i & 3) == 0) {
            return 0;
        }
        int i2 = 2;
        int i3 = this.mDy > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            Callback callback = this.mCallback;
            float f = this.mMaxSwipeVelocity;
            callback.getSwipeVelocityThreshold(f);
            velocityTracker.computeCurrentVelocity(1000, f);
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            if (yVelocity <= 0.0f) {
                i2 = 1;
            }
            float abs = Math.abs(yVelocity);
            if ((i2 & i) != 0 && i2 == i3) {
                Callback callback2 = this.mCallback;
                float f2 = this.mSwipeEscapeVelocity;
                callback2.getSwipeEscapeVelocity(f2);
                if (abs >= f2 && abs > Math.abs(xVelocity)) {
                    return i2;
                }
            }
        }
        this.mCallback.getSwipeThreshold(viewHolder);
        float height = ((float) this.mRecyclerView.getHeight()) * 0.5f;
        if ((i & i3) == 0 || Math.abs(this.mDy) <= height) {
            return 0;
        }
        return i3;
    }

    /* access modifiers changed from: private */
    public void endRecoverAnimation(RecyclerView.ViewHolder viewHolder, boolean z) {
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            if (recoverAnimation.mViewHolder == viewHolder) {
                recoverAnimation.mOverridden |= z;
                if (!recoverAnimation.mEnded) {
                    recoverAnimation.cancel();
                }
                this.mRecoverAnimations.remove(size);
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public View findChildView(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (hitTest(view, x, y, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                return view;
            }
        }
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            View view2 = recoverAnimation.mViewHolder.itemView;
            if (hitTest(view2, x, y, recoverAnimation.f3mX, recoverAnimation.f4mY)) {
                return view2;
            }
        }
        return this.mRecyclerView.findChildViewUnder(x, y);
    }

    private void getSelectedDxDy(float[] fArr) {
        if ((this.mSelectedFlags & 12) != 0) {
            fArr[0] = (this.mSelectedStartX + this.mDx) - ((float) this.mSelected.itemView.getLeft());
        } else {
            fArr[0] = this.mSelected.itemView.getTranslationX();
        }
        if ((this.mSelectedFlags & 3) != 0) {
            fArr[1] = (this.mSelectedStartY + this.mDy) - ((float) this.mSelected.itemView.getTop());
        } else {
            fArr[1] = this.mSelected.itemView.getTranslationY();
        }
    }

    private static boolean hitTest(View view, float f, float f2, float f3, float f4) {
        return f >= f3 && f <= f3 + ((float) view.getWidth()) && f2 >= f4 && f2 <= f4 + ((float) view.getHeight());
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    /* access modifiers changed from: private */
    public void removeChildDrawingOrderCallbackIfNecessary(View view) {
        if (view == this.mOverdrawChild) {
            this.mOverdrawChild = null;
            if (this.mChildDrawingOrderCallback != null) {
                this.mRecyclerView.setChildDrawingOrderCallback((RecyclerView.ChildDrawingOrderCallback) null);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
        if (r0 > 0) goto L_0x00b9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void select(android.support.p002v7.widget.RecyclerView.ViewHolder r24, int r25) {
        /*
            r23 = this;
            r11 = r23
            r12 = r24
            r13 = r25
            android.support.v7.widget.RecyclerView$ViewHolder r0 = r11.mSelected
            if (r12 != r0) goto L_0x000f
            int r0 = r11.mActionState
            if (r13 != r0) goto L_0x000f
            return
        L_0x000f:
            r0 = -9223372036854775808
            r11.mDragScrollStartTimeInMs = r0
            int r4 = r11.mActionState
            r14 = 1
            r11.endRecoverAnimation(r12, r14)
            r11.mActionState = r13
            r15 = 2
            if (r13 != r15) goto L_0x002f
            if (r12 == 0) goto L_0x0027
            android.view.View r0 = r12.itemView
            r11.mOverdrawChild = r0
            int r0 = android.os.Build.VERSION.SDK_INT
            goto L_0x002f
        L_0x0027:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Must pass a ViewHolder when dragging"
            r0.<init>(r1)
            throw r0
        L_0x002f:
            int r0 = r13 * 8
            r10 = 8
            int r0 = r0 + r10
            int r0 = r14 << r0
            int r16 = r0 + -1
            android.support.v7.widget.RecyclerView$ViewHolder r9 = r11.mSelected
            r8 = 0
            if (r9 == 0) goto L_0x015f
            android.view.View r0 = r9.itemView
            android.view.ViewParent r0 = r0.getParent()
            r7 = 0
            if (r0 == 0) goto L_0x014b
            if (r4 != r15) goto L_0x004b
            r6 = r8
            goto L_0x00ba
        L_0x004b:
            int r0 = r11.mActionState
            if (r0 != r15) goto L_0x0051
            goto L_0x00b8
        L_0x0051:
            android.support.v7.widget.helper.ItemTouchHelper$Callback r0 = r11.mCallback
            android.support.v7.widget.RecyclerView r1 = r11.mRecyclerView
            int r0 = r0.getMovementFlags(r1, r9)
            android.support.v7.widget.helper.ItemTouchHelper$Callback r1 = r11.mCallback
            android.support.v7.widget.RecyclerView r2 = r11.mRecyclerView
            int r2 = android.support.p000v4.view.ViewCompat.getLayoutDirection(r2)
            int r1 = r1.convertToAbsoluteDirection(r0, r2)
            r2 = 65280(0xff00, float:9.1477E-41)
            r1 = r1 & r2
            int r1 = r1 >> r10
            if (r1 != 0) goto L_0x006d
            goto L_0x00b8
        L_0x006d:
            r0 = r0 & r2
            int r0 = r0 >> r10
            float r2 = r11.mDx
            float r2 = java.lang.Math.abs(r2)
            float r3 = r11.mDy
            float r3 = java.lang.Math.abs(r3)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x009a
            int r2 = r11.checkHorizontalSwipe(r9, r1)
            if (r2 <= 0) goto L_0x0093
            r0 = r0 & r2
            if (r0 != 0) goto L_0x00a0
            android.support.v7.widget.RecyclerView r0 = r11.mRecyclerView
            int r0 = android.support.p000v4.view.ViewCompat.getLayoutDirection(r0)
            int r0 = android.support.p002v7.widget.helper.ItemTouchHelper.Callback.convertToRelativeDirection(r2, r0)
            goto L_0x00b9
        L_0x0093:
            int r0 = r11.checkVerticalSwipe(r9, r1)
            if (r0 <= 0) goto L_0x00b8
            goto L_0x00b9
        L_0x009a:
            int r2 = r11.checkVerticalSwipe(r9, r1)
            if (r2 <= 0) goto L_0x00a2
        L_0x00a0:
            r0 = r2
            goto L_0x00b9
        L_0x00a2:
            int r1 = r11.checkHorizontalSwipe(r9, r1)
            if (r1 <= 0) goto L_0x00b8
            r0 = r0 & r1
            if (r0 != 0) goto L_0x00b6
            android.support.v7.widget.RecyclerView r0 = r11.mRecyclerView
            int r0 = android.support.p000v4.view.ViewCompat.getLayoutDirection(r0)
            int r0 = android.support.p002v7.widget.helper.ItemTouchHelper.Callback.convertToRelativeDirection(r1, r0)
            goto L_0x00b9
        L_0x00b6:
            r0 = r1
            goto L_0x00b9
        L_0x00b8:
            r0 = r8
        L_0x00b9:
            r6 = r0
        L_0x00ba:
            android.view.VelocityTracker r0 = r11.mVelocityTracker
            if (r0 == 0) goto L_0x00c3
            r0.recycle()
            r11.mVelocityTracker = r7
        L_0x00c3:
            r0 = 4
            r1 = 0
            if (r6 == r14) goto L_0x00ed
            if (r6 == r15) goto L_0x00ed
            if (r6 == r0) goto L_0x00da
            if (r6 == r10) goto L_0x00da
            r2 = 16
            if (r6 == r2) goto L_0x00da
            r2 = 32
            if (r6 == r2) goto L_0x00da
            r17 = r1
            r18 = r17
            goto L_0x00ff
        L_0x00da:
            float r2 = r11.mDx
            float r2 = java.lang.Math.signum(r2)
            android.support.v7.widget.RecyclerView r3 = r11.mRecyclerView
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r2 = r2 * r3
            r18 = r1
            r17 = r2
            goto L_0x00ff
        L_0x00ed:
            float r2 = r11.mDy
            float r2 = java.lang.Math.signum(r2)
            android.support.v7.widget.RecyclerView r3 = r11.mRecyclerView
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r2 = r2 * r3
            r17 = r1
            r18 = r2
        L_0x00ff:
            if (r4 != r15) goto L_0x0103
            r5 = r10
            goto L_0x0108
        L_0x0103:
            if (r6 <= 0) goto L_0x0107
            r5 = r15
            goto L_0x0108
        L_0x0107:
            r5 = r0
        L_0x0108:
            float[] r0 = r11.mTmpPosition
            r11.getSelectedDxDy(r0)
            float[] r0 = r11.mTmpPosition
            r19 = r0[r8]
            r20 = r0[r14]
            android.support.v7.widget.helper.ItemTouchHelper$3 r3 = new android.support.v7.widget.helper.ItemTouchHelper$3
            r0 = r3
            r1 = r23
            r2 = r9
            r14 = r3
            r3 = r5
            r15 = r5
            r5 = r19
            r21 = r6
            r6 = r20
            r13 = r7
            r7 = r17
            r8 = r18
            r22 = r9
            r9 = r21
            r21 = r10
            r10 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            android.support.v7.widget.helper.ItemTouchHelper$Callback r0 = r11.mCallback
            android.support.v7.widget.RecyclerView r1 = r11.mRecyclerView
            float r2 = r17 - r19
            float r3 = r18 - r20
            long r0 = r0.getAnimationDuration(r1, r15, r2, r3)
            r14.setDuration(r0)
            java.util.List<android.support.v7.widget.helper.ItemTouchHelper$RecoverAnimation> r0 = r11.mRecoverAnimations
            r0.add(r14)
            r14.start()
            r8 = 1
            goto L_0x015c
        L_0x014b:
            r13 = r7
            r0 = r9
            r21 = r10
            android.view.View r1 = r0.itemView
            r11.removeChildDrawingOrderCallbackIfNecessary(r1)
            android.support.v7.widget.helper.ItemTouchHelper$Callback r1 = r11.mCallback
            android.support.v7.widget.RecyclerView r2 = r11.mRecyclerView
            r1.clearView(r2, r0)
            r8 = 0
        L_0x015c:
            r11.mSelected = r13
            goto L_0x0162
        L_0x015f:
            r21 = r10
            r8 = 0
        L_0x0162:
            if (r12 == 0) goto L_0x0197
            android.support.v7.widget.helper.ItemTouchHelper$Callback r0 = r11.mCallback
            android.support.v7.widget.RecyclerView r1 = r11.mRecyclerView
            int r0 = r0.getAbsoluteMovementFlags(r1, r12)
            r0 = r0 & r16
            int r1 = r11.mActionState
            int r1 = r1 * 8
            int r0 = r0 >> r1
            r11.mSelectedFlags = r0
            android.view.View r0 = r12.itemView
            int r0 = r0.getLeft()
            float r0 = (float) r0
            r11.mSelectedStartX = r0
            android.view.View r0 = r12.itemView
            int r0 = r0.getTop()
            float r0 = (float) r0
            r11.mSelectedStartY = r0
            r11.mSelected = r12
            r0 = r25
            r1 = 2
            if (r0 != r1) goto L_0x0197
            android.support.v7.widget.RecyclerView$ViewHolder r0 = r11.mSelected
            android.view.View r0 = r0.itemView
            r1 = 0
            r0.performHapticFeedback(r1)
            goto L_0x0198
        L_0x0197:
            r1 = 0
        L_0x0198:
            android.support.v7.widget.RecyclerView r0 = r11.mRecyclerView
            android.view.ViewParent r0 = r0.getParent()
            if (r0 == 0) goto L_0x01a8
            android.support.v7.widget.RecyclerView$ViewHolder r2 = r11.mSelected
            if (r2 == 0) goto L_0x01a5
            r1 = 1
        L_0x01a5:
            r0.requestDisallowInterceptTouchEvent(r1)
        L_0x01a8:
            if (r8 != 0) goto L_0x01b3
            android.support.v7.widget.RecyclerView r0 = r11.mRecyclerView
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            r0.requestSimpleAnimationsInNextLayout()
        L_0x01b3:
            android.support.v7.widget.helper.ItemTouchHelper$Callback r0 = r11.mCallback
            android.support.v7.widget.RecyclerView$ViewHolder r1 = r11.mSelected
            int r2 = r11.mActionState
            r0.onSelectedChanged(r1, r2)
            android.support.v7.widget.RecyclerView r0 = r11.mRecyclerView
            r0.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.helper.ItemTouchHelper.select(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    /* access modifiers changed from: private */
    public void updateDxDy(MotionEvent motionEvent, int i, int i2) {
        float x = motionEvent.getX(i2);
        float y = motionEvent.getY(i2);
        this.mDx = x - this.mInitialTouchX;
        this.mDy = y - this.mInitialTouchY;
        if ((i & 4) == 0) {
            this.mDx = Math.max(0.0f, this.mDx);
        }
        if ((i & 8) == 0) {
            this.mDx = Math.min(0.0f, this.mDx);
        }
        if ((i & 1) == 0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if ((i & 2) == 0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.removeItemDecoration(this);
                this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
                this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
                for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
                    this.mCallback.clearView(this.mRecyclerView, this.mRecoverAnimations.get(0).mViewHolder);
                }
                this.mRecoverAnimations.clear();
                this.mOverdrawChild = null;
                this.mOverdrawChildPosition = -1;
                releaseVelocityTracker();
                ItemTouchHelperGestureListener itemTouchHelperGestureListener = this.mItemTouchHelperGestureListener;
                if (itemTouchHelperGestureListener != null) {
                    itemTouchHelperGestureListener.doNotReactToLongPress();
                    this.mItemTouchHelperGestureListener = null;
                }
                if (this.mGestureDetector != null) {
                    this.mGestureDetector = null;
                }
            }
            this.mRecyclerView = recyclerView;
            if (recyclerView != null) {
                Resources resources = recyclerView.getResources();
                this.mSwipeEscapeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
                this.mMaxSwipeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
                this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
                this.mRecyclerView.addItemDecoration(this);
                this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
                this.mRecyclerView.addOnChildAttachStateChangeListener(this);
                this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
                this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
            }
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    public void onChildViewAttachedToWindow(View view) {
    }

    public void onChildViewDetachedFromWindow(View view) {
        removeChildDrawingOrderCallbackIfNecessary(view);
        RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(view);
        if (childViewHolder != null) {
            RecyclerView.ViewHolder viewHolder = this.mSelected;
            if (viewHolder == null || childViewHolder != viewHolder) {
                endRecoverAnimation(childViewHolder, false);
                if (this.mPendingCleanup.remove(childViewHolder.itemView)) {
                    this.mCallback.clearView(this.mRecyclerView, childViewHolder);
                    return;
                }
                return;
            }
            select((RecyclerView.ViewHolder) null, 0);
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f;
        float f2;
        this.mOverdrawChildPosition = -1;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition);
            float[] fArr = this.mTmpPosition;
            float f3 = fArr[0];
            f = fArr[1];
            f2 = f3;
        } else {
            f2 = 0.0f;
            f = 0.0f;
        }
        this.mCallback.onDraw(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f2, f);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f;
        float f2;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition);
            float[] fArr = this.mTmpPosition;
            float f3 = fArr[0];
            f = fArr[1];
            f2 = f3;
        } else {
            f2 = 0.0f;
            f = 0.0f;
        }
        this.mCallback.onDrawOver(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f2, f);
    }

    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        if (!this.mCallback.hasDragFlag(this.mRecyclerView, viewHolder)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
        } else if (viewHolder.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        } else {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
            }
            this.mVelocityTracker = VelocityTracker.obtain();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            select(viewHolder, 2);
        }
    }
}
