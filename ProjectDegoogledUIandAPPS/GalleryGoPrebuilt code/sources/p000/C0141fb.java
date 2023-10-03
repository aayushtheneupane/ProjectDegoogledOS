package p000;

import android.os.Looper;

/* renamed from: fb */
/* compiled from: PG */
final class C0141fb implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0147fh f9250a;

    public C0141fb(C0147fh fhVar) {
        this.f9250a = fhVar;
    }

    public final void run() {
        C0147fh fhVar = this.f9250a;
        C0171gd gdVar = fhVar.f9604w;
        if (gdVar == null || gdVar.f10991j == null) {
            fhVar.mo5622C().f9431i = false;
        } else if (Looper.myLooper() != fhVar.f9604w.f10991j.f10594d.getLooper()) {
            fhVar.f9604w.f10991j.f10594d.postAtFrontOfQueue(new C0143fd(fhVar));
        } else {
            fhVar.mo5621B();
        }
    }
}
