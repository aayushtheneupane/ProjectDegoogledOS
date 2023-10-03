package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import java.util.Arrays;

@SuppressLint({"VisibleForTests"})
/* renamed from: androidx.recyclerview.widget.u */
class C0593u implements C0554aa {
    int mCount;
    int[] mPrefetchArray;
    int mPrefetchDx;
    int mPrefetchDy;

    C0593u() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5233a(RecyclerView recyclerView, boolean z) {
        this.mCount = 0;
        int[] iArr = this.mPrefetchArray;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        C0558ca caVar = recyclerView.mLayout;
        if (recyclerView.mAdapter != null && caVar != null && caVar.isItemPrefetchEnabled()) {
            if (z) {
                if (!recyclerView.mAdapterHelper.hasPendingUpdates()) {
                    caVar.mo4746a(recyclerView.mAdapter.getItemCount(), (C0554aa) this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                caVar.mo4745a(this.mPrefetchDx, this.mPrefetchDy, recyclerView.mState, (C0554aa) this);
            }
            int i = this.mCount;
            if (i > caVar.mPrefetchMaxCountObserved) {
                caVar.mPrefetchMaxCountObserved = i;
                caVar.mPrefetchMaxObservedInInitialPrefetch = z;
                recyclerView.mRecycler.updateViewCacheSize();
            }
        }
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
