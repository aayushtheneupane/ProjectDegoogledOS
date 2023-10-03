package p000;

/* renamed from: ahl */
/* compiled from: PG */
public final class ahl extends cdu {

    /* renamed from: a */
    public final Throwable f493a;

    public ahl(Throwable th) {
        this.f493a = th;
    }

    public final String toString() {
        return String.format("FAILURE (%s)", new Object[]{this.f493a.getMessage()});
    }
}
