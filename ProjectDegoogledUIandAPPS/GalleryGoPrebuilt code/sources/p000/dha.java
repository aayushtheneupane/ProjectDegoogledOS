package p000;

import java.util.ArrayList;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: dha */
/* compiled from: PG */
public final /* synthetic */ class dha implements hpr {

    /* renamed from: a */
    public static final hpr f6531a = new dha();

    private dha() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Optional ifPresent : (List) obj) {
            arrayList.getClass();
            ifPresent.ifPresent(new dhb(arrayList));
        }
        return arrayList;
    }
}
