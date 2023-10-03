package p000;

/* renamed from: amv */
/* compiled from: PG */
final class amv extends amo {
    /* renamed from: a */
    public final boolean mo651a(amx amx, ams ams, ams ams2) {
        synchronized (amx) {
            if (amx.f804d != ams) {
                return false;
            }
            amx.f804d = ams2;
            return true;
        }
    }

    /* renamed from: a */
    public final boolean mo653a(amx amx, Object obj, Object obj2) {
        synchronized (amx) {
            if (amx.f803c != obj) {
                return false;
            }
            amx.f803c = obj2;
            return true;
        }
    }

    /* renamed from: a */
    public final boolean mo652a(amx amx, amw amw, amw amw2) {
        synchronized (amx) {
            if (amx.f805e != amw) {
                return false;
            }
            amx.f805e = amw2;
            return true;
        }
    }

    /* renamed from: a */
    public final void mo649a(amw amw, amw amw2) {
        amw.f798c = amw2;
    }

    /* renamed from: a */
    public final void mo650a(amw amw, Thread thread) {
        amw.f797b = thread;
    }
}
