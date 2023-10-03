package p000;

import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiConsumer$$CC;

/* renamed from: clg */
/* compiled from: PG */
final /* synthetic */ class clg implements BiConsumer {

    /* renamed from: a */
    private final clr f4610a;

    public clg(clr clr) {
        this.f4610a = clr;
    }

    public final void accept(Object obj, Object obj2) {
        clr clr = this.f4610a;
        cwn.m5515b((Throwable) obj2, "FolderCreationFragmentPeer: Unable to request for write permission to %s.", (String) obj);
        clr.mo3245a(true);
    }

    public final BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$$CC.andThen$$dflt$$(this, biConsumer);
    }
}
