package p000;

import com.google.android.apps.photosgo.R;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiConsumer$$CC;

/* renamed from: cms */
/* compiled from: PG */
final /* synthetic */ class cms implements BiConsumer {

    /* renamed from: a */
    private final cmv f4697a;

    public cms(cmv cmv) {
        this.f4697a = cmv;
    }

    public final void accept(Object obj, Object obj2) {
        cmv cmv = this.f4697a;
        String str = (String) obj;
        cmv.f4711l = false;
        cmv.f4703d.mo2572a((int) R.string.folder_creation_permission_error);
        cwn.m5515b((Throwable) obj2, "PickFolderToAddItems: permissions error", new Object[0]);
    }

    public final BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$$CC.andThen$$dflt$$(this, biConsumer);
    }
}
