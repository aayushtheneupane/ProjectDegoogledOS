package p000;

/* renamed from: ah */
/* compiled from: PG */
abstract class C0008ah {

    /* renamed from: a */
    public final C0011ak f466a;

    /* renamed from: b */
    public boolean f467b;

    /* renamed from: c */
    public int f468c = -1;

    /* renamed from: d */
    private final /* synthetic */ C0009ai f469d;

    public C0008ah(C0009ai aiVar, C0011ak akVar) {
        this.f469d = aiVar;
        this.f466a = akVar;
    }

    /* renamed from: a */
    public abstract boolean mo340a();

    /* renamed from: a */
    public boolean mo341a(C0681z zVar) {
        throw null;
    }

    /* renamed from: b */
    public void mo342b() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo456a(boolean z) {
        int i;
        if (z != this.f467b) {
            this.f467b = z;
            C0009ai aiVar = this.f469d;
            int i2 = aiVar.f521c;
            if (!z) {
                i = -1;
            } else {
                i = 1;
            }
            aiVar.f521c = i + i2;
            if (i2 == 0 && z) {
                aiVar.mo509a();
            }
            C0009ai aiVar2 = this.f469d;
            if (aiVar2.f521c == 0 && !this.f467b) {
                aiVar2.mo514b();
            }
            if (this.f467b) {
                this.f469d.mo510a(this);
            }
        }
    }
}
