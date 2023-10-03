package androidx.recyclerview.widget;

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
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
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
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import p000a.p012f.C0046a;
import p000a.p012f.C0047b;
import p026b.p027a.p030b.p031a.C0632a;

public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2, NestedScrollingChild3 {
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;

    /* renamed from: Bi */
    private static final int[] f560Bi = {16843830};

    /* renamed from: Ci */
    private static final int[] f561Ci = {16842987};

    /* renamed from: Di */
    static final boolean f562Di = true;

    /* renamed from: Ei */
    private static final boolean f563Ei = false;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;

    /* renamed from: Fi */
    private static final boolean f564Fi = false;

    /* renamed from: Gi */
    private static final Class[] f565Gi;
    static final Interpolator sQuinticInterpolator = new C0539L();

    /* renamed from: Ai */
    private final C0540M f566Ai;

    /* renamed from: Uh */
    private final ArrayList f567Uh;

    /* renamed from: Vh */
    private C0564fa f568Vh;

    /* renamed from: Wh */
    boolean f569Wh;

    /* renamed from: Xh */
    private int f570Xh;

    /* renamed from: Yh */
    boolean f571Yh;

    /* renamed from: Zh */
    boolean f572Zh;

    /* renamed from: _h */
    private boolean f573_h;

    /* renamed from: ai */
    private int f574ai;

    /* renamed from: bi */
    private List f575bi;

    /* renamed from: di */
    boolean f576di;

    /* renamed from: ei */
    private int f577ei;

    /* renamed from: fi */
    private int f578fi;

    /* renamed from: gi */
    private C0546T f579gi;

    /* renamed from: hi */
    private EdgeEffect f580hi;

    /* renamed from: ii */
    private EdgeEffect f581ii;

    /* renamed from: ji */
    private EdgeEffect f582ji;

    /* renamed from: ki */
    private EdgeEffect f583ki;

    /* renamed from: li */
    private int f584li;
    C0590sa mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    C0543P mAdapter;
    C0555b mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    C0559d mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    boolean mFirstLayoutComplete;
    C0597w mGapWorker;
    boolean mHasFixedSize;
    private int mInitialTouchX;
    private int mInitialTouchY;
    boolean mIsAttached;
    C0594ua mItemAnimator;
    final ArrayList mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    C0558ca mLayout;
    private final int mMinFlingVelocity;
    private final C0574ka mObserver;
    final List mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    C0593u mPrefetchRegistry;
    final C0572ja mRecycler;
    private C0566ga mScrollListener;
    private final int[] mScrollOffset;
    final C0582oa mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    final C0584pa mViewFlinger;
    final C0534Ga mViewInfoStore;

    /* renamed from: mi */
    private int f585mi;

    /* renamed from: ni */
    private final int f586ni;

    /* renamed from: oi */
    private float f587oi;

    /* renamed from: qi */
    private float f588qi;

    /* renamed from: ri */
    private boolean f589ri;

    /* renamed from: ti */
    private List f590ti;

    /* renamed from: ui */
    private C0549W f591ui;

    /* renamed from: vi */
    private final int[] f592vi;

    /* renamed from: wi */
    private NestedScrollingChildHelper f593wi;

    /* renamed from: xi */
    private final int[] f594xi;

    /* renamed from: yi */
    final int[] f595yi;

    /* renamed from: zi */
    private Runnable f596zi;

    static {
        int i = Build.VERSION.SDK_INT;
        Class cls = Integer.TYPE;
        f565Gi = new Class[]{Context.class, AttributeSet.class, cls, cls};
    }

    public RecyclerView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* renamed from: G */
    private void m687G(C0586qa qaVar) {
        View view = qaVar.itemView;
        boolean z = view.getParent() == this;
        this.mRecycler.mo5147m(getChildViewHolder(view));
        if (qaVar.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
        } else if (!z) {
            this.mChildHelper.addView(view, -1, true);
        } else {
            C0559d dVar = this.mChildHelper;
            int indexOfChild = dVar.mCallback.this$0.indexOfChild(view);
            if (indexOfChild >= 0) {
                dVar.mBucket.set(indexOfChild);
                dVar.mHiddenViews.add(view);
                dVar.mCallback.onEnteredHiddenState(view);
                return;
            }
            throw new IllegalArgumentException(C0632a.m1014a("view is not a child, cannot hide ", view));
        }
    }

    /* renamed from: cn */
    private void m696cn() {
        m703in();
        setScrollState(0);
    }

    /* renamed from: dn */
    private void m697dn() {
        int i;
        boolean z = true;
        this.mState.assertLayoutStep(1);
        mo4825a(this.mState);
        this.mState.mIsMeasuring = false;
        mo4816Wb();
        this.mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        m701gn();
        C0586qa qaVar = null;
        View focusedChild = (!this.f589ri || !hasFocus() || this.mAdapter == null) ? null : getFocusedChild();
        if (focusedChild != null) {
            qaVar = findContainingViewHolder(focusedChild);
        }
        if (qaVar == null) {
            m702hn();
        } else {
            this.mState.mFocusedItemId = this.mAdapter.hasStableIds() ? qaVar.getItemId() : -1;
            C0582oa oaVar = this.mState;
            if (this.mDataSetHasChangedAfterLayout) {
                i = -1;
            } else if (qaVar.isRemoved()) {
                i = qaVar.mOldPosition;
            } else {
                i = qaVar.getAdapterPosition();
            }
            oaVar.mFocusedItemPosition = i;
            C0582oa oaVar2 = this.mState;
            View view = qaVar.itemView;
            int id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    id = view.getId();
                }
            }
            oaVar2.mFocusedSubChildId = id;
        }
        C0582oa oaVar3 = this.mState;
        if (!oaVar3.mRunSimpleAnimations || !this.mItemsChanged) {
            z = false;
        }
        oaVar3.mTrackOldChangeHolders = z;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        C0582oa oaVar4 = this.mState;
        oaVar4.mInPreLayout = oaVar4.mRunPredictiveAnimations;
        oaVar4.mItemCount = this.mAdapter.getItemCount();
        m699f(this.f592vi);
        if (this.mState.mRunSimpleAnimations) {
            int childCount = this.mChildHelper.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i2));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.mo4704d(childViewHolderInt, this.mItemAnimator.mo5236a(this.mState, childViewHolderInt, C0594ua.m915p(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.mo4699a(mo4844d(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            saveOldPositions();
            C0582oa oaVar5 = this.mState;
            boolean z2 = oaVar5.mStructureChanged;
            oaVar5.mStructureChanged = false;
            this.mLayout.mo4727e(this.mRecycler, oaVar5);
            this.mState.mStructureChanged = z2;
            for (int i3 = 0; i3 < this.mChildHelper.getChildCount(); i3++) {
                C0586qa childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.mo4693A(childViewHolderInt2)) {
                    int p = C0594ua.m915p(childViewHolderInt2);
                    boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                    if (!hasAnyOfTheFlags) {
                        p |= NotificationCompat.FLAG_BUBBLE;
                    }
                    C0548V a = this.mItemAnimator.mo5236a(this.mState, childViewHolderInt2, p, childViewHolderInt2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        mo4826a(childViewHolderInt2, a);
                    } else {
                        this.mViewInfoStore.mo4701b(childViewHolderInt2, a);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        mo4947v(false);
        this.mState.mLayoutStep = 2;
    }

    /* renamed from: en */
    private void m698en() {
        mo4816Wb();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        C0582oa oaVar = this.mState;
        oaVar.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        oaVar.mInPreLayout = false;
        this.mLayout.mo4727e(this.mRecycler, oaVar);
        C0582oa oaVar2 = this.mState;
        oaVar2.mStructureChanged = false;
        this.mPendingSavedState = null;
        oaVar2.mRunSimpleAnimations = oaVar2.mRunSimpleAnimations && this.mItemAnimator != null;
        this.mState.mLayoutStep = 4;
        onExitLayoutOrScroll();
        mo4947v(false);
    }

    /* renamed from: f */
    private void m699f(int[] iArr) {
        int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
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

    /* renamed from: fn */
    private NestedScrollingChildHelper m700fn() {
        if (this.f593wi == null) {
            this.f593wi = new NestedScrollingChildHelper(this);
        }
        return this.f593wi;
    }

    static C0586qa getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((C0560da) view.getLayoutParams()).mViewHolder;
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        Rect rect2 = daVar.mDecorInsets;
        rect.set((view.getLeft() - rect2.left) - daVar.leftMargin, (view.getTop() - rect2.top) - daVar.topMargin, view.getRight() + rect2.right + daVar.rightMargin, view.getBottom() + rect2.bottom + daVar.bottomMargin);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007e, code lost:
        if ((r5.mItemAnimator != null && r5.mLayout.supportsPredictiveItemAnimations()) != false) goto L_0x0082;
     */
    /* renamed from: gn */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m701gn() {
        /*
            r5 = this;
            boolean r0 = r5.mDataSetHasChangedAfterLayout
            if (r0 == 0) goto L_0x0012
            androidx.recyclerview.widget.b r0 = r5.mAdapterHelper
            r0.reset()
            boolean r0 = r5.f576di
            if (r0 == 0) goto L_0x0012
            androidx.recyclerview.widget.ca r0 = r5.mLayout
            r0.mo4729g((androidx.recyclerview.widget.RecyclerView) r5)
        L_0x0012:
            androidx.recyclerview.widget.ua r0 = r5.mItemAnimator
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0022
            androidx.recyclerview.widget.ca r0 = r5.mLayout
            boolean r0 = r0.supportsPredictiveItemAnimations()
            if (r0 == 0) goto L_0x0022
            r0 = r1
            goto L_0x0023
        L_0x0022:
            r0 = r2
        L_0x0023:
            if (r0 == 0) goto L_0x002b
            androidx.recyclerview.widget.b r0 = r5.mAdapterHelper
            r0.preProcess()
            goto L_0x0030
        L_0x002b:
            androidx.recyclerview.widget.b r0 = r5.mAdapterHelper
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
            androidx.recyclerview.widget.oa r3 = r5.mState
            boolean r4 = r5.mFirstLayoutComplete
            if (r4 == 0) goto L_0x0060
            androidx.recyclerview.widget.ua r4 = r5.mItemAnimator
            if (r4 == 0) goto L_0x0060
            boolean r4 = r5.mDataSetHasChangedAfterLayout
            if (r4 != 0) goto L_0x0052
            if (r0 != 0) goto L_0x0052
            androidx.recyclerview.widget.ca r4 = r5.mLayout
            boolean r4 = r4.mRequestedSimpleAnimations
            if (r4 == 0) goto L_0x0060
        L_0x0052:
            boolean r4 = r5.mDataSetHasChangedAfterLayout
            if (r4 == 0) goto L_0x005e
            androidx.recyclerview.widget.P r4 = r5.mAdapter
            boolean r4 = r4.hasStableIds()
            if (r4 == 0) goto L_0x0060
        L_0x005e:
            r4 = r1
            goto L_0x0061
        L_0x0060:
            r4 = r2
        L_0x0061:
            r3.mRunSimpleAnimations = r4
            androidx.recyclerview.widget.oa r3 = r5.mState
            boolean r4 = r3.mRunSimpleAnimations
            if (r4 == 0) goto L_0x0081
            if (r0 == 0) goto L_0x0081
            boolean r0 = r5.mDataSetHasChangedAfterLayout
            if (r0 != 0) goto L_0x0081
            androidx.recyclerview.widget.ua r0 = r5.mItemAnimator
            if (r0 == 0) goto L_0x007d
            androidx.recyclerview.widget.ca r5 = r5.mLayout
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m701gn():void");
    }

    /* renamed from: hn */
    private void m702hn() {
        C0582oa oaVar = this.mState;
        oaVar.mFocusedItemId = -1;
        oaVar.mFocusedItemPosition = -1;
        oaVar.mFocusedSubChildId = -1;
    }

    /* renamed from: in */
    private void m703in() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean z = false;
        stopNestedScroll(0);
        EdgeEffect edgeEffect = this.f580hi;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.f580hi.isFinished();
        }
        EdgeEffect edgeEffect2 = this.f581ii;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.f581ii.isFinished();
        }
        EdgeEffect edgeEffect3 = this.f582ji;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.f582ji.isFinished();
        }
        EdgeEffect edgeEffect4 = this.f583ki;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.f583ki.isFinished();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Vb */
    public String mo4815Vb() {
        StringBuilder Pa = C0632a.m1011Pa(" ");
        Pa.append(super.toString());
        Pa.append(", adapter:");
        Pa.append(this.mAdapter);
        Pa.append(", layout:");
        Pa.append(this.mLayout);
        Pa.append(", context:");
        Pa.append(getContext());
        return Pa.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Wb */
    public void mo4816Wb() {
        this.f570Xh++;
        if (this.f570Xh == 1 && !this.f572Zh) {
            this.f571Yh = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void absorbGlows(int i, int i2) {
        if (i < 0) {
            ensureLeftGlow();
            if (this.f580hi.isFinished()) {
                this.f580hi.onAbsorb(-i);
            }
        } else if (i > 0) {
            ensureRightGlow();
            if (this.f582ji.isFinished()) {
                this.f582ji.onAbsorb(i);
            }
        }
        if (i2 < 0) {
            ensureTopGlow();
            if (this.f581ii.isFinished()) {
                this.f581ii.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            ensureBottomGlow();
            if (this.f583ki.isFinished()) {
                this.f583ki.onAbsorb(i2);
            }
        }
        if (i != 0 || i2 != 0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void addFocusables(ArrayList arrayList, int i, int i2) {
        C0558ca caVar = this.mLayout;
        if (caVar == null || !caVar.mo5029a(this, arrayList, i, i2)) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str == null) {
                StringBuilder Pa = C0632a.m1011Pa("Cannot call this method while RecyclerView is computing a layout or scrolling");
                Pa.append(mo4815Vb());
                throw new IllegalStateException(Pa.toString());
            }
            throw new IllegalStateException(str);
        } else if (this.f578fi > 0) {
            StringBuilder Pa2 = C0632a.m1011Pa("");
            Pa2.append(mo4815Vb());
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(Pa2.toString()));
        }
    }

    /* renamed from: b */
    public void mo4834b(C0550X x) {
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            caVar.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(x);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    @Deprecated
    /* renamed from: c */
    public void mo4839c(C0566ga gaVar) {
        this.mScrollListener = gaVar;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0560da) && this.mLayout.mo4720a((C0560da) layoutParams);
    }

    /* access modifiers changed from: package-private */
    public void clearOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        C0572ja jaVar = this.mRecycler;
        int size = jaVar.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((C0586qa) jaVar.mCachedViews.get(i2)).clearOldPosition();
        }
        int size2 = jaVar.mAttachedScrap.size();
        for (int i3 = 0; i3 < size2; i3++) {
            ((C0586qa) jaVar.mAttachedScrap.get(i3)).clearOldPosition();
        }
        ArrayList arrayList = jaVar.mChangedScrap;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i4 = 0; i4 < size3; i4++) {
                ((C0586qa) jaVar.mChangedScrap.get(i4)).clearOldPosition();
            }
        }
    }

    public int computeHorizontalScrollExtent() {
        C0558ca caVar = this.mLayout;
        if (caVar != null && caVar.canScrollHorizontally()) {
            return this.mLayout.mo4752c(this.mState);
        }
        return 0;
    }

    public int computeHorizontalScrollOffset() {
        C0558ca caVar = this.mLayout;
        if (caVar != null && caVar.canScrollHorizontally()) {
            return this.mLayout.mo4724d(this.mState);
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        C0558ca caVar = this.mLayout;
        if (caVar != null && caVar.canScrollHorizontally()) {
            return this.mLayout.mo4726e(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollExtent() {
        C0558ca caVar = this.mLayout;
        if (caVar != null && caVar.canScrollVertically()) {
            return this.mLayout.mo4759f(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        C0558ca caVar = this.mLayout;
        if (caVar != null && caVar.canScrollVertically()) {
            return this.mLayout.mo4728g(this.mState);
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        C0558ca caVar = this.mLayout;
        if (caVar != null && caVar.canScrollVertically()) {
            return this.mLayout.mo4733h(this.mState);
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void considerReleasingGlowsOnScroll(int i, int i2) {
        boolean z;
        EdgeEffect edgeEffect = this.f580hi;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            z = false;
        } else {
            this.f580hi.onRelease();
            z = this.f580hi.isFinished();
        }
        EdgeEffect edgeEffect2 = this.f582ji;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.f582ji.onRelease();
            z |= this.f582ji.isFinished();
        }
        EdgeEffect edgeEffect3 = this.f581ii;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.f581ii.onRelease();
            z |= this.f581ii.isFinished();
        }
        EdgeEffect edgeEffect4 = this.f583ki;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.f583ki.onRelease();
            z |= this.f583ki.isFinished();
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
                mo4816Wb();
                onEnterLayoutOrScroll();
                this.mAdapterHelper.preProcess();
                if (!this.f571Yh) {
                    int childCount = this.mChildHelper.getChildCount();
                    boolean z = false;
                    int i4 = 0;
                    while (true) {
                        if (i4 < childCount) {
                            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i4));
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
                mo4947v(true);
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
    /* renamed from: d */
    public long mo4844d(C0586qa qaVar) {
        return this.mAdapter.hasStableIds() ? qaVar.getItemId() : (long) qaVar.mPosition;
    }

    /* access modifiers changed from: package-private */
    public void defaultOnMeasure(int i, int i2) {
        setMeasuredDimension(C0558ca.chooseSize(i, getPaddingRight() + getPaddingLeft(), ViewCompat.getMinimumWidth(this)), C0558ca.chooseSize(i2, getPaddingBottom() + getPaddingTop(), ViewCompat.getMinimumHeight(this)));
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildAttached(View view) {
        C0586qa childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        C0543P p = this.mAdapter;
        if (!(p == null || childViewHolderInt == null)) {
            p.mo4803f(childViewHolderInt);
        }
        List list = this.f575bi;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0562ea) this.f575bi.get(size)).onChildViewAttachedToWindow(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchChildDetached(View view) {
        C0586qa childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        C0543P p = this.mAdapter;
        if (!(p == null || childViewHolderInt == null)) {
            p.mo4804g(childViewHolderInt);
        }
        List list = this.f575bi;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0562ea) this.f575bi.get(size)).onChildViewDetachedFromWindow(view);
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
            androidx.recyclerview.widget.P r0 = r13.mAdapter
            java.lang.String r1 = "RecyclerView"
            if (r0 != 0) goto L_0x000c
            java.lang.String r13 = "No adapter attached; skipping layout"
            android.util.Log.e(r1, r13)
            return
        L_0x000c:
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            if (r0 != 0) goto L_0x0016
            java.lang.String r13 = "No layout manager attached; skipping layout"
            android.util.Log.e(r1, r13)
            return
        L_0x0016:
            androidx.recyclerview.widget.oa r0 = r13.mState
            r2 = 0
            r0.mIsMeasuring = r2
            int r0 = r0.mLayoutStep
            r3 = 1
            if (r0 != r3) goto L_0x002c
            r13.m697dn()
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            r0.mo5081h((androidx.recyclerview.widget.RecyclerView) r13)
            r13.m698en()
            goto L_0x006a
        L_0x002c:
            androidx.recyclerview.widget.b r0 = r13.mAdapterHelper
            java.util.ArrayList r4 = r0.mPostponedList
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0040
            java.util.ArrayList r0 = r0.mPendingUpdates
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0040
            r0 = r3
            goto L_0x0041
        L_0x0040:
            r0 = r2
        L_0x0041:
            if (r0 != 0) goto L_0x0062
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            int r0 = r0.getWidth()
            int r4 = r13.getWidth()
            if (r0 != r4) goto L_0x0062
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            int r0 = r0.getHeight()
            int r4 = r13.getHeight()
            if (r0 == r4) goto L_0x005c
            goto L_0x0062
        L_0x005c:
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            r0.mo5081h((androidx.recyclerview.widget.RecyclerView) r13)
            goto L_0x006a
        L_0x0062:
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            r0.mo5081h((androidx.recyclerview.widget.RecyclerView) r13)
            r13.m698en()
        L_0x006a:
            androidx.recyclerview.widget.oa r0 = r13.mState
            r4 = 4
            r0.assertLayoutStep(r4)
            r13.mo4816Wb()
            r13.onEnterLayoutOrScroll()
            androidx.recyclerview.widget.oa r0 = r13.mState
            r0.mLayoutStep = r3
            boolean r0 = r0.mRunSimpleAnimations
            if (r0 == 0) goto L_0x01b1
            androidx.recyclerview.widget.d r0 = r13.mChildHelper
            int r0 = r0.getChildCount()
            int r0 = r0 - r3
        L_0x0085:
            if (r0 < 0) goto L_0x01aa
            androidx.recyclerview.widget.d r4 = r13.mChildHelper
            android.view.View r4 = r4.getChildAt(r0)
            androidx.recyclerview.widget.qa r4 = getChildViewHolderInt(r4)
            boolean r5 = r4.shouldIgnore()
            if (r5 == 0) goto L_0x0099
            goto L_0x01a6
        L_0x0099:
            long r5 = r13.mo4844d(r4)
            androidx.recyclerview.widget.ua r7 = r13.mItemAnimator
            androidx.recyclerview.widget.V r7 = r7.obtainHolderInfo()
            r7.mo4983i(r4)
            androidx.recyclerview.widget.Ga r8 = r13.mViewInfoStore
            androidx.recyclerview.widget.qa r8 = r8.getFromOldChangeHolders(r5)
            if (r8 == 0) goto L_0x01a1
            boolean r9 = r8.shouldIgnore()
            if (r9 != 0) goto L_0x01a1
            androidx.recyclerview.widget.Ga r9 = r13.mViewInfoStore
            boolean r9 = r9.mo4708z(r8)
            androidx.recyclerview.widget.Ga r10 = r13.mViewInfoStore
            boolean r10 = r10.mo4708z(r4)
            if (r9 == 0) goto L_0x00cb
            if (r8 != r4) goto L_0x00cb
            androidx.recyclerview.widget.Ga r5 = r13.mViewInfoStore
            r5.mo4702c((androidx.recyclerview.widget.C0586qa) r4, (androidx.recyclerview.widget.C0548V) r7)
            goto L_0x01a6
        L_0x00cb:
            androidx.recyclerview.widget.Ga r11 = r13.mViewInfoStore
            androidx.recyclerview.widget.V r11 = r11.mo4696D(r8)
            androidx.recyclerview.widget.Ga r12 = r13.mViewInfoStore
            r12.mo4702c((androidx.recyclerview.widget.C0586qa) r4, (androidx.recyclerview.widget.C0548V) r7)
            androidx.recyclerview.widget.Ga r7 = r13.mViewInfoStore
            androidx.recyclerview.widget.V r7 = r7.mo4695C(r4)
            if (r11 != 0) goto L_0x0177
            androidx.recyclerview.widget.d r7 = r13.mChildHelper
            int r7 = r7.getChildCount()
            r9 = r2
        L_0x00e5:
            if (r9 >= r7) goto L_0x0153
            androidx.recyclerview.widget.d r10 = r13.mChildHelper
            android.view.View r10 = r10.getChildAt(r9)
            androidx.recyclerview.widget.qa r10 = getChildViewHolderInt(r10)
            if (r10 != r4) goto L_0x00f4
            goto L_0x0150
        L_0x00f4:
            long r11 = r13.mo4844d(r10)
            int r11 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r11 != 0) goto L_0x0150
            androidx.recyclerview.widget.P r0 = r13.mAdapter
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
            java.lang.String r13 = r13.mo4815Vb()
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
            java.lang.String r13 = r13.mo4815Vb()
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
            java.lang.String r4 = r13.mo4815Vb()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.e(r1, r4)
            goto L_0x01a6
        L_0x0177:
            r8.setIsRecyclable(r2)
            if (r9 == 0) goto L_0x017f
            r13.m687G(r8)
        L_0x017f:
            if (r8 == r4) goto L_0x0195
            if (r10 == 0) goto L_0x0186
            r13.m687G(r4)
        L_0x0186:
            r8.mShadowedHolder = r4
            r13.m687G(r8)
            androidx.recyclerview.widget.ja r5 = r13.mRecycler
            r5.mo5147m(r8)
            r4.setIsRecyclable(r2)
            r4.mShadowingHolder = r8
        L_0x0195:
            androidx.recyclerview.widget.ua r5 = r13.mItemAnimator
            boolean r4 = r5.mo5239a((androidx.recyclerview.widget.C0586qa) r8, (androidx.recyclerview.widget.C0586qa) r4, (androidx.recyclerview.widget.C0548V) r11, (androidx.recyclerview.widget.C0548V) r7)
            if (r4 == 0) goto L_0x01a6
            r13.postAnimationRunner()
            goto L_0x01a6
        L_0x01a1:
            androidx.recyclerview.widget.Ga r5 = r13.mViewInfoStore
            r5.mo4702c((androidx.recyclerview.widget.C0586qa) r4, (androidx.recyclerview.widget.C0548V) r7)
        L_0x01a6:
            int r0 = r0 + -1
            goto L_0x0085
        L_0x01aa:
            androidx.recyclerview.widget.Ga r0 = r13.mViewInfoStore
            androidx.recyclerview.widget.M r1 = r13.f566Ai
            r0.mo4700a(r1)
        L_0x01b1:
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            androidx.recyclerview.widget.ja r1 = r13.mRecycler
            r0.mo5044d((androidx.recyclerview.widget.C0572ja) r1)
            androidx.recyclerview.widget.oa r0 = r13.mState
            int r1 = r0.mItemCount
            r0.mPreviousLayoutItemCount = r1
            r13.mDataSetHasChangedAfterLayout = r2
            r13.f576di = r2
            r0.mRunSimpleAnimations = r2
            r0.mRunPredictiveAnimations = r2
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            r0.mRequestedSimpleAnimations = r2
            androidx.recyclerview.widget.ja r0 = r13.mRecycler
            java.util.ArrayList r0 = r0.mChangedScrap
            if (r0 == 0) goto L_0x01d3
            r0.clear()
        L_0x01d3:
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            boolean r1 = r0.mPrefetchMaxObservedInInitialPrefetch
            if (r1 == 0) goto L_0x01e2
            r0.mPrefetchMaxCountObserved = r2
            r0.mPrefetchMaxObservedInInitialPrefetch = r2
            androidx.recyclerview.widget.ja r0 = r13.mRecycler
            r0.updateViewCacheSize()
        L_0x01e2:
            androidx.recyclerview.widget.ca r0 = r13.mLayout
            androidx.recyclerview.widget.oa r1 = r13.mState
            r0.mo4734i((androidx.recyclerview.widget.C0582oa) r1)
            r13.onExitLayoutOrScroll()
            r13.mo4947v(r2)
            androidx.recyclerview.widget.Ga r0 = r13.mViewInfoStore
            r0.clear()
            int[] r0 = r13.f592vi
            r1 = r0[r2]
            r4 = r0[r3]
            r13.m699f(r0)
            int[] r0 = r13.f592vi
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
            boolean r0 = r13.f589ri
            if (r0 == 0) goto L_0x0302
            androidx.recyclerview.widget.P r0 = r13.mAdapter
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
            boolean r1 = f564Fi
            if (r1 == 0) goto L_0x025b
            android.view.ViewParent r1 = r0.getParent()
            if (r1 == 0) goto L_0x024e
            boolean r1 = r0.hasFocus()
            if (r1 != 0) goto L_0x025b
        L_0x024e:
            androidx.recyclerview.widget.d r0 = r13.mChildHelper
            int r0 = r0.getChildCount()
            if (r0 != 0) goto L_0x0265
            r13.requestFocus()
            goto L_0x0302
        L_0x025b:
            androidx.recyclerview.widget.d r1 = r13.mChildHelper
            boolean r0 = r1.isHidden(r0)
            if (r0 != 0) goto L_0x0265
            goto L_0x0302
        L_0x0265:
            androidx.recyclerview.widget.oa r0 = r13.mState
            long r0 = r0.mFocusedItemId
            r3 = -1
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            r1 = 0
            if (r0 == 0) goto L_0x0281
            androidx.recyclerview.widget.P r0 = r13.mAdapter
            boolean r0 = r0.hasStableIds()
            if (r0 == 0) goto L_0x0281
            androidx.recyclerview.widget.oa r0 = r13.mState
            long r5 = r0.mFocusedItemId
            androidx.recyclerview.widget.qa r0 = r13.findViewHolderForItemId(r5)
            goto L_0x0282
        L_0x0281:
            r0 = r1
        L_0x0282:
            if (r0 == 0) goto L_0x029a
            androidx.recyclerview.widget.d r5 = r13.mChildHelper
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
            androidx.recyclerview.widget.d r0 = r13.mChildHelper
            int r0 = r0.getChildCount()
            if (r0 <= 0) goto L_0x02e6
            androidx.recyclerview.widget.oa r0 = r13.mState
            int r0 = r0.mFocusedItemPosition
            r5 = -1
            if (r0 == r5) goto L_0x02aa
            goto L_0x02ab
        L_0x02aa:
            r0 = r2
        L_0x02ab:
            androidx.recyclerview.widget.oa r2 = r13.mState
            int r2 = r2.getItemCount()
            r6 = r0
        L_0x02b2:
            if (r6 >= r2) goto L_0x02ca
            androidx.recyclerview.widget.qa r7 = r13.findViewHolderForAdapterPosition(r6)
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
            androidx.recyclerview.widget.qa r2 = r13.findViewHolderForAdapterPosition(r0)
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
            androidx.recyclerview.widget.oa r0 = r13.mState
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
            r13.m702hn()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchLayout():void");
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return m700fn().dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return m700fn().dispatchNestedPreFling(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return m700fn().dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return m700fn().dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnScrollStateChanged(int i) {
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            caVar.onScrollStateChanged(i);
        }
        onScrollStateChanged(i);
        C0566ga gaVar = this.mScrollListener;
        if (gaVar != null) {
            gaVar.mo5124c(this, i);
        }
        List list = this.f590ti;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0566ga) this.f590ti.get(size)).mo5124c(this, i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnScrolled(int i, int i2) {
        this.f578fi++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        onScrolled(i, i2);
        C0566ga gaVar = this.mScrollListener;
        if (gaVar != null) {
            gaVar.mo5125f(this, i, i2);
        }
        List list = this.f590ti;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0566ga) this.f590ti.get(size)).mo5125f(this, i, i2);
            }
        }
        this.f578fi--;
    }

    /* access modifiers changed from: package-private */
    public void dispatchPendingImportantForAccessibilityChanges() {
        int i;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            C0586qa qaVar = (C0586qa) this.mPendingAccessibilityImportanceChange.get(size);
            if (qaVar.itemView.getParent() == this && !qaVar.shouldIgnore() && (i = qaVar.mPendingAccessibilityState) != -1) {
                ViewCompat.setImportantForAccessibility(qaVar.itemView, i);
                qaVar.mPendingAccessibilityState = -1;
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

    public void draw(Canvas canvas) {
        boolean z;
        boolean z2;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z3 = false;
        for (int i = 0; i < size; i++) {
            ((C0550X) this.mItemDecorations.get(i)).mo4664b(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.f580hi;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((float) ((-getHeight()) + paddingBottom), 0.0f);
            EdgeEffect edgeEffect2 = this.f580hi;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.f581ii;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.f581ii;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.f582ji;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate((float) (-paddingTop), (float) (-width));
            EdgeEffect edgeEffect6 = this.f582ji;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.f583ki;
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
            EdgeEffect edgeEffect8 = this.f583ki;
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
        if (this.f583ki == null) {
            this.f583ki = this.f579gi.mo4981b(this, 3);
            if (this.mClipToPadding) {
                this.f583ki.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.f583ki.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureLeftGlow() {
        if (this.f580hi == null) {
            this.f580hi = this.f579gi.mo4981b(this, 0);
            if (this.mClipToPadding) {
                this.f580hi.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.f580hi.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureRightGlow() {
        if (this.f582ji == null) {
            this.f582ji = this.f579gi.mo4981b(this, 2);
            if (this.mClipToPadding) {
                this.f582ji.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            } else {
                this.f582ji.setSize(getMeasuredHeight(), getMeasuredWidth());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureTopGlow() {
        if (this.f581ii == null) {
            this.f581ii = this.f579gi.mo4981b(this, 1);
            if (this.mClipToPadding) {
                this.f581ii.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
            } else {
                this.f581ii.setSize(getMeasuredWidth(), getMeasuredHeight());
            }
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

    public C0586qa findContainingViewHolder(View view) {
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(findContainingItemView);
    }

    public C0586qa findViewHolderForAdapterPosition(int i) {
        C0586qa qaVar = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && mo4838c(childViewHolderInt) == i) {
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                qaVar = childViewHolderInt;
            }
        }
        return qaVar;
    }

    public C0586qa findViewHolderForItemId(long j) {
        C0543P p = this.mAdapter;
        C0586qa qaVar = null;
        if (p != null && p.hasStableIds()) {
            int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            for (int i = 0; i < unfilteredChildCount; i++) {
                C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == j) {
                    if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    qaVar = childViewHolderInt;
                }
            }
        }
        return qaVar;
    }

    /* access modifiers changed from: package-private */
    public C0586qa findViewHolderForPosition(int i, boolean z) {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        C0586qa qaVar = null;
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
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
                qaVar = childViewHolderInt;
            }
        }
        return qaVar;
    }

    public boolean fling(int i, int i2) {
        C0558ca caVar = this.mLayout;
        int i3 = 0;
        if (caVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        } else if (this.f572Zh) {
            return false;
        } else {
            boolean canScrollHorizontally = caVar.canScrollHorizontally();
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
                    int i4 = this.f586ni;
                    int max = Math.max(-i4, Math.min(i, i4));
                    int i5 = this.f586ni;
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
            androidx.recyclerview.widget.ca r3 = r0.mLayout
            android.view.View r3 = r3.onInterceptFocusSearch(r1, r2)
            if (r3 == 0) goto L_0x000f
            return r3
        L_0x000f:
            androidx.recyclerview.widget.P r3 = r0.mAdapter
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x0025
            androidx.recyclerview.widget.ca r3 = r0.mLayout
            if (r3 == 0) goto L_0x0025
            boolean r3 = r16.isComputingLayout()
            if (r3 != 0) goto L_0x0025
            boolean r3 = r0.f572Zh
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
            androidx.recyclerview.widget.ca r3 = r0.mLayout
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
            boolean r14 = f563Ei
            if (r14 == 0) goto L_0x0057
            r2 = r3
            goto L_0x0057
        L_0x0056:
            r13 = r5
        L_0x0057:
            if (r13 != 0) goto L_0x0085
            androidx.recyclerview.widget.ca r3 = r0.mLayout
            boolean r3 = r3.canScrollHorizontally()
            if (r3 == 0) goto L_0x0085
            androidx.recyclerview.widget.ca r3 = r0.mLayout
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
            boolean r14 = f563Ei
            if (r14 == 0) goto L_0x0085
            r2 = r3
        L_0x0085:
            if (r13 == 0) goto L_0x00a0
            r16.consumePendingUpdateOperations()
            android.view.View r3 = r16.findContainingItemView(r17)
            if (r3 != 0) goto L_0x0091
            return r11
        L_0x0091:
            r16.mo4816Wb()
            androidx.recyclerview.widget.ca r3 = r0.mLayout
            androidx.recyclerview.widget.ja r13 = r0.mRecycler
            androidx.recyclerview.widget.oa r14 = r0.mState
            r3.mo4712a((android.view.View) r1, (int) r2, (androidx.recyclerview.widget.C0572ja) r13, (androidx.recyclerview.widget.C0582oa) r14)
            r0.mo4947v(r5)
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
            r16.mo4816Wb()
            androidx.recyclerview.widget.ca r3 = r0.mLayout
            androidx.recyclerview.widget.ja r6 = r0.mRecycler
            androidx.recyclerview.widget.oa r13 = r0.mState
            android.view.View r3 = r3.mo4712a((android.view.View) r1, (int) r2, (androidx.recyclerview.widget.C0572ja) r6, (androidx.recyclerview.widget.C0582oa) r13)
            r0.mo4947v(r5)
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
            r0.m688a((android.view.View) r3, (android.view.View) r11)
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
            androidx.recyclerview.widget.ca r6 = r0.mLayout
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
            java.lang.String r0 = r16.mo4815Vb()
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            return caVar.generateDefaultLayoutParams();
        }
        StringBuilder Pa = C0632a.m1011Pa("RecyclerView has no LayoutManager");
        Pa.append(mo4815Vb());
        throw new IllegalStateException(Pa.toString());
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            return caVar.generateLayoutParams(getContext(), attributeSet);
        }
        StringBuilder Pa = C0632a.m1011Pa("RecyclerView has no LayoutManager");
        Pa.append(mo4815Vb());
        throw new IllegalStateException(Pa.toString());
    }

    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public C0543P getAdapter() {
        return this.mAdapter;
    }

    public int getBaseline() {
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            return caVar.getBaseline();
        }
        return super.getBaseline();
    }

    /* access modifiers changed from: protected */
    public int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    public long getChildItemId(View view) {
        C0586qa childViewHolderInt;
        C0543P p = this.mAdapter;
        if (p == null || !p.hasStableIds() || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1;
        }
        return childViewHolderInt.getItemId();
    }

    public int getChildLayoutPosition(View view) {
        C0586qa childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    public C0586qa getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public C0594ua getItemAnimator() {
        return this.mItemAnimator;
    }

    /* access modifiers changed from: package-private */
    public Rect getItemDecorInsetsForChild(View view) {
        C0560da daVar = (C0560da) view.getLayoutParams();
        if (!daVar.mInsetsDirty) {
            return daVar.mDecorInsets;
        }
        if (this.mState.mInPreLayout && (daVar.isItemChanged() || daVar.mViewHolder.isInvalid())) {
            return daVar.mDecorInsets;
        }
        Rect rect = daVar.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mTempRect.set(0, 0, 0, 0);
            ((C0550X) this.mItemDecorations.get(i)).mo4988a(this.mTempRect, view, this, this.mState);
            int i2 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i2 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        daVar.mInsetsDirty = false;
        return rect;
    }

    public C0558ca getLayoutManager() {
        return this.mLayout;
    }

    /* access modifiers changed from: package-private */
    public long getNanoTime() {
        if (f562Di) {
            return System.nanoTime();
        }
        return 0;
    }

    public int getScrollState() {
        return this.f584li;
    }

    public boolean hasNestedScrollingParent() {
        return m700fn().hasNestedScrollingParent();
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    /* access modifiers changed from: package-private */
    public void initAdapterManager() {
        this.mAdapterHelper = new C0555b(new C0542O(this));
    }

    /* access modifiers changed from: package-private */
    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            StringBuilder Pa = C0632a.m1011Pa("Trying to set fast scroller without both required drawables.");
            Pa.append(mo4815Vb());
            throw new IllegalArgumentException(Pa.toString());
        }
        Resources resources = getContext().getResources();
        new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(C0046a.fastscroll_default_thickness), resources.getDimensionPixelSize(C0046a.fastscroll_minimum_range), resources.getDimensionPixelOffset(C0046a.fastscroll_margin));
    }

    /* access modifiers changed from: package-private */
    public void invalidateGlows() {
        this.f583ki = null;
        this.f581ii = null;
        this.f582ji = null;
        this.f580hi = null;
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
        return this.f577ei > 0;
    }

    public final boolean isLayoutSuppressed() {
        return this.f572Zh;
    }

    public boolean isNestedScrollingEnabled() {
        return m700fn().isNestedScrollingEnabled();
    }

    /* access modifiers changed from: package-private */
    public void jumpToPositionForSmoothScroller(int i) {
        if (this.mLayout != null) {
            setScrollState(2);
            this.mLayout.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    /* access modifiers changed from: package-private */
    public void markItemDecorInsetsDirty() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ((C0560da) this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        C0572ja jaVar = this.mRecycler;
        int size = jaVar.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0560da daVar = (C0560da) ((C0586qa) jaVar.mCachedViews.get(i2)).itemView.getLayoutParams();
            if (daVar != null) {
                daVar.mInsetsDirty = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void markKnownViewsInvalid() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        C0572ja jaVar = this.mRecycler;
        int size = jaVar.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0586qa qaVar = (C0586qa) jaVar.mCachedViews.get(i2);
            if (qaVar != null) {
                qaVar.addFlags(6);
                qaVar.addChangePayload((Object) null);
            }
        }
        C0543P p = jaVar.this$0.mAdapter;
        if (p == null || !p.hasStableIds()) {
            jaVar.recycleAndClearCachedViews();
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
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i3));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i) {
                childViewHolderInt.offsetPosition(i2, false);
                this.mState.mStructureChanged = true;
            }
        }
        C0572ja jaVar = this.mRecycler;
        int size = jaVar.mCachedViews.size();
        for (int i4 = 0; i4 < size; i4++) {
            C0586qa qaVar = (C0586qa) jaVar.mCachedViews.get(i4);
            if (qaVar != null && qaVar.mPosition >= i) {
                qaVar.offsetPosition(i2, true);
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
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i11));
            if (childViewHolderInt != null && (i10 = childViewHolderInt.mPosition) >= i5 && i10 <= i4) {
                if (i10 == i) {
                    childViewHolderInt.offsetPosition(i2 - i, false);
                } else {
                    childViewHolderInt.offsetPosition(i3, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        C0572ja jaVar = this.mRecycler;
        if (i < i2) {
            i7 = i2;
            i6 = -1;
            i8 = i;
        } else {
            i8 = i2;
            i6 = 1;
            i7 = i;
        }
        int size = jaVar.mCachedViews.size();
        for (int i12 = 0; i12 < size; i12++) {
            C0586qa qaVar = (C0586qa) jaVar.mCachedViews.get(i12);
            if (qaVar != null && (i9 = qaVar.mPosition) >= i8 && i9 <= i7) {
                if (i9 == i) {
                    qaVar.offsetPosition(i2 - i, false);
                } else {
                    qaVar.offsetPosition(i6, false);
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
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i4));
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
        C0572ja jaVar = this.mRecycler;
        int size = jaVar.mCachedViews.size();
        while (true) {
            size--;
            if (size >= 0) {
                C0586qa qaVar = (C0586qa) jaVar.mCachedViews.get(size);
                if (qaVar != null) {
                    int i6 = qaVar.mPosition;
                    if (i6 >= i3) {
                        qaVar.offsetPosition(-i2, z);
                    } else if (i6 >= i) {
                        qaVar.addFlags(8);
                        jaVar.recycleCachedViewAt(size);
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
            r4.f577ei = r0
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
            androidx.recyclerview.widget.ca r1 = r4.mLayout
            if (r1 == 0) goto L_0x001e
            r1.mo5043d((androidx.recyclerview.widget.RecyclerView) r4)
        L_0x001e:
            r4.mPostedAnimatorRunner = r0
            boolean r0 = f562Di
            if (r0 == 0) goto L_0x0068
            java.lang.ThreadLocal r0 = androidx.recyclerview.widget.C0597w.sGapWorker
            java.lang.Object r0 = r0.get()
            androidx.recyclerview.widget.w r0 = (androidx.recyclerview.widget.C0597w) r0
            r4.mGapWorker = r0
            androidx.recyclerview.widget.w r0 = r4.mGapWorker
            if (r0 != 0) goto L_0x0061
            androidx.recyclerview.widget.w r0 = new androidx.recyclerview.widget.w
            r0.<init>()
            r4.mGapWorker = r0
            android.view.Display r0 = androidx.core.view.ViewCompat.getDisplay(r4)
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
            androidx.recyclerview.widget.w r1 = r4.mGapWorker
            r2 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r2 = r2 / r0
            long r2 = (long) r2
            r1.mFrameIntervalNs = r2
            java.lang.ThreadLocal r0 = androidx.recyclerview.widget.C0597w.sGapWorker
            r0.set(r1)
        L_0x0061:
            androidx.recyclerview.widget.w r0 = r4.mGapWorker
            java.util.ArrayList r0 = r0.mRecyclerViews
            r0.add(r4)
        L_0x0068:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        C0597w wVar;
        super.onDetachedFromWindow();
        C0594ua uaVar = this.mItemAnimator;
        if (uaVar != null) {
            uaVar.endAnimations();
        }
        stopScroll();
        this.mIsAttached = false;
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            caVar.mo5019a(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.f596zi);
        this.mViewInfoStore.onDetach();
        if (f562Di && (wVar = this.mGapWorker) != null) {
            wVar.mRecyclerViews.remove(this);
            this.mGapWorker = null;
        }
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            ((C0550X) this.mItemDecorations.get(i)).mo4986a(canvas, this, this.mState);
        }
    }

    /* access modifiers changed from: package-private */
    public void onEnterLayoutOrScroll() {
        this.f577ei++;
    }

    /* access modifiers changed from: package-private */
    public void onExitLayoutOrScroll() {
        mo4945t(true);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        if (this.mLayout != null && !this.f572Zh && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                f2 = this.mLayout.canScrollVertically() ? -motionEvent.getAxisValue(9) : 0.0f;
                if (this.mLayout.canScrollHorizontally()) {
                    f = motionEvent.getAxisValue(10);
                    if (!(f2 == 0.0f && f == 0.0f)) {
                        scrollByInternal((int) (f * this.f587oi), (int) (f2 * this.f588qi), motionEvent);
                    }
                }
            } else {
                if ((motionEvent.getSource() & InputDeviceCompat.SOURCE_ROTARY_ENCODER) != 0) {
                    float axisValue = motionEvent.getAxisValue(26);
                    if (this.mLayout.canScrollVertically()) {
                        f2 = -axisValue;
                    } else if (this.mLayout.canScrollHorizontally()) {
                        f = axisValue;
                        f2 = 0.0f;
                        scrollByInternal((int) (f * this.f587oi), (int) (f2 * this.f588qi), motionEvent);
                    }
                }
                f2 = 0.0f;
                f = 0.0f;
                scrollByInternal((int) (f * this.f587oi), (int) (f2 * this.f588qi), motionEvent);
            }
            f = 0.0f;
            scrollByInternal((int) (f * this.f587oi), (int) (f2 * this.f588qi), motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.f572Zh) {
            return false;
        }
        this.f568Vh = null;
        if (m692a(motionEvent)) {
            m696cn();
            return true;
        }
        C0558ca caVar = this.mLayout;
        if (caVar == null) {
            return false;
        }
        boolean canScrollHorizontally = caVar.canScrollHorizontally();
        boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.f573_h) {
                this.f573_h = false;
            }
            this.f585mi = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            if (this.f584li == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                stopNestedScroll(1);
            }
            int[] iArr = this.f594xi;
            iArr[1] = 0;
            iArr[0] = 0;
            int i = canScrollHorizontally ? 1 : 0;
            if (canScrollVertically) {
                i |= 2;
            }
            startNestedScroll(i, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.f585mi);
            if (findPointerIndex < 0) {
                StringBuilder Pa = C0632a.m1011Pa("Error processing scroll; pointer index for id ");
                Pa.append(this.f585mi);
                Pa.append(" not found. Did any MotionEvents get skipped?");
                Log.e("RecyclerView", Pa.toString());
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.f584li != 1) {
                int i2 = x2 - this.mInitialTouchX;
                int i3 = y2 - this.mInitialTouchY;
                if (!canScrollHorizontally || Math.abs(i2) <= this.mTouchSlop) {
                    z = false;
                } else {
                    this.mLastTouchX = x2;
                    z = true;
                }
                if (canScrollVertically && Math.abs(i3) > this.mTouchSlop) {
                    this.mLastTouchY = y2;
                    z = true;
                }
                if (z) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            m696cn();
        } else if (actionMasked == 5) {
            this.f585mi = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x3;
            this.mInitialTouchX = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y3;
            this.mInitialTouchY = y3;
        } else if (actionMasked == 6) {
            m694b(motionEvent);
        }
        if (this.f584li == 1) {
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
        C0558ca caVar = this.mLayout;
        if (caVar == null) {
            defaultOnMeasure(i, i2);
            return;
        }
        boolean z = false;
        if (caVar.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mLayout.mo5020a(this.mRecycler, this.mState, i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            if (!z && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    m697dn();
                }
                this.mLayout.setMeasureSpecs(i, i2);
                this.mState.mIsMeasuring = true;
                m698en();
                this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    m698en();
                    this.mLayout.setMeasuredDimensionFromChildren(i, i2);
                }
            }
        } else if (this.mHasFixedSize) {
            this.mLayout.mo5020a(this.mRecycler, this.mState, i, i2);
        } else {
            if (this.mAdapterUpdateDuringMeasure) {
                mo4816Wb();
                onEnterLayoutOrScroll();
                m701gn();
                onExitLayoutOrScroll();
                C0582oa oaVar = this.mState;
                if (oaVar.mRunPredictiveAnimations) {
                    oaVar.mInPreLayout = true;
                } else {
                    this.mAdapterHelper.consumeUpdatesInOnePass();
                    this.mState.mInPreLayout = false;
                }
                this.mAdapterUpdateDuringMeasure = false;
                mo4947v(false);
            } else if (this.mState.mRunPredictiveAnimations) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            C0543P p = this.mAdapter;
            if (p != null) {
                this.mState.mItemCount = p.getItemCount();
            } else {
                this.mState.mItemCount = 0;
            }
            mo4816Wb();
            this.mLayout.mo5020a(this.mRecycler, this.mState, i, i2);
            mo4947v(false);
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
        C0558ca caVar = this.mLayout;
        if (caVar != null && (parcelable2 = this.mPendingSavedState.mLayoutState) != null) {
            caVar.onRestoreInstanceState(parcelable2);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.mo4949a(savedState2);
        } else {
            C0558ca caVar = this.mLayout;
            if (caVar != null) {
                savedState.mLayoutState = caVar.onSaveInstanceState();
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

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int i;
        int i2;
        boolean z2;
        int i3;
        int i4;
        MotionEvent motionEvent2 = motionEvent;
        boolean z3 = false;
        if (this.f572Zh || this.f573_h) {
            return false;
        }
        C0564fa faVar = this.f568Vh;
        if (faVar != null) {
            faVar.mo4663a(this, motionEvent2);
            int action = motionEvent.getAction();
            if (action == 3 || action == 1) {
                this.f568Vh = null;
            }
            z = true;
        } else if (motionEvent.getAction() == 0) {
            z = false;
        } else {
            z = m692a(motionEvent);
        }
        if (z) {
            m696cn();
            return true;
        }
        C0558ca caVar = this.mLayout;
        if (caVar == null) {
            return false;
        }
        boolean canScrollHorizontally = caVar.canScrollHorizontally();
        boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            int[] iArr = this.f594xi;
            iArr[1] = 0;
            iArr[0] = 0;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int[] iArr2 = this.f594xi;
        obtain.offsetLocation((float) iArr2[0], (float) iArr2[1]);
        if (actionMasked == 0) {
            this.f585mi = motionEvent2.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            int i5 = canScrollHorizontally ? 1 : 0;
            if (canScrollVertically) {
                i5 |= 2;
            }
            startNestedScroll(i5, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.addMovement(obtain);
            this.mVelocityTracker.computeCurrentVelocity(1000, (float) this.f586ni);
            float f = canScrollHorizontally ? -this.mVelocityTracker.getXVelocity(this.f585mi) : 0.0f;
            float f2 = canScrollVertically ? -this.mVelocityTracker.getYVelocity(this.f585mi) : 0.0f;
            if ((f == 0.0f && f2 == 0.0f) || !fling((int) f, (int) f2)) {
                setScrollState(0);
            }
            m703in();
            z3 = true;
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent2.findPointerIndex(this.f585mi);
            if (findPointerIndex < 0) {
                StringBuilder Pa = C0632a.m1011Pa("Error processing scroll; pointer index for id ");
                Pa.append(this.f585mi);
                Pa.append(" not found. Did any MotionEvents get skipped?");
                Log.e("RecyclerView", Pa.toString());
                return false;
            }
            int x2 = (int) (motionEvent2.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent2.getY(findPointerIndex) + 0.5f);
            int i6 = this.mLastTouchX - x2;
            int i7 = this.mLastTouchY - y2;
            int[] iArr3 = this.f595yi;
            iArr3[0] = 0;
            iArr3[1] = 0;
            if (dispatchNestedPreScroll(i6, i7, iArr3, this.mScrollOffset, 0)) {
                int[] iArr4 = this.f595yi;
                i6 -= iArr4[0];
                i7 -= iArr4[1];
                int[] iArr5 = this.f594xi;
                int i8 = iArr5[0];
                int[] iArr6 = this.mScrollOffset;
                iArr5[0] = i8 + iArr6[0];
                iArr5[1] = iArr5[1] + iArr6[1];
            }
            if (this.f584li != 1) {
                if (!canScrollHorizontally || Math.abs(i2) <= (i4 = this.mTouchSlop)) {
                    z2 = false;
                } else {
                    i2 = i2 > 0 ? i2 - i4 : i2 + i4;
                    z2 = true;
                }
                if (canScrollVertically && Math.abs(i) > (i3 = this.mTouchSlop)) {
                    i = i > 0 ? i - i3 : i + i3;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
            int i9 = i;
            if (this.f584li == 1) {
                int[] iArr7 = this.mScrollOffset;
                this.mLastTouchX = x2 - iArr7[0];
                this.mLastTouchY = y2 - iArr7[1];
                if (scrollByInternal(canScrollHorizontally ? i2 : 0, canScrollVertically ? i9 : 0, motionEvent2)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                if (!(this.mGapWorker == null || (i2 == 0 && i9 == 0))) {
                    this.mGapWorker.mo5257b(this, i2, i9);
                }
            }
        } else if (actionMasked == 3) {
            m696cn();
        } else if (actionMasked == 5) {
            this.f585mi = motionEvent2.getPointerId(actionIndex);
            int x3 = (int) (motionEvent2.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x3;
            this.mInitialTouchX = x3;
            int y3 = (int) (motionEvent2.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y3;
            this.mInitialTouchY = y3;
        } else if (actionMasked == 6) {
            m694b(motionEvent);
        }
        if (!z3) {
            this.mVelocityTracker.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation(this, this.f596zi);
            this.mPostedAnimatorRunner = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeAndRecycleViews() {
        C0594ua uaVar = this.mItemAnimator;
        if (uaVar != null) {
            uaVar.endAnimations();
        }
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            caVar.mo5041c(this.mRecycler);
            this.mLayout.mo5044d(this.mRecycler);
        }
        this.mRecycler.clear();
    }

    /* access modifiers changed from: package-private */
    public boolean removeAnimatingView(View view) {
        mo4816Wb();
        boolean removeViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (removeViewIfHidden) {
            C0586qa childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.mo5147m(childViewHolderInt);
            this.mRecycler.mo5146l(childViewHolderInt);
        }
        mo4947v(!removeViewIfHidden);
        return removeViewIfHidden;
    }

    /* access modifiers changed from: protected */
    public void removeDetachedView(View view, boolean z) {
        C0586qa childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt + mo4815Vb());
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    /* access modifiers changed from: package-private */
    public void repositionShadowingViews() {
        C0586qa qaVar;
        int childCount = this.mChildHelper.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mChildHelper.getChildAt(i);
            C0586qa childViewHolder = getChildViewHolder(childAt);
            if (!(childViewHolder == null || (qaVar = childViewHolder.mShadowingHolder) == null)) {
                View view = qaVar.itemView;
                int left = childAt.getLeft();
                int top = childAt.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.mo5028a(this, this.mState, view, view2) && view2 != null) {
            m688a(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.mo5025a(this, view, rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.f567Uh.size();
        for (int i = 0; i < size; i++) {
            ((C0564fa) this.f567Uh.get(i)).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        if (this.f570Xh != 0 || this.f572Zh) {
            this.f571Yh = true;
        } else {
            super.requestLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void saveOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            C0586qa childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    public void scrollBy(int i, int i2) {
        C0558ca caVar = this.mLayout;
        if (caVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.f572Zh) {
            boolean canScrollHorizontally = caVar.canScrollHorizontally();
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
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scrollByInternal(int r22, int r23, android.view.MotionEvent r24) {
        /*
            r21 = this;
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r21.consumePendingUpdateOperations()
            androidx.recyclerview.widget.P r0 = r8.mAdapter
            r12 = 1
            r13 = 0
            if (r0 == 0) goto L_0x0029
            int[] r0 = r8.f595yi
            r0[r13] = r13
            r0[r12] = r13
            r8.mo4818a((int) r9, (int) r10, (int[]) r0)
            int[] r0 = r8.f595yi
            r1 = r0[r13]
            r0 = r0[r12]
            int r2 = r9 - r1
            int r3 = r10 - r0
            r6 = r0
            r7 = r1
            r14 = r2
            r15 = r3
            goto L_0x002d
        L_0x0029:
            r6 = r13
            r7 = r6
            r14 = r7
            r15 = r14
        L_0x002d:
            java.util.ArrayList r0 = r8.mItemDecorations
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0038
            r21.invalidate()
        L_0x0038:
            int[] r5 = r8.f595yi
            r5[r13] = r13
            r5[r12] = r13
            int[] r4 = r8.mScrollOffset
            r16 = 0
            r0 = r21
            r1 = r7
            r2 = r6
            r3 = r14
            r17 = r4
            r4 = r15
            r18 = r5
            r5 = r17
            r19 = r6
            r6 = r16
            r20 = r7
            r7 = r18
            r0.dispatchNestedScroll(r1, r2, r3, r4, r5, r6, r7)
            int[] r0 = r8.f595yi
            r1 = r0[r13]
            int r14 = r14 - r1
            r0 = r0[r12]
            int r15 = r15 - r0
            int r0 = r8.mLastTouchX
            int[] r1 = r8.mScrollOffset
            r2 = r1[r13]
            int r0 = r0 - r2
            r8.mLastTouchX = r0
            int r0 = r8.mLastTouchY
            r2 = r1[r12]
            int r0 = r0 - r2
            r8.mLastTouchY = r0
            int[] r0 = r8.f594xi
            r2 = r0[r13]
            r3 = r1[r13]
            int r2 = r2 + r3
            r0[r13] = r2
            r2 = r0[r12]
            r1 = r1[r12]
            int r2 = r2 + r1
            r0[r12] = r2
            int r0 = r21.getOverScrollMode()
            r1 = 2
            if (r0 == r1) goto L_0x0124
            if (r11 == 0) goto L_0x0121
            r0 = 8194(0x2002, float:1.1482E-41)
            boolean r0 = androidx.core.view.MotionEventCompat.isFromSource(r11, r0)
            if (r0 != 0) goto L_0x0121
            float r0 = r24.getX()
            float r1 = (float) r14
            float r2 = r24.getY()
            float r3 = (float) r15
            r4 = 0
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r5 >= 0) goto L_0x00bd
            r21.ensureLeftGlow()
            android.widget.EdgeEffect r5 = r8.f580hi
            float r7 = -r1
            int r11 = r21.getWidth()
            float r11 = (float) r11
            float r7 = r7 / r11
            int r11 = r21.getHeight()
            float r11 = (float) r11
            float r2 = r2 / r11
            float r2 = r6 - r2
            int r11 = android.os.Build.VERSION.SDK_INT
            r5.onPull(r7, r2)
            goto L_0x00d8
        L_0x00bd:
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r5 <= 0) goto L_0x00da
            r21.ensureRightGlow()
            android.widget.EdgeEffect r5 = r8.f582ji
            int r7 = r21.getWidth()
            float r7 = (float) r7
            float r7 = r1 / r7
            int r11 = r21.getHeight()
            float r11 = (float) r11
            float r2 = r2 / r11
            int r11 = android.os.Build.VERSION.SDK_INT
            r5.onPull(r7, r2)
        L_0x00d8:
            r2 = r12
            goto L_0x00db
        L_0x00da:
            r2 = r13
        L_0x00db:
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 >= 0) goto L_0x00f7
            r21.ensureTopGlow()
            android.widget.EdgeEffect r2 = r8.f581ii
            float r5 = -r3
            int r6 = r21.getHeight()
            float r6 = (float) r6
            float r5 = r5 / r6
            int r6 = r21.getWidth()
            float r6 = (float) r6
            float r0 = r0 / r6
            int r6 = android.os.Build.VERSION.SDK_INT
            r2.onPull(r5, r0)
            goto L_0x0113
        L_0x00f7:
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 <= 0) goto L_0x0114
            r21.ensureBottomGlow()
            android.widget.EdgeEffect r2 = r8.f583ki
            int r5 = r21.getHeight()
            float r5 = (float) r5
            float r5 = r3 / r5
            int r7 = r21.getWidth()
            float r7 = (float) r7
            float r0 = r0 / r7
            float r6 = r6 - r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r2.onPull(r5, r6)
        L_0x0113:
            r2 = r12
        L_0x0114:
            if (r2 != 0) goto L_0x011e
            int r0 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x011e
            int r0 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0121
        L_0x011e:
            androidx.core.view.ViewCompat.postInvalidateOnAnimation(r21)
        L_0x0121:
            r21.considerReleasingGlowsOnScroll(r22, r23)
        L_0x0124:
            r1 = r20
            r0 = r19
            if (r1 != 0) goto L_0x012c
            if (r0 == 0) goto L_0x012f
        L_0x012c:
            r8.dispatchOnScrolled(r1, r0)
        L_0x012f:
            boolean r2 = r21.awakenScrollBars()
            if (r2 != 0) goto L_0x0138
            r21.invalidate()
        L_0x0138:
            if (r1 != 0) goto L_0x013e
            if (r0 == 0) goto L_0x013d
            goto L_0x013e
        L_0x013d:
            r12 = r13
        L_0x013e:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.scrollByInternal(int, int, android.view.MotionEvent):boolean");
    }

    public void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i) {
        if (!this.f572Zh) {
            stopScroll();
            C0558ca caVar = this.mLayout;
            if (caVar == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
                return;
            }
            caVar.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!shouldDeferAccessibilityEvent(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setChildImportantForAccessibilityInternal(C0586qa qaVar, int i) {
        if (isComputingLayout()) {
            qaVar.mPendingAccessibilityState = i;
            this.mPendingAccessibilityImportanceChange.add(qaVar);
            return false;
        }
        ViewCompat.setImportantForAccessibility(qaVar.itemView, i);
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

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition == null) {
            super.setLayoutTransition((LayoutTransition) null);
            return;
        }
        throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    public void setNestedScrollingEnabled(boolean z) {
        m700fn().setNestedScrollingEnabled(z);
    }

    /* access modifiers changed from: package-private */
    public void setScrollState(int i) {
        if (i != this.f584li) {
            this.f584li = i;
            if (i != 2) {
                this.mViewFlinger.stop();
                C0558ca caVar = this.mLayout;
                if (caVar != null) {
                    caVar.stopSmoothScroller();
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
        this.f574ai = i | this.f574ai;
        return true;
    }

    public void smoothScrollBy(int i, int i2) {
        mo4817a(i, i2, (Interpolator) null, RtlSpacingHelper.UNDEFINED, false);
    }

    public void smoothScrollToPosition(int i) {
        if (!this.f572Zh) {
            C0558ca caVar = this.mLayout;
            if (caVar == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            } else {
                caVar.mo4747a(this, this.mState, i);
            }
        }
    }

    public boolean startNestedScroll(int i) {
        return m700fn().startNestedScroll(i);
    }

    public void stopNestedScroll() {
        m700fn().stopNestedScroll();
    }

    public void stopScroll() {
        setScrollState(0);
        this.mViewFlinger.stop();
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            caVar.stopSmoothScroller();
        }
    }

    public final void suppressLayout(boolean z) {
        if (z != this.f572Zh) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.f572Zh = false;
                if (!(!this.f571Yh || this.mLayout == null || this.mAdapter == null)) {
                    requestLayout();
                }
                this.f571Yh = false;
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
            this.f572Zh = true;
            this.f573_h = true;
            stopScroll();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: t */
    public void mo4945t(boolean z) {
        this.f577ei--;
        if (this.f577ei < 1) {
            this.f577ei = 0;
            if (z) {
                int i = this.f574ai;
                this.f574ai = 0;
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

    /* access modifiers changed from: package-private */
    /* renamed from: u */
    public void mo4946u(boolean z) {
        this.f576di = z | this.f576di;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: v */
    public void mo4947v(boolean z) {
        if (this.f570Xh < 1) {
            this.f570Xh = 1;
        }
        if (!z && !this.f572Zh) {
            this.f571Yh = false;
        }
        if (this.f570Xh == 1) {
            if (z && this.f571Yh && !this.f572Zh && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.f572Zh) {
                this.f571Yh = false;
            }
        }
        this.f570Xh--;
    }

    /* access modifiers changed from: package-private */
    public void viewRangeUpdate(int i, int i2, Object obj) {
        int i3;
        int i4;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < unfilteredChildCount; i6++) {
            View unfilteredChildAt = this.mChildHelper.getUnfilteredChildAt(i6);
            C0586qa childViewHolderInt = getChildViewHolderInt(unfilteredChildAt);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i4 = childViewHolderInt.mPosition) >= i && i4 < i5) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((C0560da) unfilteredChildAt.getLayoutParams()).mInsetsDirty = true;
            }
        }
        C0572ja jaVar = this.mRecycler;
        int size = jaVar.mCachedViews.size();
        while (true) {
            size--;
            if (size >= 0) {
                C0586qa qaVar = (C0586qa) jaVar.mCachedViews.get(size);
                if (qaVar != null && (i3 = qaVar.mPosition) >= i && i3 < i5) {
                    qaVar.addFlags(2);
                    jaVar.recycleCachedViewAt(size);
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
    /* renamed from: c */
    public int mo4838c(C0586qa qaVar) {
        if (qaVar.hasAnyOfTheFlags(524) || !qaVar.isBound()) {
            return -1;
        }
        C0555b bVar = this.mAdapterHelper;
        int i = qaVar.mPosition;
        int size = bVar.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0553a aVar = (C0553a) bVar.mPendingUpdates.get(i2);
            int i3 = aVar.cmd;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = aVar.positionStart;
                    if (i4 <= i) {
                        int i5 = aVar.itemCount;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = aVar.positionStart;
                    if (i6 == i) {
                        i = aVar.itemCount;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (aVar.itemCount <= i) {
                            i++;
                        }
                    }
                }
            } else if (aVar.positionStart <= i) {
                i += aVar.itemCount;
            }
        }
        return i;
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return m700fn().dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return m700fn().dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    public boolean hasNestedScrollingParent(int i) {
        return m700fn().hasNestedScrollingParent(i);
    }

    public boolean startNestedScroll(int i, int i2) {
        return m700fn().startNestedScroll(i, i2);
    }

    public void stopNestedScroll(int i) {
        m700fn().stopNestedScroll(i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        TypedArray typedArray;
        ClassLoader classLoader;
        Object[] objArr;
        Constructor<? extends U> constructor;
        NoSuchMethodException noSuchMethodException;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        int i3 = i;
        this.mObserver = new C0574ka(this);
        this.mRecycler = new C0572ja(this);
        this.mViewInfoStore = new C0534Ga();
        new C0537J(this);
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList();
        this.f567Uh = new ArrayList();
        this.f570Xh = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.f576di = false;
        this.f577ei = 0;
        this.f578fi = 0;
        this.f579gi = new C0546T();
        this.mItemAnimator = new C0581o();
        this.f584li = 0;
        this.f585mi = -1;
        this.f587oi = Float.MIN_VALUE;
        this.f588qi = Float.MIN_VALUE;
        boolean z = true;
        this.f589ri = true;
        this.mViewFlinger = new C0584pa(this);
        this.mPrefetchRegistry = f562Di ? new C0593u() : null;
        this.mState = new C0582oa();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.f591ui = new C0549W(this);
        this.mPostedAnimatorRunner = false;
        this.f592vi = new int[2];
        this.mScrollOffset = new int[2];
        this.f594xi = new int[2];
        this.f595yi = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.f596zi = new C0538K(this);
        this.f566Ai = new C0540M(this);
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, f561Ci, i3, 0);
            this.mClipToPadding = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        } else {
            this.mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.f587oi = ViewConfigurationCompat.getScaledHorizontalScrollFactor(viewConfiguration, context2);
        this.f588qi = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context2);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f586ni = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.mo5237a(this.f591ui);
        initAdapterManager();
        this.mChildHelper = new C0559d(new C0541N(this));
        if (ViewCompat.getImportantForAutofill(this) == 0) {
            int i4 = Build.VERSION.SDK_INT;
            setImportantForAutofill(8);
        }
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            int i5 = Build.VERSION.SDK_INT;
            setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        mo4828a(new C0590sa(this));
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet2, C0047b.RecyclerView, i3, 0);
            if (Build.VERSION.SDK_INT >= 29) {
                typedArray = obtainStyledAttributes2;
                i2 = 262144;
                saveAttributeDataForStyleable(context, C0047b.RecyclerView, attributeSet, obtainStyledAttributes2, i, 0);
            } else {
                typedArray = obtainStyledAttributes2;
                i2 = 262144;
            }
            String string = typedArray.getString(C0047b.RecyclerView_layoutManager);
            if (typedArray.getInt(C0047b.RecyclerView_android_descendantFocusability, -1) == -1) {
                setDescendantFocusability(i2);
            }
            this.f569Wh = typedArray.getBoolean(C0047b.RecyclerView_fastScrollEnabled, false);
            if (this.f569Wh) {
                initFastScroller((StateListDrawable) typedArray.getDrawable(C0047b.RecyclerView_fastScrollVerticalThumbDrawable), typedArray.getDrawable(C0047b.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArray.getDrawable(C0047b.RecyclerView_fastScrollHorizontalThumbDrawable), typedArray.getDrawable(C0047b.RecyclerView_fastScrollHorizontalTrackDrawable));
            }
            typedArray.recycle();
            if (string != null) {
                String trim = string.trim();
                if (!trim.isEmpty()) {
                    if (trim.charAt(0) == '.') {
                        trim = context.getPackageName() + trim;
                    } else if (!trim.contains(".")) {
                        trim = RecyclerView.class.getPackage().getName() + '.' + trim;
                    }
                    String str = trim;
                    try {
                        if (isInEditMode()) {
                            classLoader = getClass().getClassLoader();
                        } else {
                            classLoader = context.getClassLoader();
                        }
                        Class<? extends U> asSubclass = Class.forName(str, false, classLoader).asSubclass(C0558ca.class);
                        try {
                            constructor = asSubclass.getConstructor(f565Gi);
                            objArr = new Object[]{context2, attributeSet2, Integer.valueOf(i), 0};
                        } catch (NoSuchMethodException e) {
                            noSuchMethodException = e;
                            constructor = asSubclass.getConstructor(new Class[0]);
                            objArr = null;
                        }
                        constructor.setAccessible(true);
                        mo4822a((C0558ca) constructor.newInstance(objArr));
                    } catch (NoSuchMethodException e2) {
                        e2.initCause(noSuchMethodException);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e2);
                    } catch (ClassNotFoundException e3) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + str, e3);
                    } catch (InvocationTargetException e4) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e4);
                    } catch (InstantiationException e5) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e5);
                    } catch (IllegalAccessException e6) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + str, e6);
                    } catch (ClassCastException e7) {
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + str, e7);
                    }
                }
            }
            int i6 = Build.VERSION.SDK_INT;
            TypedArray obtainStyledAttributes3 = context2.obtainStyledAttributes(attributeSet2, f560Bi, i3, 0);
            if (Build.VERSION.SDK_INT >= 29) {
                saveAttributeDataForStyleable(context, f560Bi, attributeSet, obtainStyledAttributes3, i, 0);
            }
            z = obtainStyledAttributes3.getBoolean(0, true);
            obtainStyledAttributes3.recycle();
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(z);
    }

    public final void dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        m700fn().dispatchNestedScroll(i, i2, i3, i4, iArr, i5, iArr2);
    }

    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C0576la();
        Parcelable mLayoutState;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mLayoutState = parcel.readParcelable(classLoader == null ? C0558ca.class.getClassLoader() : classLoader);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo4949a(SavedState savedState) {
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
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            return caVar.generateLayoutParams(layoutParams);
        }
        StringBuilder Pa = C0632a.m1011Pa("RecyclerView has no LayoutManager");
        Pa.append(mo4815Vb());
        throw new IllegalStateException(Pa.toString());
    }

    /* renamed from: a */
    public void mo4828a(C0590sa saVar) {
        this.mAccessibilityDelegate = saVar;
        ViewCompat.setAccessibilityDelegate(this, this.mAccessibilityDelegate);
    }

    /* renamed from: a */
    public void mo4819a(C0543P p) {
        setLayoutFrozen(false);
        C0543P p2 = this.mAdapter;
        if (p2 != null) {
            p2.mo4799b((C0545S) this.mObserver);
            this.mAdapter.mo4798b(this);
        }
        removeAndRecycleViews();
        this.mAdapterHelper.reset();
        C0543P p3 = this.mAdapter;
        this.mAdapter = p;
        if (p != null) {
            p.mo4796a(this.mObserver);
        }
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            caVar.mo5018a(p3, this.mAdapter);
        }
        C0572ja jaVar = this.mRecycler;
        C0543P p4 = this.mAdapter;
        jaVar.clear();
        jaVar.getRecycledViewPool().mo5131a(p3, p4, false);
        this.mState.mStructureChanged = true;
        mo4946u(false);
        requestLayout();
    }

    /* renamed from: b */
    public void mo4836b(C0566ga gaVar) {
        List list = this.f590ti;
        if (list != null) {
            list.remove(gaVar);
        }
    }

    /* renamed from: b */
    public void mo4835b(C0564fa faVar) {
        this.f567Uh.remove(faVar);
        if (this.f568Vh == faVar) {
            this.f568Vh = null;
        }
    }

    /* renamed from: b */
    private void m694b(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f585mi) {
            int i = actionIndex == 0 ? 1 : 0;
            this.f585mi = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4837b(C0586qa qaVar, C0548V v, C0548V v2) {
        m687G(qaVar);
        qaVar.setIsRecyclable(false);
        if (this.mItemAnimator.mo5241b(qaVar, v, v2)) {
            postAnimationRunner();
        }
    }

    /* renamed from: b */
    static void m695b(C0586qa qaVar) {
        WeakReference weakReference = qaVar.mNestedRecyclerView;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view != qaVar.itemView) {
                    ViewParent parent = view.getParent();
                    view = parent instanceof View ? (View) parent : null;
                } else {
                    return;
                }
            }
            qaVar.mNestedRecyclerView = null;
        }
    }

    /* renamed from: a */
    public void mo4822a(C0558ca caVar) {
        if (caVar != this.mLayout) {
            stopScroll();
            if (this.mLayout != null) {
                C0594ua uaVar = this.mItemAnimator;
                if (uaVar != null) {
                    uaVar.endAnimations();
                }
                this.mLayout.mo5041c(this.mRecycler);
                this.mLayout.mo5044d(this.mRecycler);
                this.mRecycler.clear();
                if (this.mIsAttached) {
                    this.mLayout.mo5019a(this, this.mRecycler);
                }
                this.mLayout.mo5083i((RecyclerView) null);
                this.mLayout = null;
            } else {
                this.mRecycler.clear();
            }
            C0559d dVar = this.mChildHelper;
            dVar.mBucket.reset();
            int size = dVar.mHiddenViews.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                dVar.mCallback.onLeftHiddenState((View) dVar.mHiddenViews.get(size));
                dVar.mHiddenViews.remove(size);
            }
            C0541N n = dVar.mCallback;
            int childCount = n.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = n.getChildAt(i);
                n.this$0.dispatchChildDetached(childAt);
                childAt.clearAnimation();
            }
            n.this$0.removeAllViews();
            this.mLayout = caVar;
            if (caVar != null) {
                if (caVar.mRecyclerView == null) {
                    this.mLayout.mo5083i(this);
                    if (this.mIsAttached) {
                        this.mLayout.mo5043d(this);
                    }
                } else {
                    throw new IllegalArgumentException("LayoutManager " + caVar + " is already attached to a RecyclerView:" + caVar.mRecyclerView.mo4815Vb());
                }
            }
            this.mRecycler.updateViewCacheSize();
            requestLayout();
        }
    }

    /* renamed from: a */
    public void mo4821a(C0550X x, int i) {
        C0558ca caVar = this.mLayout;
        if (caVar != null) {
            caVar.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i < 0) {
            this.mItemDecorations.add(x);
        } else {
            this.mItemDecorations.add(i, x);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    /* renamed from: a */
    public void mo4820a(C0550X x) {
        mo4821a(x, -1);
    }

    /* renamed from: a */
    public void mo4824a(C0566ga gaVar) {
        if (this.f590ti == null) {
            this.f590ti = new ArrayList();
        }
        this.f590ti.add(gaVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4818a(int i, int i2, int[] iArr) {
        mo4816Wb();
        onEnterLayoutOrScroll();
        int i3 = Build.VERSION.SDK_INT;
        Trace.beginSection("RV Scroll");
        mo4825a(this.mState);
        int a = i != 0 ? this.mLayout.mo4710a(i, this.mRecycler, this.mState) : 0;
        int b = i2 != 0 ? this.mLayout.mo4721b(i2, this.mRecycler, this.mState) : 0;
        int i4 = Build.VERSION.SDK_INT;
        Trace.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        mo4947v(false);
        if (iArr != null) {
            iArr[0] = a;
            iArr[1] = b;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4817a(int i, int i2, Interpolator interpolator, int i3, boolean z) {
        C0558ca caVar = this.mLayout;
        if (caVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else if (!this.f572Zh) {
            int i4 = 0;
            if (!caVar.canScrollHorizontally()) {
                i = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
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
                    this.mViewFlinger.smoothScrollBy(i, i2, i3, interpolator);
                    return;
                }
                scrollBy(i, i2);
            }
        }
    }

    /* renamed from: a */
    private void m688a(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof C0560da) {
            C0560da daVar = (C0560da) layoutParams;
            if (!daVar.mInsetsDirty) {
                Rect rect = daVar.mDecorInsets;
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
        this.mLayout.mo5026a(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    /* renamed from: a */
    public void mo4823a(C0564fa faVar) {
        this.f567Uh.add(faVar);
    }

    /* renamed from: a */
    private boolean m692a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.f567Uh.size();
        int i = 0;
        while (i < size) {
            C0564fa faVar = (C0564fa) this.f567Uh.get(i);
            if (!faVar.mo4665b(this, motionEvent) || action == 3) {
                i++;
            } else {
                this.f568Vh = faVar;
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo4829a(C0594ua uaVar) {
        C0594ua uaVar2 = this.mItemAnimator;
        if (uaVar2 != null) {
            uaVar2.endAnimations();
            this.mItemAnimator.mo5237a((C0549W) null);
        }
        this.mItemAnimator = uaVar;
        C0594ua uaVar3 = this.mItemAnimator;
        if (uaVar3 != null) {
            uaVar3.mo5237a(this.f591ui);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4825a(C0582oa oaVar) {
        if (getScrollState() == 2) {
            OverScroller overScroller = this.mViewFlinger.f661Ys;
            overScroller.getFinalX();
            overScroller.getCurrX();
            overScroller.getFinalY();
            overScroller.getCurrY();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4826a(C0586qa qaVar, C0548V v) {
        qaVar.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && qaVar.isUpdated() && !qaVar.isRemoved() && !qaVar.shouldIgnore()) {
            this.mViewInfoStore.mo4699a(mo4844d(qaVar), qaVar);
        }
        this.mViewInfoStore.mo4704d(qaVar, v);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4827a(C0586qa qaVar, C0548V v, C0548V v2) {
        qaVar.setIsRecyclable(false);
        if (this.mItemAnimator.mo5238a(qaVar, v, v2)) {
            postAnimationRunner();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo4830a(C0586qa qaVar) {
        C0594ua uaVar = this.mItemAnimator;
        return uaVar == null || uaVar.mo5170a(qaVar, qaVar.getUnmodifiedPayloads());
    }
}
