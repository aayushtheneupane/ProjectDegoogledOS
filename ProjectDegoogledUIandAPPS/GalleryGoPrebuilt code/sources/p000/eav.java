package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: eav */
/* compiled from: PG */
public final class eav {

    /* renamed from: a */
    public final eau f7801a = new eau(this);

    /* renamed from: b */
    public final gwd f7802b;

    /* renamed from: c */
    public List f7803c = hso.m12047f();

    /* renamed from: d */
    public int f7804d = -1;

    /* renamed from: e */
    public Optional f7805e = Optional.empty();

    /* renamed from: f */
    private final ebc f7806f;

    public eav(ebc ebc, gwd gwd) {
        this.f7806f = ebc;
        this.f7802b = gwd;
    }

    /* renamed from: a */
    public final Optional mo4634a() {
        if (this.f7803c.isEmpty() || this.f7804d >= this.f7803c.size()) {
            return Optional.empty();
        }
        return Optional.m16285of((eaq) this.f7803c.get(this.f7804d));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4635a(int i) {
        int i2 = this.f7804d;
        if (i != i2) {
            ((eaq) this.f7803c.get(i2)).mo4230a(1);
            this.f7804d = i;
        }
        ((eaq) this.f7803c.get(i)).mo4230a(this.f7801a.mo4633a(i));
        this.f7805e.ifPresent(eat.f7797a);
    }

    /* renamed from: a */
    public final void mo4637a(eaq eaq) {
        this.f7803c.set(this.f7804d, eaq);
        this.f7802b.mo10538c(this.f7804d);
    }

    /* renamed from: a */
    public final void mo4636a(int i, boolean z) {
        this.f7804d = i;
        ebc ebc = this.f7806f;
        if (!z) {
            eaz eaz = ebc.f7830f;
            eaz.f7811i = true;
            eaz.f7812j = true;
        }
        ebc.f7826b.scrollToPosition(i);
        mo4635a(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4638a(boolean z) {
        for (int i = -1; i <= 1; i++) {
            int i2 = this.f7804d + i;
            if (i2 >= 0 && i2 < this.f7803c.size()) {
                ((eaq) this.f7803c.get(this.f7804d + i)).mo4233a(z);
            }
        }
    }
}
