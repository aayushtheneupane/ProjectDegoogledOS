package p000;

/* renamed from: iae */
/* compiled from: PG */
public final class iae extends iag {

    /* renamed from: a */
    public final Object f13714a;

    private iae(Object obj) {
        super((byte[]) null);
        this.f13714a = obj;
    }

    /* renamed from: a */
    public final boolean mo8319a() {
        return true;
    }

    /* renamed from: a */
    public static final iae m12569a(Object obj) {
        return new iae(obj);
    }

    /* renamed from: b */
    public final Throwable mo8320b() {
        throw new IllegalStateException("Successful future does not have a failure.");
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13714a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("SuccessfulTaskResult[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
