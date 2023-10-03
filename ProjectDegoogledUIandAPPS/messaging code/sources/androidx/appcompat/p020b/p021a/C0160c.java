package androidx.appcompat.p020b.p021a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import p000a.p005b.C0019f;
import p000a.p005b.C0028o;

/* renamed from: androidx.appcompat.b.a.c */
class C0160c extends C0172o {
    C0028o mStateIds;
    C0019f mTransitions;

    C0160c(C0160c cVar, C0165h hVar, Resources resources) {
        super(cVar, hVar, resources);
        if (cVar != null) {
            this.mTransitions = cVar.mTransitions;
            this.mStateIds = cVar.mStateIds;
            return;
        }
        this.mTransitions = new C0019f();
        this.mStateIds = new C0028o(10);
    }

    /* renamed from: P */
    private static long m143P(int i, int i2) {
        return ((long) i2) | (((long) i) << 32);
    }

    /* access modifiers changed from: package-private */
    public int addTransition(int i, int i2, Drawable drawable, boolean z) {
        int addChild = super.addChild(drawable);
        long P = m143P(i, i2);
        long j = z ? 8589934592L : 0;
        long j2 = (long) addChild;
        this.mTransitions.append(P, Long.valueOf(j2 | j));
        if (z) {
            this.mTransitions.append(m143P(i2, i), Long.valueOf(4294967296L | j2 | j));
        }
        return addChild;
    }

    /* access modifiers changed from: package-private */
    public int getKeyframeIdAt(int i) {
        if (i < 0) {
            return 0;
        }
        return ((Integer) this.mStateIds.get(i, 0)).intValue();
    }

    /* access modifiers changed from: package-private */
    public int indexOfKeyframe(int[] iArr) {
        int indexOfStateSet = super.indexOfStateSet(iArr);
        if (indexOfStateSet >= 0) {
            return indexOfStateSet;
        }
        return super.indexOfStateSet(StateSet.WILD_CARD);
    }

    /* access modifiers changed from: package-private */
    public int indexOfTransition(int i, int i2) {
        return (int) ((Long) this.mTransitions.get(m143P(i, i2), -1L)).longValue();
    }

    /* access modifiers changed from: package-private */
    public boolean isTransitionReversed(int i, int i2) {
        return (((Long) this.mTransitions.get(m143P(i, i2), -1L)).longValue() & 4294967296L) != 0;
    }

    /* access modifiers changed from: package-private */
    public void mutate() {
        this.mTransitions = this.mTransitions.clone();
        this.mStateIds = this.mStateIds.clone();
    }

    public Drawable newDrawable() {
        return new C0165h(this, (Resources) null);
    }

    /* access modifiers changed from: package-private */
    public boolean transitionHasReversibleFlag(int i, int i2) {
        return (((Long) this.mTransitions.get(m143P(i, i2), -1L)).longValue() & 8589934592L) != 0;
    }

    public Drawable newDrawable(Resources resources) {
        return new C0165h(this, resources);
    }
}
