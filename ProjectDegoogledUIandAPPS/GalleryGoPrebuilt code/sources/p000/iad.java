package p000;

/* renamed from: iad */
/* compiled from: PG */
public final class iad extends iag {

    /* renamed from: a */
    private final Throwable f13713a;

    private iad(Throwable th) {
        super((byte[]) null);
        this.f13713a = th;
    }

    /* renamed from: a */
    public final boolean mo8319a() {
        return false;
    }

    /* renamed from: b */
    public final Throwable mo8320b() {
        return this.f13713a;
    }

    /* renamed from: a */
    public static final iad m12566a(Throwable th) {
        return new iad(th);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13713a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
        sb.append("FailedTaskResult[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
