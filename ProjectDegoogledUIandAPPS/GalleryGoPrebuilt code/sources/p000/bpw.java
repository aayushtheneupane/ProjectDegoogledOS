package p000;

import android.content.ContentValues;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bpw */
/* compiled from: PG */
public final /* synthetic */ class bpw implements Consumer {

    /* renamed from: a */
    private final ContentValues f3330a;

    /* renamed from: b */
    private final String f3331b;

    public bpw(ContentValues contentValues, String str) {
        this.f3330a = contentValues;
        this.f3331b = str;
    }

    public final void accept(Object obj) {
        this.f3330a.put(this.f3331b, (Double) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
