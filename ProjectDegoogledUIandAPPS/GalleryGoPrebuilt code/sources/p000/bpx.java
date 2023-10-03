package p000;

import android.content.ContentValues;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bpx */
/* compiled from: PG */
public final /* synthetic */ class bpx implements Consumer {

    /* renamed from: a */
    private final ContentValues f3332a;

    /* renamed from: b */
    private final String f3333b;

    public bpx(ContentValues contentValues, String str) {
        this.f3332a = contentValues;
        this.f3333b = str;
    }

    public final void accept(Object obj) {
        this.f3332a.put(this.f3333b, (String) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
