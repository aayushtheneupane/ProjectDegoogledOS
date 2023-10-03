package p000;

import java.util.ArrayList;

/* renamed from: gn */
/* compiled from: PG */
public abstract class C0182gn {

    /* renamed from: c */
    public final ArrayList f11644c = new ArrayList();

    /* renamed from: d */
    public int f11645d;

    /* renamed from: e */
    public int f11646e;

    /* renamed from: f */
    public int f11647f;

    /* renamed from: g */
    public int f11648g;

    /* renamed from: h */
    public int f11649h;

    /* renamed from: i */
    public boolean f11650i;

    /* renamed from: j */
    public boolean f11651j = true;

    /* renamed from: k */
    public String f11652k;

    /* renamed from: l */
    public int f11653l;

    /* renamed from: m */
    public CharSequence f11654m;

    /* renamed from: n */
    public int f11655n;

    /* renamed from: o */
    public CharSequence f11656o;

    /* renamed from: p */
    public ArrayList f11657p;

    /* renamed from: q */
    public ArrayList f11658q;

    /* renamed from: r */
    public boolean f11659r = false;

    /* renamed from: a */
    public C0182gn mo5243a(C0147fh fhVar) {
        throw null;
    }

    /* renamed from: a */
    public abstract void mo5244a();

    /* renamed from: a */
    public void mo5246a(int i, C0147fh fhVar, String str, int i2) {
        throw null;
    }

    /* renamed from: b */
    public C0182gn mo5250b(C0147fh fhVar) {
        throw null;
    }

    /* renamed from: b */
    public abstract void mo5251b();

    /* renamed from: c */
    public abstract void mo5252c();

    /* renamed from: b */
    public final void mo6853b(int i, C0147fh fhVar, String str) {
        mo5246a(i, fhVar, str, 1);
    }

    /* renamed from: a */
    public final void mo6851a(C0147fh fhVar, String str) {
        mo5246a(0, fhVar, str, 1);
    }

    /* renamed from: a */
    public final void mo6852a(C0180gm gmVar) {
        this.f11644c.add(gmVar);
        gmVar.f11608c = this.f11645d;
        gmVar.f11609d = this.f11646e;
        gmVar.f11610e = this.f11647f;
        gmVar.f11611f = this.f11648g;
    }

    /* renamed from: d */
    public final void mo6854d() {
        if (!this.f11650i) {
            this.f11651j = false;
            return;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    /* renamed from: a */
    public final C0182gn mo6849a(int i, C0147fh fhVar) {
        return mo6850a(i, fhVar, (String) null);
    }

    /* renamed from: a */
    public final C0182gn mo6850a(int i, C0147fh fhVar, String str) {
        if (i != 0) {
            mo5246a(i, fhVar, str, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    /* renamed from: a */
    public final C0182gn mo6847a(int i, int i2) {
        return mo6848a(i, i2, 0, 0);
    }

    /* renamed from: a */
    public final C0182gn mo6848a(int i, int i2, int i3, int i4) {
        this.f11645d = i;
        this.f11646e = i2;
        this.f11647f = i3;
        this.f11648g = i4;
        return this;
    }
}
