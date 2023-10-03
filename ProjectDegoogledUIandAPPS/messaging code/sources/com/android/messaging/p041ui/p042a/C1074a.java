package com.android.messaging.p041ui.p042a;

import android.view.animation.Animation;

/* renamed from: com.android.messaging.ui.a.a */
class C1074a implements Animation.AnimationListener {
    final /* synthetic */ C1079f this$0;

    C1074a(C1079f fVar) {
        this.this$0 = fVar;
    }

    public void onAnimationEnd(Animation animation) {
        if (this.this$0.f1707jl != null) {
            this.this$0.f1707jl.run();
        }
        this.this$0.dismiss();
        this.this$0.mEvents.append("oAE,");
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        if (this.this$0.f1706il != null) {
            this.this$0.f1706il.run();
        }
        this.this$0.mEvents.append("oAS,");
    }
}
