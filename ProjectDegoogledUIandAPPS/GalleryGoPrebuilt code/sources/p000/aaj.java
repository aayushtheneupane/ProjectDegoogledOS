package p000;

import android.graphics.drawable.Drawable;

/* renamed from: aaj */
/* compiled from: PG */
public final class aaj implements aam {

    /* renamed from: a */
    public Drawable f20a;

    /* renamed from: b */
    public final /* synthetic */ aak f21b;

    public aaj(aak aak) {
        this.f21b = aak;
    }

    /* renamed from: a */
    public final boolean mo17a() {
        return this.f21b.f24b;
    }

    /* renamed from: b */
    public final boolean mo18b() {
        return this.f21b.f25c;
    }

    /* renamed from: a */
    public final void mo16a(int i, int i2, int i3, int i4) {
        this.f21b.f27e.set(i, i2, i3, i4);
        aak aak = this.f21b;
        aaj.super.setPadding(i + aak.f26d.left, i2 + this.f21b.f26d.top, i3 + this.f21b.f26d.right, i4 + this.f21b.f26d.bottom);
    }
}
