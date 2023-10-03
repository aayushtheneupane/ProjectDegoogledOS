package com.google.android.material.snackbar;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* compiled from: PG */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior {

    /* renamed from: g */
    public final gif f5226g = new gif(this);

    /* renamed from: e */
    public final boolean mo3609e(View view) {
        return view instanceof gij;
    }

    /* renamed from: a */
    public final boolean mo91a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        gif gif = this.f5226g;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                gir.m10379a().mo6727b(gif.f11404a);
            }
        } else if (coordinatorLayout.mo1122a(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            gir.m10379a().mo6723a(gif.f11404a);
        }
        return super.mo91a(coordinatorLayout, view, motionEvent);
    }
}
