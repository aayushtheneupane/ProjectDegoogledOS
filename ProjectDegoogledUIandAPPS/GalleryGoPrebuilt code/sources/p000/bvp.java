package p000;

import java.util.List;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvp */
/* compiled from: PG */
final /* synthetic */ class bvp implements Consumer {

    /* renamed from: a */
    private final bvv f3681a;

    public bvp(bvv bvv) {
        this.f3681a = bvv;
    }

    public final void accept(Object obj) {
        bvv bvv = this.f3681a;
        Void voidR = (Void) obj;
        byc byc = (byc) bvv.f3726h;
        boolean z = false;
        if (!byc.f3890e) {
            byc.f3890e = true;
            List list = byc.f3887b;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                bya bya = (bya) list.get(i);
                for (byb a : bya.f3882a) {
                    byc.mo2901a(a);
                }
                bya.f3883b.run();
            }
            byc.f3887b.clear();
            for (byb a2 : byc.f3886a) {
                byc.mo2901a(a2);
            }
            byc.f3886a.clear();
        }
        bvv.f3690C = true;
        bvv.f3698K.mo2817a();
        bvv.f3698K.mo2818a(((byc) bvv.f3727i).f3888c);
        bvv.f3710W.setVisibility(8);
        if (bvv.f3691D || bvv.f3720b.f3637h) {
            z = true;
        }
        bvv.mo2809b(z);
        bvv.f3726h.mo2884a();
        bvv.mo2806a(!bvv.f3696I);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
