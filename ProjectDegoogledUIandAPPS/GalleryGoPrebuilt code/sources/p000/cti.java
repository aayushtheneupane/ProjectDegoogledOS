package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cti */
/* compiled from: PG */
final /* synthetic */ class cti implements Function {

    /* renamed from: a */
    private final ctk f5627a;

    public cti(ctk ctk) {
        this.f5627a = ctk;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return this.f5627a.f5629a.getString(R.string.info_sheet_iso_format, new Object[]{(Integer) obj});
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
