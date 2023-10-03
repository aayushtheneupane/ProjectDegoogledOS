package p000;

import android.database.Cursor;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: cyn */
/* compiled from: PG */
public final /* synthetic */ class cyn implements hpr {

    /* renamed from: a */
    public static final hpr f6041a = new cyn();

    private cyn() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        List a = dcm.m5898a((Cursor) obj);
        if (a.isEmpty()) {
            return Optional.empty();
        }
        return Optional.m16285of((cxi) a.get(0));
    }
}
