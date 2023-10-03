package p000;

/* renamed from: elf */
/* compiled from: PG */
public final class elf extends UnsupportedOperationException {

    /* renamed from: a */
    private final ejt f8505a;

    public elf(ejt ejt) {
        this.f8505a = ejt;
    }

    public final String getMessage() {
        String valueOf = String.valueOf(this.f8505a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
        sb.append("Missing ");
        sb.append(valueOf);
        return sb.toString();
    }
}
