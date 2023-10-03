package p000;

import java.io.Serializable;

/* renamed from: fit */
/* compiled from: PG */
final class fit implements fkm {

    /* renamed from: a */
    public final fji f9756a;

    public fit(fji fji) {
        this.f9756a = fji;
    }

    /* renamed from: a */
    public final void mo5837a(fnj fnj, String str, long j, long j2, iqx iqx) {
        ife.m12898e((Object) fnj);
        if (this.f9756a.mo5878g()) {
            flw.m9191a(((fnd) this.f9756a.f9807c.mo2652a()).mo5893a(str, j, j2, iqx));
        }
    }

    /* renamed from: a */
    public final void mo5838a(String str) {
        mo5840b(str);
    }

    /* renamed from: b */
    public final void mo5840b(String str) {
        if (this.f9756a.mo5876e()) {
            flw.m9191a(this.f9756a.mo5877f().mo5884a(str, 1, (String) null));
        }
    }

    /* renamed from: a */
    public final void mo5836a(fnb fnb, iri iri) {
        ife.m12898e((Object) fnb);
        if (iri == null || iri.f14848d.size() == 0) {
            flw.m9202d("Primes", "Invalid traces were logged.", new Object[0]);
        } else if (this.f9756a.mo5874c()) {
            fni d = this.f9756a.mo5875d();
            if (d.mo5731b()) {
                iir g = isc.f14974r.mo8793g();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                isc isc = (isc) g.f14318b;
                iri.getClass();
                isc.f14989n = iri;
                isc.f14976a |= 32768;
                isc isc2 = (isc) g.mo8770g();
                Serializable[] serializableArr = new Serializable[2];
                String str = null;
                serializableArr[0] = (iri.f14845a & 1) != 0 ? Long.valueOf(iri.f14846b) : null;
                if (iri.f14848d.size() > 0) {
                    str = ((irk) iri.f14848d.get(0)).f14856b;
                }
                serializableArr[1] = str;
                flw.m9199b("TraceMetricService", "Recording trace %d: %s", serializableArr);
                d.mo5730a((String) null, true, isc2, (iqx) null, (String) null);
            }
        }
    }

    /* renamed from: a */
    public final void mo5835a() {
        this.f9756a.f9815k.mo5976a();
    }

    /* renamed from: c */
    public final void mo5841c() {
        if (this.f9756a.mo5872a()) {
            fiz b = this.f9756a.mo5873b();
            if (b.f9763d.compareAndSet(false, true)) {
                Thread.setDefaultUncaughtExceptionHandler(b.mo5845a(Thread.getDefaultUncaughtExceptionHandler()));
            }
        }
    }

    /* renamed from: b */
    public final void mo5839b() {
        if (this.f9756a.mo5876e()) {
            this.f9756a.mo5877f().mo5885e();
        }
    }
}
