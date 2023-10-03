package p000;

import android.os.Build;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;

/* renamed from: yo */
/* compiled from: PG */
public class C0669yo extends C0315ll {

    /* renamed from: b */
    public final RecyclerView f16402b;

    /* renamed from: c */
    public final C0668yn f16403c;

    public C0669yo(RecyclerView recyclerView) {
        this.f16402b = recyclerView;
        C0315ll b = mo234b();
        if (b == null || !(b instanceof C0668yn)) {
            this.f16403c = new C0668yn(this);
        } else {
            this.f16403c = (C0668yn) b;
        }
    }

    /* renamed from: b */
    public C0315ll mo234b() {
        return this.f16403c;
    }

    /* renamed from: d */
    public final void mo6587d(View view, AccessibilityEvent accessibilityEvent) {
        super.mo6587d(view, accessibilityEvent);
        if ((view instanceof RecyclerView) && !mo10662a()) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().mo10464a(accessibilityEvent);
            }
        }
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        super.mo232a(view, mxVar);
        if (!mo10662a() && this.f16402b.getLayoutManager() != null) {
            C0647xt layoutManager = this.f16402b.getLayoutManager();
            RecyclerView recyclerView = layoutManager.f16299j;
            C0656yb ybVar = recyclerView.mRecycler;
            C0664yj yjVar = recyclerView.mState;
            if (recyclerView.canScrollVertically(-1) || layoutManager.f16299j.canScrollHorizontally(-1)) {
                mxVar.mo9420a(8192);
                mxVar.mo9445j();
            }
            if (layoutManager.f16299j.canScrollVertically(1) || layoutManager.f16299j.canScrollHorizontally(1)) {
                mxVar.mo9420a(4096);
                mxVar.mo9445j();
            }
            int a = layoutManager.mo10422a(ybVar, yjVar);
            int b = layoutManager.mo10434b(ybVar, yjVar);
            int i = Build.VERSION.SDK_INT;
            mxVar.mo9424a((Object) new C0352mv(AccessibilityNodeInfo.CollectionInfo.obtain(a, b, false, 0)));
        }
    }

    /* renamed from: a */
    public final boolean mo233a(View view, int i, Bundle bundle) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (super.mo233a(view, i, bundle)) {
            return true;
        }
        if (mo10662a() || this.f16402b.getLayoutManager() == null) {
            return false;
        }
        C0647xt layoutManager = this.f16402b.getLayoutManager();
        RecyclerView recyclerView = layoutManager.f16299j;
        C0656yb ybVar = recyclerView.mRecycler;
        C0664yj yjVar = recyclerView.mState;
        if (recyclerView != null) {
            if (i != 4096) {
                if (i != 8192) {
                    i3 = 0;
                    i2 = 0;
                } else {
                    if (recyclerView.canScrollVertically(-1)) {
                        i4 = -((layoutManager.f16311v - layoutManager.mo10587t()) - layoutManager.mo10589v());
                    } else {
                        i4 = 0;
                    }
                    if (layoutManager.f16299j.canScrollHorizontally(-1)) {
                        i3 = -((layoutManager.f16310u - layoutManager.mo10586s()) - layoutManager.mo10588u());
                        i2 = i4;
                    }
                }
                if (!(i2 == 0 && i3 == 0)) {
                    layoutManager.f16299j.smoothScrollBy(i3, i2, (Interpolator) null, RecyclerView.UNDEFINED_DURATION, true);
                    return true;
                }
            } else {
                if (recyclerView.canScrollVertically(1)) {
                    i5 = (layoutManager.f16311v - layoutManager.mo10587t()) - layoutManager.mo10589v();
                } else {
                    i5 = 0;
                }
                if (layoutManager.f16299j.canScrollHorizontally(1)) {
                    i3 = (layoutManager.f16310u - layoutManager.mo10586s()) - layoutManager.mo10588u();
                    i2 = i4;
                    layoutManager.f16299j.smoothScrollBy(i3, i2, (Interpolator) null, RecyclerView.UNDEFINED_DURATION, true);
                    return true;
                }
            }
            i2 = i4;
            i3 = 0;
            layoutManager.f16299j.smoothScrollBy(i3, i2, (Interpolator) null, RecyclerView.UNDEFINED_DURATION, true);
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo10662a() {
        return this.f16402b.hasPendingAdapterUpdates();
    }
}
