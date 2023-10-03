package androidx.appcompat.p020b.p021a;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;

/* renamed from: androidx.appcompat.b.a.e */
class C0162e extends C0164g {

    /* renamed from: Rl */
    private final ObjectAnimator f143Rl;

    /* renamed from: Sl */
    private final boolean f144Sl;

    C0162e(AnimationDrawable animationDrawable, boolean z, boolean z2) {
        super((C0158a) null);
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        int i = z ? numberOfFrames - 1 : 0;
        int i2 = z ? 0 : numberOfFrames - 1;
        C0163f fVar = new C0163f(animationDrawable, z);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", new int[]{i, i2});
        int i3 = Build.VERSION.SDK_INT;
        ofInt.setAutoCancel(true);
        ofInt.setDuration((long) fVar.getTotalDuration());
        ofInt.setInterpolator(fVar);
        this.f144Sl = z2;
        this.f143Rl = ofInt;
    }

    public boolean canReverse() {
        return this.f144Sl;
    }

    public void reverse() {
        this.f143Rl.reverse();
    }

    public void start() {
        this.f143Rl.start();
    }

    public void stop() {
        this.f143Rl.cancel();
    }
}
