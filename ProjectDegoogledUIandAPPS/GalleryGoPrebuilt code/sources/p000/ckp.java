package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: ckp */
/* compiled from: PG */
final /* synthetic */ class ckp implements Consumer {

    /* renamed from: a */
    private final cks f4578a;

    public ckp(cks cks) {
        this.f4578a = cks;
    }

    public final void accept(Object obj) {
        this.f4578a.f4582b.mo2572a((int) R.string.folder_creation_permission_error);
        cwn.m5515b((Throwable) obj, "AddItemsToFolder: permissions error", new Object[0]);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
