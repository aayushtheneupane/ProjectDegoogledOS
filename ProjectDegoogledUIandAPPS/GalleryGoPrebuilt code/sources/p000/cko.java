package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cko */
/* compiled from: PG */
final /* synthetic */ class cko implements Consumer {

    /* renamed from: a */
    private final cks f4577a;

    public cko(cks cks) {
        this.f4577a = cks;
    }

    public final void accept(Object obj) {
        cks cks = this.f4577a;
        if (!((Boolean) obj).booleanValue()) {
            cks.f4582b.mo2572a((int) R.string.folder_creation_move_no_permission);
        } else {
            cks.mo3216a(1);
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
