package androidx.appcompat.p020b.p021a;

import android.animation.TimeInterpolator;
import android.graphics.drawable.AnimationDrawable;

/* renamed from: androidx.appcompat.b.a.f */
class C0163f implements TimeInterpolator {

    /* renamed from: Ol */
    private int[] f145Ol;

    /* renamed from: Pl */
    private int f146Pl;
    private int mTotalDuration;

    C0163f(AnimationDrawable animationDrawable, boolean z) {
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        this.f146Pl = numberOfFrames;
        int[] iArr = this.f145Ol;
        if (iArr == null || iArr.length < numberOfFrames) {
            this.f145Ol = new int[numberOfFrames];
        }
        int[] iArr2 = this.f145Ol;
        int i = 0;
        for (int i2 = 0; i2 < numberOfFrames; i2++) {
            int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
            iArr2[i2] = duration;
            i += duration;
        }
        this.mTotalDuration = i;
    }

    public float getInterpolation(float f) {
        int i = (int) ((f * ((float) this.mTotalDuration)) + 0.5f);
        int i2 = this.f146Pl;
        int[] iArr = this.f145Ol;
        int i3 = 0;
        while (i3 < i2 && i >= iArr[i3]) {
            i -= iArr[i3];
            i3++;
        }
        return (((float) i3) / ((float) i2)) + (i3 < i2 ? ((float) i) / ((float) this.mTotalDuration) : 0.0f);
    }

    /* access modifiers changed from: package-private */
    public int getTotalDuration() {
        return this.mTotalDuration;
    }
}
