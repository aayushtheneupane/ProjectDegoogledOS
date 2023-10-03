package p000a.p013g.p014a.p015a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

/* renamed from: a.g.a.a.p */
class C0063p extends Drawable.ConstantState {

    /* renamed from: wc */
    private final Drawable.ConstantState f73wc;

    public C0063p(Drawable.ConstantState constantState) {
        this.f73wc = constantState;
    }

    public boolean canApplyTheme() {
        return this.f73wc.canApplyTheme();
    }

    public int getChangingConfigurations() {
        return this.f73wc.getChangingConfigurations();
    }

    public Drawable newDrawable() {
        C0064q qVar = new C0064q();
        qVar.f40hd = (VectorDrawable) this.f73wc.newDrawable();
        return qVar;
    }

    public Drawable newDrawable(Resources resources) {
        C0064q qVar = new C0064q();
        qVar.f40hd = (VectorDrawable) this.f73wc.newDrawable(resources);
        return qVar;
    }

    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        C0064q qVar = new C0064q();
        qVar.f40hd = (VectorDrawable) this.f73wc.newDrawable(resources, theme);
        return qVar;
    }
}
