package p000;

import android.view.View;
import com.google.android.apps.photosgo.foldermanagement.creation.SingleVolumeChooserView;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: clx */
/* compiled from: PG */
final /* synthetic */ class clx implements Consumer {

    /* renamed from: a */
    private final SingleVolumeChooserView f4654a;

    public clx(SingleVolumeChooserView singleVolumeChooserView) {
        this.f4654a = singleVolumeChooserView;
    }

    public final void accept(Object obj) {
        cjv cjv = (cjv) obj;
        ihg.m13039a((hoi) new ckw(cjv.mo3177b(), cjv.mo3178c()), (View) this.f4654a);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
