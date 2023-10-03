package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* renamed from: fco */
/* compiled from: PG */
final /* synthetic */ class fco implements Callable {

    /* renamed from: a */
    private final List f9272a;

    public fco(List list) {
        this.f9272a = list;
    }

    public final Object call() {
        List list = this.f9272a;
        int size = list.size();
        fcm fcm = null;
        for (int i = 0; i < size; i++) {
            fcm fcm2 = (fcm) ife.m12871b((Future) (ieh) list.get(i));
            if (fcm2 != null) {
                ife.m12876b(fcm == null, (Object) "More than one auth provider provided result.");
                fcm = fcm2;
            }
        }
        if (fcm != null) {
            return fcm;
        }
        throw new UnsupportedOperationException("Unknown LogAuthSpec");
    }
}
