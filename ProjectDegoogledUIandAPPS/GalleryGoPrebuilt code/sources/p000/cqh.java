package p000;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import p003j$.util.Optional;

/* renamed from: cqh */
/* compiled from: PG */
public final class cqh {

    /* renamed from: a */
    public final Set f5420a = new LinkedHashSet();

    /* renamed from: b */
    public List f5421b = new ArrayList();

    /* renamed from: c */
    public Optional f5422c = Optional.empty();

    /* renamed from: d */
    public boolean f5423d = false;

    /* renamed from: e */
    private final Set f5424e = new C0292kp();

    /* renamed from: f */
    private boolean f5425f = false;

    /* renamed from: a */
    public final void mo3758a(cqg cqg) {
        this.f5424e.add(cqg);
    }

    /* renamed from: g */
    public final boolean mo3767g() {
        return ((cqe) this.f5422c.get()).f5418c == -1 || this.f5420a.size() < ((cqe) this.f5422c.get()).f5418c;
    }

    /* renamed from: a */
    public final void mo3755a() {
        ife.m12876b(this.f5422c.isPresent(), (Object) "MultiselectState must be set");
        int a = cun.m5438a(((cqe) this.f5422c.get()).f5417b);
        if (a == 0 || a != 3) {
            this.f5420a.clear();
            mo3759b();
        }
    }

    /* renamed from: c */
    public final void mo3762c() {
        this.f5420a.clear();
        this.f5424e.clear();
    }

    /* renamed from: b */
    public final void mo3759b() {
        for (cqg a : this.f5424e) {
            a.mo2621a();
        }
    }

    /* renamed from: c */
    public final boolean mo3763c(cpt cpt) {
        return this.f5420a.contains(cpt);
    }

    /* renamed from: d */
    public final boolean mo3764d() {
        ife.m12876b(this.f5422c.isPresent(), (Object) "MultiselectState must be set");
        int a = cun.m5438a(((cqe) this.f5422c.get()).f5417b);
        if (a == 0) {
            a = 4;
        }
        int i = a - 1;
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            return this.f5425f || !this.f5420a.isEmpty();
        }
        return false;
    }

    /* renamed from: b */
    public final void mo3761b(cqg cqg) {
        this.f5424e.remove(cqg);
    }

    /* renamed from: a */
    public final void mo3756a(cpt cpt) {
        ife.m12876b(this.f5422c.isPresent(), (Object) "MultiselectState must be set");
        int a = cun.m5438a(((cqe) this.f5422c.get()).f5417b);
        if ((a == 0 || a != 3) && mo3767g()) {
            this.f5420a.add(cpt);
            mo3759b();
        }
    }

    /* renamed from: a */
    public final void mo3757a(cqe cqe) {
        ife.m12876b(!this.f5422c.isPresent(), (Object) "MultiselectState cannot be set twice");
        this.f5422c = Optional.m16285of(cqe);
    }

    /* renamed from: e */
    public final void mo3765e() {
        this.f5425f = true;
        mo3759b();
    }

    /* renamed from: f */
    public final void mo3766f() {
        this.f5425f = false;
    }

    /* renamed from: b */
    public final void mo3760b(cpt cpt) {
        ife.m12876b(this.f5422c.isPresent(), (Object) "MultiselectState must be set");
        int a = cun.m5438a(((cqe) this.f5422c.get()).f5417b);
        if (a == 0 || a != 3) {
            this.f5420a.remove(cpt);
            mo3759b();
        }
    }
}
