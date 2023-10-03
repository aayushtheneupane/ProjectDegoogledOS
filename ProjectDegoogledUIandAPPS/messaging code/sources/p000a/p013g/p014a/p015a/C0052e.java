package p000a.p013g.p014a.p015a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* renamed from: a.g.a.a.e */
class C0052e extends Drawable.ConstantState {

    /* renamed from: wc */
    private final Drawable.ConstantState f37wc;

    public C0052e(Drawable.ConstantState constantState) {
        this.f37wc = constantState;
    }

    public boolean canApplyTheme() {
        return this.f37wc.canApplyTheme();
    }

    public int getChangingConfigurations() {
        return this.f37wc.getChangingConfigurations();
    }

    public Drawable newDrawable() {
        C0053f fVar = new C0053f();
        fVar.f40hd = this.f37wc.newDrawable();
        fVar.f40hd.setCallback(fVar.mCallback);
        return fVar;
    }

    public Drawable newDrawable(Resources resources) {
        C0053f fVar = new C0053f();
        fVar.f40hd = this.f37wc.newDrawable(resources);
        fVar.f40hd.setCallback(fVar.mCallback);
        return fVar;
    }

    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        C0053f fVar = new C0053f();
        fVar.f40hd = this.f37wc.newDrawable(resources, theme);
        fVar.f40hd.setCallback(fVar.mCallback);
        return fVar;
    }
}
