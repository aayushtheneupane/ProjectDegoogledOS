package p000;

import android.view.View;

/* renamed from: yt */
/* compiled from: PG */
public abstract class C0674yt extends C0641xn {

    /* renamed from: a */
    private boolean f16420a = true;

    /* renamed from: a */
    public abstract boolean mo10366a(C0667ym ymVar);

    /* renamed from: a */
    public abstract boolean mo10367a(C0667ym ymVar, int i, int i2, int i3, int i4);

    /* renamed from: a */
    public abstract boolean mo10369a(C0667ym ymVar, C0667ym ymVar2, int i, int i2, int i3, int i4);

    /* renamed from: b */
    public abstract boolean mo10371b(C0667ym ymVar);

    /* renamed from: b */
    public final boolean mo10555b(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        int i;
        int i2;
        if (xmVar == null || ((i = xmVar.f16289a) == (i2 = xmVar2.f16289a) && xmVar.f16290b == xmVar2.f16290b)) {
            return mo10371b(ymVar);
        }
        return mo10367a(ymVar, i, xmVar.f16290b, i2, xmVar2.f16290b);
    }

    /* renamed from: a */
    public final boolean mo10554a(C0667ym ymVar, C0667ym ymVar2, C0640xm xmVar, C0640xm xmVar2) {
        int i;
        int i2;
        int i3 = xmVar.f16289a;
        int i4 = xmVar.f16290b;
        if (ymVar2.mo10642b()) {
            int i5 = xmVar.f16289a;
            i = xmVar.f16290b;
            i2 = i5;
        } else {
            i2 = xmVar2.f16289a;
            i = xmVar2.f16290b;
        }
        return mo10369a(ymVar, ymVar2, i3, i4, i2, i);
    }

    /* renamed from: a */
    public boolean mo4643a(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        int i;
        int i2;
        int i3 = xmVar.f16289a;
        int i4 = xmVar.f16290b;
        View view = ymVar.f16382a;
        if (xmVar2 != null) {
            i = xmVar2.f16289a;
        } else {
            i = view.getLeft();
        }
        if (xmVar2 != null) {
            i2 = xmVar2.f16290b;
        } else {
            i2 = view.getTop();
        }
        if (ymVar.mo10653m() || (i3 == i && i4 == i2)) {
            return mo10366a(ymVar);
        }
        view.layout(i, i2, view.getWidth() + i, view.getHeight() + i2);
        return mo10367a(ymVar, i3, i4, i, i2);
    }

    /* renamed from: c */
    public boolean mo4644c(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        int i = xmVar.f16289a;
        int i2 = xmVar2.f16289a;
        if (i == i2 && xmVar.f16290b == xmVar2.f16290b) {
            mo10556d(ymVar);
            return false;
        }
        return mo10367a(ymVar, i, xmVar.f16290b, i2, xmVar2.f16290b);
    }

    /* renamed from: e */
    public final boolean mo10557e(C0667ym ymVar) {
        return !this.f16420a || ymVar.mo10650j();
    }

    /* renamed from: h */
    public final void mo10713h() {
        this.f16420a = false;
    }
}
