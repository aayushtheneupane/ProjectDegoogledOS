package p000;

/* renamed from: fim */
/* compiled from: PG */
final class fim extends fmm {

    /* renamed from: a */
    public final int f9733a;

    /* renamed from: b */
    private final iel f9734b;

    /* renamed from: c */
    private final int f9735c;

    /* renamed from: d */
    private final int f9736d;

    /* renamed from: e */
    private final boolean f9737e;

    public /* synthetic */ fim(iel iel, int i, int i2, int i3, boolean z) {
        this.f9734b = iel;
        this.f9735c = i;
        this.f9736d = i2;
        this.f9733a = i3;
        this.f9737e = z;
    }

    /* renamed from: a */
    public final iel mo5801a() {
        return this.f9734b;
    }

    /* renamed from: b */
    public final int mo5802b() {
        return this.f9735c;
    }

    /* renamed from: c */
    public final int mo5803c() {
        return this.f9736d;
    }

    /* renamed from: d */
    public final int mo5804d() {
        return this.f9733a;
    }

    /* renamed from: e */
    public final boolean mo5805e() {
        return this.f9737e;
    }

    /* renamed from: f */
    public final void mo5807f() {
    }

    /* renamed from: g */
    public final void mo5808g() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fmm)) {
            return false;
        }
        fmm fmm = (fmm) obj;
        iel iel = this.f9734b;
        if (iel == null ? fmm.mo5801a() == null : iel.equals(fmm.mo5801a())) {
            if (this.f9735c == fmm.mo5802b() && this.f9736d == fmm.mo5803c() && this.f9733a == fmm.mo5804d()) {
                fmm.mo5808g();
                fmm.mo5807f();
                if (this.f9737e == fmm.mo5805e()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        iel iel = this.f9734b;
        return (((((((((iel != null ? iel.hashCode() : 0) ^ 1000003) * 1000003) ^ this.f9735c) * 1000003) ^ this.f9736d) * 1000003) ^ this.f9733a) * 583896283) ^ (!this.f9737e ? 1237 : 1231);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f9734b);
        int i = this.f9735c;
        int i2 = this.f9736d;
        int i3 = this.f9733a;
        String valueOf2 = String.valueOf((Object) null);
        String valueOf3 = String.valueOf((Object) null);
        boolean z = this.f9737e;
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 247 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("PrimesThreadsConfigurations{primesExecutorService=");
        sb.append(valueOf);
        sb.append(", primesInitializationPriority=");
        sb.append(i);
        sb.append(", primesMetricExecutorPriority=");
        sb.append(i2);
        sb.append(", primesMetricExecutorPoolSize=");
        sb.append(i3);
        sb.append(", initAfterResumed=");
        sb.append(valueOf2);
        sb.append(", activityResumedCallback=");
        sb.append(valueOf3);
        sb.append(", enableEarlyTimers=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
