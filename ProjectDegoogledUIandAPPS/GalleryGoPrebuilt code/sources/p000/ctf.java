package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: ctf */
/* compiled from: PG */
final /* synthetic */ class ctf implements Function {

    /* renamed from: a */
    private final ctk f5624a;

    public ctf(ctk ctk) {
        this.f5624a = ctk;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return this.f5624a.f5629a.getString(R.string.info_sheet_fnumber_format, new Object[]{(Double) obj});
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
