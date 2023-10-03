package p000;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

/* renamed from: aeu */
/* compiled from: PG */
final class aeu extends Property {
    public aeu(Class cls, String str) {
        super(cls, str);
    }

    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        View view = (View) obj;
        return null;
    }

    public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
        View view = (View) obj;
        PointF pointF = (PointF) obj2;
        int round = Math.round(pointF.x);
        int round2 = Math.round(pointF.y);
        agb.m423a(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
    }
}
