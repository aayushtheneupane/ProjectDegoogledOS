package p000;

import android.content.ContentValues;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bpv */
/* compiled from: PG */
public final /* synthetic */ class bpv implements Consumer {

    /* renamed from: a */
    private final ContentValues f3328a;

    /* renamed from: b */
    private final String f3329b;

    public bpv(ContentValues contentValues, String str) {
        this.f3328a = contentValues;
        this.f3329b = str;
    }

    public final void accept(Object obj) {
        this.f3328a.put(this.f3329b, (Integer) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
