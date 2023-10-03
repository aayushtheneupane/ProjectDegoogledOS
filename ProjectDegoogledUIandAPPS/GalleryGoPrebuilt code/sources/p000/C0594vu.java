package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* renamed from: vu */
/* compiled from: PG */
public final class C0594vu implements Runnable {

    /* renamed from: a */
    public static final ThreadLocal f16160a = new ThreadLocal();

    /* renamed from: f */
    private static final Comparator f16161f = new C0591vr();

    /* renamed from: b */
    public final ArrayList f16162b = new ArrayList();

    /* renamed from: c */
    public long f16163c;

    /* renamed from: d */
    private long f16164d;

    /* renamed from: e */
    private final ArrayList f16165e = new ArrayList();

    /* renamed from: a */
    public final void mo10413a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.f16164d == 0) {
            this.f16164d = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        C0592vs vsVar = recyclerView.mPrefetchRegistry;
        vsVar.f16151a = i;
        vsVar.f16152b = i2;
    }

    /* renamed from: a */
    private static final C0667ym m15633a(RecyclerView recyclerView, int i, long j) {
        int b = recyclerView.mChildHelper.mo10314b();
        for (int i2 = 0; i2 < b; i2++) {
            C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.mo10317c(i2));
            if (childViewHolderInt.f16384c == i && !childViewHolderInt.mo10650j()) {
                return null;
            }
        }
        C0656yb ybVar = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            C0667ym a = ybVar.mo10604a(i, j);
            if (a != null) {
                if (!a.mo10652l() || a.mo10650j()) {
                    ybVar.mo10608a(a, false);
                } else {
                    ybVar.mo10606a(a.f16382a);
                }
            }
            return a;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public final void run() {
        C0593vt vtVar;
        RecyclerView recyclerView;
        long j;
        RecyclerView recyclerView2;
        C0593vt vtVar2;
        boolean z;
        long j2 = 0;
        try {
            C0264jo.m14493a(RecyclerView.TRACE_PREFETCH_TAG);
            if (!this.f16162b.isEmpty()) {
                int size = this.f16162b.size();
                long j3 = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView3 = (RecyclerView) this.f16162b.get(i);
                    if (recyclerView3.getWindowVisibility() == 0) {
                        j3 = Math.max(recyclerView3.getDrawingTime(), j3);
                    }
                }
                if (j3 != 0) {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(j3) + this.f16163c;
                    int size2 = this.f16162b.size();
                    int i2 = 0;
                    for (int i3 = 0; i3 < size2; i3++) {
                        RecyclerView recyclerView4 = (RecyclerView) this.f16162b.get(i3);
                        if (recyclerView4.getWindowVisibility() == 0) {
                            recyclerView4.mPrefetchRegistry.mo10411a(recyclerView4, false);
                            i2 += recyclerView4.mPrefetchRegistry.f16154d;
                        }
                    }
                    this.f16165e.ensureCapacity(i2);
                    int i4 = 0;
                    for (int i5 = 0; i5 < size2; i5++) {
                        RecyclerView recyclerView5 = (RecyclerView) this.f16162b.get(i5);
                        if (recyclerView5.getWindowVisibility() == 0) {
                            C0592vs vsVar = recyclerView5.mPrefetchRegistry;
                            int abs = Math.abs(vsVar.f16151a) + Math.abs(vsVar.f16152b);
                            int i6 = 0;
                            while (true) {
                                int i7 = vsVar.f16154d;
                                if (i6 >= i7 + i7) {
                                    break;
                                }
                                if (i4 >= this.f16165e.size()) {
                                    vtVar2 = new C0593vt();
                                    this.f16165e.add(vtVar2);
                                } else {
                                    vtVar2 = (C0593vt) this.f16165e.get(i4);
                                }
                                int[] iArr = vsVar.f16153c;
                                int i8 = iArr[i6 + 1];
                                if (i8 <= abs) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                vtVar2.f16155a = z;
                                vtVar2.f16156b = abs;
                                vtVar2.f16157c = i8;
                                vtVar2.f16158d = recyclerView5;
                                vtVar2.f16159e = iArr[i6];
                                i4++;
                                i6 += 2;
                            }
                        }
                    }
                    Collections.sort(this.f16165e, f16161f);
                    for (int i9 = 0; i9 < this.f16165e.size() && (recyclerView = vtVar.f16158d) != null; i9++) {
                        if (!(vtVar = (C0593vt) this.f16165e.get(i9)).f16155a) {
                            j = nanos;
                        } else {
                            j = RecyclerView.FOREVER_NS;
                        }
                        C0667ym a = m15633a(recyclerView, vtVar.f16159e, j);
                        if (!(a == null || a.f16383b == null || !a.mo10652l() || a.mo10650j() || (recyclerView2 = (RecyclerView) a.f16383b.get()) == null)) {
                            if (recyclerView2.mDataSetHasChangedAfterLayout) {
                                if (recyclerView2.mChildHelper.mo10314b() != 0) {
                                    recyclerView2.removeAndRecycleViews();
                                }
                            }
                            C0592vs vsVar2 = recyclerView2.mPrefetchRegistry;
                            vsVar2.mo10411a(recyclerView2, true);
                            if (vsVar2.f16154d != 0) {
                                C0264jo.m14493a(RecyclerView.TRACE_NESTED_PREFETCH_TAG);
                                C0664yj yjVar = recyclerView2.mState;
                                C0634xg xgVar = recyclerView2.mAdapter;
                                yjVar.f16361d = 1;
                                yjVar.f16362e = xgVar.mo220a();
                                yjVar.f16364g = false;
                                yjVar.f16365h = false;
                                yjVar.f16366i = false;
                                int i10 = 0;
                                while (true) {
                                    int i11 = vsVar2.f16154d;
                                    if (i10 >= i11 + i11) {
                                        break;
                                    }
                                    m15633a(recyclerView2, vsVar2.f16153c[i10], nanos);
                                    i10 += 2;
                                }
                                C0264jo.m14492a();
                            }
                        }
                        vtVar.f16155a = false;
                        vtVar.f16156b = 0;
                        vtVar.f16157c = 0;
                        vtVar.f16158d = null;
                        vtVar.f16159e = 0;
                    }
                }
                j2 = 0;
            }
            this.f16164d = j2;
            C0264jo.m14492a();
        } catch (Throwable th) {
            this.f16164d = 0;
            throw th;
        } finally {
        }
    }
}
