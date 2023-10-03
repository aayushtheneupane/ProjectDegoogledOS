package p000;

import android.graphics.PointF;
import android.util.Property;

/* renamed from: aeq */
/* compiled from: PG */
final class aeq extends Property {
    public aeq(Class cls, String str) {
        super(cls, str);
    }

    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        aex aex = (aex) obj;
        return null;
    }

    public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
        aex aex = (aex) obj;
        PointF pointF = (PointF) obj2;
        aex.f283a = Math.round(pointF.x);
        aex.f284b = Math.round(pointF.y);
        int i = aex.f287e + 1;
        aex.f287e = i;
        if (i == aex.f288f) {
            aex.mo268a();
        }
    }
}
