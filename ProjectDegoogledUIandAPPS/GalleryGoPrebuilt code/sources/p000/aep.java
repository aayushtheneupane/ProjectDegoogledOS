package p000;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;

/* renamed from: aep */
/* compiled from: PG */
final class aep extends Property {

    /* renamed from: a */
    private final Rect f279a = new Rect();

    public aep(Class cls, String str) {
        super(cls, str);
    }

    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        ((Drawable) obj).copyBounds(this.f279a);
        return new PointF((float) this.f279a.left, (float) this.f279a.top);
    }

    public final /* bridge */ /* synthetic */ void set(Object obj, Object obj2) {
        Drawable drawable = (Drawable) obj;
        PointF pointF = (PointF) obj2;
        drawable.copyBounds(this.f279a);
        this.f279a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
        drawable.setBounds(this.f279a);
    }
}
