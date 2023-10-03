package p000;

import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.OneUpActionsView;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmx */
/* compiled from: PG */
final /* synthetic */ class dmx implements Consumer {

    /* renamed from: a */
    private final dnn f6853a;

    public dmx(dnn dnn) {
        this.f6853a = dnn;
    }

    public final void accept(Object obj) {
        dnn dnn = this.f6853a;
        cxi cxi = (cxi) obj;
        OneUpActionsView oneUpActionsView = dnn.f6880J;
        if (oneUpActionsView.f4879c.getVisibility() == 0 && oneUpActionsView.f4879c.isActivated()) {
            dmd dmd = dmd.UNKNOWN;
            cxh cxh = cxh.UNKNOWN_MEDIA_TYPE;
            cxh a = cxh.m5592a(cxi.f5913e);
            if (a == null) {
                a = cxh.UNKNOWN_MEDIA_TYPE;
            }
            int ordinal = a.ordinal();
            if (ordinal == 0) {
                dnn.f6904m.mo2572a((int) R.string.feature_not_implemented);
            } else if (ordinal == 1) {
                dnn.f6898g.mo3272a(new dmr(cxi), "photo_editor", cnf.NONE);
            } else if (ordinal == 2) {
                dnn.f6898g.mo3272a(new dms(cxi), "video_editor", cnf.NONE);
            }
        } else {
            dnn.f6904m.mo2572a((int) R.string.oneup_not_enough_memory_to_edit);
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
