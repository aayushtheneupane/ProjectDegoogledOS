package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: xn */
/* compiled from: PG */
public abstract class C0641xn {

    /* renamed from: a */
    private final ArrayList f16291a = new ArrayList();

    /* renamed from: h */
    public C0639xl f16292h = null;

    /* renamed from: a */
    public abstract void mo10365a();

    /* renamed from: a */
    public boolean mo10368a(C0667ym ymVar, List list) {
        throw null;
    }

    /* renamed from: a */
    public abstract boolean mo4643a(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2);

    /* renamed from: a */
    public abstract boolean mo10554a(C0667ym ymVar, C0667ym ymVar2, C0640xm xmVar, C0640xm xmVar2);

    /* renamed from: b */
    public abstract boolean mo10370b();

    /* renamed from: b */
    public abstract boolean mo10555b(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2);

    /* renamed from: c */
    public abstract void mo10373c(C0667ym ymVar);

    /* renamed from: c */
    public abstract boolean mo4644c(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2);

    /* renamed from: d */
    public abstract void mo10374d();

    /* renamed from: e */
    public long mo4538e() {
        return 120;
    }

    /* renamed from: e */
    public boolean mo10557e(C0667ym ymVar) {
        throw null;
    }

    /* renamed from: f */
    public static void m15920f(C0667ym ymVar) {
        int i = ymVar.f16391j;
        if (!ymVar.mo10650j() && (i & 4) == 0) {
            int i2 = ymVar.f16385d;
            int d = ymVar.mo10644d();
            if (i2 == -1 || d == -1 || i2 != d) {
            }
        }
    }

    /* renamed from: d */
    public final void mo10556d(C0667ym ymVar) {
        C0639xl xlVar = this.f16292h;
        if (xlVar != null) {
            xlVar.mo10552a(ymVar);
        }
    }

    /* renamed from: f */
    public final void mo10558f() {
        int size = this.f16291a.size();
        for (int i = 0; i < size; i++) {
            ((C0638xk) this.f16291a.get(i)).mo10551a();
        }
        this.f16291a.clear();
    }

    /* renamed from: g */
    public static final C0640xm m15921g() {
        return new C0640xm();
    }

    /* renamed from: g */
    public static final C0640xm m15922g(C0667ym ymVar) {
        C0640xm g = m15921g();
        g.mo10553a(ymVar);
        return g;
    }
}
