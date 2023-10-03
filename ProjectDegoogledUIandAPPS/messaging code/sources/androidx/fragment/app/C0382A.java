package androidx.fragment.app;

import android.animation.Animator;
import android.view.animation.Animation;

/* renamed from: androidx.fragment.app.A */
class C0382A {
    public final Animation animation;
    public final Animator animator;

    C0382A(Animation animation2) {
        this.animation = animation2;
        this.animator = null;
        if (animation2 == null) {
            throw new IllegalStateException("Animation cannot be null");
        }
    }

    C0382A(Animator animator2) {
        this.animation = null;
        this.animator = animator2;
        if (animator2 == null) {
            throw new IllegalStateException("Animator cannot be null");
        }
    }
}
