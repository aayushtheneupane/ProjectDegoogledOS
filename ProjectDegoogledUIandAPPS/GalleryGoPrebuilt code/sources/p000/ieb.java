package p000;

/* renamed from: ieb */
/* compiled from: PG */
final class ieb extends ibn implements Runnable {

    /* renamed from: a */
    private ieh f13950a;

    public ieb(ieh ieh) {
        this.f13950a = ieh;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        this.f13950a = null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        ieh ieh = this.f13950a;
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
        ieh ieh = this.f13950a;
        if (ieh != null) {
            mo6946a(ieh);
        }
    }
}
