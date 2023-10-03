package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cif */
/* compiled from: PG */
public final /* synthetic */ class cif implements Function {

    /* renamed from: a */
    public static final Function f4454a = new cif();

    private cif() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        igw igw = (igw) obj;
        iir iir = (iir) igw.mo8790b(5);
        iir.mo8503a((iix) igw);
        return iir;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
