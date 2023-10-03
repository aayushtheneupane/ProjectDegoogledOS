package com.android.messaging.p041ui.mediapicker;

import android.os.SystemClock;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.mediapicker.y */
class C1362y implements View.OnClickListener {

    /* renamed from: kI */
    final /* synthetic */ View f2180kI;
    final /* synthetic */ C1275C this$0;

    C1362y(C1275C c, View view) {
        this.this$0 = c;
        this.f2180kI = view;
    }

    public void onClick(View view) {
        float min = Math.min(((float) this.this$0.f2118Dj.mo7890Ia().getHeight()) / ((float) this.this$0.f2001rF.getView().getHeight()), 1.0f);
        if (C1352t.get().mo7948qj()) {
            C1352t.get().mo7951tj();
            return;
        }
        C1360x xVar = new C1360x(this);
        if (C1352t.get().mo7949rj()) {
            C1352t.get().mo7936a(xVar);
            this.this$0.f2007xF.setBase(SystemClock.elapsedRealtime());
            this.this$0.f2007xF.start();
            this.this$0.m3213po();
            return;
        }
        C1275C c = this.this$0;
        View view2 = this.f2180kI;
        float fraction = c.getContext().getResources().getFraction(R.fraction.camera_shutter_max_alpha, 1, 1);
        AnimationSet animationSet = new AnimationSet(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, fraction);
        long integer = (long) (c.getContext().getResources().getInteger(R.integer.camera_shutter_duration) / 2);
        alphaAnimation.setDuration(integer);
        animationSet.addAnimation(alphaAnimation);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(fraction, 0.0f);
        alphaAnimation2.setStartOffset(integer);
        alphaAnimation2.setDuration(integer);
        animationSet.addAnimation(alphaAnimation2);
        animationSet.setAnimationListener(new C1274B(c, view2));
        view2.startAnimation(animationSet);
        C1352t.get().mo7933a(min, xVar);
        this.this$0.m3213po();
    }
}
