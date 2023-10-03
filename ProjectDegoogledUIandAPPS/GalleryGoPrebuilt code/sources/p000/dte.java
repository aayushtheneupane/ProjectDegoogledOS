package p000;

import com.google.android.apps.photosgo.oneup.photo.PhotoView;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dte */
/* compiled from: PG */
final /* synthetic */ class dte implements Consumer {

    /* renamed from: a */
    public static final Consumer f7315a = new dte();

    private dte() {
    }

    public final void accept(Object obj) {
        ((PhotoView) obj).setVisibility(8);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
