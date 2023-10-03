package p000;

import p003j$.util.Optional;

/* renamed from: dul */
/* compiled from: PG */
public abstract class dul implements cpt {
    /* renamed from: a */
    public abstract int mo4432a();

    /* renamed from: b */
    public abstract ede mo4433b();

    /* renamed from: c */
    public abstract cia mo4437c();

    /* renamed from: d */
    public abstract Optional mo4441d();

    /* renamed from: e */
    public final Object mo4458e() {
        int a = mo4432a();
        int i = a - 1;
        if (a == 0) {
            throw null;
        } else if (i == 0) {
            return mo4437c().mo3107a();
        } else {
            if (i == 1) {
                return mo4433b();
            }
            if (i == 2) {
                return mo4441d();
            }
            String a2 = dvg.m6743a(mo4432a());
            StringBuilder sb = new StringBuilder(a2.length() + 44);
            sb.append("Kind ");
            sb.append(a2);
            sb.append(" of PeopleGridItem isn't accounted for.");
            throw new IllegalStateException(sb.toString());
        }
    }
}
