package p000;

/* renamed from: se */
/* compiled from: PG */
public final class C0497se implements C0345mo {

    /* renamed from: a */
    private boolean f15856a = false;

    /* renamed from: b */
    private int f15857b;

    /* renamed from: c */
    private final /* synthetic */ C0498sf f15858c;

    protected C0497se(C0498sf sfVar) {
        this.f15858c = sfVar;
    }

    /* renamed from: a */
    public final void mo9405a() {
        this.f15856a = true;
    }

    /* renamed from: b */
    public final void mo9406b() {
        if (!this.f15856a) {
            C0498sf sfVar = this.f15858c;
            sfVar.f15863e = null;
            C0497se.super.setVisibility(this.f15857b);
        }
    }

    /* renamed from: c */
    public final void mo9407c() {
        C0497se.super.setVisibility(0);
        this.f15856a = false;
    }

    /* renamed from: a */
    public final void mo10049a(C0344mn mnVar, int i) {
        this.f15858c.f15863e = mnVar;
        this.f15857b = i;
    }
}
