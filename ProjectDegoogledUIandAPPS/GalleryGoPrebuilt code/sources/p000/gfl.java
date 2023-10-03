package p000;

import android.util.Property;
import android.view.View;

/* renamed from: gfl */
/* compiled from: PG */
final class gfl extends Property {
    public gfl(Class cls, String str) {
        super(cls, str);
    }

    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        return Float.valueOf((float) ((View) obj).getLayoutParams().width);
    }

    public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
        View view = (View) obj;
        view.getLayoutParams().width = ((Float) obj2).intValue();
        view.requestLayout();
    }
}
