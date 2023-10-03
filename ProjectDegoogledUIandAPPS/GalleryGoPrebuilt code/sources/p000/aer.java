package p000;

import android.graphics.PointF;
import android.util.Property;

/* renamed from: aer */
/* compiled from: PG */
final class aer extends Property {
    public aer(Class cls, String str) {
        super(cls, str);
    }

    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        aex aex = (aex) obj;
        return null;
    }

    public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
        aex aex = (aex) obj;
        PointF pointF = (PointF) obj2;
        aex.f285c = Math.round(pointF.x);
        aex.f286d = Math.round(pointF.y);
        int i = aex.f288f + 1;
        aex.f288f = i;
        if (aex.f287e == i) {
            aex.mo268a();
        }
    }
}
