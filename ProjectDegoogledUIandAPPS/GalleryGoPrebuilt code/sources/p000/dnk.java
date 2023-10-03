package p000;

import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import p003j$.util.Optional;

/* renamed from: dnk */
/* compiled from: PG */
final class dnk implements gry {

    /* renamed from: a */
    private final /* synthetic */ dnn f6867a;

    public dnk(dnn dnn) {
        this.f6867a = dnn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        Void voidR = (Void) obj;
        this.f6867a.f6904m.mo2572a((int) R.string.oneup_burst_set_primary_fail);
        cwn.m5515b(th, "OneUpFragment: set primary failed", new Object[0]);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2649a(Object obj) {
        Void voidR = (Void) obj;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2650a(Object obj, Object obj2) {
        Void voidR = (Void) obj;
        Void voidR2 = (Void) obj2;
        this.f6867a.f6893b = Optional.empty();
        dnn dnn = this.f6867a;
        dog dog = dnn.f6878H;
        cxi cxi = (cxi) dnn.f6884N.get();
        cxi cxi2 = (cxi) this.f6867a.f6885O.get();
        if (bmf.m3230a(((dls) dog.f6934a.mo4634a().get()).f6796a).equals(bmf.m3230a(cxi))) {
            eav eav = dog.f6934a;
            ArrayList arrayList = new ArrayList();
            ije ije = cxi.f5929u;
            int size = ije.size();
            for (int i = 0; i < size; i++) {
                cxi cxi3 = (cxi) ije.get(i);
                if (cyc.m5645a(cxi3, cxi)) {
                    iir iir = (iir) cxi3.mo8790b(5);
                    iir.mo8503a((iix) cxi3);
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    cxi cxi4 = (cxi) iir.f14318b;
                    cxi cxi5 = cxi.f5908x;
                    cxi4.f5928t = 1;
                    cxi4.f5909a = 262144 | cxi4.f5909a;
                    arrayList.add((cxi) iir.mo8770g());
                } else if (!cyc.m5645a(cxi3, cxi2)) {
                    arrayList.add(cxi3);
                } else {
                    iir iir2 = (iir) cxi3.mo8790b(5);
                    iir2.mo8503a((iix) cxi3);
                    if (iir2.f14319c) {
                        iir2.mo8751b();
                        iir2.f14319c = false;
                    }
                    cxi cxi6 = (cxi) iir2.f14318b;
                    cxi cxi7 = cxi.f5908x;
                    cxi6.f5928t = 4;
                    cxi6.f5909a |= 262144;
                    arrayList.add((cxi) iir2.mo8770g());
                }
            }
            iir iir3 = (iir) cxi2.mo8790b(5);
            iir3.mo8503a((iix) cxi2);
            if (iir3.f14319c) {
                iir3.mo8751b();
                iir3.f14319c = false;
            }
            cxi cxi8 = (cxi) iir3.f14318b;
            cxi cxi9 = cxi.f5908x;
            cxi8.f5928t = 4;
            cxi8.f5909a |= 262144;
            iir3.mo8749a((Iterable) arrayList);
            long j = cxi.f5920l;
            if (iir3.f14319c) {
                iir3.mo8751b();
                iir3.f14319c = false;
            }
            cxi cxi10 = (cxi) iir3.f14318b;
            cxi10.f5909a |= 1024;
            cxi10.f5920l = j;
            eav.mo4637a((eaq) dog.mo4295a((cxi) iir3.mo8770g(), (dik) null));
        }
    }
}
