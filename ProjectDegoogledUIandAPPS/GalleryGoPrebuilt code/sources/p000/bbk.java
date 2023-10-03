package p000;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* renamed from: bbk */
/* compiled from: PG */
public abstract class bbk implements aua, atv {

    /* renamed from: a */
    public final Drawable f1995a;

    public bbk(Drawable drawable) {
        this.f1995a = (Drawable) cns.m4632a((Object) drawable);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo1605b() {
        Drawable.ConstantState constantState = this.f1995a.getConstantState();
        return constantState == null ? this.f1995a : constantState.newDrawable();
    }

    /* renamed from: e */
    public void mo1621e() {
        Drawable drawable = this.f1995a;
        if (drawable instanceof BitmapDrawable) {
            ((BitmapDrawable) drawable).getBitmap().prepareToDraw();
        } else if (drawable instanceof bbt) {
            ((bbt) drawable).mo1783a().prepareToDraw();
        }
    }
}
