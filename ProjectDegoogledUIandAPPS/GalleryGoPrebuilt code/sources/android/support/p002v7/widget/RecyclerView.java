package android.support.p002v7.widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
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
import com.google.android.apps.photosgo.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.RecyclerView */
/* compiled from: PG */
public class RecyclerView extends ViewGroup implements C0324lu {
    public static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    public static final boolean ALLOW_THREAD_GAP_WORK = true;
    public static final boolean DEBUG = false;
    public static final int DEFAULT_ORIENTATION = 1;
    public static final boolean DISPATCH_TEMP_DETACH = false;
    public static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    public static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    public static final long FOREVER_NS = Long.MAX_VALUE;
    public static final int HORIZONTAL = 0;
    public static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;
    public static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    public static final Class[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
    public static final int MAX_SCROLL_DURATION = 2000;
    public static final int[] NESTED_SCROLLING_ATTRS = {16843830};
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final boolean POST_UPDATES_ON_ANIMATION = true;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    public static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    public static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    public static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    public static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    public static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    public static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    public static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    public static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    public static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    public static final Interpolator sQuinticInterpolator = new C0630xc();
    public C0669yo mAccessibilityDelegate;
    public final AccessibilityManager mAccessibilityManager;
    public C0634xg mAdapter;
    public C0521tb mAdapterHelper;
    public boolean mAdapterUpdateDuringMeasure;
    public EdgeEffect mBottomGlow;
    public C0636xi mChildDrawingOrderCallback;
    public C0563uq mChildHelper;
    public boolean mClipToPadding;
    public boolean mDataSetHasChangedAfterLayout;
    public boolean mDispatchItemsChangedEvent;
    public int mDispatchScrollCounter;
    public int mEatenAccessibilityChangeFlags;
    public C0637xj mEdgeEffectFactory;
    public boolean mEnableFastScroller;
    public boolean mFirstLayoutComplete;
    public C0594vu mGapWorker;
    public boolean mHasFixedSize;
    public boolean mIgnoreMotionEventTillDown;
    public int mInitialTouchX;
    public int mInitialTouchY;
    public int mInterceptRequestLayoutDepth;
    public C0651xx mInterceptingOnItemTouchListener;
    public boolean mIsAttached;
    public C0641xn mItemAnimator;
    public C0639xl mItemAnimatorListener;
    public Runnable mItemAnimatorRunner;
    public final ArrayList mItemDecorations;
    public boolean mItemsAddedOrRemoved;
    public boolean mItemsChanged;
    public int mLastTouchX;
    public int mLastTouchY;
    public C0647xt mLayout;
    public int mLayoutOrScrollCounter;
    public boolean mLayoutSuppressed;
    public boolean mLayoutWasDefered;
    public EdgeEffect mLeftGlow;
    public final int mMaxFlingVelocity;
    public final int mMinFlingVelocity;
    public final int[] mMinMaxLayoutPositions;
    public final int[] mNestedOffsets;
    public final C0658yd mObserver;
    public List mOnChildAttachStateListeners;
    public C0650xw mOnFlingListener;
    public final ArrayList mOnItemTouchListeners;
    public final List mPendingAccessibilityImportanceChange;
    public C0660yf mPendingSavedState;
    public boolean mPostedAnimatorRunner;
    public C0592vs mPrefetchRegistry;
    public boolean mPreserveFocusAfterLayout;
    public final C0656yb mRecycler;
    public C0657yc mRecyclerListener;
    public final int[] mReusableIntPair;
    public EdgeEffect mRightGlow;
    public float mScaledHorizontalScrollFactor;
    public float mScaledVerticalScrollFactor;
    public C0652xy mScrollListener;
    public List mScrollListeners;
    public final int[] mScrollOffset;
    public int mScrollPointerId;
    public int mScrollState;
    public C0325lv mScrollingChildHelper;
    public final C0664yj mState;
    public final Rect mTempRect;
    public final Rect mTempRect2;
    public final RectF mTempRectF;
    public EdgeEffect mTopGlow;
    public int mTouchSlop;
    public final Runnable mUpdateChildViewsRunnable;
    public VelocityTracker mVelocityTracker;
    public final C0666yl mViewFlinger;
    public final C0701zt mViewInfoProcessCallback;
    public final C0702zu mViewInfoStore;

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
        int i3 = Build.VERSION.SDK_INT;
        int i4 = Build.VERSION.SDK_INT;
        int i5 = Build.VERSION.SDK_INT;
        int i6 = Build.VERSION.SDK_INT;
        int i7 = Build.VERSION.SDK_INT;
        int i8 = Build.VERSION.SDK_INT;
    }

    public CharSequence getAccessibilityClassName() {
        return "android.support.v7.widget.RecyclerView";
    }

    public C0634xg getAdapter() {
        return this.mAdapter;
    }

    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public C0669yo getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public C0637xj getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public C0641xn getItemAnimator() {
        return this.mItemAnimator;
    }

    public C0647xt getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public C0650xw getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    public void onScrollStateChanged(int i) {
    }

    public void onScrolled(int i, int i2) {
    }

    public RecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        C0592vs vsVar;
        boolean z;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        int i2 = i;
        this.mObserver = new C0658yd(this);
        this.mRecycler = new C0656yb(this);
        this.mViewInfoStore = new C0702zu();
        this.mUpdateChildViewsRunnable = new C0628xa(this);
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new C0637xj();
        this.mItemAnimator = new C0578ve();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new C0666yl(this);
        if (ALLOW_THREAD_GAP_WORK) {
            vsVar = new C0592vs();
        } else {
            vsVar = null;
        }
        this.mPrefetchRegistry = vsVar;
        this.mState = new C0664yj();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new C0642xo(this);
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new C0629xb(this);
        this.mViewInfoProcessCallback = new C0631xd(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = C0341mk.m14736a(viewConfiguration);
        this.mScaledVerticalScrollFactor = C0341mk.m14738b(viewConfiguration);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        if (getOverScrollMode() == 2) {
            z = true;
        } else {
            z = false;
        }
        setWillNotDraw(z);
        this.mItemAnimator.f16292h = this.mItemAnimatorListener;
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        if (C0340mj.m14712e(this) == 0) {
            C0340mj.m14689a((View) this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new C0669yo(this));
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, C0439qa.f15600a, i2, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, C0439qa.f15600a, attributeSet, obtainStyledAttributes, i, 0);
        }
        String string = obtainStyledAttributes.getString(8);
        if (obtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(1, true);
        boolean z2 = obtainStyledAttributes.getBoolean(3, false);
        this.mEnableFastScroller = z2;
        if (z2) {
            initFastScroller((StateListDrawable) obtainStyledAttributes.getDrawable(6), obtainStyledAttributes.getDrawable(7), (StateListDrawable) obtainStyledAttributes.getDrawable(4), obtainStyledAttributes.getDrawable(5));
        }
        obtainStyledAttributes.recycle();
        createLayoutManager(context, string, attributeSet, i, 0);
        int i3 = Build.VERSION.SDK_INT;
        TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet2, NESTED_SCROLLING_ATTRS, i2, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, NESTED_SCROLLING_ATTRS, attributeSet, obtainStyledAttributes2, i, 0);
        }
        boolean z3 = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z3);
    }

    public void absorbGlows(int i, int i2) {
        if (i < 0) {
            ensureLeftGlow();
            if (this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-i);
            }
        } else if (i > 0) {
            ensureRightGlow();
            if (this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(i);
            }
        }
        if (i2 < 0) {
            ensureTopGlow();
            if (this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            ensureBottomGlow();
            if (this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(i2);
            }
        }
        if (i != 0 || i2 != 0) {
            C0340mj.m14710d(this);
        }
    }

    private void addAnimatingView(C0667ym ymVar) {
        View view = ymVar.f16382a;
        ViewParent parent = view.getParent();
        this.mRecycler.mo10612b(getChildViewHolder(view));
        if (ymVar.mo10654n()) {
            this.mChildHelper.mo10312a(view, -1, view.getLayoutParams(), true);
        } else if (parent == this) {
            C0563uq uqVar = this.mChildHelper;
            int a = uqVar.f16027a.mo10305a(view);
            if (a >= 0) {
                uqVar.f16028b.mo10297a(a);
                uqVar.mo10311a(view);
                return;
            }
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else {
            this.mChildHelper.mo10313a(view, -1, true);
        }
    }

    public void addFocusables(ArrayList arrayList, int i, int i2) {
        super.addFocusables(arrayList, i, i2);
    }

    public void addItemDecoration(C0643xp xpVar) {
        addItemDecoration(xpVar, -1);
    }

    public void addItemDecoration(C0643xp xpVar, int i) {
        C0647xt xtVar = this.mLayout;
        if (xtVar != null) {
            xtVar.mo10465a("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i < 0) {
            this.mItemDecorations.add(xpVar);
        } else {
            this.mItemDecorations.add(i, xpVar);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(C0649xv xvVar) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(xvVar);
    }

    public void addOnItemTouchListener(C0651xx xxVar) {
        this.mOnItemTouchListeners.add(xxVar);
    }

    public void addOnScrollListener(C0652xy xyVar) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(xyVar);
    }

    public void animateAppearance(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        ymVar.mo10639a(false);
        if (this.mItemAnimator.mo10555b(ymVar, xmVar, xmVar2)) {
            postAnimationRunner();
        }
    }

    private void animateChange(C0667ym ymVar, C0667ym ymVar2, C0640xm xmVar, C0640xm xmVar2, boolean z, boolean z2) {
        ymVar.mo10639a(false);
        if (z) {
            addAnimatingView(ymVar);
        }
        if (ymVar != ymVar2) {
            if (z2) {
                addAnimatingView(ymVar2);
            }
            ymVar.f16389h = ymVar2;
            addAnimatingView(ymVar);
            this.mRecycler.mo10612b(ymVar);
            ymVar2.mo10639a(false);
            ymVar2.f16390i = ymVar;
        }
        if (this.mItemAnimator.mo10554a(ymVar, ymVar2, xmVar, xmVar2)) {
            postAnimationRunner();
        }
    }

    public void animateDisappearance(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        addAnimatingView(ymVar);
        ymVar.mo10639a(false);
        if (this.mItemAnimator.mo4643a(ymVar, xmVar, xmVar2)) {
            postAnimationRunner();
        }
    }

    /* access modifiers changed from: package-private */
    public void assertInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + exceptionLabel());
        }
        throw new IllegalStateException(str + exceptionLabel());
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (!isComputingLayout()) {
            if (this.mDispatchScrollCounter > 0) {
                Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + exceptionLabel()));
            }
        } else if (str == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + exceptionLabel());
        } else {
            throw new IllegalStateException(str);
        }
    }

    public boolean canReuseUpdatedViewHolder(C0667ym ymVar) {
        C0641xn xnVar = this.mItemAnimator;
        return xnVar == null || xnVar.mo10368a(ymVar, ymVar.mo10657q());
    }

    private void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0648xu) && this.mLayout.mo10432a((C0648xu) layoutParams);
    }

    public static void clearNestedRecyclerViewIfNotNested(C0667ym ymVar) {
        WeakReference weakReference = ymVar.f16383b;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view != ymVar.f16382a) {
                    ViewParent parent = view.getParent();
                    if (!(parent instanceof View)) {
                        view = null;
                    } else {
                        view = (View) parent;
                    }
                } else {
                    return;
                }
            }
            ymVar.f16383b = null;
        }
    }

    public void clearOldPositions() {
        int b = this.mChildHelper.mo10314b();
        for (int i = 0; i < b; i++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i));
            if (!childViewHolderInt.mo10642b()) {
                childViewHolderInt.mo10634a();
            }
        }
        C0656yb ybVar = this.mRecycler;
        int size = ybVar.f16326c.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((C0667ym) ybVar.f16326c.get(i2)).mo10634a();
        }
        int size2 = ybVar.f16324a.size();
        for (int i3 = 0; i3 < size2; i3++) {
            ((C0667ym) ybVar.f16324a.get(i3)).mo10634a();
        }
        ArrayList arrayList = ybVar.f16325b;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i4 = 0; i4 < size3; i4++) {
                ((C0667ym) ybVar.f16325b.get(i4)).mo10634a();
            }
        }
    }

    public void clearOnChildAttachStateChangeListeners() {
        List list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    public int computeHorizontalScrollExtent() {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null || !xtVar.mo2620i()) {
            return 0;
        }
        return this.mLayout.mo10472e(this.mState);
    }

    public int computeHorizontalScrollOffset() {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null || !xtVar.mo2620i()) {
            return 0;
        }
        return this.mLayout.mo10467c(this.mState);
    }

    public int computeHorizontalScrollRange() {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null || !xtVar.mo2620i()) {
            return 0;
        }
        return this.mLayout.mo10474g(this.mState);
    }

    public int computeVerticalScrollExtent() {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null || !xtVar.mo10477j()) {
            return 0;
        }
        return this.mLayout.mo10473f(this.mState);
    }

    public int computeVerticalScrollOffset() {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null || !xtVar.mo10477j()) {
            return 0;
        }
        return this.mLayout.mo10469d(this.mState);
    }

    public int computeVerticalScrollRange() {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null || !xtVar.mo10477j()) {
            return 0;
        }
        return this.mLayout.mo10475h(this.mState);
    }

    public void considerReleasingGlowsOnScroll(int i, int i2) {
        EdgeEffect edgeEffect = this.mLeftGlow;
        boolean z = false;
        if (edgeEffect != null && !edgeEffect.isFinished() && i > 0) {
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
            C0340mj.m14710d(this);
        }
    }

    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            C0264jo.m14493a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            dispatchLayout();
            C0264jo.m14492a();
        } else if (!this.mAdapterHelper.mo10093d()) {
        } else {
            if (this.mAdapterHelper.mo10089a(4) && !this.mAdapterHelper.mo10089a(11)) {
                C0264jo.m14493a(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
                startInterceptRequestLayout();
                onEnterLayoutOrScroll();
                this.mAdapterHelper.mo10091b();
                if (!this.mLayoutWasDefered) {
                    if (hasUpdatedView()) {
                        dispatchLayout();
                    } else {
                        this.mAdapterHelper.mo10092c();
                    }
                }
                stopInterceptRequestLayout(true);
                onExitLayoutOrScroll();
                C0264jo.m14492a();
            } else if (this.mAdapterHelper.mo10093d()) {
                C0264jo.m14493a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                dispatchLayout();
                C0264jo.m14492a();
            }
        }
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i, int i2) {
        ClassLoader classLoader;
        Object[] objArr;
        Constructor<? extends U> constructor;
        if (str != null) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                String fullClassName = getFullClassName(context, trim);
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> asSubclass = Class.forName(fullClassName, false, classLoader).asSubclass(C0647xt.class);
                    try {
                        constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), Integer.valueOf(i2)};
                    } catch (NoSuchMethodException e) {
                        constructor = asSubclass.getConstructor(new Class[0]);
                        objArr = null;
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((C0647xt) constructor.newInstance(objArr));
                } catch (NoSuchMethodException e2) {
                    e2.initCause(e);
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, e2);
                } catch (ClassNotFoundException e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, e3);
                } catch (InvocationTargetException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e4);
                } catch (InstantiationException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e5);
                } catch (IllegalAccessException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, e6);
                } catch (ClassCastException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, e7);
                }
            }
        }
    }

    public void defaultOnMeasure(int i, int i2) {
        setMeasuredDimension(C0647xt.m15964a(i, getPaddingLeft() + getPaddingRight(), C0340mj.m14718i(this)), C0647xt.m15964a(i2, getPaddingTop() + getPaddingBottom(), C0340mj.m14719j(this)));
    }

    private boolean didChildRangeChange(int i, int i2) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        return (iArr[0] == i && iArr[1] == i2) ? false : true;
    }

    public void dispatchChildAttached(View view) {
        getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        List list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0649xv) this.mOnChildAttachStateListeners.get(size)).mo10597a();
            }
        }
    }

    public void dispatchChildDetached(View view) {
        getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        List list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0649xv) this.mOnChildAttachStateListeners.get(size)).mo10598b();
            }
        }
    }

    private void dispatchContentChangedIfNecessary() {
        int i = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i != 0 && isAccessibilityEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            C0350mt.m14765a(obtain, i);
            sendAccessibilityEventUnchecked(obtain);
        }
    }

    public void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e(TAG, "No adapter attached; skipping layout");
        } else if (this.mLayout != null) {
            C0664yj yjVar = this.mState;
            yjVar.f16366i = false;
            if (yjVar.f16361d == 1) {
                dispatchLayoutStep1();
                this.mLayout.mo10575b(this);
                dispatchLayoutStep2();
            } else {
                C0521tb tbVar = this.mAdapterHelper;
                if ((tbVar.f15905b.isEmpty() || tbVar.f15904a.isEmpty()) && this.mLayout.f16310u == getWidth() && this.mLayout.f16311v == getHeight()) {
                    this.mLayout.mo10575b(this);
                } else {
                    this.mLayout.mo10575b(this);
                    dispatchLayoutStep2();
                }
            }
            dispatchLayoutStep3();
        } else {
            Log.e(TAG, "No layout manager attached; skipping layout");
        }
    }

    private void dispatchLayoutStep1() {
        C0700zs zsVar;
        boolean z = true;
        this.mState.mo10627a(1);
        fillRemainingScrollValues(this.mState);
        this.mState.f16366i = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.mo10752a();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        C0664yj yjVar = this.mState;
        if (!yjVar.f16367j || !this.mItemsChanged) {
            z = false;
        }
        yjVar.f16365h = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        yjVar.f16364g = yjVar.f16368k;
        yjVar.f16362e = this.mAdapter.mo220a();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.f16367j) {
            int a = this.mChildHelper.mo10309a();
            for (int i = 0; i < a; i++) {
                C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10316b(i));
                if (!childViewHolderInt.mo10642b() && (!childViewHolderInt.mo10650j() || this.mAdapter.f16288b)) {
                    C0641xn.m15920f(childViewHolderInt);
                    childViewHolderInt.mo10657q();
                    this.mViewInfoStore.mo10754a(childViewHolderInt, C0641xn.m15922g(childViewHolderInt));
                    if (this.mState.f16365h && childViewHolderInt.mo10660t() && !childViewHolderInt.mo10653m() && !childViewHolderInt.mo10642b() && !childViewHolderInt.mo10650j()) {
                        this.mViewInfoStore.mo10753a(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.f16368k) {
            saveOldPositions();
            C0664yj yjVar2 = this.mState;
            boolean z2 = yjVar2.f16363f;
            yjVar2.f16363f = false;
            this.mLayout.mo4533c(this.mRecycler, yjVar2);
            this.mState.f16363f = z2;
            for (int i2 = 0; i2 < this.mChildHelper.mo10309a(); i2++) {
                C0667ym childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.mo10316b(i2));
                if (!childViewHolderInt2.mo10642b() && ((zsVar = (C0700zs) this.mViewInfoStore.f16478a.get(childViewHolderInt2)) == null || (zsVar.f16475a & 4) == 0)) {
                    C0641xn.m15920f(childViewHolderInt2);
                    boolean a2 = childViewHolderInt2.mo10640a(8192);
                    childViewHolderInt2.mo10657q();
                    C0640xm g = C0641xn.m15922g(childViewHolderInt2);
                    if (!a2) {
                        C0702zu zuVar = this.mViewInfoStore;
                        C0700zs zsVar2 = (C0700zs) zuVar.f16478a.get(childViewHolderInt2);
                        if (zsVar2 == null) {
                            zsVar2 = C0700zs.m16263a();
                            zuVar.f16478a.put(childViewHolderInt2, zsVar2);
                        }
                        zsVar2.f16475a |= 2;
                        zsVar2.f16476b = g;
                    } else {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, g);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.f16361d = 2;
    }

    private void dispatchLayoutStep2() {
        boolean z;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.mo10627a(6);
        this.mAdapterHelper.mo10094e();
        this.mState.f16362e = this.mAdapter.mo220a();
        C0664yj yjVar = this.mState;
        yjVar.f16360c = 0;
        yjVar.f16364g = false;
        this.mLayout.mo4533c(this.mRecycler, yjVar);
        C0664yj yjVar2 = this.mState;
        yjVar2.f16363f = false;
        this.mPendingSavedState = null;
        if (!yjVar2.f16367j || this.mItemAnimator == null) {
            z = false;
        } else {
            z = true;
        }
        yjVar2.f16367j = z;
        yjVar2.f16361d = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        this.mState.mo10627a(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        C0664yj yjVar = this.mState;
        yjVar.f16361d = 1;
        if (yjVar.f16367j) {
            for (int a = this.mChildHelper.mo10309a() - 1; a >= 0; a--) {
                C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10316b(a));
                if (!childViewHolderInt.mo10642b()) {
                    long changedHolderKey = getChangedHolderKey(childViewHolderInt);
                    C0640xm g = C0641xn.m15921g();
                    g.mo10553a(childViewHolderInt);
                    C0667ym ymVar = (C0667ym) this.mViewInfoStore.f16479b.mo9229a(changedHolderKey);
                    if (ymVar == null || ymVar.mo10642b()) {
                        this.mViewInfoStore.mo10757b(childViewHolderInt, g);
                    } else {
                        boolean a2 = this.mViewInfoStore.mo10755a(ymVar);
                        boolean a3 = this.mViewInfoStore.mo10755a(childViewHolderInt);
                        if (a2 && ymVar == childViewHolderInt) {
                            this.mViewInfoStore.mo10757b(childViewHolderInt, g);
                        } else {
                            C0640xm a4 = this.mViewInfoStore.mo10751a(ymVar, 4);
                            this.mViewInfoStore.mo10757b(childViewHolderInt, g);
                            C0640xm a5 = this.mViewInfoStore.mo10751a(childViewHolderInt, 8);
                            if (a4 == null) {
                                handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, ymVar);
                            } else {
                                animateChange(ymVar, childViewHolderInt, a4, a5, a2, a3);
                            }
                        }
                    }
                }
            }
            C0702zu zuVar = this.mViewInfoStore;
            C0701zt ztVar = this.mViewInfoProcessCallback;
            for (int i = zuVar.f16478a.f15193b - 1; i >= 0; i--) {
                C0667ym ymVar2 = (C0667ym) zuVar.f16478a.mo9320b(i);
                C0700zs zsVar = (C0700zs) zuVar.f16478a.mo1935d(i);
                int i2 = zsVar.f16475a;
                if ((i2 & 3) == 3) {
                    ztVar.mo10529a(ymVar2);
                } else if ((i2 & 1) != 0) {
                    C0640xm xmVar = zsVar.f16476b;
                    if (xmVar == null) {
                        ztVar.mo10529a(ymVar2);
                    } else {
                        ztVar.mo10530a(ymVar2, xmVar, zsVar.f16477c);
                    }
                } else if ((i2 & 14) == 14) {
                    ztVar.mo10531b(ymVar2, zsVar.f16476b, zsVar.f16477c);
                } else if ((i2 & 12) == 12) {
                    ztVar.mo10532c(ymVar2, zsVar.f16476b, zsVar.f16477c);
                } else if ((i2 & 4) != 0) {
                    ztVar.mo10530a(ymVar2, zsVar.f16476b, (C0640xm) null);
                } else if ((i2 & 8) != 0) {
                    ztVar.mo10531b(ymVar2, zsVar.f16476b, zsVar.f16477c);
                }
                C0700zs.m16264a(zsVar);
            }
        }
        this.mLayout.mo10571a(this.mRecycler);
        C0664yj yjVar2 = this.mState;
        yjVar2.f16359b = yjVar2.f16362e;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        yjVar2.f16367j = false;
        yjVar2.f16368k = false;
        this.mLayout.f16303n = false;
        ArrayList arrayList = this.mRecycler.f16325b;
        if (arrayList != null) {
            arrayList.clear();
        }
        C0647xt xtVar = this.mLayout;
        if (xtVar.f16307r) {
            xtVar.f16306q = 0;
            xtVar.f16307r = false;
            this.mRecycler.mo10609b();
        }
        this.mLayout.mo10430a(this.mState);
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mViewInfoStore.mo10752a();
        int[] iArr = this.mMinMaxLayoutPositions;
        if (didChildRangeChange(iArr[0], iArr[1])) {
            dispatchOnScrolled(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().mo9372a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().mo9371a(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().mo9377a(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return getScrollingChildHelper().mo9377a(i, i2, iArr, iArr2, i3);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().mo9375a(i, i2, i3, i4, iArr);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return getScrollingChildHelper().mo9376a(i, i2, i3, i4, iArr, i5, (int[]) null);
    }

    public final void dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        getScrollingChildHelper().mo9376a(i, i2, i3, i4, iArr, i5, iArr2);
    }

    public void dispatchOnScrollStateChanged(int i) {
        onScrollStateChanged(i);
        C0652xy xyVar = this.mScrollListener;
        if (xyVar != null) {
            xyVar.mo4639a(this, i);
        }
        List list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0652xy) this.mScrollListeners.get(size)).mo4639a(this, i);
            }
        }
    }

    public void dispatchOnScrolled(int i, int i2) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        onScrolled(i, i2);
        C0652xy xyVar = this.mScrollListener;
        if (xyVar != null) {
            xyVar.mo4654a(this, i, i2);
        }
        List list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0652xy) this.mScrollListeners.get(size)).mo4654a(this, i, i2);
            }
        }
        this.mDispatchScrollCounter--;
    }

    public void dispatchPendingImportantForAccessibilityChanges() {
        int i;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            C0667ym ymVar = (C0667ym) this.mPendingAccessibilityImportanceChange.get(size);
            if (ymVar.f16382a.getParent() == this && !ymVar.mo10642b() && (i = ymVar.f16395n) != -1) {
                C0340mj.m14689a(ymVar.f16382a, i);
                ymVar.f16395n = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    private boolean dispatchToOnItemTouchListeners(MotionEvent motionEvent) {
        C0651xx xxVar = this.mInterceptingOnItemTouchListener;
        if (xxVar != null) {
            xxVar.mo10396b(motionEvent);
            int action = motionEvent.getAction();
            if (action == 3 || action == 1) {
                this.mInterceptingOnItemTouchListener = null;
            }
            return true;
        } else if (motionEvent.getAction() != 0) {
            return findInterceptingOnItemTouchListener(motionEvent);
        } else {
            return false;
        }
    }

    public void draw(Canvas canvas) {
        boolean z;
        boolean z2;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z3 = false;
        for (int i = 0; i < size; i++) {
            ((C0643xp) this.mItemDecorations.get(i)).mo198a(canvas, this);
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
            if (edgeEffect4 == null || !edgeEffect4.draw(canvas)) {
                z2 = false;
            } else {
                z2 = true;
            }
            z |= z2;
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
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((float) ((-getWidth()) + getPaddingRight()), (float) ((-getHeight()) + getPaddingBottom()));
            } else {
                canvas.translate((float) (-getWidth()), (float) (-getHeight()));
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z |= z3;
            canvas.restoreToCount(save4);
        }
        if (z || (this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.mo10370b())) {
            C0340mj.m14710d(this);
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            EdgeEffect a = C0637xj.m15892a(this);
            this.mBottomGlow = a;
            if (this.mClipToPadding) {
                a.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                a.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            EdgeEffect a = C0637xj.m15892a(this);
            this.mLeftGlow = a;
            if (this.mClipToPadding) {
                a.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                a.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public void ensureRightGlow() {
        if (this.mRightGlow == null) {
            EdgeEffect a = C0637xj.m15892a(this);
            this.mRightGlow = a;
            if (this.mClipToPadding) {
                a.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                a.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    public void ensureTopGlow() {
        if (this.mTopGlow == null) {
            EdgeEffect a = C0637xj.m15892a(this);
            this.mTopGlow = a;
            if (this.mClipToPadding) {
                a.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                a.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    public String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    /* access modifiers changed from: package-private */
    public final void fillRemainingScrollValues(C0664yj yjVar) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.mViewFlinger.f16376c;
            yjVar.f16372o = overScroller.getFinalX() - overScroller.getCurrX();
            yjVar.f16373p = overScroller.getFinalY() - overScroller.getCurrY();
            return;
        }
        yjVar.f16372o = 0;
        yjVar.f16373p = 0;
    }

    public View findChildViewUnder(float f, float f2) {
        for (int a = this.mChildHelper.mo10309a() - 1; a >= 0; a--) {
            View b = this.mChildHelper.mo10316b(a);
            float translationX = b.getTranslationX();
            float translationY = b.getTranslationY();
            if (f >= ((float) b.getLeft()) + translationX && f <= ((float) b.getRight()) + translationX && f2 >= ((float) b.getTop()) + translationY && f2 <= ((float) b.getBottom()) + translationY) {
                return b;
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
        if (parent != this) {
            return null;
        }
        return view;
    }

    public C0667ym findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView != null) {
            return getChildViewHolder(findContainingItemView);
        }
        return null;
    }

    private boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            C0651xx xxVar = (C0651xx) this.mOnItemTouchListeners.get(i);
            if (xxVar.mo10394a(motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = xxVar;
                return true;
            }
        }
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int a = this.mChildHelper.mo10309a();
        if (a != 0) {
            int i = Integer.MAX_VALUE;
            int i2 = UNDEFINED_DURATION;
            for (int i3 = 0; i3 < a; i3++) {
                C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10316b(i3));
                if (!childViewHolderInt.mo10642b()) {
                    int c = childViewHolderInt.mo10643c();
                    if (c < i) {
                        i = c;
                    }
                    if (c > i2) {
                        i2 = c;
                    }
                }
            }
            iArr[0] = i;
            iArr[1] = i2;
            return;
        }
        iArr[0] = -1;
        iArr[1] = -1;
    }

    public static RecyclerView findNestedRecyclerView(View view) {
        if (view instanceof ViewGroup) {
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
        }
        return null;
    }

    private View findNextViewToFocus() {
        C0667ym findViewHolderForAdapterPosition;
        C0664yj yjVar = this.mState;
        int i = yjVar.f16369l;
        if (i == -1) {
            i = 0;
        }
        int a = yjVar.mo10626a();
        int i2 = i;
        while (i2 < a) {
            C0667ym findViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i2);
            if (findViewHolderForAdapterPosition2 == null) {
                break;
            } else if (findViewHolderForAdapterPosition2.f16382a.hasFocusable()) {
                return findViewHolderForAdapterPosition2.f16382a;
            } else {
                i2++;
            }
        }
        int min = Math.min(a, i) - 1;
        while (min >= 0 && (findViewHolderForAdapterPosition = findViewHolderForAdapterPosition(min)) != null) {
            if (findViewHolderForAdapterPosition.f16382a.hasFocusable()) {
                return findViewHolderForAdapterPosition.f16382a;
            }
            min--;
        }
        return null;
    }

    public C0667ym findViewHolderForAdapterPosition(int i) {
        C0667ym ymVar = null;
        if (!this.mDataSetHasChangedAfterLayout) {
            int b = this.mChildHelper.mo10314b();
            for (int i2 = 0; i2 < b; i2++) {
                C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i2));
                if (childViewHolderInt != null && !childViewHolderInt.mo10653m() && getAdapterPositionFor(childViewHolderInt) == i) {
                    if (!this.mChildHelper.mo10318c(childViewHolderInt.f16382a)) {
                        return childViewHolderInt;
                    }
                    ymVar = childViewHolderInt;
                }
            }
        }
        return ymVar;
    }

    public C0667ym findViewHolderForItemId(long j) {
        C0634xg xgVar = this.mAdapter;
        C0667ym ymVar = null;
        if (xgVar != null && xgVar.f16288b) {
            int b = this.mChildHelper.mo10314b();
            for (int i = 0; i < b; i++) {
                C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i));
                if (childViewHolderInt != null && !childViewHolderInt.mo10653m() && childViewHolderInt.f16386e == j) {
                    if (!this.mChildHelper.mo10318c(childViewHolderInt.f16382a)) {
                        return childViewHolderInt;
                    }
                    ymVar = childViewHolderInt;
                }
            }
        }
        return ymVar;
    }

    public C0667ym findViewHolderForLayoutPosition(int i) {
        return findViewHolderForPosition(i, false);
    }

    @Deprecated
    public C0667ym findViewHolderForPosition(int i) {
        return findViewHolderForPosition(i, false);
    }

    public C0667ym findViewHolderForPosition(int i, boolean z) {
        int b = this.mChildHelper.mo10314b();
        C0667ym ymVar = null;
        for (int i2 = 0; i2 < b; i2++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i2));
            if (childViewHolderInt != null && !childViewHolderInt.mo10653m()) {
                if (!z) {
                    if (childViewHolderInt.mo10643c() != i) {
                        continue;
                    }
                } else if (childViewHolderInt.f16384c != i) {
                    continue;
                }
                if (!this.mChildHelper.mo10318c(childViewHolderInt.f16382a)) {
                    return childViewHolderInt;
                }
                ymVar = childViewHolderInt;
            }
        }
        return ymVar;
    }

    public boolean fling(int i, int i2) {
        int i3;
        C0647xt layoutManager;
        int minFlingVelocity;
        C0663yi b;
        int a;
        C0647xt xtVar = this.mLayout;
        if (xtVar == null) {
            Log.e(TAG, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.mLayoutSuppressed) {
            return false;
        } else {
            boolean i4 = xtVar.mo2620i();
            boolean j = this.mLayout.mo10477j();
            int i5 = (!i4 || Math.abs(i) < this.mMinFlingVelocity) ? 0 : i;
            if (!j || Math.abs(i2) < this.mMinFlingVelocity) {
                i3 = 0;
            } else {
                i3 = i2;
            }
            if (i5 == 0 && i3 == 0) {
                return false;
            }
            float f = (float) i5;
            float f2 = (float) i3;
            if (dispatchNestedPreFling(f, f2)) {
                return false;
            }
            dispatchNestedFling(f, f2, true);
            C0650xw xwVar = this.mOnFlingListener;
            if (xwVar == null || (layoutManager = xwVar.f16316a.getLayoutManager()) == null || xwVar.f16316a.getAdapter() == null || ((Math.abs(i3) <= (minFlingVelocity = xwVar.f16316a.getMinFlingVelocity()) && Math.abs(i5) <= minFlingVelocity) || !(layoutManager instanceof C0662yh) || (b = xwVar.mo4651b(layoutManager)) == null || (a = xwVar.mo10483a(layoutManager, i5, i3)) == -1)) {
                if (j) {
                    i4 |= true;
                }
                startNestedScroll(i4 ? 1 : 0, 1);
                int i6 = this.mMaxFlingVelocity;
                int max = Math.max(-i6, Math.min(i5, i6));
                int i7 = this.mMaxFlingVelocity;
                int max2 = Math.max(-i7, Math.min(i3, i7));
                C0666yl ylVar = this.mViewFlinger;
                ylVar.f16378e.setScrollState(2);
                ylVar.f16375b = 0;
                ylVar.f16374a = 0;
                Interpolator interpolator = ylVar.f16377d;
                Interpolator interpolator2 = sQuinticInterpolator;
                if (interpolator != interpolator2) {
                    ylVar.f16377d = interpolator2;
                    ylVar.f16376c = new OverScroller(ylVar.f16378e.getContext(), sQuinticInterpolator);
                }
                ylVar.f16376c.fling(0, 0, max, max2, UNDEFINED_DURATION, Integer.MAX_VALUE, UNDEFINED_DURATION, Integer.MAX_VALUE);
                ylVar.mo10630a();
                return true;
            }
            b.f16342a = a;
            layoutManager.mo10572a(b);
            return true;
        }
    }

    public View focusSearch(View view, int i) {
        View view2;
        boolean z;
        int i2;
        int i3;
        boolean z2 = true;
        boolean z3 = this.mAdapter != null && this.mLayout != null && !isComputingLayout() && !this.mLayoutSuppressed;
        FocusFinder instance = FocusFinder.getInstance();
        if (!z3 || !(i == 2 || i == 1)) {
            View findNextFocus = instance.findNextFocus(this, view, i);
            if (findNextFocus == null && z3) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                view2 = this.mLayout.mo10423a(view, i, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            } else {
                view2 = findNextFocus;
            }
        } else {
            if (this.mLayout.mo10477j()) {
                if (i == 2) {
                    i3 = 130;
                } else {
                    i3 = 33;
                }
                z = instance.findNextFocus(this, view, i3) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i = i3;
                }
            } else {
                z = false;
            }
            if (!z && this.mLayout.mo2620i()) {
                if (!((this.mLayout.mo10584q() == 1) ^ (i == 2))) {
                    i2 = 17;
                } else {
                    i2 = 66;
                }
                if (instance.findNextFocus(this, view, i2) != null) {
                    z2 = false;
                }
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i = i2;
                }
                z = z2;
            }
            if (z) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                this.mLayout.mo10423a(view, i, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
            view2 = instance.findNextFocus(this, view, i);
        }
        if (view2 == null || view2.hasFocusable()) {
            return !isPreferredNextFocus(view, view2, i) ? super.focusSearch(view, i) : view2;
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i);
        }
        requestChildOnScreen(view2, (View) null);
        return view;
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        C0647xt xtVar = this.mLayout;
        if (xtVar != null) {
            return xtVar.mo2617a();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        C0647xt xtVar = this.mLayout;
        if (xtVar != null) {
            return xtVar.mo2618a(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        C0647xt xtVar = this.mLayout;
        if (xtVar != null) {
            return xtVar.mo2619a(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    public int getAdapterPositionFor(C0667ym ymVar) {
        if (ymVar.mo10640a(524) || !ymVar.mo10652l()) {
            return -1;
        }
        C0521tb tbVar = this.mAdapterHelper;
        int i = ymVar.f16384c;
        int size = tbVar.f15904a.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0520ta taVar = (C0520ta) tbVar.f15904a.get(i2);
            int i3 = taVar.f15900a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = taVar.f15901b;
                    if (i4 <= i) {
                        int i5 = taVar.f15903d;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = taVar.f15901b;
                    if (i6 == i) {
                        i = taVar.f15903d;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (taVar.f15903d <= i) {
                            i++;
                        }
                    }
                }
            } else if (taVar.f15901b <= i) {
                i += taVar.f15903d;
            }
        }
        return i;
    }

    public int getBaseline() {
        if (this.mLayout != null) {
            return -1;
        }
        return super.getBaseline();
    }

    public long getChangedHolderKey(C0667ym ymVar) {
        return !this.mAdapter.f16288b ? (long) ymVar.f16384c : ymVar.f16386e;
    }

    public int getChildAdapterPosition(View view) {
        C0667ym childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt == null) {
            return -1;
        }
        return childViewHolderInt.mo10644d();
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        C0636xi xiVar = this.mChildDrawingOrderCallback;
        if (xiVar == null) {
            return super.getChildDrawingOrder(i, i2);
        }
        return xiVar.mo10550a();
    }

    public long getChildItemId(View view) {
        C0667ym childViewHolderInt;
        C0634xg xgVar = this.mAdapter;
        if (xgVar == null || !xgVar.f16288b || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1;
        }
        return childViewHolderInt.f16386e;
    }

    public int getChildLayoutPosition(View view) {
        C0667ym childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt == null) {
            return -1;
        }
        return childViewHolderInt.mo10643c();
    }

    @Deprecated
    public int getChildPosition(View view) {
        return getChildAdapterPosition(view);
    }

    public C0667ym getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public static C0667ym getChildViewHolderInt(View view) {
        if (view != null) {
            return ((C0648xu) view.getLayoutParams()).f16312c;
        }
        return null;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        C0648xu xuVar = (C0648xu) view.getLayoutParams();
        Rect rect2 = xuVar.f16313d;
        rect.set((view.getLeft() - rect2.left) - xuVar.leftMargin, (view.getTop() - rect2.top) - xuVar.topMargin, view.getRight() + rect2.right + xuVar.rightMargin, view.getBottom() + rect2.bottom + xuVar.bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        } else if (str.contains(".")) {
            return str;
        } else {
            return RecyclerView.class.getPackage().getName() + '.' + str;
        }
    }

    public Rect getItemDecorInsetsForChild(View view) {
        C0648xu xuVar = (C0648xu) view.getLayoutParams();
        if (!xuVar.f16314e) {
            return xuVar.f16313d;
        }
        if (this.mState.f16364g && (xuVar.mo10595b() || xuVar.f16312c.mo10650j())) {
            return xuVar.f16313d;
        }
        Rect rect = xuVar.f16313d;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mTempRect.set(0, 0, 0, 0);
            ((C0643xp) this.mItemDecorations.get(i)).mo199a(this.mTempRect, view, this, this.mState);
            rect.left += this.mTempRect.left;
            rect.top += this.mTempRect.top;
            rect.right += this.mTempRect.right;
            rect.bottom += this.mTempRect.bottom;
        }
        xuVar.f16314e = false;
        return rect;
    }

    public C0643xp getItemDecorationAt(int i) {
        int itemDecorationCount = getItemDecorationCount();
        if (i >= 0 && i < itemDecorationCount) {
            return (C0643xp) this.mItemDecorations.get(i);
        }
        throw new IndexOutOfBoundsException(i + " is an invalid index for size " + itemDecorationCount);
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0;
    }

    public C0655ya getRecycledViewPool() {
        return this.mRecycler.mo10615d();
    }

    private C0325lv getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new C0325lv(this);
        }
        return this.mScrollingChildHelper;
    }

    private void handleMissingPreInfoForChangeError(long j, C0667ym ymVar, C0667ym ymVar2) {
        int a = this.mChildHelper.mo10309a();
        for (int i = 0; i < a; i++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10316b(i));
            if (childViewHolderInt != ymVar && getChangedHolderKey(childViewHolderInt) == j) {
                C0634xg xgVar = this.mAdapter;
                if (xgVar == null || !xgVar.f16288b) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + ymVar + exceptionLabel());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + ymVar + exceptionLabel());
            }
        }
        Log.e(TAG, "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + ymVar2 + " cannot be found but it is necessary for " + ymVar + exceptionLabel());
    }

    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().mo9373a(0);
    }

    public boolean hasNestedScrollingParent(int i) {
        return getScrollingChildHelper().mo9373a(i);
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.mo10093d();
    }

    private boolean hasUpdatedView() {
        int a = this.mChildHelper.mo10309a();
        for (int i = 0; i < a; i++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10316b(i));
            if (childViewHolderInt != null && !childViewHolderInt.mo10642b() && childViewHolderInt.mo10660t()) {
                return true;
            }
        }
        return false;
    }

    public void initAdapterManager() {
        this.mAdapterHelper = new C0521tb(new C0633xf(this));
    }

    private void initAutofill() {
        if (C0340mj.m14684a(this) == 0) {
            C0340mj.m14681H(this);
        }
    }

    private void initChildrenHelper() {
        this.mChildHelper = new C0563uq(new C0632xe(this));
    }

    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + exceptionLabel());
        }
        Resources resources = getContext().getResources();
        new C0587vn(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) {
            C0647xt xtVar = this.mLayout;
            if (xtVar != null) {
                xtVar.mo10465a("Cannot invalidate item decorations during a scroll or layout");
            }
            markItemDecorInsetsDirty();
            requestLayout();
        }
    }

    public boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public boolean isAnimating() {
        C0641xn xnVar = this.mItemAnimator;
        return xnVar != null && xnVar.mo10370b();
    }

    @Deprecated
    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }

    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().f15213a;
    }

    private boolean isPreferredNextFocus(View view, View view2, int i) {
        int i2;
        int i3;
        if (view2 == null || view2 == this || findContainingItemView(view2) == null) {
            return false;
        }
        if (view == null || findContainingItemView(view) == null) {
            return true;
        }
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        char c = 65535;
        if (this.mLayout.mo10584q() == 1) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        if ((this.mTempRect.left < this.mTempRect2.left || this.mTempRect.right <= this.mTempRect2.left) && this.mTempRect.right < this.mTempRect2.right) {
            i3 = 1;
        } else {
            i3 = ((this.mTempRect.right > this.mTempRect2.right || this.mTempRect.left >= this.mTempRect2.right) && this.mTempRect.left > this.mTempRect2.left) ? -1 : 0;
        }
        if ((this.mTempRect.top < this.mTempRect2.top || this.mTempRect.bottom <= this.mTempRect2.top) && this.mTempRect.bottom < this.mTempRect2.bottom) {
            c = 1;
        } else if ((this.mTempRect.bottom <= this.mTempRect2.bottom && this.mTempRect.top < this.mTempRect2.bottom) || this.mTempRect.top <= this.mTempRect2.top) {
            c = 0;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 17) {
                    return i3 < 0;
                }
                if (i == 33) {
                    return c < 0;
                }
                if (i == 66) {
                    return i3 > 0;
                }
                if (i == 130) {
                    return c > 0;
                }
                throw new IllegalArgumentException("Invalid direction: " + i + exceptionLabel());
            } else if (c <= 0 && (c != 0 || i3 * i2 < 0)) {
                return false;
            } else {
                return true;
            }
        } else if (c >= 0 && (c != 0 || i3 * i2 > 0)) {
            return false;
        } else {
            return true;
        }
    }

    public void jumpToPositionForSmoothScroller(int i) {
        if (this.mLayout != null) {
            setScrollState(2);
            this.mLayout.mo10470d(i);
            awakenScrollBars();
        }
    }

    public void markItemDecorInsetsDirty() {
        int b = this.mChildHelper.mo10314b();
        for (int i = 0; i < b; i++) {
            ((C0648xu) this.mChildHelper.mo10317c(i).getLayoutParams()).f16314e = true;
        }
        C0656yb ybVar = this.mRecycler;
        int size = ybVar.f16326c.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0648xu xuVar = (C0648xu) ((C0667ym) ybVar.f16326c.get(i2)).f16382a.getLayoutParams();
            if (xuVar != null) {
                xuVar.f16314e = true;
            }
        }
    }

    public void markKnownViewsInvalid() {
        int b = this.mChildHelper.mo10314b();
        for (int i = 0; i < b; i++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i));
            if (childViewHolderInt != null && !childViewHolderInt.mo10642b()) {
                childViewHolderInt.mo10641b(6);
            }
        }
        markItemDecorInsetsDirty();
        C0656yb ybVar = this.mRecycler;
        int size = ybVar.f16326c.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0667ym ymVar = (C0667ym) ybVar.f16326c.get(i2);
            if (ymVar != null) {
                ymVar.mo10641b(6);
                ymVar.mo10637a((Object) null);
            }
        }
        C0634xg xgVar = ybVar.f16331h.mAdapter;
        if (xgVar == null || !xgVar.f16288b) {
            ybVar.mo10613c();
        }
    }

    public void offsetChildrenHorizontal(int i) {
        int a = this.mChildHelper.mo10309a();
        for (int i2 = 0; i2 < a; i2++) {
            this.mChildHelper.mo10316b(i2).offsetLeftAndRight(i);
        }
    }

    public void offsetChildrenVertical(int i) {
        int a = this.mChildHelper.mo10309a();
        for (int i2 = 0; i2 < a; i2++) {
            this.mChildHelper.mo10316b(i2).offsetTopAndBottom(i);
        }
    }

    public void offsetPositionRecordsForInsert(int i, int i2) {
        int b = this.mChildHelper.mo10314b();
        for (int i3 = 0; i3 < b; i3++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i3));
            if (childViewHolderInt != null && !childViewHolderInt.mo10642b() && childViewHolderInt.f16384c >= i) {
                childViewHolderInt.mo10636a(i2, false);
                this.mState.f16363f = true;
            }
        }
        C0656yb ybVar = this.mRecycler;
        int size = ybVar.f16326c.size();
        for (int i4 = 0; i4 < size; i4++) {
            C0667ym ymVar = (C0667ym) ybVar.f16326c.get(i4);
            if (ymVar != null && ymVar.f16384c >= i) {
                ymVar.mo10636a(i2, true);
            }
        }
        requestLayout();
    }

    public void offsetPositionRecordsForMove(int i, int i2) {
        int i3;
        int i4;
        int b = this.mChildHelper.mo10314b();
        int i5 = i < i2 ? -1 : 1;
        int i6 = i < i2 ? i2 : i;
        int i7 = i < i2 ? i : i2;
        for (int i8 = 0; i8 < b; i8++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i8));
            if (childViewHolderInt != null && (i4 = childViewHolderInt.f16384c) >= i7 && i4 <= i6) {
                if (i4 == i) {
                    childViewHolderInt.mo10636a(i2 - i, false);
                } else {
                    childViewHolderInt.mo10636a(i5, false);
                }
                this.mState.f16363f = true;
            }
        }
        C0656yb ybVar = this.mRecycler;
        int size = ybVar.f16326c.size();
        for (int i9 = 0; i9 < size; i9++) {
            C0667ym ymVar = (C0667ym) ybVar.f16326c.get(i9);
            if (ymVar != null && (i3 = ymVar.f16384c) >= i7 && i3 <= i6) {
                if (i3 == i) {
                    ymVar.mo10636a(i2 - i, false);
                } else {
                    ymVar.mo10636a(i5, false);
                }
            }
        }
        requestLayout();
    }

    public void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int i3 = i + i2;
        int b = this.mChildHelper.mo10314b();
        for (int i4 = 0; i4 < b; i4++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i4));
            if (childViewHolderInt != null && !childViewHolderInt.mo10642b()) {
                int i5 = childViewHolderInt.f16384c;
                if (i5 >= i3) {
                    childViewHolderInt.mo10636a(-i2, z);
                    this.mState.f16363f = true;
                } else if (i5 >= i) {
                    childViewHolderInt.mo10641b(8);
                    childViewHolderInt.mo10636a(-i2, z);
                    childViewHolderInt.f16384c = i - 1;
                    this.mState.f16363f = true;
                }
            }
        }
        C0656yb ybVar = this.mRecycler;
        for (int size = ybVar.f16326c.size() - 1; size >= 0; size--) {
            C0667ym ymVar = (C0667ym) ybVar.f16326c.get(size);
            if (ymVar != null) {
                int i6 = ymVar.f16384c;
                if (i6 >= i3) {
                    ymVar.mo10636a(-i2, z);
                } else if (i6 >= i) {
                    ymVar.mo10641b(8);
                    ybVar.mo10610b(size);
                }
            }
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        boolean z = true;
        this.mIsAttached = true;
        if (!this.mFirstLayoutComplete || isLayoutRequested()) {
            z = false;
        }
        this.mFirstLayoutComplete = z;
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            C0594vu vuVar = (C0594vu) C0594vu.f16160a.get();
            this.mGapWorker = vuVar;
            if (vuVar == null) {
                this.mGapWorker = new C0594vu();
                Display B = C0340mj.m14675B(this);
                float f = 60.0f;
                if (!isInEditMode() && B != null) {
                    float refreshRate = B.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f = refreshRate;
                    }
                }
                this.mGapWorker.f16163c = (long) (1.0E9f / f);
                C0594vu.f16160a.set(this.mGapWorker);
            }
            this.mGapWorker.f16162b.add(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        C0594vu vuVar;
        super.onDetachedFromWindow();
        C0641xn xnVar = this.mItemAnimator;
        if (xnVar != null) {
            xnVar.mo10374d();
        }
        stopScroll();
        this.mIsAttached = false;
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        C0700zs.m16265b();
        if (ALLOW_THREAD_GAP_WORK && (vuVar = this.mGapWorker) != null) {
            vuVar.f16162b.remove(this);
            this.mGapWorker = null;
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            ((C0643xp) this.mItemDecorations.get(i)).mo6514a(this);
        }
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    public void onExitLayoutOrScroll(boolean z) {
        int i = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i;
        if (i <= 0) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                dispatchContentChangedIfNecessary();
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        if (this.mLayout != null && !this.mLayoutSuppressed && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                f2 = this.mLayout.mo10477j() ? -motionEvent.getAxisValue(9) : 0.0f;
                if (this.mLayout.mo2620i()) {
                    f = motionEvent.getAxisValue(10);
                    if (!(f2 == 0.0f && f == 0.0f)) {
                        scrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent);
                    }
                }
            } else {
                if ((motionEvent.getSource() & 4194304) != 0) {
                    f = motionEvent.getAxisValue(26);
                    if (this.mLayout.mo10477j()) {
                        f2 = -f;
                        f = 0.0f;
                    } else if (this.mLayout.mo2620i()) {
                        f2 = 0.0f;
                    }
                    scrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent);
                }
                f2 = 0.0f;
            }
            f = 0.0f;
            scrollByInternal((int) (f * this.mScaledHorizontalScrollFactor), (int) (f2 * this.mScaledVerticalScrollFactor), motionEvent);
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cb, code lost:
        if (r0 != false) goto L_0x00cd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            boolean r0 = r8.mLayoutSuppressed
            r1 = 0
            if (r0 != 0) goto L_0x0125
            r0 = 0
            r8.mInterceptingOnItemTouchListener = r0
            boolean r0 = r8.findInterceptingOnItemTouchListener(r9)
            r2 = 1
            if (r0 != 0) goto L_0x0121
            xt r0 = r8.mLayout
            if (r0 == 0) goto L_0x011f
            boolean r0 = r0.mo2620i()
            xt r3 = r8.mLayout
            boolean r3 = r3.mo10477j()
            android.view.VelocityTracker r4 = r8.mVelocityTracker
            if (r4 == 0) goto L_0x0022
            goto L_0x0028
        L_0x0022:
            android.view.VelocityTracker r4 = android.view.VelocityTracker.obtain()
            r8.mVelocityTracker = r4
        L_0x0028:
            android.view.VelocityTracker r4 = r8.mVelocityTracker
            r4.addMovement(r9)
            int r4 = r9.getActionMasked()
            int r5 = r9.getActionIndex()
            r6 = 2
            r7 = 1056964608(0x3f000000, float:0.5)
            if (r4 == 0) goto L_0x00da
            if (r4 == r2) goto L_0x00d1
            if (r4 == r6) goto L_0x006f
            r0 = 3
            if (r4 == r0) goto L_0x006a
            r0 = 5
            if (r4 == r0) goto L_0x004e
            r0 = 6
            if (r4 == r0) goto L_0x0049
            goto L_0x0119
        L_0x0049:
            r8.onPointerUp(r9)
            goto L_0x0119
        L_0x004e:
            int r0 = r9.getPointerId(r5)
            r8.mScrollPointerId = r0
            float r0 = r9.getX(r5)
            float r0 = r0 + r7
            int r0 = (int) r0
            r8.mLastTouchX = r0
            r8.mInitialTouchX = r0
            float r9 = r9.getY(r5)
            float r9 = r9 + r7
            int r9 = (int) r9
            r8.mLastTouchY = r9
            r8.mInitialTouchY = r9
            goto L_0x0119
        L_0x006a:
            r8.cancelScroll()
            goto L_0x0119
        L_0x006f:
            int r4 = r8.mScrollPointerId
            int r4 = r9.findPointerIndex(r4)
            if (r4 >= 0) goto L_0x0095
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Error processing scroll; pointer index for id "
            r9.append(r0)
            int r0 = r8.mScrollPointerId
            r9.append(r0)
            java.lang.String r0 = " not found. Did any MotionEvents get skipped?"
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            java.lang.String r0 = "RecyclerView"
            android.util.Log.e(r0, r9)
            return r1
        L_0x0095:
            float r5 = r9.getX(r4)
            float r5 = r5 + r7
            int r5 = (int) r5
            float r9 = r9.getY(r4)
            float r9 = r9 + r7
            int r9 = (int) r9
            int r4 = r8.mScrollState
            if (r4 == r2) goto L_0x0119
            int r4 = r8.mInitialTouchX
            int r4 = r5 - r4
            int r6 = r8.mInitialTouchY
            int r6 = r9 - r6
            if (r0 == 0) goto L_0x00bc
            int r0 = java.lang.Math.abs(r4)
            int r4 = r8.mTouchSlop
            if (r0 <= r4) goto L_0x00bb
            r8.mLastTouchX = r5
            r0 = 1
            goto L_0x00bd
        L_0x00bb:
        L_0x00bc:
            r0 = 0
        L_0x00bd:
            if (r3 != 0) goto L_0x00c0
            goto L_0x00cb
        L_0x00c0:
            int r3 = java.lang.Math.abs(r6)
            int r4 = r8.mTouchSlop
            if (r3 <= r4) goto L_0x00cb
            r8.mLastTouchY = r9
            goto L_0x00cd
        L_0x00cb:
            if (r0 == 0) goto L_0x0119
        L_0x00cd:
            r8.setScrollState(r2)
            goto L_0x0119
        L_0x00d1:
            android.view.VelocityTracker r9 = r8.mVelocityTracker
            r9.clear()
            r8.stopNestedScroll(r1)
            goto L_0x0119
        L_0x00da:
            boolean r4 = r8.mIgnoreMotionEventTillDown
            if (r4 == 0) goto L_0x00e0
            r8.mIgnoreMotionEventTillDown = r1
        L_0x00e0:
            int r4 = r9.getPointerId(r1)
            r8.mScrollPointerId = r4
            float r4 = r9.getX()
            float r4 = r4 + r7
            int r4 = (int) r4
            r8.mLastTouchX = r4
            r8.mInitialTouchX = r4
            float r9 = r9.getY()
            float r9 = r9 + r7
            int r9 = (int) r9
            r8.mLastTouchY = r9
            r8.mInitialTouchY = r9
            int r9 = r8.mScrollState
            if (r9 != r6) goto L_0x010c
            android.view.ViewParent r9 = r8.getParent()
            r9.requestDisallowInterceptTouchEvent(r2)
            r8.setScrollState(r2)
            r8.stopNestedScroll(r2)
        L_0x010c:
            int[] r9 = r8.mNestedOffsets
            r9[r2] = r1
            r9[r1] = r1
            if (r3 == 0) goto L_0x0116
            r0 = r0 | 2
        L_0x0116:
            r8.startNestedScroll(r0, r1)
        L_0x0119:
            int r9 = r8.mScrollState
            if (r9 == r2) goto L_0x011e
            return r1
        L_0x011e:
            return r2
        L_0x011f:
            return r1
        L_0x0121:
            r8.cancelScroll()
            return r2
        L_0x0125:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        C0264jo.m14493a(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        C0264jo.m14492a();
        this.mFirstLayoutComplete = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mLayout != null) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mLayout.f16299j.defaultOnMeasure(i, i2);
            if ((mode != 1073741824 || mode2 != 1073741824) && this.mAdapter != null) {
                if (this.mState.f16361d == 1) {
                    dispatchLayoutStep1();
                }
                this.mLayout.mo10564a(i, i2);
                this.mState.f16366i = true;
                dispatchLayoutStep2();
                this.mLayout.mo10574b(i, i2);
                if (this.mLayout.mo10480m()) {
                    this.mLayout.mo10564a(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.f16366i = true;
                    dispatchLayoutStep2();
                    this.mLayout.mo10574b(i, i2);
                    return;
                }
                return;
            }
            return;
        }
        defaultOnMeasure(i, i2);
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int i;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            if (actionIndex == 0) {
                i = 1;
            } else {
                i = 0;
            }
            this.mScrollPointerId = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (!isComputingLayout()) {
            return super.onRequestFocusInDescendants(i, rect);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof C0660yf)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0660yf yfVar = (C0660yf) parcelable;
        this.mPendingSavedState = yfVar;
        super.onRestoreInstanceState(yfVar.f15201b);
        C0647xt xtVar = this.mLayout;
        if (xtVar != null && (parcelable2 = this.mPendingSavedState.f16334c) != null) {
            xtVar.mo10463a(parcelable2);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        C0660yf yfVar = new C0660yf(super.onSaveInstanceState());
        C0660yf yfVar2 = this.mPendingSavedState;
        if (yfVar2 == null) {
            C0647xt xtVar = this.mLayout;
            if (xtVar != null) {
                yfVar.f16334c = xtVar.mo10476h();
            } else {
                yfVar.f16334c = null;
            }
        } else {
            yfVar.f16334c = yfVar2.f16334c;
        }
        return yfVar;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            invalidateGlows();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        MotionEvent motionEvent2 = motionEvent;
        int i = 0;
        if (this.mLayoutSuppressed || this.mIgnoreMotionEventTillDown) {
            return false;
        }
        if (!dispatchToOnItemTouchListeners(motionEvent)) {
            C0647xt xtVar = this.mLayout;
            if (xtVar == null) {
                return false;
            }
            boolean i2 = xtVar.mo2620i();
            boolean j = this.mLayout.mo10477j();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            int actionMasked = motionEvent.getActionMasked();
            int actionIndex = motionEvent.getActionIndex();
            if (actionMasked == 0) {
                int[] iArr = this.mNestedOffsets;
                iArr[1] = 0;
                iArr[0] = 0;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            int[] iArr2 = this.mNestedOffsets;
            obtain.offsetLocation((float) iArr2[0], (float) iArr2[1]);
            if (actionMasked == 0) {
                this.mScrollPointerId = motionEvent2.getPointerId(0);
                int x = (int) (motionEvent.getX() + 0.5f);
                this.mLastTouchX = x;
                this.mInitialTouchX = x;
                int y = (int) (motionEvent.getY() + 0.5f);
                this.mLastTouchY = y;
                this.mInitialTouchY = y;
                if (j) {
                    i2 |= true;
                }
                startNestedScroll(i2 ? 1 : 0, 0);
            } else if (actionMasked == 1) {
                this.mVelocityTracker.addMovement(obtain);
                this.mVelocityTracker.computeCurrentVelocity(1000, (float) this.mMaxFlingVelocity);
                float f = i2 ? -this.mVelocityTracker.getXVelocity(this.mScrollPointerId) : 0.0f;
                float f2 = j ? -this.mVelocityTracker.getYVelocity(this.mScrollPointerId) : 0.0f;
                if ((f == 0.0f && f2 == 0.0f) || !fling((int) f, (int) f2)) {
                    setScrollState(0);
                }
                resetScroll();
                obtain.recycle();
                return true;
            } else if (actionMasked == 2) {
                int findPointerIndex = motionEvent2.findPointerIndex(this.mScrollPointerId);
                if (findPointerIndex < 0) {
                    Log.e(TAG, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                    return false;
                }
                int x2 = (int) (motionEvent2.getX(findPointerIndex) + 0.5f);
                int y2 = (int) (motionEvent2.getY(findPointerIndex) + 0.5f);
                int i3 = this.mLastTouchX - x2;
                int i4 = this.mLastTouchY - y2;
                if (this.mScrollState != 1) {
                    if (!i2) {
                        z = false;
                    } else {
                        if (i3 <= 0) {
                            i3 = Math.min(0, i3 + this.mTouchSlop);
                        } else {
                            i3 = Math.max(0, i3 - this.mTouchSlop);
                        }
                        z = i3 != 0;
                    }
                    if (j) {
                        if (i4 <= 0) {
                            i4 = Math.min(0, i4 + this.mTouchSlop);
                        } else {
                            i4 = Math.max(0, i4 - this.mTouchSlop);
                        }
                        if (i4 != 0) {
                            z = true;
                        }
                    }
                    if (z) {
                        setScrollState(1);
                    }
                }
                int i5 = i3;
                int i6 = i4;
                if (this.mScrollState == 1) {
                    int[] iArr3 = this.mReusableIntPair;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    if (dispatchNestedPreScroll(!i2 ? 0 : i5, !j ? 0 : i6, iArr3, this.mScrollOffset, 0)) {
                        int[] iArr4 = this.mReusableIntPair;
                        i5 -= iArr4[0];
                        i6 -= iArr4[1];
                        int[] iArr5 = this.mNestedOffsets;
                        int i7 = iArr5[0];
                        int[] iArr6 = this.mScrollOffset;
                        iArr5[0] = i7 + iArr6[0];
                        iArr5[1] = iArr5[1] + iArr6[1];
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    int i8 = i6;
                    int[] iArr7 = this.mScrollOffset;
                    this.mLastTouchX = x2 - iArr7[0];
                    this.mLastTouchY = y2 - iArr7[1];
                    int i9 = !i2 ? 0 : i5;
                    if (j) {
                        i = i8;
                    }
                    if (scrollByInternal(i9, i, motionEvent2)) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    C0594vu vuVar = this.mGapWorker;
                    if (!(vuVar == null || (i5 == 0 && i8 == 0))) {
                        vuVar.mo10413a(this, i5, i8);
                    }
                }
            } else if (actionMasked == 3) {
                cancelScroll();
            } else if (actionMasked == 5) {
                this.mScrollPointerId = motionEvent2.getPointerId(actionIndex);
                int x3 = (int) (motionEvent2.getX(actionIndex) + 0.5f);
                this.mLastTouchX = x3;
                this.mInitialTouchX = x3;
                int y3 = (int) (motionEvent2.getY(actionIndex) + 0.5f);
                this.mLastTouchY = y3;
                this.mInitialTouchY = y3;
            } else if (actionMasked == 6) {
                onPointerUp(motionEvent);
            }
            this.mVelocityTracker.addMovement(obtain);
            obtain.recycle();
            return true;
        }
        cancelScroll();
        return true;
    }

    public void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            C0340mj.m14695a((View) this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.mo4532b();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.mo10087a();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.mo10436d();
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.mo10091b();
        } else {
            this.mAdapterHelper.mo10094e();
        }
        boolean z2 = true;
        boolean z3 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        C0664yj yjVar = this.mState;
        boolean z4 = this.mFirstLayoutComplete && this.mItemAnimator != null && ((z = this.mDataSetHasChangedAfterLayout) || z3) && (!z || this.mAdapter.f16288b);
        yjVar.f16367j = z4;
        if (!z4 || !z3 || this.mDataSetHasChangedAfterLayout || !predictiveItemAnimationsEnabled()) {
            z2 = false;
        }
        yjVar.f16368k = z2;
    }

    public void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    private void pullGlows(float f, float f2, float f3, float f4) {
        boolean z = true;
        if (f2 < 0.0f) {
            ensureLeftGlow();
            cya.m5635a(this.mLeftGlow, (-f2) / ((float) getWidth()), 1.0f - (f3 / ((float) getHeight())));
        } else if (f2 > 0.0f) {
            ensureRightGlow();
            cya.m5635a(this.mRightGlow, f2 / ((float) getWidth()), f3 / ((float) getHeight()));
        } else {
            z = false;
        }
        if (f4 < 0.0f) {
            ensureTopGlow();
            cya.m5635a(this.mTopGlow, (-f4) / ((float) getHeight()), f / ((float) getWidth()));
        } else if (f4 > 0.0f) {
            ensureBottomGlow();
            cya.m5635a(this.mBottomGlow, f4 / ((float) getHeight()), 1.0f - (f / ((float) getWidth())));
        } else if (!z && f2 == 0.0f && f4 == 0.0f) {
            return;
        }
        C0340mj.m14710d(this);
    }

    public void recordAnimationInfoIfBouncedHiddenView(C0667ym ymVar, C0640xm xmVar) {
        ymVar.mo10635a(0, 8192);
        if (this.mState.f16365h && ymVar.mo10660t() && !ymVar.mo10653m() && !ymVar.mo10642b()) {
            this.mViewInfoStore.mo10753a(getChangedHolderKey(ymVar), ymVar);
        }
        this.mViewInfoStore.mo10754a(ymVar, xmVar);
    }

    private void recoverFocusFromState() {
        View findViewById;
        if (this.mPreserveFocusAfterLayout && this.mAdapter != null && hasFocus() && getDescendantFocusability() != 393216) {
            if (getDescendantFocusability() != 131072 || !isFocused()) {
                if (!isFocused()) {
                    View focusedChild = getFocusedChild();
                    if (!IGNORE_DETACHED_FOCUSED_CHILD || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                        if (!this.mChildHelper.mo10318c(focusedChild)) {
                            return;
                        }
                    } else if (this.mChildHelper.mo10309a() == 0) {
                        requestFocus();
                        return;
                    }
                }
                long j = this.mState.f16370m;
                View view = null;
                C0667ym findViewHolderForItemId = (j == -1 || !this.mAdapter.f16288b) ? null : findViewHolderForItemId(j);
                if (findViewHolderForItemId != null && !this.mChildHelper.mo10318c(findViewHolderForItemId.f16382a) && findViewHolderForItemId.f16382a.hasFocusable()) {
                    view = findViewHolderForItemId.f16382a;
                } else if (this.mChildHelper.mo10309a() > 0) {
                    view = findNextViewToFocus();
                }
                if (view != null) {
                    int i = this.mState.f16371n;
                    if (!(((long) i) == -1 || (findViewById = view.findViewById(i)) == null || !findViewById.isFocusable())) {
                        view = findViewById;
                    }
                    view.requestFocus();
                }
            }
        }
    }

    private void releaseGlows() {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        } else {
            z = false;
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
            C0340mj.m14710d(this);
        }
    }

    public void removeAndRecycleViews() {
        C0641xn xnVar = this.mItemAnimator;
        if (xnVar != null) {
            xnVar.mo10374d();
        }
        C0647xt xtVar = this.mLayout;
        if (xtVar != null) {
            xtVar.mo10578b(this.mRecycler);
            this.mLayout.mo10571a(this.mRecycler);
        }
        this.mRecycler.mo10605a();
    }

    public boolean removeAnimatingView(View view) {
        startInterceptRequestLayout();
        C0563uq uqVar = this.mChildHelper;
        int a = uqVar.f16027a.mo10305a(view);
        boolean z = true;
        if (a == -1) {
            uqVar.mo10320d(view);
        } else if (uqVar.f16028b.mo10300c(a)) {
            uqVar.f16028b.mo10301d(a);
            uqVar.mo10320d(view);
            uqVar.f16027a.mo10306a(a);
        } else {
            z = false;
        }
        if (z) {
            C0667ym childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.mo10612b(childViewHolderInt);
            this.mRecycler.mo10607a(childViewHolderInt);
        }
        stopInterceptRequestLayout(!z);
        return z;
    }

    public void removeDetachedView(View view, boolean z) {
        C0667ym childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.mo10654n()) {
                childViewHolderInt.mo10649i();
            } else if (!childViewHolderInt.mo10642b()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt + exceptionLabel());
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    public void removeItemDecoration(C0643xp xpVar) {
        boolean z;
        C0647xt xtVar = this.mLayout;
        if (xtVar != null) {
            xtVar.mo10465a("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(xpVar);
        if (this.mItemDecorations.isEmpty()) {
            if (getOverScrollMode() == 2) {
                z = true;
            } else {
                z = false;
            }
            setWillNotDraw(z);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeItemDecorationAt(int i) {
        int itemDecorationCount = getItemDecorationCount();
        if (i < 0 || i >= itemDecorationCount) {
            throw new IndexOutOfBoundsException(i + " is an invalid index for size " + itemDecorationCount);
        }
        removeItemDecoration(getItemDecorationAt(i));
    }

    public void removeOnChildAttachStateChangeListener(C0649xv xvVar) {
        List list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.remove(xvVar);
        }
    }

    public void removeOnItemTouchListener(C0651xx xxVar) {
        this.mOnItemTouchListeners.remove(xxVar);
        if (this.mInterceptingOnItemTouchListener == xxVar) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(C0652xy xyVar) {
        List list = this.mScrollListeners;
        if (list != null) {
            list.remove(xyVar);
        }
    }

    public void repositionShadowingViews() {
        C0667ym ymVar;
        int a = this.mChildHelper.mo10309a();
        for (int i = 0; i < a; i++) {
            View b = this.mChildHelper.mo10316b(i);
            C0667ym childViewHolder = getChildViewHolder(b);
            if (!(childViewHolder == null || (ymVar = childViewHolder.f16390i) == null)) {
                View view = ymVar.f16382a;
                int left = b.getLeft();
                int top = b.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void requestChildFocus(View view, View view2) {
        C0663yi yiVar = this.mLayout.f16302m;
        if ((yiVar == null || !yiVar.f16346e) && !isComputingLayout() && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    private void requestChildOnScreen(View view, View view2) {
        boolean z;
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof C0648xu) {
            C0648xu xuVar = (C0648xu) layoutParams;
            if (!xuVar.f16314e) {
                Rect rect = xuVar.f16313d;
                this.mTempRect.left -= rect.left;
                this.mTempRect.right += rect.right;
                this.mTempRect.top -= rect.top;
                this.mTempRect.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        C0647xt xtVar = this.mLayout;
        Rect rect2 = this.mTempRect;
        boolean z2 = !this.mFirstLayoutComplete;
        if (view2 == null) {
            z = true;
        } else {
            z = false;
        }
        xtVar.mo4641a(this, view, rect2, z2, z);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.mo4640a(this, view, rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            ((C0651xx) this.mOnItemTouchListeners.get(i)).mo10395b();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth == 0 && !this.mLayoutSuppressed) {
            super.requestLayout();
        } else {
            this.mLayoutWasDefered = true;
        }
    }

    private void resetFocusInfo() {
        C0664yj yjVar = this.mState;
        yjVar.f16370m = -1;
        yjVar.f16369l = -1;
        yjVar.f16371n = -1;
    }

    private void resetScroll() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void saveFocusInfo() {
        long j;
        int i;
        C0667ym ymVar = null;
        View focusedChild = (!this.mPreserveFocusAfterLayout || !hasFocus() || this.mAdapter == null) ? null : getFocusedChild();
        if (focusedChild != null) {
            ymVar = findContainingViewHolder(focusedChild);
        }
        if (ymVar != null) {
            C0664yj yjVar = this.mState;
            if (this.mAdapter.f16288b) {
                j = ymVar.f16386e;
            } else {
                j = -1;
            }
            yjVar.f16370m = j;
            if (!this.mDataSetHasChangedAfterLayout) {
                i = ymVar.mo10653m() ? ymVar.f16385d : ymVar.mo10644d();
            } else {
                i = -1;
            }
            yjVar.f16369l = i;
            this.mState.f16371n = getDeepestFocusedViewWithId(ymVar.f16382a);
            return;
        }
        resetFocusInfo();
    }

    public void saveOldPositions() {
        int b = this.mChildHelper.mo10314b();
        for (int i = 0; i < b; i++) {
            C0667ym childViewHolderInt = getChildViewHolderInt(this.mChildHelper.mo10317c(i));
            if (!childViewHolderInt.mo10642b() && childViewHolderInt.f16385d == -1) {
                childViewHolderInt.f16385d = childViewHolderInt.f16384c;
            }
        }
    }

    public void scrollBy(int i, int i2) {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            boolean i3 = xtVar.mo2620i();
            boolean j = this.mLayout.mo10477j();
            if (i3 || j) {
                if (!i3) {
                    i = 0;
                }
                if (!j) {
                    i2 = 0;
                }
                scrollByInternal(i, i2, (MotionEvent) null);
            }
        }
    }

    public boolean scrollByInternal(int i, int i2, MotionEvent motionEvent) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = i;
        int i8 = i2;
        consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            int[] iArr = this.mReusableIntPair;
            iArr[0] = 0;
            iArr[1] = 0;
            scrollStep(i7, i8, iArr);
            int[] iArr2 = this.mReusableIntPair;
            int i9 = iArr2[0];
            int i10 = iArr2[1];
            i6 = i10;
            i5 = i9;
            i4 = i7 - i9;
            i3 = i8 - i10;
        } else {
            i6 = 0;
            i5 = 0;
            i4 = 0;
            i3 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.mReusableIntPair;
        iArr3[0] = 0;
        iArr3[1] = 0;
        dispatchNestedScroll(i5, i6, i4, i3, this.mScrollOffset, 0, iArr3);
        int[] iArr4 = this.mReusableIntPair;
        int i11 = iArr4[0];
        int i12 = i4 - i11;
        int i13 = iArr4[1];
        int i14 = i3 - i13;
        boolean z = (i11 == 0 && i13 == 0) ? false : true;
        int i15 = this.mLastTouchX;
        int[] iArr5 = this.mScrollOffset;
        int i16 = iArr5[0];
        this.mLastTouchX = i15 - i16;
        this.mLastTouchY -= iArr5[1];
        int[] iArr6 = this.mNestedOffsets;
        iArr6[0] = iArr6[0] + i16;
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (!(motionEvent == null || (motionEvent.getSource() & 8194) == 8194)) {
                pullGlows(motionEvent.getX(), (float) i12, motionEvent.getY(), (float) i14);
            }
            considerReleasingGlowsOnScroll(i, i2);
        }
        if (!(i5 == 0 && i6 == 0)) {
            dispatchOnScrolled(i5, i6);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z && i5 == 0 && i6 == 0) ? false : true;
    }

    public void scrollStep(int i, int i2, int[] iArr) {
        int i3;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        C0264jo.m14493a(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        int a = i != 0 ? this.mLayout.mo10421a(i, this.mRecycler, this.mState) : 0;
        if (i2 != 0) {
            i3 = this.mLayout.mo10433b(i2, this.mRecycler, this.mState);
        } else {
            i3 = 0;
        }
        C0264jo.m14492a();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = a;
            iArr[1] = i3;
        }
    }

    public void scrollTo(int i, int i2) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i) {
        if (!this.mLayoutSuppressed) {
            stopScroll();
            C0647xt xtVar = this.mLayout;
            if (xtVar == null) {
                Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            xtVar.mo10470d(i);
            awakenScrollBars();
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!shouldDeferAccessibilityEvent(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    public void setAccessibilityDelegateCompat(C0669yo yoVar) {
        this.mAccessibilityDelegate = yoVar;
        C0340mj.m14698a((View) this, (C0315ll) yoVar);
    }

    public void setAdapter(C0634xg xgVar) {
        setLayoutFrozen(false);
        setAdapterInternal(xgVar, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    private void setAdapterInternal(C0634xg xgVar, boolean z, boolean z2) {
        C0634xg xgVar2 = this.mAdapter;
        if (xgVar2 != null) {
            xgVar2.f16287a.unregisterObserver(this.mObserver);
        }
        if (!z || z2) {
            removeAndRecycleViews();
        }
        this.mAdapterHelper.mo10087a();
        C0634xg xgVar3 = this.mAdapter;
        this.mAdapter = xgVar;
        if (xgVar != null) {
            xgVar.f16287a.registerObserver(this.mObserver);
            xgVar.mo4519a(this);
        }
        C0656yb ybVar = this.mRecycler;
        C0634xg xgVar4 = this.mAdapter;
        ybVar.mo10605a();
        C0655ya d = ybVar.mo10615d();
        if (xgVar3 != null) {
            d.mo10602b();
        }
        if (!z && d.f16323b == 0) {
            for (int i = 0; i < d.f16322a.size(); i++) {
                ((C0653xz) d.f16322a.valueAt(i)).f16319a.clear();
            }
        }
        if (xgVar4 != null) {
            d.mo10601a();
        }
        this.mState.f16363f = true;
    }

    public void setChildDrawingOrderCallback(C0636xi xiVar) {
        boolean z;
        if (xiVar != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = xiVar;
            if (xiVar != null) {
                z = true;
            } else {
                z = false;
            }
            setChildrenDrawingOrderEnabled(z);
        }
    }

    public boolean setChildImportantForAccessibilityInternal(C0667ym ymVar, int i) {
        if (isComputingLayout()) {
            ymVar.f16395n = i;
            this.mPendingAccessibilityImportanceChange.add(ymVar);
            return false;
        }
        C0340mj.m14689a(ymVar.f16382a, i);
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

    public void setEdgeEffectFactory(C0637xj xjVar) {
        C0321lr.m14624a((Object) xjVar);
        this.mEdgeEffectFactory = xjVar;
        invalidateGlows();
    }

    public void setHasFixedSize(boolean z) {
        this.mHasFixedSize = z;
    }

    public void setItemAnimator(C0641xn xnVar) {
        C0641xn xnVar2 = this.mItemAnimator;
        if (xnVar2 != null) {
            xnVar2.mo10374d();
            this.mItemAnimator.f16292h = null;
        }
        this.mItemAnimator = xnVar;
        if (xnVar != null) {
            xnVar.f16292h = this.mItemAnimatorListener;
        }
    }

    public void setItemViewCacheSize(int i) {
        C0656yb ybVar = this.mRecycler;
        ybVar.f16328e = i;
        ybVar.mo10609b();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(C0647xt xtVar) {
        if (xtVar != this.mLayout) {
            stopScroll();
            if (this.mLayout != null) {
                C0641xn xnVar = this.mItemAnimator;
                if (xnVar != null) {
                    xnVar.mo10374d();
                }
                this.mLayout.mo10578b(this.mRecycler);
                this.mLayout.mo10571a(this.mRecycler);
                this.mRecycler.mo10605a();
                this.mLayout.mo10566a((RecyclerView) null);
                this.mLayout = null;
            } else {
                this.mRecycler.mo10605a();
            }
            C0563uq uqVar = this.mChildHelper;
            uqVar.f16028b.mo10296a();
            for (int size = uqVar.f16029c.size() - 1; size >= 0; size--) {
                uqVar.f16027a.mo10308b((View) uqVar.f16029c.get(size));
                uqVar.f16029c.remove(size);
            }
            C0632xe xeVar = (C0632xe) uqVar.f16027a;
            int a = xeVar.mo10304a();
            for (int i = 0; i < a; i++) {
                View b = xeVar.mo10307b(i);
                xeVar.f16285a.dispatchChildDetached(b);
                b.clearAnimation();
            }
            xeVar.f16285a.removeAllViews();
            this.mLayout = xtVar;
            if (xtVar != null) {
                if (xtVar.f16299j == null) {
                    this.mLayout.mo10566a(this);
                } else {
                    throw new IllegalArgumentException("LayoutManager " + xtVar + " is already attached to a RecyclerView:" + xtVar.f16299j.exceptionLabel());
                }
            }
            this.mRecycler.mo10609b();
            requestLayout();
        }
    }

    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        int i = Build.VERSION.SDK_INT;
        if (layoutTransition == null) {
            super.setLayoutTransition((LayoutTransition) null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    public void setNestedScrollingEnabled(boolean z) {
        getScrollingChildHelper().mo9370a(z);
    }

    public void setOnFlingListener(C0650xw xwVar) {
        this.mOnFlingListener = xwVar;
    }

    @Deprecated
    public void setOnScrollListener(C0652xy xyVar) {
        this.mScrollListener = xyVar;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.mPreserveFocusAfterLayout = z;
    }

    public void setRecycledViewPool(C0655ya yaVar) {
        C0656yb ybVar = this.mRecycler;
        C0655ya yaVar2 = ybVar.f16329f;
        if (yaVar2 != null) {
            yaVar2.mo10602b();
        }
        ybVar.f16329f = yaVar;
        if (ybVar.f16329f != null && ybVar.f16331h.getAdapter() != null) {
            ybVar.f16329f.mo10601a();
        }
    }

    public void setRecyclerListener(C0657yc ycVar) {
        this.mRecyclerListener = ycVar;
    }

    public void setScrollState(int i) {
        if (i != this.mScrollState) {
            this.mScrollState = i;
            if (i != 2) {
                stopScrollersInternal();
            }
            dispatchOnScrollStateChanged(i);
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i != 0) {
            if (i != 1) {
                Log.w(TAG, "setScrollingTouchSlop(): bad argument constant " + i + "; using default value");
            } else {
                this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
        }
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(C0665yk ykVar) {
        this.mRecycler.f16330g = ykVar;
    }

    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int i;
        int i2 = 0;
        if (!isComputingLayout()) {
            return false;
        }
        if (accessibilityEvent == null) {
            i = 0;
        } else {
            int i3 = Build.VERSION.SDK_INT;
            i = accessibilityEvent.getContentChangeTypes();
        }
        if (i != 0) {
            i2 = i;
        }
        this.mEatenAccessibilityChangeFlags |= i2;
        return true;
    }

    public void smoothScrollBy(int i, int i2) {
        smoothScrollBy(i, i2, (Interpolator) null);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator) {
        smoothScrollBy(i, i2, interpolator, UNDEFINED_DURATION);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator, int i3) {
        smoothScrollBy(i, i2, interpolator, i3, false);
    }

    public void smoothScrollBy(int i, int i2, Interpolator interpolator, int i3, boolean z) {
        C0647xt xtVar = this.mLayout;
        if (xtVar == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.mLayoutSuppressed) {
            int i4 = 0;
            if (!xtVar.mo2620i()) {
                i = 0;
            }
            if (!this.mLayout.mo10477j()) {
                i2 = 0;
            }
            if (i != 0 || i2 != 0) {
                if (i3 == Integer.MIN_VALUE || i3 > 0) {
                    if (z) {
                        if (i != 0) {
                            i4 = 1;
                        }
                        if (i2 != 0) {
                            i4 |= 2;
                        }
                        startNestedScroll(i4, 1);
                    }
                    this.mViewFlinger.mo10631a(i, i2, i3, interpolator);
                    return;
                }
                scrollBy(i, i2);
            }
        }
    }

    public void smoothScrollToPosition(int i) {
        if (!this.mLayoutSuppressed) {
            C0647xt xtVar = this.mLayout;
            if (xtVar == null) {
                Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                xtVar.mo6547a(this, i);
            }
        }
    }

    public void startInterceptRequestLayout() {
        int i = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i;
        if (i == 1 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
    }

    public boolean startNestedScroll(int i) {
        return getScrollingChildHelper().mo9374a(i, 0);
    }

    public boolean startNestedScroll(int i, int i2) {
        return getScrollingChildHelper().mo9374a(i, i2);
    }

    public void stopInterceptRequestLayout(boolean z) {
        int i = this.mInterceptRequestLayoutDepth;
        if (i <= 0) {
            this.mInterceptRequestLayoutDepth = 1;
            i = 1;
        }
        if (!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (i == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    public void stopNestedScroll() {
        getScrollingChildHelper().mo9378b(0);
    }

    public void stopNestedScroll(int i) {
        getScrollingChildHelper().mo9378b(i);
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    private void stopScrollersInternal() {
        C0663yi yiVar;
        this.mViewFlinger.mo10632b();
        C0647xt xtVar = this.mLayout;
        if (xtVar != null && (yiVar = xtVar.f16302m) != null) {
            yiVar.mo10623a();
        }
    }

    public final void suppressLayout(boolean z) {
        if (z != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.mLayoutSuppressed = false;
                if (!(!this.mLayoutWasDefered || this.mLayout == null || this.mAdapter == null)) {
                    requestLayout();
                }
                this.mLayoutWasDefered = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            stopScroll();
        }
    }

    public void swapAdapter(C0634xg xgVar, boolean z) {
        setLayoutFrozen(false);
        setAdapterInternal(xgVar, true, z);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    public void viewRangeUpdate(int i, int i2, Object obj) {
        int i3;
        int i4;
        int b = this.mChildHelper.mo10314b();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < b; i6++) {
            View c = this.mChildHelper.mo10317c(i6);
            C0667ym childViewHolderInt = getChildViewHolderInt(c);
            if (childViewHolderInt != null && !childViewHolderInt.mo10642b() && (i4 = childViewHolderInt.f16384c) >= i && i4 < i5) {
                childViewHolderInt.mo10641b(2);
                childViewHolderInt.mo10637a(obj);
                ((C0648xu) c.getLayoutParams()).f16314e = true;
            }
        }
        C0656yb ybVar = this.mRecycler;
        for (int size = ybVar.f16326c.size() - 1; size >= 0; size--) {
            C0667ym ymVar = (C0667ym) ybVar.f16326c.get(size);
            if (ymVar != null && (i3 = ymVar.f16384c) >= i && i3 < i5) {
                ymVar.mo10641b(2);
                ybVar.mo10610b(size);
            }
        }
    }
}
