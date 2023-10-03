package p000;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: yn */
/* compiled from: PG */
public final class C0668yn extends C0315ll {

    /* renamed from: b */
    public final Map f16400b = new WeakHashMap();

    /* renamed from: c */
    private final C0669yo f16401c;

    public C0668yn(C0669yo yoVar) {
        this.f16401c = yoVar;
    }

    /* renamed from: b */
    public final boolean mo9361b(View view, AccessibilityEvent accessibilityEvent) {
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            return llVar.mo9361b(view, accessibilityEvent);
        }
        return super.mo9361b(view, accessibilityEvent);
    }

    /* renamed from: a */
    public final C0358na mo9357a(View view) {
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            return llVar.mo9357a(view);
        }
        return super.mo9357a(view);
    }

    /* renamed from: d */
    public final void mo6587d(View view, AccessibilityEvent accessibilityEvent) {
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            llVar.mo6587d(view, accessibilityEvent);
        } else {
            super.mo6587d(view, accessibilityEvent);
        }
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        if (this.f16401c.mo10662a() || this.f16401c.f16402b.getLayoutManager() == null) {
            super.mo232a(view, mxVar);
            return;
        }
        this.f16401c.f16402b.getLayoutManager().mo10570a(view, mxVar);
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            llVar.mo232a(view, mxVar);
        } else {
            super.mo232a(view, mxVar);
        }
    }

    /* renamed from: c */
    public final void mo6751c(View view, AccessibilityEvent accessibilityEvent) {
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            llVar.mo6751c(view, accessibilityEvent);
        } else {
            super.mo6751c(view, accessibilityEvent);
        }
    }

    /* renamed from: a */
    public final boolean mo9360a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        C0315ll llVar = (C0315ll) this.f16400b.get(viewGroup);
        if (llVar != null) {
            return llVar.mo9360a(viewGroup, view, accessibilityEvent);
        }
        return super.mo9360a(viewGroup, view, accessibilityEvent);
    }

    /* renamed from: a */
    public final boolean mo233a(View view, int i, Bundle bundle) {
        if (this.f16401c.mo10662a() || this.f16401c.f16402b.getLayoutManager() == null) {
            return super.mo233a(view, i, bundle);
        }
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            if (llVar.mo233a(view, i, bundle)) {
                return true;
            }
        } else if (super.mo233a(view, i, bundle)) {
            return true;
        }
        RecyclerView recyclerView = this.f16401c.f16402b.getLayoutManager().f16299j;
        C0656yb ybVar = recyclerView.mRecycler;
        C0664yj yjVar = recyclerView.mState;
        return false;
    }

    /* renamed from: a */
    public final void mo9358a(View view, int i) {
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            llVar.mo9358a(view, i);
        } else {
            super.mo9358a(view, i);
        }
    }

    /* renamed from: a */
    public final void mo9359a(View view, AccessibilityEvent accessibilityEvent) {
        C0315ll llVar = (C0315ll) this.f16400b.get(view);
        if (llVar != null) {
            llVar.mo9359a(view, accessibilityEvent);
        } else {
            super.mo9359a(view, accessibilityEvent);
        }
    }
}
