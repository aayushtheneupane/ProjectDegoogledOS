package p000;

import com.google.android.apps.photosgo.R;
import java.util.List;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cmp */
/* compiled from: PG */
final /* synthetic */ class cmp implements Consumer {

    /* renamed from: a */
    private final cmv f4694a;

    public cmp(cmv cmv) {
        this.f4694a = cmv;
    }

    public final void accept(Object obj) {
        cmv cmv = this.f4694a;
        List list = (List) obj;
        cmv.mo3264c();
        if (list.isEmpty()) {
            cwn.m5514b("PickFolderToAddItems: Unable to add any items into folder.", new Object[0]);
            if (cmv.f4700a.f4692b) {
                cmv.f4703d.mo2572a((int) R.string.pick_folder_to_move_folder_not_writable);
            } else {
                cmv.f4703d.mo2572a((int) R.string.pick_folder_to_copy_folder_not_writable);
            }
        } else {
            cnh cnh = cmv.f4702c;
            cxe cxe = ((cxi) ((imh) list.get(0)).mo8995a(cxi.f5908x, cmv.f4706g)).f5919k;
            if (cxe == null) {
                cxe = cxe.f5893f;
            }
            cnh.mo3270a(cjw.m4432a(cxe));
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
