package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.util.ArrayList;

/* renamed from: androidx.recyclerview.widget.ia */
public class C0570ia {

    /* renamed from: Qs */
    private int f639Qs = 0;
    SparseArray mScrap = new SparseArray();

    /* renamed from: yb */
    private C0568ha m884yb(int i) {
        C0568ha haVar = (C0568ha) this.mScrap.get(i);
        if (haVar != null) {
            return haVar;
        }
        C0568ha haVar2 = new C0568ha();
        this.mScrap.put(i, haVar2);
        return haVar2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5131a(C0543P p, C0543P p2, boolean z) {
        if (p != null) {
            this.f639Qs--;
        }
        if (!z && this.f639Qs == 0) {
            for (int i = 0; i < this.mScrap.size(); i++) {
                ((C0568ha) this.mScrap.valueAt(i)).mScrapHeap.clear();
            }
        }
        if (p2 != null) {
            this.f639Qs++;
        }
    }

    /* access modifiers changed from: package-private */
    public void factorInBindTime(int i, long j) {
        C0568ha yb = m884yb(i);
        yb.mBindRunningAverageNs = runningAverage(yb.mBindRunningAverageNs, j);
    }

    /* access modifiers changed from: package-private */
    public void factorInCreateTime(int i, long j) {
        C0568ha yb = m884yb(i);
        yb.mCreateRunningAverageNs = runningAverage(yb.mCreateRunningAverageNs, j);
    }

    /* renamed from: k */
    public void mo5134k(C0586qa qaVar) {
        int itemViewType = qaVar.getItemViewType();
        ArrayList arrayList = m884yb(itemViewType).mScrapHeap;
        if (((C0568ha) this.mScrap.get(itemViewType)).mMaxScrap > arrayList.size()) {
            qaVar.resetInternal();
            arrayList.add(qaVar);
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
        long j3 = m884yb(i).mBindRunningAverageNs;
        return j3 == 0 || j + j3 < j2;
    }

    /* access modifiers changed from: package-private */
    public boolean willCreateInTime(int i, long j, long j2) {
        long j3 = m884yb(i).mCreateRunningAverageNs;
        return j3 == 0 || j + j3 < j2;
    }
}
