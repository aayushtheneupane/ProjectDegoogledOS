package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cth */
/* compiled from: PG */
final /* synthetic */ class cth implements Function {

    /* renamed from: a */
    private final ctk f5626a;

    public cth(ctk ctk) {
        this.f5626a = ctk;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return this.f5626a.f5629a.getString(R.string.info_sheet_focal_length_format, new Object[]{(Double) obj});
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
