package p000;

/* renamed from: ios */
/* compiled from: PG */
final class ios extends ibr implements Runnable {

    /* renamed from: a */
    private ieh f14606a;

    public ios(ieh ieh) {
        this.f14606a = ieh;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        this.f14606a = null;
    }

    /* renamed from: f */
    public final boolean mo9048f() {
        return super.mo8348d();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        ieh ieh = this.f14606a;
        if (ieh == null) {
            return null;
        }
        String valueOf = String.valueOf(ieh);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 11);
        sb.append("delegate=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        ieh ieh = this.f14606a;
        if (ieh != null) {
            mo6946a(ieh);
        }
    }
}
