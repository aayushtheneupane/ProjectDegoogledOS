package p000;

import android.content.Context;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

/* renamed from: bdg */
/* compiled from: PG */
public final class bdg extends C0147fh {

    /* renamed from: Z */
    private final Set f2082Z = new HashSet();

    /* renamed from: a */
    public final bcm f2083a;

    /* renamed from: aa */
    private bdg f2084aa;

    /* renamed from: b */
    public final bdd f2085b = new bdf(this);

    /* renamed from: c */
    public apn f2086c;

    /* renamed from: d */
    public C0147fh f2087d;

    public bdg() {
        bcm bcm = new bcm();
        this.f2083a = bcm;
    }

    /* renamed from: b */
    public static C0171gd m2188b(C0147fh fhVar) {
        while (true) {
            C0147fh fhVar2 = fhVar.f9607z;
            if (fhVar2 == null) {
                return fhVar.f9604w;
            }
            fhVar = fhVar2;
        }
    }

    /* renamed from: a */
    public final void mo1832a(Context context) {
        super.mo1832a(context);
        C0171gd b = m2188b(this);
        if (b != null) {
            try {
                mo1833a(mo2634k(), b);
            } catch (IllegalStateException e) {
                if (Log.isLoggable("SupportRMFragment", 5)) {
                    Log.w("SupportRMFragment", "Unable to register fragment with root", e);
                }
            }
        } else if (Log.isLoggable("SupportRMFragment", 5)) {
            Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
        }
    }

    /* renamed from: x */
    public final void mo1836x() {
        super.mo1836x();
        this.f2083a.mo1815c();
        m2187P();
    }

    /* renamed from: c */
    public final void mo1834c() {
        super.mo1834c();
        this.f2087d = null;
        m2187P();
    }

    /* renamed from: d */
    public final void mo210d() {
        super.mo210d();
        this.f2083a.mo1811a();
    }

    /* renamed from: e */
    public final void mo211e() {
        super.mo211e();
        this.f2083a.mo1813b();
    }

    /* renamed from: a */
    public final void mo1833a(Context context, C0171gd gdVar) {
        m2187P();
        bdg a = aow.m1346a(context).f1293f.mo1827a(gdVar, (C0147fh) null, bdc.m2180b(context));
        this.f2084aa = a;
        if (!equals(a)) {
            this.f2084aa.f2082Z.add(this);
        }
    }

    public final String toString() {
        String fhVar = super.toString();
        C0147fh fhVar2 = this.f9607z;
        if (fhVar2 == null) {
            fhVar2 = this.f2087d;
        }
        String valueOf = String.valueOf(fhVar2);
        StringBuilder sb = new StringBuilder(String.valueOf(fhVar).length() + 9 + String.valueOf(valueOf).length());
        sb.append(fhVar);
        sb.append("{parent=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    /* renamed from: P */
    private final void m2187P() {
        bdg bdg = this.f2084aa;
        if (bdg != null) {
            bdg.f2082Z.remove(this);
            this.f2084aa = null;
        }
    }
}
