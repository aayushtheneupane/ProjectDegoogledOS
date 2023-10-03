package android.support.p002v7.widget;

import android.os.Build;
import android.os.Trace;
import android.support.p002v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* renamed from: android.support.v7.widget.GapWorker */
final class GapWorker implements Runnable {
    static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal<>();
    static Comparator<Task> sTaskComparator = new Comparator<Task>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
            if (r5.view == null) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
            if (r4 != false) goto L_0x001d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int compare(java.lang.Object r5, java.lang.Object r6) {
            /*
                r4 = this;
                android.support.v7.widget.GapWorker$Task r5 = (android.support.p002v7.widget.GapWorker.Task) r5
                android.support.v7.widget.GapWorker$Task r6 = (android.support.p002v7.widget.GapWorker.Task) r6
                android.support.v7.widget.RecyclerView r4 = r5.view
                r0 = 0
                r1 = 1
                if (r4 != 0) goto L_0x000c
                r4 = r1
                goto L_0x000d
            L_0x000c:
                r4 = r0
            L_0x000d:
                android.support.v7.widget.RecyclerView r2 = r6.view
                if (r2 != 0) goto L_0x0013
                r2 = r1
                goto L_0x0014
            L_0x0013:
                r2 = r0
            L_0x0014:
                r3 = -1
                if (r4 == r2) goto L_0x001f
                android.support.v7.widget.RecyclerView r4 = r5.view
                if (r4 != 0) goto L_0x001d
            L_0x001b:
                r0 = r1
                goto L_0x0039
            L_0x001d:
                r0 = r3
                goto L_0x0039
            L_0x001f:
                boolean r4 = r5.immediate
                boolean r2 = r6.immediate
                if (r4 == r2) goto L_0x0028
                if (r4 == 0) goto L_0x001b
                goto L_0x001d
            L_0x0028:
                int r4 = r6.viewVelocity
                int r1 = r5.viewVelocity
                int r4 = r4 - r1
                if (r4 == 0) goto L_0x0031
            L_0x002f:
                r0 = r4
                goto L_0x0039
            L_0x0031:
                int r4 = r5.distanceToItem
                int r5 = r6.distanceToItem
                int r4 = r4 - r5
                if (r4 == 0) goto L_0x0039
                goto L_0x002f
            L_0x0039:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.GapWorker.C02071.compare(java.lang.Object, java.lang.Object):int");
        }
    };
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList<RecyclerView> mRecyclerViews = new ArrayList<>();
    private ArrayList<Task> mTasks = new ArrayList<>();

    /* renamed from: android.support.v7.widget.GapWorker$LayoutPrefetchRegistryImpl */
    static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        LayoutPrefetchRegistryImpl() {
        }

        public void addPosition(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            } else if (i2 >= 0) {
                int i3 = this.mCount * 2;
                int[] iArr = this.mPrefetchArray;
                if (iArr == null) {
                    this.mPrefetchArray = new int[4];
                    Arrays.fill(this.mPrefetchArray, -1);
                } else if (i3 >= iArr.length) {
                    this.mPrefetchArray = new int[(i3 * 2)];
                    System.arraycopy(iArr, 0, this.mPrefetchArray, 0, iArr.length);
                }
                int[] iArr2 = this.mPrefetchArray;
                iArr2[i3] = i;
                iArr2[i3 + 1] = i2;
                this.mCount++;
            } else {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
        }

        /* access modifiers changed from: package-private */
        public void collectPrefetchPositionsFromView(RecyclerView recyclerView, boolean z) {
            this.mCount = 0;
            int[] iArr = this.mPrefetchArray;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.mLayout;
            if (recyclerView.mAdapter != null && layoutManager != null && layoutManager.isItemPrefetchEnabled()) {
                if (z) {
                    if (!recyclerView.mAdapterHelper.hasPendingUpdates()) {
                        layoutManager.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                    }
                } else if (!recyclerView.hasPendingAdapterUpdates()) {
                    layoutManager.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, recyclerView.mState, this);
                }
                int i = this.mCount;
                if (i > layoutManager.mPrefetchMaxCountObserved) {
                    layoutManager.mPrefetchMaxCountObserved = i;
                    layoutManager.mPrefetchMaxObservedInInitialPrefetch = z;
                    recyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean lastPrefetchIncludedPosition(int i) {
            if (this.mPrefetchArray != null) {
                int i2 = this.mCount * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.mPrefetchArray[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.GapWorker$Task */
    static class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        Task() {
        }
    }

    GapWorker() {
    }

    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerView, int i, long j) {
        boolean z;
        int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
        int i2 = 0;
        while (true) {
            if (i2 >= unfilteredChildCount) {
                z = false;
                break;
            }
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            return null;
        }
        RecyclerView.Recycler recycler = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline = recycler.tryGetViewHolderForPositionByDeadline(i, false, j);
            if (tryGetViewHolderForPositionByDeadline != null) {
                if (!tryGetViewHolderForPositionByDeadline.isBound() || tryGetViewHolderForPositionByDeadline.isInvalid()) {
                    recycler.addViewHolderToRecycledViewPool(tryGetViewHolderForPositionByDeadline, false);
                } else {
                    recycler.recycleView(tryGetViewHolderForPositionByDeadline.itemView);
                }
            }
            return tryGetViewHolderForPositionByDeadline;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void postFromTraversal(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.mPostTimeNs == 0) {
            this.mPostTimeNs = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.mPrefetchRegistry;
        layoutPrefetchRegistryImpl.mPrefetchDx = i;
        layoutPrefetchRegistryImpl.mPrefetchDy = i2;
    }

    /* access modifiers changed from: package-private */
    public void prefetch(long j) {
        RecyclerView recyclerView;
        Task task;
        int size = this.mRecyclerViews.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView2 = this.mRecyclerViews.get(i2);
            if (recyclerView2.getWindowVisibility() == 0) {
                recyclerView2.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView2, false);
                i += recyclerView2.mPrefetchRegistry.mCount;
            }
        }
        this.mTasks.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView3 = this.mRecyclerViews.get(i4);
            if (recyclerView3.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView3.mPrefetchRegistry;
                int abs = Math.abs(layoutPrefetchRegistryImpl.mPrefetchDy) + Math.abs(layoutPrefetchRegistryImpl.mPrefetchDx);
                int i5 = i3;
                for (int i6 = 0; i6 < layoutPrefetchRegistryImpl.mCount * 2; i6 += 2) {
                    if (i5 >= this.mTasks.size()) {
                        task = new Task();
                        this.mTasks.add(task);
                    } else {
                        task = this.mTasks.get(i5);
                    }
                    int i7 = layoutPrefetchRegistryImpl.mPrefetchArray[i6 + 1];
                    task.immediate = i7 <= abs;
                    task.viewVelocity = abs;
                    task.distanceToItem = i7;
                    task.view = recyclerView3;
                    task.position = layoutPrefetchRegistryImpl.mPrefetchArray[i6];
                    i5++;
                }
                i3 = i5;
            }
        }
        Collections.sort(this.mTasks, sTaskComparator);
        int i8 = 0;
        while (i8 < this.mTasks.size()) {
            Task task2 = this.mTasks.get(i8);
            if (task2.view != null) {
                RecyclerView.ViewHolder prefetchPositionWithDeadline = prefetchPositionWithDeadline(task2.view, task2.position, task2.immediate ? Long.MAX_VALUE : j);
                if (!(prefetchPositionWithDeadline == null || prefetchPositionWithDeadline.mNestedRecyclerView == null || !prefetchPositionWithDeadline.isBound() || prefetchPositionWithDeadline.isInvalid() || (recyclerView = (RecyclerView) prefetchPositionWithDeadline.mNestedRecyclerView.get()) == null)) {
                    if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.getUnfilteredChildCount() != 0) {
                        recyclerView.removeAndRecycleViews();
                    }
                    LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl2 = recyclerView.mPrefetchRegistry;
                    layoutPrefetchRegistryImpl2.collectPrefetchPositionsFromView(recyclerView, true);
                    if (layoutPrefetchRegistryImpl2.mCount != 0) {
                        try {
                            int i9 = Build.VERSION.SDK_INT;
                            Trace.beginSection("RV Nested Prefetch");
                            RecyclerView.State state = recyclerView.mState;
                            RecyclerView.Adapter adapter = recyclerView.mAdapter;
                            state.mLayoutStep = 1;
                            state.mItemCount = adapter.getItemCount();
                            state.mInPreLayout = false;
                            state.mTrackOldChangeHolders = false;
                            state.mIsMeasuring = false;
                            for (int i10 = 0; i10 < layoutPrefetchRegistryImpl2.mCount * 2; i10 += 2) {
                                prefetchPositionWithDeadline(recyclerView, layoutPrefetchRegistryImpl2.mPrefetchArray[i10], j);
                            }
                        } finally {
                            int i11 = Build.VERSION.SDK_INT;
                            Trace.endSection();
                        }
                    }
                }
                task2.immediate = false;
                task2.viewVelocity = 0;
                task2.distanceToItem = 0;
                task2.view = null;
                task2.position = 0;
                i8++;
            } else {
                return;
            }
        }
    }

    public void run() {
        try {
            int i = Build.VERSION.SDK_INT;
            Trace.beginSection("RV Prefetch");
            if (!this.mRecyclerViews.isEmpty()) {
                int size = this.mRecyclerViews.size();
                long j = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = this.mRecyclerViews.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j = Math.max(recyclerView.getDrawingTime(), j);
                    }
                }
                if (j == 0) {
                    this.mPostTimeNs = 0;
                    int i3 = Build.VERSION.SDK_INT;
                    Trace.endSection();
                    return;
                }
                prefetch(TimeUnit.MILLISECONDS.toNanos(j) + this.mFrameIntervalNs);
                this.mPostTimeNs = 0;
                int i4 = Build.VERSION.SDK_INT;
                Trace.endSection();
            }
        } finally {
            this.mPostTimeNs = 0;
            int i5 = Build.VERSION.SDK_INT;
            Trace.endSection();
        }
    }
}
