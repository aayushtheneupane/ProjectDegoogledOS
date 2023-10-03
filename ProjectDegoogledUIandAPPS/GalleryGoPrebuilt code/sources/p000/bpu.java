package p000;

import android.content.ContentValues;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bpu */
/* compiled from: PG */
public final /* synthetic */ class bpu implements Consumer {

    /* renamed from: a */
    private final ContentValues f3326a;

    /* renamed from: b */
    private final String f3327b;

    public bpu(ContentValues contentValues, String str) {
        this.f3326a = contentValues;
        this.f3327b = str;
    }

    public final void accept(Object obj) {
        this.f3326a.put(this.f3327b, (Long) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
