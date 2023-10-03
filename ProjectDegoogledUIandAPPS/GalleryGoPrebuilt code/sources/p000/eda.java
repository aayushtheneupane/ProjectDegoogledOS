package p000;

import android.net.Uri;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: eda */
/* compiled from: PG */
final /* synthetic */ class eda implements Consumer {

    /* renamed from: a */
    private final hsj f8013a;

    public eda(hsj hsj) {
        this.f8013a = hsj;
    }

    public final void accept(Object obj) {
        this.f8013a.mo7908c((Uri) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
