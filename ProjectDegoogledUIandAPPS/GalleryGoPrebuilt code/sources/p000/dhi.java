package p000;

import android.net.Uri;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dhi */
/* compiled from: PG */
final /* synthetic */ class dhi implements Function {

    /* renamed from: a */
    public static final Function f6544a = new dhi();

    private dhi() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        dik dik = (dik) obj;
        if ((dik.f6609a & 16) == 0 || dik.f6614f != bis.EDIT.ordinal()) {
            return null;
        }
        dhp d = dhq.m6122d();
        d.mo4144a(Uri.parse(dik.f6613e));
        dii dii = dik.f6615g;
        if (dii == null) {
            dii = dii.f6597d;
        }
        d.mo4146b(dii.f6601c);
        dii dii2 = dik.f6615g;
        if (dii2 == null) {
            dii2 = dii.f6597d;
        }
        d.mo4145a(dii2.f6600b);
        return d.mo4143a();
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
