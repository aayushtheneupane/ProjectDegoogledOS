package p000;

import android.graphics.Bitmap;
import java.util.concurrent.Callable;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bur */
/* compiled from: PG */
final /* synthetic */ class bur implements Consumer {

    /* renamed from: a */
    private final bvv f3644a;

    public bur(bvv bvv) {
        this.f3644a = bvv;
    }

    public final void accept(Object obj) {
        grw grw;
        ieh ieh;
        bvv bvv = this.f3644a;
        Bitmap bitmap = (Bitmap) obj;
        bul bul = bvv.f3720b;
        if ((bul.f3630a & 16) != 0) {
            bwr bwr = bvv.f3734p;
            bwy bwy = bul.f3635f;
            if (bwy == null) {
                bwy = bwy.f3795d;
            }
            if (bwy.f3798b > 0 && bwy.f3799c > 0) {
                ieh = bwr.f3776a.mo5933a(hmq.m11749a((Callable) new bwq(bwr, bitmap, bwy)));
            } else {
                ieh = ife.m12820a((Object) bitmap);
            }
            grw = grw.m10686a(ieh);
        } else {
            grw = grw.m10686a(ife.m12820a((Object) bitmap));
        }
        bvv.f3736r.mo6987a(grw, bvv.f3708U);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
