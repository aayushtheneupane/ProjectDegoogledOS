package p000;

import android.net.Uri;
import p003j$.util.Optional;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: ceu */
/* compiled from: PG */
final /* synthetic */ class ceu implements Function {

    /* renamed from: a */
    private final Uri f4211a;

    /* renamed from: b */
    private final Optional f4212b;

    public ceu(Uri uri, Optional optional) {
        this.f4211a = uri;
        this.f4212b = optional;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        Uri uri = this.f4211a;
        Optional optional = this.f4212b;
        cxi cxi = (cxi) obj;
        Object[] objArr = {uri, optional.get()};
        iir iir = (iir) cxi.mo8790b(5);
        iir.mo8503a((iix) cxi);
        cxh cxh = cxh.IMAGE;
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        cxi cxi2 = (cxi) iir.f14318b;
        cxi cxi3 = cxi.f5908x;
        cxi2.f5913e = cxh.f5906d;
        int i = cxi2.f5909a | 8;
        cxi2.f5909a = i;
        "image/jpeg".getClass();
        cxi2.f5909a = i | 16;
        cxi2.f5914f = "image/jpeg";
        String str = (String) optional.get();
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        cxi cxi4 = (cxi) iir.f14318b;
        str.getClass();
        cxi4.f5909a |= 1048576;
        cxi4.f5930v = str;
        return (cxi) iir.mo8770g();
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
