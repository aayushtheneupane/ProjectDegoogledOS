package p000;

/* renamed from: alp */
/* compiled from: PG */
final class alp extends C0059cc {
    public alp(C0053bx bxVar) {
        super(bxVar);
    }

    /* renamed from: a */
    public final String mo578a() {
        return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
    }
}
