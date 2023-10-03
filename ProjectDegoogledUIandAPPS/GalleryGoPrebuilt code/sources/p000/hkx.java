package p000;

import android.content.Context;

/* renamed from: hkx */
/* compiled from: PG */
public final class hkx {

    /* renamed from: a */
    private final C0147fh f12949a;

    public hkx(C0147fh fhVar) {
        this.f12949a = fhVar;
    }

    /* renamed from: a */
    private final hlq m11659a(String str) {
        if (!hnb.m11774a(hnf.f13084a)) {
            return hok.m11840b((Context) ife.m12869b((Object) this.f12949a.mo5653m(), (Object) "called before fragment was attached to an Activity")).mo7577a(str);
        }
        return hnb.m11765a(str);
    }

    /* renamed from: a */
    public final hlq mo7537a() {
        return m11659a("Fragment:onActivityResult");
    }

    /* renamed from: c */
    public final hlq mo7539c() {
        hlq hlq;
        hnb.m11777b(hnf.f13084a);
        if (hnb.m11774a(hnf.f13084a)) {
            hlq = hnb.m11765a("DialogFragment:onDismiss");
        } else {
            hlq = ((hkw) iol.m14235b(this.f12949a, hkw.class)).mo2394P().mo7577a("DialogFragment:onDismiss");
        }
        return new hkv(hlq);
    }

    /* renamed from: b */
    public final hlq mo7538b() {
        return m11659a("Fragment:onOptionsItemSelected");
    }
}
