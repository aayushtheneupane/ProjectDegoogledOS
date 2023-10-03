package p000;

/* renamed from: fge */
/* compiled from: PG */
public final class fge {

    /* renamed from: a */
    private final fee f9505a;

    public fge(fee fee) {
        this.f9505a = fee;
    }

    /* renamed from: a */
    public final fgc mo5581a(C0140fa faVar) {
        boolean z;
        if (faVar.mo5ad().mo61a() == C0573v.INITIALIZED) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "Must be called in onCreate");
        return new fgc(this.f9505a, faVar);
    }
}
