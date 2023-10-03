package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cmq */
/* compiled from: PG */
final /* synthetic */ class cmq implements Consumer {

    /* renamed from: a */
    private final cmv f4695a;

    public cmq(cmv cmv) {
        this.f4695a = cmv;
    }

    public final void accept(Object obj) {
        cmv cmv = this.f4695a;
        Throwable th = (Throwable) obj;
        cmv.mo3264c();
        cwn.m5515b(th, "PickFolderToAddItems: Unable to add items into folder.", new Object[0]);
        if (C0643xp.m15943a(th)) {
            cmv.f4703d.mo2572a((int) R.string.low_storage_error);
        } else if (cmv.f4700a.f4692b) {
            cmv.f4703d.mo2572a((int) R.string.pick_folder_to_move_folder_not_writable);
        } else {
            cmv.f4703d.mo2572a((int) R.string.pick_folder_to_copy_folder_not_writable);
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
