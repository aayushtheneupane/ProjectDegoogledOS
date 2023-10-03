package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* renamed from: gnp */
/* compiled from: PG */
final /* synthetic */ class gnp implements Callable {

    /* renamed from: a */
    private final List f11698a;

    public gnp(List list) {
        this.f11698a = list;
    }

    public final Object call() {
        List list = this.f11698a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ife.m12871b((Future) (ieh) list.get(i));
        }
        return null;
    }
}
