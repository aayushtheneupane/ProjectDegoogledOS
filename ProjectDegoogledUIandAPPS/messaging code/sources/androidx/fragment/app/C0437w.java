package androidx.fragment.app;

import android.view.ViewGroup;
import android.view.animation.Animation;

/* renamed from: androidx.fragment.app.w */
class C0437w implements Animation.AnimationListener {

    /* renamed from: j */
    final /* synthetic */ ViewGroup f419j;

    /* renamed from: m */
    final /* synthetic */ C0424j f420m;
    final /* synthetic */ C0389H this$0;

    C0437w(C0389H h, ViewGroup viewGroup, C0424j jVar) {
        this.this$0 = h;
        this.f419j = viewGroup;
        this.f420m = jVar;
    }

    public void onAnimationEnd(Animation animation) {
        this.f419j.post(new C0436v(this));
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
