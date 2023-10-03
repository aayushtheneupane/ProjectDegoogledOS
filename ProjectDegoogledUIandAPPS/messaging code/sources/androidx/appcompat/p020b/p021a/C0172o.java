package androidx.appcompat.p020b.p021a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;

/* renamed from: androidx.appcompat.b.a.o */
class C0172o extends C0168k {
    int[][] mStateSets;

    C0172o(C0172o oVar, C0173p pVar, Resources resources) {
        super(oVar, pVar, resources);
        if (oVar != null) {
            this.mStateSets = oVar.mStateSets;
        } else {
            this.mStateSets = new int[this.mDrawables.length][];
        }
    }

    /* access modifiers changed from: package-private */
    public int indexOfStateSet(int[] iArr) {
        int[][] iArr2 = this.mStateSets;
        int i = this.mNumChildren;
        for (int i2 = 0; i2 < i; i2++) {
            if (StateSet.stateSetMatches(iArr2[i2], iArr)) {
                return i2;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void mutate() {
        int[][] iArr = this.mStateSets;
        int[][] iArr2 = new int[iArr.length][];
        for (int length = iArr.length - 1; length >= 0; length--) {
            int[][] iArr3 = this.mStateSets;
            iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
        }
        this.mStateSets = iArr2;
    }

    public Drawable newDrawable() {
        return new C0173p(this, (Resources) null);
    }

    public Drawable newDrawable(Resources resources) {
        return new C0173p(this, resources);
    }
}
