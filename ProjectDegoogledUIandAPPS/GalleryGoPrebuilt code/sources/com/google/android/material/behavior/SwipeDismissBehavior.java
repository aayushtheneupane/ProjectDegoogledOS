package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* compiled from: PG */
public class SwipeDismissBehavior extends abj {

    /* renamed from: a */
    public C0380nw f5148a;

    /* renamed from: b */
    public gcz f5149b;

    /* renamed from: c */
    public int f5150c = 2;

    /* renamed from: d */
    public float f5151d = 0.5f;

    /* renamed from: e */
    public float f5152e = 0.0f;

    /* renamed from: f */
    public float f5153f = 0.5f;

    /* renamed from: g */
    private boolean f5154g;

    /* renamed from: h */
    private final C0379nv f5155h = new gcx(this);

    /* renamed from: e */
    public boolean mo3609e(View view) {
        return true;
    }

    /* renamed from: a */
    public static float m5035a(float f) {
        return Math.min(Math.max(0.0f, f), 1.0f);
    }

    /* renamed from: a */
    public boolean mo91a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z = this.f5154g;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.mo1122a(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f5154g = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f5154g = false;
        }
        if (!z) {
            return false;
        }
        if (this.f5148a == null) {
            this.f5148a = C0380nw.m14844a((ViewGroup) coordinatorLayout, this.f5155h);
        }
        return this.f5148a.mo9482a(motionEvent);
    }

    /* renamed from: a */
    public final boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        if (C0340mj.m14712e(view) != 0) {
            return false;
        }
        C0340mj.m14689a(view, 1);
        C0340mj.m14706b(view, 1048576);
        if (!mo3609e(view)) {
            return false;
        }
        C0340mj.m14700a(view, C0351mu.f15248e, (C0366ni) new gcy(this));
        return false;
    }

    /* renamed from: b */
    public final boolean mo95b(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        C0380nw nwVar = this.f5148a;
        if (nwVar == null) {
            return false;
        }
        nwVar.mo9483b(motionEvent);
        return true;
    }
}
