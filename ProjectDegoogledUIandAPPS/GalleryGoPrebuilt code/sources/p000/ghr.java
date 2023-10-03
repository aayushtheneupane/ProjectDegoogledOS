package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Handler;

/* renamed from: ghr */
/* compiled from: PG */
final class ghr extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ gik f11391a;

    public ghr(gik gik) {
        this.f11391a = gik;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f11391a.mo6718f();
    }

    public final void onAnimationStart(Animator animator) {
        gik gik = this.f11391a;
        Handler handler = gik.f11413a;
        gik.f11419f.mo3673a();
    }
}
