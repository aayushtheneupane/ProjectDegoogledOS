package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: fmw */
/* compiled from: PG */
public final class fmw {

    /* renamed from: a */
    public volatile boolean f10070a;

    /* renamed from: b */
    private final List f10071b = new ArrayList();

    public fmw() {
    }

    /* renamed from: a */
    public final boolean mo5978a(fmx fmx) {
        synchronized (this.f10071b) {
            if (this.f10070a) {
                return false;
            }
            this.f10071b.add((fmx) ife.m12898e((Object) fmx));
            return true;
        }
    }

    /* renamed from: a */
    public final synchronized void mo5976a() {
        if (!this.f10070a) {
            this.f10070a = true;
            flw.m9199b("PrimesShutdown", "Shutdown ...", new Object[0]);
            synchronized (this.f10071b) {
                for (fmx a : this.f10071b) {
                    try {
                        a.mo5727a();
                    } catch (RuntimeException e) {
                        flw.m9192a("PrimesShutdown", "ShutdownListener crashed", (Throwable) e, new Object[0]);
                    }
                }
                this.f10071b.clear();
                flw.m9199b("PrimesShutdown", "All ShutdownListeners notified.", new Object[0]);
            }
        }
    }

    /* renamed from: a */
    public final void mo5977a(hqk hqk) {
        if (!this.f10070a && ((fob) hqk).mo2652a().booleanValue()) {
            mo5976a();
        }
    }

    public /* synthetic */ fmw(byte[] bArr) {
    }
}
