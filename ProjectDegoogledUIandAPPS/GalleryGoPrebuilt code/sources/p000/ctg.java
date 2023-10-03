package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: ctg */
/* compiled from: PG */
final /* synthetic */ class ctg implements Function {

    /* renamed from: a */
    private final ctk f5625a;

    public ctg(ctk ctk) {
        this.f5625a = ctk;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return this.f5625a.f5629a.getString(R.string.info_sheet_exposure_time_format, new Object[]{Double.valueOf(1.0d / ((Double) obj).doubleValue())});
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
