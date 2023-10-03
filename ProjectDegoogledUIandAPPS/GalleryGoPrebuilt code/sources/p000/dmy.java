package p000;

import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.OneUpActionsView;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dmy */
/* compiled from: PG */
final /* synthetic */ class dmy implements Consumer {

    /* renamed from: a */
    private final dnn f6854a;

    public dmy(dnn dnn) {
        this.f6854a = dnn;
    }

    public final void accept(Object obj) {
        dnn dnn = this.f6854a;
        cxi cxi = (cxi) obj;
        OneUpActionsView oneUpActionsView = dnn.f6880J;
        if (oneUpActionsView.f4878b.getVisibility() != 0 || !oneUpActionsView.f4878b.isActivated()) {
            dnn.f6904m.mo2572a((int) R.string.oneup_not_enough_memory_to_auto);
            return;
        }
        cxh cxh = cxh.IMAGE;
        cxh a = cxh.m5592a(cxi.f5913e);
        if (a == null) {
            a = cxh.UNKNOWN_MEDIA_TYPE;
        }
        if (cxh.equals(a)) {
            dnn.f6898g.mo3272a(new dmp(cxi), "auto_enhance", cnf.NONE);
            return;
        }
        Object[] objArr = new Object[1];
        cxh a2 = cxh.m5592a(cxi.f5913e);
        if (a2 == null) {
            a2 = cxh.UNKNOWN_MEDIA_TYPE;
        }
        objArr[0] = Integer.valueOf(a2.f5906d);
        cwn.m5510a("OneUpFragment: Unexpected media type[%s] in auto-enhance.", objArr);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
