package p000;

/* renamed from: grd */
/* compiled from: PG */
final class grd extends ibr {

    /* renamed from: a */
    private grf f11884a;

    /* renamed from: f */
    private final int f11885f;

    public /* synthetic */ grd(grf grf, int i) {
        this.f11884a = grf;
        this.f11885f = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        long j;
        int i;
        int a;
        gre gre;
        grf grf = this.f11884a;
        this.f11884a = null;
        if (grf != null) {
            do {
                j = grf.f11888b.get();
                i = (int) j;
                a = grf.m10656a(j);
                if (i == Integer.MIN_VALUE) {
                    StringBuilder sb = new StringBuilder(33);
                    sb.append("Refcount is: ");
                    sb.append(j);
                    throw new AssertionError(sb.toString());
                } else if (i == -2147483647) {
                    a++;
                }
            } while (!grf.f11888b.compareAndSet(j, grf.m10657a(a, i - 1)));
            if (i == -2147483647) {
                do {
                    gre = (gre) grf.f11889c.get();
                    if (gre != null && gre.f11886a <= this.f11885f) {
                        gre.cancel(true);
                    } else {
                        return;
                    }
                } while (!grf.f11889c.compareAndSet(gre, (Object) null));
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        ice ice;
        grf grf = this.f11884a;
        String str = null;
        if (!(grf == null || (ice = grf.f11887a.f11882a) == null)) {
            String valueOf = String.valueOf(ice);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 11);
            sb.append("callable=[");
            sb.append(valueOf);
            sb.append("]");
            str = sb.toString();
            gre gre = (gre) this.f11884a.f11889c.get();
            if (gre != null) {
                String valueOf2 = String.valueOf(str);
                String valueOf3 = String.valueOf(gre);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 10 + String.valueOf(valueOf3).length());
                sb2.append(valueOf2);
                sb2.append(", trial=[");
                sb2.append(valueOf3);
                sb2.append("]");
                return sb2.toString();
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo6946a(ieh ieh) {
        return super.mo6946a(ieh);
    }
}
