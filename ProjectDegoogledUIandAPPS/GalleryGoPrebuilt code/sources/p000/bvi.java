package p000;

import com.google.android.apps.photosgo.R;
import java.util.List;
import p003j$.util.Optional;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvi */
/* compiled from: PG */
final /* synthetic */ class bvi implements Consumer {

    /* renamed from: a */
    private final bvv f3674a;

    public bvi(bvv bvv) {
        this.f3674a = bvv;
    }

    public final void accept(Object obj) {
        Optional optional;
        bvv bvv = this.f3674a;
        List list = (List) obj;
        bvv.mo2814g();
        int size = list.size();
        if (size > 1) {
            cwn.m5514b("Invalid saved media list size %d in editor", Integer.valueOf(size));
        }
        if (size != 0) {
            optional = Optional.m16285of((cxi) ((imh) list.get(0)).mo8995a(cxi.f5908x, iij.m13502b()));
        } else {
            optional = Optional.empty();
        }
        buj buj = new buj(true, optional);
        bul bul = bvv.f3720b;
        if ((bul.f3630a & 4) != 0 && bul.f3633d) {
            ihg.m13041a((hoi) buj, (C0147fh) bvv.f3722d);
            return;
        }
        bvv.f3724f.mo2572a((int) R.string.editor_save_success);
        if (optional.isPresent()) {
            cxi cxi = (cxi) optional.get();
            ((apj) bvv.f3723e.mo7307a().mo1420a(cxi.f5910b).mo1426b(bvv.f3740v.mo3299c()).mo1854a((aqu) new bfa(Long.valueOf(cxi.f5918j)))).mo1421a(hnr.m11806a((ber) new bvr(bvv, buj)));
            return;
        }
        bvv.f3721c.mo3270a(buj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
