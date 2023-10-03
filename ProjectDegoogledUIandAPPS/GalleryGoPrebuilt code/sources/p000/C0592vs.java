package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.Arrays;

/* renamed from: vs */
/* compiled from: PG */
public final class C0592vs implements C0646xs {

    /* renamed from: a */
    public int f16151a;

    /* renamed from: b */
    public int f16152b;

    /* renamed from: c */
    public int[] f16153c;

    /* renamed from: d */
    public int f16154d;

    /* renamed from: a */
    public final void mo10410a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        } else if (i2 >= 0) {
            int i3 = this.f16154d;
            int i4 = i3 + i3;
            int[] iArr = this.f16153c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f16153c = iArr2;
                Arrays.fill(iArr2, -1);
            } else {
                int length = iArr.length;
                if (i4 >= length) {
                    int[] iArr3 = new int[(i4 + i4)];
                    this.f16153c = iArr3;
                    System.arraycopy(iArr, 0, iArr3, 0, length);
                }
            }
            int[] iArr4 = this.f16153c;
            iArr4[i4] = i;
            iArr4[i4 + 1] = i2;
            this.f16154d++;
        } else {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10409a() {
        int[] iArr = this.f16153c;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.f16154d = 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10411a(RecyclerView recyclerView, boolean z) {
        this.f16154d = 0;
        int[] iArr = this.f16153c;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        C0647xt xtVar = recyclerView.mLayout;
        if (recyclerView.mAdapter != null && xtVar != null && xtVar.f16305p) {
            if (z) {
                if (!recyclerView.mAdapterHelper.mo10093d()) {
                    xtVar.mo10462a(recyclerView.mAdapter.mo220a(), (C0646xs) this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                xtVar.mo4531a(this.f16151a, this.f16152b, recyclerView.mState, (C0646xs) this);
            }
            int i = this.f16154d;
            if (i > xtVar.f16306q) {
                xtVar.f16306q = i;
                xtVar.f16307r = z;
                recyclerView.mRecycler.mo10609b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo10412a(int i) {
        if (this.f16153c != null) {
            int i2 = this.f16154d;
            int i3 = i2 + i2;
            for (int i4 = 0; i4 < i3; i4 += 2) {
                if (this.f16153c[i4] == i) {
                    return true;
                }
            }
        }
        return false;
    }
}
