package p000;

import android.os.Bundle;
import java.util.Collection;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiConsumer$$CC;

/* renamed from: clh */
/* compiled from: PG */
final /* synthetic */ class clh implements BiConsumer {

    /* renamed from: a */
    private final clr f4611a;

    public clh(clr clr) {
        this.f4611a = clr;
    }

    public final void accept(Object obj, Object obj2) {
        clr clr = this.f4611a;
        Bundle bundle = (Bundle) obj;
        if (!((Boolean) obj2).booleanValue()) {
            clr.mo3245a(true);
        } else if (bundle.containsKey("media_key")) {
            try {
                clr.mo3246b(1, hto.m12125a((Collection) imi.m14115b(bundle, "media_key", cyd.f5985i, clr.f4637m)));
            } catch (ijh e) {
                cwn.m5515b((Throwable) e, "FolderCreationFragmentPeer: Unable to unparcel selected media items", new Object[0]);
                clr.mo3245a(true);
            }
        }
    }

    public final BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$$CC.andThen$$dflt$$(this, biConsumer);
    }
}
