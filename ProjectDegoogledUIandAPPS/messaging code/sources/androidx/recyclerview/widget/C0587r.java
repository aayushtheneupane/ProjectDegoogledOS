package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: androidx.recyclerview.widget.r */
class C0587r extends AnimatorListenerAdapter {
    private boolean mCanceled = false;
    final /* synthetic */ FastScroller this$0;

    C0587r(FastScroller fastScroller) {
        this.this$0 = fastScroller;
    }

    public void onAnimationCancel(Animator animator) {
        this.mCanceled = true;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.mCanceled) {
            this.mCanceled = false;
        } else if (((Float) this.this$0.f531Kr.getAnimatedValue()).floatValue() == 0.0f) {
            FastScroller fastScroller = this.this$0;
            fastScroller.f532Lr = 0;
            fastScroller.setState(0);
        } else {
            FastScroller fastScroller2 = this.this$0;
            fastScroller2.f532Lr = 2;
            fastScroller2.mo4662Nc();
        }
    }
}
