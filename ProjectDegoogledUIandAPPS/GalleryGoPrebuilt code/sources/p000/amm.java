package p000;

/* renamed from: amm */
/* compiled from: PG */
public final class amm implements Runnable {

    /* renamed from: a */
    private final amn f772a;

    /* renamed from: b */
    private final String f773b;

    public amm(amn amn, String str) {
        this.f772a = amn;
        this.f773b = str;
    }

    public final void run() {
        synchronized (this.f772a.f777d) {
            if (((amm) this.f772a.f775b.remove(this.f773b)) == null) {
                iol.m14231a();
                String.format("Timer with %s is already marked as complete.", new Object[]{this.f773b});
            } else {
                aml aml = (aml) this.f772a.f776c.remove(this.f773b);
                if (aml != null) {
                    aml.mo537a(this.f773b);
                }
            }
        }
    }
}
