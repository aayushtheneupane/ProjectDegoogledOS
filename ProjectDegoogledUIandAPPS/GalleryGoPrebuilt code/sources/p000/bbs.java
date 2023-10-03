package p000;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* renamed from: bbs */
/* compiled from: PG */
final class bbs extends Drawable.ConstantState {

    /* renamed from: a */
    public final bca f2005a;

    public bbs(bca bca) {
        this.f2005a = bca;
    }

    public final int getChangingConfigurations() {
        return 0;
    }

    public final Drawable newDrawable() {
        return new bbt(this);
    }

    public final Drawable newDrawable(Resources resources) {
        return newDrawable();
    }
}
