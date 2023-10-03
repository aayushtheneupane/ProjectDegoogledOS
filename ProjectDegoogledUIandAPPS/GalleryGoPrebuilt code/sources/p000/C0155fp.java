package p000;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* renamed from: fp */
/* compiled from: PG */
final class C0155fp extends AnimationSet implements Runnable {

    /* renamed from: a */
    private final ViewGroup f10180a;

    /* renamed from: b */
    private final View f10181b;

    /* renamed from: c */
    private boolean f10182c;

    /* renamed from: d */
    private boolean f10183d;

    /* renamed from: e */
    private boolean f10184e = true;

    public C0155fp(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.f10180a = viewGroup;
        this.f10181b = view;
        addAnimation(animation);
        this.f10180a.post(this);
    }

    public final boolean getTransformation(long j, Transformation transformation) {
        this.f10184e = true;
        if (this.f10182c) {
            return !this.f10183d;
        }
        if (!super.getTransformation(j, transformation)) {
            this.f10182c = true;
            C0331ma.m14662a(this.f10180a, this);
        }
        return true;
    }

    public final boolean getTransformation(long j, Transformation transformation, float f) {
        this.f10184e = true;
        if (this.f10182c) {
            return !this.f10183d;
        }
        if (!super.getTransformation(j, transformation, f)) {
            this.f10182c = true;
            C0331ma.m14662a(this.f10180a, this);
        }
        return true;
    }

    public final void run() {
        if (!this.f10182c && this.f10184e) {
            this.f10184e = false;
            this.f10180a.post(this);
            return;
        }
        this.f10180a.endViewTransition(this.f10181b);
        this.f10183d = true;
    }
}
