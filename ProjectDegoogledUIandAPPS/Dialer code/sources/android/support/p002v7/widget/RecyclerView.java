package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.AccessibilityDelegateCompat;
import android.support.p000v4.view.NestedScrollingChild2;
import android.support.p000v4.view.NestedScrollingChildHelper;
import android.support.p000v4.view.ScrollingView;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewConfigurationCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p002v7.recyclerview.R$styleable;
import android.support.p002v7.widget.AdapterHelper;
import android.support.p002v7.widget.ChildHelper;
import android.support.p002v7.widget.GapWorker;
import android.support.p002v7.widget.ViewBoundsCheck;
import android.support.p002v7.widget.ViewInfoStore;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: android.support.v7.widget.RecyclerView */
public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2 {
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    /* access modifiers changed from: private */
    public static final boolean ALLOW_THREAD_GAP_WORK = true;
    private static final int[] CLIP_TO_PADDING_ATTR = {16842987};
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    private static final int[] NESTED_SCROLLING_ATTRS = {16843830};
    static final boolean POST_UPDATES_ON_ANIMATION = true;
    static final Interpolator sQuinticInterpolator = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private OnItemTouchListener mActiveOnItemTouchListener;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    private EdgeEffectFactory mEdgeEffectFactory;
    boolean mEnableFastScroller;
    boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    LayoutManager mLayout;
    boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    final List<ViewHolder> mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final Recycler mRecycler;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    /* access modifiers changed from: private */
    public final int[] mScrollConsumed;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    /* access modifiers changed from: private */
    public final int[] mScrollStepConsumed;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;

    /* renamed from: android.support.v7.widget.RecyclerView$Adapter */
    public static abstract class Adapter<VH extends ViewHolder> {
        private boolean mHasStableIds = false;
        private final AdapterDataObservable mObservable = new AdapterDataObservable();

        public final void bindViewHolder(VH vh, int i) {
            vh.mPosition = i;
            if (hasStableIds()) {
                vh.mItemId = getItemId(i);
            }
            vh.setFlags(1, 519);
            int i2 = Build.VERSION.SDK_INT;
            Trace.beginSection("RV OnBindView");
            onBindViewHolder(vh, i, vh.getUnmodifiedPayloads());
            vh.clearPayload();
            ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams) layoutParams).mInsetsDirty = true;
            }
            int i3 = Build.VERSION.SDK_INT;
            Trace.endSection();
        }

        public final VH createViewHolder(ViewGroup viewGroup, int i) {
            try {
                int i2 = Build.VERSION.SDK_INT;
                Trace.beginSection("RV CreateView");
                VH onCreateViewHolder = onCreateViewHolder(viewGroup, i);
                if (onCreateViewHolder.itemView.getParent() == null) {
                    onCreateViewHolder.mItemViewType = i;
                    return onCreateViewHolder;
                }
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            } finally {
                int i3 = Build.VERSION.SDK_INT;
                Trace.endSection();
            }
        }

        public abstract int getItemCount();

        public long getItemId(int i) {
            return -1;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int i) {
            this.mObservable.notifyItemRangeChanged(i, 1, (Object) null);
        }

        public final void notifyItemInserted(int i) {
            this.mObservable.notifyItemRangeInserted(i, 1);
        }

        public final void notifyItemMoved(int i, int i2) {
            this.mObservable.notifyItemMoved(i, i2);
        }

        public final void notifyItemRemoved(int i) {
            this.mObservable.notifyItemRangeRemoved(i, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public abstract void onBindViewHolder(VH vh, int i);

        public void onBindViewHolder(VH vh, int i, List<Object> list) {
            onBindViewHolder(vh, i);
        }

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }

        public boolean onFailedToRecycleView(VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(VH vh) {
        }

        public void onViewDetachedFromWindow(VH vh) {
        }

        public void onViewRecycled(VH vh) {
        }

        public void registerAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
            this.mObservable.registerObserver(adapterDataObserver);
        }

        public void setHasStableIds(boolean z) {
            if (!hasObservers()) {
                this.mHasStableIds = z;
                return;
            }
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver adapterDataObserver) {
            this.mObservable.unregisterObserver(adapterDataObserver);
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$AdapterDataObservable */
    static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        AdapterDataObservable() {
        }

        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }

        public void notifyChanged() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                RecyclerViewDataObserver recyclerViewDataObserver = (RecyclerViewDataObserver) this.mObservers.get(size);
                RecyclerView.this.assertNotInLayoutOrScroll((String) null);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mState.mStructureChanged = true;
                recyclerView.processDataSetCompletelyChanged(true);
                if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                    RecyclerView.this.requestLayout();
                }
            }
        }

        public void notifyItemMoved(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                RecyclerViewDataObserver recyclerViewDataObserver = (RecyclerViewDataObserver) this.mObservers.get(size);
                RecyclerView.this.assertNotInLayoutOrScroll((String) null);
                if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(i, i2, 1)) {
                    recyclerViewDataObserver.triggerUpdateProcessor();
                }
            }
        }

        public void notifyItemRangeChanged(int i, int i2, Object obj) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                RecyclerViewDataObserver recyclerViewDataObserver = (RecyclerViewDataObserver) this.mObservers.get(size);
                RecyclerView.this.assertNotInLayoutOrScroll((String) null);
                if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(i, i2, obj)) {
                    recyclerViewDataObserver.triggerUpdateProcessor();
                }
            }
        }

        public void notifyItemRangeInserted(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                RecyclerViewDataObserver recyclerViewDataObserver = (RecyclerViewDataObserver) this.mObservers.get(size);
                RecyclerView.this.assertNotInLayoutOrScroll((String) null);
                if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(i, i2)) {
                    recyclerViewDataObserver.triggerUpdateProcessor();
                }
            }
        }

        public void notifyItemRangeRemoved(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                RecyclerViewDataObserver recyclerViewDataObserver = (RecyclerViewDataObserver) this.mObservers.get(size);
                RecyclerView.this.assertNotInLayoutOrScroll((String) null);
                if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(i, i2)) {
                    recyclerViewDataObserver.triggerUpdateProcessor();
                }
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$AdapterDataObserver */
    public static abstract class AdapterDataObserver {
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ChildDrawingOrderCallback */
    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int i, int i2);
    }

    /* renamed from: android.support.v7.widget.RecyclerView$EdgeEffectFactory */
    public static class EdgeEffectFactory {
        /* access modifiers changed from: protected */
        public EdgeEffect createEdgeEffect(RecyclerView recyclerView, int i) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator */
    public static abstract class ItemAnimator {
        private long mAddDuration = 120;
        private long mChangeDuration = 250;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList<>();
        private ItemAnimatorListener mListener = null;
        private long mMoveDuration = 250;
        private long mRemoveDuration = 120;

        /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator$ItemAnimatorFinishedListener */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator$ItemAnimatorListener */
        interface ItemAnimatorListener {
        }

        /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimator$ItemHolderInfo */
        public static class ItemHolderInfo {
            public int left;
            public int top;

            public ItemHolderInfo setFrom(ViewHolder viewHolder) {
                View view = viewHolder.itemView;
                this.left = view.getLeft();
                this.top = view.getTop();
                view.getRight();
                view.getBottom();
                return this;
            }
        }

        static int buildAdapterChangeFlagsForAnimations(ViewHolder viewHolder) {
            int access$1800 = viewHolder.mFlags & 14;
            if (viewHolder.isInvalid()) {
                return 4;
            }
            if ((access$1800 & 4) != 0) {
                return access$1800;
            }
            int oldPosition = viewHolder.getOldPosition();
            int adapterPosition = viewHolder.getAdapterPosition();
            return (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) ? access$1800 : access$1800 | 2048;
        }

        public abstract boolean animateAppearance(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean animateDisappearance(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean animatePersistence(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        public abstract boolean canReuseUpdatedViewHolder(ViewHolder viewHolder, List<Object> list);

        public final void dispatchAnimationFinished(ViewHolder viewHolder) {
            ItemAnimatorListener itemAnimatorListener = this.mListener;
            if (itemAnimatorListener != null) {
                ((ItemAnimatorRestoreListener) itemAnimatorListener).onAnimationFinished(viewHolder);
            }
        }

        public final void dispatchAnimationsFinished() {
            int size = this.mFinishedListeners.size();
            for (int i = 0; i < size; i++) {
                this.mFinishedListeners.get(i).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }

        public abstract void endAnimation(ViewHolder viewHolder);

        public abstract void endAnimations();

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public long getChangeDuration() {
            return this.mChangeDuration;
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            boolean isRunning = isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (!isRunning) {
                    itemAnimatorFinishedListener.onAnimationsFinished();
                } else {
                    this.mFinishedListeners.add(itemAnimatorFinishedListener);
                }
            }
            return isRunning;
        }

        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }

        public ItemHolderInfo recordPreLayoutInformation(State state, ViewHolder viewHolder, int i, List<Object> list) {
            ItemHolderInfo itemHolderInfo = new ItemHolderInfo();
            View view = viewHolder.itemView;
            itemHolderInfo.left = view.getLeft();
            itemHolderInfo.top = view.getTop();
            view.getRight();
            view.getBottom();
            return itemHolderInfo;
        }

        public abstract void runPendingAnimations();

        /* access modifiers changed from: package-private */
        public void setListener(ItemAnimatorListener itemAnimatorListener) {
            this.mListener = itemAnimatorListener;
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ItemAnimatorRestoreListener */
    private class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        ItemAnimatorRestoreListener() {
        }

        public void onAnimationFinished(ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            if (!ViewHolder.access$1700(viewHolder) && !RecyclerView.this.removeAnimatingView(viewHolder.itemView) && viewHolder.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ItemDecoration */
    public static abstract class ItemDecoration {
        @Deprecated
        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        @Deprecated
        public void onDraw(Canvas canvas, RecyclerView recyclerView) {
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
            onDraw(canvas, recyclerView);
        }

        @Deprecated
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
            onDrawOver(canvas, recyclerView);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            getItemOffsets(rect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$LayoutManager */
    public static abstract class LayoutManager {
        boolean mAutoMeasure = false;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        ViewBoundsCheck mHorizontalBoundCheck = new ViewBoundsCheck(this.mHorizontalBoundCheckCallback);
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback = new ViewBoundsCheck.Callback() {
            public View getChildAt(int i) {
                return LayoutManager.this.getChildAt(i);
            }

            public int getChildEnd(View view) {
                return LayoutManager.this.getDecoratedRight(view) + ((LayoutParams) view.getLayoutParams()).rightMargin;
            }

            public int getChildStart(View view) {
                return LayoutManager.this.getDecoratedLeft(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
            }

            public int getParentEnd() {
                return LayoutManager.this.getWidth() - LayoutManager.this.getPaddingRight();
            }

            public int getParentStart() {
                return LayoutManager.this.getPaddingLeft();
            }
        };
        boolean mIsAttachedToWindow = false;
        private boolean mItemPrefetchEnabled = true;
        private boolean mMeasurementCacheEnabled = true;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations = false;
        SmoothScroller mSmoothScroller;
        ViewBoundsCheck mVerticalBoundCheck = new ViewBoundsCheck(this.mVerticalBoundCheckCallback);
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback = new ViewBoundsCheck.Callback() {
            public View getChildAt(int i) {
                return LayoutManager.this.getChildAt(i);
            }

            public int getChildEnd(View view) {
                return LayoutManager.this.getDecoratedBottom(view) + ((LayoutParams) view.getLayoutParams()).bottomMargin;
            }

            public int getChildStart(View view) {
                return LayoutManager.this.getDecoratedTop(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
            }

            public int getParentEnd() {
                return LayoutManager.this.getHeight() - LayoutManager.this.getPaddingBottom();
            }

            public int getParentStart() {
                return LayoutManager.this.getPaddingTop();
            }
        };
        private int mWidth;
        private int mWidthMode;

        /* renamed from: android.support.v7.widget.RecyclerView$LayoutManager$LayoutPrefetchRegistry */
        public interface LayoutPrefetchRegistry {
        }

        /* renamed from: android.support.v7.widget.RecyclerView$LayoutManager$Properties */
        public static class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        private void addViewInt(View view, int i, boolean z) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (z || childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.mRecyclerView) {
                int indexOfChild = this.mChildHelper.indexOfChild(view);
                if (i == -1) {
                    i = this.mChildHelper.getChildCount();
                }
                if (indexOfChild == -1) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                    outline13.append(this.mRecyclerView.indexOfChild(view));
                    outline13.append(this.mRecyclerView.exceptionLabel());
                    throw new IllegalStateException(outline13.toString());
                } else if (indexOfChild != i) {
                    this.mRecyclerView.mLayout.moveView(indexOfChild, i);
                }
            } else {
                this.mChildHelper.addView(view, i, false);
                layoutParams.mInsetsDirty = true;
                SmoothScroller smoothScroller = this.mSmoothScroller;
                if (smoothScroller != null && smoothScroller.isRunning()) {
                    this.mSmoothScroller.onChildAttachedToWindow(view);
                }
            }
            if (layoutParams.mPendingInvalidate) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }

        public static int chooseSize(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode != Integer.MIN_VALUE) {
                return mode != 1073741824 ? Math.max(i2, i3) : size;
            }
            return Math.min(size, Math.max(i2, i3));
        }

        public static int getChildMeasureSpec(int i, int i2, int i3, int i4, boolean z) {
            int i5;
            int i6 = i - i3;
            int i7 = 0;
            int max = Math.max(0, i6);
            if (z) {
                if (i4 < 0) {
                    if (i4 == -1) {
                        if (i2 == Integer.MIN_VALUE || (i2 != 0 && i2 == 1073741824)) {
                            i5 = max;
                        } else {
                            i2 = 0;
                            i5 = 0;
                        }
                        i7 = i2;
                        max = i5;
                        return View.MeasureSpec.makeMeasureSpec(max, i7);
                    }
                    max = 0;
                    return View.MeasureSpec.makeMeasureSpec(max, i7);
                }
            } else if (i4 < 0) {
                if (i4 == -1) {
                    i7 = i2;
                } else {
                    if (i4 == -2) {
                        if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                            i7 = Integer.MIN_VALUE;
                        }
                    }
                    max = 0;
                }
                return View.MeasureSpec.makeMeasureSpec(max, i7);
            }
            max = i4;
            i7 = 1073741824;
            return View.MeasureSpec.makeMeasureSpec(max, i7);
        }

        public static Properties getProperties(Context context, AttributeSet attributeSet, int i, int i2) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, i2);
            properties.orientation = obtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = obtainStyledAttributes.getInt(9, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(8, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(10, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        private static boolean isMeasurementUpToDate(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i;
            }
            return true;
        }

        public void addDisappearingView(View view) {
            addDisappearingView(view, -1);
        }

        public void addView(View view) {
            addView(view, -1);
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        public void attachView(View view, int i, LayoutParams layoutParams) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            this.mChildHelper.attachViewToParent(view, i, layoutParams, childViewHolderInt.isRemoved());
        }

        public void calculateItemDecorationsForChild(View view, Rect rect) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.getItemDecorInsetsForChild(view));
            }
        }

        public boolean canScrollHorizontally() {
            return false;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public void collectAdjacentPrefetchPositions(int i, int i2, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        }

        public int computeHorizontalScrollExtent(State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(State state) {
            return 0;
        }

        public int computeVerticalScrollRange(State state) {
            return 0;
        }

        public void detachAndScrapAttachedViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = getChildAt(childCount);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                if (!childViewHolderInt.shouldIgnore()) {
                    if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || this.mRecyclerView.mAdapter.hasStableIds()) {
                        detachViewAt(childCount);
                        recycler.scrapView(childAt);
                        this.mRecyclerView.mViewInfoStore.onViewDetached(childViewHolderInt);
                    } else {
                        removeViewAt(childCount);
                        recycler.recycleViewHolderInternal(childViewHolderInt);
                    }
                }
            }
        }

        public void detachViewAt(int i) {
            getChildAt(i);
            this.mChildHelper.detachViewFromParent(i);
        }

        /* access modifiers changed from: package-private */
        public void dispatchAttachedToWindow(RecyclerView recyclerView) {
            this.mIsAttachedToWindow = true;
            onAttachedToWindow(recyclerView);
        }

        /* access modifiers changed from: package-private */
        public void dispatchDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            this.mIsAttachedToWindow = false;
            onDetachedFromWindow(recyclerView, recycler);
        }

        public View findContainingItemView(View view) {
            View findContainingItemView;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (findContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.mHiddenViews.contains(findContainingItemView)) {
                return null;
            }
            return findContainingItemView;
        }

        public View findViewByPosition(int i) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.mInPreLayout || !childViewHolderInt.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            if (layoutParams instanceof LayoutParams) {
                return new LayoutParams((LayoutParams) layoutParams);
            }
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            }
            return new LayoutParams(layoutParams);
        }

        public int getBaseline() {
            return -1;
        }

        public int getBottomDecorationHeight(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.bottom;
        }

        public View getChildAt(int i) {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildAt(i);
            }
            return null;
        }

        public int getChildCount() {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildCount();
            }
            return 0;
        }

        public boolean getClipToPadding() {
            RecyclerView recyclerView = this.mRecyclerView;
            return recyclerView != null && recyclerView.mClipToPadding;
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
                return 1;
            }
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getDecoratedBottom(View view) {
            return getBottomDecorationHeight(view) + view.getBottom();
        }

        public void getDecoratedBoundsWithMargins(View view, Rect rect) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
        }

        public int getDecoratedLeft(View view) {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedMeasuredHeight(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(View view) {
            return getRightDecorationWidth(view) + view.getRight();
        }

        public int getDecoratedTop(View view) {
            return view.getTop() - getTopDecorationHeight(view);
        }

        public View getFocusedChild() {
            View focusedChild;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.mHiddenViews.contains(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.mRecyclerView);
        }

        public int getLeftDecorationWidth(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.left;
        }

        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.mRecyclerView);
        }

        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.mRecyclerView);
        }

        public int getPaddingBottom() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public int getPaddingLeft() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public int getPaddingRight() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public int getPaddingTop() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getPosition(View view) {
            return ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRightDecorationWidth(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.right;
        }

        public int getRowCountForAccessibility(Recycler recycler, State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || recyclerView.mAdapter == null || !canScrollVertically()) {
                return 1;
            }
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getSelectionModeForAccessibility(Recycler recycler, State state) {
            return 0;
        }

        public int getTopDecorationHeight(View view) {
            return ((LayoutParams) view.getLayoutParams()).mDecorInsets.top;
        }

        public void getTransformedBoundingBox(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (!(this.mRecyclerView == null || (matrix = view.getMatrix()) == null || matrix.isIdentity())) {
                RectF rectF = this.mRecyclerView.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        /* access modifiers changed from: package-private */
        public boolean hasFlexibleChildInBothOrientations() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }

        public boolean isAutoMeasureEnabled() {
            return this.mAutoMeasure;
        }

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(Recycler recycler, State state) {
            return false;
        }

        public boolean isSmoothScrolling() {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            return smoothScroller != null && smoothScroller.isRunning();
        }

        public boolean isViewPartiallyVisible(View view, boolean z, boolean z2) {
            boolean z3 = this.mHorizontalBoundCheck.isViewWithinBoundFlags(view, 24579) && this.mVerticalBoundCheck.isViewWithinBoundFlags(view, 24579);
            return z ? z3 : !z3;
        }

        public void layoutDecoratedWithMargins(View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.mDecorInsets;
            view.layout(i + rect.left + layoutParams.leftMargin, i2 + rect.top + layoutParams.topMargin, (i3 - rect.right) - layoutParams.rightMargin, (i4 - rect.bottom) - layoutParams.bottomMargin);
        }

        public void measureChildWithMargins(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            int i3 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right + i;
            int i4 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom + i2;
            int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin + i3, layoutParams.width, canScrollHorizontally());
            int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin + i4, layoutParams.height, canScrollVertically());
            if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }

        public void moveView(int i, int i2) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                detachViewAt(i);
                attachView(childAt, i2);
                return;
            }
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.mRecyclerView.toString());
        }

        public void offsetChildrenHorizontal(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.offsetChildrenHorizontal(i);
            }
        }

        public void offsetChildrenVertical(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.offsetChildrenVertical(i);
            }
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        }

        public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView recyclerView) {
        }

        public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
            onDetachedFromWindow(recyclerView);
        }

        public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        }

        /* access modifiers changed from: package-private */
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityNodeInfo(recyclerView.mRecycler, recyclerView.mState, accessibilityNodeInfoCompat);
        }

        /* access modifiers changed from: package-private */
        public void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                RecyclerView recyclerView = this.mRecyclerView;
                onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
            }
        }

        public View onInterceptFocusSearch(View view, int i) {
            return null;
        }

        public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsChanged(RecyclerView recyclerView) {
        }

        public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
            onItemsUpdated(recyclerView, i, i2);
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state) {
        }

        public void onMeasure(Recycler recycler, State state, int i, int i2) {
            this.mRecyclerView.defaultOnMeasure(i, i2);
        }

        @Deprecated
        public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
            return isSmoothScrolling() || recyclerView.isComputingLayout();
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onScrollStateChanged(int i) {
        }

        /* access modifiers changed from: package-private */
        public boolean performAccessibilityAction(int i, Bundle bundle) {
            RecyclerView recyclerView = this.mRecyclerView;
            return performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, i, bundle);
        }

        public boolean performAccessibilityActionForItem(Recycler recycler, State state, View view, int i, Bundle bundle) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean performAccessibilityActionForItem(View view, int i, Bundle bundle) {
            RecyclerView recyclerView = this.mRecyclerView;
            return performAccessibilityActionForItem(recyclerView.mRecycler, recyclerView.mState, view, i, bundle);
        }

        public void removeAndRecycleAllViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void removeAndRecycleScrapInt(Recycler recycler) {
            int size = recycler.mAttachedScrap.size();
            for (int i = size - 1; i >= 0; i--) {
                View view = recycler.mAttachedScrap.get(i).itemView;
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(view, false);
                    }
                    ItemAnimator itemAnimator = this.mRecyclerView.mItemAnimator;
                    if (itemAnimator != null) {
                        itemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    ViewHolder childViewHolderInt2 = RecyclerView.getChildViewHolderInt(view);
                    Recycler unused = childViewHolderInt2.mScrapContainer = null;
                    boolean unused2 = childViewHolderInt2.mInChangeScrap = false;
                    childViewHolderInt2.clearReturnedFromScrapFlag();
                    recycler.recycleViewHolderInternal(childViewHolderInt2);
                }
            }
            recycler.mAttachedScrap.clear();
            ArrayList<ViewHolder> arrayList = recycler.mChangedScrap;
            if (arrayList != null) {
                arrayList.clear();
            }
            if (size > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public void removeAndRecycleView(View view, Recycler recycler) {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i, Recycler recycler) {
            View childAt = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(childAt);
        }

        public boolean removeCallbacks(Runnable runnable) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void removeView(View view) {
            this.mChildHelper.removeView(view);
        }

        public void removeViewAt(int i) {
            if (getChildAt(i) != null) {
                this.mChildHelper.removeViewAt(i);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00bb, code lost:
            if (r9 == false) goto L_0x00c2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean requestChildRectangleOnScreen(android.support.p002v7.widget.RecyclerView r10, android.view.View r11, android.graphics.Rect r12, boolean r13, boolean r14) {
            /*
                r9 = this;
                r0 = 2
                int[] r0 = new int[r0]
                int r1 = r9.getPaddingLeft()
                int r2 = r9.getPaddingTop()
                int r3 = r9.getWidth()
                int r4 = r9.getPaddingRight()
                int r3 = r3 - r4
                int r4 = r9.getHeight()
                int r5 = r9.getPaddingBottom()
                int r4 = r4 - r5
                int r5 = r11.getLeft()
                int r6 = r12.left
                int r5 = r5 + r6
                int r6 = r11.getScrollX()
                int r5 = r5 - r6
                int r6 = r11.getTop()
                int r7 = r12.top
                int r6 = r6 + r7
                int r11 = r11.getScrollY()
                int r6 = r6 - r11
                int r11 = r12.width()
                int r11 = r11 + r5
                int r12 = r12.height()
                int r12 = r12 + r6
                int r5 = r5 - r1
                r1 = 0
                int r7 = java.lang.Math.min(r1, r5)
                int r6 = r6 - r2
                int r2 = java.lang.Math.min(r1, r6)
                int r11 = r11 - r3
                int r3 = java.lang.Math.max(r1, r11)
                int r12 = r12 - r4
                int r12 = java.lang.Math.max(r1, r12)
                int r4 = r9.getLayoutDirection()
                r8 = 1
                if (r4 != r8) goto L_0x0063
                if (r3 == 0) goto L_0x005e
                goto L_0x006b
            L_0x005e:
                int r3 = java.lang.Math.max(r7, r11)
                goto L_0x006b
            L_0x0063:
                if (r7 == 0) goto L_0x0066
                goto L_0x006a
            L_0x0066:
                int r7 = java.lang.Math.min(r5, r3)
            L_0x006a:
                r3 = r7
            L_0x006b:
                if (r2 == 0) goto L_0x006e
                goto L_0x0072
            L_0x006e:
                int r2 = java.lang.Math.min(r6, r12)
            L_0x0072:
                r0[r1] = r3
                r0[r8] = r2
                r11 = r0[r1]
                r12 = r0[r8]
                if (r14 == 0) goto L_0x00bd
                android.view.View r14 = r10.getFocusedChild()
                if (r14 != 0) goto L_0x0084
            L_0x0082:
                r9 = r1
                goto L_0x00bb
            L_0x0084:
                int r0 = r9.getPaddingLeft()
                int r2 = r9.getPaddingTop()
                int r3 = r9.getWidth()
                int r4 = r9.getPaddingRight()
                int r3 = r3 - r4
                int r4 = r9.getHeight()
                int r5 = r9.getPaddingBottom()
                int r4 = r4 - r5
                android.support.v7.widget.RecyclerView r5 = r9.mRecyclerView
                android.graphics.Rect r5 = r5.mTempRect
                r9.getDecoratedBoundsWithMargins(r14, r5)
                int r9 = r5.left
                int r9 = r9 - r11
                if (r9 >= r3) goto L_0x0082
                int r9 = r5.right
                int r9 = r9 - r11
                if (r9 <= r0) goto L_0x0082
                int r9 = r5.top
                int r9 = r9 - r12
                if (r9 >= r4) goto L_0x0082
                int r9 = r5.bottom
                int r9 = r9 - r12
                if (r9 > r2) goto L_0x00ba
                goto L_0x0082
            L_0x00ba:
                r9 = r8
            L_0x00bb:
                if (r9 == 0) goto L_0x00c2
            L_0x00bd:
                if (r11 != 0) goto L_0x00c3
                if (r12 == 0) goto L_0x00c2
                goto L_0x00c3
            L_0x00c2:
                return r1
            L_0x00c3:
                if (r13 == 0) goto L_0x00c9
                r10.scrollBy(r11, r12)
                goto L_0x00cc
            L_0x00c9:
                r10.smoothScrollBy(r11, r12)
            L_0x00cc:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.LayoutManager.requestChildRectangleOnScreen(android.support.v7.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
        }

        public void requestLayout() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        public void scrollToPosition(int i) {
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state) {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        /* access modifiers changed from: package-private */
        public void setMeasureSpecs(int i, int i2) {
            this.mWidth = View.MeasureSpec.getSize(i);
            this.mWidthMode = View.MeasureSpec.getMode(i);
            if (this.mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(i2);
            this.mHeightMode = View.MeasureSpec.getMode(i2);
            if (this.mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }
        }

        public void setMeasuredDimension(Rect rect, int i, int i2) {
            setMeasuredDimension(chooseSize(i, getPaddingRight() + getPaddingLeft() + rect.width(), getMinimumWidth()), chooseSize(i2, getPaddingBottom() + getPaddingTop() + rect.height(), getMinimumHeight()));
        }

        /* access modifiers changed from: package-private */
        public void setMeasuredDimensionFromChildren(int i, int i2) {
            int childCount = getChildCount();
            if (childCount == 0) {
                this.mRecyclerView.defaultOnMeasure(i, i2);
                return;
            }
            int i3 = Integer.MAX_VALUE;
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                Rect rect = this.mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(childAt, rect);
                int i8 = rect.left;
                if (i8 < i3) {
                    i3 = i8;
                }
                int i9 = rect.right;
                if (i9 > i4) {
                    i4 = i9;
                }
                int i10 = rect.top;
                if (i10 < i6) {
                    i6 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i5) {
                    i5 = i11;
                }
            }
            this.mRecyclerView.mTempRect.set(i3, i6, i4, i5);
            setMeasuredDimension(this.mRecyclerView.mTempRect, i, i2);
        }

        /* access modifiers changed from: package-private */
        public void setRecyclerView(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView;
                this.mChildHelper = recyclerView.mChildHelper;
                this.mWidth = recyclerView.getWidth();
                this.mHeight = recyclerView.getHeight();
            }
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        /* access modifiers changed from: package-private */
        public boolean shouldMeasureChild(View view, int i, int i2, LayoutParams layoutParams) {
            return view.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getWidth(), i, layoutParams.width) || !isMeasurementUpToDate(view.getHeight(), i2, layoutParams.height);
        }

        /* access modifiers changed from: package-private */
        public boolean shouldMeasureTwice() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean shouldReMeasureChild(View view, int i, int i2, LayoutParams layoutParams) {
            return !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getMeasuredWidth(), i, layoutParams.width) || !isMeasurementUpToDate(view.getMeasuredHeight(), i2, layoutParams.height);
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothScroller) {
            SmoothScroller smoothScroller2 = this.mSmoothScroller;
            if (!(smoothScroller2 == null || smoothScroller == smoothScroller2 || !smoothScroller2.isRunning())) {
                this.mSmoothScroller.stop();
            }
            this.mSmoothScroller = smoothScroller;
            this.mSmoothScroller.start(this.mRecyclerView, this);
        }

        /* access modifiers changed from: package-private */
        public void stopSmoothScroller() {
            SmoothScroller smoothScroller = this.mSmoothScroller;
            if (smoothScroller != null) {
                smoothScroller.stop();
            }
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        public void addDisappearingView(View view, int i) {
            addViewInt(view, i, true);
        }

        public void addView(View view, int i) {
            addViewInt(view, i, false);
        }

        public void onInitializeAccessibilityEvent(Recycler recycler, State state, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null && accessibilityEvent != null) {
                boolean z = true;
                if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                    z = false;
                }
                accessibilityEvent.setScrollable(z);
                Adapter adapter = this.mRecyclerView.mAdapter;
                if (adapter != null) {
                    accessibilityEvent.setItemCount(adapter.getItemCount());
                }
            }
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        public boolean onRequestChildFocus(RecyclerView recyclerView, State state, View view, View view2) {
            return onRequestChildFocus(recyclerView, view, view2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x0072 A[ADDED_TO_REGION] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean performAccessibilityAction(android.support.p002v7.widget.RecyclerView.Recycler r2, android.support.p002v7.widget.RecyclerView.State r3, int r4, android.os.Bundle r5) {
            /*
                r1 = this;
                android.support.v7.widget.RecyclerView r2 = r1.mRecyclerView
                r3 = 0
                if (r2 != 0) goto L_0x0006
                return r3
            L_0x0006:
                r5 = 4096(0x1000, float:5.74E-42)
                r0 = 1
                if (r4 == r5) goto L_0x0042
                r5 = 8192(0x2000, float:1.14794E-41)
                if (r4 == r5) goto L_0x0012
                r2 = r3
                r4 = r2
                goto L_0x0070
            L_0x0012:
                r4 = -1
                boolean r2 = r2.canScrollVertically(r4)
                if (r2 == 0) goto L_0x0029
                int r2 = r1.getHeight()
                int r5 = r1.getPaddingTop()
                int r2 = r2 - r5
                int r5 = r1.getPaddingBottom()
                int r2 = r2 - r5
                int r2 = -r2
                goto L_0x002a
            L_0x0029:
                r2 = r3
            L_0x002a:
                android.support.v7.widget.RecyclerView r5 = r1.mRecyclerView
                boolean r4 = r5.canScrollHorizontally(r4)
                if (r4 == 0) goto L_0x006f
                int r4 = r1.getWidth()
                int r5 = r1.getPaddingLeft()
                int r4 = r4 - r5
                int r5 = r1.getPaddingRight()
                int r4 = r4 - r5
                int r4 = -r4
                goto L_0x0070
            L_0x0042:
                boolean r2 = r2.canScrollVertically(r0)
                if (r2 == 0) goto L_0x0057
                int r2 = r1.getHeight()
                int r4 = r1.getPaddingTop()
                int r2 = r2 - r4
                int r4 = r1.getPaddingBottom()
                int r2 = r2 - r4
                goto L_0x0058
            L_0x0057:
                r2 = r3
            L_0x0058:
                android.support.v7.widget.RecyclerView r4 = r1.mRecyclerView
                boolean r4 = r4.canScrollHorizontally(r0)
                if (r4 == 0) goto L_0x006f
                int r4 = r1.getWidth()
                int r5 = r1.getPaddingLeft()
                int r4 = r4 - r5
                int r5 = r1.getPaddingRight()
                int r4 = r4 - r5
                goto L_0x0070
            L_0x006f:
                r4 = r3
            L_0x0070:
                if (r2 != 0) goto L_0x0075
                if (r4 != 0) goto L_0x0075
                return r3
            L_0x0075:
                android.support.v7.widget.RecyclerView r1 = r1.mRecyclerView
                r1.smoothScrollBy(r4, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.LayoutManager.performAccessibilityAction(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, int, android.os.Bundle):boolean");
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i = 0;
            int position = canScrollVertically() ? getPosition(view) : 0;
            if (canScrollHorizontally()) {
                i = getPosition(view);
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(position, 1, i, 1, false, false));
        }

        public void attachView(View view, int i) {
            attachView(view, i, (LayoutParams) view.getLayoutParams());
        }

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public void setMeasuredDimension(int i, int i2) {
            this.mRecyclerView.setMeasuredDimension(i, i2);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return requestChildRectangleOnScreen(recyclerView, view, rect, z, false);
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$OnChildAttachStateChangeListener */
    public interface OnChildAttachStateChangeListener {
    }

    /* renamed from: android.support.v7.widget.RecyclerView$OnItemTouchListener */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    /* renamed from: android.support.v7.widget.RecyclerView$OnScrollListener */
    public static abstract class OnScrollListener {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$RecycledViewPool */
    public static class RecycledViewPool {
        private int mAttachCount = 0;
        SparseArray<ScrapData> mScrap = new SparseArray<>();

        /* renamed from: android.support.v7.widget.RecyclerView$RecycledViewPool$ScrapData */
        static class ScrapData {
            long mBindRunningAverageNs = 0;
            long mCreateRunningAverageNs = 0;
            int mMaxScrap = 5;
            final ArrayList<ViewHolder> mScrapHeap = new ArrayList<>();

            ScrapData() {
            }
        }

        private ScrapData getScrapDataForType(int i) {
            ScrapData scrapData = this.mScrap.get(i);
            if (scrapData != null) {
                return scrapData;
            }
            ScrapData scrapData2 = new ScrapData();
            this.mScrap.put(i, scrapData2);
            return scrapData2;
        }

        /* access modifiers changed from: package-private */
        public void factorInBindTime(int i, long j) {
            ScrapData scrapDataForType = getScrapDataForType(i);
            scrapDataForType.mBindRunningAverageNs = runningAverage(scrapDataForType.mBindRunningAverageNs, j);
        }

        /* access modifiers changed from: package-private */
        public void factorInCreateTime(int i, long j) {
            ScrapData scrapDataForType = getScrapDataForType(i);
            scrapDataForType.mCreateRunningAverageNs = runningAverage(scrapDataForType.mCreateRunningAverageNs, j);
        }

        /* access modifiers changed from: package-private */
        public void onAdapterChanged(Adapter adapter, Adapter adapter2, boolean z) {
            if (adapter != null) {
                this.mAttachCount--;
            }
            if (!z && this.mAttachCount == 0) {
                for (int i = 0; i < this.mScrap.size(); i++) {
                    this.mScrap.valueAt(i).mScrapHeap.clear();
                }
            }
            if (adapter2 != null) {
                this.mAttachCount++;
            }
        }

        public void putRecycledView(ViewHolder viewHolder) {
            int itemViewType = viewHolder.getItemViewType();
            ArrayList<ViewHolder> arrayList = getScrapDataForType(itemViewType).mScrapHeap;
            if (this.mScrap.get(itemViewType).mMaxScrap > arrayList.size()) {
                viewHolder.resetInternal();
                arrayList.add(viewHolder);
            }
        }

        /* access modifiers changed from: package-private */
        public long runningAverage(long j, long j2) {
            if (j == 0) {
                return j2;
            }
            return (j2 / 4) + ((j / 4) * 3);
        }

        /* access modifiers changed from: package-private */
        public boolean willBindInTime(int i, long j, long j2) {
            long j3 = getScrapDataForType(i).mBindRunningAverageNs;
            return j3 == 0 || j + j3 < j2;
        }

        /* access modifiers changed from: package-private */
        public boolean willCreateInTime(int i, long j, long j2) {
            long j3 = getScrapDataForType(i).mCreateRunningAverageNs;
            return j3 == 0 || j + j3 < j2;
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$Recycler */
    public final class Recycler {
        final ArrayList<ViewHolder> mAttachedScrap = new ArrayList<>();
        final ArrayList<ViewHolder> mCachedViews = new ArrayList<>();
        ArrayList<ViewHolder> mChangedScrap = null;
        RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax = 2;
        private final List<ViewHolder> mUnmodifiableAttachedScrap = Collections.unmodifiableList(this.mAttachedScrap);
        int mViewCacheMax = 2;

        public Recycler() {
        }

        private void invalidateDisplayListInt(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    invalidateDisplayListInt((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                    return;
                }
                int visibility = viewGroup.getVisibility();
                viewGroup.setVisibility(4);
                viewGroup.setVisibility(visibility);
            }
        }

        /* access modifiers changed from: package-private */
        public void addViewHolderToRecycledViewPool(ViewHolder viewHolder, boolean z) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewHolder);
            if (viewHolder.hasAnyOfTheFlags(16384)) {
                viewHolder.setFlags(0, 16384);
                ViewCompat.setAccessibilityDelegate(viewHolder.itemView, (AccessibilityDelegateCompat) null);
            }
            if (z) {
                Adapter adapter = RecyclerView.this.mAdapter;
                if (adapter != null) {
                    adapter.onViewRecycled(viewHolder);
                }
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mState != null) {
                    recyclerView.mViewInfoStore.removeViewHolder(viewHolder);
                }
            }
            viewHolder.mOwnerRecyclerView = null;
            getRecycledViewPool().putRecycledView(viewHolder);
        }

        public void clear() {
            this.mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        public int convertPreLayoutPositionToPostLayout(int i) {
            if (i < 0 || i >= RecyclerView.this.mState.getItemCount()) {
                throw new IndexOutOfBoundsException("invalid position " + i + ". State " + "item count is " + RecyclerView.this.mState.getItemCount() + RecyclerView.this.exceptionLabel());
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.mState.mInPreLayout) {
                return i;
            }
            return recyclerView.mAdapterHelper.findPositionOffset(i, 0);
        }

        /* access modifiers changed from: package-private */
        public RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecycledViewPool();
            }
            return this.mRecyclerPool;
        }

        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        /* access modifiers changed from: package-private */
        public void quickRecycleScrapView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            Recycler unused = childViewHolderInt.mScrapContainer = null;
            boolean unused2 = childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(childViewHolderInt);
        }

        /* access modifiers changed from: package-private */
        public void recycleAndClearCachedViews() {
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                recycleCachedViewAt(size);
            }
            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = RecyclerView.this.mPrefetchRegistry;
                int[] iArr = layoutPrefetchRegistryImpl.mPrefetchArray;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                layoutPrefetchRegistryImpl.mCount = 0;
            }
        }

        /* access modifiers changed from: package-private */
        public void recycleCachedViewAt(int i) {
            addViewHolderToRecycledViewPool(this.mCachedViews.get(i), true);
            this.mCachedViews.remove(i);
        }

        public void recycleView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            recycleViewHolderInternal(childViewHolderInt);
        }

        /* access modifiers changed from: package-private */
        public void recycleViewHolderInternal(ViewHolder viewHolder) {
            boolean z;
            boolean z2 = false;
            if (viewHolder.isScrap() || viewHolder.itemView.getParent() != null) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Scrapped or attached views may not be recycled. isScrap:");
                outline13.append(viewHolder.isScrap());
                outline13.append(" isAttached:");
                if (viewHolder.itemView.getParent() != null) {
                    z2 = true;
                }
                outline13.append(z2);
                outline13.append(RecyclerView.this.exceptionLabel());
                throw new IllegalArgumentException(outline13.toString());
            } else if (viewHolder.isTmpDetached()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder + RecyclerView.this.exceptionLabel());
            } else if (!viewHolder.shouldIgnore()) {
                boolean access$1100 = ViewHolder.access$1100(viewHolder);
                Adapter adapter = RecyclerView.this.mAdapter;
                if ((adapter != null && access$1100 && adapter.onFailedToRecycleView(viewHolder)) || viewHolder.isRecyclable()) {
                    if (this.mViewCacheMax <= 0 || viewHolder.hasAnyOfTheFlags(526)) {
                        z = false;
                    } else {
                        int size = this.mCachedViews.size();
                        if (size >= this.mViewCacheMax && size > 0) {
                            recycleCachedViewAt(0);
                            size--;
                        }
                        if (RecyclerView.ALLOW_THREAD_GAP_WORK && size > 0 && !RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(viewHolder.mPosition)) {
                            do {
                                size--;
                                if (size < 0) {
                                    break;
                                }
                            } while (RecyclerView.this.mPrefetchRegistry.lastPrefetchIncludedPosition(this.mCachedViews.get(size).mPosition));
                            size++;
                        }
                        this.mCachedViews.add(size, viewHolder);
                        z = true;
                    }
                    if (!z) {
                        addViewHolderToRecycledViewPool(viewHolder, true);
                        z2 = true;
                    }
                } else {
                    z = false;
                }
                RecyclerView.this.mViewInfoStore.removeViewHolder(viewHolder);
                if (!z && !z2 && access$1100) {
                    viewHolder.mOwnerRecyclerView = null;
                }
            } else {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
                outline132.append(RecyclerView.this.exceptionLabel());
                throw new IllegalArgumentException(outline132.toString());
            }
        }

        /* access modifiers changed from: package-private */
        public void scrapView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList<>();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.mChangedScrap.add(childViewHolderInt);
            } else if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || RecyclerView.this.mAdapter.hasStableIds()) {
                childViewHolderInt.setScrapContainer(this, false);
                this.mAttachedScrap.add(childViewHolderInt);
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
                outline13.append(RecyclerView.this.exceptionLabel());
                throw new IllegalArgumentException(outline13.toString());
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:149:0x02d0, code lost:
            r7 = null;
         */
        /* JADX WARNING: Removed duplicated region for block: B:111:0x0213  */
        /* JADX WARNING: Removed duplicated region for block: B:114:0x021b  */
        /* JADX WARNING: Removed duplicated region for block: B:154:0x02da  */
        /* JADX WARNING: Removed duplicated region for block: B:168:0x0316  */
        /* JADX WARNING: Removed duplicated region for block: B:181:0x0394  */
        /* JADX WARNING: Removed duplicated region for block: B:190:0x03b3  */
        /* JADX WARNING: Removed duplicated region for block: B:195:0x03da  */
        /* JADX WARNING: Removed duplicated region for block: B:196:0x03dd  */
        /* JADX WARNING: Removed duplicated region for block: B:223:0x0472  */
        /* JADX WARNING: Removed duplicated region for block: B:224:0x0480  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x008f  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x0096  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.p002v7.widget.RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(int r19, boolean r20, long r21) {
            /*
                r18 = this;
                r0 = r18
                r1 = r19
                if (r1 < 0) goto L_0x04a4
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r2 = r2.mState
                int r2 = r2.getItemCount()
                if (r1 >= r2) goto L_0x04a4
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.mInPreLayout
                r3 = 32
                r8 = 0
                r10 = 0
                if (r2 == 0) goto L_0x0091
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r2 = r0.mChangedScrap
                if (r2 == 0) goto L_0x008c
                int r2 = r2.size()
                if (r2 != 0) goto L_0x0027
                goto L_0x008c
            L_0x0027:
                r4 = r10
            L_0x0028:
                if (r4 >= r2) goto L_0x0045
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r5 = r0.mChangedScrap
                java.lang.Object r5 = r5.get(r4)
                android.support.v7.widget.RecyclerView$ViewHolder r5 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r5
                boolean r6 = r5.wasReturnedFromScrap()
                if (r6 != 0) goto L_0x0042
                int r6 = r5.getLayoutPosition()
                if (r6 != r1) goto L_0x0042
                r5.addFlags(r3)
                goto L_0x008d
            L_0x0042:
                int r4 = r4 + 1
                goto L_0x0028
            L_0x0045:
                android.support.v7.widget.RecyclerView r4 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r4 = r4.mAdapter
                boolean r4 = r4.hasStableIds()
                if (r4 == 0) goto L_0x008c
                android.support.v7.widget.RecyclerView r4 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.AdapterHelper r4 = r4.mAdapterHelper
                int r4 = r4.findPositionOffset(r1, r10)
                if (r4 <= 0) goto L_0x008c
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r5 = r5.mAdapter
                int r5 = r5.getItemCount()
                if (r4 >= r5) goto L_0x008c
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r5 = r5.mAdapter
                long r4 = r5.getItemId(r4)
                r6 = r10
            L_0x006c:
                if (r6 >= r2) goto L_0x008c
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r7 = r0.mChangedScrap
                java.lang.Object r7 = r7.get(r6)
                android.support.v7.widget.RecyclerView$ViewHolder r7 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r7
                boolean r11 = r7.wasReturnedFromScrap()
                if (r11 != 0) goto L_0x0089
                long r11 = r7.getItemId()
                int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
                if (r11 != 0) goto L_0x0089
                r7.addFlags(r3)
                r5 = r7
                goto L_0x008d
            L_0x0089:
                int r6 = r6 + 1
                goto L_0x006c
            L_0x008c:
                r5 = r8
            L_0x008d:
                if (r5 == 0) goto L_0x0092
                r2 = 1
                goto L_0x0093
            L_0x0091:
                r5 = r8
            L_0x0092:
                r2 = r10
            L_0x0093:
                r4 = -1
                if (r5 != 0) goto L_0x0213
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r5 = r0.mAttachedScrap
                int r5 = r5.size()
                r6 = r10
            L_0x009d:
                if (r6 >= r5) goto L_0x00cf
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r7 = r0.mAttachedScrap
                java.lang.Object r7 = r7.get(r6)
                android.support.v7.widget.RecyclerView$ViewHolder r7 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r7
                boolean r11 = r7.wasReturnedFromScrap()
                if (r11 != 0) goto L_0x00cc
                int r11 = r7.getLayoutPosition()
                if (r11 != r1) goto L_0x00cc
                boolean r11 = r7.isInvalid()
                if (r11 != 0) goto L_0x00cc
                android.support.v7.widget.RecyclerView r11 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r11 = r11.mState
                boolean r11 = r11.mInPreLayout
                if (r11 != 0) goto L_0x00c7
                boolean r11 = r7.isRemoved()
                if (r11 != 0) goto L_0x00cc
            L_0x00c7:
                r7.addFlags(r3)
                goto L_0x0176
            L_0x00cc:
                int r6 = r6 + 1
                goto L_0x009d
            L_0x00cf:
                if (r20 != 0) goto L_0x014d
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.ChildHelper r5 = r5.mChildHelper
                java.util.List<android.view.View> r6 = r5.mHiddenViews
                int r6 = r6.size()
                r7 = r10
            L_0x00dc:
                if (r7 >= r6) goto L_0x0104
                java.util.List<android.view.View> r11 = r5.mHiddenViews
                java.lang.Object r11 = r11.get(r7)
                android.view.View r11 = (android.view.View) r11
                android.support.v7.widget.ChildHelper$Callback r12 = r5.mCallback
                android.support.v7.widget.RecyclerView$5 r12 = (android.support.p002v7.widget.RecyclerView.C02195) r12
                android.support.v7.widget.RecyclerView$ViewHolder r12 = r12.getChildViewHolder(r11)
                int r13 = r12.getLayoutPosition()
                if (r13 != r1) goto L_0x0101
                boolean r13 = r12.isInvalid()
                if (r13 != 0) goto L_0x0101
                boolean r12 = r12.isRemoved()
                if (r12 != 0) goto L_0x0101
                goto L_0x0105
            L_0x0101:
                int r7 = r7 + 1
                goto L_0x00dc
            L_0x0104:
                r11 = r8
            L_0x0105:
                if (r11 == 0) goto L_0x014d
                android.support.v7.widget.RecyclerView$ViewHolder r5 = android.support.p002v7.widget.RecyclerView.getChildViewHolderInt(r11)
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.ChildHelper r6 = r6.mChildHelper
                r6.unhide(r11)
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.ChildHelper r6 = r6.mChildHelper
                int r6 = r6.indexOfChild(r11)
                if (r6 == r4) goto L_0x012d
                android.support.v7.widget.RecyclerView r7 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.ChildHelper r7 = r7.mChildHelper
                r7.detachViewFromParent(r6)
                r0.scrapView(r11)
                r6 = 8224(0x2020, float:1.1524E-41)
                r5.addFlags(r6)
                r7 = r5
                goto L_0x0176
            L_0x012d:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "layout index should not be -1 after unhiding a view:"
                r2.append(r3)
                r2.append(r5)
                android.support.v7.widget.RecyclerView r0 = android.support.p002v7.widget.RecyclerView.this
                java.lang.String r0 = r0.exceptionLabel()
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                r1.<init>(r0)
                throw r1
            L_0x014d:
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r5 = r0.mCachedViews
                int r5 = r5.size()
                r6 = r10
            L_0x0154:
                if (r6 >= r5) goto L_0x0175
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r7 = r0.mCachedViews
                java.lang.Object r7 = r7.get(r6)
                android.support.v7.widget.RecyclerView$ViewHolder r7 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r7
                boolean r11 = r7.isInvalid()
                if (r11 != 0) goto L_0x0172
                int r11 = r7.getLayoutPosition()
                if (r11 != r1) goto L_0x0172
                if (r20 != 0) goto L_0x0176
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r5 = r0.mCachedViews
                r5.remove(r6)
                goto L_0x0176
            L_0x0172:
                int r6 = r6 + 1
                goto L_0x0154
            L_0x0175:
                r7 = r8
            L_0x0176:
                if (r7 == 0) goto L_0x0214
                boolean r5 = r7.isRemoved()
                if (r5 == 0) goto L_0x0185
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r5 = r5.mState
                boolean r5 = r5.mInPreLayout
                goto L_0x01ca
            L_0x0185:
                int r5 = r7.mPosition
                if (r5 < 0) goto L_0x01f3
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r6 = r6.mAdapter
                int r6 = r6.getItemCount()
                if (r5 >= r6) goto L_0x01f3
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r6 = r5.mState
                boolean r6 = r6.mInPreLayout
                if (r6 != 0) goto L_0x01aa
                android.support.v7.widget.RecyclerView$Adapter r5 = r5.mAdapter
                int r6 = r7.mPosition
                int r5 = r5.getItemViewType(r6)
                int r6 = r7.getItemViewType()
                if (r5 == r6) goto L_0x01aa
                goto L_0x01c7
            L_0x01aa:
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r5 = r5.mAdapter
                boolean r5 = r5.hasStableIds()
                if (r5 == 0) goto L_0x01c9
                long r5 = r7.getItemId()
                android.support.v7.widget.RecyclerView r11 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r11 = r11.mAdapter
                int r12 = r7.mPosition
                long r11 = r11.getItemId(r12)
                int r5 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
                if (r5 != 0) goto L_0x01c7
                goto L_0x01c9
            L_0x01c7:
                r5 = r10
                goto L_0x01ca
            L_0x01c9:
                r5 = 1
            L_0x01ca:
                if (r5 != 0) goto L_0x01f1
                if (r20 != 0) goto L_0x01ef
                r5 = 4
                r7.addFlags(r5)
                boolean r5 = r7.isScrap()
                if (r5 == 0) goto L_0x01e3
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.view.View r6 = r7.itemView
                r5.removeDetachedView(r6, r10)
                r7.unScrap()
                goto L_0x01ec
            L_0x01e3:
                boolean r5 = r7.wasReturnedFromScrap()
                if (r5 == 0) goto L_0x01ec
                r7.clearReturnedFromScrapFlag()
            L_0x01ec:
                r0.recycleViewHolderInternal(r7)
            L_0x01ef:
                r7 = r8
                goto L_0x0214
            L_0x01f1:
                r2 = 1
                goto L_0x0214
            L_0x01f3:
                java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Inconsistency detected. Invalid view holder adapter position"
                r2.append(r3)
                r2.append(r7)
                android.support.v7.widget.RecyclerView r0 = android.support.p002v7.widget.RecyclerView.this
                java.lang.String r0 = r0.exceptionLabel()
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                r1.<init>(r0)
                throw r1
            L_0x0213:
                r7 = r5
            L_0x0214:
                r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                if (r7 != 0) goto L_0x0394
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.AdapterHelper r5 = r5.mAdapterHelper
                int r5 = r5.findPositionOffset(r1, r10)
                if (r5 < 0) goto L_0x0357
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r6 = r6.mAdapter
                int r6 = r6.getItemCount()
                if (r5 >= r6) goto L_0x0357
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r6 = r6.mAdapter
                int r13 = r6.getItemViewType(r5)
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r6 = r6.mAdapter
                boolean r6 = r6.hasStableIds()
                if (r6 == 0) goto L_0x02d7
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r6 = r6.mAdapter
                long r6 = r6.getItemId(r5)
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r14 = r0.mAttachedScrap
                int r14 = r14.size()
                int r14 = r14 + r4
            L_0x0250:
                if (r14 < 0) goto L_0x029f
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r15 = r0.mAttachedScrap
                java.lang.Object r15 = r15.get(r14)
                android.support.v7.widget.RecyclerView$ViewHolder r15 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r15
                long r16 = r15.getItemId()
                int r16 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
                if (r16 != 0) goto L_0x029a
                boolean r16 = r15.wasReturnedFromScrap()
                if (r16 != 0) goto L_0x029a
                int r9 = r15.getItemViewType()
                if (r13 != r9) goto L_0x0287
                r15.addFlags(r3)
                boolean r3 = r15.isRemoved()
                if (r3 == 0) goto L_0x0285
                android.support.v7.widget.RecyclerView r3 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r3 = r3.mState
                boolean r3 = r3.mInPreLayout
                if (r3 != 0) goto L_0x0285
                r3 = 2
                r6 = 14
                r15.setFlags(r3, r6)
            L_0x0285:
                r7 = r15
                goto L_0x02d1
            L_0x0287:
                if (r20 != 0) goto L_0x029a
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r9 = r0.mAttachedScrap
                r9.remove(r14)
                android.support.v7.widget.RecyclerView r9 = android.support.p002v7.widget.RecyclerView.this
                android.view.View r3 = r15.itemView
                r9.removeDetachedView(r3, r10)
                android.view.View r3 = r15.itemView
                r0.quickRecycleScrapView(r3)
            L_0x029a:
                int r14 = r14 + -1
                r3 = 32
                goto L_0x0250
            L_0x029f:
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r3 = r0.mCachedViews
                int r3 = r3.size()
                int r3 = r3 + r4
            L_0x02a6:
                if (r3 < 0) goto L_0x02d0
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r9 = r0.mCachedViews
                java.lang.Object r9 = r9.get(r3)
                android.support.v7.widget.RecyclerView$ViewHolder r9 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r9
                long r14 = r9.getItemId()
                int r14 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
                if (r14 != 0) goto L_0x02cd
                int r14 = r9.getItemViewType()
                if (r13 != r14) goto L_0x02c7
                if (r20 != 0) goto L_0x02c5
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r6 = r0.mCachedViews
                r6.remove(r3)
            L_0x02c5:
                r7 = r9
                goto L_0x02d1
            L_0x02c7:
                if (r20 != 0) goto L_0x02cd
                r0.recycleCachedViewAt(r3)
                goto L_0x02d0
            L_0x02cd:
                int r3 = r3 + -1
                goto L_0x02a6
            L_0x02d0:
                r7 = r8
            L_0x02d1:
                if (r7 == 0) goto L_0x02d7
                r7.mPosition = r5
                r9 = 1
                goto L_0x02d8
            L_0x02d7:
                r9 = r2
            L_0x02d8:
                if (r7 != 0) goto L_0x0314
                android.support.v7.widget.RecyclerView$RecycledViewPool r2 = r18.getRecycledViewPool()
                android.util.SparseArray<android.support.v7.widget.RecyclerView$RecycledViewPool$ScrapData> r2 = r2.mScrap
                java.lang.Object r2 = r2.get(r13)
                android.support.v7.widget.RecyclerView$RecycledViewPool$ScrapData r2 = (android.support.p002v7.widget.RecyclerView.RecycledViewPool.ScrapData) r2
                if (r2 == 0) goto L_0x02fe
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r3 = r2.mScrapHeap
                boolean r3 = r3.isEmpty()
                if (r3 != 0) goto L_0x02fe
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r2 = r2.mScrapHeap
                int r3 = r2.size()
                int r3 = r3 + r4
                java.lang.Object r2 = r2.remove(r3)
                android.support.v7.widget.RecyclerView$ViewHolder r2 = (android.support.p002v7.widget.RecyclerView.ViewHolder) r2
                goto L_0x02ff
            L_0x02fe:
                r2 = r8
            L_0x02ff:
                if (r2 == 0) goto L_0x0313
                r2.resetInternal()
                boolean r3 = android.support.p002v7.widget.RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST
                if (r3 == 0) goto L_0x0313
                android.view.View r3 = r2.itemView
                boolean r4 = r3 instanceof android.view.ViewGroup
                if (r4 == 0) goto L_0x0313
                android.view.ViewGroup r3 = (android.view.ViewGroup) r3
                r0.invalidateDisplayListInt(r3, r10)
            L_0x0313:
                r7 = r2
            L_0x0314:
                if (r7 != 0) goto L_0x0395
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                long r14 = r2.getNanoTime()
                int r2 = (r21 > r11 ? 1 : (r21 == r11 ? 0 : -1))
                if (r2 == 0) goto L_0x032d
                android.support.v7.widget.RecyclerView$RecycledViewPool r2 = r0.mRecyclerPool
                r3 = r13
                r4 = r14
                r6 = r21
                boolean r2 = r2.willCreateInTime(r3, r4, r6)
                if (r2 != 0) goto L_0x032d
                return r8
            L_0x032d:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r3 = r2.mAdapter
                android.support.v7.widget.RecyclerView$ViewHolder r7 = r3.createViewHolder(r2, r13)
                boolean r2 = android.support.p002v7.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
                if (r2 == 0) goto L_0x034a
                android.view.View r2 = r7.itemView
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.findNestedRecyclerView(r2)
                if (r2 == 0) goto L_0x034a
                java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
                r3.<init>(r2)
                r7.mNestedRecyclerView = r3
            L_0x034a:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                long r2 = r2.getNanoTime()
                android.support.v7.widget.RecyclerView$RecycledViewPool r4 = r0.mRecyclerPool
                long r2 = r2 - r14
                r4.factorInCreateTime(r13, r2)
                goto L_0x0395
            L_0x0357:
                java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Inconsistency detected. Invalid item position "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r1 = "(offset:"
                r3.append(r1)
                r3.append(r5)
                java.lang.String r1 = ")."
                r3.append(r1)
                java.lang.String r1 = "state:"
                r3.append(r1)
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r1 = r1.mState
                int r1 = r1.getItemCount()
                r3.append(r1)
                android.support.v7.widget.RecyclerView r0 = android.support.p002v7.widget.RecyclerView.this
                java.lang.String r0 = r0.exceptionLabel()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2.<init>(r0)
                throw r2
            L_0x0394:
                r9 = r2
            L_0x0395:
                r8 = r7
                if (r9 == 0) goto L_0x03cc
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.mInPreLayout
                if (r2 != 0) goto L_0x03cc
                r2 = 8192(0x2000, float:1.14794E-41)
                boolean r3 = r8.hasAnyOfTheFlags(r2)
                if (r3 == 0) goto L_0x03cc
                r8.setFlags(r10, r2)
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.mRunSimpleAnimations
                if (r2 == 0) goto L_0x03cc
                int r2 = android.support.p002v7.widget.RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(r8)
                r2 = r2 | 4096(0x1000, float:5.74E-42)
                android.support.v7.widget.RecyclerView r3 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$ItemAnimator r4 = r3.mItemAnimator
                android.support.v7.widget.RecyclerView$State r3 = r3.mState
                java.util.List r5 = r8.getUnmodifiedPayloads()
                android.support.v7.widget.RecyclerView$ItemAnimator$ItemHolderInfo r2 = r4.recordPreLayoutInformation(r3, r8, r2, r5)
                android.support.v7.widget.RecyclerView r3 = android.support.p002v7.widget.RecyclerView.this
                r3.recordAnimationInfoIfBouncedHiddenView(r8, r2)
            L_0x03cc:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.mInPreLayout
                if (r2 == 0) goto L_0x03dd
                boolean r2 = r8.isBound()
                if (r2 == 0) goto L_0x03dd
                r8.mPreLayoutPosition = r1
                goto L_0x03f0
            L_0x03dd:
                boolean r2 = r8.isBound()
                if (r2 == 0) goto L_0x03f4
                boolean r2 = r8.needsUpdate()
                if (r2 != 0) goto L_0x03f4
                boolean r2 = r8.isInvalid()
                if (r2 == 0) goto L_0x03f0
                goto L_0x03f4
            L_0x03f0:
                r1 = r10
                r4 = 1
                goto L_0x046a
            L_0x03f4:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.AdapterHelper r2 = r2.mAdapterHelper
                int r13 = r2.findPositionOffset(r1, r10)
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                r8.mOwnerRecyclerView = r2
                int r3 = r8.getItemViewType()
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                long r14 = r2.getNanoTime()
                int r2 = (r21 > r11 ? 1 : (r21 == r11 ? 0 : -1))
                if (r2 == 0) goto L_0x041a
                android.support.v7.widget.RecyclerView$RecycledViewPool r2 = r0.mRecyclerPool
                r4 = r14
                r6 = r21
                boolean r2 = r2.willBindInTime(r3, r4, r6)
                if (r2 != 0) goto L_0x041a
                goto L_0x03f0
            L_0x041a:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r2 = r2.mAdapter
                r2.bindViewHolder(r8, r13)
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                long r2 = r2.getNanoTime()
                android.support.v7.widget.RecyclerView$RecycledViewPool r4 = r0.mRecyclerPool
                int r5 = r8.getItemViewType()
                long r2 = r2 - r14
                r4.factorInBindTime(r5, r2)
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                boolean r2 = r2.isAccessibilityEnabled()
                if (r2 == 0) goto L_0x045e
                android.view.View r2 = r8.itemView
                int r3 = android.support.p000v4.view.ViewCompat.getImportantForAccessibility(r2)
                if (r3 != 0) goto L_0x0448
                int r3 = android.os.Build.VERSION.SDK_INT
                r4 = 1
                r2.setImportantForAccessibility(r4)
                goto L_0x0449
            L_0x0448:
                r4 = 1
            L_0x0449:
                boolean r3 = android.support.p000v4.view.ViewCompat.hasAccessibilityDelegate(r2)
                if (r3 != 0) goto L_0x045f
                r3 = 16384(0x4000, float:2.2959E-41)
                r8.addFlags(r3)
                android.support.v7.widget.RecyclerView r3 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerViewAccessibilityDelegate r3 = r3.mAccessibilityDelegate
                android.support.v4.view.AccessibilityDelegateCompat r3 = r3.mItemDelegate
                android.support.p000v4.view.ViewCompat.setAccessibilityDelegate(r2, r3)
                goto L_0x045f
            L_0x045e:
                r4 = 1
            L_0x045f:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r2 = r2.mState
                boolean r2 = r2.mInPreLayout
                if (r2 == 0) goto L_0x0469
                r8.mPreLayoutPosition = r1
            L_0x0469:
                r1 = r4
            L_0x046a:
                android.view.View r2 = r8.itemView
                android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
                if (r2 != 0) goto L_0x0480
                android.support.v7.widget.RecyclerView r0 = android.support.p002v7.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r0 = r0.generateDefaultLayoutParams()
                android.support.v7.widget.RecyclerView$LayoutParams r0 = (android.support.p002v7.widget.RecyclerView.LayoutParams) r0
                android.view.View r2 = r8.itemView
                r2.setLayoutParams(r0)
                goto L_0x0499
            L_0x0480:
                android.support.v7.widget.RecyclerView r3 = android.support.p002v7.widget.RecyclerView.this
                boolean r3 = r3.checkLayoutParams(r2)
                if (r3 != 0) goto L_0x0496
                android.support.v7.widget.RecyclerView r0 = android.support.p002v7.widget.RecyclerView.this
                android.view.ViewGroup$LayoutParams r0 = r0.generateLayoutParams((android.view.ViewGroup.LayoutParams) r2)
                android.support.v7.widget.RecyclerView$LayoutParams r0 = (android.support.p002v7.widget.RecyclerView.LayoutParams) r0
                android.view.View r2 = r8.itemView
                r2.setLayoutParams(r0)
                goto L_0x0499
            L_0x0496:
                r0 = r2
                android.support.v7.widget.RecyclerView$LayoutParams r0 = (android.support.p002v7.widget.RecyclerView.LayoutParams) r0
            L_0x0499:
                r0.mViewHolder = r8
                if (r9 == 0) goto L_0x04a0
                if (r1 == 0) goto L_0x04a0
                goto L_0x04a1
            L_0x04a0:
                r4 = r10
            L_0x04a1:
                r0.mPendingInvalidate = r4
                return r8
            L_0x04a4:
                java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Invalid item position "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r4 = "("
                r3.append(r4)
                r3.append(r1)
                java.lang.String r1 = "). Item count:"
                r3.append(r1)
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r1 = r1.mState
                int r1 = r1.getItemCount()
                r3.append(r1)
                android.support.v7.widget.RecyclerView r0 = android.support.p002v7.widget.RecyclerView.this
                java.lang.String r0 = r0.exceptionLabel()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2.<init>(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.Recycler.tryGetViewHolderForPositionByDeadline(int, boolean, long):android.support.v7.widget.RecyclerView$ViewHolder");
        }

        /* access modifiers changed from: package-private */
        public void unscrapView(ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            } else {
                this.mAttachedScrap.remove(viewHolder);
            }
            Recycler unused = viewHolder.mScrapContainer = null;
            boolean unused2 = viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }

        /* access modifiers changed from: package-private */
        public void updateViewCacheSize() {
            LayoutManager layoutManager = RecyclerView.this.mLayout;
            this.mViewCacheMax = this.mRequestedCacheMax + (layoutManager != null ? layoutManager.mPrefetchMaxCountObserved : 0);
            for (int size = this.mCachedViews.size() - 1; size >= 0 && this.mCachedViews.size() > this.mViewCacheMax; size--) {
                recycleCachedViewAt(size);
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$RecyclerViewDataObserver */
    private class RecyclerViewDataObserver extends AdapterDataObserver {
        RecyclerViewDataObserver() {
        }

        /* access modifiers changed from: package-private */
        public void triggerUpdateProcessor() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                    ViewCompat.postOnAnimation(recyclerView, recyclerView.mUpdateChildViewsRunnable);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.mAdapterUpdateDuringMeasure = true;
            recyclerView2.requestLayout();
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$SmoothScroller */
    public static abstract class SmoothScroller {
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction = new Action(0, 0);
        private boolean mRunning;
        private boolean mStarted;
        private int mTargetPosition = -1;
        private View mTargetView;

        /* renamed from: android.support.v7.widget.RecyclerView$SmoothScroller$Action */
        public static class Action {
            private boolean mChanged = false;
            private int mConsecutiveUpdates = 0;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition = -1;

            public Action(int i, int i2) {
                this.mDx = i;
                this.mDy = i2;
                this.mDuration = Integer.MIN_VALUE;
                this.mInterpolator = null;
            }

            /* access modifiers changed from: package-private */
            public boolean hasJumpTarget() {
                return this.mJumpToPosition >= 0;
            }

            public void jumpTo(int i) {
                this.mJumpToPosition = i;
            }

            /* access modifiers changed from: package-private */
            public void runIfNecessary(RecyclerView recyclerView) {
                int i = this.mJumpToPosition;
                if (i >= 0) {
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i);
                    this.mChanged = false;
                } else if (!this.mChanged) {
                    this.mConsecutiveUpdates = 0;
                } else if (this.mInterpolator == null || this.mDuration >= 1) {
                    int i2 = this.mDuration;
                    if (i2 >= 1) {
                        Interpolator interpolator = this.mInterpolator;
                        if (interpolator != null) {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i2, interpolator);
                        } else if (i2 == Integer.MIN_VALUE) {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
                        } else {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i2);
                        }
                        this.mConsecutiveUpdates++;
                        if (this.mConsecutiveUpdates > 10) {
                            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                        }
                        this.mChanged = false;
                        return;
                    }
                    throw new IllegalStateException("Scroll duration must be a positive number");
                } else {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
            }

            public void update(int i, int i2, int i3, Interpolator interpolator) {
                this.mDx = i;
                this.mDy = i2;
                this.mDuration = i3;
                this.mInterpolator = interpolator;
                this.mChanged = true;
            }
        }

        /* renamed from: android.support.v7.widget.RecyclerView$SmoothScroller$ScrollVectorProvider */
        public interface ScrollVectorProvider {
            PointF computeScrollVectorForPosition(int i);
        }

        static /* synthetic */ void access$800(SmoothScroller smoothScroller, int i, int i2) {
            int i3;
            int i4;
            int i5;
            PointF computeScrollVectorForPosition;
            SmoothScroller smoothScroller2 = smoothScroller;
            RecyclerView recyclerView = smoothScroller2.mRecyclerView;
            if (!smoothScroller2.mRunning || smoothScroller2.mTargetPosition == -1 || recyclerView == null) {
                smoothScroller.stop();
            }
            if (!(!smoothScroller2.mPendingInitialRun || smoothScroller2.mTargetView != null || smoothScroller2.mLayoutManager == null || (computeScrollVectorForPosition = smoothScroller2.computeScrollVectorForPosition(smoothScroller2.mTargetPosition)) == null || (computeScrollVectorForPosition.x == 0.0f && computeScrollVectorForPosition.y == 0.0f))) {
                recyclerView.scrollStep((int) Math.signum(computeScrollVectorForPosition.x), (int) Math.signum(computeScrollVectorForPosition.y), (int[]) null);
            }
            int i6 = 0;
            smoothScroller2.mPendingInitialRun = false;
            View view = smoothScroller2.mTargetView;
            if (view != null) {
                if (smoothScroller2.getChildPosition(view) == smoothScroller2.mTargetPosition) {
                    View view2 = smoothScroller2.mTargetView;
                    State state = recyclerView.mState;
                    Action action = smoothScroller2.mRecyclingAction;
                    LinearSmoothScroller linearSmoothScroller = (LinearSmoothScroller) smoothScroller2;
                    PointF pointF = linearSmoothScroller.mTargetVector;
                    int i7 = (pointF == null || pointF.x == 0.0f) ? 0 : i5 > 0 ? 1 : -1;
                    LayoutManager layoutManager = linearSmoothScroller.getLayoutManager();
                    if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
                        i3 = 0;
                    } else {
                        LayoutParams layoutParams = (LayoutParams) view2.getLayoutParams();
                        i3 = linearSmoothScroller.calculateDtToFit(layoutManager.getDecoratedLeft(view2) - layoutParams.leftMargin, layoutManager.getDecoratedRight(view2) + layoutParams.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), i7);
                    }
                    PointF pointF2 = linearSmoothScroller.mTargetVector;
                    int i8 = (pointF2 == null || pointF2.y == 0.0f) ? 0 : i4 > 0 ? 1 : -1;
                    LayoutManager layoutManager2 = linearSmoothScroller.getLayoutManager();
                    if (layoutManager2 != null && layoutManager2.canScrollVertically()) {
                        LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
                        i6 = linearSmoothScroller.calculateDtToFit(layoutManager2.getDecoratedTop(view2) - layoutParams2.topMargin, layoutManager2.getDecoratedBottom(view2) + layoutParams2.bottomMargin, layoutManager2.getPaddingTop(), layoutManager2.getHeight() - layoutManager2.getPaddingBottom(), i8);
                    }
                    int ceil = (int) Math.ceil(((double) linearSmoothScroller.calculateTimeForScrolling((int) Math.sqrt((double) ((i6 * i6) + (i3 * i3))))) / 0.3356d);
                    if (ceil > 0) {
                        action.update(-i3, -i6, ceil, linearSmoothScroller.mDecelerateInterpolator);
                    }
                    smoothScroller2.mRecyclingAction.runIfNecessary(recyclerView);
                    smoothScroller.stop();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    smoothScroller2.mTargetView = null;
                }
            }
            if (smoothScroller2.mRunning) {
                smoothScroller2.onSeekTargetStep(i, i2, recyclerView.mState, smoothScroller2.mRecyclingAction);
                boolean hasJumpTarget = smoothScroller2.mRecyclingAction.hasJumpTarget();
                smoothScroller2.mRecyclingAction.runIfNecessary(recyclerView);
                if (!hasJumpTarget) {
                    return;
                }
                if (smoothScroller2.mRunning) {
                    smoothScroller2.mPendingInitialRun = true;
                    recyclerView.mViewFlinger.postOnAnimation();
                    return;
                }
                smoothScroller.stop();
            }
        }

        public PointF computeScrollVectorForPosition(int i) {
            LayoutManager layoutManager = this.mLayoutManager;
            if (layoutManager instanceof ScrollVectorProvider) {
                return ((ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(i);
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
            outline13.append(ScrollVectorProvider.class.getCanonicalName());
            Log.w("RecyclerView", outline13.toString());
            return null;
        }

        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }

        public int getChildPosition(View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }

        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        /* access modifiers changed from: protected */
        public void onChildAttachedToWindow(View view) {
            if (this.mRecyclerView.getChildLayoutPosition(view) == this.mTargetPosition) {
                this.mTargetView = view;
            }
        }

        /* access modifiers changed from: protected */
        public abstract void onSeekTargetStep(int i, int i2, State state, Action action);

        public void setTargetPosition(int i) {
            this.mTargetPosition = i;
        }

        /* access modifiers changed from: package-private */
        public void start(RecyclerView recyclerView, LayoutManager layoutManager) {
            if (this.mStarted) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("An instance of ");
                outline13.append(getClass().getSimpleName());
                outline13.append(" was started ");
                outline13.append("more than once. Each instance of");
                outline13.append(getClass().getSimpleName());
                outline13.append(" ");
                outline13.append("is intended to only be used once. You should create a new instance for ");
                outline13.append("each use.");
                Log.w("RecyclerView", outline13.toString());
            }
            this.mRecyclerView = recyclerView;
            this.mLayoutManager = layoutManager;
            int i = this.mTargetPosition;
            if (i != -1) {
                int unused = this.mRecyclerView.mState.mTargetPosition = i;
                this.mRunning = true;
                this.mPendingInitialRun = true;
                this.mTargetView = this.mRecyclerView.mLayout.findViewByPosition(this.mTargetPosition);
                LinearSmoothScroller linearSmoothScroller = (LinearSmoothScroller) this;
                this.mRecyclerView.mViewFlinger.postOnAnimation();
                this.mStarted = true;
                return;
            }
            throw new IllegalArgumentException("Invalid target position");
        }

        /* access modifiers changed from: protected */
        public final void stop() {
            if (this.mRunning) {
                this.mRunning = false;
                LinearSmoothScroller linearSmoothScroller = (LinearSmoothScroller) this;
                linearSmoothScroller.mInterimTargetDy = 0;
                linearSmoothScroller.mInterimTargetDx = 0;
                linearSmoothScroller.mTargetVector = null;
                int unused = this.mRecyclerView.mState.mTargetPosition = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                LayoutManager layoutManager = this.mLayoutManager;
                if (layoutManager.mSmoothScroller == this) {
                    layoutManager.mSmoothScroller = null;
                }
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$State */
    public static class State {
        private SparseArray<Object> mData;
        int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout = false;
        boolean mIsMeasuring = false;
        int mItemCount = 0;
        int mLayoutStep = 1;
        int mPreviousLayoutItemCount = 0;
        boolean mRunPredictiveAnimations = false;
        boolean mRunSimpleAnimations = false;
        boolean mStructureChanged = false;
        /* access modifiers changed from: private */
        public int mTargetPosition = -1;
        boolean mTrackOldChangeHolders = false;

        /* access modifiers changed from: package-private */
        public void assertLayoutStep(int i) {
            if ((this.mLayoutStep & i) == 0) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Layout state should be one of ");
                outline13.append(Integer.toBinaryString(i));
                outline13.append(" but it is ");
                outline13.append(Integer.toBinaryString(this.mLayoutStep));
                throw new IllegalStateException(outline13.toString());
            }
        }

        public int getItemCount() {
            return this.mInPreLayout ? this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout : this.mItemCount;
        }

        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }

        public boolean hasTargetScrollPosition() {
            return this.mTargetPosition != -1;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("State{mTargetPosition=");
            outline13.append(this.mTargetPosition);
            outline13.append(", mData=");
            outline13.append(this.mData);
            outline13.append(", mItemCount=");
            outline13.append(this.mItemCount);
            outline13.append(", mIsMeasuring=");
            outline13.append(this.mIsMeasuring);
            outline13.append(", mPreviousLayoutItemCount=");
            outline13.append(this.mPreviousLayoutItemCount);
            outline13.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
            outline13.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
            outline13.append(", mStructureChanged=");
            outline13.append(this.mStructureChanged);
            outline13.append(", mInPreLayout=");
            outline13.append(this.mInPreLayout);
            outline13.append(", mRunSimpleAnimations=");
            outline13.append(this.mRunSimpleAnimations);
            outline13.append(", mRunPredictiveAnimations=");
            outline13.append(this.mRunPredictiveAnimations);
            outline13.append('}');
            return outline13.toString();
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ViewFlinger */
    class ViewFlinger implements Runnable {
        private boolean mEatRunOnAnimationRequest = false;
        Interpolator mInterpolator = RecyclerView.sQuinticInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback = false;
        /* access modifiers changed from: private */
        public OverScroller mScroller;

        ViewFlinger() {
            this.mScroller = new OverScroller(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
        }

        private int computeScrollDuration(int i, int i2, int i3, int i4) {
            int i5;
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = abs > abs2;
            int sqrt = (int) Math.sqrt((double) ((i4 * i4) + (i3 * i3)));
            int sqrt2 = (int) Math.sqrt((double) ((i2 * i2) + (i * i)));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i6 = width / 2;
            float f = (float) width;
            float f2 = (float) i6;
            float sin = (((float) Math.sin((double) ((Math.min(1.0f, (((float) sqrt2) * 1.0f) / f) - 0.5f) * 0.47123894f))) * f2) + f2;
            if (sqrt > 0) {
                i5 = Math.round(Math.abs(sin / ((float) sqrt)) * 1000.0f) * 4;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i5 = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
            }
            return Math.min(i5, 2000);
        }

        public void fling(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation();
        }

        /* access modifiers changed from: package-private */
        public void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
                return;
            }
            RecyclerView.this.removeCallbacks(this);
            ViewCompat.postOnAnimation(RecyclerView.this, this);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
            if (r8 > 0) goto L_0x00fc;
         */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x00f4  */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x0104  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r23 = this;
                r0 = r23
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$LayoutManager r2 = r1.mLayout
                if (r2 != 0) goto L_0x000c
                r23.stop()
                return
            L_0x000c:
                r2 = 0
                r0.mReSchedulePostAnimationCallback = r2
                r3 = 1
                r0.mEatRunOnAnimationRequest = r3
                r1.consumePendingUpdateOperations()
                android.widget.OverScroller r1 = r0.mScroller
                android.support.v7.widget.RecyclerView r4 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$LayoutManager r4 = r4.mLayout
                android.support.v7.widget.RecyclerView$SmoothScroller r4 = r4.mSmoothScroller
                boolean r5 = r1.computeScrollOffset()
                if (r5 == 0) goto L_0x01a2
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                int[] r5 = r5.mScrollConsumed
                int r12 = r1.getCurrX()
                int r13 = r1.getCurrY()
                int r6 = r0.mLastFlingX
                int r14 = r12 - r6
                int r6 = r0.mLastFlingY
                int r15 = r13 - r6
                r0.mLastFlingX = r12
                r0.mLastFlingY = r13
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                r10 = 0
                r11 = 1
                r7 = r14
                r8 = r15
                r9 = r5
                boolean r6 = r6.dispatchNestedPreScroll(r7, r8, r9, r10, r11)
                if (r6 == 0) goto L_0x0050
                r6 = r5[r2]
                int r14 = r14 - r6
                r5 = r5[r3]
                int r15 = r15 - r5
            L_0x0050:
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$Adapter r6 = r5.mAdapter
                if (r6 == 0) goto L_0x00a7
                int[] r6 = r5.mScrollStepConsumed
                r5.scrollStep(r14, r15, r6)
                android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.this
                int[] r5 = r5.mScrollStepConsumed
                r5 = r5[r2]
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                int[] r6 = r6.mScrollStepConsumed
                r6 = r6[r3]
                int r7 = r14 - r5
                int r8 = r15 - r6
                if (r4 == 0) goto L_0x00ab
                boolean r9 = r4.isPendingInitialRun()
                if (r9 != 0) goto L_0x00ab
                boolean r9 = r4.isRunning()
                if (r9 == 0) goto L_0x00ab
                android.support.v7.widget.RecyclerView r9 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$State r9 = r9.mState
                int r9 = r9.getItemCount()
                if (r9 != 0) goto L_0x008d
                r4.stop()
                goto L_0x00ab
            L_0x008d:
                int r10 = r4.getTargetPosition()
                if (r10 < r9) goto L_0x009f
                int r9 = r9 - r3
                r4.setTargetPosition(r9)
                int r9 = r14 - r7
                int r10 = r15 - r8
                android.support.p002v7.widget.RecyclerView.SmoothScroller.access$800(r4, r9, r10)
                goto L_0x00ab
            L_0x009f:
                int r9 = r14 - r7
                int r10 = r15 - r8
                android.support.p002v7.widget.RecyclerView.SmoothScroller.access$800(r4, r9, r10)
                goto L_0x00ab
            L_0x00a7:
                r5 = r2
                r6 = r5
                r7 = r6
                r8 = r7
            L_0x00ab:
                android.support.v7.widget.RecyclerView r9 = android.support.p002v7.widget.RecyclerView.this
                java.util.ArrayList<android.support.v7.widget.RecyclerView$ItemDecoration> r9 = r9.mItemDecorations
                boolean r9 = r9.isEmpty()
                if (r9 != 0) goto L_0x00ba
                android.support.v7.widget.RecyclerView r9 = android.support.p002v7.widget.RecyclerView.this
                r9.invalidate()
            L_0x00ba:
                android.support.v7.widget.RecyclerView r9 = android.support.p002v7.widget.RecyclerView.this
                int r9 = r9.getOverScrollMode()
                r10 = 2
                if (r9 == r10) goto L_0x00c8
                android.support.v7.widget.RecyclerView r9 = android.support.p002v7.widget.RecyclerView.this
                r9.considerReleasingGlowsOnScroll(r14, r15)
            L_0x00c8:
                android.support.v7.widget.RecyclerView r9 = android.support.p002v7.widget.RecyclerView.this
                r21 = 0
                r22 = 1
                r16 = r9
                r17 = r5
                r18 = r6
                r19 = r7
                r20 = r8
                boolean r9 = r16.dispatchNestedScroll(r17, r18, r19, r20, r21, r22)
                if (r9 != 0) goto L_0x0120
                if (r7 != 0) goto L_0x00e2
                if (r8 == 0) goto L_0x0120
            L_0x00e2:
                float r9 = r1.getCurrVelocity()
                int r9 = (int) r9
                if (r7 == r12) goto L_0x00f1
                if (r7 >= 0) goto L_0x00ed
                int r11 = -r9
                goto L_0x00f2
            L_0x00ed:
                if (r7 <= 0) goto L_0x00f1
                r11 = r9
                goto L_0x00f2
            L_0x00f1:
                r11 = r2
            L_0x00f2:
                if (r8 == r13) goto L_0x00fb
                if (r8 >= 0) goto L_0x00f8
                int r9 = -r9
                goto L_0x00fc
            L_0x00f8:
                if (r8 <= 0) goto L_0x00fb
                goto L_0x00fc
            L_0x00fb:
                r9 = r2
            L_0x00fc:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                int r2 = r2.getOverScrollMode()
                if (r2 == r10) goto L_0x0109
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                r2.absorbGlows(r11, r9)
            L_0x0109:
                if (r11 != 0) goto L_0x0113
                if (r7 == r12) goto L_0x0113
                int r2 = r1.getFinalX()
                if (r2 != 0) goto L_0x0120
            L_0x0113:
                if (r9 != 0) goto L_0x011d
                if (r8 == r13) goto L_0x011d
                int r2 = r1.getFinalY()
                if (r2 != 0) goto L_0x0120
            L_0x011d:
                r1.abortAnimation()
            L_0x0120:
                if (r5 != 0) goto L_0x0124
                if (r6 == 0) goto L_0x0129
            L_0x0124:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                r2.dispatchOnScrolled(r5, r6)
            L_0x0129:
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                boolean r2 = r2.awakenScrollBars()
                if (r2 != 0) goto L_0x0136
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                r2.invalidate()
            L_0x0136:
                if (r15 == 0) goto L_0x0146
                android.support.v7.widget.RecyclerView r2 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$LayoutManager r2 = r2.mLayout
                boolean r2 = r2.canScrollVertically()
                if (r2 == 0) goto L_0x0146
                if (r6 != r15) goto L_0x0146
                r2 = r3
                goto L_0x0147
            L_0x0146:
                r2 = 0
            L_0x0147:
                if (r14 == 0) goto L_0x0157
                android.support.v7.widget.RecyclerView r6 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.RecyclerView$LayoutManager r6 = r6.mLayout
                boolean r6 = r6.canScrollHorizontally()
                if (r6 == 0) goto L_0x0157
                if (r5 != r14) goto L_0x0157
                r5 = r3
                goto L_0x0158
            L_0x0157:
                r5 = 0
            L_0x0158:
                if (r14 != 0) goto L_0x015c
                if (r15 == 0) goto L_0x0163
            L_0x015c:
                if (r5 != 0) goto L_0x0163
                if (r2 == 0) goto L_0x0161
                goto L_0x0163
            L_0x0161:
                r2 = 0
                goto L_0x0164
            L_0x0163:
                r2 = r3
            L_0x0164:
                boolean r1 = r1.isFinished()
                if (r1 != 0) goto L_0x0182
                if (r2 != 0) goto L_0x0175
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                boolean r1 = r1.hasNestedScrollingParent(r3)
                if (r1 != 0) goto L_0x0175
                goto L_0x0182
            L_0x0175:
                r23.postOnAnimation()
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.GapWorker r2 = r1.mGapWorker
                if (r2 == 0) goto L_0x01a2
                r2.postFromTraversal(r1, r14, r15)
                goto L_0x01a2
            L_0x0182:
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                r2 = 0
                r1.setScrollState(r2)
                boolean r1 = android.support.p002v7.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
                if (r1 == 0) goto L_0x019d
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                android.support.v7.widget.GapWorker$LayoutPrefetchRegistryImpl r1 = r1.mPrefetchRegistry
                int[] r2 = r1.mPrefetchArray
                if (r2 == 0) goto L_0x019a
                r5 = -1
                java.util.Arrays.fill(r2, r5)
            L_0x019a:
                r2 = 0
                r1.mCount = r2
            L_0x019d:
                android.support.v7.widget.RecyclerView r1 = android.support.p002v7.widget.RecyclerView.this
                r1.stopNestedScroll(r3)
            L_0x01a2:
                if (r4 == 0) goto L_0x01b8
                boolean r1 = r4.isPendingInitialRun()
                if (r1 == 0) goto L_0x01af
                r1 = 0
                android.support.p002v7.widget.RecyclerView.SmoothScroller.access$800(r4, r1, r1)
                goto L_0x01b0
            L_0x01af:
                r1 = 0
            L_0x01b0:
                boolean r2 = r0.mReSchedulePostAnimationCallback
                if (r2 != 0) goto L_0x01b9
                r4.stop()
                goto L_0x01b9
            L_0x01b8:
                r1 = 0
            L_0x01b9:
                r0.mEatRunOnAnimationRequest = r1
                boolean r1 = r0.mReSchedulePostAnimationCallback
                if (r1 == 0) goto L_0x01c2
                r23.postOnAnimation()
            L_0x01c2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.ViewFlinger.run():void");
        }

        public void smoothScrollBy(int i, int i2) {
            smoothScrollBy(i, i2, computeScrollDuration(i, i2, 0, 0));
        }

        public void stop() {
            RecyclerView.this.removeCallbacks(this);
            this.mScroller.abortAnimation();
        }

        public void smoothScrollBy(int i, int i2, int i3) {
            smoothScrollBy(i, i2, i3, RecyclerView.sQuinticInterpolator);
        }

        public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
            int computeScrollDuration = computeScrollDuration(i, i2, 0, 0);
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            smoothScrollBy(i, i2, computeScrollDuration, interpolator);
        }

        public void smoothScrollBy(int i, int i2, int i3, Interpolator interpolator) {
            if (this.mInterpolator != interpolator) {
                this.mInterpolator = interpolator;
                this.mScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.startScroll(0, 0, i, i2, i3);
            int i4 = Build.VERSION.SDK_INT;
            postOnAnimation();
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$ViewHolder */
    public static abstract class ViewHolder {
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        public final View itemView;
        /* access modifiers changed from: private */
        public int mFlags;
        /* access modifiers changed from: private */
        public boolean mInChangeScrap = false;
        private int mIsRecyclableCount = 0;
        long mItemId = -1;
        int mItemViewType = -1;
        WeakReference<RecyclerView> mNestedRecyclerView;
        int mOldPosition = -1;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads = null;
        int mPendingAccessibilityState = -1;
        int mPosition = -1;
        int mPreLayoutPosition = -1;
        /* access modifiers changed from: private */
        public Recycler mScrapContainer = null;
        ViewHolder mShadowedHolder = null;
        ViewHolder mShadowingHolder = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mWasImportantForAccessibilityBeforeHidden = 0;

        public ViewHolder(View view) {
            if (view != null) {
                this.itemView = view;
                return;
            }
            throw new IllegalArgumentException("itemView may not be null");
        }

        static /* synthetic */ boolean access$1100(ViewHolder viewHolder) {
            return (viewHolder.mFlags & 16) == 0 && ViewCompat.hasTransientState(viewHolder.itemView);
        }

        static /* synthetic */ boolean access$1700(ViewHolder viewHolder) {
            return (viewHolder.mFlags & 16) != 0;
        }

        static /* synthetic */ void access$200(ViewHolder viewHolder, RecyclerView recyclerView) {
            int i = viewHolder.mPendingAccessibilityState;
            if (i != -1) {
                viewHolder.mWasImportantForAccessibilityBeforeHidden = i;
            } else {
                viewHolder.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(viewHolder.itemView);
            }
            recyclerView.setChildImportantForAccessibilityInternal(viewHolder, 4);
        }

        static /* synthetic */ void access$300(ViewHolder viewHolder, RecyclerView recyclerView) {
            recyclerView.setChildImportantForAccessibilityInternal(viewHolder, viewHolder.mWasImportantForAccessibilityBeforeHidden);
            viewHolder.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        /* access modifiers changed from: package-private */
        public void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                if (this.mPayloads == null) {
                    this.mPayloads = new ArrayList();
                    this.mUnmodifiedPayloads = Collections.unmodifiableList(this.mPayloads);
                }
                this.mPayloads.add(obj);
            }
        }

        /* access modifiers changed from: package-private */
        public void addFlags(int i) {
            this.mFlags = i | this.mFlags;
        }

        /* access modifiers changed from: package-private */
        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        /* access modifiers changed from: package-private */
        public void clearPayload() {
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        /* access modifiers changed from: package-private */
        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        /* access modifiers changed from: package-private */
        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        /* access modifiers changed from: package-private */
        public void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
            addFlags(8);
            offsetPosition(i2, z);
            this.mPosition = i;
        }

        public final int getAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.getAdapterPositionFor(this);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int i = this.mPreLayoutPosition;
            return i == -1 ? this.mPosition : i;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        /* access modifiers changed from: package-private */
        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            List<Object> list = this.mPayloads;
            if (list == null || list.size() == 0) {
                return FULLUPDATE_PAYLOADS;
            }
            return this.mUnmodifiedPayloads;
        }

        /* access modifiers changed from: package-private */
        public boolean hasAnyOfTheFlags(int i) {
            return (this.mFlags & i) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isAdapterPositionUnknown() {
            return (this.mFlags & 512) != 0 || isInvalid();
        }

        /* access modifiers changed from: package-private */
        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView);
        }

        /* access modifiers changed from: package-private */
        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isScrap() {
            return this.mScrapContainer != null;
        }

        /* access modifiers changed from: package-private */
        public boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        /* access modifiers changed from: package-private */
        public boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        /* access modifiers changed from: package-private */
        public void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        /* access modifiers changed from: package-private */
        public void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        /* access modifiers changed from: package-private */
        public void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        /* access modifiers changed from: package-private */
        public void setFlags(int i, int i2) {
            this.mFlags = (i & i2) | (this.mFlags & (~i2));
        }

        public final void setIsRecyclable(boolean z) {
            int i = this.mIsRecyclableCount;
            this.mIsRecyclableCount = z ? i - 1 : i + 1;
            int i2 = this.mIsRecyclableCount;
            if (i2 < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && i2 == 1) {
                this.mFlags |= 16;
            } else if (z && this.mIsRecyclableCount == 0) {
                this.mFlags &= -17;
            }
        }

        /* access modifiers changed from: package-private */
        public void setScrapContainer(Recycler recycler, boolean z) {
            this.mScrapContainer = recycler;
            this.mInChangeScrap = z;
        }

        /* access modifiers changed from: package-private */
        public boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("ViewHolder{");
            outline13.append(Integer.toHexString(hashCode()));
            outline13.append(" position=");
            outline13.append(this.mPosition);
            outline13.append(" id=");
            outline13.append(this.mItemId);
            outline13.append(", oldPos=");
            outline13.append(this.mOldPosition);
            outline13.append(", pLpos:");
            outline13.append(this.mPreLayoutPosition);
            StringBuilder sb = new StringBuilder(outline13.toString());
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13(" not recyclable(");
                outline132.append(this.mIsRecyclableCount);
                outline132.append(")");
                sb.append(outline132.toString());
            }
            if (isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        /* access modifiers changed from: package-private */
        public void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        /* access modifiers changed from: package-private */
        public boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Class<?>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            r0 = 1
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 16843830(0x1010436, float:2.369658E-38)
            r1[r2] = r3
            NESTED_SCROLLING_ATTRS = r1
            int[] r1 = new int[r0]
            r3 = 16842987(0x10100eb, float:2.3694217E-38)
            r1[r2] = r3
            CLIP_TO_PADDING_ATTR = r1
            int r1 = android.os.Build.VERSION.SDK_INT
            FORCE_INVALIDATE_DISPLAY_LIST = r2
            ALLOW_SIZE_IN_UNSPECIFIED_SPEC = r0
            POST_UPDATES_ON_ANIMATION = r0
            ALLOW_THREAD_GAP_WORK = r0
            FORCE_ABS_FOCUS_SEARCH_DIRECTION = r2
            IGNORE_DETACHED_FOCUSED_CHILD = r2
            r1 = 4
            java.lang.Class[] r1 = new java.lang.Class[r1]
            java.lang.Class<android.content.Context> r3 = android.content.Context.class
            r1[r2] = r3
            java.lang.Class<android.util.AttributeSet> r2 = android.util.AttributeSet.class
            r1[r0] = r2
            r0 = 2
            java.lang.Class r2 = java.lang.Integer.TYPE
            r1[r0] = r2
            r0 = 3
            r1[r0] = r2
            LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = r1
            android.support.v7.widget.RecyclerView$3 r0 = new android.support.v7.widget.RecyclerView$3
            r0.<init>()
            sQuinticInterpolator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.<clinit>():void");
    }

    public RecyclerView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    private void addAnimatingView(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean z = view.getParent() == this;
        this.mRecycler.unscrapView(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        } else if (!z) {
            this.mChildHelper.addView(view, -1, true);
        } else {
            ChildHelper childHelper = this.mChildHelper;
            int indexOfChild = RecyclerView.this.indexOfChild(view);
            if (indexOfChild >= 0) {
                childHelper.mBucket.set(indexOfChild);
                childHelper.mHiddenViews.add(view);
                ((C02195) childHelper.mCallback).onEnteredHiddenState(view);
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("view is not a child, cannot hide ", view));
        }
    }

    private void cancelTouch() {
        resetTouch();
        setScrollState(0);
    }

    static void clearNestedRecyclerViewIfNotNested(ViewHolder viewHolder) {
        WeakReference<RecyclerView> weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view != viewHolder.itemView) {
                    ViewParent parent = view.getParent();
                    view = parent instanceof View ? (View) parent : null;
                } else {
                    return;
                }
            }
            viewHolder.mNestedRecyclerView = null;
        }
    }

    private void dispatchLayoutStep1() {
        int i;
        boolean z = true;
        this.mState.assertLayoutStep(1);
        fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        ViewHolder viewHolder = null;
        View focusedChild = (!this.mPreserveFocusAfterLayout || !hasFocus() || this.mAdapter == null) ? null : getFocusedChild();
        if (focusedChild != null) {
            viewHolder = findContainingViewHolder(focusedChild);
        }
        if (viewHolder == null) {
            resetFocusInfo();
        } else {
            this.mState.mFocusedItemId = this.mAdapter.hasStableIds() ? viewHolder.getItemId() : -1;
            State state = this.mState;
            if (this.mDataSetHasChangedAfterLayout) {
                i = -1;
            } else if (viewHolder.isRemoved()) {
                i = viewHolder.mOldPosition;
            } else {
                i = viewHolder.getAdapterPosition();
            }
            state.mFocusedItemPosition = i;
            State state2 = this.mState;
            View view = viewHolder.itemView;
            int id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    id = view.getId();
                }
            }
            state2.mFocusedSubChildId = id;
        }
        State state3 = this.mState;
        if (!state3.mRunSimpleAnimations || !this.mItemsChanged) {
            z = false;
        }
        state3.mTrackOldChangeHolders = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        State state4 = this.mState;
        state4.mInPreLayout = state4.mRunPredictiveAnimations;
        state4.mItemCount = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            int childCount = this.mChildHelper.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i2));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.addToPreLayout(childViewHolderInt, this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt, ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            saveOldPositions();
            State state5 = this.mState;
            boolean z2 = state5.mStructureChanged;
            state5.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, state5);
            this.mState.mStructureChanged = z2;
            for (int i3 = 0; i3 < this.mChildHelper.getChildCount(); i3++) {
                ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(childViewHolderInt2)) {
                    int buildAdapterChangeFlagsForAnimations = ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt2);
                    boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                    if (!hasAnyOfTheFlags) {
                        buildAdapterChangeFlagsForAnimations |= 4096;
                    }
                    ItemAnimator.ItemHolderInfo recordPreLayoutInformation = this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt2, buildAdapterChangeFlagsForAnimations, childViewHolderInt2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, recordPreLayoutInformation);
                    } else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(childViewHolderInt2, recordPreLayoutInformation);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    private void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        State state = this.mState;
        state.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        state.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, state);
        State state2 = this.mState;
        state2.mStructureChanged = false;
        this.mPendingSavedState = null;
        state2.mRunSimpleAnimations = state2.mRunSimpleAnimations && this.mItemAnimator != null;
        this.mState.mLayoutStep = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i2) {
                    i2 = layoutPosition;
                }
                if (layoutPosition > i) {
                    i = layoutPosition;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i;
    }

    static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerView findNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i));
            if (findNestedRecyclerView != null) {
                return findNestedRecyclerView;
            }
        }
        return null;
    }

    static ViewHolder getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).mViewHolder;
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect2 = layoutParams.mDecorInsets;
        rect.set((view.getLeft() - rect2.left) - layoutParams.leftMargin, (view.getTop() - rect2.top) - layoutParams.topMargin, view.getRight() + rect2.right + layoutParams.rightMargin, view.getBottom() + rect2.bottom + layoutParams.bottomMargin);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007e, code lost:
        if ((r5.mItemAnimator != null && r5.mLayout.supportsPredictiveItemAnimations()) != false) goto L_0x0082;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processAdapterUpdatesAndSetAnimationFlags() {
        /*
            r5 = this;
            boolean r0 = r5.mDataSetHasChangedAfterLayout
            if (r0 == 0) goto L_0x0012
            android.support.v7.widget.AdapterHelper r0 = r5.mAdapterHelper
            r0.reset()
            boolean r0 = r5.mDispatchItemsChangedEvent
            if (r0 == 0) goto L_0x0012
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r5.mLayout
            r0.onItemsChanged(r5)
        L_0x0012:
            android.support.v7.widget.RecyclerView$ItemAnimator r0 = r5.mItemAnimator
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0022
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r5.mLayout
            boolean r0 = r0.supportsPredictiveItemAnimations()
            if (r0 == 0) goto L_0x0022
            r0 = r1
            goto L_0x0023
        L_0x0022:
            r0 = r2
        L_0x0023:
            if (r0 == 0) goto L_0x002b
            android.support.v7.widget.AdapterHelper r0 = r5.mAdapterHelper
            r0.preProcess()
            goto L_0x0030
        L_0x002b:
            android.support.v7.widget.AdapterHelper r0 = r5.mAdapterHelper
            r0.consumeUpdatesInOnePass()
        L_0x0030:
            boolean r0 = r5.mItemsAddedOrRemoved
            if (r0 != 0) goto L_0x003b
            boolean r0 = r5.mItemsChanged
            if (r0 == 0) goto L_0x0039
            goto L_0x003b
        L_0x0039:
            r0 = r2
            goto L_0x003c
        L_0x003b:
            r0 = r1
        L_0x003c:
            android.support.v7.widget.RecyclerView$State r3 = r5.mState
            boolean r4 = r5.mFirstLayoutComplete
            if (r4 == 0) goto L_0x0060
            android.support.v7.widget.RecyclerView$ItemAnimator r4 = r5.mItemAnimator
            if (r4 == 0) goto L_0x0060
            boolean r4 = r5.mDataSetHasChangedAfterLayout
            if (r4 != 0) goto L_0x0052
            if (r0 != 0) goto L_0x0052
            android.support.v7.widget.RecyclerView$LayoutManager r4 = r5.mLayout
            boolean r4 = r4.mRequestedSimpleAnimations
            if (r4 == 0) goto L_0x0060
        L_0x0052:
            boolean r4 = r5.mDataSetHasChangedAfterLayout
            if (r4 == 0) goto L_0x005e
            android.support.v7.widget.RecyclerView$Adapter r4 = r5.mAdapter
            boolean r4 = r4.hasStableIds()
            if (r4 == 0) goto L_0x0060
        L_0x005e:
            r4 = r1
            goto L_0x0061
        L_0x0060:
            r4 = r2
        L_0x0061:
            r3.mRunSimpleAnimations = r4
            android.support.v7.widget.RecyclerView$State r3 = r5.mState
            boolean r4 = r3.mRunSimpleAnimations
            if (r4 == 0) goto L_0x0081
            if (r0 == 0) goto L_0x0081
            boolean r0 = r5.mDataSetHasChangedAfterLayout
            if (r0 != 0) goto L_0x0081
            android.support.v7.widget.RecyclerView$ItemAnimator r0 = r5.mItemAnimator
            if (r0 == 0) goto L_0x007d
            android.support.v7.widget.RecyclerView$LayoutManager r5 = r5.mLayout
            boolean r5 = r5.supportsPredictiveItemAnimations()
            if (r5 == 0) goto L_0x007d
            r5 = r1
            goto L_0x007e
        L_0x007d:
            r5 = r2
        L_0x007e:
            if (r5 == 0) goto L_0x0081
            goto L_0x0082
        L_0x0081:
            r1 = r2
        L_0x0082:
            r3.mRunPredictiveAnimations = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.processAdapterUpdatesAndSetAnimationFlags():void");
    }

    private void requestChildOnScreen(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.mInsetsDirty) {
                Rect rect = layoutParams2.mDecorInsets;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.requestChildRectangleOnScreen(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    private void resetFocusInfo() {
        State state = this.mState;
        state.mFocusedItemId = -1;
        state.mFocusedItemPosition = -1;
        state.mFocusedSubChildId = -1;
    }

    private void resetTouch() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean z = false;
        stopNestedScroll(0);
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: private */
    public void scrollStep(int i, int i2, int[] iArr) {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        int i3 = Build.VERSION.SDK_INT;
        Trace.beginSection("RV Scroll");
        fillRemainingScrollValues(this.mState);
        int scrollHorizontallyBy = i != 0 ? this.mLayout.scrollHorizontallyBy(i, this.mRecycler, this.mState) : 0;
        int scrollVerticallyBy = i2 != 0 ? this.mLayout.scrollVerticallyBy(i2, this.mRecycler, this.mState) : 0;
        int i4 = Build.VERSION.SDK_INT;
        Trace.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = scrollHorizontallyBy;
            iArr[1] = scrollVerticallyBy;
        }
    }

    /* access modifiers changed from: package-private */
    public void absorbGlows(int i, int i2) {
        if (i < 0) {
            ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-i);
        } else if (i > 0) {
            ensureRightGlow();
            this.mRightGlow.onAbsorb(i);
        }
        if (i2 < 0) {
            ensureTopGlow();
            this.mTopGlow.onAbsorb(-i2);
        } else if (i2 > 0) {
            ensureBottomGlow();
            this.mBottomGlow.onAbsorb(i2);
        }
        if (i != 0 || i2 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || !layoutManager.onAddFocusables(this, arrayList, i, i2)) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    public void addItemDecoration(ItemDecoration itemDecoration, int i) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i < 0) {
            this.mItemDecorations.add(itemDecoration);
        } else {
            this.mItemDecorations.add(i, itemDecoration);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
    }

    public void addOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }

    public void addOnScrollListener(OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(onScrollListener);
    }

    /* access modifiers changed from: package-private */
    public void animateAppearance(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
        }
    }

    /* access modifiers changed from: package-private */
    public void animateDisappearance(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        addAnimatingView(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            postAnimationRunner();
        }
    }

    /* access modifiers changed from: package-private */
    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Cannot call this method while RecyclerView is computing a layout or scrolling");
                outline13.append(exceptionLabel());
                throw new IllegalStateException(outline13.toString());
            }
            throw new IllegalStateException(str);
        } else if (this.mDispatchScrollCounter > 0) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("");
            outline132.append(exceptionLabel());
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(outline132.toString()));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder) {
        ItemAnimator itemAnimator = this.mItemAnimator;
        return itemAnimator == null || itemAnimator.canReuseUpdatedViewHolder(viewHolder, viewHolder.getUnmodifiedPayloads());
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.mLayout.checkLayoutParams((LayoutParams) layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void clearOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            recycler.mCachedViews.get(i2).clearOldPosition();
        }
        int size2 = recycler.mAttachedScrap.size();
        for (int i3 = 0; i3 < size2; i3++) {
            recycler.mAttachedScrap.get(i3).clearOldPosition();
        }
        ArrayList<ViewHolder> arrayList = recycler.mChangedScrap;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i4 = 0; i4 < size3; i4++) {
                recycler.mChangedScrap.get(i4).clearOldPosition();
            }
        }
    }

    public int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }

    public int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void considerReleasingGlowsOnScroll(int i, int i2) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            z = false;
        } else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            int i = Build.VERSION.SDK_INT;
            Trace.beginSection("RV FullInvalidate");
            dispatchLayout();
            int i2 = Build.VERSION.SDK_INT;
            Trace.endSection();
        } else if (this.mAdapterHelper.hasPendingUpdates()) {
            if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                int i3 = Build.VERSION.SDK_INT;
                Trace.beginSection("RV PartialInvalidate");
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                this.mAdapterHelper.preProcess();
                if (!this.mLayoutWasDefered) {
                    int childCount = this.mChildHelper.getChildCount();
                    boolean z = false;
                    int i4 = 0;
                    while (true) {
                        if (i4 < childCount) {
                            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i4));
                            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                                z = true;
                                break;
                            }
                            i4++;
                        } else {
                            break;
                        }
                    }
                    if (z) {
                        dispatchLayout();
                    } else {
                        this.mAdapterHelper.consumePostponedUpdates();
                    }
                }
                stopInterceptRequestLayout(true);
                onExitLayoutOrScroll();
                int i5 = Build.VERSION.SDK_INT;
                Trace.endSection();
            } else if (this.mAdapterHelper.hasPendingUpdates()) {
                int i6 = Build.VERSION.SDK_INT;
                Trace.beginSection("RV FullInvalidate");
                dispatchLayout();
                int i7 = Build.VERSION.SDK_INT;
                Trace.endSection();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void defaultOnMeasure(int i, int i2) {
        setMeasuredDimension(LayoutManager.chooseSize(i, getPaddingRight() + getPaddingLeft(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(i2, getPaddingBottom() + getPaddingTop(), ViewCompat.getMinimumHeight(this)));
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildAttached(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        Adapter adapter = this.mAdapter;
        if (!(adapter == null || childViewHolderInt == null)) {
            adapter.onViewAttachedToWindow(childViewHolderInt);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((ItemTouchHelper) this.mOnChildAttachStateListeners.get(size)).onChildViewAttachedToWindow(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildDetached(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        Adapter adapter = this.mAdapter;
        if (!(adapter == null || childViewHolderInt == null)) {
            adapter.onViewDetachedFromWindow(childViewHolderInt);
        }
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((ItemTouchHelper) this.mOnChildAttachStateListeners.get(size)).onChildViewDetachedFromWindow(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0261, code lost:
        if (r13.mChildHelper.isHidden(r0) == false) goto L_0x0302;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02c5, code lost:
        r1 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchLayout() {
        /*
            r13 = this;
            android.support.v7.widget.RecyclerView$Adapter r0 = r13.mAdapter
            java.lang.String r1 = "RecyclerView"
            if (r0 != 0) goto L_0x000c
            java.lang.String r13 = "No adapter attached; skipping layout"
            android.util.Log.e(r1, r13)
            return
        L_0x000c:
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            if (r0 != 0) goto L_0x0016
            java.lang.String r13 = "No layout manager attached; skipping layout"
            android.util.Log.e(r1, r13)
            return
        L_0x0016:
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            r2 = 0
            r0.mIsMeasuring = r2
            int r0 = r0.mLayoutStep
            r3 = 1
            if (r0 != r3) goto L_0x002c
            r13.dispatchLayoutStep1()
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            r0.setExactMeasureSpecsFrom(r13)
            r13.dispatchLayoutStep2()
            goto L_0x006a
        L_0x002c:
            android.support.v7.widget.AdapterHelper r0 = r13.mAdapterHelper
            java.util.ArrayList<android.support.v7.widget.AdapterHelper$UpdateOp> r4 = r0.mPostponedList
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0040
            java.util.ArrayList<android.support.v7.widget.AdapterHelper$UpdateOp> r0 = r0.mPendingUpdates
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0040
            r0 = r3
            goto L_0x0041
        L_0x0040:
            r0 = r2
        L_0x0041:
            if (r0 != 0) goto L_0x0062
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            int r0 = r0.getWidth()
            int r4 = r13.getWidth()
            if (r0 != r4) goto L_0x0062
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            int r0 = r0.getHeight()
            int r4 = r13.getHeight()
            if (r0 == r4) goto L_0x005c
            goto L_0x0062
        L_0x005c:
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            r0.setExactMeasureSpecsFrom(r13)
            goto L_0x006a
        L_0x0062:
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            r0.setExactMeasureSpecsFrom(r13)
            r13.dispatchLayoutStep2()
        L_0x006a:
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            r4 = 4
            r0.assertLayoutStep(r4)
            r13.startInterceptRequestLayout()
            r13.onEnterLayoutOrScroll()
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            r0.mLayoutStep = r3
            boolean r0 = r0.mRunSimpleAnimations
            if (r0 == 0) goto L_0x01b1
            android.support.v7.widget.ChildHelper r0 = r13.mChildHelper
            int r0 = r0.getChildCount()
            int r0 = r0 - r3
        L_0x0085:
            if (r0 < 0) goto L_0x01aa
            android.support.v7.widget.ChildHelper r4 = r13.mChildHelper
            android.view.View r4 = r4.getChildAt(r0)
            android.support.v7.widget.RecyclerView$ViewHolder r4 = getChildViewHolderInt(r4)
            boolean r5 = r4.shouldIgnore()
            if (r5 == 0) goto L_0x0099
            goto L_0x01a6
        L_0x0099:
            long r5 = r13.getChangedHolderKey(r4)
            android.support.v7.widget.RecyclerView$ItemAnimator r7 = r13.mItemAnimator
            android.support.v7.widget.RecyclerView$ItemAnimator$ItemHolderInfo r7 = r7.obtainHolderInfo()
            r7.setFrom(r4)
            android.support.v7.widget.ViewInfoStore r8 = r13.mViewInfoStore
            android.support.v7.widget.RecyclerView$ViewHolder r8 = r8.getFromOldChangeHolders(r5)
            if (r8 == 0) goto L_0x01a1
            boolean r9 = r8.shouldIgnore()
            if (r9 != 0) goto L_0x01a1
            android.support.v7.widget.ViewInfoStore r9 = r13.mViewInfoStore
            boolean r9 = r9.isDisappearing(r8)
            android.support.v7.widget.ViewInfoStore r10 = r13.mViewInfoStore
            boolean r10 = r10.isDisappearing(r4)
            if (r9 == 0) goto L_0x00cb
            if (r8 != r4) goto L_0x00cb
            android.support.v7.widget.ViewInfoStore r5 = r13.mViewInfoStore
            r5.addToPostLayout(r4, r7)
            goto L_0x01a6
        L_0x00cb:
            android.support.v7.widget.ViewInfoStore r11 = r13.mViewInfoStore
            android.support.v7.widget.RecyclerView$ItemAnimator$ItemHolderInfo r11 = r11.popFromPreLayout(r8)
            android.support.v7.widget.ViewInfoStore r12 = r13.mViewInfoStore
            r12.addToPostLayout(r4, r7)
            android.support.v7.widget.ViewInfoStore r7 = r13.mViewInfoStore
            android.support.v7.widget.RecyclerView$ItemAnimator$ItemHolderInfo r7 = r7.popFromPostLayout(r4)
            if (r11 != 0) goto L_0x0177
            android.support.v7.widget.ChildHelper r7 = r13.mChildHelper
            int r7 = r7.getChildCount()
            r9 = r2
        L_0x00e5:
            if (r9 >= r7) goto L_0x0153
            android.support.v7.widget.ChildHelper r10 = r13.mChildHelper
            android.view.View r10 = r10.getChildAt(r9)
            android.support.v7.widget.RecyclerView$ViewHolder r10 = getChildViewHolderInt(r10)
            if (r10 != r4) goto L_0x00f4
            goto L_0x0150
        L_0x00f4:
            long r11 = r13.getChangedHolderKey(r10)
            int r11 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r11 != 0) goto L_0x0150
            android.support.v7.widget.RecyclerView$Adapter r0 = r13.mAdapter
            java.lang.String r1 = " \n View Holder 2:"
            if (r0 == 0) goto L_0x012c
            boolean r0 = r0.hasStableIds()
            if (r0 == 0) goto L_0x012c
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:"
            r2.append(r3)
            r2.append(r10)
            r2.append(r1)
            r2.append(r4)
            java.lang.String r13 = r13.exceptionLabel()
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r0.<init>(r13)
            throw r0
        L_0x012c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:"
            r2.append(r3)
            r2.append(r10)
            r2.append(r1)
            r2.append(r4)
            java.lang.String r13 = r13.exceptionLabel()
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r0.<init>(r13)
            throw r0
        L_0x0150:
            int r9 = r9 + 1
            goto L_0x00e5
        L_0x0153:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Problem while matching changed view holders with the newones. The pre-layout information for the change holder "
            r5.append(r6)
            r5.append(r8)
            java.lang.String r6 = " cannot be found but it is necessary for "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r13.exceptionLabel()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.e(r1, r4)
            goto L_0x01a6
        L_0x0177:
            r8.setIsRecyclable(r2)
            if (r9 == 0) goto L_0x017f
            r13.addAnimatingView(r8)
        L_0x017f:
            if (r8 == r4) goto L_0x0195
            if (r10 == 0) goto L_0x0186
            r13.addAnimatingView(r4)
        L_0x0186:
            r8.mShadowedHolder = r4
            r13.addAnimatingView(r8)
            android.support.v7.widget.RecyclerView$Recycler r5 = r13.mRecycler
            r5.unscrapView(r8)
            r4.setIsRecyclable(r2)
            r4.mShadowingHolder = r8
        L_0x0195:
            android.support.v7.widget.RecyclerView$ItemAnimator r5 = r13.mItemAnimator
            boolean r4 = r5.animateChange(r8, r4, r11, r7)
            if (r4 == 0) goto L_0x01a6
            r13.postAnimationRunner()
            goto L_0x01a6
        L_0x01a1:
            android.support.v7.widget.ViewInfoStore r5 = r13.mViewInfoStore
            r5.addToPostLayout(r4, r7)
        L_0x01a6:
            int r0 = r0 + -1
            goto L_0x0085
        L_0x01aa:
            android.support.v7.widget.ViewInfoStore r0 = r13.mViewInfoStore
            android.support.v7.widget.ViewInfoStore$ProcessCallback r1 = r13.mViewInfoProcessCallback
            r0.process(r1)
        L_0x01b1:
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            android.support.v7.widget.RecyclerView$Recycler r1 = r13.mRecycler
            r0.removeAndRecycleScrapInt(r1)
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            int r1 = r0.mItemCount
            r0.mPreviousLayoutItemCount = r1
            r13.mDataSetHasChangedAfterLayout = r2
            r13.mDispatchItemsChangedEvent = r2
            r0.mRunSimpleAnimations = r2
            r0.mRunPredictiveAnimations = r2
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            r0.mRequestedSimpleAnimations = r2
            android.support.v7.widget.RecyclerView$Recycler r0 = r13.mRecycler
            java.util.ArrayList<android.support.v7.widget.RecyclerView$ViewHolder> r0 = r0.mChangedScrap
            if (r0 == 0) goto L_0x01d3
            r0.clear()
        L_0x01d3:
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            boolean r1 = r0.mPrefetchMaxObservedInInitialPrefetch
            if (r1 == 0) goto L_0x01e2
            r0.mPrefetchMaxCountObserved = r2
            r0.mPrefetchMaxObservedInInitialPrefetch = r2
            android.support.v7.widget.RecyclerView$Recycler r0 = r13.mRecycler
            r0.updateViewCacheSize()
        L_0x01e2:
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r13.mLayout
            android.support.v7.widget.RecyclerView$State r1 = r13.mState
            r0.onLayoutCompleted(r1)
            r13.onExitLayoutOrScroll()
            r13.stopInterceptRequestLayout(r2)
            android.support.v7.widget.ViewInfoStore r0 = r13.mViewInfoStore
            r0.clear()
            int[] r0 = r13.mMinMaxLayoutPositions
            r1 = r0[r2]
            r4 = r0[r3]
            r13.findMinMaxChildLayoutPositions(r0)
            int[] r0 = r13.mMinMaxLayoutPositions
            r5 = r0[r2]
            if (r5 != r1) goto L_0x0209
            r0 = r0[r3]
            if (r0 == r4) goto L_0x0208
            goto L_0x0209
        L_0x0208:
            r3 = r2
        L_0x0209:
            if (r3 == 0) goto L_0x020e
            r13.dispatchOnScrolled(r2, r2)
        L_0x020e:
            boolean r0 = r13.mPreserveFocusAfterLayout
            if (r0 == 0) goto L_0x0302
            android.support.v7.widget.RecyclerView$Adapter r0 = r13.mAdapter
            if (r0 == 0) goto L_0x0302
            boolean r0 = r13.hasFocus()
            if (r0 == 0) goto L_0x0302
            int r0 = r13.getDescendantFocusability()
            r1 = 393216(0x60000, float:5.51013E-40)
            if (r0 == r1) goto L_0x0302
            int r0 = r13.getDescendantFocusability()
            r1 = 131072(0x20000, float:1.83671E-40)
            if (r0 != r1) goto L_0x0234
            boolean r0 = r13.isFocused()
            if (r0 == 0) goto L_0x0234
            goto L_0x0302
        L_0x0234:
            boolean r0 = r13.isFocused()
            if (r0 != 0) goto L_0x0265
            android.view.View r0 = r13.getFocusedChild()
            boolean r1 = IGNORE_DETACHED_FOCUSED_CHILD
            if (r1 == 0) goto L_0x025b
            android.view.ViewParent r1 = r0.getParent()
            if (r1 == 0) goto L_0x024e
            boolean r1 = r0.hasFocus()
            if (r1 != 0) goto L_0x025b
        L_0x024e:
            android.support.v7.widget.ChildHelper r0 = r13.mChildHelper
            int r0 = r0.getChildCount()
            if (r0 != 0) goto L_0x0265
            r13.requestFocus()
            goto L_0x0302
        L_0x025b:
            android.support.v7.widget.ChildHelper r1 = r13.mChildHelper
            boolean r0 = r1.isHidden(r0)
            if (r0 != 0) goto L_0x0265
            goto L_0x0302
        L_0x0265:
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            long r0 = r0.mFocusedItemId
            r3 = -1
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            r1 = 0
            if (r0 == 0) goto L_0x0281
            android.support.v7.widget.RecyclerView$Adapter r0 = r13.mAdapter
            boolean r0 = r0.hasStableIds()
            if (r0 == 0) goto L_0x0281
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            long r5 = r0.mFocusedItemId
            android.support.v7.widget.RecyclerView$ViewHolder r0 = r13.findViewHolderForItemId(r5)
            goto L_0x0282
        L_0x0281:
            r0 = r1
        L_0x0282:
            if (r0 == 0) goto L_0x029a
            android.support.v7.widget.ChildHelper r5 = r13.mChildHelper
            android.view.View r6 = r0.itemView
            boolean r5 = r5.isHidden(r6)
            if (r5 != 0) goto L_0x029a
            android.view.View r5 = r0.itemView
            boolean r5 = r5.hasFocusable()
            if (r5 != 0) goto L_0x0297
            goto L_0x029a
        L_0x0297:
            android.view.View r1 = r0.itemView
            goto L_0x02e6
        L_0x029a:
            android.support.v7.widget.ChildHelper r0 = r13.mChildHelper
            int r0 = r0.getChildCount()
            if (r0 <= 0) goto L_0x02e6
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            int r0 = r0.mFocusedItemPosition
            r5 = -1
            if (r0 == r5) goto L_0x02aa
            goto L_0x02ab
        L_0x02aa:
            r0 = r2
        L_0x02ab:
            android.support.v7.widget.RecyclerView$State r2 = r13.mState
            int r2 = r2.getItemCount()
            r6 = r0
        L_0x02b2:
            if (r6 >= r2) goto L_0x02ca
            android.support.v7.widget.RecyclerView$ViewHolder r7 = r13.findViewHolderForAdapterPosition(r6)
            if (r7 != 0) goto L_0x02bb
            goto L_0x02ca
        L_0x02bb:
            android.view.View r8 = r7.itemView
            boolean r8 = r8.hasFocusable()
            if (r8 == 0) goto L_0x02c7
            android.view.View r0 = r7.itemView
        L_0x02c5:
            r1 = r0
            goto L_0x02e6
        L_0x02c7:
            int r6 = r6 + 1
            goto L_0x02b2
        L_0x02ca:
            int r0 = java.lang.Math.min(r2, r0)
            int r0 = r0 + r5
        L_0x02cf:
            if (r0 < 0) goto L_0x02e6
            android.support.v7.widget.RecyclerView$ViewHolder r2 = r13.findViewHolderForAdapterPosition(r0)
            if (r2 != 0) goto L_0x02d8
            goto L_0x02e6
        L_0x02d8:
            android.view.View r5 = r2.itemView
            boolean r5 = r5.hasFocusable()
            if (r5 == 0) goto L_0x02e3
            android.view.View r0 = r2.itemView
            goto L_0x02c5
        L_0x02e3:
            int r0 = r0 + -1
            goto L_0x02cf
        L_0x02e6:
            if (r1 == 0) goto L_0x0302
            android.support.v7.widget.RecyclerView$State r0 = r13.mState
            int r0 = r0.mFocusedSubChildId
            long r5 = (long) r0
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x02fe
            android.view.View r0 = r1.findViewById(r0)
            if (r0 == 0) goto L_0x02fe
            boolean r2 = r0.isFocusable()
            if (r2 == 0) goto L_0x02fe
            goto L_0x02ff
        L_0x02fe:
            r0 = r1
        L_0x02ff:
            r0.requestFocus()
        L_0x0302:
            r13.resetFocusInfo()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.dispatchLayout():void");
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr, 0);
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnScrollStateChanged(int i) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onScrollStateChanged(i);
        }
        onScrollStateChanged(i);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(this, i);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrollStateChanged(this, i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnScrolled(int i, int i2) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        onScrolled(i, i2);
        OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrolled(this, i, i2);
        }
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrolled(this, i, i2);
            }
        }
        this.mDispatchScrollCounter--;
    }

    /* access modifiers changed from: package-private */
    public void dispatchPendingImportantForAccessibilityChanges() {
        int i;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = this.mPendingAccessibilityImportanceChange.get(size);
            if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i = viewHolder.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(viewHolder.itemView, i);
                viewHolder.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public void draw(Canvas canvas) {
        boolean z;
        boolean z2;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z3 = false;
        for (int i = 0; i < size; i++) {
            this.mItemDecorations.get(i).onDrawOver(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) ((-getHeight()) + paddingBottom), 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate((float) (-paddingTop), (float) (-width));
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 == null || edgeEffect7.isFinished()) {
            z2 = z;
        } else {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((float) (getPaddingRight() + (-getWidth())), (float) (getPaddingBottom() + (-getHeight())));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 = z3 | z;
            canvas.restoreToCount(save4);
        }
        if (!z2 && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.isRunning()) {
            z2 = true;
        }
        if (z2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    /* access modifiers changed from: package-private */
    public void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            this.mBottomGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
            if (this.mClipToPadding) {
                this.mBottomGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            this.mLeftGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
            if (this.mClipToPadding) {
                this.mLeftGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureRightGlow() {
        if (this.mRightGlow == null) {
            this.mRightGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
            if (this.mClipToPadding) {
                this.mRightGlow.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureTopGlow() {
        if (this.mTopGlow == null) {
            this.mTopGlow = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
            if (this.mClipToPadding) {
                this.mTopGlow.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String exceptionLabel() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(" ");
        outline13.append(super.toString());
        outline13.append(", adapter:");
        outline13.append(this.mAdapter);
        outline13.append(", layout:");
        outline13.append(this.mLayout);
        outline13.append(", context:");
        outline13.append(getContext());
        return outline13.toString();
    }

    /* access modifiers changed from: package-private */
    public final void fillRemainingScrollValues(State state) {
        if (getScrollState() == 2) {
            OverScroller access$400 = this.mViewFlinger.mScroller;
            access$400.getFinalX();
            access$400.getCurrX();
            access$400.getFinalY();
            access$400.getCurrY();
        }
    }

    public View findChildViewUnder(float f, float f2) {
        for (int childCount = this.mChildHelper.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mChildHelper.getChildAt(childCount);
            float translationX = childAt.getTranslationX();
            float translationY = childAt.getTranslationY();
            if (f >= ((float) childAt.getLeft()) + translationX && f <= ((float) childAt.getRight()) + translationX && f2 >= ((float) childAt.getTop()) + translationY && f2 <= ((float) childAt.getBottom()) + translationY) {
                return childAt;
            }
        }
        return null;
    }

    public View findContainingItemView(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = (View) parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public ViewHolder findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(findContainingItemView);
    }

    public ViewHolder findViewHolderForAdapterPosition(int i) {
        ViewHolder viewHolder = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionFor(childViewHolderInt) == i) {
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }

    public ViewHolder findViewHolderForItemId(long j) {
        Adapter adapter = this.mAdapter;
        ViewHolder viewHolder = null;
        if (adapter != null && adapter.hasStableIds()) {
            int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            for (int i = 0; i < unfilteredChildCount; i++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == j) {
                    if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    viewHolder = childViewHolderInt;
                }
            }
        }
        return viewHolder;
    }

    /* access modifiers changed from: package-private */
    public ViewHolder findViewHolderForPosition(int i, boolean z) {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder viewHolder = null;
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (z) {
                    if (childViewHolderInt.mPosition != i) {
                        continue;
                    }
                } else if (childViewHolderInt.getLayoutPosition() != i) {
                    continue;
                }
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }

    public boolean fling(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        int i3 = 0;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.mLayoutFrozen) {
            return false;
        } else {
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (!canScrollHorizontally || Math.abs(i) < this.mMinFlingVelocity) {
                i = 0;
            }
            if (!canScrollVertically || Math.abs(i2) < this.mMinFlingVelocity) {
                i2 = 0;
            }
            if (i == 0 && i2 == 0) {
                return false;
            }
            float f = (float) i;
            float f2 = (float) i2;
            if (!dispatchNestedPreFling(f, f2)) {
                boolean z = canScrollHorizontally || canScrollVertically;
                dispatchNestedFling(f, f2, z);
                if (z) {
                    if (canScrollHorizontally) {
                        i3 = 1;
                    }
                    if (canScrollVertically) {
                        i3 |= 2;
                    }
                    startNestedScroll(i3, 1);
                    int i4 = this.mMaxFlingVelocity;
                    int max = Math.max(-i4, Math.min(i, i4));
                    int i5 = this.mMaxFlingVelocity;
                    this.mViewFlinger.fling(max, Math.max(-i5, Math.min(i2, i5)));
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01a0, code lost:
        if (r11 > 0) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01c3, code lost:
        if (r13 > 0) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01c6, code lost:
        if (r11 < 0) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01c9, code lost:
        if (r13 < 0) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01d1, code lost:
        if ((r13 * r6) < 0) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x01d9, code lost:
        if ((r13 * r6) > 0) goto L_0x01a3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:139:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            android.view.View r3 = r3.onInterceptFocusSearch(r1, r2)
            if (r3 == 0) goto L_0x000f
            return r3
        L_0x000f:
            android.support.v7.widget.RecyclerView$Adapter r3 = r0.mAdapter
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x0025
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            if (r3 == 0) goto L_0x0025
            boolean r3 = r16.isComputingLayout()
            if (r3 != 0) goto L_0x0025
            boolean r3 = r0.mLayoutFrozen
            if (r3 != 0) goto L_0x0025
            r3 = r4
            goto L_0x0026
        L_0x0025:
            r3 = r5
        L_0x0026:
            android.view.FocusFinder r6 = android.view.FocusFinder.getInstance()
            r7 = 130(0x82, float:1.82E-43)
            r8 = 66
            r9 = 33
            r10 = 17
            r11 = 0
            r12 = 2
            if (r3 == 0) goto L_0x00a5
            if (r2 == r12) goto L_0x003a
            if (r2 != r4) goto L_0x00a5
        L_0x003a:
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            boolean r3 = r3.canScrollVertically()
            if (r3 == 0) goto L_0x0056
            if (r2 != r12) goto L_0x0046
            r3 = r7
            goto L_0x0047
        L_0x0046:
            r3 = r9
        L_0x0047:
            android.view.View r13 = r6.findNextFocus(r0, r1, r3)
            if (r13 != 0) goto L_0x004f
            r13 = r4
            goto L_0x0050
        L_0x004f:
            r13 = r5
        L_0x0050:
            boolean r14 = FORCE_ABS_FOCUS_SEARCH_DIRECTION
            if (r14 == 0) goto L_0x0057
            r2 = r3
            goto L_0x0057
        L_0x0056:
            r13 = r5
        L_0x0057:
            if (r13 != 0) goto L_0x0085
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            boolean r3 = r3.canScrollHorizontally()
            if (r3 == 0) goto L_0x0085
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            int r3 = r3.getLayoutDirection()
            if (r3 != r4) goto L_0x006b
            r3 = r4
            goto L_0x006c
        L_0x006b:
            r3 = r5
        L_0x006c:
            if (r2 != r12) goto L_0x0070
            r13 = r4
            goto L_0x0071
        L_0x0070:
            r13 = r5
        L_0x0071:
            r3 = r3 ^ r13
            if (r3 == 0) goto L_0x0076
            r3 = r8
            goto L_0x0077
        L_0x0076:
            r3 = r10
        L_0x0077:
            android.view.View r13 = r6.findNextFocus(r0, r1, r3)
            if (r13 != 0) goto L_0x007f
            r13 = r4
            goto L_0x0080
        L_0x007f:
            r13 = r5
        L_0x0080:
            boolean r14 = FORCE_ABS_FOCUS_SEARCH_DIRECTION
            if (r14 == 0) goto L_0x0085
            r2 = r3
        L_0x0085:
            if (r13 == 0) goto L_0x00a0
            r16.consumePendingUpdateOperations()
            android.view.View r3 = r16.findContainingItemView(r17)
            if (r3 != 0) goto L_0x0091
            return r11
        L_0x0091:
            r16.startInterceptRequestLayout()
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            android.support.v7.widget.RecyclerView$Recycler r13 = r0.mRecycler
            android.support.v7.widget.RecyclerView$State r14 = r0.mState
            r3.onFocusSearchFailed(r1, r2, r13, r14)
            r0.stopInterceptRequestLayout(r5)
        L_0x00a0:
            android.view.View r3 = r6.findNextFocus(r0, r1, r2)
            goto L_0x00c9
        L_0x00a5:
            android.view.View r6 = r6.findNextFocus(r0, r1, r2)
            if (r6 != 0) goto L_0x00c8
            if (r3 == 0) goto L_0x00c8
            r16.consumePendingUpdateOperations()
            android.view.View r3 = r16.findContainingItemView(r17)
            if (r3 != 0) goto L_0x00b7
            return r11
        L_0x00b7:
            r16.startInterceptRequestLayout()
            android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.mLayout
            android.support.v7.widget.RecyclerView$Recycler r6 = r0.mRecycler
            android.support.v7.widget.RecyclerView$State r13 = r0.mState
            android.view.View r3 = r3.onFocusSearchFailed(r1, r2, r6, r13)
            r0.stopInterceptRequestLayout(r5)
            goto L_0x00c9
        L_0x00c8:
            r3 = r6
        L_0x00c9:
            if (r3 == 0) goto L_0x00e0
            boolean r6 = r3.hasFocusable()
            if (r6 != 0) goto L_0x00e0
            android.view.View r4 = r16.getFocusedChild()
            if (r4 != 0) goto L_0x00dc
            android.view.View r0 = super.focusSearch(r1, r2)
            return r0
        L_0x00dc:
            r0.requestChildOnScreen(r3, r11)
            return r1
        L_0x00e0:
            if (r3 == 0) goto L_0x01a3
            if (r3 != r0) goto L_0x00e6
            goto L_0x01a3
        L_0x00e6:
            android.view.View r6 = r0.findContainingItemView(r3)
            if (r6 != 0) goto L_0x00ef
            r4 = r5
            goto L_0x01db
        L_0x00ef:
            if (r1 != 0) goto L_0x00f3
            goto L_0x01db
        L_0x00f3:
            android.view.View r6 = r16.findContainingItemView(r17)
            if (r6 != 0) goto L_0x00fb
            goto L_0x01db
        L_0x00fb:
            android.graphics.Rect r6 = r0.mTempRect
            int r11 = r17.getWidth()
            int r13 = r17.getHeight()
            r6.set(r5, r5, r11, r13)
            android.graphics.Rect r6 = r0.mTempRect2
            int r11 = r3.getWidth()
            int r13 = r3.getHeight()
            r6.set(r5, r5, r11, r13)
            android.graphics.Rect r6 = r0.mTempRect
            r0.offsetDescendantRectToMyCoords(r1, r6)
            android.graphics.Rect r6 = r0.mTempRect2
            r0.offsetDescendantRectToMyCoords(r3, r6)
            android.support.v7.widget.RecyclerView$LayoutManager r6 = r0.mLayout
            int r6 = r6.getLayoutDirection()
            r11 = -1
            if (r6 != r4) goto L_0x012a
            r6 = r11
            goto L_0x012b
        L_0x012a:
            r6 = r4
        L_0x012b:
            android.graphics.Rect r13 = r0.mTempRect
            int r14 = r13.left
            android.graphics.Rect r15 = r0.mTempRect2
            int r15 = r15.left
            if (r14 < r15) goto L_0x0139
            int r13 = r13.right
            if (r13 > r15) goto L_0x0145
        L_0x0139:
            android.graphics.Rect r13 = r0.mTempRect
            int r13 = r13.right
            android.graphics.Rect r14 = r0.mTempRect2
            int r14 = r14.right
            if (r13 >= r14) goto L_0x0145
            r13 = r4
            goto L_0x0160
        L_0x0145:
            android.graphics.Rect r13 = r0.mTempRect
            int r14 = r13.right
            android.graphics.Rect r15 = r0.mTempRect2
            int r15 = r15.right
            if (r14 > r15) goto L_0x0153
            int r13 = r13.left
            if (r13 < r15) goto L_0x015f
        L_0x0153:
            android.graphics.Rect r13 = r0.mTempRect
            int r13 = r13.left
            android.graphics.Rect r14 = r0.mTempRect2
            int r14 = r14.left
            if (r13 <= r14) goto L_0x015f
            r13 = r11
            goto L_0x0160
        L_0x015f:
            r13 = r5
        L_0x0160:
            android.graphics.Rect r14 = r0.mTempRect
            int r15 = r14.top
            android.graphics.Rect r5 = r0.mTempRect2
            int r5 = r5.top
            if (r15 < r5) goto L_0x016e
            int r14 = r14.bottom
            if (r14 > r5) goto L_0x017a
        L_0x016e:
            android.graphics.Rect r5 = r0.mTempRect
            int r5 = r5.bottom
            android.graphics.Rect r14 = r0.mTempRect2
            int r14 = r14.bottom
            if (r5 >= r14) goto L_0x017a
            r11 = r4
            goto L_0x0194
        L_0x017a:
            android.graphics.Rect r5 = r0.mTempRect
            int r14 = r5.bottom
            android.graphics.Rect r15 = r0.mTempRect2
            int r15 = r15.bottom
            if (r14 > r15) goto L_0x0188
            int r5 = r5.top
            if (r5 < r15) goto L_0x0193
        L_0x0188:
            android.graphics.Rect r5 = r0.mTempRect
            int r5 = r5.top
            android.graphics.Rect r14 = r0.mTempRect2
            int r14 = r14.top
            if (r5 <= r14) goto L_0x0193
            goto L_0x0194
        L_0x0193:
            r11 = 0
        L_0x0194:
            if (r2 == r4) goto L_0x01d4
            if (r2 == r12) goto L_0x01cc
            if (r2 == r10) goto L_0x01c9
            if (r2 == r9) goto L_0x01c6
            if (r2 == r8) goto L_0x01c3
            if (r2 != r7) goto L_0x01a5
            if (r11 <= 0) goto L_0x01a3
            goto L_0x01db
        L_0x01a3:
            r4 = 0
            goto L_0x01db
        L_0x01a5:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid direction: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r0 = r16.exceptionLabel()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            throw r1
        L_0x01c3:
            if (r13 <= 0) goto L_0x01a3
            goto L_0x01db
        L_0x01c6:
            if (r11 >= 0) goto L_0x01a3
            goto L_0x01db
        L_0x01c9:
            if (r13 >= 0) goto L_0x01a3
            goto L_0x01db
        L_0x01cc:
            if (r11 > 0) goto L_0x01db
            if (r11 != 0) goto L_0x01a3
            int r13 = r13 * r6
            if (r13 < 0) goto L_0x01a3
            goto L_0x01db
        L_0x01d4:
            if (r11 < 0) goto L_0x01db
            if (r11 != 0) goto L_0x01a3
            int r13 = r13 * r6
            if (r13 > 0) goto L_0x01a3
        L_0x01db:
            if (r4 == 0) goto L_0x01de
            goto L_0x01e2
        L_0x01de:
            android.view.View r3 = super.focusSearch(r1, r2)
        L_0x01e2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("RecyclerView has no LayoutManager");
        outline13.append(exceptionLabel());
        throw new IllegalStateException(outline13.toString());
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("RecyclerView has no LayoutManager");
        outline13.append(exceptionLabel());
        throw new IllegalStateException(outline13.toString());
    }

    public Adapter getAdapter() {
        return this.mAdapter;
    }

    /* access modifiers changed from: package-private */
    public int getAdapterPositionFor(ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            return -1;
        }
        AdapterHelper adapterHelper = this.mAdapterHelper;
        int i = viewHolder.mPosition;
        int size = adapterHelper.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            AdapterHelper.UpdateOp updateOp = adapterHelper.mPendingUpdates.get(i2);
            int i3 = updateOp.cmd;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = updateOp.positionStart;
                    if (i4 <= i) {
                        int i5 = updateOp.itemCount;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = updateOp.positionStart;
                    if (i6 == i) {
                        i = updateOp.itemCount;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (updateOp.itemCount <= i) {
                            i++;
                        }
                    }
                }
            } else if (updateOp.positionStart <= i) {
                i += updateOp.itemCount;
            }
        }
        return i;
    }

    public int getBaseline() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.getBaseline();
        }
        return super.getBaseline();
    }

    /* access modifiers changed from: package-private */
    public long getChangedHolderKey(ViewHolder viewHolder) {
        return this.mAdapter.hasStableIds() ? viewHolder.getItemId() : (long) viewHolder.mPosition;
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        ChildDrawingOrderCallback childDrawingOrderCallback = this.mChildDrawingOrderCallback;
        if (childDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(i, i2);
        }
        return childDrawingOrderCallback.onGetChildDrawingOrder(i, i2);
    }

    public int getChildLayoutPosition(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    public ViewHolder getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    /* access modifiers changed from: package-private */
    public Rect getItemDecorInsetsForChild(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mInsetsDirty) {
            return layoutParams.mDecorInsets;
        }
        if (this.mState.mInPreLayout && (layoutParams.isItemChanged() || layoutParams.mViewHolder.isInvalid())) {
            return layoutParams.mDecorInsets;
        }
        Rect rect = layoutParams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i).getItemOffsets(this.mTempRect, view, this, this.mState);
            int i2 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i2 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.mInsetsDirty = false;
        return rect;
    }

    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    /* access modifiers changed from: package-private */
    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0;
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent(0);
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    /* access modifiers changed from: package-private */
    public void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() {
            /* access modifiers changed from: package-private */
            public void dispatchUpdate(AdapterHelper.UpdateOp updateOp) {
                int i = updateOp.cmd;
                if (i == 1) {
                    RecyclerView recyclerView = RecyclerView.this;
                    recyclerView.mLayout.onItemsAdded(recyclerView, updateOp.positionStart, updateOp.itemCount);
                } else if (i == 2) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    recyclerView2.mLayout.onItemsRemoved(recyclerView2, updateOp.positionStart, updateOp.itemCount);
                } else if (i == 4) {
                    RecyclerView recyclerView3 = RecyclerView.this;
                    recyclerView3.mLayout.onItemsUpdated(recyclerView3, updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                } else if (i == 8) {
                    RecyclerView recyclerView4 = RecyclerView.this;
                    recyclerView4.mLayout.onItemsMoved(recyclerView4, updateOp.positionStart, updateOp.itemCount, 1);
                }
            }

            public ViewHolder findViewHolder(int i) {
                ViewHolder findViewHolderForPosition = RecyclerView.this.findViewHolderForPosition(i, true);
                if (findViewHolderForPosition != null && !RecyclerView.this.mChildHelper.isHidden(findViewHolderForPosition.itemView)) {
                    return findViewHolderForPosition;
                }
                return null;
            }

            public void markViewHoldersUpdated(int i, int i2, Object obj) {
                RecyclerView.this.viewRangeUpdate(i, i2, obj);
                RecyclerView.this.mItemsChanged = true;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Trying to set fast scroller without both required drawables.");
            outline13.append(exceptionLabel());
            throw new IllegalArgumentException(outline13.toString());
        }
        Resources resources = getContext().getResources();
        new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
    }

    /* access modifiers changed from: package-private */
    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    /* access modifiers changed from: package-private */
    public boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    /* access modifiers changed from: package-private */
    public void jumpToPositionForSmoothScroller(int i) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    /* access modifiers changed from: package-private */
    public void markItemDecorInsetsDirty() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            LayoutParams layoutParams = (LayoutParams) recycler.mCachedViews.get(i2).itemView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.mInsetsDirty = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void markKnownViewsInvalid() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            ViewHolder viewHolder = recycler.mCachedViews.get(i2);
            if (viewHolder != null) {
                viewHolder.addFlags(6);
                viewHolder.addChangePayload((Object) null);
            }
        }
        Adapter adapter = RecyclerView.this.mAdapter;
        if (adapter == null || !adapter.hasStableIds()) {
            recycler.recycleAndClearCachedViews();
        }
    }

    public void offsetChildrenHorizontal(int i) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            this.mChildHelper.getChildAt(i2).offsetLeftAndRight(i);
        }
    }

    public void offsetChildrenVertical(int i) {
        int childCount = this.mChildHelper.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            this.mChildHelper.getChildAt(i2).offsetTopAndBottom(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void offsetPositionRecordsForInsert(int i, int i2) {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i3 = 0; i3 < unfilteredChildCount; i3++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i3));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i) {
                childViewHolderInt.offsetPosition(i2, false);
                this.mState.mStructureChanged = true;
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i4 = 0; i4 < size; i4++) {
            ViewHolder viewHolder = recycler.mCachedViews.get(i4);
            if (viewHolder != null && viewHolder.mPosition >= i) {
                viewHolder.offsetPosition(i2, true);
            }
        }
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void offsetPositionRecordsForMove(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        if (i < i2) {
            i5 = i;
            i4 = i2;
            i3 = -1;
        } else {
            i4 = i;
            i5 = i2;
            i3 = 1;
        }
        for (int i11 = 0; i11 < unfilteredChildCount; i11++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i11));
            if (childViewHolderInt != null && (i10 = childViewHolderInt.mPosition) >= i5 && i10 <= i4) {
                if (i10 == i) {
                    childViewHolderInt.offsetPosition(i2 - i, false);
                } else {
                    childViewHolderInt.offsetPosition(i3, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        Recycler recycler = this.mRecycler;
        if (i < i2) {
            i7 = i2;
            i6 = -1;
            i8 = i;
        } else {
            i8 = i2;
            i6 = 1;
            i7 = i;
        }
        int size = recycler.mCachedViews.size();
        for (int i12 = 0; i12 < size; i12++) {
            ViewHolder viewHolder = recycler.mCachedViews.get(i12);
            if (viewHolder != null && (i9 = viewHolder.mPosition) >= i8 && i9 <= i7) {
                if (i9 == i) {
                    viewHolder.offsetPosition(i2 - i, false);
                } else {
                    viewHolder.offsetPosition(i6, false);
                }
            }
        }
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int i3 = i + i2;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i4 = 0; i4 < unfilteredChildCount; i4++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i4));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i5 = childViewHolderInt.mPosition;
                if (i5 >= i3) {
                    childViewHolderInt.offsetPosition(-i2, z);
                    this.mState.mStructureChanged = true;
                } else if (i5 >= i) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(i - 1, -i2, z);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        while (true) {
            size--;
            if (size >= 0) {
                ViewHolder viewHolder = recycler.mCachedViews.get(size);
                if (viewHolder != null) {
                    int i6 = viewHolder.mPosition;
                    if (i6 >= i3) {
                        viewHolder.offsetPosition(-i2, z);
                    } else if (i6 >= i) {
                        viewHolder.addFlags(8);
                        recycler.recycleCachedViewAt(size);
                    }
                }
            } else {
                requestLayout();
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        if (r0 >= 30.0f) goto L_0x0053;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAttachedToWindow() {
        /*
            r4 = this;
            super.onAttachedToWindow()
            r0 = 0
            r4.mLayoutOrScrollCounter = r0
            r1 = 1
            r4.mIsAttached = r1
            boolean r2 = r4.mFirstLayoutComplete
            if (r2 == 0) goto L_0x0014
            boolean r2 = r4.isLayoutRequested()
            if (r2 != 0) goto L_0x0014
            goto L_0x0015
        L_0x0014:
            r1 = r0
        L_0x0015:
            r4.mFirstLayoutComplete = r1
            android.support.v7.widget.RecyclerView$LayoutManager r1 = r4.mLayout
            if (r1 == 0) goto L_0x001e
            r1.dispatchAttachedToWindow(r4)
        L_0x001e:
            r4.mPostedAnimatorRunner = r0
            boolean r0 = ALLOW_THREAD_GAP_WORK
            if (r0 == 0) goto L_0x0068
            java.lang.ThreadLocal<android.support.v7.widget.GapWorker> r0 = android.support.p002v7.widget.GapWorker.sGapWorker
            java.lang.Object r0 = r0.get()
            android.support.v7.widget.GapWorker r0 = (android.support.p002v7.widget.GapWorker) r0
            r4.mGapWorker = r0
            android.support.v7.widget.GapWorker r0 = r4.mGapWorker
            if (r0 != 0) goto L_0x0061
            android.support.v7.widget.GapWorker r0 = new android.support.v7.widget.GapWorker
            r0.<init>()
            r4.mGapWorker = r0
            android.view.Display r0 = android.support.p000v4.view.ViewCompat.getDisplay(r4)
            r1 = 1114636288(0x42700000, float:60.0)
            boolean r2 = r4.isInEditMode()
            if (r2 != 0) goto L_0x0052
            if (r0 == 0) goto L_0x0052
            float r0 = r0.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r0 = r1
        L_0x0053:
            android.support.v7.widget.GapWorker r1 = r4.mGapWorker
            r2 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r2 = r2 / r0
            long r2 = (long) r2
            r1.mFrameIntervalNs = r2
            java.lang.ThreadLocal<android.support.v7.widget.GapWorker> r0 = android.support.p002v7.widget.GapWorker.sGapWorker
            r0.set(r1)
        L_0x0061:
            android.support.v7.widget.GapWorker r0 = r4.mGapWorker
            java.util.ArrayList<android.support.v7.widget.RecyclerView> r0 = r0.mRecyclerViews
            r0.add(r4)
        L_0x0068:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.onAttachedToWindow():void");
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        stopScroll();
        this.mIsAttached = false;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
        if (ALLOW_THREAD_GAP_WORK && (gapWorker = this.mGapWorker) != null) {
            gapWorker.mRecyclerViews.remove(this);
            this.mGapWorker = null;
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mItemDecorations.get(i).onDraw(canvas, this, this.mState);
        }
    }

    /* access modifiers changed from: package-private */
    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    /* access modifiers changed from: package-private */
    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        if (this.mLayout != null && !this.mLayoutFrozen && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                f2 = this.mLayout.canScrollVertically() ? -motionEvent.getAxisValue(9) : 0.0f;
                if (this.mLayout.canScrollHorizontally()) {
                    f = motionEvent.getAxisValue(10);
                    if (!(f2 == 0.0f && f == 0.0f)) {
                        scrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent);
                    }
                }
            } else {
                if ((motionEvent.getSource() & 4194304) != 0) {
                    float axisValue = motionEvent.getAxisValue(26);
                    if (this.mLayout.canScrollVertically()) {
                        f2 = -axisValue;
                    } else if (this.mLayout.canScrollHorizontally()) {
                        f = axisValue;
                        f2 = 0.0f;
                        scrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent);
                    }
                }
                f2 = 0.0f;
                f = 0.0f;
                scrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent);
            }
            f = 0.0f;
            scrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        if (this.mLayoutFrozen) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        int size = this.mOnItemTouchListeners.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                z = false;
                break;
            }
            OnItemTouchListener onItemTouchListener = this.mOnItemTouchListeners.get(i);
            if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mActiveOnItemTouchListener = onItemTouchListener;
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            cancelTouch();
            return true;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return false;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.mIgnoreMotionEventTillDown) {
                this.mIgnoreMotionEventTillDown = false;
            }
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            if (this.mScrollState == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = canScrollHorizontally ? 1 : 0;
            if (canScrollVertically) {
                i2 |= 2;
            }
            startNestedScroll(i2, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (findPointerIndex < 0) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Error processing scroll; pointer index for id ");
                outline13.append(this.mScrollPointerId);
                outline13.append(" not found. Did any MotionEvents get skipped?");
                Log.e("RecyclerView", outline13.toString());
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.mScrollState != 1) {
                int i3 = x2 - this.mInitialTouchX;
                int i4 = y2 - this.mInitialTouchY;
                if (!canScrollHorizontally || Math.abs(i3) <= this.mTouchSlop) {
                    z2 = false;
                } else {
                    this.mLastTouchX = x2;
                    z2 = true;
                }
                if (canScrollVertically && Math.abs(i4) > this.mTouchSlop) {
                    this.mLastTouchY = y2;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            cancelTouch();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x3;
            this.mInitialTouchX = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y3;
            this.mInitialTouchY = y3;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        if (this.mScrollState == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        Trace.beginSection("RV OnLayout");
        dispatchLayout();
        int i6 = Build.VERSION.SDK_INT;
        Trace.endSection();
        this.mFirstLayoutComplete = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            defaultOnMeasure(i, i2);
            return;
        }
        boolean z = false;
        if (layoutManager.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mLayout.onMeasure(this.mRecycler, this.mState, i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            if (!z && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(i, i2);
                this.mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                }
            }
        } else if (this.mHasFixedSize) {
            this.mLayout.onMeasure(this.mRecycler, this.mState, i, i2);
        } else {
            if (this.mAdapterUpdateDuringMeasure) {
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                processAdapterUpdatesAndSetAnimationFlags();
                onExitLayoutOrScroll();
                State state = this.mState;
                if (state.mRunPredictiveAnimations) {
                    state.mInPreLayout = true;
                } else {
                    this.mAdapterHelper.consumeUpdatesInOnePass();
                    this.mState.mInPreLayout = false;
                }
                this.mAdapterUpdateDuringMeasure = false;
                stopInterceptRequestLayout(false);
            } else if (this.mState.mRunPredictiveAnimations) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            Adapter adapter = this.mAdapter;
            if (adapter != null) {
                this.mState.mItemCount = adapter.getItemCount();
            } else {
                this.mState.mItemCount = 0;
            }
            startInterceptRequestLayout();
            this.mLayout.onMeasure(this.mRecycler, this.mState, i, i2);
            stopInterceptRequestLayout(false);
            this.mState.mInPreLayout = false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        this.mPendingSavedState = (SavedState) parcelable;
        super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && (parcelable2 = this.mPendingSavedState.mLayoutState) != null) {
            layoutManager.onRestoreInstanceState(parcelable2);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.copyFrom(savedState2);
        } else {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                savedState.mLayoutState = layoutManager.onSaveInstanceState();
            } else {
                savedState.mLayoutState = null;
            }
        }
        return savedState;
    }

    public void onScrollStateChanged(int i) {
    }

    public void onScrolled(int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            invalidateGlows();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r15) {
        /*
            r14 = this;
            boolean r0 = r14.mLayoutFrozen
            r1 = 0
            if (r0 != 0) goto L_0x01f9
            boolean r0 = r14.mIgnoreMotionEventTillDown
            if (r0 == 0) goto L_0x000b
            goto L_0x01f9
        L_0x000b:
            int r0 = r15.getAction()
            android.support.v7.widget.RecyclerView$OnItemTouchListener r2 = r14.mActiveOnItemTouchListener
            r3 = 3
            r4 = 1
            if (r2 == 0) goto L_0x0025
            r5 = 0
            if (r0 != 0) goto L_0x001b
            r14.mActiveOnItemTouchListener = r5
            goto L_0x0025
        L_0x001b:
            r2.onTouchEvent(r14, r15)
            if (r0 == r3) goto L_0x0022
            if (r0 != r4) goto L_0x0040
        L_0x0022:
            r14.mActiveOnItemTouchListener = r5
            goto L_0x0040
        L_0x0025:
            if (r0 == 0) goto L_0x0045
            java.util.ArrayList<android.support.v7.widget.RecyclerView$OnItemTouchListener> r0 = r14.mOnItemTouchListeners
            int r0 = r0.size()
            r2 = r1
        L_0x002e:
            if (r2 >= r0) goto L_0x0045
            java.util.ArrayList<android.support.v7.widget.RecyclerView$OnItemTouchListener> r5 = r14.mOnItemTouchListeners
            java.lang.Object r5 = r5.get(r2)
            android.support.v7.widget.RecyclerView$OnItemTouchListener r5 = (android.support.p002v7.widget.RecyclerView.OnItemTouchListener) r5
            boolean r6 = r5.onInterceptTouchEvent(r14, r15)
            if (r6 == 0) goto L_0x0042
            r14.mActiveOnItemTouchListener = r5
        L_0x0040:
            r0 = r4
            goto L_0x0046
        L_0x0042:
            int r2 = r2 + 1
            goto L_0x002e
        L_0x0045:
            r0 = r1
        L_0x0046:
            if (r0 == 0) goto L_0x004c
            r14.cancelTouch()
            return r4
        L_0x004c:
            android.support.v7.widget.RecyclerView$LayoutManager r0 = r14.mLayout
            if (r0 != 0) goto L_0x0051
            return r1
        L_0x0051:
            boolean r0 = r0.canScrollHorizontally()
            android.support.v7.widget.RecyclerView$LayoutManager r2 = r14.mLayout
            boolean r2 = r2.canScrollVertically()
            android.view.VelocityTracker r5 = r14.mVelocityTracker
            if (r5 != 0) goto L_0x0065
            android.view.VelocityTracker r5 = android.view.VelocityTracker.obtain()
            r14.mVelocityTracker = r5
        L_0x0065:
            android.view.MotionEvent r5 = android.view.MotionEvent.obtain(r15)
            int r6 = r15.getActionMasked()
            int r7 = r15.getActionIndex()
            if (r6 != 0) goto L_0x0079
            int[] r8 = r14.mNestedOffsets
            r8[r4] = r1
            r8[r1] = r1
        L_0x0079:
            int[] r8 = r14.mNestedOffsets
            r9 = r8[r1]
            float r9 = (float) r9
            r8 = r8[r4]
            float r8 = (float) r8
            r5.offsetLocation(r9, r8)
            r8 = 1056964608(0x3f000000, float:0.5)
            if (r6 == 0) goto L_0x01c8
            if (r6 == r4) goto L_0x0186
            r9 = 2
            if (r6 == r9) goto L_0x00bd
            if (r6 == r3) goto L_0x00b8
            r0 = 5
            if (r6 == r0) goto L_0x009c
            r0 = 6
            if (r6 == r0) goto L_0x0097
            goto L_0x01ee
        L_0x0097:
            r14.onPointerUp(r15)
            goto L_0x01ee
        L_0x009c:
            int r0 = r15.getPointerId(r7)
            r14.mScrollPointerId = r0
            float r0 = r15.getX(r7)
            float r0 = r0 + r8
            int r0 = (int) r0
            r14.mLastTouchX = r0
            r14.mInitialTouchX = r0
            float r15 = r15.getY(r7)
            float r15 = r15 + r8
            int r15 = (int) r15
            r14.mLastTouchY = r15
            r14.mInitialTouchY = r15
            goto L_0x01ee
        L_0x00b8:
            r14.cancelTouch()
            goto L_0x01ee
        L_0x00bd:
            int r3 = r14.mScrollPointerId
            int r3 = r15.findPointerIndex(r3)
            if (r3 >= 0) goto L_0x00df
            java.lang.String r15 = "Error processing scroll; pointer index for id "
            java.lang.StringBuilder r15 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r15)
            int r14 = r14.mScrollPointerId
            r15.append(r14)
            java.lang.String r14 = " not found. Did any MotionEvents get skipped?"
            r15.append(r14)
            java.lang.String r14 = r15.toString()
            java.lang.String r15 = "RecyclerView"
            android.util.Log.e(r15, r14)
            return r1
        L_0x00df:
            float r6 = r15.getX(r3)
            float r6 = r6 + r8
            int r6 = (int) r6
            float r15 = r15.getY(r3)
            float r15 = r15 + r8
            int r15 = (int) r15
            int r3 = r14.mLastTouchX
            int r3 = r3 - r6
            int r7 = r14.mLastTouchY
            int r13 = r7 - r15
            int[] r10 = r14.mScrollConsumed
            int[] r11 = r14.mScrollOffset
            r12 = 0
            r7 = r14
            r8 = r3
            r9 = r13
            boolean r7 = r7.dispatchNestedPreScroll(r8, r9, r10, r11, r12)
            if (r7 == 0) goto L_0x0125
            int[] r7 = r14.mScrollConsumed
            r8 = r7[r1]
            int r3 = r3 - r8
            r7 = r7[r4]
            int r13 = r13 - r7
            int[] r7 = r14.mScrollOffset
            r8 = r7[r1]
            float r8 = (float) r8
            r7 = r7[r4]
            float r7 = (float) r7
            r5.offsetLocation(r8, r7)
            int[] r7 = r14.mNestedOffsets
            r8 = r7[r1]
            int[] r9 = r14.mScrollOffset
            r10 = r9[r1]
            int r8 = r8 + r10
            r7[r1] = r8
            r8 = r7[r4]
            r9 = r9[r4]
            int r8 = r8 + r9
            r7[r4] = r8
        L_0x0125:
            int r7 = r14.mScrollState
            if (r7 == r4) goto L_0x0150
            if (r0 == 0) goto L_0x013a
            int r7 = java.lang.Math.abs(r3)
            int r8 = r14.mTouchSlop
            if (r7 <= r8) goto L_0x013a
            if (r3 <= 0) goto L_0x0137
            int r3 = r3 - r8
            goto L_0x0138
        L_0x0137:
            int r3 = r3 + r8
        L_0x0138:
            r7 = r4
            goto L_0x013b
        L_0x013a:
            r7 = r1
        L_0x013b:
            if (r2 == 0) goto L_0x014b
            int r8 = java.lang.Math.abs(r13)
            int r9 = r14.mTouchSlop
            if (r8 <= r9) goto L_0x014b
            if (r13 <= 0) goto L_0x0149
            int r13 = r13 - r9
            goto L_0x014a
        L_0x0149:
            int r13 = r13 + r9
        L_0x014a:
            r7 = r4
        L_0x014b:
            if (r7 == 0) goto L_0x0150
            r14.setScrollState(r4)
        L_0x0150:
            int r7 = r14.mScrollState
            if (r7 != r4) goto L_0x01ee
            int[] r7 = r14.mScrollOffset
            r8 = r7[r1]
            int r6 = r6 - r8
            r14.mLastTouchX = r6
            r6 = r7[r4]
            int r15 = r15 - r6
            r14.mLastTouchY = r15
            if (r0 == 0) goto L_0x0164
            r15 = r3
            goto L_0x0165
        L_0x0164:
            r15 = r1
        L_0x0165:
            if (r2 == 0) goto L_0x0169
            r0 = r13
            goto L_0x016a
        L_0x0169:
            r0 = r1
        L_0x016a:
            boolean r15 = r14.scrollByInternal(r15, r0, r5)
            if (r15 == 0) goto L_0x0177
            android.view.ViewParent r15 = r14.getParent()
            r15.requestDisallowInterceptTouchEvent(r4)
        L_0x0177:
            android.support.v7.widget.GapWorker r15 = r14.mGapWorker
            if (r15 == 0) goto L_0x01ee
            if (r3 != 0) goto L_0x017f
            if (r13 == 0) goto L_0x01ee
        L_0x017f:
            android.support.v7.widget.GapWorker r15 = r14.mGapWorker
            r15.postFromTraversal(r14, r3, r13)
            goto L_0x01ee
        L_0x0186:
            android.view.VelocityTracker r15 = r14.mVelocityTracker
            r15.addMovement(r5)
            android.view.VelocityTracker r15 = r14.mVelocityTracker
            r3 = 1000(0x3e8, float:1.401E-42)
            int r6 = r14.mMaxFlingVelocity
            float r6 = (float) r6
            r15.computeCurrentVelocity(r3, r6)
            r15 = 0
            if (r0 == 0) goto L_0x01a2
            android.view.VelocityTracker r0 = r14.mVelocityTracker
            int r3 = r14.mScrollPointerId
            float r0 = r0.getXVelocity(r3)
            float r0 = -r0
            goto L_0x01a3
        L_0x01a2:
            r0 = r15
        L_0x01a3:
            if (r2 == 0) goto L_0x01af
            android.view.VelocityTracker r2 = r14.mVelocityTracker
            int r3 = r14.mScrollPointerId
            float r2 = r2.getYVelocity(r3)
            float r2 = -r2
            goto L_0x01b0
        L_0x01af:
            r2 = r15
        L_0x01b0:
            int r3 = (r0 > r15 ? 1 : (r0 == r15 ? 0 : -1))
            if (r3 != 0) goto L_0x01b8
            int r15 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r15 == 0) goto L_0x01c0
        L_0x01b8:
            int r15 = (int) r0
            int r0 = (int) r2
            boolean r15 = r14.fling(r15, r0)
            if (r15 != 0) goto L_0x01c3
        L_0x01c0:
            r14.setScrollState(r1)
        L_0x01c3:
            r14.resetTouch()
            r1 = r4
            goto L_0x01ee
        L_0x01c8:
            int r3 = r15.getPointerId(r1)
            r14.mScrollPointerId = r3
            float r3 = r15.getX()
            float r3 = r3 + r8
            int r3 = (int) r3
            r14.mLastTouchX = r3
            r14.mInitialTouchX = r3
            float r15 = r15.getY()
            float r15 = r15 + r8
            int r15 = (int) r15
            r14.mLastTouchY = r15
            r14.mInitialTouchY = r15
            if (r0 == 0) goto L_0x01e6
            r15 = r4
            goto L_0x01e7
        L_0x01e6:
            r15 = r1
        L_0x01e7:
            if (r2 == 0) goto L_0x01eb
            r15 = r15 | 2
        L_0x01eb:
            r14.startNestedScroll(r15, r1)
        L_0x01ee:
            if (r1 != 0) goto L_0x01f5
            android.view.VelocityTracker r14 = r14.mVelocityTracker
            r14.addMovement(r5)
        L_0x01f5:
            r5.recycle()
            return r4
        L_0x01f9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    public void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    /* access modifiers changed from: package-private */
    public void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(viewHolder), viewHolder);
        }
        this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
    }

    /* access modifiers changed from: package-private */
    public void removeAndRecycleViews() {
        ItemAnimator itemAnimator = this.mItemAnimator;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    /* access modifiers changed from: package-private */
    public boolean removeAnimatingView(View view) {
        startInterceptRequestLayout();
        boolean removeViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (removeViewIfHidden) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(childViewHolderInt);
            this.mRecycler.recycleViewHolderInternal(childViewHolderInt);
        }
        stopInterceptRequestLayout(!removeViewIfHidden);
        return removeViewIfHidden;
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View view, boolean z) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt + exceptionLabel());
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    public void removeItemDecoration(ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(itemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        List<OnChildAttachStateChangeListener> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.remove(onChildAttachStateChangeListener);
        }
    }

    public void removeOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.remove(onItemTouchListener);
        if (this.mActiveOnItemTouchListener == onItemTouchListener) {
            this.mActiveOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(OnScrollListener onScrollListener) {
        List<OnScrollListener> list = this.mScrollListeners;
        if (list != null) {
            list.remove(onScrollListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void repositionShadowingViews() {
        ViewHolder viewHolder;
        int childCount = this.mChildHelper.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mChildHelper.getChildAt(i);
            ViewHolder childViewHolder = getChildViewHolder(childAt);
            if (!(childViewHolder == null || (viewHolder = childViewHolder.mShadowingHolder) == null)) {
                View view = viewHolder.itemView;
                int left = childAt.getLeft();
                int top = childAt.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            this.mOnItemTouchListeners.get(i).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutFrozen) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void saveOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    public void scrollBy(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutFrozen) {
            boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    i = 0;
                }
                if (!canScrollVertically) {
                    i2 = 0;
                }
                scrollByInternal(i, i2, (MotionEvent) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scrollByInternal(int r19, int r20, android.view.MotionEvent r21) {
        /*
            r18 = this;
            r7 = r18
            r8 = r19
            r9 = r20
            r10 = r21
            r18.consumePendingUpdateOperations()
            android.support.v7.widget.RecyclerView$Adapter r0 = r7.mAdapter
            r11 = 1
            r12 = 0
            if (r0 == 0) goto L_0x0025
            int[] r0 = r7.mScrollStepConsumed
            r7.scrollStep(r8, r9, r0)
            int[] r0 = r7.mScrollStepConsumed
            r1 = r0[r12]
            r0 = r0[r11]
            int r2 = r8 - r1
            int r3 = r9 - r0
            r6 = r0
            r15 = r1
            r13 = r2
            r14 = r3
            goto L_0x0029
        L_0x0025:
            r6 = r12
            r13 = r6
            r14 = r13
            r15 = r14
        L_0x0029:
            java.util.ArrayList<android.support.v7.widget.RecyclerView$ItemDecoration> r0 = r7.mItemDecorations
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0034
            r18.invalidate()
        L_0x0034:
            int[] r5 = r7.mScrollOffset
            r16 = 0
            r0 = r18
            r1 = r15
            r2 = r6
            r3 = r13
            r4 = r14
            r17 = r6
            r6 = r16
            boolean r0 = r0.dispatchNestedScroll(r1, r2, r3, r4, r5, r6)
            if (r0 == 0) goto L_0x0077
            int r0 = r7.mLastTouchX
            int[] r1 = r7.mScrollOffset
            r2 = r1[r12]
            int r0 = r0 - r2
            r7.mLastTouchX = r0
            int r0 = r7.mLastTouchY
            r2 = r1[r11]
            int r0 = r0 - r2
            r7.mLastTouchY = r0
            if (r10 == 0) goto L_0x0063
            r0 = r1[r12]
            float r0 = (float) r0
            r1 = r1[r11]
            float r1 = (float) r1
            r10.offsetLocation(r0, r1)
        L_0x0063:
            int[] r0 = r7.mNestedOffsets
            r1 = r0[r12]
            int[] r2 = r7.mScrollOffset
            r3 = r2[r12]
            int r1 = r1 + r3
            r0[r12] = r1
            r1 = r0[r11]
            r2 = r2[r11]
            int r1 = r1 + r2
            r0[r11] = r1
            goto L_0x0120
        L_0x0077:
            int r0 = r18.getOverScrollMode()
            r1 = 2
            if (r0 == r1) goto L_0x0120
            if (r10 == 0) goto L_0x011d
            r0 = 8194(0x2002, float:1.1482E-41)
            int r1 = r21.getSource()
            r1 = r1 & r0
            if (r1 != r0) goto L_0x008b
            r0 = r11
            goto L_0x008c
        L_0x008b:
            r0 = r12
        L_0x008c:
            if (r0 != 0) goto L_0x011d
            float r0 = r21.getX()
            float r1 = (float) r13
            float r2 = r21.getY()
            float r3 = (float) r14
            r4 = 0
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r5 >= 0) goto L_0x00b9
            r18.ensureLeftGlow()
            android.widget.EdgeEffect r5 = r7.mLeftGlow
            float r10 = -r1
            int r13 = r18.getWidth()
            float r13 = (float) r13
            float r10 = r10 / r13
            int r13 = r18.getHeight()
            float r13 = (float) r13
            float r2 = r2 / r13
            float r2 = r6 - r2
            int r13 = android.os.Build.VERSION.SDK_INT
            r5.onPull(r10, r2)
            goto L_0x00d4
        L_0x00b9:
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r5 <= 0) goto L_0x00d6
            r18.ensureRightGlow()
            android.widget.EdgeEffect r5 = r7.mRightGlow
            int r10 = r18.getWidth()
            float r10 = (float) r10
            float r10 = r1 / r10
            int r13 = r18.getHeight()
            float r13 = (float) r13
            float r2 = r2 / r13
            int r13 = android.os.Build.VERSION.SDK_INT
            r5.onPull(r10, r2)
        L_0x00d4:
            r2 = r11
            goto L_0x00d7
        L_0x00d6:
            r2 = r12
        L_0x00d7:
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 >= 0) goto L_0x00f3
            r18.ensureTopGlow()
            android.widget.EdgeEffect r2 = r7.mTopGlow
            float r5 = -r3
            int r6 = r18.getHeight()
            float r6 = (float) r6
            float r5 = r5 / r6
            int r6 = r18.getWidth()
            float r6 = (float) r6
            float r0 = r0 / r6
            int r6 = android.os.Build.VERSION.SDK_INT
            r2.onPull(r5, r0)
            goto L_0x010f
        L_0x00f3:
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 <= 0) goto L_0x0110
            r18.ensureBottomGlow()
            android.widget.EdgeEffect r2 = r7.mBottomGlow
            int r5 = r18.getHeight()
            float r5 = (float) r5
            float r5 = r3 / r5
            int r10 = r18.getWidth()
            float r10 = (float) r10
            float r0 = r0 / r10
            float r6 = r6 - r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r2.onPull(r5, r6)
        L_0x010f:
            r2 = r11
        L_0x0110:
            if (r2 != 0) goto L_0x011a
            int r0 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x011a
            int r0 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x011d
        L_0x011a:
            android.support.p000v4.view.ViewCompat.postInvalidateOnAnimation(r18)
        L_0x011d:
            r18.considerReleasingGlowsOnScroll(r19, r20)
        L_0x0120:
            r0 = r17
            if (r15 != 0) goto L_0x0126
            if (r0 == 0) goto L_0x0129
        L_0x0126:
            r7.dispatchOnScrolled(r15, r0)
        L_0x0129:
            boolean r1 = r18.awakenScrollBars()
            if (r1 != 0) goto L_0x0132
            r18.invalidate()
        L_0x0132:
            if (r15 != 0) goto L_0x0136
            if (r0 == 0) goto L_0x0137
        L_0x0136:
            r12 = r11
        L_0x0137:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.scrollByInternal(int, int, android.view.MotionEvent):boolean");
    }

    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i) {
        if (!this.mLayoutFrozen) {
            stopScroll();
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            layoutManager.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!shouldDeferAccessibilityEvent(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, this.mAccessibilityDelegate);
    }

    public void setAdapter(Adapter adapter) {
        setLayoutFrozen(false);
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        removeAndRecycleViews();
        this.mAdapterHelper.reset();
        Adapter adapter3 = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged(adapter3, this.mAdapter);
        }
        Recycler recycler = this.mRecycler;
        Adapter adapter4 = this.mAdapter;
        recycler.clear();
        recycler.getRecycledViewPool().onAdapterChanged(adapter3, adapter4, false);
        this.mState.mStructureChanged = true;
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = childDrawingOrderCallback;
            setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder, int i) {
        if (isComputingLayout()) {
            viewHolder.mPendingAccessibilityState = i;
            this.mPendingAccessibilityImportanceChange.add(viewHolder);
            return false;
        }
        ViewCompat.setImportantForAccessibility(viewHolder.itemView, i);
        return true;
    }

    public void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public void setItemAnimator(ItemAnimator itemAnimator) {
        ItemAnimator itemAnimator2 = this.mItemAnimator;
        if (itemAnimator2 != null) {
            itemAnimator2.endAnimations();
            this.mItemAnimator.setListener((ItemAnimator.ItemAnimatorListener) null);
        }
        this.mItemAnimator = itemAnimator;
        ItemAnimator itemAnimator3 = this.mItemAnimator;
        if (itemAnimator3 != null) {
            itemAnimator3.setListener(this.mItemAnimatorListener);
        }
    }

    public void setLayoutFrozen(boolean z) {
        if (z != this.mLayoutFrozen) {
            assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (!z) {
                this.mLayoutFrozen = false;
                if (!(!this.mLayoutWasDefered || this.mLayout == null || this.mAdapter == null)) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.mLayoutFrozen = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager != this.mLayout) {
            stopScroll();
            if (this.mLayout != null) {
                ItemAnimator itemAnimator = this.mItemAnimator;
                if (itemAnimator != null) {
                    itemAnimator.endAnimations();
                }
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
                this.mRecycler.clear();
                if (this.mIsAttached) {
                    this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
                }
                this.mLayout.setRecyclerView((RecyclerView) null);
                this.mLayout = null;
            } else {
                this.mRecycler.clear();
            }
            ChildHelper childHelper = this.mChildHelper;
            childHelper.mBucket.reset();
            int size = childHelper.mHiddenViews.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                ((C02195) childHelper.mCallback).onLeftHiddenState(childHelper.mHiddenViews.get(size));
                childHelper.mHiddenViews.remove(size);
            }
            C02195 r0 = (C02195) childHelper.mCallback;
            int childCount = r0.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = r0.getChildAt(i);
                RecyclerView.this.dispatchChildDetached(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
            this.mLayout = layoutManager;
            if (layoutManager != null) {
                if (layoutManager.mRecyclerView == null) {
                    this.mLayout.setRecyclerView(this);
                    if (this.mIsAttached) {
                        this.mLayout.dispatchAttachedToWindow(this);
                    }
                } else {
                    throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView:" + layoutManager.mRecyclerView.exceptionLabel());
                }
            }
            this.mRecycler.updateViewCacheSize();
            requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().setNestedScrollingEnabled(z);
    }

    /* access modifiers changed from: package-private */
    public void setScrollState(int i) {
        if (i != this.mScrollState) {
            this.mScrollState = i;
            if (i != 2) {
                this.mViewFlinger.stop();
                LayoutManager layoutManager = this.mLayout;
                if (layoutManager != null) {
                    layoutManager.stopSmoothScroller();
                }
            }
            dispatchOnScrollStateChanged(i);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int i;
        if (!isComputingLayout()) {
            return false;
        }
        if (accessibilityEvent != null) {
            int i2 = Build.VERSION.SDK_INT;
            i = accessibilityEvent.getContentChangeTypes();
        } else {
            i = 0;
        }
        if (i == 0) {
            i = 0;
        }
        this.mEatenAccessibilityChangeFlags = i | this.mEatenAccessibilityChangeFlags;
        return true;
    }

    public void smoothScrollBy(int i, int i2) {
        smoothScrollBy(i, i2, (Interpolator) null);
    }

    public void smoothScrollToPosition(int i) {
        if (!this.mLayoutFrozen) {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                layoutManager.smoothScrollToPosition(this, this.mState, i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void startInterceptRequestLayout() {
        this.mInterceptRequestLayoutDepth++;
        if (this.mInterceptRequestLayoutDepth == 1 && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }
    }

    public boolean startNestedScroll(int i) {
        return getScrollingChildHelper().startNestedScroll(i, 0);
    }

    /* access modifiers changed from: package-private */
    public void stopInterceptRequestLayout(boolean z) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutFrozen) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll(0);
    }

    public void stopScroll() {
        setScrollState(0);
        this.mViewFlinger.stop();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.stopSmoothScroller();
        }
    }

    /* access modifiers changed from: package-private */
    public void viewRangeUpdate(int i, int i2, Object obj) {
        int i3;
        int i4;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < unfilteredChildCount; i6++) {
            View unfilteredChildAt = this.mChildHelper.getUnfilteredChildAt(i6);
            ViewHolder childViewHolderInt = getChildViewHolderInt(unfilteredChildAt);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i4 = childViewHolderInt.mPosition) >= i && i4 < i5) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((LayoutParams) unfilteredChildAt.getLayoutParams()).mInsetsDirty = true;
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        while (true) {
            size--;
            if (size >= 0) {
                ViewHolder viewHolder = recycler.mCachedViews.get(size);
                if (viewHolder != null && (i3 = viewHolder.mPosition) >= i && i3 < i5) {
                    viewHolder.addFlags(2);
                    recycler.recycleCachedViewAt(size);
                }
            } else {
                return;
            }
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* access modifiers changed from: package-private */
    public void onExitLayoutOrScroll(boolean z) {
        this.mLayoutOrScrollCounter--;
        if (this.mLayoutOrScrollCounter < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                int i = this.mEatenAccessibilityChangeFlags;
                this.mEatenAccessibilityChangeFlags = 0;
                if (i != 0 && isAccessibilityEnabled()) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain();
                    obtain.setEventType(2048);
                    int i2 = Build.VERSION.SDK_INT;
                    obtain.setContentChangeTypes(i);
                    sendAccessibilityEventUnchecked(obtain);
                }
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutFrozen) {
            if (!layoutManager.canScrollHorizontally()) {
                i = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                i2 = 0;
            }
            if (i != 0 || i2 != 0) {
                this.mViewFlinger.smoothScrollBy(i, i2, interpolator);
            }
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ClassLoader classLoader;
        Constructor<? extends U> constructor;
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() {
            public void run() {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
                    RecyclerView recyclerView2 = RecyclerView.this;
                    if (!recyclerView2.mIsAttached) {
                        recyclerView2.requestLayout();
                    } else if (recyclerView2.mLayoutFrozen) {
                        recyclerView2.mLayoutWasDefered = true;
                    } else {
                        recyclerView2.consumePendingUpdateOperations();
                    }
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new EdgeEffectFactory();
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        boolean z = true;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        Object[] objArr = null;
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mScrollStepConsumed = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable() {
            public void run() {
                ItemAnimator itemAnimator = RecyclerView.this.mItemAnimator;
                if (itemAnimator != null) {
                    itemAnimator.runPendingAnimations();
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {
            public void processPersistent(ViewHolder viewHolder, ItemAnimator.ItemHolderInfo itemHolderInfo, ItemAnimator.ItemHolderInfo itemHolderInfo2) {
                viewHolder.setIsRecyclable(false);
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mDataSetHasChangedAfterLayout) {
                    if (recyclerView.mItemAnimator.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                        RecyclerView.this.postAnimationRunner();
                    }
                } else if (recyclerView.mItemAnimator.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    RecyclerView.this.postAnimationRunner();
                }
            }
        };
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, CLIP_TO_PADDING_ATTR, i, 0);
            this.mClipToPadding = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        } else {
            this.mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context);
        int i2 = Build.VERSION.SDK_INT;
        this.mScaledVerticalScrollFactor = viewConfiguration.getScaledVerticalScrollFactor();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        initAdapterManager();
        this.mChildHelper = new ChildHelper(new ChildHelper.Callback() {
            public void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    if (childViewHolderInt.isTmpDetached() || childViewHolderInt.shouldIgnore()) {
                        childViewHolderInt.clearTmpDetachFlag();
                    } else {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt + RecyclerView.this.exceptionLabel());
                    }
                }
                RecyclerView.this.attachViewToParent(view, i, layoutParams);
            }

            public View getChildAt(int i) {
                return RecyclerView.this.getChildAt(i);
            }

            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }

            public ViewHolder getChildViewHolder(View view) {
                return RecyclerView.getChildViewHolderInt(view);
            }

            public void onEnteredHiddenState(View view) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    ViewHolder.access$200(childViewHolderInt, RecyclerView.this);
                }
            }

            public void onLeftHiddenState(View view) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    ViewHolder.access$300(childViewHolderInt, RecyclerView.this);
                }
            }

            public void removeViewAt(int i) {
                View childAt = RecyclerView.this.getChildAt(i);
                if (childAt != null) {
                    RecyclerView.this.dispatchChildDetached(childAt);
                    childAt.clearAnimation();
                }
                RecyclerView.this.removeViewAt(i);
            }
        });
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            int i3 = Build.VERSION.SDK_INT;
            setImportantForAutofill(8);
        }
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            int i4 = Build.VERSION.SDK_INT;
            setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, 0);
            String string = obtainStyledAttributes2.getString(7);
            if (obtainStyledAttributes2.getInt(1, -1) == -1) {
                setDescendantFocusability(262144);
            }
            this.mEnableFastScroller = obtainStyledAttributes2.getBoolean(2, false);
            if (this.mEnableFastScroller) {
                initFastScroller((StateListDrawable) obtainStyledAttributes2.getDrawable(5), obtainStyledAttributes2.getDrawable(6), (StateListDrawable) obtainStyledAttributes2.getDrawable(3), obtainStyledAttributes2.getDrawable(4));
            }
            obtainStyledAttributes2.recycle();
            if (string != null) {
                String trim = string.trim();
                if (!trim.isEmpty()) {
                    if (trim.charAt(0) == '.') {
                        trim = context.getPackageName() + trim;
                    } else if (!trim.contains(".")) {
                        trim = RecyclerView.class.getPackage().getName() + '.' + trim;
                    }
                    try {
                        if (isInEditMode()) {
                            classLoader = getClass().getClassLoader();
                        } else {
                            classLoader = context.getClassLoader();
                        }
                        Class<? extends U> asSubclass = classLoader.loadClass(trim).asSubclass(LayoutManager.class);
                        try {
                            constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                            objArr = new Object[]{context, attributeSet, Integer.valueOf(i), 0};
                        } catch (NoSuchMethodException e) {
                            constructor = asSubclass.getConstructor(new Class[0]);
                        }
                        constructor.setAccessible(true);
                        setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                    } catch (NoSuchMethodException e2) {
                        e2.initCause(e);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + trim, e2);
                    } catch (ClassNotFoundException e3) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + trim, e3);
                    } catch (InvocationTargetException e4) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + trim, e4);
                    } catch (InstantiationException e5) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + trim, e5);
                    } catch (IllegalAccessException e6) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + trim, e6);
                    } catch (ClassCastException e7) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + trim, e7);
                    }
                }
            }
            int i5 = Build.VERSION.SDK_INT;
            TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, NESTED_SCROLLING_ATTRS, i, 0);
            z = obtainStyledAttributes3.getBoolean(0, true);
            obtainStyledAttributes3.recycle();
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(z);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return getScrollingChildHelper().dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    public boolean hasNestedScrollingParent(int i) {
        return getScrollingChildHelper().hasNestedScrollingParent(i);
    }

    public boolean startNestedScroll(int i, int i2) {
        return getScrollingChildHelper().startNestedScroll(i, i2);
    }

    public void stopNestedScroll(int i) {
        getScrollingChildHelper().stopNestedScroll(i);
    }

    /* renamed from: android.support.v7.widget.RecyclerView$LayoutParams */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        final Rect mDecorInsets = new Rect();
        boolean mInsetsDirty = true;
        boolean mPendingInvalidate = false;
        ViewHolder mViewHolder;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* renamed from: android.support.v7.widget.RecyclerView$SavedState */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        Parcelable mLayoutState;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mLayoutState = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        /* access modifiers changed from: package-private */
        public void copyFrom(SavedState savedState) {
            this.mLayoutState = savedState.mLayoutState;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.mLayoutState, 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("RecyclerView has no LayoutManager");
        outline13.append(exceptionLabel());
        throw new IllegalStateException(outline13.toString());
    }

    public void addItemDecoration(ItemDecoration itemDecoration) {
        addItemDecoration(itemDecoration, -1);
    }
}
