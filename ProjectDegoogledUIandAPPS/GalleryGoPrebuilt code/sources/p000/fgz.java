package p000;

/* renamed from: fgz */
/* compiled from: PG */
final /* synthetic */ class fgz implements Runnable {

    /* renamed from: a */
    private final fhc f9560a;

    public fgz(fhc fhc) {
        this.f9560a = fhc;
    }

    public final void run() {
        fhc fhc = this.f9560a;
        fhc.mo5670d();
        fhc.f9618d = 0.0f;
        fhc.f9617c = (fhc.f9617c + 216.0f) % 360.0f;
        int b = fhc.mo5668b();
        fhc.f9619e = b;
        int[] iArr = fhc.f9621g;
        int i = iArr[b];
        fhc.f9620f = i;
        fhc.f9616b.setIntValues(new int[]{i, iArr[fhc.mo5668b()]});
    }
}
