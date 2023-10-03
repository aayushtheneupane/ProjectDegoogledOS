package p000;

/* renamed from: guw */
/* compiled from: PG */
final class guw {

    /* renamed from: a */
    public static final guw f12099a = new guw(guu.LOCAL_STATE_CHANGE);

    /* renamed from: b */
    public static final guw f12100b = new guw(guu.REMOTE_STATE_CHANGE);

    /* renamed from: c */
    public final guu f12101c;

    private guw(guu guu) {
        this.f12101c = guu;
    }

    public final String toString() {
        return String.format("ResultPropagator.Update for CallReason %s", new Object[]{this.f12101c});
    }
}
