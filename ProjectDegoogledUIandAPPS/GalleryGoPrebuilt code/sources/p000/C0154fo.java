package p000;

import android.animation.Animator;
import android.view.animation.Animation;

/* renamed from: fo */
/* compiled from: PG */
public final class C0154fo {

    /* renamed from: a */
    public final Animation f10127a;

    /* renamed from: b */
    public final Animator f10128b;

    public C0154fo(Animator animator) {
        this.f10127a = null;
        this.f10128b = animator;
    }

    public C0154fo(Animation animation) {
        this.f10127a = animation;
        this.f10128b = null;
        if (animation == null) {
            throw new IllegalStateException("Animation cannot be null");
        }
    }
}
