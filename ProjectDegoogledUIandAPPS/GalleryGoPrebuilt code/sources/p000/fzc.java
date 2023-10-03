package p000;

import android.net.Uri;
import java.io.IOException;
import java.util.concurrent.Future;

/* renamed from: fzc */
/* compiled from: PG */
final /* synthetic */ class fzc implements ice {

    /* renamed from: a */
    private final fzg f10715a;

    public fzc(fzg fzg) {
        this.f10715a = fzg;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        fzg fzg = this.f10715a;
        Uri uri = (Uri) ife.m12871b((Future) fzg.f10723b);
        fzg.mo6354a(uri, (Uri) ife.m12871b((Future) fzg.f10724c));
        try {
            return ife.m12820a((Object) fzg.mo6353a(uri));
        } catch (IOException e) {
            return ibv.m12658a(fzg.f10727f.mo6349a(e), hmq.m11744a((icf) new fzf(fzg)), fzg.f10725d);
        }
    }
}
