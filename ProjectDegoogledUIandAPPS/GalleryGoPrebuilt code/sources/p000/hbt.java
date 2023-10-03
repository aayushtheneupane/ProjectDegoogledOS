package p000;

/* renamed from: hbt */
/* compiled from: PG */
public final class hbt extends C0002ab {

    /* renamed from: b */
    private hkn f12466b;

    public hbt(C0149fj fjVar) {
        super(fjVar);
    }

    /* renamed from: a */
    public final void mo62a(C0546u uVar) {
        if (this.f62a.f15181e > 0) {
            hkn hkn = this.f12466b;
            C0546u uVar2 = C0546u.ON_CREATE;
            int ordinal = uVar.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    hkn.mo7517a("Lifecycle.Started");
                } else if (ordinal == 2) {
                    hkn.mo7517a("Lifecycle.Resumed");
                } else if (ordinal == 3) {
                    hkn.mo7517a("Lifecycle.Paused");
                } else if (ordinal == 4) {
                    hkn.mo7517a("Lifecycle.Stopped");
                } else if (ordinal == 5) {
                    hkn.mo7517a("Lifecycle.Destroyed");
                } else {
                    String valueOf = String.valueOf(uVar);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
                    sb.append("Unknown lifecycle: ");
                    sb.append(valueOf);
                    throw new UnsupportedOperationException(sb.toString());
                }
            } else if (hkn.f12925e == null) {
                hkn.mo7517a("Lifecycle.Created");
                hkn.f12924d = true;
            }
            try {
                super.mo62a(uVar);
            } finally {
                this.f12466b.mo7518a(uVar);
            }
        } else {
            super.mo62a(uVar);
        }
    }

    /* renamed from: a */
    public final void mo7270a(hkn hkn) {
        ife.m12876b(this.f12466b == null, (Object) "Activity was already created");
        this.f12466b = hkn;
    }
}
