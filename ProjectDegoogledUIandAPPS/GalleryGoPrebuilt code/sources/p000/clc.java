package p000;

import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiConsumer$$CC;

/* renamed from: clc */
/* compiled from: PG */
final /* synthetic */ class clc implements BiConsumer {

    /* renamed from: a */
    private final clr f4603a;

    public clc(clr clr) {
        this.f4603a = clr;
    }

    public final void accept(Object obj, Object obj2) {
        clr clr = this.f4603a;
        String str = (String) obj;
        if (((Boolean) obj2).booleanValue()) {
            clr.f4630f.mo3254a(str);
            clr.f4632h.mo7099b(ife.m12820a((Object) null), (Object) "storage_volume_data_service");
        }
        clr.mo3245a(true);
    }

    public final BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$$CC.andThen$$dflt$$(this, biConsumer);
    }
}
