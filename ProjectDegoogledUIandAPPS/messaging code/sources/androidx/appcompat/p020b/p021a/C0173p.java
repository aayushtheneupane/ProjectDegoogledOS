package androidx.appcompat.p020b.p021a;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;

@SuppressLint({"RestrictedAPI"})
/* renamed from: androidx.appcompat.b.a.p */
class C0173p extends C0169l {

    /* renamed from: Rc */
    private C0172o f168Rc;
    private boolean mMutated;

    C0173p(C0172o oVar, Resources resources) {
        mo1085a(new C0172o(oVar, this, resources));
        onStateChange(getState());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1085a(C0168k kVar) {
        super.mo1085a(kVar);
        if (kVar instanceof C0172o) {
            this.f168Rc = (C0172o) kVar;
        }
    }

    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    /* access modifiers changed from: package-private */
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    public boolean isStateful() {
        return true;
    }

    public Drawable mutate() {
        if (!this.mMutated) {
            super.mutate();
            if (this == this) {
                this.f168Rc.mutate();
                this.mMutated = true;
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int indexOfStateSet = this.f168Rc.indexOfStateSet(iArr);
        if (indexOfStateSet < 0) {
            indexOfStateSet = this.f168Rc.indexOfStateSet(StateSet.WILD_CARD);
        }
        return selectDrawable(indexOfStateSet) || onStateChange;
    }

    /* access modifiers changed from: package-private */
    public C0172o cloneConstantState() {
        return new C0172o(this.f168Rc, this, (Resources) null);
    }

    C0173p(C0172o oVar) {
        if (oVar != null) {
            mo1085a(oVar);
        }
    }
}
