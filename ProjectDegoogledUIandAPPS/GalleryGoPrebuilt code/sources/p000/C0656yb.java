package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: yb */
/* compiled from: PG */
public final class C0656yb {

    /* renamed from: a */
    public final ArrayList f16324a = new ArrayList();

    /* renamed from: b */
    public ArrayList f16325b = null;

    /* renamed from: c */
    public final ArrayList f16326c = new ArrayList();

    /* renamed from: d */
    public final List f16327d = Collections.unmodifiableList(this.f16324a);

    /* renamed from: e */
    public int f16328e = 2;

    /* renamed from: f */
    public C0655ya f16329f;

    /* renamed from: g */
    public C0665yk f16330g;

    /* renamed from: h */
    public final /* synthetic */ RecyclerView f16331h;

    /* renamed from: i */
    private int f16332i = 2;

    public C0656yb(RecyclerView recyclerView) {
        this.f16331h = recyclerView;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10608a(C0667ym ymVar, boolean z) {
        RecyclerView.clearNestedRecyclerViewIfNotNested(ymVar);
        View view = ymVar.f16382a;
        C0669yo yoVar = this.f16331h.mAccessibilityDelegate;
        if (yoVar != null) {
            C0315ll b = yoVar.mo234b();
            C0340mj.m14698a(view, b instanceof C0668yn ? (C0315ll) ((C0668yn) b).f16400b.remove(view) : null);
        }
        if (z) {
            C0657yc ycVar = this.f16331h.mRecyclerListener;
            if (ycVar != null) {
                ycVar.mo10616a();
            }
            C0634xg xgVar = this.f16331h.mAdapter;
            if (xgVar != null) {
                xgVar.mo4520a(ymVar);
            }
            RecyclerView recyclerView = this.f16331h;
            if (recyclerView.mState != null) {
                recyclerView.mViewInfoStore.mo10759d(ymVar);
            }
        }
        ymVar.f16396o = null;
        C0655ya d = mo10615d();
        int i = ymVar.f16387f;
        ArrayList arrayList = d.mo10600a(i).f16319a;
        C0653xz xzVar = (C0653xz) d.f16322a.get(i);
        if (arrayList.size() < 5) {
            ymVar.mo10658r();
            arrayList.add(ymVar);
        }
    }

    /* renamed from: a */
    public final void mo10605a() {
        this.f16324a.clear();
        mo10613c();
    }

    /* renamed from: a */
    public final int mo10603a(int i) {
        if (i < 0 || i >= this.f16331h.mState.mo10626a()) {
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + this.f16331h.mState.mo10626a() + this.f16331h.exceptionLabel());
        }
        RecyclerView recyclerView = this.f16331h;
        return recyclerView.mState.f16364g ? recyclerView.mAdapterHelper.mo10090b(i) : i;
    }

    /* renamed from: d */
    public final C0655ya mo10615d() {
        if (this.f16329f == null) {
            this.f16329f = new C0655ya();
        }
        return this.f16329f;
    }

    /* renamed from: a */
    private final void m16076a(ViewGroup viewGroup, boolean z) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                m16076a((ViewGroup) childAt, true);
            }
        }
        if (!z) {
            return;
        }
        if (viewGroup.getVisibility() == 4) {
            viewGroup.setVisibility(0);
            viewGroup.setVisibility(4);
            return;
        }
        int visibility = viewGroup.getVisibility();
        viewGroup.setVisibility(4);
        viewGroup.setVisibility(visibility);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo10611b(View view) {
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.f16392k = null;
        childViewHolderInt.f16393l = false;
        childViewHolderInt.mo10648h();
        mo10607a(childViewHolderInt);
    }

    /* renamed from: c */
    public final void mo10613c() {
        for (int size = this.f16326c.size() - 1; size >= 0; size--) {
            mo10610b(size);
        }
        this.f16326c.clear();
        if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
            this.f16331h.mPrefetchRegistry.mo10409a();
        }
    }

    /* renamed from: b */
    public final void mo10610b(int i) {
        mo10608a((C0667ym) this.f16326c.get(i), true);
        this.f16326c.remove(i);
    }

    /* renamed from: a */
    public final void mo10606a(View view) {
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.mo10654n()) {
            this.f16331h.removeDetachedView(view, false);
        }
        if (childViewHolderInt.mo10645e()) {
            childViewHolderInt.mo10646f();
        } else if (childViewHolderInt.mo10647g()) {
            childViewHolderInt.mo10648h();
        }
        mo10607a(childViewHolderInt);
        if (this.f16331h.mItemAnimator != null && !childViewHolderInt.mo10659s()) {
            this.f16331h.mItemAnimator.mo10373c(childViewHolderInt);
        }
    }

    /* renamed from: a */
    public final void mo10607a(C0667ym ymVar) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        boolean z4 = false;
        if (ymVar.mo10645e() || ymVar.f16382a.getParent() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Scrapped or attached views may not be recycled. isScrap:");
            sb.append(ymVar.mo10645e());
            sb.append(" isAttached:");
            if (ymVar.f16382a.getParent() == null) {
                z3 = false;
            }
            sb.append(z3);
            sb.append(this.f16331h.exceptionLabel());
            throw new IllegalArgumentException(sb.toString());
        } else if (ymVar.mo10654n()) {
            throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + ymVar + this.f16331h.exceptionLabel());
        } else if (!ymVar.mo10642b()) {
            if ((ymVar.f16391j & 16) != 0 || !C0340mj.m14709c(ymVar.f16382a)) {
                z = false;
            } else {
                z = true;
            }
            if (ymVar.mo10659s()) {
                if (this.f16332i <= 0 || ymVar.mo10640a(526)) {
                    z2 = false;
                } else {
                    int size = this.f16326c.size();
                    if (size >= this.f16332i && size > 0) {
                        mo10610b(0);
                        size--;
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK && size > 0 && !this.f16331h.mPrefetchRegistry.mo10412a(ymVar.f16384c)) {
                        int i = size - 1;
                        while (i >= 0) {
                            if (!this.f16331h.mPrefetchRegistry.mo10412a(((C0667ym) this.f16326c.get(i)).f16384c)) {
                                break;
                            }
                            i--;
                        }
                        size = i + 1;
                    }
                    this.f16326c.add(size, ymVar);
                    z2 = true;
                }
                if (!z2) {
                    mo10608a(ymVar, true);
                    z4 = z2;
                    this.f16331h.mViewInfoStore.mo10759d(ymVar);
                    if (!z4 && !z3 && z) {
                        ymVar.f16396o = null;
                        return;
                    }
                    return;
                }
                z4 = z2;
            }
            z3 = false;
            this.f16331h.mViewInfoStore.mo10759d(ymVar);
            if (!z4) {
            }
        } else {
            throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + this.f16331h.exceptionLabel());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo10614c(View view) {
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (!childViewHolderInt.mo10640a(12) && childViewHolderInt.mo10660t() && !this.f16331h.canReuseUpdatedViewHolder(childViewHolderInt)) {
            if (this.f16325b == null) {
                this.f16325b = new ArrayList();
            }
            childViewHolderInt.mo10638a(this, true);
            this.f16325b.add(childViewHolderInt);
        } else if (!childViewHolderInt.mo10650j() || childViewHolderInt.mo10653m() || this.f16331h.mAdapter.f16288b) {
            childViewHolderInt.mo10638a(this, false);
            this.f16324a.add(childViewHolderInt);
        } else {
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + this.f16331h.exceptionLabel());
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0209, code lost:
        if (r8.f16386e != r9.mo224b(r8.f16384c)) goto L_0x020c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x04dd, code lost:
        if ((r4 + r9) < r20) goto L_0x04e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01d0, code lost:
        if (r1.f16331h.mState.f16364g != false) goto L_0x01d2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x057f  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x058d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0090  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.C0667ym mo10604a(int r19, long r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            if (r0 < 0) goto L_0x05b1
            android.support.v7.widget.RecyclerView r2 = r1.f16331h
            yj r2 = r2.mState
            int r2 = r2.mo10626a()
            if (r0 >= r2) goto L_0x05b1
            android.support.v7.widget.RecyclerView r2 = r1.f16331h
            yj r2 = r2.mState
            boolean r2 = r2.f16364g
            r3 = 32
            r5 = 0
            r6 = 0
            if (r2 == 0) goto L_0x0093
            java.util.ArrayList r2 = r1.f16325b
            if (r2 == 0) goto L_0x008b
            int r2 = r2.size()
            if (r2 != 0) goto L_0x0027
            goto L_0x008b
        L_0x0027:
            r7 = 0
        L_0x0028:
            if (r7 >= r2) goto L_0x0047
            java.util.ArrayList r8 = r1.f16325b
            java.lang.Object r8 = r8.get(r7)
            ym r8 = (p000.C0667ym) r8
            boolean r9 = r8.mo10647g()
            if (r9 == 0) goto L_0x0039
        L_0x0038:
            goto L_0x0044
        L_0x0039:
            int r9 = r8.mo10643c()
            if (r9 != r0) goto L_0x0038
            r8.mo10641b(r3)
            goto L_0x008c
        L_0x0044:
            int r7 = r7 + 1
            goto L_0x0028
        L_0x0047:
            android.support.v7.widget.RecyclerView r7 = r1.f16331h
            xg r8 = r7.mAdapter
            boolean r8 = r8.f16288b
            if (r8 == 0) goto L_0x008b
            tb r7 = r7.mAdapterHelper
            int r7 = r7.mo10090b((int) r0)
            if (r7 <= 0) goto L_0x008b
            android.support.v7.widget.RecyclerView r8 = r1.f16331h
            xg r8 = r8.mAdapter
            int r8 = r8.mo220a()
            if (r7 >= r8) goto L_0x008b
            android.support.v7.widget.RecyclerView r8 = r1.f16331h
            xg r8 = r8.mAdapter
            long r7 = r8.mo224b(r7)
            r9 = 0
        L_0x006a:
            if (r9 >= r2) goto L_0x008a
            java.util.ArrayList r10 = r1.f16325b
            java.lang.Object r10 = r10.get(r9)
            ym r10 = (p000.C0667ym) r10
            boolean r11 = r10.mo10647g()
            if (r11 == 0) goto L_0x007b
        L_0x007a:
            goto L_0x0087
        L_0x007b:
            long r11 = r10.f16386e
            int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r13 != 0) goto L_0x007a
            r10.mo10641b(r3)
            r8 = r10
            goto L_0x008c
        L_0x0087:
            int r9 = r9 + 1
            goto L_0x006a
        L_0x008a:
        L_0x008b:
            r8 = r5
        L_0x008c:
            if (r8 == 0) goto L_0x0090
            r2 = 1
            goto L_0x0096
        L_0x0090:
            r2 = 0
            goto L_0x0096
        L_0x0093:
            r8 = r5
            r2 = 0
        L_0x0096:
            r7 = -1
            if (r8 != 0) goto L_0x0254
            java.util.ArrayList r8 = r1.f16324a
            int r9 = r8.size()
            r8 = 0
        L_0x00a0:
            if (r8 < r9) goto L_0x0191
            android.support.v7.widget.RecyclerView r8 = r1.f16331h
            uq r8 = r8.mChildHelper
            java.util.List r9 = r8.f16029c
            int r9 = r9.size()
            r10 = 0
        L_0x00ad:
            if (r10 >= r9) goto L_0x00d2
            java.util.List r11 = r8.f16029c
            java.lang.Object r11 = r11.get(r10)
            android.view.View r11 = (android.view.View) r11
            ym r12 = android.support.p002v7.widget.RecyclerView.getChildViewHolderInt(r11)
            int r13 = r12.mo10643c()
            if (r13 == r0) goto L_0x00c2
            goto L_0x00cf
        L_0x00c2:
            boolean r13 = r12.mo10650j()
            if (r13 != 0) goto L_0x00cf
            boolean r12 = r12.mo10653m()
            if (r12 != 0) goto L_0x00cf
            goto L_0x00d4
        L_0x00cf:
            int r10 = r10 + 1
            goto L_0x00ad
        L_0x00d2:
            r11 = r5
        L_0x00d4:
            if (r11 == 0) goto L_0x015f
            ym r8 = android.support.p002v7.widget.RecyclerView.getChildViewHolderInt(r11)
            android.support.v7.widget.RecyclerView r9 = r1.f16331h
            uq r9 = r9.mChildHelper
            up r10 = r9.f16027a
            int r10 = r10.mo10305a((android.view.View) r11)
            if (r10 < 0) goto L_0x0148
            uo r12 = r9.f16028b
            boolean r12 = r12.mo10300c(r10)
            if (r12 == 0) goto L_0x0131
            uo r12 = r9.f16028b
            r12.mo10299b(r10)
            r9.mo10320d((android.view.View) r11)
            android.support.v7.widget.RecyclerView r9 = r1.f16331h
            uq r9 = r9.mChildHelper
            int r9 = r9.mo10315b((android.view.View) r11)
            if (r9 == r7) goto L_0x0111
            android.support.v7.widget.RecyclerView r10 = r1.f16331h
            uq r10 = r10.mChildHelper
            r10.mo10319d((int) r9)
            r1.mo10614c(r11)
            r9 = 8224(0x2020, float:1.1524E-41)
            r8.mo10641b(r9)
            goto L_0x01c0
        L_0x0111:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "layout index should not be -1 after unhiding a view:"
            r2.append(r3)
            r2.append(r8)
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            java.lang.String r3 = r3.exceptionLabel()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0131:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "trying to unhide a view that was not hidden"
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0148:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "view is not a child, cannot hide "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x015f:
            java.util.ArrayList r8 = r1.f16326c
            int r8 = r8.size()
            r9 = 0
        L_0x0166:
            if (r9 >= r8) goto L_0x018e
            java.util.ArrayList r10 = r1.f16326c
            java.lang.Object r10 = r10.get(r9)
            ym r10 = (p000.C0667ym) r10
            boolean r11 = r10.mo10650j()
            if (r11 == 0) goto L_0x0177
        L_0x0176:
            goto L_0x018b
        L_0x0177:
            int r11 = r10.mo10643c()
            if (r11 != r0) goto L_0x0176
            boolean r11 = r10.mo10655o()
            if (r11 != 0) goto L_0x018b
            java.util.ArrayList r8 = r1.f16326c
            r8.remove(r9)
            r8 = r10
            goto L_0x01c0
        L_0x018b:
            int r9 = r9 + 1
            goto L_0x0166
        L_0x018e:
            r8 = r5
            goto L_0x01c0
        L_0x0191:
            java.util.ArrayList r10 = r1.f16324a
            java.lang.Object r10 = r10.get(r8)
            ym r10 = (p000.C0667ym) r10
            boolean r11 = r10.mo10647g()
            if (r11 == 0) goto L_0x01a1
        L_0x019f:
            goto L_0x0250
        L_0x01a1:
            int r11 = r10.mo10643c()
            if (r11 != r0) goto L_0x019f
            boolean r11 = r10.mo10650j()
            if (r11 != 0) goto L_0x0250
            android.support.v7.widget.RecyclerView r11 = r1.f16331h
            yj r11 = r11.mState
            boolean r11 = r11.f16364g
            if (r11 != 0) goto L_0x01bb
            boolean r11 = r10.mo10653m()
            if (r11 != 0) goto L_0x0250
        L_0x01bb:
            r10.mo10641b(r3)
            r8 = r10
        L_0x01c0:
            if (r8 != 0) goto L_0x01c4
            goto L_0x0254
        L_0x01c4:
            boolean r9 = r8.mo10653m()
            if (r9 == 0) goto L_0x01d5
            android.support.v7.widget.RecyclerView r9 = r1.f16331h
            yj r9 = r9.mState
            boolean r9 = r9.f16364g
            if (r9 == 0) goto L_0x020c
        L_0x01d2:
            r2 = 1
            goto L_0x0255
        L_0x01d5:
            int r9 = r8.f16384c
            if (r9 < 0) goto L_0x0230
            android.support.v7.widget.RecyclerView r10 = r1.f16331h
            xg r10 = r10.mAdapter
            int r10 = r10.mo220a()
            if (r9 >= r10) goto L_0x0230
            android.support.v7.widget.RecyclerView r9 = r1.f16331h
            yj r10 = r9.mState
            boolean r10 = r10.f16364g
            if (r10 != 0) goto L_0x01f7
            xg r9 = r9.mAdapter
            int r10 = r8.f16384c
            int r9 = r9.mo221a((int) r10)
            int r10 = r8.f16387f
            if (r9 != r10) goto L_0x020c
        L_0x01f7:
            android.support.v7.widget.RecyclerView r9 = r1.f16331h
            xg r9 = r9.mAdapter
            boolean r10 = r9.f16288b
            if (r10 == 0) goto L_0x01d2
            long r10 = r8.f16386e
            int r12 = r8.f16384c
            long r12 = r9.mo224b(r12)
            int r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r9 != 0) goto L_0x020c
            goto L_0x01d2
        L_0x020c:
            r9 = 4
            r8.mo10641b(r9)
            boolean r9 = r8.mo10645e()
            if (r9 == 0) goto L_0x0221
            android.support.v7.widget.RecyclerView r9 = r1.f16331h
            android.view.View r10 = r8.f16382a
            r9.removeDetachedView(r10, r6)
            r8.mo10646f()
            goto L_0x022a
        L_0x0221:
            boolean r9 = r8.mo10647g()
            if (r9 == 0) goto L_0x022a
            r8.mo10648h()
        L_0x022a:
            r1.mo10607a((p000.C0667ym) r8)
            r8 = r5
            goto L_0x0255
        L_0x0230:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Inconsistency detected. Invalid view holder adapter position"
            r2.append(r3)
            r2.append(r8)
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            java.lang.String r3 = r3.exceptionLabel()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0250:
            int r8 = r8 + 1
            goto L_0x00a0
        L_0x0254:
        L_0x0255:
            r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r8 != 0) goto L_0x045d
            android.support.v7.widget.RecyclerView r13 = r1.f16331h
            tb r13 = r13.mAdapterHelper
            int r13 = r13.mo10090b((int) r0)
            if (r13 < 0) goto L_0x0425
            android.support.v7.widget.RecyclerView r14 = r1.f16331h
            xg r14 = r14.mAdapter
            int r14 = r14.mo220a()
            if (r13 >= r14) goto L_0x0425
            android.support.v7.widget.RecyclerView r14 = r1.f16331h
            xg r14 = r14.mAdapter
            int r14 = r14.mo221a((int) r13)
            android.support.v7.widget.RecyclerView r15 = r1.f16331h
            xg r15 = r15.mAdapter
            boolean r4 = r15.f16288b
            if (r4 == 0) goto L_0x030f
            long r16 = r15.mo224b(r13)
            java.util.ArrayList r4 = r1.f16324a
            int r4 = r4.size()
            int r4 = r4 + r7
        L_0x028b:
            if (r4 < 0) goto L_0x02d4
            java.util.ArrayList r8 = r1.f16324a
            java.lang.Object r8 = r8.get(r4)
            ym r8 = (p000.C0667ym) r8
            long r9 = r8.f16386e
            int r15 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r15 == 0) goto L_0x029c
            goto L_0x02d1
        L_0x029c:
            boolean r9 = r8.mo10647g()
            if (r9 != 0) goto L_0x02d1
            int r9 = r8.f16387f
            if (r14 == r9) goto L_0x02b8
            java.util.ArrayList r9 = r1.f16324a
            r9.remove(r4)
            android.support.v7.widget.RecyclerView r9 = r1.f16331h
            android.view.View r10 = r8.f16382a
            r9.removeDetachedView(r10, r6)
            android.view.View r8 = r8.f16382a
            r1.mo10611b((android.view.View) r8)
            goto L_0x02d1
        L_0x02b8:
            r8.mo10641b(r3)
            boolean r3 = r8.mo10653m()
            if (r3 == 0) goto L_0x02d0
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            yj r3 = r3.mState
            boolean r3 = r3.f16364g
            if (r3 != 0) goto L_0x02d0
            r3 = 2
            r4 = 14
            r8.mo10635a((int) r3, (int) r4)
        L_0x02d0:
            goto L_0x0309
        L_0x02d1:
            int r4 = r4 + -1
            goto L_0x028b
        L_0x02d4:
            java.util.ArrayList r3 = r1.f16326c
            int r3 = r3.size()
            int r3 = r3 + r7
        L_0x02db:
            if (r3 < 0) goto L_0x0307
            java.util.ArrayList r4 = r1.f16326c
            java.lang.Object r4 = r4.get(r3)
            ym r4 = (p000.C0667ym) r4
            long r8 = r4.f16386e
            int r10 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r10 == 0) goto L_0x02ec
            goto L_0x0304
        L_0x02ec:
            boolean r8 = r4.mo10655o()
            if (r8 != 0) goto L_0x0304
            int r8 = r4.f16387f
            if (r14 != r8) goto L_0x02fe
            java.util.ArrayList r8 = r1.f16326c
            r8.remove(r3)
            r8 = r4
            goto L_0x0309
        L_0x02fe:
            r1.mo10610b((int) r3)
            r8 = r5
            goto L_0x0309
        L_0x0304:
            int r3 = r3 + -1
            goto L_0x02db
        L_0x0307:
            r8 = r5
        L_0x0309:
            if (r8 == 0) goto L_0x030f
            r8.f16384c = r13
            r2 = 1
            goto L_0x0310
        L_0x030f:
        L_0x0310:
            if (r8 != 0) goto L_0x032a
            yk r3 = r1.f16330g
            if (r3 == 0) goto L_0x032a
            android.view.View r3 = r3.mo10629a()
            if (r3 == 0) goto L_0x032a
            android.support.v7.widget.RecyclerView r4 = r1.f16331h
            ym r8 = r4.getChildViewHolder(r3)
            if (r8 == 0) goto L_0x0348
            boolean r3 = r8.mo10642b()
            if (r3 != 0) goto L_0x032b
        L_0x032a:
            goto L_0x0365
        L_0x032b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view."
            r2.append(r3)
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            java.lang.String r3 = r3.exceptionLabel()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0348:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getViewForPositionAndType returned a view which does not have a ViewHolder"
            r2.append(r3)
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            java.lang.String r3 = r3.exceptionLabel()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0365:
            if (r8 != 0) goto L_0x03b3
            ya r3 = r18.mo10615d()
            android.util.SparseArray r3 = r3.f16322a
            java.lang.Object r3 = r3.get(r14)
            xz r3 = (p000.C0653xz) r3
            if (r3 == 0) goto L_0x039d
            java.util.ArrayList r4 = r3.f16319a
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x039d
            java.util.ArrayList r3 = r3.f16319a
            int r4 = r3.size()
            int r4 = r4 + r7
        L_0x0384:
            if (r4 < 0) goto L_0x039c
            java.lang.Object r7 = r3.get(r4)
            ym r7 = (p000.C0667ym) r7
            boolean r7 = r7.mo10655o()
            if (r7 != 0) goto L_0x0399
            java.lang.Object r3 = r3.remove(r4)
            ym r3 = (p000.C0667ym) r3
            goto L_0x039e
        L_0x0399:
            int r4 = r4 + -1
            goto L_0x0384
        L_0x039c:
        L_0x039d:
            r3 = r5
        L_0x039e:
            if (r3 == 0) goto L_0x03b2
            r3.mo10658r()
            boolean r4 = android.support.p002v7.widget.RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST
            if (r4 == 0) goto L_0x03b2
            android.view.View r4 = r3.f16382a
            boolean r7 = r4 instanceof android.view.ViewGroup
            if (r7 == 0) goto L_0x03b2
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            r1.m16076a((android.view.ViewGroup) r4, (boolean) r6)
        L_0x03b2:
            r8 = r3
        L_0x03b3:
            if (r8 != 0) goto L_0x045d
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            long r3 = r3.getNanoTime()
            int r7 = (r20 > r11 ? 1 : (r20 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x03d4
            ya r7 = r1.f16329f
            xz r7 = r7.mo10600a(r14)
            long r7 = r7.f16320b
            r9 = 0
            int r13 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r13 != 0) goto L_0x03ce
            goto L_0x03d4
        L_0x03ce:
            long r7 = r7 + r3
            int r9 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r9 < 0) goto L_0x03d4
            return r5
        L_0x03d4:
            android.support.v7.widget.RecyclerView r5 = r1.f16331h
            xg r7 = r5.mAdapter
            java.lang.String r8 = "RV CreateView"
            p000.C0264jo.m14493a(r8)     // Catch:{ all -> 0x0420 }
            ym r8 = r7.mo222a((android.view.ViewGroup) r5, (int) r14)     // Catch:{ all -> 0x0420 }
            android.view.View r5 = r8.f16382a     // Catch:{ all -> 0x0420 }
            android.view.ViewParent r5 = r5.getParent()     // Catch:{ all -> 0x0420 }
            if (r5 != 0) goto L_0x0418
            r8.f16387f = r14     // Catch:{ all -> 0x0420 }
            p000.C0264jo.m14492a()
            boolean r5 = android.support.p002v7.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
            if (r5 != 0) goto L_0x03f3
            goto L_0x0402
        L_0x03f3:
            android.view.View r5 = r8.f16382a
            android.support.v7.widget.RecyclerView r5 = android.support.p002v7.widget.RecyclerView.findNestedRecyclerView(r5)
            if (r5 == 0) goto L_0x0402
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference
            r7.<init>(r5)
            r8.f16383b = r7
        L_0x0402:
            android.support.v7.widget.RecyclerView r5 = r1.f16331h
            long r9 = r5.getNanoTime()
            ya r5 = r1.f16329f
            xz r5 = r5.mo10600a(r14)
            long r13 = r5.f16320b
            long r9 = r9 - r3
            long r3 = p000.C0655ya.m16072a(r13, r9)
            r5.f16320b = r3
            goto L_0x045e
        L_0x0418:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0420 }
            java.lang.String r2 = "ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)"
            r0.<init>(r2)     // Catch:{ all -> 0x0420 }
            throw r0     // Catch:{ all -> 0x0420 }
        L_0x0420:
            r0 = move-exception
            p000.C0264jo.m14492a()
            throw r0
        L_0x0425:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Inconsistency detected. Invalid item position "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = "(offset:"
            r3.append(r0)
            r3.append(r13)
            java.lang.String r0 = ").state:"
            r3.append(r0)
            android.support.v7.widget.RecyclerView r0 = r1.f16331h
            yj r0 = r0.mState
            int r0 = r0.mo10626a()
            r3.append(r0)
            android.support.v7.widget.RecyclerView r0 = r1.f16331h
            java.lang.String r0 = r0.exceptionLabel()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x045d:
        L_0x045e:
            if (r2 == 0) goto L_0x048c
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            yj r3 = r3.mState
            boolean r3 = r3.f16364g
            if (r3 == 0) goto L_0x0469
            goto L_0x048c
        L_0x0469:
            r3 = 8192(0x2000, float:1.14794E-41)
            boolean r4 = r8.mo10640a((int) r3)
            if (r4 == 0) goto L_0x048c
            r8.mo10635a((int) r6, (int) r3)
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            yj r3 = r3.mState
            boolean r3 = r3.f16367j
            if (r3 == 0) goto L_0x048c
            p000.C0641xn.m15920f(r8)
            r8.mo10657q()
            xm r3 = p000.C0641xn.m15922g(r8)
            android.support.v7.widget.RecyclerView r4 = r1.f16331h
            r4.recordAnimationInfoIfBouncedHiddenView(r8, r3)
        L_0x048c:
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            yj r3 = r3.mState
            boolean r3 = r3.f16364g
            if (r3 == 0) goto L_0x04a1
            boolean r3 = r8.mo10652l()
            if (r3 != 0) goto L_0x049b
            goto L_0x04a1
        L_0x049b:
            r8.f16388g = r0
        L_0x049d:
            r0 = 0
            r7 = 1
            goto L_0x0576
        L_0x04a1:
            boolean r3 = r8.mo10652l()
            if (r3 != 0) goto L_0x04a8
        L_0x04a7:
            goto L_0x04b5
        L_0x04a8:
            boolean r3 = r8.mo10651k()
            if (r3 != 0) goto L_0x04a7
            boolean r3 = r8.mo10650j()
            if (r3 != 0) goto L_0x04a7
        L_0x04b4:
            goto L_0x049d
        L_0x04b5:
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            tb r3 = r3.mAdapterHelper
            int r3 = r3.mo10090b((int) r0)
            android.support.v7.widget.RecyclerView r4 = r1.f16331h
            r8.f16396o = r4
            int r5 = r8.f16387f
            long r9 = r4.getNanoTime()
            int r4 = (r20 > r11 ? 1 : (r20 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x04e0
            ya r4 = r1.f16329f
            xz r4 = r4.mo10600a(r5)
            long r4 = r4.f16321c
            r11 = 0
            int r7 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x04da
            goto L_0x04e0
        L_0x04da:
            long r4 = r4 + r9
            int r7 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r7 < 0) goto L_0x04e0
            goto L_0x04b4
        L_0x04e0:
            android.support.v7.widget.RecyclerView r4 = r1.f16331h
            xg r4 = r4.mAdapter
            r8.f16384c = r3
            boolean r5 = r4.f16288b
            if (r5 == 0) goto L_0x04f0
            long r11 = r4.mo224b(r3)
            r8.f16386e = r11
        L_0x04f0:
            r5 = 519(0x207, float:7.27E-43)
            r7 = 1
            r8.mo10635a((int) r7, (int) r5)
            java.lang.String r5 = "RV OnBindView"
            p000.C0264jo.m14493a(r5)
            java.util.List r5 = r8.mo10657q()
            r4.mo4521a(r8, r3, r5)
            r8.mo10656p()
            android.view.View r3 = r8.f16382a
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            boolean r4 = r3 instanceof p000.C0648xu
            if (r4 == 0) goto L_0x0514
            xu r3 = (p000.C0648xu) r3
            r4 = 1
            r3.f16314e = r4
        L_0x0514:
            p000.C0264jo.m14492a()
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            long r3 = r3.getNanoTime()
            ya r5 = r1.f16329f
            int r7 = r8.f16387f
            xz r5 = r5.mo10600a(r7)
            long r11 = r5.f16321c
            long r3 = r3 - r9
            long r3 = p000.C0655ya.m16072a(r11, r3)
            r5.f16321c = r3
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            boolean r3 = r3.isAccessibilityEnabled()
            if (r3 != 0) goto L_0x0538
            r7 = 1
            goto L_0x0569
        L_0x0538:
            android.view.View r3 = r8.f16382a
            int r4 = p000.C0340mj.m14712e(r3)
            if (r4 == 0) goto L_0x0542
            r7 = 1
            goto L_0x0547
        L_0x0542:
            r7 = 1
            p000.C0340mj.m14689a((android.view.View) r3, (int) r7)
        L_0x0547:
            android.support.v7.widget.RecyclerView r4 = r1.f16331h
            yo r4 = r4.mAccessibilityDelegate
            if (r4 == 0) goto L_0x0569
            ll r4 = r4.mo234b()
            boolean r5 = r4 instanceof p000.C0668yn
            if (r5 == 0) goto L_0x0566
            r5 = r4
            yn r5 = (p000.C0668yn) r5
            ll r9 = p000.C0340mj.m14705b(r3)
            if (r9 != 0) goto L_0x055f
            goto L_0x0566
        L_0x055f:
            if (r9 == r5) goto L_0x0566
            java.util.Map r5 = r5.f16400b
            r5.put(r3, r9)
        L_0x0566:
            p000.C0340mj.m14698a((android.view.View) r3, (p000.C0315ll) r4)
        L_0x0569:
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            yj r3 = r3.mState
            boolean r3 = r3.f16364g
            if (r3 == 0) goto L_0x0574
            r8.f16388g = r0
            goto L_0x0575
        L_0x0574:
        L_0x0575:
            r0 = 1
        L_0x0576:
            android.view.View r3 = r8.f16382a
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            if (r3 != 0) goto L_0x058d
            android.support.v7.widget.RecyclerView r3 = r1.f16331h
            android.view.ViewGroup$LayoutParams r3 = r3.generateDefaultLayoutParams()
            xu r3 = (p000.C0648xu) r3
            android.view.View r4 = r8.f16382a
            r4.setLayoutParams(r3)
            goto L_0x05a5
        L_0x058d:
            android.support.v7.widget.RecyclerView r4 = r1.f16331h
            boolean r4 = r4.checkLayoutParams(r3)
            if (r4 != 0) goto L_0x05a3
            android.support.v7.widget.RecyclerView r4 = r1.f16331h
            android.view.ViewGroup$LayoutParams r3 = r4.generateLayoutParams((android.view.ViewGroup.LayoutParams) r3)
            xu r3 = (p000.C0648xu) r3
            android.view.View r4 = r8.f16382a
            r4.setLayoutParams(r3)
            goto L_0x05a5
        L_0x05a3:
            xu r3 = (p000.C0648xu) r3
        L_0x05a5:
            r3.f16312c = r8
            if (r2 != 0) goto L_0x05ab
        L_0x05a9:
            r4 = 0
            goto L_0x05ae
        L_0x05ab:
            if (r0 == 0) goto L_0x05a9
            r4 = 1
        L_0x05ae:
            r3.f16315f = r4
            return r8
        L_0x05b1:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid item position "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r4 = "("
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = "). Item count:"
            r3.append(r0)
            android.support.v7.widget.RecyclerView r0 = r1.f16331h
            yj r0 = r0.mState
            int r0 = r0.mo10626a()
            r3.append(r0)
            android.support.v7.widget.RecyclerView r0 = r1.f16331h
            java.lang.String r0 = r0.exceptionLabel()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            goto L_0x05ea
        L_0x05e9:
            throw r2
        L_0x05ea:
            goto L_0x05e9
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0656yb.mo10604a(int, long):ym");
    }

    /* renamed from: b */
    public final void mo10612b(C0667ym ymVar) {
        if (ymVar.f16393l) {
            this.f16325b.remove(ymVar);
        } else {
            this.f16324a.remove(ymVar);
        }
        ymVar.f16392k = null;
        ymVar.f16393l = false;
        ymVar.mo10648h();
    }

    /* renamed from: b */
    public final void mo10609b() {
        C0647xt xtVar = this.f16331h.mLayout;
        this.f16332i = this.f16328e + (xtVar != null ? xtVar.f16306q : 0);
        for (int size = this.f16326c.size() - 1; size >= 0 && this.f16326c.size() > this.f16332i; size--) {
            mo10610b(size);
        }
    }
}
