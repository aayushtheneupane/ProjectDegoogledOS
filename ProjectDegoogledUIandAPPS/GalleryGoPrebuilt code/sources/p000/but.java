package p000;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import p003j$.util.Optional;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: but */
/* compiled from: PG */
final /* synthetic */ class but implements Consumer {

    /* renamed from: a */
    private final bvv f3646a;

    public but(bvv bvv) {
        this.f3646a = bvv;
    }

    public final void accept(Object obj) {
        grw grw;
        cxi cxi;
        ieh ieh;
        bvv bvv = this.f3646a;
        dbn a = dbn.m5855a((Bitmap) obj, bvv.f3739u);
        bul bul = bvv.f3720b;
        if ((bul.f3630a & 8) == 0 || bul.f3634e.isEmpty()) {
            int b = ggf.m10254b(bvv.f3720b.f3631b);
            int i = b - 1;
            if (b == 0) {
                throw null;
            } else if (i == 0) {
                dbs dbs = bvv.f3732n;
                bul bul2 = bvv.f3720b;
                if (bul2.f3631b == 1) {
                    cxi = (cxi) bul2.f3632c;
                } else {
                    cxi = cxi.f5908x;
                }
                grw = grw.m10689d(dbs.mo4038a(cyc.m5648b(cxi), a));
            } else if (i != 1) {
                bvv.mo2805a((Throwable) new IllegalStateException(String.format("Unsupported typecase: %s", new Object[]{ggf.m10251a(ggf.m10254b(bvv.f3720b.f3631b))})));
                return;
            } else {
                try {
                    grw = grw.m10689d(bvv.f3732n.mo4040a(Optional.empty(), a.mo3208e(), a, true));
                } catch (IOException e) {
                    bvv.mo2805a((Throwable) e);
                    return;
                }
            }
        } else {
            dbs dbs2 = bvv.f3732n;
            String str = bvv.f3720b.f3634e;
            Uri parse = Uri.parse(str);
            if ("content".equals(parse.getScheme())) {
                ieh = dbs2.mo4039a(Optional.empty(), parse, a, false, false, true, "save as for content URI");
            } else if ("file".equals(parse.getScheme())) {
                ieh = dbs2.mo4039a(Optional.empty(), parse, a, true, false, true, "save as for file URI");
            } else if (parse.getScheme() != null || parse.getPath() == null) {
                String valueOf = String.valueOf(str);
                ieh = ife.m12822a((Throwable) new IOException(valueOf.length() == 0 ? new String("Cannot save to path with unknown format: ") : "Cannot save to path with unknown format: ".concat(valueOf)));
            } else {
                ieh = dbs2.mo4040a(Optional.empty(), (String) ife.m12898e((Object) parse.getPath()), a, true);
            }
            grw = grw.m10689d(ieh);
        }
        bvv.f3736r.mo6987a(grw, bvv.f3701N);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
