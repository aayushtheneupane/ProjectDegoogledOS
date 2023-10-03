package p000;

/* renamed from: cnv */
/* compiled from: PG */
final class cnv implements bee {

    /* renamed from: a */
    private final iev f4746a;

    public cnv(iev iev) {
        this.f4746a = iev;
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        super.finalize();
        if (!this.f4746a.isDone()) {
            this.f4746a.mo6952a((Throwable) new IllegalStateException("Wrong Glide instance was used."));
        }
    }

    /* renamed from: a */
    public final boolean mo1486a(atu atu) {
        if (atu == null) {
            this.f4746a.mo6952a((Throwable) new RuntimeException("Glide load failed with no exception"));
            return true;
        }
        this.f4746a.mo6952a((Throwable) atu);
        return true;
    }

    /* renamed from: a */
    public final boolean mo1487a(Object obj) {
        try {
            this.f4746a.mo8346b(obj);
            return true;
        } catch (Throwable th) {
            this.f4746a.mo6952a(th);
            return true;
        }
    }
}
