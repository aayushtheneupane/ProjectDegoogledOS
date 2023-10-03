package p000;

import android.util.Property;
import android.view.View;

/* renamed from: afz */
/* compiled from: PG */
final class afz extends Property {
    public afz(Class cls, String str) {
        super(cls, str);
    }

    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        return Float.valueOf(agb.m424b((View) obj));
    }

    public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
        agb.m421a((View) obj, ((Float) obj2).floatValue());
    }
}
