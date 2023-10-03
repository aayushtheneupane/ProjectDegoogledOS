package p000;

import com.google.android.apps.photosgo.R;
import java.io.IOException;
import p003j$.util.Optional;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpb */
/* compiled from: PG */
final /* synthetic */ class dpb implements Consumer {

    /* renamed from: a */
    private final dpt f6981a;

    public dpb(dpt dpt) {
        this.f6981a = dpt;
    }

    public final void accept(Object obj) {
        String str;
        dpt dpt = this.f6981a;
        if (((Boolean) obj).booleanValue()) {
            iir g = eai.f7772e.mo8793g();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eai eai = (eai) g.f14318b;
            eai.f7774a |= 1;
            eai.f7775b = R.string.progress_saving_changes;
            eaj a = eaj.m7021a((eai) g.mo8770g());
            a.mo5429b(dpt.f7009a.mo5659r(), "progress_fragment");
            dpt.f7033y = Optional.m16285of(a);
            apj a2 = dpt.f7015g.mo7307a();
            cxi cxi = dpt.f7010b;
            if (cxi.f5912d) {
                str = ebh.m7086a(cxi.f5910b).mo4663d();
            } else {
                str = cxi.f5910b;
            }
            ((apj) ((apj) a2.mo1420a(str).mo1854a((aqu) new bfa(Long.valueOf(dpt.f7010b.f5918j)))).mo1426b(dpt.f7021m.mo3300d()).mo1856a((ard) new bar(-dpt.f7010b.f5925q))).mo1421a(hnr.m11806a(dpt.f7006H));
            return;
        }
        dpt.mo4327a(new IOException("Storage permission is not granted"));
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
