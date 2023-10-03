package p000;

import android.database.Cursor;
import java.util.Iterator;
import p003j$.util.Optional;

/* renamed from: eeb */
/* compiled from: PG */
final /* synthetic */ class eeb implements hpr {

    /* renamed from: a */
    public static final hpr f8092a = new eeb();

    private eeb() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Object obj2;
        Iterator it = een.m7335a((Cursor) obj).iterator();
        if (it.hasNext()) {
            obj2 = ife.m12870b(it);
        } else {
            obj2 = null;
        }
        return Optional.ofNullable((edw) obj2);
    }
}
