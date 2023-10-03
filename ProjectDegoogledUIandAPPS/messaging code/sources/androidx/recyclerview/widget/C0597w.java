package androidx.recyclerview.widget;

import android.os.Build;
import android.os.Trace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* renamed from: androidx.recyclerview.widget.w */
final class C0597w implements Runnable {
    static final ThreadLocal sGapWorker = new ThreadLocal();
    static Comparator sTaskComparator = new C0591t();
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList mRecyclerViews = new ArrayList();
    private ArrayList mTasks = new ArrayList();

    C0597w() {
    }

    /* renamed from: a */
    private C0586qa m936a(RecyclerView recyclerView, int i, long j) {
        boolean z;
        int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
        int i2 = 0;
        while (true) {
            if (i2 >= unfilteredChildCount) {
                z = false;
                break;
            }
            C0586qa childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            return null;
        }
        C0572ja jaVar = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            C0586qa tryGetViewHolderForPositionByDeadline = jaVar.tryGetViewHolderForPositionByDeadline(i, false, j);
            if (tryGetViewHolderForPositionByDeadline != null) {
                if (!tryGetViewHolderForPositionByDeadline.isBound() || tryGetViewHolderForPositionByDeadline.isInvalid()) {
                    jaVar.mo5141a(tryGetViewHolderForPositionByDeadline, false);
                } else {
                    jaVar.recycleView(tryGetViewHolderForPositionByDeadline.itemView);
                }
            }
            return tryGetViewHolderForPositionByDeadline;
        } finally {
            recyclerView.mo4945t(false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo5257b(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.mPostTimeNs == 0) {
            this.mPostTimeNs = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        C0593u uVar = recyclerView.mPrefetchRegistry;
        uVar.mPrefetchDx = i;
        uVar.mPrefetchDy = i2;
    }

    /* access modifiers changed from: package-private */
    public void prefetch(long j) {
        RecyclerView recyclerView;
        C0595v vVar;
        int size = this.mRecyclerViews.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView2 = (RecyclerView) this.mRecyclerViews.get(i2);
            if (recyclerView2.getWindowVisibility() == 0) {
                recyclerView2.mPrefetchRegistry.mo5233a(recyclerView2, false);
                i += recyclerView2.mPrefetchRegistry.mCount;
            }
        }
        this.mTasks.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView3 = (RecyclerView) this.mRecyclerViews.get(i4);
            if (recyclerView3.getWindowVisibility() == 0) {
                C0593u uVar = recyclerView3.mPrefetchRegistry;
                int abs = Math.abs(uVar.mPrefetchDy) + Math.abs(uVar.mPrefetchDx);
                int i5 = i3;
                for (int i6 = 0; i6 < uVar.mCount * 2; i6 += 2) {
                    if (i5 >= this.mTasks.size()) {
                        vVar = new C0595v();
                        this.mTasks.add(vVar);
                    } else {
                        vVar = (C0595v) this.mTasks.get(i5);
                    }
                    int i7 = uVar.mPrefetchArray[i6 + 1];
                    vVar.immediate = i7 <= abs;
                    vVar.viewVelocity = abs;
                    vVar.distanceToItem = i7;
                    vVar.view = recyclerView3;
                    vVar.position = uVar.mPrefetchArray[i6];
                    i5++;
                }
                i3 = i5;
            }
        }
        Collections.sort(this.mTasks, sTaskComparator);
        int i8 = 0;
        while (i8 < this.mTasks.size()) {
            C0595v vVar2 = (C0595v) this.mTasks.get(i8);
            if (vVar2.view != null) {
                C0586qa a = m936a(vVar2.view, vVar2.position, vVar2.immediate ? Long.MAX_VALUE : j);
                if (!(a == null || a.mNestedRecyclerView == null || !a.isBound() || a.isInvalid() || (recyclerView = (RecyclerView) a.mNestedRecyclerView.get()) == null)) {
                    if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.getUnfilteredChildCount() != 0) {
                        recyclerView.removeAndRecycleViews();
                    }
                    C0593u uVar2 = recyclerView.mPrefetchRegistry;
                    uVar2.mo5233a(recyclerView, true);
                    if (uVar2.mCount != 0) {
                        try {
                            int i9 = Build.VERSION.SDK_INT;
                            Trace.beginSection("RV Nested Prefetch");
                            C0582oa oaVar = recyclerView.mState;
                            C0543P p = recyclerView.mAdapter;
                            oaVar.mLayoutStep = 1;
                            oaVar.mItemCount = p.getItemCount();
                            oaVar.mInPreLayout = false;
                            oaVar.mTrackOldChangeHolders = false;
                            oaVar.mIsMeasuring = false;
                            for (int i10 = 0; i10 < uVar2.mCount * 2; i10 += 2) {
                                m936a(recyclerView, uVar2.mPrefetchArray[i10], j);
                            }
                        } finally {
                            int i11 = Build.VERSION.SDK_INT;
                            Trace.endSection();
                        }
                    }
                }
                vVar2.immediate = false;
                vVar2.viewVelocity = 0;
                vVar2.distanceToItem = 0;
                vVar2.view = null;
                vVar2.position = 0;
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
                    RecyclerView recyclerView = (RecyclerView) this.mRecyclerViews.get(i2);
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
