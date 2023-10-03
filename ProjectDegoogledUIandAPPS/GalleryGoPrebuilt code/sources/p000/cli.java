package p000;

import android.os.Bundle;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiConsumer$$CC;

/* renamed from: cli */
/* compiled from: PG */
final /* synthetic */ class cli implements BiConsumer {

    /* renamed from: a */
    private final clr f4612a;

    public cli(clr clr) {
        this.f4612a = clr;
    }

    public final void accept(Object obj, Object obj2) {
        Bundle bundle = (Bundle) obj;
        this.f4612a.mo3245a(true);
        cwn.m5515b((Throwable) obj2, "FolderCreationFragmentPeer: Unable to get write permissions for media to move.", new Object[0]);
    }

    public final BiConsumer andThen(BiConsumer biConsumer) {
        return BiConsumer$$CC.andThen$$dflt$$(this, biConsumer);
    }
}
