package p000;

import com.google.android.gms.common.api.Status;

/* renamed from: eli */
/* compiled from: PG */
public final class eli extends ell {

    /* renamed from: a */
    private final elq f8507a;

    public eli(elq elq) {
        this.f8507a = elq;
    }

    /* renamed from: a */
    public final void mo4962a(Status status) {
        this.f8507a.mo4980b(status);
    }

    /* renamed from: a */
    public final void mo4964a(Exception exc) {
        String simpleName = exc.getClass().getSimpleName();
        String localizedMessage = exc.getLocalizedMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(simpleName).length() + 2 + String.valueOf(localizedMessage).length());
        sb.append(simpleName);
        sb.append(": ");
        sb.append(localizedMessage);
        this.f8507a.mo4980b(new Status(10, sb.toString()));
    }

    /* renamed from: c */
    public final void mo4965c(enl enl) {
        try {
            this.f8507a.mo4981b((ekl) enl.f8649b);
        } catch (RuntimeException e) {
            mo4964a((Exception) e);
        }
    }

    /* renamed from: a */
    public final void mo4963a(emg emg, boolean z) {
        elq elq = this.f8507a;
        emg.f8552a.put(elq, Boolean.valueOf(z));
        elq.mo3506a((ekw) new eme(emg, elq));
    }
}
