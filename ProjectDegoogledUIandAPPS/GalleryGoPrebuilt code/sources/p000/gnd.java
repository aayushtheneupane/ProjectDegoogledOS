package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* renamed from: gnd */
/* compiled from: PG */
final /* synthetic */ class gnd implements Callable {

    /* renamed from: a */
    private final gng f11668a;

    /* renamed from: b */
    private final gna f11669b;

    /* renamed from: c */
    private final List f11670c;

    public gnd(gng gng, gna gna, List list) {
        this.f11668a = gng;
        this.f11669b = gna;
        this.f11670c = list;
    }

    public final Object call() {
        gng gng = this.f11668a;
        gna gna = this.f11669b;
        List list = this.f11670c;
        iir iir = (iir) gng.mo8790b(5);
        iir.mo8503a((iix) gng);
        int a = gna.mo6855a();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        gng gng2 = (gng) iir.f14318b;
        gng gng3 = gng.f11674d;
        gng2.f11676a |= 1;
        gng2.f11677b = a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            gnj gnj = (gnj) ife.m12871b((Future) (ieh) list.get(i));
            iir.mo8744a(gnj.f11686b, gnj);
        }
        return (gng) iir.mo8770g();
    }
}
