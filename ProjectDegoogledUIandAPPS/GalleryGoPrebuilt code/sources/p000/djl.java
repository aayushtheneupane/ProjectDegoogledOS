package p000;

import java.util.HashMap;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: djl */
/* compiled from: PG */
final /* synthetic */ class djl implements hpr {

    /* renamed from: a */
    public static final hpr f6667a = new djl();

    private djl() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        HashMap hashMap = new HashMap();
        for (Optional ifPresent : (List) obj) {
            ifPresent.ifPresent(new djm(hashMap));
        }
        return hashMap;
    }
}
