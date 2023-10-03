package p000;

/* renamed from: qn */
/* compiled from: PG */
final class C0452qn extends C0346mp {

    /* renamed from: a */
    private boolean f15662a = false;

    /* renamed from: b */
    private int f15663b = 0;

    /* renamed from: c */
    private final /* synthetic */ C0453qo f15664c;

    public C0452qn(C0453qo qoVar) {
        this.f15664c = qoVar;
    }

    /* renamed from: b */
    public final void mo9406b() {
        int i = this.f15663b + 1;
        this.f15663b = i;
        if (i == this.f15664c.f15665a.size()) {
            C0345mo moVar = this.f15664c.f15666b;
            if (moVar != null) {
                moVar.mo9406b();
            }
            this.f15663b = 0;
            this.f15662a = false;
            this.f15664c.f15667c = false;
        }
    }

    /* renamed from: c */
    public final void mo9407c() {
        if (!this.f15662a) {
            this.f15662a = true;
            C0345mo moVar = this.f15664c.f15666b;
            if (moVar != null) {
                moVar.mo9407c();
            }
        }
    }
}
