package p000;

/* renamed from: jj */
/* compiled from: PG */
public final class C0259jj {

    /* renamed from: a */
    public boolean f15079a;

    /* renamed from: b */
    public C0258ji f15080b;

    /* renamed from: c */
    public boolean f15081c;

    /* renamed from: a */
    public final void mo9160a(C0258ji jiVar) {
        synchronized (this) {
            while (this.f15081c) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            if (this.f15080b != jiVar) {
                this.f15080b = jiVar;
                if (this.f15079a) {
                    jiVar.mo279a();
                }
            }
        }
    }
}
