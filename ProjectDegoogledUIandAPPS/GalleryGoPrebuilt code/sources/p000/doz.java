package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: doz */
/* compiled from: PG */
final /* synthetic */ class doz implements Consumer {

    /* renamed from: a */
    private final dpt f6974a;

    public doz(dpt dpt) {
        this.f6974a = dpt;
    }

    public final void accept(Object obj) {
        dpt dpt = this.f6974a;
        Void voidR = (Void) obj;
        dpt.f7012d.mo2572a((int) R.string.auto_enhance_replace_success);
        dpt.mo4329c();
        dpt.f7011c.mo3274e();
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
