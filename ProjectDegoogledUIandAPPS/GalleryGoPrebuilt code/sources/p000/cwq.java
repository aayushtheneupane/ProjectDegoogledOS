package p000;

import java.util.concurrent.Executor;

/* renamed from: cwq */
/* compiled from: PG */
public final class cwq {

    /* renamed from: a */
    public final inw f5815a;

    /* renamed from: b */
    public final inw f5816b;

    /* renamed from: c */
    private final iel f5817c;

    /* renamed from: d */
    private final iel f5818d;

    public cwq(inw inw, iel iel, inw inw2) {
        this.f5815a = inw;
        this.f5817c = gte.m10774a(iel);
        this.f5818d = iel;
        this.f5816b = inw2;
    }

    /* renamed from: a */
    public final void mo3869a(int i) {
        iir g = bjx.f2967d.mo8793g();
        hlj a = hnb.m11765a("Clearcut log event");
        try {
            cwn.m5509a(a.mo7548a(this.f5817c.mo5931a(hmq.m11748a((Runnable) new cwo(this, g, i)))), "ClearCutLogger: Failed to submit log event", new Object[0]);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo3870a(ieh ieh, int i, int i2) {
        cwp cwp = new cwp(this, i, i2);
        ife.m12841a(ieh, hmq.m11746a((idw) cwp), (Executor) this.f5818d);
    }
}
