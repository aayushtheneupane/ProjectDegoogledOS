package p000;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* renamed from: bep */
/* compiled from: PG */
public abstract class bep extends beu {

    /* renamed from: c */
    private Animatable f2183c;

    public bep(ImageView imageView) {
        super(imageView);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo1908a(Object obj);

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
        this.f2190b.mo1915a();
        Animatable animatable = this.f2183c;
        if (animatable != null) {
            animatable.stop();
        }
        m2370b((Object) null);
        m2371d(drawable);
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        m2370b((Object) null);
        m2371d(drawable);
    }

    /* renamed from: c */
    public final void mo1898c(Drawable drawable) {
        m2370b((Object) null);
        m2371d(drawable);
    }

    /* renamed from: a */
    public final void mo1433a(Object obj, bex bex) {
        m2370b(obj);
    }

    /* renamed from: b */
    public final void mo1442b() {
        Animatable animatable = this.f2183c;
        if (animatable != null) {
            animatable.start();
        }
    }

    /* renamed from: c */
    public final void mo1443c() {
        Animatable animatable = this.f2183c;
        if (animatable != null) {
            animatable.stop();
        }
    }

    /* renamed from: d */
    private final void m2371d(Drawable drawable) {
        ((ImageView) this.f2189a).setImageDrawable(drawable);
    }

    /* renamed from: b */
    private final void m2370b(Object obj) {
        mo1908a(obj);
        if (obj instanceof Animatable) {
            Animatable animatable = (Animatable) obj;
            this.f2183c = animatable;
            animatable.start();
            return;
        }
        this.f2183c = null;
    }
}
