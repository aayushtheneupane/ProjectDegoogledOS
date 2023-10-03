package p000;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

/* renamed from: agv */
/* compiled from: PG */
final class agv extends Drawable.ConstantState {

    /* renamed from: a */
    private final Drawable.ConstantState f450a;

    public agv(Drawable.ConstantState constantState) {
        this.f450a = constantState;
    }

    public final boolean canApplyTheme() {
        return this.f450a.canApplyTheme();
    }

    public int getChangingConfigurations() {
        return this.f450a.getChangingConfigurations();
    }

    public final Drawable newDrawable() {
        agw agw = new agw();
        agw.f396a = (VectorDrawable) this.f450a.newDrawable();
        return agw;
    }

    public final Drawable newDrawable(Resources resources) {
        agw agw = new agw();
        agw.f396a = (VectorDrawable) this.f450a.newDrawable(resources);
        return agw;
    }

    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        agw agw = new agw();
        agw.f396a = (VectorDrawable) this.f450a.newDrawable(resources, theme);
        return agw;
    }
}
