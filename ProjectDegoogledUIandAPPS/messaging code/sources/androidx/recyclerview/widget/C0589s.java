package androidx.recyclerview.widget;

import android.animation.ValueAnimator;

/* renamed from: androidx.recyclerview.widget.s */
class C0589s implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ FastScroller this$0;

    C0589s(FastScroller fastScroller) {
        this.this$0 = fastScroller;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        this.this$0.f536vr.setAlpha(floatValue);
        this.this$0.f537wr.setAlpha(floatValue);
        this.this$0.mo4662Nc();
    }
}
