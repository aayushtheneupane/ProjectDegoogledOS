package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import androidx.core.view.OneShotPreDrawListener;

/* renamed from: androidx.fragment.app.B */
class C0383B extends AnimationSet implements Runnable {

    /* renamed from: Zk */
    private final View f334Zk;

    /* renamed from: _k */
    private boolean f335_k;
    private boolean mAnimating = true;
    private boolean mEnded;
    private final ViewGroup mParent;

    C0383B(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.mParent = viewGroup;
        this.f334Zk = view;
        addAnimation(animation);
        this.mParent.post(this);
    }

    public boolean getTransformation(long j, Transformation transformation) {
        this.mAnimating = true;
        if (this.mEnded) {
            return !this.f335_k;
        }
        if (!super.getTransformation(j, transformation)) {
            this.mEnded = true;
            OneShotPreDrawListener.add(this.mParent, this);
        }
        return true;
    }

    public void run() {
        if (this.mEnded || !this.mAnimating) {
            this.mParent.endViewTransition(this.f334Zk);
            this.f335_k = true;
            return;
        }
        this.mAnimating = false;
        this.mParent.post(this);
    }

    public boolean getTransformation(long j, Transformation transformation, float f) {
        this.mAnimating = true;
        if (this.mEnded) {
            return !this.f335_k;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.mEnded = true;
            OneShotPreDrawListener.add(this.mParent, this);
        }
        return true;
    }
}
